package com.saartak.el.models.LoanAmountWisePricingDefaultValues;

import androidx.annotation.NonNull;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoanAmountWisePricingDefaultValuesResponseTable {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @SerializedName("ID")
    @Expose(serialize = false, deserialize = false)
    private int id;

    @SerializedName("DefaultROI")
    @Expose
    private String defaultROI;

    @SerializedName("DefaultProcessingFee")
    @Expose
    private String defaultProcessingFee;

    @SerializedName("DefaultDocumentationCharges")
    @Expose
    private String defaultDocumentationCharges;

    public int getId() {
        return id;
    }

    public String getDefaultROI() {
        return defaultROI;
    }

    public String getDefaultProcessingFee() {
        return defaultProcessingFee;
    }

    public String getDefaultDocumentationCharges() {
        return defaultDocumentationCharges;
    }
}
