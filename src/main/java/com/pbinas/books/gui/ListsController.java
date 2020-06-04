package com.pbinas.books.gui;

import com.pbinas.books.service.BookListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/lists")
public class ListsController {

    @Autowired
    private BookListService bookListService;

    @RequestMapping("/add")
    public String addList() {
        return "addList";
    }
}
