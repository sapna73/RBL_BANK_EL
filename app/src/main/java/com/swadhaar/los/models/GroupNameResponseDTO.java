package com.swadhaar.los.models;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;

public class GroupNameResponseDTO {

    @Expose
    ArrayList<Table> Table;

    public ArrayList<GroupNameResponseDTO.Table> getTable() {
        return Table;
    }

    public void setTable(ArrayList<GroupNameResponseDTO.Table> table) {
        Table = table;
    }

    public static class Table {
        @Expose
        private String NewGroupName;

        public String getNewGroupName() {
            return NewGroupName;
        }

        public void setNewGroupName(String newGroupName) {
            NewGroupName = newGroupName;
        }
    }
}
