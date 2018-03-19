package com.blopto.web.blopto.bean;


import com.blopto.web.blopto.bean.PostDTO.PostDTO;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
public class PostController {
    private final static String SUBSCRIPTION_PAGE = "subscription/subscription";
    private final PostService postService;

    @Autowired
    PostController(PostService postService) {
        this.postService = postService;
    }

    @RequestMapping(value = "/api/submitpost",method = RequestMethod.POST,produces = "application/json")
    public @ResponseBody
    String addPost(@ModelAttribute PostDTO postDTO, Model model) {
        Post post = new Post();
        post.setDate();
        post.setPost(postDTO.getPost());
        postService.addPost(post);
        return post.toString();
    }


}
