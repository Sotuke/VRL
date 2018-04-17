package com.blopto.web.controller;

import com.blopto.web.bean.User;
import com.blopto.web.service.PostService;
import com.blopto.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SettingsController {

    private final UserService userService;

    @Autowired
    SettingsController(PostService postService, UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/settings")
    public String settings(Model model, Authentication authentication) {
        return "settings";
    }

}
