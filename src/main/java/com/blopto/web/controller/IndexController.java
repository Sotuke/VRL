package com.blopto.web.controller;

import com.blopto.web.bean.User;
import com.blopto.web.service.PostService;
import com.blopto.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
    @ModelAttribute("loggedInUser")
    public void secure(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByEmail(auth.getName());
        model.addAttribute("loggedInUser", user);
    }
    @GetMapping("/user")
    public String index(
            @RequestParam(value="username", required=false, defaultValue="Username") String username,
            @RequestParam(value="handle", required=false, defaultValue="username") String handle,
            Model model, Authentication authentication
    ) {
        System.out.println(userService.countById());
        List posts = postService.findByUserId(userService.findByEmail(authentication.getName()).getId());
        Collections.reverse(posts);
        model.addAttribute("posts", posts);
        model.addAttribute("username", authentication.getName() );
        model.addAttribute("handle", handle);
        return TEMPLATE_MAIN;
    }

    @GetMapping("/")
    public String landing(Authentication authentication,Model model) {
        if (authentication != null) return "redirect:/user";
        model.addAttribute("count", userService.countById());
        return "landing";
    }


}
