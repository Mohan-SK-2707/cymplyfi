package com.flyerssoft.org_chart.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import java.util.Date;

/**
 * The employee job history details dto
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeJobHistoryDto {

    private Long id;

    @NotBlank(message = "Company is mandatory")
    @Size(min = 3, max = 30, message = "Company size must be between 3 to 30")
    @Pattern(regexp = "[a-zA-Z]+", message = "Company must not contain special characters & numerics")
    private String Company;

    @NotBlank(message = "Role is mandatory")
    @Size(min = 3, max = 30, message = "Role size must be between 3 to 30")
    @Pattern(regexp = "[a-zA-Z]+", message = "Role must not contain special characters & numerics")
    private String role;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-yyyy")
    private Date jobStartDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-yyyy")
    private Date jobEndDate;
}
