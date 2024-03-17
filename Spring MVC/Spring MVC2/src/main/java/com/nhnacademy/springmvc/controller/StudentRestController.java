package com.nhnacademy.springmvc.controller;

import com.nhnacademy.springmvc.domain.Student;
import com.nhnacademy.springmvc.domain.StudentModifyRequest;
import com.nhnacademy.springmvc.domain.StudentRegisterRequest;
import com.nhnacademy.springmvc.exception.StudentNotFoundException;
import com.nhnacademy.springmvc.exception.ValidationFailedException;
import com.nhnacademy.springmvc.repository.StudentRepository;
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
    private final StudentRepository studentRepository;

    public StudentRestController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping("students/{studentId}")
    @ResponseStatus(HttpStatus.OK)
    public Student getStudent(@PathVariable("studentId") Long studentId){
        if(!studentRepository.exists(studentId)){
            throw new StudentNotFoundException();
        }

        return studentRepository.getStudent(studentId);
    }

    @PostMapping("/students")
    @ResponseStatus(HttpStatus.CREATED)
    public Student postStudent(@Valid @RequestBody StudentRegisterRequest studentRegisterRequest,
                               BindingResult bindingResult){
        if(bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }
        return studentRepository.register(studentRegisterRequest.getName(),
                studentRegisterRequest.getEmail(), studentRegisterRequest.getScore(),
                studentRegisterRequest.getComment());
    }

    @PutMapping("students/{studentId}")
    @ResponseStatus(HttpStatus.OK)
    public Student putStudent(@PathVariable("studentId")Long studentId, @Valid @RequestBody StudentModifyRequest studentModifyRequest, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new ValidationFailedException(bindingResult);
        }
        if(!studentRepository.exists(studentId)){
            throw new StudentNotFoundException();
        }
        return studentRepository.modify(studentId,studentModifyRequest.getName(),studentModifyRequest.getEmail(),studentModifyRequest.getScore(),studentModifyRequest.getComment());
    }
}
