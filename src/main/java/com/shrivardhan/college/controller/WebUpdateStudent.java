package com.shrivardhan.college.controller;

import com.shrivardhan.college.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WebUpdateStudent {

    @Autowired
    private StudentService service;

    @GetMapping("/web/updateStudent")
    public String showUpdateForm() {
        return "updateStudent";
    }

    @PostMapping("/updateStudentName")
    public String updateStudentName(
            @RequestParam Long id,
            @RequestParam String name) {

        service.updateStudentName(id, name);
        return "update";
    }
}