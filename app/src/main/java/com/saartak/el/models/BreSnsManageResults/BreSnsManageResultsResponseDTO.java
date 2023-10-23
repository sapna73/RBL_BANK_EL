package com.saartak.el.models.BreSnsManageResults;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class BreSnsManageResultsResponseDTO {

    @SerializedName("Table")
    @Expose
    ArrayList<BreSnsManageResultsREsponseTable> breSnsManageResultsREsponseTables;

    public ArrayList<BreSnsManageResultsREsponseTable> getBreSnsManageResultsREsponseTables() {
        return breSnsManageResultsREsponseTables;
    }

    public void setBreSnsManageResultsREsponseTables(ArrayList<BreSnsManageResultsREsponseTable> breSnsManageResultsREsponseTables) {
        this.breSnsManageResultsREsponseTables = breSnsManageResultsREsponseTables;
    }
}
