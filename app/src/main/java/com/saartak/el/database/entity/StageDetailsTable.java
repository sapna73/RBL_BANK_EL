package com.saartak.el.database.entity;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity
public class StageDetailsTable {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("CurrentStage")
    @Expose
    private int CurrentStage;

    @SerializedName("CurrentStageName")
    @Expose
    private String CurrentStageName;

    @SerializedName("ActionId")
    @Expose
    private int ActionId;

    @SerializedName("ActionName")
    @Expose
    private String ActionName;

    @SerializedName("CustomerUniqueId")
    @Expose
    private String CustomerUniqueId;

    @SerializedName("Remark")
    @Expose
    private String Remark;

    @SerializedName("Description")
    @Expose
    private String Description;

    @SerializedName("CustomerName")
    @Expose
    private String customerName;

    public StageDetailsTable() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCurrentStage() {
        return CurrentStage;
    }

    public void setCurrentStage(int currentStage) {
        CurrentStage = currentStage;
    }

    public String getCurrentStageName() {
        return CurrentStageName;
    }

    public void setCurrentStageName(String currentStageName) {
        CurrentStageName = currentStageName;
    }

    public int getActionId() {
        return ActionId;
    }

    public void setActionId(int actionId) {
        ActionId = actionId;
    }

    public String getActionName() {
        return ActionName;
    }

    public void setActionName(String actionName) {
        ActionName = actionName;
    }

    public String getCustomerUniqueId() {
        return CustomerUniqueId;
    }

    public void setCustomerUniqueId(String customerUniqueId) {
        CustomerUniqueId = customerUniqueId;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
}
