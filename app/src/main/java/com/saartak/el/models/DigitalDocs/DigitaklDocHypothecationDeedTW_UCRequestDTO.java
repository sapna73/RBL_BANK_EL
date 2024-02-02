package com.saartak.el.models.DigitalDocs;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public  class DigitaklDocHypothecationDeedTW_UCRequestDTO {
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
        @SerializedName("Registration number from collateral screen")
        private String RegistrationNumberFromCollateralScreen;
        @Expose
        @SerializedName("Model")
        private String Model;
        @Expose
        @SerializedName("Make")
        private String Make;
        @Expose
        @SerializedName("Sanctioned Amount")
        private String SanctionedAmount;
        @Expose
        @SerializedName("Co_Applicant_2_Address_Details_3")
        private String Co_Applicant_2_Address_Details_3;
        @Expose
        @SerializedName("Co_Applicant_2_Address_Details_2")
        private String Co_Applicant_2_Address_Details_2;
        @Expose
        @SerializedName("Co_Applicant_2_Address_Details_1")
        private String Co_Applicant_2_Address_Details_1;
        @Expose
        @SerializedName("Co_Applicant_1_Address_Details_3")
        private String Co_Applicant_1_Address_Details_3;
        @Expose
        @SerializedName("Co_Applicant_1_Address_Details_2")
        private String Co_Applicant_1_Address_Details_2;
        @Expose
        @SerializedName("Co_Applicant_1_Address_Details_1")
        private String Co_Applicant_1_Address_Details_1;
        @Expose
        @SerializedName("Applicant_Address_Details_3")
        private String Applicant_Address_Details_3;
        @Expose
        @SerializedName("Applicant_Address_Details_2")
        private String Applicant_Address_Details_2;
        @Expose
        @SerializedName("Applicant_Address_Details_1")
        private String Applicant_Address_Details_1;
        @Expose
        @SerializedName("Co-Applicant 2 Name_Text")
        private String CoApplicant2NameText;
        @Expose
        @SerializedName("Co-Applicant 1 Name_Text")
        private String CoApplicant1NameText;
        @Expose
        @SerializedName("Applicant Name_Text")
        private String ApplicantNameText;
        @Expose
        @SerializedName("LOS ID")
        private String LOSID;
        @Expose
        @SerializedName("Application submitted Date_DDMMYYYY")
        private String ApplicationSubmittedDateDDMMYYYY;
        @Expose
        @SerializedName("RBL Sanction Date_DDMMYYYY")
        private String RBLSanctionDateDDMMYYYY;

        public void setRegistrationNumberFromCollateralScreen(String registrationNumberFromCollateralScreen) {
            RegistrationNumberFromCollateralScreen = registrationNumberFromCollateralScreen;
        }

        public void setModel(String model) {
            Model = model;
        }

        public void setMake(String make) {
            Make = make;
        }

        public void setSanctionedAmount(String sanctionedAmount) {
            SanctionedAmount = sanctionedAmount;
        }

        public void setCo_Applicant_2_Address_Details_3(String co_Applicant_2_Address_Details_3) {
            Co_Applicant_2_Address_Details_3 = co_Applicant_2_Address_Details_3;
        }

        public void setCo_Applicant_2_Address_Details_2(String co_Applicant_2_Address_Details_2) {
            Co_Applicant_2_Address_Details_2 = co_Applicant_2_Address_Details_2;
        }

        public void setCo_Applicant_2_Address_Details_1(String co_Applicant_2_Address_Details_1) {
            Co_Applicant_2_Address_Details_1 = co_Applicant_2_Address_Details_1;
        }

        public void setCo_Applicant_1_Address_Details_3(String co_Applicant_1_Address_Details_3) {
            Co_Applicant_1_Address_Details_3 = co_Applicant_1_Address_Details_3;
        }

        public void setCo_Applicant_1_Address_Details_2(String co_Applicant_1_Address_Details_2) {
            Co_Applicant_1_Address_Details_2 = co_Applicant_1_Address_Details_2;
        }

        public void setCo_Applicant_1_Address_Details_1(String co_Applicant_1_Address_Details_1) {
            Co_Applicant_1_Address_Details_1 = co_Applicant_1_Address_Details_1;
        }

        public void setApplicant_Address_Details_3(String applicant_Address_Details_3) {
            Applicant_Address_Details_3 = applicant_Address_Details_3;
        }

        public void setApplicant_Address_Details_2(String applicant_Address_Details_2) {
            Applicant_Address_Details_2 = applicant_Address_Details_2;
        }

        public void setApplicant_Address_Details_1(String applicant_Address_Details_1) {
            Applicant_Address_Details_1 = applicant_Address_Details_1;
        }

        public void setCoApplicant2NameText(String coApplicant2NameText) {
            CoApplicant2NameText = coApplicant2NameText;
        }

        public void setCoApplicant1NameText(String coApplicant1NameText) {
            CoApplicant1NameText = coApplicant1NameText;
        }

        public void setApplicantNameText(String applicantNameText) {
            ApplicantNameText = applicantNameText;
        }

        public void setLOSID(String LOSID) {
            this.LOSID = LOSID;
        }

        public void setApplicationSubmittedDateDDMMYYYY(String applicationSubmittedDateDDMMYYYY) {
            ApplicationSubmittedDateDDMMYYYY = applicationSubmittedDateDDMMYYYY;
        }

        public void setRBLSanctionDateDDMMYYYY(String RBLSanctionDateDDMMYYYY) {
            this.RBLSanctionDateDDMMYYYY = RBLSanctionDateDDMMYYYY;
        }
    }
}
