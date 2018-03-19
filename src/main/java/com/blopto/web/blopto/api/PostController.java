package com.blopto.web.blopto.api;


import com.blopto.web.blopto.bean.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class PostController {
    private final static String SUBSCRIPTION_PAGE = "/api/submitpost";
    private final PostService postService;

    @Autowired
    PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/api/submitpost")
    public @ResponseBody
    String addPost(@ModelAttribute Post postDTO) {
        System.out.println("mida");
        Post post = new Post();
        post.setPost(postDTO.getPost());
        post.setDate((int) System.currentTimeMillis() / 1000);
        System.out.println(post);
        postService.addPost(post);
        return "out";
    }
}
