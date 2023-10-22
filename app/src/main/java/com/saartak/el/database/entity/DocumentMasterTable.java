package com.saartak.el.database.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity
public class DocumentMasterTable {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("ScreenId")
    @Expose
    private String ScreenId;

    @SerializedName("CustomerType")
    @Expose
    private String CustomerType;

    @SerializedName("DisplayName")
    @Expose
    private String DisplayName;

    @SerializedName("DocumentName")
    @Expose
    private String DocumentName;

    @SerializedName("TagName")
    @Expose
    private String TagName;

    @SerializedName("FileFormat")
    @Expose
    private String FileFormat;

    public DocumentMasterTable() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getScreenId() {
        return ScreenId;
    }

    public void setScreenId(String screenId) {
        ScreenId = screenId;
    }

    public String getCustomerType() {
        return CustomerType;
    }

    public void setCustomerType(String customerType) {
        CustomerType = customerType;
    }

    public String getDisplayName() {
        return DisplayName;
    }

    public void setDisplayName(String displayName) {
        DisplayName = displayName;
    }

    public String getTagName() {
        return TagName;
    }

    public void setTagName(String tagName) {
        TagName = tagName;
    }

    public String getFileFormat() {
        return FileFormat;
    }

    public void setFileFormat(String fileFormat) {
        FileFormat = fileFormat;
    }

    public String getDocumentName() {
        return DocumentName;
    }

    public void setDocumentName(String documentName) {
        DocumentName = documentName;
    }
}
