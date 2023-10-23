package com.saartak.el.models.TWLMakeModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetTWLDataRequestDTO {
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
        private SpParameters SpParameters;
        @Expose
        @SerializedName("SpName")
        private String SpName="";

        public void setSpParameters(SpParameters spParameters) {
            SpParameters = spParameters;
        }

        public void setSpName(String spName) {
            SpName = spName;
        }

        public static class SpParameters {
            @Expose
            @SerializedName("Type")
            private String Type;
            @Expose
            @SerializedName("ElectricModel")
            private String ElectricModel;
            @Expose
            @SerializedName("EngineCC")
            private String EngineCC;
            @Expose
            @SerializedName("Twowheelertype")
            private String Twowheelertype;
            @Expose
            @SerializedName("Variant")
            private String Variant;
            @Expose
            @SerializedName("Model")
            private String Model;
            @Expose
            @SerializedName("Manufacturer")
            private String Manufacturer;
            @Expose
            @SerializedName("State")
            private String State;
            @Expose
            @SerializedName("Category")
            private String category;

            public void setType(String type) {
                Type = type;
            }

            public void setElectricModel(String electricModel) {
                ElectricModel = electricModel;
            }

            public void setEngineCC(String engineCC) {
                EngineCC = engineCC;
            }

            public void setTwowheelertype(String twowheelertype) {
                Twowheelertype = twowheelertype;
            }

            public void setVariant(String variant) {
                Variant = variant;
            }

            public void setModel(String model) {
                Model = model;
            }

            public void setManufacturer(String manufacturer) {
                Manufacturer = manufacturer;
            }

            public void setState(String state) {
                State = state;
            }

            public void setCategory(String category) {
                this.category = category;
            }
        }
    }


}
