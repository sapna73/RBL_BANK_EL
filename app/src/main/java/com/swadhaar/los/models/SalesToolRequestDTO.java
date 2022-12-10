package com.swadhaar.los.models;

import com.google.gson.annotations.Expose;
import com.swadhaar.los.database.entity.SalesToolTable;

public class SalesToolRequestDTO {

    @Expose
    private String UniqueId;
    @Expose
    private String ClientID;
    @Expose
    private String ExternalCustomerId;
    @Expose
    private String CreatedDate;
    @Expose
    private String CreatedBy;
    @Expose
    private String ServiceType;
    @Expose
    private RequestStringClass RequestString;

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

    public RequestStringClass getRequestString() {
        return RequestString;
    }

    public void setRequestString(RequestStringClass requestString) {
        RequestString = requestString;
    }

    public static class RequestStringClass{
        @Expose
        private SalesToolTable calculationRequest;

        public SalesToolTable getCalculationRequest() {
            return calculationRequest;
        }

        public void setCalculationRequest(SalesToolTable calculationRequest) {
            this.calculationRequest = calculationRequest;
        }
    }

}
