package com.shrivardhan.college.controller;

import com.shrivardhan.college.model.Student;
import com.shrivardhan.college.service.StudentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/GetStudent")
public class GetStudent {

    private final StudentService studentService;

    public GetStudent(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/{id}")
    public Student retrieve(@PathVariable long id) {
        return studentService.retrieveStudent(id);
    }

}