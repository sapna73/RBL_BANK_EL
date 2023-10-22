package com.saartak.el.models;

import com.google.gson.annotations.Expose;
import com.saartak.el.database.entity.KnowledgeBankTable;

import java.util.ArrayList;

public class KnowledgeBankResponseDTO {


    @Expose
    ArrayList<KnowledgeBankTable> Table;

    public ArrayList<KnowledgeBankTable> getTable() {
        return Table;
    }

    public void setTable(ArrayList<KnowledgeBankTable> table) {
        Table = table;
    }
}

