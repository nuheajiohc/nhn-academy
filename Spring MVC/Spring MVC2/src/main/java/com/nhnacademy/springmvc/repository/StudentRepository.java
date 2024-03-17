package com.nhnacademy.springmvc.repository;

import com.nhnacademy.springmvc.domain.Student;
import java.util.Collection;

public interface StudentRepository {
    boolean exists(Long id);


    Student register(String name, String email, int score, String comment);

    Student modify(Long id, String name, String email, int score, String comment);

    Student getStudent(Long id);

    Collection<Student> getStudents();
}
