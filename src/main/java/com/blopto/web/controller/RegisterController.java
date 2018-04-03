
package com.blopto.web.controller;

import com.blopto.web.bean.User;
import com.blopto.web.bean.dto.RegistrationDTO;
import com.blopto.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterController {

    @Autowired
    private UserService userService;
    @Autowired
    private MailSender mailSender;

    @GetMapping(value="/register")
    public String printWelcome(Model model) {
        return "register";
    }

    @PostMapping(value = "/api/register")
    public String processRegistrationForm(RegistrationDTO registrationDTO) {
        System.out.println("eee");
        User user = new User();

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        user.setFirstName(registrationDTO.getFirstName());
        user.setLastName(registrationDTO.getLastName());
        user.setUsername(registrationDTO.getUsername());
        user.setEmail(registrationDTO.getEmail());
        user.setIdentityNumber(registrationDTO.getIdentityNumber());
        user.setPassword(bCryptPasswordEncoder.encode(registrationDTO.getPassword()));

        try {
            userService.registerUser(user);

            try {
                SimpleMailMessage email = new SimpleMailMessage();
                email.setTo(user.getEmail());
                email.setFrom("kem.laurits@gmail.com");
                email.setSubject("Welcome to Blopto!");
                email.setText("Welcome, " + user.getUsername() + "!\nThis is a small notification that " +
                        "you have been added to ");

                mailSender.send(email);

            } catch (Exception ex) {
                ex.printStackTrace();
            }

            return "login";

        } catch (Exception e) {
            e.printStackTrace();
            return "{\"success\":false,\"error\":\"" + e.getMessage() + "\"}";
        }
    }
}