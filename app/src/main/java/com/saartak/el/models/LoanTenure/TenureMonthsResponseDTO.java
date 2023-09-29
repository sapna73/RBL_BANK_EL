package com.saartak.el.models.LoanTenure;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.saartak.el.models.NegitiveProfileList.NegitiveProfileListResponseTable;

import java.util.ArrayList;

public class TenureMonthsResponseDTO {

    @SerializedName("Table")
    @Expose
    ArrayList<TenureMonthsResponseTable> tenureMonthsResponseTable;

    public ArrayList<TenureMonthsResponseTable> getTenureMonthsResponseTable() {
        return tenureMonthsResponseTable;
    }

    public void setTenureMonthsResponseTable(ArrayList<TenureMonthsResponseTable> tenureMonthsResponseTable) {
        this.tenureMonthsResponseTable = tenureMonthsResponseTable;
    }
}
