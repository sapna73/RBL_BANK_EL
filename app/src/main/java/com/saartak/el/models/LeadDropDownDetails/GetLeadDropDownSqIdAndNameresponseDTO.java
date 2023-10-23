package com.saartak.el.models.LeadDropDownDetails;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class GetLeadDropDownSqIdAndNameresponseDTO {
    @SerializedName("Table")
    @Expose
    ArrayList<GetLeadDropDownSqIdAndNameTable> getLeadDropDownSqIdAndNameTables;

    public ArrayList<GetLeadDropDownSqIdAndNameTable> getGetLeadDropDownSqIdAndNameTables() {
        return getLeadDropDownSqIdAndNameTables;
    }

    public void setGetLeadDropDownSqIdAndNameTables(ArrayList<GetLeadDropDownSqIdAndNameTable> getLeadDropDownSqIdAndNameTables) {
        this.getLeadDropDownSqIdAndNameTables = getLeadDropDownSqIdAndNameTables;
    }

}
