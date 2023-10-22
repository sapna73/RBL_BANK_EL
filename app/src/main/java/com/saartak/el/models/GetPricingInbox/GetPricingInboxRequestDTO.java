package com.saartak.el.models.GetPricingInbox;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetPricingInboxRequestDTO {
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
            @SerializedName("Role")
            private String Role;
            @Expose
            @SerializedName("CustomerId")
            private String CustomerId;
            @Expose
            @SerializedName("BranchId")
            private String BranchId;
            @Expose
            @SerializedName("ProductId")
            private String ProductId;
            @Expose
            @SerializedName("ProjectId")
            private String ProjectId;
            @Expose
            @SerializedName("Status")
            private String status;

            public void setRole(String role) {
                Role = role;
            }

            public void setCustomerId(String customerId) {
                CustomerId = customerId;
            }

            public void setBranchId(String branchId) {
                BranchId = branchId;
            }

            public void setProductId(String productId) {
                ProductId = productId;
            }
            public void setProjectId(String projectId) {
                ProjectId = projectId;
            }

            public void setStatus(String status) {
                this.status = status;
            }
        }
    }

}
