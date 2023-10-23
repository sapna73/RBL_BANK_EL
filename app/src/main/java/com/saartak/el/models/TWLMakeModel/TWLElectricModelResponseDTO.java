package com.saartak.el.models.TWLMakeModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class TWLElectricModelResponseDTO {

    @SerializedName("Table")
    @Expose
    ArrayList<TWLElectricModelResponseTable> twlElectricModelResponseTables;

    public ArrayList<TWLElectricModelResponseTable> getTwlElectricModelResponseTables() {
        return twlElectricModelResponseTables;
    }

    public void setTwlElectricModelResponseTables(ArrayList<TWLElectricModelResponseTable> twlElectricModelResponseTables) {
        this.twlElectricModelResponseTables = twlElectricModelResponseTables;
    }
}
