package com.saartak.el.database.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

@Entity
public class CenterMeetingAttendanceTable {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @SerializedName("id")
    @Expose (serialize = false, deserialize = false)
    private int id;

    @SerializedName("CustomerID")
    @Expose
    private String CustomerID;

    @SerializedName("CustomerName")
    @Expose
    private String CustomerName;

    @SerializedName("CenterId")
    @Expose
    private String CenterId;

    @SerializedName("CenterName")
    @Expose
    private String CenterName;

    @SerializedName("Attentance")
    @Expose
    private boolean Attentance;

    @SerializedName("Reason")
    @Expose
    private String Reason;

    @SerializedName("DateTime")
    @Expose
    private Date DateTime;

    @SerializedName("Sync")
    @Expose(serialize = false, deserialize = false)
    private boolean Sync;

    @SerializedName("SyncDateTime")
    @Expose(serialize = false, deserialize = false)
    private Date SyncDateTime;

    @SerializedName("StaffId")
    @Expose
    private String StaffId;

    @SerializedName("SyncResponse")
    @Expose(serialize = false, deserialize = false)
    private String SyncResponse;

    @SerializedName("RefId")
    @Expose
    private String refId;

    public CenterMeetingAttendanceTable() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustomerID() {
        return CustomerID;
    }

    public void setCustomerID(String customerID) {
        CustomerID = customerID;
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

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String customerName) {
        CustomerName = customerName;
    }

    public boolean isAttentance() {
        return Attentance;
    }

    public void setAttentance(boolean attentance) {
        Attentance = attentance;
    }

    public String getReason() {
        return Reason;
    }

    public void setReason(String reason) {
        Reason = reason;
    }

    public Date getDateTime() {
        return DateTime;
    }

    public void setDateTime(Date dateTime) {
        DateTime = dateTime;
    }

    public boolean isSync() {
        return Sync;
    }

    public void setSync(boolean sync) {
        Sync = sync;
    }

    public Date getSyncDateTime() {
        return SyncDateTime;
    }

    public void setSyncDateTime(Date syncDateTime) {
        SyncDateTime = syncDateTime;
    }

    public String getStaffId() {
        return StaffId;
    }

    public void setStaffId(String staffId) {
        StaffId = staffId;
    }

    public String getSyncResponse() {
        return SyncResponse;
    }

    public void setSyncResponse(String syncResponse) {
        SyncResponse = syncResponse;
    }

    public String getRefId() {
        return refId;
    }

    public void setRefId(String refId) {
        this.refId = refId;
    }
}
