package com.saartak.el.models.TypeOfProfession;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class TypeOfProfessionResponseDTO {

    @SerializedName("Table")
    @Expose
    ArrayList<GetLeadDropDownTypeOfProfessionTable> getLeadDropDownTypeOfProfessionTable;

    public ArrayList<GetLeadDropDownTypeOfProfessionTable> getGetLeadDropDownTypeOfProfessionTable() {
        return getLeadDropDownTypeOfProfessionTable;
    }

    public void setGetLeadDropDownTypeOfProfessionTable(ArrayList<GetLeadDropDownTypeOfProfessionTable> getLeadDropDownTypeOfProfessionTable) {
        this.getLeadDropDownTypeOfProfessionTable = getLeadDropDownTypeOfProfessionTable;
    }
}
