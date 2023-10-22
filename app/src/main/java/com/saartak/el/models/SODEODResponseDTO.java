package com.saartak.el.models;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;

public class SODEODResponseDTO {


    @Expose
    ArrayList<Table> Table;

    public ArrayList<SODEODResponseDTO.Table> getTable() {
        return Table;
    }

    public void setTable(ArrayList<SODEODResponseDTO.Table> table) {
        Table = table;
    }

    public static class Table {
        @Expose
        private String Response;

        public String getResponse() {
            return Response;
        }

        public void setResponse(String response) {
            Response = response;
        }
    }
}

