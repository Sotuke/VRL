package com.blopto.web.repository;

import com.blopto.web.bean.Statistics;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StatisticsRepository extends JpaRepository<Statistics, Long> {
    @Query("select s.browser from Statistics s GROUP BY s.browser order by count(s.browser) desc ")
    List<String> findMostUsedBrowser(Pageable pageable);
    @Query("select s.screenSize from Statistics s GROUP BY s.screenSize order by count(s.screenSize) desc ")
    List<String> findMostUsedScreensize(Pageable pageable);
    @Query("select s.os from Statistics s GROUP BY s.os order by count(s.os) desc ")
    List<String> findMostUsedOs(Pageable pageable);
    List<Statistics> findAll();
}
