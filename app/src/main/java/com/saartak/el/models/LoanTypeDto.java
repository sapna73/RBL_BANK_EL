package com.saartak.el.models;

import com.google.gson.annotations.Expose;

public class LoanTypeDto {
    @Expose
    private String loanType;
    @Expose
    private int loanIcon;

    public LoanTypeDto(String loanType, int loanIcon) {
        this.loanType = loanType;
        this.loanIcon = loanIcon;
    }

    public String getLoanType() {
        return loanType;
    }

    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }

    public int getLoanIcon() {
        return loanIcon;
    }

    public void setLoanIcon(int loanIcon) {
        this.loanIcon = loanIcon;
    }
}
