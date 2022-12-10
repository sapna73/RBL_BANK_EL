package com.swadhaar.los.models;

import com.google.gson.annotations.Expose;

import java.util.Date;
import java.util.List;

public class CashCollectionSummaryDTO {

    @Expose
    private Date collectionDate;
    @Expose
    private int overAllTotalDueCollection;
    @Expose
    private int overAllTotalCashCollection;
    @Expose
    private int overAllTotalSavingsCollection;
    @Expose
    private int overAllTotalDigitalCollection;
    @Expose
    private int overAllTotalCollection;
    @Expose
    private int overAllTotalCollectionSyncFalse;
    @Expose
    private boolean isDenomination;
    @Expose
    private boolean isUpload;
    @Expose
    private List<IndividualCenterCollection> individualCenterCollectionList;

    public Date getCollectionDate() {
        return collectionDate;
    }

    public void setCollectionDate(Date collectionDate) {
        this.collectionDate = collectionDate;
    }

    public int getOverAllTotalDueCollection() {
        return overAllTotalDueCollection;
    }

    public void setOverAllTotalDueCollection(int overAllTotalDueCollection) {
        this.overAllTotalDueCollection = overAllTotalDueCollection;
    }

    public int getOverAllTotalCashCollection() {
        return overAllTotalCashCollection;
    }

    public void setOverAllTotalCashCollection(int overAllTotalCashCollection) {
        this.overAllTotalCashCollection = overAllTotalCashCollection;
    }

    public int getOverAllTotalSavingsCollection() {
        return overAllTotalSavingsCollection;
    }

    public void setOverAllTotalSavingsCollection(int overAllTotalSavingsCollection) {
        this.overAllTotalSavingsCollection = overAllTotalSavingsCollection;
    }

    public int getOverAllTotalDigitalCollection() {
        return overAllTotalDigitalCollection;
    }

    public void setOverAllTotalDigitalCollection(int overAllTotalDigitalCollection) {
        this.overAllTotalDigitalCollection = overAllTotalDigitalCollection;
    }

    public int getOverAllTotalCollection() {
        return overAllTotalCollection;
    }

    public void setOverAllTotalCollection(int overAllTotalCollection) {
        this.overAllTotalCollection = overAllTotalCollection;
    }

    public int getOverAllTotalCollectionSyncFalse() {
        return overAllTotalCollectionSyncFalse;
    }

    public void setOverAllTotalCollectionSyncFalse(int overAllTotalCollectionSyncFalse) {
        this.overAllTotalCollectionSyncFalse = overAllTotalCollectionSyncFalse;
    }

    public boolean isDenomination() {
        return isDenomination;
    }

    public void setDenomination(boolean denomination) {
        isDenomination = denomination;
    }

    public boolean isUpload() {
        return isUpload;
    }

    public void setUpload(boolean upload) {
        isUpload = upload;
    }

    public List<IndividualCenterCollection> getIndividualCenterCollectionList() {
        return individualCenterCollectionList;
    }

    public void setIndividualCenterCollectionList(List<IndividualCenterCollection> individualCenterCollectionList) {
        this.individualCenterCollectionList = individualCenterCollectionList;
    }

    public static class IndividualCenterCollection{
        @Expose
        private Date collectionDate;
        @Expose
        private String centerName;
        @Expose
        private int centerDue;
        @Expose
        private int centerCashCollection;
        @Expose
        private int centerSavingsCollection;
        @Expose
        private int centerDigitalCollection;
        @Expose
        private int centerTotalCollection;
        @Expose
        private boolean centerConfirm;
        @Expose
        private boolean centerSync;


        public Date getCollectionDate() {
            return collectionDate;
        }

        public void setCollectionDate(Date collectionDate) {
            this.collectionDate = collectionDate;
        }

        public String getCenterName() {
            return centerName;
        }

        public void setCenterName(String centerName) {
            this.centerName = centerName;
        }

        public int getCenterDue() {
            return centerDue;
        }

        public void setCenterDue(int centerDue) {
            this.centerDue = centerDue;
        }

        public int getCenterCashCollection() {
            return centerCashCollection;
        }

        public void setCenterCashCollection(int centerCashCollection) {
            this.centerCashCollection = centerCashCollection;
        }

        public int getCenterSavingsCollection() {
            return centerSavingsCollection;
        }

        public void setCenterSavingsCollection(int centerSavingsCollection) {
            this.centerSavingsCollection = centerSavingsCollection;
        }

        public int getCenterDigitalCollection() {
            return centerDigitalCollection;
        }

        public void setCenterDigitalCollection(int centerDigitalCollection) {
            this.centerDigitalCollection = centerDigitalCollection;
        }

        public int getCenterTotalCollection() {
            return centerTotalCollection;
        }

        public void setCenterTotalCollection(int centerTotalCollection) {
            this.centerTotalCollection = centerTotalCollection;
        }

        public boolean isCenterConfirm() {
            return centerConfirm;
        }

        public void setCenterConfirm(boolean centerConfirm) {
            this.centerConfirm = centerConfirm;
        }

        public boolean isCenterSync() {
            return centerSync;
        }

        public void setCenterSync(boolean centerSync) {
            this.centerSync = centerSync;
        }
    }

}
