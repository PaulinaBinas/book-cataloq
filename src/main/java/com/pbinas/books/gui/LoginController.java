package com.pbinas.books.gui;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/log-in")
public class LoginController {

    @RequestMapping
    public String login() {
        return "login";
    }

    @RequestMapping("/error")
    public String loginErroe(Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }
}
