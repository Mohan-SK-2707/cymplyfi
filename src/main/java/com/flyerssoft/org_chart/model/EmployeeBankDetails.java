package com.flyerssoft.org_chart.model;

import com.flyerssoft.org_chart.enums.EmployeeBankAccountType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The employee bank detail entity
 */

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeBankDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String bankName;
    private Long bankAccountNumber;
    private String bankIfscCode;
    private String bankBranchLocation;
    private String adharNumber;
    private String panNumber;
    @Enumerated(EnumType.STRING)
    private EmployeeBankAccountType employeeBankAccountType;
//    @OneToOne
//    @JsonIgnore
//    private EmployeePersonalDetails employeePersonalDetails;
}
