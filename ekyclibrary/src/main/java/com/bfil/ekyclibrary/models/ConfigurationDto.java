package com.bfil.ekyclibrary.models;

import java.io.Serializable;

/**
 * Created by Ram Kumar Bomma on 20-03-2018.
 */

public class ConfigurationDto implements Serializable {

    private String aquirerID;
    private String env;
    private String envType;
    private String URL;

    public String getAquirerID() {
        return aquirerID;
    }

    public void setAquirerID(String aquirerID) {
        this.aquirerID = aquirerID;
    }

    public String getEnv() {
        return env;
    }

    public void setEnv(String env) {
        this.env = env;
    }

    public String getEnvType() {
        return envType;
    }

    public void setEnvType(String envType) {
        this.envType = envType;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }
}
