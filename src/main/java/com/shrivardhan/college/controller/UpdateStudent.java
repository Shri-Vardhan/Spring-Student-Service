/*package com.shrivardhan.college.controller;

import com.shrivardhan.college.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/updateStudent")
public class UpdateStudent {
    private final StudentService service;
    public UpdateStudent(StudentService service) {
        this.service = service;
    }
    @PutMapping("/{id}")
    public String updateStudent(
            @PathVariable Long id,
            @RequestBody String name) {
        // RequestBody to carry student model object, to update any field
        service.updateStudentName(id, name);
        return "Student name updated successfully";
    }
}*/
package com.shrivardhan.college.controller;

import com.shrivardhan.college.model.Student;
import com.shrivardhan.college.service.StudentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/updateStudent")
public class UpdateStudent {

    private final StudentService studentService;

    public UpdateStudent(StudentService studentService) {
        this.studentService = studentService;
    }

    @PutMapping
    public String updateStudent(@RequestBody Student student) {
        int rowsAffected = studentService.updateStudent(student);

        return (rowsAffected > 0)
                ? "Student record updated successfully."
                : "Student record not found.";
    }
}