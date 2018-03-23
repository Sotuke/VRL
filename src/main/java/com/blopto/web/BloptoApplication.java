package com.blopto.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class BloptoApplication {

    public static void main(String[] args) {
        SpringApplication.run(BloptoApplication.class, args);
    }
}
