package com.flyerssoft.org_chart.repo;

import com.flyerssoft.org_chart.model.EmployeeDepartment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeDepartmentRepository extends JpaRepository<EmployeeDepartment,Long> {
}
