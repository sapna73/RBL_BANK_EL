package com.swadhaar.los.models.RATSourseOfIncome;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.swadhaar.los.models.TypeOfProfession.GetLeadDropDownTypeOfProfessionTable;

import java.util.ArrayList;

public class SourseOfIncomeDropDownResponseDTO {
    @SerializedName("Table")
    @Expose
    ArrayList<SPSourseOfIncomeResponseTable> spSourseOfIncomeResponseTables;

    public ArrayList<SPSourseOfIncomeResponseTable> getSpSourseOfIncomeResponseTables() {
        return spSourseOfIncomeResponseTables;
    }

    public void setSpSourseOfIncomeResponseTables(ArrayList<SPSourseOfIncomeResponseTable> spSourseOfIncomeResponseTables) {
        this.spSourseOfIncomeResponseTables = spSourseOfIncomeResponseTables;
    }
}
