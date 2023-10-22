package com.saartak.el.database.entity;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity
public class RawDataFromServerTable {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("SerialNo")
    @Expose
    private int SerialNo;

    @SerializedName("WorkFlowName")
    @Expose
    private String WorkFlowName;

    @SerializedName("CustomerId")
    @Expose
    private String CustomerId;

    @SerializedName("CustomerName")
    @Expose
    private String CustomerName;

    @SerializedName("REQUESTEDLOANAMOUNT")
    @Expose
    private String REQUESTEDLOANAMOUNT;

    @SerializedName("CreatedOn")
    @Expose
    private String CreatedOn;

    @SerializedName("Rwas")
    @Expose
    private String Rwas;

    @SerializedName("ScreenId")
    @Expose
    private String ScreenId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSerialNo() {
        return SerialNo;
    }

    public void setSerialNo(int serialNo) {
        SerialNo = serialNo;
    }

    public String getWorkFlowName() {
        return WorkFlowName;
    }

    public void setWorkFlowName(String workFlowName) {
        WorkFlowName = workFlowName;
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

    public String getREQUESTEDLOANAMOUNT() {
        return REQUESTEDLOANAMOUNT;
    }

    public void setREQUESTEDLOANAMOUNT(String REQUESTEDLOANAMOUNT) {
        this.REQUESTEDLOANAMOUNT = REQUESTEDLOANAMOUNT;
    }

    public String getCreatedOn() {
        return CreatedOn;
    }

    public void setCreatedOn(String createdOn) {
        CreatedOn = createdOn;
    }

    public String getRwas() {
        return Rwas;
    }

    public void setRwas(String rwas) {
        Rwas = rwas;
    }

    public String getScreenId() {
        return ScreenId;
    }

    public void setScreenId(String screenId) {
        ScreenId = screenId;
    }
}
