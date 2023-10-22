package com.saartak.el.models;

import com.google.gson.annotations.Expose;

public class BalancesheetDTO {
    @Expose
    private String buttonName;
    @Expose
    private int buttonIcon;
    @Expose
    private int colorLight;
    @Expose
    private int colorDark;

    public int getColorLight() {
        return colorLight;
    }

    public void setColorLight(int colorLight) {
        this.colorLight = colorLight;
    }

    public int getColorDark() {
        return colorDark;
    }

    public void setColorDark(int colorDark) {
        this.colorDark = colorDark;
    }

    public String getButtonName() {
        return buttonName;
    }

    public void setButtonName(String buttonName) {
        this.buttonName = buttonName;
    }

    public int getButtonIcon() {
        return buttonIcon;
    }

    public void setButtonIcon(int buttonIcon) {
        this.buttonIcon = buttonIcon;
    }

    public BalancesheetDTO(String buttonName, int buttonIcon,int colorLight,int colorDark){
        this.buttonName = buttonName;
        this.buttonIcon = buttonIcon;
        this.colorLight= colorLight;
        this.colorDark= colorDark;
    }
}
