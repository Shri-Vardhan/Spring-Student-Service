package com.shrivardhan.college.controller;

import com.shrivardhan.college.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebViewStudent {

    @Autowired
    private StudentService service;

    @GetMapping("/web/viewStudents")
    public String students(Model model) {
        model.addAttribute("students", service.getStudents());
        return "viewStudents";
    }
}