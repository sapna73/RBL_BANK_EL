package com.saartak.el.models.CreditApprovalScreenPricing;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CreditApprovalScreenPricingResponseDTO {

    @SerializedName("Table")
    @Expose
    ArrayList<CreditApprovalScreenPricingTable> creditApprovalScreenPricingTable;

    public ArrayList<CreditApprovalScreenPricingTable> getCreditApprovalScreenPricingTable() {
        return creditApprovalScreenPricingTable;
    }

    public void setCreditApprovalScreenPricingTable(ArrayList<CreditApprovalScreenPricingTable> creditApprovalScreenPricingTable) {
        this.creditApprovalScreenPricingTable = creditApprovalScreenPricingTable;
    }
}
