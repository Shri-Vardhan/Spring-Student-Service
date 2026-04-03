package com.shrivardhan.college.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.context.annotation.PropertySource;

@Controller
@PropertySource("classpath:url.properties")
public class WebDeleteStudent {

    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${student.delete.url}")
    private String deleteUrl;

    @GetMapping("/web/deleteStudent")
    public String deleteStudent(Model model) {
        model.addAttribute("message", "Enter Student id to delete");
        return "deleteStudent";
    }

    @PostMapping("/web/deleteStudent")
    public String processDeleteStudent(@RequestParam Long id, Model model) {
        String url = deleteUrl + "/" + id;
        String forObject = restTemplate.getForObject(url, String.class);
        
        model.addAttribute("message", forObject);
        return "deleteStudent";
    }
}
