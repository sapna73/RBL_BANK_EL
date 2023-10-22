package com.saartak.el.models.TWLMakeModel;

import androidx.annotation.NonNull;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TWLExShowRoomPriceResponseTable {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @SerializedName("ID")
    @Expose(serialize = false, deserialize = false)
    private int id;

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

    @SerializedName("State")
    @Expose
    private String state;

    @SerializedName("ExShowroomPrice")
    @Expose
    private String exShowroomPrice;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getVariant() {
        return variant;
    }

    public void setVariant(String variant) {
        this.variant = variant;
    }

    public String getTwowheelertype() {
        return twowheelertype;
    }

    public void setTwowheelertype(String twowheelertype) {
        this.twowheelertype = twowheelertype;
    }

    public String getEngineCC() {
        return engineCC;
    }

    public void setEngineCC(String engineCC) {
        this.engineCC = engineCC;
    }

    public String getElectricModel() {
        return electricModel;
    }

    public void setElectricModel(String electricModel) {
        this.electricModel = electricModel;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getExShowroomPrice() {
        return exShowroomPrice;
    }

    public void setExShowroomPrice(String exShowroomPrice) {
        this.exShowroomPrice = exShowroomPrice;
    }
}
