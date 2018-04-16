package com.blopto.web.bean.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistrationDTO {

    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;
    private String identityNumber;
    private String provider;
}
