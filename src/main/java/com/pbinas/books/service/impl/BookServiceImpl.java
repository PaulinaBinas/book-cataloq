package com.pbinas.books.service.impl;

import com.pbinas.books.model.entity.BookEntity;
import com.pbinas.books.model.entity.BookListEntity;
import com.pbinas.books.repository.BookRepository;
import com.pbinas.books.service.BookListService;
import com.pbinas.books.service.BookService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookListService bookListService;

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
        this.bookListService.removeBook(id);
        this.bookRepository.deleteById(id);
    }

    @Override
    public void modifyBook(BookEntity book) {
        this.bookRepository.save(book);
    }

    @Override
    public void removeList(long id, long listId) {
        BookEntity book = this.findById(id);
        book.getLists().removeIf(list -> list.getId() == listId);
        this.save(book);
    }

    @Override
    public void removeList(long listId) {
        this.findAll().stream().forEach(book -> {
            book.getLists().removeIf(list -> list.getId() == listId);
            this.bookRepository.save(book);
        });
    }
}
