package com.saartak.el.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.saartak.el.database.entity.BranchProductFeatureMasterTable;

import java.util.ArrayList;

public class BranchProductFeatureMasterResponseDTO {

    @SerializedName("Table")
    @Expose
    ArrayList<BranchProductFeatureMasterTable> BranchProductFeatureMasterTable;


    public ArrayList<BranchProductFeatureMasterTable> getBranchProductFeatureMasterTable() {
        return BranchProductFeatureMasterTable;
    }

    public void setBranchProductFeatureMasterTable(ArrayList<BranchProductFeatureMasterTable> branchProductFeatureMasterTable) {
        BranchProductFeatureMasterTable = branchProductFeatureMasterTable;
    }
}
