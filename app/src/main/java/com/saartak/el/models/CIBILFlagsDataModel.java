package com.saartak.el.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CIBILFlagsDataModel {

    @SerializedName("Deliquency")
    @Expose
    private String deliquency;

    @SerializedName("Hunter")
    @Expose
    private String hunter;

    @SerializedName("Ramp")
    @Expose
    private String ramp;

    @SerializedName("Dedup")
    @Expose
    private String dedup;

    @SerializedName("CPVApplicant")
    @Expose
    private String cpvApplicant;

    @SerializedName("CPVBusinessOrSalary")
    @Expose
    private String cPVBusinessOrSalary;

    @SerializedName("LaonTenure")
    @Expose
    private String laonTenure;

    @SerializedName("LoanAmount")
    @Expose
    private String loanAmount;

    @SerializedName("ROI")
    @Expose
    private String roI;

    public String getDeliquency() {
        return deliquency;
    }

    public void setDeliquency(String deliquency) {
        this.deliquency = deliquency;
    }

    public String getHunter() {
        return hunter;
    }

    public void setHunter(String hunter) {
        this.hunter = hunter;
    }

    public String getRamp() {
        return ramp;
    }

    public void setRamp(String ramp) {
        this.ramp = ramp;
    }

    public String getDedup() {
        return dedup;
    }

    public void setDedup(String dedup) {
        this.dedup = dedup;
    }

    public String getCpvApplicant() {
        return cpvApplicant;
    }

    public void setCpvApplicant(String cpvApplicant) {
        this.cpvApplicant = cpvApplicant;
    }

    public String getcPVBusinessOrSalary() {
        return cPVBusinessOrSalary;
    }

    public void setcPVBusinessOrSalary(String cPVBusinessOrSalary) {
        this.cPVBusinessOrSalary = cPVBusinessOrSalary;
    }

    public String getLaonTenure() {
        return laonTenure;
    }

    public void setLaonTenure(String laonTenure) {
        this.laonTenure = laonTenure;
    }

    public String getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(String loanAmount) {
        this.loanAmount = loanAmount;
    }

    public String getRoI() {
        return roI;
    }

    public void setRoI(String roI) {
        this.roI = roI;
    }
}
