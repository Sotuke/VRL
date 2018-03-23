package com.blopto.web.blopto.bean;


import com.blopto.web.blopto.bean.dto.PostDTO;
import com.blopto.web.blopto.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PostController {
    private final static String SUBSCRIPTION_PAGE = "/api/submitpost";
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
        try {
            postService.addPost(post);
        }
        catch (Exception e) {
            return "{\"success\":false}";
        }
        for (Post i : postService.getAllPost()) System.out.println(i.getDate() + " " + i.getId() + " " + i.getPost());
        return "{\"success\":true}";
    }


}
