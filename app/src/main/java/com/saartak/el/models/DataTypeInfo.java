package com.saartak.el.models;

import com.google.gson.annotations.Expose;
import com.saartak.el.database.entity.DynamicUITable;

import static com.saartak.el.constants.AppConstant.LOAN_NAME_JLG;
import static com.saartak.el.dynamicui.constants.ParametersConstant.DATATYPE_ALPHA_NUMERIC;
import static com.saartak.el.dynamicui.constants.ParametersConstant.DATATYPE_INT;
import static com.saartak.el.dynamicui.constants.ParametersConstant.DATATYPE_NUMERIC;
import static com.saartak.el.dynamicui.constants.ParametersConstant.DATATYPE_STRING;
import static com.saartak.el.dynamicui.constants.ParametersConstant.SPINNER_ITEM_FIELD_NAME_VOTER_ID;
import static com.saartak.el.dynamicui.constants.ParametersConstant.TAG_NAME_KYC_TYPE_AADHAAR;
import static com.saartak.el.dynamicui.constants.ParametersConstant.TAG_NAME_KYC_TYPE_ADDRESS_ELECTRICITY_BILL;
import static com.saartak.el.dynamicui.constants.ParametersConstant.TAG_NAME_KYC_TYPE_ADDRESS_GST;
import static com.saartak.el.dynamicui.constants.ParametersConstant.TAG_NAME_KYC_TYPE_ADDRESS_JOB_CARD_NREGA;
import static com.saartak.el.dynamicui.constants.ParametersConstant.TAG_NAME_KYC_TYPE_ADDRESS_SHOP_ESTABLISHMENT_ACT;
import static com.saartak.el.dynamicui.constants.ParametersConstant.TAG_NAME_KYC_TYPE_ADDRESS_UTILITY_BILL;
import static com.saartak.el.dynamicui.constants.ParametersConstant.TAG_NAME_KYC_TYPE_AUTHORIZED_SIGNATURE_PROOF;
import static com.saartak.el.dynamicui.constants.ParametersConstant.TAG_NAME_KYC_TYPE_BANKPASSBOOK;
import static com.saartak.el.dynamicui.constants.ParametersConstant.TAG_NAME_KYC_TYPE_BANK_STATEMENT;
import static com.saartak.el.dynamicui.constants.ParametersConstant.TAG_NAME_KYC_TYPE_BANK_STATEMENT_WITH_PASSBOOK;
import static com.saartak.el.dynamicui.constants.ParametersConstant.TAG_NAME_KYC_TYPE_DEEMED_OVD;
import static com.saartak.el.dynamicui.constants.ParametersConstant.TAG_NAME_KYC_TYPE_DRIVINGLICENSE;
import static com.saartak.el.dynamicui.constants.ParametersConstant.TAG_NAME_KYC_TYPE_JOBCARD;
import static com.saartak.el.dynamicui.constants.ParametersConstant.TAG_NAME_KYC_TYPE_MOA_AOA;
import static com.saartak.el.dynamicui.constants.ParametersConstant.TAG_NAME_KYC_TYPE_NREGAJOBCARD;
import static com.saartak.el.dynamicui.constants.ParametersConstant.TAG_NAME_KYC_TYPE_PANCARD;
import static com.saartak.el.dynamicui.constants.ParametersConstant.TAG_NAME_KYC_TYPE_PASSPORT;
import static com.saartak.el.dynamicui.constants.ParametersConstant.TAG_NAME_KYC_TYPE_RATION_CARD;
import static com.saartak.el.dynamicui.constants.ParametersConstant.TAG_NAME_KYC_TYPE_RBL_REFERENCE_ID;
import static com.saartak.el.dynamicui.constants.ParametersConstant.TAG_NAME_KYC_TYPE_UIDAI_ELIGIBLE_DOCUMENTS;
import static com.saartak.el.dynamicui.constants.ParametersConstant.TAG_NAME_KYC_TYPE_UTILITY_BILLS;
import static com.saartak.el.dynamicui.constants.ParametersConstant.TAG_NAME_KYC_TYPE_VID;
import static com.saartak.el.dynamicui.constants.ParametersConstant.TAG_NAME_KYC_TYPE_VOTERID;
import static com.saartak.el.dynamicui.constants.ParametersConstant.VOTER_ID_LENGTH_JLG;

public class DataTypeInfo {
    @Expose
    private String length;
    @Expose
    private String hint;
    @Expose
    private String inputType;
    @Expose
    private int dataEntryType;
    @Expose
    private String hintTag;

    public int getDataEntryType() {
        return dataEntryType;
    }

    public void setDataEntryType(int dataEntryType) {
        this.dataEntryType = dataEntryType;
    }

    public String getHintTag() {
        return hintTag;
    }
    public void setHintTag(String hintTag) {
        this.hintTag = hintTag;
    }

    public String getInputType() {
        return inputType;
    }

    public void setInputType(String inputType) {
        this.inputType = inputType;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }
    String[] InputArray = {
            "Aadhaar",
            "Voter-ID",
            "PAN Card",
            "MOA & AOA",
            "Driving License",
            "Passport",
            "Bank Passbook with Photograph",
            "Job Card",
            "VID",
            "NREGA Job Card",
            "Ration Card",
            "Utility Bills",
            "Bank Statement with Passbook",
            "RBL Reference ID",
            "Authorized Signature Proof",
            "Deemed OVD",
            "UIDAI Eligible Documents",
            "Job Card issued by NREGA",
            "Shop Establishment Act",
            "GST",
            "Electricity Bill",
            "Bank Statement",
            "Utility Bill",
    };
    String[] HintTagArray = {
            TAG_NAME_KYC_TYPE_AADHAAR,
            TAG_NAME_KYC_TYPE_VOTERID,
            TAG_NAME_KYC_TYPE_PANCARD,
            TAG_NAME_KYC_TYPE_MOA_AOA,
            TAG_NAME_KYC_TYPE_DRIVINGLICENSE,
            TAG_NAME_KYC_TYPE_PASSPORT,
            TAG_NAME_KYC_TYPE_BANKPASSBOOK,
            TAG_NAME_KYC_TYPE_JOBCARD,
            TAG_NAME_KYC_TYPE_VID,
            TAG_NAME_KYC_TYPE_NREGAJOBCARD,
            TAG_NAME_KYC_TYPE_RATION_CARD,
            TAG_NAME_KYC_TYPE_UTILITY_BILLS,
            TAG_NAME_KYC_TYPE_BANK_STATEMENT_WITH_PASSBOOK,
            TAG_NAME_KYC_TYPE_RBL_REFERENCE_ID,
            TAG_NAME_KYC_TYPE_AUTHORIZED_SIGNATURE_PROOF,
            TAG_NAME_KYC_TYPE_DEEMED_OVD,
            TAG_NAME_KYC_TYPE_UIDAI_ELIGIBLE_DOCUMENTS,
            TAG_NAME_KYC_TYPE_ADDRESS_JOB_CARD_NREGA,
            TAG_NAME_KYC_TYPE_ADDRESS_SHOP_ESTABLISHMENT_ACT,
            TAG_NAME_KYC_TYPE_ADDRESS_GST,
            TAG_NAME_KYC_TYPE_ADDRESS_ELECTRICITY_BILL,
            TAG_NAME_KYC_TYPE_BANK_STATEMENT,
            TAG_NAME_KYC_TYPE_ADDRESS_UTILITY_BILL
    };
    String[] HintArray = {
            "Enter Aadhaar",
            "Enter Voter-ID",
            "Enter PAN Card",
            "Enter MOA & AOA",
            "Enter Driving License",
            "Enter Passport",
            "Enter Bank Passbook with Photograph",
            "Enter Job Card",
            "Enter VID",
            "Enter NREGA Job Card",
            "Enter Ration Card",
            "Enter Utility Bills Id",
            "Enter Bank Statement With Passbook",
            "Enter RBL Reference ID",
            "Enter Authorized Signature Proof",
            "Enter Deemed OVD",
            "Enter UIDAI Eligible Documents",
            "Enter Job Card issued by NREGA",
            "Enter Shop Establishment Act",
            "Enter GST",
            "Enter Electricity Bill",
            "Enter Bank Statement",
            "Enter Utility Bill"
    };
    // TODO: 09-04-2019 needs to check proper length
    String[] LengthArray = {
            "12",
            "20",
            "10",
            "46",
            "16",
            "8",
            "20",
            "20",
            "16",
            "20",
            "25",
            "20",
            "20",
            "20",
            "20",
            "20",
            "20",
            "40",
            "40",
            "40",
            "40",
            "40",
            "40"
    };

    String[] InputTypeArray = {
            DATATYPE_INT,
            DATATYPE_STRING,
            DATATYPE_STRING,
            DATATYPE_STRING,
            DATATYPE_STRING,
            DATATYPE_STRING,
            DATATYPE_STRING,
            DATATYPE_STRING,
            DATATYPE_INT,
            DATATYPE_STRING,
            DATATYPE_STRING,
            DATATYPE_STRING,
            DATATYPE_STRING,
            DATATYPE_STRING,
            DATATYPE_STRING,
            DATATYPE_STRING,
            DATATYPE_STRING,
            DATATYPE_STRING,
            DATATYPE_STRING,
            DATATYPE_STRING,
            DATATYPE_STRING,
            DATATYPE_STRING,
            DATATYPE_STRING
    };
    int[] DataEntryTypeArray = {
            DATATYPE_NUMERIC,
            DATATYPE_ALPHA_NUMERIC,
            DATATYPE_ALPHA_NUMERIC,
            DATATYPE_ALPHA_NUMERIC,
            DATATYPE_ALPHA_NUMERIC,
            DATATYPE_ALPHA_NUMERIC,
            DATATYPE_ALPHA_NUMERIC,
            DATATYPE_ALPHA_NUMERIC,
            DATATYPE_NUMERIC,
            DATATYPE_ALPHA_NUMERIC,
            DATATYPE_ALPHA_NUMERIC,
            DATATYPE_ALPHA_NUMERIC,
            DATATYPE_ALPHA_NUMERIC,
            DATATYPE_ALPHA_NUMERIC,
            DATATYPE_ALPHA_NUMERIC,
            DATATYPE_ALPHA_NUMERIC,
            DATATYPE_ALPHA_NUMERIC,
            DATATYPE_ALPHA_NUMERIC,
            DATATYPE_ALPHA_NUMERIC,
            DATATYPE_ALPHA_NUMERIC,
            DATATYPE_ALPHA_NUMERIC,
            DATATYPE_ALPHA_NUMERIC,
            DATATYPE_ALPHA_NUMERIC
    };

    public DataTypeInfo(String selectedItem, DynamicUITable dynamicUITable) {
        for (int i = 0; i < InputArray.length; i++) {
            String kycType = InputArray[i];
            if (kycType.equalsIgnoreCase(selectedItem)) {
                if(dynamicUITable.getLoanType().equalsIgnoreCase(LOAN_NAME_JLG) && selectedItem.contains(SPINNER_ITEM_FIELD_NAME_VOTER_ID)){
                    setLength(VOTER_ID_LENGTH_JLG);
                }else {
                    setLength(LengthArray[i]);
                }
                setHint(HintArray[i]);
                setInputType(InputTypeArray[i]);
                setDataEntryType(DataEntryTypeArray[i]);
                setHintTag(HintTagArray[i]);
                break;
            } else {
                setLength("25");
                setHint("Please Enter KYC ID");
                setInputType(DATATYPE_STRING);
                setDataEntryType(DATATYPE_ALPHA_NUMERIC);
                setHintTag(dynamicUITable.getFieldTag());
            }
        }
    }
}