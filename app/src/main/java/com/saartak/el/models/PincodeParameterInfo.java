package com.saartak.el.models;

import com.google.gson.annotations.Expose;

public class PincodeParameterInfo {
    @Expose
    private String fieldName;
    @Expose
    private String screenName;
    @Expose
    private String value;
    @Expose
    private boolean visibilty;
    @Expose
    private boolean enabled;
    @Expose
    private String[] paramvalues;
    @Expose
    private String FieldType;


    public PincodeParameterInfo(String fieldName, String screenName, String value, boolean visibilty,
                                boolean enabled, String fieldType, String[] paramValues) {
        this.fieldName = fieldName;
        this.screenName = screenName;
        this.value = value;
        this.visibilty = visibilty;
        this.enabled = enabled;
        this.FieldType = fieldType;
        this.paramvalues = paramValues;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getscreenName() {
        return screenName;
    }

    public void setscreenName(String screenName) {
        this.screenName = screenName;
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

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String[] getParamvalues() {
        return paramvalues;
    }

    public void setParamvalues(String[] paramvalues) {
        this.paramvalues = paramvalues;
    }

    public String getFieldType() {
        return FieldType;
    }

    public void setFieldType(String fieldType) {
        FieldType = fieldType;
    }
}
