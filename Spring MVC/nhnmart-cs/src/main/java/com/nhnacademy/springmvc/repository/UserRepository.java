package com.nhnacademy.springmvc.repository;

import com.nhnacademy.springmvc.domain.user.User;

public interface UserRepository {

    boolean exists(String id);

    boolean matches(String id, String password);

    User getUser(String id);

    void addUser(User user);
}
