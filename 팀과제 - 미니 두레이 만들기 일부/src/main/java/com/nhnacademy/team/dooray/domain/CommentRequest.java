package com.nhnacademy.team.dooray.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class CommentRequest {
    private String comment;

    private String accountId;
}
