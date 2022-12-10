package com.swadhaar.los.models;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;

public class DBRResponseDTO {


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
        private String PickMtd;
        @Expose
        private String RuleInPercent;

        public String getPickMtd() {
            return PickMtd;
        }

        public void setPickMtd(String pickMtd) {
            PickMtd = pickMtd;
        }

        public String getRuleInPercent() {
            return RuleInPercent;
        }

        public void setRuleInPercent(String ruleInPercent) {
            RuleInPercent = ruleInPercent;
        }
    }
}


