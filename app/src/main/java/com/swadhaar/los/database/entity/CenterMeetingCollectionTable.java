package com.swadhaar.los.database.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.swadhaar.los.database.converter.DateTimeConverter;

import java.util.Date;

@Entity (indices = {@Index(value = {"LoanID"},
        unique = true)})
public class CenterMeetingCollectionTable {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @SerializedName("id")
    @Expose (serialize = false, deserialize = false)
    private int id;

    @SerializedName("LoanID")
    @Expose
    private String LoanID;

    @SerializedName("CustomerId")
    @Expose
    private String CustomerId;

    @SerializedName("CustomerName")
    @Expose
    private String CustomerName;

    @SerializedName("GroupId")
    @Expose
    private String GroupId;

    @SerializedName("GroupName")
    @Expose
    private String GroupName;

    @SerializedName("CenterId")
    @Expose
    private String CenterId;

    @SerializedName("CenterName")
    @Expose
    private String CenterName;

    @SerializedName("CollectedAmount")
    @Expose
    private int CollectedAmount;

    @SerializedName("CashCollectedAmount")
    @Expose
    private int CashCollectedAmount=0;

    @SerializedName("DigitalCollectedAmount")
    @Expose
    private int DigitalCollectedAmount=0;

    @SerializedName("EMI")
    @Expose
    private int EMI;

    @SerializedName("TotalDue")
    @Expose
    private int TotalDue;

    @SerializedName("SavingsCollection")
    @Expose
    private int SavingsCollection;

    @SerializedName("SavingsCollectedAmount")
    @Expose(serialize = false, deserialize = false)
    private int SavingsCollectedAmount;

    @SerializedName("Reason")
    @Expose
    private String Reason;

    @SerializedName("CMDate")
    @Expose
    private Date CMDate;

    @SerializedName("CenterMeetingTime")
    @Expose
    private String CenterMeetingTime;

    @SerializedName("MemberRelationName")
    @Expose
    private String MemberRelationName;

    @SerializedName("CollectionDateTime")
    @Expose
    @TypeConverters(DateTimeConverter.class)
    private Date CollectionDateTime;

    @SerializedName("PaidByOtherMember")
    @Expose
    private boolean PaidByOtherMember;

    @SerializedName("PTPDate")
    @Expose
    private Date PTPDate;

    @SerializedName("DigitalPayment")
    @Expose
    private boolean DigitalPayment;

    @SerializedName("SavingsConfirm")
    @Expose
    private boolean SavingsConfirm;

    @SerializedName("Confirm")
    @Expose
    private boolean Confirm;

    @SerializedName("ConfirmCount")
    @Expose(serialize = false, deserialize = false)
    private int ConfirmCount=0;

    @SerializedName("isSaved")
    @Expose(serialize = false, deserialize = false)
    private boolean isSaved;

    @SerializedName("Sync")
    @Expose(serialize = false, deserialize = false)
    private boolean Sync;

    @SerializedName("SyncDateTime")
    @Expose(serialize = false, deserialize = false)
    private Date SyncDateTime;

    @SerializedName("StaffId")
    @Expose
    private String StaffId;

    @SerializedName("SyncResponse")
    @Expose(serialize = false, deserialize = false)
    private String SyncResponse;

    @SerializedName("RefId")
    @Expose
    private String refId;

    @SerializedName("Approved")
    @Expose
    private boolean approved=false;

    @SerializedName("DueDate")
    @Expose
    private Date DueDate;

    @SerializedName("CollectionType")
    @Expose
    private String CollectionType;
    // TODO: digital collection related fields
    @SerializedName("AccountCreated")
    @Expose(serialize = false, deserialize = false)
    boolean AccountCreated;
    @SerializedName("SmsTriggered")
    @Expose(serialize = false, deserialize = false)
    boolean SmsTriggered;
    @SerializedName("PaymentStatus")
    @Expose(serialize = false, deserialize = false)
    boolean PaymentStatus;
    @SerializedName("RequestId")
    @Expose(serialize = false, deserialize = false)
    String RequestId;

    public CenterMeetingCollectionTable() {

    }

    public int getConfirmCount() {
        return ConfirmCount;
    }

    public void setConfirmCount(int confirmCount) {
        ConfirmCount = confirmCount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLoanID() {
        return LoanID;
    }

    public void setLoanID(String loanID) {
        LoanID = loanID;
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

    public int getCollectedAmount() {
        return CollectedAmount;
    }

    public void setCollectedAmount(int collectedAmount) {
        CollectedAmount = collectedAmount;
    }

    public int getCashCollectedAmount() {
        return CashCollectedAmount;
    }

    public void setCashCollectedAmount(int cashCollectedAmount) {
        CashCollectedAmount = cashCollectedAmount;
    }

    public int getDigitalCollectedAmount() {
        return DigitalCollectedAmount;
    }

    public void setDigitalCollectedAmount(int digitalCollectedAmount) {
        DigitalCollectedAmount = digitalCollectedAmount;
    }

    public int getEMI() {
        return EMI;
    }

    public void setEMI(int EMI) {
        this.EMI = EMI;
    }

    public Date getCMDate() {
        return CMDate;
    }

    public void setCMDate(Date CMDate) {
        this.CMDate = CMDate;
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

    public Date getPTPDate() {
        return PTPDate;
    }

    public void setPTPDate(Date PTPDate) {
        this.PTPDate = PTPDate;
    }

    public int getSavingsCollection() {
        return SavingsCollection;
    }

    public void setSavingsCollection(int savingsCollection) {
        SavingsCollection = savingsCollection;
    }

    public int getSavingsCollectedAmount() {
        return SavingsCollectedAmount;
    }

    public void setSavingsCollectedAmount(int savingsCollectedAmount) {
        SavingsCollectedAmount = savingsCollectedAmount;
    }

    public int getTotalDue() {
        return TotalDue;
    }

    public void setTotalDue(int totalDue) {
        TotalDue = totalDue;
    }

    public String getReason() {
        return Reason;
    }

    public void setReason(String reason) {
        Reason = reason;
    }

    public Date getCollectionDateTime() {
        return CollectionDateTime;
    }

    public void setCollectionDateTime(Date collectionDateTime) {
        CollectionDateTime = collectionDateTime;
    }

    public boolean isConfirm() {
        return Confirm;
    }

    public void setConfirm(boolean confirm) {
        Confirm = confirm;
    }

    public boolean isSavingsConfirm() {
        return SavingsConfirm;
    }

    public void setSavingsConfirm(boolean savingsConfirm) {
        SavingsConfirm = savingsConfirm;
    }

    public boolean isDigitalPayment() {
        return DigitalPayment;
    }

    public void setDigitalPayment(boolean digitalPayment) {
        DigitalPayment = digitalPayment;
    }

    public boolean isPaidByOtherMember() {
        return PaidByOtherMember;
    }

    public void setPaidByOtherMember(boolean paidByOtherMember) {
        PaidByOtherMember = paidByOtherMember;
    }

    public boolean isSaved() {
        return isSaved;
    }

    public void setSaved(boolean saved) {
        isSaved = saved;
    }

    public boolean isSync() {
        return Sync;
    }

    public void setSync(boolean sync) {
        Sync = sync;
    }

    public Date getSyncDateTime() {
        return SyncDateTime;
    }

    public void setSyncDateTime(Date syncDateTime) {
        SyncDateTime = syncDateTime;
    }

    public String getStaffId() {
        return StaffId;
    }

    public void setStaffId(String staffId) {
        StaffId = staffId;
    }

    public String getSyncResponse() {
        return SyncResponse;
    }

    public void setSyncResponse(String syncResponse) {
        SyncResponse = syncResponse;
    }

    public String getRefId() {
        return refId;
    }

    public void setRefId(String refId) {
        this.refId = refId;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public Date getDueDate() {
        return DueDate;
    }

    public void setDueDate(Date dueDate) {
        DueDate = dueDate;
    }

    public String getCollectionType() {
        return CollectionType;
    }

    public void setCollectionType(String collectionType) {
        CollectionType = collectionType;
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
