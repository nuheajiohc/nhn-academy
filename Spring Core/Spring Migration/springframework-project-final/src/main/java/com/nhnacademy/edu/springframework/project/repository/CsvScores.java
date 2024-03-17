package com.nhnacademy.edu.springframework.project.repository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.StringTokenizer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;

@Repository
public class CsvScores implements Scores {

    private List<Score> scores;
    private StringTokenizer st;

    public CsvScores(List<Score> scores) {
        this.scores = scores;
    }

    @Override
    public void load() {
        ClassPathResource resource = new ClassPathResource("data/score.csv");
        try (BufferedReader br = new BufferedReader(new InputStreamReader(resource.getInputStream()))) {
            String scoreInfo;
            while ((scoreInfo = br.readLine()) != null) {
                this.st = new StringTokenizer(scoreInfo, ",");
                scores.add(new Score(Integer.parseInt(this.st.nextToken()), Integer.parseInt(this.st.nextToken())));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Score> findAll() {
        return this.scores;
    }
}
