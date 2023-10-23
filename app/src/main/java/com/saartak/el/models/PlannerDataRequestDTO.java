package com.saartak.el.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class PlannerDataRequestDTO {

    @Expose
    private String ConnectionString = "audit";
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

        // "SpParameters":{"ActivityRefNum":"1004141200506174447","BranchId":"1031","Start_Lat":"17.4611175","Start_Long":"78.4505284","End_Lat":"17.4611175",


        @SerializedName("ActivityRefNum")
        @Expose
        private String activityRefNum;

        @SerializedName("BranchId")
        @Expose
        private String branchId;

        @SerializedName("Start_Lat")
        @Expose
        private String start_Lat;

        @SerializedName("Start_Long")
        @Expose
        private String start_Long;

        @SerializedName("End_Lat")
        @Expose
        private String End_Lat;

        @SerializedName("End_Long")
        @Expose
        private String End_Long;

        @SerializedName("TotalDistance")
        @Expose
        private String TotalDistance;

//          "End_Long":"78.4505284","TotalDistance":"2.342","OwnVehical":"Y","TypeOfVehical":"TW","Purpose":"LEAD","UserId":"SIF1004141",
//        EMP ID     "AccompaniedId":"1004141200506174447","ApplicationId":"1004141200506174447","FreeText1":"abc","FreeText2":"abcd","FreeText3":"ghhjj"}}],"UserId":""}


        @SerializedName("OwnVehical")
        @Expose
        private String OwnVehical;

        @SerializedName("TypeOfVehical")
        @Expose
        private String TypeOfVehical;

        @SerializedName("Purpose")
        @Expose
        private String Purpose;

        @SerializedName("UserId")
        @Expose
        private String UserId;

        @SerializedName("AccompaniedId")
        @Expose
        private String AccompaniedId;

        @SerializedName("ApplicationId")
        @Expose
        private String ApplicationId;

        @SerializedName("FreeText1")
        @Expose
        private String FreeText1;


        @SerializedName("FreeText2")
        @Expose
        private String FreeText2;

        @SerializedName("FreeText3")
        @Expose
        private String FreeText3;

        public void setActivityRefNum(String activityRefNum) {
            this.activityRefNum = activityRefNum;
        }

        public void setBranchId(String branchId) {
            this.branchId = branchId;
        }

        public void setStart_Lat(String start_Lat) {
            this.start_Lat = start_Lat;
        }

        public void setStart_Long(String start_Long) {
            this.start_Long = start_Long;
        }

        public void setEnd_Lat(String end_Lat) {
            End_Lat = end_Lat;
        }

        public void setEnd_Long(String end_Long) {
            End_Long = end_Long;
        }

        public void setTotalDistance(String totalDistance) {
            TotalDistance = totalDistance;
        }

        public void setOwnVehical(String ownVehical) {
            OwnVehical = ownVehical;
        }

        public void setTypeOfVehical(String typeOfVehical) {
            TypeOfVehical = typeOfVehical;
        }

        public void setPurpose(String purpose) {
            Purpose = purpose;
        }

        public void setUserId(String userId) {
            UserId = userId;
        }

        public void setAccompaniedId(String accompaniedId) {
            AccompaniedId = accompaniedId;
        }

        public void setApplicationId(String applicationId) {
            ApplicationId = applicationId;
        }

        public void setFreeText1(String freeText1) {
            FreeText1 = freeText1;
        }

        public void setFreeText2(String freeText2) {
            FreeText2 = freeText2;
        }

        public void setFreeText3(String freeText3) {
            FreeText3 = freeText3;
        }


        public String getActivityRefNum() {
            return activityRefNum;
        }

        public String getBranchId() {
            return branchId;
        }

        public String getStart_Lat() {
            return start_Lat;
        }

        public String getStart_Long() {
            return start_Long;
        }

        public String getEnd_Lat() {
            return End_Lat;
        }

        public String getEnd_Long() {
            return End_Long;
        }

        public String getTotalDistance() {
            return TotalDistance;
        }

        public String getOwnVehical() {
            return OwnVehical;
        }

        public String getTypeOfVehical() {
            return TypeOfVehical;
        }

        public String getPurpose() {
            return Purpose;
        }

        public String getUserId() {
            return UserId;
        }

        public String getAccompaniedId() {
            return AccompaniedId;
        }

        public String getApplicationId() {
            return ApplicationId;
        }

        public String getFreeText1() {
            return FreeText1;
        }

        public String getFreeText2() {
            return FreeText2;
        }

        public String getFreeText3() {
            return FreeText3;
        }


    }
}


