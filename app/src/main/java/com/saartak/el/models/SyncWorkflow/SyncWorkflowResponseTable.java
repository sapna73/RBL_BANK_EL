package com.saartak.el.models.SyncWorkflow;

import androidx.annotation.NonNull;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SyncWorkflowResponseTable {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @SerializedName("Id")
    @Expose(serialize = false, deserialize = false)
    private int id;

    @SerializedName("Status")
    @Expose
    private int Status;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }
}
