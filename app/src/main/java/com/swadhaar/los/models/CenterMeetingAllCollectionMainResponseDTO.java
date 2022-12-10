package com.swadhaar.los.models;

import java.util.ArrayList;

public class CenterMeetingAllCollectionMainResponseDTO {

    ArrayList<Table> Table;

    public ArrayList<Table> getTable() {
        return Table;
    }

    public void setTable(ArrayList<Table> table) {
        Table = table;
    }

    public static class Table {
        private String Response;

        public String getResponse() {
            return Response;
        }

        public void setResponse(String response) {
            Response = response;
        }


    }
}

