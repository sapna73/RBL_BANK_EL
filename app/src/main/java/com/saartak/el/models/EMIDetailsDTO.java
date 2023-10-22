package com.saartak.el.models;

import com.google.gson.annotations.Expose;

import java.util.Date;

public class EMIDetailsDTO {

    @Expose
    String productCode;
    @Expose
    String LAN;
    @Expose
    String customerId;
    @Expose
    String customerName;
    @Expose
    String memberRelationName;
    @Expose
    int EMI;
    @Expose
    int totalDue;
    @Expose
    int collection;
    @Expose
    int Current_Installment;
    @Expose
    int Total_Installment;
    @Expose
    String arrearReason;
    @Expose
    boolean paidByOtherMember;
    @Expose
    Date PTPDate;
    // TODO: digital collection related fields
    @Expose
    boolean AccountCreated;
    @Expose
    boolean SmsTriggered;
    @Expose
    boolean PaymentStatus;
    @Expose
    String RequestId;

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getLAN() {
        return LAN;
    }

    public void setLAN(String LAN) {
        this.LAN = LAN;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getMemberRelationName() {
        return memberRelationName;
    }

    public void setMemberRelationName(String memberRelationName) {
        this.memberRelationName = memberRelationName;
    }

    public int getEMI() {
        return EMI;
    }

    public void setEMI(int EMI) {
        this.EMI = EMI;
    }

    public int getTotalDue() {
        return totalDue;
    }

    public void setTotalDue(int totalDue) {
        this.totalDue = totalDue;
    }

    public int getCollection() {
        return collection;
    }

    public void setCollection(int collection) {
        this.collection = collection;
    }

    public int getCurrent_Installment() {
        return Current_Installment;
    }

    public void setCurrent_Installment(int current_Installment) {
        Current_Installment = current_Installment;
    }

    public int getTotal_Installment() {
        return Total_Installment;
    }

    public void setTotal_Installment(int total_Installment) {
        Total_Installment = total_Installment;
    }

    public String getArrearReason() {
        return arrearReason;
    }

    public void setArrearReason(String arrearReason) {
        this.arrearReason = arrearReason;
    }

    public boolean isPaidByOtherMember() {
        return paidByOtherMember;
    }

    public void setPaidByOtherMember(boolean paidByOtherMember) {
        this.paidByOtherMember = paidByOtherMember;
    }

    public Date getPTPDate() {
        return PTPDate;
    }

    public void setPTPDate(Date PTPDate) {
        this.PTPDate = PTPDate;
    }

    public boolean isAccountCreated() {
        return AccountCreated;
    }

    public void setAccountCreated(boolean accountCreated) {
        AccountCreated = accountCreated;
    }

    public boolean isSmsTriggered() {
        return SmsTriggered;
    }

    public void setSmsTriggered(boolean smsTriggered) {
        SmsTriggered = smsTriggered;
    }

    public boolean isPaymentStatus() {
        return PaymentStatus;
    }

    public void setPaymentStatus(boolean paymentStatus) {
        PaymentStatus = paymentStatus;
    }

    public String getRequestId() {
        return RequestId;
    }

    public void setRequestId(String requestId) {
        RequestId = requestId;
    }
}
