package com.nhnacademy.edu.springframework.project.config;

import com.nhnacademy.edu.springframework.project.repository.Score;
import com.nhnacademy.edu.springframework.project.service.Student;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan(basePackages = "com.nhnacademy.edu.springframework.project")
@EnableAspectJAutoProxy
public class AppConfig {
    @Bean
    public List<Score> Scores() {
        return new ArrayList<>();
    }

    @Bean
    public Collection<Student> students() {
        return new ArrayList<>();
    }
}
