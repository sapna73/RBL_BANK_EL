package com.saartak.el.models;

import com.google.gson.annotations.Expose;

public class LeadDetailsDTO {
    @Expose
    private String lead_Name;
    @Expose
    private String lead_ID;
    @Expose
    private String lead_Status;

    public LeadDetailsDTO(String lead_Name, String lead_ID, String lead_Status) {
        this.lead_Name = lead_Name;
        this.lead_ID = lead_ID;
        this.lead_Status = lead_Status;
    }

    public String getLead_Name() {
        return lead_Name;
    }

    public void setLead_Name(String lead_Name) {
        this.lead_Name = lead_Name;
    }

    public String getLead_ID() {
        return lead_ID;
    }

    public void setLead_ID(String lead_ID) {
        this.lead_ID = lead_ID;
    }

    public String getLead_Status() {
        return lead_Status;
    }

    public void setLead_Status(String lead_Status) {
        this.lead_Status = lead_Status;
    }
}
