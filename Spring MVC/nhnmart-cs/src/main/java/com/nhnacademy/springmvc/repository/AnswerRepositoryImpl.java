package com.nhnacademy.springmvc.repository;

import com.nhnacademy.springmvc.domain.answer.Answer;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public class AnswerRepositoryImpl implements AnswerRepository{

    private final Map<Long,Answer> answerMap = new HashMap<>();

    @Override
    public boolean exists(long id) {
        return answerMap.containsKey(id);
    }

    @Override
    public Answer register(long inquiryId, String title, String content, String managerId) {
        Answer answer = new Answer(inquiryId, managerId, content);
        answerMap.put(inquiryId,answer);
        return answer;
    }
}
