package com.nhnacademy.springmvc.domain;

import lombok.Getter;
import lombok.Setter;

public class User {
    @Getter
    private final String id;

    @Getter
    private final String password;

    public User(String id, String password) {
        this.id = id;
        this.password = password;
    }

}
