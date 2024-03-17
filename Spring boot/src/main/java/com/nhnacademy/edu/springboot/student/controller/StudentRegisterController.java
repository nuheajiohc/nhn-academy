package com.nhnacademy.edu.springboot.student.controller;

import com.nhnacademy.edu.springboot.student.domain.Student;
import com.nhnacademy.edu.springboot.student.domain.StudentRegisterRequest;
import com.nhnacademy.edu.springboot.student.exception.ValidationFailedException;
import com.nhnacademy.edu.springboot.student.service.StudentService;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/student/register")
public class StudentRegisterController {
    private final StudentService studentService;

    public StudentRegisterController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public String studentRegisterForm() {
        return "studentRegister";
    }

    @PostMapping
    public ModelAndView registerStudent(@Valid @ModelAttribute StudentRegisterRequest studentRegisterRequest,
                                        BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }
        Long id = studentService.getStudents().size() + 1L;
        Student student = studentService.createStudent(new Student(id,
                studentRegisterRequest.getName(),
                studentRegisterRequest.getEmail(), studentRegisterRequest.getScore(),
                studentRegisterRequest.getComment()));

        ModelAndView mv = new ModelAndView("studentView");
        mv.addObject("student", student);
        return mv;
    }
}
