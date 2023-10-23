package com.saartak.el.models.LeadDropDownDetails;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class GetLeadDropDownDetailsResponseDTO {
    @SerializedName("Table")
    @Expose
    ArrayList<GetLeadDropDownBranchNameTable> getLeadDropDownBranchNameTables;

    public ArrayList<GetLeadDropDownBranchNameTable> getGetLeadDropDownBranchNameTables() {
        return getLeadDropDownBranchNameTables;
    }

    public void setGetLeadDropDownBranchNameTables(ArrayList<GetLeadDropDownBranchNameTable> getLeadDropDownBranchNameTables) {
        this.getLeadDropDownBranchNameTables = getLeadDropDownBranchNameTables;
    }
}
