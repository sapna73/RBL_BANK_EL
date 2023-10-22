package com.saartak.el.database.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity
public class StaffActivityTable {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @SerializedName("id")
    @Expose (serialize = false, deserialize = false)
    private int id;

    @SerializedName("CenterName")
    @Expose
    private String CenterName;

    @SerializedName("StaffId")
    @Expose
    private String StaffId;

    @SerializedName("Activity")
    @Expose
    private String Activity;

    @SerializedName("Status")
    @Expose
    private int Status;

    @SerializedName("TimeStamp")
    @Expose
    private String TimeStamp;

    @SerializedName("Battery")
    @Expose
    private int Battery;

    @SerializedName("NetworkSignal")
    @Expose
    private int NetworkSignal;

    @SerializedName("Latitude")
    @Expose
    private String Latitude;

    @SerializedName("Longitude")
    @Expose
    private String Longitude;

    @SerializedName("Sync")
    @Expose(serialize = false, deserialize = false)
    private boolean Sync;

    @SerializedName("SyncDateTime")
    @Expose(serialize = false, deserialize = false)
    private String SyncDateTime;

    @SerializedName("SyncResponse")
    @Expose(serialize = false, deserialize = false)
    private String SyncResponse;

    @SerializedName("RefId")
    @Expose
    private String refId;

    public StaffActivityTable() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCenterName() {
        return CenterName;
    }

    public void setCenterName(String centerName) {
        CenterName = centerName;
    }

    public String getStaffId() {
        return StaffId;
    }

    public void setStaffId(String staffId) {
        StaffId = staffId;
    }

    public String getActivity() {
        return Activity;
    }

    public void setActivity(String activity) {
        Activity = activity;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }

    public int getBattery() {
        return Battery;
    }

    public void setBattery(int battery) {
        Battery = battery;
    }

    public int getNetworkSignal() {
        return NetworkSignal;
    }

    public void setNetworkSignal(int networkSignal) {
        NetworkSignal = networkSignal;
    }

    public String getLatitude() {
        return Latitude;
    }

    public void setLatitude(String latitude) {
        Latitude = latitude;
    }

    public String getLongitude() {
        return Longitude;
    }

    public void setLongitude(String longitude) {
        Longitude = longitude;
    }

    public String getTimeStamp() {
        return TimeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        TimeStamp = timeStamp;
    }

    public String getSyncDateTime() {
        return SyncDateTime;
    }

    public void setSyncDateTime(String syncDateTime) {
        SyncDateTime = syncDateTime;
    }

    public boolean isSync() {
        return Sync;
    }

    public void setSync(boolean sync) {
        Sync = sync;
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
