package com.swadhaar.los.models.RATSourseOfIncome;

import androidx.annotation.NonNull;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SPCustomerTypeResponseTable {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @SerializedName("ID")
    @Expose(serialize = false, deserialize = false)
    private int id;

    @SerializedName("SeqID")
    @Expose
    private String seqId;

    @SerializedName("CustomerTypeID")
    @Expose
    private String customerId;

    @SerializedName("SourceOfIncome")
    @Expose
    private String sourceOfIncome;

    public int getId() {
        return id;
    }

    public String getSeqId() {
        return seqId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getSourceOfIncome() {
        return sourceOfIncome;
    }
}
