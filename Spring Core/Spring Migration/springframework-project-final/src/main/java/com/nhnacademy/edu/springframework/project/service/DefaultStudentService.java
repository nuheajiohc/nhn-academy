package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.annotation.TargetMethod;
import com.nhnacademy.edu.springframework.project.repository.CsvStudents;
import com.nhnacademy.edu.springframework.project.repository.StudentService;
import com.nhnacademy.edu.springframework.project.repository.Students;
import java.lang.annotation.Target;
import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class DefaultStudentService implements StudentService {

    private Students studentRepository;

    public DefaultStudentService(Students studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    @TargetMethod
    public Collection<Student> getPassedStudents() {
        // TODO 1 : pass한 학생만 반환하도록 수정하세요.
        // Student 는 Score 를 갖고 있고 Score 에는 pass 여부를 알수 있는 메서드가 있습니다.
        // Java stream api 의 filter() 를 사용하여 필터링된 Student 객체를 리턴 하세요. (Students 와 Student 는 다릅니다.)

        return studentRepository.findAll().stream()
                .filter(student -> student.getScore() != null && !student.getScore().isFail())
                .collect(Collectors.toList());
    }

    @Override
    @TargetMethod
    public Collection<Student> getStudentsOrderByScore() {
        // TODO 4 : 성적 순으로 학생 정보(Student)를 반환합니다.
        // 소팅 문제입니다. Java Stream API 의 소팅 관련 메서드를 사용하세요.
        return studentRepository.findAll().stream()
                .sorted((student1, student2) -> {
                    Integer score1 = (student1.getScore() != null) ? student1.getScore().getScore() : 0;
                    Integer score2 = (student2.getScore() != null) ? student2.getScore().getScore() : 0;
                    return score2 - score1;
                })
                .collect(Collectors.toList());
    }
}
