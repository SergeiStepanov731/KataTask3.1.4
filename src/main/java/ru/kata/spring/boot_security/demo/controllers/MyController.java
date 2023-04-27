package ru.kata.spring.boot_security.demo.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.entities.User;
import ru.kata.spring.boot_security.demo.services.UserServiceImpl;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class MyController {

    private final UserServiceImpl userServiceImpl;

    MyController(UserServiceImpl userServiceImpl){
        this.userServiceImpl = userServiceImpl;
    }

    @GetMapping()
    public String showUserInformation(Principal principal, Model model){
        User user= userServiceImpl.findByUserName(principal.getName());
        model.addAttribute("user", userServiceImpl.findUserById(user.getId()));
        return "/user";
    }
}
