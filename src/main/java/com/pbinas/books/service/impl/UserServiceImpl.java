package com.pbinas.books.service.impl;

import com.pbinas.books.repository.UserRepository;
import com.pbinas.books.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
}
