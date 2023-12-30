package com.saartak.el.models.Logoff;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LogoffRequestDTO {

    @Expose
    @SerializedName("SpNameWithParameter")
    private List<LogoffRequestDTO.SpNameWithParameter> SpNameWithParameter;
    @Expose
    @SerializedName("ProjectName")
    private String ProjectName="EL";
    @Expose
    @SerializedName("IMEINumber")
    private String IMEINumber;
    @Expose
    @SerializedName("ConnectionString")
    private String ConnectionString="UsedCar";

    public List<LogoffRequestDTO.SpNameWithParameter> getSpNameWithParameter() {
        return SpNameWithParameter;
    }

    public void setSpNameWithParameter(List<LogoffRequestDTO.SpNameWithParameter> spNameWithParameter) {
        SpNameWithParameter = spNameWithParameter;
    }

    public void setProjectName(String projectName) {
        ProjectName = projectName;
    }

    public void setIMEINumber(String IMEINumber) {
        this.IMEINumber = IMEINumber;
    }

    public void setConnectionString(String connectionString) {
        ConnectionString = connectionString;
    }

    public static class SpNameWithParameter {
        @Expose
        @SerializedName("SpParameters")
        private LogoffRequestDTO.SpNameWithParameter.SpParameters SpParameters;
        @Expose
        @SerializedName("SpName")
        private String SpName;

        public static class SpParameters {
            @Expose
            @SerializedName("UserID")
            private String userId;

            public String getUserId() {
                return userId;
            }

            public void setUserId(String userId) {
                this.userId = userId;
            }
        }

        public void setSpParameters(LogoffRequestDTO.SpNameWithParameter.SpParameters spParameters) {
            SpParameters = spParameters;
        }

        public void setSpName(String spName) {
            SpName = spName;
        }

    }


}
