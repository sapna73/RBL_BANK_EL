package com.saartak.el.models;

import com.google.gson.annotations.Expose;

public class OTPTriggerRequestStringDTO {

    @Expose
    private OTPTriggerRequestDTO OTPTriggerRequest;

    public OTPTriggerRequestDTO getOTPTriggerRequest() {
        return OTPTriggerRequest;
    }

    public void setOTPTriggerRequest(OTPTriggerRequestDTO OTPTriggerRequest) {
        this.OTPTriggerRequest = OTPTriggerRequest;
    }
}
