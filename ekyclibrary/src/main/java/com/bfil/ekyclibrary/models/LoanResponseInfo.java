package com.bfil.ekyclibrary.models;

/**
 * Created by Ali hussains on 8/17/2017.
 */

public class LoanResponseInfo {

    private String ResponseCode;
    private String ResponseMsg;
    private String LoanID;
    private String Amount;
    private String FrequnceyType;
    private String Frequency;
    private String startdate;
    private String EndDate;
    private String clientloancode;

    public LoanResponseInfo(){
        ResponseCode="";
        ResponseMsg="";
        LoanID="";
        Amount="";
        FrequnceyType="";
        Frequency="";
        startdate="";
        EndDate="";
        clientloancode="";
    }

    public String getResponseCode() {
        return ResponseCode;
    }

    public void setResponseCode(String responseCode) {
        ResponseCode = responseCode;
    }

    public String getResponseMsg() {
        return ResponseMsg;
    }

    public void setResponseMsg(String responseMsg) {
        ResponseMsg = responseMsg;
    }

    public String getLoanID() {
        return LoanID;
    }

    public void setLoanID(String loanID) {
        LoanID = loanID;
    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }
    public String getFrequnceyType() {
        return FrequnceyType;
    }

    public void setFrequnceyType(String frequnceyType) {
        FrequnceyType = frequnceyType;
    }

    public String getFrequency() {
        return Frequency;
    }

    public void setFrequency(String frequency) {
        Frequency = frequency;
    }
    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public String getEndDate() {
        return EndDate;
    }

    public void setEndDate(String endDate) {
        EndDate = endDate;
    }

    public String getClientloancode() {
        return clientloancode;
    }

    public void setClientloancode(String clientloancode) {
        this.clientloancode = clientloancode;
    }
}
