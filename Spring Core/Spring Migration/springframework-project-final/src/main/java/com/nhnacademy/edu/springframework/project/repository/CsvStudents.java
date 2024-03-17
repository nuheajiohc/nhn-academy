package com.nhnacademy.edu.springframework.project.repository;

import com.nhnacademy.edu.springframework.project.service.Student;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;

@Repository
public class CsvStudents implements Students {

    private Collection<Student> students;
    private StringTokenizer st;

    public CsvStudents(Collection<Student> students) {
        this.students = students;
    }

    @Override
    public void load() {
        ClassPathResource resource = new ClassPathResource("data/student.csv");
        try (BufferedReader br = new BufferedReader(new InputStreamReader(resource.getInputStream()))) {
            String studentInfo;
            while ((studentInfo = br.readLine()) != null) {
                this.st = new StringTokenizer(studentInfo, ",");
                students.add(new Student(Integer.parseInt(this.st.nextToken()), this.st.nextToken()));
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
