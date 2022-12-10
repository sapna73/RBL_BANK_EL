package com.swadhaar.los.models;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;

public class RawDataCBRequestDTO {

    @Expose
    private String ConnectionString = "audit";
    @Expose
    private String UserId = "101";
    @Expose
    private String IMEINumber = "50002";
    @Expose
    private String ProjectName = "IL";
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
        @Expose
        private String CustomerId = "";
        @Expose
        private String ScreenId = "";

        public String getCustomerId() {
            return CustomerId;
        }

        public void setCustomerId(String customerId) {
            CustomerId = customerId;
        }

        public String getScreenId() {
            return ScreenId;
        }

        public void setScreenId(String screenId) {
            ScreenId = screenId;
        }
    }
}
