package com.saartak.el.models.BreSnsManageResults;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public  class BreSnsManageResultsRequestDTO {
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
            @SerializedName("Type")
            private String Type;
            @Expose
            @SerializedName("CreatedBy")
            private String CreatedBy;
            @Expose
            @SerializedName("LoanScheme")
            private String LoanScheme;
            @Expose
            @SerializedName("IsSNSWorkflow")
            private String IsSNSWorkflow;
            @Expose
            @SerializedName("SnsStatus")
            private String SnsStatus;
            @Expose
            @SerializedName("BreEligibleEMI")
            private String BreEligibleEMI;
            @Expose
            @SerializedName("BreROI")
            private String BreROI;
            @Expose
            @SerializedName("BreTenure")
            private String BreTenure;
            @Expose
            @SerializedName("BreEligibiltyLoanAmount")
            private String BreEligibiltyLoanAmount;
            @Expose
            @SerializedName("ProjectId")
            private String ProjectId;
            @Expose
            @SerializedName("ProductId")
            private String ProductId;
            @Expose
            @SerializedName("CustomerId")
            private String CustomerId;

            public void setType(String type) {
                Type = type;
            }

            public void setCreatedBy(String createdBy) {
                CreatedBy = createdBy;
            }

            public void setLoanScheme(String loanScheme) {
                LoanScheme = loanScheme;
            }

            public void setIsSNSWorkflow(String isSNSWorkflow) {
                IsSNSWorkflow = isSNSWorkflow;
            }

            public void setSnsStatus(String snsStatus) {
                SnsStatus = snsStatus;
            }

            public void setBreEligibleEMI(String breEligibleEMI) {
                BreEligibleEMI = breEligibleEMI;
            }

            public void setBreROI(String breROI) {
                BreROI = breROI;
            }

            public void setBreTenure(String breTenure) {
                BreTenure = breTenure;
            }

            public void setBreEligibiltyLoanAmount(String breEligibiltyLoanAmount) {
                BreEligibiltyLoanAmount = breEligibiltyLoanAmount;
            }

            public void setProjectId(String projectId) {
                ProjectId = projectId;
            }

            public void setProductId(String productId) {
                ProductId = productId;
            }

            public void setCustomerId(String customerId) {
                CustomerId = customerId;
            }
        }
    }


}
