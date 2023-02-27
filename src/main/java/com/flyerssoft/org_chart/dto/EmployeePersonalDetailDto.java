package com.flyerssoft.org_chart.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.flyerssoft.org_chart.enums.EmployeeGender;
import com.flyerssoft.org_chart.enums.EmployeeMartialStatus;
import com.flyerssoft.org_chart.enums.Role;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * The employee personal details dto
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmployeePersonalDetailDto {

    private Long id;

    @NotBlank(message = "First name is mandatory")
    @Size(min = 3, max = 30, message = "First name size must be between 3 to 30")
    @Pattern(regexp = "[a-zA-Z]+", message = "First name must not contain special characters & numerics")
    private String firstName;

    @NotBlank(message = "Last name is mandatory")
    @Size(min = 3, max = 30, message = "Last name size must be between 3 to 30")
    @Pattern(regexp = "[a-zA-Z]+", message = "Last name must not contain special characters & numerics")
    @Column(name = "last_name")
    private String lastName;

    @Email(message = "Email is not valid", regexp = "^[A-Za-z0-9+_.-]+@(.+)$")
    @NotBlank(message = "Email cannot be empty")
    @Column(name = "personal_email", unique = true)
    private String email;

    // @JsonIgnore
    @NotBlank(message = "Password is mandatory")
    private String password;

    @NotNull(message = "Contact number cannot be blank")
    @Size(min = 10, max = 10, message = "Contact number size must be 10")
    @Pattern(regexp = "[0-9]+", message = "Contact number allows only numeric values")
    @Column(name = "contact_num")
    private String contactNumber;

    @NotNull(message = "Emergency contact number cannot be blank")
    @Size(min = 10, max = 10, message = "Emergency contact size must be 10")
    @Pattern(regexp = "[0-9]+", message = "Emergency contact allows only numeric values")
    @Column(name = "emergency_contact_num")
    private String emergencyContactNumber;

    @Enumerated(EnumType.STRING)
    private EmployeeGender employeeGender;

    @Enumerated(EnumType.STRING)
    private EmployeeMartialStatus employeeMartialStatus;

    @Enumerated(EnumType.STRING)
    private Role role;

    @NotBlank(message = "Designation is mandatory for employee")
    @Pattern(regexp = "[a-zA-Z]+", message = "Designation must not contain special characters & numerics")
    private String designation;

    @Email(message = "Email is not valid", regexp = "^[A-Za-z0-9+_.-]+@(.+)$")
    @NotBlank(message = "Email cannot be empty")
    @Column(name = "Office_email", unique = true)
    private String officeEmail;
    @NotNull(message = "EmployeeId cannot be blank")
   // @Pattern(regexp = "[0-9]+", message = " EmployeeId allows only numeric values")
    @Column(name = "EmployeeId")
    private String employeeId;

    private List<EmployeeAddressDto> employeeAddresses;
    private List<EmployeeJobHistoryDto> jobHistories;
    private List<EmployeeEducationalDetailsDto> educationalDetails;
    private EmployeeBankDetailsDto employeeBankDetails;
}
