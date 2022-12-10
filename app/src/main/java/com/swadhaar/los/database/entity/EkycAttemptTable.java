package com.swadhaar.los.database.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Entity
public class EkycAttemptTable implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("aadhaar_no")
    @Expose
    private String aadhaar_no;

    @SerializedName("attempt")
    @Expose
    private int attempt;

    @SerializedName("screen_no")
    @Expose
    private String screen_no;

    @SerializedName("tvMobNo")
    @Expose
    private String message;

    public EkycAttemptTable() {
    }

    public EkycAttemptTable(String aadhaar_no, int attempt, String screen_no, String message) {
        this.aadhaar_no = aadhaar_no;
        this.attempt = attempt;
        this.screen_no = screen_no;
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAadhaar_no() {
        return aadhaar_no;
    }

    public void setAadhaar_no(String aadhaar_no) {
        this.aadhaar_no = aadhaar_no;
    }

    public int getAttempt() {
        return attempt;
    }

    public void setAttempt(int attempt) {
        this.attempt = attempt;
    }

    public String getScreen_no() {
        return screen_no;
    }

    public void setScreen_no(String screen_no) {
        this.screen_no = screen_no;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}
