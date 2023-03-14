package com.flyerssoft.org_chart.service.serviceImpl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.flyerssoft.org_chart.dto.EmployeePersonalDetailDto;
import com.flyerssoft.org_chart.enums.Role;
import com.flyerssoft.org_chart.model.EmployeeDepartment;
import com.flyerssoft.org_chart.model.EmployeePersonalDetails;
import com.flyerssoft.org_chart.repo.EmployeeDepartmentRepository;
import com.flyerssoft.org_chart.repo.EmployeeDetailRepository;
import com.flyerssoft.org_chart.response.AppResponse;
import com.flyerssoft.org_chart.response.LoginResponse;
import com.flyerssoft.org_chart.security.JwtTokenUtils;
import com.flyerssoft.org_chart.security.UserDataService;
import com.flyerssoft.org_chart.service.EmployeeService;
import com.flyerssoft.org_chart.utility.AppUtils;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.io.IOException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@SpringJUnitConfig
@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {

    @Mock
    EmployeeDetailRepository employeeDetailRepository;

    @MockBean
    private EmployeeDepartmentRepository employeeDepartmentRepository;

    @InjectMocks
    EmployeeServiceImpl employeeService;

    @MockBean
    private AppUtils utils;

    @MockBean
    private JwtTokenUtils jwtTokenUtils;

    @MockBean
    private UserDataService userDataService;

    @MockBean
    private PasswordEncoder passwordEncoder;

    private EmployeePersonalDetailDto personalDetailDto;

    private EmployeePersonalDetails personalDetailsEntity;

    private EmployeePersonalDetailDto updateResponseDto;

    private EmployeePersonalDetails updateResponseEntity;

    @BeforeEach
    void init() throws IOException {
        personalDetailDto = new ObjectMapper().readValue(this.getClass().getResourceAsStream("/mockData/employee_req_dto.json"),
                new TypeReference<EmployeePersonalDetailDto>() {
                });
        personalDetailsEntity = new ObjectMapper().readValue(this.getClass().getResourceAsStream("/mockData/employee_req_dto.json"),
                new TypeReference<EmployeePersonalDetails>() {
                });
        updateResponseDto = new ObjectMapper().readValue(this.getClass().getResourceAsStream("/mockData/employeeUpdate.json"),
                new TypeReference<EmployeePersonalDetailDto>() {
                });
        updateResponseEntity = new ObjectMapper().readValue(this.getClass().getResourceAsStream("/mockData/employeeUpdate.json"),
                new TypeReference<EmployeePersonalDetails>() {
                });
    }



    @Test
    void addEmployeeDetail() {

        EmployeePersonalDetailDto employeePersonalDetailDto = new EmployeePersonalDetailDto();
        employeePersonalDetailDto.setOfficialEmail("test@test.com");
        employeePersonalDetailDto.setContactNumber("1234567890");
        employeePersonalDetailDto.setPassword("password");
        employeePersonalDetailDto.setRole(Role.ADMIN);

        EmployeePersonalDetails employeeDetailRequest = new EmployeePersonalDetails();
        employeeDetailRequest.setOfficialEmail("test@test.com");
        employeeDetailRequest.setContactNumber("1234567890");
        employeeDetailRequest.setPassword("encodedPassword");
        employeeDetailRequest.setRole(Role.ADMIN);

        when(utils.dtoToEntity(employeePersonalDetailDto)).thenReturn(employeeDetailRequest);
        when(passwordEncoder.encode(employeePersonalDetailDto.getPassword())).thenReturn("encodedPassword");
        when(employeeDepartmentRepository.findByDepartmentName(any())).thenReturn(new EmployeeDepartment());

        AppResponse<EmployeePersonalDetailDto> appResponse = employeeService.addEmployeeDetail(employeePersonalDetailDto);

        verify(utils, times(1)).dtoToEntity(employeePersonalDetailDto);
        verify(passwordEncoder, times(1)).encode(employeePersonalDetailDto.getPassword());
        verify(employeeDetailRepository, times(1)).save(employeeDetailRequest);

        assertEquals(201, appResponse.getStatus());
        assertTrue(appResponse.getSuccess());
    }

    @Test
    void getEmployeeDetailsById() throws Exception {
        AppResponse<EmployeePersonalDetailDto> response = new AppResponse<>();
        response.setStatus(200);
        response.setSuccess(true);
        response.setData(personalDetailDto);
        when(employeeDetailRepository.findById(anyLong())).thenReturn(Optional.ofNullable(personalDetailsEntity));
        when(utils.mapEntityToDtos(personalDetailsEntity)).thenReturn(response.getData());
        AppResponse<EmployeePersonalDetailDto> employeeDetailResponse = employeeService.getEmployeeDetailsById(1L);
        Assertions.assertEquals(response, employeeDetailResponse);
    }

    @Test
    void updateEmployee() {
        when(employeeDetailRepository.findById(1L)).thenReturn(Optional.of(personalDetailsEntity));
        when(utils.updateEmployeeDetails(eq(personalDetailsEntity), any(EmployeePersonalDetailDto.class)))
                .thenReturn(personalDetailsEntity);
        when(employeeDetailRepository.save(personalDetailsEntity)).thenReturn(updateResponseEntity);
        AppResponse<EmployeePersonalDetailDto> response = employeeService.updateEmployee(1L, updateResponseDto);
        response.setData(updateResponseDto);
        assertEquals(202, response.getStatus());
        assertTrue(response.getSuccess());
        assertEquals(updateResponseEntity.getFirstName(), response.getData().getFirstName());
        assertEquals(updateResponseEntity.getEmail(), response.getData().getEmail());
    }

    @Test
    void deleteEmployee() throws Exception {
        Long employeeId = 1L;
        doNothing().when(employeeDetailRepository).deleteById(employeeId);
        AppResponse<String> response = employeeService.deleteEmployee(employeeId);
        assertEquals(204, response.getStatus());
        assertEquals(true, response.getSuccess());
        assertEquals("Employee Deleted Successfully", response.getData());
    }

    @Test
    public void Login() throws Exception {
        EmployeePersonalDetails user = new EmployeePersonalDetails();
        user.setOfficialEmail("test@test.com");
        user.setPassword("password");
        user.setRole(Role.ADMIN);
        user.setDesignation("Software Engineer");
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setId(1L);

        when(employeeDetailRepository.findByOfficialEmail("test@test.com")).thenReturn(user);
        when(passwordEncoder.matches("password", "password")).thenReturn(true);
        when(jwtTokenUtils.generateToken(any(), any())).thenReturn("test token");

        AppResponse<LoginResponse> response = employeeService.userLogin("test@test.com", "password");

        assertNotNull(response);
        assertEquals(200, response.getStatus());
        assertTrue(response.getSuccess());
        assertNotNull(response.getData());

        LoginResponse loginResponse = response.getData();
        assertEquals(1L, loginResponse.getUserId());
        assertEquals("John", loginResponse.getFirstName());
        assertEquals("Doe", loginResponse.getLastName());
        assertEquals("Software Engineer", loginResponse.getDesignation());
        assertEquals("test token", loginResponse.getToken());
    }


    @Test
    void allEmployeeDtoResponse() {

    }

    @Test
    void getCeoAndAllDepartments() {
    }

    @Test
    void getManagersOfDepartment() {

    }

    @Test
    void getChildEmployeesOrReportingManagers() {
    }

    @Test
    void getAllDepartmentDetails() {

    }
}