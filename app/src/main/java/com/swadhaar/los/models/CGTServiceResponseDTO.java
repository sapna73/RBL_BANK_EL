package com.swadhaar.los.models;

import com.google.gson.annotations.Expose;
import com.swadhaar.los.database.entity.ProductMasterTable;

import java.util.ArrayList;

public class CGTServiceResponseDTO {


    @Expose
    ArrayList<Table> Table;

    public ArrayList<Table> getTable() {
        return Table;
    }

    public void setTable(ArrayList<Table> table) {
        Table = table;
    }

    public class Table {
        @Expose
        private String ResponseMessage;

        public String getResponseMessage() {
            return ResponseMessage;
        }

        public void setResponseMessage(String responseMessage) {
            ResponseMessage = responseMessage;
        }
    }
}

