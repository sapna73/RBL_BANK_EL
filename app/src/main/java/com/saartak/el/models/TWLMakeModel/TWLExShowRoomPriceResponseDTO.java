package com.saartak.el.models.TWLMakeModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class TWLExShowRoomPriceResponseDTO {

    @SerializedName("Table")
    @Expose
    ArrayList<TWLExShowRoomPriceResponseTable> twlExShowRoomPriceResponseTables;

    public ArrayList<TWLExShowRoomPriceResponseTable> getTwlExShowRoomPriceResponseTables() {
        return twlExShowRoomPriceResponseTables;
    }

    public void setTwlExShowRoomPriceResponseTables(ArrayList<TWLExShowRoomPriceResponseTable> twlExShowRoomPriceResponseTables) {
        this.twlExShowRoomPriceResponseTables = twlExShowRoomPriceResponseTables;
    }
}
