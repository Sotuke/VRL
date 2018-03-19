package com.blopto.web.blopto.bean;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PostController {
    private final PostService postService;

    @Autowired
    PostController(PostService postService) {
        this.postService = postService;
    }

    @RequestMapping(value = "/api/submitpost",method = RequestMethod.POST)
    public void addPost(@RequestBody Post postDTO) {
        System.out.println(postDTO.getDate() + " " + postDTO.getId());
        postService.addPost(postDTO);
        List<Post> ee = postService.getAllPost();
        for (Post e: ee){
            System.out.println(e.getDate());
            System.out.println(e.getId());
            System.out.println(e.getPost());

        }
    }

}
