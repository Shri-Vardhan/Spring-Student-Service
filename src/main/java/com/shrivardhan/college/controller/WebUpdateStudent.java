/*package com.shrivardhan.college.controller;

import com.shrivardhan.college.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class WebUpdateStudent {

    @Autowired
    private StudentService service;

    @GetMapping("/web/updateStudent/{id}")
    public String showUpdateForm(@PathVariable Long id, Model model) {
        model.addAttribute("id", id);
        return "updateStudent";
    }

    @PostMapping("/updateStudentName")
    public String updateStudentName(
            @RequestParam Long id,
            @RequestParam String name) {

        service.updateStudentName(id, name);
        return "update";
    }

}*/
package com.shrivardhan.college.controller;

import com.shrivardhan.college.model.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@Controller
public class WebUpdateStudent {

    private final RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/web/updateStudent/{id}")
    public String showStudent(@PathVariable Long id, Model model) {
        String url = "http://localhost:8080/api/getStudent/" + id;
        Student student = restTemplate.getForObject(url, Student.class);
        model.addAttribute("student", student);
        return "updateStudent";
    }
    @PostMapping("/web/updateStudent/{id}")
    public String updateStudent(@PathVariable Long id,
                                @RequestParam String name,
                                @RequestParam Integer age,
                                Model model) {
        String url = "http://localhost:8080/api/updateStudent/" + id + "?name=" + name + "&age=" + age;
        restTemplate.put(url, null);

        // Re-fetch updated student
        String getUrl = "http://localhost:8080/api/getStudent/" + id;
        Student student = restTemplate.getForObject(getUrl, Student.class);
        model.addAttribute("student", student);
        model.addAttribute("message", "Student updated successfully!");
        return "updateStudent";
    }

}