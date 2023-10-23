package com.saartak.el.models.GetCityNameModel;

import com.google.gson.annotations.Expose;
public class CityRequestDTO {

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

    @Expose
    private RequestString RequestString;

    public static class  RequestString{


        @Expose
        private String _for = "";

        @Expose
        private String access_token = "";

        public String get_for() {
            return _for;
        }

        public void set_for(String _for) {
            this._for = _for;
        }

        public String getAccess_token() {
            return access_token;
        }

        public void setAccess_token(String access_token) {
            this.access_token = access_token;
        }
    }

    public CityRequestDTO.RequestString getRequestString() {
        return RequestString;
    }

    public void setRequestString(CityRequestDTO.RequestString requestString) {
        RequestString = requestString;
    }
}
