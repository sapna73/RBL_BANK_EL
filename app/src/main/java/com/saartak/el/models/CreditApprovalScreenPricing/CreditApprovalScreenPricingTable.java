package com.saartak.el.models.CreditApprovalScreenPricing;

import androidx.annotation.NonNull;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CreditApprovalScreenPricingTable {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @SerializedName("ID")
    @Expose(serialize = false, deserialize = false)
    private int id;

    @SerializedName("LoanAmount")
    @Expose
    private String loanAmount;

    @SerializedName("ROI")
    @Expose
    private String roi;

    @SerializedName("Tenure")
    @Expose
    private String tenure;

    @SerializedName("EligibleEMI")
    @Expose
    private String eligibleEMI;

    @SerializedName("LoanScheme")
    @Expose
    private String loanScheme;


    public int getId() {
        return id;
    }

    public String getLoanAmount() {
        return loanAmount;
    }

    public String getRoi() {
        return roi;
    }

    public String getTenure() {
        return tenure;
    }

    public String getEligibleEMI() {
        return eligibleEMI;
    }

    public String getLoanScheme() {
        return loanScheme;
    }
}
