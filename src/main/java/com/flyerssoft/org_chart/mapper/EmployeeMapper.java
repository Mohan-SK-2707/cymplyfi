package com.flyerssoft.org_chart.mapper;

import com.flyerssoft.org_chart.dto.*;
import com.flyerssoft.org_chart.enums.*;
import com.flyerssoft.org_chart.model.*;
import com.flyerssoft.org_chart.response.CustomEmployeeResponseDto;
import com.flyerssoft.org_chart.utility.AppUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.Enumeration;
import java.util.List;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {


    EmployeePersonalDetailDto entityToDto(EmployeePersonalDetails employeeDetailResponse);


    EmployeeBankDetailsDto bankEntityToDto(EmployeeBankDetails employeeBankDetails);


    List<EmployeeEducationalDetailsDto> educationalEntityToDto(List<EmployeeEducationalDetails> employeeEducationalDetails);

    List<EmployeeEducationalDetails> dtoToEducationalEntity(List<EmployeeEducationalDetailsDto> educationalDetailsDtos);

    List<EmployeeJobHistoryDto> jobHistoryEntityToDto(List<EmployeeJobHistory> jobHistories);

    List<EmployeeJobHistory> dtoTojobJobHistories(List<EmployeeJobHistoryDto> employeeJobHistoriesDto);

//    EmployeePersonalDetails mapPersonalDetailsToUpdatedEmployeePersonalDetails(EmployeePersonalDetailDto employeePersonalDetailsDto);

    @AfterMapping
    static void mapEnums(@MappingTarget final EmployeePersonalDetails details) {

        //Mapping Gender values
        if (ObjectUtils.isNotEmpty(details) && ObjectUtils.isNotEmpty(details.getEmployeeGender())) {
            if (String.valueOf(details.getEmployeeGender()).equalsIgnoreCase("male")) {
                details.setEmployeeGender(EmployeeGender.MALE);
            } else if (String.valueOf(details.getEmployeeGender()).equalsIgnoreCase("female")) {
                details.setEmployeeGender(EmployeeGender.FEMALE);
            } else if (String.valueOf(details.getEmployeeGender()).equalsIgnoreCase("others")) {
                details.setEmployeeGender(EmployeeGender.OTHERS);
            }
        }

        //Mapping marital status
        if (ObjectUtils.isNotEmpty(details) && ObjectUtils.isNotEmpty(details.getEmployeeMartialStatus())) {
            if (String.valueOf(details.getEmployeeMartialStatus()).equalsIgnoreCase("single")) {
                details.setEmployeeMartialStatus(EmployeeMartialStatus.SINGLE);
            } else if (String.valueOf(details.getEmployeeMartialStatus()).equalsIgnoreCase("married")) {
                details.setEmployeeMartialStatus(EmployeeMartialStatus.MARRIED);
            }
        }

        //Mapping bank account type
        if (ObjectUtils.isNotEmpty(details) && ObjectUtils.isNotEmpty(details.getEmployeeBankDetails())) {
            if (ObjectUtils.isNotEmpty(details.getEmployeeBankDetails().getEmployeeBankAccountType())) {
                if (String.valueOf(details.getEmployeeBankDetails().getEmployeeBankAccountType()).equalsIgnoreCase("personal")) {
                    details.getEmployeeBankDetails().setEmployeeBankAccountType(EmployeeBankAccountType.PERSONAL);
                } else if (String.valueOf(details.getEmployeeBankDetails().getEmployeeBankAccountType()).equalsIgnoreCase("current")) {
                    details.getEmployeeBankDetails().setEmployeeBankAccountType(EmployeeBankAccountType.CURRENT);
                } else if (String.valueOf(details.getEmployeeBankDetails().getEmployeeBankAccountType()).equalsIgnoreCase("salaried")) {
                    details.getEmployeeBankDetails().setEmployeeBankAccountType(EmployeeBankAccountType.SALARIED);
                }
            }
        }

        //Mapping educational type
        if (ObjectUtils.isNotEmpty(details) && CollectionUtils.isNotEmpty(details.getEducationalDetails())) {
            details.getEducationalDetails().stream().filter(edu -> ObjectUtils.isNotEmpty(edu) && ObjectUtils.isNotEmpty(edu.getEmployeeEducationalType())).forEach(edu -> {
                if (String.valueOf(edu.getEmployeeEducationalType()).equalsIgnoreCase("undergraduate")) {
                    edu.setEmployeeEducationalType(EmployeeEducationalType.UNDERGRADUATE);
                } else if (String.valueOf(edu.getEmployeeEducationalType()).equalsIgnoreCase("postgraduate")) {
                    edu.setEmployeeEducationalType(EmployeeEducationalType.POSTGRADUATE);
                } else if (String.valueOf(edu.getEmployeeEducationalType()).equalsIgnoreCase("diploma")) {
                    edu.setEmployeeEducationalType(EmployeeEducationalType.DIPLOMA);
                }
            });
        }

        //Mapping address type
        if (ObjectUtils.isNotEmpty(details) && CollectionUtils.isNotEmpty(details.getEmployeeAddresses())) {
            details.getEmployeeAddresses().stream().filter(addr -> ObjectUtils.isNotEmpty(addr) && ObjectUtils.isNotEmpty(addr.getAddressType())).forEach(addr -> {
                if (String.valueOf(addr.getAddressType()).equalsIgnoreCase("current")) {
                    addr.setAddressType(AddressType.CURRENT);
                } else if (String.valueOf(addr.getAddressType()).equalsIgnoreCase("permanent")) {
                    addr.setAddressType(AddressType.PERMANENT);
                }
            });
        }
    }

    List<EmployeeAddress> dtoAddrToEntity(List<EmployeeAddressDto> addressDtos);

    List<EmployeeAddressDto> entityAddrrToDto(List<EmployeeAddress> employeeAddresses);

    EmployeeBankDetails dtoToBankEntity(EmployeeBankDetailsDto employeeBankDetails);
    EmployeePersonalDetails dtoToEntity(EmployeePersonalDetailDto employeePersonalDetailDto);

    CustomEmployeeResponseDto entityToCustomListDto(EmployeePersonalDetails employeePersonalDetailsList);

    EmployeeDepartmentDto departmentEntityToDto(EmployeeDepartment department);

    EmployeeDepartment dtoToDepartmentEntity(EmployeeDepartmentDto employeeDepartment);
}
