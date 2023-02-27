package com.flyerssoft.org_chart.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class LoginRequestDto {

    @NotBlank
    private String email;
    @NotBlank
    private String password;
}
