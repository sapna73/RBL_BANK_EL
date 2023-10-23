package com.saartak.el.models.ESignEstamp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public  class ESignEStampResponseDTO {


    @Expose
    @SerializedName("ApiResponse")
    private ApiResponse ApiResponse;
    @Expose
    @SerializedName("ErrorMessage")
    private String ErrorMessage;
    @Expose
    @SerializedName("ErrorCode")
    private String ErrorCode;
    @Expose
    @SerializedName("ResponseMessage")
    private String ResponseMessage;
    @Expose
    @SerializedName("ResponseCode")
    private String ResponseCode;
    @Expose
    @SerializedName("UniqueId")
    private String UniqueId;

    public ApiResponse getApiResponse() {
        return ApiResponse;
    }

    public void setApiResponse(ApiResponse apiResponse) {
        ApiResponse = apiResponse;
    }

    public String getErrorMessage() {
        return ErrorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        ErrorMessage = errorMessage;
    }

    public String getErrorCode() {
        return ErrorCode;
    }

    public void setErrorCode(String errorCode) {
        ErrorCode = errorCode;
    }

    public String getResponseMessage() {
        return ResponseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        ResponseMessage = responseMessage;
    }

    public String getResponseCode() {
        return ResponseCode;
    }

    public void setResponseCode(String responseCode) {
        ResponseCode = responseCode;
    }

    public String getUniqueId() {
        return UniqueId;
    }

    public void setUniqueId(String uniqueId) {
        UniqueId = uniqueId;
    }

    public static class ApiResponse {
        @Expose
        @SerializedName("responsetime")
        private String responsetime;
        @Expose
        @SerializedName("channel")
        private String channel;
        @Expose
        @SerializedName("sanctionno")
        private String sanctionno;
        @Expose
        @SerializedName("loanno")
        private String loanno;
        @Expose
        @SerializedName("statusmsg")
        private String statusmsg;
        @Expose
        @SerializedName("statuscode")
        private String statuscode;
        @Expose
        @SerializedName("uniquetxnid")
        private String uniquetxnid;

        public String getResponsetime() {
            return responsetime;
        }

        public void setResponsetime(String responsetime) {
            this.responsetime = responsetime;
        }

        public String getChannel() {
            return channel;
        }

        public void setChannel(String channel) {
            this.channel = channel;
        }

        public String getSanctionno() {
            return sanctionno;
        }

        public void setSanctionno(String sanctionno) {
            this.sanctionno = sanctionno;
        }

        public String getLoanno() {
            return loanno;
        }

        public void setLoanno(String loanno) {
            this.loanno = loanno;
        }

        public String getStatusmsg() {
            return statusmsg;
        }

        public void setStatusmsg(String statusmsg) {
            this.statusmsg = statusmsg;
        }

        public String getStatuscode() {
            return statuscode;
        }

        public void setStatuscode(String statuscode) {
            this.statuscode = statuscode;
        }

        public String getUniquetxnid() {
            return uniquetxnid;
        }

        public void setUniquetxnid(String uniquetxnid) {
            this.uniquetxnid = uniquetxnid;
        }
    }
}
