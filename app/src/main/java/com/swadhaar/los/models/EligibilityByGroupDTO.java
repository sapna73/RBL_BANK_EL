package com.swadhaar.los.models;

import com.google.gson.annotations.Expose;
import com.swadhaar.los.database.entity.EligibilityTable;

import java.util.List;

public class EligibilityByGroupDTO {
    @Expose
    String groupName;
    @Expose
    List<List<EligibilityTable>> listOfEligibilityProductList;

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public List<List<EligibilityTable>> getListOfEligibilityProductList() {
        return listOfEligibilityProductList;
    }

    public void setListOfEligibilityProductList(List<List<EligibilityTable>> listOfEligibilityProductList) {
        this.listOfEligibilityProductList = listOfEligibilityProductList;
    }
}
