package com.saartak.el.models;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;

public class QCReSubmissionDataRequestDTO {

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

//        @SerializedName("CMDate")
//        @Expose
//        private String cmDate;
//
//        @SerializedName("LOID")
//        @Expose
//        private String loId;
//
//
//        public String getCmDate() {
//            return cmDate;
//        }
//
//        public void setCmDate(String cmDate) {
//            this.cmDate = cmDate;
//        }
//
//        public String getLoId() {
//            return loId;
//        }
//
//        public void setLoId(String loId) {
//            this.loId = loId;
//        }

    }
}
