package com.saartak.el.models.RATSourseOfIncome;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class IndustryTypeDropDownResponseDTO {

    @SerializedName("Table")
    @Expose
    ArrayList<SPIndustryTypeResponseTable> spIndustryTypeResponseTables;

    public ArrayList<SPIndustryTypeResponseTable> getSpIndustryTypeResponseTables() {
        return spIndustryTypeResponseTables;
    }

    public void setSpIndustryTypeResponseTables(ArrayList<SPIndustryTypeResponseTable> spIndustryTypeResponseTables) {
        this.spIndustryTypeResponseTables = spIndustryTypeResponseTables;
    }
}
