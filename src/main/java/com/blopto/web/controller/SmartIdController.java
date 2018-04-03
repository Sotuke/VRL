package com.blopto.web.service;

import com.blopto.web.bean.dto.RegistrationDTO;
import ee.sk.smartid.AuthenticationHash;
import ee.sk.smartid.SmartIdAuthenticationResponse;
import ee.sk.smartid.SmartIdClient;
import ee.sk.smartid.rest.dao.NationalIdentity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class SmartIdController {

    @GetMapping("login")
    public String smartIDLogin(Model model) {
        RegistrationDTO registrationDTO = new RegistrationDTO();
        model.addAttribute("user", registrationDTO);
        return "login";
    }

    @PostMapping("/api/smart")
    @ResponseBody
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

        try {

            SmartIdAuthenticationResponse authenticationResponse = client
                    .createAuthentication()
                    .withNationalIdentity(nationalIdentity)
                    .withAuthenticationHash(authenticationHash)
                    .withCertificateLevel("QUALIFIED") // Certificate level can either be "QUALIFIED" or "ADVANCED"
                    .authenticate();
            return "{\"success\":true}";

        } catch (Exception exception) {
            return "{\"success\":false,\"error\":" + exception + "}";
        }
    }

}
