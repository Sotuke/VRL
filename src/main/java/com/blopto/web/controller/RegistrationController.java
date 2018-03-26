package com.blopto.web.controller;

import com.blopto.web.bean.User;
import com.blopto.web.bean.dto.RegistrationDTO;
import com.blopto.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

@Controller
public class RegistrationController {

    private UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    /*
    @Autowired
    public RegistrationController(UserService userService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userService = userService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
    */

    @PostMapping(value = "/api/register", produces = "application/json")
    @ResponseBody
    public String processRegistrationForm(@ModelAttribute RegistrationDTO registrationDTO, Model model) {

        User user = new User();

        user.setFirstName(registrationDTO.getFirstName());
        user.setLastName(registrationDTO.getLastName());
        user.setUsername(registrationDTO.getUsername());
        user.setEmail(registrationDTO.getEmail());
        //user.setPassword(bCryptPasswordEncoder.encode(registrationDTO.getPassword()));
        user.setPassword(registrationDTO.getPassword());

        try {
            userService.registerUser(user);
            return "{\"success\":true}";

        } catch (Exception e) {
            return "{\"success\":false}";
        }
    }

    /*
    @GetMapping("/login")
    @ResponseBody
    public String login(Model model, Principal principal) {

        String username = principal.getName();
        System.out.println("User Name: " + username);

        User loginedUser = (User) ((Authentication) principal).getPrincipal();

        String userInfo = loginedUser.toString();
        model.addAttribute("userInfo", userInfo);

        return "login";
    }

*/
    @GetMapping("/user")
    public Principal user(Principal principal) {
        return principal;
    }

}
