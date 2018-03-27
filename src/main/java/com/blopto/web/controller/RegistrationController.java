package com.blopto.web.controller;

import com.blopto.web.bean.User;
import com.blopto.web.bean.dto.RegistrationDTO;
import com.blopto.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

@Controller
public class RegistrationController {

    //private BCryptPasswordEncoder bCryptPasswordEncoder;
    private UserService userService;

    private JavaMailSender mailSender;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
        //this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    @GetMapping("/registration")
    public String getRegistrationPage(Model model) {
        RegistrationDTO registrationDTO = new RegistrationDTO();
        model.addAttribute("user", registrationDTO);

        return "registration";
    }



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

            SimpleMailMessage email = new SimpleMailMessage();

            email.setTo("kem.laurits@gmail.com");
            email.setFrom("kem.laurits@gmail.com");
            email.setSubject("subject");
            email.setText("yolo!");
            mailSender.send(email);

            return "{\"success\":true}";

        } catch (Exception e) {
            return "{\"success\":false}";
        }
    }


    @GetMapping("/user")
    public Principal user(Principal principal) {
        return principal;
    }

}
