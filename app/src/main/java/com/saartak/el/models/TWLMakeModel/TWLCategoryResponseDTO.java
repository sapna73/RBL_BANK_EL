package com.saartak.el.models.TWLMakeModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class TWLCategoryResponseDTO {

    @SerializedName("Table")
    @Expose
    ArrayList<TWLCategoryResponseTable> twlCategoryResponseTables;

    public ArrayList<TWLCategoryResponseTable> getTwlCategoryResponseTables() {
        return twlCategoryResponseTables;
    }

    public void setTwlCategoryResponseTables(ArrayList<TWLCategoryResponseTable> twlCategoryResponseTables) {
        this.twlCategoryResponseTables = twlCategoryResponseTables;
    }
}
