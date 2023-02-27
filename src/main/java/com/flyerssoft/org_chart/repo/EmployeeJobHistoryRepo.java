package com.flyerssoft.org_chart.repo;

import com.flyerssoft.org_chart.model.EmployeeJobHistory;
import com.flyerssoft.org_chart.model.EmployeePersonalDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeJobHistoryRepo extends JpaRepository<EmployeeJobHistory,Long> {
}
