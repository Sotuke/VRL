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
        List<Statistics> browser = repository.findMostUsedBrowser(new PageRequest(0, queryLimit));
        List<Statistics> screen = repository.findMostUsedScreensize(new PageRequest(0, queryLimit));
        List<Statistics> os = repository.findMostUsedOs(new PageRequest(0, queryLimit));
        if (browser.size() != 0) {
            model.addAttribute("browser",browser.get(0).getBrowser());
            System.out.println(browser.get(0).getBrowser());
            model.addAttribute("size",screen.get(0).getScreenSize());
            System.out.println(screen.get(0).getScreenSize());
            model.addAttribute("os",os.get(0).getOs());
            System.out.println(os.get(0).getOs());
        }
        return "about";
    }
}
