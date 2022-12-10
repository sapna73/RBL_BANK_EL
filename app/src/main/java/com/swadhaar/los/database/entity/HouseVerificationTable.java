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
public class HouseVerificationTable {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @SerializedName("id")
    @Expose (serialize = false, deserialize = false)
    private int id;

    @SerializedName("clientId")
    @Expose
    private String clientId;

    @SerializedName("clientName")
    @Expose
    private String clientName;

    @SerializedName("loanId")
    @Expose
    private String loanId;

    @SerializedName("loanProduct")
    @Expose
    private String loanProduct;

    @SerializedName("phoneNo")
    @Expose
    private String phoneNo;

    @SerializedName("dob")
    @Expose
    private String dob;

    @SerializedName("age")
    @Expose
    private String age;

    @SerializedName("nomineeName")
    @Expose
    private String nomineeName;

    @SerializedName("distanceFromCenter")
    @Expose
    private String distanceFromCenter;

    @SerializedName("kyc1_id_proof")
    @Expose
    private String kyc1_id_proof;

    @SerializedName("kyc1_id")
    @Expose
    private String kyc1_id;

    @SerializedName("kyc2_id_proof")
    @Expose
    private String kyc2_id_proof;

    @SerializedName("kyc2_id")
    @Expose
    private String kyc2_id;

    @SerializedName("kyc1_address")
    @Expose
    private String kyc1_address;

    @SerializedName("nominee_id_proof")
    @Expose
    private String nominee_id_proof;

    @SerializedName("nominee_id")
    @Expose
    private String nominee_id;

    @SerializedName("centerId")
    @Expose
    private String centerId;

    @SerializedName("centerName")
    @Expose
    private String centerName;

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

    @SerializedName("createdBy")
    @Expose
    private String createdBy;

    @SerializedName("reviewBy")
    @Expose
    private String reviewBy;

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

    @SerializedName("isHousePhotoCaptured")
    @Expose
    private boolean isHousePhotoCaptured;

    @SerializedName("isHousePhotoUploaded")
    @Expose
    private boolean isHousePhotoUploaded;

    @SerializedName("file_name")
    @Expose
    private String file_name;

    @SerializedName("file_path")
    @Expose
    private String file_path;

    @SerializedName("houseVerification")
    @Expose
    private boolean houseVerification;


    public HouseVerificationTable() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
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

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getReviewBy() {
        return reviewBy;
    }

    public void setReviewBy(String reviewBy) {
        this.reviewBy = reviewBy;
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

    public boolean isHouseVerification() {
        return houseVerification;
    }

    public void setHouseVerification(boolean houseVerification) {
        this.houseVerification = houseVerification;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getNomineeName() {
        return nomineeName;
    }

    public void setNomineeName(String nomineeName) {
        this.nomineeName = nomineeName;
    }

    public String getDistanceFromCenter() {
        return distanceFromCenter;
    }

    public void setDistanceFromCenter(String distanceFromCenter) {
        this.distanceFromCenter = distanceFromCenter;
    }

    public String getKyc1_id_proof() {
        return kyc1_id_proof;
    }

    public void setKyc1_id_proof(String kyc1_id_proof) {
        this.kyc1_id_proof = kyc1_id_proof;
    }

    public String getKyc1_id() {
        return kyc1_id;
    }

    public void setKyc1_id(String kyc1_id) {
        this.kyc1_id = kyc1_id;
    }

    public String getKyc2_id_proof() {
        return kyc2_id_proof;
    }

    public void setKyc2_id_proof(String kyc2_id_proof) {
        this.kyc2_id_proof = kyc2_id_proof;
    }

    public String getKyc2_id() {
        return kyc2_id;
    }

    public void setKyc2_id(String kyc2_id) {
        this.kyc2_id = kyc2_id;
    }

    public String getKyc1_address() {
        return kyc1_address;
    }

    public void setKyc1_address(String kyc1_address) {
        this.kyc1_address = kyc1_address;
    }

    public String getNominee_id_proof() {
        return nominee_id_proof;
    }

    public void setNominee_id_proof(String nominee_id_proof) {
        this.nominee_id_proof = nominee_id_proof;
    }

    public String getNominee_id() {
        return nominee_id;
    }

    public void setNominee_id(String nominee_id) {
        this.nominee_id = nominee_id;
    }

    public boolean isHousePhotoCaptured() {
        return isHousePhotoCaptured;
    }

    public void setHousePhotoCaptured(boolean housePhotoCaptured) {
        isHousePhotoCaptured = housePhotoCaptured;
    }

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public String getFile_path() {
        return file_path;
    }

    public void setFile_path(String file_path) {
        this.file_path = file_path;
    }

    public boolean isHousePhotoUploaded() {
        return isHousePhotoUploaded;
    }

    public void setHousePhotoUploaded(boolean housePhotoUploaded) {
        isHousePhotoUploaded = housePhotoUploaded;
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
}
