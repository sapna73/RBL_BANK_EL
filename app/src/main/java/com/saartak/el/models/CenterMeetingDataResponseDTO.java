package com.saartak.el.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.saartak.el.database.entity.CenterMeetingTable;

import java.util.ArrayList;

public class CenterMeetingDataResponseDTO {

    @SerializedName("Table")
    @Expose
    ArrayList<CenterMeetingTable> CenterMeetingTable;

    public ArrayList<com.saartak.el.database.entity.CenterMeetingTable> getCenterMeetingTable() {
        return CenterMeetingTable;
    }

    public void setCenterMeetingTable(ArrayList<com.saartak.el.database.entity.CenterMeetingTable> centerMeetingTable) {
        CenterMeetingTable = centerMeetingTable;
    }
}

