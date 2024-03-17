package com.nhnacademy.edu.springframework.project.repository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class CsvScores implements Scores {

    private static CsvScores instance = new CsvScores();
    private List<Score> scores;

    private CsvScores() {
        this.scores = new ArrayList<>();
    }

    /**
     * TODO 2 :
     * Java Singleton 패턴으로 getInstance() 를 구현하세요.
     **/

    public static Scores getInstance() {
        return instance;
    }

    // TODO 5 : score.csv 파일에서 데이터를 읽어 멤버 변수에 추가하는 로직을 구현하세요.
    @Override
    public void load() {
        URL resource = getClass().getClassLoader().getResource("data/score.csv");
        String filePath = resource.getFile();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String scoreInfo;
            while ((scoreInfo = br.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(scoreInfo, ",");
                scores.add(new Score(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
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
