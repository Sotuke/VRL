package com.blopto.web.blopto.service;

import com.blopto.web.blopto.bean.User;
import com.blopto.web.blopto.registration.UserService;
import com.blopto.web.blopto.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserService userService;
    private UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserService userService) {
        this.userService = userService;
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // siin peaks vist Optionali kasutama
        ///User user = userRepository.findByUsername(username);
        //User user = userService(user)


        User user = userRepository.findByUsername(username);

        if (user == null) {
            System.out.println("User not found! " + username);
            throw new UsernameNotFoundException("User " + username + " was not found!");
        }

        UserDetails userDetails = (UserDetails) user;

        return userDetails;
    }

}