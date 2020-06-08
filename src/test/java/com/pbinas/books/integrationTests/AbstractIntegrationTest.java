package com.pbinas.books.integrationTests;

import com.pbinas.books.repository.AuthorRepository;
import com.pbinas.books.repository.BookListRepository;
import com.pbinas.books.repository.BookRepository;
import com.pbinas.books.repository.UserRepository;
import com.pbinas.books.service.BookListService;
import com.pbinas.books.service.BookService;
import com.pbinas.books.service.UserService;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

public abstract class AbstractIntegrationTest {

    @Autowired
    WebApplicationContext webApplicationContext;

    @Autowired
    UserRepository testUserRepository;

    @Autowired
    BookRepository testBookRepository;

    @Autowired
    BookListRepository testBookListRepository;

    @Autowired
    AuthorRepository testAuthorRepository;

    @Autowired
    UserService testUserService;

    @Autowired
    BookService testBookService;

    @Autowired
    BookListService testBookListService;

    MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }
}
