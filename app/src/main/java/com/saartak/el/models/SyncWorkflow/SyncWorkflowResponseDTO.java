package com.saartak.el.models.SyncWorkflow;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class SyncWorkflowResponseDTO {

    @SerializedName("Table")
    @Expose
    ArrayList<SyncWorkflowResponseTable> syncWorkflowResponseTable;

    public ArrayList<SyncWorkflowResponseTable> getSyncWorkflowResponseTable() {
        return syncWorkflowResponseTable;
    }

    public void setSyncWorkflowResponseTable(ArrayList<SyncWorkflowResponseTable> syncWorkflowResponseTable) {
        this.syncWorkflowResponseTable = syncWorkflowResponseTable;
    }
}
