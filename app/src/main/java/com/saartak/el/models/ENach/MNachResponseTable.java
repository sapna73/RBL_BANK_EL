package com.saartak.el.models.ENach;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public abstract class MNachResponseTable {


    @Expose
    @SerializedName("TokenId")
    private String TokenId;

    public String getTokenId() {
        return TokenId;
    }
}
