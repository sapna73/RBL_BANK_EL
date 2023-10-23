package com.saartak.el.models;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;

public class ColdCallFromServerResponseDTO {


    @Expose
    ArrayList<Table> Table;

    public ArrayList<ColdCallFromServerResponseDTO.Table> getTable() {
        return Table;
    }

    public void setTable(ArrayList<ColdCallFromServerResponseDTO.Table> table) {
        Table = table;
    }

    public static class Table {
        @Expose
        private String CCTokenId;
        @Expose
        private String CreatedOn;
        @Expose
        private String CreatedBy;
        @Expose
        private String TargetName;
        @Expose
        private String MarketName;
        @Expose
        private String BusinessName;
        @Expose
        private String MobileNo;
        @Expose
        private int InterestedInLoan;
        @Expose
        private int IsPremium;
        @Expose
        private String Comments;
        @Expose
        private String Pincode;

        public String getCCTokenId() {
            return CCTokenId;
        }

        public void setCCTokenId(String CCTokenId) {
            this.CCTokenId = CCTokenId;
        }

        public String getCreatedOn() {
            return CreatedOn;
        }

        public void setCreatedOn(String createdOn) {
            CreatedOn = createdOn;
        }

        public String getCreatedBy() {
            return CreatedBy;
        }

        public void setCreatedBy(String createdBy) {
            CreatedBy = createdBy;
        }

        public String getTargetName() {
            return TargetName;
        }

        public void setTargetName(String targetName) {
            TargetName = targetName;
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

        public int getInterestedInLoan() {
            return InterestedInLoan;
        }

        public void setInterestedInLoan(int interestedInLoan) {
            InterestedInLoan = interestedInLoan;
        }

        public int getIsPremium() {
            return IsPremium;
        }

        public void setIsPremium(int isPremium) {
            IsPremium = isPremium;
        }

        public String getComments() {
            return Comments;
        }

        public void setComments(String comments) {
            Comments = comments;
        }

        public String getPincode() {
            return Pincode;
        }

        public void setPincode(String pincode) {
            Pincode = pincode;
        }
    }
}

