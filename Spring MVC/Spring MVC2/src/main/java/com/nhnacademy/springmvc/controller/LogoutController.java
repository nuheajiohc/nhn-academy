package com.nhnacademy.springmvc.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LogoutController {

    @GetMapping("/logout")
    public String doLogout(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/";
    }
}
