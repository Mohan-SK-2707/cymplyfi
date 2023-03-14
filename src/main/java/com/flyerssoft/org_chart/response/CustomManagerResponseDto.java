package com.flyerssoft.org_chart.response;

import com.flyerssoft.org_chart.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomManagerResponseDto {
    private Long id;

    private String firstName;

    private String lastName;

    private Role role;
}
