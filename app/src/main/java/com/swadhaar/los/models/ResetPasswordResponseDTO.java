package com.swadhaar.los.models;

import com.google.gson.annotations.Expose;

public class ResetPasswordResponseDTO {
    @Expose
    private String ResponseCode;
    @Expose
    private String ResponseMessage;
    @Expose
    private String ErrorCode;
    @Expose
    private String ErrorMessage;
    @Expose
    private ApiResponseDTO ApiResponse;

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

    public ApiResponseDTO getApiResponse() {
        return ApiResponse;
    }

    public void setApiResponse(ApiResponseDTO apiResponse) {
        ApiResponse = apiResponse;
    }

    public class ApiResponseDTO {
        @Expose
        private ResetPasswordResponseClass ResetPasswordResponse;

        public ResetPasswordResponseClass getResetPasswordResponse() {
            return ResetPasswordResponse;
        }

        public void setResetPasswordResponse(ResetPasswordResponseClass resetPasswordResponse) {
            ResetPasswordResponse = resetPasswordResponse;
        }
    }

    public class ResetPasswordResponseClass {
        @Expose
        private String Status;
        @Expose
        private String Message;
        @Expose
        private String UserId;
        @Expose
        private String Password;
        @Expose
        private String P1;
        @Expose
        private String P2;
        @Expose
        private String P3;
        @Expose
        private String P4;
        @Expose
        private String P5;

        public String getStatus() {
            return Status;
        }

        public void setStatus(String status) {
            Status = status;
        }

        public String getMessage() {
            return Message;
        }

        public void setMessage(String message) {
            Message = message;
        }

        public String getUserId() {
            return UserId;
        }

        public void setUserId(String userId) {
            UserId = userId;
        }

        public String getPassword() {
            return Password;
        }

        public void setPassword(String password) {
            Password = password;
        }

        public String getP1() {
            return P1;
        }

        public void setP1(String p1) {
            P1 = p1;
        }

        public String getP2() {
            return P2;
        }

        public void setP2(String p2) {
            P2 = p2;
        }

        public String getP3() {
            return P3;
        }

        public void setP3(String p3) {
            P3 = p3;
        }

        public String getP4() {
            return P4;
        }

        public void setP4(String p4) {
            P4 = p4;
        }

        public String getP5() {
            return P5;
        }

        public void setP5(String p5) {
            P5 = p5;
        }
    }
}
