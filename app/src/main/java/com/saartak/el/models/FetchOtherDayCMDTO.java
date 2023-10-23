package com.saartak.el.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FetchOtherDayCMDTO {
    @Expose
    String centerName;
    @SerializedName("Selected")
    @Expose
    private boolean Selected;

    public String getCenterName() {
        return centerName;
    }

    public void setCenterName(String centerName) {
        this.centerName = centerName;
    }

    public boolean isSelected() {
        return Selected;
    }

    public void setSelected(boolean selected) {
        Selected = selected;
    }
}
