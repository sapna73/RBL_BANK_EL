package com.saartak.el.models.DigitalDocs;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DigitalDocRequestSanctionLetterRuralDTO {
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

    public void setUniqueId(String uniqueId) {
        UniqueId = uniqueId;
    }

    public void setServiceType(String serviceType) {
        ServiceType = serviceType;
    }

    public void setRequestString(DigitalDocRequestSanctionLetterRuralDTO.RequestString requestString) {
        RequestString = requestString;
    }

    public void setKYCId(String KYCId) {
        this.KYCId = KYCId;
    }

    public void setExternalCustomerId(String externalCustomerId) {
        ExternalCustomerId = externalCustomerId;
    }

    public void setCreatedDate(String createdDate) {
        CreatedDate = createdDate;
    }

    public void setCreatedByProject(String createdByProject) {
        CreatedByProject = createdByProject;
    }

    public void setCreatedBy(String createdBy) {
        CreatedBy = createdBy;
    }

    public void setClientID(String clientID) {
        ClientID = clientID;
    }

    public static class RequestString {
        @Expose
        @SerializedName("Bydefault850+GSTifincaseofUsedtractoronly,elseitshouldbeZero")
        private String elseitshouldbeZero;
        @Expose
        @SerializedName("InsuranceandCharges_TractorORVehicalInsurance")
        private String InsuranceandCharges_TractorORVehicalInsurance;
        @Expose
        @SerializedName("NamefromCoApplicant2KYC")
        private String NamefromCoApplicant2KYC;
        @Expose
        @SerializedName("NamefromCoApplicantKYC")
        private String NamefromCoApplicantKYC;
        @Expose
        @SerializedName("StampdutyamountbasedonDealerState")
        private String StampdutyamountbasedonDealerState;
        @Expose
        @SerializedName("PERSONALACCIDENTALINSURANCEAMOUNT_ProductDetails")
        private String PERSONALACCIDENTALINSURANCEAMOUNT_ProductDetails;
        @Expose
        @SerializedName("CREDITPROTECTINSURANCEAMOUNT_ProductDetails")
        private String CREDITPROTECTINSURANCEAMOUNT_ProductDetails;
        @Expose
        @SerializedName("DocumentationCharges_ProductDetails")
        private String DocumentationCharges_ProductDetails;
        @Expose
        @SerializedName("ProcessingFee_ProductDetails")
        private String ProcessingFee_ProductDetails;
        @Expose
        @SerializedName("STARTDATEOFINSTALLMENT")
        private String STARTDATEOFINSTALLMENT;
        @Expose
        @SerializedName("Frequency_ProductDetails")
        private String Frequency_ProductDetails;
        @Expose
        @SerializedName("Tenure_ProductDetails")
        private String Tenure_ProductDetails;
        @Expose
        @SerializedName("ROI")
        private String ROI;
        @Expose
        @SerializedName("FinalLTV_ProductDetails")
        private String FinalLTV_ProductDetails;
        @Expose
        @SerializedName("LoanAmount+InsuranceAmount_TotalLoanAmount(LoanAmount+InsuranceAmount)_inWords")
        private String totalLoanAmount_inWords;
        @Expose
        @SerializedName("LoanAmount+InsuranceAmount_TotalLoanAmount(LoanAmount+InsuranceAmount)")
        private String totalLoanAmount;
        @Expose
        @SerializedName("Producttype")
        private String Producttype;
        @Expose
        @SerializedName("NamefromCoApplicant_2KYC")
        private String NamefromCoApplicant_2KYC;
        @Expose
        @SerializedName("Addressdetails_2_ApplicantKYC")
        private String Addressdetails_2_ApplicantKYC;
        @Expose
        @SerializedName("Addressdetails_1_ApplicantKYC")
        private String Addressdetails_1_ApplicantKYC;
        @Expose
        @SerializedName("NamefromApplicantKYC")
        private String NamefromApplicantKYC;
        @Expose
        @SerializedName("CreditSanctionDate")
        private String CreditSanctionDate;
        @Expose
        @SerializedName("SequenceNo")
        private String SequenceNo;

        public void setElseitshouldbeZero(String elseitshouldbeZero) {
            this.elseitshouldbeZero = elseitshouldbeZero;
        }

        public void setInsuranceandCharges_TractorORVehicalInsurance(String insuranceandCharges_TractorORVehicalInsurance) {
            InsuranceandCharges_TractorORVehicalInsurance = insuranceandCharges_TractorORVehicalInsurance;
        }

        public void setNamefromCoApplicant2KYC(String namefromCoApplicant2KYC) {
            NamefromCoApplicant2KYC = namefromCoApplicant2KYC;
        }

        public void setNamefromCoApplicantKYC(String namefromCoApplicantKYC) {
            NamefromCoApplicantKYC = namefromCoApplicantKYC;
        }

        public void setStampdutyamountbasedonDealerState(String stampdutyamountbasedonDealerState) {
            StampdutyamountbasedonDealerState = stampdutyamountbasedonDealerState;
        }

        public void setPERSONALACCIDENTALINSURANCEAMOUNT_ProductDetails(String PERSONALACCIDENTALINSURANCEAMOUNT_ProductDetails) {
            this.PERSONALACCIDENTALINSURANCEAMOUNT_ProductDetails = PERSONALACCIDENTALINSURANCEAMOUNT_ProductDetails;
        }

        public void setCREDITPROTECTINSURANCEAMOUNT_ProductDetails(String CREDITPROTECTINSURANCEAMOUNT_ProductDetails) {
            this.CREDITPROTECTINSURANCEAMOUNT_ProductDetails = CREDITPROTECTINSURANCEAMOUNT_ProductDetails;
        }

        public void setDocumentationCharges_ProductDetails(String documentationCharges_ProductDetails) {
            DocumentationCharges_ProductDetails = documentationCharges_ProductDetails;
        }

        public void setProcessingFee_ProductDetails(String processingFee_ProductDetails) {
            ProcessingFee_ProductDetails = processingFee_ProductDetails;
        }

        public void setSTARTDATEOFINSTALLMENT(String STARTDATEOFINSTALLMENT) {
            this.STARTDATEOFINSTALLMENT = STARTDATEOFINSTALLMENT;
        }

        public void setFrequency_ProductDetails(String frequency_ProductDetails) {
            Frequency_ProductDetails = frequency_ProductDetails;
        }

        public void setTenure_ProductDetails(String tenure_ProductDetails) {
            Tenure_ProductDetails = tenure_ProductDetails;
        }

        public void setROI(String ROI) {
            this.ROI = ROI;
        }

        public void setFinalLTV_ProductDetails(String finalLTV_ProductDetails) {
            FinalLTV_ProductDetails = finalLTV_ProductDetails;
        }

        public void setTotalLoanAmount_inWords(String totalLoanAmount_inWords) {
            this.totalLoanAmount_inWords = totalLoanAmount_inWords;
        }

        public void setTotalLoanAmount(String totalLoanAmount) {
            this.totalLoanAmount = totalLoanAmount;
        }

        public void setProducttype(String producttype) {
            Producttype = producttype;
        }

        public void setNamefromCoApplicant_2KYC(String namefromCoApplicant_2KYC) {
            NamefromCoApplicant_2KYC = namefromCoApplicant_2KYC;
        }

        public void setAddressdetails_2_ApplicantKYC(String addressdetails_2_ApplicantKYC) {
            Addressdetails_2_ApplicantKYC = addressdetails_2_ApplicantKYC;
        }

        public void setAddressdetails_1_ApplicantKYC(String addressdetails_1_ApplicantKYC) {
            Addressdetails_1_ApplicantKYC = addressdetails_1_ApplicantKYC;
        }

        public void setNamefromApplicantKYC(String namefromApplicantKYC) {
            NamefromApplicantKYC = namefromApplicantKYC;
        }

        public void setCreditSanctionDate(String creditSanctionDate) {
            CreditSanctionDate = creditSanctionDate;
        }

        public void setSequenceNo(String sequenceNo) {
            SequenceNo = sequenceNo;
        }
    }
}
