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
        List<Statistics> e = repository.findMostUsedBrowser(new PageRequest(0, queryLimit));
        if (e.size() != 0) {
        }
        return "about";
    }
}
