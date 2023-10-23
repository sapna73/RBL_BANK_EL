package com.saartak.el.models.TWLMakeModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class TWLManufacturerResponseDTO {
    @SerializedName("Table")
    @Expose
    ArrayList<TWLManufacturerResponseTable> twlManufacturerResponseTables;


    public ArrayList<TWLManufacturerResponseTable> getTwlManufacturerResponseTables() {
        return twlManufacturerResponseTables;
    }

    public void setTwlManufacturerResponseTables(ArrayList<TWLManufacturerResponseTable> twlManufacturerResponseTables) {
        this.twlManufacturerResponseTables = twlManufacturerResponseTables;
    }
}
