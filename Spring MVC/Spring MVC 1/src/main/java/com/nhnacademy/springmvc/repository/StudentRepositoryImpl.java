package com.nhnacademy.springmvc.repository;

import com.nhnacademy.springmvc.domain.Student;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class StudentRepositoryImpl implements StudentRepository {

    private final Map<Long, Student> studentMap = new HashMap<>();

    @Override
    public boolean exists(Long id) {
        return studentMap.containsKey(id);
    }

    @Override
    public Student register(String name, String email, int score, String comment) {
        long id = studentMap.isEmpty() ? 1 : Collections.max(studentMap.keySet()) + 1;
        Student student = new Student(id, name, email, score, comment);
        studentMap.put(id, student);
        return student;
    }

    @Override
    public Student modify(Long id, String name, String email, int score, String comment) {
        Student modifiedStudent = new Student(id,name,email,score,comment);
        studentMap.put(id,modifiedStudent);
        return modifiedStudent;
    }

    @Override
    public Student getStudent(Long id) {
        return exists(id) ? studentMap.get(id) : null;
    }

    @Override
    public Collection<Student> getStudents() {
        return this.studentMap.values();
    }
}
