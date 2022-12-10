package com.swadhaar.los.database.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

@Entity
public class CashDenominationTable {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @SerializedName("id")
    @Expose(serialize = false, deserialize = false)
    private int id;

    @SerializedName("count_2000")
    @Expose
    private int count_2000;

    @SerializedName("count_1000")
    @Expose
    private int count_1000;

    @SerializedName("count_500")
    @Expose
    private int count_500;

    @SerializedName("count_200")
    @Expose
    private int count_200;

    @SerializedName("count_100")
    @Expose
    private int count_100;

    @SerializedName("count_50")
    @Expose
    private int count_50;

    @SerializedName("count_20")
    @Expose
    private int count_20;

    @SerializedName("count_10")
    @Expose
    private int count_10;

    @SerializedName("count_5")
    @Expose
    private int count_5;

    @SerializedName("count_2")
    @Expose
    private int count_2;

    @SerializedName("count_1")
    @Expose
    private int count_1;

    @SerializedName("total_2000")
    @Expose
    private int total_2000;

    @SerializedName("total_1000")
    @Expose
    private int total_1000;

    @SerializedName("total_500")
    @Expose
    private int total_500;

    @SerializedName("total_200")
    @Expose
    private int total_200;

    @SerializedName("total_100")
    @Expose
    private int total_100;

    @SerializedName("total_50")
    @Expose
    private int total_50;

    @SerializedName("total_20")
    @Expose
    private int total_20;

    @SerializedName("total_10")
    @Expose
    private int total_10;

    @SerializedName("total_5")
    @Expose
    private int total_5;

    @SerializedName("total_2")
    @Expose
    private int total_2;

    @SerializedName("total_1")
    @Expose
    private int total_1;

    @SerializedName("total_count")
    @Expose
    private int total_count;

    @SerializedName("total_amount")
    @Expose
    private int total_amount;

    @SerializedName("StaffId")
    @Expose
    private String StaffId;

    @SerializedName("CMDate")
    @Expose
    private Date CMDate;

    @SerializedName("Sync")
    @Expose(serialize = false, deserialize = false)
    private boolean Sync;

    @SerializedName("SyncDateTime")
    @Expose(serialize = false, deserialize = false)
    private Date SyncDateTime;

    @SerializedName("SyncResponse")
    @Expose(serialize = false, deserialize = false)
    private String SyncResponse;

    @SerializedName("RefId")
    @Expose
    private String refId;


    public int getCount_2000() {
        return count_2000;
    }

    public void setCount_2000(int count_2000) {
        this.count_2000 = count_2000;
    }

    public int getCount_1000() {
        return count_1000;
    }

    public void setCount_1000(int count_1000) {
        this.count_1000 = count_1000;
    }

    public int getCount_500() {
        return count_500;
    }

    public void setCount_500(int count_500) {
        this.count_500 = count_500;
    }

    public int getCount_200() {
        return count_200;
    }

    public void setCount_200(int count_200) {
        this.count_200 = count_200;
    }

    public int getCount_100() {
        return count_100;
    }

    public void setCount_100(int count_100) {
        this.count_100 = count_100;
    }

    public int getCount_50() {
        return count_50;
    }

    public void setCount_50(int count_50) {
        this.count_50 = count_50;
    }

    public int getCount_20() {
        return count_20;
    }

    public void setCount_20(int count_20) {
        this.count_20 = count_20;
    }

    public int getCount_10() {
        return count_10;
    }

    public void setCount_10(int count_10) {
        this.count_10 = count_10;
    }

    public int getCount_5() {
        return count_5;
    }

    public void setCount_5(int count_5) {
        this.count_5 = count_5;
    }

    public int getCount_2() {
        return count_2;
    }

    public void setCount_2(int count_2) {
        this.count_2 = count_2;
    }

    public int getCount_1() {
        return count_1;
    }

    public void setCount_1(int count_1) {
        this.count_1 = count_1;
    }

    public int getTotal_2000() {
        return total_2000;
    }

    public void setTotal_2000(int total_2000) {
        this.total_2000 = total_2000;
    }

    public int getTotal_1000() {
        return total_1000;
    }

    public void setTotal_1000(int total_1000) {
        this.total_1000 = total_1000;
    }

    public int getTotal_500() {
        return total_500;
    }

    public void setTotal_500(int total_500) {
        this.total_500 = total_500;
    }

    public int getTotal_200() {
        return total_200;
    }

    public void setTotal_200(int total_200) {
        this.total_200 = total_200;
    }

    public int getTotal_100() {
        return total_100;
    }

    public void setTotal_100(int total_100) {
        this.total_100 = total_100;
    }

    public int getTotal_50() {
        return total_50;
    }

    public void setTotal_50(int total_50) {
        this.total_50 = total_50;
    }

    public int getTotal_20() {
        return total_20;
    }

    public void setTotal_20(int total_20) {
        this.total_20 = total_20;
    }

    public int getTotal_10() {
        return total_10;
    }

    public void setTotal_10(int total_10) {
        this.total_10 = total_10;
    }

    public int getTotal_5() {
        return total_5;
    }

    public void setTotal_5(int total_5) {
        this.total_5 = total_5;
    }

    public int getTotal_2() {
        return total_2;
    }

    public void setTotal_2(int total_2) {
        this.total_2 = total_2;
    }

    public int getTotal_1() {
        return total_1;
    }

    public void setTotal_1(int total_1) {
        this.total_1 = total_1;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTotal_count() {
        return total_count;
    }

    public void setTotal_count(int total_count) {
        this.total_count = total_count;
    }

    public int getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(int total_amount) {
        this.total_amount = total_amount;
    }

    public String getStaffId() {
        return StaffId;
    }

    public void setStaffId(String staffId) {
        StaffId = staffId;
    }

    public Date getCMDate() {
        return CMDate;
    }

    public void setCMDate(Date CMDate) {
        this.CMDate = CMDate;
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
