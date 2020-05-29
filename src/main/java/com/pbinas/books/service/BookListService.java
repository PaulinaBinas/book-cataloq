package com.pbinas.books.service;

import com.pbinas.books.model.entity.BookEntity;
import com.pbinas.books.model.entity.BookListEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface BookListService {

    void addList(BookListEntity list);

    void addBookToList(long bookId, long listId);

    BookListEntity getBookList(long id);

    List<BookListEntity> getAllBookLists();

    void removeList(long id);

    void removeBookFromList(long bookId, long listId);

    void renameList(long id,String newName);
}
