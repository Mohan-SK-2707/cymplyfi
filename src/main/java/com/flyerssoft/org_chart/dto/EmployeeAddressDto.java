package com.flyerssoft.org_chart.dto;

import com.flyerssoft.org_chart.enums.AddressType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

/**
 * The employee address details dto
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeAddressDto {

    private Long id;

    @Enumerated(EnumType.STRING)
    private AddressType addressType;

    @NotBlank(message = "Field is mandatory")
    @Size(min = 10, max = 30, message = "Address line 1 size must be between 10 to 30")
    private String Line1;

    private String Line2;

    @NotBlank(message = "City is mandatory")
    @Size(min = 3, max = 30, message = "City size must be between 3 to 30")
    private String city;

    @NotBlank(message = "State is mandatory")
    @Size(min = 3, max = 30, message = "State size must be between 10 to 30")
    private String state;

    @NotBlank(message = "PinCode is mandatory")
    @Size(min = 6, max = 6, message = "PinCode must in 6 digit")
    private String pinCode;

}
