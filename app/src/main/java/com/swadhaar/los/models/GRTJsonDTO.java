package com.swadhaar.los.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GRTJsonDTO {
    @Expose
    private String CenterName = "";
    @Expose
    private String CenterId = "";
    @Expose
    private String FormationDate = "";
    @Expose
    private int MaximumIndividuals;
    @Expose
    private String MeetingTime = "";
    @Expose
    private String AddressLine1 = "";
    @Expose
    private String AddressLine2 = "";
    @Expose
    private String AddressLine3 = "";
    @Expose
    private String PinCode = "";
    @Expose
    private String BranchCode = "";
    @Expose
    private String PrimaryContact = "";
    @Expose
    private String PhoneNo = "";
    @Expose
    private String SecondaryContact = "";
    @Expose
    private String SecondaryphoneNo = "";
    @Expose
    private String ServicingAgent = "";
    @Expose
    private String OperatingRegionCode = "";


    @SerializedName("groups")
    @Expose
    private List<GroupDetail> groupDetailList;

    public String getCenterName() {
        return CenterName;
    }

    public void setCenterName(String centerName) {
        CenterName = centerName;
    }

    public String getCenterId() {
        return CenterId;
    }

    public void setCenterId(String centerId) {
        CenterId = centerId;
    }

    public String getFormationDate() {
        return FormationDate;
    }

    public void setFormationDate(String formationDate) {
        FormationDate = formationDate;
    }

    public String getPinCode() {
        return PinCode;
    }

    public void setPinCode(String pinCode) {
        PinCode = pinCode;
    }

    public int getMaximumIndividuals() {
        return MaximumIndividuals;
    }

    public void setMaximumIndividuals(int maximumIndividuals) {
        MaximumIndividuals = maximumIndividuals;
    }

    public String getMeetingTime() {
        return MeetingTime;
    }

    public void setMeetingTime(String meetingTime) {
        MeetingTime = meetingTime;
    }

    public String getAddressLine1() {
        return AddressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        AddressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return AddressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        AddressLine2 = addressLine2;
    }

    public String getAddressLine3() {
        return AddressLine3;
    }

    public void setAddressLine3(String addressLine3) {
        AddressLine3 = addressLine3;
    }

    public String getBranchCode() {
        return BranchCode;
    }

    public void setBranchCode(String branchCode) {
        BranchCode = branchCode;
    }

    public String getPrimaryContact() {
        return PrimaryContact;
    }

    public void setPrimaryContact(String primaryContact) {
        PrimaryContact = primaryContact;
    }

    public String getPhoneNo() {
        return PhoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        PhoneNo = phoneNo;
    }

    public String getSecondaryContact() {
        return SecondaryContact;
    }

    public void setSecondaryContact(String secondaryContact) {
        SecondaryContact = secondaryContact;
    }

    public String getSecondaryphoneNo() {
        return SecondaryphoneNo;
    }

    public void setSecondaryphoneNo(String secondaryphoneNo) {
        SecondaryphoneNo = secondaryphoneNo;
    }

    public String getServicingAgent() {
        return ServicingAgent;
    }

    public void setServicingAgent(String servicingAgent) {
        ServicingAgent = servicingAgent;
    }

    public String getOperatingRegionCode() {
        return OperatingRegionCode;
    }

    public void setOperatingRegionCode(String operatingRegionCode) {
        OperatingRegionCode = operatingRegionCode;
    }

    public List<GroupDetail> getGroupDetailList() {
        return groupDetailList;
    }

    public void setGroupDetailList(List<GroupDetail> groupDetailList) {
        this.groupDetailList = groupDetailList;
    }

    public static class GroupDetail {
        @Expose
        private String GroupName = "";
        @Expose
        private String GroupId = "";
        @Expose
        private String CenterName = "";
        @Expose
        private String CenterId = "";
        @Expose
        private String MaximumCenterLimit = "";
        @Expose
        private String GroupType = "";
        @Expose
        private int MinNumber;
        @Expose
        private int MaxNumber;
        @Expose
        private String FormationDate = "";
        @Expose
        private String MeetingTime = "";
        @Expose
        private String MeetingFrequency = "";
        @Expose
        private int DistanceFromBranch;
        @Expose
        private String BranchCode = "";
        @Expose
        private String OperatingRegionCode = "";

        @SerializedName("customers")
        @Expose
        private List<CustomerDetail> customerDetailList;

        public String getGroupName() {
            return GroupName;
        }

        public void setGroupName(String groupName) {
            GroupName = groupName;
        }

        public String getGroupId() {
            return GroupId;
        }

        public void setGroupId(String groupId) {
            GroupId = groupId;
        }

        public String getCenterName() {
            return CenterName;
        }

        public void setCenterName(String centerName) {
            CenterName = centerName;
        }

        public String getCenterId() {
            return CenterId;
        }

        public void setCenterId(String centerId) {
            CenterId = centerId;
        }

        public String getOperatingRegionCode() {
            return OperatingRegionCode;
        }

        public void setOperatingRegionCode(String operatingRegionCode) {
            OperatingRegionCode = operatingRegionCode;
        }

        public String getMaximumCenterLimit() {
            return MaximumCenterLimit;
        }

        public void setMaximumCenterLimit(String maximumCenterLimit) {
            MaximumCenterLimit = maximumCenterLimit;
        }

        public String getGroupType() {
            return GroupType;
        }

        public void setGroupType(String groupType) {
            GroupType = groupType;
        }

        public int getMinNumber() {
            return MinNumber;
        }

        public void setMinNumber(int minNumber) {
            MinNumber = minNumber;
        }

        public int getMaxNumber() {
            return MaxNumber;
        }

        public void setMaxNumber(int maxNumber) {
            MaxNumber = maxNumber;
        }

        public String getFormationDate() {
            return FormationDate;
        }

        public void setFormationDate(String formationDate) {
            FormationDate = formationDate;
        }

        public String getMeetingTime() {
            return MeetingTime;
        }

        public void setMeetingTime(String meetingTime) {
            MeetingTime = meetingTime;
        }

        public String getMeetingFrequency() {
            return MeetingFrequency;
        }

        public void setMeetingFrequency(String meetingFrequency) {
            MeetingFrequency = meetingFrequency;
        }

        public int getDistanceFromBranch() {
            return DistanceFromBranch;
        }

        public void setDistanceFromBranch(int distanceFromBranch) {
            DistanceFromBranch = distanceFromBranch;
        }

        public String getBranchCode() {
            return BranchCode;
        }

        public void setBranchCode(String branchCode) {
            BranchCode = branchCode;
        }

        public List<CustomerDetail> getCustomerDetailList() {
            return customerDetailList;
        }

        public void setCustomerDetailList(List<CustomerDetail> customerDetailList) {
            this.customerDetailList = customerDetailList;
        }
    }

    public static class CustomerDetail {
        @Expose
        private String ClientName = "";
        @Expose
        private String ClientId = "";
        @Expose
        private String CenterId = "";
        @Expose
        private String groupId = "";
        @Expose
        private String AddressLine1 = "";
        @Expose
        private String AddressLine2 = "";
        @Expose
        private String AddressLine3 = "";
        @Expose
        private String pin = "";
        @Expose
        private String DateOfBirth = "";
        @Expose
        private String MobileNo = "";
        @Expose
        private String Caste = "";
        @Expose
        private String Gender = "";
        @Expose
        private String MaritalStatus = "";
        @Expose
        private String Nationality = "";
        @Expose
        private String Religion = "";
        @Expose
        private String MotherTongue = "";
        @Expose
        private String BranchCode = "";
        @Expose
        private String OperatingRegionCode = "";
        @Expose
        private String AadharNo = "";
        @Expose
        private String voterId = "";
        @Expose
        private String BarcodeNo = "";
        @Expose
        private String Health = "";
        @Expose
        private String Occupation = "";
        @Expose
        private String EducationalQualification = "";
        @Expose
        private String Category = "";
        @Expose
        private String Language = "";
        @Expose
        private String BCBranchCode = "";
        @Expose
        private String MinorityCommunity = "";
        @Expose
        private String DisabilityHandicapped = "";
        @Expose
        private String BorrowerType = "";
        @Expose
        private String Collector = "";
        @Expose
        private String Approver = "";
        @Expose
        private String SpouseName = "";
        @Expose
        private String SpouseDOB = "";
        @Expose
        private String NomineeName = "";
        @Expose
        private String NomineeRelationship = "";
        @Expose
        private String CBcheck = "";
        @Expose
        private String CardIssuanceFlag = "";
        @Expose
        private String BANKNAME = "";
        @Expose
        private String BANK_AC_NO = "";
        @Expose
        private String BANK_BRANCH_NAME = "";
        @Expose
        private String WeakerSection = "";
        @Expose
        private String LandHolding = "";
        @Expose
        private int RenewalFlag = 0;
        @Expose
        private int IsExisting = 0;

        @SerializedName("loans")
        @Expose
        private List<LoanDetail> loanDetailList;

        public String getClientName() {
            return ClientName;
        }

        public void setClientName(String clientName) {
            ClientName = clientName;
        }

        public String getClientId() {
            return ClientId;
        }

        public void setClientId(String clientId) {
            ClientId = clientId;
        }

        public String getCenterId() {
            return CenterId;
        }

        public void setCenterId(String centerId) {
            CenterId = centerId;
        }

        public String getGroupId() {
            return groupId;
        }

        public void setGroupId(String groupId) {
            this.groupId = groupId;
        }

        public String getAddressLine1() {
            return AddressLine1;
        }

        public void setAddressLine1(String addressLine1) {
            AddressLine1 = addressLine1;
        }

        public String getAddressLine2() {
            return AddressLine2;
        }

        public void setAddressLine2(String addressLine2) {
            AddressLine2 = addressLine2;
        }

        public String getAddressLine3() {
            return AddressLine3;
        }

        public void setAddressLine3(String addressLine3) {
            AddressLine3 = addressLine3;
        }

        public String getPin() {
            return pin;
        }

        public void setPin(String pin) {
            this.pin = pin;
        }

        public String getDateOfBirth() {
            return DateOfBirth;
        }

        public void setDateOfBirth(String dateOfBirth) {
            DateOfBirth = dateOfBirth;
        }

        public String getMobileNo() {
            return MobileNo;
        }

        public void setMobileNo(String mobileNo) {
            MobileNo = mobileNo;
        }

        public String getCaste() {
            return Caste;
        }

        public void setCaste(String caste) {
            Caste = caste;
        }

        public String getGender() {
            return Gender;
        }

        public void setGender(String gender) {
            Gender = gender;
        }

        public String getMaritalStatus() {
            return MaritalStatus;
        }

        public void setMaritalStatus(String maritalStatus) {
            MaritalStatus = maritalStatus;
        }

        public int getIsExisting() {
            return IsExisting;
        }

        public void setIsExisting(int isExisting) {
            IsExisting = isExisting;
        }

        public String getWeakerSection() {
            return WeakerSection;
        }

        public void setWeakerSection(String weakerSection) {
            WeakerSection = weakerSection;
        }

        public String getLandHolding() {
            return LandHolding;
        }

        public void setLandHolding(String landHolding) {
            LandHolding = landHolding;
        }

        public String getNationality() {
            return Nationality;
        }

        public void setNationality(String nationality) {
            Nationality = nationality;
        }

        public String getReligion() {
            return Religion;
        }

        public void setReligion(String religion) {
            Religion = religion;
        }

        public String getMotherTongue() {
            return MotherTongue;
        }

        public void setMotherTongue(String motherTongue) {
            MotherTongue = motherTongue;
        }

        public String getBranchCode() {
            return BranchCode;
        }

        public void setBranchCode(String branchCode) {
            BranchCode = branchCode;
        }

        public String getOperatingRegionCode() {
            return OperatingRegionCode;
        }

        public String getBarcodeNo() {
            return BarcodeNo;
        }

        public void setBarcodeNo(String barcodeNo) {
            BarcodeNo = barcodeNo;
        }

        public String getCardIssuanceFlag() {
            return CardIssuanceFlag;
        }

        public void setCardIssuanceFlag(String cardIssuanceFlag) {
            CardIssuanceFlag = cardIssuanceFlag;
        }

        public void setOperatingRegionCode(String operatingRegionCode) {
            OperatingRegionCode = operatingRegionCode;
        }

        public String getAadharNo() {
            return AadharNo;
        }

        public void setAadharNo(String aadharNo) {
            AadharNo = aadharNo;
        }

        public String getVoterId() {
            return voterId;
        }

        public void setVoterId(String voterId) {
            this.voterId = voterId;
        }

        public String getHealth() {
            return Health;
        }

        public void setHealth(String health) {
            Health = health;
        }

        public String getOccupation() {
            return Occupation;
        }

        public void setOccupation(String occupation) {
            Occupation = occupation;
        }

        public String getEducationalQualification() {
            return EducationalQualification;
        }

        public void setEducationalQualification(String educationalQualification) {
            EducationalQualification = educationalQualification;
        }

        public String getCategory() {
            return Category;
        }

        public void setCategory(String category) {
            Category = category;
        }

        public String getLanguage() {
            return Language;
        }

        public void setLanguage(String language) {
            Language = language;
        }

        public String getBCBranchCode() {
            return BCBranchCode;
        }

        public void setBCBranchCode(String BCBranchCode) {
            this.BCBranchCode = BCBranchCode;
        }

        public String getMinorityCommunity() {
            return MinorityCommunity;
        }

        public void setMinorityCommunity(String minorityCommunity) {
            MinorityCommunity = minorityCommunity;
        }

        public String getDisabilityHandicapped() {
            return DisabilityHandicapped;
        }

        public void setDisabilityHandicapped(String disabilityHandicapped) {
            DisabilityHandicapped = disabilityHandicapped;
        }

        public String getBorrowerType() {
            return BorrowerType;
        }

        public void setBorrowerType(String borrowerType) {
            BorrowerType = borrowerType;
        }

        public String getCollector() {
            return Collector;
        }

        public void setCollector(String collector) {
            Collector = collector;
        }

        public String getApprover() {
            return Approver;
        }

        public void setApprover(String approver) {
            Approver = approver;
        }

        public String getSpouseName() {
            return SpouseName;
        }

        public void setSpouseName(String spouseName) {
            SpouseName = spouseName;
        }

        public String getSpouseDOB() {
            return SpouseDOB;
        }

        public void setSpouseDOB(String spouseDOB) {
            SpouseDOB = spouseDOB;
        }

        public String getNomineeName() {
            return NomineeName;
        }

        public void setNomineeName(String nomineeName) {
            NomineeName = nomineeName;
        }

        public String getNomineeRelationship() {
            return NomineeRelationship;
        }

        public void setNomineeRelationship(String nomineeRelationship) {
            NomineeRelationship = nomineeRelationship;
        }

        public String getCBcheck() {
            return CBcheck;
        }

        public void setCBcheck(String CBcheck) {
            this.CBcheck = CBcheck;
        }

        public String getBANKNAME() {
            return BANKNAME;
        }

        public void setBANKNAME(String BANKNAME) {
            this.BANKNAME = BANKNAME;
        }

        public String getBANK_AC_NO() {
            return BANK_AC_NO;
        }

        public void setBANK_AC_NO(String BANK_AC_NO) {
            this.BANK_AC_NO = BANK_AC_NO;
        }

        public String getBANK_BRANCH_NAME() {
            return BANK_BRANCH_NAME;
        }

        public void setBANK_BRANCH_NAME(String BANK_BRANCH_NAME) {
            this.BANK_BRANCH_NAME = BANK_BRANCH_NAME;
        }

        public int getRenewalFlag() {
            return RenewalFlag;
        }

        public void setRenewalFlag(int renewalFlag) {
            RenewalFlag = renewalFlag;
        }

        public List<LoanDetail> getLoanDetailList() {
            return loanDetailList;
        }

        public void setLoanDetailList(List<LoanDetail> loanDetailList) {
            this.loanDetailList = loanDetailList;
        }
    }

    public static class LoanDetail {
        @Expose
        private String loanId = "";
        @Expose
        private String ClientId = "";
        @Expose
        private String CenterId = "";
        @Expose
        private String GroupId = "";
        @Expose
        private String LoanProductCode = "";
        @Expose
        private String LoanPurpose = "";
        @Expose
        private int LoanAmount;
        @Expose
        private String DisbursementMode = "";
        @Expose
        private int NoOfInstallments;
        @Expose
        private String RepaymentFrequency = "";
        @Expose
        private String LoanCycle = "";
        @Expose
        private String BarcodeNo = "";
        @Expose
        private String InsuranceFlag = "";
        @Expose
        private String LoanStartDate = "";
        @Expose
        private String RepaymentStartDate = "";
        @Expose
        private String BCBranchCode = "";
        @Expose
        private String ExpectedDisbursalDate = "";
        @Expose
        private String Collector = "";
        @Expose
        private String Approver = "";
        @Expose
        private String Top_upLoan = "0";
        @Expose
        private int HospitalCash;
        @Expose
        private int PrepaidCharges;
        @Expose
        private String NomineeName = "";
        @Expose
        private String Address1 = "";
        @Expose
        private String Address2 = "";
        @Expose
        private String Address3 = "";
        @Expose
        private String Relation = "";
        @Expose
        private String DOB = "";
        @Expose
        private String NomineeAge = "";
        @Expose
        private String Gender = "";
        @Expose
        private String State = "";
        @Expose
        private String City = "";
        @Expose
        private String PinCode = "";
        @Expose
        private String Minor = "";
        @Expose
        private String GuardianTitle = "";
        @Expose
        private String GuardianName = "";
        @Expose
        private String GuardianDOB = "";
        @Expose
        private String GuardianGender = "";
        @Expose
        private String GuardianAddr1 = "";
        @Expose
        private String GuardianRelation = "";
        @Expose
        private String CategoryOfLoan = "";
        @Expose
        private String BankName = "";
        @Expose
        private String IFSCCode = "";
        @Expose
        private String BankAccountNo = "";


        public String getLoanId() {
            return loanId;
        }

        public void setLoanId(String loanId) {
            this.loanId = loanId;
        }

        public String getClientId() {
            return ClientId;
        }

        public void setClientId(String clientId) {
            ClientId = clientId;
        }

        public String getCenterId() {
            return CenterId;
        }

        public void setCenterId(String centerId) {
            CenterId = centerId;
        }

        public String getGroupId() {
            return GroupId;
        }

        public void setGroupId(String groupId) {
            GroupId = groupId;
        }

        public String getLoanProductCode() {
            return LoanProductCode;
        }

        public void setLoanProductCode(String loanProductCode) {
            LoanProductCode = loanProductCode;
        }

        public String getLoanPurpose() {
            return LoanPurpose;
        }

        public void setLoanPurpose(String loanPurpose) {
            LoanPurpose = loanPurpose;
        }

        public int getLoanAmount() {
            return LoanAmount;
        }

        public void setLoanAmount(int loanAmount) {
            LoanAmount = loanAmount;
        }

        public String getDisbursementMode() {
            return DisbursementMode;
        }

        public void setDisbursementMode(String disbursementMode) {
            DisbursementMode = disbursementMode;
        }

        public String getState() {
            return State;
        }

        public void setState(String state) {
            State = state;
        }

        public String getCity() {
            return City;
        }

        public void setCity(String city) {
            City = city;
        }

        public String getBarcodeNo() {
            return BarcodeNo;
        }

        public void setBarcodeNo(String barcodeNo) {
            BarcodeNo = barcodeNo;
        }

        public String getBankName() {
            return BankName;
        }

        public void setBankName(String bankName) {
            BankName = bankName;
        }

        public String getIFSCCode() {
            return IFSCCode;
        }

        public void setIFSCCode(String IFSCCode) {
            this.IFSCCode = IFSCCode;
        }

        public String getBankAccountNo() {
            return BankAccountNo;
        }

        public void setBankAccountNo(String bankAccountNo) {
            BankAccountNo = bankAccountNo;
        }

        public int getNoOfInstallments() {
            return NoOfInstallments;
        }

        public void setNoOfInstallments(int noOfInstallments) {
            NoOfInstallments = noOfInstallments;
        }

        public String getRepaymentFrequency() {
            return RepaymentFrequency;
        }

        public void setRepaymentFrequency(String repaymentFrequency) {
            RepaymentFrequency = repaymentFrequency;
        }

        public String getLoanCycle() {
            return LoanCycle;
        }

        public void setLoanCycle(String loanCycle) {
            LoanCycle = loanCycle;
        }

        public String getLoanStartDate() {
            return LoanStartDate;
        }

        public void setLoanStartDate(String loanStartDate) {
            LoanStartDate = loanStartDate;
        }

        public String getRepaymentStartDate() {
            return RepaymentStartDate;
        }

        public void setRepaymentStartDate(String repaymentStartDate) {
            RepaymentStartDate = repaymentStartDate;
        }

        public String getBCBranchCode() {
            return BCBranchCode;
        }

        public void setBCBranchCode(String BCBranchCode) {
            this.BCBranchCode = BCBranchCode;
        }

        public String getExpectedDisbursalDate() {
            return ExpectedDisbursalDate;
        }

        public void setExpectedDisbursalDate(String expectedDisbursalDate) {
            ExpectedDisbursalDate = expectedDisbursalDate;
        }

        public String getMinor() {
            return Minor;
        }

        public void setMinor(String minor) {
            Minor = minor;
        }

        public String getCollector() {
            return Collector;
        }

        public void setCollector(String collector) {
            Collector = collector;
        }

        public String getApprover() {
            return Approver;
        }

        public void setApprover(String approver) {
            Approver = approver;
        }

        public String getTop_upLoan() {
            return Top_upLoan;
        }

        public void setTop_upLoan(String top_upLoan) {
            Top_upLoan = top_upLoan;
        }

        public int getHospitalCash() {
            return HospitalCash;
        }

        public void setHospitalCash(int hospitalCash) {
            HospitalCash = hospitalCash;
        }

        public int getPrepaidCharges() {
            return PrepaidCharges;
        }

        public void setPrepaidCharges(int prepaidCharges) {
            PrepaidCharges = prepaidCharges;
        }

        public String getNomineeName() {
            return NomineeName;
        }

        public void setNomineeName(String nomineeName) {
            NomineeName = nomineeName;
        }

        public String getAddress1() {
            return Address1;
        }

        public void setAddress1(String address1) {
            Address1 = address1;
        }

        public String getAddress2() {
            return Address2;
        }

        public void setAddress2(String address2) {
            Address2 = address2;
        }

        public String getAddress3() {
            return Address3;
        }

        public void setAddress3(String address3) {
            Address3 = address3;
        }

        public String getRelation() {
            return Relation;
        }

        public void setRelation(String relation) {
            Relation = relation;
        }

        public String getDOB() {
            return DOB;
        }

        public void setDOB(String DOB) {
            this.DOB = DOB;
        }

        public String getNomineeAge() {
            return NomineeAge;
        }

        public void setNomineeAge(String nomineeAge) {
            NomineeAge = nomineeAge;
        }

        public String getGender() {
            return Gender;
        }

        public void setGender(String gender) {
            Gender = gender;
        }

        public String getPinCode() {
            return PinCode;
        }

        public void setPinCode(String pinCode) {
            PinCode = pinCode;
        }

        public String getInsuranceFlag() {
            return InsuranceFlag;
        }

        public void setInsuranceFlag(String insuranceFlag) {
            InsuranceFlag = insuranceFlag;
        }

        public String getGuardianTitle() {
            return GuardianTitle;
        }

        public void setGuardianTitle(String guardianTitle) {
            GuardianTitle = guardianTitle;
        }

        public String getGuardianName() {
            return GuardianName;
        }

        public void setGuardianName(String guardianName) {
            GuardianName = guardianName;
        }

        public String getGuardianDOB() {
            return GuardianDOB;
        }

        public void setGuardianDOB(String guardianDOB) {
            GuardianDOB = guardianDOB;
        }

        public String getGuardianGender() {
            return GuardianGender;
        }

        public void setGuardianGender(String guardianGender) {
            GuardianGender = guardianGender;
        }

        public String getGuardianAddr1() {
            return GuardianAddr1;
        }

        public void setGuardianAddr1(String guardianAddr1) {
            GuardianAddr1 = guardianAddr1;
        }

        public String getGuardianRelation() {
            return GuardianRelation;
        }

        public void setGuardianRelation(String guardianRelation) {
            GuardianRelation = guardianRelation;
        }

        public String getCategoryOfLoan() {
            return CategoryOfLoan;
        }

        public void setCategoryOfLoan(String categoryOfLoan) {
            CategoryOfLoan = categoryOfLoan;
        }
    }
}
