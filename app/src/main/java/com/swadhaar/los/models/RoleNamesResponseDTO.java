package com.swadhaar.los.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.swadhaar.los.database.entity.RoleNameTable;

import java.util.ArrayList;

public class RoleNamesResponseDTO {

   /* @SerializedName("Table")
    ArrayList<RoleNameTable> roleNameTableList;

    public ArrayList<RoleNameTable> getRoleNameTableList() {
        return roleNameTableList;
    }

    public void setRoleNameTableList(ArrayList<RoleNameTable> roleNameTableList) {
        this.roleNameTableList = roleNameTableList;
    }*/

    @Expose
   ArrayList<Table> Table;

    public ArrayList<RoleNamesResponseDTO.Table> getTable() {
        return Table;
    }

    public void setTable(ArrayList<RoleNamesResponseDTO.Table> table) {
        Table = table;
    }

    public static class Table {
        @Expose
        private String Roles;
        @Expose
        private String RoleID;

        public String getRoles() {
            return Roles;
        }

        public String getRoleID() {
            return RoleID;
        }

        public void setRoles(String roles) {
            Roles = roles;
        }

        public void setRoleID(String roleID) {
            RoleID = roleID;
        }
    }
}
