package com.saartak.el.models.CreditApprovalScreenPricing;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public  class CreditApprovalScreenPricingrequestDTO {

    @Expose
    @SerializedName("SpNameWithParameter")
    private List<SpNameWithParameter> SpNameWithParameter;
    @Expose
    @SerializedName("ProjectName")
    private String ProjectName;
    @Expose
    @SerializedName("IMEINumber")
    private String IMEINumber;
    @Expose
    @SerializedName("ConnectionString")
    private String ConnectionString="audit";


    public List<SpNameWithParameter> getSpNameWithParameter() {
        return SpNameWithParameter;
    }

    public void setSpNameWithParameter(List<SpNameWithParameter> spNameWithParameter) {
        SpNameWithParameter = spNameWithParameter;
    }

    public String getProjectName() {
        return ProjectName;
    }

    public void setProjectName(String projectName) {
        ProjectName = projectName;
    }

    public String getIMEINumber() {
        return IMEINumber;
    }

    public void setIMEINumber(String IMEINumber) {
        this.IMEINumber = IMEINumber;
    }

    public String getConnectionString() {
        return ConnectionString;
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

        public SpParameters getSpParameters() {
            return SpParameters;
        }

        public void setSpParameters(SpParameters spParameters) {
            SpParameters = spParameters;
        }

        public String getSpName() {
            return SpName;
        }

        public void setSpName(String spName) {
            SpName = spName;
        }

        public static class SpParameters {
            @Expose
            @SerializedName("CustomerId")
            private String CustomerId;
            @Expose
            @SerializedName("ProductId")
            private String ProductId;
            @Expose
            @SerializedName("ProjectId")
            private String ProjectId;
            @Expose
            @SerializedName("Reqfrom")
            private String Reqfrom="1";

            public String getCustomerId() {
                return CustomerId;
            }

            public void setCustomerId(String customerId) {
                CustomerId = customerId;
            }

            public String getReqfrom() {
                return Reqfrom;
            }

            public void setReqfrom(String reqfrom) {
                Reqfrom = reqfrom;
            }

            public String getProductId() {
                return ProductId;
            }

            public void setProductId(String productId) {
                ProductId = productId;
            }

            public String getProjectId() {
                return ProjectId;
            }

            public void setProjectId(String projectId) {
                ProjectId = projectId;
            }
        }
    }


}
