package com.flyerssoft.org_chart.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomEmployeeResponseDto {

    private Long id;

    private String firstName;

    private String lastName;

    private String officialEmail;

    private String designation;

    private String role;

    private String employeeId;

    private String contactNumber;
}
