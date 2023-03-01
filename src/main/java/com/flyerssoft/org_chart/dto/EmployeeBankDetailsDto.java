package com.flyerssoft.org_chart.dto;

import com.flyerssoft.org_chart.enums.EmployeeBankAccountType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

/**
 * The employee bank details dto
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeBankDetailsDto {

    private Long id;

    @NotBlank(message = "Bank name is mandatory")
    @Size(min = 3, max = 30, message = "Bank name size must be between 3 to 30")
    @Pattern(regexp = "^[A-Za-z\\s]*$", message = "Bank Name must not contain special characters & numerics")
    private String bankName;

    @NotBlank(message = "Account number is mandatory")
    @Size(min = 3, max = 17, message = "Account number size must be between 3 to 17")
    @Pattern(regexp = "[0-9]+", message = "Account number must not contain special characters & alphabets")
    private String bankAccountNumber;

    @NotBlank(message = "IFSC is mandatory")
    @Size(min = 3, max = 10, message = "IFSC size must be between 3 to 10")
    @Pattern(regexp = "[a-zA-Z0-9]+", message = "IFSC must not contain special characters")
    private String bankIfscCode;

    @NotBlank(message = "Branch is mandatory")
    @Size(min = 3, max = 10, message = "Branch size must be between 3 to 10")
    private String bankBranchLocation;

    @NotBlank(message = "Aadhaar is mandatory")
    @Size(min = 12,max = 12, message = "AadhaarNumber should be 12 Digits")
    @Pattern(regexp = "[0-9]+", message = "Aadhaar must not contain special characters & alphabets")
    private String aadharNumber;

    @NotBlank(message = "PAN is mandatory")
    @Pattern(regexp = "[a-zA-Z0-9]+", message = "PAN must not contain special characters")
    private String panNumber;

    @Enumerated(EnumType.STRING)
    private EmployeeBankAccountType employeeBankAccountType;
}
