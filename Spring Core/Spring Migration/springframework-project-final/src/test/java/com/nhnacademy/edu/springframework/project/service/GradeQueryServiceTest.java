package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.repository.Score;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

class GradeQueryServiceTest {

    private static ApplicationContext context = new AnnotationConfigApplicationContext(
            "com.nhnacademy.edu.springframework");
    private static GradeQueryService gradeQueryService;
    private static DataLoadService dataLoadService;

    @BeforeAll
    public static void setUp() {
        gradeQueryService = context.getBean("defaultGradeQueryService", GradeQueryService.class);
        dataLoadService = context.getBean("csvDataLoadService", DataLoadService.class);
        dataLoadService.loadAndMerge();
    }

    @Test
    public void getScoreByStudentName1() {
        String name = "A";
        List<Score> scores = gradeQueryService.getScoreByStudentName(name);

        Assertions.assertEquals(scores.size(), 2);

    }

    @Test
    public void getScoreByStudentName2() {
        String name = "O";
        List<Score> scores = gradeQueryService.getScoreByStudentName(name);

        Assertions.assertEquals(scores.size(), 0);
    }

    @Test
    void getScoreByStudentSeq() {
        int seq = 1;
        Score score = gradeQueryService.getScoreByStudentSeq(seq);
        Assertions.assertEquals(score.getScore(), 30
        );
    }
}