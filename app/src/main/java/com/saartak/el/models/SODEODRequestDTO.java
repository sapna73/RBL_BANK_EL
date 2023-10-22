package com.saartak.el.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class SODEODRequestDTO {

    @Expose
    private String ConnectionString = "audit";
    @Expose
    private String UserId = "101";
    @Expose
    private String IMEINumber = "50002";
    @Expose
    private String ProjectName = "LOS";
    @Expose
    ArrayList<SpNameWithParameterClass> SpNameWithParameter = new ArrayList<SpNameWithParameterClass>();

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

        @Override
        public String toString() {
            return "SpNameWithParameterClass{" +
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

        public SpParametersClass getSpParameters() {
            return SpParameters;
        }

        public void setSpParameters(SpParametersClass spParameters) {
            SpParameters = spParameters;
        }
    }

    public static class SpParametersClass {
        @SerializedName("staffId")
        @Expose
        private String staffId;

        @SerializedName("staffName")
        @Expose
        private String staffName;

        @SerializedName("branchId")
        @Expose
        private String branchId;

        @SerializedName("branchGSTcode")
        @Expose
        private String branchGSTcode;

        @SerializedName("created_date")
        @Expose
        private String created_date;

        @SerializedName("isSOD")
        @Expose
        private int isSOD;

        @SerializedName("isEOD")
        @Expose
        private int isEOD;

        @SerializedName("SOD_Latitude")
        @Expose
        private String SOD_Latitude;

        @SerializedName("SOD_Longitue")
        @Expose
        private String SOD_Longitue;

        @SerializedName("EOD_Latitude")
        @Expose
        private String EOD_Latitude;

        @SerializedName("EOD_Longitude")
        @Expose
        private String EOD_Longitude;

        public String getStaffId() {
            return staffId;
        }

        public void setStaffId(String staffId) {
            this.staffId = staffId;
        }

        public String getStaffName() {
            return staffName;
        }

        public void setStaffName(String staffName) {
            this.staffName = staffName;
        }

        public String getBranchId() {
            return branchId;
        }

        public void setBranchId(String branchId) {
            this.branchId = branchId;
        }

        public String getBranchGSTcode() {
            return branchGSTcode;
        }

        public void setBranchGSTcode(String branchGSTcode) {
            this.branchGSTcode = branchGSTcode;
        }

        public String getCreated_date() {
            return created_date;
        }

        public void setCreated_date(String created_date) {
            this.created_date = created_date;
        }

        public int getIsSOD() {
            return isSOD;
        }

        public void setIsSOD(int isSOD) {
            this.isSOD = isSOD;
        }

        public int getIsEOD() {
            return isEOD;
        }

        public void setIsEOD(int isEOD) {
            this.isEOD = isEOD;
        }

        public String getSOD_Latitude() {
            return SOD_Latitude;
        }

        public void setSOD_Latitude(String SOD_Latitude) {
            this.SOD_Latitude = SOD_Latitude;
        }

        public String getSOD_Longitue() {
            return SOD_Longitue;
        }

        public void setSOD_Longitue(String SOD_Longitue) {
            this.SOD_Longitue = SOD_Longitue;
        }

        public String getEOD_Latitude() {
            return EOD_Latitude;
        }

        public void setEOD_Latitude(String EOD_Latitude) {
            this.EOD_Latitude = EOD_Latitude;
        }

        public String getEOD_Longitude() {
            return EOD_Longitude;
        }

        public void setEOD_Longitude(String EOD_Longitude) {
            this.EOD_Longitude = EOD_Longitude;
        }
    }
}
