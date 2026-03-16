/*package com.shrivardhan.college.controller;

import com.shrivardhan.college.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/updateStudent")
public class UpdateStudent {
    private final StudentService service;
    public UpdateStudent(StudentService service) {
        this.service = service;
    }
    @PutMapping("/{id}")
    public String updateStudent(
            @PathVariable Long id,
            @RequestBody String name) {
        // RequestBody to carry student model object, to update any field
        service.updateStudentName(id, name);
        return "Student name updated successfully";
    }
}*/
package com.shrivardhan.college.controller;

import com.shrivardhan.college.model.Student;
import com.shrivardhan.college.service.StudentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UpdateStudent {

    private final StudentService studentService;

    public UpdateStudent(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/getStudent/{id}")
    public Student getStudent(@PathVariable Long id) {
        return studentService.retrieveStudent(id);
    }

    @PutMapping("/updateStudent/{id}")
    public String updateStudent(@PathVariable Long id,
                                @RequestParam String name,
                                @RequestParam Integer age) {
        studentService.updateStudent(id, name, age);
        return "Updated successfully";
    }
}