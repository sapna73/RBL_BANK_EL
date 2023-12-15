package com.saartak.el.models;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;

public class LeadMasterRequestDTO {

    @Expose
    private String ConnectionString = "audit";
    @Expose
    private String UserId = "101";
    @Expose
    private String IMEINumber = "50002";
    @Expose
    private String ProjectName = "LOS";
    @Expose
    ArrayList<LeadMasterRequestDTO.SpLeadNameWithParameterClass> SpNameWithParameter = new ArrayList<LeadMasterRequestDTO.SpLeadNameWithParameterClass>();

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

    public ArrayList<LeadMasterRequestDTO.SpLeadNameWithParameterClass> getSpNameWithParameter() {
        return SpNameWithParameter;
    }

    public void setSpNameWithParameter(ArrayList<LeadMasterRequestDTO.SpLeadNameWithParameterClass> spNameWithParameter) {
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

    public static class SpLeadNameWithParameterClass {
        @Expose
        private String SpName = "";
        @Expose
        private LeadMasterRequestDTO.SpParametersClass SpParameters;

        @Override
        public String toString() {
            return "SpLeadNameWithParameterClass{" +
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

        public LeadMasterRequestDTO.SpParametersClass getSpParameters() {
            return SpParameters;
        }

        public void setSpParameters(LeadMasterRequestDTO.SpParametersClass spParameters) {
            SpParameters = spParameters;
        }
    }

    public static class SpParametersClass {
        @Expose
        private String SegmentId = "";
        @Expose
        private String BCID = "";

        public String getSegmentId(String productId) {
            return SegmentId;
        }

        public void setSegmentId(String segmentId) {
            SegmentId = segmentId;
        }

        public String getBCID() {
            return BCID;
        }

        public void setBCID(String BCID) {
            this.BCID = BCID;
        }
    }
}
