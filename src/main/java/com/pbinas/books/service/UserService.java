package com.pbinas.books.service;

import com.pbinas.books.model.entity.UserEntity;

public interface UserService {

    UserEntity getUser(String username);

    void saveUser(UserEntity user);

    UserEntity getLoggedInUser();
}
