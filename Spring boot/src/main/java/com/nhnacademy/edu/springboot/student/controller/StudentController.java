package com.nhnacademy.edu.springboot.student.controller;

import com.nhnacademy.edu.springboot.student.domain.Student;
import com.nhnacademy.edu.springboot.student.domain.StudentModifyRequest;
import com.nhnacademy.edu.springboot.student.exception.StudentNotFoundException;
import com.nhnacademy.edu.springboot.student.exception.ValidationFailedException;
import com.nhnacademy.edu.springboot.student.repository.StudentRepository;
import com.nhnacademy.edu.springboot.student.service.StudentService;
import java.util.Objects;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @ModelAttribute("student")
    public Student getStudent(@PathVariable(value = "studentId", required = false) Long studentId) {
        return studentService.getStudent(studentId);
    }

    @GetMapping("/{studentId}")
    public String viewStudent(@ModelAttribute Student student, Model model) {
        if (Objects.isNull(student)) {
            model.addAttribute("exception", new StudentNotFoundException());
            return "error";
        }
        return "studentView";
    }

    @GetMapping(value = "/{studentId}", params = "hideScore")
    public String viewStudentByLimitingInfo(@RequestParam("hideScore") String hideScore, Model model) {
        if (Objects.nonNull(hideScore) && hideScore.equals("yes")) {
            model.addAttribute("hideInfo", true);
            return "studentView";
        }

        model.addAttribute("exception", new StudentNotFoundException());
        return "error";
    }

    @GetMapping("/{studentId}/modify")
    public String studentModifyForm(@ModelAttribute Student student, Model model) {
        if (Objects.isNull(student)) {
            model.addAttribute("exception", new StudentNotFoundException());
            return "error";
        }
        return "studentModify";
    }

    @PostMapping("/{studentId}/modify")
    public String modifyStudent(@ModelAttribute Student student,
                                @Valid @ModelAttribute StudentModifyRequest studentModifyRequest,
                                BindingResult bindingResult, ModelMap modelMap) {
        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }

        Student modifiedStudent = studentService.modifyStudent(new Student(student.getId(), studentModifyRequest.getName(),
                studentModifyRequest.getEmail(), studentModifyRequest.getScore(), studentModifyRequest.getComment()));

        modelMap.addAttribute("student", modifiedStudent);
        return "studentView";
    }

    @ExceptionHandler(StudentNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void handleException() {
    }
}
