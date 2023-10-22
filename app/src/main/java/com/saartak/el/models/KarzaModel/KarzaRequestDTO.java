package com.saartak.el.models.KarzaModel;

import com.google.gson.annotations.Expose;

public class KarzaRequestDTO {

    @Expose
    private String ClientID = "";

    @Expose
    private String ServiceType = "Karza";

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
    private RequestString RequestString;



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

    public String getKYCId() {
        return KYCId;
    }

    public void setKYCId(String KYCId) {
        this.KYCId = KYCId;
    }

    public RequestString getRequestString() {
        return RequestString;
    }

    public void setRequestString(RequestString requestString) {
        RequestString = requestString;
    }

    public static class RequestString {

        @Expose
        private String registrationNumber = "";

        @Expose
        private String consent = "Y";

        @Expose
        private double version = 3.1;

        public String getRegistrationNumber() {
            return registrationNumber;
        }

        public void setRegistrationNumber(String registrationNumber) {
            this.registrationNumber = registrationNumber;
        }

        public String getConsent() {
            return consent;
        }

        public void setConsent(String consent) {
            this.consent = consent;
        }

        public double getVersion() {
            return version;
        }

        public void setVersion(double version) {
            this.version = version;
        }
    }
}