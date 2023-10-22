package com.saartak.el.models.NegitiveProfileList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class NegitiveProfileListResponseDTO {

    @SerializedName("Table")
    @Expose
    ArrayList<NegitiveProfileListResponseTable> negitiveProfileListResponseDTO;

    public ArrayList<NegitiveProfileListResponseTable> getNegitiveProfileListResponseDTO() {
        return negitiveProfileListResponseDTO;
    }

    public void setNegitiveProfileListResponseDTO(ArrayList<NegitiveProfileListResponseTable> negitiveProfileListResponseDTO) {
        this.negitiveProfileListResponseDTO = negitiveProfileListResponseDTO;
    }
}
