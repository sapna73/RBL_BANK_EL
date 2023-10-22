package com.saartak.el.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class JWS {
    @SerializedName("nonce")
    private String nonce;

    @SerializedName("timestampMs")
    private long timestampMs;

    @SerializedName("apkPackageName")
    private String apkPackageName;

    @SerializedName("apkDigestSha256")
    private String apkDigestSha256;

    @SerializedName("ctsProfileMatch")
    private boolean ctsProfileMatch;

    @SerializedName("extension")
    private String extension;

    @SerializedName("apkCertificateDigestSha256")
    private List<String> apkCertificateDigestSha256;

    @SerializedName("basicIntegrity")
    private boolean basicIntegrity;

    public String getNonce() {

        return nonce;
    }

    public long getTimestampMs() {

        return timestampMs;
    }

    public String getApkPackageName() {

        return apkPackageName;
    }

    public String getApkDigestSha256() {

        return apkDigestSha256;
    }

    public boolean isCtsProfileMatch() {

        return ctsProfileMatch;
    }

    public String getExtension() {

        return extension;
    }

    public List<String> getApkCertificateDigestSha256() {

        return apkCertificateDigestSha256;
    }

    public boolean isBasicIntegrity() {

        return basicIntegrity;
    }
}
