package com.nhnacademy.springmvc.repository;

import com.nhnacademy.springmvc.domain.answer.Answer;

public interface AnswerRepository {
    boolean exists(long id);

    Answer register(long inquiryId, String title, String content, String managerId);
}
