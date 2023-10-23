package com.saartak.el.database.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity
public class PlannerTable {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("ActivityRefNum")
    @Expose
    private String clientId;

    @SerializedName("staffId")
    @Expose
    private String staffId;

    @SerializedName("staffName")
    @Expose
    private String staffName;

    @SerializedName("branchId")
    @Expose
    private String branchId;

    @SerializedName("branchGSTcode")
    @Expose
    private String branchGSTcode;

    @SerializedName("loan_type")
    @Expose
    private String loan_type;

    @SerializedName("created_date")
    @Expose
    private String created_date;

    @SerializedName("isTripStart")
    @Expose
    private boolean isTripStart;

    @SerializedName("isTripEnd")
    @Expose
    private boolean isTripEnd;

    @SerializedName("PurposeOfVisit")
    @Expose
    private String PurposeOfVisit;

    @SerializedName("ApplicationId")
    @Expose
    private String ApplicationId;

    @SerializedName("ShopName")
    @Expose
    private String ShopName;

    @SerializedName("AlongWithStaff")
    @Expose
    private boolean AlongWithStaff;

    @SerializedName("AccompaniedId")
    @Expose
    private String EmployeeId;

    @SerializedName("EmployeeName")
    @Expose
    private String EmployeeName;

    @SerializedName("Designation")
    @Expose
    private String Designation;

    @SerializedName("TravelWithOwnVehicle")
    @Expose
    private boolean TravelWithOwnVehicle;

    @SerializedName("VehicleType")
    @Expose
    private boolean VehicleType;

    @SerializedName("StartLat")
    @Expose
    private String StartLat="";

    @SerializedName("StartLong")
    @Expose
    private String StartLong="";

    @SerializedName("EndLat")
    @Expose
    private String EndLat="";

    @SerializedName("EndLong")
    @Expose
    private String EndLong="";

    @SerializedName("TotalDistance")
    @Expose
    private String Distance;

    @SerializedName("sync")
    @Expose
    private boolean sync;


    public void setId(int id) {
        this.id = id;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getBranchGSTcode() {
        return branchGSTcode;
    }

    public void setBranchGSTcode(String branchGSTcode) {
        this.branchGSTcode = branchGSTcode;
    }

    public String getLoan_type() {
        return loan_type;
    }

    public void setLoan_type(String loan_type) {
        this.loan_type = loan_type;
    }

    public String getCreated_date() {
        return created_date;
    }

    public void setCreated_date(String created_date) {
        this.created_date = created_date;
    }

    public boolean isTripStart() {
        return isTripStart;
    }

    public void setTripStart(boolean TripStart) {
        isTripStart = TripStart;
    }

    public boolean isTripEnd() {
        return isTripEnd;
    }

    public void setTripEnd(boolean TripEnd) {
        isTripEnd = TripEnd;
    }

    public void setPurposeOfVisit(String purposeOfVisit) {
        PurposeOfVisit = purposeOfVisit;
    }

    public void setApplicationId(String applicationId) {
        ApplicationId = applicationId;
    }

    public String getApplicationId() {
        return ApplicationId;
    }

    public void setShopName(String shopName) {
        ShopName = shopName;
    }

    public void setAlongWithStaff(boolean alongWithStaff) {
        AlongWithStaff = alongWithStaff;
    }

    public void setEmployeeId(String employeeId) {
        EmployeeId = employeeId;
    }

    public void setEmployeeName(String employeeName) {
        EmployeeName = employeeName;
    }

    public void setDesignation(String designation) {
        Designation = designation;
    }

    public void setTravelWithOwnVehicle(boolean travelWithOwnVehicle) {
        TravelWithOwnVehicle = travelWithOwnVehicle;
    }

    public void setVehicleType(boolean vehicleType) {
        VehicleType = vehicleType;
    }

    public void setStartLat(String startLat) {
        StartLat = startLat;
    }

    public void setStartLong(String startLong) {
        StartLong = startLong;
    }

    public void setEndLat(String endLat) {
        EndLat = endLat;
    }

    public void setEndLong(String endLong) {
        EndLong = endLong;
    }

    public void setDistance(String distance) {
        Distance = distance;
    }

    public int getId() {
        return id;
    }

    public String getPurposeOfVisit() {
        return PurposeOfVisit;
    }

    public String getShopName() {
        return ShopName;
    }

    public boolean isAlongWithStaff() {
        return AlongWithStaff;
    }

    public String getEmployeeId() {
        return EmployeeId;
    }

    public String getEmployeeName() {
        return EmployeeName;
    }

    public String getDesignation() {
        return Designation;
    }

    public boolean isTravelWithOwnVehicle() {
        return TravelWithOwnVehicle;
    }

    public boolean isVehicleType() {
        return VehicleType;
    }

    public String getStartLat() {
        return StartLat;
    }

    public String getStartLong() {
        return StartLong;
    }

    public String getEndLat() {
        return EndLat;
    }

    public String getEndLong() {
        return EndLong;
    }

    public String getDistance() {
        return Distance;
    }

    public boolean isSync() {
        return sync;
    }

    public void setSync(boolean sync) {
        this.sync = sync;
    }

}
