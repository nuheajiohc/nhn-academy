package com.nhnacademy.edu.springboot.student.repository;

import com.nhnacademy.edu.springboot.student.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
    User findByIdAndPassword(String id, String password);
}
