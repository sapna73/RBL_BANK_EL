package com.swadhaar.los.models;

import com.google.gson.annotations.Expose;

public class EKYCResponseDTO {
	@Expose
    private String ResponseCode;
	@Expose
    private String ResponseMessage;
	@Expose
    private String ErrorCode;
	@Expose
    private String ErrorMessage;
	@Expose
    private int attempt;
	@Expose
    private ApiResponse ApiResponse;

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

    public int getAttempt() {
        return attempt;
    }

    public void setAttempt(int attempt) {
        this.attempt = attempt;
    }


    public com.swadhaar.los.models.ApiResponse getApiResponse() {
        return ApiResponse;
    }

    public void setApiResponse(com.swadhaar.los.models.ApiResponse apiResponse) {
        ApiResponse = apiResponse;
    }
}
