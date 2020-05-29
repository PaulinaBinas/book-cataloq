package com.pbinas.books.rest;

import com.pbinas.books.repository.AuthorRepository;
import com.pbinas.books.service.BookService;
import com.pbinas.books.model.entity.BookEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/book")
public class BookRestController {

    @Autowired
    private BookService bookService;

    @Autowired
    private AuthorRepository authorRepository;

    @PostMapping
    public void addBook(@RequestBody BookEntity book) {
        book.getAuthors().stream().forEach(author -> authorRepository.save(author));
        book.setDateAdded(LocalDate.now());
        this.bookService.save(book);
    }

    @GetMapping
    public BookEntity getBook(@RequestParam long id) {
        return this.bookService.findById(id);
    }

    @GetMapping("/all")
    public List<BookEntity> getAllBooks() {
        return this.bookService.findAll();
    }

    @DeleteMapping
    public void removeBook(@RequestParam long id) { this.bookService.removeBook(id); }

    @PutMapping("/modify")
    public void modifyBook(@RequestBody BookEntity book) {
        this.bookService.modifyBook(book);
    }
}
