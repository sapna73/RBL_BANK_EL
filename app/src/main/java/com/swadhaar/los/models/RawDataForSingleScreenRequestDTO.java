package com.swadhaar.los.models;

import com.google.gson.annotations.Expose;

import java.util.List;

public class RawDataForSingleScreenRequestDTO {

    @Expose
    private String ConnectionString;
    @Expose
    private String UserId;
    @Expose
    private String IMEINumber;
    @Expose
    private String ProjectName;
    @Expose
    private List<SpNameWithParameterClass> SpNameWithParameter;

    public String getConnectionString() {
        return ConnectionString;
    }

    public void setConnectionString(String connectionString) {
        ConnectionString = connectionString;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getIMEINumber() {
        return IMEINumber;
    }

    public void setIMEINumber(String IMEINumber) {
        this.IMEINumber = IMEINumber;
    }

    public String getProjectName() {
        return ProjectName;
    }

    public void setProjectName(String projectName) {
        ProjectName = projectName;
    }

    public List<SpNameWithParameterClass> getSpNameWithParameter() {
        return SpNameWithParameter;
    }

    public void setSpNameWithParameter(List<SpNameWithParameterClass> spNameWithParameter) {
        SpNameWithParameter = spNameWithParameter;
    }

    public static class SpNameWithParameterClass {
        @Expose
        private String SpName;
        @Expose
        private SpParametersClass SpParameters;

        public String getSpName() {
            return SpName;
        }

        public void setSpName(String spName) {
            SpName = spName;
        }

        public SpParametersClass getSpParameters() {
            return SpParameters;
        }

        public void setSpParameters(SpParametersClass spParameters) {
            SpParameters = spParameters;
        }
    }

    public static class SpParametersClass {
        @Expose
        private String RowIdentity;
        @Expose
        private String UserId;
        @Expose
        private String RoleId;
        @Expose
        private String ScreenId;
        @Expose
        private String CustomerId;

        public String getRowIdentity() {
            return RowIdentity;
        }

        public void setRowIdentity(String rowIdentity) {
            RowIdentity = rowIdentity;
        }

        public String getUserId() {
            return UserId;
        }

        public void setUserId(String userId) {
            UserId = userId;
        }

        public String getRoleId() {
            return RoleId;
        }

        public void setRoleId(String roleId) {
            RoleId = roleId;
        }

        public String getScreenId() {
            return ScreenId;
        }

        public void setScreenId(String screenId) {
            ScreenId = screenId;
        }

        public String getCustomerId() {
            return CustomerId;
        }

        public void setCustomerId(String customerId) {
            CustomerId = customerId;
        }
    }
}
