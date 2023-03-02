package com.flyerssoft.org_chart.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.flyerssoft.org_chart.enums.EmployeeGender;
import com.flyerssoft.org_chart.enums.EmployeeMartialStatus;
import com.flyerssoft.org_chart.enums.Role;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.Valid;
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

    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Email is not valid", regexp = "^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$")
    @Column(name = "personal_email", unique = true)
    private String email;


    @NotBlank(message = "Password is mandatory")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$", message = "Password should contain 8 characters with (1 lowercase, 1 uppercase, 1 symbol , 1 number)")
    private String password;

    @NotBlank(message = "Contact number cannot be blank")
    @Size(min = 10, max = 12, message = "Contact number size must be 10 to 12 in size")
    @Pattern(regexp = "[0-9]+", message = "Contact number allows only numeric values")
    @Column(name = "contact_num")
    private String contactNumber;

    @NotBlank(message = "Emergency contact number cannot be blank")
    @Size(min = 10, max = 12, message = "Emergency contact size must be 10 to 12 in size")
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
    @Pattern(regexp = "^[A-Za-z\\s]*$", message = "Designation must not contain special characters & numerics")
    private String designation;

    private Long primaryReportingManager;

    private Long reportingManager;

    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Email is not valid", regexp = "^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$")
    @Column(name = "Office_email", unique = true)
    private String officialEmail;

    @NotBlank(message = "EmployeeId cannot be blank")
    @Column(name = "employeeId")
    private String employeeId;

    @Valid
    private EmployeeDepartmentDto employeeDepartment;

    @Valid
    private List<EmployeeAddressDto> employeeAddresses;

    @Valid
    private List<EmployeeJobHistoryDto> jobHistories;

    @Valid
    private List<EmployeeEducationalDetailsDto> educationalDetails;

    @Valid
    private EmployeeBankDetailsDto employeeBankDetails;

}
