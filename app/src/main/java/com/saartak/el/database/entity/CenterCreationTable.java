package com.saartak.el.database.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity
public class CenterCreationTable {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @SerializedName("id")
    @Expose (serialize = false, deserialize = false)
    private int id;

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

    @SerializedName("loan_type")
    @Expose (serialize = false, deserialize = false)
    private String loan_type;

    @SerializedName("Status")
    @Expose (serialize = false, deserialize = false)
    private String Status;

    @SerializedName("Remarks")
    @Expose (serialize = false, deserialize = false)
    private String Remarks;

    @SerializedName("sync")
    @Expose (serialize = false, deserialize = false)
    private boolean sync;

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
    @Expose (serialize = false, deserialize = false)
    private String response;

    @SerializedName("NoOfMembers")
    @Expose
    private int NoOfMembers=0;


    public CenterCreationTable() {

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

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getRemarks() {
        return Remarks;
    }

    public void setRemarks(String remarks) {
        Remarks = remarks;
    }

    public boolean isSync() {
        return sync;
    }

    public void setSync(boolean sync) {
        this.sync = sync;
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

    public String getZoneName() {
        return zoneName;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public int getNoOfMembers() {
        return NoOfMembers;
    }

    public void setNoOfMembers(int noOfMembers) {
        NoOfMembers = noOfMembers;
    }
}
