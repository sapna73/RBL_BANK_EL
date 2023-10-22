package com.saartak.el.models;

import com.google.gson.annotations.Expose;
import com.saartak.el.database.entity.OverDueCMTable;

import java.util.List;

public class OverDueCMDTO {
    @Expose
    String centerName;
    @Expose
    List<OverDueCMTable> overDueCMTableList;

    public String getCenterName() {
        return centerName;
    }

    public void setCenterName(String centerName) {
        this.centerName = centerName;
    }

    public List<OverDueCMTable> getOverDueCMTableList() {
        return overDueCMTableList;
    }

    public void setOverDueCMTableList(List<OverDueCMTable> overDueCMTableList) {
        this.overDueCMTableList = overDueCMTableList;
    }
}
