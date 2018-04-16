package com.blopto.web.controller;

import com.blopto.web.bean.Post;
import com.blopto.web.bean.User;
import com.blopto.web.service.PostService;
import com.blopto.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.List;

@Controller
public class IndexController {
    private static final String TEMPLATE_MAIN = "index";
    private final PostService postService;
    private final UserService userService;

    @Autowired
    IndexController(PostService postService, UserService userService) {
        this.postService = postService;
        this.userService = userService;
    }

    @GetMapping("/user")
    public String index(
            @RequestParam(value="username", required=false, defaultValue="Username") String username,
            @RequestParam(value="handle", required=false, defaultValue="username") String handle,
            Model model, Authentication authentication, Pageable pageable
    ) {

        List posts = postService.getAllPost();

        User user = userService.getUser(authentication.getName());
        Long postCount = postService.getCount(user.getId());

        Collections.reverse(posts);
        model.addAttribute("posts", posts);
        model.addAttribute("username", user.getFirstName());
        model.addAttribute("handle", authentication.getName());
        model.addAttribute("postCount", postCount);

        return TEMPLATE_MAIN;
    }

    @GetMapping("/")
    public String landing() {
        return "landing";
    }

}
