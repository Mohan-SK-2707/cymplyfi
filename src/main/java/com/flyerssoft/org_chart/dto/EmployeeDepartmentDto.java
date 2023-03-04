package com.flyerssoft.org_chart.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    @Pattern(regexp = "^[A-Za-z\\s]*$", message = "Department name must not contain special characters & numerics")
    private String departmentName;

    @NotNull(message = "Manager Id Is Mandatory")
    private int managerId;
}
