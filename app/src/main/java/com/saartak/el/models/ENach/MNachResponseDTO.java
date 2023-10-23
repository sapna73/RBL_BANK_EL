package com.saartak.el.models.ENach;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MNachResponseDTO {

    @SerializedName("Table")
    @Expose
    ArrayList<MNachResponseTable> mNachResponseTables;


    public ArrayList<MNachResponseTable> getmNachResponseTables() {
        return mNachResponseTables;
    }

    public void setmNachResponseTables(ArrayList<MNachResponseTable> mNachResponseTables) {
        this.mNachResponseTables = mNachResponseTables;
    }
}
