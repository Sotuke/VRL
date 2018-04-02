package com.blopto.web.controller;


import com.blopto.web.repository.UserRepository;
import com.blopto.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HelloController {

    @Autowired
    private UserRepository userRepository;
    private Facebook facebook;
    private ConnectionRepository connectionRepository;
    private UserService userService;

    public HelloController(Facebook facebook, ConnectionRepository connectionRepository, UserService userService) {
        this.facebook = facebook;
        this.connectionRepository = connectionRepository;
        this.userService = userService;
    }

    @GetMapping
    public String helloFacebook(Model model) {
        if (connectionRepository.findPrimaryConnection(Facebook.class) == null) {
            return "redirect:/connect/facebook";
        }

        String [] fields = { "id","name","email","first_name","last_name"};
        User facebookUser = facebook.fetchObject("me", User.class, fields);
        System.out.println(facebookUser.getEmail());
        com.blopto.web.bean.User user = userRepository.findByEmail(facebookUser.getEmail());

        if (user == null) {
            com.blopto.web.bean.User newUser = new com.blopto.web.bean.User();
            newUser.setEmail(facebookUser.getEmail());
            newUser.setFirstName(facebookUser.getFirstName());
            newUser.setLastName(facebookUser.getLastName());
            model.addAttribute("user", newUser);
            return "registration";
        } else {
            return "test2";
        }
    }
}