package com.nhnacademy.edu.springframework;

import com.nhnacademy.edu.springframework.config.MainConfig;
import com.nhnacademy.edu.springframework.service.MessageService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(MainConfig.class);

        MessageService service = context.getBean("messageService", MessageService.class);
        User user = context.getBean("user", User.class);

        service.doSendMessage(user, "히히");
    }
}