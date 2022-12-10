package com.swadhaar.los.database.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity
public class TodayCollectionScheduledTable {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @SerializedName("id")
    @Expose (serialize = false, deserialize = false)
    private int id;

    @SerializedName("CenterId")
    @Expose
    private String CenterId;

    @SerializedName("CenterName")
    @Expose
    private String CenterName;

    @SerializedName("LoanOfficerId")
    @Expose
    private String LoanOfficerId;

    @SerializedName("centerMeetingDate")
    @Expose
    private String centerMeetingDate;

    @SerializedName("centerMeetingTime")
    @Expose
    private String centerMeetingTime;

    @SerializedName("membersCount")
    @Expose
    private String membersCount;

    @SerializedName("EMI_Amount")
    @Expose
    private int EMI_Amount;

    @SerializedName("Total_Due")
    @Expose
    private int Total_Due;

    @SerializedName("CollectedAmount")
    @Expose
    private int CollectedAmount;

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

    @SerializedName("Status")
    @Expose(serialize = false, deserialize = false)
    private String Status;

    @SerializedName("Sync")
    @Expose(serialize = false, deserialize = false)
    private boolean Sync;

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

    public String getLoanOfficerId() {
        return LoanOfficerId;
    }

    public void setLoanOfficerId(String loanOfficerId) {
        LoanOfficerId = loanOfficerId;
    }

    public String getCenterMeetingDate() {
        return centerMeetingDate;
    }

    public void setCenterMeetingDate(String centerMeetingDate) {
        this.centerMeetingDate = centerMeetingDate;
    }

    public String getCenterMeetingTime() {
        return centerMeetingTime;
    }

    public void setCenterMeetingTime(String centerMeetingTime) {
        this.centerMeetingTime = centerMeetingTime;
    }

    public String getMembersCount() {
        return membersCount;
    }

    public void setMembersCount(String membersCount) {
        this.membersCount = membersCount;
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

    public boolean isSync() {
        return Sync;
    }

    public void setSync(boolean sync) {
        Sync = sync;
    }

    public int getCollectedAmount() {
        return CollectedAmount;
    }

    public void setCollectedAmount(int collectedAmount) {
        CollectedAmount = collectedAmount;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }
}
