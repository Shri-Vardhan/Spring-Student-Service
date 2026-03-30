package com.shrivardhan.college.controller;

import com.shrivardhan.college.service.StudentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/deleteStudent")
public class DeleteStudent {

    private final StudentService studentService;

    public DeleteStudent(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/{id}")
    public String deleteStudent(@PathVariable int id) {
        int status = studentService.deleteStudent(id);
        return (status == 1)
                ? "Student deleted successfully."
                : "Failed to delete student record.";
    }
}