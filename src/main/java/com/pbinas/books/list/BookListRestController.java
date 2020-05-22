package com.pbinas.books.list;

import com.pbinas.books.book.BookEntity;
import com.pbinas.books.book.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/list")
public class BookListRestController {

    @Autowired
    private BookListRepository bookListRepository;

    @Autowired
    private BookRepository bookRepository;

    @PostMapping
    public void addList(@RequestBody BookListEntity bookList) {
        this.bookListRepository.save(bookList);
    }

    @PostMapping("/{listId}/addBook/{bookId}")
    public void addBookToList(@PathVariable long bookId, @PathVariable long listId) {
        BookEntity book = this.bookRepository.findDistinctById(bookId);
        if(book != null) {
            BookListEntity bookList = this.bookListRepository.findDistinctById(listId);
            bookList.getBooks().add(book);
            this.bookListRepository.save(bookList);
        }
    }

    @GetMapping
    public BookListEntity getBookList(@RequestParam long id) {
        return this.bookListRepository.findDistinctById(id);
    }

    @GetMapping("/all")
    public List<BookListEntity> getAllBookLists() {
        return this.bookListRepository.findAll();
    }
}
