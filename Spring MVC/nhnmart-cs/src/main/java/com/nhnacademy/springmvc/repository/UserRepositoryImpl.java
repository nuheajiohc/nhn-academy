package com.nhnacademy.springmvc.repository;

import com.nhnacademy.springmvc.domain.user.User;
import com.nhnacademy.springmvc.exception.LogoutFailedException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class UserRepositoryImpl implements UserRepository{
    private final Map<String,User> userMap = new HashMap<>();

    public boolean exists(String id) {
        return userMap.containsKey(id);
    }

    public boolean matches(String id, String password) {
        return Optional.ofNullable(getUser(id))
                .map(user -> user.getPassword().equals(password))
                .orElse(false);
    }

    public User getUser(String id) {
        return exists(id) ? userMap.get(id) : null;
    }

    public void addUser(User user) {
        if (exists(user.getId())) {
            throw new LogoutFailedException();
        }
        userMap.put(user.getId(),user);
    }
}
