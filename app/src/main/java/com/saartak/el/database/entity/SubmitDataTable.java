package com.saartak.el.database.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

@Entity
public class SubmitDataTable {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @SerializedName("id")
    @Expose (serialize = false, deserialize = false)
    private int id;

    @SerializedName("BCID")
    @Expose
    private String BCID;

    @SerializedName("BCBRID")
    @Expose
    private String BCBRID;

    @SerializedName("ProjectId")
    @Expose
    private String ProjectId="1";

    @SerializedName("ScreenId")
    @Expose
    private String ScreenId;

    @SerializedName("ScreenName")
    @Expose
    private String ScreenName;

    @SerializedName("moduleType")
    @Expose
    private String moduleType;

    @SerializedName("ProductId")
    @Expose
    private String ProductId="";

    @SerializedName("ModuleId")
    @Expose
    private String ModuleId="APP";

    @SerializedName("CreatedBy")
    @Expose
    private String CreatedBy;

    @SerializedName("RawData")
    @Expose
//    @TypeConverters(DataTypeConverter.class)
    private String RawData;

    @SerializedName("ActionName")
    @Expose
    private String ActionName;

    @SerializedName("FieldName")
    @Expose
    private String FieldName;

    @SerializedName("value")
    @Expose
    private String value;

    @SerializedName("timestamp")
    @Expose
    private Date timestamp;

    @SerializedName("request")
    @Expose (serialize = false, deserialize = false)
    private String request;

    @SerializedName("response")
    @Expose
    private String response;

    @SerializedName("UniqueID")
    @Expose
    private String UniqueID;

    @SerializedName("ApplicationId")
    @Expose
    private String ApplicationId;

    @SerializedName("EntityId")
    @Expose
    private String EntityId="1031";

    @SerializedName("IMEINumber")
    @Expose
    private String IMEINumber;

    @SerializedName("CurrentStageId")
    @Expose
    private int CurrentStageId;

    @SerializedName("remarks")
    @Expose
    private String remarks;

    @SerializedName("RoleId")
    @Expose
    private String RoleId;

    @SerializedName("StageId")
    @Expose
    private String StageId;

    @SerializedName("FieldId")
    @Expose
    private String FieldId="0";

    @SerializedName("WorkflowId")
    @Expose
    private String WorkflowId;

    public SubmitDataTable(int id, String BCID, String BCBRID, String projectId, String screenId,
                           String productId, String moduleId, String createdBy, String rawData,
                           String actionName, String fieldName, String value, Date timestamp,
                           String response, String uniqueID) {
        this.id = id;
        this.BCID = BCID;
        this.BCBRID = BCBRID;
        ProjectId = projectId;
        ScreenId = screenId;
        ProductId = productId;
        ModuleId = moduleId;
        CreatedBy = createdBy;
        RawData = rawData;
        ActionName = actionName;
        FieldName = fieldName;
        this.value = value;
        this.timestamp = timestamp;
        this.response=response;
        this.UniqueID=uniqueID;

    }

    public SubmitDataTable() {
        // EMPTY CONSTRUCTOR
    }

    public String getEntityId() {
        return EntityId;
    }

    public void setEntityId(String entityId) {
        EntityId = entityId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFieldName() {
        return FieldName;
    }

    public void setFieldName(String fieldName) {
        FieldName = fieldName;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getActionName() {
        return ActionName;
    }

    public void setActionName(String actionName) {
        ActionName = actionName;
    }

    public String getBCID() {
        return BCID;
    }

    public void setBCID(String BCID) {
        this.BCID = BCID;
    }

    public String getBCBRID() {
        return BCBRID;
    }

    public void setBCBRID(String BCBRID) {
        this.BCBRID = BCBRID;
    }

    public String getProjectId() {
        return ProjectId;
    }

    public void setProjectId(String projectId) {
        ProjectId = projectId;
    }

    public String getScreenId() {
        return ScreenId;
    }

    public void setScreenId(String screenId) {
        ScreenId = screenId;
    }

    public String getProductId() {
        return ProductId;
    }

    public void setProductId(String productId) {
        ProductId = productId;
    }

    public String getModuleId() {
        return ModuleId;
    }

    public void setModuleId(String moduleId) {
        ModuleId = moduleId;
    }

    public String getCreatedBy() {
        return CreatedBy;
    }

    public void setCreatedBy(String createdBy) {
        CreatedBy = createdBy;
    }

    public String getRawData() {
        return RawData;
    }

    public void setRawData(String rawData) {
        RawData = rawData;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getUniqueID() {
        return UniqueID;
    }

    public void setUniqueID(String uniqueID) {
        UniqueID = uniqueID;
    }

    public String getRoleId() {
        return RoleId;
    }

    public void setRoleId(String roleId) {
        RoleId = roleId;
    }

    public String getWorkflowId() {
        return WorkflowId;
    }

    public void setWorkflowId(String workflowId) {
        WorkflowId = workflowId;
    }

    public String getStageId() {
        return StageId;
    }

    public void setStageId(String stageId) {
        StageId = stageId;
    }

    public String getFieldId() {
        return FieldId;
    }

    public void setFieldId(String fieldId) {
        FieldId = fieldId;
    }

    public String getApplicationId() {
        return ApplicationId;
    }

    public void setApplicationId(String applicationId) {
        ApplicationId = applicationId;
    }


    public int getCurrentStageId() {
        return CurrentStageId;
    }

    public void setCurrentStageId(int currentStageId) {
        CurrentStageId = currentStageId;
    }

    public String getModuleType() {
        return moduleType;
    }

    public void setModuleType(String moduleType) {
        this.moduleType = moduleType;
    }

    public String getScreenName() {
        return ScreenName;
    }

    public void setScreenName(String screenName) {
        ScreenName = screenName;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getIMEINumber() {
        return IMEINumber;
    }

    public void setIMEINumber(String IMEINumber) {
        this.IMEINumber = IMEINumber;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }
}
