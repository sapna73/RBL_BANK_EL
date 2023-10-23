package com.saartak.el.models;

import com.google.gson.annotations.Expose;

public class AadhaarVaultResponseDTO {

    @Expose
    private String ResponseCode;
    @Expose
    private String ResponseMessage;
    @Expose
    private String ErrorCode;
    @Expose
    private String ErrorMessage;
    @Expose
    private ApiResponseClass ApiResponse;

    public String getResponseCode() {
        return ResponseCode;
    }

    public void setResponseCode(String responseCode) {
        ResponseCode = responseCode;
    }

    public String getResponseMessage() {
        return ResponseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        ResponseMessage = responseMessage;
    }

    public String getErrorCode() {
        return ErrorCode;
    }

    public void setErrorCode(String errorCode) {
        ErrorCode = errorCode;
    }

    public String getErrorMessage() {
        return ErrorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        ErrorMessage = errorMessage;
    }

    public ApiResponseClass getApiResponse() {
        return ApiResponse;
    }

    public void setApiResponse(ApiResponseClass apiResponse) {
        ApiResponse = apiResponse;
    }

    public class ApiResponseClass {
        @Expose
        private String AadhaarRefKey;
        @Expose
        private String DateTime;
        @Expose
        private String Status;
        @Expose
        private String Channel;
        @Expose
        private String Token;
        @Expose
        private String ReqId;
        @Expose
        private String Hash;
        @Expose
        private String Error_Description;

        public String getAadhaarRefKey() {
            return AadhaarRefKey;
        }

        public void setAadhaarRefKey(String aadhaarRefKey) {
            AadhaarRefKey = aadhaarRefKey;
        }

        public String getDateTime() {
            return DateTime;
        }

        public void setDateTime(String dateTime) {
            DateTime = dateTime;
        }

        public String getStatus() {
            return Status;
        }

        public void setStatus(String status) {
            Status = status;
        }

        public String getChannel() {
            return Channel;
        }

        public void setChannel(String channel) {
            Channel = channel;
        }

        public String getToken() {
            return Token;
        }

        public void setToken(String token) {
            Token = token;
        }

        public String getReqId() {
            return ReqId;
        }

        public void setReqId(String reqId) {
            ReqId = reqId;
        }

        public String getHash() {
            return Hash;
        }

        public void setHash(String hash) {
            Hash = hash;
        }

        public String getError_Description() {
            return Error_Description;
        }

        public void setError_Description(String error_Description) {
            Error_Description = error_Description;
        }
    }

}
