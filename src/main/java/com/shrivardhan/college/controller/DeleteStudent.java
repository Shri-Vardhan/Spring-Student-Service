package com.shrivardhan.college.controller;

import com.shrivardhan.college.service.StudentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/deleteStudent")
public class DeleteStudent {

    private final StudentService studentService;

    public DeleteStudent(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/{id}")
    public void deleteStudent(@PathVariable int id) {
        studentService.deleteStudent(id);
    }
}