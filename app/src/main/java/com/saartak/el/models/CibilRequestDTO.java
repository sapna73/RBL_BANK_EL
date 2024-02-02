package com.saartak.el.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CibilRequestDTO {

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
    @SerializedName("RequestFrom")
    private String RequestFrom="APP";
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
    public void setRequestFrom(String requestFrom) {
        RequestFrom = requestFrom;
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
        private cibilEnquiryClass cibilEnquiry;

        public cibilEnquiryClass getCibilEnquiry() {
            return cibilEnquiry;
        }

        public void setCibilEnquiry(cibilEnquiryClass cibilEnquiry) {
            this.cibilEnquiry = cibilEnquiry;
        }
    }

    public static class cibilEnquiryClass {
        @Expose
        private String ModuleType = "";
        @Expose
        private String ProductCode = "MSME";
        @Expose
        private String ChannelType = "Sarthak";
        @Expose
        private String InquirySource = "Sarthak";
        @Expose
        private String Title = "";
        @Expose
        private String ApplicantFirstName = "";
        @Expose
        private String ApplicantMiddleName = "";
        @Expose
        private String ApplicantLastName = "";
        @Expose
        private String DateofBirth = "";
        @Expose
        private String Gender = "";
        @Expose
        private String PanNo = "";
        @Expose
        private String EmailID = "";
        @Expose
        private String ResidenceAddress1 = "";
        @Expose
        private String ResidenceAddress2 = "";
        @Expose
        private String ResidenceAddress3 = "";
        @Expose
        private String ResidencePincode = "";
        @Expose
        private String ResidenceCity = "";
        @Expose
        private String ResidenceState = "";
        @Expose
        private String ResidenceSTDCode = "";
        @Expose
        private String ResidencePhone = "";
        @Expose
        private String ResidenceMobileNumber = "";
        @Expose
        private String OfficeAddress1 = "";
        @Expose
        private String OfficeAddress2 = "";
        @Expose
        private String OfficeAddress3 = "";
        @Expose
        private String OfficePincode = "";
        @Expose
        private String OfficeCity = "";
        @Expose
        private String OfficeState = "";
        @Expose
        private String OfficePhone = "";
        @Expose
        private String OfficefMobileNumber = "";
        @Expose
        private String PassportNumber = "";
        @Expose
        private String DLNo = "";
        @Expose
        private String VoterId = "";
        @Expose
        private String UId = "";
        @Expose
        private String RationCardNo = "";
        @Expose
        private String MonthlyIncome = "";
        @Expose
        private String Residence_stability = "";
        @Expose
        private String Employment_stability = "";
        @Expose
        private String Loan_Account_1 = "";
        @Expose
        private String Loan_Account_2 = "";
        @Expose
        private String Loan_Account_3 = "";
        @Expose
        private String Loan_Account_4 = "";
        @Expose
        private String Loan_Account_5 = "";
        @Expose
        private String Placeholder6 = "";
        @Expose
        private String Placeholder7 = "";
        @Expose
        private String Placeholder8 = "";
        @Expose
        private String Placeholder9 = "";
        @Expose
        private String Placeholder10 = "APP"; // TODO: Hardcoded value to check request tvName mobile app or web

        public String getModuleType() {
            return ModuleType;
        }

        public void setModuleType(String moduleType) {
            ModuleType = moduleType;
        }

        public String getProductCode() {
            return ProductCode;
        }

        public void setProductCode(String productCode) {
            ProductCode = productCode;
        }

        public String getChannelType() {
            return ChannelType;
        }

        public void setChannelType(String channelType) {
            ChannelType = channelType;
        }

        public String getTitle() {
            return Title;
        }

        public void setTitle(String title) {
            Title = title;
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

        public String getEmailID() {
            return EmailID;
        }

        public void setEmailID(String emailID) {
            EmailID = emailID;
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

        public String getResidenceSTDCode() {
            return ResidenceSTDCode;
        }

        public void setResidenceSTDCode(String residenceSTDCode) {
            ResidenceSTDCode = residenceSTDCode;
        }

        public String getResidencePhone() {
            return ResidencePhone;
        }

        public void setResidencePhone(String residencePhone) {
            ResidencePhone = residencePhone;
        }

        public String getResidenceMobileNumber() {
            return ResidenceMobileNumber;
        }

        public void setResidenceMobileNumber(String residenceMobileNumber) {
            ResidenceMobileNumber = residenceMobileNumber;
        }

        public String getOfficeAddress1() {
            return OfficeAddress1;
        }

        public void setOfficeAddress1(String officeAddress1) {
            OfficeAddress1 = officeAddress1;
        }

        public String getOfficeAddress2() {
            return OfficeAddress2;
        }

        public void setOfficeAddress2(String officeAddress2) {
            OfficeAddress2 = officeAddress2;
        }

        public String getOfficeAddress3() {
            return OfficeAddress3;
        }

        public void setOfficeAddress3(String officeAddress3) {
            OfficeAddress3 = officeAddress3;
        }

        public String getOfficePincode() {
            return OfficePincode;
        }

        public void setOfficePincode(String officePincode) {
            OfficePincode = officePincode;
        }

        public String getOfficeCity() {
            return OfficeCity;
        }

        public void setOfficeCity(String officeCity) {
            OfficeCity = officeCity;
        }

        public String getOfficeState() {
            return OfficeState;
        }

        public void setOfficeState(String officeState) {
            OfficeState = officeState;
        }

        public String getOfficePhone() {
            return OfficePhone;
        }

        public void setOfficePhone(String officePhone) {
            OfficePhone = officePhone;
        }

        public String getOfficefMobileNumber() {
            return OfficefMobileNumber;
        }

        public void setOfficefMobileNumber(String officefMobileNumber) {
            OfficefMobileNumber = officefMobileNumber;
        }

        public String getPassportNumber() {
            return PassportNumber;
        }

        public void setPassportNumber(String passportNumber) {
            PassportNumber = passportNumber;
        }

        public String getDLNo() {
            return DLNo;
        }

        public void setDLNo(String DLNo) {
            this.DLNo = DLNo;
        }

        public String getVoterId() {
            return VoterId;
        }

        public void setVoterId(String voterId) {
            VoterId = voterId;
        }

        public String getUId() {
            return UId;
        }

        public void setUId(String UId) {
            this.UId = UId;
        }

        public String getRationCardNo() {
            return RationCardNo;
        }

        public void setRationCardNo(String rationCardNo) {
            RationCardNo = rationCardNo;
        }

        public String getMonthlyIncome() {
            return MonthlyIncome;
        }

        public void setMonthlyIncome(String monthlyIncome) {
            MonthlyIncome = monthlyIncome;
        }

        public String getResidence_stability() {
            return Residence_stability;
        }

        public void setResidence_stability(String residence_stability) {
            Residence_stability = residence_stability;
        }


        public String getInquirySource() {
            return InquirySource;
        }

        public void setInquirySource(String inquirySource) {
            InquirySource = inquirySource;
        }

        public String getEmployment_stability() {
            return Employment_stability;
        }

        public void setEmployment_stability(String employment_stability) {
            Employment_stability = employment_stability;
        }

        public String getLoan_Account_1() {
            return Loan_Account_1;
        }

        public void setLoan_Account_1(String loan_Account_1) {
            Loan_Account_1 = loan_Account_1;
        }

        public String getLoan_Account_2() {
            return Loan_Account_2;
        }

        public void setLoan_Account_2(String loan_Account_2) {
            Loan_Account_2 = loan_Account_2;
        }

        public String getLoan_Account_3() {
            return Loan_Account_3;
        }

        public void setLoan_Account_3(String loan_Account_3) {
            Loan_Account_3 = loan_Account_3;
        }

        public String getLoan_Account_4() {
            return Loan_Account_4;
        }

        public void setLoan_Account_4(String loan_Account_4) {
            Loan_Account_4 = loan_Account_4;
        }

        public String getLoan_Account_5() {
            return Loan_Account_5;
        }

        public void setLoan_Account_5(String loan_Account_5) {
            Loan_Account_5 = loan_Account_5;
        }

        public String getPlaceholder6() {
            return Placeholder6;
        }

        public void setPlaceholder6(String placeholder6) {
            Placeholder6 = placeholder6;
        }

        public String getPlaceholder7() {
            return Placeholder7;
        }

        public void setPlaceholder7(String placeholder7) {
            Placeholder7 = placeholder7;
        }

        public String getPlaceholder8() {
            return Placeholder8;
        }

        public void setPlaceholder8(String placeholder8) {
            Placeholder8 = placeholder8;
        }

        public String getPlaceholder9() {
            return Placeholder9;
        }

        public void setPlaceholder9(String placeholder9) {
            Placeholder9 = placeholder9;
        }

        public String getPlaceholder10() {
            return Placeholder10;
        }

        public void setPlaceholder10(String placeholder10) {
            Placeholder10 = placeholder10;
        }
    }

}