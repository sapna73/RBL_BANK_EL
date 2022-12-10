package com.swadhaar.los.models;

import com.google.gson.annotations.Expose;

public class CBMFIRequestDTO {

    @Expose
    private String UniqueId = "";
    @Expose
    private String ClientID = "";
    @Expose
    private String ExternalCustomerId = "";
    @Expose
    private String KYCId = "";
    @Expose
    private String CreatedDate = "";
    @Expose
    private String CreatedBy = "";
    @Expose
    private String ServiceType = "";
    @Expose
    private RequestStringClass RequestString;

    public String getUniqueId() {
        return UniqueId;
    }

    public void setUniqueId(String uniqueId) {
        UniqueId = uniqueId;
    }

    public String getClientID() {
        return ClientID;
    }

    public void setClientID(String clientID) {
        ClientID = clientID;
    }

    public String getExternalCustomerId() {
        return ExternalCustomerId;
    }

    public void setExternalCustomerId(String externalCustomerId) {
        ExternalCustomerId = externalCustomerId;
    }

    public String getKYCId() {
        return KYCId;
    }

    public void setKYCId(String KYCId) {
        this.KYCId = KYCId;
    }

    public String getCreatedDate() {
        return CreatedDate;
    }

    public void setCreatedDate(String createdDate) {
        CreatedDate = createdDate;
    }

    public String getCreatedBy() {
        return CreatedBy;
    }

    public void setCreatedBy(String createdBy) {
        CreatedBy = createdBy;
    }

    public String getServiceType() {
        return ServiceType;
    }

    public void setServiceType(String serviceType) {
        ServiceType = serviceType;
    }

    public RequestStringClass getRequestString() {
        return RequestString;
    }

    public void setRequestString(RequestStringClass requestString) {
        RequestString = requestString;
    }

    public static class RequestStringClass {
        @Expose
        private cbEnquiryClass cbmfi;

        public cbEnquiryClass getCbEnquiry() {
            return cbmfi;
        }

        public void setCbEnquiry(cbEnquiryClass cbEnquiry) {
            this.cbmfi = cbEnquiry;
        }
    }

    public static class cbEnquiryClass {
        @Expose
        private String RequestFrom="App";
        @Expose
        private String ModuleType="";
        @Expose
        private String ApplicationId="";
        @Expose
        private String InquirySource="sarthak";
        @Expose
        private String SolutionSetId="";
        @Expose
        private String SolutionSetVersion="";
        @Expose
        private String ExecutionMode="";
        @Expose
        private String EnvironmentType="";
        @Expose
        private String ChannelPartnerId="";
        @Expose
        private String ApplicantFirstName="";
        @Expose
        private String ApplicantMiddleName="";
        @Expose
        private String ApplicantLastName="";
        @Expose
        private String DateofBirth="";
        @Expose
        private String Gender="";
        @Expose
        private String PanNo="";
        @Expose
        private String MaritalStatus="";
        @Expose
        private String ResidenceAddress1="";
        @Expose
        private String ResidenceAddress2="";
        @Expose
        private String ResidenceAddress3="";
        @Expose
        private String ResidencePincode="";
        @Expose
        private String ResidenceCity="";
        @Expose
        private String ResidenceState="";
        @Expose
        private String ResidenceMobileNumber="";
        @Expose
        private String AppliedLoanAmount="0";
        @Expose
        private String NoOfDependents="";
        @Expose
        private String AdditionalName="";
        @Expose
        private String AdditionalNameType="";
        @Expose
        private String AdditionalId1="";
        @Expose
        private String AdditionalId2="";
        @Expose
        private String AdharCardNo="";
        @Expose
        private String RationCard="";
        @Expose
        private String VoterId="";
        @Expose
        private String Passport="";
        @Expose
        private String PhoneNumber="";
        @Expose
        private String DriverLicense="";
        @Expose
        private String BranchIDMFI="";
        @Expose
        private String KendraIDMFI="";
        @Expose
        private String Placeholder1="";
        @Expose
        private String Placeholder2="";
        @Expose
        private String Placeholder3="";
        @Expose
        private String Placeholder4="";
        @Expose
        private String Placeholder5="";
        @Expose
        private String P1="";
        @Expose
        private String P2="";
        @Expose
        private String P3="";
        @Expose
        private String P4="";
        @Expose
        private String P5="";


        public String getRequestFrom() {
            return RequestFrom;
        }

        public void setRequestFrom(String requestFrom) {
            RequestFrom = requestFrom;
        }

        public String getModuleType() {
            return ModuleType;
        }

        public void setModuleType(String moduleType) {
            ModuleType = moduleType;
        }

        public String getApplicationId() {
            return ApplicationId;
        }

        public void setApplicationId(String applicationId) {
            ApplicationId = applicationId;
        }

        public String getInquirySource() {
            return InquirySource;
        }

        public void setInquirySource(String inquirySource) {
            InquirySource = inquirySource;
        }

        public String getSolutionSetId() {
            return SolutionSetId;
        }

        public void setSolutionSetId(String solutionSetId) {
            SolutionSetId = solutionSetId;
        }

        public String getSolutionSetVersion() {
            return SolutionSetVersion;
        }

        public void setSolutionSetVersion(String solutionSetVersion) {
            SolutionSetVersion = solutionSetVersion;
        }

        public String getExecutionMode() {
            return ExecutionMode;
        }

        public void setExecutionMode(String executionMode) {
            ExecutionMode = executionMode;
        }

        public String getEnvironmentType() {
            return EnvironmentType;
        }

        public void setEnvironmentType(String environmentType) {
            EnvironmentType = environmentType;
        }

        public String getChannelPartnerId() {
            return ChannelPartnerId;
        }

        public void setChannelPartnerId(String channelPartnerId) {
            ChannelPartnerId = channelPartnerId;
        }

        public String getApplicantFirstName() {
            return ApplicantFirstName;
        }

        public void setApplicantFirstName(String applicantFirstName) {
            ApplicantFirstName = applicantFirstName;
        }

        public String getApplicantMiddleName() {
            return ApplicantMiddleName;
        }

        public void setApplicantMiddleName(String applicantMiddleName) {
            ApplicantMiddleName = applicantMiddleName;
        }

        public String getApplicantLastName() {
            return ApplicantLastName;
        }

        public void setApplicantLastName(String applicantLastName) {
            ApplicantLastName = applicantLastName;
        }

        public String getDateofBirth() {
            return DateofBirth;
        }

        public void setDateofBirth(String dateofBirth) {
            DateofBirth = dateofBirth;
        }

        public String getGender() {
            return Gender;
        }

        public void setGender(String gender) {
            Gender = gender;
        }

        public String getPanNo() {
            return PanNo;
        }

        public void setPanNo(String panNo) {
            PanNo = panNo;
        }

        public String getMaritalStatus() {
            return MaritalStatus;
        }

        public void setMaritalStatus(String maritalStatus) {
            MaritalStatus = maritalStatus;
        }

        public String getResidenceAddress1() {
            return ResidenceAddress1;
        }

        public void setResidenceAddress1(String residenceAddress1) {
            ResidenceAddress1 = residenceAddress1;
        }

        public String getResidenceAddress2() {
            return ResidenceAddress2;
        }

        public void setResidenceAddress2(String residenceAddress2) {
            ResidenceAddress2 = residenceAddress2;
        }

        public String getResidenceAddress3() {
            return ResidenceAddress3;
        }

        public void setResidenceAddress3(String residenceAddress3) {
            ResidenceAddress3 = residenceAddress3;
        }

        public String getResidencePincode() {
            return ResidencePincode;
        }

        public void setResidencePincode(String residencePincode) {
            ResidencePincode = residencePincode;
        }

        public String getResidenceCity() {
            return ResidenceCity;
        }

        public void setResidenceCity(String residenceCity) {
            ResidenceCity = residenceCity;
        }

        public String getResidenceState() {
            return ResidenceState;
        }

        public void setResidenceState(String residenceState) {
            ResidenceState = residenceState;
        }

        public String getResidenceMobileNumber() {
            return ResidenceMobileNumber;
        }

        public void setResidenceMobileNumber(String residenceMobileNumber) {
            ResidenceMobileNumber = residenceMobileNumber;
        }

        public String getAppliedLoanAmount() {
            return AppliedLoanAmount;
        }

        public void setAppliedLoanAmount(String appliedLoanAmount) {
            AppliedLoanAmount = appliedLoanAmount;
        }

        public String getNoOfDependents() {
            return NoOfDependents;
        }

        public void setNoOfDependents(String noOfDependents) {
            NoOfDependents = noOfDependents;
        }

        public String getAdditionalName() {
            return AdditionalName;
        }

        public void setAdditionalName(String additionalName) {
            AdditionalName = additionalName;
        }

        public String getAdditionalNameType() {
            return AdditionalNameType;
        }

        public void setAdditionalNameType(String additionalNameType) {
            AdditionalNameType = additionalNameType;
        }

        public String getAdditionalId1() {
            return AdditionalId1;
        }

        public void setAdditionalId1(String additionalId1) {
            AdditionalId1 = additionalId1;
        }

        public String getAdditionalId2() {
            return AdditionalId2;
        }

        public void setAdditionalId2(String additionalId2) {
            AdditionalId2 = additionalId2;
        }

        public String getAdharCardNo() {
            return AdharCardNo;
        }

        public void setAdharCardNo(String adharCardNo) {
            AdharCardNo = adharCardNo;
        }

        public String getRationCard() {
            return RationCard;
        }

        public void setRationCard(String rationCard) {
            RationCard = rationCard;
        }

        public String getVoterId() {
            return VoterId;
        }

        public void setVoterId(String voterId) {
            VoterId = voterId;
        }

        public String getPassport() {
            return Passport;
        }

        public void setPassport(String passport) {
            Passport = passport;
        }

        public String getPhoneNumber() {
            return PhoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            PhoneNumber = phoneNumber;
        }

        public String getDriverLicense() {
            return DriverLicense;
        }

        public void setDriverLicense(String driverLicense) {
            DriverLicense = driverLicense;
        }

        public String getBranchIDMFI() {
            return BranchIDMFI;
        }

        public void setBranchIDMFI(String branchIDMFI) {
            BranchIDMFI = branchIDMFI;
        }

        public String getKendraIDMFI() {
            return KendraIDMFI;
        }

        public void setKendraIDMFI(String kendraIDMFI) {
            KendraIDMFI = kendraIDMFI;
        }

        public String getPlaceholder1() {
            return Placeholder1;
        }

        public void setPlaceholder1(String placeholder1) {
            Placeholder1 = placeholder1;
        }

        public String getPlaceholder2() {
            return Placeholder2;
        }

        public void setPlaceholder2(String placeholder2) {
            Placeholder2 = placeholder2;
        }

        public String getPlaceholder3() {
            return Placeholder3;
        }

        public void setPlaceholder3(String placeholder3) {
            Placeholder3 = placeholder3;
        }

        public String getPlaceholder4() {
            return Placeholder4;
        }

        public void setPlaceholder4(String placeholder4) {
            Placeholder4 = placeholder4;
        }

        public String getPlaceholder5() {
            return Placeholder5;
        }

        public void setPlaceholder5(String placeholder5) {
            Placeholder5 = placeholder5;
        }

        public String getP1() {
            return P1;
        }

        public void setP1(String p1) {
            P1 = p1;
        }

        public String getP2() {
            return P2;
        }

        public void setP2(String p2) {
            P2 = p2;
        }

        public String getP3() {
            return P3;
        }

        public void setP3(String p3) {
            P3 = p3;
        }

        public String getP4() {
            return P4;
        }

        public void setP4(String p4) {
            P4 = p4;
        }

        public String getP5() {
            return P5;
        }

        public void setP5(String p5) {
            P5 = p5;
        }
    }
}
