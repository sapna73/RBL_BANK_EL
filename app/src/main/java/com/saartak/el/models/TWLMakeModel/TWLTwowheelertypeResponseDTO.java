package com.saartak.el.models.TWLMakeModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class TWLTwowheelertypeResponseDTO {

    @SerializedName("Table")
    @Expose
    ArrayList<TWLTwowheelertypeResponseTable> twlTwowheelertypeResponseTables;

    public ArrayList<TWLTwowheelertypeResponseTable> getTwlTwowheelertypeResponseTables() {
        return twlTwowheelertypeResponseTables;
    }

    public void setTwlTwowheelertypeResponseTables(ArrayList<TWLTwowheelertypeResponseTable> twlTwowheelertypeResponseTables) {
        this.twlTwowheelertypeResponseTables = twlTwowheelertypeResponseTables;
    }
}
