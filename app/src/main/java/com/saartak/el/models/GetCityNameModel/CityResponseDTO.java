package com.saartak.el.models.GetCityNameModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CityResponseDTO {

    @SerializedName("UniqueId")
    @Expose
    private String uniqueId;


    @SerializedName("ResponseCode")
    @Expose
    private String responseCode;


    @SerializedName("ResponseMessage")
    @Expose
    private String responseMessage;

    @SerializedName("ErrorCode")
    @Expose
    private String errorCode;

    @SerializedName("ErrorMessage")
    @Expose
    private String errorMessage;

    @SerializedName("ApiResponse")
    @Expose
    private ApiResponse ApiResponse;

    public static class ApiResponse {

        @SerializedName("city")
        @Expose
        public ArrayList<String> city;


        public ArrayList<String> getCity() {
            return city;
        }

        public void setCity(ArrayList<String> city) {
            this.city = city;
        }
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public CityResponseDTO.ApiResponse getApiResponse() {
        return ApiResponse;
    }

    public void setApiResponse(CityResponseDTO.ApiResponse apiResponse) {
        ApiResponse = apiResponse;
    }
}
