package com.nhnacademy.springmvc.config;

import com.nhnacademy.springmvc.Base;
import com.nhnacademy.springmvc.domain.user.Customer;
import com.nhnacademy.springmvc.domain.user.Manager;
import com.nhnacademy.springmvc.repository.UserRepository;
import com.nhnacademy.springmvc.repository.UserRepositoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;

@Configuration
@ComponentScan(basePackageClasses = Base.class, excludeFilters = {@ComponentScan.Filter(Controller.class)})
public class RootConfig {

    @Bean
    public UserRepository customerRepository(){
        UserRepositoryImpl userRepository = new UserRepositoryImpl();
        userRepository.addUser(new Customer("jaehun1234","1234","최재훈"));
        userRepository.addUser(new Customer("minsu1234","1234","박민수"));
        userRepository.addUser(new Customer("suyeon1234","1234","순수연"));
        return userRepository;
    }

    @Bean
    public UserRepository managerRepository(){
        UserRepositoryImpl userRepository = new UserRepositoryImpl();
        userRepository.addUser(new Manager("jiwon1234","1234","홍지원"));
        userRepository.addUser(new Manager("gaeun1234","1234","이가은"));
        userRepository.addUser(new Manager("suyeon1234","1234","순수연"));
        return userRepository;
    }
}
