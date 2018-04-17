package com.blopto.web.bean;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@Table(name = "posts")
public class Statistics {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String ip;
    private String screenSize;
    private String browser;
    private String browserVersion;
    private String mobile;
    private String os;
    private String osVersion;
    private String cookies;
}