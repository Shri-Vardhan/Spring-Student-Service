# Student Management System
> Spring Boot REST API • Oracle DB • JdbcTemplate

![Java](https://img.shields.io/badge/Java-25-blue) ![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.0.1-green) ![Oracle](https://img.shields.io/badge/Oracle-PDB-red)

## Overview

A production-structured Spring Boot REST application that performs full CRUD operations on student records stored in an Oracle Pluggable Database (PDB). Designed to mirror real enterprise Java backends — with a clean layered architecture, environment-aware configuration via Spring Profiles, structured logging with Log4j, and both REST API and Thymeleaf-based web interfaces.

---

## Architecture

| Layer | Package | Responsibility |
|-------|---------|----------------|
| Controller | `com.shrivardhan.college.controller` | Exposes REST endpoints and web MVC routes |
| Service | `com.shrivardhan.college.service` | Business logic, validation, exception handling |
| Repository | `com.shrivardhan.college.repository` | JdbcTemplate SQL queries against Oracle PDB |
| Model | `com.shrivardhan.college.model` | Java objects for DB row mapping |

---

## Tech Stack

| Technology | Details |
|-----------|---------|
| Java | 25 (via Gradle toolchain) |
| Spring Boot | 4.0.1 — embedded Tomcat |
| Spring JDBC | JdbcTemplate — explicit SQL, no ORM overhead |
| Oracle DB | Oracle PDB 21c (`ojdbc11:23.3.0.23.09`) |
| Thymeleaf | Server-side HTML templating |
| Log4j | Structured application logging |
| Gradle | Build tool |
| Spring Profiles | Environment-specific configuration (dev / prod) |

---

## REST API Reference

| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/api/viewStudents` | List students with pagination (`?page=0&size=10`) |
| `GET` | `/api/viewStudents/count` | Return total student count |
| `GET` | `/api/GetStudent/{id}` | Retrieve a student by ID |
| `POST` | `/api/insertStudent` | Insert a new student (JSON body) |
| `PUT` | `/api/updateStudent` | Update student name and age (JSON body) |
| `GET` | `/api/deleteStudent/{id}` | Delete a student by ID |

---

## Prerequisites

- Java 25+
- Gradle 8+
- Oracle Database PDB accessible on the network

---

## Getting Started

### 1. Clone the Repository

```bash
git clone https://github.com/shrivardhan/college-student-management.git
cd college-student-management
```

### 2. Configure the Database

Edit the appropriate profile config under `src/main/resources/`:

```yaml
# application-dev.yml
spring:
  datasource:
    url: jdbc:oracle:thin:@//192.168.0.120:1521/ORCLPDB
    username: dev_user
    password: dev_pass
    driver-class-name: oracle.jdbc.OracleDriver
```

### 3. Create the Student Table

```sql
CREATE TABLE student (
  id   NUMBER PRIMARY KEY,
  name VARCHAR2(100),
  age  NUMBER
);
```

### 4. Build and Run

```bash
# Run with dev profile
./gradlew bootRun --args='--spring.profiles.active=dev'

# Or build a JAR
./gradlew build
java -jar build/libs/college-1.0-SNAPSHOT.jar --spring.profiles.active=prod
```

### 5. Access the Application

- REST API: `http://localhost:8080/api`
- Web UI: `http://localhost:8080/web/viewStudents`

---

## Exception Handling

A global `@RestControllerAdvice` returns structured JSON error responses:

| Exception | HTTP Status | When Thrown |
|-----------|-------------|-------------|
| `StudentAlreadyExistsException` | 409 CONFLICT | Student ID already exists |
| `StudentNotFoundException` | 404 NOT FOUND | Requested ID does not exist |
| `DataAccessResourceFailureException` | 503 UNAVAILABLE | Cannot reach Oracle DB |

---

## Spring Profiles

| Profile | Config File | For |
|---------|-------------|-----|
| default | `application.yml` | Shared / fallback |
| dev | `application-dev.yml` | Local development VM |
| prod | `application-prod.yml` | Production Oracle PDB |

---

## Deployment Topology

Runs across two Virtual Machines:

- **VM 1 (Linux):** Oracle Database PDB — listener on port `1521`
- **VM 2 (Windows):** Spring Boot app — connects to remote Oracle PDB via JDBC

---

## Project Structure

```text
src/main/java/com/shrivardhan/college/
├── controller/
├── service/
├── repository/
├── model/
└── exception/

src/main/resources/
├── features/
├── static/
├── application.yml
├── application-dev.yml
├── application-prod.yml
└── templates/
```
