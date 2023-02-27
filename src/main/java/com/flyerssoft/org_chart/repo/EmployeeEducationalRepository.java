package com.flyerssoft.org_chart.repo;

import com.flyerssoft.org_chart.model.EmployeeEducationalDetails;
import com.flyerssoft.org_chart.model.EmployeePersonalDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeEducationalRepository extends JpaRepository<EmployeeEducationalDetails,Long> {
}
