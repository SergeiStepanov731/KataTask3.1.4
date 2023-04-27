package ru.kata.spring.boot_security.demo.services;

import ru.kata.spring.boot_security.demo.entities.User;

import java.util.List;

public interface UserService {

    User findByUserName(String username);

    List<User> getAllUsers();

    void update(Long id, User user);

    User findUserById(Long userId);

    void delete(Long id, User user);

    void saveUser(User user);

}
