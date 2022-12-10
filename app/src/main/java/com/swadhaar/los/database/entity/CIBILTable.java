package com.swadhaar.los.database.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity
public class CIBILTable {

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

    @SerializedName("timestamp")
    @Expose
    private String timestamp;

    @SerializedName("client_id")
    @Expose
    private String client_id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("mobileNumber")
    @Expose
    private String mobileNumber;

    @SerializedName("loan_type")
    @Expose
    private String loan_type;

    @SerializedName("moduleType")
    @Expose
    private String moduleType;

    @SerializedName("userId")
    @Expose
    private String userId;

    @SerializedName("score")
    @Expose
    private String score;

    @SerializedName("Decision")
    @Expose
    private String Decision;


    @SerializedName("Reason")
    @Expose
    private String Reason;

    @SerializedName("ApplicationId")
    @Expose
    private String ApplicationId;

    @SerializedName("SolutionSetInstanceId")
    @Expose
    private String SolutionSetInstanceId;

    @SerializedName("isCBChecked")
    @Expose
    private boolean isCBChecked=false;


    public CIBILTable() {
    }



    public CIBILTable(int rawId, String screen_no, String screen_name, String client_id,
                                String loan_type, String moduleType,String mobileNumber,String name,String userId) {
        this.rawId = rawId;
        this.screen_no = screen_no;
        this.screen_name = screen_name;
        this.client_id = client_id;
        this.loan_type=loan_type;
        this.moduleType=moduleType;
        this.mobileNumber=mobileNumber;
        this.name=name;
        this.userId=userId;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
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

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getDecision() {
        return Decision;
    }

    public void setDecision(String decision) {
        Decision = decision;
    }

    public String getReason() {
        return Reason;
    }

    public void setReason(String reason) {
        Reason = reason;
    }

    public String getApplicationId() {
        return ApplicationId;
    }

    public void setApplicationId(String applicationId) {
        ApplicationId = applicationId;
    }

    public String getSolutionSetInstanceId() {
        return SolutionSetInstanceId;
    }

    public void setSolutionSetInstanceId(String solutionSetInstanceId) {
        SolutionSetInstanceId = solutionSetInstanceId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public boolean isCBChecked() {
        return isCBChecked;
    }

    public void setCBChecked(boolean CBChecked) {
        isCBChecked = CBChecked;
    }
}

