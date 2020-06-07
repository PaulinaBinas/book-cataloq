package com.pbinas.books.rest;

import com.pbinas.books.model.dto.UserTo;
import com.pbinas.books.model.entity.UserEntity;
import com.pbinas.books.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserRestController {

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper mapper;

    @PostMapping
    public ModelAndView addUser(@RequestParam(value = "username") String username, @RequestParam(value = "password") String password) {
        this.userService.addUser(username, password);
        return new ModelAndView("home");
    }

    @GetMapping
    public UserTo getUser(@RequestParam long id) {
        return convertToDto(this.userService.getUser(id));
    }

    @GetMapping("/all")
    public List<UserTo> getAllUsers() {
        return this.userService.getAllUsers().stream().map(this::convertToDto).collect(Collectors.toList());
    }

    private UserTo convertToDto(UserEntity userEntity) {
        return this.mapper.map(userEntity, UserTo.class);
    }

    private UserEntity convertToEntity(UserTo userTo) {
        return this.mapper.map(userTo, UserEntity.class);
    }
}
