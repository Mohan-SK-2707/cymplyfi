package com.flyerssoft.org_chart.service;

import com.flyerssoft.org_chart.dto.EmployeePersonalDetailDto;
import com.flyerssoft.org_chart.dto.LoginResponse;

public interface EmployeeService {

 /**
  * Add Employee details.
  *
  * @param employeePersonalDetailDto employeePersonalDetailDto
  * @return employee details
  */
 EmployeePersonalDetailDto addEmployeeDetail(EmployeePersonalDetailDto employeePersonalDetailDto);

 /**
  * Get employee details.
  *
  * @param id id
  * @return employee details
  * @throws Exception employee not found
  */
 EmployeePersonalDetailDto getEmployeeDetailsById (Long id) throws Exception;

 /**
  * Update employee details.
  *
  * @param id id
  * @param employeePersonalDetailDto employeePersonalDetailDto
  * @return updated employee details
  *  @throws Exception employee not found
  */
 EmployeePersonalDetailDto updateEmployee(Long id, EmployeePersonalDetailDto employeePersonalDetailDto) throws Exception ;

 /**
  * Delete employee details.
  *
  * @param id id
  * @return successful message
  * @throws Exception employee not found
  */
 String deleteEmployee(Long id) throws Exception;

 LoginResponse userLogin(String email, String password) throws Exception;
}
