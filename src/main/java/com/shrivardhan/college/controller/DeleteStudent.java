package com.shrivardhan.college.controller;

import com.shrivardhan.college.service.StudentService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/deleteStudent")
public class DeleteStudent {

    private final StudentService studentService;

    public DeleteStudent(StudentService studentService) {
        this.studentService = studentService;
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
    }
}