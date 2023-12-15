package com.saartak.el.models;

import com.google.gson.annotations.Expose;
import com.saartak.el.database.entity.DocumentMasterTable;

import java.util.ArrayList;

public class DocumentMasterResponseDTO {

    @Expose
    ArrayList<DocumentMasterTable> Table;

    public ArrayList<DocumentMasterTable> getTable() {
        return Table;
    }

    public void setTable(ArrayList<DocumentMasterTable> table) {
        Table = table;
    }
}

