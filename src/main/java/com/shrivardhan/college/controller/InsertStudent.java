package com.shrivardhan.college.controller;

import com.shrivardhan.college.model.Student;
import com.shrivardhan.college.service.StudentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/insertstudents")
public class InsertStudent {

    private final StudentService studentService;

    public InsertStudent(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public String insertStudent(@RequestBody Student student) {
        studentService.insertStudent(student);
        return "Student created successfully";
    }
}