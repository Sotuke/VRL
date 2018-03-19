package com.blopto.web.blopto.api;


import com.blopto.web.blopto.api.PostDTO.PostDTO;
// import org.json.JSONObject; //???
import com.blopto.web.blopto.bean.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PostController {
    private final static String SUBSCRIPTION_PAGE = "/api/submitpost";
    private final PostService postService;

    @Autowired
    PostController(PostService postService) {
        this.postService = postService;
    }

    @RequestMapping(value = "/api/submitpost", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    String addPost(@ModelAttribute PostDTO postDTO, Model model) {
        Post post = new Post();
        post.setDate();
        post.setPost(postDTO.getPost());
        try {
            postService.addPost(post);
        }
        catch (Exception e) {
            return "{\"success\": false}";
        }
        //for (Post i : postService.getAllPosts()) System.out.println(i.getDate() + " " + i.getId() + " " + i.getPost());
        return "{\"success\": true}";
    }


}
