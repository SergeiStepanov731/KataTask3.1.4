package ru.kata.spring.boot_security.demo.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.entities.Role;
import ru.kata.spring.boot_security.demo.entities.User;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.RoleServiceImpl;
import ru.kata.spring.boot_security.demo.services.UserService;
import ru.kata.spring.boot_security.demo.services.UserServiceImpl;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;


@Component
public class Creat {

    private final RoleService roleService;
    private final UserService userService;

    public Creat(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @PostConstruct
    public void run() {
        Role role = new Role("Admin", 1);
        Role role1 = new Role("user", 2);
        roleService.saveRole(role);
        roleService.saveRole(role1);
        Set<Role> SetRoles = new HashSet<>();
        SetRoles.add(role);
        SetRoles.add(role1);
        Set<Role> users = new HashSet<>();
        users.add(role1);
        User user = new User("User_1", "user1", new BCryptPasswordEncoder(12).encode("1234"), 27L, "admin@mail.ru", SetRoles);
        User user1 = new User("User_2", "user2", new BCryptPasswordEncoder(12).encode("1234"), 33L, "user@mail.ru", users);
        userService.saveUser(user);
        userService.saveUser(user1);
    }

}


