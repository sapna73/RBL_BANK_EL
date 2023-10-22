package com.saartak.el.database.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity
public class CGTAttendanceTable {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @SerializedName("id")
    @Expose (serialize = false, deserialize = false)
    private int id;

    @SerializedName("loanId")
    @Expose
    private String loanId;

    @SerializedName("ClientId")
    @Expose
    private String ClientId;

    @SerializedName("ClientName")
    @Expose
    private String ClientName;

    @SerializedName("CenterId")
    @Expose
    private String CenterId;

    @SerializedName("CenterName")
    @Expose
    private String CenterName;

    @SerializedName("CGT1Attendance")
    @Expose
    private boolean CGT1Attendance;

    @SerializedName("CGT2Attendance")
    @Expose
    private boolean CGT2Attendance;

    @SerializedName("CGT1AbsentReason")
    @Expose
    private String CGT1AbsentReason="";

    @SerializedName("CGT2AbsentReason")
    @Expose
    private String CGT2AbsentReason="";

    @SerializedName("CreatedDate")
    @Expose
    private String CreatedDate;

    @SerializedName("Sync")
    @Expose(serialize = false, deserialize = false)
    private boolean Sync;

    @SerializedName("SyncDateTime")
    @Expose(serialize = false, deserialize = false)
    private String SyncDateTime;

    @SerializedName("CreatedBy")
    @Expose
    private String CreatedBy;

    @SerializedName("SyncResponse")
    @Expose(serialize = false, deserialize = false)
    private String SyncResponse;

    public CGTAttendanceTable() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLoanId() {
        return loanId;
    }

    public void setLoanId(String loanId) {
        this.loanId = loanId;
    }

    public String getClientId() {
        return ClientId;
    }

    public void setClientId(String clientId) {
        ClientId = clientId;
    }

    public String getClientName() {
        return ClientName;
    }

    public void setClientName(String clientName) {
        ClientName = clientName;
    }

    public String getCenterId() {
        return CenterId;
    }

    public void setCenterId(String centerId) {
        CenterId = centerId;
    }

    public String getCenterName() {
        return CenterName;
    }

    public void setCenterName(String centerName) {
        CenterName = centerName;
    }

    public boolean isCGT1Attendance() {
        return CGT1Attendance;
    }

    public void setCGT1Attendance(boolean CGT1Attendance) {
        this.CGT1Attendance = CGT1Attendance;
    }

    public boolean isCGT2Attendance() {
        return CGT2Attendance;
    }

    public void setCGT2Attendance(boolean CGT2Attendance) {
        this.CGT2Attendance = CGT2Attendance;
    }

    public String getCGT1AbsentReason() {
        return CGT1AbsentReason;
    }

    public void setCGT1AbsentReason(String CGT1AbsentReason) {
        this.CGT1AbsentReason = CGT1AbsentReason;
    }

    public String getCGT2AbsentReason() {
        return CGT2AbsentReason;
    }

    public void setCGT2AbsentReason(String CGT2AbsentReason) {
        this.CGT2AbsentReason = CGT2AbsentReason;
    }

    public String getCreatedBy() {
        return CreatedBy;
    }

    public void setCreatedBy(String createdBy) {
        CreatedBy = createdBy;
    }

    public boolean isSync() {
        return Sync;
    }

    public void setSync(boolean sync) {
        Sync = sync;
    }

    public String getCreatedDate() {
        return CreatedDate;
    }

    public void setCreatedDate(String createdDate) {
        CreatedDate = createdDate;
    }

    public String getSyncDateTime() {
        return SyncDateTime;
    }

    public void setSyncDateTime(String syncDateTime) {
        SyncDateTime = syncDateTime;
    }

    public String getSyncResponse() {
        return SyncResponse;
    }

    public void setSyncResponse(String syncResponse) {
        SyncResponse = syncResponse;
    }
}
