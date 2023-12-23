package com.saartak.el.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetBankDetailsByIfscCodeResponseDTO {
    @Expose
    @SerializedName("ApiResponse")
    private ApiResponseEntity ApiResponse;
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

    public ApiResponseEntity getApiResponse() {
        return ApiResponse;
    }

    public void setApiResponse(ApiResponseEntity ApiResponse) {
        this.ApiResponse = ApiResponse;
    }

    public String getErrorMessage() {
        return ErrorMessage;
    }

    public void setErrorMessage(String ErrorMessage) {
        this.ErrorMessage = ErrorMessage;
    }

    public String getErrorCode() {
        return ErrorCode;
    }

    public void setErrorCode(String ErrorCode) {
        this.ErrorCode = ErrorCode;
    }

    public String getResponseMessage() {
        return ResponseMessage;
    }

    public void setResponseMessage(String ResponseMessage) {
        this.ResponseMessage = ResponseMessage;
    }

    public String getResponseCode() {
        return ResponseCode;
    }

    public void setResponseCode(String ResponseCode) {
        this.ResponseCode = ResponseCode;
    }

    public String getUniqueId() {
        return UniqueId;
    }

    public void setUniqueId(String UniqueId) {
        this.UniqueId = UniqueId;
    }

    public static class ApiResponseEntity {
        @Expose
        @SerializedName("BRANCH_NAME")
        private String BRANCH_NAME;
        @Expose
        @SerializedName("BANK_NAME")
        private String BANK_NAME;
        @Expose
        @SerializedName("IFSC_CODE")
        private String IFSC_CODE;

        public String getBRANCH_NAME() {
            return BRANCH_NAME;
        }

        public void setBRANCH_NAME(String BRANCH_NAME) {
            this.BRANCH_NAME = BRANCH_NAME;
        }

        public String getBANK_NAME() {
            return BANK_NAME;
        }

        public void setBANK_NAME(String BANK_NAME) {
            this.BANK_NAME = BANK_NAME;
        }

        public String getIFSC_CODE() {
            return IFSC_CODE;
        }

        public void setIFSC_CODE(String IFSC_CODE) {
            this.IFSC_CODE = IFSC_CODE;
        }
    }
}
