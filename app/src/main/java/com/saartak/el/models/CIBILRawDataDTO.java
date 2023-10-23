package com.saartak.el.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CIBILRawDataDTO {

    @SerializedName("CustomerId")
    @Expose
    private String CustomerId = "";

    @SerializedName("Type")
    @Expose
    private String Type = "";

    @SerializedName("ModeType")
    @Expose
    private String ModeType = "";

    @SerializedName("CATEGORY")
    @Expose
    private String CATEGORY = "";

    @SerializedName("NAME")
    @Expose
    private String NAME = "";

    @SerializedName("DATE OF BIRTH")
    @Expose
    private String dob = "";

    @SerializedName("SCORE")
    @Expose
    private String SCORE = "";

    @SerializedName("Decision")
    @Expose
    private String Decision = "";

    @SerializedName("Reason")
    @Expose
    private String Reason = "";

    @SerializedName("DISBURSED AMOUNT")
    @Expose
    private String disbursedAmount = "";

    @SerializedName("OUSTANDING AMOUNT")
    @Expose
    private String outstandingAmount = "";

    @SerializedName("TOTAL INSTALLMENT AMOUNT")
    @Expose
    private String totalInstallmentAmount = "";

    @SerializedName("CurrentBalance")
    @Expose
    private String CurrentBalance = "";

    @SerializedName("Overdue Amount")
    @Expose
    private String overDueAmount = "";

    @SerializedName("MAX DPD")
    @Expose
    private String maxDPD = "";

    @SerializedName("AnyDefaultInPaymentHistory")
    @Expose
    private String AnyDefaultInPaymentHistory = "";

    @SerializedName("CreatedDate")
    @Expose
    private String CreatedDate = "";


    public String getCustomerId() {
        return CustomerId;
    }

    public void setCustomerId(String customerId) {
        CustomerId = customerId;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getModeType() {
        return ModeType;
    }

    public void setModeType(String modeType) {
        ModeType = modeType;
    }

    public String getCATEGORY() {
        return CATEGORY;
    }

    public void setCATEGORY(String CATEGORY) {
        this.CATEGORY = CATEGORY;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getSCORE() {
        return SCORE;
    }

    public void setSCORE(String SCORE) {
        this.SCORE = SCORE;
    }

    public String getDecision() {
        return Decision;
    }

    public void setDecision(String decision) {
        Decision = decision;
    }

    public String getReason() {
        return Reason;
    }

    public void setReason(String reason) {
        Reason = reason;
    }

    public String getDisbursedAmount() {
        return disbursedAmount;
    }

    public void setDisbursedAmount(String disbursedAmount) {
        this.disbursedAmount = disbursedAmount;
    }

    public String getOutstandingAmount() {
        return outstandingAmount;
    }

    public void setOutstandingAmount(String outstandingAmount) {
        this.outstandingAmount = outstandingAmount;
    }

    public String getTotalInstallmentAmount() {
        return totalInstallmentAmount;
    }

    public void setTotalInstallmentAmount(String totalInstallmentAmount) {
        this.totalInstallmentAmount = totalInstallmentAmount;
    }

    public String getCurrentBalance() {
        return CurrentBalance;
    }

    public void setCurrentBalance(String currentBalance) {
        CurrentBalance = currentBalance;
    }

    public String getOverDueAmount() {
        return overDueAmount;
    }

    public void setOverDueAmount(String overDueAmount) {
        this.overDueAmount = overDueAmount;
    }

    public String getMaxDPD() {
        return maxDPD;
    }

    public void setMaxDPD(String maxDPD) {
        this.maxDPD = maxDPD;
    }

    public String getAnyDefaultInPaymentHistory() {
        return AnyDefaultInPaymentHistory;
    }

    public void setAnyDefaultInPaymentHistory(String anyDefaultInPaymentHistory) {
        AnyDefaultInPaymentHistory = anyDefaultInPaymentHistory;
    }

    public String getCreatedDate() {
        return CreatedDate;
    }

    public void setCreatedDate(String createdDate) {
        CreatedDate = createdDate;
    }
}
