package com.saartak.el.models.ProcessPricingWF;

import androidx.annotation.NonNull;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProcessPricingWFResponseTable {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @SerializedName("ID")
    @Expose(serialize = false, deserialize = false)
    private int id;

    @SerializedName("Status")
    @Expose
    private String Status;

    @SerializedName("StatusCode")
    @Expose
    private int statusCode;


    public int getId() {
        return id;
    }

    public String getStatus() {
        return Status;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
