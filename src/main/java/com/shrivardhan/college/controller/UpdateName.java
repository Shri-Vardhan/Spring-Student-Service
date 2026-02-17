package com.shrivardhan.college.controller;

import com.shrivardhan.college.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UpdateName {
        private final StudentService studentService;

        public UpdateName(StudentService studentService) {
            this.studentService = studentService;
        }

        @GetMapping("/updateStudentName")
        public String showUpdateForm() {
        return "update";
         }

        @PostMapping("/updateStudentName")
        public String updateStudentName(
                @RequestParam Long id,
                @RequestParam String name) {

            studentService.updateStudentName(id, name);
            return "update";
        }
}