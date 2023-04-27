package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.kata.spring.boot_security.demo.entities.User;
import ru.kata.spring.boot_security.demo.services.UserServiceImpl;

import java.security.Principal;


@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserServiceImpl userServiceImpl;
    private final PasswordEncoder passwordEncoder;

    public AdminController(UserServiceImpl userServiceImpl, PasswordEncoder passwordEncoder) {
        this.userServiceImpl = userServiceImpl;
        this.passwordEncoder = passwordEncoder;
    }


    @GetMapping("/users")
    public String getAllUsers(Model model, Principal principal) {
        User user = userServiceImpl.findByUserName(principal.getName());
        model.addAttribute("user", userServiceImpl.findUserById(user.getId()));
        model.addAttribute("AllUsers", userServiceImpl.getAllUsers());
        model.addAttribute("role", user.getRoles());
        model.addAttribute("roles");
        return "/infoAboutUsers";
    }

    @PostMapping("/users/addUser")
    public String addUser(@ModelAttribute("user") User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userServiceImpl.saveUser(user);
        return "redirect:/admin/users";
    }

    @GetMapping(value = "/new")
    public String addNewUser(Model model, Principal principal) {
        User user = userServiceImpl.findByUserName(principal.getName());
        model.addAttribute("user", userServiceImpl.findUserById(user.getId()));
        model.addAttribute("role", user.getRoles());
        return "/addNewUser";
    }

    @GetMapping("/users/update/{id}")
    public String update(@ModelAttribute("user") User user, @PathVariable("id") Long id) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userServiceImpl.update(user.getId(), user);
        return "redirect:/admin/users";
    }


    @GetMapping("/delete/{id}")
    public String delete(@ModelAttribute("user") User user, @PathVariable("id") Long id) {
        userServiceImpl.delete(user.getId(), user);
        return "redirect:/admin/users";
    }

    @GetMapping("/getOne")
    @ResponseBody
    public User getOne(Long id) {
        return userServiceImpl.findUserById(id);
    }
}