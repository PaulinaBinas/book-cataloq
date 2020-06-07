package com.pbinas.books;

import com.pbinas.books.repository.UserRepository;
import com.pbinas.books.service.UserService;
import com.pbinas.books.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
public class AbstractTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;
}
