package com.saartak.el.models.LoanTenure;

import androidx.annotation.NonNull;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TenureMonthsResponseTable {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @SerializedName("ID")
    @Expose(serialize = false, deserialize = false)
    private int id;

    @SerializedName("Tenure")
    @Expose
    private String tenure;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenure() {
        return tenure;
    }

    public void setTenure(String tenure) {
        this.tenure = tenure;
    }
}
