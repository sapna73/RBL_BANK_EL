package com.saartak.el.models.TWLMakeModel;

import androidx.annotation.NonNull;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TWLTwowheelertypeResponseTable {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @SerializedName("ID")
    @Expose(serialize = false, deserialize = false)
    private int id;

    @SerializedName("Twowheelertype")
    @Expose
    private String twowheelertype;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTwowheelertype() {
        return twowheelertype;
    }

    public void setTwowheelertype(String twowheelertype) {
        this.twowheelertype = twowheelertype;
    }
}
