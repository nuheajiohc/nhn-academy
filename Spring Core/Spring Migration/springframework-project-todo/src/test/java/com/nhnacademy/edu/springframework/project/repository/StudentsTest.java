package com.nhnacademy.edu.springframework.project.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.nhnacademy.edu.springframework.project.service.Student;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StudentsTest {

    private Students students;

    @BeforeEach
    public void setUp() {
        this.students = CsvStudents.getInstance();
    }

    @Test
    void load() {
        this.students.load();
        Assertions.assertThat(this.students.findAll()).isNotEmpty();
    }

    @Test
    void findAll() {
        this.students.load();
        assertEquals(this.students.findAll().size(), 4);

        assertEquals(((List<Student>) this.students.findAll()).get(0), new Student(1, "A"));
        assertEquals(((List<Student>) this.students.findAll()).get(3), new Student(4, "D"));
    }

    @Test
    void merge() {
        Scores scores = CsvScores.getInstance();
        scores.load();
        this.students.load();

        this.students.merge(scores.findAll());
        Assertions.assertThat(((List<Student>) this.students.findAll()).get(0).getScore()).isNotNull();
        Assertions.assertThat(((List<Student>) this.students.findAll()).get(3).getScore()).isNull();
    }
}