package com.pbinas.books.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/login")
public class LoginRestController {

    @Autowired
    UserRepository userRepository;

    @PostMapping
    public void addUser(@RequestBody LoginForm loginForm) {
        UserEntity user = new UserEntity();
        user.setLogin(loginForm.getLogin());
        user.setPassword(loginForm.getPassword());
        userRepository.save(user);
    }

    @GetMapping
    public UserEntity getUser(@RequestParam long id) {
        return userRepository.findDistinctById(id);
    }

    @GetMapping("/all")
    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

}
