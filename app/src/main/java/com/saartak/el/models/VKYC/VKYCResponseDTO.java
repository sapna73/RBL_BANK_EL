package com.saartak.el.models.VKYC;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public  class VKYCResponseDTO {

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
        @SerializedName("validity_duration")
        private int validity_duration;
        @Expose
        @SerializedName("user_id")
        private String user_id;
        @Expose
        @SerializedName("status")
        private String status;
        @Expose
        @SerializedName("session_id")
        private String session_id;
        @Expose
        @SerializedName("redirectLink")
        private String redirectLink;
        @Expose
        @SerializedName("phone_number")
        private String phone_number;
        @Expose
        @SerializedName("message")
        private String message;
        @Expose
        @SerializedName("link_id")
        private String link_id;
        @Expose
        @SerializedName("link_expiry_time")
        private String link_expiry_time;
        @Expose
        @SerializedName("code")
        private String code;

        public int getValidity_duration() {
            return validity_duration;
        }

        public String getUser_id() {
            return user_id;
        }

        public String getStatus() {
            return status;
        }

        public String getSession_id() {
            return session_id;
        }

        public String getRedirectLink() {
            return redirectLink;
        }

        public String getPhone_number() {
            return phone_number;
        }

        public String getMessage() {
            return message;
        }

        public String getLink_id() {
            return link_id;
        }

        public String getLink_expiry_time() {
            return link_expiry_time;
        }

        public String getCode() {
            return code;
        }
    }
}
