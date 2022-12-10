package com.swadhaar.los.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.swadhaar.los.database.entity.QCReSubmissionTable;

import java.util.ArrayList;

public class QCReSubmissionDataResponseDTO {

    @SerializedName("Table")
    @Expose
    ArrayList<QCReSubmissionTable> qcReSubmissionTables;

    public ArrayList<QCReSubmissionTable> getQcReSubmissionTables() {
        return qcReSubmissionTables;
    }

    public void setQcReSubmissionTables(ArrayList<QCReSubmissionTable> qcReSubmissionTables) {
        this.qcReSubmissionTables = qcReSubmissionTables;
    }
}

