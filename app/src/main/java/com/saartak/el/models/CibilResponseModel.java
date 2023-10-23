package com.saartak.el.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CibilResponseModel {

    @SerializedName("isAccepctOrDecline")
    @Expose
    private String isAccepctOrDecline;

    @SerializedName("loanScheme")
    @Expose
    private String loanScheme;

    @SerializedName("isSubmit")
    @Expose
    private String isSubmit;

    @SerializedName("flag")
    @Expose
    private String flag;

    public String getIsAccepctOrDecline() {
        return isAccepctOrDecline;
    }

    public void setIsAccepctOrDecline(String isAccepctOrDecline) {
        this.isAccepctOrDecline = isAccepctOrDecline;
    }

    public String getLoanScheme() {
        return loanScheme;
    }

    public void setLoanScheme(String loanScheme) {
        this.loanScheme = loanScheme;
    }

    public String getIsSubmit() {
        return isSubmit;
    }

    public void setIsSubmit(String isSubmit) {
        this.isSubmit = isSubmit;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}
