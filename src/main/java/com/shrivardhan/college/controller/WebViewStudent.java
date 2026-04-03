package com.shrivardhan.college.controller;

import com.shrivardhan.college.model.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Controller
public class WebViewStudent {

    private final RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/web/viewStudents")
    public String students(
            @RequestParam(defaultValue = "0") int page,
            Model model) {

        int size = 10;

        String url = "http://localhost:8080/api/viewStudents?page=" + page + "&size=" + size;

        Student[] studentsArray = restTemplate.getForObject(url, Student[].class);

        List<Student> students = studentsArray != null
                ? Arrays.asList(studentsArray)
                : Collections.emptyList();

        Integer totalCount = restTemplate.getForObject(
                "http://localhost:8080/api/viewStudents/count",
                Integer.class
        );

        int totalPages = (int) Math.ceil((double) totalCount / size);

        model.addAttribute("students", students);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);

        return "viewStudents";
    }
}