package com.swadhaar.los.database.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity
public class EligibilityTable {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @SerializedName("id")
    @Expose (serialize = false, deserialize = false)
    private int id;

    @SerializedName("CustomerId")
    @Expose
    private String CustomerId;

    @SerializedName("CustomerName")
    @Expose
    private String CustomerName;

    @SerializedName("LoanAccountNumber")
    @Expose
    private String LoanAccountNumber;

    @SerializedName("EMI_Amount")
    @Expose
    private int EMI_Amount;

    @SerializedName("ProductCode")
    @Expose
    private String ProductCode;

    @SerializedName("ProductName")
    @Expose
    private String ProductName;

    @SerializedName("LoanStatus")
    @Expose
    private String LoanStatus;

    @SerializedName("isInterested")
    @Expose
    private boolean isInterested;


    public EligibilityTable() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustomerId() {
        return CustomerId;
    }

    public void setCustomerId(String customerId) {
        CustomerId = customerId;
    }

    public String getLoanAccountNumber() {
        return LoanAccountNumber;
    }

    public void setLoanAccountNumber(String loanAccountNumber) {
        LoanAccountNumber = loanAccountNumber;
    }

    public int getEMI_Amount() {
        return EMI_Amount;
    }

    public void setEMI_Amount(int EMI_Amount) {
        this.EMI_Amount = EMI_Amount;
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

    public String getLoanStatus() {
        return LoanStatus;
    }

    public void setLoanStatus(String loanStatus) {
        LoanStatus = loanStatus;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String customerName) {
        CustomerName = customerName;
    }

    public boolean isInterested() {
        return isInterested;
    }

    public void setInterested(boolean interested) {
        isInterested = interested;
    }
}
