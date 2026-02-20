package com.shrivardhan.college.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

@Controller
public class WebDeleteStudent {

    private final RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/web/deleteStudent")
    public String deleteStudent(Model model) {
        model.addAttribute("message", "Enter Student id to delete");
        return "deleteStudent";
    }

    @PostMapping("/web/deleteStudent")
    public String processDeleteStudent(@RequestParam Long id, Model model) {
        String url = "http://localhost:8080/api/deleteStudent/" + id;
        restTemplate.delete(url);
        model.addAttribute("message", "Student deleted successfully");
        return "deleteStudent";
    }
}
