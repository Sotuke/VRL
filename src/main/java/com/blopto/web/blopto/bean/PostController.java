package com.blopto.web.blopto.bean;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@Controller
public class PostController {
    private final static String SUBSCRIPTION_PAGE = "/api/submitpost";
    private final PostService postService;

    @Autowired
    PostController(PostService postService) {
        this.postService = postService;
    }

    @RequestMapping(value = "/api/submitpost",method = RequestMethod.POST)
    public @ResponseBody Post addPost(@RequestBody Post postDTO) {
        postDTO.setDate(new Timestamp(System.currentTimeMillis()));
        postService.addPost(postDTO);
        return postDTO;
    }

}
