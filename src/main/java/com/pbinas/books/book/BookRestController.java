package com.pbinas.books.book;

import com.pbinas.books.author.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/book")
public class BookRestController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @PostMapping
    public void addBook(@RequestBody BookEntity book) {
        book.getAuthors().stream().forEach(author -> authorRepository.save(author));
        book.setDateAdded(LocalDate.now());
        this.bookRepository.save(book);
    }

    @GetMapping
    public BookEntity getBook(@RequestParam long id) {
        return this.bookRepository.findDistinctById(id);
    }

    @GetMapping("/all")
    public List<BookEntity> getAllBooks() {
        return this.bookRepository.findAll();
    }
}
