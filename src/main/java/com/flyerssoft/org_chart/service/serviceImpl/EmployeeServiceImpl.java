package com.flyerssoft.org_chart.service.serviceImpl;

import com.flyerssoft.org_chart.dto.*;
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
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeDetailRepository employeeRepository;

    @Autowired
    private AppUtils utils;

    @Autowired
    UserDataService userDataService;

    @Autowired
    JwtTokenUtils jwtTokenUtils;

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
        this.checkEmployeeExistsByCredentials(employeePersonalDetailDto.getEmail(), employeePersonalDetailDto.getContactNumber());
        EmployeePersonalDetails employeeDetailRequest = utils.dtoToEntity(employeePersonalDetailDto);
        employeeDetailRequest.setPassword(passwordEncoder.encode(employeePersonalDetailDto.getPassword()));
        employeeDetailRequest.setRole(employeePersonalDetailDto.getRole());
        log.info("Requested details for persisting after mapping : {} ", employeeDetailRequest);
        EmployeePersonalDetails employeeDetailResponse = employeeRepository.save(employeeDetailRequest);
        log.info("Employee details saved to the db");
        return new AppResponse<>(201, true, utils.mapEntityToDtos(employeeDetailResponse));
    }

    /**
     * Fetch employee details but before fetching it will check employee credentials are correct, after that
     * employee details will be shown
     *
     * @param id id
     * @return requested employee details will be shown
     * @throws Exception
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
     * @param id id
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
     * @param email email
     * @param password password
     * @return employee details
     * @throws Exception Bad credential exception
     */
    @Override
    public AppResponse<LoginResponse> userLogin(String email, String password) throws Exception {
        EmployeePersonalDetails existUser = employeeRepository.findByEmail(email);
        System.out.println("user - " + existUser);
        if (ObjectUtils.isNotEmpty(existUser)) {
            if (passwordEncoder.matches(password, existUser.getPassword())) {
                userDataService.authenticate(email, password);
                return new AppResponse<>(200, true, loginToken(existUser));
            } else {
                log.error("Password Incorrect");
                throw new BadCredentialException("Bad Credentials");
            }
        } else {
            log.error("User not exist with this username : {}", email);
            throw new NotFoundException("User doesn't exist with this username - " + email);
        }
    }

    public LoginResponse loginToken(EmployeePersonalDetails user) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        Map<String, Object> claims = new HashMap<>();
        claims.put("name", user.getEmail());
        if (ObjectUtils.isNotEmpty(user.getRole())) {
            authorities.add(new SimpleGrantedAuthority(String.valueOf(user.getRole())));
            claims.put("role", user.getRole());
            claims.put("userId", user.getId());
        } else {
            claims.put("role", "EMPLOYEE");
            claims.put("userId", user.getId());
        }
        final String loginToken = jwtTokenUtils.generateToken(new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities), claims);
        return new LoginResponse(user.getId(), user.getFirstName(), user.getLastName(), user.getRole(), user.getDesignation(), loginToken);
    }

    private void checkEmployeeExistsByCredentials(String emailId, String contactNum) {
        EmployeePersonalDetails findEmployeeByEmail = employeeRepository.findByEmail(emailId);
        if (ObjectUtils.isEmpty(findEmployeeByEmail)) {
            EmployeePersonalDetails findEmployeeByContactNum = employeeRepository.findByContactNumber(contactNum);
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
}
