package com.saartak.el.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DocumentUploadRawdataResponseDTO {


    @SerializedName("SCREEN ID")
    @Expose
    private String screen_id;

    @SerializedName("FILE COUNT")
    @Expose
    private int fileCount = 0;

    @SerializedName("FILES")
    @Expose
    private String[] FILES;

    @SerializedName("CUSTOMER TYPE")
    @Expose
    private String customer_type;

    @SerializedName("DOCUMENT NAME")
    @Expose
    private String document_name;

    @SerializedName("DISPLAY NAME")
    @Expose
    private String display_name;

    public String getScreen_id() {
        return screen_id;
    }

    public void setScreen_id(String screen_id) {
        this.screen_id = screen_id;
    }

    public int getFileCount() {
        return fileCount;
    }

    public void setFileCount(int fileCount) {
        this.fileCount = fileCount;
    }

    public String[] getFILES() {
        return FILES;
    }

    public void setFILES(String[] FILES) {
        this.FILES = FILES;
    }

    public String getCustomer_type() {
        return customer_type;
    }

    public void setCustomer_type(String customer_type) {
        this.customer_type = customer_type;
    }

    public String getDocument_name() {
        return document_name;
    }

    public void setDocument_name(String document_name) {
        this.document_name = document_name;
    }

    public String getDisplay_name() {
        return display_name;
    }

    public void setDisplay_name(String display_name) {
        this.display_name = display_name;
    }
}
