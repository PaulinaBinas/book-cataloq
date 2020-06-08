package com.pbinas.books.service;

import com.pbinas.books.model.entity.UserEntity;

import java.util.List;

public interface UserService {

    UserEntity getUser(String username);

    UserEntity getUser(long id);

    List<UserEntity> getAllUsers();

    void saveUser(UserEntity user);

    UserEntity addUser(String username, String password);

    UserEntity getLoggedInUser();
}
