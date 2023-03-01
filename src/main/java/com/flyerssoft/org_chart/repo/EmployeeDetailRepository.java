package com.flyerssoft.org_chart.repo;

import com.flyerssoft.org_chart.model.EmployeePersonalDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Repository
public interface EmployeeDetailRepository extends JpaRepository<EmployeePersonalDetails,Long> {

    Boolean deleteUserById(Long id);

    EmployeePersonalDetails findByEmail(String personalEmailId);

    EmployeePersonalDetails findByContactNumber(String contactNumber);

    EmployeePersonalDetails findByRole(String role);

    List<EmployeePersonalDetails> findByDepartmentAndRole(Long departmentId, String role);
}
