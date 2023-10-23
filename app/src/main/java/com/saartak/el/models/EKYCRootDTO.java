package com.saartak.el.models;

import com.google.gson.annotations.Expose;

public class EKYCRootDTO {
	@Expose
    private String uniqueId;
	@Expose
    private String createdBy;
	@Expose
    private String serviceType;
	@Expose
    private RequestString requestString;
	@Expose
    private String createdDate;
	@Expose
    private String clientID;
	@Expose
    private Object kYCId;
	@Expose
    private Object externalCustomerId;

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setRequestString(RequestString requestString) {
        this.requestString = requestString;
    }

    public RequestString getRequestString() {
        return requestString;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setClientID(String clientID) {
        this.clientID = clientID;
    }

    public String getClientID() {
        return clientID;
    }

    public void setKYCId(Object kYCId) {
        this.kYCId = kYCId;
    }

    public Object getKYCId() {
        return kYCId;
    }

    public void setExternalCustomerId(Object externalCustomerId) {
        this.externalCustomerId = externalCustomerId;
    }

    public Object getExternalCustomerId() {
        return externalCustomerId;
    }

    @Override
    public String toString() {
        return
                "EKYCRootDTO{" +
                        "uniqueId = '" + uniqueId + '\'' +
                        ",createdBy = '" + createdBy + '\'' +
                        ",serviceType = '" + serviceType + '\'' +
                        ",requestString = '" + requestString + '\'' +
                        ",createdDate = '" + createdDate + '\'' +
                        ",clientID = '" + clientID + '\'' +
                        ",kYCId = '" + kYCId + '\'' +
                        ",externalCustomerId = '" + externalCustomerId + '\'' +
                        "}";
    }
}
