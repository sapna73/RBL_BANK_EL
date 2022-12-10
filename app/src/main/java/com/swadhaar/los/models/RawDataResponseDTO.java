package com.swadhaar.los.models;

import com.google.gson.annotations.Expose;
import com.swadhaar.los.database.entity.DynamicUITable;

import java.util.ArrayList;

public class RawDataResponseDTO {


    @Expose
    ArrayList<Table> Table;

    public ArrayList<RawDataResponseDTO.Table> getTable() {
        return Table;
    }

    public void setTable(ArrayList<RawDataResponseDTO.Table> table) {
        Table = table;
    }

    public static class Table {
        @Expose
        private int SerialNo;
        @Expose
        private String WorkFlowName;
        @Expose
        private int WorkFlowId;
        @Expose
        private String CustomerId;
        @Expose
        private String CustomerName;
        @Expose
        private String REQUESTEDLOANAMOUNT;
        @Expose
        private String CreatedOn;
        @Expose
        private String Rwas;
        @Expose
        private int ScreenId;

        @Expose
        private String BranchGSTCode;
        @Expose
        private String BranchName;
        @Expose
        private String ClusterName;
        @Expose
        private String RegionName;
        @Expose
        private String ClusterCode;
        @Expose
        private String RegionCode;


        public int getWorkFlowId() {
            return WorkFlowId;
        }

        public int getScreenId() {
            return ScreenId;
        }

        public String getRwas() {
            return Rwas;
        }

        public void setRwas(String rwas) {
            this.Rwas = rwas;
        }

        public int getSerialNo() {
            return SerialNo;
        }

        public String getWorkFlowName() {
            return WorkFlowName;
        }

        public String getCustomerId() {
            return CustomerId;
        }

        public String getCustomerName() {
            return CustomerName;
        }

        public String getREQUESTEDLOANAMOUNT() {
            return REQUESTEDLOANAMOUNT;
        }

        public String getCreatedOn() {
            return CreatedOn;
        }

        // Setter Methods

        public void setSerialNo(int SerialNo) {
            this.SerialNo = SerialNo;
        }

        public void setWorkFlowName(String WorkFlowName) {
            this.WorkFlowName = WorkFlowName;
        }

        public void setCustomerId(String CustomerId) {
            this.CustomerId = CustomerId;
        }

        public void setCustomerName(String CustomerName) {
            this.CustomerName = CustomerName;
        }

        public void setREQUESTEDLOANAMOUNT(String REQUESTEDLOANAMOUNT) {
            this.REQUESTEDLOANAMOUNT = REQUESTEDLOANAMOUNT;
        }

        public void setCreatedOn(String CreatedOn) {
            this.CreatedOn = CreatedOn;
        }

        public void setWorkFlowId(int workFlowId) {
            WorkFlowId = workFlowId;
        }

        public void setScreenId(int screenId) {
            ScreenId = screenId;
        }

        public String getBranchGSTCode() {
            return BranchGSTCode;
        }

        public void setBranchGSTCode(String branchGSTCode) {
            BranchGSTCode = branchGSTCode;
        }

        public String getBranchName() {
            return BranchName;
        }

        public void setBranchName(String branchName) {
            BranchName = branchName;
        }

        public String getClusterName() {
            return ClusterName;
        }

        public void setClusterName(String clusterName) {
            ClusterName = clusterName;
        }

        public String getRegionName() {
            return RegionName;
        }

        public void setRegionName(String regionName) {
            RegionName = regionName;
        }

        public String getClusterCode() {
            return ClusterCode;
        }

        public void setClusterCode(String clusterCode) {
            ClusterCode = clusterCode;
        }

        public String getRegionCode() {
            return RegionCode;
        }

        public void setRegionCode(String regionCode) {
            RegionCode = regionCode;
        }
    }
}

