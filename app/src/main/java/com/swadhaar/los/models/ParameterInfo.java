package com.swadhaar.los.models;

import com.google.gson.annotations.Expose;

public class ParameterInfo {
    @Expose
    private String fieldTag;
    @Expose
    private String screenId;
    @Expose
    private String value;
    @Expose
    private boolean visibilty;
    @Expose
    private boolean enabled;

    public ParameterInfo(String fieldTag, String screenId, String value, boolean visibilty, boolean enabled) {
        this.fieldTag = fieldTag;
        this.screenId = screenId;
        this.value = value;
        this.visibilty = visibilty;
        this.enabled = enabled;
    }

    public String getfieldTag() {
        return fieldTag;
    }

    public void setfieldTag(String fieldTag) {
        this.fieldTag = fieldTag;
    }


    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isVisibilty() {
        return visibilty;
    }

    public void setVisibilty(boolean visibilty) {
        this.visibilty = visibilty;
    }

    public String getscreenId() {
        return screenId;
    }

    public void setscreenId(String screenId) {
        this.screenId = screenId;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
