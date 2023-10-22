package com.saartak.el.models.GetPricingInbox;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class GetPricingInboxResponseDTO {

    @SerializedName("Table")
    @Expose
    ArrayList<GetPricingInboxResponseTable> getPricingInboxResponseTable;

    public ArrayList<GetPricingInboxResponseTable> getGetPricingInboxResponseTable() {
        return getPricingInboxResponseTable;
    }

    public void setGetPricingInboxResponseTable(ArrayList<GetPricingInboxResponseTable> getPricingInboxResponseTable) {
        this.getPricingInboxResponseTable = getPricingInboxResponseTable;
    }
}
