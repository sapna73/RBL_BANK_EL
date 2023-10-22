package com.saartak.el.database.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity
public class NetworkStrengthTable {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("network_strength")
    @Expose
    private int network_strength;

    public NetworkStrengthTable(int id, int network_strength) {
        this.id = id;
        this.network_strength = network_strength;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNetwork_strength() {
        return network_strength;
    }

    public void setNetwork_strength(int network_strength) {
        this.network_strength = network_strength;
    }
}
