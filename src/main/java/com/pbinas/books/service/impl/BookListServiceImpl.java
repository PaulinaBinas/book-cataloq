package com.pbinas.books.service.impl;

import com.pbinas.books.model.entity.BookEntity;
import com.pbinas.books.model.entity.BookListEntity;
import com.pbinas.books.repository.BookListRepository;
import com.pbinas.books.service.BookListService;
import com.pbinas.books.service.BookService;
import com.pbinas.books.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookListServiceImpl implements BookListService {

    @Autowired
    private BookListRepository bookListRepository;

    @Autowired
    private BookService bookService;

    @Override
    public void saveList(BookListEntity list) {
        this.bookListRepository.save(list);
    }

    @Override
    public void addBookToList(long bookId, long listId) {
        BookEntity book = this.bookService.findById(bookId);

        if(book != null) {
            BookListEntity bookList = this.bookListRepository.findDistinctById(listId);
            if(!bookList.getBooks().stream().anyMatch(b -> b.getId() == bookId)) {
                bookList.getBooks().add(book);
                this.bookListRepository.save(bookList);
                this.bookService.addList(bookId, bookList);
            }
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
        this.bookService.removeList(id);
        this.bookListRepository.deleteById(id);
    }

    @Override
    public void removeBookFromList(long bookId, long listId) {
        BookListEntity list = this.bookListRepository.findDistinctById(listId);
        list.getBooks().removeIf(book -> book.getId() == bookId);
        this.bookListRepository.save(list);
        this.bookService.removeList(bookId, listId);
    }

    @Override
    public void renameList(long id, String newName) {
        BookListEntity list = this.bookListRepository.findDistinctById(id);
        list.setTitle(newName);
        this.bookListRepository.save(list);
    }

    @Override
    public void removeBook(long bookId) {
        this.getAllBookLists().stream().forEach(list -> {
            list.getBooks().removeIf(book -> book.getId() == bookId);
            this.saveList(list);
        });
    }
}
