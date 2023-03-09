package com.flyerssoft.org_chart.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.flyerssoft.org_chart.dto.EmployeePersonalDetailDto;
import com.flyerssoft.org_chart.response.AppResponse;
import com.flyerssoft.org_chart.response.LoginResponse;
import com.flyerssoft.org_chart.security.JwtAuthenticationFilter;
import com.flyerssoft.org_chart.security.JwtTokenUtils;
import com.flyerssoft.org_chart.security.UserDataService;
import com.flyerssoft.org_chart.service.EmployeeService;
import com.flyerssoft.org_chart.utility.StoreRoleBean;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringJUnitConfig
@WebMvcTest(EmployeeController.class)
@MockBean({JwtTokenUtils.class, UserDataService.class, StoreRoleBean.class})
class EmployeeControllerTest {

    @MockBean
    EmployeeService employeeService;

    @Autowired
    MockMvc mockMvc;

    private EmployeePersonalDetailDto employeeRequestDto;
    private LoginResponse response;

    @BeforeEach
    public void setup() throws IOException {
        employeeRequestDto = new ObjectMapper().readValue(this.getClass().getResourceAsStream("/mockData/employee_req_dto.json"),
        new TypeReference<EmployeePersonalDetailDto>(){
        });

        response = new ObjectMapper().readValue(this.getClass().getResourceAsStream("/mockData/login.json"),
                new TypeReference<LoginResponse>(){
                });
    }

    @Test
    void addEmployeeDetail() {

    }

//    @WithMockUser(value = "spring")
//    @Test
//    void test() throws Exception {
//        this.mockMvc.perform(MockMvcRequestBuilders.get("/employee"))
//                .andExpect(status().isOk());
//    }


    @WithMockUser(value = "spring")
    @Test
    void getEmployeeById() throws Exception {
        AppResponse<EmployeePersonalDetailDto> appResponse = new AppResponse<>();
        appResponse.setData(employeeRequestDto);
        Mockito.when(employeeService.getEmployeeDetailsById(1L)).thenReturn(appResponse);
        this.mockMvc.perform(MockMvcRequestBuilders.get("/employee/{id}", 1L))
                .andExpect(status().isOk());
    }

    @Test
    void updateEmployee() {

    }

    @Test
    void deleteEmployee() {

    }

    @WithMockUser(value = "spring")
    @Test
    void userLogin() throws Exception {
        AppResponse<LoginResponse> appResponse = new AppResponse<>();
        appResponse.setData(response);
        Mockito.when(employeeService.userLogin("admin@gmail.com","Hello@112&")).thenReturn(appResponse);
        this.mockMvc.perform(MockMvcRequestBuilders.post("/employee/login"))
                .andExpect(status().isOk());
    }

    @Test
    void allEmployeeDtoResponse() {
    }

    @Test
    void getDepartmentsForHierarchy() {
    }

    @Test
    void getListOfManagers() {
    }

    @Test
    void testGetDepartmentsForHierarchy() {
    }

    @Test
    void getAllDepartmentDetails() {
        Assertions.assertTrue(true);
    }
}