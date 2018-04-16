package com.blopto.web.controller;


import com.blopto.web.bean.Post;
import com.blopto.web.bean.User;
import com.blopto.web.bean.dto.PostDTO;
import com.blopto.web.repository.UserRepository;
import com.blopto.web.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PostController {
    private final static String SUBSCRIPTION_PAGE = "/api/submitpost";
    private final PostService postService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping(value = "/api/submitpost", produces = "application/json")
    public @ResponseBody
    String addPost(@ModelAttribute PostDTO postDTO, Model model, Authentication authentication) {

        User user = userRepository.findByEmail(authentication.getName());

        Post post = new Post();
        post.setUser(user);
        post.setDate();
        post.setPost(postDTO.getPost());
        try {
            postService.addPost(post);
        } catch (Exception e) {
            return "{\"success\":false}";
        }
        //for (Post i : postService.getAllPost()) System.out.println(i.getDate() + " " + i.getId() + " " + i.getPost());
        return "{\"success\":true}";
    }


}
