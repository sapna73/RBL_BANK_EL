package com.saartak.el.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ColdCallDataRequestDTO {

    @Expose
    private String ConnectionString = "audit";
    @Expose
    private String UserId = "";
    @Expose
    private String IMEINumber = "";
    @Expose
    private String ProjectName = "";
    @Expose
    ArrayList<SpNameWithParameterClass> SpNameWithParameter = new ArrayList<SpNameWithParameterClass>();

    public ArrayList<SpNameWithParameterClass> getSpNameWithParameter() {
        return SpNameWithParameter;
    }

    public void setSpNameWithParameter(ArrayList<SpNameWithParameterClass> spNameWithParameter) {
        SpNameWithParameter = spNameWithParameter;
    }

    // Getter Methods

    public String getConnectionString() {
        return ConnectionString;
    }

    public String getUserId() {
        return UserId;
    }

    public String getIMEINumber() {
        return IMEINumber;
    }

    public String getProjectName() {
        return ProjectName;
    }

    // Setter Methods

    public void setConnectionString(String ConnectionString) {
        this.ConnectionString = ConnectionString;
    }

    public void setUserId(String UserId) {
        this.UserId = UserId;
    }

    public void setIMEINumber(String IMEINumber) {
        this.IMEINumber = IMEINumber;
    }

    public void setProjectName(String ProjectName) {
        this.ProjectName = ProjectName;
    }

    public static class SpNameWithParameterClass {
        @Expose
        private String SpName = "";
        @Expose
        private SpParametersClass SpParameters;

        public String getSpName() {
            return SpName;
        }

        public void setSpName(String spName) {
            SpName = spName;
        }

        public SpParametersClass getSpParameters() {
            return SpParameters;
        }

        public void setSpParameters(SpParametersClass spParameters) {
            SpParameters = spParameters;
        }
    }

    public static class SpParametersClass {

        @SerializedName("CCTokenId")
        @Expose
        private String clientId= "";

        @SerializedName("TargetName")
        @Expose
        private String clientName= "";

        @SerializedName("MarketName")
        @Expose
        private String MarketName= "";

        @SerializedName("BusinessName")
        @Expose
        private String BusinessName= "";

        @SerializedName("MobileNo")
        @Expose
        private String MobileNo= "";

        @SerializedName("Lat")
        @Expose
        private String Lat = "";

        @SerializedName("Long")
        @Expose
        private String Long = "";

        @SerializedName("InterestedInLoan")
        @Expose
        private int InterestedInLoan;

        @SerializedName("CreatedBy")
        @Expose
        private String createdBy= "";

        @SerializedName("Pincode")
        @Expose
        private String Pincode= "";

        @SerializedName("CallTimeStamp")
        @Expose
        private String CallTimeStamp= "";

        @SerializedName("Comments")
        @Expose
        private String Comments= "";

        @SerializedName("ProductId")
        @Expose
        private String ProductId= "";


        public String getClientId() {
            return clientId;
        }

        public void setClientId(String clientId) {
            this.clientId = clientId;
        }

        public String getClientName() {
            return clientName;
        }

        public void setClientName(String clientName) {
            this.clientName = clientName;
        }

        public String getMarketName() {
            return MarketName;
        }

        public void setMarketName(String marketName) {
            MarketName = marketName;
        }

        public String getBusinessName() {
            return BusinessName;
        }

        public void setBusinessName(String businessName) {
            BusinessName = businessName;
        }

        public String getMobileNo() {
            return MobileNo;
        }

        public void setMobileNo(String mobileNo) {
            MobileNo = mobileNo;
        }

        public String getLat() {
            return Lat;
        }

        public void setLat(String lat) {
            Lat = lat;
        }

        public String getLong() {
            return Long;
        }

        public void setLong(String aLong) {
            Long = aLong;
        }

        public int getInterestedInLoan() {
            return InterestedInLoan;
        }


        public void setInterestedInLoan(int interestedInLoan) {
            InterestedInLoan = interestedInLoan;
        }

        public String getCreatedBy() {
            return createdBy;
        }

        public void setCreatedBy(String createdBy) {
            this.createdBy = createdBy;
        }

        public String getPincode() {
            return Pincode;
        }

        public void setPincode(String pincode) {
            Pincode = pincode;
        }

        public String getCallTimeStamp() {
            return CallTimeStamp;
        }

        public void setCallTimeStamp(String callTimeStamp) {
            CallTimeStamp = callTimeStamp;
        }

        public String getComments() {
            return Comments;
        }

        public void setComments(String comments) {
            Comments = comments;
        }

        public String getProductId() {
            return ProductId;
        }

        public void setProductId(String productId) {
            ProductId = productId;
        }
    }
}
