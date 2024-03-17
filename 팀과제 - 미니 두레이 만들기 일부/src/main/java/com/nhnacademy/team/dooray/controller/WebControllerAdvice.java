package com.nhnacademy.team.dooray.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
public class WebControllerAdvice {
//    @ResponseStatus(HttpStatus.FORBIDDEN)
//    public String notFound() {
//        return "error";
//    }


    @ExceptionHandler(Exception.class)
    public String handleException(Exception ex, Model model) {

        log.error("", ex);

        model.addAttribute("exception", ex);
        return "error";
    }

}
