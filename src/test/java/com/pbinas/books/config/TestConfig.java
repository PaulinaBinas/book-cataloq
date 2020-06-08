package com.pbinas.books.config;

import com.pbinas.books.repository.AuthorRepository;
import com.pbinas.books.repository.BookListRepository;
import com.pbinas.books.repository.BookRepository;
import com.pbinas.books.repository.UserRepository;
import com.pbinas.books.service.AuthorService;
import com.pbinas.books.service.BookListService;
import com.pbinas.books.service.BookService;
import com.pbinas.books.service.UserService;
import com.pbinas.books.service.impl.AuthorServiceImpl;
import com.pbinas.books.service.impl.BookListServiceImpl;
import com.pbinas.books.service.impl.BookServiceImpl;
import com.pbinas.books.service.impl.UserServiceImpl;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import static org.mockito.Mockito.mock;

@TestConfiguration
public class TestConfig {

    @MockBean
    public UserRepository testUserRepository;

    @MockBean
    public BookRepository testBookRepository;

    @MockBean
    public BookListRepository testBookListRepository;

    @MockBean
    public AuthorRepository testAuthorRepository;

    @Bean
    @Primary
    public UserService testUserService() {
        return new UserServiceImpl();
    }

    @Bean
    @Primary
    public BookService testBookService() {
        return new BookServiceImpl();
    }

    @Bean
    @Primary
    public BookListService testBookListService() { return new BookListServiceImpl(); }

    @Bean
    @Primary
    public AuthorService testAuthorService() { return new AuthorServiceImpl(); }
}
