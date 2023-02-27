package com.flyerssoft.org_chart.repo;

import com.flyerssoft.org_chart.model.EmployeeAddress;
import com.flyerssoft.org_chart.model.EmployeePersonalDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeAddressRepo extends JpaRepository<EmployeeAddress,Long> {
}
