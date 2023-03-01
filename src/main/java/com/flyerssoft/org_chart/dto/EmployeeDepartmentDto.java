package com.flyerssoft.org_chart.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDepartmentDto {

    private Long id;

    @NotBlank(message = "Department name cannot be empty")
    @Pattern(regexp = "[a-zA-Z]+", message = "Department name must not contain special characters & numerics")
    private String departmentName;

    @NotBlank(message = "Head of the department is mandatory")
    @Pattern(regexp = "[a-zA-Z]+", message = "Head of the department must not contain special characters & numerics")
    private String headOfDepartment;
}
