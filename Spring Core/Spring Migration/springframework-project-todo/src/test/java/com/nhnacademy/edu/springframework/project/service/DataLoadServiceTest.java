package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.repository.CsvStudents;
import com.nhnacademy.edu.springframework.project.repository.Students;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DataLoadServiceTest {

    private DataLoadService dataLoadService;
    private Students students;

    @BeforeEach
    public void setUp() {
        this.dataLoadService = new CsvDataLoadService();
    }

    @Test
    void loadAndMerge() {
        this.dataLoadService.loadAndMerge();
        this.students = CsvStudents.getInstance();

        Assertions.assertEquals(students.findAll().size(), 4);
        org.assertj.core.api.Assertions.assertThat(((List<Student>) this.students.findAll()).get(0).getScore())
                .isNotNull();
        org.assertj.core.api.Assertions.assertThat(((List<Student>) this.students.findAll()).get(3).getScore())
                .isNull();
    }
}