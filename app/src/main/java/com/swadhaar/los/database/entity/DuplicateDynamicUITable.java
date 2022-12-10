package com.swadhaar.los.database.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.swadhaar.los.database.converter.DataTypeConverter;

import java.io.Serializable;

@Entity
public class DuplicateDynamicUITable implements Serializable {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("FieldName")
    @Expose
    private String FieldName;

    @SerializedName("DataType")
    @Expose
    private String DataType;

    @SerializedName("Length")
    @Expose
    private String Length;

    @SerializedName("IsRequired")
    @Expose
    private String IsRequired;

    @SerializedName("FieldType")
    @Expose
    private String FieldType;

    @SerializedName("FieldTag")
    @Expose
    private String FieldTag;

    @SerializedName("Visibility")
    @Expose
    private boolean Visibility;

    @SerializedName("Hint")
    @Expose
    private String Hint;

    @SerializedName("ErrorMessage")
    @Expose
    private String ErrorMessage;

    @SerializedName("paramlist")
    @Expose
    @TypeConverters(DataTypeConverter.class)
    private String[] paramlist;

    @SerializedName("spinnerItemPosition")
    @Expose
    private int spinnerItemPosition;

    @SerializedName("value")
    @Expose
    private String value;

    @SerializedName("screenID")
    @Expose
    private String screenID;

    @SerializedName("screenName")
    @Expose
    private String screenName;

    @SerializedName("IsEditableMob")
    @Expose
    private boolean isEditable;

    @SerializedName("plusSignName")
    @Expose
    private String plusSignName;

    @SerializedName("clientID")
    @Expose
    private String clientID;

    @SerializedName("LoanType")
    @Expose
    private String LoanType;

    @SerializedName("DefaultValue")
    @Expose
    private String DefaultValue;

    @SerializedName("DataEntryType")
    @Expose
    private int DataEntryType;

    @SerializedName("RequiredInWeb")
    @Expose
    private int RequiredInWeb;

    @SerializedName("FeatureID")
    @Expose
    private int FeatureID;

    @SerializedName("isValid")
    @Expose
    private boolean isValid=true;


    @SerializedName("user_id")
    @Expose
    private String user_id;

    @SerializedName("moduleType")
    @Expose
    private String moduleType;

    @SerializedName("MinLength")
    @Expose
    private String MinLength;

    @SerializedName("UniqueId")
    @Expose
    private String UniqueId="1";

    @SerializedName("CoRelationID")
    @Expose
    private String CoRelationID="1";

    @SerializedName("ProductId")
    @Expose
    private String ProductId;

    @SerializedName("sync")
    @Expose
    private boolean sync;


    public DuplicateDynamicUITable() {

    }

    public DuplicateDynamicUITable(@NonNull int id, String fieldName, String dataType, String length, String isRequired,
                          String fieldType, String fieldTag, boolean visibility, String hint, String errorMessage
            ,String[] paramlist, int spinnerItemPosition, String value, String screenID,boolean isEditable,
                          String plusSignName,String clientID,String LoanType) {
        this.id = id;
        FieldName = fieldName;
        DataType = dataType;
        Length = length;
        IsRequired = isRequired;
        FieldType = fieldType;
        FieldTag = fieldTag;
        Visibility = visibility;
        Hint = hint;
        ErrorMessage = errorMessage;
        this.paramlist = paramlist;
        this.spinnerItemPosition = spinnerItemPosition;
        this.value = value;
        this.screenID=screenID;
        this.isEditable=isEditable;
        this.plusSignName=plusSignName;
        this.clientID=clientID;
        this.LoanType=LoanType;
    }

    @NonNull
    public int getId() {
        return id;
    }

    public void setId(@NonNull int id) {
        this.id = id;
    }

    public String getFieldName() {
        return FieldName;
    }

    public void setFieldName(String fieldName) {
        FieldName = fieldName;
    }

    public String getDataType() {
        return DataType;
    }

    public void setDataType(String dataType) {
        DataType = dataType;
    }

    public String getLength() {
        return Length;
    }

    public void setLength(String length) {
        Length = length;
    }

    public String getIsRequired() {
        return IsRequired;
    }

    public void setIsRequired(String isRequired) {
        IsRequired = isRequired;
    }

    public String getFieldType() {
        return FieldType;
    }

    public void setFieldType(String fieldType) {
        FieldType = fieldType;
    }

    public String getFieldTag() {
        return FieldTag;
    }

    public void setFieldTag(String fieldTag) {
        FieldTag = fieldTag;
    }

    public boolean isVisibility() {
        return Visibility;
    }

    public void setVisibility(boolean visibility) {
        Visibility = visibility;
    }

    public String getHint() {
        return Hint;
    }

    public void setHint(String hint) {
        Hint = hint;
    }

    public String getErrorMessage() {
        return ErrorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        ErrorMessage = errorMessage;
    }

    public String[] getParamlist() {
        return paramlist;
    }

    public void setParamlist(String[] paramlist) {
        this.paramlist = paramlist;
    }

    public int getSpinnerItemPosition() {
        return spinnerItemPosition;
    }

    public void setSpinnerItemPosition(int spinnerItemPosition) {
        this.spinnerItemPosition = spinnerItemPosition;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getScreenID() {
        return screenID;
    }

    public void setScreenID(String screenName) {
        this.screenID = screenName;
    }

    public boolean isEditable() {
        return isEditable;
    }
    public void setEditable(boolean editable) {
        isEditable = editable;
    }

    public String getPlusSignName() {
        return plusSignName;
    }

    public void setPlusSignName(String plusSignName) {
        this.plusSignName = plusSignName;
    }

    public String getClientID() {
        return clientID;
    }
    public void setClientID(String clientID) {
        this.clientID = clientID;
    }

    public String getLoanType() {
        return LoanType;
    }
    public void setLoanType(String loanType) {
        LoanType = loanType;
    }

    public String getDefaultValue() {
        return DefaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        DefaultValue = defaultValue;
    }

    public int getDataEntryType() {
        return DataEntryType;
    }

    public void setDataEntryType(int dataEntryType) {
        DataEntryType = dataEntryType;
    }

    public int getRequiredInWeb() {
        return RequiredInWeb;
    }

    public void setRequiredInWeb(int requiredInWeb) {
        RequiredInWeb = requiredInWeb;
    }

    public int getFeatureID() {
        return FeatureID;
    }

    public void setFeatureID(int featureID) {
        FeatureID = featureID;
    }

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getModuleType() {
        return moduleType;
    }

    public void setModuleType(String moduleType) {
        this.moduleType = moduleType;
    }

    public String getMinLength() {
        return MinLength;
    }

    public void setMinLength(String minLength) {
        MinLength = minLength;
    }

    public String getCoRelationID() {
        return CoRelationID;
    }

    public void setCoRelationID(String coRelationID) {
        CoRelationID = coRelationID;
    }

    public String getUniqueId() {
        return UniqueId;
    }

    public void setUniqueId(String uniqueId) {
        UniqueId = uniqueId;
    }

    public String getProductId() {
        return ProductId;
    }

    public void setProductId(String productId) {
        ProductId = productId;
    }

    public boolean isSync() {
        return sync;
    }

    public void setSync(boolean sync) {
        this.sync = sync;
    }
}
