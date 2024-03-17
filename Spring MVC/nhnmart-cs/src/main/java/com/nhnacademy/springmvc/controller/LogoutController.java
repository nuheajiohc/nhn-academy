package com.nhnacademy.springmvc.controller;

import com.nhnacademy.springmvc.exception.LogoutFailedException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LogoutController {

    @GetMapping("/logout")
    public String doLogout(HttpServletRequest request) {
        if (request.getSession().getAttribute("sessionId") == null) {
            throw new LogoutFailedException();
        }

        request.getSession().invalidate();
        return "redirect:/";
    }
}
