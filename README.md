Student Management System
-------------------------
-------------------------

System Architecture
-------------------

Browser (HTML + Thymeleaf + JavaScript)
↓
Web Controllers
↓
REST APIs
↓
Service Layer
↓
Database

------------------------------------------------------------

Complete Application Flow
-------------------------

URL:
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

------------------------------------------------------------

Insert Student Flow
-------------------

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

------------------------------------------------------------

Delete Student Flow
-------------------

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

------------------------------------------------------------

Update Student Flow (Under Development)
---------------------------------------

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
Under Development — Not Functional

Reason:

Partial integration between frontend JavaScript Fetch API and backend REST update endpoint.
Request mapping and request body handling require refinement.

------------------------------------------------------------

Current Feature Status
----------------------

View Students — Implemented
Insert Student — Implemented
Delete Student — Implemented
Update Student — Under Development

------------------------------------------------------------