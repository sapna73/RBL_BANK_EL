package com.saartak.el.models;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;

public class RawDataCBResponseDTO {


    @Expose
    ArrayList<Table> Table;

    public ArrayList<RawDataCBResponseDTO.Table> getTable() {
        return Table;
    }

    public void setTable(ArrayList<RawDataCBResponseDTO.Table> table) {
        Table = table;
    }

    public static class Table {
        @Expose
        private String IL_AMOUNT_eligibility;

        public String getIL_AMOUNT_eligibility() {
            return IL_AMOUNT_eligibility;
        }

        public void setIL_AMOUNT_eligibility(String IL_AMOUNT_eligibility) {
            this.IL_AMOUNT_eligibility = IL_AMOUNT_eligibility;
        }
    }
}

