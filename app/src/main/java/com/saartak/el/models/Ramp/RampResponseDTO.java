package com.saartak.el.models.Ramp;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RampResponseDTO {

    @Expose
    @SerializedName("ApiResponse")
    public ApiResponse ApiResponse;
    @Expose
    @SerializedName("ErrorMessage")
    public String ErrorMessage;
    @Expose
    @SerializedName("ErrorCode")
    public String ErrorCode;
    @Expose
    @SerializedName("ResponseMessage")
    public String ResponseMessage;
    @Expose
    @SerializedName("ResponseCode")
    public String ResponseCode;
    @Expose
    @SerializedName("UniqueId")
    public String UniqueId;

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
        @SerializedName("RampResponse")
        public RampResponse RampResponse;

        public RampResponse getRampResponse() {
            return RampResponse;
        }

        public void setRampResponse(RampResponse rampResponse) {
            RampResponse = rampResponse;
        }

        public static class RampResponse {
            @Expose
            @SerializedName("M1")
            public String M1;
            @Expose
            @SerializedName("Z1")
            public String Z1;
            @Expose
            @SerializedName("D1")
            public String D1;
            @Expose
            @SerializedName("ApplicationId")
            public String ApplicationId;
            @Expose
            @SerializedName("UniqueRequestId")
            public String UniqueRequestId;
            @Expose
            @SerializedName("CaseStatus")
            public String CaseStatus;
            @Expose
            @SerializedName("Message")
            public String Message;
            @Expose
            @SerializedName("Status")
            public String Status;

            public String getM1() {
                return M1;
            }

            public void setM1(String m1) {
                M1 = m1;
            }

            public String getZ1() {
                return Z1;
            }

            public void setZ1(String z1) {
                Z1 = z1;
            }

            public String getD1() {
                return D1;
            }

            public void setD1(String d1) {
                D1 = d1;
            }

            public String getApplicationId() {
                return ApplicationId;
            }

            public void setApplicationId(String applicationId) {
                ApplicationId = applicationId;
            }

            public String getUniqueRequestId() {
                return UniqueRequestId;
            }

            public void setUniqueRequestId(String uniqueRequestId) {
                UniqueRequestId = uniqueRequestId;
            }

            public String getCaseStatus() {
                return CaseStatus;
            }

            public void setCaseStatus(String caseStatus) {
                CaseStatus = caseStatus;
            }

            public String getMessage() {
                return Message;
            }

            public void setMessage(String message) {
                Message = message;
            }

            public String getStatus() {
                return Status;
            }

            public void setStatus(String status) {
                Status = status;
            }
        }
    }
}
