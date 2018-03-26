package com.blopto.web.controller;

import com.blopto.web.bean.User;
import com.blopto.web.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.PagedList;
import org.springframework.social.facebook.api.Post;
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

    public HelloController(Facebook facebook, ConnectionRepository connectionRepository) {
        this.facebook = facebook;
        this.connectionRepository = connectionRepository;
    }


    @GetMapping
    public String helloFacebook(Model model) {
        if (connectionRepository.findPrimaryConnection(Facebook.class) == null) {
            return "redirect:/connect/facebook";
        }

        User user = new User();

        user.setFirstName(facebook.userOperations().getUserProfile().getFirstName());
        user.setLastName(facebook.userOperations().getUserProfile().getLastName());
        user.setUsername(facebook.userOperations().getUserProfile().getId());
        user.setEmail(facebook.userOperations().getUserProfile().getEmail());

        // mingi uuema fb api versiooniga alljärgnev ei tööta enam
        //model.addAttribute("facebookProfile", facebook.userOperations().getUserProfile());

        userRepository.save(user);

        PagedList<Post> feed = facebook.feedOperations().getFeed();
        model.addAttribute("feed", feed);
        return "hello";



    }

}