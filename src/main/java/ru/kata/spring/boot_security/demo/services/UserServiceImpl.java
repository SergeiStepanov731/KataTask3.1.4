package ru.kata.spring.boot_security.demo.services;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.entities.User;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;

import java.util.List;

@Service
public class UserServiceImpl implements UserDetailsService, UserService {

    private final UserRepository userRepository;


    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        user.getRoles().size();
        if (user == null) {
            throw new UsernameNotFoundException(String.format("User '%s' not found", username));
        }
        return user;

    }

    @Transactional(readOnly = true)
    public User findByUserName(String username) {
        return userRepository.findByUsername(username);
    }


    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Transactional
    public void update(Long id, User user1) {
        user1.setId(id);
        user1.setName(user1.getName());
        user1.setAge(user1.getAge());
        user1.setEmail(user1.getEmail());
        user1.setRoles(user1.getRoles());
        user1.setUsername(user1.getUsername());
        String encodedPassword = new BCryptPasswordEncoder().encode(user1.getPassword());
        user1.setPassword(encodedPassword);
        userRepository.save(user1);
    }


    @Transactional(readOnly = true)
    public User findUserById(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    @Transactional
    public void delete(Long id, User user) {
        user.setId(id);
        userRepository.delete(user);
    }

    @Transactional
    public void saveUser(User user) {
        userRepository.save(user);
    }

}
