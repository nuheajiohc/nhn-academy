package com.nhnacademy.edu.springboot.student.service;

import com.nhnacademy.edu.springboot.student.domain.Student;
import com.nhnacademy.edu.springboot.student.repository.StudentRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class DefaultStudentService implements StudentService {

    private StudentRepository studentRepository;

    public DefaultStudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student createStudent(Student student) {
        boolean present = studentRepository.findById(student.getId()).isPresent();
        if (present) {
            throw new IllegalStateException("already exist " + student.getId());
        }
        return studentRepository.save(student);
    }

    @Override
    public Student modifyStudent(Student student) {
        return studentRepository.saveAndFlush(student);
    }

    @Override
    public Student getStudent(Long id) {
        return studentRepository.findById(id).orElseThrow();
    }

    @Override
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }
}
