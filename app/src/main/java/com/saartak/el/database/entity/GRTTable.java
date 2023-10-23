package com.saartak.el.database.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity
public class GRTTable {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @SerializedName("id")
    @Expose (serialize = false, deserialize = false)
    private int id;

    @SerializedName("centerId")
    @Expose
    private String centerId;

    @SerializedName("centerName")
    @Expose
    private String centerName;

    @SerializedName("clientId")
    @Expose
    private String clientId;

    @SerializedName("clientName")
    @Expose
    private String clientName;

    @SerializedName("phoneNo")
    @Expose
    private String phoneNo;

    @SerializedName("loanId")
    @Expose
    private String loanId;

    @SerializedName("loanProduct")
    @Expose
    private String loanProduct;

    @SerializedName("loanProductCode")
    @Expose
    private String loanProductCode;

    @SerializedName("villageName")
    @Expose
    private String villageName;

    @SerializedName("villageId")
    @Expose
    private String villageId;

    @SerializedName("loan_type")
    @Expose
    private String loan_type;

    @SerializedName("Status")
    @Expose
    private String Status;

    @SerializedName("Remarks")
    @Expose
    private String Remarks;

    @SerializedName("sync")
    @Expose
    private boolean sync;

    @SerializedName("AllDataCaptured")
    @Expose
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
    @Expose
    private String response;

    @SerializedName("houseVerification")
    @Expose
    private boolean houseVerification;

    @SerializedName("memberDetailVerification")
    @Expose
    private boolean memberDetailVerification;

    @SerializedName("groupFormation")
    @Expose
    private boolean groupFormation;

    @SerializedName("grtRejected")
    @Expose
    private boolean grtRejected;

    @SerializedName("isExistingCustomer")
    @Expose
    private boolean isExistingCustomer;

    public GRTTable() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getLoanId() {
        return loanId;
    }

    public void setLoanId(String loanId) {
        this.loanId = loanId;
    }

    public String getLoanProduct() {
        return loanProduct;
    }

    public String getLoanProductCode() {
        return loanProductCode;
    }

    public void setLoanProductCode(String loanProductCode) {
        this.loanProductCode = loanProductCode;
    }

    public void setLoanProduct(String loanProduct) {
        this.loanProduct = loanProduct;
    }

    public String getVillageName() {
        return villageName;
    }

    public void setVillageName(String villageName) {
        this.villageName = villageName;
    }

    public String getVillageId() {
        return villageId;
    }

    public void setVillageId(String villageId) {
        this.villageId = villageId;
    }

    public String getLoan_type() {
        return loan_type;
    }

    public void setLoan_type(String loan_type) {
        this.loan_type = loan_type;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
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

    public boolean isHouseVerification() {
        return houseVerification;
    }

    public void setHouseVerification(boolean houseVerification) {
        this.houseVerification = houseVerification;
    }

    public boolean isMemberDetailVerification() {
        return memberDetailVerification;
    }

    public void setMemberDetailVerification(boolean memberDetailVerification) {
        this.memberDetailVerification = memberDetailVerification;
    }

    public boolean isGroupFormation() {
        return groupFormation;
    }

    public void setGroupFormation(boolean groupFormation) {
        this.groupFormation = groupFormation;
    }

    public boolean isGrtRejected() {
        return grtRejected;
    }

    public void setGrtRejected(boolean grtRejected) {
        this.grtRejected = grtRejected;
    }

    public boolean isExistingCustomer() {
        return isExistingCustomer;
    }

    public void setExistingCustomer(boolean existingCustomer) {
        isExistingCustomer = existingCustomer;
    }
}
