package com.swadhaar.los.database.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity
public class GroupTable {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @SerializedName("id")
    @Expose(serialize = false, deserialize = false)
    private int id;

    @SerializedName("groupId")
    @Expose
    private String groupId;

    @SerializedName("groupName")
    @Expose
    private String groupName;

    @SerializedName("clientId")
    @Expose
    private String clientId;

    @SerializedName("clientName")
    @Expose
    private String clientName;

    @SerializedName("isTeamLeaderOne")
    @Expose
    private boolean isTeamLeaderOne;


    @SerializedName("isTeamLeaderTwo")
    @Expose
    private boolean isTeamLeaderTwo;

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

    @SerializedName("loan_type")
    @Expose(serialize = false, deserialize = false)
    private String loan_type;


    @SerializedName("Remarks")
    @Expose(serialize = false, deserialize = false)
    private String Remarks;


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

    @SerializedName("response")
    @Expose(serialize = false, deserialize = false)
    private String response;

    public GroupTable() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getLoan_type() {
        return loan_type;
    }

    public void setLoan_type(String loan_type) {
        this.loan_type = loan_type;
    }

    public String getRemarks() {
        return Remarks;
    }

    public void setRemarks(String remarks) {
        Remarks = remarks;
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

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public boolean isTeamLeaderOne() {
        return isTeamLeaderOne;
    }

    public void setTeamLeaderOne(boolean teamLeaderOne) {
        isTeamLeaderOne = teamLeaderOne;
    }

    public boolean isTeamLeaderTwo() {
        return isTeamLeaderTwo;
    }

    public void setTeamLeaderTwo(boolean teamLeaderTwo) {
        isTeamLeaderTwo = teamLeaderTwo;
    }
}
