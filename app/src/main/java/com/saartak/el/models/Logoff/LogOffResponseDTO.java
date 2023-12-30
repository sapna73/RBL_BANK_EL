package com.saartak.el.models.Logoff;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.saartak.el.models.LoanTenure.TenureMonthsResponseTable;

import java.util.ArrayList;

public class LogOffResponseDTO {

    @SerializedName("Table")
    @Expose
    ArrayList<LogOffResponseTable> logOffResponseTables;

    public ArrayList<LogOffResponseTable> getLogOffResponseTables() {
        return logOffResponseTables;
    }

    public void setLogOffResponseTables(ArrayList<LogOffResponseTable> logOffResponseTables) {
        this.logOffResponseTables = logOffResponseTables;
    }
}
