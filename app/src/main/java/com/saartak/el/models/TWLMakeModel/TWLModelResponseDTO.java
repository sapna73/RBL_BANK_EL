package com.saartak.el.models.TWLMakeModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class TWLModelResponseDTO {

    @SerializedName("Table")
    @Expose
    ArrayList<TWLModelResponseTable> twlModelResponseTables;


    public ArrayList<TWLModelResponseTable> getTwlModelResponseTables() {
        return twlModelResponseTables;
    }
}
