package com.blopto.web.blopto.index;

import com.blopto.web.blopto.service.ITweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {

    @Autowired
    ITweetService tweetService;

    private static final String TEMPLATE_MAIN = "index";

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String index(
            @RequestParam(value="username", required=false, defaultValue="Username") String username,
            @RequestParam(value="handle", required=false, defaultValue="username") String handle,
            Model model
    ) {
        model.addAttribute("username", username);
        model.addAttribute("handle", handle);
        System.out.println("weee");
        return TEMPLATE_MAIN;
    }
    @GetMapping("/sasa")
    public String getSubscriptons(Model model) {
        //model.addAttribute("subscriptions", tweetService.findAll());
        System.out.println("weee");
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

