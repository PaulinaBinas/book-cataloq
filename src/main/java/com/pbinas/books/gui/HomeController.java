package com.pbinas.books.gui;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {
    @RequestMapping
    public String home() {
        return "home";
    }

    @RequestMapping("success")
    public String success() { return "success"; }
}
