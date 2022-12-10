package com.swadhaar.los.models;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;

public class RawDataRequestDTO {

    @Expose
    private String ConnectionString = "audit";
    @Expose
    private String UserId = "";
    @Expose
    private String IMEINumber = "";
    @Expose
    private String ProjectName = "";
    @Expose
    ArrayList<SpNameWithParameterClass> SpNameWithParameter = new ArrayList<SpNameWithParameterClass>();

    @Override
    public String toString() {
        return "RawDataRequestDTO{" +
                "ConnectionString='" + ConnectionString + '\'' +
                ", UserId='" + UserId + '\'' +
                ", IMEINumber='" + IMEINumber + '\'' +
                ", ProjectName='" + ProjectName + '\'' +
                ", SpNameWithParameter=" + SpNameWithParameter +
                '}';
    }

    public ArrayList<SpNameWithParameterClass> getSpNameWithParameter() {
        return SpNameWithParameter;
    }

    public void setSpNameWithParameter(ArrayList<SpNameWithParameterClass> spNameWithParameter) {
        SpNameWithParameter = spNameWithParameter;
    }

    // Getter Methods

    public String getConnectionString() {
        return ConnectionString;
    }

    public String getUserId() {
        return UserId;
    }

    public String getIMEINumber() {
        return IMEINumber;
    }

    public String getProjectName() {
        return ProjectName;
    }

    // Setter Methods

    public void setConnectionString(String ConnectionString) {
        this.ConnectionString = ConnectionString;
    }

    public void setUserId(String UserId) {
        this.UserId = UserId;
    }

    public void setIMEINumber(String IMEINumber) {
        this.IMEINumber = IMEINumber;
    }

    public void setProjectName(String ProjectName) {
        this.ProjectName = ProjectName;
    }

    public static class SpNameWithParameterClass {
        @Expose
        private String SpName = "";
        @Expose
        private SpParametersClass SpParameters;

        @Override
        public String toString() {
            return "SpNameWithParameterClass{" +
                    "SpName='" + SpName + '\'' +
                    ", SpParameters=" + SpParameters +
                    '}';
        }

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
        // TODO: ****** MSME PARAMETERS *********
        @Expose
        private String CustomerIdsCommaSeperated;
        @Expose
        private String RoleId;
        @Expose
        private String ProjectId;
        @Expose
        private String ProductId;
        @Expose
        private int AllSrc=1;

        // TODO: ****** MSME PARAMETERS *********

        // TODO: ****** JLG PARAMETERS *********
        @Expose
        private String uniqueId;
        @Expose
        private String UserId;
        @Expose
        private String Role;

        // TODO: ****** JLG PARAMETERS *********


        public String getCustomerIdsCommaSeperated() {
            return CustomerIdsCommaSeperated;
        }

        public void setCustomerIdsCommaSeperated(String customerIdsCommaSeperated) {
            CustomerIdsCommaSeperated = customerIdsCommaSeperated;
        }

        public String getRoleId() {
            return RoleId;
        }

        public void setRoleId(String roleId) {
            RoleId = roleId;
        }

        public String getProjectId() {
            return ProjectId;
        }

        public void setProjectId(String projectId) {
            ProjectId = projectId;
        }

        public String getProductId() {
            return ProductId;
        }

        public void setProductId(String productId) {
            ProductId = productId;
        }

        public int getAllSrc() {
            return AllSrc;
        }

        public void setAllSrc(int allSrc) {
            AllSrc = allSrc;
        }


        public String getUniqueId() {
            return uniqueId;
        }

        public void setUniqueId(String uniqueId) {
            this.uniqueId = uniqueId;
        }

        public String getUserId() {
            return UserId;
        }

        public void setUserId(String userId) {
            UserId = userId;
        }

        public String getRole() {
            return Role;
        }

        public void setRole(String role) {
            Role = role;
        }
    }
}
