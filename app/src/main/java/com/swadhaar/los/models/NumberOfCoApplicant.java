package com.swadhaar.los.models;

import com.google.gson.annotations.Expose;

public class NumberOfCoApplicant {
    @Expose
    int remainingCoApplicant;
    @Expose
    int nextCoApplicant;

    public int getRemainingCoApplicant() {
        return remainingCoApplicant;
    }

    public void setRemainingCoApplicant(int remainingCoApplicant) {
        this.remainingCoApplicant = remainingCoApplicant;
    }

    public int getNextCoApplicant() {
        return nextCoApplicant;
    }

    public void setNextCoApplicant(int nextCoApplicant) {
        this.nextCoApplicant = nextCoApplicant;
    }
}
