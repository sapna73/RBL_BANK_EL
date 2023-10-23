package com.saartak.el.models;

import com.google.gson.annotations.Expose;

public class EkycRequest {
	@Expose
    private String authVersionToUse;
	@Expose
    private String freshnessFactor;
	@Expose
    private String channel;
	@Expose
    private String terminalId;
	@Expose
    private String deviceId;
	@Expose
    private String encryptedHmac;
	@Expose
    private String registeredDevicePublicKey;
	@Expose
    private String csrId;
	@Expose
    private String requestId;
	@Expose
    private String ekycPurposeStr;
	@Expose
    private String registeredDeviceProviderId;
	@Expose
    private String certificateIdentifier;
	@Expose
    private String aadhaarNo;
	@Expose
    private String registeredDeviceServiceId;
	@Expose
    private String registeredDeviceModelId;
	@Expose
    private String registeredDeviceServiceVersion;
	@Expose
    private String ekycType;
	@Expose
    private int resentCount;
	@Expose
    private String version;
	@Expose
    private String userConcent;
	@Expose
    private Object csrPassword;
	@Expose
    private String sessionKeyValue;
	@Expose
    private String transType;
	@Expose
    private String encryptedPid;
	@Expose
    private String onlyEkycConfirmationRequired;
	@Expose
    private String registeredDeviceCode;

    public void setAuthVersionToUse(String authVersionToUse) {
        this.authVersionToUse = authVersionToUse;
    }

    public String getAuthVersionToUse() {
        return authVersionToUse;
    }

    public void setFreshnessFactor(String freshnessFactor) {
        this.freshnessFactor = freshnessFactor;
    }

    public String getFreshnessFactor() {
        return freshnessFactor;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getChannel() {
        return channel;
    }

    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }

    public String getTerminalId() {
        return terminalId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setEncryptedHmac(String encryptedHmac) {
        this.encryptedHmac = encryptedHmac;
    }

    public String getEncryptedHmac() {
        return encryptedHmac;
    }

    public void setRegisteredDevicePublicKey(String registeredDevicePublicKey) {
        this.registeredDevicePublicKey = registeredDevicePublicKey;
    }

    public String getRegisteredDevicePublicKey() {
        return registeredDevicePublicKey;
    }

    public void setCsrId(String csrId) {
        this.csrId = csrId;
    }

    public String getCsrId() {
        return csrId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setEkycPurposeStr(String ekycPurposeStr) {
        this.ekycPurposeStr = ekycPurposeStr;
    }

    public String getEkycPurposeStr() {
        return ekycPurposeStr;
    }

    public void setRegisteredDeviceProviderId(String registeredDeviceProviderId) {
        this.registeredDeviceProviderId = registeredDeviceProviderId;
    }

    public String getRegisteredDeviceProviderId() {
        return registeredDeviceProviderId;
    }

    public void setCertificateIdentifier(String certificateIdentifier) {
        this.certificateIdentifier = certificateIdentifier;
    }

    public String getCertificateIdentifier() {
        return certificateIdentifier;
    }

    public void setAadhaarNo(String aadhaarNo) {
        this.aadhaarNo = aadhaarNo;
    }

    public String getAadhaarNo() {
        return aadhaarNo;
    }

    public void setRegisteredDeviceServiceId(String registeredDeviceServiceId) {
        this.registeredDeviceServiceId = registeredDeviceServiceId;
    }

    public String getRegisteredDeviceServiceId() {
        return registeredDeviceServiceId;
    }

    public void setRegisteredDeviceModelId(String registeredDeviceModelId) {
        this.registeredDeviceModelId = registeredDeviceModelId;
    }

    public String getRegisteredDeviceModelId() {
        return registeredDeviceModelId;
    }

    public void setRegisteredDeviceServiceVersion(String registeredDeviceServiceVersion) {
        this.registeredDeviceServiceVersion = registeredDeviceServiceVersion;
    }

    public String getRegisteredDeviceServiceVersion() {
        return registeredDeviceServiceVersion;
    }

    public void setEkycType(String ekycType) {
        this.ekycType = ekycType;
    }

    public String getEkycType() {
        return ekycType;
    }

    public void setResentCount(int resentCount) {
        this.resentCount = resentCount;
    }

    public int getResentCount() {
        return resentCount;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getVersion() {
        return version;
    }

    public void setUserConcent(String userConcent) {
        this.userConcent = userConcent;
    }

    public String getUserConcent() {
        return userConcent;
    }

    public void setCsrPassword(Object csrPassword) {
        this.csrPassword = csrPassword;
    }

    public Object getCsrPassword() {
        return csrPassword;
    }

    public void setSessionKeyValue(String sessionKeyValue) {
        this.sessionKeyValue = sessionKeyValue;
    }

    public String getSessionKeyValue() {
        return sessionKeyValue;
    }

    public void setTransType(String transType) {
        this.transType = transType;
    }

    public String getTransType() {
        return transType;
    }

    public void setEncryptedPid(String encryptedPid) {
        this.encryptedPid = encryptedPid;
    }

    public String getEncryptedPid() {
        return encryptedPid;
    }

    public void setOnlyEkycConfirmationRequired(String onlyEkycConfirmationRequired) {
        this.onlyEkycConfirmationRequired = onlyEkycConfirmationRequired;
    }

    public String getOnlyEkycConfirmationRequired() {
        return onlyEkycConfirmationRequired;
    }

    public void setRegisteredDeviceCode(String registeredDeviceCode) {
        this.registeredDeviceCode = registeredDeviceCode;
    }

    public String getRegisteredDeviceCode() {
        return registeredDeviceCode;
    }

    @Override
    public String toString() {
        return
                "EkycRequest{" +
                        "authVersionToUse = '" + authVersionToUse + '\'' +
                        ",freshnessFactor = '" + freshnessFactor + '\'' +
                        ",channel = '" + channel + '\'' +
                        ",terminalId = '" + terminalId + '\'' +
                        ",deviceId = '" + deviceId + '\'' +
                        ",encryptedHmac = '" + encryptedHmac + '\'' +
                        ",registeredDevicePublicKey = '" + registeredDevicePublicKey + '\'' +
                        ",csrId = '" + csrId + '\'' +
                        ",requestId = '" + requestId + '\'' +
                        ",ekycPurposeStr = '" + ekycPurposeStr + '\'' +
                        ",registeredDeviceProviderId = '" + registeredDeviceProviderId + '\'' +
                        ",certificateIdentifier = '" + certificateIdentifier + '\'' +
                        ",aadhaarNo = '" + aadhaarNo + '\'' +
                        ",registeredDeviceServiceId = '" + registeredDeviceServiceId + '\'' +
                        ",registeredDeviceModelId = '" + registeredDeviceModelId + '\'' +
                        ",registeredDeviceServiceVersion = '" + registeredDeviceServiceVersion + '\'' +
                        ",ekycType = '" + ekycType + '\'' +
                        ",resentCount = '" + resentCount + '\'' +
                        ",version = '" + version + '\'' +
                        ",userConcent = '" + userConcent + '\'' +
                        ",csrPassword = '" + csrPassword + '\'' +
                        ",sessionKeyValue = '" + sessionKeyValue + '\'' +
                        ",transType = '" + transType + '\'' +
                        ",encryptedPid = '" + encryptedPid + '\'' +
                        ",onlyEkycConfirmationRequired = '" + onlyEkycConfirmationRequired + '\'' +
                        ",registeredDeviceCode = '" + registeredDeviceCode + '\'' +
                        "}";
    }
}
