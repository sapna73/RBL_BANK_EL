package com.saartak.el.database.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity
public class QCReSubmissionTable {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @SerializedName("id")
    @Expose (serialize = false, deserialize = false)
    private int id;

    @SerializedName("ExternalCustomerId")
    @Expose
    private String ExternalCustomerId;

    @SerializedName("CustomerId")
    @Expose
    private String CustomerId;

    @SerializedName("CustomerName")
    @Expose
    private String CustomerName;

    @SerializedName("CenterId")
    @Expose
    private String CenterId;

    @SerializedName("CenterName")
    @Expose
    private String CenterName;

    @SerializedName("GroupId")
    @Expose
    private String GroupId;

    @SerializedName("GroupName")
    @Expose
    private String GroupName;

    @SerializedName("LoanApplicationNumber")
    @Expose
    private String LoanApplicationNumber;

    @SerializedName("LoanAmount")
    @Expose
    private String LoanAmount;

    @SerializedName("ProductCode")
    @Expose
    private String ProductCode;

    @SerializedName("ProductName")
    @Expose
    private String ProductName;

    @SerializedName("BarcodeNo")
    @Expose
    private String BarcodeNo;

    @SerializedName("RejectReason")
    @Expose
    private String RejectReason;

    @SerializedName("createdBy")
    @Expose
    private String createdBy;


    public QCReSubmissionTable() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getExternalCustomerId() {
        return ExternalCustomerId;
    }

    public void setExternalCustomerId(String externalCustomerId) {
        ExternalCustomerId = externalCustomerId;
    }

    public String getCustomerId() {
        return CustomerId;
    }

    public void setCustomerId(String customerId) {
        CustomerId = customerId;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String customerName) {
        CustomerName = customerName;
    }

    public String getCenterId() {
        return CenterId;
    }

    public void setCenterId(String centerId) {
        CenterId = centerId;
    }

    public String getCenterName() {
        return CenterName;
    }

    public void setCenterName(String centerName) {
        CenterName = centerName;
    }

    public String getGroupId() {
        return GroupId;
    }

    public void setGroupId(String groupId) {
        GroupId = groupId;
    }

    public String getGroupName() {
        return GroupName;
    }

    public void setGroupName(String groupName) {
        GroupName = groupName;
    }

    public String getLoanApplicationNumber() {
        return LoanApplicationNumber;
    }

    public void setLoanApplicationNumber(String loanApplicationNumber) {
        LoanApplicationNumber = loanApplicationNumber;
    }

    public String getLoanAmount() {
        return LoanAmount;
    }

    public void setLoanAmount(String loanAmount) {
        LoanAmount = loanAmount;
    }

    public String getProductCode() {
        return ProductCode;
    }

    public void setProductCode(String productCode) {
        ProductCode = productCode;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getBarcodeNo() {
        return BarcodeNo;
    }

    public void setBarcodeNo(String barcodeNo) {
        BarcodeNo = barcodeNo;
    }

    public String getRejectReason() {
        return RejectReason;
    }

    public void setRejectReason(String rejectReason) {
        RejectReason = rejectReason;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
}
