package com.saartak.el.database.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.saartak.el.database.converter.TimestampConverter;

import java.util.Date;

import static com.saartak.el.dynamicui.constants.ParametersConstant.INT_CB_STATUS_PENDING;

@Entity
public class MasterTable {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("clientId")
    @Expose
    private String clientId;

    @SerializedName("clientName")
    @Expose
    private String clientName;

    @SerializedName("phoneNo")
    @Expose
    private String phoneNo;

    @SerializedName("loan_type")
    @Expose
    private String loan_type;

    @SerializedName("loan_amount")
    @Expose
    private String loan_amount;

    @SerializedName("currentStage")
    @Expose
    private String currentStage;

    @SerializedName("ActionId")
    @Expose
    private int ActionId=0;

    @SerializedName("CurrentStageId")
    @Expose
    private int CurrentStageId=0;

    @SerializedName("ApplicationStatus")
    @Expose
    private String ApplicationStatus;

    @SerializedName("FinalStatus")
    @Expose
    private String FinalStatus;

    @SerializedName("Remarks")
    @Expose
    private String Remarks;

    @SerializedName("sync")
    @Expose
    private boolean sync;

    @SerializedName("AllDataCaptured")
    @Expose
    private boolean AllDataCaptured;

    @SerializedName("createdBy")
    @Expose
    private String createdBy;

    @SerializedName("reviewBy")
    @Expose
    private String reviewBy;

    @SerializedName("branchId")
    @Expose
    private String branchId;

    @SerializedName("branchGSTcode")
    @Expose
    private String branchGSTcode;

    @SerializedName("created_date")
    @Expose
    @TypeConverters(TimestampConverter.class)
    private Date created_date;

    @SerializedName("response")
    @Expose
    private String response;

    @SerializedName("centerId")
    @Expose
    private String centerId;

    @SerializedName("centerName")
    @Expose
    private String centerName;

    @SerializedName("CBStatus")
    @Expose
    private int CBStatus=INT_CB_STATUS_PENDING;

    @SerializedName("isDataNeedsToCaptureFromServer")
    @Expose
    private boolean isDataNeedsToCaptureFromServer;

    @SerializedName("VKYCStatus")
    @Expose
    private String vkycStatus;

    @SerializedName("VKYCStatusCoApp")
    @Expose
    private String VKYCStatusCoApp;

    public MasterTable() {

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

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getLoan_type() {
        return loan_type;
    }

    public void setLoan_type(String loan_type) {
        this.loan_type = loan_type;
    }

    public String getLoan_amount() {
        return loan_amount;
    }

    public void setLoan_amount(String loan_amount) {
        this.loan_amount = loan_amount;
    }

    public String getCurrentStage() {
        return currentStage;
    }

    public void setCurrentStage(String currentStage) {
        this.currentStage = currentStage;
    }

    public String getApplicationStatus() {
        return ApplicationStatus;
    }

    public void setApplicationStatus(String applicationStatus) {
        ApplicationStatus = applicationStatus;
    }

    public String getFinalStatus() {
        return FinalStatus;
    }

    public void setFinalStatus(String finalStatus) {
        FinalStatus = finalStatus;
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

    public String getRemarks() {
        return Remarks;
    }

    public void setRemarks(String remarks) {
        Remarks = remarks;
    }

    public boolean isAllDataCaptured() {
        return AllDataCaptured;
    }

    public void setAllDataCaptured(boolean allDataCaptured) {
        AllDataCaptured = allDataCaptured;
    }

    public int getActionId() {
        return ActionId;
    }

    public void setActionId(int actionId) {
        ActionId = actionId;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getBranchGSTcode() {
        return branchGSTcode;
    }

    public void setBranchGSTcode(String branchGSTcode) {
        this.branchGSTcode = branchGSTcode;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public int getCurrentStageId() {
        return CurrentStageId;
    }

    public void setCurrentStageId(int currentStageId) {
        CurrentStageId = currentStageId;
    }

    public String getReviewBy() {
        return reviewBy;
    }

    public void setReviewBy(String reviewBy) {
        this.reviewBy = reviewBy;
    }

    public String getCenterId() {
        return centerId;
    }

    public void setCenterId(String centerId) {
        this.centerId = centerId;
    }

    public String getCenterName() {
        return centerName;
    }

    public void setCenterName(String centerName) {
        this.centerName = centerName;
    }

    public int getCBStatus() {
        return CBStatus;
    }

    public void setCBStatus(int CBStatus) {
        this.CBStatus = CBStatus;
    }

    public boolean isDataNeedsToCaptureFromServer() {
        return isDataNeedsToCaptureFromServer;
    }

    public void setDataNeedsToCaptureFromServer(boolean dataNeedsToCaptureFromServer) {
        isDataNeedsToCaptureFromServer = dataNeedsToCaptureFromServer;
    }

    public String getVkycStatus() {
        return vkycStatus;
    }

    public void setVkycStatus(String vkycStatus) {
        this.vkycStatus = vkycStatus;
    }

    public String getVKYCStatusCoApp() {
        return VKYCStatusCoApp;
    }

    public void setVKYCStatusCoApp(String VKYCStatusCoApp) {
        this.VKYCStatusCoApp = VKYCStatusCoApp;
    }
}
