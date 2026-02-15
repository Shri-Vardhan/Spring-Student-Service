package com.shrivardhan.college.controller;

import com.shrivardhan.college.model.Student;
import com.shrivardhan.college.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RequestMapping("/students")
public class Studentcontroller {

    private final StudentService studentService;
    public Studentcontroller(StudentService studentService){
        this.studentService = studentService;
    }


    @GetMapping
    public String getAllStudents(Model model){
        List<Student> students = studentService.getStudents();
        model.addAttribute("students", students);
        return "students";   // refers to test.html
    }

}


