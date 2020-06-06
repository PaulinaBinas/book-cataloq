package com.pbinas.books.service;

import com.pbinas.books.model.entity.BookEntity;
import com.pbinas.books.model.entity.BookListEntity;

import java.util.List;

public interface BookService {

    List<BookEntity> findAll();

    BookEntity findById(long id);

    void save(BookEntity book);

    void removeBook(long id);

    void modifyBook(BookEntity book);
}
