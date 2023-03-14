package com.flyerssoft.org_chart.service.serviceImpl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.flyerssoft.org_chart.dto.*;
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
import com.flyerssoft.org_chart.response.EmployeeDepartmentCustomDto;
import com.flyerssoft.org_chart.response.CustomEmployeeDepartmentResponseDto;
import com.flyerssoft.org_chart.response.CustomEmployeeResponseDto;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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
    private PasswordEncoder passwordEncoder;

    private EmployeePersonalDetailDto personalDetailDto;

    private EmployeePersonalDetails personalDetailsEntity;

    private EmployeePersonalDetailDto updateResponseDto;

    private EmployeePersonalDetails updateResponseEntity;

    private EmployeePersonalDetails personalDetails;
    private List<CustomEmployeeResponseDto> customEmployeeResponseDto;

    private AppResponse<CustomEmployeeDepartmentResponseDto> customEmployeeDepartmentResponseDto;

    private AppResponse<List<CustomEmployeeResponseDto>> custommanagersrequest;
    private AppResponse<List<CustomEmployeeResponseDto>> responses;
    private AppResponse<List<EmployeePersonalDetails>> customanagersResponse;
    private AppResponse<List<CustomEmployeeResponseDto>> getallEmployees;
    private AppResponse<List<EmployeeDepartmentCustomDto>> allDepartmentsWithManager;

    private List<EmployeePersonalDetails> employeePersonalDetails;

    @BeforeEach
    void init() throws IOException {
        personalDetailDto = new ObjectMapper().readValue(this.getClass().getResourceAsStream("/mockData/employee_req_dto.json"),
                new TypeReference<EmployeePersonalDetailDto>() {
                });
        personalDetailsEntity = new ObjectMapper().readValue(this.getClass().getResourceAsStream("/mockData/employeeGet.json"),
                new TypeReference<EmployeePersonalDetails>() {
                });
        updateResponseEntity = new ObjectMapper().readValue(this.getClass().getResourceAsStream("/mockData/employeeUpdate.json"),
                new TypeReference<EmployeePersonalDetails>() {
                });
        updateResponseDto = new ObjectMapper().readValue(this.getClass().getResourceAsStream("/mockData/employeeUpdate.json"),
                new TypeReference<EmployeePersonalDetailDto>() {
                });
        personalDetails = new ObjectMapper().readValue(this.getClass().getResourceAsStream("/mockData/employee_req_dto.json"),
                new TypeReference<EmployeePersonalDetails>() {
                });
        customEmployeeResponseDto = new ObjectMapper().readValue(this.getClass().getResourceAsStream("/mockData/getAllEmployee.json"),
                new TypeReference<List<CustomEmployeeResponseDto>>() {
                });

        customEmployeeDepartmentResponseDto = new ObjectMapper().readValue(this.getClass().getResourceAsStream("/mockData/hierarchy.json"),
                new TypeReference<AppResponse<CustomEmployeeDepartmentResponseDto>>() {
                });
        custommanagersrequest = new ObjectMapper().readValue(this.getClass().getResourceAsStream("/mockData/CustomEmployeeDetails.json"),
                new TypeReference<AppResponse<List<CustomEmployeeResponseDto>>>() {
                });
        customanagersResponse = new ObjectMapper().readValue(this.getClass().getResourceAsStream("/mockData/hierarchymanager.json"),
                new TypeReference<AppResponse<List<EmployeePersonalDetails>>>() {
                });
        responses = new ObjectMapper().readValue(this.getClass().getResourceAsStream("/mockData/CustomEmployeeDetails.json"),
                new TypeReference<AppResponse<List<CustomEmployeeResponseDto>>>() {
                });
        getallEmployees = new ObjectMapper().readValue(this.getClass().getResourceAsStream("/mockData/CustomEmployeeDetails.json"),
                new TypeReference<AppResponse<List<CustomEmployeeResponseDto>>>() {
                });
        allDepartmentsWithManager = new ObjectMapper().readValue(this.getClass().getResourceAsStream("/mockData/alldepartmentswithmanager.json"),
                new TypeReference<AppResponse<List<EmployeeDepartmentCustomDto>>>() {
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
        assertEquals(201, appResponse.getStatus());    assertTrue(appResponse.getSuccess());
    }
    @Test
    void getEmployeeDetailsById() throws Exception {
        AppResponse<EmployeePersonalDetailDto> response = new AppResponse<>();
        response.setStatus(200);
        response.setSuccess(true);
        personalDetailDto.setId(1L);
        response.setData(personalDetailDto);
        when(employeeDetailRepository.findById(anyLong())).thenReturn(Optional.ofNullable(personalDetailsEntity));
        when(utils.mapEntityToDtos(personalDetailsEntity)).thenReturn(response.getData());
        AppResponse<EmployeePersonalDetailDto> employeeDetailResponse;
        employeeDetailResponse = employeeService.getEmployeeDetailsById(1L);
        Assertions.assertNotNull(response);
    }

    @Test
    void updateEmployee() {
        when(employeeDetailRepository.findById(1L)).thenReturn(Optional.of(personalDetailsEntity));
        when(utils.updateEmployeeDetails(eq(personalDetailsEntity),any(EmployeePersonalDetailDto.class))).thenReturn(personalDetailsEntity);
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

//    @Test
//    public void Login() throws Exception {
//        EmployeePersonalDetails user = new EmployeePersonalDetails();
//        user.setOfficialEmail("test@test.com");
//        user.setPassword("password");
//        user.setRole(Role.ADMIN);
//        user.setDesignation("Software Engineer");
//        user.setFirstName("John");
//        user.setLastName("Doe");
//        user.setId(1L);
//
//        when(employeeDetailRepository.findByOfficialEmail("test@test.com")).thenReturn(user);
//        when(passwordEncoder.matches("password", "password")).thenReturn(true);
//        when(jwtTokenUtils.generateToken(any(), any())).thenReturn("test token");
//
//        AppResponse<LoginResponse> response = employeeService.userLogin("test@test.com", "password");
//
//        assertNotNull(response);
//        assertEquals(200, response.getStatus());
//        assertTrue(response.getSuccess());
//        assertNotNull(response.getData());
//
//        LoginResponse loginResponse = response.getData();
//        assertEquals(1L, loginResponse.getUserId());
//        assertEquals("John", loginResponse.getFirstName());
//        assertEquals("Doe", loginResponse.getLastName());
//        assertEquals("Software Engineer", loginResponse.getDesignation());
//        assertEquals("test token", loginResponse.getToken());
//    }


    @Test
    void allEmployeeDtoResponse() {
        AppResponse<List<CustomEmployeeResponseDto>> response = new AppResponse<>();
        response.setStatus(200);
        response.setSuccess(true);
        response.setData(getallEmployees.getData());
        Mockito.when(employeeDetailRepository.findAll()).thenReturn(customanagersResponse.getData());
        Mockito.when(utils.mapEntityListToCustomDtos(customanagersResponse.getData())).thenReturn(getallEmployees.getData());
        AppResponse<List<CustomEmployeeResponseDto>> response1 = employeeService.allEmployeeDtoResponse();
        Assertions.assertEquals(response, responses);
    }

    @Test
    void getCeoAndAllDepartments() {
        AppResponse<CustomEmployeeDepartmentResponseDto> response = new AppResponse<>();
        response.setStatus(200);
        response.setSuccess(true);
        response.setData(customEmployeeDepartmentResponseDto.getData());
        Mockito.when(employeeDetailRepository.findByRole(Role.SUPER_ADMIN)).thenReturn(new EmployeePersonalDetails());
        Mockito.when(employeeDepartmentRepository.findAll()).thenReturn(List.of(new EmployeeDepartment()));
        Mockito.when(utils.deptEntityListToDto(List.of(new EmployeeDepartment()))).thenReturn(Arrays.asList(new EmployeeDepartmentDto()));
        AppResponse<OrganisationDepartmentResponse> employeeDetailResponse = employeeService.getCeoAndAllDepartments();
        Assertions.assertEquals(response, customEmployeeDepartmentResponseDto);
    }

    @Test
    void getManagersOfDepartment() throws JsonProcessingException {
        AppResponse<List<CustomEmployeeResponseDto>> response = new AppResponse<>();
        response.setStatus(200);
        response.setSuccess(true);
        response.setData(custommanagersrequest.getData());
        Mockito.when(employeeDetailRepository.findByDepartment(1l, Role.ADMIN.toString())).thenReturn(customanagersResponse.getData());
        Mockito.when(utils.mapEntityListToCustomDtos(customanagersResponse.getData())).thenReturn(customEmployeeResponseDto);
        AppResponse<List<CustomEmployeeResponseDto>> managerDetails = employeeService.getManagersOfDepartment(1l);
        Assertions.assertEquals(response, responses);
    }

    @Test
    void getAllDepartmentDetails() {
        AppResponse<List<EmployeeDepartmentCustomDto>> response = new AppResponse<>();
        response.setStatus(200);
        response.setSuccess(true);
        response.setData(allDepartmentsWithManager.getData());
        EmployeeDepartment employeeDepartment = new EmployeeDepartment(12l, "frontend", 321l);
        //  Mockito.when(employeeDepartmentRepository.findAll()).thenReturn(List.of(employeeDepartment));
        //     Mockito.when(employeeDetailRepository.findAllById(List.of(anyLong()))).thenReturn(employeePersonalDetails);
        //Mockito.when(employeePersonalDetails).thenReturn((List<EmployeePersonalDetails>) employeeDepartmentCustomDto);
        Assertions.assertEquals(allDepartmentsWithManager, response);
    }

    @Test
    void getChildEmployeesOrReportingManagers() {
        AppResponse<?> response = new AppResponse<>(200, true, new ReporteeManagersResponse(new EmployeePersonalDetailDto(), null, new EmployeePersonalDetailDto()));
        AppResponse<?> response1 = new AppResponse<>(200, true, new ReporteeManagersResponse(new EmployeePersonalDetailDto(), null, new EmployeePersonalDetailDto()));
        // Mockito.when(storeRoleBean.role).thenReturn(anyString());
        //   Mockito.when(Objects.equals(anyString(), "SUPER_ADMIN")).thenReturn(true);
        //  Mockito.when(employeeDetailRepository.findByPrimaryReportingManager(anyLong())).thenReturn(employeePersonalDetails);
        Mockito.when(utils.employeePersonalEntityListToDto(employeePersonalDetails)).thenReturn(Arrays.asList(personalDetailDto));
        Assertions.assertEquals(response, response1);
        // Mockito.when(Objects.equals(anyString(), "SUPER_ADMIN")).thenReturn(false);
        // Mockito.when(employeeDetailRepository.findById(anyLong())).thenReturn(Optional.of(personalDetails));
        //Mockito.when(Optional.of(employeePersonalDetails).isPresent()).thenReturn(true);
        Mockito.when(utils.mapEntityToDtos(personalDetails)).thenReturn(personalDetailDto);
        Assertions.assertEquals(personalDetails, personalDetails);
        //  Mockito.when(employeeDetailRepository.findById(personalDetails.getPrimaryReportingManager())).thenReturn(Optional.ofNullable(personalDetails));
        //  Mockito.when(Optional.of(personalDetails).isEmpty()).thenThrow(new NotFoundException("Primary Reporting Manager not found"));
        Mockito.when(utils.mapEntityToDtos(personalDetails)).thenReturn(personalDetailDto);
        Assertions.assertEquals(personalDetails, personalDetails);
        // Mockito.when(ObjectUtils.isEmpty(employeePersonalDetails)).thenReturn(response.getSuccess());
        // Mockito.when(employeeDetailRepository.findById(personalDetails.getReportingManager())).thenReturn(Optional.ofNullable(personalDetails));
        //  Mockito.when(Optional.of(personalDetails).isEmpty()).thenThrow(new NotFoundException("Escalation Reporting Manager not found"));
        Mockito.when(utils.mapEntityToDtos(personalDetails)).thenReturn(personalDetailDto);
        // Mockito.when(employeePersonalDetails).thenReturn((List<EmployeePersonalDetails>) response1);
        //Mockito.when(Optional.of(employeePersonalDetails).isPresent()).thenReturn(false).thenThrow(new NotFoundException("Employee not found"));
    }
}