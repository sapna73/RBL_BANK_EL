package com.saartak.el.database.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.saartak.el.database.converter.DataTypeConverter;

@Entity
public class DocumentUploadTableNew {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("SCREEN ID")
    @Expose
    private String screen_id;

    @SerializedName("client_id")
    @Expose
    private String client_id;

    @SerializedName("DOCUMENT NAME")
    @Expose
    private String document_name;

    @SerializedName("DOCUMENT FULL NAME")
    @Expose
    private String document_full_name;

    @SerializedName("DOCUMENT TAG")
    @Expose
    private String document_tag;

    @SerializedName("isHeader")
    @Expose
    private boolean isHeader;


    @SerializedName("CUSTOMER TYPE")
    @Expose
    private String customer_type;

    @SerializedName("DISPLAY NAME")
    @Expose
    private String display_name;

    @SerializedName("FULL DISPLAY NAME")
    @Expose
    private String full_display_name;

    @SerializedName("FileFormat")
    @Expose
    private String file_format;

    @SerializedName("FileName")
    @Expose
    private String file_name;

    @SerializedName("DocumentStatus")
    @Expose
    private boolean document_status;

    @SerializedName("loan_type")
    @Expose
    private String loan_type;

    @SerializedName("moduleType")
    @Expose
    private String module_type;

    @SerializedName("user_id")
    @Expose
    private String user_id;

    @SerializedName("product_id")
    @Expose
    private String product_id;

    @SerializedName("FilePath")
    @Expose
    private String file_path;

    @SerializedName("ResponseMessage")
    @Expose
    private String ResponseMessage;

    @SerializedName("secretKey")
    @Expose
    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    private byte[] secretKey;

    @SerializedName("randomNumber")
    @Expose
    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    private byte[] randomNumber;

    @SerializedName("FILE COUNT")
    @Expose
    private int fileCount=0;

    @SerializedName("FILES")
    @TypeConverters(DataTypeConverter.class)
    private String[] FILES;

    @SerializedName("NumberOfImages")
    @Expose
    private int NumberOfImages=0;

    @SerializedName("isEditable")
    @Expose
    private boolean isEditable=true;


    public DocumentUploadTableNew() {

    }

    public DocumentUploadTableNew(String screen_id, String client_id, String document_name, String document_full_name,
                                  String document_tag, String customer_type,
                                  String display_name, String full_display_name, String file_format, String loan_type, String module_type,
                                  String user_id,String product_id,
                                  boolean isHeader,int noOfImagesToCapture,boolean isEditable) {
        this.screen_id = screen_id;
        this.client_id = client_id;
        this.document_name = document_name;
        this.document_full_name = document_full_name;
        this.document_tag = document_tag;
        this.customer_type = customer_type;
        this.display_name = display_name;
        this.full_display_name = full_display_name;
        this.file_format = file_format;
        this.loan_type = loan_type;
        this.module_type = module_type;
        this.user_id=user_id;
        this.product_id=product_id;
        this.isHeader=isHeader;
        this.NumberOfImages=noOfImagesToCapture;
        this.isEditable=isEditable;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getScreen_id() {
        return screen_id;
    }

    public void setScreen_id(String screen_id) {
        this.screen_id = screen_id;
    }

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getDocument_name() {
        return document_name;
    }

    public void setDocument_name(String document_name) {
        this.document_name = document_name;
    }

    public String getDocument_tag() {
        return document_tag;
    }

    public void setDocument_tag(String document_tag) {
        this.document_tag = document_tag;
    }

    public String getCustomer_type() {
        return customer_type;
    }

    public void setCustomer_type(String customer_type) {
        this.customer_type = customer_type;
    }

    public String getDisplay_name() {
        return display_name;
    }

    public void setDisplay_name(String display_name) {
        this.display_name = display_name;
    }

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }


    public boolean isDocument_status() {
        return document_status;
    }

    public void setDocument_status(boolean document_status) {
        this.document_status = document_status;
    }

    public String getLoan_type() {
        return loan_type;
    }

    public void setLoan_type(String loan_type) {
        this.loan_type = loan_type;
    }

    public String getModule_type() {
        return module_type;
    }

    public void setModule_type(String module_type) {
        this.module_type = module_type;
    }

    public String getFile_path() {
        return file_path;
    }

    public void setFile_path(String file_path) {
        this.file_path = file_path;
    }

    public String getFull_display_name() {
        return full_display_name;
    }

    public void setFull_display_name(String full_display_name) {
        this.full_display_name = full_display_name;
    }

    public String getDocument_full_name() {
        return document_full_name;
    }

    public void setDocument_full_name(String document_full_name) {
        this.document_full_name = document_full_name;
    }

    public boolean isHeader() {
        return isHeader;
    }

    public void setHeader(boolean header) {
        isHeader = header;
    }

    public boolean isEditable() {
        return isEditable;
    }

    public void setEditable(boolean editable) {
        isEditable = editable;
    }

    public String getResponseMessage() {
        return ResponseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        ResponseMessage = responseMessage;
    }

    public String[] getFILES() {
        return FILES;
    }

    public void setFILES(String[] FILES) {
        this.FILES = FILES;
    }

    public byte[] getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(byte[] secretKey) {
        this.secretKey = secretKey;
    }

    public byte[] getRandomNumber() {
        return randomNumber;
    }

    public void setRandomNumber(byte[] randomNumber) {
        this.randomNumber = randomNumber;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public int getFileCount() {
        return fileCount;
    }

    public void setFileCount(int fileCount) {
        this.fileCount = fileCount;
    }

    public String getFile_format() {
        return file_format;
    }

    public void setFile_format(String file_format) {
        this.file_format = file_format;
    }

    public int getNumberOfImages() {
        return NumberOfImages;
    }

    public void setNumberOfImages(int numberOfImages) {
        NumberOfImages = numberOfImages;
    }
}
