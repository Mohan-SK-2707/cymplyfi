package com.flyerssoft.org_chart.controller;

import com.flyerssoft.org_chart.dto.EmployeePersonalDetailDto;
import com.flyerssoft.org_chart.dto.LoginRequestDto;
import com.flyerssoft.org_chart.dto.LoginResponse;
import com.flyerssoft.org_chart.service.EmployeeService;
//import jakarta.validation.Valid;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
//@RequestMapping("/flyers-soft")
@Slf4j
@Validated
@CrossOrigin(origins = "http://localhost:8081")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/employee/add")
    public ResponseEntity<EmployeePersonalDetailDto> addEmployeeDetail(@Valid @RequestBody EmployeePersonalDetailDto employeePersonalDetailDto) {
        log.info("Add Employee Controller Accessed");
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.addEmployeeDetail(employeePersonalDetailDto));
    }

    @GetMapping("/employee/{id}")
    public ResponseEntity<EmployeePersonalDetailDto> getEmployeeById(@PathVariable(required = true) Long id) throws Exception {
        log.info("Fetch Employee Controller Accessed");
        return ResponseEntity.status(HttpStatus.OK).body(employeeService.getEmployeeDetailsById(id));
    }

    @PutMapping("/employee/update")
    public ResponseEntity<EmployeePersonalDetailDto> updateEmployee(@RequestParam(required = true) Long id, @RequestBody EmployeePersonalDetailDto employeePersonalDetailDto) throws Exception {
        log.info("Update Employee Controller Accessed");
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(employeeService.updateEmployee(id, employeePersonalDetailDto));
    }

    @DeleteMapping("/employee/remove/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable(required = true) Long id) throws Exception {
        log.info("Remove Employee Controller Accessed");
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(employeeService.deleteEmployee(id));
    }

    @PostMapping("/employee/login")
    public ResponseEntity<LoginResponse> userLogin(@RequestBody LoginRequestDto loginRequestDto) throws Exception {
    log.info("Login Request Accessed");
    return ResponseEntity.status(HttpStatus.ACCEPTED).body(employeeService.userLogin(loginRequestDto.getEmail(),loginRequestDto.getPassword()));
    }

}
