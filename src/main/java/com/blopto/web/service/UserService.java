package com.blopto.web.service;

import com.blopto.web.bean.User;
import com.blopto.web.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service("userService")
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public User registerUser(User newUser) {

        User existing = userRepository.findByEmail(newUser.getEmail());
        if (existing != null) {
            throw new UnsupportedOperationException("Cannot register user with same e-mail twice");
        }

        existing = userRepository.findByUsername(newUser.getUsername());
        if (existing != null) {
            throw new UnsupportedOperationException("Cannot register user with same username twice");
        }

        existing = userRepository.findByIdentityNumber(newUser.getIdentityNumber());
        if (existing != null && existing.getIdentityNumber() != null) {
            throw new UnsupportedOperationException("Cannot register user with same identity number twice");
        }

        return userRepository.save(newUser);
    }
    public User getUser(String user){return userRepository.findByUsername(user);};

    public User getUserData(User user) {
        return userRepository.findByEmail(user.getEmail());
    }
}