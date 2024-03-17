package com.nhnacademy.edu.springboot.student.controller;

import com.nhnacademy.edu.springboot.student.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/student")
public class StudentListController {
    private final StudentService studentService;

    public StudentListController(StudentService studentService) {
        this.studentService = studentService;
    }
    
    @GetMapping("/studentlist")
    public String viewStudents(Model model) {
        System.out.println(studentService.getStudents());
        model.addAttribute("students", studentService.getStudents());
        return "studentList";
    }
}
