package com.swadhaar.los.database.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity (indices = {@Index(value = {"mobileNumber"},
        unique = true)})
public class OTPVerificationTable {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("rawId")
    @Expose
    private int rawId;

    @SerializedName("screen_no")
    @Expose
    private String screen_no;

    @SerializedName("screen_name")
    @Expose
    private String screen_name;

    @SerializedName("otp")
    @Expose
    private String otp;

    @SerializedName("RefferenceId")
    @Expose
    private String RefferenceId;

    @SerializedName("timestamp")
    @Expose
    private String timestamp;

    @SerializedName("client_id")
    @Expose
    private String client_id;

    @SerializedName("OTPDisplayName")
    @Expose
    private String OTPDisplayName;

    @SerializedName("mobileNumber")
    @Expose
    private String mobileNumber;

    @SerializedName("isOTPVerified")
    @Expose
    private boolean isOTPVerified;

    @SerializedName("loan_type")
    @Expose
    private String loan_type;

    @SerializedName("moduleType")
    @Expose
    private String moduleType;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("isOTPGenerated")
    @Expose
    private boolean isOTPGenerated=false;


    public OTPVerificationTable() {
    }

    public OTPVerificationTable(int rawId, String screen_no, String screen_name, String client_id,
                               String OTPDisplayName, String loan_type, String moduleType,String mobileNumber,String name) {
        this.rawId = rawId;
        this.screen_no = screen_no;
        this.screen_name = screen_name;
        this.client_id = client_id;
        this.OTPDisplayName = OTPDisplayName;
        this.loan_type=loan_type;
        this.moduleType=moduleType;
        this.mobileNumber=mobileNumber;
        this.name=name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRawId() {
        return rawId;
    }

    public void setRawId(int rawId) {
        this.rawId = rawId;
    }

    public String getScreen_no() {
        return screen_no;
    }

    public void setScreen_no(String screen_no) {
        this.screen_no = screen_no;
    }

    public String getScreen_name() {
        return screen_name;
    }

    public void setScreen_name(String screen_name) {
        this.screen_name = screen_name;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getOTPDisplayName() {
        return OTPDisplayName;
    }

    public void setOTPDisplayName(String OTPDisplayName) {
        this.OTPDisplayName = OTPDisplayName;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public boolean isOTPVerified() {
        return isOTPVerified;
    }

    public void setOTPVerified(boolean OTPVerified) {
        isOTPVerified = OTPVerified;
    }

    public String getLoan_type() {
        return loan_type;
    }

    public void setLoan_type(String loan_type) {
        this.loan_type = loan_type;
    }

    public String getModuleType() {
        return moduleType;
    }

    public void setModuleType(String moduleType) {
        this.moduleType = moduleType;
    }

    public String getRefferenceId() {
        return RefferenceId;
    }

    public void setRefferenceId(String refferenceId) {
        RefferenceId = refferenceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isOTPGenerated() {
        return isOTPGenerated;
    }

    public void setOTPGenerated(boolean OTPGenerated) {
        isOTPGenerated = OTPGenerated;
    }
}

