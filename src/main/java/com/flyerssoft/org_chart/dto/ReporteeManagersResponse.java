package com.flyerssoft.org_chart.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReporteeManagersResponse {
    private EmployeePersonalDetailDto primaryReporteeManager;
    private EmployeePersonalDetailDto reporteeManager;
    private EmployeePersonalDetailDto userDetails;

}
