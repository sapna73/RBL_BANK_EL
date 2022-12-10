package com.swadhaar.los.models;

import com.google.gson.annotations.Expose;

public class ConnectionModel {

    @Expose
    private int type;
    @Expose
    private boolean isConnected;

    public ConnectionModel(int type, boolean isConnected) {
        this.type = type;
        this.isConnected = isConnected;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public boolean isConnected() {
        return isConnected;
    }

    public void setConnected(boolean connected) {
        isConnected = connected;
    }
}
