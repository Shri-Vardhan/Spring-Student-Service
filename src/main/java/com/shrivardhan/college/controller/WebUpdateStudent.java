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

import com.shrivardhan.college.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
@Controller
public class WebUpdateStudent {

    @GetMapping("/web/updateStudent/{id}")
    public String showUpdateForm(@PathVariable Long id, Model model) {
        model.addAttribute("id", id);
        return "updateStudent";
    }
}