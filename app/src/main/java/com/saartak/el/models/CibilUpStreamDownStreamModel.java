package com.saartak.el.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CibilUpStreamDownStreamModel {

    @SerializedName("isUpStreamSucces")
    @Expose
    private String isUpStreamSucces="";

    @SerializedName("isDownStreamSucces")
    @Expose
    private String isDownStreamSucces="";

    public String getIsUpStreamSucces() {
        return isUpStreamSucces;
    }

    public void setIsUpStreamSucces(String isUpStreamSucces) {
        this.isUpStreamSucces = isUpStreamSucces;
    }

    public String getIsDownStreamSucces() {
        return isDownStreamSucces;
    }

    public void setIsDownStreamSucces(String isDownStreamSucces) {
        this.isDownStreamSucces = isDownStreamSucces;
    }
}
