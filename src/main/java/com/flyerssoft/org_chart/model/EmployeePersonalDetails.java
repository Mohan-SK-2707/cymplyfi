package com.flyerssoft.org_chart.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.flyerssoft.org_chart.enums.EmployeeGender;
import com.flyerssoft.org_chart.enums.EmployeeMartialStatus;
import com.flyerssoft.org_chart.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * The employee personal detail entity
 */

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EmployeePersonalDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
    private String designation;
    private String officeEmail;
    private String employeeId;
    private String contactNumber;
    private String emergencyContactNumber;
    @Enumerated(EnumType.STRING)
    private EmployeeGender employeeGender;
    @Enumerated(EnumType.STRING)
    private EmployeeMartialStatus employeeMartialStatus;


    @OneToOne(cascade = CascadeType.ALL)//, orphanRemoval = true
    @JsonIgnore
    private EmployeeBankDetails employeeBankDetails;

    @OneToMany(cascade = CascadeType.ALL)//, orphanRemoval = true
    @JoinColumn(name = "employeeId" ,referencedColumnName = "id")
    private List<EmployeeEducationalDetails> educationalDetails;

    @OneToMany( cascade = CascadeType.ALL)//, orphanRemoval = true
    @JoinColumn(name = "employeeId" ,referencedColumnName = "id")
    private List<EmployeeJobHistory> jobHistories;

    @OneToMany(cascade = CascadeType.ALL)//, orphanRemoval = true
    @JoinColumn(name = "employeeId" ,referencedColumnName = "id")
    private List<EmployeeAddress> employeeAddresses;
}
