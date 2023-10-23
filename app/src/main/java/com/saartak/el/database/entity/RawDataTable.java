package com.saartak.el.database.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.saartak.el.database.converter.DateConverter;

import java.util.Date;

@Entity
public class RawDataTable {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("rawdata")
    @Expose
    private String rawdata;

    @SerializedName("dynamic_ui_rawdata")
    @Expose
    private String dynamic_ui_rawdata;

    @SerializedName("screen_no")
    @Expose
    private String screen_no;

    @SerializedName("screen_name")
    @Expose
    private String screen_name;

    @SerializedName("additional")
    @Expose
    private String additional;

    @SerializedName("client_id")
    @Expose
    private String client_id;

    @SerializedName("tag_name")
    @Expose
    private String tag_name;

    @SerializedName("field_name")
    @Expose
    private String field_name;

    @SerializedName("sync")
    @Expose
    private boolean sync;

    @SerializedName("ref_id")
    @Expose
    private int ref_id;

    @SerializedName("created_date")
    @Expose
    @TypeConverters(DateConverter.class)
    private Date created_date;

    @SerializedName("loan_type")
    @Expose
    private String loan_type;

    @SerializedName("user_id")
    @Expose
    private String user_id;

    @SerializedName("moduleType")
    @Expose
    private String moduleType;

    @SerializedName("ApplicationStatus")
    @Expose
    private String ApplicationStatus="New";

    @SerializedName("FinalStatus")
    @Expose
    private String FinalStatus="Pending";

    @SerializedName("UniqueId")
    @Expose
    private String UniqueId="1";

    @SerializedName("CoRelationID")
    @Expose
    private String CoRelationID;

    @SerializedName("ProductId")
    @Expose
    private String ProductId;

    public RawDataTable(){

    }

    public RawDataTable(/*int id,*/ String rawdata, String screen_no, String screen_name,
                                    String additional, String client_id,String loan_type,String user_id,String moduleType,String coRelationID) {
//        this.id = id;
        this.rawdata = rawdata;
        this.screen_no = screen_no;
        this.screen_name = screen_name;
        this.additional = additional;
        this.client_id = client_id;
        this.loan_type=loan_type;
        this.user_id=user_id;
        this.moduleType=moduleType;
        this.CoRelationID=coRelationID;
    }

    public String getModuleType() {
        return moduleType;
    }

    public void setModuleType(String moduleType) {
        this.moduleType = moduleType;
    }

    public String getLoan_type() {
        return loan_type;
    }

    public void setLoan_type(String loan_type) {
        this.loan_type = loan_type;
    }

    public String getTag_name() {
        return tag_name;
    }

    public void setTag_name(String tag_name) {
        this.tag_name = tag_name;
    }

    public String getField_name() {
        return field_name;
    }

    public void setField_name(String field_name) {
        this.field_name = field_name;
    }

    public int getRef_id() {
        return ref_id;
    }

    public void setRef_id(int ref_id) {
        this.ref_id = ref_id;
    }

    public Date getCreated_date() {
        return created_date;
    }

    public void setCreated_date(Date created_date) {
        this.created_date = created_date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRawdata() {
        return rawdata;
    }

    public void setRawdata(String rawdata) {
        this.rawdata = rawdata;
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

    public String getAdditional() {
        return additional;
    }

    public void setAdditional(String additional) {
        this.additional = additional;
    }

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public boolean isSync() {
        return sync;
    }
    public void setSync(boolean sync) {
        this.sync = sync;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getApplicationStatus() {
        return ApplicationStatus;
    }

    public void setApplicationStatus(String applicationStatus) {
        ApplicationStatus = applicationStatus;
    }

    public String getFinalStatus() {
        return FinalStatus;
    }

    public void setFinalStatus(String finalStatus) {
        FinalStatus = finalStatus;
    }

    public String getDynamic_ui_rawdata() {
        return dynamic_ui_rawdata;
    }

    public void setDynamic_ui_rawdata(String dynamic_ui_rawdata) {
        this.dynamic_ui_rawdata = dynamic_ui_rawdata;
    }

    public String getCoRelationID() {
        return CoRelationID;
    }

    public void setCoRelationID(String coRelationID) {
        CoRelationID = coRelationID;
    }

    public String getProductId() {
        return ProductId;
    }

    public void setProductId(String productId) {
        ProductId = productId;
    }

    public String getUniqueId() {
        return UniqueId;
    }

    public void setUniqueId(String uniqueId) {
        UniqueId = uniqueId;
    }
}
