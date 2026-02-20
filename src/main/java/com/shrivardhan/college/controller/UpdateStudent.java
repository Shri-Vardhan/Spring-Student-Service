package com.shrivardhan.college.controller;

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
}