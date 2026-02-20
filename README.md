System Architecture
Browser (HTML + Thymeleaf + JavaScript)
↓
Web Controllers
↓
REST APIs
↓
Service Layer
↓
Database

Complete Application Flow

RL:

/web/viewStudents

Flow:

Browser
↓
Web Controller (/web/viewStudents)
↓
REST API → /api/viewStudents
↓
StudentService → Database
↓
JSON Response
↓
Converted → List<Student>
↓
Thymeleaf → HTML Table

Purpose:
Displays all students in a tabular format using Thymeleaf.

3.2 Insert Student Flow

URL:

/web/insertStudent

Flow:

Browser Form
↓
Web Controller (/web/insertStudent)
↓
REST API → /api/insertStudent
↓
StudentService → Database
↓
Success Response → UI

Purpose:
Creates a new student record using REST-based backend communication.

3.3 Delete Student Flow

URL:

/web/deleteStudent

Flow:

Browser Form
↓
Web Controller (/web/deleteStudent)
↓
REST API → /api/deleteStudent/{id}
↓
StudentService → Database
↓
Deletion Confirmation → UI

Purpose:
Deletes a student record based on student ID.

3.4 Update Student Flow (⚠️ Currently Not Working)

URL:

/web/updateStudent/{id}

Intended Flow:

Browser
↓
Load Page → /web/updateStudent/{id}
↓
JS Fetch → GET /api/GetStudent/{id}
↓
Populate Form Fields
↓
JS Fetch → PUT /api/updateStudent/{id}
↓
StudentService → Database

Current Status:
⚠️ Under Development — Not Functional at This Time

Reason:

Partial integration between frontend JavaScript Fetch API and backend REST update endpoint

Request mapping and request body handling still require refinement

Current Feature Status
Feature	Status
View Students	✅ Implemented
Insert Student	✅ Implemented
Delete Student	✅ Implemented
Update Student	⚠️ Under Development