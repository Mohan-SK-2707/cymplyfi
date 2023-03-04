package com.flyerssoft.org_chart.dto;

import com.flyerssoft.org_chart.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ManagerDtoResponse {

    private Long id;

    private String firstName;

    private String officialEmail;

    private Role role;
}
