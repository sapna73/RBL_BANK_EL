package com.saartak.el.models.PINCodeValidationLP;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class PinCodeResponseDTO {
    @SerializedName("Table")
    @Expose
    ArrayList<PinCodeResponseTable> pinCodeResponseTables;

    public ArrayList<PinCodeResponseTable> getPinCodeResponseTables() {
        return pinCodeResponseTables;
    }

    public void setPinCodeResponseTables(ArrayList<PinCodeResponseTable> pinCodeResponseTables) {
        this.pinCodeResponseTables = pinCodeResponseTables;
    }
}
