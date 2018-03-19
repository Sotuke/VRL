package com.blopto.web.blopto.registration;

import com.blopto.web.blopto.bean.User;
import com.blopto.web.blopto.bean.dto.RegistrationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RegistrationController {

    private UserService userService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public RegistrationController(UserService userService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userService = userService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    @PostMapping(value = "/api/register", produces = "application/json")
    @ResponseBody
    public String processRegistrationForm(@ModelAttribute RegistrationDTO registrationDTO, Model model) {

        User user = new User();
        user.setFirstName(registrationDTO.getFirstName());
        user.setLastName(registrationDTO.getLastName());
        user.setEmail(registrationDTO.getEmail());
        user.setPassword(bCryptPasswordEncoder.encode(registrationDTO.getPassword()));

        try {
            userService.registerUser(user);
            return "{\"success\":true}";

        } catch (Exception e) {
            return "{\"success\":false}";
        }
    }

}
