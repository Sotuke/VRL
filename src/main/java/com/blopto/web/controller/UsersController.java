package com.blopto.web.controller;

import com.blopto.web.bean.User;
import com.blopto.web.service.PostService;
import com.blopto.web.service.UserService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class UsersController {

    private final UserService userService;

    @Autowired
    UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value="/api/users")
    public @ResponseBody
    String users(Model model, Authentication authentication) {
        JSONObject jsonObject = new JSONObject();
        List<User> users = userService.findAll();
        for (User user: users) {
            try {
                jsonObject.append("username", user.getUsername());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        System.out.println(jsonObject.toString());
        return jsonObject.toString();
    }
}
