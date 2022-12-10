package com.swadhaar.los.models;

import com.google.gson.annotations.Expose;

public class WorkflowResponseDTO {

    @Expose
    private String StageName;
    @Expose
    private String ActionTaken;
    @Expose
    private String CreatedBy;
    @Expose
    private String RequestedLoanAmount;
    @Expose
    private String Tenure;
    @Expose
    private String CustomerId;
    @Expose
    private String Comment;
    @Expose
    private String RateOfInterest;
    @Expose
    private String Installment;
    @Expose
    private String CreatedOn;
    @Expose
    private String NextStage;

    public String getStageName() {
        return StageName;
    }

    public void setStageName(String stageName) {
        StageName = stageName;
    }

    public String getActionTaken() {
        return ActionTaken;
    }

    public void setActionTaken(String actionTaken) {
        ActionTaken = actionTaken;
    }

    public String getCreatedBy() {
        return CreatedBy;
    }

    public void setCreatedBy(String createdBy) {
        CreatedBy = createdBy;
    }

    public String getRequestedLoanAmount() {
        return RequestedLoanAmount;
    }

    public void setRequestedLoanAmount(String requestedLoanAmount) {
        RequestedLoanAmount = requestedLoanAmount;
    }

    public String getTenure() {
        return Tenure;
    }

    public void setTenure(String tenure) {
        Tenure = tenure;
    }

    public String getCustomerId() {
        return CustomerId;
    }

    public void setCustomerId(String customerId) {
        CustomerId = customerId;
    }

    public String getComment() {
        return Comment;
    }

    public void setComment(String comment) {
        Comment = comment;
    }

    public String getRateOfInterest() {
        return RateOfInterest;
    }

    public void setRateOfInterest(String rateOfInterest) {
        RateOfInterest = rateOfInterest;
    }

    public String getInstallment() {
        return Installment;
    }

    public void setInstallment(String installment) {
        Installment = installment;
    }

    public String getCreatedOn() {
        return CreatedOn;
    }

    public void setCreatedOn(String createdOn) {
        CreatedOn = createdOn;
    }

    public String getNextStage() {
        return NextStage;
    }

    public void setNextStage(String nextStage) {
        NextStage = nextStage;
    }
}
