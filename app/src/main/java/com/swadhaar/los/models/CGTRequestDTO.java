package com.swadhaar.los.models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.swadhaar.los.database.entity.CGTAttendanceTable;
import com.swadhaar.los.database.entity.CGTTable;
import com.swadhaar.los.database.entity.GroupTable;
import com.swadhaar.los.database.entity.HouseVerificationTable;
import com.swadhaar.los.database.entity.LoanTable;

import java.util.List;

public class CGTRequestDTO {

    @SerializedName("centerId")
    @Expose
    private String centerId;

    @SerializedName("centerName")
    @Expose
    private String centerName;

    @SerializedName("villageName")
    @Expose
    private String villageName;

    @SerializedName("villageId")
    @Expose
    private String villageId;

    @SerializedName("zoneName")
    @Expose
    private String zoneName;

    @SerializedName("areaName")
    @Expose
    private String areaName;

    @SerializedName("createdBy")
    @Expose
    private String createdBy;

    @SerializedName("branchId")
    @Expose
    private String branchId;

    @SerializedName("branchGSTcode")
    @Expose
    private String branchGSTcode;

    @SerializedName("created_date")
    @Expose
    private String created_date;

    @SerializedName("NoOfMembers")
    @Expose
    private int NoOfMembers=0;

    @SerializedName("Loan Details")
    @Expose
    private List<LoanTable> loanTableList;

    @SerializedName("CGT Details")
    @Expose
    private List<CGTTable> cgtTableList;

    @SerializedName("House Details")
    @Expose
    private List<HouseVerificationTable> houseVerificationTableList;

    @SerializedName("Group Details")
    @Expose
    private List<GroupTable> groupTableList;

    @SerializedName("Attendance Details")
    @Expose
    private List<CGTAttendanceTable> cgtAttendanceTableList;

    public CGTRequestDTO() {

    }

    public String getCenterId() {
        return centerId;
    }

    public void setCenterId(String centerId) {
        this.centerId = centerId;
    }

    public String getCenterName() {
        return centerName;
    }

    public void setCenterName(String centerName) {
        this.centerName = centerName;
    }

    public String getVillageName() {
        return villageName;
    }

    public void setVillageName(String villageName) {
        this.villageName = villageName;
    }

    public String getVillageId() {
        return villageId;
    }

    public void setVillageId(String villageId) {
        this.villageId = villageId;
    }

    public String getZoneName() {
        return zoneName;
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getBranchGSTcode() {
        return branchGSTcode;
    }

    public void setBranchGSTcode(String branchGSTcode) {
        this.branchGSTcode = branchGSTcode;
    }

    public String getCreated_date() {
        return created_date;
    }

    public void setCreated_date(String created_date) {
        this.created_date = created_date;
    }

    public int getNoOfMembers() {
        return NoOfMembers;
    }

    public void setNoOfMembers(int noOfMembers) {
        NoOfMembers = noOfMembers;
    }

    public List<LoanTable> getLoanTableList() {
        return loanTableList;
    }

    public void setLoanTableList(List<LoanTable> loanTableList) {
        this.loanTableList = loanTableList;
    }

    public List<CGTTable> getCgtTableList() {
        return cgtTableList;
    }

    public void setCgtTableList(List<CGTTable> cgtTableList) {
        this.cgtTableList = cgtTableList;
    }

    public List<HouseVerificationTable> getHouseVerificationTableList() {
        return houseVerificationTableList;
    }

    public void setHouseVerificationTableList(List<HouseVerificationTable> houseVerificationTableList) {
        this.houseVerificationTableList = houseVerificationTableList;
    }

    public List<GroupTable> getGroupTableList() {
        return groupTableList;
    }

    public void setGroupTableList(List<GroupTable> groupTableList) {
        this.groupTableList = groupTableList;
    }

    public List<CGTAttendanceTable> getCgtAttendanceTableList() {
        return cgtAttendanceTableList;
    }

    public void setCgtAttendanceTableList(List<CGTAttendanceTable> cgtAttendanceTableList) {
        this.cgtAttendanceTableList = cgtAttendanceTableList;
    }
}
