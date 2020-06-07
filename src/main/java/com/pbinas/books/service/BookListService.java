package com.pbinas.books.service;

import com.pbinas.books.model.entity.BookListEntity;

import java.util.List;

public interface BookListService {

    void saveList(BookListEntity list);

    void addBookToList(long bookId, long listId);

    BookListEntity getBookList(long id);

    List<BookListEntity> getAllBookLists();

    void removeList(long id);

    void removeBookFromList(long bookId, long listId);

    void renameList(long id,String newName);

    void removeBook(long bookId);
}
