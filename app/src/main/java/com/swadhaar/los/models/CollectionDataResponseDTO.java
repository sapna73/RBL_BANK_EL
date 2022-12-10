package com.swadhaar.los.models;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;

public class CollectionDataResponseDTO {
    @Expose
    ArrayList<Table> Table;

    public ArrayList<CollectionDataResponseDTO.Table> getTable() {
        return Table;
    }

    public void setTable(ArrayList<CollectionDataResponseDTO.Table> table) {
        Table = table;
    }

    public static class Table {

        @Expose
        private String CustomerId;
        @Expose
        private String Customer_Name;
        @Expose
        private String BusinessAddress;
        @Expose
        private String CommunicationAddress;
        @Expose
        private String MobileNumbaer;
        @Expose
        private String Co_ApplicantName;
        @Expose
        private String Co_ApplicantMobileNumber;
        @Expose
        private String POS;
        @Expose
        private String EMI_AMT;

        public String getCustomerId() {
            return CustomerId;
        }

        public String getCustomer_Name() {
            return Customer_Name;
        }

        public String getBusinessAddress() {
            return BusinessAddress;
        }

        public String getCommunicationAddress() {
            return CommunicationAddress;
        }

        public String getMobileNumber() {
            return MobileNumbaer;
        }

        public String getCo_ApplicantName() {
            return Co_ApplicantName;
        }

        public String getCo_ApplicantMobileNumber() {
            return Co_ApplicantMobileNumber;
        }

        public String getPOS() {
            return POS;
        }

        public String getEMI_AMT() {
            return EMI_AMT;
        }

        public void setCustomerId(String customerId) {
            CustomerId = customerId;
        }

        public void setCustomer_Name(String customer_Name) {
            Customer_Name = customer_Name;
        }

        public void setBusinessAddress(String businessAddress) {
            BusinessAddress = businessAddress;
        }

        public void setCommunicationAddress(String communicationAddress) {
            CommunicationAddress = communicationAddress;
        }

        public void setMobileNumber(String mobileNumbaer) {
            MobileNumbaer = mobileNumbaer;
        }

        public void setCo_ApplicantName(String co_ApplicantName) {
            Co_ApplicantName = co_ApplicantName;
        }

        public void setCo_ApplicantMobileNumber(String co_ApplicantMobileNumber) {
            Co_ApplicantMobileNumber = co_ApplicantMobileNumber;
        }

        public void setPOS(String POS) {
            this.POS = POS;
        }

        public void setEMI_AMT(String EMI_AMT) {
            this.EMI_AMT = EMI_AMT;
        }
    }
}
