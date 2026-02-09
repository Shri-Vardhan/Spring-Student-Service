package com.shrivardhan.college.controller;

import com.shrivardhan.college.model.Student;
import com.shrivardhan.college.service.StudentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/insertstudents")
public class Insertstudent {

    private final StudentService studentService;

    public Insertstudent(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public String createStudent(@RequestBody Student student) {
        studentService.createStudent(student);
        return "Student created successfully";
    }
}