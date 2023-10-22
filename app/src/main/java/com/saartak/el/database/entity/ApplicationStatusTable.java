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
public class ApplicationStatusTable {
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

    @SerializedName("loan_type")
    @Expose
    private String loan_type;

    @SerializedName("currentStage")
    @Expose
    private String currentStage;

    @SerializedName("CurrentStageId")
    @Expose
    private int CurrentStageId=0;

    @SerializedName("Remarks")
    @Expose
    private String Remarks;

    @SerializedName("createdBy")
    @Expose
    private String createdBy;

    @SerializedName("reviewBy")
    @Expose
    private String reviewBy;

    @SerializedName("created_date")
    @Expose
    @TypeConverters(TimestampConverter.class)
    private Date created_date;


    public ApplicationStatusTable() {

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

    public String getLoan_type() {
        return loan_type;
    }

    public void setLoan_type(String loan_type) {
        this.loan_type = loan_type;
    }

    public String getCurrentStage() {
        return currentStage;
    }

    public void setCurrentStage(String currentStage) {
        this.currentStage = currentStage;
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
}
