package com.saartak.el.models.TWLMakeModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class TWLVariantResponseDTO {

    @SerializedName("Table")
    @Expose
    ArrayList<TWLVariantResponseTable> twlVariantResponseTables;

    public ArrayList<TWLVariantResponseTable> getTwlVariantResponseTables() {
        return twlVariantResponseTables;
    }
}
