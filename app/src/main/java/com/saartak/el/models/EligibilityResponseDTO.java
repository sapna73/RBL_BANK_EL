package com.saartak.el.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.saartak.el.database.entity.EligibilityTable;

import java.util.ArrayList;

public class EligibilityResponseDTO {

    @SerializedName("Table")
    @Expose
    ArrayList<EligibilityTable> EligibilityTable;


    public ArrayList<com.saartak.el.database.entity.EligibilityTable> getEligibilityTable() {
        return EligibilityTable;
    }

    public void setEligibilityTable(ArrayList<com.saartak.el.database.entity.EligibilityTable> eligibilityTable) {
        EligibilityTable = eligibilityTable;
    }
}
