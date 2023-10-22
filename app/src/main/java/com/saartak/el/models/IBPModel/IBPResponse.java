package com.saartak.el.models.IBPModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class IBPResponse {

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
        @SerializedName("fairprice")
        @Expose
        private int fairprice;

        @SerializedName("marketprice")
        @Expose
        private int marketprice;

        @SerializedName("bestprice")
        @Expose
        private int bestprice;

        public int getFairprice() {
            return fairprice;
        }

        public void setFairprice(int fairprice) {
            this.fairprice = fairprice;
        }

        public int getMarketprice() {
            return marketprice;
        }

        public void setMarketprice(int marketprice) {
            this.marketprice = marketprice;
        }

        public int getBestprice() {
            return bestprice;
        }

        public void setBestprice(int bestprice) {
            this.bestprice = bestprice;
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

    public IBPResponse.ApiResponse getApiResponse() {
        return ApiResponse;
    }

    public void setApiResponse(IBPResponse.ApiResponse apiResponse) {
        ApiResponse = apiResponse;
    }
}
