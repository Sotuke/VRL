package com.blopto.web.repository;

import com.blopto.web.bean.Statistics;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StatisticsRepository extends JpaRepository<Statistics, Long> {
    @Query("select s from Statistics s GROUP BY s order by count(s) desc")
    Statistics findMostUsedBrowser(Pageable pageable);

}
