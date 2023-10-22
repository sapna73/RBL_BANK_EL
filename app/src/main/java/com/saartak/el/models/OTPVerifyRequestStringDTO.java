package com.saartak.el.models;

import com.google.gson.annotations.Expose;

public class OTPVerifyRequestStringDTO {
    @Expose
    private OTPVerifyRequestDTO otpverifyRequest;

    public OTPVerifyRequestDTO getOtpverifyRequest() {
        return otpverifyRequest;
    }

    public void setOtpverifyRequest(OTPVerifyRequestDTO otpverifyRequest) {
        this.otpverifyRequest = otpverifyRequest;
    }
}
