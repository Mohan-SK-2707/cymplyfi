package com.flyerssoft.org_chart.mapper;

import com.flyerssoft.org_chart.dto.*;
import com.flyerssoft.org_chart.enums.*;
import com.flyerssoft.org_chart.exceptionhandler.FieldException;
import com.flyerssoft.org_chart.model.*;
import com.flyerssoft.org_chart.response.CustomEmployeeResponseDto;
import com.flyerssoft.org_chart.utility.AppUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.mapstruct.AfterMapping;
import org.mapstruct.BeforeMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.time.DateTimeException;
import java.time.Month;
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

    List<EmployeeAddress> dtoAddrToEntity(List<EmployeeAddressDto> addressDtos);

    List<EmployeeAddressDto> entityAddrrToDto(List<EmployeeAddress> employeeAddresses);

    EmployeeBankDetails dtoToBankEntity(EmployeeBankDetailsDto employeeBankDetails);

    EmployeePersonalDetails dtoToEntity(EmployeePersonalDetailDto employeePersonalDetailDto);

    CustomEmployeeResponseDto entityToCustomListDto(EmployeePersonalDetails employeePersonalDetailsList);

    EmployeeDepartmentDto departmentEntityToDto(EmployeeDepartment department);

    EmployeeDepartment dtoToDepartmentEntity(EmployeeDepartmentDto employeeDepartment);

    @BeforeMapping
    default void validateDateAndMonth(@MappingTarget final EmployeePersonalDetails details) {
        //Job history date validations
        if (ObjectUtils.isNotEmpty(details)) {
            if (CollectionUtils.isNotEmpty(details.getJobHistories())) {
                details.getJobHistories().stream().forEach(job -> {
                    if (ObjectUtils.isNotEmpty(job)) {
                        if (ObjectUtils.isNotEmpty(job.getJobStartDate()) && ObjectUtils.isNotEmpty(job.getJobEndDate())) {
                            this.checkValidMonth(job);
                            this.checkValidYear(job.getJobStartDate().getYear(), job.getJobEndDate().getYear());
                        }
                    }
                });
            }
        }
    }

    default void checkValidMonth(EmployeeJobHistory jobHistory) {
        if (jobHistory.getJobStartDate().getMonth() < 1 || jobHistory.getJobStartDate().getMonth() > 12) {
            throw new FieldException("Invalid value for MonthOfYear");
        }

        if (jobHistory.getJobStartDate().getMonth() < 1 || jobHistory.getJobEndDate().getMonth() > 12) {
            throw new FieldException("Invalid value for MonthOfYear");
        }
    }

    default void checkValidYear(int startYear, int endYear) {
        if (startYear < endYear) {
            throw new FieldException("Job history dates are not valid");
        }
    }

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
    List<EmployeeDepartmentDto> departmentEntityListToDto(List<EmployeeDepartment> departments);

    List<EmployeePersonalDetailDto> employeePersonalDetailEntityListToDto(List<EmployeePersonalDetails> employees);
}
