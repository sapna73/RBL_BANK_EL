package com.swadhaar.los.models;

import com.google.gson.annotations.Expose;

public class LoginRequestDTO {
    @Expose
    String UserID;
    @Expose
    String Password;
    @Expose
    String Key = "ValidateUserLogin";
    @Expose
    String UserSystemName = "Mobile";
    @Expose
    String UserIPAddress = "Mobile";
    @Expose
    String ApplicationType = "MobileApp";

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getKey() {
        return Key;
    }

    public void setKey(String key) {
        Key = key;
    }

    public String getUserSystemName() {
        return UserSystemName;
    }

    public void setUserSystemName(String userSystemName) {
        UserSystemName = userSystemName;
    }

    public String getUserIPAddress() {
        return UserIPAddress;
    }

    public void setUserIPAddress(String userIPAddress) {
        UserIPAddress = userIPAddress;
    }
}
