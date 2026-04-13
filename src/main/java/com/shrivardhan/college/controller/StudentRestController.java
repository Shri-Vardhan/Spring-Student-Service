package com.shrivardhan.college.controller;

import com.shrivardhan.college.model.Student;
import com.shrivardhan.college.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentRestController {

    private final StudentService studentService;

    public StudentRestController(StudentService studentService) {
        this.studentService = studentService;
    }

    // GET ALL
    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getStudents();
    }

    // GET BY ID
    @GetMapping("/{id}")
    public Student getStudent(@PathVariable Long id) {
        return studentService.retrieveStudent(id);
    }

    // INSERT
    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return studentService.insertStudent(student);
    }

    // UPDATE
    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable Long id,
                                 @RequestParam String name,
                                 @RequestParam Integer age) {
        return studentService.updateStudent(id, name, age);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return "Student deleted successfully";
    }

    /***************************************************************************************
     **************************************************************************************
     **************************************************************************************
     ***************************************************************************************/

    //Method-Name
    @GetMapping("/search")
    public List<Student> searchStudents(
            @RequestParam String name,
            @RequestParam int age
    ) {
        return studentService.getStudentsByNameAndAge(name, age);
    }

    //JPL-Query
    @GetMapping("/search-JPLquery")
    public List<Student> searchJPLquery(
            @RequestParam String name,
            @RequestParam int age
    ) {
        return studentService.getStudentsJPLquery(name, age);
    }

    //Native-SQL
    @GetMapping("/search-native")
    public List<Student> searchNative(
            @RequestParam String name,
            @RequestParam int age
    ) {
        return studentService.getStudentsNative(name, age);
    }
}