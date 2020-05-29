package com.pbinas.books.service.impl;

import com.pbinas.books.model.entity.BookEntity;
import com.pbinas.books.repository.BookRepository;
import com.pbinas.books.service.BookService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<BookEntity> findAll() {
        return this.bookRepository.findAll();
    }

    @Override
    public BookEntity findById(long id) {
        return this.bookRepository.findDistinctById(id);
    }

    @Override
    public void save(BookEntity book) {
        this.bookRepository.save(book);
    }

    @Override
    public void removeBook(long id) {
        this.bookRepository.deleteById(id);
    }

    @Override
    public void modifyBook(BookEntity book) {
        this.bookRepository.save(book);
    }
}
