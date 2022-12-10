package com.swadhaar.los.models;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;

public class CenterNameResponseDTO {

    @Expose
    ArrayList<Table> Table;

    public ArrayList<CenterNameResponseDTO.Table> getTable() {
        return Table;
    }

    public void setTable(ArrayList<CenterNameResponseDTO.Table> table) {
        Table = table;
    }

    public static class Table {
        @Expose
        private String NewCenterName;

        public String getNewCenterName() {
            return NewCenterName;
        }

        public void setNewCenterName(String newCenterName) {
            NewCenterName = newCenterName;
        }
    }
}
