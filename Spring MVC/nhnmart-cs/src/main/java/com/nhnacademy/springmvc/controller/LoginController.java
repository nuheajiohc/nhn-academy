package com.nhnacademy.springmvc.controller;

import com.nhnacademy.springmvc.exception.UserNotFoundException;
import com.nhnacademy.springmvc.repository.UserRepository;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    private final UserRepository customerRepository;

    private final UserRepository managerRepository;

    public LoginController(UserRepository customerRepository, UserRepository managerRepository) {
        this.customerRepository = customerRepository;
        this.managerRepository = managerRepository;
    }

    @GetMapping("/login")
    public String login() {
        return "loginForm";
    }

    @PostMapping("/login")
    public String doLogin(@RequestParam("id") String id,
                          @RequestParam("pw") String pw,
                          @RequestParam("role") String role,
                          HttpServletRequest request) {

        UserRepository userRepository = getUserRepositoryByRole(role);

        if (userRepository.matches(id, pw)) {
            request.getSession().setAttribute("sessionId", id);
            request.getSession().setAttribute("sessionRole", role);
            return "redirect:/";
        }

        throw new UserNotFoundException();
    }

    private UserRepository getUserRepositoryByRole(String role) {
        switch (role) {
            case "customer":
                return customerRepository;
            case "manager":
                return managerRepository;
            default:
                return null;
        }
    }

}
