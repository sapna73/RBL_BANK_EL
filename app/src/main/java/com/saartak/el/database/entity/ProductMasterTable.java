package com.saartak.el.database.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity
public class    ProductMasterTable {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("ProductCode")
    @Expose
    private String ProductCode;

    @SerializedName("FinacleCode")
    @Expose
    private String FinacleCode;

    @SerializedName("ProductCategory")
    @Expose
    private String ProductCategory;

    @SerializedName("SchemeName")
    @Expose
    private String SchemeName;

    @SerializedName("MaxLoanAmount")
    @Expose
    private String MaxLoanAmount;

    @SerializedName("MinLoanAmount")
    @Expose
    private String MinLoanAmount;

    @SerializedName("MinROI")
    @Expose
    private String MinROI;

    @SerializedName("MaxROI")
    @Expose
    private String MaxROI;

    @SerializedName("DefaultROI")
    @Expose
    private String DefaultROI;

    @SerializedName("MinTenor")
    @Expose
    private String MinTenor;

    @SerializedName("MaxTenor")
    @Expose
    private String MaxTenor;

    @SerializedName("ProcessingFee")
    @Expose
    private String ProcessingFee;

    @SerializedName("InsuranceFee")
    @Expose
    private String InsuranceFee;

    @SerializedName("Step")
    @Expose
    private String Step;

    @SerializedName("DBRCalMtd")
    @Expose
    private String DBRCalMtd;

    @SerializedName("FeeAmt")
    @Expose
    private String FeeAmt;

    @SerializedName("Percentage")
    @Expose
    private String Percentage;

    @SerializedName("GST")
    @Expose
    private String GST;

    @SerializedName("LTVCalMtd")
    @Expose
    private String LTVCalMtd;


    public ProductMasterTable() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductCode() {
        return ProductCode;
    }

    public void setProductCode(String productCode) {
        ProductCode = productCode;
    }

    public String getFinacleCode() {
        return FinacleCode;
    }

    public void setFinacleCode(String finacleCode) {
        FinacleCode = finacleCode;
    }

    public String getProductCategory() {
        return ProductCategory;
    }

    public void setProductCategory(String productCategory) {
        ProductCategory = productCategory;
    }

    public String getSchemeName() {
        return SchemeName;
    }

    public void setSchemeName(String schemeName) {
        SchemeName = schemeName;
    }

    public String getMaxLoanAmount() {
        return MaxLoanAmount;
    }

    public void setMaxLoanAmount(String maxLoanAmount) {
        MaxLoanAmount = maxLoanAmount;
    }

    public String getMinLoanAmount() {
        return MinLoanAmount;
    }

    public void setMinLoanAmount(String minLoanAmount) {
        MinLoanAmount = minLoanAmount;
    }

    public String getMinROI() {
        return MinROI;
    }

    public void setMinROI(String minROI) {
        MinROI = minROI;
    }

    public String getMaxROI() {
        return MaxROI;
    }

    public void setMaxROI(String maxROI) {
        MaxROI = maxROI;
    }

    public String getDefaultROI() {
        return DefaultROI;
    }

    public void setDefaultROI(String defaultROI) {
        DefaultROI = defaultROI;
    }

    public String getMinTenor() {
        return MinTenor;
    }

    public void setMinTenor(String minTenor) {
        MinTenor = minTenor;
    }

    public String getMaxTenor() {
        return MaxTenor;
    }

    public void setMaxTenor(String maxTenor) {
        MaxTenor = maxTenor;
    }

    public String getProcessingFee() {
        return ProcessingFee;
    }

    public void setProcessingFee(String processingFee) {
        ProcessingFee = processingFee;
    }

    public String getInsuranceFee() {
        return InsuranceFee;
    }

    public void setInsuranceFee(String insuranceFee) {
        InsuranceFee = insuranceFee;
    }

    public String getStep() {
        return Step;
    }

    public void setStep(String step) {
        Step = step;
    }

    public String getDBRCalMtd() {
        return DBRCalMtd;
    }

    public void setDBRCalMtd(String DBRCalMtd) {
        this.DBRCalMtd = DBRCalMtd;
    }

    public String getLTVCalMtd() {
        return LTVCalMtd;
    }

    public void setLTVCalMtd(String LTVCalMtd) {
        this.LTVCalMtd = LTVCalMtd;
    }

    public String getFeeAmt() {
        return FeeAmt;
    }

    public void setFeeAmt(String feeAmt) {
        FeeAmt = feeAmt;
    }

    public String getPercentage() {
        return Percentage;
    }

    public void setPercentage(String percentage) {
        Percentage = percentage;
    }

    public String getGST() {
        return GST;
    }

    public void setGST(String GST) {
        this.GST = GST;
    }
}
