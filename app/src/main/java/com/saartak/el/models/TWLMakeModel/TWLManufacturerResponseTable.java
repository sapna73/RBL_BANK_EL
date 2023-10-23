package com.saartak.el.models.TWLMakeModel;

import androidx.annotation.NonNull;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TWLManufacturerResponseTable {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @SerializedName("ID")
    @Expose(serialize = false, deserialize = false)
    private int id;

    @SerializedName("State")
    @Expose
    private String state;

    @SerializedName("Manufacturer")
    @Expose
    private String manufacturer;

    @SerializedName("Model")
    @Expose
    private String model;

    @SerializedName("Variant")
    @Expose
    private String variant;

    @SerializedName("Twowheelertype")
    @Expose
    private String twowheelertype;

    @SerializedName("EngineCC")
    @Expose
    private String engineCC;

    @SerializedName("ElectricModel")
    @Expose
    private String electricModel;

    @SerializedName("Category")
    @Expose
    private String category;

    @SerializedName("ExShowroomPrice")
    @Expose
    private String exShowroomPrice;

    public String getState() {
        return state;
    }

    public int getId() {
        return id;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getModel() {
        return model;
    }

    public String getVariant() {
        return variant;
    }

    public String getTwowheelertype() {
        return twowheelertype;
    }

    public String getEngineCC() {
        return engineCC;
    }

    public String getElectricModel() {
        return electricModel;
    }

    public String getCategory() {
        return category;
    }

    public String getExShowroomPrice() {
        return exShowroomPrice;
    }
}
