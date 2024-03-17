package com.nhnacademy.edu.springframework.service;

import com.nhnacademy.edu.springframework.User;
import com.nhnacademy.edu.springframework.messagesender.MessageSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class MessageService {

    private MessageSender messageSender;

    @Autowired
    public MessageService(@Qualifier("doorayMessageSender") MessageSender messageSender) {
        this.messageSender = messageSender;
    }

    public void doSendMessage(User user,String message) {
        messageSender.sendMessage(user, message);
    }
}
