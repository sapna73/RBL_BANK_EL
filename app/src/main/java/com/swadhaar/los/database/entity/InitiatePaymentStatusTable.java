package com.swadhaar.los.database.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity
public class InitiatePaymentStatusTable {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @SerializedName("id")
    @Expose(serialize = false, deserialize = false)
    private int id;

    @SerializedName("Customer Id")
    @Expose
    private String CustomerId;

    @SerializedName("Customer Name")
    @Expose
    private String CustomerName;

    @SerializedName("Loan Account Number")
    @Expose
    private String LoanAccountNumber;

    @SerializedName("Center Name")
    @Expose
    private String CenterName;

    private int Due_Amount;

    @SerializedName("Mobile Number")
    @Expose
    private String MobileNumber;

    @SerializedName("Payment Status")
    @Expose
    private String PaymentStatus;

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
        this.CustomerId = customerId;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String customerName) {
        this.CustomerName = customerName;
    }

    public String getLoanAccountNumber() {
        return LoanAccountNumber;
    }

    public void setLoanAccountNumber(String loanAccountNumber) {
        this.LoanAccountNumber = loanAccountNumber;
    }

    public String getCenterName() {
        return CenterName;
    }

    public void setCenterName(String centerName) {
        this.CenterName = centerName;
    }

    public int getDue_Amount() {
        return Due_Amount;
    }

    public void setDue_Amount(int Due_Amount) {
        this.Due_Amount = Due_Amount;
    }

    public String getMobileNumber() {
        return MobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.MobileNumber = mobileNumber;
    }

    public String getPaymentStatus() {
        return PaymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.PaymentStatus = paymentStatus;
    }
}
