package com.saartak.el.models.RAT;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public  class RATRequestDTO {
    @Expose
    @SerializedName("RequestString")
    private RequestString RequestString;
    @Expose
    @SerializedName("co_applicant_id")
    private String co_applicant_id;
    @Expose
    @SerializedName("ModuleType")
    private String ModuleType;
    @Expose
    @SerializedName("ApplicationId")
    private String ApplicationId;
    @Expose
    @SerializedName("ServiceType")
    private String ServiceType;
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
    @SerializedName("CreatedDate")
    private String CreatedDate;
    @Expose
    @SerializedName("KYCId")
    private String KYCId;
    @Expose
    @SerializedName("ExternalCustomerId")
    private String ExternalCustomerId;
    @Expose
    @SerializedName("ClientID")
    private String ClientID;
    @Expose
    @SerializedName("UniqueId")
    private String UniqueId;

    public void setRequestString(RequestString requestString) {
        RequestString = requestString;
    }

    public void setCo_applicant_id(String co_applicant_id) {
        this.co_applicant_id = co_applicant_id;
    }

    public void setModuleType(String moduleType) {
        ModuleType = moduleType;
    }

    public void setApplicationId(String applicationId) {
        ApplicationId = applicationId;
    }

    public void setServiceType(String serviceType) {
        ServiceType = serviceType;
    }

    public void setCreatedByProject(String createdByProject) {
        CreatedByProject = createdByProject;
    }

    public void setRequestFrom(String requestFrom) {
        RequestFrom = requestFrom;
    }

    public void setCreatedBy(String createdBy) {
        CreatedBy = createdBy;
    }

    public void setCreatedDate(String createdDate) {
        CreatedDate = createdDate;
    }

    public void setKYCId(String KYCId) {
        this.KYCId = KYCId;
    }

    public void setExternalCustomerId(String externalCustomerId) {
        ExternalCustomerId = externalCustomerId;
    }

    public void setClientID(String clientID) {
        ClientID = clientID;
    }

    public void setUniqueId(String uniqueId) {
        UniqueId = uniqueId;
    }

    public static class RequestString {
        @Expose
        @SerializedName("RatApiRequest")
        private RatApiRequest RatApiRequest;

        public void setRatApiRequest(RequestString.RatApiRequest ratApiRequest) {
            RatApiRequest = ratApiRequest;
        }

        public static class RatApiRequest {
            @Expose
            @SerializedName("ExistingCifRating")
            private String ExistingCifRating;
            @Expose
            @SerializedName("RiskCompliance")
            private String RiskCompliance;
            @Expose
            @SerializedName("PEPCategory")
            private String PEPCategory;
            @Expose
            @SerializedName("IndustryType")
            private String IndustryType;
            @Expose
            @SerializedName("CountryOfResidence")
            private String CountryOfResidence;
            @Expose
            @SerializedName("SourceOfIncome")
            private String SourceOfIncome;
            @Expose
            @SerializedName("CustomerType")
            private String CustomerType;
            @Expose
            @SerializedName("ExistingCustomer")
            private String ExistingCustomer;
            @Expose
            @SerializedName("CustomerAddress")
            private String CustomerAddress;
            @Expose
            @SerializedName("CustomerName")
            private String CustomerName;

            public void setExistingCifRating(String existingCifRating) {
                ExistingCifRating = existingCifRating;
            }

            public void setRiskCompliance(String riskCompliance) {
                RiskCompliance = riskCompliance;
            }

            public void setPEPCategory(String PEPCategory) {
                this.PEPCategory = PEPCategory;
            }

            public void setIndustryType(String industryType) {
                IndustryType = industryType;
            }

            public void setCountryOfResidence(String countryOfResidence) {
                CountryOfResidence = countryOfResidence;
            }

            public void setSourceOfIncome(String sourceOfIncome) {
                SourceOfIncome = sourceOfIncome;
            }

            public void setCustomerType(String customerType) {
                CustomerType = customerType;
            }

            public void setExistingCustomer(String existingCustomer) {
                ExistingCustomer = existingCustomer;
            }

            public void setCustomerAddress(String customerAddress) {
                CustomerAddress = customerAddress;
            }

            public void setCustomerName(String customerName) {
                CustomerName = customerName;
            }
        }
    }


}
