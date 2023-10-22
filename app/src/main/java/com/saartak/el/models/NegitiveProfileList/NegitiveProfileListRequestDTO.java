package com.saartak.el.models.NegitiveProfileList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public  class NegitiveProfileListRequestDTO {
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

    public List<NegitiveProfileListRequestDTO.SpNameWithParameter> getSpNameWithParameter() {
        return SpNameWithParameter;
    }

    public void setSpNameWithParameter(List<NegitiveProfileListRequestDTO.SpNameWithParameter> spNameWithParameter) {
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

        public static class SpParameters {
            @Expose
            @SerializedName("ProductID")
            private String ProductID;
            @Expose
            @SerializedName("Type")
            private String Type;

            public void setProductID(String productID) {
                ProductID = productID;
            }

            public void setType(String type) {
                Type = type;
            }
        }

        public void setSpParameters(SpNameWithParameter.SpParameters spParameters) {
            SpParameters = spParameters;
        }

        public void setSpName(String spName) {
            SpName = spName;
        }
    }




}
