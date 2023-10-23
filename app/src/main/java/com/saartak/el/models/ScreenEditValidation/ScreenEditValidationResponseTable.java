package com.saartak.el.models.ScreenEditValidation;

import androidx.annotation.NonNull;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ScreenEditValidationResponseTable {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @SerializedName("Id")
    @Expose(serialize = false, deserialize = false)
    private int id;

    @SerializedName("IsEditValue")
    @Expose
    private String isEditValue;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIsEditValue() {
        return isEditValue;
    }

    public void setIsEditValue(String isEditValue) {
        this.isEditValue = isEditValue;
    }
}
