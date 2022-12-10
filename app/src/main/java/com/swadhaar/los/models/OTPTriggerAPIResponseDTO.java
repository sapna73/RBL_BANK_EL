package com.swadhaar.los.models;

import com.google.gson.annotations.Expose;

public class OTPTriggerAPIResponseDTO {
    @Expose
    private String UniqueId;
    @Expose
    private String Status;
    @Expose
    private String RefferenceId;
    @Expose
    private String ResponseMessage;

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

    public String getRefferenceId() {
        return RefferenceId;
    }

    public void setRefferenceId(String refferenceId) {
        RefferenceId = refferenceId;
    }

    public String getResponseMessage() {
        return ResponseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        ResponseMessage = responseMessage;
    }

}
