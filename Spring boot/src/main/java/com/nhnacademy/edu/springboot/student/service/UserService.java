package com.nhnacademy.edu.springboot.student.service;

import com.nhnacademy.edu.springboot.student.domain.User;
import java.util.List;

public interface UserService {
    List<User> getUsers();

    User createUser(User user);

    User getUser(String id);

    void deleteUser(String id);

    boolean matches(String id, String password);
}
