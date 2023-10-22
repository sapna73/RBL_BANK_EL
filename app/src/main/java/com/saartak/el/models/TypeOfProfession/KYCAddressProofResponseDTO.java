package com.saartak.el.models.TypeOfProfession;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class KYCAddressProofResponseDTO {

    @SerializedName("Table")
    @Expose
    ArrayList<GetAddressAddressProofTable> getAddressAddressProofTable;

    public ArrayList<GetAddressAddressProofTable> getGetAddressAddressProofTable() {
        return getAddressAddressProofTable;
    }

    public void setGetAddressAddressProofTable(ArrayList<GetAddressAddressProofTable> getAddressAddressProofTable) {
        this.getAddressAddressProofTable = getAddressAddressProofTable;
    }
}
