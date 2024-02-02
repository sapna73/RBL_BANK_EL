package com.saartak.el.models.ESignEstamp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public  class ESignEStampStatusRequestDTO {

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

    public void setRequestString(ESignEStampStatusRequestDTO.RequestString requestString) {
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
        @SerializedName("ddestatusreq")
        private Ddestatusreq ddestatusreq;

        public void setDdestatusreq(Ddestatusreq ddestatusreq) {
            this.ddestatusreq = ddestatusreq;
        }

        public static class Ddestatusreq {
            @Expose
            @SerializedName("uniquetxnid")
            private String uniquetxnid;
            @Expose
            @SerializedName("existingtxnid")
            private String existingtxnid;
            @Expose
            @SerializedName("sanctionno")
            private String sanctionno;
            @Expose
            @SerializedName("loanno")
            private String loanno;
            @Expose
            @SerializedName("businessvertical")
            private String businessvertical;
            @Expose
            @SerializedName("channel")
            private String channel;
            @Expose
            @SerializedName("requesttime")
            private String requesttime;
            @Expose
            @SerializedName("sessiontoken")
            private String sessiontoken;

            public void setUniquetxnid(String uniquetxnid) {
                this.uniquetxnid = uniquetxnid;
            }

            public void setExistingtxnid(String existingtxnid) {
                this.existingtxnid = existingtxnid;
            }

            public void setSanctionno(String sanctionno) {
                this.sanctionno = sanctionno;
            }

            public void setLoanno(String loanno) {
                this.loanno = loanno;
            }

            public void setBusinessvertical(String businessvertical) {
                this.businessvertical = businessvertical;
            }

            public void setChannel(String channel) {
                this.channel = channel;
            }

            public void setRequesttime(String requesttime) {
                this.requesttime = requesttime;
            }

            public void setSessiontoken(String sessiontoken) {
                this.sessiontoken = sessiontoken;
            }
        }
    }

}
