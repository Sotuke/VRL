package com.blopto.web.controller;


import com.blopto.web.service.UserService;
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
        String name=facebookUser.getName();
        String email=facebookUser.getEmail();
        String firstname=facebookUser.getFirstName();
        String lastname=facebookUser.getLastName();
        model.addAttribute("name",name );
        model.addAttribute("email",email );
        model.addAttribute("firstname",firstname);
        model.addAttribute("lastname",lastname);
        model.addAttribute("facebookProfile", facebook.fetchObject("me", User.class, fields));

        System.out.println(facebookUser.getEmail());
        System.out.println(facebookUser.getFirstName());
        System.out.println(facebookUser.getLastName());
        System.out.println(facebookUser.getName());

        com.blopto.web.bean.User user = new com.blopto.web.bean.User();

        user.setEmail(facebookUser.getEmail());
        user.setFirstName(facebookUser.getFirstName());
        user.setLastName(facebookUser.getLastName());

        userService.registerUser(user);

        return "hello";
    }

}