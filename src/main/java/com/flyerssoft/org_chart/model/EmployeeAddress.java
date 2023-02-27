package com.flyerssoft.org_chart.model;

import com.flyerssoft.org_chart.enums.AddressType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The employee address detail entity
 */

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Enumerated(EnumType.STRING)
    private AddressType addressType;
    private String Line1;
    private String Line2;
    private String city;
    private String state;
    private Long pinCode;

}
