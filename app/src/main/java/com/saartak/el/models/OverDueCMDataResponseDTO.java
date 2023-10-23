package com.saartak.el.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.saartak.el.database.entity.OverDueCMTable;

import java.util.ArrayList;

public class OverDueCMDataResponseDTO {

    @SerializedName("Table")
    @Expose
    ArrayList<OverDueCMTable> OverDueCMTable;

    public ArrayList<OverDueCMTable> getOverDueCMTable() {
        return OverDueCMTable;
    }

    public void setOverDueCMTable(ArrayList<OverDueCMTable> overDueCMTable) {
        OverDueCMTable = overDueCMTable;
    }
}

