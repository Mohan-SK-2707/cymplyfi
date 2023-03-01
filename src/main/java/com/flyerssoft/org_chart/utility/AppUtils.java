package com.flyerssoft.org_chart.utility;

import com.flyerssoft.org_chart.dto.*;
import com.flyerssoft.org_chart.mapper.EmployeeMapper;
import com.flyerssoft.org_chart.model.*;
import com.flyerssoft.org_chart.response.CustomEmployeeResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class AppUtils {

    @Autowired
    private EmployeeMapper mapper;

    public EmployeePersonalDetailDto mapEntityToDtos(EmployeePersonalDetails employeePersonalDetails) {
        List<EmployeeEducationalDetailsDto> educationalDetailsDto = mapper.educationalEntityToDto(employeePersonalDetails.getEducationalDetails());
        EmployeeBankDetailsDto bankDetailsDetails = mapper.bankEntityToDto(employeePersonalDetails.getEmployeeBankDetails());
        EmployeeDepartmentDto departmentDto = mapper.departmentEntityToDto(employeePersonalDetails.getDepartment());
        List<EmployeeJobHistoryDto> jobHistoriesDto = mapper.jobHistoryEntityToDto(employeePersonalDetails.getJobHistories());
        List<EmployeeAddressDto> addressDetails = mapper.entityAddrrToDto(employeePersonalDetails.getEmployeeAddresses());
        EmployeePersonalDetailDto personalDetailDto = new EmployeePersonalDetailDto();
        personalDetailDto.setId(employeePersonalDetails.getId());
        personalDetailDto.setFirstName(employeePersonalDetails.getFirstName());
        personalDetailDto.setLastName(employeePersonalDetails.getLastName());
        personalDetailDto.setEmail(employeePersonalDetails.getEmail());
        personalDetailDto.setContactNumber(employeePersonalDetails.getContactNumber());
        personalDetailDto.setEmergencyContactNumber(employeePersonalDetails.getEmergencyContactNumber());
        personalDetailDto.setEmployeeMartialStatus(employeePersonalDetails.getEmployeeMartialStatus());
        personalDetailDto.setEmployeeGender(employeePersonalDetails.getEmployeeGender());
        personalDetailDto.setRole(employeePersonalDetails.getRole());
        personalDetailDto.setPrimaryReportingManager(employeePersonalDetails.getPrimaryReportingManager());
        personalDetailDto.setReportingManager(employeePersonalDetails.getReportingManager());
        personalDetailDto.setEmployeeDepartment(departmentDto);
        personalDetailDto.setEmployeeAddresses(addressDetails);
        personalDetailDto.setEducationalDetails(educationalDetailsDto);
        personalDetailDto.setEmployeeBankDetails(bankDetailsDetails);
        personalDetailDto.setJobHistories(jobHistoriesDto);
        return personalDetailDto;
    }

    public EmployeePersonalDetails dtoToEntity(EmployeePersonalDetailDto employeePersonalDetailDto) {
        List<EmployeeEducationalDetails> employeeEducationalDetails = mapper.dtoToEducationalEntity(employeePersonalDetailDto.getEducationalDetails());
        EmployeeBankDetails employeeBankDetails = mapper.dtoToBankEntity(employeePersonalDetailDto.getEmployeeBankDetails());
        EmployeeDepartment employeeDepartment = mapper.dtoToDepartmentEntity(employeePersonalDetailDto.getEmployeeDepartment());
        List<EmployeeJobHistory> employeeJobHistories = mapper.dtoTojobJobHistories(employeePersonalDetailDto.getJobHistories());
        List<EmployeeAddress> employeeAddresses = mapper.dtoAddrToEntity(employeePersonalDetailDto.getEmployeeAddresses());
        EmployeePersonalDetails employeePersonalDetails = mapper.dtoToEntity(employeePersonalDetailDto);
        employeePersonalDetails.setDepartment(employeeDepartment);
        employeePersonalDetails.setEducationalDetails(employeeEducationalDetails);
        employeePersonalDetails.setEmployeeBankDetails(employeeBankDetails);
        employeePersonalDetails.setJobHistories(employeeJobHistories);
        employeePersonalDetails.setEmployeeAddresses(employeeAddresses);
        return employeePersonalDetails;
    }

    //Custom update methods
    public EmployeePersonalDetails updateEmployeeDetails(EmployeePersonalDetails existsEmployeePersonalDetails, EmployeePersonalDetailDto employeePersonalDetailDto) {
        existsEmployeePersonalDetails.setFirstName(employeePersonalDetailDto.getFirstName());
        existsEmployeePersonalDetails.setLastName(employeePersonalDetailDto.getLastName());
        existsEmployeePersonalDetails.setEmployeeGender(employeePersonalDetailDto.getEmployeeGender());
        existsEmployeePersonalDetails.setContactNumber(employeePersonalDetailDto.getContactNumber());
        existsEmployeePersonalDetails.setEmergencyContactNumber(employeePersonalDetailDto.getEmergencyContactNumber());
        if (CollectionUtils.isNotEmpty(employeePersonalDetailDto.getEmployeeAddresses())) {
            if (employeePersonalDetailDto.getEmployeeAddresses().size() > 2) {
                log.warn("Address cannot be more than two");
                List<EmployeeAddressDto> addressDtos = new ArrayList<>();
                for (int i = 0; i < existsEmployeePersonalDetails.getEmployeeAddresses().size(); i++) {
                    if (existsEmployeePersonalDetails.getEmployeeAddresses().get(i).getId() == employeePersonalDetailDto.getEmployeeAddresses().get(i).getId()) {
                        addressDtos.add(employeePersonalDetailDto.getEmployeeAddresses().get(i));
                    } else {
                        log.error("Invalid address entry");
                    }
                }
                existsEmployeePersonalDetails.setEmployeeAddresses(mapper.dtoAddrToEntity(addressDtos));
                log.warn("Updated Addresses size : {}", existsEmployeePersonalDetails.getEmployeeAddresses().size());
            }
        }
        if (CollectionUtils.isNotEmpty(employeePersonalDetailDto.getJobHistories())) {
            for (int i = 0; i < employeePersonalDetailDto.getJobHistories().size(); i++) {
                if (ObjectUtils.isNotEmpty(employeePersonalDetailDto.getJobHistories()) && ObjectUtils.isNotEmpty(existsEmployeePersonalDetails.getJobHistories())) {
                    if (ObjectUtils.isNotEmpty(employeePersonalDetailDto.getJobHistories().get(i)) && ObjectUtils.isNotEmpty(existsEmployeePersonalDetails.getJobHistories().get(i))) {
                        if (employeePersonalDetailDto.getJobHistories().get(i).getId() == existsEmployeePersonalDetails.getJobHistories().get(i).getId()) {
                            existsEmployeePersonalDetails.setJobHistories(mapper.dtoTojobJobHistories(employeePersonalDetailDto.getJobHistories()));
                        }
                    }
                }
            }
        } else {
            existsEmployeePersonalDetails.setJobHistories(mapper.dtoTojobJobHistories(employeePersonalDetailDto.getJobHistories()));
        }
        if (CollectionUtils.isNotEmpty(existsEmployeePersonalDetails.getEducationalDetails())) {
            if (CollectionUtils.isNotEmpty(employeePersonalDetailDto.getEducationalDetails())) {
                for (int i = 0; i < employeePersonalDetailDto.getEducationalDetails().size(); i++) {
                    if (ObjectUtils.isNotEmpty(employeePersonalDetailDto.getEducationalDetails().get(i)) && ObjectUtils.isNotEmpty(existsEmployeePersonalDetails.getEducationalDetails().get(i))) {
                        if (employeePersonalDetailDto.getEducationalDetails().get(i).getId() == existsEmployeePersonalDetails.getEducationalDetails().get(i).getId()) {
                            existsEmployeePersonalDetails.setEducationalDetails(mapper.dtoToEducationalEntity(employeePersonalDetailDto.getEducationalDetails()));
                        }
                    }
                }
            }
        } else {
            existsEmployeePersonalDetails.setEducationalDetails(mapper.dtoToEducationalEntity(employeePersonalDetailDto.getEducationalDetails()));
        }
        if (ObjectUtils.isNotEmpty(existsEmployeePersonalDetails.getEmployeeBankDetails())) {
            if (ObjectUtils.isNotEmpty(employeePersonalDetailDto.getEmployeeBankDetails())) {
                existsEmployeePersonalDetails.setEmployeeBankDetails(mapper.dtoToBankEntity(employeePersonalDetailDto.getEmployeeBankDetails()));
            }
        }
        return existsEmployeePersonalDetails;
    }


    public CustomEmployeeResponseDto mapEntityToCustomDtos(EmployeePersonalDetails employeePersonalDetailsList) {
        return mapper.entityToCustomListDto(employeePersonalDetailsList);
    }
}
