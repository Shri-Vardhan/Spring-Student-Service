Feature: Student Operations

  Scenario: Add student
    Given admin is on add student page
    When admin enters student details
    Then student should be added

  Scenario: View students
    Given students exist
    When admin opens students page
    Then students should be displayed

  Scenario: Update student name
    Given a student exists
    When admin updates the student name
    Then student name should be updated

  Scenario: Delete student
    Given a student exists
    When admin deletes the student
    Then student should be removed
