package com.saartak.el.models;

import com.google.gson.annotations.Expose;

public class LogRequestDTO {

    @Expose
    private String UniqueId;
    @Expose
    private String ProjectName;
    @Expose
    private String FlagUatorProd;
    @Expose
    private String RequestFrom;
    @Expose
    private String ExternalCustId;
    @Expose
    private String KYCId;
    @Expose
    private String TrackLocation;
    @Expose
    private String TrackMessage;
    @Expose
    private String RequestJson;
    @Expose
    private String ResponseJson;
    @Expose
    private String RequestXML;
    @Expose
    private String ResponseXML;
    @Expose
    private String ErrorMessage;
    @Expose
    private String ErrorCode;
    @Expose
    private int Status;
    @Expose
    private String Token;
    @Expose
    private String Parameter1;
    @Expose
    private String Parameter2;
    @Expose
    private String CreatedBy;
    @Expose
    private String ClientId;
    @Expose
    private String Parameter5;
    @Expose
    private String Parameter6;
    @Expose
    private String Parameter7;

    public String getUniqueId() {
        return UniqueId;
    }

    public void setUniqueId(String uniqueId) {
        UniqueId = uniqueId;
    }

    public String getProjectName() {
        return ProjectName;
    }

    public void setProjectName(String projectName) {
        ProjectName = projectName;
    }

    public String getFlagUatorProd() {
        return FlagUatorProd;
    }

    public void setFlagUatorProd(String flagUatorProd) {
        FlagUatorProd = flagUatorProd;
    }

    public String getRequestFrom() {
        return RequestFrom;
    }

    public void setRequestFrom(String requestFrom) {
        RequestFrom = requestFrom;
    }

    public String getExternalCustId() {
        return ExternalCustId;
    }

    public void setExternalCustId(String externalCustId) {
        ExternalCustId = externalCustId;
    }

    public String getKYCId() {
        return KYCId;
    }

    public void setKYCId(String KYCId) {
        this.KYCId = KYCId;
    }

    public String getTrackLocation() {
        return TrackLocation;
    }

    public void setTrackLocation(String trackLocation) {
        TrackLocation = trackLocation;
    }

    public String getTrackMessage() {
        return TrackMessage;
    }

    public void setTrackMessage(String trackMessage) {
        TrackMessage = trackMessage;
    }

    public String getRequestJson() {
        return RequestJson;
    }

    public void setRequestJson(String requestJson) {
        RequestJson = requestJson;
    }

    public String getResponseJson() {
        return ResponseJson;
    }

    public void setResponseJson(String responseJson) {
        ResponseJson = responseJson;
    }

    public String getRequestXML() {
        return RequestXML;
    }

    public void setRequestXML(String requestXML) {
        RequestXML = requestXML;
    }

    public String getResponseXML() {
        return ResponseXML;
    }

    public void setResponseXML(String responseXML) {
        ResponseXML = responseXML;
    }

    public String getErrorMessage() {
        return ErrorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        ErrorMessage = errorMessage;
    }

    public String getErrorCode() {
        return ErrorCode;
    }

    public void setErrorCode(String errorCode) {
        ErrorCode = errorCode;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }

    public String getToken() {
        return Token;
    }

    public void setToken(String token) {
        Token = token;
    }

    public String getParameter1() {
        return Parameter1;
    }

    public void setParameter1(String parameter1) {
        Parameter1 = parameter1;
    }

    public String getParameter2() {
        return Parameter2;
    }

    public void setParameter2(String parameter2) {
        Parameter2 = parameter2;
    }

    public String getCreatedBy() {
        return CreatedBy;
    }

    public void setCreatedBy(String createdBy) {
        CreatedBy = createdBy;
    }

    public String getClientId() {
        return ClientId;
    }

    public void setClientId(String clientId) {
        ClientId = clientId;
    }

    public String getParameter5() {
        return Parameter5;
    }

    public void setParameter5(String parameter5) {
        Parameter5 = parameter5;
    }

    public String getParameter6() {
        return Parameter6;
    }

    public void setParameter6(String parameter6) {
        Parameter6 = parameter6;
    }

    public String getParameter7() {
        return Parameter7;
    }

    public void setParameter7(String parameter7) {
        Parameter7 = parameter7;
    }
}
