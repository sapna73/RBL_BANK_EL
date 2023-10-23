package com.saartak.el.models.RATSourseOfIncome;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class RATSPRequestDTO {

    @Expose
    private String ConnectionString = "audit";
    @Expose
    private String UserId = "";
    @Expose
    private String IMEINumber = "";
    @Expose
    private String ProjectName = "EL";
    @Expose
    ArrayList<SpNameWithParameterClass> SpNameWithParameter = new ArrayList<SpNameWithParameterClass>();


    public void setSpNameWithParameter(ArrayList<SpNameWithParameterClass> spNameWithParameter) {
        SpNameWithParameter = spNameWithParameter;
    }

    public void setConnectionString(String connectionString) {
        ConnectionString = connectionString;
    }

    public void setIMEINumber(String IMEINumber) {
        this.IMEINumber = IMEINumber;
    }

    public void setProjectName(String projectName) {
        ProjectName = projectName;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public static class SpNameWithParameterClass {
        @Expose
        private String SpName = "USP_RATSourceOfIncomeMaster";

        @SerializedName("SpParameters")
        @Expose
        private SpParametersClass SpParameters;

        public void setSpName(String spName) {
            SpName = spName;
        }

        public void setSpParameters(SpParametersClass spParameters) {
            SpParameters = spParameters;
        }
    }

    public static class SpParametersClass {

        @Expose
        private String CustomerTypeID = "";

        @Expose
        private String Type = "";

        public void setCustomerTypeID(String customerTypeID) {
            CustomerTypeID = customerTypeID;
        }

        public void setType(String type) {
            Type = type;
        }
    }
}
