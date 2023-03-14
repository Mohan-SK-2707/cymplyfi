package com.flyerssoft.org_chart.response;

import com.flyerssoft.org_chart.dto.EmployeeDepartmentDto;
import com.flyerssoft.org_chart.dto.ManagerDtoResponse;
import com.flyerssoft.org_chart.model.EmployeeDepartment;
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
