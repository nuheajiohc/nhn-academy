package com.nhnacademy.springmvc.domain.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public abstract class User {
    private final String id;

    private final String password;

    private final String name;
}
