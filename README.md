# Spring Boot REST API for Managing Student Records

A Spring Boot REST API for managing student records backed by Oracle via Spring Data JPA.

## Data Model

### Student Entity

Mapped to the `student` table in Oracle.

| Field | Java Type | Column | Notes                                             |
| ----- | --------- | ------ | ------------------------------------------------- |
| id    | Long      | id     | Primary key — caller-supplied, not auto-generated |
| name  | String    | name   | Student's full name                               |
| age   | Integer   | age    | Student's age                                     |

```java
@Entity
@Table(name = "student")
public class Student {

    @Id
    private Long id;   // No @GeneratedValue — ID must be provided in the request body

    private String name;
    private Integer age;
}
```

**Important:** There is no `@GeneratedValue` on the `id` field. The client is responsible for supplying a unique ID on every `POST` request. If the ID already exists, the API returns `409 Conflict`.

---

## JPA & Database Layer

### Repository

```java
public interface StudentRepository extends JpaRepository<Student, Long> {
}
```

`JpaRepository<Student, Long>` provides the following out of the box — no additional code needed:

| Method           | SQL Equivalent                              | Used by                     |
| ---------------- | ------------------------------------------- | --------------------------- |
| `findAll()`      | `SELECT * FROM student`                     | `GET /api/students`         |
| `findById(id)`   | `SELECT * FROM student WHERE id = ?`        | `GET /api/students/{id}`    |
| `existsById(id)` | `SELECT COUNT(*) FROM student WHERE id = ?` | `POST duplicate check`      |
| `save(student)`  | `INSERT or UPDATE (merge)`                  | `POST, PUT`                 |
| `deleteById(id)` | `DELETE FROM student WHERE id = ?`          | `DELETE /api/students/{id}` |

### How `save()` decides INSERT vs UPDATE

Hibernate checks whether the entity is "new" using the `id` field:

* If `id` is `null` → `INSERT`
* If `id` is set and the row exists → `UPDATE (merge)`
* If `id` is set but the row does not exist → `INSERT` of a new row with that ID

In this project, `existsById()` is called manually before `save()` during `POST` to prevent accidental overwrites.

### DDL Behaviour

Controlled by `spring.jpa.hibernate.ddl-auto`:

| Value      | What Hibernate does on startup                                                                 |
| ---------- | ---------------------------------------------------------------------------------------------- |
| `update`   | Creates the table if it doesn't exist; alters columns if the entity changes. Never drops data. |
| `create`   | Drops and recreates the table every startup. All data is lost.                                 |
| `validate` | Checks that the schema matches the entity. Fails fast if there's a mismatch.                   |
| `none`     | Does nothing. Schema managed externally (recommended for production).                          |

---

## API Reference

**Base URL:** `/api/students`
All responses are `application/json`.
All request bodies must be `Content-Type: application/json`.

### GET `/api/students` — List All Students

Fetches every student row from the database. No filtering or pagination — returns the full table.

#### Request

```http
GET /api/students
```

#### Response `200 OK`

```json
[
  { "id": 1, "name": "Ravi Kumar", "age": 21 },
  { "id": 2, "name": "Priya Sharma", "age": 22 }
]
```

Returns an empty array `[]` if no students exist.

---

### GET `/api/students/{id}` — Get Student by ID

Looks up a single student by their primary key.

#### Request

```http
GET /api/students/1
```

#### Response `200 OK`

```json
{ "id": 1, "name": "Ravi Kumar", "age": 21 }
```

Failure case: If the ID does not exist, a plain `RuntimeException` is thrown with the message `"Student not found"`. This is currently unhandled — no `404` is returned; Spring Boot will respond with a `500`. A dedicated `StudentNotFoundException` mapped to `404 Not Found` should be added.

---

### POST `/api/students` — Create a Student

Inserts a new student. The `id` field must be included in the body — the server does not generate it.

#### Request

```http
POST /api/students
Content-Type: application/json
```

```json
{ "id": 3, "name": "Anil Reddy", "age": 20 }
```

What happens internally:

1. `existsById(3)` is called first.
2. If a student with `id = 3` already exists → `409 Conflict`
3. If not → `repository.save(student)` runs an `INSERT`

#### Response `200 OK`

```json
{ "id": 3, "name": "Anil Reddy", "age": 20 }
```

#### Error `409 Conflict`

```json
{
  "timestamp": "2025-04-12T10:30:00",
  "status": 409,
  "error": "CONFLICT",
  "message": "Student already exists",
  "path": "/api/students"
}
```

---

### PUT `/api/students/{id}` — Update a Student

Updates the `name` and `age` of an existing student. Fields are passed as query parameters, not a request body.

#### Request

```http
PUT /api/students/3?name=Anil%20Kumar&age=21
```

| Parameter | Type    | Location | Required | Description                 |
| --------- | ------- | -------- | -------- | --------------------------- |
| id        | Long    | Path     | Yes      | ID of the student to update |
| name      | String  | Query    | Yes      | New name value              |
| age       | Integer | Query    | Yes      | New age value               |

What happens internally:

1. `retrieveStudent(id)` fetches the existing entity (`SELECT`)
2. `setName()` and `setAge()` are called on the managed entity
3. `repository.save(student)` runs an `UPDATE` on the same row

#### Response `200 OK`

```json
{ "id": 3, "name": "Anil Kumar", "age": 21 }
```

**Note:** If the `id` does not exist, this hits the same unhandled `RuntimeException` as the GET — results in a `500`. Same fix applies.

---

### DELETE `/api/students/{id}` — Delete a Student

Removes a student row by ID.

#### Request

```http
DELETE /api/students/3
```

#### Response `200 OK`

```text
Student deleted successfully
```

**Note:** `deleteById()` from JPA does not throw an exception if the ID does not exist — it silently does nothing. The success message is returned regardless of whether a row was actually deleted.

---

## Error Handling

Errors are caught in `GlobalExceptionHandler` (`@RestControllerAdvice`) and returned as a consistent JSON structure.

### Error Response Shape

```json
{
  "timestamp": "2025-04-12T10:30:00",
  "status": 409,
  "error": "CONFLICT",
  "message": "Human-readable description",
  "path": "/api/students"
}
```

### Handled Exceptions

| Exception                            | Status                    | When it fires                                             |
| ------------------------------------ | ------------------------- | --------------------------------------------------------- |
| `StudentAlreadyExistsException`      | `409 Conflict`            | POST with an ID that already exists                       |
| `DataAccessResourceFailureException` | `503 Service Unavailable` | Oracle is unreachable or the connection pool is exhausted |

### Unhandled (known gaps)

| Scenario                                | Current behaviour             | Should be       |
| --------------------------------------- | ----------------------------- | --------------- |
| GET `/api/students/999` (missing ID)    | `500 Internal Server Error`   | `404 Not Found` |
| PUT `/api/students/999` (missing ID)    | `500 Internal Server Error`   | `404 Not Found` |
| DELETE `/api/students/999` (missing ID) | `200 OK with success message` | `404 Not Found` |

---

## Environment Configuration

The application uses Spring Profiles to separate credentials and settings per environment.

The active profile is set in `application.properties`:

```properties
spring.profiles.active=default
```
