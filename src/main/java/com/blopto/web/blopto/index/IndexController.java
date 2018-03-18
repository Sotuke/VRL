package com.blopto.web.blopto.index;

import com.blopto.web.blopto.service.ITweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class IndexController {

    @Autowired
    ITweetService tweetService;

    private static final String TEMPLATE_MAIN = "index";

    @GetMapping("/")
    public String index(
            @RequestParam(value="username", required=false, defaultValue="Username") String username,
            @RequestParam(value="handle", required=false, defaultValue="username") String handle,
            Model model
    ) {
        model.addAttribute("username", username);
        model.addAttribute("handle", handle);
        return TEMPLATE_MAIN;
    }


}

/* näidis!
public class MyController {

    @Autowired
    ICityService cityService;

    @RequestMapping("/showCities")
    public String findCities(Model model) {

        List<City> cities = (List<City>) cityService.findAll();

        model.addAttribute("cities", cities);

        return "showCities";
    }
}*/

