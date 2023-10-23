package com.saartak.el.models.RATSourseOfIncome;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

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
