package com.flyerssoft.org_chart.service;

import com.flyerssoft.org_chart.dto.EmployeePersonalDetailDto;
import com.flyerssoft.org_chart.dto.OrganisationDepartmentResponse;
import com.flyerssoft.org_chart.response.AppResponse;
import com.flyerssoft.org_chart.response.CustomEmployeeResponseDto;
import com.flyerssoft.org_chart.response.LoginResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface EmployeeService {

 /**
  * Add Employee details.
  *
  * @param employeePersonalDetailDto employeePersonalDetailDto
  * @return employee details
  */
 AppResponse<EmployeePersonalDetailDto> addEmployeeDetail(EmployeePersonalDetailDto employeePersonalDetailDto);

 /**
  * Get employee details.
  *
  * @param id id
  * @return employee details
  * @throws Exception employee not found
  */
 AppResponse<EmployeePersonalDetailDto> getEmployeeDetailsById (Long id) throws Exception;

 /**
  * Update employee details.
  *
  * @param id id
  * @param employeePersonalDetailDto employeePersonalDetailDto
  * @return updated employee details
  *  @throws Exception employee not found
  */
 AppResponse<EmployeePersonalDetailDto> updateEmployee(Long id, EmployeePersonalDetailDto employeePersonalDetailDto) throws Exception ;

 /**
  * Delete employee details.
  *
  * @param id id
  * @return successful message
  * @throws Exception employee not found
  */
 AppResponse<String> deleteEmployee(Long id) throws Exception;

 /**
  * Login - To view the employee details
  * @param email email
  * @param password
  * @return employee details
  * @throws Exception
  */
 AppResponse<LoginResponse> userLogin(String email, String password) throws Exception;

 AppResponse<List<CustomEmployeeResponseDto>> allEmployeeDtoResponse();

 AppResponse<OrganisationDepartmentResponse> getCeoAndAllDepartments();

 AppResponse<List<EmployeePersonalDetailDto>> getManagersOfDepartment(Long departmentId);

 AppResponse<?> getChildEmployeesOrReportingManagers(Long reporteeId);
 }
