package com.saartak.el.models.UserLoginMenu;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity
public class UserLoginMenuTable {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @SerializedName("Id")
    @Expose(serialize = false, deserialize = false)
    private int id;

    @SerializedName("MainMenuId")
    @Expose
    private String mainMenuId;

    @SerializedName("SubMenu")
    @Expose
    private String subMenu;

    @SerializedName("RoleId")
    @Expose
    private String roleId;

    @SerializedName("RoleName")
    @Expose
    private String roleName;


    @SerializedName("SqID")
    @Expose
    private String sqID;


    @SerializedName("DeptID")
    @Expose
    private String deptID;


    @SerializedName("Username")
    @Expose
    private String username;


    @SerializedName("DesignationID")
    @Expose
    private String designationID;


    @SerializedName("IsActive")
    @Expose
    private boolean isActive;

    @SerializedName("IsDashboard")
    @Expose
    private boolean isDashboard;

    @SerializedName("DashboardName")
    @Expose
    private String dashboardName;

    @SerializedName("ProjectId")
    @Expose
    private String projectId;

    @SerializedName("ProductId")
    @Expose
    private String productId;

    @SerializedName("WorkflowId")
    @Expose
    private String workflowId;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMainMenuId() {
        return mainMenuId;
    }

    public void setMainMenuId(String mainMenuId) {
        this.mainMenuId = mainMenuId;
    }

    public String getSubMenu() {
        return subMenu;
    }

    public void setSubMenu(String subMenu) {
        this.subMenu = subMenu;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getSqID() {
        return sqID;
    }

    public void setSqID(String sqID) {
        this.sqID = sqID;
    }

    public String getDeptID() {
        return deptID;
    }

    public void setDeptID(String deptID) {
        this.deptID = deptID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDesignationID() {
        return designationID;
    }

    public void setDesignationID(String designationID) {
        this.designationID = designationID;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isDashboard() {
        return isDashboard;
    }

    public void setDashboard(boolean dashboard) {
        isDashboard = dashboard;
    }

    public String getDashboardName() {
        return dashboardName;
    }

    public void setDashboardName(String dashboardName) {
        this.dashboardName = dashboardName;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getWorkflowId() {
        return workflowId;
    }

    public void setWorkflowId(String workflowId) {
        this.workflowId = workflowId;
    }
}
