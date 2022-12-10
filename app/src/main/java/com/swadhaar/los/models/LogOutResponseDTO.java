package com.swadhaar.los.models;

import com.google.gson.annotations.Expose;

public class LogOutResponseDTO {
    @Expose
    private String Error;
    @Expose
    private String Message;

    public String getError() {
        return Error;
    }

    public void setError(String error) {
        Error = error;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }
}
