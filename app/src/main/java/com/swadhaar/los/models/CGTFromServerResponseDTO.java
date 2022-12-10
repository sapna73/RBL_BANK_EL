package com.swadhaar.los.models;

import com.google.gson.annotations.Expose;
import com.swadhaar.los.database.entity.CGTTable;
import com.swadhaar.los.database.entity.GroupTable;
import com.swadhaar.los.database.entity.HouseVerificationTable;
import com.swadhaar.los.database.entity.LoanTable;

import java.util.ArrayList;
import java.util.List;

public class CGTFromServerResponseDTO {


    @Expose
    ArrayList<Table> Table;

    public ArrayList<CGTFromServerResponseDTO.Table> getTable() {
        return Table;
    }

    public void setTable(ArrayList<CGTFromServerResponseDTO.Table> table) {
        Table = table;
    }

    public static class Table {
        @Expose
        private String RawDataCGT;
        @Expose
        private String RawDataGroup;
        @Expose
        private String RawDataHouse;
        @Expose
        private String RawDataLoanRequest;

        public String getRawDataCGT() {
            return RawDataCGT;
        }

        public void setRawDataCGT(String rawDataCGT) {
            RawDataCGT = rawDataCGT;
        }

        public String getRawDataGroup() {
            return RawDataGroup;
        }

        public void setRawDataGroup(String rawDataGroup) {
            RawDataGroup = rawDataGroup;
        }

        public String getRawDataHouse() {
            return RawDataHouse;
        }

        public void setRawDataHouse(String rawDataHouse) {
            RawDataHouse = rawDataHouse;
        }

        public String getRawDataLoanRequest() {
            return RawDataLoanRequest;
        }

        public void setRawDataLoanRequest(String rawDataLoanRequest) {
            RawDataLoanRequest = rawDataLoanRequest;
        }
    }
}

