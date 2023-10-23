package com.saartak.el.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CibilTenureMonthsModel {
    @SerializedName("TenureMonths")
    @Expose
    private String tenureMonths;

    @SerializedName("RequestedLoanAmount")
    @Expose
    private String requestedLoanAmount;

    @SerializedName("RateOfInterest")
    @Expose
    private String rateOfInterest;

    @SerializedName("EMI")
    @Expose
    private String emiAmount;

    @SerializedName("SNS_N_SNS_Y")
    @Expose
    private String sns_n_y;

    public String getTenureMonths() {
        return tenureMonths;
    }

    public void setTenureMonths(String tenureMonths) {
        this.tenureMonths = tenureMonths;
    }

    public String getRequestedLoanAmount() {
        return requestedLoanAmount;
    }

    public void setRequestedLoanAmount(String requestedLoanAmount) {
        this.requestedLoanAmount = requestedLoanAmount;
    }

    public String getRateOfInterest() {
        return rateOfInterest;
    }

    public void setRateOfInterest(String rateOfInterest) {
        this.rateOfInterest = rateOfInterest;
    }

    public String getSns_n_y() {
        return sns_n_y;
    }

    public void setSns_n_y(String sns_n_y) {
        this.sns_n_y = sns_n_y;
    }

    public String getEmiAmount() {
        return emiAmount;
    }

    public void setEmiAmount(String emiAmount) {
        this.emiAmount = emiAmount;
    }
}
