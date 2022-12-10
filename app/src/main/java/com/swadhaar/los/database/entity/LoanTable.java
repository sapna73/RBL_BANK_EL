package com.swadhaar.los.database.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity
public class LoanTable {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @SerializedName("id")
    @Expose (serialize = false, deserialize = false)
    private int id;

    @SerializedName("loanId")
    @Expose
    private String loanId;

    @SerializedName("clientId")
    @Expose
    private String clientId;

    @SerializedName("clientName")
    @Expose
    private String clientName;

    @SerializedName("isExistingCustomer")
    @Expose
    private boolean isExistingCustomer;

    @SerializedName("phoneNo")
    @Expose
    private String phoneNo;

    @SerializedName("loan_type")
    @Expose (serialize = false, deserialize = false)
    private String loan_type;

    @SerializedName("loan_amount")
    @Expose
    private String loan_amount;

    @SerializedName("ApplicationStatus")
    @Expose (serialize = false, deserialize = false)
    private String ApplicationStatus;

    @SerializedName("FinalStatus")
    @Expose (serialize = false, deserialize = false)
    private String FinalStatus;

    @SerializedName("Remarks")
    @Expose (serialize = false, deserialize = false)
    private String Remarks;

    @SerializedName("sync")
    @Expose (serialize = false, deserialize = false)
    private boolean sync;

    @SerializedName("AllDataCaptured")
    @Expose (serialize = false, deserialize = false)
    private boolean AllDataCaptured;

    @SerializedName("createdBy")
    @Expose
    private String createdBy;

    @SerializedName("branchId")
    @Expose
    private String branchId;

    @SerializedName("branchGSTcode")
    @Expose
    private String branchGSTcode;

    @SerializedName("created_date")
    @Expose
    private String created_date;

    @SerializedName("response")
    @Expose (serialize = false, deserialize = false)
    private String response;

    @SerializedName("centerId")
    @Expose
    private String centerId;

    @SerializedName("centerName")
    @Expose
    private String centerName;

    @SerializedName("loanProductName")
    @Expose
    private String loanProductName;

    @SerializedName("loanProductCode")
    @Expose
    private String loanProductCode;

    @SerializedName("isInterested")
    @Expose
    private boolean isInterested;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLoanId() {
        return loanId;
    }

    public void setLoanId(String loanId) {
        this.loanId = loanId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getLoan_type() {
        return loan_type;
    }

    public void setLoan_type(String loan_type) {
        this.loan_type = loan_type;
    }

    public String getLoan_amount() {
        return loan_amount;
    }

    public void setLoan_amount(String loan_amount) {
        this.loan_amount = loan_amount;
    }

    public String getApplicationStatus() {
        return ApplicationStatus;
    }

    public void setApplicationStatus(String applicationStatus) {
        ApplicationStatus = applicationStatus;
    }

    public boolean isExistingCustomer() {
        return isExistingCustomer;
    }

    public void setExistingCustomer(boolean existingCustomer) {
        isExistingCustomer = existingCustomer;
    }

    public String getFinalStatus() {
        return FinalStatus;
    }

    public void setFinalStatus(String finalStatus) {
        FinalStatus = finalStatus;
    }

    public String getRemarks() {
        return Remarks;
    }

    public void setRemarks(String remarks) {
        Remarks = remarks;
    }

    public boolean isSync() {
        return sync;
    }

    public void setSync(boolean sync) {
        this.sync = sync;
    }

    public boolean isAllDataCaptured() {
        return AllDataCaptured;
    }

    public void setAllDataCaptured(boolean allDataCaptured) {
        AllDataCaptured = allDataCaptured;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getBranchGSTcode() {
        return branchGSTcode;
    }

    public void setBranchGSTcode(String branchGSTcode) {
        this.branchGSTcode = branchGSTcode;
    }

    public String getCreated_date() {
        return created_date;
    }

    public void setCreated_date(String created_date) {
        this.created_date = created_date;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getCenterId() {
        return centerId;
    }

    public void setCenterId(String centerId) {
        this.centerId = centerId;
    }

    public String getCenterName() {
        return centerName;
    }

    public void setCenterName(String centerName) {
        this.centerName = centerName;
    }

    public String getLoanProductName() {
        return loanProductName;
    }

    public void setLoanProductName(String loanProductName) {
        this.loanProductName = loanProductName;
    }

    public String getLoanProductCode() {
        return loanProductCode;
    }

    public void setLoanProductCode(String loanProductCode) {
        this.loanProductCode = loanProductCode;
    }

    public boolean isInterested() {
        return isInterested;
    }

    public void setInterested(boolean interested) {
        isInterested = interested;
    }

    public LoanTable() {

    }

}
