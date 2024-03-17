package com.nhnacademy.edu.springframework.project.service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.nhnacademy.edu.springframework.project.repository.StudentService;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

class StudentServiceTest {


    private static ApplicationContext context = new AnnotationConfigApplicationContext(
            "com.nhnacademy.edu.springframework");
    private static StudentService studentService;
    private static DataLoadService dataLoadService;


    @BeforeAll
    public static void setup() {
        studentService = context.getBean("defaultStudentService", StudentService.class);
        dataLoadService = context.getBean("csvDataLoadService", DataLoadService.class);
        dataLoadService.loadAndMerge();
    }

    @Test
    void getPassedStudents() {
        Assertions.assertEquals(studentService.getPassedStudents().size(), 2);
    }

    @Test
    void getStudentsOrderByScore() {
        List<Student> orderedStudents = (List<Student>) studentService.getStudentsOrderByScore();

        for (int i = 0; i < orderedStudents.size() - 1; i++) {
            int currentScore;
            int nextScore;
            if (orderedStudents.get(i).getScore() == null) {
                currentScore = 0;
            } else {
                currentScore = orderedStudents.get(i).getScore().getScore();
            }

            if (orderedStudents.get(i + 1).getScore() == null) {
                nextScore = 0;
            } else {
                nextScore = orderedStudents.get(i + 1).getScore().getScore();
            }
            boolean isDesc =
                    currentScore >= nextScore;
            assertTrue(isDesc);
        }

    }
}