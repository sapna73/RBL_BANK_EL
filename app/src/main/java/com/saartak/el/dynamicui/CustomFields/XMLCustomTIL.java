package com.saartak.el.dynamicui.CustomFields;

import android.content.Context;
import android.content.res.Resources;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.DigitsKeyListener;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.View;

import com.bfil.uilibrary.helpers.AppConstants;
import com.bfil.uilibrary.helpers.AppHelper;
import com.bfil.uilibrary.helpers.Verhoeff;
import com.bfil.uilibrary.widgets.textInputLayout.CustomTextInputLayout;
import com.bfil.uilibrary.widgets.textInputLayout.LengthTextWatcher;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.saartak.el.R;
import com.saartak.el.constants.AppConstant;
import com.saartak.el.database.entity.DynamicUITable;

import java.util.List;

import static com.saartak.el.constants.AppConstant.LOAN_NAME_AHL;
import static com.saartak.el.constants.AppConstant.LOAN_NAME_INDIVIDUAL;
import static com.saartak.el.constants.AppConstant.LOAN_NAME_JLG;
import static com.saartak.el.constants.AppConstant.LOAN_NAME_MSME;
import static com.saartak.el.constants.AppConstant.LOAN_NAME_PHL;
import static com.saartak.el.constants.AppConstant.LOAN_NAME_TWL;
import static com.saartak.el.constants.AppConstant.LOAN_NAME_EL;
import static com.saartak.el.constants.AppConstant.SCREEN_N0_HOUSE_EXPENSES;
import static com.saartak.el.constants.AppConstant.SCREEN_N0_HOUSE_EXPENSES_MSME;
import static com.saartak.el.constants.AppConstant.SCREEN_N0_INVENTORY_BUSINESS_ASSETS_MSME;
import static com.saartak.el.constants.AppConstant.SCREEN_N0_MANUFACTURING_OPERATING_EXPENSE;
import static com.saartak.el.constants.AppConstant.SCREEN_N0_OPERATING_EXPENSE_MSME;
import static com.saartak.el.constants.AppConstant.SCREEN_N0_PRODUCT;
import static com.saartak.el.constants.AppConstant.SCREEN_N0_PRODUCT_ESTIMATE_DETAIL_MSME;
import static com.saartak.el.constants.AppConstant.SCREEN_N0_PRODUCT_RAW_MATERIAL_MSME;
import static com.saartak.el.constants.AppConstant.SCREEN_N0_SERVICE_ESTIMATE_DETAIL_MSME;
import static com.saartak.el.constants.AppConstant.SCREEN_N0_SERVICE_RAW_MATERIAL_MSME;
import static com.saartak.el.constants.AppConstant.SCREEN_NAME_APPLICANT_TWO_WHEELER_DETAIL;
import static com.saartak.el.constants.AppConstant.SCREEN_NAME_COAPPLICANT_PERSONAL_DETAIL;
import static com.saartak.el.constants.AppConstant.SCREEN_NAME_COLD_CALLING;
import static com.saartak.el.constants.AppConstant.SCREEN_NAME_GST_MSME;
import static com.saartak.el.constants.AppConstant.SCREEN_NAME_HOUSE_EXPENSES;
import static com.saartak.el.constants.AppConstant.SCREEN_NAME_LEAD;
import static com.saartak.el.constants.AppConstant.SCREEN_NAME_NOMINEE_DETAIL;
import static com.saartak.el.constants.AppConstant.SCREEN_NAME_PERSONAL_DETAIL;
import static com.saartak.el.constants.AppConstant.SCREEN_NAME_PURCHASE_BILLS_MSME;
import static com.saartak.el.constants.AppConstant.SCREEN_NAME_SALES_TOOL;
import static com.saartak.el.constants.AppConstant.SCREEN_NO_APPLICANT_LOAN_PROPOSAL_AHL;
import static com.saartak.el.constants.AppConstant.SCREEN_NO_APPLICANT_LOAN_PROPOSAL_MSME;
import static com.saartak.el.constants.AppConstant.SCREEN_NO_APPLICANT_LOAN_PROPOSAL_EL;
import static com.saartak.el.constants.AppConstant.getObjectByTAG;
import static com.saartak.el.dynamicui.constants.ParametersConstant.*;

public class XMLCustomTIL extends CustomTextInputLayout {
    Context context;
    AppHelper appHelper;
    DynamicUITable dynamicUITable;
    List<DynamicUITable> dynamicUITableList;
    TextInputListener textInputListener;

    public XMLCustomTIL(Context context, DynamicUITable viewParameters, AppHelper appHelp, List<DynamicUITable> dynamicUITableList, TextInputListener textInputListener) {
        super(context);
        this.context = context;
        appHelper = appHelp;
        this.dynamicUITable = viewParameters;
        this.dynamicUITableList = dynamicUITableList;
        this.textInputListener = textInputListener;

        try {

            LayoutParams lLayout = new LayoutParams(
                    LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
            lLayout.setMargins(convertDpToPixel(0),
                    convertDpToPixel(4),
                    0, 0
            );
            lLayout.weight = 1;
            setLayoutParams(lLayout);
            setPadding(5, 0, 0, 0);
            setBoxBackgroundMode(TextInputLayout.BOX_BACKGROUND_OUTLINE);
            setBoxCornerRadii(5, 5, 5, 5);
            ContextThemeWrapper styleContext = new ContextThemeWrapper(context, R.style.CustomEditText);
            final TextInputEditText editText = new TextInputEditText(styleContext);
            editText.setLayoutParams(lLayout);
            editText.setTextColor(context.getResources().getColor(R.color.black));

            int id = View.generateViewId();
            editText.setId(id);
            editText.setLongClickable(false);

            if (viewParameters != null && viewParameters.getFieldTag().equalsIgnoreCase(TAG_NAME_LEAD_GENERATION_DATE)) {
                try {
                    String currentDate = appHelper.getCurrentDate(AppConstant.DATE_TIME_FORMAT);
                    if (!TextUtils.isEmpty(currentDate)) {
                        viewParameters.setValue(currentDate);
                    } else {
                        viewParameters.setEditable(true);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

            if (viewParameters != null && TextUtils.isEmpty(viewParameters.getValue())) {
                if (!TextUtils.isEmpty(viewParameters.getDefaultValue())) {
                    viewParameters.setValue(viewParameters.getDefaultValue());
                }
            }

            if (viewParameters != null && !viewParameters.isEditable()) {
                editText.setEnabled(false);
            }
            setLongClickable(false);

            String length = viewParameters.getLength();
            if (!TextUtils.isEmpty(length)) {
                editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(Integer.parseInt(length))});
            }
            if (!TextUtils.isEmpty(viewParameters.getIsRequired()) && viewParameters.getIsRequired().equalsIgnoreCase(VIEW_REQUIRED)) {
                setHint(viewParameters.getHint(), true); // TODO: 09-04-2019 need to change
                if (TextUtils.isEmpty(viewParameters.getHint())) {
                    setHint(viewParameters.getFieldName(), true); // TODO: 09-04-2019 need to change
                }
            } else {
                setHint(viewParameters.getHint(), false); // TODO: 09-04-2019 need to change
                if (TextUtils.isEmpty(viewParameters.getHint())) {
                    setHint(viewParameters.getFieldName(), false); // TODO: 09-04-2019 need to change
                }
            }
            /* SETTING INPUT TYPE */
            setInputType(editText, viewParameters);
            /* SETTING INPUT TEXT */
            setInputText(editText, viewParameters);

            // TODO: aadhaar mask ----------------
            if (viewParameters != null){
                if (!TextUtils.isEmpty(viewParameters.getFieldName())&&!TextUtils.isEmpty(viewParameters.getFieldTag())) {
                    if (viewParameters.getFieldName().equalsIgnoreCase(TAG_NAME_KYC_ID) && viewParameters.getFieldTag().equalsIgnoreCase(TAG_NAME_KYC_TYPE_AADHAAR)
                            || viewParameters.getFieldName().equalsIgnoreCase(TAG_NAME_RE_ENTER_KYC_ID)) {
                        editText.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_PASSWORD);
                        setPasswordVisibilityToggleEnabled(true);
                    }
                }
            }
            // TODO: Mobile number mask for AHL Premium Cold call
            if (viewParameters != null && viewParameters.getLoanType().equalsIgnoreCase(LOAN_NAME_AHL)
                    && viewParameters.getScreenName().equalsIgnoreCase(SCREEN_NAME_COLD_CALLING)){
                if (!TextUtils.isEmpty(viewParameters.getFieldTag())) {
                    if (viewParameters.getFieldTag().equalsIgnoreCase(TAG_NAME_MOBILE_NO_IN_COLD_CALLING)) {
                        if (viewParameters.getOptional() !=null && viewParameters.getOptional().equalsIgnoreCase(TAG_NAME_PREMIUM_COLD_CALL)) {
                            editText.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_PASSWORD);
//                        setPasswordVisibilityToggleEnabled(true);
                        }
                    }
                }
            }
            // TODO: Mobile number mask for PHL Premium Cold call
            if (viewParameters != null && viewParameters.getLoanType().equalsIgnoreCase(LOAN_NAME_PHL)
                    && viewParameters.getScreenName().equalsIgnoreCase(SCREEN_NAME_COLD_CALLING)){
                if (!TextUtils.isEmpty(viewParameters.getFieldTag())) {
                    if (viewParameters.getFieldTag().equalsIgnoreCase(TAG_NAME_MOBILE_NO_IN_COLD_CALLING)) {
                        if (viewParameters.getOptional() !=null && viewParameters.getOptional().equalsIgnoreCase(TAG_NAME_PREMIUM_COLD_CALL)) {
                            editText.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_PASSWORD);
//                        setPasswordVisibilityToggleEnabled(true);
                        }
                    }
                }
            }
            if (viewParameters != null && viewParameters.getLoanType().equalsIgnoreCase(LOAN_NAME_EL)
                    && viewParameters.getScreenName().equalsIgnoreCase(SCREEN_NAME_COLD_CALLING)){
                if (!TextUtils.isEmpty(viewParameters.getFieldTag())) {
                    if (viewParameters.getFieldTag().equalsIgnoreCase(TAG_NAME_MOBILE_NO_IN_COLD_CALLING)) {
                        if (viewParameters.getOptional() !=null && viewParameters.getOptional().equalsIgnoreCase(TAG_NAME_PREMIUM_COLD_CALL)) {
                            editText.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_PASSWORD);
//                        setPasswordVisibilityToggleEnabled(true);
                        }
                    }
                }
            }
            if (viewParameters != null && viewParameters.getLoanType().equalsIgnoreCase(LOAN_NAME_TWL)
                    && viewParameters.getScreenName().equalsIgnoreCase(SCREEN_NAME_COLD_CALLING)){
                if (!TextUtils.isEmpty(viewParameters.getFieldTag())) {
                    if (viewParameters.getFieldTag().equalsIgnoreCase(TAG_NAME_MOBILE_NO_IN_COLD_CALLING)) {
                        if (viewParameters.getOptional() !=null && viewParameters.getOptional().equalsIgnoreCase(TAG_NAME_PREMIUM_COLD_CALL)) {
                            editText.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_PASSWORD);
//                        setPasswordVisibilityToggleEnabled(true);
                        }
                    }
                }
            }
            if (viewParameters != null && viewParameters.getScreenName().equalsIgnoreCase(SCREEN_NAME_COLD_CALLING)){
                if (!TextUtils.isEmpty(viewParameters.getFieldTag())) {
                    if (viewParameters.getFieldTag().equalsIgnoreCase(TAG_NAME_MOBILE_NO_IN_COLD_CALLING)) {
                        if (viewParameters.getOptional() !=null && viewParameters.getOptional().equalsIgnoreCase(TAG_NAME_PREMIUM_COLD_CALL)) {
                            editText.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_PASSWORD);
//                        setPasswordVisibilityToggleEnabled(true);
                        }
                    }
                }
            }
            if (viewParameters != null && viewParameters.getScreenName().equalsIgnoreCase(SCREEN_NAME_PERSONAL_DETAIL) || viewParameters.getScreenName().equalsIgnoreCase(SCREEN_NAME_COAPPLICANT_PERSONAL_DETAIL)|| viewParameters.getScreenName().equalsIgnoreCase(SCREEN_NAME_LEAD) ){
                if (!TextUtils.isEmpty(viewParameters.getFieldTag())) {
                    if (viewParameters.getFieldTag().equalsIgnoreCase(TAG_NAME_E_MAIL_ID)) {{
                            editText.setInputType(InputType.TYPE_TEXT_FLAG_AUTO_COMPLETE);
                        }
                    }
                }
            }

            if (viewParameters != null && viewParameters.getScreenName().equalsIgnoreCase(SCREEN_NAME_PERSONAL_DETAIL) || viewParameters.getScreenName().equalsIgnoreCase(SCREEN_NAME_COAPPLICANT_PERSONAL_DETAIL) ){
                if (!TextUtils.isEmpty(viewParameters.getFieldTag())) {
                    if (viewParameters.getFieldTag().equalsIgnoreCase(TAG_NAME_COUNTRY_NAME)) {
                        {
                            editText.setText("INDIA");
                        }
                    }
                }
            }
//             TODO: Mobile number mask for Lead - Premium Cold call
            if (viewParameters != null && viewParameters.getLoanType().equalsIgnoreCase(LOAN_NAME_AHL)
                    && viewParameters.getScreenName().equalsIgnoreCase(SCREEN_NAME_LEAD)){
                if (!TextUtils.isEmpty(viewParameters.getFieldTag())) {
                    if (viewParameters.getFieldTag().equalsIgnoreCase(TAG_NAME_MOBILE_NUMBER)) {
                        if (viewParameters.getOptional() !=null && viewParameters.getOptional().equalsIgnoreCase(TAG_NAME_PREMIUM_COLD_CALL)) {
                            editText.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_PASSWORD);
//                        setPasswordVisibilityToggleEnabled(true);
                        }
                    }
                }
            }

            //             TODO: Mobile number mask for Lead - Premium Cold call
            if (viewParameters != null && viewParameters.getLoanType().equalsIgnoreCase(LOAN_NAME_PHL)
                    && viewParameters.getScreenName().equalsIgnoreCase(SCREEN_NAME_LEAD)){
                if (!TextUtils.isEmpty(viewParameters.getFieldTag())) {
                    if (viewParameters.getFieldTag().equalsIgnoreCase(TAG_NAME_MOBILE_NUMBER)) {
                        if (viewParameters.getOptional() != null && viewParameters.getOptional().equalsIgnoreCase(TAG_NAME_PREMIUM_COLD_CALL)) {
                            editText.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_PASSWORD);
//                        setPasswordVisibilityToggleEnabled(true);
                        }
                    }
                }
            }
            if (viewParameters != null && viewParameters.getLoanType().equalsIgnoreCase(LOAN_NAME_EL)
                    && viewParameters.getScreenName().equalsIgnoreCase(SCREEN_NAME_LEAD)){
                if (!TextUtils.isEmpty(viewParameters.getFieldTag())) {
                    if (viewParameters.getFieldTag().equalsIgnoreCase(TAG_NAME_MOBILE_NUMBER)) {
                        if (viewParameters.getOptional() !=null && viewParameters.getOptional().equalsIgnoreCase(TAG_NAME_PREMIUM_COLD_CALL)) {
                            editText.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_PASSWORD);
//                        setPasswordVisibilityToggleEnabled(true);
                        }
                    }
                }
            } if (viewParameters != null && viewParameters.getLoanType().equalsIgnoreCase(LOAN_NAME_TWL)
                    && viewParameters.getScreenName().equalsIgnoreCase(SCREEN_NAME_LEAD)){
                if (!TextUtils.isEmpty(viewParameters.getFieldTag())) {
                    if (viewParameters.getFieldTag().equalsIgnoreCase(TAG_NAME_MOBILE_NUMBER)) {
                        if (viewParameters.getOptional() !=null && viewParameters.getOptional().equalsIgnoreCase(TAG_NAME_PREMIUM_COLD_CALL)) {
                            editText.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_PASSWORD);
//                        setPasswordVisibilityToggleEnabled(true);
                        }
                    }
                }
            }
            if (viewParameters != null && viewParameters.getScreenName().equalsIgnoreCase(SCREEN_NAME_LEAD)){
                if (!TextUtils.isEmpty(viewParameters.getFieldTag())) {
                    if (viewParameters.getFieldTag().equalsIgnoreCase(TAG_NAME_MOBILE_NUMBER)) {
                        if (viewParameters.getOptional() !=null && viewParameters.getOptional().equalsIgnoreCase(TAG_NAME_PREMIUM_COLD_CALL)) {
                            editText.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_PASSWORD);
//                        setPasswordVisibilityToggleEnabled(true);
                        }
                    }
                }
            }
            // TODO: newly added condition
            if (!viewParameters.isValid()) {
                if (!TextUtils.isEmpty(viewParameters.getErrorMessage())) {
                    setErrorMsg(viewParameters.getErrorMessage());
                    setHasValidInput(viewParameters.isValid());
                }
            }/*else{
                setErrorMsg("");
                setHasValidInput(viewParameters.isValid());
            }*/


            editText.addTextChangedListener(new CustomTextWatcher(context, this) {
                @Override
                protected void onValidated(boolean b, CustomTextInputLayout customTextInputLayout, String s, Object tag) {
                    viewParameters.setValue(s);// TODO: Adding values whatever entered
                    if (dynamicUITable != null && dynamicUITable.getScreenName().equalsIgnoreCase(SCREEN_NAME_APPLICANT_TWO_WHEELER_DETAIL)) {
                        textInputListener.callBackForEditTextValue(customTextInputLayout, s, tag);
                    }
                }
            });


                /*if(viewParameters.isOnItemClick()) {
                    editText.requestFocus();
                    InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
                }*/



            /* Adding EditText in View */
            this.addView(editText);

            // TODO: Setting enabled false for view purpose on screen not for edit
            if(viewParameters !=null && viewParameters.isSync()){
                setEnabled(false);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setInputText(TextInputEditText editText, DynamicUITable viewParameters) {
        try {
//            String inputText="";
            // TODO: No need to check this condition
//            if (!TextUtils.isEmpty(viewParameters.getIsRequired()) && viewParameters.getIsRequired().equalsIgnoreCase(VIEW_REQUIRED)){
            // TODO: 03-04-2019 need to check empty condition
            setTag(viewParameters);
            editText.setTag(viewParameters.getFieldName());
            if (viewParameters.getValue() != null) {
                editText.setText(viewParameters.getValue());
            }
            if (!viewParameters.isEditable()) {
                editText.setEnabled(viewParameters.isEditable());
            }
//            }
//            else {
//                // TODO: Need to check this condition
//                setTag(null);
//                editText.setTag(null);
//            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void setInputType(TextInputEditText editText, DynamicUITable viewParameters) {
        try {
            if (!TextUtils.isEmpty(viewParameters.getDataType()) && viewParameters.getDataType().equalsIgnoreCase(DATATYPE_STRING)) {
                if (!TextUtils.isEmpty(viewParameters.getDataType()) && viewParameters.getDataEntryType() == DATATYPE_ALPHA) {
                    // TODO: ALL TEXT FIELD IN CAPITALS & ONLY ALPHA CHARACTERS
//                    editText.setInputType(InputType.TYPE_TEXT_VARIATION_PERSON_NAME + InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS);

                    editText.setKeyListener(DigitsKeyListener.getInstance("abcdefghijklmnopqrstuvwxyz ABCDEFGHIJKLMNOPQRSTUVWXYZ "));
                    editText.setRawInputType(InputType.TYPE_TEXT_VARIATION_PERSON_NAME + InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS);

                } else if (!TextUtils.isEmpty(viewParameters.getDataType()) &&
                        viewParameters.getDataEntryType() == DATATYPE_ALPHA_NUMERIC) {
                    // TODO: ALL TEXT FIELD IN CAPITALS & ALPHA NUMERIC
//                    editText.setInputType(InputType.TYPE_TEXT_VARIATION_PERSON_NAME + InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS);

                    editText.setKeyListener(DigitsKeyListener.getInstance("abcdefghijklmnopqrstuvwxyz ABCDEFGHIJKLMNOPQRSTUVWXYZ 0123456789"));
                    editText.setRawInputType(InputType.TYPE_TEXT_VARIATION_PERSON_NAME + InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS);

                } else if (!TextUtils.isEmpty(viewParameters.getDataType()) &&
                        viewParameters.getDataEntryType() == DATATYPE_FLOAT) {
                    // TODO: ALL TEXT FIELD IN CAPITALS & ONLY FLOAT AND DOUBLE
                    editText.setKeyListener(DigitsKeyListener.getInstance("0123456789."));
                    editText.setRawInputType(InputType.TYPE_TEXT_VARIATION_PERSON_NAME + InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS);

                }else if (!TextUtils.isEmpty(viewParameters.getDataType()) &&
                        viewParameters.getDataEntryType() == DATATYPE_ALL_CAPS) {
                    // TODO: ALL TEXT FIELD IN CAPITALS & ONLY FLOAT AND DOUBLE
                    editText.setKeyListener(DigitsKeyListener.getInstance(" ABCDEFGHIJKLMNOPQRSTUVWXYZ "));
                    editText.setRawInputType(InputType.TYPE_TEXT_VARIATION_PERSON_NAME + InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS);

                } else if (!TextUtils.isEmpty(viewParameters.getDataType()) && viewParameters.getDataEntryType() == DATATYPE_ALL) {
                    // TODO: ALL CHARACTERS
                    editText.setInputType(InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS);
                }
            } else if (!TextUtils.isEmpty(viewParameters.getDataType()) && viewParameters.getDataType().equalsIgnoreCase(DATATYPE_INT)
                    || viewParameters.getDataEntryType() == DATATYPE_NUMERIC) {
                // TODO: ONLY NUMERIC
                editText.setInputType(InputType.TYPE_CLASS_NUMBER);
            } else if (!TextUtils.isEmpty(viewParameters.getDataType()) && viewParameters.getDataEntryType() == DATATYPE_ALL) {
                // TODO: ALL CHARACTERS
                editText.setInputType(InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public XMLCustomTIL(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public XMLCustomTIL(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public abstract class CustomTextWatcher implements TextWatcher {

        private final String TAG = LengthTextWatcher.class.getCanonicalName();

        private CustomTextInputLayout mCustomTextInputLayout;
        private String currentText = "";
        private CharSequence hint = "";
        private CharSequence hintWithAsterisk = "";

        public CustomTextWatcher(final Context context, CustomTextInputLayout customTextInputLayout) {
            try {
                this.mCustomTextInputLayout = customTextInputLayout;
                if (mCustomTextInputLayout != null && mCustomTextInputLayout.getEditText().getText() != null) {
                    currentText = mCustomTextInputLayout.getEditText().getText().toString().trim();
                }
                hintWithAsterisk = mCustomTextInputLayout.getHint();
                String str = hintWithAsterisk.toString();
                if (mCustomTextInputLayout.isMandatory()) {
                    hint = str.subSequence(0, str.length() - 2);
                } else {
                    hint = str;
                }
                if (TextUtils.isEmpty(currentText)) {
                    mCustomTextInputLayout.setHint(null);
                    mCustomTextInputLayout.getEditText().setHint(hintWithAsterisk);
                }
                mCustomTextInputLayout.getEditText().setOnFocusChangeListener(new OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {
                        try {
                            if (!hasFocus) {
                                if (TextUtils.isEmpty(currentText)) {
                                    mCustomTextInputLayout.setHint(null);
                                    mCustomTextInputLayout.getEditText().setHint(hintWithAsterisk);
                                } else {
                                    mCustomTextInputLayout.setHint(hint);
                                    mCustomTextInputLayout.getEditText().setHint(null);
                                }
                            } else {
                                mCustomTextInputLayout.setHint(hint);
                                mCustomTextInputLayout.getEditText().setHint(null);
                            }
                        } catch (Exception e) {
//                            e.printStackTrace();
                        }
                    }
                });
            } catch (Exception e) {
//                e.printStackTrace();
            }
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

     /*   @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            try {
                currentText = charSequence.toString();
                if (mCustomTextInputLayout.getHint() == null){
                    if (currentText.length() > 0){
                        mCustomTextInputLayout.setHint(hint);
                    } else {
                        mCustomTextInputLayout.setHint(null);
                    }
                } else {
                    mCustomTextInputLayout.getEditText().setHint(null);
                }
                mCustomTextInputLayout.setErrorEnabled(false);
                mCustomTextInputLayout.setError("");
                boolean moveToNext = false;
                if (!TextUtils.isEmpty(currentText) && currentText.length() > 0){
                    moveToNext = true;
                }
                onValidated(moveToNext, mCustomTextInputLayout,
                        currentText, mCustomTextInputLayout.getTag());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }*/

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            try {
                currentText = s.toString();
                if (mCustomTextInputLayout.getHint() == null) {
                    if (currentText.length() > 0) {
                        mCustomTextInputLayout.setHint(hint);
                    } else {
                        mCustomTextInputLayout.setHint(null);
                    }
                } else {
                    mCustomTextInputLayout.getEditText().setHint(null);
                }
                mCustomTextInputLayout.setErrorEnabled(false);
                mCustomTextInputLayout.setError("");
                boolean moveToNext = false;
                String tag = "";
                DynamicUITable viewParameters = null;
                String error = "";
                int iLength = -1;
                if (mCustomTextInputLayout.getTag() != null) {
                    viewParameters = (DynamicUITable) mCustomTextInputLayout.getTag();
                    tag = viewParameters.getFieldTag();
                    Log.i(TAG, "TAG VALUE ---> " + tag);
//                    iLength = Integer.parseInt(context.getString(getIdentifier(tag + "_LENGTH")));
                    iLength = Integer.parseInt(viewParameters.getMinLength());
                }


                if (!TextUtils.isEmpty(tag)) {
                    if (s.length() > 0) {
                        if (s.length() < iLength) {
//                            error = viewParameters.getFieldTag() + " should be at least " + viewParameters.getMinLength() + " digit";
                            if (viewParameters.getScreenID().equalsIgnoreCase(SCREEN_N0_MANUFACTURING_OPERATING_EXPENSE)) {
                                if (s.toString().startsWith("0")) {
                                    mCustomTextInputLayout.getEditText().setText("");
                                    mCustomTextInputLayout.setErrorEnabled(false);
                                    mCustomTextInputLayout.setError("");
                                    // mCustomTextInputLayout.getEditText().setSelection(mCustomTextInputLayout.getEditText().getText().toString().length());
                                } else {
                                    error = viewParameters.getFieldTag() + " should be at least " + viewParameters.getMinLength() + " digit";
                                    viewParameters.setValue(s.toString());
                                    // TODO: Sum of all fields by feature id
                                    textInputListener.callBackForSumOfAllFieldsByFeatureId(viewParameters, TAG_NAME_EXPENSES);
                                }
                            } else if (viewParameters.getScreenName().equalsIgnoreCase(SCREEN_NAME_HOUSE_EXPENSES)) {
                                if (s.toString().startsWith("0")) {
                                    mCustomTextInputLayout.getEditText().setText("");
                                    mCustomTextInputLayout.setErrorEnabled(false);
                                    mCustomTextInputLayout.setError("");
                                    // mCustomTextInputLayout.getEditText().setSelection(mCustomTextInputLayout.getEditText().getText().toString().length());
                                } else {
                                    error = viewParameters.getFieldTag() + " should be at least " + viewParameters.getMinLength() + " digit";
                                    // TODO: Sum of all fields by feature id
                                    textInputListener.callBackForSumOfAllFieldsByFeatureId(viewParameters, TAG_NAME_TOTAL_FAMILY_EXPENSES);
                                }
                            } else {
                                error = viewParameters.getFieldTag() + " should be at least " + viewParameters.getMinLength() + " digit";
                            }
                        } else {
                            if (viewParameters != null && (viewParameters.getDataType().equalsIgnoreCase(DATATYPE_INT)
                                    || viewParameters.getDataEntryType() == DATATYPE_NUMERIC)) {
                                if (s.toString().startsWith("0")) {
                                    // TODO: starts with zero
//                                    error = context.getString(getIdentifier(tag + "_INVALID_ERROR"));
                                    if (tag.equalsIgnoreCase(TAG_NAME_TELEPHONE_NO)) {
                                        if (!appHelper.patternMatch(AppConstants.REGEX_PATTERN_LAND_LINE_NUMBER, s.toString())) {
//                                    error = context.getString(getIdentifier(tag + "_INVALID_ERROR"));
                                            error = viewParameters.getFieldTag() + " invalid";
                                        } else {

                                            moveToNext = true;
                                        }
                                    } else if (tag.equalsIgnoreCase(TAG_NAME_GOOD_DAILY)
                                            || tag.equalsIgnoreCase(TAG_NAME_REGULAR_DAILY)
                                            || tag.equalsIgnoreCase(TAG_NAME_BAD_DAILY)
                                            || tag.equalsIgnoreCase(TAG_NAME_NO_OF_GOOD_DAYS_IN_A_WEEK)
                                            || tag.equalsIgnoreCase(TAG_NAME_NO_OF_REGULAR_DAYS_IN_A_WEEK)
                                            || tag.equalsIgnoreCase(TAG_NAME_NO_OF_BAD_DAYS_IN_A_WEEK)) {
                                        viewParameters.setValue(s.toString());
                                        // TODO: For calculating declared sales weekly ( no of days ==> good + regular + bad <= 7 )
                                        textInputListener.callBackForCalcAvgSales(viewParameters, tag, TAG_NAME_AVERAGE_WEEKLY_SALES, 7);
                                    } else if (tag.equalsIgnoreCase(TAG_NAME_GOOD_WEEKLY)
                                            || tag.equalsIgnoreCase(TAG_NAME_REGULAR_WEEKLY)
                                            || tag.equalsIgnoreCase(TAG_NAME_BAD_WEEKLY)
                                            || tag.equalsIgnoreCase(TAG_NAME_NO_OF_GOOD_WEEKS_IN_A_MONTH)
                                            || tag.equalsIgnoreCase(TAG_NAME_NO_OF_REGULAR_WEEKS_IN_A_MONTH)
                                            || tag.equalsIgnoreCase(TAG_NAME_NO_OF_BAD_WEEKS_IN_A_MONTH)) {
                                        viewParameters.setValue(s.toString());
                                        // TODO: For calculating declared sales monthly ( no of days ==> good + regular + bad <= 4 )
                                        textInputListener.callBackForCalcAvgSales(viewParameters, tag, TAG_NAME_AVERAGE_MONTHLY_SALES, 4);
                                    } else if (tag.equalsIgnoreCase(TAG_NAME_GOOD_YEARLY)
                                            || tag.equalsIgnoreCase(TAG_NAME_REGULAR_YEARLY)
                                            || tag.equalsIgnoreCase(TAG_NAME_BAD_YEARLY)
                                            || tag.equalsIgnoreCase(TAG_NAME_NO_OF_GOOD_MONTHS_IN_AN_YEAR)
                                            || tag.equalsIgnoreCase(TAG_NAME_NO_OF_REGULAR_MONTHS_IN_AN_YEAR)
                                            || tag.equalsIgnoreCase(TAG_NAME_NO_OF_BAD_MONTHS_IN_AN_YEAR)) {
                                        viewParameters.setValue(s.toString());
                                        // TODO: For calculating declared sales yearly ( no of days ==> good + regular + bad <= 12 )
                                        textInputListener.callBackForCalcAvgSales(viewParameters, tag, TAG_NAME_AVERAGE_YEARLY_SALES, 12);
                                    }
                                    else if (tag.equalsIgnoreCase(TAG_NAME_NO_OF_CHILDREN)
                                            || tag.equalsIgnoreCase(TAG_NAME_NO_OF_DEPENDENT_ELDERLY_IN_THE_HOUSE)
                                            || tag.equalsIgnoreCase(TAG_NAME_WORKING_FAMILY_MEMBER)
                                            || tag.equalsIgnoreCase(TAG_NAME_HOUSEHOLD_FAMILY_MEMBERS)
                                            || tag.equalsIgnoreCase(TAG_NAME_LANDLINE_NUMBER)
                                            || tag.equalsIgnoreCase(TAG_NAME_LANDLINE_NUMBER_)
                                            || tag.equalsIgnoreCase(TAG_NAME_BUSINESS_VINTAGE)
                                            || tag.equalsIgnoreCase(TAG_NAME_NO_OF_YEARS_WITH_CURRENT_EMPLOYER)
                                            || tag.equalsIgnoreCase(TAG_NAME_ACCOUNT_NUMBER)
                                            || tag.equalsIgnoreCase(TAG_NAME_TELEPHONE_NUMBER)
                                            || tag.equalsIgnoreCase(TAG_NAME_TELEPHONE_NO)
                                            || tag.equalsIgnoreCase(TAG_NAME_BALANCE)
                                            || tag.equalsIgnoreCase(TAG_NAME_INWARD_BOUNCES_IN_BANKING_HISTORY)
                                            || tag.equalsIgnoreCase(TAG_NAME_OUTWARD_BOUNCES_IN_BANKING_HISTORY)
                                            || tag.equalsIgnoreCase(TAG_NAME_TOTAL_BOUNCES_IN_BANKING_HISTORY)
                                            || tag.equalsIgnoreCase(TAG_NAME_MARGIN_IN_PURCHASE_BILLS)
                                            || tag.equalsIgnoreCase(TAG_NAME_DEPENDENT_FAMILY_MEMBERS)
                                            || tag.equalsIgnoreCase(TAG_NAME_FIRST_INSTALLMENT_DATE_IN_LOAN_APPROVAL_MSME)
                                            || tag.equalsIgnoreCase(TAG_NAME_RE_ENTER_ACCOUNT_NUMBER)) {

                                        // TODO: 28-08-2019 Validate account  number
                                        if (tag.equalsIgnoreCase(TAG_NAME_ACCOUNT_NUMBER)) {

                                            // if (viewParameters.getScreenName().equalsIgnoreCase(SCREEN_NAME_CO_APPLICANT_BANK_DETAILS)) {
                                            viewParameters.setValue(s.toString());
                                            textInputListener.callBackForDeDupeValidation(viewParameters, XMLCustomTIL.this);
                                            // }
                                        }
                                        else if (tag.equalsIgnoreCase(TAG_NAME_RE_ENTER_ACCOUNT_NUMBER)) {
                                            DynamicUITable accountNumber = getObjectByTAG(TAG_NAME_ACCOUNT_NUMBER, dynamicUITableList);
                                            if (accountNumber != null) {
                                                if (!TextUtils.isEmpty(accountNumber.getValue())) {
                                                    double acNum = Double.parseDouble(accountNumber.getValue());
                                                    double reAcc = Double.parseDouble(s.toString());
                                                    if (!(reAcc == acNum)) {
                                                        error = "Account number and re account number should be same";
                                                    }
                                                } else {
                                                    error = "Please enter account number first";
                                                }
                                            }
                                        } else {

                                            moveToNext = true;
                                        }
                                    }
                                    else if (viewParameters.getScreenID().equalsIgnoreCase(SCREEN_N0_MANUFACTURING_OPERATING_EXPENSE)) {
                                        // TODO: remove 0
                                        mCustomTextInputLayout.getEditText().setText(s.toString().substring(1));
                                        mCustomTextInputLayout.getEditText().setSelection(mCustomTextInputLayout.getEditText().getText().toString().length());
                                        if (mCustomTextInputLayout.getEditText().getText().toString().length() < iLength) {
                                            error = viewParameters.getFieldTag() + " should be at least " + viewParameters.getMinLength() + " digit";
                                        }
                                    } else if (viewParameters.getScreenName().equalsIgnoreCase(SCREEN_NAME_HOUSE_EXPENSES)) {
                                        // TODO: remove 0
                                        mCustomTextInputLayout.getEditText().setText(s.toString().substring(1));
                                        mCustomTextInputLayout.getEditText().setSelection(mCustomTextInputLayout.getEditText().getText().toString().length());
                                        if (mCustomTextInputLayout.getEditText().getText().toString().length() < iLength) {
                                            error = viewParameters.getFieldTag() + " should be at least " + viewParameters.getMinLength() + " digit";
                                        }
                                    } else {
                                        error = " Should Not Start With Zero";
                                    }
                                } else {
                                    // TODO: Not starts with zero

                                    if (tag.equalsIgnoreCase("AADHAAR")
                                            || tag.equalsIgnoreCase(TAG_NAME_KYC_TYPE_AADHAAR)
                                            || tag.equalsIgnoreCase(SPINNER_ITEM_FIELD_NAME_GUARANTOR_AADHAAR)
                                            || tag.equalsIgnoreCase(TAG_NAME_KYC_TYPE_RE_ENTER_AADHAAR)) {
                                        if(!TextUtils.isEmpty(s) && s.toString().startsWith("1")){
                                            error= viewParameters.getFieldTag()+" should not starts with 1";

                                        }else {
                                            if (!TextUtils.isEmpty(s) && s.length() < 12 || !Verhoeff.validateVerhoeff(s.toString())) {
//                                    error = context.getString(getIdentifier(tag + "_INVALID_ERROR"));
                                                error = viewParameters.getFieldTag() + " invalid";
                                            } else {
                                                // if (viewParameters.getScreenName().equalsIgnoreCase(SCREEN_NAME_CO_APPLICANT_KYC)) {
                                                viewParameters.setValue(s.toString());
                                                textInputListener.callBackForDeDupeValidation(viewParameters, XMLCustomTIL.this);
//                                            } else if (viewParameters.getScreenName().equalsIgnoreCase(SCREEN_NAME_APPLICANT_KYC)) {
//                                                viewParameters.setValue(s.toString());
//                                                textInputListener.callBackForDeDupeValidation(viewParameters, XMLCustomTIL.this);
//
//                                            }

                                                moveToNext = true;
                                            }
                                        }
                                    }else if (tag.equalsIgnoreCase("VID")) {
                                        if(s.toString().length()!=16) {
                                            error = "Enter Valid 16 Digit VID Number";
                                        }else {
                                            // if (viewParameters.getScreenName().equalsIgnoreCase(SCREEN_NAME_CO_APPLICANT_KYC)) {
                                            viewParameters.setValue(s.toString());
                                            textInputListener.callBackForDeDupeValidation(viewParameters, XMLCustomTIL.this);
                                            // }
                                            moveToNext = true;
                                        }

                                    }
                                    else if (tag.equalsIgnoreCase(TAG_NAME_MOBILE)
                                            || tag.equalsIgnoreCase(TAG_NAME_MOBILE_NO)
                                            || tag.equalsIgnoreCase(TAG_NAME_CONTACT_NO)
                                            || tag.equalsIgnoreCase(TAG_NAME_CONTACT_NO_1)
                                            || tag.equalsIgnoreCase(TAG_NAME_MOBILE_NUMBER)) {
                                        if (!appHelper.patternMatch(AppConstants.REGEX_PATTERN_MOBILE_NUMBER, s.toString())) {
//                                    error = context.getString(getIdentifier(tag + "_INVALID_ERROR"));
                                            error = viewParameters.getFieldTag() + " invalid";
                                        } else {
                                            if (viewParameters.getScreenName().equalsIgnoreCase(SCREEN_NAME_LEAD)
                                                    || viewParameters.getScreenName().equalsIgnoreCase(SCREEN_NAME_COLD_CALLING)|| viewParameters.getScreenName().equalsIgnoreCase(SCREEN_NAME_PERSONAL_DETAIL)|| viewParameters.getScreenName().equalsIgnoreCase(SCREEN_NAME_COAPPLICANT_PERSONAL_DETAIL)) {
                                                viewParameters.setValue(s.toString());
                                                textInputListener.callBackForDeDupeValidation(viewParameters, XMLCustomTIL.this);
                                            }
                                            moveToNext = true;
                                        }
                                    }
                                    else if (tag.equalsIgnoreCase(TAG_NAME_CONTACT_NO_2)) {
                                        DynamicUITable contactNumber1 = getObjectByTAG(TAG_NAME_CONTACT_NO_1, dynamicUITableList);

                                        if (!TextUtils.isEmpty(contactNumber1.getValue())) {

                                            if (!appHelper.patternMatch(AppConstants.REGEX_PATTERN_MOBILE_NUMBER, s.toString())) {
//                                    error = context.getString(getIdentifier(tag + "_INVALID_ERROR"));
                                                error = viewParameters.getFieldTag() + " invalid";

                                            } else if (contactNumber1.getValue().toString().equalsIgnoreCase(s.toString())) {
                                                error = "Both contacts should be different";
                                            } else {
                                                if (viewParameters.getScreenName().equalsIgnoreCase(SCREEN_NAME_LEAD)) {
                                                    viewParameters.setValue(s.toString());
                                                    textInputListener.callBackForDeDupeValidation(viewParameters, XMLCustomTIL.this);
                                                }
                                                moveToNext = true;
                                            }
                                        } else {
                                            error = "Please enter Contact number 1";
                                        }

                                    } else if (tag.equalsIgnoreCase(TAG_NAME_RE_ENTER_CONTACT_NO_2)) {

                                        DynamicUITable contactNumber = getObjectByTAG(TAG_NAME_CONTACT_NO_2, dynamicUITableList);
                                        DynamicUITable contactNumber1 = getObjectByTAG(TAG_NAME_CONTACT_NO_1, dynamicUITableList);
                                        if (!TextUtils.isEmpty(contactNumber1.getValue())) {
                                            if (contactNumber != null) {
                                                if (!TextUtils.isEmpty(contactNumber.getValue())) {
                                                    double conNum = Double.parseDouble(contactNumber.getValue());
                                                    double reConNum = Double.parseDouble(s.toString());
                                                    if (!(contactNumber.getValue().equalsIgnoreCase(s.toString()))) {
                                                        error = "Mobile number and re entered mobile number should be same";
                                                    } else {
                                                        moveToNext = true;
                                                    }
                                                } else {
                                                    error = "Please enter mobile number first";
                                                }
                                            } else {
                                                moveToNext = true;
                                            }
                                        } else {

                                            error = "Please enter Contact number 1";
                                        }

                                    } else if (tag.equalsIgnoreCase(TAG_NAME_RE_ENTER_CONTACT_NO)) {

                                        DynamicUITable contactNumber = getObjectByTAG(TAG_NAME_CONTACT_NO, dynamicUITableList);
                                        if (!TextUtils.isEmpty(contactNumber.getValue())) {

                                            double conNum = Double.parseDouble(contactNumber.getValue());
                                            double reConNum = Double.parseDouble(s.toString());
                                            if (!(contactNumber.getValue().equalsIgnoreCase(s.toString()))) {
                                                error = "Mobile number and re entered mobile number should be same";
                                            } else {
                                                moveToNext = true;
                                            }

                                        } else {

                                            error = "Please enter Contact number first";
                                        }

                                    } else if (tag.equalsIgnoreCase(TAG_NAME_GOOD_DAILY)
                                            || tag.equalsIgnoreCase(TAG_NAME_REGULAR_DAILY)
                                            || tag.equalsIgnoreCase(TAG_NAME_BAD_DAILY)
                                            || tag.equalsIgnoreCase(TAG_NAME_NO_OF_GOOD_DAYS_IN_A_WEEK)
                                            || tag.equalsIgnoreCase(TAG_NAME_NO_OF_REGULAR_DAYS_IN_A_WEEK)
                                            || tag.equalsIgnoreCase(TAG_NAME_NO_OF_BAD_DAYS_IN_A_WEEK)) {
                                        viewParameters.setValue(s.toString());
                                        // TODO: For calculating declared sales weekly ( no of days ==> good + regular + bad <= 7 )
                                        textInputListener.callBackForCalcAvgSales(viewParameters, tag, TAG_NAME_AVERAGE_WEEKLY_SALES, 7);
                                    } // TODO: Prasanna 8/3/2019
                                    else if (tag.equalsIgnoreCase(TAG_NAME_RE_ENTER_CONTACT_NO_1)) {
                                        DynamicUITable contactNumber = getObjectByTAG(TAG_NAME_CONTACT_NO_1, dynamicUITableList);
                                        if (contactNumber != null) {
                                            if (!TextUtils.isEmpty(contactNumber.getValue())) {
                                                double conNum = Double.parseDouble(contactNumber.getValue());
                                                double reConNum = Double.parseDouble(s.toString());
                                                if (!(reConNum == conNum)) {
                                                    error = "Mobile number and re entered mobile number should be same";
                                                } else {
                                                    moveToNext = true;
                                                }
                                            } else {
                                                error = "Please enter mobile number first";
                                            }
                                        } else {
                                            moveToNext = true;
                                        }
                                    } else if (tag.equalsIgnoreCase(TAG_NAME_GOOD_WEEKLY)
                                            || tag.equalsIgnoreCase(TAG_NAME_REGULAR_WEEKLY)
                                            || tag.equalsIgnoreCase(TAG_NAME_BAD_WEEKLY)
                                            || tag.equalsIgnoreCase(TAG_NAME_NO_OF_GOOD_WEEKS_IN_A_MONTH)
                                            || tag.equalsIgnoreCase(TAG_NAME_NO_OF_REGULAR_WEEKS_IN_A_MONTH)
                                            || tag.equalsIgnoreCase(TAG_NAME_NO_OF_BAD_WEEKS_IN_A_MONTH)) {
                                        viewParameters.setValue(s.toString());
                                        // TODO: For calculating declared sales monthly ( no of days ==> good + regular + bad <= 4 )
                                        textInputListener.callBackForCalcAvgSales(viewParameters, tag, TAG_NAME_AVERAGE_MONTHLY_SALES, 4);
                                    } else if (tag.equalsIgnoreCase(TAG_NAME_GOOD_YEARLY)
                                            || tag.equalsIgnoreCase(TAG_NAME_REGULAR_YEARLY)
                                            || tag.equalsIgnoreCase(TAG_NAME_BAD_YEARLY)
                                            || tag.equalsIgnoreCase(TAG_NAME_NO_OF_GOOD_MONTHS_IN_AN_YEAR)
                                            || tag.equalsIgnoreCase(TAG_NAME_NO_OF_REGULAR_MONTHS_IN_AN_YEAR)
                                            || tag.equalsIgnoreCase(TAG_NAME_NO_OF_BAD_MONTHS_IN_AN_YEAR)) {
                                        viewParameters.setValue(s.toString());
                                        // TODO: For calculating declared sales yearly ( no of days ==> good + regular + bad <= 12 )
                                        textInputListener.callBackForCalcAvgSales(viewParameters, tag, TAG_NAME_AVERAGE_YEARLY_SALES, 12);

                                    } else if (viewParameters.getScreenID().equalsIgnoreCase(SCREEN_N0_MANUFACTURING_OPERATING_EXPENSE)) {
                                        viewParameters.setValue(s.toString());
                                        // TODO: Sum of all fields by feature id
                                        textInputListener.callBackForSumOfAllFieldsByFeatureId(viewParameters, TAG_NAME_EXPENSES);
                                    } else if (tag.equalsIgnoreCase(TAG_NAME_PURCHASES_IN_PURCHASE_ANALYSIS)) {
                                        // TODO: Calculate Purchase Analysis
                                        viewParameters.setValue(s.toString());
                                        textInputListener.callBackForCalculatePurchaseAnalysis(viewParameters);
                                    } else if (viewParameters.getScreenName().equalsIgnoreCase(SCREEN_NAME_PURCHASE_BILLS_MSME)
                                            && tag.equalsIgnoreCase(TAG_NAME_MARGIN_IN_PURCHASE_BILLS)) {
                                        // TODO: Calculate Purchase Bills
                                        if(!TextUtils.isEmpty(s) && !(s.toString().equalsIgnoreCase("0")))
                                            textInputListener.callBackForPurchaseBillsCalculation(viewParameters);
                                    } else if (viewParameters.getScreenName().equalsIgnoreCase(SCREEN_NAME_GST_MSME)) {
                                        // TODO: GST CALCULATION
                                        textInputListener.callBackForGSTCalculation(dynamicUITable);
                                    } else if (tag.equalsIgnoreCase(TAG_NAME_PINCODE)) {
                                        if (!appHelper.patternMatch(AppConstants.REGEX_PATTERN_PINCODE, s.toString())) {
                                            error = viewParameters.getFieldTag() + " invalid";
                                        } else {
                                            moveToNext = true;
                                        }
                                    } else if (tag.equalsIgnoreCase(TAG_NAME_DISTANCE_FROM_BRANCH_IN_VILLAGE_SURVEY)) {

                                        viewParameters.setValue(s.toString());
                                        if (Integer.parseInt(s.toString()) < MINIMUM_DISTANCE_FROM_BRANCH_JLG) {
                                            error = ERROR_MESSAGE_FOR_MIN_DISTANCE_JLG;
                                        } else if (Integer.parseInt(s.toString()) > MAXIMUM_DISTANCE_FROM_BRANCH_JLG) {
                                            error = ERROR_MESSAGE_FOR_MAX_DISTANCE_JLG;
                                        } else {
                                            moveToNext = true;
                                        }
                                    }
                                    else if (tag.equalsIgnoreCase("AGE") ||
                                            tag.equalsIgnoreCase(TAG_NAME_CONTAINS_DOB)||
                                            tag.equalsIgnoreCase(TAG_NAME_SAME_FIRST_INSTALLMENT_DATE)) {
                                        if (viewParameters.getLoanType().equalsIgnoreCase(LOAN_NAME_INDIVIDUAL) ||
                                                viewParameters.getLoanType().equalsIgnoreCase(LOAN_NAME_TWL) || viewParameters.getLoanType().equalsIgnoreCase(LOAN_NAME_EL)) {
                                            viewParameters.setValue(s.toString());
                                            if (Integer.parseInt(s.toString()) < 3) {
                                                error = ERROR_MESSAGE_FOR_MIN_AGE_EL_EL;
                                            } else if (Integer.parseInt(s.toString()) > 75) {
                                                error = ERROR_MESSAGE_FOR_MAX_AGE_EL_EL;
                                            } else {

                                                /*
                                                if (viewParameters.getScreenName().equalsIgnoreCase(SCREEN_NAME_APPLICANT_KYC)
                                                        || viewParameters.getScreenName().equalsIgnoreCase(SCREEN_NAME_CO_APPLICANT_KYC)) {
                                                    DynamicUITable dynamicUITable_KYCTYPE = getObjectByTAG(TAG_NAME_KYC_TYPE, dynamicUITableList);

                                                    if (dynamicUITable_KYCTYPE.getValue().equalsIgnoreCase(SPINNER_ITEM_FIELD_NAME_VOTER_ID)) {
                                                        DynamicUITable dynamicUITable = getObjectByTAG(TAG_NAME_CONTAINS_DOB, dynamicUITableList);

                                                        dynamicUITable.setValue(appHelper.getDOBbasedOnAge(s.toString()));
                                                        textInputListener.callBackForUpdateUI();
                                                    }
                                                }*/
                                                moveToNext = true;

                                            }
                                        }else if (viewParameters.getLoanType().equalsIgnoreCase(LOAN_NAME_JLG)) {
                                            viewParameters.setValue(s.toString());
                                            textInputListener.callBackForAgeValidationJLG(viewParameters, XMLCustomTIL.this);
//                                            if (Integer.parseInt(s.toString()) < 21) {//
//                                                error = ERROR_MESSAGE_FOR_MIN_AGE_IL;
//                                            } else if (Integer.parseInt(s.toString()) > 58) {
//                                                error = ERROR_MESSAGE_FOR_MAX_AGE_IL;
//                                            } else {
//                                                moveToNext = true;
//
//                                            }

                                        } else if (viewParameters.getLoanType().equalsIgnoreCase(LOAN_NAME_MSME)
                                                ||viewParameters.getLoanType().equalsIgnoreCase(LOAN_NAME_AHL)) {
                                            viewParameters.setValue(s.toString());
                                            if (Integer.parseInt(s.toString()) < 18) {
                                                error = ERROR_MESSAGE_FOR_MIN_AGE_MSME;
                                            } else if (Integer.parseInt(s.toString()) > 100) {
                                                error = ERROR_MESSAGE_FOR_MAX_AGE_MSME;
                                            }  else {

                                                /*if (viewParameters.getScreenName().equalsIgnoreCase(SCREEN_NAME_APPLICANT_KYC)
                                                        || viewParameters.getScreenName().equalsIgnoreCase(SCREEN_NAME_CO_APPLICANT_KYC)) {
                                                    DynamicUITable dynamicUITable_KYCTYPE = getObjectByTAG(TAG_NAME_KYC_TYPE, dynamicUITableList);

                                                    if (dynamicUITable_KYCTYPE.getValue().equalsIgnoreCase(SPINNER_ITEM_FIELD_NAME_VOTER_ID)) {
                                                        DynamicUITable dynamicUITable = getObjectByTAG(TAG_NAME_CONTAINS_DOB, dynamicUITableList);

                                                        dynamicUITable.setValue(appHelper.getDOBbasedOnAge(s.toString()));
                                                       viewParameters.setErrorMessage("");
                                                        textInputListener.callBackForUpdateUI();

                                                    }
                                                }
*/
                                                moveToNext = true;
                                            }
                                        }
                                        else if (!viewParameters.getLoanType().equalsIgnoreCase("")) {
                                            viewParameters.setValue(s.toString());
                                            if (Integer.parseInt(s.toString()) < 0) {
                                                error = ERROR_MESSAGE_FOR_MIN_AGE_MSME;
                                            } else if (Integer.parseInt(s.toString()) > 100) {
                                                error = ERROR_MESSAGE_FOR_MAX_AGE_MSME;
                                            }  else {

                                                /*if (viewParameters.getScreenName().equalsIgnoreCase(SCREEN_NAME_APPLICANT_KYC)
                                                        || viewParameters.getScreenName().equalsIgnoreCase(SCREEN_NAME_CO_APPLICANT_KYC)) {
                                                    DynamicUITable dynamicUITable_KYCTYPE = getObjectByTAG(TAG_NAME_KYC_TYPE, dynamicUITableList);

                                                    if (dynamicUITable_KYCTYPE.getValue().equalsIgnoreCase(SPINNER_ITEM_FIELD_NAME_VOTER_ID)) {
                                                        DynamicUITable dynamicUITable = getObjectByTAG(TAG_NAME_CONTAINS_DOB, dynamicUITableList);

                                                        dynamicUITable.setValue(appHelper.getDOBbasedOnAge(s.toString()));
                                                       viewParameters.setErrorMessage("");
                                                        textInputListener.callBackForUpdateUI();

                                                    }
                                                }
*/
                                                moveToNext = true;
                                            }
                                        }
                                        else {
                                            if (Integer.parseInt(s.toString()) < 18) {
                                                error = ERROR_MESSAGE_FOR_MIN_AGE_MSME;
                                            } else if (Integer.parseInt(s.toString()) > 63) {
                                                error = ERROR_MESSAGE_FOR_MAX_AGE_MSME;
                                            } else {
                                                moveToNext = true;
                                            }
                                        }
                                    }
                                    else if (viewParameters.getLoanType().equalsIgnoreCase(LOAN_NAME_JLG) &&tag.equalsIgnoreCase(TAG_NAME_FAMILY_INCOME_JLG)) {
                                        if (Integer.parseInt(s.toString()) < 3000) {
                                            error = "Family income should be greater than or equal to 3000";
                                        }else{
                                            moveToNext=true;
                                        }

                                    }
                                    else if (tag.equalsIgnoreCase(TAG_NAME_TENURE_IN_SALES_TOOL) && viewParameters.getScreenName().equalsIgnoreCase(SCREEN_NAME_SALES_TOOL)) {
                                        DynamicUITable lookingForObj = getObjectByTAG(TAG_NAME_LOOKING_FOR_IN_SALES_TOOL, dynamicUITableList);
                                        if (lookingForObj != null) {

                                            int tenure = Integer.parseInt(s.toString());
                                            if (lookingForObj != null) {
                                                if (lookingForObj.getValue().equalsIgnoreCase(RADIO_BUTTON_ITEM_SECURED_LOAN)) {
                                                    // TODO: min tenure =6, max tenure=120
                                                    if (tenure <= 5 ) {
                                                        error = "Minimum Tenure is 6";
                                                    }else if(tenure>120){
                                                        error="Maximum Tenure is 120";
                                                    } else {
                                                        moveToNext = true;
                                                    }
                                                } else if (lookingForObj.getValue().equalsIgnoreCase(RADIO_BUTTON_ITEM_UNSECURED_LOAN)) {
                                                    // TODO: min tenure =6, max tenure=54
                                                    if (tenure <= 5 ) {
                                                        error = "Minimum Tenure is 6";
                                                    }else if(tenure > 54){
                                                        error="Maximum Tenure is 54";
                                                    }
                                                    else {
                                                        moveToNext = true;
                                                    }
                                                }
                                            }
                                        }

                                    }
                                    else if (tag.equalsIgnoreCase(TAG_NAME_ACCOUNT_NUMBER)) {
                                        if (!appHelper.patternMatch(AppConstants.ACCOUNT_NUMBER_VALIDATION, s.toString())) {
                                            error = viewParameters.getFieldTag() + " invalid";
                                        } else {
                                            // if (viewParameters.getScreenName().equalsIgnoreCase(SCREEN_NAME_CO_APPLICANT_BANK_DETAILS)) {
                                            viewParameters.setValue(s.toString());
                                            textInputListener.callBackForDeDupeValidation(viewParameters, XMLCustomTIL.this);
                                            // }
                                            moveToNext = true;
                                        }
                                    }
                                    else if (tag.equalsIgnoreCase(TAG_NAME_NOMINEE_AGE)) {
                                        if(viewParameters.getLoanType().equalsIgnoreCase(LOAN_NAME_JLG)){
                                            // textInputListener.callBackForAgeValidationJLG(viewParameters, XMLCustomTIL.this);
                                            textInputListener.callBackForAgeValidationJLG(viewParameters, XMLCustomTIL.this);

                                        }else {

                                            // TODO: For Nominee Details IL
                                            if (Integer.parseInt(s.toString()) < 18) {
                                                moveToNext = true;
                                                if (viewParameters.getScreenName().equalsIgnoreCase(SCREEN_NAME_NOMINEE_DETAIL)) {
                                                    textInputListener.callBackForEnableGuardianDetails();
                                                }


                                            } else if (Integer.parseInt(s.toString()) > 18) {
                                                moveToNext = true;
                                                if (viewParameters.getScreenName().equalsIgnoreCase(SCREEN_NAME_NOMINEE_DETAIL)) {
                                                    textInputListener.callBackForDisableGuardianDetails();
                                                }

                                            } else if (Integer.parseInt(s.toString()) > 63) {
                                                error = "Age must be less than 63 years";
                                            } else {
                                                moveToNext = true;
                                            }
                                        }
                                    }
                                    else if(tag.equalsIgnoreCase(TAG_NAME_SPOUSE_AGE)){

                                        if( viewParameters.getLoanType().equalsIgnoreCase(LOAN_NAME_JLG) && viewParameters.getScreenName().equalsIgnoreCase(SCREEN_NAME_PERSONAL_DETAIL)){
                                            if (Integer.parseInt(s.toString()) < 18) {
                                                error = ERROR_MESSAGE_FOR_MIN_AGE_JLG;
                                            }else {
                                                moveToNext=true;
                                            }

                                        }

                                    }
                                    else if (viewParameters.getScreenID().equalsIgnoreCase(SCREEN_N0_INVENTORY_BUSINESS_ASSETS_MSME)) {
                                        if (tag.equalsIgnoreCase(TAG_NAME_QUANTITY_IN_BUSINESS_ASSETS_MSME)
                                                || tag.equalsIgnoreCase(TAG_NAME_PURCHASE_PRICE_IN_BUSINESS_ASSETS_MSME)) {
                                            // TODO: For calculating current inventory ( quantity x purchase price = current inventory )
                                            viewParameters.setValue(s.toString());

                                            DynamicUITable quantityObj = getObjectByTAG(TAG_NAME_QUANTITY_IN_BUSINESS_ASSETS_MSME, dynamicUITableList);
                                            DynamicUITable purchasePriceObj = getObjectByTAG(TAG_NAME_PURCHASE_PRICE_IN_BUSINESS_ASSETS_MSME, dynamicUITableList);

                                            if (purchasePriceObj != null && quantityObj !=null) {
                                                if (!TextUtils.isEmpty(purchasePriceObj.getValue()) && !TextUtils.isEmpty(quantityObj.getValue())) {
                                                    int purchasePrice = Integer.parseInt(purchasePriceObj.getValue());
                                                    int quantity = Integer.parseInt(quantityObj.getValue());
                                                    DynamicUITable currentInventoryObj = getObjectByTAG(TAG_NAME_CURRENT_INVENTORY_IN_BUSINESS_ASSETS_MSME, dynamicUITableList);
                                                    if (currentInventoryObj != null) {
                                                        int currentInventory = quantity * purchasePrice;
                                                        currentInventoryObj.setValue(String.valueOf(currentInventory));
                                                        moveToNext = true;
                                                        dynamicUITable.setValue(s.toString());
                                                        textInputListener.callBackForUpdateSingleField(currentInventoryObj);
                                                    } else {
                                                        error = "Unable to calculate current inventory";
                                                    }
                                                } else {
                                                    moveToNext = true;
                                                }
                                            } else {
                                                moveToNext = true;
                                            }
                                        }
                                    }else if (viewParameters.getScreenID().equalsIgnoreCase(SCREEN_N0_PRODUCT)) {
                                        if (tag.equalsIgnoreCase(TAG_NAME_PURCHASE_PRICE) || tag.equalsIgnoreCase(TAG_NAME_QUANTITY)) {
                                            // TODO: For calculating business assets ( quantity x purchase price = total inventory )
                                            viewParameters.setValue(s.toString());
                                            DynamicUITable quantityObj = getObjectByTAG(TAG_NAME_QUANTITY, dynamicUITableList);
                                            DynamicUITable purchasePriceObj = getObjectByTAG(TAG_NAME_PURCHASE_PRICE, dynamicUITableList);

                                            if (quantityObj != null && purchasePriceObj!=null) {
                                                if (!TextUtils.isEmpty(purchasePriceObj.getValue()) && !TextUtils.isEmpty(quantityObj.getValue())){
                                                    int quantity = Integer.parseInt(quantityObj.getValue());
                                                    int price = Integer.parseInt(s.toString());
                                                    DynamicUITable currentInventoryObj = getObjectByTAG(TAG_NAME_CURRENT_INVENTORY, dynamicUITableList);
                                                    if (currentInventoryObj != null) {
                                                        int currentInventory = quantity * price;
                                                        currentInventoryObj.setValue(String.valueOf(currentInventory));
                                                        moveToNext = true;
                                                        dynamicUITable.setValue(s.toString());
                                                        textInputListener.callBackForUpdateSingleField(currentInventoryObj);
                                                    } else {
                                                        error = "Unable to calculate current inventory";
                                                    }
                                                } else {
                                                    moveToNext = true;
                                                }
                                            } else {
                                                moveToNext = true;
                                            }
                                        }
                                    }else if (viewParameters.getScreenID().equalsIgnoreCase(SCREEN_N0_PRODUCT_ESTIMATE_DETAIL_MSME)) {
                                        // TODO: For calculating business assets ( quantity sold x selling price price * frequency = total  sales )

                                        if (tag.equalsIgnoreCase(TAG_NAME_QUANTITY_SOLD_IN_PRODUCT_ESTIMATE_DETAIL)
                                                ||tag.equalsIgnoreCase(TAG_NAME_SELLING_PRICE_IN_PRODUCT_ESTIMATE_DETAIL)
                                                ||tag.equalsIgnoreCase(TAG_NAME_FREQUENCY_IN_PRODUCT_ESTIMATE_DETAIL)
                                        ) {

                                            DynamicUITable quantityObj = getObjectByTAG(TAG_NAME_QUANTITY_SOLD_IN_PRODUCT_ESTIMATE_DETAIL, dynamicUITableList);
                                            DynamicUITable sellingPriceObj = getObjectByTAG(TAG_NAME_SELLING_PRICE_IN_PRODUCT_ESTIMATE_DETAIL, dynamicUITableList);
                                            DynamicUITable freqPriceObj = getObjectByTAG(TAG_NAME_FREQUENCY_IN_PRODUCT_ESTIMATE_DETAIL, dynamicUITableList);

                                            if (tag.equalsIgnoreCase(TAG_NAME_QUANTITY_SOLD_IN_PRODUCT_ESTIMATE_DETAIL)){
                                                quantityObj.setValue(s.toString());
                                            }else if(tag.equalsIgnoreCase(TAG_NAME_SELLING_PRICE_IN_PRODUCT_ESTIMATE_DETAIL)){
                                                sellingPriceObj.setValue(s.toString());
                                            }else if(tag.equalsIgnoreCase(TAG_NAME_FREQUENCY_IN_PRODUCT_ESTIMATE_DETAIL)){
                                                freqPriceObj.setValue(s.toString());
                                            }

                                            DynamicUITable totalSalesObj = getObjectByTAG(TAG_NAME_TOTAL_SALES_IN_PRODUCT_ESTIMATE_DETAIL, dynamicUITableList);

                                            if (quantityObj != null && sellingPriceObj != null && freqPriceObj !=null){

                                                if (!TextUtils.isEmpty(quantityObj.getValue()) && !TextUtils.isEmpty(sellingPriceObj.getValue())
                                                        && !TextUtils.isEmpty(freqPriceObj.getValue())){

                                                    int quantity = Integer.parseInt(quantityObj.getValue());
                                                    int price = Integer.parseInt(sellingPriceObj.getValue());
                                                    int frequency = Integer.parseInt(freqPriceObj.getValue());
                                                    if (totalSalesObj != null) {
                                                        int currentInventory = quantity * price * frequency;
                                                        totalSalesObj.setValue(String.valueOf(currentInventory));
                                                        moveToNext = true;
                                                        dynamicUITable.setValue(s.toString());
                                                        textInputListener.callBackForUpdateSingleField(totalSalesObj);
                                                    } else {
                                                        error = "Unable to calculate current inventory";
                                                    }
                                                }else{
                                                    moveToNext = true;
                                                }
                                            }else {
                                                moveToNext = true;

                                            }
                                        } else if (tag.equalsIgnoreCase(TAG_NAME_PACKING_ITEMS_DIRECT_IN_PRODUCT_ESTIMATE_DETAIL)) {
                                            // TODO: For calculating business assets ( direct wages + packing items  = Direct costs )
                                            DynamicUITable directWagesObj = getObjectByTAG(TAG_NAME_DIRECT_WAGES_IN_PRODUCT_ESTIMATE_DETAIL, dynamicUITableList);
                                            if (directWagesObj != null && !TextUtils.isEmpty(directWagesObj.getValue())) {

                                                int directWages = Integer.parseInt(directWagesObj.getValue());
                                                int packingItems = Integer.parseInt(s.toString());

                                                DynamicUITable directCostsObj = getObjectByTAG(TAG_NAME_DIRECT_COSTS_IN_PRODUCT_ESTIMATE_DETAIL, dynamicUITableList);
                                                if (directCostsObj != null) {
                                                    int currentInventory = directWages + packingItems;
                                                    directCostsObj.setValue(String.valueOf(currentInventory));
                                                    moveToNext = true;
                                                    dynamicUITable.setValue(s.toString());
                                                    textInputListener.callBackForUpdateSingleField(directCostsObj);
                                                } else {
                                                    error = "Unable to calculate current inventory";
                                                }
                                            } else {
                                                error = "Please enter the direct wages first";
                                            }
                                        }
                                        else if(tag.equalsIgnoreCase(TAG_NAME_DIRECT_WAGES_IN_PRODUCT_ESTIMATE_DETAIL)){
                                            // TODO: For calculating business assets ( direct wages + packing items  = Direct costs)
                                            DynamicUITable packingItemsObj = getObjectByTAG(TAG_NAME_PACKING_ITEMS_DIRECT_IN_PRODUCT_ESTIMATE_DETAIL, dynamicUITableList);
                                            if (packingItemsObj != null && !TextUtils.isEmpty(packingItemsObj.getValue())) {

                                                int packingItems  = Integer.parseInt(packingItemsObj.getValue());
                                                int directWages  = Integer.parseInt(s.toString());

                                                DynamicUITable directCostsObj = getObjectByTAG(TAG_NAME_DIRECT_COSTS_IN_PRODUCT_ESTIMATE_DETAIL, dynamicUITableList);
                                                if (directCostsObj != null) {
                                                    int currentInventory = directWages + packingItems;
                                                    directCostsObj.setValue(String.valueOf(currentInventory));
                                                    moveToNext = true;
                                                    dynamicUITable.setValue(s.toString());
                                                    textInputListener.callBackForUpdateUI();
                                                } else {
                                                    error = "Unable to calculate current inventory";
                                                }
                                            }


                                        }


                                    }else if (viewParameters.getScreenID().equalsIgnoreCase(SCREEN_N0_SERVICE_ESTIMATE_DETAIL_MSME)) {
                                        if (tag.equalsIgnoreCase(TAG_NAME_AVG_PRICE_PER_SERVICE_IN_SERVICE_ESTIMATE_DETAIL)
                                                || tag.equalsIgnoreCase(TAG_NAME_NO_OF_CUSTOMERS_IN_SERVICE_ESTIMATE_DETAIL)) {
                                            // TODO: For calculating total sales
                                            //  ( no. of customers in a month  * avg price per service = total sales)

                                            DynamicUITable customersInAMonthObj = getObjectByTAG(TAG_NAME_NO_OF_CUSTOMERS_IN_SERVICE_ESTIMATE_DETAIL, dynamicUITableList);
                                            DynamicUITable avgPriceServiceObj = getObjectByTAG(TAG_NAME_AVG_PRICE_PER_SERVICE_IN_SERVICE_ESTIMATE_DETAIL, dynamicUITableList);
                                            if (tag.equalsIgnoreCase(TAG_NAME_NO_OF_CUSTOMERS_IN_SERVICE_ESTIMATE_DETAIL)){
                                                customersInAMonthObj.setValue(s.toString());
                                            }else if(tag.equalsIgnoreCase(TAG_NAME_AVG_PRICE_PER_SERVICE_IN_SERVICE_ESTIMATE_DETAIL)){
                                                avgPriceServiceObj.setValue(s.toString());
                                            }

                                            if (customersInAMonthObj != null && !TextUtils.isEmpty(customersInAMonthObj.getValue())) {
                                                if (avgPriceServiceObj != null && !TextUtils.isEmpty(avgPriceServiceObj.getValue())) {

                                                    int customersInAMonth = Integer.parseInt(customersInAMonthObj.getValue());

                                                    int avgPricePerService = Integer.parseInt(avgPriceServiceObj.getValue());
                                                    DynamicUITable monthlySalesObj = getObjectByTAG(TAG_NAME_TOTAL_SALES_IN_SERVICE_ESTIMATE_DETAIL, dynamicUITableList);
                                                    if (monthlySalesObj != null) {
                                                        int rawMaterialCost = customersInAMonth * avgPricePerService;
                                                        monthlySalesObj.setValue(String.valueOf(rawMaterialCost));
                                                        moveToNext = true;
                                                        dynamicUITable.setValue(s.toString());
                                                        textInputListener.callBackForUpdateUI();
                                                    } else {
                                                        error = "Unable to calculate total sales";
                                                    }
                                                }/*else{

                                                }*/
                                            }else {
                                                error = "Please enter the No. of customers in a month first";
                                            }
                                        }

                                    }

                                    else if (viewParameters.getScreenID().equalsIgnoreCase(SCREEN_N0_SERVICE_RAW_MATERIAL_MSME)) {
                                        if (tag.equalsIgnoreCase(TAG_NAME_PRICE_PER_UNIT_OF_RAW_MATERIAL_IN_SERVICE_RAW_MATERIAL)
                                                || tag.equalsIgnoreCase(TAG_NAME_QUANTITY_RAW_MATERIAL_PURCHASED_IN_SERVICE_RAW_MATERIAL)) {
                                            // TODO: For calculating cost of raw material
                                            //  ( Qty of Raw material* Price per unit of raw material = Cost of Raw Material)

                                            DynamicUITable qtyOfRawMaterialObj = getObjectByTAG(TAG_NAME_QUANTITY_RAW_MATERIAL_PURCHASED_IN_SERVICE_RAW_MATERIAL, dynamicUITableList);
                                            DynamicUITable pricePerUnitObj = getObjectByTAG(TAG_NAME_PRICE_PER_UNIT_OF_RAW_MATERIAL_IN_SERVICE_RAW_MATERIAL, dynamicUITableList);
                                            if (tag.equalsIgnoreCase(TAG_NAME_QUANTITY_RAW_MATERIAL_PURCHASED_IN_SERVICE_RAW_MATERIAL)){
                                                qtyOfRawMaterialObj.setValue(s.toString());
                                            }else if(tag.equalsIgnoreCase(TAG_NAME_PRICE_PER_UNIT_OF_RAW_MATERIAL_IN_SERVICE_RAW_MATERIAL)){
                                                pricePerUnitObj.setValue(s.toString());
                                            }


                                            if (qtyOfRawMaterialObj != null && !TextUtils.isEmpty(qtyOfRawMaterialObj.getValue())) {
                                                if(pricePerUnitObj!=null && !TextUtils.isEmpty(pricePerUnitObj.getValue())) {

                                                    int qtyOfRawMaterial = Integer.parseInt(qtyOfRawMaterialObj.getValue());
                                                    int pricePerUnit = Integer.parseInt(pricePerUnitObj.getValue());
                                                    DynamicUITable costOfRawMaterial = getObjectByTAG(TAG_NAME_COST_OF_RAW_MATERIAL_IN_SERVICE_RAW_MATERIAL, dynamicUITableList);
                                                    if (costOfRawMaterial != null) {
                                                        int rawMaterialCost = qtyOfRawMaterial * pricePerUnit;
                                                        costOfRawMaterial.setValue(String.valueOf(rawMaterialCost));
                                                        costOfRawMaterial.setEditable(false);
                                                        moveToNext = true;
                                                        dynamicUITable.setValue(s.toString());
                                                        textInputListener.callBackForUpdateUI();
                                                    } else {
                                                        error = "Unable to calculate cost of raw material";
                                                    }
                                                }
                                            } else {
                                                error = "Please enter the quantity of raw material first";
                                            }
                                        }

                                        if (tag.equalsIgnoreCase(TAG_NAME_CORRESPONDING_UNITS_OF_SERVICE_IN_SERVICE_RAW_MATERIAL)) {
                                            // TODO: For calculating cost of Material Cost Per Product
                                            //  ( Cost of Raw Material/Corresponding units of product= Material Cost Per Product)

                                            DynamicUITable costOfRawMaterialObj = getObjectByTAG(TAG_NAME_COST_OF_RAW_MATERIAL_IN_SERVICE_RAW_MATERIAL, dynamicUITableList);
                                            if (costOfRawMaterialObj != null && !TextUtils.isEmpty(costOfRawMaterialObj.getValue())) {

                                                int costOfRawMaterial = Integer.parseInt(costOfRawMaterialObj.getValue());

                                                int correspondingUnits = Integer.parseInt(s.toString());
                                                DynamicUITable materialCost = getObjectByTAG(TAG_NAME_MATERIAL_COST_PER_SERVICE_IN_SERVICE_RAW_MATERIAL, dynamicUITableList);
                                                if (materialCost != null) {
                                                    int rawMaterialCost = costOfRawMaterial / correspondingUnits;
                                                    materialCost.setValue(String.valueOf(rawMaterialCost));
                                                    moveToNext = true;
                                                    dynamicUITable.setValue(s.toString());
                                                    textInputListener.callBackForUpdateUI();
                                                } else {
                                                    error = "Unable to calculate cost of material cost per service";
                                                }
                                            } else {
                                                error = "Please enter the cost of raw material first";
                                            }
                                        }

                                    }

                                    else if (viewParameters.getScreenID().equalsIgnoreCase(SCREEN_N0_PRODUCT_RAW_MATERIAL_MSME)) {
                                        if (tag.equalsIgnoreCase(TAG_NAME_PRICE_PER_UNIT_OF_RAW_MATERIAL_IN_PRODUCT_RAW_MATERIAL)
                                                || tag.equalsIgnoreCase(TAG_NAME_QUANTITY_RAW_MATERIAL_PURCHASED_IN_PRODUCT_RAW_MATERIAL)) {
                                            // TODO: For calculating raw material cost
                                            //  ( qty of raw material purchased  * price per unit of raw material = raw material cost)

                                            DynamicUITable qtyRawMaterialPurchasedObj = getObjectByTAG(TAG_NAME_QUANTITY_RAW_MATERIAL_PURCHASED_IN_PRODUCT_RAW_MATERIAL, dynamicUITableList);
                                            DynamicUITable pricePerUnitObj = getObjectByTAG(TAG_NAME_PRICE_PER_UNIT_OF_RAW_MATERIAL_IN_PRODUCT_RAW_MATERIAL, dynamicUITableList);
                                            if (tag.equalsIgnoreCase(TAG_NAME_QUANTITY_RAW_MATERIAL_PURCHASED_IN_PRODUCT_RAW_MATERIAL)) {
                                                qtyRawMaterialPurchasedObj.setValue(s.toString());
                                            } else if (tag.equalsIgnoreCase(TAG_NAME_PRICE_PER_UNIT_OF_RAW_MATERIAL_IN_PRODUCT_RAW_MATERIAL)) {
                                                pricePerUnitObj.setValue(s.toString());
                                            }

                                            if (qtyRawMaterialPurchasedObj != null && !TextUtils.isEmpty(qtyRawMaterialPurchasedObj.getValue())) {
                                                if (pricePerUnitObj != null && !TextUtils.isEmpty(pricePerUnitObj.getValue())) {

                                                    int qtyRawMaterialPurchased = Integer.parseInt(qtyRawMaterialPurchasedObj.getValue());
                                                    int pricePerUnit = Integer.parseInt(pricePerUnitObj.getValue());
                                                    DynamicUITable monthlySalesObj = getObjectByTAG(TAG_NAME_RAW_MATERIAL_COST_IN_PRODUCT_RAW_MATERIAL, dynamicUITableList);
                                                    if (monthlySalesObj != null) {
                                                        int rawMaterialCost = qtyRawMaterialPurchased * pricePerUnit;
                                                        monthlySalesObj.setValue(String.valueOf(rawMaterialCost));
                                                        moveToNext = true;
                                                        dynamicUITable.setValue(s.toString());
                                                        textInputListener.callBackForUpdateUI();
                                                    } else {
                                                        error = "Unable to calculate raw material cost";
                                                    }
                                                }
                                            }else {
                                                error = "Please enter the quantity raw material purchased first";
                                            }
                                        }
                                        else  if (tag.equalsIgnoreCase(TAG_NAME_CORRESPONDING_UNITS_OF_PRODUCT_IN_PRODUCT_RAW_MATERIAL)) {
                                            // TODO: For calculating  material cost per product
                                            //  ( Cost of Raw Material/Corresponding units of product =  material cost per product)
                                            DynamicUITable rawMaterialCostObj = getObjectByTAG(TAG_NAME_RAW_MATERIAL_COST_IN_PRODUCT_RAW_MATERIAL, dynamicUITableList);
                                            if (rawMaterialCostObj != null && !TextUtils.isEmpty(rawMaterialCostObj.getValue())) {

                                                int rawMaterialCost = Integer.parseInt(rawMaterialCostObj.getValue());

                                                int correspondingUnits = Integer.parseInt(s.toString());
                                                DynamicUITable materialCostPerProductObj = getObjectByTAG(TAG_NAME_MATERIAL_COST_PER_PRODUCT_IN_PRODUCT_RAW_MATERIAL, dynamicUITableList);
                                                if (materialCostPerProductObj != null) {
                                                    int materialCostPerUnit = rawMaterialCost / correspondingUnits;
                                                    materialCostPerProductObj.setValue(String.valueOf(materialCostPerUnit));
                                                    moveToNext = true;
                                                    dynamicUITable.setValue(s.toString());
                                                    textInputListener.callBackForUpdateUI();
                                                } else {
                                                    error = "Unable to  material cost";
                                                }
                                            } else {
                                                error = "Please enter the raw material cost first";
                                            }
                                        }

                                    }
                                    else if (tag.equalsIgnoreCase(TAG_NAME_FREQUENCY)) {
                                        // TODO: For calculating business assets ( quantity x  price * frequency = total monthly sales )

                                        DynamicUITable quantityObj = getObjectByTAG(TAG_NAME_QUANTITY, dynamicUITableList);
                                        DynamicUITable sellingPriceObj = getObjectByTAG(TAG_NAME_SELLING_PRICE, dynamicUITableList);
                                        if (sellingPriceObj != null && !TextUtils.isEmpty(sellingPriceObj.getValue())) {
                                            if (quantityObj != null) {
                                                if (!TextUtils.isEmpty(quantityObj.getValue())) {
                                                    int quantity = Integer.parseInt(quantityObj.getValue());
                                                    int price = Integer.parseInt(sellingPriceObj.getValue());
                                                    int frequency = Integer.parseInt(s.toString());
                                                    DynamicUITable monthlySalesObj = getObjectByTAG(TAG_NAME_MONTHLY_SALES, dynamicUITableList);
                                                    if (monthlySalesObj != null) {
                                                        int currentInventory = quantity * price * frequency;
                                                        monthlySalesObj.setValue(String.valueOf(currentInventory));
                                                        moveToNext = true;
                                                        dynamicUITable.setValue(s.toString());
                                                        textInputListener.callBackForUpdateUI();
                                                    } else {
                                                        error = "Unable to calculate current inventory";
                                                    }
                                                } else {
                                                    error = "Please enter the quantity first";
                                                }
                                            } else {
                                                moveToNext = true;
                                            }
                                        } else {
                                            error = "Please enter the selling price first";
                                        }
                                    }
                                    //Added by Prasanna
                                    else if (tag.equalsIgnoreCase(TAG_NAME_RE_ENTER_ACCOUNT_NUMBER)) {
                                        DynamicUITable accountNumber = getObjectByTAG(TAG_NAME_ACCOUNT_NUMBER, dynamicUITableList);
                                        if (accountNumber != null) {
                                            if (!TextUtils.isEmpty(accountNumber.getValue())) {
                                                double acNum = Double.parseDouble(accountNumber.getValue());
                                                double reAcc = Double.parseDouble(s.toString());
                                                if (!(reAcc == acNum)) {
                                                    error = "Account number and re account number should be same";
                                                } else {
                                                    moveToNext = true;
                                                }
                                            } else {
                                                error = "Please enter account number first";
                                            }
                                        } else {
                                            moveToNext = true;
                                        }
                                    } else if ((viewParameters.getScreenID().equalsIgnoreCase(SCREEN_N0_HOUSE_EXPENSES))) {
                                        viewParameters.setValue(s.toString());
                                        // TODO: Sum of all fields by feature id
                                        textInputListener.callBackForSumOfAllFieldsByFeatureId(viewParameters, TAG_NAME_TOTAL_FAMILY_EXPENSES);
                                    } else if ((viewParameters.getScreenID().equalsIgnoreCase(SCREEN_N0_HOUSE_EXPENSES_MSME))) {
                                        viewParameters.setValue(s.toString());
                                        // TODO: Sum of all fields by feature id
                                        textInputListener.callBackForSumOfAllFieldsByFeatureId(viewParameters, TAG_NAME_TOTAL_EXPENSES);
                                    }
                                    else if ((viewParameters.getScreenID().equalsIgnoreCase(SCREEN_N0_OPERATING_EXPENSE_MSME))) {
                                        viewParameters.setValue(s.toString());

                                        // TODO: Sum of all fields by feature id
                                        textInputListener.callBackForSumOfAllFieldsByFeatureId(viewParameters, TAG_NAME_EXPENSES);
                                    }else if (tag.equalsIgnoreCase(TAG_NAME_FIXED)) {
                                        // TODO: For calculating total verified income ( fixed + variable = total verified income )
                                        DynamicUITable variableObj = getObjectByTAG(TAG_NAME_VARIABLE, dynamicUITableList);
                                        if (variableObj != null) {
                                            if (!TextUtils.isEmpty(variableObj.getValue())) {
                                                int variable = Integer.parseInt(variableObj.getValue());
                                                int fixed = 0;
                                                if (!s.toString().equalsIgnoreCase("")) {
                                                    fixed = Integer.parseInt(s.toString());
                                                }
//                                                int fixed = Integer.parseInt(s.toString());
                                                DynamicUITable totalVerifiedIncomeObj = getObjectByTAG(TAG_NAME_TOTAL_VERIFIED_INCOME, dynamicUITableList);
                                                if (totalVerifiedIncomeObj != null) {
                                                    int totalVerifiedIncome = fixed + variable;
                                                    totalVerifiedIncomeObj.setValue(String.valueOf(totalVerifiedIncome));
                                                    moveToNext = true;
                                                    dynamicUITable.setValue(s.toString());
                                                    textInputListener.callBackForUpdateSingleField(totalVerifiedIncomeObj);
                                                } else {
                                                    error = "Unable to calculate total verified income";
                                                }
                                            } else {
                                                //   error = "Please enter the fixed amount first";
                                            }
                                        } else {
                                            moveToNext = true;
                                        }
                                    } else if (tag.equalsIgnoreCase(TAG_NAME_VARIABLE)) {
                                        // TODO: For calculating total verified income ( fixed + variable = total verified income )
                                        DynamicUITable fixedObj = getObjectByTAG(TAG_NAME_FIXED, dynamicUITableList);
                                        if (fixedObj != null) {
                                            if (!TextUtils.isEmpty(fixedObj.getValue())) {
                                                int fixed = Integer.parseInt(fixedObj.getValue());
                                                int variable = 0;
                                                if (!s.toString().equalsIgnoreCase("")) {
                                                    variable = Integer.parseInt(s.toString());
                                                }
                                                // int variable = Integer.parseInt(s.toString());
                                                DynamicUITable totalVerifiedIncomeObj = getObjectByTAG(TAG_NAME_TOTAL_VERIFIED_INCOME, dynamicUITableList);
                                                if (totalVerifiedIncomeObj != null) {
                                                    int totalVerifiedIncome = fixed + variable;
                                                    totalVerifiedIncomeObj.setValue(String.valueOf(totalVerifiedIncome));
                                                    moveToNext = true;
                                                    dynamicUITable.setValue(s.toString());
                                                    textInputListener.callBackForUpdateSingleField(totalVerifiedIncomeObj);
                                                } else {
                                                    error = "Unable to calculate total verified income";
                                                }
                                            } else {
                                                error = "Please enter the fixed amount first";
                                            }
                                        } else {
                                            moveToNext = true;
                                        }
                                    } // TODO: 23-10-2019 TO CALCULATE TOTAL BOUNCES
                                    else if (tag.equalsIgnoreCase(TAG_NAME_INWARD_BOUNCES_IN_BANKING_HISTORY)) {
                                        // TODO: For calculating total BOUNCES ( inwardBounces + outward bounces = total bounces )
                                        DynamicUITable outwardBouncesObj = getObjectByTAG(TAG_NAME_OUTWARD_BOUNCES_IN_BANKING_HISTORY, dynamicUITableList);
                                        int inwardBounces = 0;
                                        int outwardBounces = 0;
                                        if (outwardBouncesObj != null && !TextUtils.isEmpty(outwardBouncesObj.getValue())) {
                                            outwardBounces = Integer.parseInt(outwardBouncesObj.getValue());
                                        }

                                        if (!s.toString().equalsIgnoreCase("")) {
                                            inwardBounces = Integer.parseInt(s.toString());
                                        }
                                        DynamicUITable totalBouncesObj = getObjectByTAG(TAG_NAME_TOTAL_BOUNCES_IN_BANKING_HISTORY, dynamicUITableList);
                                        if (totalBouncesObj != null) {
                                            if (!s.toString().equalsIgnoreCase("") && outwardBouncesObj != null && !TextUtils.isEmpty(outwardBouncesObj.getValue())) {
                                                int totalBounces = inwardBounces + outwardBounces;
                                                totalBouncesObj.setValue(String.valueOf(totalBounces));
                                                moveToNext = true;
                                            } else {
                                                totalBouncesObj.setValue(null);
                                            }

                                            dynamicUITable.setValue(s.toString());
                                            //textInputListener.callBackForUpdateUI();
                                            textInputListener.callBackForUpdateSingleField(totalBouncesObj);

                                        } else {
                                            error = "Unable to calculate total bounces";
                                        }


                                    } else if (tag.equalsIgnoreCase(TAG_NAME_OUTWARD_BOUNCES_IN_BANKING_HISTORY)) {
                                        DynamicUITable inwardBouncesObj = getObjectByTAG(TAG_NAME_INWARD_BOUNCES_IN_BANKING_HISTORY, dynamicUITableList);
                                        int inwardBounces = 0;
                                        int outwardBounces = 0;
                                        if (inwardBouncesObj != null) {
                                            if (!TextUtils.isEmpty(inwardBouncesObj.getValue())) {
                                                inwardBounces = Integer.parseInt(inwardBouncesObj.getValue());
                                                // inwardBouncesObj.setValue(String.valueOf(inwardBounces));
                                            }
                                        }
                                        DynamicUITable totalBouncesObj = getObjectByTAG(TAG_NAME_TOTAL_BOUNCES_IN_BANKING_HISTORY, dynamicUITableList);

                                        if (!s.toString().equalsIgnoreCase("")) {
                                            outwardBounces = Integer.parseInt(s.toString());
                                        }
                                        // int variable = Integer.parseInt(s.toString());
                                        if (totalBouncesObj != null) {
                                            if (!s.toString().equalsIgnoreCase("") && inwardBouncesObj != null && !TextUtils.isEmpty(inwardBouncesObj.getValue())) {
                                                int totalBouncesIncome = inwardBounces + outwardBounces;
                                                totalBouncesObj.setValue(String.valueOf(totalBouncesIncome));
                                                moveToNext = true;
                                            } else {
                                                totalBouncesObj.setValue(null);
                                            }

                                            dynamicUITable.setValue(s.toString());
                                            //textInputListener.callBackForUpdateUI();
                                            textInputListener.callBackForUpdateSingleField(totalBouncesObj);

                                        } else {
                                            error = "Unable to calculate total verified income";
                                        }
                                    } else if (tag.equalsIgnoreCase(TAG_NAME_VALUE_IN_DIRECT_BUSINESS_EXPENSE)) {
                                        DynamicUITable frequencyObj = getObjectByTAG(TAG_NAME_FREQUENCY_IN_DIRECT_BUSINESS_EXPENSE, dynamicUITableList);
                                        int frequencyvalue = 0;
                                        int value = 0;
                                        if (frequencyObj != null) {
                                            if (!TextUtils.isEmpty(frequencyObj.getValue())) {
                                                //Daily:25,weekly:4,monthly:1
                                                if (frequencyObj.getValue().equalsIgnoreCase(FREQUENCY_DAILY)) {
                                                    frequencyvalue = 25;
                                                } else if (frequencyObj.getValue().equalsIgnoreCase(FREQUENCY_WEEKLY)) {
                                                    frequencyvalue = 4;
                                                } else if (frequencyObj.getValue().equalsIgnoreCase(FREQUENCY_MONTHLY)) {
                                                    frequencyvalue = 1;
                                                }
                                            }
                                            DynamicUITable totalexpenseObj = getObjectByTAG(TAG_NAME_TOTAL_EXPENSES_IN_DIRECT_BUSINESS_EXPENSE, dynamicUITableList);

                                            if (!s.toString().equalsIgnoreCase("")) {
                                                value = Integer.parseInt(s.toString());
                                            }
                                            if (totalexpenseObj != null) {
                                                if (!s.toString().equalsIgnoreCase("") && frequencyObj != null && !TextUtils.isEmpty(frequencyObj.getValue())) {
                                                    int totalexpense = frequencyvalue * value;
                                                    totalexpenseObj.setValue(String.valueOf(totalexpense));
                                                    moveToNext = true;
                                                } else {
                                                    totalexpenseObj.setValue(null);
                                                }

                                                dynamicUITable.setValue(s.toString());
                                                //textInputListener.callBackForUpdateUI();
                                                textInputListener.callBackForUpdateSingleField(totalexpenseObj);

                                            } else {
                                                error = "Unable to calculate total expense";
                                            }
                                        }
                                    }else if(viewParameters.getScreenID().equalsIgnoreCase(SCREEN_NO_APPLICANT_LOAN_PROPOSAL_EL)
                                            && tag.equalsIgnoreCase(TAG_NAME_REQUESTED_LOAN_AMOUNT)){
                                        viewParameters.setValue(s.toString());

                                        textInputListener.callBackForLoanAmountValidation(viewParameters, XMLCustomTIL.this);

                                    }
//                                    else if(viewParameters.getScreenID().equalsIgnoreCase(SCREEN_NO_APPLICANT_LOAN_PROPOSAL_AHL)
//                                            && tag.equalsIgnoreCase(TAG_NAME_REQUESTED_LOAN_AMOUNT)){
//                                        // TODO: AHL requested loan amount
//                                        int minimumAmount = AHL_MINIMUM_LOAN_AMOUNT;
//                                        int maximumAmount = AHL_MAXIMUM_LOAN_AMOUNT;
//
//                                        if (Integer.parseInt(s.toString()) < minimumAmount) {
//                                            error = "Requested loan amount should be greater than " + minimumAmount;
//                                        } else if (Integer.parseInt(s.toString()) > maximumAmount) {
//                                            error = "Requested loan amount should be less than " + maximumAmount;
//                                        } else {
//                                            moveToNext = true;
//                                        }
//
//                                    }

                                    else if (tag.equalsIgnoreCase(TAG_NAME_REQUESTED_LOAN_AMOUNT)) {

                                        int minimumAmount = 1000;
                                        int maximumAmount = 150000000;
                                        if (viewParameters.getLoanType().equalsIgnoreCase(LOAN_NAME_INDIVIDUAL)) {
                                            minimumAmount = 40000;
                                            maximumAmount = 200000;
                                        }else if(viewParameters.getLoanType().equalsIgnoreCase(LOAN_NAME_MSME)){
                                            minimumAmount = 75000;
                                            maximumAmount = 5000000;
                                        }
                                        else if(viewParameters.getLoanType().equalsIgnoreCase(LOAN_NAME_PHL)){
                                            minimumAmount = PHL_MINIMUM_LOAN_AMOUNT;
                                            maximumAmount = PHL_MAXIMUM_LOAN_AMOUNT;
                                        }else if(viewParameters.getLoanType().equalsIgnoreCase(LOAN_NAME_EL)){
                                            minimumAmount = EL_MINIMUM_LOAN_AMOUNT;
                                            maximumAmount = EL_MAXIMUM_LOAN_AMOUNT;
                                        }else if(viewParameters.getLoanType().equalsIgnoreCase(LOAN_NAME_TWL)){
                                            minimumAmount = PHL_MINIMUM_LOAN_AMOUNT;
                                            maximumAmount = PHL_MAXIMUM_LOAN_AMOUNT;
                                        }
                                        else if(viewParameters.getLoanType().equalsIgnoreCase(LOAN_NAME_AHL)){
                                            minimumAmount = AHL_MINIMUM_LOAN_AMOUNT;
                                            maximumAmount = AHL_MAXIMUM_LOAN_AMOUNT;
                                        }else {
                                            minimumAmount = AHL_MINIMUM_LOAN_AMOUNT;
                                            maximumAmount = AHL_MAXIMUM_LOAN_AMOUNT;
                                        }
                                        if (Integer.parseInt(s.toString()) < minimumAmount) {
                                            error = "Requested loan amount should be greater than " + minimumAmount;
                                        } else if (Integer.parseInt(s.toString()) > maximumAmount) {
                                            error = "Requested loan amount should be less than " + maximumAmount;
                                        } else {
                                            moveToNext = true;
                                        }

                                    }
                                    else if (tag.equalsIgnoreCase(TAG_NAME_DECLARED_MONTHLY_REPAYMENT_CAPACITY) || tag.equalsIgnoreCase(TAG_NAME_SAME_DECLARED_REPAYMENT_CAPACITY)) {
                                        DynamicUITable reqLoanAmtObj = getObjectByTAG(TAG_NAME_REQUESTED_LOAN_AMOUNT, dynamicUITableList);
                                        if (reqLoanAmtObj != null) {
                                            if (!TextUtils.isEmpty(reqLoanAmtObj.getValue())) {
                                                double loanAmtValue = Double.parseDouble(reqLoanAmtObj.getValue());
                                                if (Double.parseDouble(s.toString()) >= loanAmtValue) {
                                                    error = "Declared Repayment Capacity should be less than requested loan amount";
                                                } else {
                                                    moveToNext = true;
                                                }

                                            } else {
                                                error = "Please enter the requested loan amount first";
                                            }
                                        } else {
                                            moveToNext = true;
                                        }
                                    } else {
                                        moveToNext = true;
                                    }
                                }
                            } else {
                                if (tag.equalsIgnoreCase("PANCARD")
                                        || tag.equalsIgnoreCase(TAG_NAME_KYC_TYPE_PANCARD)
                                        || tag.equalsIgnoreCase(TAG_NAME_KYC_TYPE_PAN)
                                        || tag.equalsIgnoreCase(TAG_NAME_GUARANTOR_BUSINESS_PAN)
                                        || tag.equalsIgnoreCase(TAG_NAME_GUARANTOR_PAN_NUMBER)
                                        || tag.equalsIgnoreCase(TAG_NAME_KYC_TYPE_RE_ENTER_PANCARD)) {
                                    if (!appHelper.patternMatch(AppConstants.REGEX_PATTERN_PANCARD, s.toString())) {
                                        error = viewParameters.getFieldTag() + " invalid";
                                    } else {

//                                        if (viewParameters.getScreenName().equalsIgnoreCase(SCREEN_NAME_CO_APPLICANT_KYC)) {
                                        viewParameters.setValue(s.toString());
                                        textInputListener.callBackForDeDupeValidation(viewParameters, XMLCustomTIL.this);
//                                        }

                                        moveToNext = true;
                                    }
                                } else if (tag.equalsIgnoreCase(TAG_NAME_KYC_TYPE_PASSPORT)
                                        || tag.equalsIgnoreCase(TAG_NAME_KYC_TYPE_RE_ENTER_PASSPORT)
                                        || tag.equalsIgnoreCase(SPINNER_ITEM_FIELD_NAME_GUARANTOR_PASSPORT)
                                ) {
                                    if (!appHelper.patternMatch(AppConstants.REGEX_PATTERN_PASSPORT, s.toString())) {
                                        error = viewParameters.getFieldTag() + " invalid";
                                    } else {
                                        // if (viewParameters.getScreenName().equalsIgnoreCase(SCREEN_NAME_CO_APPLICANT_KYC)) {
                                        viewParameters.setValue(s.toString());
                                        textInputListener.callBackForDeDupeValidation(viewParameters, XMLCustomTIL.this);
                                        // }

                                        moveToNext = true;
                                    }
                                }
                                //Added by prasanna,  (commected by naveen in 05/07/2023)
                                /*else if (tag.equalsIgnoreCase(TAG_NAME_IFSC_CODE)) {
                                    if (!appHelper.patternMatch(AppConstants.IFSC_CODE_VALIDATION, s.toString())) {
                                        error = viewParameters.getFieldTag() + " invalid";
                                    } else {
                                        if(viewParameters.getLoanType().equalsIgnoreCase(LOAN_NAME_JLG)){
                                            viewParameters.setValue(s.toString());
                                            textInputListener.callBackForIFSC(viewParameters, XMLCustomTIL.this);

                                        }else {
                                            moveToNext = true;
                                        }
                                    }
                                } */else if (tag.equalsIgnoreCase("VOTERID")
                                        || tag.equalsIgnoreCase(TAG_NAME_KYC_TYPE_VOTERID)
                                        || tag.equalsIgnoreCase(TAG_NAME_KYC_TYPE_RE_ENTER_VOTERID)) {
                                    if (s.length() <10) {
                                        error = viewParameters.getFieldTag() + " invalid";
                                    } else {
                                        //if (viewParameters.getScreenName().equalsIgnoreCase(SCREEN_NAME_CO_APPLICANT_KYC)) {
                                        viewParameters.setValue(s.toString());
                                        textInputListener.callBackForDeDupeValidation(viewParameters, XMLCustomTIL.this);
                                        //}
                                        moveToNext = true;
                                    }
                                }
//                                else if (tag.equalsIgnoreCase(TAG_NAME_KYC_TYPE_RATION_CARD)) {
//                                        if (viewParameters.getScreenName().equalsIgnoreCase(SCREEN_NAME_CO_APPLICANT_KYC)) {
//                                            viewParameters.setValue(s.toString());
//                                            textInputListener.callBackForDeDupeValidation(viewParameters, XMLCustomTIL.this);
//                                        }
//                                        moveToNext = true;
//                                }
                                else if (tag.equalsIgnoreCase(TAG_NAME_KYC_TYPE_BANKPASSBOOK)
                                        || tag.equalsIgnoreCase(TAG_NAME_KYC_TYPE_RE_ENTER_BANKPASSBOOK)
                                        || tag.equalsIgnoreCase(TAG_NAME_KYC_TYPE_JOBCARD)
                                        || tag.equalsIgnoreCase(TAG_NAME_KYC_TYPE_RE_ENTER_JOBCARD)
                                        || tag.equalsIgnoreCase(TAG_NAME_KYC_TYPE_NREGAJOBCARD)
                                        || tag.equalsIgnoreCase(TAG_NAME_KYC_TYPE_RE_ENTER_NREGAJOBCARD)
                                        || tag.equalsIgnoreCase(TAG_NAME_BUSINESS_SUB_SECTOR)
                                        || tag.equalsIgnoreCase(TAG_NAME_SPOUSE_NAME)
                                        || tag.equalsIgnoreCase(TAG_NAME_MOTHER_MAIDEN_NAME)
                                        || tag.equalsIgnoreCase(TAG_NAME_MOTHER_NAME)
                                        || tag.equalsIgnoreCase(TAG_NAME_FATHER_NAME)
                                        || tag.equalsIgnoreCase(TAG_NAME_EXISTING_CUSTOMER_ID)
                                        || tag.equalsIgnoreCase(TAG_NAME_REFERRAL_DESIGNATION)
                                        || tag.equalsIgnoreCase(TAG_NAME_REFERRAL_ID)
//                                        || tag.contains(TAG_NAME_CONTAINS_NAME) // TODO: contains
                                        || tag.equalsIgnoreCase(TAG_NAME_FULL_NAME)
                                ) {
//                                    if (tag.equalsIgnoreCase(TAG_NAME_KYC_TYPE_DRIVINGLICENSE)){
//                                        if (!appHelper.patternMatch(AppConstants.REGEX_PATTERN_DRIVINGLICENSE, s.toString())) {
//                                            error = viewParameters.getFieldTag() + " invalid";
//                                        } else {
//                                            if (viewParameters.getScreenName().equalsIgnoreCase(SCREEN_NAME_CO_APPLICANT_KYC)) {
//                                                viewParameters.setValue(s.toString());
//                                                textInputListener.callBackForDeDupeValidation(viewParameters, XMLCustomTIL.this);
//                                            }
//
//                                            moveToNext = true;
//                                        }
//                                    }else {//removedd

                                    if (appHelper.patternMatch(AppConstants.REGEX_PATTERN_CONTINEOUS_THREE_CHARACTERS, s.toString())) {
                                        error = viewParameters.getFieldTag() + " invalid";
                                    } else {
                                        moveToNext = true;
                                    }
                                    // }
                                } else if (tag.equalsIgnoreCase("VINTAGE")) {
                                    if (Integer.parseInt(s.toString()) == 0 || Integer.parseInt(s.toString()) > 11) {
                                        error = viewParameters.getFieldTag() + " invalid";
                                    } else {
                                        moveToNext = true;
                                    }
                                } else if (tag.equalsIgnoreCase("AREA")) {
                                    if (Integer.parseInt(s.toString()) < 50) {
                                        error = viewParameters.getFieldTag() + " invalid";
                                    } else {
                                        moveToNext = true;
                                    }
                                }
                                //Added by prasanna
                                else if (tag.equalsIgnoreCase(TAG_NAME_RE_ENTER_IFSC_CODE)) {
                                    DynamicUITable ifsc = getObjectByTAG(TAG_NAME_IFSC_CODE, dynamicUITableList);
                                    if (ifsc != null) {
                                        if (!TextUtils.isEmpty(ifsc.getValue())) {
                                            if (s.toString().equals(ifsc.getValue().toString())) {
                                                moveToNext = true;
                                            } else {
                                                error = "IFSC code and re IFSC code should be same";
                                            }
                                        } else {
                                            error = "Please enter ifsc code first";
                                        }
                                    } else {
                                        moveToNext = true;
                                    }
                                } else {
                                    moveToNext = true;
                                }
                            }
                        }
                    } else {
                        // TODO: Length is zero
                        if (viewParameters.getScreenID().equalsIgnoreCase(SCREEN_N0_PRODUCT)) {
                            if (tag.equalsIgnoreCase(TAG_NAME_PURCHASE_PRICE)) {
                                // TODO: For calculating business assets ( quantity x purchase price = total inventory )

                                DynamicUITable quantityObj = getObjectByTAG(TAG_NAME_QUANTITY, dynamicUITableList);
                                if (quantityObj != null) {
                                    if (!TextUtils.isEmpty(quantityObj.getValue())) {
                                        int quantity = Integer.parseInt(quantityObj.getValue());
                                        int price = Integer.parseInt(s.toString());
                                        DynamicUITable currentInventoryObj = getObjectByTAG(TAG_NAME_CURRENT_INVENTORY, dynamicUITableList);
                                        if (currentInventoryObj != null) {
                                            int currentInventory = quantity * price;
                                            currentInventoryObj.setValue(String.valueOf(currentInventory));
                                            moveToNext = true;
                                            dynamicUITable.setValue(s.toString());
                                            textInputListener.callBackForUpdateUI();
                                        } else {
                                            error = "Unable to calculate current inventory";
                                        }
                                    } else {
                                        error = "Please enter the quantity first";
                                    }
                                } else {
                                    moveToNext = true;
                                }
                            } else if (tag.equalsIgnoreCase(TAG_NAME_QUANTITY)) {
                                // TODO: For calculating business assets ( quantity x purchase price = total inventory )

                                DynamicUITable purchasePriceObj = getObjectByTAG(TAG_NAME_PURCHASE_PRICE, dynamicUITableList);
                                if (purchasePriceObj != null) {
                                    if (!TextUtils.isEmpty(purchasePriceObj.getValue())) {
                                        int purchasePrice = Integer.parseInt(purchasePriceObj.getValue());
                                        int quantity = Integer.parseInt(s.toString());
                                        DynamicUITable currentInventoryObj = getObjectByTAG(TAG_NAME_CURRENT_INVENTORY, dynamicUITableList);
                                        if (currentInventoryObj != null) {
                                            int currentInventory = quantity * purchasePrice;
                                            currentInventoryObj.setValue(String.valueOf(currentInventory));
                                            moveToNext = true;
                                            dynamicUITable.setValue(s.toString());
                                            textInputListener.callBackForUpdateUI();
                                        } else {
                                            error = "Unable to calculate current inventory";
                                        }
                                    } else {
                                        moveToNext = true;
                                    }
                                } else {
                                    moveToNext = true;
                                }
                            }
                        } else if (viewParameters.getScreenID().equalsIgnoreCase(SCREEN_N0_MANUFACTURING_OPERATING_EXPENSE)) {
                            viewParameters.setValue(s.toString());
                            // TODO: Sum of all fields by feature id
                            textInputListener.callBackForSumOfAllFieldsByFeatureId(viewParameters, TAG_NAME_EXPENSES);
                        } else if (viewParameters.getScreenID().equalsIgnoreCase(SCREEN_N0_HOUSE_EXPENSES)) {
                            // TODO: Sum of all fields by feature id
                            textInputListener.callBackForSumOfAllFieldsByFeatureId(viewParameters, TAG_NAME_TOTAL_FAMILY_EXPENSES);
                        } else if (viewParameters.getScreenID().equalsIgnoreCase(SCREEN_N0_HOUSE_EXPENSES_MSME)) {
                            // TODO: Sum of all fields by feature id
                            textInputListener.callBackForSumOfAllFieldsByFeatureId(viewParameters, TAG_NAME_TOTAL_EXPENSES);
                        } else if ((viewParameters.getScreenID().equalsIgnoreCase(SCREEN_N0_OPERATING_EXPENSE_MSME))) {
                            viewParameters.setValue(s.toString());
                            // TODO: Sum of all fields by feature id
                            textInputListener.callBackForSumOfAllFieldsByFeatureId(viewParameters, TAG_NAME_EXPENSES);
                        } else if (viewParameters.getScreenID().equalsIgnoreCase(SCREEN_N0_PRODUCT_ESTIMATE_DETAIL_MSME)) {
                            // TODO: For calculating business assets ( quantity sold x selling price price * frequency = total  sales )

                            if (tag.equalsIgnoreCase(TAG_NAME_FREQUENCY_IN_PRODUCT_ESTIMATE_DETAIL)
                                    || tag.equalsIgnoreCase(TAG_NAME_QUANTITY_SOLD_IN_PRODUCT_ESTIMATE_DETAIL)
                                    || tag.equalsIgnoreCase(TAG_NAME_SELLING_PRICE_IN_PRODUCT_ESTIMATE_DETAIL)) {
                                DynamicUITable totalSalesObj = getObjectByTAG(TAG_NAME_TOTAL_SALES_IN_PRODUCT_ESTIMATE_DETAIL, dynamicUITableList);
                                totalSalesObj.setValue(null);
                                dynamicUITable.setValue(s.toString());
                                textInputListener.callBackForUpdateUI();
                            } else if (tag.equalsIgnoreCase(TAG_NAME_PACKING_ITEMS_DIRECT_IN_PRODUCT_ESTIMATE_DETAIL)
                                    || tag.equalsIgnoreCase(TAG_NAME_DIRECT_WAGES_IN_PRODUCT_ESTIMATE_DETAIL)) {

                                DynamicUITable directCosts = getObjectByTAG(TAG_NAME_DIRECT_COSTS_IN_PRODUCT_ESTIMATE_DETAIL, dynamicUITableList);
                                directCosts.setValue(null);
                                dynamicUITable.setValue(s.toString());
                                textInputListener.callBackForUpdateUI();

                            }
                        } else if (viewParameters.getScreenID().equalsIgnoreCase(SCREEN_N0_PRODUCT_RAW_MATERIAL_MSME)) {
                            if (tag.equalsIgnoreCase(TAG_NAME_PRICE_PER_UNIT_OF_RAW_MATERIAL_IN_PRODUCT_RAW_MATERIAL)
                                    || tag.equalsIgnoreCase(TAG_NAME_QUANTITY_RAW_MATERIAL_PURCHASED_IN_PRODUCT_RAW_MATERIAL)) {
                                DynamicUITable directCosts = getObjectByTAG(TAG_NAME_RAW_MATERIAL_COST_IN_PRODUCT_RAW_MATERIAL, dynamicUITableList);
                                directCosts.setValue(null);
                                dynamicUITable.setValue(s.toString());
                                textInputListener.callBackForUpdateUI();

                            } else  if (tag.equalsIgnoreCase(TAG_NAME_CORRESPONDING_UNITS_OF_PRODUCT_IN_PRODUCT_RAW_MATERIAL)) {
                                DynamicUITable directCosts = getObjectByTAG(TAG_NAME_MATERIAL_COST_PER_PRODUCT_IN_PRODUCT_RAW_MATERIAL, dynamicUITableList);
                                directCosts.setValue(null);
                                dynamicUITable.setValue(s.toString());
                                textInputListener.callBackForUpdateUI();
                            }
                        }
                        else if (tag.equalsIgnoreCase(TAG_NAME_FREQUENCY)) {
                            // TODO: For calculating business assets ( quantity x  price * frequency = total monthly sales )

                            DynamicUITable quantityObj = getObjectByTAG(TAG_NAME_QUANTITY, dynamicUITableList);
                            DynamicUITable sellingPriceObj = getObjectByTAG(TAG_NAME_SELLING_PRICE, dynamicUITableList);
                            if (sellingPriceObj != null && !TextUtils.isEmpty(sellingPriceObj.getValue())) {
                                if (quantityObj != null) {
                                    if (!TextUtils.isEmpty(quantityObj.getValue())) {
                                        int quantity = Integer.parseInt(quantityObj.getValue());
                                        int price = Integer.parseInt(sellingPriceObj.getValue());
                                        int frequency = Integer.parseInt(s.toString());
                                        DynamicUITable monthlySalesObj = getObjectByTAG(TAG_NAME_MONTHLY_SALES, dynamicUITableList);
                                        if (monthlySalesObj != null) {
                                            int currentInventory = quantity * price * frequency;
                                            monthlySalesObj.setValue(String.valueOf(currentInventory));
                                            moveToNext = true;
                                            dynamicUITable.setValue(s.toString());
                                            textInputListener.callBackForUpdateUI();
                                        } else {
                                            error = "Unable to calculate current inventory";
                                        }
                                    } else {
                                        error = "Please enter the quantity first";
                                    }
                                } else {
                                    moveToNext = true;
                                }
                            } else {
                                error = "Please enter the selling price first";
                            }
                        } else if (tag.equalsIgnoreCase(TAG_NAME_FIXED)) {
                            // TODO: For calculating total verified income ( fixed + variable = total verified income )
                            DynamicUITable variableObj = getObjectByTAG(TAG_NAME_VARIABLE, dynamicUITableList);
                            if (variableObj != null) {
                                if (!TextUtils.isEmpty(variableObj.getValue())) {
                                    int variable = Integer.parseInt(variableObj.getValue());
                                    int fixed = 0;
                                    if (!s.toString().equalsIgnoreCase("")) {
                                        fixed = Integer.parseInt(s.toString());
                                    }
                                    // int fixed = Integer.parseInt(s.toString());
                                    DynamicUITable totalVerifiedIncomeObj = getObjectByTAG(TAG_NAME_TOTAL_VERIFIED_INCOME, dynamicUITableList);
                                    if (totalVerifiedIncomeObj != null) {
                                        int totalVerifiedIncome = fixed + variable;
                                        totalVerifiedIncomeObj.setValue(String.valueOf(totalVerifiedIncome));
                                        moveToNext = true;
                                        dynamicUITable.setValue(s.toString());
                                        textInputListener.callBackForUpdateSingleField(totalVerifiedIncomeObj);
                                    } else {
                                        error = "Unable to calculate total verified income";
                                    }
                                } else {
                                    //   error = "Please enter the fixed amount first";
                                }
                            } else {
                                moveToNext = true;
                            }
                        } else if (tag.equalsIgnoreCase(TAG_NAME_VARIABLE)) {
                            // TODO: For calculating total verified income ( fixed + variable = total verified income )
                            DynamicUITable fixedObj = getObjectByTAG(TAG_NAME_FIXED, dynamicUITableList);
                            if (fixedObj != null) {
                                if (!TextUtils.isEmpty(fixedObj.getValue())) {
                                    int fixed = Integer.parseInt(fixedObj.getValue());
                                    int variable = 0;
                                    if (!s.toString().equalsIgnoreCase("")) {
                                        variable = Integer.parseInt(s.toString());
                                    }
                                    //int variable = Integer.parseInt(s.toString());
                                    DynamicUITable totalVerifiedIncomeObj = getObjectByTAG(TAG_NAME_TOTAL_VERIFIED_INCOME, dynamicUITableList);
                                    if (totalVerifiedIncomeObj != null) {
                                        int totalVerifiedIncome = fixed + variable;
                                        totalVerifiedIncomeObj.setValue(String.valueOf(totalVerifiedIncome));
                                        moveToNext = true;
                                        dynamicUITable.setValue(s.toString());
                                        textInputListener.callBackForUpdateSingleField(totalVerifiedIncomeObj);
                                    } else {
                                        error = "Unable to calculate total verified income";
                                    }
                                } else {
                                    error = "Please enter the fixed amount first";
                                }
                            } else {
                                moveToNext = true;
                            }
                        }// TODO: 23-10-2019 TO CALCULATE TOTAL BOUNCES
                        else if (tag.equalsIgnoreCase(TAG_NAME_INWARD_BOUNCES_IN_BANKING_HISTORY)) {
                            // TODO: For calculating total BOUNCES ( inwardBounces + outward bounces = total bounces )
                            DynamicUITable outwardBouncesObj = getObjectByTAG(TAG_NAME_OUTWARD_BOUNCES_IN_BANKING_HISTORY, dynamicUITableList);
                            if (outwardBouncesObj != null) {
                                DynamicUITable totalBouncesIncomeObj = getObjectByTAG(TAG_NAME_TOTAL_BOUNCES_IN_BANKING_HISTORY, dynamicUITableList);

                                if (!TextUtils.isEmpty(outwardBouncesObj.getValue())) {
                                    int outwardBounces = Integer.parseInt(outwardBouncesObj.getValue());
                                    int inwardBounces = 0;
                                    if (!s.toString().equalsIgnoreCase("")) {
                                        inwardBounces = Integer.parseInt(s.toString());
                                    }
                                    //int variable = Integer.parseInt(s.toString());
                                    if (totalBouncesIncomeObj != null) {
                                        if (!s.toString().equalsIgnoreCase("")) {
                                            int totalVerifiedIncome = inwardBounces + outwardBounces;
                                            totalBouncesIncomeObj.setValue(String.valueOf(totalVerifiedIncome));
                                            moveToNext = true;
                                        } else {
                                            totalBouncesIncomeObj.setValue(null);
                                        }
                                        dynamicUITable.setValue(s.toString());
                                        textInputListener.callBackForUpdateUI();
                                    } else {
                                        error = "Unable to calculate total bounces";
                                    }
                                } else {
                                    totalBouncesIncomeObj.setValue(null);

                                    dynamicUITable.setValue(s.toString());
                                    textInputListener.callBackForUpdateUI();
                                    // error = "Please enter the fixed amount first";
                                }
                            } else {
                                moveToNext = true;
                            }


                        } else if (tag.equalsIgnoreCase(TAG_NAME_OUTWARD_BOUNCES_IN_BANKING_HISTORY)) {
                            DynamicUITable inwardBouncesObj = getObjectByTAG(TAG_NAME_INWARD_BOUNCES_IN_BANKING_HISTORY, dynamicUITableList);
                            if (inwardBouncesObj != null) {
                                DynamicUITable totalBouncesObj = getObjectByTAG(TAG_NAME_TOTAL_BOUNCES_IN_BANKING_HISTORY, dynamicUITableList);

                                if (!TextUtils.isEmpty(inwardBouncesObj.getValue())) {
                                    int inwardBounces = Integer.parseInt(inwardBouncesObj.getValue());
                                    int outwarddBounces = 0;
                                    if (!s.toString().equalsIgnoreCase("")) {
                                        outwarddBounces = Integer.parseInt(s.toString());
                                    }
                                    //int variable = Integer.parseInt(s.toString());
                                    if (totalBouncesObj != null) {
                                        if (!s.toString().equalsIgnoreCase("")) {
                                            int totalBounces = inwardBounces + outwarddBounces;
                                            totalBouncesObj.setValue(String.valueOf(totalBounces));
                                            moveToNext = true;
                                        } else {
                                            totalBouncesObj.setValue(null);
                                        }
                                        dynamicUITable.setValue(s.toString());
                                        textInputListener.callBackForUpdateUI();
                                    } else {
                                        error = "Unable to calculate total bounces";
                                    }
                                } else {
                                    totalBouncesObj.setValue(null);
                                    dynamicUITable.setValue(s.toString());
                                    textInputListener.callBackForUpdateUI();
                                    // error = "Please enter the fixed amount first";
                                }
                            } else {
                                moveToNext = true;
                            }


                        } else if (viewParameters.getScreenID().equalsIgnoreCase(SCREEN_N0_SERVICE_ESTIMATE_DETAIL_MSME)) {
                            if (tag.equalsIgnoreCase(TAG_NAME_AVG_PRICE_PER_SERVICE_IN_SERVICE_ESTIMATE_DETAIL)
                                    || tag.equalsIgnoreCase(TAG_NAME_NO_OF_CUSTOMERS_IN_SERVICE_ESTIMATE_DETAIL) ){

                                DynamicUITable totalSalesObj = getObjectByTAG(TAG_NAME_TOTAL_SALES_IN_SERVICE_ESTIMATE_DETAIL, dynamicUITableList);
                                totalSalesObj.setValue(null);
                                dynamicUITable.setValue(s.toString());
                                textInputListener.callBackForUpdateUI();

                            }

                        } else if (viewParameters.getScreenID().equalsIgnoreCase(SCREEN_N0_SERVICE_RAW_MATERIAL_MSME)) {
                            if (tag.equalsIgnoreCase(TAG_NAME_QUANTITY_RAW_MATERIAL_PURCHASED_IN_SERVICE_RAW_MATERIAL)
                                    || tag.equalsIgnoreCase(TAG_NAME_PRICE_PER_UNIT_OF_RAW_MATERIAL_IN_SERVICE_RAW_MATERIAL)) {
                                // TODO: For calculating cost of raw material
                                //  ( Qty of Raw material* Price per unit of raw material = Cost of Raw Material)

                                DynamicUITable costOfRawMaterialObj = getObjectByTAG(TAG_NAME_COST_OF_RAW_MATERIAL_IN_SERVICE_RAW_MATERIAL, dynamicUITableList);
                                costOfRawMaterialObj.setValue(null);
                                dynamicUITable.setValue(s.toString());
                                textInputListener.callBackForUpdateUI();
                            }
                            else if (tag.equalsIgnoreCase(TAG_NAME_CORRESPONDING_UNITS_OF_SERVICE_IN_SERVICE_RAW_MATERIAL)) {
                                // TODO: For calculating cost of Material Cost Per Product
                                //  ( Cost of Raw Material/Corresponding units of product= Material Cost Per Product)
                                DynamicUITable materialCostObj = getObjectByTAG(TAG_NAME_MATERIAL_COST_PER_SERVICE_IN_SERVICE_RAW_MATERIAL, dynamicUITableList);

                                materialCostObj.setValue(null);
                                dynamicUITable.setValue(s.toString());
                                textInputListener.callBackForUpdateUI();


                            }

                        }
                        else if (tag.equalsIgnoreCase(TAG_NAME_VALUE_IN_DIRECT_BUSINESS_EXPENSE)) {
                            DynamicUITable frequencyObj = getObjectByTAG(TAG_NAME_FREQUENCY_IN_DIRECT_BUSINESS_EXPENSE, dynamicUITableList);
                            int frequencyvalue = 0;
                            int value = 0;
                            if (frequencyObj != null) {
                                if (!TextUtils.isEmpty(frequencyObj.getValue())) {
                                    //Daily:25,weekly:4,monthly:1
                                    if (frequencyObj.getValue().equalsIgnoreCase(FREQUENCY_DAILY)) {
                                        frequencyvalue = 25;
                                    } else if (frequencyObj.getValue().equalsIgnoreCase(FREQUENCY_WEEKLY)) {
                                        frequencyvalue = 4;
                                    } else if (frequencyObj.getValue().equalsIgnoreCase(FREQUENCY_MONTHLY)) {
                                        frequencyvalue = 1;
                                    }
                                }
                                DynamicUITable totalexpenseObj = getObjectByTAG(TAG_NAME_TOTAL_EXPENSES_IN_DIRECT_BUSINESS_EXPENSE, dynamicUITableList);

                                if (!s.toString().equalsIgnoreCase("")) {
                                    value = Integer.parseInt(s.toString());
                                }
                                if (totalexpenseObj != null) {
                                    if (!s.toString().equalsIgnoreCase("") && frequencyObj != null && !TextUtils.isEmpty(frequencyObj.getValue())) {
                                        int totalexpense = frequencyvalue * value;
                                        totalexpenseObj.setValue(String.valueOf(totalexpense));
                                        moveToNext = true;
                                    } else {
                                        totalexpenseObj.setValue(null);
                                    }

                                    dynamicUITable.setValue(s.toString());
                                    textInputListener.callBackForUpdateUI();

                                } else {
                                    error = "Unable to calculate total expense";
                                }
                            } else {
                                moveToNext = true;
                            }
                        } else if (tag.equalsIgnoreCase(TAG_NAME_GOOD_DAILY)
                                || tag.equalsIgnoreCase(TAG_NAME_REGULAR_DAILY)
                                || tag.equalsIgnoreCase(TAG_NAME_BAD_DAILY)
                                || tag.equalsIgnoreCase(TAG_NAME_NO_OF_GOOD_DAYS_IN_A_WEEK)
                                || tag.equalsIgnoreCase(TAG_NAME_NO_OF_REGULAR_DAYS_IN_A_WEEK)
                                || tag.equalsIgnoreCase(TAG_NAME_NO_OF_BAD_DAYS_IN_A_WEEK)) {
                            viewParameters.setValue(s.toString());
                            // TODO: For calculating declared sales weekly ( no of days ==> good + regular + bad <= 7 )
                            textInputListener.callBackForCalcAvgSales(viewParameters, tag, TAG_NAME_AVERAGE_WEEKLY_SALES, 7);
                        } else if (tag.equalsIgnoreCase(TAG_NAME_GOOD_WEEKLY)
                                || tag.equalsIgnoreCase(TAG_NAME_REGULAR_WEEKLY)
                                || tag.equalsIgnoreCase(TAG_NAME_BAD_WEEKLY)
                                || tag.equalsIgnoreCase(TAG_NAME_NO_OF_GOOD_WEEKS_IN_A_MONTH)
                                || tag.equalsIgnoreCase(TAG_NAME_NO_OF_REGULAR_WEEKS_IN_A_MONTH)
                                || tag.equalsIgnoreCase(TAG_NAME_NO_OF_BAD_WEEKS_IN_A_MONTH)) {
                            viewParameters.setValue(s.toString());
                            // TODO: For calculating declared sales monthly ( no of days ==> good + regular + bad <= 4 )
                            textInputListener.callBackForCalcAvgSales(viewParameters, tag, TAG_NAME_AVERAGE_MONTHLY_SALES, 4);
                        } else if (tag.equalsIgnoreCase(TAG_NAME_GOOD_YEARLY)
                                || tag.equalsIgnoreCase(TAG_NAME_REGULAR_YEARLY)
                                || tag.equalsIgnoreCase(TAG_NAME_BAD_YEARLY)
                                || tag.equalsIgnoreCase(TAG_NAME_NO_OF_GOOD_MONTHS_IN_AN_YEAR)
                                || tag.equalsIgnoreCase(TAG_NAME_NO_OF_REGULAR_MONTHS_IN_AN_YEAR)
                                || tag.equalsIgnoreCase(TAG_NAME_NO_OF_BAD_MONTHS_IN_AN_YEAR)) {
                            viewParameters.setValue(s.toString());
                            // TODO: For calculating declared sales yearly ( no of days ==> good + regular + bad <= 12  )
                            textInputListener.callBackForCalcAvgSales(viewParameters, tag, TAG_NAME_AVERAGE_YEARLY_SALES, 12);
                        } else if (tag.equalsIgnoreCase(TAG_NAME_PURCHASES_IN_PURCHASE_ANALYSIS)) {
                            // TODO: Calculate Purchase Analysis
                            viewParameters.setValue(s.toString());
                            textInputListener.callBackForCalculatePurchaseAnalysis(viewParameters);
                        } else if (viewParameters.getScreenName().equalsIgnoreCase(SCREEN_NAME_PURCHASE_BILLS_MSME)
                                && tag.equalsIgnoreCase(TAG_NAME_MARGIN_IN_PURCHASE_BILLS)) {
                            // TODO: Calculate Purchase Bills
                            if(!TextUtils.isEmpty(s) && !(s.toString().equalsIgnoreCase("0")))
                                textInputListener.callBackForPurchaseBillsCalculation(viewParameters);
                        } else if (viewParameters.getScreenName().equalsIgnoreCase(SCREEN_NAME_GST_MSME)) {
                            // TODO: GST CALCULATION
                            textInputListener.callBackForGSTCalculation(dynamicUITable);
                        }
                    }
                }
                Log.i(TAG, "ERROR VALUE ---> " + error);
                mCustomTextInputLayout.setErrorMsg(error);
                mCustomTextInputLayout.setHasValidInput(moveToNext);
                dynamicUITable.setErrorMessage(error);
                if (!TextUtils.isEmpty(error)) {
                    dynamicUITable.setValid(false);
                } else {
                    dynamicUITable.setValid(true);
                }
                if(!tag.equalsIgnoreCase(TAG_NAME_TWO_WHEELER_ON_ROAD_PRICE)) {
                    onValidated(moveToNext, mCustomTextInputLayout, mCustomTextInputLayout.getEditText().getText().toString().trim(),
                            mCustomTextInputLayout.getTag());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }

        protected abstract void onValidated(boolean moveToNext,
                                            CustomTextInputLayout customTextInputLayout,
                                            String text, Object tag);
    }

    private int getIdentifier(String tag) {
        int id = -1;
        try {
            id = context.getResources().getIdentifier(tag, "string", context.getPackageName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }


    //This function to convert DPs to pixels
    private int convertDpToPixel(float dp) {
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        float px = dp * (metrics.densityDpi / 160f);
        return Math.round(px);
    }

    public interface TextInputListener {
        void callBackForEnableGuardianDetails();

        void callBackForDisableGuardianDetails();

        void callBackForUpdateUI();

        void callBackForUpdateSingleField(DynamicUITable dynamicUITable);

        void callBackForAutoSum(DynamicUITable dynamicUITable, String toTag);

        void callBackForSumOfAllFieldsByFeatureId(DynamicUITable dynamicUITable, String resultTag);

        void callBackForCalcAvgSales(DynamicUITable dynamicUITable, String tag, String tagToDisplayResult, int totalNo);

        void callBackForCalculatePurchaseAnalysis(DynamicUITable dynamicUITable);

        void callBackForPurchaseBillsCalculation(DynamicUITable dynamicUITable);

        void callBackForGSTCalculation(DynamicUITable dynamicUITable);

        void callBackForDeDupeValidation(DynamicUITable dynamicUITable, View view);

        void callBackForIFSC(DynamicUITable dynamicUITable, View view);

        void callBackForLoanAmountValidation(DynamicUITable dynamicUITable, View view);

        void callBackForAgeValidationJLG(DynamicUITable dynamicUITable, View view);

        void callBackForEditTextValue(Object o1, Object o2, Object o3);
    }
}
