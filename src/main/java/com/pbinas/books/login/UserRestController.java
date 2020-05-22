package com.pbinas.books.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserRestController {

    @Autowired
    UserRepository userRepository;

    @PostMapping
    public void addUser(@RequestParam(value = "username") String username, @RequestParam(value = "password") String password) {
        UserEntity user = new UserEntity();
        user.setId(0);
        user.setUsername(username);
        user.setPassword(password);
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
