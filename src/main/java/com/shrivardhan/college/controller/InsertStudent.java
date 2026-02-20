package com.shrivardhan.college.controller;

import com.shrivardhan.college.model.Student;
import com.shrivardhan.college.service.StudentService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/insertStudent")
public class InsertStudent {

    private final StudentService studentService;

    public InsertStudent(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public String insertStudent(@RequestBody Student student) {
        studentService.insertStudent(student);
        return "Student created successfully !!!!";
    }
}