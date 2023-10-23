package com.saartak.el.models;

import com.google.gson.annotations.Expose;

public class CMCollectionLocalResponseDTO {
    @Expose
    private boolean isValid;
    @Expose
    private String response;
    @Expose
    private int totalNormalCollection;
    @Expose
    private int totalSavingCollection;
    @Expose
    private int overallTotalCollection;

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public int getTotalNormalCollection() {
        return totalNormalCollection;
    }

    public void setTotalNormalCollection(int totalNormalCollection) {
        this.totalNormalCollection = totalNormalCollection;
    }

    public int getTotalSavingCollection() {
        return totalSavingCollection;
    }

    public void setTotalSavingCollection(int totalSavingCollection) {
        this.totalSavingCollection = totalSavingCollection;
    }

    public int getOverallTotalCollection() {
        return overallTotalCollection;
    }

    public void setOverallTotalCollection(int overallTotalCollection) {
        this.overallTotalCollection = overallTotalCollection;
    }
}
