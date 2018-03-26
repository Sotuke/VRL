package com.blopto.web.service;

import com.blopto.web.bean.User;
import com.blopto.web.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.stereotype.Service;

@Service
public class FacebookConnectionSignup implements ConnectionSignUp {

    @Autowired
    private UserRepository userRepository;

    @Override
    public String execute(Connection<?> connection) {
        System.out.println("signup === ");
        final User user = new User();
        user.setUsername(connection.getDisplayName());
        user.setEmail(connection.fetchUserProfile().getEmail());
        user.setFirstName(connection.fetchUserProfile().getFirstName());
        user.setLastName(connection.fetchUserProfile().getLastName());
        user.setPassword("asd");
        userRepository.save(user);
        return user.getUsername();
    }

}