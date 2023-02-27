package com.flyerssoft.org_chart.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.flyerssoft.org_chart.model.OrgChart;

@EnableJpaRepositories
public interface OrgChartRepo extends JpaRepository<OrgChart, Long>{

}
