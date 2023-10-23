package com.saartak.el.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.saartak.el.database.entity.LogInTable;

import java.util.ArrayList;

public class LoginResponseDTO {
    @Expose
    ArrayList<Object> _menus = new ArrayList<Object>();
    @SerializedName("UserDetails")
    @Expose
    LogInTable logInTable;

    public LogInTable getLogInTable() {
        return logInTable;
    }

    public void setLogInTable(LogInTable logInTable) {
        this.logInTable = logInTable;
    }

    public ArrayList<Object> get_menus() {
        return _menus;
    }

    public void set_menus(ArrayList<Object> _menus) {
        this._menus = _menus;
    }
}
