package com.swadhaar.los.models;

import com.google.gson.annotations.Expose;

import retrofit2.http.Body;

public class CBMFIResponseDTO {
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
        private String ApplicationId;
        @Expose
        private String ExternalApplicationId;
        @Expose
        private String ProcessDate;
        @Expose
        private String IsError;
        @Expose
        private String ErrorMessage;
        @Expose
        private String Score;
        @Expose
        private String Decision;
        @Expose
        private String Reason;
        @Expose
        private String MaxEligibleAmnt;
        @Expose
        private String ApprovedLoanAmnt;
        @Expose
        EquifaxResponse EquifaxResponseObject;


        public String getApplicationId() {
            return ApplicationId;
        }

        public void setApplicationId(String applicationId) {
            ApplicationId = applicationId;
        }

        public String getExternalApplicationId() {
            return ExternalApplicationId;
        }

        public void setExternalApplicationId(String externalApplicationId) {
            ExternalApplicationId = externalApplicationId;
        }

        public String getProcessDate() {
            return ProcessDate;
        }

        public void setProcessDate(String processDate) {
            ProcessDate = processDate;
        }

        public String getIsError() {
            return IsError;
        }

        public void setIsError(String isError) {
            IsError = isError;
        }

        public String getErrorMessage() {
            return ErrorMessage;
        }

        public void setErrorMessage(String errorMessage) {
            ErrorMessage = errorMessage;
        }

        public String getScore() {
            return Score;
        }

        public void setScore(String score) {
            Score = score;
        }

        public String getDecision() {
            return Decision;
        }

        public void setDecision(String decision) {
            Decision = decision;
        }

        public String getReason() {
            return Reason;
        }

        public void setReason(String reason) {
            Reason = reason;
        }

        public String getMaxEligibleAmnt() {
            return MaxEligibleAmnt;
        }

        public void setMaxEligibleAmnt(String maxEligibleAmnt) {
            MaxEligibleAmnt = maxEligibleAmnt;
        }

        public String getApprovedLoanAmnt() {
            return ApprovedLoanAmnt;
        }

        public void setApprovedLoanAmnt(String approvedLoanAmnt) {
            ApprovedLoanAmnt = approvedLoanAmnt;
        }

        public EquifaxResponse getEquifaxResponseObject() {
            return EquifaxResponseObject;
        }

        public void setEquifaxResponseObject(EquifaxResponse equifaxResponseObject) {
            EquifaxResponseObject = equifaxResponseObject;
        }
    }

    public class EquifaxResponse {
        @Expose
        private String Soapenv;

        public String getSoapenv() {
            return Soapenv;
        }

        public void setSoapenv(String soapenv) {
            Soapenv = soapenv;
        }
    }
    }
