package com.saartak.el.models.BreSnsManageResults;

import androidx.annotation.NonNull;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BreSnsManageResultsREsponseTable {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @SerializedName("ID")
    @Expose(serialize = false, deserialize = false)
    private int id;

    @SerializedName("STATUSCODE")
    @Expose
    private String statusCode;

    public int getId() {
        return id;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }
}
