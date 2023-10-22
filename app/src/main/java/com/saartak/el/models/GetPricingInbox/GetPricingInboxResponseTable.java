package com.saartak.el.models.GetPricingInbox;

import androidx.annotation.NonNull;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public  class GetPricingInboxResponseTable {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @SerializedName("ID")
    @Expose(serialize = false, deserialize = false)
    private int id;

    @SerializedName("ProjectId")
    @Expose
    private String projectId;

    @SerializedName("ProductId")
    @Expose
    private String ProductId;

    @SerializedName("BranchId")
    @Expose
    private String BranchId;

    @SerializedName("CustomerId")
    @Expose
    private String customerId;

    @SerializedName("CustomerName")
    @Expose
    private String customerName;

    @SerializedName("City")
    @Expose
    private String city;

    @SerializedName("ReqLoanAmount")
    @Expose
    private String reqLoanAmount;

    @SerializedName("ReqROI")
    @Expose
    private String reqROI;

    @SerializedName("ReqPF")
    @Expose
    private String reqPF;

    @SerializedName("ReqDocCharges")
    @Expose
    private String reqDocCharges;

    @SerializedName("ApproverRole")
    @Expose
    private String approverRole;

    @SerializedName("ApproverLevel")
    @Expose
    private String approverLevel;

    @SerializedName("Status")
    @Expose
    private String status;

    @SerializedName("CreatedBy")
    @Expose
    private String createdBy;

    @SerializedName("CreatedDateTime")
    @Expose
    private String createdDateTime;

    @SerializedName("UpdatedBy")
    @Expose
    private String updatedBy;

    @SerializedName("UpdatedDatetime")
    @Expose
    private String updatedDatetime;


    public int getId() {
        return id;
    }

    public String getProjectId() {
        return projectId;
    }

    public String getProductId() {
        return ProductId;
    }

    public String getBranchId() {
        return BranchId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCity() {
        return city;
    }

    public String getReqLoanAmount() {
        return reqLoanAmount;
    }

    public String getReqROI() {
        return reqROI;
    }

    public String getReqPF() {
        return reqPF;
    }

    public String getReqDocCharges() {
        return reqDocCharges;
    }

    public String getApproverRole() {
        return approverRole;
    }

    public String getApproverLevel() {
        return approverLevel;
    }

    public String getStatus() {
        return status;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public String getCreatedDateTime() {
        return createdDateTime;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public String getUpdatedDatetime() {
        return updatedDatetime;
    }
}
