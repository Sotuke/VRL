package com.blopto.web.blopto.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // whitelist everything for now
        http.authorizeRequests().antMatchers("/**").permitAll();

        // public resources
        //String[] resources = {"/greeting", "/css/**", "/js/**", "/subscription"};
        // with spring security, we authorize all requests except the paths described in "resources" array above
        /*http.cors().and().csrf().disable();
        http.authorizeRequests()
                .antMatchers(HttpMethod.GET, resources).permitAll()
                .antMatchers(HttpMethod.POST, resources).permitAll()
                .anyRequest().authenticated().and().formLogin().permitAll();*/
    }
}
