package com.saartak.el.models.PINCodeArea;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PinCodeAreaRequestDTO {

    @Expose
    @SerializedName("SpNameWithParameter")
    private List<SpNameWithParameter> SpNameWithParameter;
    @Expose
    @SerializedName("ProjectName")
    private String ProjectName = "EL";
    @Expose
    @SerializedName("IMEINumber")
    private String IMEINumber;
    @Expose
    @SerializedName("ConnectionString")
    private String ConnectionString = "audit";

    public void setSpNameWithParameter(List<SpNameWithParameter> spNameWithParameter) {
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
        private SpNameWithParameter.SpParameters SpParameters;
        @Expose
        @SerializedName("SpName")
        private String SpName;

        public void setSpParameters(SpNameWithParameter.SpParameters spParameters) {
            SpParameters = spParameters;
        }

        public void setSpName(String spName) {
            SpName = spName;
        }

        public static class SpParameters {
            @Expose
            @SerializedName("CommAddressPin")
            private String commAddressPin = "";

            @Expose
            @SerializedName("ResiAddressPin")
            private String resiAddressPin = "";

            @Expose
            @SerializedName("OfficePin")
            private String officePin = "";

            public String getCommAddressPin() {
                return commAddressPin;
            }

            public void setCommAddressPin(String commAddressPin) {
                this.commAddressPin = commAddressPin;
            }

            public void setResiAddressPin(String resiAddressPin) {
                this.resiAddressPin = resiAddressPin;
            }

            public void setOfficePin(String officePin) {
                this.officePin = officePin;
            }
        }
    }

}
