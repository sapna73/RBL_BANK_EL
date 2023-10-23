package com.saartak.el.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class InsertRawDataRequest {
    @Expose
    @SerializedName("RawDataBag")
    private String RawDataBag;
    @Expose
    @SerializedName("WorkflowId")
    private int WorkflowId;
    @Expose
    @SerializedName("Uniqueid")
    private String Uniqueid;
    @Expose
    @SerializedName("StageId")
    private int StageId;
    @Expose
    @SerializedName("ScreenId")
    private int ScreenId;
    @Expose
    @SerializedName("RoleId")
    private String RoleId;
    @Expose
    @SerializedName("ProjectId")
    private String ProjectId;
    @Expose
    @SerializedName("ProductId")
    private String ProductId;
    @Expose
    @SerializedName("ModuleId")
    private String ModuleId;
    @Expose
    @SerializedName("FieldId")
    private int FieldId;
    @Expose
    @SerializedName("CreatedBy")
    private String CreatedBy;
    @Expose
    @SerializedName("BCID")
    private String BCID;
    @Expose
    @SerializedName("BCBRID")
    private String BCBRID;
    @Expose
    @SerializedName("ActionName")
    private String ActionName;

    public void setRawDataBag(String rawDataBag) {
        RawDataBag = rawDataBag;
    }

    public void setWorkflowId(int workflowId) {
        WorkflowId = workflowId;
    }

    public void setUniqueid(String uniqueid) {
        Uniqueid = uniqueid;
    }

    public void setStageId(int stageId) {
        StageId = stageId;
    }

    public void setScreenId(int screenId) {
        ScreenId = screenId;
    }

    public void setRoleId(String roleId) {
        RoleId = roleId;
    }

    public void setProjectId(String projectId) {
        ProjectId = projectId;
    }

    public void setProductId(String productId) {
        ProductId = productId;
    }

    public void setModuleId(String moduleId) {
        ModuleId = moduleId;
    }

    public void setFieldId(int fieldId) {
        FieldId = fieldId;
    }

    public void setCreatedBy(String createdBy) {
        CreatedBy = createdBy;
    }

    public void setBCID(String BCID) {
        this.BCID = BCID;
    }

    public void setBCBRID(String BCBRID) {
        this.BCBRID = BCBRID;
    }

    public void setActionName(String actionName) {
        ActionName = actionName;
    }
}
