package com.saartak.el.models.RATSourseOfIncome;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CustomerTypeDropDownResponseDTO {

    @SerializedName("Table")
    @Expose
    ArrayList<SPCustomerTypeResponseTable> getLeadDropDownTypeOfProfessionTable;

    public ArrayList<SPCustomerTypeResponseTable> getGetLeadDropDownTypeOfProfessionTable() {
        return getLeadDropDownTypeOfProfessionTable;
    }

    public void setGetLeadDropDownTypeOfProfessionTable(ArrayList<SPCustomerTypeResponseTable> getLeadDropDownTypeOfProfessionTable) {
        this.getLeadDropDownTypeOfProfessionTable = getLeadDropDownTypeOfProfessionTable;
    }
}
