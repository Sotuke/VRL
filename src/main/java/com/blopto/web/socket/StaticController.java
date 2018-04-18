package com.blopto.web.socket;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class StaticController {

    @GetMapping(value = "/static")
    public String staticCont(Model model) {
        return "static";
    }
}
