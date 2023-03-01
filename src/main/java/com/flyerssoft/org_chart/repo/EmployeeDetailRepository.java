package com.flyerssoft.org_chart.repo;

import com.flyerssoft.org_chart.model.EmployeePersonalDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public interface EmployeeDetailRepository extends JpaRepository<EmployeePersonalDetails,Long> {

    Boolean deleteUserById(Long id);

    EmployeePersonalDetails findByOfficialEmail(String officialEmail);

    EmployeePersonalDetails findByContactNumber(String contactNumber);
}
