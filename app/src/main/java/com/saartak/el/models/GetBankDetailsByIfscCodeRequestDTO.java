package com.saartak.el.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetBankDetailsByIfscCodeRequestDTO {
    @Expose
    @SerializedName("UniqueId")
    private String UniqueId;
    @Expose
    @SerializedName("ServiceType")
    private String ServiceType;
    @Expose
    @SerializedName("RequestString")
    private RequestStringEntity RequestString;
    @Expose
    @SerializedName("RequestFrom")
    private String RequestFrom;
    @Expose
    @SerializedName("ModuleType")
    private String ModuleType;
    @Expose
    @SerializedName("ExtCustId")
    private String ExtCustId;
    @Expose
    @SerializedName("CreatedDate")
    private String CreatedDate;
    @Expose
    @SerializedName("CreatedBy")
    private String CreatedBy;
    @Expose
    @SerializedName("ClientID")
    private String ClientID;

    public String getUniqueId() {
        return UniqueId;
    }

    public void setUniqueId(String UniqueId) {
        this.UniqueId = UniqueId;
    }

    public String getServiceType() {
        return ServiceType;
    }

    public void setServiceType(String ServiceType) {
        this.ServiceType = ServiceType;
    }

    public RequestStringEntity getRequestString() {
        return RequestString;
    }

    public void setRequestString(RequestStringEntity RequestString) {
        this.RequestString = RequestString;
    }

    public String getRequestFrom() {
        return RequestFrom;
    }

    public void setRequestFrom(String RequestFrom) {
        this.RequestFrom = RequestFrom;
    }

    public String getModuleType() {
        return ModuleType;
    }

    public void setModuleType(String ModuleType) {
        this.ModuleType = ModuleType;
    }

    public String getExtCustId() {
        return ExtCustId;
    }

    public void setExtCustId(String ExtCustId) {
        this.ExtCustId = ExtCustId;
    }

    public String getCreatedDate() {
        return CreatedDate;
    }

    public void setCreatedDate(String CreatedDate) {
        this.CreatedDate = CreatedDate;
    }

    public String getCreatedBy() {
        return CreatedBy;
    }

    public void setCreatedBy(String CreatedBy) {
        this.CreatedBy = CreatedBy;
    }

    public String getClientID() {
        return ClientID;
    }

    public void setClientID(String ClientID) {
        this.ClientID = ClientID;
    }

    public static class RequestStringEntity {
        @Expose
        @SerializedName("IFSCCode")
        private String IFSCCode;

        public String getIFSCCode() {
            return IFSCCode;
        }

        public void setIFSCCode(String IFSCCode) {
            this.IFSCCode = IFSCCode;
        }
    }
}

