# Spring Boot REST API for Managing Student Records

A Spring Boot REST API for managing student records backed by Oracle via **Spring Data JPA**.

---

## Data Model

### Student Entity

Mapped to the `student` table in Oracle.

| Field | Java Type | Column | Notes |
|---|---|---|---|
| id | Long | id | Primary key, caller supplied |
| name | String | name | Student full name |
| age | Integer | age | Student age |

```java
@Entity
@Table(name = "student")
public class Student {

    @Id
    private Long id;

    private String name;
    private Integer age;
}
```

---

## Repository
```java
public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findByNameAndAgeGreaterThan(String name, int age);

    @Query("SELECT s FROM Student s WHERE s.name = :name AND s.age > :age")
    List<Student> findStudents(@Param("name") String name,
                               @Param("age") int age);

    @Query(value = "SELECT * FROM student WHERE name = :name AND age > :age",
           nativeQuery = true)
    List<Student> findStudentNative(@Param("name") String name,
                                    @Param("age") int age);
}
```

---

# JPA Querying Approaches

This project demonstrates **3 ways to write queries in Spring Data JPA**.

---

## 1) Method Name Query

### Repository
```java
List<Student> findByNameAndAgeGreaterThan(String name, int age);
```

### SQL Generated
```sql
SELECT *
FROM student
WHERE name = ?
AND age > ?
```

### API
```http
GET http://192.168.0.121:8080/api/students/search?name=Test&age=17
```

---

## 2) JPQL Query

### Repository
```java
@Query("SELECT s FROM Student s WHERE s.name = :name AND s.age > :age")
List<Student> findStudents(@Param("name") String name,
                           @Param("age") int age);
```

### Equivalent SQL
```sql
SELECT *
FROM student
WHERE name = ?
AND age > ?
```

### API
```http
GET http://192.168.0.121:8080/api/students/search-JPLquery?name=Test&age=17
```

---

## 3) Native SQL Query

### Repository
```java
@Query(value = "SELECT * FROM student WHERE name = :name AND age > :age",
       nativeQuery = true)
List<Student> findStudentNative(@Param("name") String name,
                                @Param("age") int age);
```

### SQL
```sql
SELECT *
FROM student
WHERE name = ?
AND age > ?
```

### API
```http
GET http://192.168.0.121:8080/api/students/search-native?name=Test&age=17
```

---

## Service Layer
Service usage remains identical.

```java
public List<Student> getStudentsByNameAndAge(String name, int age) {
    return repository.findByNameAndAgeGreaterThan(name, age);
}

public List<Student> getStudentsJPLquery(String name, int age) {
    return repository.findStudents(name, age);
}

public List<Student> getStudentsNative(String name, int age) {
    return repository.findStudentNative(name, age);
}
```

---