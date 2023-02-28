package com.flyerssoft.org_chart.mapper;

import com.flyerssoft.org_chart.dto.EmployeeAddressDto;
import com.flyerssoft.org_chart.dto.EmployeeBankDetailsDto;
import com.flyerssoft.org_chart.dto.EmployeeEducationalDetailsDto;
import com.flyerssoft.org_chart.dto.EmployeeJobHistoryDto;
import com.flyerssoft.org_chart.dto.EmployeePersonalDetailDto;
import com.flyerssoft.org_chart.model.EmployeeAddress;
import com.flyerssoft.org_chart.model.EmployeeBankDetails;
import com.flyerssoft.org_chart.model.EmployeeEducationalDetails;
import com.flyerssoft.org_chart.model.EmployeeJobHistory;
import com.flyerssoft.org_chart.model.EmployeePersonalDetails;
import com.flyerssoft.org_chart.response.CustomEmployeeResponseDto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-02-28T12:58:25+0530",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 19.0.2 (Oracle Corporation)"
)
@Component
public class EmployeeMapperImpl implements EmployeeMapper {

    @Override
    public EmployeePersonalDetailDto entityToDto(EmployeePersonalDetails employeeDetailResponse) {
        if ( employeeDetailResponse == null ) {
            return null;
        }

        EmployeePersonalDetailDto employeePersonalDetailDto = new EmployeePersonalDetailDto();

        employeePersonalDetailDto.setId( employeeDetailResponse.getId() );
        employeePersonalDetailDto.setFirstName( employeeDetailResponse.getFirstName() );
        employeePersonalDetailDto.setLastName( employeeDetailResponse.getLastName() );
        employeePersonalDetailDto.setEmail( employeeDetailResponse.getEmail() );
        employeePersonalDetailDto.setPassword( employeeDetailResponse.getPassword() );
        employeePersonalDetailDto.setContactNumber( employeeDetailResponse.getContactNumber() );
        employeePersonalDetailDto.setEmergencyContactNumber( employeeDetailResponse.getEmergencyContactNumber() );
        employeePersonalDetailDto.setEmployeeGender( employeeDetailResponse.getEmployeeGender() );
        employeePersonalDetailDto.setEmployeeMartialStatus( employeeDetailResponse.getEmployeeMartialStatus() );
        employeePersonalDetailDto.setRole( employeeDetailResponse.getRole() );
        employeePersonalDetailDto.setDesignation( employeeDetailResponse.getDesignation() );
        employeePersonalDetailDto.setOfficialEmail( employeeDetailResponse.getOfficialEmail() );
        employeePersonalDetailDto.setEmployeeId( employeeDetailResponse.getEmployeeId() );
        employeePersonalDetailDto.setEmployeeAddresses( entityAddrrToDto( employeeDetailResponse.getEmployeeAddresses() ) );
        employeePersonalDetailDto.setJobHistories( jobHistoryEntityToDto( employeeDetailResponse.getJobHistories() ) );
        employeePersonalDetailDto.setEducationalDetails( educationalEntityToDto( employeeDetailResponse.getEducationalDetails() ) );
        employeePersonalDetailDto.setEmployeeBankDetails( bankEntityToDto( employeeDetailResponse.getEmployeeBankDetails() ) );

        return employeePersonalDetailDto;
    }

    @Override
    public EmployeeBankDetailsDto bankEntityToDto(EmployeeBankDetails employeeBankDetails) {
        if ( employeeBankDetails == null ) {
            return null;
        }

        EmployeeBankDetailsDto employeeBankDetailsDto = new EmployeeBankDetailsDto();

        employeeBankDetailsDto.setId( employeeBankDetails.getId() );
        employeeBankDetailsDto.setBankName( employeeBankDetails.getBankName() );
        employeeBankDetailsDto.setBankAccountNumber( employeeBankDetails.getBankAccountNumber() );
        employeeBankDetailsDto.setBankIfscCode( employeeBankDetails.getBankIfscCode() );
        employeeBankDetailsDto.setBankBranchLocation( employeeBankDetails.getBankBranchLocation() );
        employeeBankDetailsDto.setAadharNumber( employeeBankDetails.getAadharNumber() );
        employeeBankDetailsDto.setPanNumber( employeeBankDetails.getPanNumber() );
        employeeBankDetailsDto.setEmployeeBankAccountType( employeeBankDetails.getEmployeeBankAccountType() );

        return employeeBankDetailsDto;
    }

    @Override
    public List<EmployeeEducationalDetailsDto> educationalEntityToDto(List<EmployeeEducationalDetails> employeeEducationalDetails) {
        if ( employeeEducationalDetails == null ) {
            return null;
        }

        List<EmployeeEducationalDetailsDto> list = new ArrayList<EmployeeEducationalDetailsDto>( employeeEducationalDetails.size() );
        for ( EmployeeEducationalDetails employeeEducationalDetails1 : employeeEducationalDetails ) {
            list.add( employeeEducationalDetailsToEmployeeEducationalDetailsDto( employeeEducationalDetails1 ) );
        }

        return list;
    }

    @Override
    public List<EmployeeEducationalDetails> dtoToEducationalEntity(List<EmployeeEducationalDetailsDto> educationalDetailsDtos) {
        if ( educationalDetailsDtos == null ) {
            return null;
        }

        List<EmployeeEducationalDetails> list = new ArrayList<EmployeeEducationalDetails>( educationalDetailsDtos.size() );
        for ( EmployeeEducationalDetailsDto employeeEducationalDetailsDto : educationalDetailsDtos ) {
            list.add( employeeEducationalDetailsDtoToEmployeeEducationalDetails( employeeEducationalDetailsDto ) );
        }

        return list;
    }

    @Override
    public List<EmployeeJobHistoryDto> jobHistoryEntityToDto(List<EmployeeJobHistory> jobHistories) {
        if ( jobHistories == null ) {
            return null;
        }

        List<EmployeeJobHistoryDto> list = new ArrayList<EmployeeJobHistoryDto>( jobHistories.size() );
        for ( EmployeeJobHistory employeeJobHistory : jobHistories ) {
            list.add( employeeJobHistoryToEmployeeJobHistoryDto( employeeJobHistory ) );
        }

        return list;
    }

    @Override
    public List<EmployeeJobHistory> dtoTojobJobHistories(List<EmployeeJobHistoryDto> employeeJobHistoriesDto) {
        if ( employeeJobHistoriesDto == null ) {
            return null;
        }

        List<EmployeeJobHistory> list = new ArrayList<EmployeeJobHistory>( employeeJobHistoriesDto.size() );
        for ( EmployeeJobHistoryDto employeeJobHistoryDto : employeeJobHistoriesDto ) {
            list.add( employeeJobHistoryDtoToEmployeeJobHistory( employeeJobHistoryDto ) );
        }

        return list;
    }

    @Override
    public List<EmployeeAddress> dtoAddrToEntity(List<EmployeeAddressDto> addressDtos) {
        if ( addressDtos == null ) {
            return null;
        }

        List<EmployeeAddress> list = new ArrayList<EmployeeAddress>( addressDtos.size() );
        for ( EmployeeAddressDto employeeAddressDto : addressDtos ) {
            list.add( employeeAddressDtoToEmployeeAddress( employeeAddressDto ) );
        }

        return list;
    }

    @Override
    public List<EmployeeAddressDto> entityAddrrToDto(List<EmployeeAddress> employeeAddresses) {
        if ( employeeAddresses == null ) {
            return null;
        }

        List<EmployeeAddressDto> list = new ArrayList<EmployeeAddressDto>( employeeAddresses.size() );
        for ( EmployeeAddress employeeAddress : employeeAddresses ) {
            list.add( employeeAddressToEmployeeAddressDto( employeeAddress ) );
        }

        return list;
    }

    @Override
    public EmployeeBankDetails dtoToBankEntity(EmployeeBankDetailsDto employeeBankDetails) {
        if ( employeeBankDetails == null ) {
            return null;
        }

        EmployeeBankDetails employeeBankDetails1 = new EmployeeBankDetails();

        employeeBankDetails1.setId( employeeBankDetails.getId() );
        employeeBankDetails1.setBankName( employeeBankDetails.getBankName() );
        employeeBankDetails1.setBankAccountNumber( employeeBankDetails.getBankAccountNumber() );
        employeeBankDetails1.setBankIfscCode( employeeBankDetails.getBankIfscCode() );
        employeeBankDetails1.setBankBranchLocation( employeeBankDetails.getBankBranchLocation() );
        employeeBankDetails1.setAadharNumber( employeeBankDetails.getAadharNumber() );
        employeeBankDetails1.setPanNumber( employeeBankDetails.getPanNumber() );
        employeeBankDetails1.setEmployeeBankAccountType( employeeBankDetails.getEmployeeBankAccountType() );

        return employeeBankDetails1;
    }

    @Override
    public EmployeePersonalDetails dtoToEntity(EmployeePersonalDetailDto employeePersonalDetailDto) {
        if ( employeePersonalDetailDto == null ) {
            return null;
        }

        EmployeePersonalDetails employeePersonalDetails = new EmployeePersonalDetails();

        employeePersonalDetails.setId( employeePersonalDetailDto.getId() );
        employeePersonalDetails.setFirstName( employeePersonalDetailDto.getFirstName() );
        employeePersonalDetails.setLastName( employeePersonalDetailDto.getLastName() );
        employeePersonalDetails.setEmail( employeePersonalDetailDto.getEmail() );
        employeePersonalDetails.setPassword( employeePersonalDetailDto.getPassword() );
        employeePersonalDetails.setRole( employeePersonalDetailDto.getRole() );
        employeePersonalDetails.setDesignation( employeePersonalDetailDto.getDesignation() );
        employeePersonalDetails.setOfficialEmail( employeePersonalDetailDto.getOfficialEmail() );
        employeePersonalDetails.setEmployeeId( employeePersonalDetailDto.getEmployeeId() );
        employeePersonalDetails.setContactNumber( employeePersonalDetailDto.getContactNumber() );
        employeePersonalDetails.setEmergencyContactNumber( employeePersonalDetailDto.getEmergencyContactNumber() );
        employeePersonalDetails.setEmployeeGender( employeePersonalDetailDto.getEmployeeGender() );
        employeePersonalDetails.setEmployeeMartialStatus( employeePersonalDetailDto.getEmployeeMartialStatus() );
        employeePersonalDetails.setEmployeeBankDetails( dtoToBankEntity( employeePersonalDetailDto.getEmployeeBankDetails() ) );
        employeePersonalDetails.setEducationalDetails( dtoToEducationalEntity( employeePersonalDetailDto.getEducationalDetails() ) );
        employeePersonalDetails.setJobHistories( dtoTojobJobHistories( employeePersonalDetailDto.getJobHistories() ) );
        employeePersonalDetails.setEmployeeAddresses( dtoAddrToEntity( employeePersonalDetailDto.getEmployeeAddresses() ) );

        EmployeeMapper.mapEnums( employeePersonalDetails );

        return employeePersonalDetails;
    }

    @Override
    public CustomEmployeeResponseDto entityToCustomListDto(EmployeePersonalDetails employeePersonalDetailsList) {
        if ( employeePersonalDetailsList == null ) {
            return null;
        }

        CustomEmployeeResponseDto customEmployeeResponseDto = new CustomEmployeeResponseDto();

        customEmployeeResponseDto.setId( employeePersonalDetailsList.getId() );
        customEmployeeResponseDto.setFirstName( employeePersonalDetailsList.getFirstName() );
        customEmployeeResponseDto.setLastName( employeePersonalDetailsList.getLastName() );
        customEmployeeResponseDto.setOfficialEmail( employeePersonalDetailsList.getOfficialEmail() );
        customEmployeeResponseDto.setDesignation( employeePersonalDetailsList.getDesignation() );
        if ( employeePersonalDetailsList.getRole() != null ) {
            customEmployeeResponseDto.setRole( employeePersonalDetailsList.getRole().name() );
        }
        customEmployeeResponseDto.setEmployeeId( employeePersonalDetailsList.getEmployeeId() );
        customEmployeeResponseDto.setContactNumber( employeePersonalDetailsList.getContactNumber() );

        return customEmployeeResponseDto;
    }

    protected EmployeeEducationalDetailsDto employeeEducationalDetailsToEmployeeEducationalDetailsDto(EmployeeEducationalDetails employeeEducationalDetails) {
        if ( employeeEducationalDetails == null ) {
            return null;
        }

        EmployeeEducationalDetailsDto employeeEducationalDetailsDto = new EmployeeEducationalDetailsDto();

        employeeEducationalDetailsDto.setId( employeeEducationalDetails.getId() );
        employeeEducationalDetailsDto.setQualification( employeeEducationalDetails.getQualification() );
        employeeEducationalDetailsDto.setStream( employeeEducationalDetails.getStream() );
        employeeEducationalDetailsDto.setPercentage( employeeEducationalDetails.getPercentage() );
        employeeEducationalDetailsDto.setCourseStartDate( employeeEducationalDetails.getCourseStartDate() );
        employeeEducationalDetailsDto.setCourseEndDate( employeeEducationalDetails.getCourseEndDate() );
        employeeEducationalDetailsDto.setEmployeeEducationalType( employeeEducationalDetails.getEmployeeEducationalType() );

        return employeeEducationalDetailsDto;
    }

    protected EmployeeEducationalDetails employeeEducationalDetailsDtoToEmployeeEducationalDetails(EmployeeEducationalDetailsDto employeeEducationalDetailsDto) {
        if ( employeeEducationalDetailsDto == null ) {
            return null;
        }

        EmployeeEducationalDetails employeeEducationalDetails = new EmployeeEducationalDetails();

        employeeEducationalDetails.setId( employeeEducationalDetailsDto.getId() );
        employeeEducationalDetails.setQualification( employeeEducationalDetailsDto.getQualification() );
        employeeEducationalDetails.setStream( employeeEducationalDetailsDto.getStream() );
        employeeEducationalDetails.setPercentage( employeeEducationalDetailsDto.getPercentage() );
        employeeEducationalDetails.setCourseStartDate( employeeEducationalDetailsDto.getCourseStartDate() );
        employeeEducationalDetails.setCourseEndDate( employeeEducationalDetailsDto.getCourseEndDate() );
        employeeEducationalDetails.setEmployeeEducationalType( employeeEducationalDetailsDto.getEmployeeEducationalType() );

        return employeeEducationalDetails;
    }

    protected EmployeeJobHistoryDto employeeJobHistoryToEmployeeJobHistoryDto(EmployeeJobHistory employeeJobHistory) {
        if ( employeeJobHistory == null ) {
            return null;
        }

        EmployeeJobHistoryDto employeeJobHistoryDto = new EmployeeJobHistoryDto();

        employeeJobHistoryDto.setId( employeeJobHistory.getId() );
        employeeJobHistoryDto.setCompany( employeeJobHistory.getCompany() );
        employeeJobHistoryDto.setRole( employeeJobHistory.getRole() );
        employeeJobHistoryDto.setJobStartDate( employeeJobHistory.getJobStartDate() );
        employeeJobHistoryDto.setJobEndDate( employeeJobHistory.getJobEndDate() );

        return employeeJobHistoryDto;
    }

    protected EmployeeJobHistory employeeJobHistoryDtoToEmployeeJobHistory(EmployeeJobHistoryDto employeeJobHistoryDto) {
        if ( employeeJobHistoryDto == null ) {
            return null;
        }

        EmployeeJobHistory employeeJobHistory = new EmployeeJobHistory();

        employeeJobHistory.setId( employeeJobHistoryDto.getId() );
        employeeJobHistory.setCompany( employeeJobHistoryDto.getCompany() );
        employeeJobHistory.setRole( employeeJobHistoryDto.getRole() );
        employeeJobHistory.setJobStartDate( employeeJobHistoryDto.getJobStartDate() );
        employeeJobHistory.setJobEndDate( employeeJobHistoryDto.getJobEndDate() );

        return employeeJobHistory;
    }

    protected EmployeeAddress employeeAddressDtoToEmployeeAddress(EmployeeAddressDto employeeAddressDto) {
        if ( employeeAddressDto == null ) {
            return null;
        }

        EmployeeAddress employeeAddress = new EmployeeAddress();

        employeeAddress.setId( employeeAddressDto.getId() );
        employeeAddress.setAddressType( employeeAddressDto.getAddressType() );
        employeeAddress.setLine1( employeeAddressDto.getLine1() );
        employeeAddress.setLine2( employeeAddressDto.getLine2() );
        employeeAddress.setCity( employeeAddressDto.getCity() );
        employeeAddress.setState( employeeAddressDto.getState() );
        employeeAddress.setPinCode( employeeAddressDto.getPinCode() );

        return employeeAddress;
    }

    protected EmployeeAddressDto employeeAddressToEmployeeAddressDto(EmployeeAddress employeeAddress) {
        if ( employeeAddress == null ) {
            return null;
        }

        EmployeeAddressDto employeeAddressDto = new EmployeeAddressDto();

        employeeAddressDto.setId( employeeAddress.getId() );
        employeeAddressDto.setAddressType( employeeAddress.getAddressType() );
        employeeAddressDto.setLine1( employeeAddress.getLine1() );
        employeeAddressDto.setLine2( employeeAddress.getLine2() );
        employeeAddressDto.setCity( employeeAddress.getCity() );
        employeeAddressDto.setState( employeeAddress.getState() );
        employeeAddressDto.setPinCode( employeeAddress.getPinCode() );

        return employeeAddressDto;
    }
}
