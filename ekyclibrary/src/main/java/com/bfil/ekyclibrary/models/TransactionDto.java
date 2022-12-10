package com.bfil.ekyclibrary.models;

import java.io.Serializable;


public class TransactionDto implements Serializable {

    private String aadharNo;
    private String amt;
    private String userName;
    private String mobileNo;
    private String language;
    private String merchantId;
    private String transactionType;
    private String imeiNumber;
    private String BankShortName = "indusInd";
    private String guardianName;
    private String merchantAccNo;
    private String screeNo = "0";
    private String deviceId;
    private String clientId;
    private String merchantCategory;
    private Enum authTransType;
    private boolean isAutoFill;

    public String getAadharNo() {
        return aadharNo;
    }

    public void setAadharNo(String aadharNo) {
        this.aadharNo = aadharNo;
    }

    public String getAmt() {
        return amt;
    }

    public void setAmt(String amt) {
        this.amt = amt;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getImeiNumber() {
        return imeiNumber;
    }

    public void setImeiNumber(String imeiNumber) {
        this.imeiNumber = imeiNumber;
    }

    public String getBankShortName() {
        return BankShortName;
    }

    public void setBankShortName(String bankShortName) {
        BankShortName = bankShortName;
    }

    public String getGuardianName() {
        return guardianName;
    }

    public void setGuardianName(String guardianName) {
        this.guardianName = guardianName;
    }

    public String getMerchantAccNo() {
        return merchantAccNo;
    }

    public void setMerchantAccNo(String merchantAccNo) {
        this.merchantAccNo = merchantAccNo;
    }

    public String getScreeNo() {
        return screeNo;
    }

    public void setScreeNo(String screeNo) {
        this.screeNo = screeNo;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getMerchantCategory() {
        return merchantCategory;
    }

    public void setMerchantCategory(String merchantCategory) {
        this.merchantCategory = merchantCategory;
    }

    public Enum getAuthTransType() {
        return authTransType;
    }

    public void setAuthTransType(Enum authTransType) {
        this.authTransType = authTransType;
    }

    public boolean isAutoFill() {
        return isAutoFill;
    }

    public void setAutoFill(boolean autoFill) {
        isAutoFill = autoFill;
    }
}
