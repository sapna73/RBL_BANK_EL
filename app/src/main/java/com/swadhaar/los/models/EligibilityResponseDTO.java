package com.swadhaar.los.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.swadhaar.los.database.entity.EligibilityTable;

import java.util.ArrayList;

public class EligibilityResponseDTO {

    @SerializedName("Table")
    @Expose
    ArrayList<EligibilityTable> EligibilityTable;


    public ArrayList<com.swadhaar.los.database.entity.EligibilityTable> getEligibilityTable() {
        return EligibilityTable;
    }

    public void setEligibilityTable(ArrayList<com.swadhaar.los.database.entity.EligibilityTable> eligibilityTable) {
        EligibilityTable = eligibilityTable;
    }
}
