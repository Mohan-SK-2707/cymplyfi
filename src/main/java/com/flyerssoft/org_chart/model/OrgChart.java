package com.flyerssoft.org_chart.model;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "org_chart")
public class OrgChart {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String name;

	private String designation;

	private String organisation;

	private String location;

	private String role;

	private Set<Integer> reportees;

//	"name": "Sathish",
//    "designation": "Senior Software Developer",
//    "organisation": "Flyerssoft",
//    "location": "Chennai",
//    "role": "SrDeveloper",
//    "reportees": []
}
