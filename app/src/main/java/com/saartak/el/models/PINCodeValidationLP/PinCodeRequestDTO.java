package com.saartak.el.models.PINCodeValidationLP;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public  class PinCodeRequestDTO {
    @Expose
    @SerializedName("SpNameWithParameter")
    private List<SpNameWithParameter> SpNameWithParameter;
    @Expose
    @SerializedName("ProjectName")
    private String ProjectName="EL";
    @Expose
    @SerializedName("IMEINumber")
    private String IMEINumber;
    @Expose
    @SerializedName("ConnectionString")
    private String ConnectionString="audit";

    public void setSpNameWithParameter(List<PinCodeRequestDTO.SpNameWithParameter> spNameWithParameter) {
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
        private SpParameters SpParameters;
        @Expose
        @SerializedName("SpName")
        private String SpName;

        public void setSpParameters(SpParameters spParameters) {
            SpParameters = spParameters;
        }

        public void setSpName(String spName) {
            SpName = spName;
        }

        public static class SpParameters {
            @Expose
            @SerializedName("officePin")
            private String officePin;
            @Expose
            @SerializedName("AreaName")
            private String areaName="";
            @Expose
            @SerializedName("BusinessAddressPin")
            private String BusinessAddressPin;
            @Expose
            @SerializedName("CommAddressPin")
            private String CommAddressPin="";
            @Expose
            @SerializedName("ResiAddressPin")
            private String ResiAddressPin;

            public void setOfficePin(String officePin) {
                this.officePin = officePin;
            }

            public void setBusinessAddressPin(String businessAddressPin) {
                BusinessAddressPin = businessAddressPin;
            }

            public void setCommAddressPin(String commAddressPin) {
                CommAddressPin = commAddressPin;
            }

            public void setResiAddressPin(String resiAddressPin) {
                ResiAddressPin = resiAddressPin;
            }

            public void setAreaName(String areaName) {
                this.areaName = areaName;
            }
        }
    }

}
