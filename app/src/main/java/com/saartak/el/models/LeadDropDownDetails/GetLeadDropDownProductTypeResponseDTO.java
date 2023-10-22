package com.saartak.el.models.LeadDropDownDetails;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class GetLeadDropDownProductTypeResponseDTO {
    @SerializedName("Table")
    @Expose
    ArrayList<GetLeadDropDownProductTypeTable> getLeadDropDownProductTypeTables;

    public ArrayList<GetLeadDropDownProductTypeTable> getGetLeadDropDownProductTypeTables() {
        return getLeadDropDownProductTypeTables;
    }

    public void setGetLeadDropDownProductTypeTables(ArrayList<GetLeadDropDownProductTypeTable> getLeadDropDownProductTypeTables) {
        this.getLeadDropDownProductTypeTables = getLeadDropDownProductTypeTables;
    }
}
