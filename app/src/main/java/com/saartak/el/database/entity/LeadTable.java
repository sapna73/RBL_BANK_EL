package com.saartak.el.database.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity
public class LeadTable {
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

   /* @SerializedName("KYCType")
    @Expose
    private String kycType;

    @SerializedName("KYCNumber")
    @Expose
    private String KYCNumber;

    @SerializedName("KYCDateOfBirth")
    @Expose
    private String KYCDateOfBirth;

    @SerializedName("KYCAge")
    @Expose
    private String KYCAge;

    @SerializedName("AddressLineOne")
    @Expose
    private String AddressLineOne;

    @SerializedName("AddressLineTwo")
    @Expose
    private String AddressLineTwo;

    @SerializedName("LoanAmount")
    @Expose
    private String LoanAmount;

    @SerializedName("LoanTenure")
    @Expose
    private String LoanTenure;*/

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
    private String Lat;

    @SerializedName("Long")
    @Expose
    private String Long;

    @SerializedName("loan_type")
    @Expose
    private String loan_type;

    @SerializedName("Remarks")
    @Expose
    private String Remarks;

    @SerializedName("InterestedInLoan")
    @Expose
    private int InterestedInLoan;

    @SerializedName("lead_status")
    @Expose
    private String leadStatus;

    @SerializedName("NextFollowUpDate")
    @Expose
    private String NextFollowUpDate;

    @SerializedName("Pincode")
    @Expose
    private String Pincode;

    /*@SerializedName("DISTRICT")
    @Expose
    private String district;

    @SerializedName("GENDER")
    @Expose
    private String gender;

    @SerializedName("STATE")
    @Expose
    private String state;

    @SerializedName("CITY")
    @Expose
    private String city;

    @SerializedName("ADDRESS TYPE")
    @Expose
    private String address_type;

    @SerializedName("CUSTOMER TYPE")
    @Expose
    private String customer_type;

    @SerializedName("LAND MARK")
    @Expose
    private String land_mark;
*/
    @SerializedName("sync")
    @Expose
    private boolean sync;

    @SerializedName("CreatedBy")
    @Expose
    private String createdBy;

    @SerializedName("CreatedOn")
    @Expose
    private String created_date;

    @SerializedName("response")
    @Expose
    private String response;

    @SerializedName("IsDataCaptured")
    @Expose
    private boolean IsDataCaptured;


    @SerializedName("IsPremium")
    @Expose
    private int IsPremium=0;

    public LeadTable() {

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

    public String getLeadStatus() {
        return leadStatus;
    }

    public void setLeadStatus(String leadStatus) {
        this.leadStatus = leadStatus;
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

    public boolean isIsDataCaptured() {
        return IsDataCaptured;
    }

    public void setIsDataCaptured(boolean isDataCaptured) {
        IsDataCaptured = isDataCaptured;
    }

    public int getIsPremium() {
        return IsPremium;
    }

    public void setIsPremium(int isPremium) {
        IsPremium = isPremium;
    }

   /* public String getKycType() {
        return kycType;
    }

    public void setKycType(String kycType) {
        this.kycType = kycType;
    }

    public String getKYCNumber() {
        return KYCNumber;
    }

    public void setKYCNumber(String KYCNumber) {
        this.KYCNumber = KYCNumber;
    }

    public String getKYCDateOfBirth() {
        return KYCDateOfBirth;
    }

    public void setKYCDateOfBirth(String KYCDateOfBirth) {
        this.KYCDateOfBirth = KYCDateOfBirth;
    }

    public String getKYCAge() {
        return KYCAge;
    }

    public void setKYCAge(String KYCAge) {
        this.KYCAge = KYCAge;
    }

    public String getAddressLineOne() {
        return AddressLineOne;
    }

    public void setAddressLineOne(String addressLineOne) {
        AddressLineOne = addressLineOne;
    }

    public String getAddressLineTwo() {
        return AddressLineTwo;
    }

    public void setAddressLineTwo(String addressLineTwo) {
        AddressLineTwo = addressLineTwo;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress_type() {
        return address_type;
    }

    public void setAddress_type(String address_type) {
        this.address_type = address_type;
    }

    public String getCustomer_type() {
        return customer_type;
    }

    public void setCustomer_type(String customer_type) {
        this.customer_type = customer_type;
    }

    public String getLoanAmount() {
        return LoanAmount;
    }

    public void setLoanAmount(String loanAmount) {
        LoanAmount = loanAmount;
    }

    public String getLoanTenure() {
        return LoanTenure;
    }

    public void setLoanTenure(String loanTenure) {
        LoanTenure = loanTenure;
    }

    public String getLand_mark() {
        return land_mark;
    }

    public void setLand_mark(String land_mark) {
        this.land_mark = land_mark;
    }*/
}
