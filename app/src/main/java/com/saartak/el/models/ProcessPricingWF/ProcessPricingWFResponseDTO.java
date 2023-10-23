package com.saartak.el.models.ProcessPricingWF;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

public class ProcessPricingWFResponseDTO {

    @SerializedName("Table")
    @Expose
    ArrayList<ProcessPricingWFResponseTable> processPricingWFResponseTableList;


    public ArrayList<ProcessPricingWFResponseTable> getProcessPricingWFResponseTableList() {
        return processPricingWFResponseTableList;
    }

    public void setProcessPricingWFResponseTableList(ArrayList<ProcessPricingWFResponseTable> processPricingWFResponseTableList) {
        this.processPricingWFResponseTableList = processPricingWFResponseTableList;
    }
}
