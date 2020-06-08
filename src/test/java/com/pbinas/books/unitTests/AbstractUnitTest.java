package com.pbinas.books.unitTests;

import com.pbinas.books.repository.AuthorRepository;
import com.pbinas.books.repository.BookListRepository;
import com.pbinas.books.repository.BookRepository;
import com.pbinas.books.repository.UserRepository;
import com.pbinas.books.service.BookListService;
import com.pbinas.books.service.BookService;
import com.pbinas.books.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;


public class AbstractUnitTest {

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
}
