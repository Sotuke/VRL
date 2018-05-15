package com.blopto.web.bean.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StatisticsDTO {
    private String screenSize;
    private String browser;
    private String browserVersion;
    private String mobile;
    private String os;
    private String osVersion;
    private String cookies;
}
