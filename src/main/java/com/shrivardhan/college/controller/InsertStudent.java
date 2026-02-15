package com.shrivardhan.college.controller;

import com.shrivardhan.college.model.Student;
import com.shrivardhan.college.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class InsertStudent {
    private final StudentService studentService;

    public InsertStudent(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/insertStudent")
    public String processInsertStudent(Student student) {
        studentService.insertStudent(student);
        return "Student created successfully";
    }
}