package com.blopto.web.service;

import com.blopto.web.bean.Post;
import com.blopto.web.bean.User;
import com.blopto.web.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service("userService")
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostService postService;

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

        //existing = userRepository.findByIdentityNumber(newUser.getIdentityNumber());
        //if (existing != null && existing.getIdentityNumber() != null) {
         //   throw new UnsupportedOperationException("Cannot register user with same identity number twice");
        //}

        return userRepository.save(newUser);
    }
    @Transactional
    public void removeUser(User user){
        if (containsUser(user)){
            postService.removePosts(user);
            userRepository.deleteUserByEmail(user.getEmail());
        }
    }
    public boolean containsUser(User user){
        try {
            findByEmail(user.getEmail());
        }catch (Exception exception){
            return false;
        }
        return true;
    }
    public User getUser(String user){return userRepository.findByUsername(user);};

    public User getUserData(User user) {
        return userRepository.findByEmail(user.getEmail());
    }

    public User findByEmail(String name) {
        return userRepository.findByEmail(name);
    }
    public Long countById(){return userRepository.count();}
    public List<User> findAll() {return  userRepository.findAll();};
}