package com.blopto.web.controller;

import com.blopto.web.repository.UserRepository;
import com.blopto.web.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping(value="/login")
    public String printWelcome(Model model) {
        return "login";
    }
}
