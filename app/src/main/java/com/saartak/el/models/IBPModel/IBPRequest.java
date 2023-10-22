package com.saartak.el.models.IBPModel;

import com.google.gson.annotations.Expose;

public class IBPRequest {
    @Expose
    private String ClientID = "";

    @Expose
    private String ServiceType = "IBBPrice";

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
        private String _for = "comprehensivePrice";
        @Expose
        private String year = "";

        @Expose
        private String month = "";

        @Expose
        private String make = "";

        @Expose
        private String model = "";

        @Expose
        private String variant = "";

        @Expose
        private String location = "";

        @Expose
        private String color = "";

        @Expose
        private String owner = "";

        @Expose
        private String kilometer = "";

        @Expose
        private String access_token = "";

        public String getYear() {
            return year;
        }

        public void setYear(String year) {
            this.year = year;
        }

        public String getMonth() {
            return month;
        }

        public void setMonth(String month) {
            this.month = month;
        }

        public String getMake() {
            return make;
        }

        public void setMake(String make) {
            this.make = make;
        }

        public String getModel() {
            return model;
        }

        public void setModel(String model) {
            this.model = model;
        }

        public String getVariant() {
            return variant;
        }

        public void setVariant(String variant) {
            this.variant = variant;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public String getOwner() {
            return owner;
        }

        public void setOwner(String owner) {
            this.owner = owner;
        }

        public String getKilometer() {
            return kilometer;
        }

        public void setKilometer(String kilometer) {
            this.kilometer = kilometer;
        }

        public String getAccess_token() {
            return access_token;
        }

        public void setAccess_token(String access_token) {
            this.access_token = access_token;
        }
    }
}
