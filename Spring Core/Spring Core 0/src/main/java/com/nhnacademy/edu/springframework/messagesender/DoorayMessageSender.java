package com.nhnacademy.edu.springframework.messagesender;

import com.nhn.dooray.client.DoorayHook;
import com.nhn.dooray.client.DoorayHookSender;
import com.nhnacademy.edu.springframework.User;
import com.nhnacademy.edu.springframework.annotation.TargetMethod;

public class DoorayMessageSender implements MessageSender {

    private DoorayHookSender doorayHookSender;


    public DoorayMessageSender(DoorayHookSender doorayHookSender) {
        this.doorayHookSender = doorayHookSender;
    }

    @Override
    @TargetMethod
    public boolean sendMessage(User user, String message) {
        doorayHookSender.send(DoorayHook.builder()
                .botName(user.getName())
                .text(message)
                .build());
        return true;
    }
}