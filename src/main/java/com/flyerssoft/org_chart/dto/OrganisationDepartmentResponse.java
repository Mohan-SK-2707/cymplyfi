package com.flyerssoft.org_chart.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrganisationDepartmentResponse {

    private EmployeePersonalDetailDto ceo;
    private List<EmployeeDepartmentDto> departments;
}
