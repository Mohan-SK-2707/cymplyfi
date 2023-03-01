package com.flyerssoft.org_chart.repo;

import com.flyerssoft.org_chart.enums.Role;
import com.flyerssoft.org_chart.model.EmployeePersonalDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Repository
public interface EmployeeDetailRepository extends JpaRepository<EmployeePersonalDetails,Long> {

    Boolean deleteUserById(Long id);

    EmployeePersonalDetails findByEmail(String personalEmailId);

    EmployeePersonalDetails findByContactNumber(String contactNumber);

    EmployeePersonalDetails findByRole(Role role);

    @Query(value = "SELECT * FROM employee_personal_details e WHERE e.department_id = :departmentId AND e.role = :role", nativeQuery = true)
    List<EmployeePersonalDetails> findByDepartment(@Param("departmentId") Long departmentId, @Param("role") String role);

    List<EmployeePersonalDetails> findByPrimaryReportingManager(Long primaryReportingManagerId);
}
