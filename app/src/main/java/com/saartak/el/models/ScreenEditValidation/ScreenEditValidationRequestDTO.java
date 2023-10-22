package com.saartak.el.models.ScreenEditValidation;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ScreenEditValidationRequestDTO {
    @Expose
    @SerializedName("SpNameWithParameter")
    private List<SpNameWithParameter> SpNameWithParameter;
    @Expose
    @SerializedName("ProjectName")
    private String ProjectName;
    @Expose
    @SerializedName("IMEINumber")
    private String IMEINumber;
    @Expose
    @SerializedName("UserId")
    private String UserId;
    @Expose
    @SerializedName("ConnectionString")
    private String ConnectionString="Wf";

    public void setSpNameWithParameter(List<SpNameWithParameter> spNameWithParameter) {
        SpNameWithParameter = spNameWithParameter;
    }

    public void setProjectName(String projectName) {
        ProjectName = projectName;
    }

    public void setIMEINumber(String IMEINumber) {
        this.IMEINumber = IMEINumber;
    }

    public void setUserId(String userId) {
        UserId = userId;
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

        public static class SpParameters {
            @Expose
            @SerializedName("ApplicationId")
            private String applicationId;
            @Expose
            @SerializedName("WorkFlowId")
            private String WorkFlowId;

            public void setApplicationId(String applicationId) {
                this.applicationId = applicationId;
            }

            public void setWorkFlowId(String workFlowId) {
                WorkFlowId = workFlowId;
            }

        }
    }


}
