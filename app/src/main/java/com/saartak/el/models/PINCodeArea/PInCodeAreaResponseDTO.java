package com.saartak.el.models.PINCodeArea;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class PInCodeAreaResponseDTO {

    @SerializedName("Table")
    @Expose
    ArrayList<PinCodeAreaResponseTable> pinCodeAreaResponseTables;

    public ArrayList<PinCodeAreaResponseTable> getPinCodeAreaResponseTables() {
        return pinCodeAreaResponseTables;
    }

    public void setPinCodeAreaResponseTables(ArrayList<PinCodeAreaResponseTable> pinCodeAreaResponseTables) {
        this.pinCodeAreaResponseTables = pinCodeAreaResponseTables;
    }
}
