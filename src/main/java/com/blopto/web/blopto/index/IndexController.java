package com.blopto.web.blopto.index;

import com.blopto.web.blopto.bean.Post;
import com.blopto.web.blopto.bean.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class IndexController {


    private static final String TEMPLATE_MAIN = "index";

    @GetMapping("/index")
    public @ResponseBody String index(
            @RequestParam(value="username", required=false, defaultValue="Username") String username,
            @RequestParam(value="handle", required=false, defaultValue="username") String handle,
            Model model
    ) {
        model.addAttribute("username", username);
        model.addAttribute("handle", handle);
        return TEMPLATE_MAIN;
    }




}

