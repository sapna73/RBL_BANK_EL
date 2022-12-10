package com.swadhaar.los.dynamicui.CustomFields;

import android.content.Context;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.DigitsKeyListener;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.bfil.uilibrary.helpers.AppConstants;
import com.bfil.uilibrary.helpers.AppHelper;
import com.bfil.uilibrary.helpers.Verhoeff;
import com.bfil.uilibrary.widgets.textInputLayout.CustomTextInputLayout;
import com.bfil.uilibrary.widgets.textInputLayout.LengthTextWatcher;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.swadhaar.los.R;
import com.swadhaar.los.database.entity.DynamicUITable;

import java.util.List;

import static com.swadhaar.los.constants.AppConstant.LOAN_NAME_INDIVIDUAL;
import static com.swadhaar.los.constants.AppConstant.LOAN_NAME_JLG;
import static com.swadhaar.los.constants.AppConstant.LOAN_NAME_MSME;
import static com.swadhaar.los.constants.AppConstant.LOAN_NAME_TW;
import static com.swadhaar.los.dynamicui.constants.ParametersConstant.DATATYPE_ALL;
import static com.swadhaar.los.dynamicui.constants.ParametersConstant.DATATYPE_ALPHA;
import static com.swadhaar.los.dynamicui.constants.ParametersConstant.DATATYPE_ALPHA_NUMERIC;
import static com.swadhaar.los.dynamicui.constants.ParametersConstant.DATATYPE_INT;
import static com.swadhaar.los.dynamicui.constants.ParametersConstant.DATATYPE_NUMERIC;
import static com.swadhaar.los.dynamicui.constants.ParametersConstant.DATATYPE_STRING;
import static com.swadhaar.los.dynamicui.constants.ParametersConstant.TAG_NAME_CONTAINS_NAME;
import static com.swadhaar.los.dynamicui.constants.ParametersConstant.TAG_NAME_DECLARED_MONTHLY_REPAYMENT_CAPACITY;
import static com.swadhaar.los.dynamicui.constants.ParametersConstant.TAG_NAME_KYC_TYPE_BANKPASSBOOK;
import static com.swadhaar.los.dynamicui.constants.ParametersConstant.TAG_NAME_KYC_TYPE_DRIVINGLICENSE;
import static com.swadhaar.los.dynamicui.constants.ParametersConstant.TAG_NAME_KYC_TYPE_JOBCARD;
import static com.swadhaar.los.dynamicui.constants.ParametersConstant.TAG_NAME_KYC_TYPE_NREGAJOBCARD;
import static com.swadhaar.los.dynamicui.constants.ParametersConstant.TAG_NAME_KYC_TYPE_PASSPORT;
import static com.swadhaar.los.dynamicui.constants.ParametersConstant.TAG_NAME_KYC_TYPE_RE_ENTER_DRIVINGLICENSE;
import static com.swadhaar.los.dynamicui.constants.ParametersConstant.TAG_NAME_REQUESTED_LOAN_AMOUNT;

public class XMLCustomTextviewWithValue extends CustomTextInputLayout {
    Context context;
    AppHelper appHelper;
    DynamicUITable dynamicUITable;
    List<DynamicUITable> dynamicUITableList;

    public XMLCustomTextviewWithValue(Context context, DynamicUITable viewParameters, AppHelper appHelp, List<DynamicUITable> dynamicUITableList) {
        super(context);
        this.context=context;
        appHelper=appHelp;
        this.dynamicUITable=viewParameters;
        this.dynamicUITableList=dynamicUITableList;

        try {

            LayoutParams lLayout = new LayoutParams(
                    LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
            setOrientation(VERTICAL);
            setLayoutParams(lLayout);
            setTag(viewParameters);
            setPadding(5,0,0,0);
            setBoxBackgroundMode(TextInputLayout.BOX_BACKGROUND_OUTLINE);
            setBoxCornerRadii(5, 5, 5, 5);
//            ContextThemeWrapper styleContext = new ContextThemeWrapper(context, R.style.CustomEditText);
            final TextView textViewLable = new TextView(context);
            textViewLable.setLayoutParams(lLayout);
            textViewLable.setTextColor(context.getResources().getColor(R.color.black));
//            editText.setLongClickable(false);
            /* SETTING INPUT TYPE */
//            setInputType(editText,viewParameters);

            /* SETTING INPUT TEXT */
            setInputText(textViewLable,viewParameters);
            /* Adding EditText in View */
            this.addView(textViewLable);

            final TextView textViewValue = new TextView(context);
            textViewValue.setLayoutParams(lLayout);
            textViewValue.setTextColor(context.getResources().getColor(R.color.black));
            setInputText(textViewValue,viewParameters);
            this.addView(textViewValue);

            setHint(viewParameters.getHint()); // TODO: 09-04-2019 need to change
            if(TextUtils.isEmpty(viewParameters.getHint())){
                setHint(viewParameters.getFieldName()); // TODO: 09-04-2019 need to change
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setInputText(TextView editText, DynamicUITable viewParameters) {
        try{
//            String inputText="";
            // TODO: No need to check this condition
//            if (!TextUtils.isEmpty(viewParameters.getIsRequired()) && viewParameters.getIsRequired().equalsIgnoreCase(VIEW_REQUIRED)){
                // TODO: 03-04-2019 need to check empty condition
                setTag(viewParameters);
                editText.setTag(viewParameters.getFieldName());
                if(viewParameters.getValue()!=null) {
                    editText.setText(viewParameters.getValue());
                }
                if(!viewParameters.isEditable()){
                    editText.setEnabled(viewParameters.isEditable());
                }
//            }
//            else {
//                // TODO: Need to check this condition
//                setTag(null);
//                editText.setTag(null);
//            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private void setInputType(TextInputEditText editText, DynamicUITable viewParameters) {
        try{
            if (!TextUtils.isEmpty(viewParameters.getDataType()) && viewParameters.getDataType().equalsIgnoreCase(DATATYPE_STRING))
            {
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
                }else  if (!TextUtils.isEmpty(viewParameters.getDataType()) && viewParameters.getDataEntryType()==DATATYPE_ALL){
                    // TODO: ALL CHARACTERS
                    editText.setInputType(InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS);
                }
            }
           else if (!TextUtils.isEmpty(viewParameters.getDataType()) && viewParameters.getDataType().equalsIgnoreCase(DATATYPE_INT)
            ||viewParameters.getDataEntryType()==DATATYPE_NUMERIC){
                // TODO: ONLY NUMERIC
                editText.setInputType(InputType.TYPE_CLASS_NUMBER);
           }else  if (!TextUtils.isEmpty(viewParameters.getDataType()) && viewParameters.getDataEntryType()==DATATYPE_ALL){
                // TODO: ALL CHARACTERS
                editText.setInputType(InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS);
            }

        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public XMLCustomTextviewWithValue(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public XMLCustomTextviewWithValue(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public abstract class CustomTextWatcher implements TextWatcher{

        private final String TAG = LengthTextWatcher.class.getCanonicalName();

        private CustomTextInputLayout mCustomTextInputLayout;
        private String currentText = "";
        private CharSequence hint = "";
        private CharSequence hintWithAsterisk = "";

        public CustomTextWatcher(final Context context, CustomTextInputLayout customTextInputLayout) {
            try {
                this.mCustomTextInputLayout = customTextInputLayout;
                if(mCustomTextInputLayout!=null && mCustomTextInputLayout.getEditText().getText()!=null){
                currentText = mCustomTextInputLayout.getEditText().getText().toString().trim();
                }
                hintWithAsterisk = mCustomTextInputLayout.getHint();
                String str = hintWithAsterisk.toString();
                if (mCustomTextInputLayout.isMandatory()){
                    hint = str.subSequence(0, str.length() - 2);
                } else {
                    hint = str;
                }
                if (TextUtils.isEmpty(currentText)){
                    mCustomTextInputLayout.setHint(null);
                    mCustomTextInputLayout.getEditText().setHint(hintWithAsterisk);
                }
                mCustomTextInputLayout.getEditText().setOnFocusChangeListener(new OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {
                        try {
                            if (!hasFocus) {
                                if (TextUtils.isEmpty(currentText)){
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
                    iLength =Integer.parseInt(viewParameters.getLength());
                }

                if (!TextUtils.isEmpty(tag)) {
                    if (s.length() > 0) {
//                        if (s.length() < iLength) {
////                            error = context.getString(getIdentifier(tag + "_LENGTH_ERROR"));
//                            // TODO: 10-04-2019 need to add this conditon properly
//                            error = viewParameters.getFieldTag()+ " should be "+viewParameters.getLength()+" digit";
//                        } else {
                            if (tag.equalsIgnoreCase("AADHAAR") || tag.equalsIgnoreCase("VID")) {
                                if (!Verhoeff.validateVerhoeff(s.toString())) {
//                                    error = context.getString(getIdentifier(tag + "_INVALID_ERROR"));
                                    error = viewParameters.getFieldTag()+ " invalid";
                                } else {
                                    moveToNext = true;
                                }
                            } else if (tag.equalsIgnoreCase("MOBILE")) {
                                if (!appHelper.patternMatch(AppConstants.REGEX_PATTERN_MOBILE_NUMBER, s.toString())) {
//                                    error = context.getString(getIdentifier(tag + "_INVALID_ERROR"));
                                    error = viewParameters.getFieldTag()+ " invalid";
                                } else {
                                    moveToNext = true;
                                }
                            } else if (tag.equalsIgnoreCase("PANCARD")) {
                                if (!appHelper.patternMatch(AppConstants.REGEX_PATTERN_PANCARD, s.toString())) {
                                    error = viewParameters.getFieldTag()+ " invalid";
                                } else {
                                    moveToNext = true;
                                }
                            } else if (tag.equalsIgnoreCase("VOTERID")) {
                                if (!appHelper.patternMatch(AppConstants.REGEX_PATTERN_VOTERID, s.toString())) {
                                    error = viewParameters.getFieldTag()+ " invalid";
                                } else {
                                    moveToNext = true;
                                }
                            }else if (tag.equalsIgnoreCase(TAG_NAME_KYC_TYPE_DRIVINGLICENSE)
                                        ||tag.equalsIgnoreCase(TAG_NAME_KYC_TYPE_RE_ENTER_DRIVINGLICENSE)
                                        ||tag.equalsIgnoreCase(TAG_NAME_KYC_TYPE_PASSPORT)
                                        ||tag.equalsIgnoreCase(TAG_NAME_KYC_TYPE_BANKPASSBOOK)
                                        ||tag.equalsIgnoreCase(TAG_NAME_KYC_TYPE_JOBCARD)
                                        ||tag.equalsIgnoreCase(TAG_NAME_KYC_TYPE_NREGAJOBCARD)
                                        ||tag.contains(TAG_NAME_CONTAINS_NAME) // TODO: contains
                                        ||tag.equalsIgnoreCase(TAG_NAME_KYC_TYPE_DRIVINGLICENSE)
                            ) {
                                if (appHelper.patternMatch(AppConstants.REGEX_PATTERN_CONTINEOUS_THREE_CHARACTERS, s.toString())) {
                                    error = viewParameters.getFieldTag()+ " invalid";
                                } else {
                                    moveToNext = true;
                                }
                            } else if (tag.equalsIgnoreCase("AGE")) {
                                if (viewParameters.getLoanType().equalsIgnoreCase(LOAN_NAME_JLG)||
                                        viewParameters.getLoanType().equalsIgnoreCase(LOAN_NAME_INDIVIDUAL)||
                                        viewParameters.getLoanType().equalsIgnoreCase(LOAN_NAME_TW) ){
                                    if (Integer.parseInt(s.toString()) < 18) {
                                        error = "Age must be greater than 18 years";
                                    } else if (Integer.parseInt(s.toString()) > 57) {
                                        error = "Age must be less than 57 years";
                                    } else {
                                        moveToNext = true;
                                    }
                                }else if (viewParameters.getLoanType().equalsIgnoreCase(LOAN_NAME_MSME)){
                                    if (Integer.parseInt(s.toString()) < 21) {
                                        error = "Age must be greater than 21 years";
                                    } else if (Integer.parseInt(s.toString()) > 63) {
                                        error = "Age must be less than 63 years";
                                    } else {
                                        moveToNext = true;
                                    }
                                }
                            } else if (tag.equalsIgnoreCase("VINTAGE")) {
                                if (Integer.parseInt(s.toString()) == 0 || Integer.parseInt(s.toString()) > 11) {
                                    error = viewParameters.getFieldTag()+ " invalid";
                                } else {
                                    moveToNext = true;
                                }
                            } else if (tag.equalsIgnoreCase("AREA")) {
                                if (Integer.parseInt(s.toString()) < 50) {
                                    error = viewParameters.getFieldTag()+ " invalid";
                                } else {
                                    moveToNext = true;
                                }
                            }else if (tag.equalsIgnoreCase(TAG_NAME_DECLARED_MONTHLY_REPAYMENT_CAPACITY)) {
                                DynamicUITable reqLoanAmtObj=getObjectByTAG(TAG_NAME_REQUESTED_LOAN_AMOUNT,dynamicUITableList);
                                if(reqLoanAmtObj !=null) {
                                    if(!TextUtils.isEmpty(reqLoanAmtObj.getValue())) {
                                        int loanAmtValue = Integer.parseInt(reqLoanAmtObj.getValue());
                                        if (Integer.parseInt(s.toString()) > loanAmtValue) {
                                            error ="Declared monthly repayment cannot be more than requested loan amount";
                                        } else {
                                            moveToNext = true;
                                        }

                                    }else{
                                        error = "Please enter the requested loan amount first" ;
                                    }
                                }else{
                                    moveToNext=true;
                                }
                            } else {
                                moveToNext = true;
                            }
                        }
                    }
                // TODO: Need to check this condition
                    /*else {
                        error = context.getString(com.bfil.uilibrary.R.string.EMPTY_ERROR);
                    }*/
               /* } else {
                    moveToNext = true;
                }*/
                Log.i(TAG, "ERROR VALUE ---> " + error);
                mCustomTextInputLayout.setErrorMsg(error);
                mCustomTextInputLayout.setHasValidInput(moveToNext);
                onValidated(moveToNext, mCustomTextInputLayout, mCustomTextInputLayout.getEditText().getText().toString().trim(),
                        mCustomTextInputLayout.getTag());
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

    public DynamicUITable getObjectByTAG(String tagNameKycType, List<DynamicUITable> viewParametersList) {
        DynamicUITable dynamicUITable1 = null;
        try {
            for (DynamicUITable dynamicUITable : viewParametersList) {
                if (dynamicUITable != null && !TextUtils.isEmpty(dynamicUITable.getFieldTag())
                        && dynamicUITable.getFieldTag().trim().equalsIgnoreCase(tagNameKycType)) {

                    dynamicUITable1 = dynamicUITable;
                    break;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return dynamicUITable1;
    }
}
