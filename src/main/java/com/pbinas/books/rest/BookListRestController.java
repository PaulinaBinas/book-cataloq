package com.pbinas.books.rest;

import com.pbinas.books.model.entity.BookEntity;
import com.pbinas.books.model.entity.BookListEntity;
import com.pbinas.books.repository.BookListRepository;
import com.pbinas.books.repository.BookRepository;
import com.pbinas.books.service.BookListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/list")
public class BookListRestController {

    @Autowired
    private BookListService bookListService;


    @PostMapping
    public void addList(@RequestBody BookListEntity bookList) {
        this.bookListService.addList(bookList);
    }

    @PostMapping("/{listId}/addBook/{bookId}")
    public void addBookToList(@PathVariable long bookId, @PathVariable long listId) {
        this.bookListService.addBookToList(bookId, listId);
    }

    @GetMapping
    public BookListEntity getBookList(@RequestParam long id) {
        return this.bookListService.getBookList(id);
    }

    @GetMapping("/all")
    public List<BookListEntity> getAllBookLists() {
        return this.bookListService.getAllBookLists();
    }

    @DeleteMapping
    public void removeList(@RequestParam long id) {
        this.bookListService.removeList(id);
    }

    @DeleteMapping("/{listId}/removeBook/{bookId}")
    public void removeBookFromList (@PathVariable long bookId, @PathVariable long listId) {
        this.bookListService.removeBookFromList(bookId, listId);
    }

    @PatchMapping
    public void renameList(@RequestParam long id, @RequestParam String newName) {
        this.bookListService.renameList(id, newName);
    }
}
