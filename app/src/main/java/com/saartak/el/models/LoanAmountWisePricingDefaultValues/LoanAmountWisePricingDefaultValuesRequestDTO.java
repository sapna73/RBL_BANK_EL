package com.saartak.el.models.LoanAmountWisePricingDefaultValues;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public  class LoanAmountWisePricingDefaultValuesRequestDTO {

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
        private String SpName;

        public void setSpParameters(SpNameWithParameter.SpParameters spParameters) {
            SpParameters = spParameters;
        }

        public void setSpName(String spName) {
            SpName = spName;
        }

        public static class SpParameters {
            @Expose
            @SerializedName("city")
            private String city;
            @Expose
            @SerializedName("LoanAmount")
            private String LoanAmount;
            @Expose
            @SerializedName("ProductId")
            private String ProductId;
            @Expose
            @SerializedName("ProjectId")
            private String ProjectId;

            public void setCity(String city) {
                this.city = city;
            }

            public void setLoanAmount(String loanAmount) {
                LoanAmount = loanAmount;
            }

            public void setProductId(String productId) {
                ProductId = productId;
            }

            public void setProjectId(String projectId) {
                ProjectId = projectId;
            }
        }
    }


}
