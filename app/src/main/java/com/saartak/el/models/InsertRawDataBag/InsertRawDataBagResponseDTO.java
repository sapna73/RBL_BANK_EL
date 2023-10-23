package com.saartak.el.models.InsertRawDataBag;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class InsertRawDataBagResponseDTO {

    @SerializedName("Table")
    @Expose
    ArrayList<InsertRawDataBagResponseTable> insertRawDataBagResponseTable;

    public ArrayList<InsertRawDataBagResponseTable> getInsertRawDataBagResponseTable() {
        return insertRawDataBagResponseTable;
    }

    public void setInsertRawDataBagResponseTable(ArrayList<InsertRawDataBagResponseTable> insertRawDataBagResponseTable) {
        this.insertRawDataBagResponseTable = insertRawDataBagResponseTable;
    }
}
