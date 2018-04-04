package com.blopto.web.controller;

import com.blopto.web.bean.User;
import com.blopto.web.bean.dto.RegistrationDTO;
import com.blopto.web.repository.UserRepository;
import com.blopto.web.service.UserService;
import ee.sk.smartid.AuthenticationHash;
import ee.sk.smartid.SmartIdAuthenticationResponse;
import ee.sk.smartid.SmartIdClient;
import ee.sk.smartid.rest.dao.NationalIdentity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class SmartIdController {

    @Autowired
    private UserRepository userRepository;
    private UserService userService;

    @GetMapping("/login/smart")
    public String smartIDLogin(Model model) {
        RegistrationDTO registrationDTO = new RegistrationDTO();
        model.addAttribute("user", registrationDTO);


        return "smart";
    }

    @PostMapping(value = "/api/smart", produces = "application/json")
    public String smartId(@ModelAttribute RegistrationDTO registrationDTO, Model model) {

        // Create new SmartIdClient against testing environment
        SmartIdClient client = new SmartIdClient();
        client.setRelyingPartyUUID("00000000-0000-0000-0000-000000000000");
        client.setRelyingPartyName("DEMO");
        client.setHostUrl("https://sid.demo.sk.ee/smart-id-rp/v1/");

        String identityNumber = registrationDTO.getIdentityNumber();
        NationalIdentity nationalIdentity = new NationalIdentity("EE",identityNumber);

        // For security reasons a new hash value must be created for each new authentication request
        AuthenticationHash authenticationHash = AuthenticationHash.generateRandomHash();


        // Verify that the user with the specified identity number exists
        User user = userRepository.findByIdentityNumber(identityNumber);
        if (user != null) {

            try {
                SmartIdAuthenticationResponse authenticationResponse = client
                        .createAuthentication()
                        .withNationalIdentity(nationalIdentity)
                        .withAuthenticationHash(authenticationHash)
                        .withCertificateLevel("QUALIFIED") // Certificate level can either be "QUALIFIED" or "ADVANCED"
                        .authenticate();

                //return "{\"success\":true}";
                String username = user.getUsername();
                String password = user.getPassword();

                Authentication authentication = new UsernamePasswordAuthenticationToken(username, password,
                        AuthorityUtils.createAuthorityList("USER"));
                SecurityContextHolder.getContext().setAuthentication(authentication);

                return "redirect:/user";

            } catch (Exception exception) {
                return "redirect:/login/smart";
            }
        } else {
            return "redirect:/login/smart";

        }


    }

}
