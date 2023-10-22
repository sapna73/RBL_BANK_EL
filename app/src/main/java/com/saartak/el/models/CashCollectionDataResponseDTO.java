package com.saartak.el.models;

import com.google.gson.annotations.Expose;

public class CashCollectionDataResponseDTO {

    @Expose
    private String UniqueId;
    @Expose
    private String Status;
    @Expose
    private String Result;
    @Expose
    private String ErrorMessage;
    @Expose
    private String P1;
    @Expose
    private String P2;
    @Expose
    private String P3;

    public String getUniqueId() {
        return UniqueId;
    }

    public void setUniqueId(String uniqueId) {
        UniqueId = uniqueId;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getResult() {
        return Result;
    }

    public void setResult(String result) {
        Result = result;
    }

    public String getErrorMessage() {
        return ErrorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        ErrorMessage = errorMessage;
    }

    public String getP1() {
        return P1;
    }

    public void setP1(String p1) {
        P1 = p1;
    }

    public String getP2() {
        return P2;
    }

    public void setP2(String p2) {
        P2 = p2;
    }

    public String getP3() {
        return P3;
    }

    public void setP3(String p3) {
        P3 = p3;
    }
}
