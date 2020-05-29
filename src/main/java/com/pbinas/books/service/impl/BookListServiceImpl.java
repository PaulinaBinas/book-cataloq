package com.pbinas.books.service.impl;

import com.pbinas.books.model.entity.BookEntity;
import com.pbinas.books.model.entity.BookListEntity;
import com.pbinas.books.repository.BookListRepository;
import com.pbinas.books.repository.BookRepository;
import com.pbinas.books.service.BookListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookListServiceImpl implements BookListService {

    @Autowired
    private BookListRepository bookListRepository;

    @Autowired
    private BookRepository bookRepository;

    @Override
    public void addList(BookListEntity list) {
        this.bookListRepository.save(list);
    }

    @Override
    public void addBookToList(long bookId, long listId) {
        BookEntity book = this.bookRepository.findDistinctById(bookId);
        if(book != null) {
            BookListEntity bookList = this.bookListRepository.findDistinctById(listId);
            bookList.getBooks().add(book);
            this.bookListRepository.save(bookList);
        }
    }

    @Override
    public BookListEntity getBookList(long id) {
        return this.bookListRepository.findDistinctById(id);
    }

    @Override
    public List<BookListEntity> getAllBookLists() {
        return this.bookListRepository.findAll();
    }

    @Override
    public void removeList(long id) {
        this.bookListRepository.deleteById(id);
    }

    @Override
    public void removeBookFromList(long bookId, long listId) {
        BookListEntity list = this.bookListRepository.findDistinctById(listId);
        list.getBooks().removeIf(book -> book.getId() == bookId);
        this.bookListRepository.save(list);
    }

    @Override
    public void renameList(long id, String newName) {
        BookListEntity list = this.bookListRepository.findDistinctById(id);
        list.setTitle(newName);
        this.bookListRepository.save(list);
    }
}
