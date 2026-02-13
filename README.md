Spring Boot JDBC REST API – Enterprise Application

Overview
--------

This project is an enterprise-grade Spring Boot REST API built using Spring JDBC for database interactions. It is designed following real-world company standards with clear separation of environments (dev / prod), secure configuration management, containerized deployment using Docker, and CI/CD integration via Jenkins.

The application exposes RESTful endpoints, connects to a relational database using JDBC templates, and supports profile-based configuration for development and production environments.

Technology Stack
----------------

Java: 17

Spring Boot: 3.x

Spring JDBC (JdbcTemplate)

REST API (Spring Web)

Database: Oracle Pluggable DB (Dev-Server)

Build Tool: Gradle

Logging Tool: log4j | slf4j

Containerization: Docker

CI/CD: Jenkins

Config Files: yml , json

Version Control: Git (Bitbucket / GitHub)

Total profiles = 3 (default, prod and dev) If profile not specified in execution command, then system will load application-default.yml (configured in .pro file)

Commands to execute on the terminal
-----------------------------------
### Method 01 - Creating Jar then executing ###
./gradlew clean build
java -jar build\libs\javaexamples-1.0-SNAPSHOT.jar java -jar build\libs\javaexamples-1.0-SNAPSHOT.jar --spring.profiles.active=dev

### Method 02 - With out jar ###
./gradlew bootRun ./gradlew bootRun --args='--spring.profiles.active=dev' ./gradlew bootRun --args='--spring.profiles.active=prod'

### Rest API Command ###
To Make a build -> ./gradlew clean build To start spring embedded tomcat -> java -jar build\libs\javaexamples-1.0-SNAPSHOT.jar To get records (HTTP Request) -> http://localhost:8080/students To delete a student record (HTTP Request) -> http://localhost:8080/deletestudents/{id}

# Exception - Scenario 01 #

{
"timestamp": "2026-02-13T21:45:11.9826378",
"status": 409,
"error": "CONFLICT",
"message": "Student with ID 105 already exists",
"path": "/insertstudents"
}

# Exception - Scenario 02 #
Expected output on the browser - when DB is not available

{
"timestamp": "2026-02-13T21:50:28.3937278",
"status": 503,
"error": "SERVICE_UNAVAILABLE",
"message": "Failed to obtain JDBC Connection",
"path": "/students"
}



