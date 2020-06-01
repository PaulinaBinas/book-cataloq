package com.pbinas.books.service.impl;

import com.pbinas.books.model.entity.UserEntity;
import com.pbinas.books.repository.UserRepository;
import com.pbinas.books.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsByNameServiceWrapper;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserEntity getUser(String username) {
        return this.userRepository.findUserEntityByUsername(username);
    }

    @Override
    public void saveUser(UserEntity user) {
        this.userRepository.save(user);
    }

    @Override
    public UserEntity getLoggedInUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        try {
            UserDetails user = (UserDetails) auth.getPrincipal();
            return this.getUser(user.getUsername());
        } catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
