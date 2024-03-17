package com.nhnacademy.springmvc.controller;

import com.nhnacademy.springmvc.domain.Student;
import com.nhnacademy.springmvc.domain.StudentModifyRequest;
import com.nhnacademy.springmvc.exception.StudentNotFoundException;
import com.nhnacademy.springmvc.exception.ValidationFailedException;
import com.nhnacademy.springmvc.repository.StudentRepository;
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
    private final StudentRepository studentRepository;

    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @ModelAttribute("student")
    public Student getStudent(@PathVariable(value = "studentId", required = false) Long studentId) {
        return studentRepository.getStudent(studentId);
    }

    @GetMapping("/studentlist")
    public String viewStudents(Model model) {
        model.addAttribute("students", studentRepository.getStudents());
        return "studentList";
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

        Student modifiedStudent = studentRepository.modify(student.getId(), studentModifyRequest.getName(),
                studentModifyRequest.getEmail(), studentModifyRequest.getScore(), studentModifyRequest.getComment());

        modelMap.addAttribute("student", modifiedStudent);
        return "studentView";
    }

    @ExceptionHandler(StudentNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void handleException() {
    }
}
