package com.saartak.el.models.UserLoginMenu;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;

public class UserLoginMenuResponseDTO {

    @Expose
    ArrayList<UserLoginMenuTable> Table;

    public ArrayList<UserLoginMenuTable> getTable() {
        return Table;
    }

    public void setTable(ArrayList<UserLoginMenuTable> table) {
        Table = table;
    }
}