package com.swadhaar.los.models;

import com.google.gson.annotations.Expose;

public class LogOutRequestDTO {
    @Expose
    private String UserID;

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }
}
