package com.saartak.el.database.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity
public class LogTable {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("date_time")
    @Expose
    private String date_time;

    @SerializedName("staff_id")
    @Expose
    private String staff_id;

    @SerializedName("methodName")
    @Expose
    private String methodName;

    @SerializedName("tvMobNo")
    @Expose
    private String message;

    @SerializedName("screen_no")
    @Expose
    private String screen_no;

    @SerializedName("screen_name")
    @Expose
    private String screen_name;

    @SerializedName("client_id")
    @Expose
    private String client_id;

    @SerializedName("loan_type")
    @Expose
    private String loan_type;

    @SerializedName("module_type")
    @Expose
    private String module_type;

    @SerializedName("imei_number")
    @Expose
    private String imei_number;

    public LogTable() {
    }

    public LogTable(String date_time, String staff_id,String methodName, String message, String screen_no, String screen_name, String client_id, String loan_type, String module_type, String imei_number) {
        this.date_time = date_time;
        this.staff_id = staff_id;
        this.methodName = methodName;
        this.message = message;
        this.screen_no = screen_no;
        this.screen_name = screen_name;
        this.client_id = client_id;
        this.loan_type = loan_type;
        this.module_type = module_type;
        this.imei_number = imei_number;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate_time() {
        return date_time;
    }

    public void setDate_time(String date_time) {
        this.date_time = date_time;
    }

    public String getStaff_id() {
        return staff_id;
    }

    public void setStaff_id(String staff_id) {
        this.staff_id = staff_id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getLoan_type() {
        return loan_type;
    }

    public void setLoan_type(String loan_type) {
        this.loan_type = loan_type;
    }

    public String getImei_number() {
        return imei_number;
    }

    public String getModule_type() {
        return module_type;
    }

    public void setModule_type(String module_type) {
        this.module_type = module_type;
    }

    public void setImei_number(String imei_number) {
        this.imei_number = imei_number;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }
}
