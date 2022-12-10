package com.swadhaar.los.models;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;

public class CenterNameRequestDTO {
  //  "ConnectionString":"jlg","IMEINumber":"358520071961721","ProjectName":"",

    @Expose
    private String ConnectionString = "jlg";
    @Expose
    private String IMEINumber = "";
    @Expose
    private String ProjectName = "";
    @Expose
    ArrayList<SpNameWithParameterClass> SpNameWithParameter = new ArrayList<SpNameWithParameterClass>();

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
    // "SpNameWithParameter":[{"SpName":"usp_GenCenterSeries","SpParameters": { "BranchId": "1031", "StaffId": "SIF1004141", "VillageName":"ROHTAK", "SequenceNumber":"3"}

        @Expose
        private String BranchId = "";
        @Expose
        private String StaffId = "";
        @Expose
        private String VillageName = "";
        @Expose
        private String SequenceNumber = "";

        public String getBranchId() {
            return BranchId;
        }

        public String getStaffId() {
            return StaffId;
        }

        public String getVillageName() {
            return VillageName;
        }

        public String getSequenceNumber() {
            return SequenceNumber;
        }

        public void setBranchId(String branchId) {
            BranchId = branchId;
        }

        public void setStaffId(String staffId) {
            StaffId = staffId;
        }

        public void setVillageName(String villageName) {
            VillageName = villageName;
        }

        public void setSequenceNumber(String sequenceNumber) {
            SequenceNumber = sequenceNumber;
        }
    }

}
