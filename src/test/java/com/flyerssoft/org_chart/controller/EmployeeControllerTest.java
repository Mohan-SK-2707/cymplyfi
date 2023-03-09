package com.flyerssoft.org_chart.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.flyerssoft.org_chart.dto.EmployeePersonalDetailDto;
import com.flyerssoft.org_chart.dto.LoginRequestDto;
import com.flyerssoft.org_chart.response.AppResponse;
import com.flyerssoft.org_chart.response.LoginResponse;
import com.flyerssoft.org_chart.security.JwtAuthenticationFilter;
import com.flyerssoft.org_chart.security.JwtTokenUtils;
import com.flyerssoft.org_chart.security.UserDataService;
import com.flyerssoft.org_chart.security.WebConfig;
import com.flyerssoft.org_chart.service.EmployeeService;
import com.flyerssoft.org_chart.utility.StoreRoleBean;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.request.RequestPostProcessor;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

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
@MockBean({JwtTokenUtils.class, UserDataService.class, StoreRoleBean.class, WebConfig.class})
class EmployeeControllerTest {

    @MockBean
    EmployeeService employeeService;

    @Autowired
    MockMvc mockMvc;
    private EmployeePersonalDetailDto employeeRequestDto;
    private LoginResponse response;
    private LoginRequestDto requestDto;
    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    public void setup() throws IOException {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        employeeRequestDto = new ObjectMapper().readValue(this.getClass().getResourceAsStream("/mockData/employee_req_dto.json"),
                new TypeReference<EmployeePersonalDetailDto>() {
                });

        response = new ObjectMapper().readValue(this.getClass().getResourceAsStream("/mockData/login.json"),
                new TypeReference<LoginResponse>() {
                });
        requestDto = new ObjectMapper().readValue(this.getClass().getResourceAsStream("/mockData/loginRequestDto.json"), new TypeReference<LoginRequestDto>() {
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

    @Test
    void userLogin() throws Exception {
        AppResponse<LoginResponse> appResponse = new AppResponse<>();
        appResponse.setData(response);
        requestDto.setEmail("intern2@gmail.com");
        requestDto.setPassword("Hell0&@#881j");
        Mockito.when(employeeService.userLogin(requestDto.getEmail(), requestDto.getPassword())).thenReturn(appResponse);
        String response1 = new ObjectMapper().writeValueAsString(requestDto);
        this.mockMvc.perform(MockMvcRequestBuilders.post("/employee/login").contentType(MediaType.APPLICATION_JSON).content(response1).characterEncoding("UTF-8").accept(MediaType.APPLICATION_JSON))
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