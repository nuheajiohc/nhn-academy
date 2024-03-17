package com.nhnacademy.springmvc.domain.answer;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Value;

@Value
public class AnswerRegisterRequest {
    @NotNull
    private long inquiryId;
    @NotNull
    private String managerId;
    @NotBlank
    @Size(max=40000)
    private String content;
}
