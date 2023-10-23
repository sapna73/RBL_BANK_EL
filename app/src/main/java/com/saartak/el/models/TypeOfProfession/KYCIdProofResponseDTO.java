package com.saartak.el.models.TypeOfProfession;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class KYCIdProofResponseDTO {

    @SerializedName("Table")
    @Expose
    ArrayList<GetKYCDropDownIDProofTable> getKYCDropDownIDProofTable;

    public ArrayList<GetKYCDropDownIDProofTable> getGetKYCDropDownIDProofTable() {
        return getKYCDropDownIDProofTable;
    }

    public void setGetKYCDropDownIDProofTable(ArrayList<GetKYCDropDownIDProofTable> getKYCDropDownIDProofTable) {
        this.getKYCDropDownIDProofTable = getKYCDropDownIDProofTable;
    }
}
