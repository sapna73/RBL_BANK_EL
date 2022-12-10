package com.swadhaar.los.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.swadhaar.los.database.entity.CashCollectionSummaryTable;
import com.swadhaar.los.database.entity.CashDenominationTable;
import com.swadhaar.los.database.entity.CenterMeetingAttendanceTable;
import com.swadhaar.los.database.entity.CenterMeetingCollectionTable;
import com.swadhaar.los.database.entity.StaffActivityTable;

import java.util.ArrayList;
import java.util.List;

public class CenterMeetingAllCollectionTablesDataRequestDTO {

    @Expose
    private String StaffId = "";
    @Expose
    private String Branch = "";
    @Expose
    private String SOD = "";
    @Expose
    private StaffDatabag StaffDatabag;

    // Getter Methods
    public String getStaffId() {
        return StaffId;
    }
    public String getBranch() {
        return Branch;
    }
    public String getSOD() {
        return SOD;
    }

    public StaffDatabag getStaffDatabag() {
        return StaffDatabag;
    }

    // Setter Methods
    public void setStaffId(String staffId) {
        StaffId = staffId;
    }
    public void setBranch(String branch) {
        Branch = branch;
    }
    public void setSOD(String SOD) {
        this.SOD = SOD;
    }

    public void setStaffDatabag(StaffDatabag staffDatabag) {
        this.StaffDatabag = staffDatabag;
    }

    public static class StaffDatabag {
        @SerializedName("CenterCollection")
        @Expose
        List<CenterMeetingCollectionTable> centerMeetingCollectionTableArrayList = new ArrayList<CenterMeetingCollectionTable>();

        @SerializedName("CenterMeetingAttendance")
        @Expose
        List<CenterMeetingAttendanceTable> centerMeetingAttendanceTableArrayList = new ArrayList<CenterMeetingAttendanceTable>();

        @SerializedName("CashCollectionSummary")
        @Expose
        List<CashCollectionSummaryTable> cashCollectionSummaryTableArrayList = new ArrayList<CashCollectionSummaryTable>();

        @SerializedName("CashDenomination")
        @Expose
        List<CashDenominationTable> cashDenominationTableArrayList = new ArrayList<CashDenominationTable>();

        @SerializedName("StaffActivity")
        @Expose
        List<StaffActivityTable> staffActivityTableArrayList = new ArrayList<StaffActivityTable>();

        public List<CenterMeetingCollectionTable> getCenterMeetingCollectionTableArrayList() {
            return centerMeetingCollectionTableArrayList;
        }

        public void setCenterMeetingCollectionTableArrayList(List<CenterMeetingCollectionTable> centerMeetingCollectionTableArrayList) {
            this.centerMeetingCollectionTableArrayList = centerMeetingCollectionTableArrayList;
        }

        public List<CenterMeetingAttendanceTable> getCenterMeetingAttendanceTableArrayList() {
            return centerMeetingAttendanceTableArrayList;
        }

        public void setCenterMeetingAttendanceTableArrayList(List<CenterMeetingAttendanceTable> centerMeetingAttendanceTableArrayList) {
            this.centerMeetingAttendanceTableArrayList = centerMeetingAttendanceTableArrayList;
        }

        public List<CashCollectionSummaryTable> getCashCollectionSummaryTableArrayList() {
            return cashCollectionSummaryTableArrayList;
        }

        public void setCashCollectionSummaryTableArrayList(List<CashCollectionSummaryTable> cashCollectionSummaryTableArrayList) {
            this.cashCollectionSummaryTableArrayList = cashCollectionSummaryTableArrayList;
        }

        public List<CashDenominationTable> getCashDenominationTableArrayList() {
            return cashDenominationTableArrayList;
        }

        public void setCashDenominationTableArrayList(List<CashDenominationTable> cashDenominationTableArrayList) {
            this.cashDenominationTableArrayList = cashDenominationTableArrayList;
        }

        public List<StaffActivityTable> getStaffActivityTableArrayList() {
            return staffActivityTableArrayList;
        }

        public void setStaffActivityTableArrayList(List<StaffActivityTable> staffActivityTableArrayList) {
            this.staffActivityTableArrayList = staffActivityTableArrayList;
        }
    }
}
