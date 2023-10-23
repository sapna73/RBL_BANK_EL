package com.saartak.el.database.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity
public class KnowledgeBankTable {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("DocumentName")
    @Expose
    private String DocumentName;

    @SerializedName("FileName")
    @Expose
    private String FileName;

    @SerializedName("FileType")
    @Expose
    private String FileType;

    @SerializedName("UpdatedBy")
    @Expose
    private String UpdatedBy;

    @SerializedName("UpdatedOn")
    @Expose
    private String UpdatedOn;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDocumentName() {
        return DocumentName;
    }

    public void setDocumentName(String documentName) {
        this.DocumentName = documentName;
    }

    public String getFileName() {
        return FileName;
    }

    public void setFileName(String fileName) {
        this.FileName = fileName;
    }

    public String getFileType() {
        return FileType;
    }

    public void setFileType(String fileType) {
        this.FileType = fileType;
    }

    public String getUpdatedBy() {
        return UpdatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        UpdatedBy = updatedBy;
    }

    public String getUpdatedOn() {
        return UpdatedOn;
    }

    public void setUpdatedOn(String updatedOn) {
        UpdatedOn = updatedOn;
    }
}
