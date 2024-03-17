package com.nhnacademy.edu.springframework.project.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

class ScoresTest {

    private Scores scores;
    private ApplicationContext context = new AnnotationConfigApplicationContext("com.nhnacademy.edu.springframework");

    @BeforeEach
    public void setUp() {
        this.scores = context.getBean("csvScores",Scores.class);
    }

    @Test
    void load() {
        this.scores.load();
        Assertions.assertThat(this.scores.findAll()).isNotEmpty();
    }

    @Test
    void findAll() {
        this.scores.load();
        assertEquals(this.scores.findAll().size(), 3);

        assertEquals(this.scores.findAll().get(0), new Score(1, 30));
        assertEquals(this.scores.findAll().get(2), new Score(3, 70));
    }
}