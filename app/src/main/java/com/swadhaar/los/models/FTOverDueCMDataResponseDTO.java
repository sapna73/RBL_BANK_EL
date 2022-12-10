package com.swadhaar.los.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.swadhaar.los.database.entity.FTOverDueCMTable;
import com.swadhaar.los.database.entity.OverDueCMTable;

import java.util.ArrayList;

public class FTOverDueCMDataResponseDTO {

    @SerializedName("Table")
    @Expose
    ArrayList<FTOverDueCMTable> FTOverDueCMTable;

    public ArrayList<FTOverDueCMTable> getFTOverDueCMTable() {
        return FTOverDueCMTable;
    }

    public void setFTOverDueCMTable(ArrayList<FTOverDueCMTable> overDueCMTable) {
        FTOverDueCMTable = overDueCMTable;
    }
}

