package com.saartak.el.models.TWLMakeModel;

import androidx.annotation.NonNull;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TWLElectricModelResponseTable {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @SerializedName("ID")
    @Expose(serialize = false, deserialize = false)
    private int id;

    @SerializedName("ElectricModel")
    @Expose
    private String electricModel;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getElectricModel() {
        return electricModel;
    }

    public void setElectricModel(String electricModel) {
        this.electricModel = electricModel;
    }
}
