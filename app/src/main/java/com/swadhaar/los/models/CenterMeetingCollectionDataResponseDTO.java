package com.swadhaar.los.models;

import com.google.gson.annotations.Expose;

import org.json.JSONObject;

public class CenterMeetingCollectionDataResponseDTO {

    @Expose
    private JSONObject jsonObject;

    public JSONObject getJsonObject() {
        return jsonObject;
    }

    public void setJsonObject(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }
}
