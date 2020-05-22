package com.pbinas.books.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<BookEntity> findAll() {
        return this.bookRepository.findAll();
    }

    public BookEntity findById(long id) {
        return this.bookRepository.findDistinctById(id);
    }

    public void save(BookEntity book) {
        this.bookRepository.save(book);
    }
}
