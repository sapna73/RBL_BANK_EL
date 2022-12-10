package com.swadhaar.los.models;


import com.google.gson.annotations.Expose;

public class ScreenDetailsDTO {

    @Expose
    private String screenId;
    @Expose
    private String screenName;
    @Expose
    private String moduleType;
    @Expose
    private String productId;

    public String getScreenId() {
        return screenId;
    }

    public void setScreenId(String screenId) {
        this.screenId = screenId;
    }

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    public String getModuleType() {
        return moduleType;
    }

    public void setModuleType(String moduleType) {
        this.moduleType = moduleType;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }
}


