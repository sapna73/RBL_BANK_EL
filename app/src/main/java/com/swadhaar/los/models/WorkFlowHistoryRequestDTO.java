package com.swadhaar.los.models;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;

public class WorkFlowHistoryRequestDTO {

   /* {"ConnectionString":"wf","UserId":"101","IMEINumber":"50002","ProjectName":"LOS",
            "SpNameWithParameter":[{"SpName":"usp_GetCustomersView",
            "SpParameters":{"userid":"SIF1004028","fromDate":"2020-01-01","toDate":"2020-04-30","wf":"4"}}]}*/

    @Expose
   private String ConnectionString = "wf";
    @Expose
    private String UserId = "";
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
        //  ,"SpParameters":{"userid":"SIF1004028","fromDate":"2020-01-01","toDate":"2020-04-30","wf":"4"}}

        @Expose
        private String userid = "";
        @Expose
        private String fromDate = "";
        @Expose
        private String toDate = "";
        @Expose
        private String wf = "";

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public void setFromDate(String fromDate) {
            this.fromDate = fromDate;
        }

        public void setToDate(String toDate) {
            this.toDate = toDate;
        }

        public void setWf(String wf) {
            this.wf = wf;
        }

        public String getUserid() {
            return userid;
        }

        public String getFromDate() {
            return fromDate;
        }

        public String getToDate() {
            return toDate;
        }

        public String getWf() {
            return wf;
        }
    }
}
