package com.swadhaar.los.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.swadhaar.los.database.entity.CenterMeetingTable;

import java.util.ArrayList;
import java.util.List;

public class CenterMeetingDataResponseDTO {

    @SerializedName("Table")
    @Expose
    ArrayList<CenterMeetingTable> CenterMeetingTable;

    public ArrayList<com.swadhaar.los.database.entity.CenterMeetingTable> getCenterMeetingTable() {
        return CenterMeetingTable;
    }

    public void setCenterMeetingTable(ArrayList<com.swadhaar.los.database.entity.CenterMeetingTable> centerMeetingTable) {
        CenterMeetingTable = centerMeetingTable;
    }
}

