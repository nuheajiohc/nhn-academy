package com.nhnacademy.springmvc.domain.answer;

import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class Answer {

    private final long inquiryId;
    private final String managerId;
    private final String content;
    private final LocalDateTime answerTime;

    public Answer(long inquiryId, String managerId, String content) {
        this.inquiryId = inquiryId;
        this.managerId = managerId;
        this.content = content;
        this.answerTime = LocalDateTime.now();
    }
}
