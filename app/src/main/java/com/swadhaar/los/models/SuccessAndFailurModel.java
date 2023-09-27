package com.swadhaar.los.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CibilSuccessAndFailurModel {
    @SerializedName("CibilSuccessAndFailur")
    @Expose
    private String isCibilSuccessAndFailur="";

    public String getIsCibilSuccessAndFailur() {
        return isCibilSuccessAndFailur;
    }

    public void setIsCibilSuccessAndFailur(String isCibilSuccessAndFailur) {
        this.isCibilSuccessAndFailur = isCibilSuccessAndFailur;
    }
}
