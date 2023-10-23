package com.saartak.el.models;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;

public class ColdCallDataResponseDTO {


    @Expose
    ArrayList<Table> Table;

    public ArrayList<ColdCallDataResponseDTO.Table> getTable() {
        return Table;
    }

    public void setTable(ArrayList<ColdCallDataResponseDTO.Table> table) {
        Table = table;
    }

    public static class Table {
        public String getResult() {
            return Result;
        }

        public void setResult(String result) {
            Result = result;
        }

        @Expose
        private String Result;
        @Expose
        private String UniqueId;
        @Expose
        private String BCID;
        @Expose
        private String BCBRID;
        @Expose
        private String CreatedOn;
        @Expose
        private int ScreenId;
        @Expose
        private String ProjectId;
        @Expose
        private String ProductId;
        @Expose
        private String Raws;

        public String getUniqueId() {
            return UniqueId;
        }

        public void setUniqueId(String uniqueId) {
            UniqueId = uniqueId;
        }

        public String getBCID() {
            return BCID;
        }

        public void setBCID(String BCID) {
            this.BCID = BCID;
        }

        public String getBCBRID() {
            return BCBRID;
        }

        public void setBCBRID(String BCBRID) {
            this.BCBRID = BCBRID;
        }

        public String getCreatedOn() {
            return CreatedOn;
        }

        public void setCreatedOn(String createdOn) {
            CreatedOn = createdOn;
        }

        public int getScreenId() {
            return ScreenId;
        }

        public void setScreenId(int screenId) {
            ScreenId = screenId;
        }

        public String getProjectId() {
            return ProjectId;
        }

        public void setProjectId(String projectId) {
            ProjectId = projectId;
        }

        public String getProductId() {
            return ProductId;
        }

        public void setProductId(String productId) {
            ProductId = productId;
        }

        public String getRaws() {
            return Raws;
        }

        public void setRaws(String raws) {
            Raws = raws;
        }
    }
}

