package com.saartak.el.models.Deliquency;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class DeliquencyResponseDataFromTable {

    @SerializedName("Table")
    @Expose
    ArrayList<WEBResponse> webResponses;

    private class WEBResponse {
        @SerializedName("WebResponse")
        @Expose
        private DeliquencyResponseDTO deliquencyResponseDTO;

        public DeliquencyResponseDTO getDeliquencyResponseDTO() {
            return deliquencyResponseDTO;
        }

        public void setDeliquencyResponseDTO(DeliquencyResponseDTO deliquencyResponseDTO) {
            this.deliquencyResponseDTO = deliquencyResponseDTO;
        }
    }

    public ArrayList<WEBResponse> getWebResponses() {
        return webResponses;
    }

    public void setWebResponses(ArrayList<WEBResponse> webResponses) {
        this.webResponses = webResponses;
    }
}
