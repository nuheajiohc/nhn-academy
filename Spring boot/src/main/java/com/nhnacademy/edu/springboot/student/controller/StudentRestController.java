package com.nhnacademy.edu.springboot.student.controller;

import com.nhnacademy.edu.springboot.student.domain.Student;
import com.nhnacademy.edu.springboot.student.domain.StudentModifyRequest;
import com.nhnacademy.edu.springboot.student.domain.StudentRegisterRequest;
import com.nhnacademy.edu.springboot.student.exception.StudentNotFoundException;
import com.nhnacademy.edu.springboot.student.exception.ValidationFailedException;
import com.nhnacademy.edu.springboot.student.service.StudentService;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentRestController {
    private final StudentService studentService;

    public StudentRestController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("students/{studentId}")
    @ResponseStatus(HttpStatus.OK)
    public Student getStudent(@PathVariable("studentId") Long studentId) {
        if (studentService.getStudent(studentId) == null) {
            throw new StudentNotFoundException();
        }

        return studentService.getStudent(studentId);
    }

    @PostMapping(value = "/students")
    @ResponseStatus(HttpStatus.CREATED)
    public Student postStudent(@Valid @RequestBody StudentRegisterRequest studentRegisterRequest,
                               BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }

        Long id = studentService.getStudents().size() + 1L;
        Student student = new Student(id, studentRegisterRequest.getName(),
                studentRegisterRequest.getEmail(), studentRegisterRequest.getScore(),
                studentRegisterRequest.getComment());

        return studentService.createStudent(student);
    }

    @PutMapping("students/{studentId}")
    @ResponseStatus(HttpStatus.OK)
    public Student putStudent(@PathVariable("studentId") Long studentId,
                              @Valid @RequestBody StudentModifyRequest studentModifyRequest,
                              BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }
        if (studentService.getStudent(studentId) == null) {
            throw new StudentNotFoundException();
        }
        return studentService.modifyStudent(
                new Student(studentId, studentModifyRequest.getName(), studentModifyRequest.getEmail(),
                        studentModifyRequest.getScore(), studentModifyRequest.getComment()));
    }
}
