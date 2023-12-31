package com.saartak.el.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.saartak.el.database.entity.InitiatePaymentStatusTable;

import java.util.ArrayList;

public class InitiatePaymentStatusResponseDTO {
    @SerializedName("Table")
    @Expose
    ArrayList<InitiatePaymentStatusTable> initiatePaymentStatusTable;

    public ArrayList<InitiatePaymentStatusTable> getInitiatePaymentStatusTable() {
        return initiatePaymentStatusTable;
    }

    public void setInitiatePaymentStatusTable(ArrayList<InitiatePaymentStatusTable> initiatePaymentStatusTable) {
        this.initiatePaymentStatusTable = initiatePaymentStatusTable;
    }
}
