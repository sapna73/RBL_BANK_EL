package com.saartak.el.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CenterMeetingAllCollectionMainRequestDTO {
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
        private String rawCollection = "";
        @Expose
        private String CreatedBy = "";

        public String getRawCollection() {
            return rawCollection;
        }

        public void setRawCollection(String rawCollection) {
            this.rawCollection = rawCollection;
        }

        public String getCreatedBy() {
            return CreatedBy;
        }

        public void setCreatedBy(String createdBy) {
            CreatedBy = createdBy;
        }
    }
}
