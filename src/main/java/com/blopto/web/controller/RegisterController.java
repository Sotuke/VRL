package com.blopto.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RegisterController {

    @GetMapping(value="/register")
    public String printWelcome(Model model) {
        return "register";
    }
}
