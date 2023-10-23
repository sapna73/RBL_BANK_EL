package com.saartak.el.models.Deliquency;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public  class DeliquencyResponseDTO {


    @Expose
    @SerializedName("ApiResponse")
    private List<ApiResponse> ApiResponse;
    @Expose
    @SerializedName("ErrorMessage")
    private String ErrorMessage;
    @Expose
    @SerializedName("ErrorCode")
    private String ErrorCode;
    @Expose
    @SerializedName("ResponseMessage")
    private String ResponseMessage;
    @Expose
    @SerializedName("ResponseCode")
    private String ResponseCode;
    @Expose
    @SerializedName("UniqueId")
    private String UniqueId;

    @Expose
    @SerializedName("ModuleType")
    private String moduleType;

    public String getModuleType() {
        return moduleType;
    }

    public void setModuleType(String moduleType) {
        this.moduleType = moduleType;
    }

    public List<ApiResponse> getApiResponse() {
        return ApiResponse;
    }

    public String getErrorMessage() {
        return ErrorMessage;
    }

    public String getErrorCode() {
        return ErrorCode;
    }

    public String getResponseMessage() {
        return ResponseMessage;
    }

    public String getResponseCode() {
        return ResponseCode;
    }

    public String getUniqueId() {
        return UniqueId;
    }

    public static class ApiResponse {
        @Expose
        @SerializedName("MatchCount")
        private String MatchCount;
        @Expose
        @SerializedName("TWriteOff")
        private String TWriteOff;
        @Expose
        @SerializedName("L24Classification")
        private String L24Classification;
        @Expose
        @SerializedName("CloseDate")
        private String CloseDate;
        @Expose
        @SerializedName("CurrentDPD")
        private String CurrentDPD;
        @Expose
        @SerializedName("Classification")
        private String Classification;
        @Expose
        @SerializedName("CustId")
        private String CustId;
        @Expose
        @SerializedName("Lmd")
        private String Lmd;
        @Expose
        @SerializedName("LmdBy")
        private String LmdBy;
        @Expose
        @SerializedName("Crd")
        private String Crd;
        @Expose
        @SerializedName("CrdBy")
        private String CrdBy;
        @Expose
        @SerializedName("Product_ID")
        private String Product_ID;
        @Expose
        @SerializedName("ID")
        private String ID;
        @Expose
        @SerializedName("IsDisabled")
        private String IsDisabled;
        @Expose
        @SerializedName("SourceSystem")
        private String SourceSystem;
        @Expose
        @SerializedName("ProductSource")
        private String ProductSource;
        @Expose
        @SerializedName("Advances")
        private String Advances;
        @Expose
        @SerializedName("LoanAccountNo")
        private String LoanAccountNo;
        @Expose
        @SerializedName("Name")
        private String Name;
        @Expose
        @SerializedName("UCICNo")
        private String UCICNo;

        public String getMatchCount() {
            return MatchCount;
        }
        public String getTWriteOff() {
            return TWriteOff;
        }

        public String getL24Classification() {
            return L24Classification;
        }

        public String getCloseDate() {
            return CloseDate;
        }

        public String getCurrentDPD() {
            return CurrentDPD;
        }

        public String getClassification() {
            return Classification;
        }

        public String getCustId() {
            return CustId;
        }

        public String getLmd() {
            return Lmd;
        }

        public String getLmdBy() {
            return LmdBy;
        }

        public String getCrd() {
            return Crd;
        }

        public String getCrdBy() {
            return CrdBy;
        }

        public String getProduct_ID() {
            return Product_ID;
        }

        public String getID() {
            return ID;
        }

        public String getIsDisabled() {
            return IsDisabled;
        }

        public String getSourceSystem() {
            return SourceSystem;
        }

        public String getProductSource() {
            return ProductSource;
        }

        public String getAdvances() {
            return Advances;
        }

        public String getLoanAccountNo() {
            return LoanAccountNo;
        }

        public String getName() {
            return Name;
        }

        public String getUCICNo() {
            return UCICNo;
        }
    }
}
