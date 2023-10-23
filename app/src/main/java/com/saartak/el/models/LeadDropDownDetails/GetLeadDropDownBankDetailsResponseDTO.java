package com.saartak.el.models.LeadDropDownDetails;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class GetLeadDropDownBankDetailsResponseDTO {
    @SerializedName("Table")
    @Expose
    ArrayList<GetLeadDropDownBankDetailsTable> getLeadDropDownBankDetailsTables;

    public ArrayList<GetLeadDropDownBankDetailsTable> getGetLeadDropDownBankDetailsTables() {
        return getLeadDropDownBankDetailsTables;
    }

    public void setGetLeadDropDownBankDetailsTables(ArrayList<GetLeadDropDownBankDetailsTable> getLeadDropDownBankDetailsTables) {
        this.getLeadDropDownBankDetailsTables = getLeadDropDownBankDetailsTables;
    }
}
