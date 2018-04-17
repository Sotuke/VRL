package com.blopto.web.socket;


import com.blopto.web.service.PostService;
import com.blopto.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UpdateController {

    private final UserService userService;

    @Autowired
    UpdateController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/api/test",produces = "application/json")
    public @ResponseBody String count(Model model) {
        long a = userService.countById();
        return "{\"number\":" + a +"}";
    }
}