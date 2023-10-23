package com.saartak.el.models.IBBMasters;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class GetColorResponseDTO {
    @SerializedName("UniqueId")
    @Expose
    public String uniqueId;

    @SerializedName("ResponseCode")
    @Expose
    public String responseCode;

    @SerializedName("ResponseMessage")
    @Expose
    public String responseMessage;

    @SerializedName("ErrorCode")
    @Expose
    public String errorCode;

    @SerializedName("ErrorMessage")
    @Expose
    public String errorMessage;

    @SerializedName("ApiResponse")
    @Expose
    public ApiResponse apiResponse;

    public class ApiResponse{

        @SerializedName("color")
        @Expose
        public ArrayList<String> color;

        public ArrayList<String> getColor() {
            return color;
        }
    }


    public String getUniqueId() {
        return uniqueId;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public ApiResponse getApiResponse() {
        return apiResponse;
    }
}
