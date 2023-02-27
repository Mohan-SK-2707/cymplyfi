package com.flyerssoft.org_chart.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.flyerssoft.org_chart.enums.EmployeeEducationalType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * The employee educational detail entity
 */

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeEducationalDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String qualification;
    private String stream;
    private String percentage;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-yyyy")
    private Date courseStartDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-yyyy")
    private Date courseEndDate;
    @Enumerated(EnumType.STRING)
    private EmployeeEducationalType employeeEducationalType;

}
