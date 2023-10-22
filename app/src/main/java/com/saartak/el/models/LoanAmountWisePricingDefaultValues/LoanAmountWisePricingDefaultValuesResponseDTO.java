package com.saartak.el.models.LoanAmountWisePricingDefaultValues;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class LoanAmountWisePricingDefaultValuesResponseDTO {

    @SerializedName("Table")
    @Expose
    ArrayList<LoanAmountWisePricingDefaultValuesResponseTable> loanAmountWisePricingDefaultValuesResponseTables;

    public ArrayList<LoanAmountWisePricingDefaultValuesResponseTable> getLoanAmountWisePricingDefaultValuesResponseTables() {
        return loanAmountWisePricingDefaultValuesResponseTables;
    }

    public void setLoanAmountWisePricingDefaultValuesResponseTables(ArrayList<LoanAmountWisePricingDefaultValuesResponseTable> loanAmountWisePricingDefaultValuesResponseTables) {
        this.loanAmountWisePricingDefaultValuesResponseTables = loanAmountWisePricingDefaultValuesResponseTables;
    }
}
