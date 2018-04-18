package com.blopto.web.controller;

import com.blopto.web.bean.Statistics;
import com.blopto.web.repository.StatisticsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class AboutController {

    private final StatisticsRepository repository;

    @Autowired
    AboutController(StatisticsRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/about")
    public String getMap(Model model) {
        int queryLimit = 1;
        List<String> browser = repository.findMostUsedBrowser(new PageRequest(0, queryLimit));
        List<String> screen = repository.findMostUsedScreensize(new PageRequest(0, queryLimit));
        List<String> os = repository.findMostUsedOs(new PageRequest(0, queryLimit));
        if (browser.size() != 0) {
            model.addAttribute("browser",browser.get(0));
            System.out.println(browser.get(0));
            model.addAttribute("size",screen.get(0));
            System.out.println(screen.get(0));
            model.addAttribute("os",os.get(0));
            System.out.println(os.get(0));
        }
        return "about";
    }
}
