package com.nhnacademy.edu.springframework.project.repository;

import java.util.Objects;

public class Score {
    private final int studentSeq;
    private final int score;

    public Score(int studentSeq, int score) {
        this.studentSeq = studentSeq;
        this.score = score;
    }

    public int getStudentSeq() {
        return studentSeq;
    }

    public int getScore() {
        return score;
    }

    public boolean isFail() {
        return (60 > this.score);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Score score1 = (Score) o;
        return studentSeq == score1.studentSeq && score == score1.score;
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentSeq, score);
    }

    @Override
    public String toString() {
        return "Score{" +
                "studentSeq=" + studentSeq +
                ", score=" + score +
                '}';
    }
}
