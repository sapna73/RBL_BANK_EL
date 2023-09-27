package com.swadhaar.los.models.DigitalDocs;

import com.google.gson.annotations.SerializedName;

public abstract class DigitalDocRequestDTO {


    @SerializedName("AADHAR")
    private String AADHAR;
    @SerializedName("UniqueId")
    private String UniqueId;
    @SerializedName("ServiceType")
    private String ServiceType;
    @SerializedName("RequestString")
    private RequestString RequestString;
    @SerializedName("KYCId")
    private String KYCId;
    @SerializedName("ExternalCustomerId")
    private String ExternalCustomerId;
    @SerializedName("CreatedDate")
    private String CreatedDate;
    @SerializedName("CreatedByProject")
    private String CreatedByProject;
    @SerializedName("CreatedBy")
    private String CreatedBy;
    @SerializedName("ClientID")
    private String ClientID;

    public String getAADHAR() {
        return AADHAR;
    }

    public String getUniqueId() {
        return UniqueId;
    }

    public String getServiceType() {
        return ServiceType;
    }

    public RequestString getRequestString() {
        return RequestString;
    }

    public String getKYCId() {
        return KYCId;
    }

    public String getExternalCustomerId() {
        return ExternalCustomerId;
    }

    public String getCreatedDate() {
        return CreatedDate;
    }

    public String getCreatedByProject() {
        return CreatedByProject;
    }

    public String getCreatedBy() {
        return CreatedBy;
    }

    public String getClientID() {
        return ClientID;
    }

    public static class RequestString {
        @SerializedName("Bydefault850+GSTifincaseofUsedtractoronly,elseitshouldbeZero")
        private String elseitshouldbeZero;
        @SerializedName("InsuranceandCharges_TractorORVehicalInsurance")
        private String InsuranceandCharges_TractorORVehicalInsurance;
        @SerializedName("NamefromCoApplicant2KYC")
        private String NamefromCoApplicant2KYC;
        @SerializedName("NamefromCoApplicantKYC")
        private String NamefromCoApplicantKYC;
        @SerializedName("StampdutyamountbasedonDealerState")
        private String StampdutyamountbasedonDealerState;
        @SerializedName("PERSONALACCIDENTALINSURANCEAMOUNT_ProductDetails")
        private String PERSONALACCIDENTALINSURANCEAMOUNT_ProductDetails;
        @SerializedName("CREDITPROTECTINSURANCEAMOUNT_ProductDetails")
        private String CREDITPROTECTINSURANCEAMOUNT_ProductDetails;
        @SerializedName("DocumentationCharges_ProductDetails")
        private String DocumentationCharges_ProductDetails;
        @SerializedName("ProcessingFee_ProductDetails")
        private String ProcessingFee_ProductDetails;
        @SerializedName("STARTDATEOFINSTALLMENT")
        private String STARTDATEOFINSTALLMENT;
        @SerializedName("Frequency_ProductDetails")
        private String Frequency_ProductDetails;
        @SerializedName("Tenure_ProductDetails")
        private String Tenure_ProductDetails;
        @SerializedName("ROI")
        private String ROI;
        @SerializedName("FinalLTV_ProductDetails")
        private String FinalLTV_ProductDetails;
        @SerializedName("LoanAmount+InsuranceAmount_TotalLoanAmount(LoanAmount+InsuranceAmount)_inWords")
        private String totalLoanAmount_inWords;
        @SerializedName("LoanAmount+InsuranceAmount_TotalLoanAmount(LoanAmount+InsuranceAmount)")
        private String LoanAmount+InsuranceAmount_TotalLoanAmount(LoanAmount+InsuranceAmount);
        @SerializedName("Producttype")
        private String Producttype;
        @SerializedName("NamefromCoApplicant_2KYC")
        private String NamefromCoApplicant_2KYC;
        @SerializedName("Addressdetails_2_ApplicantKYC")
        private String Addressdetails_2_ApplicantKYC;
        @SerializedName("Addressdetails_1_ApplicantKYC")
        private String Addressdetails_1_ApplicantKYC;
        @SerializedName("NamefromApplicantKYC")
        private String NamefromApplicantKYC;
        @SerializedName("CreditSanctionDate")
        private String CreditSanctionDate;
        @SerializedName("SequenceNo")
        private String SequenceNo;

        public String getTHIS_IA_AN_INVALID_JAVA_IDENTIFIER_MANUALLY_RESOLVE_THE_ISSUE() {
            return THIS_IA_AN_INVALID_JAVA_IDENTIFIER_MANUALLY_RESOLVE_THE_ISSUE;
        }

        public String getInsuranceandCharges_TractorORVehicalInsurance() {
            return InsuranceandCharges_TractorORVehicalInsurance;
        }

        public String getNamefromCoApplicant2KYC() {
            return NamefromCoApplicant2KYC;
        }

        public String getNamefromCoApplicantKYC() {
            return NamefromCoApplicantKYC;
        }

        public String getStampdutyamountbasedonDealerState() {
            return StampdutyamountbasedonDealerState;
        }

        public String getPERSONALACCIDENTALINSURANCEAMOUNT_ProductDetails() {
            return PERSONALACCIDENTALINSURANCEAMOUNT_ProductDetails;
        }

        public String getCREDITPROTECTINSURANCEAMOUNT_ProductDetails() {
            return CREDITPROTECTINSURANCEAMOUNT_ProductDetails;
        }

        public String getDocumentationCharges_ProductDetails() {
            return DocumentationCharges_ProductDetails;
        }

        public String getProcessingFee_ProductDetails() {
            return ProcessingFee_ProductDetails;
        }

        public String getSTARTDATEOFINSTALLMENT() {
            return STARTDATEOFINSTALLMENT;
        }

        public String getFrequency_ProductDetails() {
            return Frequency_ProductDetails;
        }

        public String getTenure_ProductDetails() {
            return Tenure_ProductDetails;
        }

        public String getROI() {
            return ROI;
        }

        public String getFinalLTV_ProductDetails() {
            return FinalLTV_ProductDetails;
        }

        public String getTHIS_IA_AN_INVALID_JAVA_IDENTIFIER_MANUALLY_RESOLVE_THE_ISSUE() {
            return THIS_IA_AN_INVALID_JAVA_IDENTIFIER_MANUALLY_RESOLVE_THE_ISSUE;
        }

        public String getTHIS_IA_AN_INVALID_JAVA_IDENTIFIER_MANUALLY_RESOLVE_THE_ISSUE() {
            return THIS_IA_AN_INVALID_JAVA_IDENTIFIER_MANUALLY_RESOLVE_THE_ISSUE;
        }

        public String getProducttype() {
            return Producttype;
        }

        public String getNamefromCoApplicant_2KYC() {
            return NamefromCoApplicant_2KYC;
        }

        public String getAddressdetails_2_ApplicantKYC() {
            return Addressdetails_2_ApplicantKYC;
        }

        public String getAddressdetails_1_ApplicantKYC() {
            return Addressdetails_1_ApplicantKYC;
        }

        public String getNamefromApplicantKYC() {
            return NamefromApplicantKYC;
        }

        public String getCreditSanctionDate() {
            return CreditSanctionDate;
        }

        public String getSequenceNo() {
            return SequenceNo;
        }
    }
}
