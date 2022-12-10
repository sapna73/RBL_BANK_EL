package com.swadhaar.los.models;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;

public class IFSCResponseDTO {
    @Expose
    ArrayList<Table> Table;

    public ArrayList<IFSCResponseDTO.Table> getTable() {
        return Table;
    }

    public void setTable(ArrayList<IFSCResponseDTO.Table> table) {
        Table = table;
    }
    /*{
        "_Data41196": "{\"Table\":[{\"BANK\":\"STATE BANK OF INDIA\",\"IFSC\":\"SBIN0021807\",\"BRANCH\":\"RAJANAGARAM\",\"CENTRE\":\"RAJANAGARAM\",\"DISTRICT\":\"EAST GODAVARI\",\"STATE\":\"ANDHRA PRADESH\",
        \"ADDRESS\":\"N9MAINRAGANHIBMMACENTER\",\"CONTACT\":\"8.83248e+009\",\"IMPS\":true,\"RTGS\":true,\"CITY\":\"RAJANAGARAM\",\"NEFT\":true,\"MICR\":\"5.05003e+008\",\"UPI\":true}]}"
    }*/
    public static class Table {
        @Expose
        private String BANK;
        @Expose
        private String IFSC;
        @Expose
        private String BRANCH;
        @Expose
        private String CENTRE;
        @Expose
        private String DISTRICT;
        @Expose
        private String STATE;
        @Expose
        private String ADDRESS;
        @Expose
        private String CONTACT;
        @Expose
        private String IMPS;
        @Expose
        private String CITY;
        @Expose
        private String NEFT;
        @Expose
        private String MICR;
        @Expose
        private String UPI;

        public String getBANK() {
            return BANK;
        }

        public void setBANK(String BANK) {
            this.BANK = BANK;
        }

        public String getIFSC() {
            return IFSC;
        }

        public void setIFSC(String IFSC) {
            this.IFSC = IFSC;
        }

        public String getBRANCH() {
            return BRANCH;
        }

        public void setBRANCH(String BRANCH) {
            this.BRANCH = BRANCH;
        }

        public String getCENTRE() {
            return CENTRE;
        }

        public void setCENTRE(String CENTRE) {
            this.CENTRE = CENTRE;
        }

        public String getDISTRICT() {
            return DISTRICT;
        }

        public void setDISTRICT(String DISTRICT) {
            this.DISTRICT = DISTRICT;
        }

        public String getSTATE() {
            return STATE;
        }

        public void setSTATE(String STATE) {
            this.STATE = STATE;
        }

        public String getADDRESS() {
            return ADDRESS;
        }

        public void setADDRESS(String ADDRESS) {
            this.ADDRESS = ADDRESS;
        }

        public String getCONTACT() {
            return CONTACT;
        }

        public void setCONTACT(String CONTACT) {
            this.CONTACT = CONTACT;
        }

        public String getIMPS() {
            return IMPS;
        }

        public void setIMPS(String IMPS) {
            this.IMPS = IMPS;
        }

        public String getCITY() {
            return CITY;
        }

        public void setCITY(String CITY) {
            this.CITY = CITY;
        }

        public String getNEFT() {
            return NEFT;
        }

        public void setNEFT(String NEFT) {
            this.NEFT = NEFT;
        }

        public String getMICR() {
            return MICR;
        }

        public void setMICR(String MICR) {
            this.MICR = MICR;
        }

        public String getUPI() {
            return UPI;
        }

        public void setUPI(String UPI) {
            this.UPI = UPI;
        }
    }

}
