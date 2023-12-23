package com.saartak.el.models.PinCode;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PinCodeDataFromServerResponseDTO {

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
        @SerializedName("DISTRICT")
        private List<String> District;

        @Expose
        @SerializedName("CITY")
        private List<String> City;

        @Expose
        @SerializedName("STATE")
        private String State;

        public List<String> getDistrict() {
            return District;
        }

        public void setDistrict(List<String> District) {
            this.District = District;
        }

        public List<String> getCity() {
            return City;
        }

        public void setCity(List<String> City) {
            this.City = City;
        }

        public String getState() {
            return State;
        }

        public void setState(String State) {
            this.State = State;
        }
    }
}
