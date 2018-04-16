package com.blopto.web.controller;

import com.blopto.web.bean.User;
import com.blopto.web.bean.dto.RegistrationDTO;
import com.blopto.web.security.facebook.FacebookProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

    @Autowired
    FacebookProvider facebookProvider;


    @RequestMapping(value = "/facebook", method = RequestMethod.GET)
    public String loginToFacebook(Model model) {
        return facebookProvider.getFacebookUserData(model, new User());
    }
    @GetMapping(value="/login")
    public String login(Model model) {
        return "login";
    }
}
