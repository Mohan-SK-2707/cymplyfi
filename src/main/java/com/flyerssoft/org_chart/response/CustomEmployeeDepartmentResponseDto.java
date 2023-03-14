package com.flyerssoft.org_chart.response;

import com.flyerssoft.org_chart.dto.EmployeeDepartmentDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomEmployeeDepartmentResponseDto {

    private CustomEmployeeResponseDto ceo;

    private List<EmployeeDepartmentDto> departments;
}
