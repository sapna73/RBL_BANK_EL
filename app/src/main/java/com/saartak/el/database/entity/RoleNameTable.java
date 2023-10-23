package com.saartak.el.database.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
@Entity
public class RoleNameTable {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @SerializedName("id")
    @Expose(serialize = false, deserialize = false)
    private int id;

    @SerializedName("staffId")
    @Expose
    private String staffId;

    @SerializedName("rolename")
    @Expose
    private String rolename;

    @SerializedName("roleId")
    @Expose
    private String roleId;

    @SerializedName("isSelected")
    @Expose
    private boolean isSelected;

    public int getId() {
        return id;
    }

    public String getStaffId() {
        return staffId;
    }

    public String getRolename() {
        return rolename;
    }

    public String getRoleId() {
        return roleId;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
