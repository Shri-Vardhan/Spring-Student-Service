/*
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
*/
package com.shrivardhan.college.controller;

import com.shrivardhan.college.model.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Controller
public class WebViewStudent {

    private final RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/web/viewStudents")
    public String students(Model model) {

        String url = "http://localhost:8080/api/viewStudents";
        Student[] studentsArray =
                restTemplate.getForObject(url, Student[].class);
        List<Student> students = Arrays.asList(studentsArray);
        model.addAttribute("students", students);
        return "viewStudents";
    }
}