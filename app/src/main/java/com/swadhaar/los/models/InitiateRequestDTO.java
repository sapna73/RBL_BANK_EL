package com.swadhaar.los.models;

import com.google.gson.annotations.Expose;

public class InitiateRequestDTO {

    /**
     * UniqueId : 20090910SS26
     * ServiceType : AccountSms
     * ClientID :
     * ExternalCustId :
     * CreatedDate :
     * CreatedBy : JLG
     * RequestString : {"AccountSmsRequest":{"AccountId":"Z00QJD3_01313805","AccountName":"INDU DEEVI","MobileNumber":"9618673063","Loan_Amount":"1","AppName":"","requestId":"","Vpa":"abc@okhdfc","CompanyCode":"TWN","SendSms":"","StaffId":"","Branch_Id":"","Branch_Name":"","Center_Name":"","Group_Name":"","MEMBER_ID":"","MEMBER_Name":"","Loan_Product":"","Loan_Cycle":"","Emi_Amount":"","Total_OutStanding":"","Amount_In_Arrear":"","Alternate_No":"","Date_of_call":"","Member_Family_Health":"","Call_Connected_Status":"","Members_Remarks":"","Last_Call_Date":"","PTP_Date":"","Remarks":"","Created_By":"","Willing_To_Pay_EMI":"","Have_SmartPhone_And_Will_to_Pay_EmI":"","Inst_No":"","Due_Date":"","Digital_Payment_Mode":"","Call_Triggered":"","Loan_Account_No_1":"","Loan_Account_No_2":"","Member_Mobile_Number":"","LAN_1_Flag":"","AmountToBePaid":"","LAN_2_Flag":"","P1":"","P2":"","P3":"","P4":"","P5":"","P6":""}}
     */
    @Expose
    private String UniqueId;
    @Expose
    private String ServiceType;
    @Expose
    private String ClientID;
    @Expose
    private String ExternalCustId;
    @Expose
    private String CreatedDate;
    @Expose
    private String CreatedBy;
    @Expose
    private RequestStringBean RequestString;

    public String getUniqueId() {
        return UniqueId;
    }

    public void setUniqueId(String UniqueId) {
        this.UniqueId = UniqueId;
    }

    public String getServiceType() {
        return ServiceType;
    }

    public void setServiceType(String ServiceType) {
        this.ServiceType = ServiceType;
    }

    public String getClientID() {
        return ClientID;
    }

    public void setClientID(String ClientID) {
        this.ClientID = ClientID;
    }

    public String getExternalCustId() {
        return ExternalCustId;
    }

    public void setExternalCustId(String ExternalCustId) {
        this.ExternalCustId = ExternalCustId;
    }

    public String getCreatedDate() {
        return CreatedDate;
    }

    public void setCreatedDate(String CreatedDate) {
        this.CreatedDate = CreatedDate;
    }

    public String getCreatedBy() {
        return CreatedBy;
    }

    public void setCreatedBy(String CreatedBy) {
        this.CreatedBy = CreatedBy;
    }

    public RequestStringBean getRequestString() {
        return RequestString;
    }

    public void setRequestString(RequestStringBean RequestString) {
        this.RequestString = RequestString;
    }

    public static class RequestStringBean {
        /**
         * AccountSmsRequest : {"AccountId":"Z00QJD3_01313805","AccountName":"INDU DEEVI","MobileNumber":"9618673063","Loan_Amount":"1","AppName":"","requestId":"","Vpa":"abc@okhdfc","CompanyCode":"TWN","SendSms":"","StaffId":"","Branch_Id":"","Branch_Name":"","Center_Name":"","Group_Name":"","MEMBER_ID":"","MEMBER_Name":"","Loan_Product":"","Loan_Cycle":"","Emi_Amount":"","Total_OutStanding":"","Amount_In_Arrear":"","Alternate_No":"","Date_of_call":"","Member_Family_Health":"","Call_Connected_Status":"","Members_Remarks":"","Last_Call_Date":"","PTP_Date":"","Remarks":"","Created_By":"","Willing_To_Pay_EMI":"","Have_SmartPhone_And_Will_to_Pay_EmI":"","Inst_No":"","Due_Date":"","Digital_Payment_Mode":"","Call_Triggered":"","Loan_Account_No_1":"","Loan_Account_No_2":"","Member_Mobile_Number":"","LAN_1_Flag":"","AmountToBePaid":"","LAN_2_Flag":"","P1":"","P2":"","P3":"","P4":"","P5":"","P6":""}
         */

        @Expose
        private AccountSmsRequestBean AccountSmsRequest;

        public AccountSmsRequestBean getAccountSmsRequest() {
            return AccountSmsRequest;
        }

        public void setAccountSmsRequest(AccountSmsRequestBean AccountSmsRequest) {
            this.AccountSmsRequest = AccountSmsRequest;
        }
    }

    public static class AccountSmsRequestBean {
        /**
         * AccountId : Z00QJD3_01313805
         * AccountName : INDU DEEVI
         * MobileNumber : 9618673063
         * Loan_Amount : 1
         * AppName :
         * requestId :
         * Vpa : abc@okhdfc
         * CompanyCode : TWN
         * SendSms :
         * StaffId :
         * Branch_Id :
         * Branch_Name :
         * Center_Name :
         * Group_Name :
         * MEMBER_ID :
         * MEMBER_Name :
         * Loan_Product :
         * Loan_Cycle :
         * Emi_Amount :
         * Total_OutStanding :
         * Amount_In_Arrear :
         * Alternate_No :
         * Date_of_call :
         * Member_Family_Health :
         * Call_Connected_Status :
         * Members_Remarks :
         * Last_Call_Date :
         * PTP_Date :
         * Remarks :
         * Created_By :
         * Willing_To_Pay_EMI :
         * Have_SmartPhone_And_Will_to_Pay_EmI :
         * Inst_No :
         * Due_Date :
         * Digital_Payment_Mode :
         * Call_Triggered :
         * Loan_Account_No_1 :
         * Loan_Account_No_2 :
         * Member_Mobile_Number :
         * LAN_1_Flag :
         * AmountToBePaid :
         * LAN_2_Flag :
         * P1 :
         * P2 :
         * P3 :
         * P4 :
         * P5 :
         * P6 :
         */

        @Expose
        private String AccountId;
        @Expose
        private String AccountName;
        @Expose
        private String MobileNumber;
        @Expose
        private String Loan_Amount;
        @Expose
        private String AppName;
        @Expose
        private String requestId;
        @Expose
        private String Vpa;
        @Expose
        private String CompanyCode;
        @Expose
        private String SendSms;
        @Expose
        private String StaffId;
        @Expose
        private String Branch_Id;
        @Expose
        private String Branch_Name;
        @Expose
        private String Center_Name;
        @Expose
        private String Group_Name;
        @Expose
        private String MEMBER_ID;
        @Expose
        private String MEMBER_Name;
        @Expose
        private String Loan_Product;
        @Expose
        private String Loan_Cycle;
        @Expose
        private String Emi_Amount;
        @Expose
        private String Total_OutStanding;
        @Expose
        private String Amount_In_Arrear;
        @Expose
        private String Alternate_No;
        @Expose
        private String Date_of_call;
        @Expose
        private String Member_Family_Health;
        @Expose
        private String Call_Connected_Status;
        @Expose
        private String Members_Remarks;
        @Expose
        private String Last_Call_Date;
        @Expose
        private String PTP_Date;
        @Expose
        private String Remarks;
        @Expose
        private String Created_By;
        @Expose
        private String Willing_To_Pay_EMI;
        @Expose
        private String Have_SmartPhone_And_Will_to_Pay_EmI;
        @Expose
        private String Inst_No;
        @Expose
        private String Due_Date;
        @Expose
        private String Digital_Payment_Mode;
        @Expose
        private String Call_Triggered;
        @Expose
        private String Loan_Account_No_1;
        @Expose
        private String Loan_Account_No_2;
        @Expose
        private String Member_Mobile_Number;
        @Expose
        private String LAN_1_Flag;
        @Expose
        private String AmountToBePaid;
        @Expose
        private String LAN_2_Flag;
        @Expose
        private String P1;
        @Expose
        private String P2;
        @Expose
        private String P3;
        @Expose
        private String P4;
        @Expose
        private String P5;
        @Expose
        private String P6;

        public String getAccountId() {
            return AccountId;
        }

        public void setAccountId(String AccountId) {
            this.AccountId = AccountId;
        }

        public String getAccountName() {
            return AccountName;
        }

        public void setAccountName(String AccountName) {
            this.AccountName = AccountName;
        }

        public String getMobileNumber() {
            return MobileNumber;
        }

        public void setMobileNumber(String MobileNumber) {
            this.MobileNumber = MobileNumber;
        }

        public String getLoan_Amount() {
            return Loan_Amount;
        }

        public void setLoan_Amount(String Loan_Amount) {
            this.Loan_Amount = Loan_Amount;
        }

        public String getAppName() {
            return AppName;
        }

        public void setAppName(String AppName) {
            this.AppName = AppName;
        }

        public String getRequestId() {
            return requestId;
        }

        public void setRequestId(String requestId) {
            this.requestId = requestId;
        }

        public String getVpa() {
            return Vpa;
        }

        public void setVpa(String Vpa) {
            this.Vpa = Vpa;
        }

        public String getCompanyCode() {
            return CompanyCode;
        }

        public void setCompanyCode(String CompanyCode) {
            this.CompanyCode = CompanyCode;
        }

        public String getSendSms() {
            return SendSms;
        }

        public void setSendSms(String SendSms) {
            this.SendSms = SendSms;
        }

        public String getStaffId() {
            return StaffId;
        }

        public void setStaffId(String StaffId) {
            this.StaffId = StaffId;
        }

        public String getBranch_Id() {
            return Branch_Id;
        }

        public void setBranch_Id(String Branch_Id) {
            this.Branch_Id = Branch_Id;
        }

        public String getBranch_Name() {
            return Branch_Name;
        }

        public void setBranch_Name(String Branch_Name) {
            this.Branch_Name = Branch_Name;
        }

        public String getCenter_Name() {
            return Center_Name;
        }

        public void setCenter_Name(String Center_Name) {
            this.Center_Name = Center_Name;
        }

        public String getGroup_Name() {
            return Group_Name;
        }

        public void setGroup_Name(String Group_Name) {
            this.Group_Name = Group_Name;
        }

        public String getMEMBER_ID() {
            return MEMBER_ID;
        }

        public void setMEMBER_ID(String MEMBER_ID) {
            this.MEMBER_ID = MEMBER_ID;
        }

        public String getMEMBER_Name() {
            return MEMBER_Name;
        }

        public void setMEMBER_Name(String MEMBER_Name) {
            this.MEMBER_Name = MEMBER_Name;
        }

        public String getLoan_Product() {
            return Loan_Product;
        }

        public void setLoan_Product(String Loan_Product) {
            this.Loan_Product = Loan_Product;
        }

        public String getLoan_Cycle() {
            return Loan_Cycle;
        }

        public void setLoan_Cycle(String Loan_Cycle) {
            this.Loan_Cycle = Loan_Cycle;
        }

        public String getEmi_Amount() {
            return Emi_Amount;
        }

        public void setEmi_Amount(String Emi_Amount) {
            this.Emi_Amount = Emi_Amount;
        }

        public String getTotal_OutStanding() {
            return Total_OutStanding;
        }

        public void setTotal_OutStanding(String Total_OutStanding) {
            this.Total_OutStanding = Total_OutStanding;
        }

        public String getAmount_In_Arrear() {
            return Amount_In_Arrear;
        }

        public void setAmount_In_Arrear(String Amount_In_Arrear) {
            this.Amount_In_Arrear = Amount_In_Arrear;
        }

        public String getAlternate_No() {
            return Alternate_No;
        }

        public void setAlternate_No(String Alternate_No) {
            this.Alternate_No = Alternate_No;
        }

        public String getDate_of_call() {
            return Date_of_call;
        }

        public void setDate_of_call(String Date_of_call) {
            this.Date_of_call = Date_of_call;
        }

        public String getMember_Family_Health() {
            return Member_Family_Health;
        }

        public void setMember_Family_Health(String Member_Family_Health) {
            this.Member_Family_Health = Member_Family_Health;
        }

        public String getCall_Connected_Status() {
            return Call_Connected_Status;
        }

        public void setCall_Connected_Status(String Call_Connected_Status) {
            this.Call_Connected_Status = Call_Connected_Status;
        }

        public String getMembers_Remarks() {
            return Members_Remarks;
        }

        public void setMembers_Remarks(String Members_Remarks) {
            this.Members_Remarks = Members_Remarks;
        }

        public String getLast_Call_Date() {
            return Last_Call_Date;
        }

        public void setLast_Call_Date(String Last_Call_Date) {
            this.Last_Call_Date = Last_Call_Date;
        }

        public String getPTP_Date() {
            return PTP_Date;
        }

        public void setPTP_Date(String PTP_Date) {
            this.PTP_Date = PTP_Date;
        }

        public String getRemarks() {
            return Remarks;
        }

        public void setRemarks(String Remarks) {
            this.Remarks = Remarks;
        }

        public String getCreated_By() {
            return Created_By;
        }

        public void setCreated_By(String Created_By) {
            this.Created_By = Created_By;
        }

        public String getWilling_To_Pay_EMI() {
            return Willing_To_Pay_EMI;
        }

        public void setWilling_To_Pay_EMI(String Willing_To_Pay_EMI) {
            this.Willing_To_Pay_EMI = Willing_To_Pay_EMI;
        }

        public String getHave_SmartPhone_And_Will_to_Pay_EmI() {
            return Have_SmartPhone_And_Will_to_Pay_EmI;
        }

        public void setHave_SmartPhone_And_Will_to_Pay_EmI(String Have_SmartPhone_And_Will_to_Pay_EmI) {
            this.Have_SmartPhone_And_Will_to_Pay_EmI = Have_SmartPhone_And_Will_to_Pay_EmI;
        }

        public String getInst_No() {
            return Inst_No;
        }

        public void setInst_No(String Inst_No) {
            this.Inst_No = Inst_No;
        }

        public String getDue_Date() {
            return Due_Date;
        }

        public void setDue_Date(String Due_Date) {
            this.Due_Date = Due_Date;
        }

        public String getDigital_Payment_Mode() {
            return Digital_Payment_Mode;
        }

        public void setDigital_Payment_Mode(String Digital_Payment_Mode) {
            this.Digital_Payment_Mode = Digital_Payment_Mode;
        }

        public String getCall_Triggered() {
            return Call_Triggered;
        }

        public void setCall_Triggered(String Call_Triggered) {
            this.Call_Triggered = Call_Triggered;
        }

        public String getLoan_Account_No_1() {
            return Loan_Account_No_1;
        }

        public void setLoan_Account_No_1(String Loan_Account_No_1) {
            this.Loan_Account_No_1 = Loan_Account_No_1;
        }

        public String getLoan_Account_No_2() {
            return Loan_Account_No_2;
        }

        public void setLoan_Account_No_2(String Loan_Account_No_2) {
            this.Loan_Account_No_2 = Loan_Account_No_2;
        }

        public String getMember_Mobile_Number() {
            return Member_Mobile_Number;
        }

        public void setMember_Mobile_Number(String Member_Mobile_Number) {
            this.Member_Mobile_Number = Member_Mobile_Number;
        }

        public String getLAN_1_Flag() {
            return LAN_1_Flag;
        }

        public void setLAN_1_Flag(String LAN_1_Flag) {
            this.LAN_1_Flag = LAN_1_Flag;
        }

        public String getAmountToBePaid() {
            return AmountToBePaid;
        }

        public void setAmountToBePaid(String AmountToBePaid) {
            this.AmountToBePaid = AmountToBePaid;
        }

        public String getLAN_2_Flag() {
            return LAN_2_Flag;
        }

        public void setLAN_2_Flag(String LAN_2_Flag) {
            this.LAN_2_Flag = LAN_2_Flag;
        }

        public String getP1() {
            return P1;
        }

        public void setP1(String P1) {
            this.P1 = P1;
        }

        public String getP2() {
            return P2;
        }

        public void setP2(String P2) {
            this.P2 = P2;
        }

        public String getP3() {
            return P3;
        }

        public void setP3(String P3) {
            this.P3 = P3;
        }

        public String getP4() {
            return P4;
        }

        public void setP4(String P4) {
            this.P4 = P4;
        }

        public String getP5() {
            return P5;
        }

        public void setP5(String P5) {
            this.P5 = P5;
        }

        public String getP6() {
            return P6;
        }

        public void setP6(String P6) {
            this.P6 = P6;
        }
    }
}
