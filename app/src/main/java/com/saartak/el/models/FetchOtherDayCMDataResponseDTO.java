package com.saartak.el.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.saartak.el.database.entity.FetchOtherDayCMTable;

import java.util.ArrayList;

public class FetchOtherDayCMDataResponseDTO {

    @SerializedName("Table")
    @Expose
    ArrayList<FetchOtherDayCMTable> FetchOtherDayCMTable;

    public ArrayList<com.saartak.el.database.entity.FetchOtherDayCMTable> getFetchOtherDayCMTable() {
        return FetchOtherDayCMTable;
    }

    public void setFetchOtherDayCMTable(ArrayList<com.saartak.el.database.entity.FetchOtherDayCMTable> fetchOtherDayCMTable) {
        FetchOtherDayCMTable = fetchOtherDayCMTable;
    }
}

