package com.saartak.el.database.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity
public class OverDueCMTable {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @SerializedName("id")
    @Expose (serialize = false, deserialize = false)
    private int id;

    @SerializedName("CenterId")
    @Expose
    private String CenterId;

    @SerializedName("Center Name")
    @Expose
    private String CenterName;

    @SerializedName("GroupId")
    @Expose
    private String GroupId;

    @SerializedName("GroupName")
    @Expose
    private String GroupName;

    @SerializedName("CustomerId")
    @Expose
    private String CustomerId;

    @SerializedName("Customer Name")
    @Expose
    private String CustomerName;

    @SerializedName("CustomerCode")
    @Expose
    private String CustomerCode;

    @SerializedName("BranchCode")
    @Expose
    private String BranchCode;

    @SerializedName("BranchName")
    @Expose
    private String BranchName;

    @SerializedName("LoanOfficerId")
    @Expose
    private String LoanOfficerId;

    @SerializedName("max_eligible_amount")
    @Expose
    private String max_eligible_amount;

    @SerializedName("product")
    @Expose
    private String product;

    @SerializedName("ProductName")
    @Expose
    private String ProductName;

    @SerializedName("CM Date")
    @Expose
    private String CenterMeetingDate;

    @SerializedName("CenterMeetingTime")
    @Expose
    private String CenterMeetingTime;

    @SerializedName("MemberRelationName")
    @Expose
    private String MemberRelationName;

    @SerializedName("EMI_Amount")
    @Expose
    private int EMI_Amount;

    @SerializedName("Total Due")
    @Expose
    private int Total_Due;

    @SerializedName("SavingsCollection")
    @Expose
    private int SavingsCollection=100;

    @SerializedName("LoanAccountNumber")
    @Expose
    private String LoanAccountNumber;

    @SerializedName("RequiredInstallmentAmount")
    @Expose
    private int RequiredInstallmentAmount;

    @SerializedName("Current_Installment")
    @Expose
    private int Current_Installment;

    @SerializedName("Total_Installment")
    @Expose
    private int Total_Installment;

    @SerializedName("Collection_Type")
    @Expose
    private String CollectionType;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getCustomerCode() {
        return CustomerCode;
    }

    public void setCustomerCode(String customerCode) {
        CustomerCode = customerCode;
    }

    public String getBranchCode() {
        return BranchCode;
    }

    public void setBranchCode(String branchCode) {
        BranchCode = branchCode;
    }

    public String getBranchName() {
        return BranchName;
    }

    public void setBranchName(String branchName) {
        BranchName = branchName;
    }

    public String getLoanOfficerId() {
        return LoanOfficerId;
    }

    public void setLoanOfficerId(String loanOfficerId) {
        LoanOfficerId = loanOfficerId;
    }

    public String getMax_eligible_amount() {
        return max_eligible_amount;
    }

    public void setMax_eligible_amount(String max_eligible_amount) {
        this.max_eligible_amount = max_eligible_amount;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getCenterMeetingDate() {
        return CenterMeetingDate;
    }

    public void setCenterMeetingDate(String centerMeetingDate) {
        this.CenterMeetingDate = centerMeetingDate;
    }

    public String getCenterMeetingTime() {
        return CenterMeetingTime;
    }

    public void setCenterMeetingTime(String centerMeetingTime) {
        CenterMeetingTime = centerMeetingTime;
    }

    public String getMemberRelationName() {
        return MemberRelationName;
    }

    public void setMemberRelationName(String memberRelationName) {
        MemberRelationName = memberRelationName;
    }

    public int getEMI_Amount() {
        return EMI_Amount;
    }

    public void setEMI_Amount(int EMI_Amount) {
        this.EMI_Amount = EMI_Amount;
    }

    public int getTotal_Due() {
        return Total_Due;
    }

    public void setTotal_Due(int total_Due) {
        Total_Due = total_Due;
    }

    public int getSavingsCollection() {
        return SavingsCollection;
    }

    public void setSavingsCollection(int savingsCollection) {
        SavingsCollection = savingsCollection;
    }

    public String getLoanAccountNumber() {
        return LoanAccountNumber;
    }

    public void setLoanAccountNumber(String loanAccountNumber) {
        LoanAccountNumber = loanAccountNumber;
    }

    public int getRequiredInstallmentAmount() {
        return RequiredInstallmentAmount;
    }

    public void setRequiredInstallmentAmount(int requiredInstallmentAmount) {
        RequiredInstallmentAmount = requiredInstallmentAmount;
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

    public String getCollectionType() {
        return CollectionType;
    }

    public void setCollectionType(String collectionType) {
        CollectionType = collectionType;
    }
}
