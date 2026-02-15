package com.shrivardhan.college.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

@Controller
public class InsertStudentController {
    @GetMapping("/insertStudent")
    public String showInsertStudentPage(Model model) {
        model.addAttribute("message", "Welcome to Insert Student Page");
        return "insertStudent"; // maps to insertStudent.html
    }
}
