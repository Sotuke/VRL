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

        return userRepository.save(newUser);
    }

    public User getUserData(User user) {
        return userRepository.findByEmail(user.getEmail());
    }
}