package com.swadhaar.los.database.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.swadhaar.los.database.converter.TimestampConverter;

import java.util.Date;

@Entity
public class LogInTable {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("Key")
    @Expose
    public String Key ;

    @SerializedName("UserID")
    @Expose
    public String UserID ;

    @SerializedName("Username")
    @Expose
    public String Username ;

    @SerializedName("Password")
    @Expose
    public String Password ;

    @SerializedName("ApiKey")
    @Expose
    public String ApiKey ;

    @SerializedName("UserType")
    @Expose
    public String UserType ;

    @SerializedName("FName")
    @Expose
    public String FName ;

    @SerializedName("MName")
    @Expose
    public String MName ;

    @SerializedName("LName")
    @Expose
    public String LName ;

    @SerializedName("ErrorMessage")
    @Expose
    public String ErrorMessage ;

    @SerializedName("ErrorCode")
    @Expose
    public int ErrorCode ;

    @SerializedName("isNewUser")
    @Expose
    public int isNewUser ;

    @SerializedName("UserIPAddress")
    @Expose
    public String UserIPAddress ;

    @SerializedName("UserSystemName")
    @Expose
    public String UserSystemName ;

    @SerializedName("ModelPermission")
    @Expose
    public String ModelPermission ;

    @SerializedName("Error")
    @Expose
    public String Error ;

    @SerializedName("Message")
    @Expose
    public String Message ;

    @SerializedName("DepartmentName")
    @Expose
    public String DepartmentName ;

    @SerializedName("BranchID")
    @Expose
    public String BranchID ;

    @SerializedName("RoleId")
    @Expose
    public int RoleId ;

    @SerializedName("BCID")
    @Expose
    public int BCID ;

    @SerializedName("RoleName")
    @Expose
    public String RoleName ;

    @SerializedName("DeptID")
    @Expose
    public String DeptID ;

    @SerializedName("Mobile")
    @Expose
    public String Mobile ;

    @SerializedName("DesignationID")
    @Expose
    public int DesignationID ;

    @SerializedName("BranchName")
    @Expose
    public String BranchName ;

    @SerializedName("DesignationName")
    @Expose
    public String DesignationName ;

    @SerializedName("Zone")
    @Expose
    public String Zone ;

    @SerializedName("Region")
    @Expose
    public String Region ;

    @SerializedName("BranchGSTCode")
    @Expose
    public String BranchGSTCode ;

    @SerializedName("ApplicationType")
    @Expose
    public String ApplicationType ;

    @SerializedName("IsKillPreviousSession")
    @Expose
    public boolean IsKillPreviousSession ;


    public LogInTable() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKey() {
        return Key;
    }

    public void setKey(String key) {
        Key = key;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getApiKey() {
        return ApiKey;
    }

    public void setApiKey(String apiKey) {
        ApiKey = apiKey;
    }

    public String getUserType() {
        return UserType;
    }

    public void setUserType(String userType) {
        UserType = userType;
    }

    public String getFName() {
        return FName;
    }

    public void setFName(String FName) {
        this.FName = FName;
    }

    public String getMName() {
        return MName;
    }

    public void setMName(String MName) {
        this.MName = MName;
    }

    public String getLName() {
        return LName;
    }

    public void setLName(String LName) {
        this.LName = LName;
    }

    public String getErrorMessage() {
        return ErrorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        ErrorMessage = errorMessage;
    }

    public int getErrorCode() {
        return ErrorCode;
    }

    public void setErrorCode(int errorCode) {
        ErrorCode = errorCode;
    }

    public int getIsNewUser() {
        return isNewUser;
    }

    public void setIsNewUser(int isNewUser) {
        this.isNewUser = isNewUser;
    }

    public String getUserIPAddress() {
        return UserIPAddress;
    }

    public void setUserIPAddress(String userIPAddress) {
        UserIPAddress = userIPAddress;
    }

    public String getUserSystemName() {
        return UserSystemName;
    }

    public void setUserSystemName(String userSystemName) {
        UserSystemName = userSystemName;
    }

    public String getModelPermission() {
        return ModelPermission;
    }

    public void setModelPermission(String modelPermission) {
        ModelPermission = modelPermission;
    }

    public String getError() {
        return Error;
    }

    public void setError(String error) {
        Error = error;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public String getDepartmentName() {
        return DepartmentName;
    }

    public void setDepartmentName(String departmentName) {
        DepartmentName = departmentName;
    }

    public String getBranchID() {
        return BranchID;
    }

    public void setBranchID(String branchID) {
        BranchID = branchID;
    }

    public int getRoleId() {
        return RoleId;
    }

    public void setRoleId(int roleId) {
        RoleId = roleId;
    }

    public int getBCID() {
        return BCID;
    }

    public void setBCID(int BCID) {
        this.BCID = BCID;
    }

    public String getRoleName() {
        return RoleName;
    }

    public void setRoleName(String roleName) {
        RoleName = roleName;
    }

    public String getDeptID() {
        return DeptID;
    }

    public void setDeptID(String deptID) {
        DeptID = deptID;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    public int getDesignationID() {
        return DesignationID;
    }

    public void setDesignationID(int designationID) {
        DesignationID = designationID;
    }

    public String getBranchName() {
        return BranchName;
    }

    public void setBranchName(String branchName) {
        BranchName = branchName;
    }

    public String getDesignationName() {
        return DesignationName;
    }

    public void setDesignationName(String designationName) {
        DesignationName = designationName;
    }

    public String getZone() {
        return Zone;
    }

    public void setZone(String zone) {
        Zone = zone;
    }

    public String getRegion() {
        return Region;
    }

    public void setRegion(String region) {
        Region = region;
    }

    public String getBranchGSTCode() {
        return BranchGSTCode;
    }

    public void setBranchGSTCode(String branchGSTCode) {
        BranchGSTCode = branchGSTCode;
    }

    public String getApplicationType() {
        return ApplicationType;
    }

    public void setApplicationType(String applicationType) {
        ApplicationType = applicationType;
    }

    public boolean isKillPreviousSession() {
        return IsKillPreviousSession;
    }

    public void setKillPreviousSession(boolean killPreviousSession) {
        IsKillPreviousSession = killPreviousSession;
    }
}
