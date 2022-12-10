package com.swadhaar.los.models;

import com.google.gson.annotations.Expose;

public class OTPTriggerResponseDTO {

    @Expose
    private String ResponseCode;
    @Expose
    private String ResponseMessage;
    @Expose
    private String ErrorCode;
    @Expose
    private String ErrorMessage;
    @Expose
    private OTPTriggerAPIResponseDTO ApiResponse;

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

    public OTPTriggerAPIResponseDTO getApiResponse() {
        return ApiResponse;
    }

    public void setApiResponse(OTPTriggerAPIResponseDTO apiResponse) {
        ApiResponse = apiResponse;
    }
}
