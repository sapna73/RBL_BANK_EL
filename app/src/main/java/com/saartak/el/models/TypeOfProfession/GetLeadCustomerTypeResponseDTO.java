package com.saartak.el.models.TypeOfProfession;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class GetLeadCustomerTypeResponseDTO {

    @SerializedName("Table")
    @Expose
    ArrayList<GetLeadCustomerTypeTable> getLeadCustomerTypeTablesList;


    public ArrayList<GetLeadCustomerTypeTable> getGetLeadCustomerTypeTablesList() {
        return getLeadCustomerTypeTablesList;
    }

    public void setGetLeadCustomerTypeTablesList(ArrayList<GetLeadCustomerTypeTable> getLeadCustomerTypeTablesList) {
        this.getLeadCustomerTypeTablesList = getLeadCustomerTypeTablesList;
    }
}
