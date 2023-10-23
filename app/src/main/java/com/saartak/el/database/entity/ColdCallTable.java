package com.saartak.el.database.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.saartak.el.database.converter.TimestampConverter;

import java.util.Date;

@Entity
public class ColdCallTable {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("CCTokenId")
    @Expose
    private String clientId;

    @SerializedName("TargetName")
    @Expose
    private String clientName;

    @SerializedName("MarketName")
    @Expose
    private String MarketName;

    @SerializedName("BusinessName")
    @Expose
    private String BusinessName;

    @SerializedName("MobileNo")
    @Expose
    private String MobileNo;

    @SerializedName("Lat")
    @Expose
    private String Lat="";

    @SerializedName("Long")
    @Expose
    private String Long="";

    @SerializedName("loan_type")
    @Expose
    private String loan_type;

    @SerializedName("Remarks")
    @Expose
    private String Remarks;

    @SerializedName("InterestedInLoan")
    @Expose
    private int InterestedInLoan;

    @SerializedName("When")
    @Expose
    private String When;

    @SerializedName("NextFollowUpDate")
    @Expose
    private String NextFollowUpDate;

    @SerializedName("Pincode")
    @Expose
    private String Pincode;

    @SerializedName("sync")
    @Expose
    private boolean sync;

    @SerializedName("CreatedBy")
    @Expose
    private String createdBy;

    @SerializedName("CreatedOn")
    @Expose
    @TypeConverters(TimestampConverter.class)
    private Date created_date;

    @SerializedName("response")
    @Expose
    private String response;

    @SerializedName("IsPremium")
    @Expose
    private int IsPremium;

    @SerializedName("CallTimeStamp")
    @Expose
    private String CallTimeStamp="";

    @SerializedName("Comments")
    @Expose
    private String Comments="";

    @SerializedName("ProductId")
    @Expose
    private String ProductId="";

    @SerializedName("AllDataCaptured")
    @Expose
    private boolean AllDataCaptured;


    public ColdCallTable() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getMarketName() {
        return MarketName;
    }

    public void setMarketName(String marketName) {
        MarketName = marketName;
    }

    public String getBusinessName() {
        return BusinessName;
    }

    public void setBusinessName(String businessName) {
        BusinessName = businessName;
    }

    public String getMobileNo() {
        return MobileNo;
    }

    public void setMobileNo(String mobileNo) {
        MobileNo = mobileNo;
    }

    public String getLat() {
        return Lat;
    }

    public void setLat(String lat) {
        Lat = lat;
    }

    public String getLong() {
        return Long;
    }

    public void setLong(String aLong) {
        Long = aLong;
    }

    public String getLoan_type() {
        return loan_type;
    }

    public void setLoan_type(String loan_type) {
        this.loan_type = loan_type;
    }

    public String getRemarks() {
        return Remarks;
    }

    public void setRemarks(String remarks) {
        Remarks = remarks;
    }

    public String getNextFollowUpDate() {
        return NextFollowUpDate;
    }

    public void setNextFollowUpDate(String nextFollowUpDate) {
        NextFollowUpDate = nextFollowUpDate;
    }

     public String getWhen() {
        return When;
    }

    public void setWhen(String when) {
        When = when;
    }

    public String getPincode() {
        return Pincode;
    }

    public void setPincode(String pincode) {
        Pincode = pincode;
    }

    public int getInterestedInLoan() {
        return InterestedInLoan;
    }

    public void setInterestedInLoan(int interestedInLoan) {
        InterestedInLoan = interestedInLoan;
    }

    public boolean isSync() {
        return sync;
    }

    public void setSync(boolean sync) {
        this.sync = sync;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreated_date() {
        return created_date;
    }

    public void setCreated_date(Date created_date) {
        this.created_date = created_date;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public int getIsPremium() {
        return IsPremium;
    }

    public void setIsPremium(int isPremium) {
        IsPremium = isPremium;
    }

    public String getCallTimeStamp() {
        return CallTimeStamp;
    }

    public void setCallTimeStamp(String callTimeStamp) {
        CallTimeStamp = callTimeStamp;
    }

    public String getComments() {
        return Comments;
    }

    public void setComments(String comments) {
        Comments = comments;
    }

    public String getProductId() {
        return ProductId;
    }

    public void setProductId(String productId) {
        ProductId = productId;
    }

    public boolean isAllDataCaptured() {
        return AllDataCaptured;
    }

    public void setAllDataCaptured(boolean allDataCaptured) {
        AllDataCaptured = allDataCaptured;
    }
}
