package com.swadhaar.los.database.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity
public class DocumentUploadTable {
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


    @SerializedName("client_id")
    @Expose
    private String client_id;


    @SerializedName("DocumentName")
    @Expose
    private String DocumentName;

    @SerializedName("DocumentTAG")
    @Expose
    private String DocumentTAG;

    @SerializedName("DocumentDisplayName")
    @Expose
    private String DocumentDisplayName;

    @SerializedName("FileName")
    @Expose
    private String FileName;

    @SerializedName("Position")
    @Expose
    private String Position;

    @SerializedName("DocumentStatus")
    @Expose
    private boolean DocumentStatus;

    @SerializedName("loan_type")
    @Expose
    private String loan_type;

    @SerializedName("moduleType")
    @Expose
    private String moduleType;

    @SerializedName("FilePath")
    @Expose
    private String FilePath;

    public DocumentUploadTable() {
    }

    public DocumentUploadTable(int rawId, String screen_no, String screen_name, String client_id, String documentName,
                               String documentTAG, String documentDisplayName,String frontOrBack,String fileName,
                               String loan_type,String moduleType) {
        this.rawId = rawId;
        this.screen_no = screen_no;
        this.screen_name = screen_name;
        this.client_id = client_id;
        DocumentName = documentName;
        DocumentTAG = documentTAG;
        DocumentDisplayName = documentDisplayName;
        Position=frontOrBack;
        FileName=fileName;
        this.loan_type=loan_type;
        this.moduleType=moduleType;
    }

    public String getPosition() {
        return Position;
    }

    public void setPosition(String position) {
        Position = position;
    }

    public String getFileName() {
        return FileName;
    }

    public void setFileName(String fileName) {
        FileName = fileName;
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

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getDocumentName() {
        return DocumentName;
    }

    public void setDocumentName(String documentName) {
        DocumentName = documentName;
    }

    public String getDocumentTAG() {
        return DocumentTAG;
    }

    public void setDocumentTAG(String documentTAG) {
        DocumentTAG = documentTAG;
    }

    public String getDocumentDisplayName() {
        return DocumentDisplayName;
    }

    public void setDocumentDisplayName(String documentDisplayName) {
        DocumentDisplayName = documentDisplayName;
    }

    public boolean isDocumentStatus() {
        return DocumentStatus;
    }

    public void setDocumentStatus(boolean documentStatus) {
        DocumentStatus = documentStatus;
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

    public String getFilePath() {
        return FilePath;
    }

    public void setFilePath(String filePath) {
        FilePath = filePath;
    }
}
