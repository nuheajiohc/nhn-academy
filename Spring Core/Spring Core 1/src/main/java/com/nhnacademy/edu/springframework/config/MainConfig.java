package com.nhnacademy.edu.springframework.config;

import com.nhn.dooray.client.DoorayHookSender;
import com.nhnacademy.edu.springframework.User;
import com.nhnacademy.edu.springframework.aop.TimeAspect;
import com.nhnacademy.edu.springframework.messagesender.DoorayMessageSender;
import com.nhnacademy.edu.springframework.messagesender.MessageSender;
import com.nhnacademy.edu.springframework.service.MessageService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableAspectJAutoProxy
public class MainConfig {

    private String url = "https://hook.dooray.com/services/3204376758577275363/3707922642738216364/WdJUIozGSgGdnxhQnS1lSA";

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public DoorayHookSender doorayHookSender() {
        return new DoorayHookSender(restTemplate(), url);
    }

    @Bean
    public DoorayMessageSender doorayMessageSender() {
        return new DoorayMessageSender(doorayHookSender());
    }

    @Bean
    public MessageService messageService(MessageSender doorayMessageSender){
        return new MessageService(doorayMessageSender);
    }

    @Bean
    public User user() {
        return new User("test");
    }

    @Bean
    public TimeAspect timeAspect() {
        return new TimeAspect();
    }
}
