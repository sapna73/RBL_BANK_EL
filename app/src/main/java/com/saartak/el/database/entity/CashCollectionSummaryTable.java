package com.saartak.el.database.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

@Entity
public class CashCollectionSummaryTable {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @SerializedName("id")
    @Expose (serialize = false, deserialize = false)
    private int id;

    @SerializedName("CenterName")
    @Expose
    private String CenterName;

    @SerializedName("CenterDue")
    @Expose
    private int CenterDue;

    @SerializedName("CashCollection")
    @Expose
    private int CashCollection;

    @SerializedName("SavingsCollection")
    @Expose
    private int SavingsCollection;

    @SerializedName("DigitalCollection")
    @Expose
    private int DigitalCollection;

    @SerializedName("TotalCollection")
    @Expose
    private int TotalCollection;

    @SerializedName("CollectionDate")
    @Expose
    private Date CollectionDate;

    @SerializedName("Confirm")
    @Expose
    private boolean Confirm;

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

    public CashCollectionSummaryTable() {

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

    public int getCenterDue() {
        return CenterDue;
    }

    public void setCenterDue(int centerDue) {
        CenterDue = centerDue;
    }

    public int getCashCollection() {
        return CashCollection;
    }

    public void setCashCollection(int cashCollection) {
        CashCollection = cashCollection;
    }

    public int getSavingsCollection() {
        return SavingsCollection;
    }

    public void setSavingsCollection(int savingsCollection) {
        SavingsCollection = savingsCollection;
    }

    public int getDigitalCollection() {
        return DigitalCollection;
    }

    public void setDigitalCollection(int digitalCollection) {
        DigitalCollection = digitalCollection;
    }

    public int getTotalCollection() {
        return TotalCollection;
    }

    public void setTotalCollection(int totalCollection) {
        TotalCollection = totalCollection;
    }

    public Date getCollectionDate() {
        return CollectionDate;
    }

    public void setCollectionDate(Date collectionDate) {
        CollectionDate = collectionDate;
    }

    public boolean isConfirm() {
        return Confirm;
    }
    public void setConfirm(boolean confirm) {
        Confirm = confirm;
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
