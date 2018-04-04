package com.blopto.web.controller;


import com.blopto.web.repository.UserRepository;
import com.blopto.web.service.PostService;
import com.blopto.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collections;
import java.util.List;

@Controller
public class FacebookController {

    @Autowired
    private UserRepository userRepository;
    private Facebook facebook;
    private ConnectionRepository connectionRepository;
    private UserService userService;
    private PostService postService;


    public FacebookController(Facebook facebook, ConnectionRepository connectionRepository, UserService userService, PostService postService) {
        this.facebook = facebook;
        this.connectionRepository = connectionRepository;
        this.userService = userService;
        this.postService = postService;
    }

    @GetMapping("/login/facebook")
    public String helloFacebook(Model model) {
        if (connectionRepository.findPrimaryConnection(Facebook.class) == null) {
            return "redirect:/connect/facebook";
        }

        String [] fields = { "id","email","first_name","last_name"};
        User facebookUser = facebook.fetchObject("me", User.class, fields);
        com.blopto.web.bean.User user = userRepository.findByEmail(facebookUser.getEmail());

        if (user == null) {
            com.blopto.web.bean.User newUser = new com.blopto.web.bean.User();
            newUser.setEmail(facebookUser.getEmail());
            newUser.setFirstName(facebookUser.getFirstName());
            newUser.setLastName(facebookUser.getLastName());
            model.addAttribute("user", newUser);
            return "register";
        } else {
            Authentication authentication = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(),
                    AuthorityUtils.createAuthorityList("USER"));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return "redirect:/user";
        }
    }

}