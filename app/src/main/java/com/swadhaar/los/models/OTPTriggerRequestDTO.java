package com.swadhaar.los.models;

import com.google.gson.annotations.Expose;

public class OTPTriggerRequestDTO {
    @Expose
    private String MobileNumber;
    @Expose
    private String MessageText;
    @Expose
    private String Purpose;
    @Expose
    private String IMEINumber;
    @Expose
    private String P1;
    @Expose
    private String P2;
    @Expose
    private String P3;
    @Expose
    private String P4;


    public String getIMEINumber() {
        return IMEINumber;
    }

    public void setIMEINumber(String IMEINumber) {
        this.IMEINumber = IMEINumber;
    }

    public String getMobileNumber() {
        return MobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        MobileNumber = mobileNumber;
    }

    public String getMessageText() {
        return MessageText;
    }

    public void setMessageText(String messageText) {
        MessageText = messageText;
    }

    public String getPurpose() {
        return Purpose;
    }

    public void setPurpose(String purpose) {
        Purpose = purpose;
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
}
