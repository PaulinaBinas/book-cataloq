package com.pbinas.books.rest;

import com.pbinas.books.model.dto.BookListTo;
import com.pbinas.books.model.dto.BookTo;
import com.pbinas.books.model.entity.BookListEntity;
import com.pbinas.books.service.BookListService;
import com.pbinas.books.service.BookService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/list")
public class BookListRestController {

    @Autowired
    private BookListService bookListService;

    @Autowired
    private ModelMapper mapper;

    @PostMapping
    public void addList(@RequestBody BookListTo bookList) {
        this.bookListService.saveList(convertToEntity(bookList));
    }

    @PostMapping("/{listId}/addBook/{bookId}")
    public void addBookToList(@PathVariable long bookId, @PathVariable long listId) {
            this.bookListService.addBookToList(bookId, listId);
    }

    @GetMapping
    public BookListTo getBookList(@RequestParam long id) {
        return convertToDto(this.bookListService.getBookList(id));
    }

    @GetMapping("/all")
    public List<BookListTo> getAllBookLists() {
        return this.bookListService.getAllBookLists().stream().map(this::convertToDto).collect(Collectors.toList());
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

    private BookListTo convertToDto(BookListEntity bookListEntity) {
        BookListTo bookListTo = this.mapper.map(bookListEntity, BookListTo.class);
        List<String> books = new ArrayList<>();
        bookListEntity.getBooks().forEach(book -> books.add(book.getTitle()));
        bookListTo.setBooks(books);
        return bookListTo;
    }

    private BookListEntity convertToEntity(BookListTo bookListTo) {
        return this.mapper.map(bookListTo, BookListEntity.class);
    }
}
