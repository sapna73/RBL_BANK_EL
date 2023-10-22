package com.saartak.el.models.RAT;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RATResponseDTO {
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
    @Expose
    @SerializedName("ModuleType")
    private String moduleType;

    public String getModuleType() {
        return moduleType;
    }

    public void setModuleType(String moduleType) {
        this.moduleType = moduleType;
    }

    public ApiResponse getApiResponse() {
        return ApiResponse;
    }

    public String getErrorMessage() {
        return ErrorMessage;
    }

    public String getErrorCode() {
        return ErrorCode;
    }

    public String getResponseMessage() {
        return ResponseMessage;
    }

    public String getResponseCode() {
        return ResponseCode;
    }

    public String getUniqueId() {
        return UniqueId;
    }

    public static class ApiResponse {
        @Expose
        @SerializedName("HighRiskMessage")
        private String HighRiskMessage;
        @Expose
        @SerializedName("DateTime")
        private String DateTime;
        @Expose
        @SerializedName("RiskStatus")
        private String RiskStatus;

        @Expose
        @SerializedName("ErrorDesc")
        private String errorDesc;

        public String getHighRiskMessage() {
            return HighRiskMessage;
        }

        public String getDateTime() {
            return DateTime;
        }

        public String getRiskStatus() {
            return RiskStatus;
        }

        public String getErrorDesc() {
            return errorDesc;
        }
    }
}
