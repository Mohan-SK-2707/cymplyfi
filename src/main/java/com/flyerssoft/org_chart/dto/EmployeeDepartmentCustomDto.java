package com.flyerssoft.org_chart.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDepartmentCustomDto {
    private Long departmentId;
    private String departmentName;
    private ManagerDtoResponse manager;
}
