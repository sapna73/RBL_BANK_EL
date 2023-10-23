package com.saartak.el.database.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity
public class SODTable {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @SerializedName("id")
    @Expose (serialize = false, deserialize = false)
    private int id;

    @SerializedName("staffId")
    @Expose
    private String staffId;

    @SerializedName("staffName")
    @Expose
    private String staffName;

    @SerializedName("branchId")
    @Expose
    private String branchId;

    @SerializedName("branchGSTcode")
    @Expose
    private String branchGSTcode;

    @SerializedName("created_date")
    @Expose
    private String created_date;

    @SerializedName("isSOD")
    @Expose
    private boolean isSOD;

    @SerializedName("isEOD")
    @Expose
    private boolean isEOD;

    @SerializedName("SOD_Latitude")
    @Expose
    private String SOD_Latitude;

    @SerializedName("SOD_Longitue")
    @Expose
    private String SOD_Longitue;

    @SerializedName("EOD_Latitude")
    @Expose
    private String EOD_Latitude;

    @SerializedName("EOD_Longitude")
    @Expose
    private String EOD_Longitude;

    @SerializedName("sync")
    @Expose
    private boolean sync;

    @SerializedName("response")
    @Expose
    private String response;


    public SODTable() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
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

    public boolean isSOD() {
        return isSOD;
    }

    public void setSOD(boolean SOD) {
        isSOD = SOD;
    }

    public boolean isEOD() {
        return isEOD;
    }

    public void setEOD(boolean EOD) {
        isEOD = EOD;
    }

    public String getSOD_Latitude() {
        return SOD_Latitude;
    }

    public void setSOD_Latitude(String SOD_Latitude) {
        this.SOD_Latitude = SOD_Latitude;
    }

    public String getSOD_Longitue() {
        return SOD_Longitue;
    }

    public void setSOD_Longitue(String SOD_Longitue) {
        this.SOD_Longitue = SOD_Longitue;
    }

    public String getEOD_Latitude() {
        return EOD_Latitude;
    }

    public void setEOD_Latitude(String EOD_Latitude) {
        this.EOD_Latitude = EOD_Latitude;
    }

    public String getEOD_Longitude() {
        return EOD_Longitude;
    }

    public void setEOD_Longitude(String EOD_Longitude) {
        this.EOD_Longitude = EOD_Longitude;
    }

    public boolean isSync() {
        return sync;
    }

    public void setSync(boolean sync) {
        this.sync = sync;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
