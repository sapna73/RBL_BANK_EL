package com.saartak.el.models.LDAP_Login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginnewResponseDTO {


    private String UniqueId;
    private String ResponseCode;
    private String ResponseMessage;
    private String ErrorCode;
    private String ErrorMessage;
    private ApiResponseDTO ApiResponse;

    public String getUniqueId() {
        return UniqueId;
    }

    public void setUniqueId(String uniqueId) {
        UniqueId = uniqueId;
    }

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
        private LoginResponseDTO LoginResponse;
        public LoginResponseDTO getLoginResponse() {
            return LoginResponse;
        }

        public void setLoginResponse(LoginResponseDTO loginResponse) {
            LoginResponse = loginResponse;
        }




    }
    public class LoginResponseDTO{

        private String Status;
        private String Message;
        private String UserId;
        private String UserName;
        private String BranchId;
        private String BranchName;
        private String BranchGSTCode;
        private String BCID;
        private String Zone;
        private String Region;
        private String IsLatestApp;
        private String Token;
        private String P1;
        private String P2;
        private String P3;
        private String P4;
        private String P5;
        @Expose
        @SerializedName("D1")
        private String D1;
        @Expose
        @SerializedName("M1")
        private String M1;
        @Expose
        @SerializedName("Z1")
        private String Z1;

        public String getD1() {
            return D1;
        }

        public void setD1(String d1) {
            D1 = d1;
        }

        public String getM1() {
            return M1;
        }

        public void setM1(String m1) {
            M1 = m1;
        }

        public String getZ1() {
            return Z1;
        }
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

        public String getUserName() {
            return UserName;
        }

        public void setUserName(String userName) {
            UserName = userName;
        }

        public String getBranchId() {
            return BranchId;
        }

        public void setBranchId(String branchId) {
            BranchId = branchId;
        }

        public String getBranchName() {
            return BranchName;
        }

        public void setBranchName(String branchName) {
            BranchName = branchName;
        }

        public String getBranchGSTCode() {
            return BranchGSTCode;
        }

        public void setBranchGSTCode(String branchGSTCode) {
            BranchGSTCode = branchGSTCode;
        }

        public String getBCID() {
            return BCID;
        }

        public void setBCID(String BCID) {
            this.BCID = BCID;
        }

        public String getZone() {
            return Zone;
        }

        public void setZone(String zone) {
            Zone = zone;
        }

        public String getRegion() {
            return Region;
        }

        public void setRegion(String region) {
            Region = region;
        }

        public void setZ1(String z1) {
            Z1 = z1;
        }

        public String getIsLatestApp() {
            return IsLatestApp;
        }

        public void setIsLatestApp(String isLatestApp) {
            IsLatestApp = isLatestApp;
        }

        public String getToken() {
            return Token;
        }

        public void setToken(String token) {
            Token = token;
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
