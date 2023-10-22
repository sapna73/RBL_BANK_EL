package com.saartak.el.models.LDAP_Login;

import com.google.gson.annotations.Expose;

public class LoginNewRequestDTO {

    @Expose
    private String UniqueId = "";

    @Expose
    private String ServiceType = "loginldap";

    @Expose
    private String ClientID = "";

    @Expose
    private String ExtCustId = "";

    @Expose
    private String CreatedDate = "";

    @Expose
    private String CreatedBy = "EL";

    @Expose
    private String RequestFrom = "";

    @Expose
    private RequestStringClass RequestString;

    public String getUniqueId() {
        return UniqueId;
    }

    public void setUniqueId(String uniqueId) {
        UniqueId = uniqueId;
    }

    public String getServiceType() {
        return ServiceType;
    }

    public void setServiceType(String serviceType) {
        ServiceType = serviceType;
    }

    public String getClientID() {
        return ClientID;
    }

    public void setClientID(String clientID) {
        ClientID = clientID;
    }

    public String getExtCustId() {
        return ExtCustId;
    }

    public void setExtCustId(String extCustId) {
        ExtCustId = extCustId;
    }

    public String getCreatedDate() {
        return CreatedDate;
    }

    public void setCreatedDate(String createdDate) {
        CreatedDate = createdDate;
    }

    public String getCreatedBy() {

        return CreatedBy;
    }

    public void setCreatedBy(String createdBy) {

        CreatedBy = createdBy;
    }

    public String getRequestFrom() {

        return RequestFrom;
    }

    public void setRequestFrom(String requestFrom) {

        RequestFrom = requestFrom;
    }

    public RequestStringClass getRequestString() {

        return RequestString;
    }

    public void setRequestString(RequestStringClass requestString) {
        RequestString = requestString;
    }

    public static class RequestStringClass {

        @Expose
        private LoginRequest loginRequest;

        public LoginRequest getLoginRequest() {
            return loginRequest;
        }

        public void setLoginRequest(LoginRequest loginRequest) {
            this.loginRequest = loginRequest;
        }

    }
    public static class LoginRequest {

        @Expose
        private String UserId;

        @Expose
        private String Password;

        @Expose
        private String RequestedByAppOrWeb = "app";

        @Expose
        private String LoginType = "api";

        @Expose
        private String AppVersion = "";

        @Expose
        private String SessionId = "";

        @Expose
        private String P1 = "";

        @Expose
        private String P2 = "";

        @Expose
        private String P3 = "";

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

        public String getRequestedByAppOrWeb() {
            return RequestedByAppOrWeb;
        }

        public void setRequestedByAppOrWeb(String requestedByAppOrWeb) {
            RequestedByAppOrWeb = requestedByAppOrWeb;
        }

        public String getLoginType() {
            return LoginType;
        }

        public void setLoginType(String loginType) {
            LoginType = loginType;
        }

        public String getAppVersion() {
            return AppVersion;
        }

        public void setAppVersion(String appVersion) {
            AppVersion = appVersion;
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