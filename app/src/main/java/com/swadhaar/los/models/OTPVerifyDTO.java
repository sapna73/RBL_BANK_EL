package com.swadhaar.los.models;

import com.google.gson.annotations.Expose;

public class OTPVerifyDTO {
    @Expose
    private String UniqueId;
    @Expose
    private String ClientID;
    @Expose
    private String ExternalCustomerId;
    @Expose
    private String KYCId;
    @Expose
    private String CreatedDate;
    @Expose
    private String CreatedBy;
    @Expose
    private String ServiceType;
    @Expose
    private OTPVerifyRequestStringDTO RequestString;

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

    public OTPVerifyRequestStringDTO getRequestString() {
        return RequestString;
    }

    public void setRequestString(OTPVerifyRequestStringDTO requestString) {
        RequestString = requestString;
    }
}
