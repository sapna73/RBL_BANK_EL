package com.saartak.el.models;

import com.google.gson.annotations.Expose;
import com.saartak.el.database.entity.FTOverDueCMTable;

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
