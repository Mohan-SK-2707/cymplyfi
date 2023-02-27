package com.flyerssoft.org_chart.controller;

import com.flyerssoft.org_chart.dto.EmployeePersonalDetailDto;
import com.flyerssoft.org_chart.dto.LoginRequestDto;
import com.flyerssoft.org_chart.response.AppResponse;
import com.flyerssoft.org_chart.response.CustomEmployeeResponseDto;
import com.flyerssoft.org_chart.response.LoginResponse;
import com.flyerssoft.org_chart.service.EmployeeService;
//import jakarta.validation.Valid;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
//@RequestMapping("/flyers-soft")
@Slf4j
@Validated
@CrossOrigin(origins = "http://localhost:8081")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    /**
     * Signup Api, Employee needs to Signup with all the requested credentials.
     * @param employeePersonalDetailDto
     * @return employeeDetails
     */
    @PostMapping("/employee/add")
    public ResponseEntity<AppResponse<EmployeePersonalDetailDto>> addEmployeeDetail(@Valid @RequestBody EmployeePersonalDetailDto employeePersonalDetailDto) {
        log.info("Add Employee Controller Accessed");
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.addEmployeeDetail(employeePersonalDetailDto));
    }

    /**
     * Fetch employee details by using employee id
     * @param id
     * @return employee details
     * @throws Exception
     */
    @GetMapping("/employee/{id}")
    public ResponseEntity<AppResponse<EmployeePersonalDetailDto>> getEmployeeById(@PathVariable(required = true) Long id) throws Exception {
        log.info("Fetch Employee Controller Accessed");
        return ResponseEntity.status(HttpStatus.OK).body(employeeService.getEmployeeDetailsById(id));
    }

    /**
     * Update api,To update Employee details, employee should already exist
     * Employee details can be updated by Admin
     * @param id
     * @param employeePersonalDetailDto
     * @return Updated employee credentials
     * @throws Exception
     */
    @PutMapping("/employee/update")
    public ResponseEntity<AppResponse<EmployeePersonalDetailDto>> updateEmployee(@RequestParam(required = true) Long id, @RequestBody EmployeePersonalDetailDto employeePersonalDetailDto) throws Exception {
        log.info("Update Employee Controller Accessed");
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(employeeService.updateEmployee(id, employeePersonalDetailDto));
    }

    /**
     * To delete employee details,employee should already exist
     * Employee details can be deleted by Admin
     * @param id
     * @return employee details will be deleted
     * @throws Exception
     */

    @DeleteMapping("/employee/remove/{id}")
    public ResponseEntity<AppResponse<String>> deleteEmployee(@PathVariable(required = true) Long id) throws Exception {
        log.info("Remove Employee Controller Accessed");
        return ResponseEntity.status(HttpStatus.OK).body(employeeService.deleteEmployee(id));
    }

    /**
     *  To login, employee need to Signup and employee can be login with his username and password.
     * @param loginRequestDto
     * @return employee credential
     * @throws Exception
     */
    @PostMapping("/employee/login")
    public ResponseEntity<AppResponse<LoginResponse>> userLogin(@RequestBody @Valid LoginRequestDto loginRequestDto) throws Exception {
    log.info("Login Request Accessed");
    return ResponseEntity.status(HttpStatus.ACCEPTED).body(employeeService.userLogin(loginRequestDto.getEmail(),loginRequestDto.getPassword()));
    }

    @GetMapping("/employee/all")
    public ResponseEntity<AppResponse<List<CustomEmployeeResponseDto>>> allEmployeeDtoResponse(){
        return ResponseEntity.status(HttpStatus.OK).body(employeeService.allEmployeeDtoResponse());
    }

}
