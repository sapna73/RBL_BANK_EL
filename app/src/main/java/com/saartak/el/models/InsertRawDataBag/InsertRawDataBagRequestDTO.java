package com.saartak.el.models.InsertRawDataBag;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class InsertRawDataBagRequestDTO {

    @Expose
    @SerializedName("SpNameWithParameter")
    private List<SpNameWithParameter> SpNameWithParameter;
    @Expose
    @SerializedName("ProjectName")
    private String ProjectName="EL";
    @Expose
    @SerializedName("UserId")
    private String userId;
    @Expose
    @SerializedName("IMEINumber")
    private String IMEINumber;
    @Expose
    @SerializedName("ConnectionString")
    private String ConnectionString="audit";

    public void setSpNameWithParameter(List<SpNameWithParameter> spNameWithParameter) {
        SpNameWithParameter = spNameWithParameter;
    }

    public void setProjectName(String projectName) {
        ProjectName = projectName;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setIMEINumber(String IMEINumber) {
        this.IMEINumber = IMEINumber;
    }

    public void setConnectionString(String connectionString) {
        ConnectionString = connectionString;
    }

    public static class SpNameWithParameter {
        @Expose
        @SerializedName("SpParameters")
        private SpParameters SpParameters;
        @Expose
        @SerializedName("SpName")
        private String SpName;

        public void setSpParameters(SpParameters spParameters) {
            SpParameters = spParameters;
        }

        public void setSpName(String spName) {
            SpName = spName;
        }

        public static  class SpParameters {
            @Expose
            @SerializedName("ActionName")
            private String ActionName;
            @Expose
            @SerializedName("StageId")
            private int StageId;
            @Expose
            @SerializedName("WorkflowId")
            private int WorkflowId;
            @Expose
            @SerializedName("RoleId")
            private String RoleId;
            @Expose
            @SerializedName("FieldId")
            private int FieldId;
            @SerializedName("RawDataBag")
            @Expose
            private String rawData;
            @Expose
            @SerializedName("CreatedBy")
            private String CreatedBy;
            @Expose
            @SerializedName("ModuleId")
            private String ModuleId;
            @Expose
            @SerializedName("ScreenId")
            private int ScreenId;
            @Expose
            @SerializedName("BCBRID")
            private String BCBRID;
            @Expose
            @SerializedName("BCID")
            private String BCID;
            @Expose
            @SerializedName("ProductId")
            private String ProductId;
            @Expose
            @SerializedName("ProjectId")
            private String ProjectId;
            @Expose
            @SerializedName("Uniqueid")
            private String Uniqueid;

            public void setActionName(String actionName) {
                ActionName = actionName;
            }

            public void setStageId(int stageId) {
                StageId = stageId;
            }

            public void setWorkflowId(int workflowId) {
                WorkflowId = workflowId;
            }

            public void setRoleId(String roleId) {
                RoleId = roleId;
            }

            public void setFieldId(int fieldId) {
                FieldId = fieldId;
            }

            public void setRawData(String rawData) {
                this.rawData = rawData;
            }

            public void setCreatedBy(String createdBy) {
                CreatedBy = createdBy;
            }

            public void setModuleId(String moduleId) {
                ModuleId = moduleId;
            }

            public void setScreenId(int screenId) {
                ScreenId = screenId;
            }

            public void setBCBRID(String BCBRID) {
                this.BCBRID = BCBRID;
            }

            public void setBCID(String BCID) {
                this.BCID = BCID;
            }

            public void setProductId(String productId) {
                ProductId = productId;
            }

            public void setProjectId(String projectId) {
                ProjectId = projectId;
            }

            public void setUniqueid(String uniqueid) {
                Uniqueid = uniqueid;
            }
        }
    }
}
