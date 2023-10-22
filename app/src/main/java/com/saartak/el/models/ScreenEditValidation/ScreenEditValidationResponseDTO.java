package com.saartak.el.models.ScreenEditValidation;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ScreenEditValidationResponseDTO {

    @SerializedName("Table")
    @Expose
    ArrayList<ScreenEditValidationResponseTable> screenEditValidationResponseTable;

    public ArrayList<ScreenEditValidationResponseTable> getScreenEditValidationResponseTable() {
        return screenEditValidationResponseTable;
    }

    public void setScreenEditValidationResponseTable(ArrayList<ScreenEditValidationResponseTable> screenEditValidationResponseTable) {
        this.screenEditValidationResponseTable = screenEditValidationResponseTable;
    }
}
