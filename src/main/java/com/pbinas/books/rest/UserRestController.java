package com.pbinas.books.rest;

import com.pbinas.books.model.entity.UserEntity;
import com.pbinas.books.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserRestController {

    @Autowired
    UserRepository userRepository;

    @PostMapping
    public ModelAndView addUser(@RequestParam(value = "username") String username, @RequestParam(value = "password") String password) {
        UserEntity user = new UserEntity();
        user.setId(0);
        user.setUsername(username);
        user.setPassword(password);
        userRepository.save(user);
        return new ModelAndView("home");
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
