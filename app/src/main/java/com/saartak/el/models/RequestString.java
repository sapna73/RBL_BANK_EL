package com.saartak.el.models;

import com.google.gson.annotations.Expose;

public class RequestString {
	@Expose
    private EkycRequest ekycRequest;

    public void setEkycRequest(EkycRequest ekycRequest) {
        this.ekycRequest = ekycRequest;
    }

    public EkycRequest getEkycRequest() {
        return ekycRequest;
    }

    @Override
    public String toString() {
        return
                "RequestString{" +
                        "ekycRequest = '" + ekycRequest + '\'' +
                        "}";
    }
}
