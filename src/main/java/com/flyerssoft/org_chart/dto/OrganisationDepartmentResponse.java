package com.flyerssoft.org_chart.dto;

import com.flyerssoft.org_chart.response.CustomEmployeeResponseDto;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrganisationDepartmentResponse {

    private CustomEmployeeResponseDto ceo;
    private List<EmployeeDepartmentDto> departments;
}
