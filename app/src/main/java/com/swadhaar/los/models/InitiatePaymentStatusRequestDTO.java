package com.swadhaar.los.models;

import com.google.gson.annotations.Expose;

import java.util.List;

public class InitiatePaymentStatusRequestDTO {

    /**
     * ConnectionString : saarthimce
     * IMEINumber : 1234567890
     * ProjectName : Connect
     * SpNameWithParameter : [{"SpName":"usp_GetOnlineCollectionSummary","SpParameters":{"RequestedDate":"2020-09-03","UserId":"SIF1009362"}}]
     * UserId :
     */
    @Expose
    private String ConnectionString="saarthimce";
    @Expose
    private String IMEINumber="";
    @Expose
    private String ProjectName="";
    @Expose
    private String UserId;
    @Expose
    private List<SpNameWithParameterBean> SpNameWithParameter;

    public String getConnectionString() {
        return ConnectionString;
    }

    public void setConnectionString(String ConnectionString) {
        this.ConnectionString = ConnectionString;
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

    public void setProjectName(String ProjectName) {
        this.ProjectName = ProjectName;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String UserId) {
        this.UserId = UserId;
    }

    public List<SpNameWithParameterBean> getSpNameWithParameter() {
        return SpNameWithParameter;
    }

    public void setSpNameWithParameter(List<SpNameWithParameterBean> SpNameWithParameter) {
        this.SpNameWithParameter = SpNameWithParameter;
    }

    public static class SpNameWithParameterBean {
        /**
         * SpName : usp_GetOnlineCollectionSummary
         * SpParameters : {"RequestedDate":"2020-09-03","UserId":"SIF1009362"}
         */
        @Expose
        private String SpName;
        @Expose
        private SpParametersBean SpParameters;

        public String getSpName() {
            return SpName;
        }

        public void setSpName(String SpName) {
            this.SpName = SpName;
        }

        public SpParametersBean getSpParameters() {
            return SpParameters;
        }

        public void setSpParameters(SpParametersBean SpParameters) {
            this.SpParameters = SpParameters;
        }
    }

    public static class SpParametersBean {
        /**
         * RequestedDate : 2020-09-03
         * UserId : SIF1009362
         */
        @Expose
        private String RequestedDate;
        @Expose
        private String UserId;

        public String getRequestedDate() {
            return RequestedDate;
        }

        public void setRequestedDate(String RequestedDate) {
            this.RequestedDate = RequestedDate;
        }

        public String getUserId() {
            return UserId;
        }

        public void setUserId(String UserId) {
            this.UserId = UserId;
        }
    }
}
