package com.saartak.el.models.Posidex;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class PosidexResponseDataFromTable {

        @SerializedName("Table")
        @Expose
        ArrayList<WEBResponse> webResponses;

        private class WEBResponse {
            @SerializedName("WebResponse")
            @Expose
            private PosidexResponseDTO posidexResponseDTO;

            public PosidexResponseDTO getPosidexResponseDTO() {
                return posidexResponseDTO;
            }

            public void setPosidexResponseDTO(PosidexResponseDTO posidexResponseDTO) {
                this.posidexResponseDTO = posidexResponseDTO;
            }
        }

    }
