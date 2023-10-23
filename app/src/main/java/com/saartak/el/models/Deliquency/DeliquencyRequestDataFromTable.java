package com.saartak.el.models.Deliquency;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class DeliquencyRequestDataFromTable {
    @Expose
    private String ConnectionString = "audit";
    //        private String UserId="";
    @Expose
    private String IMEINumber = "";
    @Expose
    private String ProjectName = "";

    @Expose
    private String UserId = "";
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

//        public String getUserId() {
//            return UserId;
//        }

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

//        public void setUserId( String UserId ) {
//            this.UserId = UserId;
//        }

    public void setIMEINumber(String IMEINumber) {
        this.IMEINumber = IMEINumber;
    }

    public void setProjectName(String ProjectName) {
        this.ProjectName = ProjectName;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public static class SpNameWithParameterClass {
        @Expose
        private String SpName = "";

        @SerializedName("SpParameters")
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
        private String UniqueId = "";
        @Expose
        private String ProductId = "";

        public String getUniqueId() {
            return UniqueId;
        }

        public void setUniqueId(String uniqueId) {
            UniqueId = uniqueId;
        }

        public String getProductId() {
            return ProductId;
        }

        public void setProductId(String productId) {
            ProductId = productId;
        }
    }
}
