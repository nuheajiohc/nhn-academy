package com.nhnacademy.edu.springframework.project.repository;

import com.nhnacademy.edu.springframework.project.service.Student;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;


public class CsvStudents implements Students {

    private static CsvStudents instance = new CsvStudents();
    private Collection<Student> students;

    /**
     * TODO 3 :
     * Java Singleton 패턴으로 getInstance() 를 구현하세요.
     **/

    private CsvStudents() {
        this.students = new ArrayList<>();
    }

    public static Students getInstance() {
        return instance;
    }

    // TODO 7 : student.csv 파일에서 데이터를 읽어 클래스 멤버 변수에 추가하는 로직을 구현하세요.
    // 데이터를 적재하고 읽기 위해서, 적절한 자료구조를 사용하세요.
    @Override
    public void load() {
        URL resource = getClass().getClassLoader().getResource("data/student.csv");
        String filePath = resource.getFile();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String studentInfo;
            while ((studentInfo = br.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(studentInfo, ",");
                students.add(new Student(Integer.parseInt(st.nextToken()), st.nextToken()));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Collection<Student> findAll() {
        return this.students;
    }

    /**
     * TODO 8 : students 데이터에 score 정보를 추가하세요.
     *
     * @param scores
     */
    @Override
    public void merge(Collection<Score> scores) {
        Map<Integer,Score> scoreMap = new HashMap<>();
        for(Score score: scores){
            scoreMap.put(score.getStudentSeq(),score);
        }

        for(Student student : students){
            int studentSeq = student.getSeq();
            if(scoreMap.containsKey(studentSeq)){
                student.setScore(scoreMap.get(studentSeq));
            }
        }
    }
}
