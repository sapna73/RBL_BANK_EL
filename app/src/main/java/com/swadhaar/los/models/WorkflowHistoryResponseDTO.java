package com.swadhaar.los.models;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;

public class WorkflowHistoryResponseDTO {


    @Expose
    ArrayList<Table> Table;

    public ArrayList<WorkflowHistoryResponseDTO.Table> getTable() {
        return Table;
    }

    public void setTable(ArrayList<WorkflowHistoryResponseDTO.Table> table) {
        Table = table;
    }

    public static class Table {
        @Expose
        private int CurrentStage;
        @Expose
        private String CurrentStageName;
        @Expose
        private String CustomerUniqueId;
        @Expose
        private String FullName;
        @Expose
        private String StageRemark;
        @Expose
        private String CreatedOn;


        public void setCurrentStage(int currentStage) {
            CurrentStage = currentStage;
        }

        public void setCurrentStageName(String currentStageName) {
            CurrentStageName = currentStageName;
        }

        public void setCustomerUniqueId(String customerUniqueId) {
            CustomerUniqueId = customerUniqueId;
        }

        public int getCurrentStage() {
            return CurrentStage;
        }

        public String getCurrentStageName() {
            return CurrentStageName;
        }

        public String getCustomerUniqueId() {
            return CustomerUniqueId;
        }

        public String getFullName() {
            return FullName;
        }

        public void setFullName(String fullName) {
            FullName = fullName;
        }

        public void setStageRemark(String stageRemark) {
            StageRemark = stageRemark;
        }

        public String getStageRemark() {
            return StageRemark;
        }

        public String getCreatedOn() {
            return CreatedOn;
        }

        public void setCreatedOn(String createdOn) {
            CreatedOn = createdOn;
        }
    }

}
