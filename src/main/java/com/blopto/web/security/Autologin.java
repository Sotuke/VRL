package com.blopto.web.security;

import java.util.HashSet;
import java.util.Set;

import com.blopto.web.bean.User;
import com.blopto.web.bean.dto.RegistrationDTO;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


@Service
public class Autologin {


    public void setSecuritycontext(User userForm) {
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("USER"));
        Authentication authentication = new UsernamePasswordAuthenticationToken(userForm.getUsername(), userForm.getPassword(), grantedAuthorities);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
