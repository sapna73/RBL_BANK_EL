package com.swadhaar.los.database.entity;


import android.app.Application;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

@Entity
public class WorkFlowTable {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("LoanProductName")
    @Expose
    private String LoanProductName;

    @SerializedName("LoanProductCode")
    @Expose
    private String LoanProductCode;

    @SerializedName("EKYC")
    @Expose
    private boolean EKYC;

    @SerializedName("CB")
    @Expose
    private boolean CB;

    @SerializedName("LoanApplication")
    @Expose
    private boolean LoanApplication;

    @SerializedName("CGT")
    @Expose
    private boolean CGT;

    @SerializedName("GRT")
    @Expose
    private boolean GRT;

    @SerializedName("ROI")
    @Expose
    private String ROI;

    @SerializedName("Tenure")
    @Expose
    private String Tenure;

    @SerializedName("MinAge")
    @Expose
    private String MinAge;

    @SerializedName("MaxAge")
    @Expose
    private String MaxAge;

    @SerializedName("MinAmount")
    @Expose
    private String MinAmount;

    @SerializedName("MaxAmount")
    @Expose
    private String MaxAmount;

    public WorkFlowTable() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLoanProductName() {
        return LoanProductName;
    }

    public void setLoanProductName(String loanProductName) {
        LoanProductName = loanProductName;
    }

    public String getLoanProductCode() {
        return LoanProductCode;
    }

    public void setLoanProductCode(String loanProductCode) {
        LoanProductCode = loanProductCode;
    }

    public boolean isEKYC() {
        return EKYC;
    }

    public void setEKYC(boolean EKYC) {
        this.EKYC = EKYC;
    }

    public boolean isCB() {
        return CB;
    }

    public void setCB(boolean CB) {
        this.CB = CB;
    }

    public boolean isLoanApplication() {
        return LoanApplication;
    }

    public void setLoanApplication(boolean loanApplication) {
        LoanApplication = loanApplication;
    }

    public boolean isCGT() {
        return CGT;
    }

    public void setCGT(boolean CGT) {
        this.CGT = CGT;
    }

    public boolean isGRT() {
        return GRT;
    }

    public void setGRT(boolean GRT) {
        this.GRT = GRT;
    }

    public String getROI() {
        return ROI;
    }

    public void setROI(String ROI) {
        this.ROI = ROI;
    }

    public String getTenure() {
        return Tenure;
    }

    public void setTenure(String tenure) {
        Tenure = tenure;
    }

    public String getMinAge() {
        return MinAge;
    }

    public void setMinAge(String minAge) {
        MinAge = minAge;
    }

    public String getMaxAge() {
        return MaxAge;
    }

    public void setMaxAge(String maxAge) {
        MaxAge = maxAge;
    }

    public String getMinAmount() {
        return MinAmount;
    }

    public void setMinAmount(String minAmount) {
        MinAmount = minAmount;
    }

    public String getMaxAmount() {
        return MaxAmount;
    }

    public void setMaxAmount(String maxAmount) {
        MaxAmount = maxAmount;
    }
}
