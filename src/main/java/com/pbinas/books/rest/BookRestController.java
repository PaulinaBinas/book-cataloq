package com.pbinas.books.rest;

import com.pbinas.books.model.dto.BookTo;
import com.pbinas.books.repository.AuthorRepository;
import com.pbinas.books.service.BookService;
import com.pbinas.books.model.entity.BookEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.awt.*;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/book")
public class BookRestController {

    @Autowired
    private BookService bookService;

    @Autowired
    private ModelMapper mapper;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addBook(@RequestBody BookTo book) {
        this.bookService.save(convertToEntity(book));
    }

    @GetMapping
    public BookTo getBook(@RequestParam long id) {
        return convertToDto(this.bookService.findById(id));
    }

    @GetMapping("/all")
    public List<BookTo> getAllBooks() {
        return this.bookService.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @DeleteMapping
    public void removeBook(@RequestParam long id) {
        this.bookService.removeBook(id);
    }

    @PutMapping("/modify")
    public void modifyBook(@RequestBody BookTo book) {
        this.bookService.modifyBook(convertToEntity(book));
    }

    private BookTo convertToDto(BookEntity bookEntity) {
        return this.mapper.map(bookEntity, BookTo.class);
    }

    private BookEntity convertToEntity(BookTo bookTo) {
        return this.mapper.map(bookTo, BookEntity.class);
    }
}
