package com.flyerssoft.org_chart.dto;

import com.flyerssoft.org_chart.enums.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {

    private String firstName;

    private String lastName;

    @Enumerated(EnumType.STRING)
    private Role role;

    private String token;
}
