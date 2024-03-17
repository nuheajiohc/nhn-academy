package com.nhnacademy.edu.springboot.student.controller;

import com.nhnacademy.edu.springboot.student.service.UserService;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login() {
        return "loginForm";
    }

    @PostMapping("/login")
    public String doLogin(@RequestParam("id") String id,
                          @RequestParam("pwd") String pwd,
                          HttpServletRequest request) {
        if (userService.matches(id, pwd)) {
            request.getSession().setAttribute("sessionId", id);
            return "redirect:/";
        }
        return "loginForm";
    }
}
