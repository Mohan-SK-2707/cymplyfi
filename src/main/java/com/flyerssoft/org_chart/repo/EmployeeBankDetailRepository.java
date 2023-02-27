package com.flyerssoft.org_chart.repo;

import com.flyerssoft.org_chart.model.EmployeeBankDetails;
import com.flyerssoft.org_chart.model.EmployeePersonalDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeBankDetailRepository extends JpaRepository<EmployeeBankDetails,Long> {
}
