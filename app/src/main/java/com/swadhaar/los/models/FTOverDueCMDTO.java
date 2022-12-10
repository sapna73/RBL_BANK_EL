package com.swadhaar.los.models;

import com.google.gson.annotations.Expose;
import com.swadhaar.los.database.entity.FTOverDueCMTable;
import com.swadhaar.los.database.entity.OverDueCMTable;

import java.util.List;

public class FTOverDueCMDTO {
    @Expose
    String centerName;
    @Expose
    List<FTOverDueCMTable> ftOverDueCMTableList;

    public String getCenterName() {
        return centerName;
    }

    public void setCenterName(String centerName) {
        this.centerName = centerName;
    }

    public List<FTOverDueCMTable> getFtOverDueCMTableList() {
        return ftOverDueCMTableList;
    }

    public void setFtOverDueCMTableList(List<FTOverDueCMTable> ftOverDueCMTableList) {
        this.ftOverDueCMTableList = ftOverDueCMTableList;
    }
}
