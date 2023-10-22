package com.saartak.el.models;

import com.google.gson.annotations.Expose;
import com.saartak.el.database.entity.CenterCreationTable;

public class CGTDetailsDTO {

    @Expose
    private CenterCreationTable centerCreationTable;
    @Expose
    String message;
    @Expose
    boolean syncSuccess;


    public CenterCreationTable getCenterCreationTable() {
        return centerCreationTable;
    }

    public void setCenterCreationTable(CenterCreationTable centerCreationTable) {
        this.centerCreationTable = centerCreationTable;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSyncSuccess() {
        return syncSuccess;
    }

    public void setSyncSuccess(boolean syncSuccess) {
        this.syncSuccess = syncSuccess;
    }
}
