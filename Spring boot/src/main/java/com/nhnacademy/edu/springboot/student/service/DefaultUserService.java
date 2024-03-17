package com.nhnacademy.edu.springboot.student.service;

import com.nhnacademy.edu.springboot.student.domain.User;
import com.nhnacademy.edu.springboot.student.repository.UserRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class DefaultUserService implements UserService{
    private UserRepository userRepository;

    public DefaultUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    @Override
    public User createUser(User user) {
        boolean present = userRepository.findById(user.getId()).isPresent();
        if(present) throw new IllegalStateException("already exist" + user.getId());
        return userRepository.save(user);
    }

    @Override
    public User getUser(String id) {
        return userRepository.findById(id).orElseThrow();
    }

    @Override
    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }

    @Override
    public boolean matches(String id, String password) {
        return userRepository.findByIdAndPassword(id,password)!=null;
    }


}
