package com.swadhaar.los.models;

import com.google.gson.annotations.Expose;

public class ResetPasswordRequestDTO {
    @Expose
    private String ClientID;
    @Expose
    private String CreatedBy;
    @Expose
    private String CreatedDate;
    @Expose
    private String ServiceType;
    @Expose
    private String UniqueId;
    @Expose
    private RequestStringClass RequestString;

    public String getClientID() {
        return ClientID;
    }

    public void setClientID(String clientID) {
        ClientID = clientID;
    }

    public String getCreatedBy() {
        return CreatedBy;
    }

    public void setCreatedBy(String createdBy) {
        CreatedBy = createdBy;
    }

    public String getCreatedDate() {
        return CreatedDate;
    }

    public void setCreatedDate(String createdDate) {
        CreatedDate = createdDate;
    }

    public String getServiceType() {
        return ServiceType;
    }

    public void setServiceType(String serviceType) {
        ServiceType = serviceType;
    }

    public String getUniqueId() {
        return UniqueId;
    }

    public void setUniqueId(String uniqueId) {
        UniqueId = uniqueId;
    }

    public RequestStringClass getRequestString() {
        return RequestString;
    }

    public void setRequestString(RequestStringClass requestString) {
        RequestString = requestString;
    }

    public static class RequestStringClass {
        @Expose
        private ResetPasswordRequestClass ResetPasswordRequest;

        public ResetPasswordRequestClass getResetPasswordRequest() {
            return ResetPasswordRequest;
        }

        public void setResetPasswordRequest(ResetPasswordRequestClass resetPasswordRequest) {
            ResetPasswordRequest = resetPasswordRequest;
        }
    }

    public static class ResetPasswordRequestClass {
        @Expose
        private String UserId;
        @Expose
        private String MobileNumber;
        @Expose
        private String IMEINumber;
        @Expose
        private String P1;
        @Expose
        private String P2;
        @Expose
        private String P3;


        public String getUserId() {
            return UserId;
        }

        public void setUserId(String userId) {
            UserId = userId;
        }

        public String getMobileNumber() {
            return MobileNumber;
        }

        public void setMobileNumber(String mobileNumber) {
            MobileNumber = mobileNumber;
        }

        public String getIMEINumber() {
            return IMEINumber;
        }

        public void setIMEINumber(String IMEINumber) {
            this.IMEINumber = IMEINumber;
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
    }
}
