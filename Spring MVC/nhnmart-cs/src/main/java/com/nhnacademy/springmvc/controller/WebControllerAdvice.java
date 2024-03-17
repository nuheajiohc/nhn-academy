package com.nhnacademy.springmvc.controller;

import com.nhnacademy.springmvc.exception.InquiryNotFoundException;
import com.nhnacademy.springmvc.exception.LogoutFailedException;
import com.nhnacademy.springmvc.exception.UserNotFoundException;
import com.nhnacademy.springmvc.exception.ValidationFailedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class WebControllerAdvice {

    @ExceptionHandler(UserNotFoundException.class)
    public String handleUserNotFoundException(Exception ex, Model model){
        log.error("",ex);

        model.addAttribute("exception",ex);
        return "error";
    }

    @ExceptionHandler(LogoutFailedException.class)
    public String handleLogoutFailedException(Exception ex, Model model){
        log.error("",ex);

        model.addAttribute("exception",ex);
        return "error";
    }

    @ExceptionHandler(ValidationFailedException.class)
    public String handleValidationFailedException(Exception ex, Model model){
        log.error("",ex);

        model.addAttribute("exception",ex);
        return "error";
    }

    @ExceptionHandler(InquiryNotFoundException.class)
    public String handleInquiryNotFoundException(Exception ex, Model model){
        log.error("",ex);

        model.addAttribute("exception",ex);
        return "error";
    }


    @ExceptionHandler(Exception.class)
    public String handleException(Exception ex, Model model) {
        log.error("", ex);

        model.addAttribute("exception", ex);
        return "error";
    }
}
