package com.shrivardhan.college.controller;


import com.shrivardhan.college.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/updateStudent")
public class UpdateStudent {
    @Autowired
    private StudentService service;
    @PutMapping("/{id}")
    public String UpdateStudent(
            @PathVariable Long id,
            @RequestParam String name) {
        service.updateStudentName(id, name);
        return "Student name updated successfully";
    }
}
