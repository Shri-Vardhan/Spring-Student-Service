package com.shrivardhan.college.controller;

import com.shrivardhan.college.model.Student;
import com.shrivardhan.college.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class WebInsertStudent {

    @Autowired
    private StudentService service;

    @GetMapping("/web/insertStudent")
    public String insertStudent() {
        return "addStudent";
    }

    @PostMapping("/web/insertStudent")
    public String processInsertStudent(Student student, Model model) {
        service.insertStudent(student);
        model.addAttribute("message", "Student created successfully");
        return "addStudent";
    }
}
