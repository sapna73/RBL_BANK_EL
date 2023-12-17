package com.saartak.el.models;

import com.google.gson.annotations.Expose;
import com.saartak.el.database.entity.ProductMasterTable;

import java.util.ArrayList;

public class ProductMasterResponseDTO {

    @Expose
    ArrayList<ProductMasterTable> Table;

    public ArrayList<ProductMasterTable> getTable() {
        return Table;
    }

    public void setTable(ArrayList<ProductMasterTable> table) {
        Table = table;
    }
}

