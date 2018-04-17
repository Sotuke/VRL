package com.blopto.web.security.facebook;

import com.blopto.web.bean.dto.RegistrationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.User;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class FacebookProvider  {

    private static final String FACEBOOK = "facebook";
    private static final String REDIRECT_LOGIN = "redirect:/login";
    private static final String REDIRECT_USER = "redirect:/user";

    @Autowired
    BaseProvider baseProvider ;


    public String getFacebookUserData(Model model, com.blopto.web.bean.User userForm) {
        ConnectionRepository connectionRepository = baseProvider.getConnectionRepository();
        if (connectionRepository.findPrimaryConnection(Facebook.class) == null) {
            return REDIRECT_LOGIN;
        }
        populateUserDetailsFromFacebook(userForm);
        baseProvider.saveUserDetails(userForm);
        baseProvider.autoLoginUser(userForm);
        model.addAttribute("loggedInUser",userForm);
        return REDIRECT_USER;
    }

    protected void populateUserDetailsFromFacebook(com.blopto.web.bean.User userForm) {
        System.out.print(2);
        Facebook facebook = baseProvider.getFacebook();
        String [] fields = { "id","email","first_name","last_name"};
        User user = facebook.fetchObject("me", User.class, fields);
        userForm.setEmail(user.getEmail());
        userForm.setFirstName(user.getFirstName());
        userForm.setLastName(user.getLastName());
        userForm.setUsername(user.getFirstName() + user.getLastName());
        userForm.setProvider(FACEBOOK);
    }



}