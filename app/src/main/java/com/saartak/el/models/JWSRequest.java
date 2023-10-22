package com.saartak.el.models;

import com.google.gson.annotations.SerializedName;

public class JWSRequest {
    @SerializedName("signedAttestation")
    private String signedAttestation;

    public void setSignedAttestation(String signedAttestation) {

        this.signedAttestation = signedAttestation;
    }
}
