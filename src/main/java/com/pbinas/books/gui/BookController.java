package com.pbinas.books.gui;

import com.pbinas.books.service.BookListService;
import com.pbinas.books.service.impl.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookServiceImpl bookService;

    @Autowired
    private BookListService bookListService;

    @RequestMapping
    public String login(Model model) {
        model.addAttribute("books", bookService.findAll());
        model.addAttribute("lists", bookListService.getAllBookLists());
        return "books";
    }

    @RequestMapping("/add")
    public String addBook() {
        return "addBook";
    }
}
