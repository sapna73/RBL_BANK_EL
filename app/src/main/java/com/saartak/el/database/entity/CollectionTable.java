package com.saartak.el.database.entity;

import androidx.annotation.NonNull;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.saartak.el.database.converter.TimestampConverter;

import java.util.Date;

//@Entity
public class CollectionTable {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("ClientId")
    @Expose
    private String clientId;

    @SerializedName("ClientName")
    @Expose
    private String clientName;

    @SerializedName("BusinessAddress")
    @Expose
    private String BusinessAddress;

    @SerializedName("CommunicationAddress")
    @Expose
    private String CommunicationAddress;

    @SerializedName("LoanType")
    @Expose
    private String loanType;

    @SerializedName("EMI")
    @Expose
    private String EMI;

    @SerializedName("ApplicantMobileNo")
    @Expose
    private String ApplicantMobileNo;

    @SerializedName("CoApplicantName")
    @Expose
    private String CoApplicantName;


    @SerializedName("CoApplicantMobileNo")
    @Expose
    private String CoApplicantMobileNo;

    @SerializedName("POS")
    @Expose
    private String POS;

   @SerializedName("TeleCallingStatus")
    @Expose
    private String TeleCallingStatus;

   @SerializedName("CallingRemarks")
    @Expose
    private String CallingRemarks;

   @SerializedName("PPTDate")
    @Expose
   @TypeConverters(TimestampConverter.class)
    private Date PPTDate;

   @SerializedName("ModeOfPayment")
    @Expose
    private String ModeOfPayment;

    @SerializedName("sync")
    @Expose
    private boolean sync;

    @SerializedName("CreatedBy")
    @Expose
    private String createdBy;

    @SerializedName("created_date")
    @Expose
    @TypeConverters(TimestampConverter.class)
    private String created_date;

    @SerializedName("response")
    @Expose
    private String response;



    public int getId() {
        return id;
    }

    public String getClientId() {
        return clientId;
    }

    public String getClientName() {
        return clientName;
    }

    public String getLoanType() {
        return loanType;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getBusinessAddress() {
        return BusinessAddress;
    }

    public String getCommunicationAddress() {
        return CommunicationAddress;
    }

    public void setBusinessAddress(String businessAddress) {
        BusinessAddress = businessAddress;
    }

    public void setCommunicationAddress(String communicationAddress) {
        CommunicationAddress = communicationAddress;
    }

    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }

    public void setEMI(String EMI) {
        this.EMI = EMI;
    }

    public void setApplicantMobileNo(String applicantMobileNo) {
        ApplicantMobileNo = applicantMobileNo;
    }

    public void setCoApplicantName(String coApplicantName) {
        CoApplicantName = coApplicantName;
    }

    public void setCoApplicantMobileNo(String coApplicantMobileNo) {
        CoApplicantMobileNo = coApplicantMobileNo;
    }

    public void setPOS(String POS) {
        this.POS = POS;
    }

    public void setTeleCallingStatus(String teleCallingStatus) {
        TeleCallingStatus = teleCallingStatus;
    }

    public void setCallingRemarks(String callingRemarks) {
        CallingRemarks = callingRemarks;
    }

    public void setPPTDate(Date PPTDate) {
        this.PPTDate = PPTDate;
    }

    public void setModeOfPayment(String modeOfPayment) {
        ModeOfPayment = modeOfPayment;
    }

    public void setSync(boolean sync) {
        this.sync = sync;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public void setCreated_date(String created_date) {
        this.created_date = created_date;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getEMI() {
        return EMI;
    }

    public String getApplicantMobileNo() {
        return ApplicantMobileNo;
    }

    public String getCoApplicantName() {
        return CoApplicantName;
    }

    public String getCoApplicantMobileNo() {
        return CoApplicantMobileNo;
    }

    public String getPOS() {
        return POS;
    }

    public String getTeleCallingStatus() {
        return TeleCallingStatus;
    }

    public String getCallingRemarks() {
        return CallingRemarks;
    }

    public Date getPPTDate() {
        return PPTDate;
    }

    public String getModeOfPayment() {
        return ModeOfPayment;
    }

    public boolean isSync() {
        return sync;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public String getCreated_date() {
        return created_date;
    }

    public String getResponse() {
        return response;
    }
}
