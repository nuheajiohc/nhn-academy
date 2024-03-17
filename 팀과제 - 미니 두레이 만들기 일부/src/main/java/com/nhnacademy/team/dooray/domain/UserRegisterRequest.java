package com.nhnacademy.team.dooray.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserRegisterRequest {
    private String accountId;
    private String authority;
}
