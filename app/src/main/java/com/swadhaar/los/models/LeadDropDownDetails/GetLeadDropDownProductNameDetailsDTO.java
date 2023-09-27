package com.swadhaar.los.models.LeadDropDownDetails;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class GetLeadDropDownProductNameDetails {
    @SerializedName("Table")
    @Expose
    ArrayList<GetLeadDropDownProductNameTable> getLeadDropDownProductNameTable;


    public ArrayList<GetLeadDropDownProductNameTable> getGetLeadDropDownProductNameTable() {
        return getLeadDropDownProductNameTable;
    }

    public void setGetLeadDropDownProductNameTable(ArrayList<GetLeadDropDownProductNameTable> getLeadDropDownProductNameTable) {
        this.getLeadDropDownProductNameTable = getLeadDropDownProductNameTable;
    }
}
