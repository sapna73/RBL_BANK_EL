package com.saartak.el.models.ProcessPricingWF;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public  class ProcessPricingWFRequestDTO {
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
            @SerializedName("Status")
            private String Status="";
            @Expose
            @SerializedName("RequestedDocCharges")
            private String RequestedDocCharges="0";
            @Expose
            @SerializedName("RequestedPF")
            private String RequestedPF="0";
            @Expose
            @SerializedName("RequestedROI")
            private String RequestedROI="0";
            @Expose
            @SerializedName("City")
            private String City="";
            @Expose
            @SerializedName("RequestedLoanAmount_VehicleCost")
            private String RequestedLoanAmount_VehicleCost="0";
            @Expose
            @SerializedName("CustomerId")
            private String CustomerId="";
            @Expose
            @SerializedName("CustomerName")
            private String CustomerName="";
            @Expose
            @SerializedName("CreatedUpdatedBy")
            private String CreatedUpdatedBy="";
            @Expose
            @SerializedName("BranchId")
            private String BranchId="";
            @Expose
            @SerializedName("ProductId")
            private String ProductId="";
            @Expose
            @SerializedName("ProjectId")
            private String ProjectId="";

            public void setStatus(String status) {
                Status = status;
            }

            public void setRequestedDocCharges(String requestedDocCharges) {
                RequestedDocCharges = requestedDocCharges;
            }

            public void setRequestedPF(String requestedPF) {
                RequestedPF = requestedPF;
            }

            public void setRequestedROI(String requestedROI) {
                RequestedROI = requestedROI;
            }

            public void setCity(String city) {
                City = city;
            }

            public void setRequestedLoanAmount_VehicleCost(String requestedLoanAmount_VehicleCost) {
                RequestedLoanAmount_VehicleCost = requestedLoanAmount_VehicleCost;
            }

            public void setCustomerId(String customerId) {
                CustomerId = customerId;
            }

            public void setCustomerName(String customerName) {
                CustomerName = customerName;
            }

            public void setCreatedUpdatedBy(String createdUpdatedBy) {
                CreatedUpdatedBy = createdUpdatedBy;
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
        }
    }

}
