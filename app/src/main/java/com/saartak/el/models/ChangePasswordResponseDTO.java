package com.saartak.el.models;

import com.google.gson.annotations.Expose;

public class ChangePasswordResponseDTO {

    @Expose
    private String ErrorMsg;
    @Expose
    private int ErrorCode;

    public String getErrorMsg() {
        return ErrorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        ErrorMsg = errorMsg;
    }

    public int getErrorCode() {
        return ErrorCode;
    }

    public void setErrorCode(int errorCode) {
        ErrorCode = errorCode;
    }
}
