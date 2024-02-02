package com.saartak.el.models.DigitalDocs;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public  class DigitalDocApplicationFormUCRequestDTO {
    @Expose
    @SerializedName("AADHAR")
    private String AADHAR;
    @Expose
    @SerializedName("UniqueId")
    private String UniqueId;
    @Expose
    @SerializedName("ServiceType")
    private String ServiceType;
    @Expose
    @SerializedName("RequestString")
    private RequestString RequestString;
    @Expose
    @SerializedName("KYCId")
    private String KYCId;
    @Expose
    @SerializedName("ExternalCustomerId")
    private String ExternalCustomerId;
    @Expose
    @SerializedName("CreatedDate")
    private String CreatedDate;
    @Expose
    @SerializedName("CreatedByProject")
    private String CreatedByProject;
    @Expose
    @SerializedName("RequestFrom")
    private String RequestFrom="APP";
    @Expose
    @SerializedName("CreatedBy")
    private String CreatedBy;
    @Expose
    @SerializedName("ClientID")
    private String ClientID;

    public void setAADHAR(String AADHAR) {
        this.AADHAR = AADHAR;
    }

    public void setUniqueId(String UniqueId) {
        this.UniqueId = UniqueId;
    }

    public void setServiceType(String ServiceType) {
        this.ServiceType = ServiceType;
    }

    public void setRequestString(RequestString RequestString) {
        this.RequestString = RequestString;
    }

    public void setKYCId(String KYCId) {
        this.KYCId = KYCId;
    }

    public void setExternalCustomerId(String ExternalCustomerId) {
        this.ExternalCustomerId = ExternalCustomerId;
    }

    public void setCreatedDate(String CreatedDate) {
        this.CreatedDate = CreatedDate;
    }

    public void setCreatedByProject(String CreatedByProject) {
        this.CreatedByProject = CreatedByProject;
    }

    public void setCreatedBy(String CreatedBy) {
        this.CreatedBy = CreatedBy;
    }

    public void setClientID(String ClientID) {
        this.ClientID = ClientID;
    }

    public static class RequestString {
        @Expose
        @SerializedName("Requested_Loan_Amount")
        private String Requested_Loan_Amount;
        @Expose
        @SerializedName("LEAD_Generation_Date_DDMMYYYY")
        private String LEAD_Generation_Date_DDMMYYYY;
        @Expose
        @SerializedName("Co_Applicant2_Residence_Address_Line_Pincode")
        private String Co_Applicant2_Residence_Address_Line_Pincode;
        @Expose
        @SerializedName("Co_Applicant2_Residence_Address_Line_Country")
        private String Co_Applicant2_Residence_Address_Line_Country;
        @Expose
        @SerializedName("Co_Applicant2_Residence_Address_Line_State")
        private String Co_Applicant2_Residence_Address_Line_State;
        @Expose
        @SerializedName("Co_Applicant2_Residence_Address_Line_District")
        private String Co_Applicant2_Residence_Address_Line_District;
        @Expose
        @SerializedName("Co_Applicant2_Residence_Address_Line_City")
        private String Co_Applicant2_Residence_Address_Line_City;
        @Expose
        @SerializedName("Co_Applicant2_Residence_Address_Line_2")
        private String Co_Applicant2_Residence_Address_Line_2;
        @Expose
        @SerializedName("Co_Applicant2_Residence_Address_Line_1")
        private String Co_Applicant2_Residence_Address_Line_1;
        @Expose
        @SerializedName("Co_Applicant1_permanent_Address_Line_Country")
        private String Co_Applicant1_permanent_Address_Line_Country;
        @Expose
        @SerializedName("Co_Applicant1_permanent_Address_Line_Pincode")
        private String Co_Applicant1_permanent_Address_Line_Pincode;
        @Expose
        @SerializedName("Co_Applicant1_permanent_Address_Line_State")
        private String Co_Applicant1_permanent_Address_Line_State;
        @Expose
        @SerializedName("Co_Applicant1_permanent_Address_Line_District")
        private String Co_Applicant1_permanent_Address_Line_District;
        @Expose
        @SerializedName("Co_Applicant1_permanent_Address_Line_City")
        private String Co_Applicant1_permanent_Address_Line_City;
        @Expose
        @SerializedName("Co_Applicant1_permanent_Address_Line_2")
        private String Co_Applicant1_permanent_Address_Line_2;
        @Expose
        @SerializedName("Co_Applicant1_permanent_Address_Line_1")
        private String Co_Applicant1_permanent_Address_Line_1;
        @Expose
        @SerializedName("Main_Applicant_permanent_Address_Line_Country")
        private String Main_Applicant_permanent_Address_Line_Country;
        @Expose
        @SerializedName("Main_Applicant_permanent_Address_Line_Pincode")
        private String Main_Applicant_permanent_Address_Line_Pincode;
        @Expose
        @SerializedName("Main_Applicant_permanent_Address_Line_State")
        private String Main_Applicant_permanent_Address_Line_State;
        @Expose
        @SerializedName("Main_Applicant_permanent_Address_Line_District")
        private String Main_Applicant_permanent_Address_Line_District;
        @Expose
        @SerializedName("Main_Applicant_permanent_Address_Line_City")
        private String Main_Applicant_permanent_Address_Line_City;
        @Expose
        @SerializedName("Main_Applicant_permanent_Address_Line_2")
        private String Main_Applicant_permanent_Address_Line_2;
        @Expose
        @SerializedName("Main_Applicant_permanent_Address_Line_1")
        private String Main_Applicant_permanent_Address_Line_1;
        @Expose
        @SerializedName("Reference_2_Contact_Number")
        private String Reference_2_Contact_Number;
        @Expose
        @SerializedName("Reference_2_address")
        private String Reference_2_address;
        @Expose
        @SerializedName("Reference_2_Last_Name")
        private String Reference_2_Last_Name;
        @Expose
        @SerializedName("Reference_2_First_Name")
        private String Reference_2_First_Name;
        @Expose
        @SerializedName("Reference_1_Contact_Number")
        private String Reference_1_Contact_Number;
        @Expose
        @SerializedName("Reference_1_address")
        private String Reference_1_address;
        @Expose
        @SerializedName("Reference_1_Last_Name")
        private String Reference_1_Last_Name;
        @Expose
        @SerializedName("Reference_1_First_Name")
        private String Reference_1_First_Name;
        @Expose
        @SerializedName("Co_Applicant1_Office_Address_Line_Country")
        private String Co_Applicant1_Office_Address_Line_Country;
        @Expose
        @SerializedName("Co_Applicant1_Office_Address_Line_Pincode")
        private String Co_Applicant1_Office_Address_Line_Pincode;
        @Expose
        @SerializedName("Co_Applicant1_Office_Address_Line_State")
        private String Co_Applicant1_Office_Address_Line_State;
        @Expose
        @SerializedName("Co_Applicant1_Office_Address_Line_District")
        private String Co_Applicant1_Office_Address_Line_District;
        @Expose
        @SerializedName("Co_Applicant1_Office_Address_Line_City")
        private String Co_Applicant1_Office_Address_Line_City;
        @Expose
        @SerializedName("Co_Applicant1_Office_Address_Line_2")
        private String Co_Applicant1_Office_Address_Line_2;
        @Expose
        @SerializedName("Co_Applicant1_Office_Address_Line_1")
        private String Co_Applicant1_Office_Address_Line_1;
        @Expose
        @SerializedName("Main_Applicant_Office_Address_Line_Country")
        private String Main_Applicant_Office_Address_Line_Country;
        @Expose
        @SerializedName("Main_Applicant_Office_Address_Line_Pincode")
        private String Main_Applicant_Office_Address_Line_Pincode;
        @Expose
        @SerializedName("Main_Applicant_Office_Address_Line_State")
        private String Main_Applicant_Office_Address_Line_State;
        @Expose
        @SerializedName("Main_Applicant_Office_Address_Line_District")
        private String Main_Applicant_Office_Address_Line_District;
        @Expose
        @SerializedName("Main_Applicant_Office_Address_Line_City")
        private String Main_Applicant_Office_Address_Line_City;
        @Expose
        @SerializedName("Main_Applicant_Office_Address_Line_2")
        private String Main_Applicant_Office_Address_Line_2;
        @Expose
        @SerializedName("Main_Applicant_Office_Address_Line_1")
        private String Main_Applicant_Office_Address_Line_1;
        @Expose
        @SerializedName("Co_Applicant1_Residence_Address_Line_Country")
        private String Co_Applicant1_Residence_Address_Line_Country;
        @Expose
        @SerializedName("Co_Applicant1_Residence_Address_Line_Pincode")
        private String Co_Applicant1_Residence_Address_Line_Pincode;
        @Expose
        @SerializedName("Co_Applicant1_Residence_Address_Line_State")
        private String Co_Applicant1_Residence_Address_Line_State;
        @Expose
        @SerializedName("Co_Applicant1_Residence_Address_Line_District")
        private String Co_Applicant1_Residence_Address_Line_District;
        @Expose
        @SerializedName("Co_Applicant1_Residence_Address_Line_City")
        private String Co_Applicant1_Residence_Address_Line_City;
        @Expose
        @SerializedName("Co_Applicant1_Residence_Address_Line_2")
        private String Co_Applicant1_Residence_Address_Line_2;
        @Expose
        @SerializedName("Co_Applicant1_Residence_Address_Line_1")
        private String Co_Applicant1_Residence_Address_Line_1;
        @Expose
        @SerializedName("Main_Applicant_Residence_Address_Line_Country")
        private String Main_Applicant_Residence_Address_Line_Country;
        @Expose
        @SerializedName("Main_Applicant_Residence_Address_Line_Pincode")
        private String Main_Applicant_Residence_Address_Line_Pincode;
        @Expose
        @SerializedName("Main_Applicant_Residence_Address_Line_State")
        private String Main_Applicant_Residence_Address_Line_State;
        @Expose
        @SerializedName("Main_Applicant_Residence_Address_Line_District")
        private String Main_Applicant_Residence_Address_Line_District;
        @Expose
        @SerializedName("Main_Applicant_Residence_Address_Line_City")
        private String Main_Applicant_Residence_Address_Line_City;
        @Expose
        @SerializedName("Main_Applicant_Residence_Address_Line_2")
        private String Main_Applicant_Residence_Address_Line_2;
        @Expose
        @SerializedName("Main_Applicant_Residence_Address_Line_1")
        private String Main_Applicant_Residence_Address_Line_1;
        @Expose
        @SerializedName("PoA_Identification_Number")
        private String PoA_Identification_Number;
        @Expose
        @SerializedName("PoI_Identification_Number")
        private String PoI_Identification_Number;
        @Expose
        @SerializedName("NREGA_Job_Card")
        private String NREGA_Job_Card;
        @Expose
        @SerializedName("PAN_No")
        private String PAN_No;
        @Expose
        @SerializedName("UID_Aadhaar")
        private String UID_Aadhaar;
        @Expose
        @SerializedName("Driving_License")
        private String Driving_License;
        @Expose
        @SerializedName("Voter_ID_Card")
        private String Voter_ID_Card;
        @Expose
        @SerializedName("Passport_Number")
        private String Passport_Number;
        @Expose
        @SerializedName("Dependent3_Occupation_test")
        private String Dependent3_Occupation_test;
        @Expose
        @SerializedName("Dependent2_Occupation_test")
        private String Dependent2_Occupation_test;
        @Expose
        @SerializedName("Dependent3_age_test")
        private String Dependent3_age_test;
        @Expose
        @SerializedName("Dependent2_age_test")
        private String Dependent2_age_test;
        @Expose
        @SerializedName("Dependent3_relation_test")
        private String Dependent3_relation_test;
        @Expose
        @SerializedName("Dependent2_relation_test")
        private String Dependent2_relation_test;
        @Expose
        @SerializedName("Dependent3_Name_test")
        private String Dependent3_Name_test;
        @Expose
        @SerializedName("Dependent2_Name_test")
        private String Dependent2_Name_test;
        @Expose
        @SerializedName("Dependent1_Occupation_test")
        private String Dependent1_Occupation_test;
        @Expose
        @SerializedName("Dependent1_age_test")
        private String Dependent1_age_test;
        @Expose
        @SerializedName("Dependent1_relation_test")
        private String Dependent1_relation_test;
        @Expose
        @SerializedName("Dependent1_Name_test")
        private String Dependent1_Name_test;
        @Expose
        @SerializedName("Applicant_Mother_Maiden_Name")
        private String Applicant_Mother_Maiden_Name;
        @Expose
        @SerializedName("Applicants_email_id_professional")
        private String Applicants_email_id_professional;
        @Expose
        @SerializedName("Applicants_email_id_personal")
        private String Applicants_email_id_personal;
        @Expose
        @SerializedName("Applicant_Mobile_Number")
        private String Applicant_Mobile_Number;
        @Expose
        @SerializedName("Applicants_Mother_Name")
        private String Applicants_Mother_Name;
        @Expose
        @SerializedName("Applicants_Father_Name")
        private String Applicants_Father_Name;
        @Expose
        @SerializedName("Applicant_Name_As_per_KYC")
        private String Applicant_Name_As_per_KYC;
        @Expose
        @SerializedName("Co_Applicant2_Name_Text")
        private String Co_Applicant2_Name_Text;
        @Expose
        @SerializedName("Co_Applicant1_Name_Text")
        private String Co_Applicant1_Name_Text;
        @Expose
        @SerializedName("Applicant_Name_Text")
        private String Applicant_Name_Text;
        @Expose
        @SerializedName("IFSC_Code")
        private String IFSC_Code;
        @Expose
        @SerializedName("Dealeer_Account_Type")
        private String Dealeer_Account_Type;
        @Expose
        @SerializedName("Dealeer_Account_Number")
        private String Dealeer_Account_Number;
        @Expose
        @SerializedName("Dealeer_Branch_Name")
        private String Dealeer_Branch_Name;
        @Expose
        @SerializedName("Dealeer_Bank_Name")
        private String Dealeer_Bank_Name;
        @Expose
        @SerializedName("Dealeer_Name")
        private String Dealeer_Name;

        public void setRequested_Loan_Amount(String Requested_Loan_Amount) {
            this.Requested_Loan_Amount = Requested_Loan_Amount;
        }

        public void setLEAD_Generation_Date_DDMMYYYY(String LEAD_Generation_Date_DDMMYYYY) {
            this.LEAD_Generation_Date_DDMMYYYY = LEAD_Generation_Date_DDMMYYYY;
        }

        public void setCo_Applicant2_Residence_Address_Line_Pincode(String Co_Applicant2_Residence_Address_Line_Pincode) {
            this.Co_Applicant2_Residence_Address_Line_Pincode = Co_Applicant2_Residence_Address_Line_Pincode;
        }

        public void setCo_Applicant2_Residence_Address_Line_Country(String Co_Applicant2_Residence_Address_Line_Country) {
            this.Co_Applicant2_Residence_Address_Line_Country = Co_Applicant2_Residence_Address_Line_Country;
        }

        public void setCo_Applicant2_Residence_Address_Line_State(String Co_Applicant2_Residence_Address_Line_State) {
            this.Co_Applicant2_Residence_Address_Line_State = Co_Applicant2_Residence_Address_Line_State;
        }

        public void setCo_Applicant2_Residence_Address_Line_District(String Co_Applicant2_Residence_Address_Line_District) {
            this.Co_Applicant2_Residence_Address_Line_District = Co_Applicant2_Residence_Address_Line_District;
        }

        public void setCo_Applicant2_Residence_Address_Line_City(String Co_Applicant2_Residence_Address_Line_City) {
            this.Co_Applicant2_Residence_Address_Line_City = Co_Applicant2_Residence_Address_Line_City;
        }

        public void setCo_Applicant2_Residence_Address_Line_2(String Co_Applicant2_Residence_Address_Line_2) {
            this.Co_Applicant2_Residence_Address_Line_2 = Co_Applicant2_Residence_Address_Line_2;
        }

        public void setCo_Applicant2_Residence_Address_Line_1(String Co_Applicant2_Residence_Address_Line_1) {
            this.Co_Applicant2_Residence_Address_Line_1 = Co_Applicant2_Residence_Address_Line_1;
        }

        public void setCo_Applicant1_permanent_Address_Line_Country(String Co_Applicant1_permanent_Address_Line_Country) {
            this.Co_Applicant1_permanent_Address_Line_Country = Co_Applicant1_permanent_Address_Line_Country;
        }

        public void setCo_Applicant1_permanent_Address_Line_Pincode(String Co_Applicant1_permanent_Address_Line_Pincode) {
            this.Co_Applicant1_permanent_Address_Line_Pincode = Co_Applicant1_permanent_Address_Line_Pincode;
        }

        public void setCo_Applicant1_permanent_Address_Line_State(String Co_Applicant1_permanent_Address_Line_State) {
            this.Co_Applicant1_permanent_Address_Line_State = Co_Applicant1_permanent_Address_Line_State;
        }

        public void setCo_Applicant1_permanent_Address_Line_District(String Co_Applicant1_permanent_Address_Line_District) {
            this.Co_Applicant1_permanent_Address_Line_District = Co_Applicant1_permanent_Address_Line_District;
        }

        public void setCo_Applicant1_permanent_Address_Line_City(String Co_Applicant1_permanent_Address_Line_City) {
            this.Co_Applicant1_permanent_Address_Line_City = Co_Applicant1_permanent_Address_Line_City;
        }

        public void setCo_Applicant1_permanent_Address_Line_2(String Co_Applicant1_permanent_Address_Line_2) {
            this.Co_Applicant1_permanent_Address_Line_2 = Co_Applicant1_permanent_Address_Line_2;
        }

        public void setCo_Applicant1_permanent_Address_Line_1(String Co_Applicant1_permanent_Address_Line_1) {
            this.Co_Applicant1_permanent_Address_Line_1 = Co_Applicant1_permanent_Address_Line_1;
        }

        public void setMain_Applicant_permanent_Address_Line_Country(String Main_Applicant_permanent_Address_Line_Country) {
            this.Main_Applicant_permanent_Address_Line_Country = Main_Applicant_permanent_Address_Line_Country;
        }

        public void setMain_Applicant_permanent_Address_Line_Pincode(String Main_Applicant_permanent_Address_Line_Pincode) {
            this.Main_Applicant_permanent_Address_Line_Pincode = Main_Applicant_permanent_Address_Line_Pincode;
        }

        public void setMain_Applicant_permanent_Address_Line_State(String Main_Applicant_permanent_Address_Line_State) {
            this.Main_Applicant_permanent_Address_Line_State = Main_Applicant_permanent_Address_Line_State;
        }

        public void setMain_Applicant_permanent_Address_Line_District(String Main_Applicant_permanent_Address_Line_District) {
            this.Main_Applicant_permanent_Address_Line_District = Main_Applicant_permanent_Address_Line_District;
        }

        public void setMain_Applicant_permanent_Address_Line_City(String Main_Applicant_permanent_Address_Line_City) {
            this.Main_Applicant_permanent_Address_Line_City = Main_Applicant_permanent_Address_Line_City;
        }

        public void setMain_Applicant_permanent_Address_Line_2(String Main_Applicant_permanent_Address_Line_2) {
            this.Main_Applicant_permanent_Address_Line_2 = Main_Applicant_permanent_Address_Line_2;
        }

        public void setMain_Applicant_permanent_Address_Line_1(String Main_Applicant_permanent_Address_Line_1) {
            this.Main_Applicant_permanent_Address_Line_1 = Main_Applicant_permanent_Address_Line_1;
        }

        public void setReference_2_Contact_Number(String Reference_2_Contact_Number) {
            this.Reference_2_Contact_Number = Reference_2_Contact_Number;
        }

        public void setReference_2_address(String Reference_2_address) {
            this.Reference_2_address = Reference_2_address;
        }

        public void setReference_2_Last_Name(String Reference_2_Last_Name) {
            this.Reference_2_Last_Name = Reference_2_Last_Name;
        }

        public void setReference_2_First_Name(String Reference_2_First_Name) {
            this.Reference_2_First_Name = Reference_2_First_Name;
        }

        public void setReference_1_Contact_Number(String Reference_1_Contact_Number) {
            this.Reference_1_Contact_Number = Reference_1_Contact_Number;
        }

        public void setReference_1_address(String Reference_1_address) {
            this.Reference_1_address = Reference_1_address;
        }

        public void setReference_1_Last_Name(String Reference_1_Last_Name) {
            this.Reference_1_Last_Name = Reference_1_Last_Name;
        }

        public void setReference_1_First_Name(String Reference_1_First_Name) {
            this.Reference_1_First_Name = Reference_1_First_Name;
        }

        public void setCo_Applicant1_Office_Address_Line_Country(String Co_Applicant1_Office_Address_Line_Country) {
            this.Co_Applicant1_Office_Address_Line_Country = Co_Applicant1_Office_Address_Line_Country;
        }

        public void setCo_Applicant1_Office_Address_Line_Pincode(String Co_Applicant1_Office_Address_Line_Pincode) {
            this.Co_Applicant1_Office_Address_Line_Pincode = Co_Applicant1_Office_Address_Line_Pincode;
        }

        public void setCo_Applicant1_Office_Address_Line_State(String Co_Applicant1_Office_Address_Line_State) {
            this.Co_Applicant1_Office_Address_Line_State = Co_Applicant1_Office_Address_Line_State;
        }

        public void setCo_Applicant1_Office_Address_Line_District(String Co_Applicant1_Office_Address_Line_District) {
            this.Co_Applicant1_Office_Address_Line_District = Co_Applicant1_Office_Address_Line_District;
        }

        public void setCo_Applicant1_Office_Address_Line_City(String Co_Applicant1_Office_Address_Line_City) {
            this.Co_Applicant1_Office_Address_Line_City = Co_Applicant1_Office_Address_Line_City;
        }

        public void setCo_Applicant1_Office_Address_Line_2(String Co_Applicant1_Office_Address_Line_2) {
            this.Co_Applicant1_Office_Address_Line_2 = Co_Applicant1_Office_Address_Line_2;
        }

        public void setCo_Applicant1_Office_Address_Line_1(String Co_Applicant1_Office_Address_Line_1) {
            this.Co_Applicant1_Office_Address_Line_1 = Co_Applicant1_Office_Address_Line_1;
        }

        public void setMain_Applicant_Office_Address_Line_Country(String Main_Applicant_Office_Address_Line_Country) {
            this.Main_Applicant_Office_Address_Line_Country = Main_Applicant_Office_Address_Line_Country;
        }

        public void setMain_Applicant_Office_Address_Line_Pincode(String Main_Applicant_Office_Address_Line_Pincode) {
            this.Main_Applicant_Office_Address_Line_Pincode = Main_Applicant_Office_Address_Line_Pincode;
        }

        public void setMain_Applicant_Office_Address_Line_State(String Main_Applicant_Office_Address_Line_State) {
            this.Main_Applicant_Office_Address_Line_State = Main_Applicant_Office_Address_Line_State;
        }

        public void setMain_Applicant_Office_Address_Line_District(String Main_Applicant_Office_Address_Line_District) {
            this.Main_Applicant_Office_Address_Line_District = Main_Applicant_Office_Address_Line_District;
        }

        public void setMain_Applicant_Office_Address_Line_City(String Main_Applicant_Office_Address_Line_City) {
            this.Main_Applicant_Office_Address_Line_City = Main_Applicant_Office_Address_Line_City;
        }

        public void setMain_Applicant_Office_Address_Line_2(String Main_Applicant_Office_Address_Line_2) {
            this.Main_Applicant_Office_Address_Line_2 = Main_Applicant_Office_Address_Line_2;
        }

        public void setMain_Applicant_Office_Address_Line_1(String Main_Applicant_Office_Address_Line_1) {
            this.Main_Applicant_Office_Address_Line_1 = Main_Applicant_Office_Address_Line_1;
        }

        public void setCo_Applicant1_Residence_Address_Line_Country(String Co_Applicant1_Residence_Address_Line_Country) {
            this.Co_Applicant1_Residence_Address_Line_Country = Co_Applicant1_Residence_Address_Line_Country;
        }

        public void setCo_Applicant1_Residence_Address_Line_Pincode(String Co_Applicant1_Residence_Address_Line_Pincode) {
            this.Co_Applicant1_Residence_Address_Line_Pincode = Co_Applicant1_Residence_Address_Line_Pincode;
        }

        public void setCo_Applicant1_Residence_Address_Line_State(String Co_Applicant1_Residence_Address_Line_State) {
            this.Co_Applicant1_Residence_Address_Line_State = Co_Applicant1_Residence_Address_Line_State;
        }

        public void setCo_Applicant1_Residence_Address_Line_District(String Co_Applicant1_Residence_Address_Line_District) {
            this.Co_Applicant1_Residence_Address_Line_District = Co_Applicant1_Residence_Address_Line_District;
        }

        public void setCo_Applicant1_Residence_Address_Line_City(String Co_Applicant1_Residence_Address_Line_City) {
            this.Co_Applicant1_Residence_Address_Line_City = Co_Applicant1_Residence_Address_Line_City;
        }

        public void setCo_Applicant1_Residence_Address_Line_2(String Co_Applicant1_Residence_Address_Line_2) {
            this.Co_Applicant1_Residence_Address_Line_2 = Co_Applicant1_Residence_Address_Line_2;
        }

        public void setCo_Applicant1_Residence_Address_Line_1(String Co_Applicant1_Residence_Address_Line_1) {
            this.Co_Applicant1_Residence_Address_Line_1 = Co_Applicant1_Residence_Address_Line_1;
        }

        public void setMain_Applicant_Residence_Address_Line_Country(String Main_Applicant_Residence_Address_Line_Country) {
            this.Main_Applicant_Residence_Address_Line_Country = Main_Applicant_Residence_Address_Line_Country;
        }

        public void setMain_Applicant_Residence_Address_Line_Pincode(String Main_Applicant_Residence_Address_Line_Pincode) {
            this.Main_Applicant_Residence_Address_Line_Pincode = Main_Applicant_Residence_Address_Line_Pincode;
        }

        public void setMain_Applicant_Residence_Address_Line_State(String Main_Applicant_Residence_Address_Line_State) {
            this.Main_Applicant_Residence_Address_Line_State = Main_Applicant_Residence_Address_Line_State;
        }

        public void setMain_Applicant_Residence_Address_Line_District(String Main_Applicant_Residence_Address_Line_District) {
            this.Main_Applicant_Residence_Address_Line_District = Main_Applicant_Residence_Address_Line_District;
        }

        public void setMain_Applicant_Residence_Address_Line_City(String Main_Applicant_Residence_Address_Line_City) {
            this.Main_Applicant_Residence_Address_Line_City = Main_Applicant_Residence_Address_Line_City;
        }

        public void setMain_Applicant_Residence_Address_Line_2(String Main_Applicant_Residence_Address_Line_2) {
            this.Main_Applicant_Residence_Address_Line_2 = Main_Applicant_Residence_Address_Line_2;
        }

        public void setMain_Applicant_Residence_Address_Line_1(String Main_Applicant_Residence_Address_Line_1) {
            this.Main_Applicant_Residence_Address_Line_1 = Main_Applicant_Residence_Address_Line_1;
        }

        public void setPoA_Identification_Number(String PoA_Identification_Number) {
            this.PoA_Identification_Number = PoA_Identification_Number;
        }

        public void setPoI_Identification_Number(String PoI_Identification_Number) {
            this.PoI_Identification_Number = PoI_Identification_Number;
        }

        public void setNREGA_Job_Card(String NREGA_Job_Card) {
            this.NREGA_Job_Card = NREGA_Job_Card;
        }

        public void setPAN_No(String PAN_No) {
            this.PAN_No = PAN_No;
        }

        public void setUID_Aadhaar(String UID_Aadhaar) {
            this.UID_Aadhaar = UID_Aadhaar;
        }

        public void setDriving_License(String Driving_License) {
            this.Driving_License = Driving_License;
        }

        public void setVoter_ID_Card(String Voter_ID_Card) {
            this.Voter_ID_Card = Voter_ID_Card;
        }

        public void setPassport_Number(String Passport_Number) {
            this.Passport_Number = Passport_Number;
        }

        public void setDependent3_Occupation_test(String Dependent3_Occupation_test) {
            this.Dependent3_Occupation_test = Dependent3_Occupation_test;
        }

        public void setDependent2_Occupation_test(String Dependent2_Occupation_test) {
            this.Dependent2_Occupation_test = Dependent2_Occupation_test;
        }

        public void setDependent3_age_test(String Dependent3_age_test) {
            this.Dependent3_age_test = Dependent3_age_test;
        }

        public void setDependent2_age_test(String Dependent2_age_test) {
            this.Dependent2_age_test = Dependent2_age_test;
        }

        public void setDependent3_relation_test(String Dependent3_relation_test) {
            this.Dependent3_relation_test = Dependent3_relation_test;
        }

        public void setDependent2_relation_test(String Dependent2_relation_test) {
            this.Dependent2_relation_test = Dependent2_relation_test;
        }

        public void setDependent3_Name_test(String Dependent3_Name_test) {
            this.Dependent3_Name_test = Dependent3_Name_test;
        }

        public void setDependent2_Name_test(String Dependent2_Name_test) {
            this.Dependent2_Name_test = Dependent2_Name_test;
        }

        public void setDependent1_Occupation_test(String Dependent1_Occupation_test) {
            this.Dependent1_Occupation_test = Dependent1_Occupation_test;
        }

        public void setDependent1_age_test(String Dependent1_age_test) {
            this.Dependent1_age_test = Dependent1_age_test;
        }

        public void setDependent1_relation_test(String Dependent1_relation_test) {
            this.Dependent1_relation_test = Dependent1_relation_test;
        }

        public void setDependent1_Name_test(String Dependent1_Name_test) {
            this.Dependent1_Name_test = Dependent1_Name_test;
        }

        public void setApplicant_Mother_Maiden_Name(String Applicant_Mother_Maiden_Name) {
            this.Applicant_Mother_Maiden_Name = Applicant_Mother_Maiden_Name;
        }

        public void setApplicants_email_id_professional(String Applicants_email_id_professional) {
            this.Applicants_email_id_professional = Applicants_email_id_professional;
        }

        public void setApplicants_email_id_personal(String Applicants_email_id_personal) {
            this.Applicants_email_id_personal = Applicants_email_id_personal;
        }

        public void setApplicant_Mobile_Number(String Applicant_Mobile_Number) {
            this.Applicant_Mobile_Number = Applicant_Mobile_Number;
        }

        public void setApplicants_Mother_Name(String Applicants_Mother_Name) {
            this.Applicants_Mother_Name = Applicants_Mother_Name;
        }

        public void setApplicants_Father_Name(String Applicants_Father_Name) {
            this.Applicants_Father_Name = Applicants_Father_Name;
        }

        public void setApplicant_Name_As_per_KYC(String Applicant_Name_As_per_KYC) {
            this.Applicant_Name_As_per_KYC = Applicant_Name_As_per_KYC;
        }

        public void setCo_Applicant2_Name_Text(String Co_Applicant2_Name_Text) {
            this.Co_Applicant2_Name_Text = Co_Applicant2_Name_Text;
        }

        public void setCo_Applicant1_Name_Text(String Co_Applicant1_Name_Text) {
            this.Co_Applicant1_Name_Text = Co_Applicant1_Name_Text;
        }

        public void setApplicant_Name_Text(String Applicant_Name_Text) {
            this.Applicant_Name_Text = Applicant_Name_Text;
        }

        public void setIFSC_Code(String IFSC_Code) {
            this.IFSC_Code = IFSC_Code;
        }

        public void setDealeer_Account_Type(String Dealeer_Account_Type) {
            this.Dealeer_Account_Type = Dealeer_Account_Type;
        }

        public void setDealeer_Account_Number(String Dealeer_Account_Number) {
            this.Dealeer_Account_Number = Dealeer_Account_Number;
        }

        public void setDealeer_Branch_Name(String Dealeer_Branch_Name) {
            this.Dealeer_Branch_Name = Dealeer_Branch_Name;
        }

        public void setDealeer_Bank_Name(String Dealeer_Bank_Name) {
            this.Dealeer_Bank_Name = Dealeer_Bank_Name;
        }

        public void setDealeer_Name(String Dealeer_Name) {
            this.Dealeer_Name = Dealeer_Name;
        }
    }
}
