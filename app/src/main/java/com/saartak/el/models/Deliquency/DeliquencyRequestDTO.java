package com.saartak.el.models.Deliquency;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DeliquencyRequestDTO {

    @Expose
    private String ClientID = "";

    @Expose
    private String ServiceType = "Deliquency";

    @Expose
    private String CreatedByProject = "";

    @Expose
    private String ExternalCustomerId = "";

    @Expose
    private String CreatedDate = "";

    @Expose
    private String CreatedBy = "EL";

    @Expose
    private String UniqueId = "";

    @Expose
    private String AADHAR = "";

    @Expose
    private String KYCId = "";

    @Expose
    @SerializedName("ModuleType")
    private String moduleType;
    @Expose
    @SerializedName("co_applicant_id")
    private String coapplicantId;

    @Expose
    private RequestString RequestString;

    public static class RequestString {

        @Expose
        private String ProductID = "";

        @Expose
        private String SearchableValue = "";

        @Expose
        private String channelId = "SRTK";


        public String getProductID() {
            return ProductID;
        }

        public void setProductID(String productID) {
            ProductID = productID;
        }

        public String getSearchableValue() {
            return SearchableValue;
        }

        public void setSearchableValue(String searchableValue) {
            SearchableValue = searchableValue;
        }

        public String getChannelId() {
            return channelId;
        }

        public void setChannelId(String channelId) {
            this.channelId = channelId;
        }
    }


    public String getClientID() {
        return ClientID;
    }

    public void setClientID(String clientID) {
        ClientID = clientID;
    }

    public String getServiceType() {
        return ServiceType;
    }

    public void setServiceType(String serviceType) {
        ServiceType = serviceType;
    }

    public String getCreatedByProject() {
        return CreatedByProject;
    }

    public void setCreatedByProject(String createdByProject) {
        CreatedByProject = createdByProject;
    }

    public String getExternalCustomerId() {
        return ExternalCustomerId;
    }

    public void setExternalCustomerId(String externalCustomerId) {
        ExternalCustomerId = externalCustomerId;
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

    public String getUniqueId() {
        return UniqueId;
    }

    public void setUniqueId(String uniqueId) {
        UniqueId = uniqueId;
    }

    public String getAADHAR() {
        return AADHAR;
    }

    public void setAADHAR(String AADHAR) {
        this.AADHAR = AADHAR;
    }

    public String getModuleType() {
        return moduleType;
    }

    public void setModuleType(String moduleType) {
        this.moduleType = moduleType;
    }

    public String getCoapplicantId() {
        return coapplicantId;
    }

    public void setCoapplicantId(String coapplicantId) {
        this.coapplicantId = coapplicantId;
    }

    public String getKYCId() {
        return KYCId;
    }

    public void setKYCId(String KYCId) {
        this.KYCId = KYCId;
    }

    public DeliquencyRequestDTO.RequestString getRequestString() {
        return RequestString;
    }

    public void setRequestString(DeliquencyRequestDTO.RequestString requestString) {
        RequestString = requestString;
    }
}
