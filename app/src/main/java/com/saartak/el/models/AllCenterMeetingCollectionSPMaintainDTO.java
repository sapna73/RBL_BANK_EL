package com.saartak.el.models;

import com.google.gson.annotations.Expose;

public class AllCenterMeetingCollectionSPMaintainDTO {

    @Expose
    private String tableName;
    @Expose
    private int tableFieldPOS;
    @Expose
    private String tableRefID;
    @Expose
    private String responseJson;

    public String getTableRefID() {
        return tableRefID;
    }

    public void setTableRefID(String tableRefID) {
        this.tableRefID = tableRefID;
    }

    public String getResponseJson() {
        return responseJson;
    }

    public void setResponseJson(String responseJson) {
        this.responseJson = responseJson;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public int getTableFieldPOS() {
        return tableFieldPOS;
    }

    public void setTableFieldPOS(int tableFieldPOS) {
        this.tableFieldPOS = tableFieldPOS;
    }

}
