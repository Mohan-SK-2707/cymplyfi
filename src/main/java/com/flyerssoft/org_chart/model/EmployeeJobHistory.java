package com.flyerssoft.org_chart.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * The employee job history detail entity
 */

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeJobHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String Company;
    private String role;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-yyyy")
    private Date jobStartDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-yyyy")
    private Date jobEndDate;

}
