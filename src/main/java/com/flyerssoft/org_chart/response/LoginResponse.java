package com.flyerssoft.org_chart.response;

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

    private Long userId;

    private String firstName;

    private String lastName;

    @Enumerated(EnumType.STRING)
    private Role role;

    private String designation;

    private String token;
}
