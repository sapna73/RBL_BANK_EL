package com.saartak.el.database.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity
public class CGTTable {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @SerializedName("id")
    @Expose (serialize = false, deserialize = false)
    private int id;

    @SerializedName("centerId")
    @Expose
    private String centerId;

    @SerializedName("centerName")
    @Expose
    private String centerName;

    @SerializedName("clientId")
    @Expose
    private String clientId;

    @SerializedName("clientName")
    @Expose
    private String clientName;

    @SerializedName("phoneNo")
    @Expose
    private String phoneNo;

    @SerializedName("loanId")
    @Expose
    private String loanId;

    @SerializedName("loanProduct")
    @Expose
    private String loanProduct;

    @SerializedName("loanProductCode")
    @Expose
    private String loanProductCode;

    @SerializedName("villageName")
    @Expose
    private String villageName;

    @SerializedName("villageId")
    @Expose
    private String villageId;

    @SerializedName("loan_type")
    @Expose (serialize = false, deserialize = false)
    private String loan_type;

    @SerializedName("Status")
    @Expose (serialize = false, deserialize = false)
    private String Status;

    @SerializedName("Remarks")
    @Expose (serialize = false, deserialize = false)
    private String Remarks;

    @SerializedName("sync")
    @Expose (serialize = false, deserialize = false)
    private boolean sync;

    @SerializedName("AllDataCaptured")
    @Expose
    private boolean AllDataCaptured;

    @SerializedName("createdBy")
    @Expose
    private String createdBy;

    @SerializedName("branchId")
    @Expose
    private String branchId;

    @SerializedName("branchGSTcode")
    @Expose
    private String branchGSTcode;

    @SerializedName("created_date")
    @Expose
    private String created_date;

    @SerializedName("response")
    @Expose
    private String response;

    @SerializedName("cycleOneStartSession")
    @Expose
    private String cycleOneStartSession;

    @SerializedName("cycleOneEndSession")
    @Expose
    private String cycleOneEndSession;

    @SerializedName("cycleTwoStartSession")
    @Expose
    private String cycleTwoStartSession;

    @SerializedName("cycleTwoEndSession")
    @Expose
    private String cycleTwoEndSession;

//    @SerializedName("cycleOneAttendance")
//    @Expose
//    private boolean cycleOneAttendance;
//
//    @SerializedName("cycleTwoAttendance")
//    @Expose
//    private boolean cycleTwoAttendance;

    @SerializedName("activity1")
    @Expose
    private boolean activity1;

    @SerializedName("activity2")
    @Expose
    private boolean activity2;

    @SerializedName("activity3")
    @Expose
    private boolean activity3;

    @SerializedName("houseVerification")
    @Expose
    private boolean houseVerification;

    @SerializedName("isCycleOneCompleted")
    @Expose
    private boolean isCycleOneCompleted;

    @SerializedName("isCycleTwoCompleted")
    @Expose
    private boolean isCycleTwoCompleted;

    @SerializedName("cgtRejected")
    @Expose
    private boolean cgtRejected;

    @SerializedName("isExistingCustomer")
    @Expose
    private boolean isExistingCustomer;

    private boolean isDropOutCustomer;

    public CGTTable() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCenterId() {
        return centerId;
    }

    public void setCenterId(String centerId) {
        this.centerId = centerId;
    }

    public String getCenterName() {
        return centerName;
    }

    public void setCenterName(String centerName) {
        this.centerName = centerName;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getLoanId() {
        return loanId;
    }

    public void setLoanId(String loanId) {
        this.loanId = loanId;
    }

    public String getLoanProduct() {
        return loanProduct;
    }

    public void setLoanProduct(String loanProduct) {
        this.loanProduct = loanProduct;
    }

    public String getVillageName() {
        return villageName;
    }

    public void setVillageName(String villageName) {
        this.villageName = villageName;
    }

    public String getVillageId() {
        return villageId;
    }

    public void setVillageId(String villageId) {
        this.villageId = villageId;
    }

    public String getLoan_type() {
        return loan_type;
    }

    public void setLoan_type(String loan_type) {
        this.loan_type = loan_type;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getRemarks() {
        return Remarks;
    }

    public void setRemarks(String remarks) {
        Remarks = remarks;
    }

    public boolean isSync() {
        return sync;
    }

    public void setSync(boolean sync) {
        this.sync = sync;
    }

    public String getLoanProductCode() {
        return loanProductCode;
    }

    public void setLoanProductCode(String loanProductCode) {
        this.loanProductCode = loanProductCode;
    }

    public boolean isAllDataCaptured() {
        return AllDataCaptured;
    }

    public void setAllDataCaptured(boolean allDataCaptured) {
        AllDataCaptured = allDataCaptured;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
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

    public String getCreated_date() {
        return created_date;
    }

    public void setCreated_date(String created_date) {
        this.created_date = created_date;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getCycleOneStartSession() {
        return cycleOneStartSession;
    }

    public void setCycleOneStartSession(String cycleOneStartSession) {
        this.cycleOneStartSession = cycleOneStartSession;
    }

    public String getCycleOneEndSession() {
        return cycleOneEndSession;
    }

    public void setCycleOneEndSession(String cycleOneEndSession) {
        this.cycleOneEndSession = cycleOneEndSession;
    }

    public String getCycleTwoStartSession() {
        return cycleTwoStartSession;
    }

    public void setCycleTwoStartSession(String cycleTwoStartSession) {
        this.cycleTwoStartSession = cycleTwoStartSession;
    }

    public String getCycleTwoEndSession() {
        return cycleTwoEndSession;
    }

    public void setCycleTwoEndSession(String cycleTwoEndSession) {
        this.cycleTwoEndSession = cycleTwoEndSession;
    }

//    public boolean isCycleOneAttendance() {
//        return cycleOneAttendance;
//    }
//
//    public void setCycleOneAttendance(boolean cycleOneAttendance) {
//        this.cycleOneAttendance = cycleOneAttendance;
//    }
//
//    public boolean isCycleTwoAttendance() {
//        return cycleTwoAttendance;
//    }
//
//    public void setCycleTwoAttendance(boolean cycleTwoAttendance) {
//        this.cycleTwoAttendance = cycleTwoAttendance;
//    }

    public boolean isActivity1() {
        return activity1;
    }

    public void setActivity1(boolean activity1) {
        this.activity1 = activity1;
    }

    public boolean isActivity2() {
        return activity2;
    }

    public void setActivity2(boolean activity2) {
        this.activity2 = activity2;
    }

    public boolean isActivity3() {
        return activity3;
    }

    public void setActivity3(boolean activity3) {
        this.activity3 = activity3;
    }

    public boolean isHouseVerification() {
        return houseVerification;
    }

    public void setHouseVerification(boolean houseVerification) {
        this.houseVerification = houseVerification;
    }

    public boolean isCycleOneCompleted() {
        return isCycleOneCompleted;
    }

    public void setCycleOneCompleted(boolean cycleOneCompleted) {
        isCycleOneCompleted = cycleOneCompleted;
    }

    public boolean isCycleTwoCompleted() {
        return isCycleTwoCompleted;
    }

    public void setCycleTwoCompleted(boolean cycleTwoCompleted) {
        isCycleTwoCompleted = cycleTwoCompleted;
    }

    public boolean isCgtRejected() {
        return cgtRejected;
    }

    public void setCgtRejected(boolean cgtRejected) {
        this.cgtRejected = cgtRejected;
    }

    public boolean isExistingCustomer() {
        return isExistingCustomer;
    }

    public void setExistingCustomer(boolean existingCustomer) {
        isExistingCustomer = existingCustomer;
    }

    public boolean isDropOutCustomer() {
        return isDropOutCustomer;
    }

    public void setDropOutCustomer(boolean dropOutCustomer) {
        isDropOutCustomer = dropOutCustomer;
    }
}
