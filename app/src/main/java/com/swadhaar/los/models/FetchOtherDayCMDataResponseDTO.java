package com.swadhaar.los.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.swadhaar.los.database.entity.CenterMeetingTable;
import com.swadhaar.los.database.entity.FetchOtherDayCMTable;

import java.util.ArrayList;

public class FetchOtherDayCMDataResponseDTO {

    @SerializedName("Table")
    @Expose
    ArrayList<FetchOtherDayCMTable> FetchOtherDayCMTable;

    public ArrayList<com.swadhaar.los.database.entity.FetchOtherDayCMTable> getFetchOtherDayCMTable() {
        return FetchOtherDayCMTable;
    }

    public void setFetchOtherDayCMTable(ArrayList<com.swadhaar.los.database.entity.FetchOtherDayCMTable> fetchOtherDayCMTable) {
        FetchOtherDayCMTable = fetchOtherDayCMTable;
    }
}

