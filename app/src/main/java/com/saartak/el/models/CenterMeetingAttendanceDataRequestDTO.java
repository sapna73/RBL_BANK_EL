package com.saartak.el.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.saartak.el.database.entity.CenterMeetingAttendanceTable;

import java.util.ArrayList;

public class CenterMeetingAttendanceDataRequestDTO {

    @Expose
    private String ConnectionString = "jlg";
    //        private String UserId="";
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
        private CenterMeetingAttendanceTable SpParameters;

        public String getSpName() {
            return SpName;
        }

        public void setSpName(String spName) {
            SpName = spName;
        }

        public CenterMeetingAttendanceTable getSpParameters() {
            return SpParameters;
        }

        public void setSpParameters(CenterMeetingAttendanceTable spParameters) {
            SpParameters = spParameters;
        }
    }
}
