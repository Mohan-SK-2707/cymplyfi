package com.flyerssoft.org_chart.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.flyerssoft.org_chart.enums.EmployeeEducationalType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import java.util.Date;

/**
 * The employee educational details dto
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeEducationalDetailsDto {

    private Long id;

    @NotBlank(message = "Qualification is mandatory")
    @Size(min = 3, max = 30, message = "Qualification size must be between 3 to 30")
    @Pattern(regexp = "^[A-Za-z\\s]*$", message = "Qualification must not contain special characters & numerics")
    private String qualification;

    @NotBlank(message = "Stream is mandatory")
    @Size(min = 3, max = 30, message = "Stream size must be between 3 to 30")
    @Pattern(regexp = "^[A-Za-z\\s]*$", message = "Stream must not contain special characters & numerics")
    private String stream;

    @NotNull(message = "Percentage is mandatory")
    private String percentage;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-yyyy")
    private Date courseStartDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-yyyy")
    private Date courseEndDate;

    @Enumerated(EnumType.STRING)
    private EmployeeEducationalType employeeEducationalType;
}
