
package com.shrivardhan.college.controller;

import com.shrivardhan.college.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WebDeleteStudent {
    @Autowired
    private StudentService service;

    @GetMapping("/web/deleteStudent")
    public String deleteStudent() {
        return "deleteStudent";
    }

    @PostMapping("/web/deleteStudent")
    public String processDeleteStudent(@RequestParam Long id, Model model) {
        service.deleteStudent(id);
        model.addAttribute("message", "Student deleted successfully");
        return "deleteStudent";
    }
}
