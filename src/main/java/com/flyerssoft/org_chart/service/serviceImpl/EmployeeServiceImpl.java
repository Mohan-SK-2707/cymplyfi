package com.flyerssoft.org_chart.service.serviceImpl;

import com.flyerssoft.org_chart.dto.*;
import com.flyerssoft.org_chart.enums.Role;
import com.flyerssoft.org_chart.enums.AddressType;
import com.flyerssoft.org_chart.exceptionhandler.AddressAlreadyExistException;
import com.flyerssoft.org_chart.exceptionhandler.BadCredentialException;
import com.flyerssoft.org_chart.exceptionhandler.NotFoundException;
import com.flyerssoft.org_chart.exceptionhandler.ResourceAlreadyExistsException;
import com.flyerssoft.org_chart.model.*;
import com.flyerssoft.org_chart.repo.*;
import com.flyerssoft.org_chart.response.AppResponse;
import com.flyerssoft.org_chart.response.CustomEmployeeResponseDto;
import com.flyerssoft.org_chart.response.LoginResponse;
import com.flyerssoft.org_chart.security.JwtTokenUtils;
import com.flyerssoft.org_chart.security.UserDataService;
import com.flyerssoft.org_chart.service.EmployeeService;
import com.flyerssoft.org_chart.utility.AppUtils;
import com.flyerssoft.org_chart.utility.StoreRoleBean;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static ch.qos.logback.core.joran.spi.ConsoleTarget.findByName;

@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeDetailRepository employeeRepository;

    @Autowired
    private AppUtils utils;

    @Autowired
    private UserDataService userDataService;

    @Autowired
    private EmployeeDepartmentRepository employeeDepartmentRepository;

    @Autowired
    private JwtTokenUtils jwtTokenUtils;

    @Autowired
    private StoreRoleBean storeRoleBean;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Add employee details - employee need to fill all the requested fields to Signup
     *
     * @param employeePersonalDetailDto employeePersonalDetailDto
     * @return employee details will be added
     */

    @Override
    public AppResponse<EmployeePersonalDetailDto> addEmployeeDetail(EmployeePersonalDetailDto employeePersonalDetailDto) {
        //modelMapper.map(employeePersonalDetailDto, EmployeePersonalDetails.class);
        log.info("Requested details from input : {} ", employeePersonalDetailDto);
        this.checkEmployeeExistsByCredentials(employeePersonalDetailDto.getOfficialEmail(), employeePersonalDetailDto.getContactNumber());
        EmployeePersonalDetails employeeDetailRequest = utils.dtoToEntity(employeePersonalDetailDto);
        employeeDetailRequest.setPassword(passwordEncoder.encode(employeePersonalDetailDto.getPassword()));
        employeeDetailRequest.setRole(employeePersonalDetailDto.getRole());
        log.info("Requested details for persisting after mapping : {} ", employeeDetailRequest);
        //address validation
        this.checkAddressAndPersist(employeeDetailRequest);
        //fetch department Id
        EmployeePersonalDetails employeeDetailResponse = null;
        if (ObjectUtils.isNotEmpty(employeeDetailRequest.getDepartment())) {
            EmployeeDepartment department = this.employeeDepartmentRepository.findByDepartmentName(employeeDetailRequest.getDepartment().getDepartmentName());
            log.info("department from DB :{}", department);
            if (ObjectUtils.isNotEmpty(employeeDetailRequest.getPrimaryReportingManager())) {
                String primaryReportingManagerName = this.getReportingManagerName(employeeDetailRequest.getPrimaryReportingManager());
                employeeDetailRequest.setPrimaryReportingManagerName(primaryReportingManagerName);
            }
            if (ObjectUtils.isNotEmpty(employeeDetailRequest.getReportingManager())) {
                String reportingManagerName = this.getReportingManagerName(employeeDetailRequest.getReportingManager());
                employeeDetailRequest.setReportingManagerName(reportingManagerName);
            }
            employeeDetailResponse = employeeRepository.save(employeeDetailRequest);
            log.info("Employee details saved to the db");
        } else {
            if (ObjectUtils.isNotEmpty(employeeDetailRequest.getPrimaryReportingManager())) {
                String primaryReportingManagerName = this.getReportingManagerName(employeeDetailRequest.getPrimaryReportingManager());
                employeeDetailRequest.setPrimaryReportingManagerName(primaryReportingManagerName);
            }
            if (ObjectUtils.isNotEmpty(employeeDetailRequest.getReportingManager())) {
                String reportingManagerName = this.getReportingManagerName(employeeDetailRequest.getReportingManager());
                employeeDetailRequest.setReportingManagerName(reportingManagerName);
            }
            employeeDetailResponse = employeeRepository.save(employeeDetailRequest);
            log.info("Employee details saved to the db");
        }
        return new AppResponse<>(201, true, utils.mapEntityToDtos(employeeDetailResponse));
    }


    private String getReportingManagerName(Long id) {
        Optional<EmployeePersonalDetails> managerById = employeeRepository.findById(id);
        if (managerById.isPresent()) {
            return managerById.get().getFirstName();
        } else {
            return null;
        }
    }

    private void checkAddressAndPersist(EmployeePersonalDetails employeePersonalDetails) {
        if (utils.addressTypesValidation(employeePersonalDetails, AddressType.PERMANENT)) {
            throw new AddressAlreadyExistException("Permanent address can't be more than one");
        }
        if (utils.addressTypesValidation(employeePersonalDetails, AddressType.CURRENT)) {
            throw new AddressAlreadyExistException("Current address can't be more than one");
        }
    }


    /**
     * Fetch employee details but before fetching it will check employee credentials are correct, after that
     * employee details will be shown
     *
     * @param id id
     * @return requested employee details will be shown
     * @throws Exception Employee not found
     */
    @Override
    public AppResponse<EmployeePersonalDetailDto> getEmployeeDetailsById(Long id) throws Exception {
        Optional<EmployeePersonalDetails> existingEmployeeDetailsResponse = employeeRepository.findById(id);
        log.info("Fetched  employee data from the db :{} ", existingEmployeeDetailsResponse);
        if (existingEmployeeDetailsResponse.isPresent()) {
            return new AppResponse<>(200, true, utils.mapEntityToDtos(existingEmployeeDetailsResponse.get()));
        } else {
            log.error("requesting employee not found");
            throw new NotFoundException("Requesting employee not found");
        }
    }

    /**
     * To update the employee details, employee details will be requested to check already exits.
     * if it existed it will allow to update the details
     * but update can be done by admin
     *
     * @param id                        id
     * @param employeePersonalDetailDto employeePersonalDetailDto
     * @return updated employee details
     */
    @Override
    public AppResponse<EmployeePersonalDetailDto> updateEmployee(Long id, EmployeePersonalDetailDto employeePersonalDetailDto) {
        Optional<EmployeePersonalDetails> existsEmployeePersonalDetails = employeeRepository.findById(id);
        log.info("Existing employee details from db : {} ", existsEmployeePersonalDetails);
        if (existsEmployeePersonalDetails.isPresent()) {
            EmployeePersonalDetails updatedPersonalDetails = utils.updateEmployeeDetails(existsEmployeePersonalDetails.get(), employeePersonalDetailDto);
            log.info("Requesting data to update employee : {} ", updatedPersonalDetails);
            EmployeePersonalDetails savedPersonalDetails = employeeRepository.save(updatedPersonalDetails);
            log.info("Updated data to saved to the db ");
            return new AppResponse<>(202, true, utils.mapEntityToDtos(savedPersonalDetails));
        } else {
            log.error("Requesting User not found");
            throw new NotFoundException("Requesting User not found - " + employeePersonalDetailDto.getEmail());
        }
    }

    /**
     * To Delete the employee details, employee details will be requested to check already exits.
     * if it existed it will allow to Delete the details
     * Employee details can be deleted by Admin
     *
     * @param id id
     * @return employee details will be deleted
     * @throws Exception employee not found
     */
    @Override
    public AppResponse<String> deleteEmployee(Long id) throws Exception {
        try {
            employeeRepository.deleteById(id);
            return new AppResponse<>(204, true, "Employee Deleted Successfully");
        } catch (Exception ex) {
            log.info("Employee already deleted -" + "" + ex.toString());
            throw new NotFoundException("Employee already removed found ");
        }
    }

    /**
     * To login email and password will be need, and it will check employee already exits
     * If employee exists it will allow to log in
     *
     * @param officialEmail officialEmail
     * @param password      password
     * @return employee details
     * @throws Exception Bad credential exception
     */
    @Override
    public AppResponse<LoginResponse> userLogin(String officialEmail, String password) throws Exception {
        EmployeePersonalDetails existUser = employeeRepository.findByOfficialEmail(officialEmail);
        System.out.println("user - " + existUser);
        if (ObjectUtils.isNotEmpty(existUser)) {
            if (passwordEncoder.matches(password, existUser.getPassword())) {
                userDataService.authenticate(officialEmail, password);
                return new AppResponse<>(200, true, loginToken(existUser));
            } else {
                log.error("Password Incorrect");
                throw new BadCredentialException("Bad Credentials");
            }
        } else {
            log.error("User not exist with this username : {}", officialEmail);
            throw new BadCredentialException("Bad Credentials");
        }
    }

    public LoginResponse loginToken(EmployeePersonalDetails user) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        Map<String, Object> claims = new HashMap<>();
        claims.put("name", user.getOfficialEmail());
        if (ObjectUtils.isNotEmpty(user.getRole())) {
            authorities.add(new SimpleGrantedAuthority(String.valueOf(user.getRole())));
            claims.put("role", user.getRole());
            claims.put("userId", user.getId());
        } else {
            claims.put("role", "EMPLOYEE");
            claims.put("userId", user.getId());
        }
        final String loginToken = jwtTokenUtils.generateToken(new org.springframework.security.core.userdetails.User(user.getOfficialEmail(), user.getPassword(), authorities), claims);
        return new LoginResponse(user.getId(), user.getFirstName(), user.getLastName(), user.getRole(), user.getDesignation(), loginToken);
    }

    private void checkEmployeeExistsByCredentials(String emailId, String contactNum) {
        EmployeePersonalDetails findEmployeeByEmail = employeeRepository.findByOfficialEmail(emailId);
        log.info("Employee by email from DB : {}", findEmployeeByEmail);
        if (ObjectUtils.isEmpty(findEmployeeByEmail)) {
            EmployeePersonalDetails findEmployeeByContactNum = employeeRepository.findByContactNumber(contactNum);
            log.info("Employee by mobile from DB : {}", findEmployeeByContactNum);
            if (ObjectUtils.isNotEmpty(findEmployeeByContactNum)) {
                //throw message Employee already exists with this contact number
                log.error("Employee already exist with this contact num : {}", contactNum);
                throw new ResourceAlreadyExistsException("Employee already exists with this contact number");
            }
        } else {
            //throw message Employee already exists with this email
            log.error("Employee already exist with this email : {}", emailId);
            throw new ResourceAlreadyExistsException("Employee already exists with this emailId");
        }
    }

    @Override
    public AppResponse<List<CustomEmployeeResponseDto>> allEmployeeDtoResponse() {
        List<CustomEmployeeResponseDto> employeePersonalDetailsList = employeeRepository.findAll().stream().map(employeePersonalDetails -> utils.mapEntityToCustomDtos(employeePersonalDetails)).collect(Collectors.toList());
        log.info("Users size from DB : {}", employeePersonalDetailsList.size());
        return new AppResponse<>(200, true, employeePersonalDetailsList);
    }

    @Override
    public AppResponse<OrganisationDepartmentResponse> getCeoAndAllDepartments() {
        EmployeePersonalDetails ceoDetails = employeeRepository.findByRole(Role.SUPER_ADMIN);
        if (ObjectUtils.isNotEmpty(ceoDetails)) {
            List<EmployeeDepartment> departments = employeeDepartmentRepository.findAll();
            List<EmployeeDepartmentDto> departmentDtos = utils.deptEntityListToDto(departments);
            return new AppResponse<>(200, true, new OrganisationDepartmentResponse(utils.mapEntityToCustomDtos(ceoDetails), departmentDtos));
        } else {
            log.error("Super admin doesn't exist");
            throw new NotFoundException("Super admin doesn't exist");
        }
    }

    @Override
    public AppResponse<List<CustomEmployeeResponseDto>> getManagersOfDepartment(Long departmentId) {
        List<EmployeePersonalDetails> listOfManagerDetails = employeeRepository.findByDepartment(departmentId, Role.ADMIN.toString());
        return new AppResponse<>(200, true, utils.mapEntityListToCustomDtos(listOfManagerDetails));
    }
    //    @Query(value = "SELECT * FROM employee_personal_details
    //    WHERE department_id = :departmentId AND role = :role", nativeQuery = true)

    @Override
    public AppResponse<?> getChildEmployeesOrReportingManagers(Long reporteeId) {
        String role = storeRoleBean.role;
        if (Objects.equals(role, "SUPER_ADMIN")) {
            List<EmployeePersonalDetails> childEmployees = employeeRepository.findByPrimaryReportingManager(reporteeId);
            return new AppResponse<>(200, true, utils.employeePersonalEntityListToDto(childEmployees));
        } else {
            Optional<EmployeePersonalDetails> optionalEmployeePersonalDetails = employeeRepository.findById(reporteeId);
            if (optionalEmployeePersonalDetails.isPresent()) {
                // todo: need to get the details in single query
                EmployeePersonalDetailDto userDetails = utils.mapEntityToDtos(optionalEmployeePersonalDetails.get());
                Optional<EmployeePersonalDetails> optionalPrimaryManagerDetails = employeeRepository.findById(userDetails.getPrimaryReportingManager());
                if (optionalPrimaryManagerDetails.isEmpty()) {
                    throw new NotFoundException("Primary Reporting Manager not found");
                }
                EmployeePersonalDetailDto primaryReportingManager = utils.mapEntityToDtos(optionalPrimaryManagerDetails.get());
                if (userDetails.getReportingManager() == null) {
                    return new AppResponse<>(200, true, new ReporteeManagersResponse(primaryReportingManager, null, userDetails));
                }
                Optional<EmployeePersonalDetails> optionalEscalationManagerDetails = employeeRepository.findById(userDetails.getReportingManager());
                if (optionalEscalationManagerDetails.isEmpty()) {
                    throw new NotFoundException("Escalation Reporting Manager not found");
                }
                EmployeePersonalDetailDto reportingManager = utils.mapEntityToDtos(optionalEscalationManagerDetails.get());
                return new AppResponse<>(200, true, new ReporteeManagersResponse(primaryReportingManager, reportingManager, userDetails));

            } else {
                log.error("Employee not found");
                throw new NotFoundException("Employee not found");
            }
        }
    }

    @Override
    public AppResponse<List<EmployeeDepartmentCustomDto>> getAllDepartmentDetails() {
        List<EmployeeDepartment> departments = employeeDepartmentRepository.findAll();
        log.info("departments list and size :{} , :{}", departments, departments.size());
        List<Long> managerIds = departments.stream().map(dept -> dept.getManager_Id()).collect(Collectors.toList());
        List<EmployeePersonalDetails> managerInfoList = employeeRepository.findAllById(managerIds);
        log.info("manager employee list and size :{} , :{}", managerInfoList, managerInfoList.size());
        List<EmployeeDepartmentCustomDto> departmentCustomDtoList = new ArrayList<>();
        ManagerDtoResponse managerDto = null;
        EmployeeDepartmentCustomDto departmentCustomDto = null;
        for (int i = 0; i < departments.size(); i++) {
            departmentCustomDto = new EmployeeDepartmentCustomDto();
            departmentCustomDto.setDepartmentId(departments.get(i).getId());
            departmentCustomDto.setDepartmentName(departments.get(i).getDepartmentName());
            if (departments.get(i).getManager_Id() == managerInfoList.get(i).getId()) {
                managerDto = new ManagerDtoResponse();
                managerDto.setId(managerInfoList.get(i).getId());
                managerDto.setFirstName(managerInfoList.get(i).getFirstName());
                managerDto.setOfficialEmail(managerInfoList.get(i).getOfficialEmail());
                managerDto.setRole(managerInfoList.get(i).getRole());
            }
            departmentCustomDto.setManager(managerDto);
            departmentCustomDtoList.add(departmentCustomDto);
        }
        log.info("departments and manager list and size :{}", departmentCustomDtoList.size());
        return new AppResponse<>(200, true, departmentCustomDtoList);

    }


}
