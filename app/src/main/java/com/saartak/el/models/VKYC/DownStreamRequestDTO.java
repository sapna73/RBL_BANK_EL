package com.saartak.el.models.VKYC;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public  class DownStreamRequestDTO {


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

    public void setRequestString(DownStreamRequestDTO.RequestString requestString) {
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
        @SerializedName("session_id")
        private String session_id;
        @Expose
        @SerializedName("customerId")
        private String customerId;
        @Expose
        @SerializedName("clientCode")
        private String clientCode;

        public void setSession_id(String session_id) {
            this.session_id = session_id;
        }

        public void setCustomerId(String customerId) {
            this.customerId = customerId;
        }

        public void setClientCode(String clientCode) {
            this.clientCode = clientCode;
        }
    }
}
