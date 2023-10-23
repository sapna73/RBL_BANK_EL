package com.saartak.el.models.TWLMakeModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class TWLEngineCCResponseDTO {

    @SerializedName("Table")
    @Expose
    ArrayList<TWLEngineCCResponseTable> twlEngineCCResponseTables;

    public ArrayList<TWLEngineCCResponseTable> getTwlEngineCCResponseTables() {
        return twlEngineCCResponseTables;
    }

    public void setTwlEngineCCResponseTables(ArrayList<TWLEngineCCResponseTable> twlEngineCCResponseTables) {
        this.twlEngineCCResponseTables = twlEngineCCResponseTables;
    }
}
