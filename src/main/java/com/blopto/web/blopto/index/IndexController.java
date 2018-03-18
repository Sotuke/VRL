package com.blopto.web.blopto.index;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {

    private static final String TEMPLATE_MAIN = "index";

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String index(
            @RequestParam(value="username", required=false, defaultValue="Username") String username,
            @RequestParam(value="handle", required=false, defaultValue="username") String handle,
            Model model
    ) {
        model.addAttribute("username", username);
        model.addAttribute("handle", handle);
        return TEMPLATE_MAIN;
    }
}

