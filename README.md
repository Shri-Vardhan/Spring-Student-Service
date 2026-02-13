# Exception - Scenario 01 #
Expected output on the browser - when user try to insert a duplicate ID 105

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