package com.nhnacademy.edu.springframework.project.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ScoresTest {

    private Scores scores;

    @BeforeEach
    public void setUp() {
        this.scores = CsvScores.getInstance();
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