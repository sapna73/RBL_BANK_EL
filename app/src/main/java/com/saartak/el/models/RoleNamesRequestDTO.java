package com.saartak.el.models;

import com.google.gson.annotations.Expose;

public class RoleNamesRequestDTO {

    @Expose
    private String Key="GetRoleNames";
    @Expose
    private String UserID="";

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }
}
