package com.blopto.web.socket;

import com.blopto.web.bean.Statistics;
import com.blopto.web.bean.dto.RegistrationDTO;
import com.blopto.web.bean.dto.StatisticsDTO;
import com.blopto.web.repository.StatisticsRepository;
import com.blopto.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class StatisticController {

    private final StatisticsRepository repository;

    @Autowired
    StatisticController(StatisticsRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(value = "/api/statistics",method=RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public void statistics(@ModelAttribute StatisticsDTO statisticsDTO) {
        Statistics statistics = new Statistics();
        statistics.setBrowser(statisticsDTO.getBrowser());
        statistics.setScreenSize(statisticsDTO.getScreenSize());
        statistics.setBrowserVersion(statisticsDTO.getBrowserVersion());
        statistics.setOs(statisticsDTO.getOs());
        repository.save(statistics);
    }
}
