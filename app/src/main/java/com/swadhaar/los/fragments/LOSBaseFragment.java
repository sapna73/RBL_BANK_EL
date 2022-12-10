package com.swadhaar.los.fragments;


import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.bfil.ekyclibrary.models.AadharData;
import com.bfil.ekyclibrary.models.ConfigurationDto;
import com.bfil.ekyclibrary.models.TransactionDto;
import com.bfil.uilibrary.dialogs.ConfirmationDialog;
import com.bfil.uilibrary.helpers.AppConstants;
import com.bfil.uilibrary.helpers.AppHelper;
import com.bfil.uilibrary.helpers.Verhoeff;
import com.bfil.uilibrary.widgets.customSpinner.SearchableSpinner;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.precision.csp.aua.client.PauaAuth;
import com.precision.csp.authapi.common.RequestType;
import com.precision.rdservice.CaptureResponse;
import com.precision.rdservice.DeviceInfo;
import com.swadhaar.los.R;
import com.swadhaar.los.activities.BaseActivity;
import com.swadhaar.los.activities.CreateNewCenterActivity;
import com.swadhaar.los.activities.TargetDetailsActivity;
import com.swadhaar.los.api.DynamicUIWebservice;
import com.swadhaar.los.constants.AppConstant;
import com.swadhaar.los.database.dao.DynamicUIDao;
import com.swadhaar.los.database.entity.CBCheckTable;
import com.swadhaar.los.database.entity.CenterCreationTable;
import com.swadhaar.los.database.entity.DynamicUITable;
import com.swadhaar.los.database.entity.LeadTable;
import com.swadhaar.los.database.entity.ProductMasterTable;
import com.swadhaar.los.database.entity.RawDataTable;
import com.swadhaar.los.database.entity.SubmitDataTable;
import com.swadhaar.los.dynamicui.CustomFields.XMLCustomSeekBar;
import com.swadhaar.los.dynamicui.CustomFields.XMLCustomSpinner;
import com.swadhaar.los.dynamicui.CustomFields.XMLCustomTIL;
import com.swadhaar.los.dynamicui.CustomFields.XMLCustomView;
import com.swadhaar.los.dynamicui.CustomFields.XMLPinView;
import com.swadhaar.los.dynamicui.CustomFields.XmlCustomCheckBox;
import com.swadhaar.los.dynamicui.CustomFields.XmlCustomRadioGroup;

import com.swadhaar.los.dynamicui.constants.ParametersConstant;
import com.swadhaar.los.dynamicui.fragments.BaseFragment;
import com.swadhaar.los.dynamicui.services.DynamicUIWebService;
import com.swadhaar.los.fragments.balancesheet.BusinessAssetsFragment;
import com.swadhaar.los.fragments.balancesheet.BusinessLiabilitiesFragment;
import com.swadhaar.los.fragments.balancesheet.HouseLiabilitiesFragment;
import com.swadhaar.los.fragments.balancesheet.msme.BusinessAssertsMsmeFragment;
import com.swadhaar.los.fragments.balancesheet.msme.BusinessLiabilitiesMsmeFragment;
import com.swadhaar.los.fragments.balancesheet.msme.HouseAssetsMsmeFragment;
import com.swadhaar.los.fragments.balancesheet.msme.HouseLiabilitiesMsmeFragment;
import com.swadhaar.los.fragments.decisions.msme.CashFlowSummaryMSMEFragment;
import com.swadhaar.los.fragments.decisions.msme.LoanApprovalMSMEFragment;
import com.swadhaar.los.fragments.decisions.msme.MortgageMSMEFragment;
import com.swadhaar.los.fragments.decisions.ObservationsFragment;
import com.swadhaar.los.fragments.decisions.msme.HypothecationMsmeFragment;
import com.swadhaar.los.fragments.decisions.msme.SubjectToConditionsMsmeFragment;
import com.swadhaar.los.fragments.incomeassessment.DeclaredSalesWeeklyFragment;
import com.swadhaar.los.fragments.incomeassessment.DeclaredSalesYearlyFragment;
import com.swadhaar.los.fragments.incomeassessment.HouseExpensesFragment;
import com.swadhaar.los.fragments.incomeassessment.HouseIncomeFragment;
import com.swadhaar.los.fragments.incomeassessment.OperatingExpenseFragment;
import com.swadhaar.los.fragments.incomeassessment.ProductEstimateFragment;
import com.swadhaar.los.fragments.incomeassessment.PurchaseAnalysisFragment;
import com.swadhaar.los.fragments.incomeassessment.SummaryFragment;
import com.swadhaar.los.fragments.incomeassessment.msme.BankingHistoryFragment;
import com.swadhaar.los.fragments.incomeassessment.msme.DirectBusinessExpenseFragment;
import com.swadhaar.los.fragments.incomeassessment.msme.GSTFragment;
import com.swadhaar.los.fragments.incomeassessment.msme.GeneralIncomeFragment;
import com.swadhaar.los.fragments.incomeassessment.msme.ITRFragment;
import com.swadhaar.los.fragments.incomeassessment.msme.IncomeAssessmentSummaryMsmeFragment;
import com.swadhaar.los.fragments.incomeassessment.msme.OtherIncomeSourceFragment;
import com.swadhaar.los.fragments.incomeassessment.msme.ProductEstimateDetailFragment;
import com.swadhaar.los.fragments.incomeassessment.msme.ProductEstimateMSMEFragment;
import com.swadhaar.los.fragments.incomeassessment.msme.PurchaseBillsFragment;
import com.swadhaar.los.fragments.incomeassessment.msme.SalesBillsFragment;
import com.swadhaar.los.fragments.incomeassessment.msme.ServiceEstimateDetailFragment;
import com.swadhaar.los.fragments.incomeassessment.msme.ServiceEstimateFragment;
import com.swadhaar.los.fragments.loanproposal.ApplicantLoanProposalFragment;
import com.swadhaar.los.fragments.loanproposal.LoanProposalSummaryFragment;
import com.swadhaar.los.fragments.references.ReferencesFragment;
import com.swadhaar.los.interfce.FragmentToActivityInterface;
import com.swadhaar.los.models.ApiResponse;
import com.swadhaar.los.models.DataTypeInfo;
import com.swadhaar.los.models.EKYCResponseDTO;
import com.swadhaar.los.models.EKYCRootDTO;
import com.swadhaar.los.models.EkycRequest;
import com.swadhaar.los.models.ParameterInfo;
import com.swadhaar.los.models.PincodeParameterInfo;
import com.swadhaar.los.models.PincodeResponseDTO;
import com.swadhaar.los.models.RequestString;
import com.swadhaar.los.repositories.DynamicUIRepository;
import com.swadhaar.los.view_models.DynamicUIViewModel;

import org.json.JSONObject;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Executor;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.HEAD;

import static com.bfil.uilibrary.helpers.AppConstants.ACCOUNT_NUMBER_VALIDATION;
import static com.swadhaar.los.constants.AppConstant.*;
import static com.swadhaar.los.constants.AppConstant.LOAN_NAME_MSME;
import static com.swadhaar.los.constants.AppConstant.setKeyValueForObject;
import static com.swadhaar.los.dynamicui.constants.ParametersConstant.*;
import static com.swadhaar.los.dynamicui.constants.ParametersConstant.FIELD_NAME_ADD_ADVANCES_OR_ARREARS;
import static com.swadhaar.los.dynamicui.constants.ParametersConstant.FIELD_NAME_ADD_LIABILITY;
import static com.swadhaar.los.dynamicui.constants.ParametersConstant.FIELD_NAME_ADD_PRODUCT_OR_INVENTORY;
import static com.swadhaar.los.dynamicui.constants.ParametersConstant.FIELD_NAME_SUBMIT;
import static com.swadhaar.los.dynamicui.constants.ParametersConstant.TAG_NAME_CALCULATE_BUTTON;


public class LOSBaseFragment extends BaseFragment {

    private static final String TAG = LOSBaseFragment.class.getCanonicalName();
    private DynamiUIinterfacce dynamiUIinterfacce;
    private RawDataTable rawDataFronRawDataTable=null;
//   public List<DynamicUITable> baseViewParameterList;

    @Inject
    public ViewModelProvider.Factory viewModelFactory;
    public DynamicUIViewModel viewModel;

    public void setDynamiUIinterfacce(DynamiUIinterfacce dynamiUIinterfacce) {
        this.dynamiUIinterfacce = dynamiUIinterfacce;
    }

    public interface DynamiUIinterfacce {
        void dynamicUICallback(List<DynamicUITable> viewParametersList);

        Fragment getFragment();
    }


    public void dynamicUI(final List<DynamicUITable> viewParametersList) {
        try {
            dynamicViews = new ArrayList<>();
            ll.removeAllViews();
            for (final DynamicUITable viewParameters : viewParametersList) {

                // TODO: TEXT BOX
                if ((!TextUtils.isEmpty(viewParameters.getFieldType()) && viewParameters.getFieldType().
                        equalsIgnoreCase(FIELD_TYPE_TEXT_BOX)
                        || viewParameters.getFieldType().
                        equalsIgnoreCase(FIELD_TYPE_TEXT_LABEL_WITH_TEXT_BOX)
                        || viewParameters.getFieldType().
                        equalsIgnoreCase(FIELD_TYPE_CALENDAR))) { // TODO: TEXT BOX && CALENDAR && LABEL WITH TEXT BOX
                    final XMLCustomTIL customTextInputLayout = new XMLCustomTIL(getActivity(), viewParameters, appHelper,
                            viewParametersList, new XMLCustomTIL.TextInputListener() {
                        @Override
                        public void callBackForEnableGuardianDetails() {
                            List<ParameterInfo> parameterInfoList = new ArrayList<>();
                            parameterInfoList.add(new ParameterInfo(TAG_NAME_GUARDIAN_DETAIL, SCREEN_ID, "", true, true));
                            parameterInfoList.add(new ParameterInfo(TAG_NAME_GUARDIAN_FULL_NAME, SCREEN_ID, "", true, true));
                            parameterInfoList.add(new ParameterInfo(TAG_NAME_GUARDIAN_KYC_TYPE, SCREEN_ID, "", true, true));
                            parameterInfoList.add(new ParameterInfo(TAG_NAME_GUARDIAN_KYC_ID, SCREEN_ID, "", true, true));
                            parameterInfoList.add(new ParameterInfo(TAG_NAME_GUARDIAN_DATE_OF_BIRTH, SCREEN_ID, "", true, true));
                            parameterInfoList.add(new ParameterInfo(TAG_NAME_GUARDIAN_AGE, SCREEN_ID, "", true, true));
                            parameterInfoList.add(new ParameterInfo(TAG_NAME_GUARDIAN_GENDER, SCREEN_ID, "", true, true));
                            EnableOrDisableByFieldNameInDB(parameterInfoList, viewParametersList);

                        }

                        @Override
                        public void callBackForDisableGuardianDetails() {
                            List<ParameterInfo> parameterInfoList = new ArrayList<>();
                            parameterInfoList.add(new ParameterInfo(TAG_NAME_GUARDIAN_DETAIL, SCREEN_ID, "", false, true));
                            parameterInfoList.add(new ParameterInfo(TAG_NAME_GUARDIAN_FULL_NAME, SCREEN_ID, "", false, true));
                            parameterInfoList.add(new ParameterInfo(TAG_NAME_GUARDIAN_KYC_TYPE, SCREEN_ID, "", false, true));
                            parameterInfoList.add(new ParameterInfo(TAG_NAME_GUARDIAN_KYC_ID, SCREEN_ID, "", false, true));
                            parameterInfoList.add(new ParameterInfo(TAG_NAME_GUARDIAN_DATE_OF_BIRTH, SCREEN_ID, "", false, true));
                            parameterInfoList.add(new ParameterInfo(TAG_NAME_GUARDIAN_AGE, SCREEN_ID, "", false, true));
                            parameterInfoList.add(new ParameterInfo(TAG_NAME_GUARDIAN_GENDER, SCREEN_ID, "", false, true));
                            EnableOrDisableByFieldNameInDB(parameterInfoList, viewParametersList);
                        }

                        @Override
                        public void callBackForUpdateUI() {

//                            updateDynamicUITable(viewParametersList,SCREEN_ID);
                            dynamicUI(viewParametersList);
                        }

                        @Override
                        public void callBackForAutoSum(DynamicUITable dynamicUITable, String toTag) {
                            try {
                                autoSumFields(dynamicUITable, toTag, dynamicUITable.getScreenID(), dynamicUITable.getScreenID());
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }

                        @Override
                        public void callBackForUpdateSingleField(DynamicUITable dynamicUITable) {
                            try {
                                setValueForSingleFieldInUI(dynamicUITable, viewParametersList);
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }

                        @Override
                        public void callBackForSumOfAllFieldsByFeatureId(DynamicUITable dynamicUITable, String resultTag) {
                            try {
                                sumOfAllFieldsByFeatureId(dynamicUITable, viewParametersList, resultTag);
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }

                        @Override
                        public void callBackForCalcAvgSales(DynamicUITable dynamicUITable, String tag, String tagToDisplayResult
                                , int totalNo) {
                            try {
                                calculateAvgSales(viewParametersList, dynamicUITable, tag, tagToDisplayResult, totalNo);
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }

                        @Override
                        public void callBackForCalculatePurchaseAnalysis(DynamicUITable dynamicUITable) {
                            try {
                                calculatePurchaseFrequency(dynamicUITable, viewParametersList);
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }

                        @Override
                        public void callBackForPurchaseBillsCalculation(DynamicUITable dynamicUITable) {
                            try {
                                purchaseBillsCalculation(dynamicUITable, viewParametersList);
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }

                        @Override
                        public void callBackForGSTCalculation(DynamicUITable dynamicUITable) {
                            try {
                                GSTCalculationByFeatureId(dynamicUITable, viewParametersList, TAG_NAME_MONTHLY_SALES_IN_GST_SCREEN);
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }


                        @Override
                        public void callBackForIFSC(DynamicUITable dynamicUITable, View view) {
                            try {
                                getIFSCDataFromServer(dynamicUITable, viewParametersList);
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }
                        @Override
                        public void callBackForDeDupeValidation(DynamicUITable dynamicUITable, View view) {
                            try {

                                // TODO: OLD LOGIC
                             /*   if (SCREEN_NAME.equalsIgnoreCase(SCREEN_NAME_CO_APPLICANT_KYC)) {
//                                    dedupeValidationForKYCId(SCREEN_NAME_APPLICANT_KYC, SCREEN_ID, dynamicUITable.getValue(), dynamicUITable.getFieldTag(),
//                                            dynamicUITable, viewParametersList);
                                    dedupeValidationForKYCIdforcoapplicant(SCREEN_NAME_APPLICANT_KYC, SCREEN_NAME_CO_APPLICANT_KYC, dynamicUITable.getValue(), dynamicUITable.getFieldTag(), dynamicUITable.getModuleType(),
                                            dynamicUITable, viewParametersList);
                                } else if (SCREEN_NAME.equalsIgnoreCase(SCREEN_NAME_COAPPLICANT_PERSONAL_DETAIL)) {
                                    dedupeValidationForKYCIdforcoapplicant(SCREEN_NAME_PERSONAL_DETAIL, SCREEN_NAME_COAPPLICANT_PERSONAL_DETAIL, dynamicUITable.getValue(), dynamicUITable.getFieldTag(), dynamicUITable.getModuleType(),
                                            dynamicUITable, viewParametersList);
                                }else if (SCREEN_NAME.equalsIgnoreCase(SCREEN_NAME_APPLICANT_KYC)) {
                                    dedupeValidationForKYCId(SCREEN_NAME_APPLICANT_KYC, SCREEN_ID, dynamicUITable.getValue(), dynamicUITable.getFieldTag(),
                                            dynamicUITable, viewParametersList);
                                } else if (SCREEN_NAME.equalsIgnoreCase(SCREEN_NAME_CO_APPLICANT_BANK_DETAILS)) {
                                    dedupeValidation(SCREEN_NO_BANK_DETAILS_IL, dynamicUITable.getValue(), dynamicUITable.getFieldTag(),
                                            dynamicUITable, viewParametersList);
                                } else {
                                    dedupeValidation(SCREEN_ID, dynamicUITable.getValue(), dynamicUITable.getFieldTag(),
                                            dynamicUITable, viewParametersList);
                                }
                                */

                                // TODO: If it is valid and kyc type is RE ENTER AADHAAR then call Aadhaar Vault validation
                                if ((dynamicUITable.getFieldTag().equalsIgnoreCase(TAG_NAME_KYC_TYPE_AADHAAR)
                                        || dynamicUITable.getFieldTag().equalsIgnoreCase(TAG_NAME_KYC_TYPE_RE_ENTER_AADHAAR))) {

                                    if (appHelper.isNetworkAvailable()) {
                                        aadhaarVaultServiceCall(dynamicUITable, viewParametersList);
                                    }
                                } else {
                                    // TODO: NEW LOGIC
                                    deDupeValidationForAllScreen(dynamicUITable, viewParametersList);
                                }
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }

                        @Override
                        public void callBackForLoanAmountValidation(DynamicUITable dynamicUITable, View view) {
                            try {
                                loanAmountValidation(dynamicUITable, viewParametersList);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void callBackForAgeValidationJLG(DynamicUITable dynamicUITable, View view) {
                            try {
                                ageValidationJLG(dynamicUITable, viewParametersList);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }


                    });

                    if (viewParameters.getFieldTag().equalsIgnoreCase(TAG_NAME_PINCODE)
                            || viewParameters.getFieldTag().equalsIgnoreCase(TAG_NAME_PERMANENT_PINCODE)
                            || viewParameters.getFieldTag().equalsIgnoreCase(TAG_NAME_COMMUNICATION_PINCODE)) { // TODO: PIN CODE
                        // TODO: PIN CODE
                        customTextInputLayout.getEditText().addTextChangedListener(new TextWatcher() {
                            @Override
                            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                            }

                            @Override
                            public void onTextChanged(CharSequence s, int start, int before, int count) {
                                if (!TextUtils.isEmpty(s) && s.toString().length() == 6) {
                                    if (appHelper.isNetworkAvailable()) {
                                        getPincodeDetails(s.toString(), viewParametersList, viewParameters.getFieldTag());
                                    } else {
                                        appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT,
                                                "No Internet Connection", new ConfirmationDialog.ActionCallback() {
                                                    @Override
                                                    public void onAction() {
                                                        List<PincodeParameterInfo> parameterInfoList = new ArrayList<>();
                                                        parameterInfoList.add(new PincodeParameterInfo(TAG_NAME_PINCODE, SCREEN_ID, s.toString(), true, true, FIELD_TYPE_TEXT_BOX, new String[0]));
                                                        parameterInfoList.add(new PincodeParameterInfo(TAG_NAME_VILLAGE, SCREEN_ID, "", true, true, FIELD_TYPE_TEXT_BOX, new String[0]));
                                                        parameterInfoList.add(new PincodeParameterInfo(TAG_NAME_CITY, SCREEN_ID, "", true, true, FIELD_TYPE_TEXT_BOX, new String[0]));
                                                        parameterInfoList.add(new PincodeParameterInfo(TAG_NAME_DISTRICT, SCREEN_ID, "", true, true, FIELD_TYPE_TEXT_BOX, new String[0]));
                                                        parameterInfoList.add(new PincodeParameterInfo(TAG_NAME_STATE, SCREEN_ID, "", true, true, FIELD_TYPE_TEXT_BOX, new String[0]));
                                                        changePinCodeFields(parameterInfoList, viewParametersList);
                                                        Log.e("Tag", "added permanent pincode and communication pincode");

                                                        if (viewParameters.getFieldTag().equalsIgnoreCase(TAG_NAME_PERMANENT_PINCODE)) {
                                                            List<PincodeParameterInfo> parameterInfoListPerm = new ArrayList<>();
                                                            parameterInfoListPerm.add(new PincodeParameterInfo(TAG_NAME_PERMANENT_PINCODE, SCREEN_ID, s.toString(), true, true, FIELD_TYPE_TEXT_BOX, new String[0]));
                                                            parameterInfoListPerm.add(new PincodeParameterInfo(TAG_NAME_PERMANENT_CITY, SCREEN_ID, "", true, true, FIELD_TYPE_TEXT_BOX, new String[0]));
                                                            parameterInfoListPerm.add(new PincodeParameterInfo(TAG_NAME_PERMANENT_DISTRICT, SCREEN_ID, "", true, true, FIELD_TYPE_TEXT_BOX, new String[0]));
                                                            parameterInfoListPerm.add(new PincodeParameterInfo(TAG_NAME_PERMANENT_STATE, SCREEN_ID, "", true, true, FIELD_TYPE_TEXT_BOX, new String[0]));
                                                            changePinCodeFields(parameterInfoListPerm, viewParametersList);
                                                        }

                                                        if (viewParameters.getFieldTag().equalsIgnoreCase(TAG_NAME_COMMUNICATION_PINCODE)) {
                                                            List<PincodeParameterInfo> parameterInfoListCom = new ArrayList<>();
                                                            parameterInfoListCom.add(new PincodeParameterInfo(TAG_NAME_PERMANENT_PINCODE, SCREEN_ID, s.toString(), true, true, FIELD_TYPE_TEXT_BOX, new String[0]));
                                                            parameterInfoListCom.add(new PincodeParameterInfo(TAG_NAME_COMMUNICATION_CITY, SCREEN_ID, "", true, true, FIELD_TYPE_TEXT_BOX, new String[0]));
                                                            parameterInfoListCom.add(new PincodeParameterInfo(TAG_NAME_COMMUNICATION_DISTRICT, SCREEN_ID, "", true, true, FIELD_TYPE_TEXT_BOX, new String[0]));
                                                            parameterInfoListCom.add(new PincodeParameterInfo(TAG_NAME_COMMUNICATION_STATE, SCREEN_ID, "", true, true, FIELD_TYPE_TEXT_BOX, new String[0]));
                                                            changePinCodeFields(parameterInfoListCom, viewParametersList);
                                                        }

                                                    }
                                                });
                                    }
                                }
                            }

                            @Override
                            public void afterTextChanged(Editable s) {

                            }
                        });
                    }

                    if (viewParameters.getFieldType().equalsIgnoreCase(FIELD_TYPE_CALENDAR)) { // TODO: CALENDAR
                        final Calendar calendar = Calendar.getInstance();
                        final DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                calendar.set(Calendar.YEAR, year);
                                calendar.set(Calendar.MONTH, month);
                                calendar.set(Calendar.DAY_OF_MONTH, day);
                                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(AppConstant.DATE_FORMAT_YYYY_MM_DD, Locale.US);
                                String selectedDate = simpleDateFormat.format(calendar.getTime());
                                customTextInputLayout.getEditText().setText(selectedDate);

                                // TODO: 10-04-2019 need to check in all screen
                                if (viewParameters.getFieldTag().contains(TAG_NAME_CONTAINS_DOB)) {
                                    int age = appHelper.getAgeBasedOnDOB(selectedDate);
                                    if (viewParameters.getFieldTag().contains(TAG_NAME_NOMINEE_DATE_OF_BIRTH)) {
                                        setValueByLoopingDynamicUI(dynamicViews, String.valueOf(age), TAG_NAME_NOMINEE_AGE, viewParametersList);
                                    } else if (viewParameters.getFieldTag().contains(TAG_NAME_GUARDIAN_DATE_OF_BIRTH)) {
                                        setValueByLoopingDynamicUI(dynamicViews, String.valueOf(age), TAG_NAME_GUARDIAN_AGE, viewParametersList);
                                    } else if (viewParameters.getFieldTag().contains(TAG_NAME_SPOUSE_DATE_OF_BIRTH)) {
                                        setValueByLoopingDynamicUI(dynamicViews, String.valueOf(age), TAG_NAME_SPOUSE_AGE, viewParametersList);
                                    } else {
                                        setValueByLoopingDynamicUI(dynamicViews, String.valueOf(age), TAG_NAME_CONTAINS_AGE, viewParametersList);
                                    }
                                }


                                if (viewParameters.getFieldTag().contains(TAG_NAME_BUSINESS_STARTED_ON)
                                        || viewParameters.getFieldTag().contains(TAG_NAME_BUSINESS_INCORPORATION_DATE)) {
                                    int age = appHelper.getbusinessvintageBasedOnYEAR(selectedDate);//

                                    setValueByLoopingDynamicUI(dynamicViews, String.valueOf(age), TAG_NAME_BUSINESS_VINTAGE);

                                }

                                if (viewParameters.getFieldTag().contains(TAG_NAME_WORKINH_SINCE_WITH_CURRENT_EMPLOYER)) {
                                    int age = appHelper.getbusinessvintageBasedOnYEAR(selectedDate);//

                                    setValueByLoopingDynamicUI(dynamicViews, String.valueOf(age), TAG_NAME_NO_OF_YEARS_WITH_CURRENT_EMPLOYER);

                                }


                                // TODO: date format should be this format only "january 2019" in banking history child
                                if (viewParameters.getFieldTag().contains(TAG_NAME_MONTH_IN_BANKING_HISTORY)
                                        && viewParameters.getScreenName().equalsIgnoreCase(SCREEN_NAME_MONTHLY_TRANSACTION_DETAIL_MSME)) {
                                    //int age = appHelper.getbusinessvintageBasedOnYEAR(selectedDate);//
                                    String month_datepicker = appHelper.getMonthByDate(DATE_FORMAT_YYYY_MM_DD, selectedDate);//

                                    //setValueByLoopingDynamicUI(dynamicViews, age, TAG_NAME_MONTH_IN_BANKING_HISTORY);
                                    // TODO: calling deDupValidation for BankingHistory method
                                    viewParameters.setValue(selectedDate);
                                    deDupValidationforMonthInPD(viewParameters, viewParametersList, month_datepicker);
                                }
//                                // TODO: date format should be this format only "january 2019" in Sales bills detail
//                                if (viewParameters.getFieldTag().contains(TAG_NAME_DATE_IN_SALES_BILLS)
//                                        &&viewParameters.getScreenName().equalsIgnoreCase(SCREEN_NAME_SALES_BILLS_DETAIL_MSME)) {
//                                    //int age = appHelper.getbusinessvintageBasedOnYEAR(selectedDate);//
//                                    String month_datepicker = appHelper.getMonthByDate(DATE_FORMAT_DD_MM_YYYY2,selectedDate);//
//
//                                    //setValueByLoopingDynamicUI(dynamicViews, age, TAG_NAME_MONTH_IN_BANKING_HISTORY);
//                                    // TODO: calling deDupValidation for sales bills detail
//                                    viewParameters.setValue(selectedDate);
//                                    deDupValidationforMonthInPD(viewParameters,viewParametersList,month_datepicker);
//                                }
//
//                                // TODO: date format should be this format only "january 2019" in Purchase bills detail
//                                if (viewParameters.getFieldTag().contains(TAG_NAME_DATE_IN_PURCHASE_BILLS)
//                                        &&viewParameters.getScreenName().equalsIgnoreCase(SCREEN_NAME_PURCHASE_BILLS_DETAIL_MSME)) {
//                                    //int age = appHelper.getbusinessvintageBasedOnYEAR(selectedDate);//
//                                    String month_datepicker = appHelper.getMonthByDate(DATE_FORMAT_DD_MM_YYYY2,selectedDate);//
//
//                                    //setValueByLoopingDynamicUI(dynamicViews, age, TAG_NAME_MONTH_IN_BANKING_HISTORY);
//                                    // TODO: calling deDupValidation for purchase bills detail
//                                    viewParameters.setValue(selectedDate);
//                                    deDupValidationforMonthInPD(viewParameters,viewParametersList,month_datepicker);
//                                }

                                if ((viewParameters.getScreenName().equalsIgnoreCase(SCREEN_NAME_MORTGAGE_BUILDING_MSME)
                                        || viewParameters.getScreenName().equalsIgnoreCase(SCREEN_NAME_MORTGAGE_LAND_MSME)
                                        || viewParameters.getScreenName().equalsIgnoreCase(SCREEN_NAME_MORTGAGE_OPEN_PLOT_MSME)
                                ) && viewParameters.getFieldTag().contains(TAG_NAME_PURCHASED_ON_IN_FIXED_ASSET)) {
                                    int age = appHelper.getbusinessvintageBasedOnYEAR(selectedDate);//
                                    setValueByLoopingDynamicUI(dynamicViews, String.valueOf(age), TAG_NAME_COLLATERAL_OWNERSHIP_IN_FIXED_ASSET);

                                }
                                if ((viewParameters.getScreenName().equalsIgnoreCase(SCREEN_NAME_HYPOTHECATION_DETAIL_MSME)
                                ) && viewParameters.getFieldTag().contains(TAG_NAME_PURCHASED_ON_IN_HYPOTHECATION_MSME)) {
                                    int age = appHelper.getbusinessvintageBasedOnYEAR(selectedDate);//
                                    setValueByLoopingDynamicUI(dynamicViews, String.valueOf(age), TAG_NAME_COLLATERAL_OWNERSHIP_IN_HYPOTHECATION_MSME);

                                }
                            }
                        };

                        customTextInputLayout.getEditText().setClickable(true);
                        customTextInputLayout.getEditText().setLongClickable(false);
                        customTextInputLayout.getEditText().setFocusable(false);
                        customTextInputLayout.getEditText().setFocusableInTouchMode(false);
                        customTextInputLayout.getEditText().setCursorVisible(false);
                        customTextInputLayout.getEditText().setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), dateSetListener,
                                        calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

                                if (viewParameters.getFieldTag().contains(TAG_NAME_CONTAINS_DOB)
                                        || viewParameters.getFieldTag().contains(TAG_NAME_BUSINESS_STARTED_ON)
                                        || viewParameters.getFieldTag().equalsIgnoreCase(TAG_NAME_SPOUSE_DATE_OF_BIRTH)
                                        || viewParameters.getFieldTag().equalsIgnoreCase(TAG_NAME_MONTH_IN_BANKING_HISTORY)
                                        || viewParameters.getFieldTag().equalsIgnoreCase(TAG_NAME_DATE_IN_PURCHASE_BILLS)
                                        || viewParameters.getFieldTag().equalsIgnoreCase(TAG_NAME_DATE_IN_SALES_BILLS)
                                        || viewParameters.getFieldTag().equalsIgnoreCase(TAG_NAME_BUSINESS_INCORPORATION_DATE)
                                        || viewParameters.getFieldTag().equalsIgnoreCase(TAG_NAME_PURCHASED_ON_IN_FIXED_ASSET)
                                        || viewParameters.getFieldTag().equalsIgnoreCase(TAG_NAME_PURCHASE_DATE_IN_BUSINESS_ASSETS_MSME)
                                        || viewParameters.getFieldTag().equalsIgnoreCase(TAG_NAME_WORKINH_SINCE_WITH_CURRENT_EMPLOYER)
                                ) {
                                    // TODO: Feature date not allowed
                                    Calendar myCalendar = Calendar.getInstance();
//                                    toLDate = convertStringToDate(toDate , AppConstants.DATE_FORMAT_YYYY_MM_DD_T_HH_MM_SS);
                                    myCalendar.setTime(myCalendar.getTime());
                                    myCalendar.set(Calendar.HOUR_OF_DAY, myCalendar.getMaximum(Calendar.HOUR_OF_DAY));
                                    myCalendar.set(Calendar.MINUTE, myCalendar.getMaximum(Calendar.MINUTE));
                                    myCalendar.set(Calendar.SECOND, myCalendar.getMaximum(Calendar.SECOND));
                                    myCalendar.set(Calendar.MILLISECOND, myCalendar.getMaximum(Calendar.MILLISECOND));
                                    datePickerDialog.getDatePicker().setMaxDate(myCalendar.getTimeInMillis());
                                } else if (viewParameters.getFieldTag().equalsIgnoreCase(TAG_NAME_NEXT_FOLLOW_UP_DATE_IN_COLD_CALLING)) {

                                    Calendar myCalendar = Calendar.getInstance();
                                    datePickerDialog.getDatePicker().setMinDate(myCalendar.getTimeInMillis() + (24 * 60 * 60 * 1000));

                                } else {
                                    // TODO: Past date not allowed
                                    Calendar myCalendar1 = Calendar.getInstance();
//                                    fromLDate = convertStringToDate(fromDate , AppConstants.DATE_FORMAT_YYYY_MM_DD_T_HH_MM_SS);
                                    myCalendar1.setTime(myCalendar1.getTime());
                                    myCalendar1.set(Calendar.HOUR_OF_DAY, myCalendar1.getMinimum(Calendar.HOUR_OF_DAY));
                                    myCalendar1.set(Calendar.MINUTE, myCalendar1.getMinimum(Calendar.MINUTE));
                                    myCalendar1.set(Calendar.SECOND, myCalendar1.getMinimum(Calendar.SECOND));
                                    myCalendar1.set(Calendar.MILLISECOND, myCalendar1.getMinimum(Calendar.MILLISECOND));
                                    datePickerDialog.getDatePicker().setMinDate(myCalendar1.getTimeInMillis());
                                }

                                datePickerDialog.show();
                            }
                        });


                    }

                    if (viewParameters.isVisibility()) {
                        if ((!TextUtils.isEmpty(viewParameters.getFieldType()) && viewParameters.getFieldType().
                                equalsIgnoreCase(FIELD_TYPE_TEXT_LABEL_WITH_TEXT_BOX))) {

                            LinearLayout linearLayout = new LinearLayout(getActivity());
                            linearLayout.setOrientation(LinearLayout.HORIZONTAL);
                            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                            linearLayout.setLayoutParams(layoutParams);
                            linearLayout.addView(addTextViewLable(viewParameters, viewParametersList)); // TODO: lable
                            linearLayout.addView(customTextInputLayout); // TODO: Text box
                            linearLayout.setTag(viewParameters);


                            if (viewParameters.isVisibility()) {
                                dynamicViews.add(customTextInputLayout);
                                addViewToParentLayout(linearLayout);
                            }

                        } else {
                            dynamicViews.add(customTextInputLayout);
                            addViewToParentLayout(customTextInputLayout);
                        }
                    }
                }

                // TODO: LABEL
                else if ((!TextUtils.isEmpty(viewParameters.getFieldType()) && viewParameters.getFieldType().
                        equalsIgnoreCase(FIELD_TYPE_TEXT_LABEL))) { // TODO: LABEL
                    if (viewParameters.isVisibility()) {
                        View view = addTextViewHeading(viewParameters);
                        dynamicViews.add(view);
                        addViewToParentLayout(view);

                        if (viewParameters.getScreenName().equalsIgnoreCase(SCREEN_NAME_LOAN_APPROVAL_MSME) &&
                                viewParameters.getFieldTag().equalsIgnoreCase(TAG_NAME_TOTAL_MONTHLY_SALES_IN_LOAN_APPROVAL_MSME)) {
                            view.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    updateDynamicUITable(viewParametersList, SCREEN_ID);
                                    removeAllChildFragments(ll.getId() + "");
                                    initChild(LOAN_TYPE, CLIENT_ID, PROJECT_ID_LOS, PRODUCT_ID_MSME, SCREEN_N0_LOAN_SUGGESTION_MSME,
                                            SCREEN_NAME_LOAN_SUGGESTION_MSME, viewParametersList, null
                                            , viewParametersList.get(0).getCoRelationID());
                                }
                            });
                        }
                    }
                }

                // TODO: CALCULATOR
                else if ((!TextUtils.isEmpty(viewParameters.getFieldType()) &&
                        viewParameters.getFieldType().equalsIgnoreCase(FIELD_TYPE_CALCULATOR)
                        || viewParameters.getFieldType().equalsIgnoreCase(FIELD_TYPE_NEW_LIABILITY))) { // TODO: CALCULATOR && NEW LIABILITY

                    LinearLayout linearLayout = new LinearLayout(getActivity());
                    linearLayout.setOrientation(LinearLayout.HORIZONTAL);
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    linearLayout.setLayoutParams(layoutParams);
                    linearLayout.addView(addTextViewLable(viewParameters, viewParametersList)); // TODO: lable
                    linearLayout.addView(addTextViewValue(viewParameters, viewParametersList)); // TODO: value
                    linearLayout.setTag(viewParameters);

                    if (viewParameters.isVisibility()) {
                        dynamicViews.add(linearLayout);
                        addViewToParentLayout(linearLayout);
                    }

                }

                // TODO: NEW ROW
                else if ((!TextUtils.isEmpty(viewParameters.getFieldType()) &&
                        viewParameters.getFieldType().equalsIgnoreCase(FIELD_TYPE_NEW_ROW))) { // TODO: NEW ROW

                    LinearLayout linearLayout = new LinearLayout(getActivity());
                    linearLayout.setOrientation(LinearLayout.HORIZONTAL);
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    linearLayout.setLayoutParams(layoutParams);
                    linearLayout.addView(addTextViewLableForNewRow(viewParameters, viewParametersList)); // TODO: lable
                    linearLayout.addView(addTextViewValueForNewRow(viewParameters, viewParametersList)); // TODO: value
                    linearLayout.setTag(viewParameters);

                    if (viewParameters.isVisibility()) {
                        dynamicViews.add(linearLayout);
                        addViewToParentLayout(linearLayout);
                    }

                }

                // TODO: CALCULATOR WITH STATUS
                else if ((!TextUtils.isEmpty(viewParameters.getFieldType()) &&
                        viewParameters.getFieldType().equalsIgnoreCase(FIELD_TYPE_CALCULATOR_WITH_STATUS))) { //
                    // TODO: CALCULATOR WITH STATUS

                    LinearLayout linearLayout = new LinearLayout(getActivity());
                    linearLayout.setOrientation(LinearLayout.HORIZONTAL);
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    linearLayout.setLayoutParams(layoutParams);
                    linearLayout.setTag(viewParameters);
                    linearLayout.addView(addTextViewLable(viewParameters, viewParametersList)); // TODO: lable
                    linearLayout.addView(addTextViewValue(viewParameters, viewParametersList)); // TODO: value
                    linearLayout.addView(addImageView(viewParameters, viewParametersList)); // TODO: image view

                    if (viewParameters.isVisibility()) {
                        dynamicViews.add(linearLayout);
                        addViewToParentLayout(linearLayout);
                    }

                }

                // TODO: VIEW
                else if ((!TextUtils.isEmpty(viewParameters.getFieldType()) &&
                        viewParameters.getFieldType().equalsIgnoreCase(FIELD_TYPE_VIEW))) { //
                    // TODO: VIEW ( dummy view for module type and no of co applicant )
                    final XMLCustomView view = new XMLCustomView(getActivity(), viewParameters);
                    dynamicViews.add(view);
                    addViewToParentLayout(view);

                }

                // TODO: PIN VIEW
                else if ((!TextUtils.isEmpty(viewParameters.getFieldType()) && viewParameters.getFieldType().
                        equalsIgnoreCase(FIELD_TYPE_PIN_VIEW))) { // TODO: PIN VIEW

                    final XMLPinView customPinView = new XMLPinView(getActivity());

                    RelativeLayout relativeLayout = new RelativeLayout(getActivity());
                    RelativeLayout.LayoutParams llParentParrams = new RelativeLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT);

                    llParentParrams.setMargins(0, 30, 0, 10);
                    relativeLayout.setLayoutParams(llParentParrams);

                    relativeLayout.addView(customPinView);

                    RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) customPinView.getLayoutParams();
                    lp.addRule(RelativeLayout.CENTER_IN_PARENT);
                    customPinView.setLayoutParams(lp);

                    customPinView.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            if (s.length() == 4) {
                                if (s.toString().equalsIgnoreCase("1234")) { // TODO: Hardcoded value
                                    customPinView.setLineColor(Color.GREEN);

                                    appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.SUCCESS,
                                            "OTP authenticated successfully", new ConfirmationDialog.ActionCallback() {
                                                @Override
                                                public void onAction() {
                                                    List<ParameterInfo> parameterInfoList = new ArrayList<>();
                                                    parameterInfoList.add(new ParameterInfo(TAG_NAME_CBCHECK_BUTTON, SCREEN_ID, "", true, true));
                                                    parameterInfoList.add(new ParameterInfo(TAG_NAME_ENTER_OTP, SCREEN_ID, "", false, true));
                                                    parameterInfoList.add(new ParameterInfo(TAG_NAME_RESEND_OTP_BUTTON, SCREEN_ID, "", false, true));
                                                    EnableOrDisableByFieldNameInDB(parameterInfoList, viewParametersList);
                                                }
                                            });
                                } else {
                                    customPinView.setLineColor(Color.RED);
                                    appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ERROR,
                                            "Invalid OTP", new ConfirmationDialog.ActionCallback() {
                                                @Override
                                                public void onAction() {
                                                   /* List<ParameterInfo> parameterInfoList = new ArrayList<>();
                                                    parameterInfoList.add(new ParameterInfo(TAG_NAME_RESEND_OTP_BUTTON, SCREEN_ID, "", true, true));
                                                    EnableOrDisableByFieldNameInDB(parameterInfoList);*/
                                                }
                                            });
                                }
                            } else {
                                customPinView.setLineColor(ResourcesCompat.getColorStateList(getResources(), R.color.colorPrimaryDark, getActivity().getTheme()));

                            }
                        }

                        @Override
                        public void afterTextChanged(Editable s) {

                        }
                    });

                    if (viewParameters.isVisibility()) {
                        dynamicViews.add(relativeLayout);
                        addViewToParentLayout(relativeLayout);
                    }
                }

                // TODO: CHECK BOX
                else if ((!TextUtils.isEmpty(viewParameters.getFieldType()) && viewParameters.getFieldType().
                        equalsIgnoreCase(FIELD_TYPE_CHECKBOX))) {// TODO: CHECK BOX

                    HorizontalScrollView mScrollView = new HorizontalScrollView(getActivity());
                    LinearLayout.LayoutParams scrollViewLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    mScrollView.setFillViewport(true);
                    mScrollView.setLayoutParams(scrollViewLayoutParams);
                    mScrollView.setPadding(5, 15, 5, 15);
                    LinearLayout attrControlsSubContainer = new LinearLayout(getActivity());
                    attrControlsSubContainer.setOrientation(LinearLayout.HORIZONTAL);
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    attrControlsSubContainer.setLayoutParams(layoutParams);
                    attrControlsSubContainer.addView(addTextViews(viewParameters));

                    String[] parameterList = viewParameters.getParamlist();
                    XmlCustomCheckBox customCheckBox = null;
                    for (String checkBoxItem : parameterList) {
//                        customCheckBox = new XmlCustomCheckBox(getActivity(), viewParameters, checkBoxItem);
                        customCheckBox = new XmlCustomCheckBox(getActivity(), viewParameters, "Yes");
                        dynamicViews.add(customCheckBox);
                        attrControlsSubContainer.addView(customCheckBox);
                    }
                    mScrollView.addView(attrControlsSubContainer);
                    if (viewParameters.isVisibility()) {
                        dynamicViews.add(customCheckBox);
                        addViewToParentLayout(mScrollView);
                    }

                }

                // TODO: SEEK BAR
                else if ((!TextUtils.isEmpty(viewParameters.getFieldType()) && viewParameters.getFieldType().
                        equalsIgnoreCase(FIELD_TYPE_SEEK_BAR))) { // TODO: SEEK BAR
                    LinearLayout llParent = new LinearLayout(getActivity());
                    LinearLayout.LayoutParams llParentParrams = new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT);
                    llParent.setOrientation(LinearLayout.VERTICAL);

                    llParentParrams.setMargins(0, 15, 5, 10);
                    llParent.setLayoutParams(llParentParrams);

//                    llParent.addView(addTextViews(viewParameters));

                    LinearLayout linearLayout = new LinearLayout(getActivity());
                    linearLayout.setOrientation(LinearLayout.HORIZONTAL);
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    linearLayout.setLayoutParams(layoutParams);
                    linearLayout.addView(addTextViewLable(viewParameters, viewParametersList)); // TODO: lable
                    linearLayout.addView(addTextViewValue(viewParameters, viewParametersList)); // TODO: value
                    linearLayout.setTag(viewParameters);

                    llParent.addView(linearLayout);

                    LinearLayout llSeekbarContainer = new LinearLayout(getActivity());
                    llSeekbarContainer.setOrientation(LinearLayout.HORIZONTAL);
                    LinearLayout.LayoutParams llparams = new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT);
                    llparams.weight = 1;
                    llSeekbarContainer.setLayoutParams(llparams);
                    final XMLCustomSeekBar customSeekBar = new XMLCustomSeekBar(getActivity(), viewParameters, appHelper,
                            viewParametersList, new XMLCustomSeekBar.SeekBarListener() {
                        @Override
                        public void callBackForCalculateLoanProposal(DynamicUITable dynamicUITable) {
                            if (viewParameters.getScreenName().equalsIgnoreCase(SCREEN_NAME_LOAN_PROPOSAL)) {
                                calculateLoanProposalFinalPD(dynamicUITable, viewParametersList, true);
                            } else if (viewParameters.getScreenName().equalsIgnoreCase(SCREEN_NAME_LOAN_APPROVAL_MSME)) {
                                loanApprovalScreenChangesByDropdown(dynamicUITable, viewParametersList);
                            }
                        }
                    });

                    llSeekbarContainer.addView(customSeekBar);
                    llParent.addView(llSeekbarContainer);
                    if (viewParameters.isVisibility()) {
                        dynamicViews.add(customSeekBar);
                        addViewToParentLayout(llParent);
                    }
                }

                // TODO: RADIO BUTTON
                else if ((!TextUtils.isEmpty(viewParameters.getFieldType()) && viewParameters.getFieldType().
                        equalsIgnoreCase(FIELD_TYPE_RADIOBUTTON))) { // TODO: RADIO BUTTON
                    LinearLayout llParent = new LinearLayout(getActivity());
                    LinearLayout.LayoutParams llParentParrams = new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT);
                    llParent.setOrientation(LinearLayout.VERTICAL);

                    llParentParrams.setMargins(0, 10, 5, 10);
                    llParent.setLayoutParams(llParentParrams);

                    llParent.addView(addTextViews(viewParameters));

                    LinearLayout llRadioGroup = new LinearLayout(getActivity());
                    llRadioGroup.setOrientation(LinearLayout.HORIZONTAL);
                    LinearLayout.LayoutParams llparams = new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT);
                    llparams.weight = 1;
                    if (!(LOSBaseFragment.this instanceof AuditFragment)) {
                        // TODO: No need of box for all screens
//                        llRadioGroup.setBackground(this.getResources().getDrawable(R.drawable.bg_custom_border));
                    }
                    llRadioGroup.setLayoutParams(llparams);

                    HorizontalScrollView mScrollView = new HorizontalScrollView(getActivity());
                    LinearLayout.LayoutParams scrollViewLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    mScrollView.setFillViewport(true);
                    mScrollView.setLayoutParams(scrollViewLayoutParams);
//                    mScrollView.setPadding(0, 15, 0, 15);

                    final XmlCustomRadioGroup customRadioGroup = new XmlCustomRadioGroup(getActivity(), viewParameters);
                    mScrollView.addView(customRadioGroup);
                    llRadioGroup.addView(mScrollView);
                    llParent.addView(llRadioGroup);
                    if (viewParameters.isVisibility()) {
                        dynamicViews.add(customRadioGroup);
                        addViewToParentLayout(llParent);
                        if (!TextUtils.isEmpty(viewParameters.getValue())) {
                            String checkValue = viewParameters.getValue();
                            boolean editable = viewParameters.isEditable();
                            // TODO: Setting enabled false for view purpose on screen not for edit
                            if (viewParameters.isSync()) {
                                editable = false;
                            }
                            appHelper.checkRadioButton(customRadioGroup, checkValue, editable);
                        }
                    }

                    customRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(RadioGroup group, int checkedId) {
                            if (customRadioGroup.getCheckedRadioButtonId() != -1) {

                                // This will get the radiobutton that has changed in its check state
                                RadioButton checkedRadioButton = (RadioButton) group.findViewById(checkedId);
                                /*if (checkedRadioButton != null) {
                                    // This puts the value (true/false) into the variable
                                    boolean isPressed = checkedRadioButton.isPressed();
                                    // If the radiobutton that is not pressed..
                                    if (!isPressed) {
                                        return;
                                    }
                                }*/
                                String selectedRadioButton = appHelper.getSelectedRadioButtonItem(group, checkedId);
                                viewParameters.setValue(selectedRadioButton);
                                DynamicUITable dynamicUITable = (DynamicUITable) customRadioGroup.getTag();
                                dynamicUITable.setValue(selectedRadioButton);
                              /*  if (LOSBaseFragment.this instanceof AddressDetailsFragment ||
                                        LOSBaseFragment.this instanceof CoApplicantAddressDetailFragment) {
                                    GetSpinnerItemFromDB(TAG_NAME_KYC_TYPE, SCREEN_NO_APPLICANT_KYC_IL, selectedRadioButton, dynamicUITable.getFieldTag(), viewParametersList);
                                          need to remove thisll
                                } else */
                                if (LOSBaseFragment.this instanceof AddressDetailsFragment) {

//                                    setAddressKYCType(dynamicUITable, viewParametersList, SCREEN_NAME_APPLICANT_KYC);
                                    addressDetailValidation(dynamicUITable, viewParametersList);
                                } else if (LOSBaseFragment.this instanceof CoApplicantAddressDetailFragment) {
//                                    setAddressKYCType(dynamicUITable, viewParametersList, SCREEN_NAME_CO_APPLICANT_KYC);
                                    addressDetailValidation(dynamicUITable, viewParametersList);
                                } else if (LOSBaseFragment.this instanceof SocioEconomicDetailFragment || LOSBaseFragment.this instanceof CoApplicantSocioEconomicDetail) {
                                    if (!TextUtils.isEmpty(dynamicUITable.getFieldTag())
                                            && (dynamicUITable.getFieldTag().equalsIgnoreCase(TAG_NAME_APPLICANT_DISABLED)
                                            || dynamicUITable.getFieldTag().equalsIgnoreCase(TAG_NAME_CO_APPLICANT_DISABLED))) {
                                        if (!TextUtils.isEmpty(selectedRadioButton) && selectedRadioButton.equalsIgnoreCase("Yes")) {
                                            List<ParameterInfo> parameterInfoList = new ArrayList<>();
                                            parameterInfoList.add(new ParameterInfo(TAG_NAME_DISABILITY_TYPE, SCREEN_ID, "", true, true));
                                            EnableOrDisableByFieldNameInDB(parameterInfoList, viewParametersList);
                                        } else {
                                            List<ParameterInfo> parameterInfoList = new ArrayList<>();
                                            parameterInfoList.add(new ParameterInfo(TAG_NAME_DISABILITY_TYPE, SCREEN_ID, "", false, true));
                                            EnableOrDisableByFieldNameInDB(parameterInfoList, viewParametersList);
                                        }
                                    }

                                } else if (LOSBaseFragment.this instanceof ColdCallingFragment) {
                                    if (!TextUtils.isEmpty(dynamicUITable.getFieldTag())
                                            && (dynamicUITable.getFieldTag().equalsIgnoreCase(TAG_NAME_INTERESTED_IN_LOAN_IN_COLD_CALLING))) {
                                        if (!TextUtils.isEmpty(selectedRadioButton) && selectedRadioButton.equalsIgnoreCase("Yes")) {
                                            List<ParameterInfo> parameterInfoList = new ArrayList<>();
                                            parameterInfoList.add(new ParameterInfo(TAG_NAME_WHEN_IN_COLD_CALLING, SCREEN_ID, "", true, true));
                                            parameterInfoList.add(new ParameterInfo(TAG_NAME_NEXT_FOLLOW_UP_DATE_IN_COLD_CALLING, SCREEN_ID, "", true, true));
                                            EnableOrDisableByFieldNameInDB(parameterInfoList, viewParametersList);
                                        } else {
                                            List<ParameterInfo> parameterInfoList = new ArrayList<>();
                                            parameterInfoList.add(new ParameterInfo(TAG_NAME_WHEN_IN_COLD_CALLING, SCREEN_ID, "", false, true));
                                            parameterInfoList.add(new ParameterInfo(TAG_NAME_NEXT_FOLLOW_UP_DATE_IN_COLD_CALLING, SCREEN_ID, "", false, true));
                                            EnableOrDisableByFieldNameInDB(parameterInfoList, viewParametersList);
                                        }
                                    } else if (!TextUtils.isEmpty(dynamicUITable.getFieldTag())
                                            && (dynamicUITable.getFieldTag().equalsIgnoreCase(TAG_NAME_WHEN_IN_COLD_CALLING))) {
                                        if (!TextUtils.isEmpty(selectedRadioButton) && selectedRadioButton.equalsIgnoreCase(RADIO_BUTTON_WHEN_ITEM_NOW)) {
                                            List<ParameterInfo> parameterInfoList = new ArrayList<>();
                                            String currentDate = appHelper.getCurrentDate(DATE_FORMAT_YYYY_MM_DD);
                                            parameterInfoList.add(new ParameterInfo(TAG_NAME_NEXT_FOLLOW_UP_DATE_IN_COLD_CALLING, SCREEN_ID, currentDate, true, false));
                                            EnableOrDisableByFieldNameInDB(parameterInfoList, viewParametersList);
                                        } else {
                                            List<ParameterInfo> parameterInfoList = new ArrayList<>();
                                            parameterInfoList.add(new ParameterInfo(TAG_NAME_NEXT_FOLLOW_UP_DATE_IN_COLD_CALLING, SCREEN_ID, "", true, true));
                                            EnableOrDisableByFieldNameInDB(parameterInfoList, viewParametersList);
                                        }
                                    }
                                } else if(LOSBaseFragment.this instanceof SalesToolFragment){
                                    if (!TextUtils.isEmpty(dynamicUITable.getFieldTag())
                                            && (dynamicUITable.getFieldTag().equalsIgnoreCase(TAG_NAME_LOOKING_FOR_IN_SALES_TOOL))) {
                                        if (!TextUtils.isEmpty(selectedRadioButton) && selectedRadioButton.equalsIgnoreCase(RADIO_BUTTON_ITEM_SECURED_LOAN)) {
                                            List<ParameterInfo> parameterInfoList = new ArrayList<>();
                                            parameterInfoList.add(new ParameterInfo(TAG_NAME_TYPE_OF_SECURITY_IN_SALES_TOOL, SCREEN_ID, "", true, true));
                                            parameterInfoList.add(new ParameterInfo(TAG_NAME_SECURITY_OWNERSHIP_IN_SALES_TOOL, SCREEN_ID, "", true, true));
                                            EnableOrDisableByFieldNameInDB(parameterInfoList, viewParametersList);
                                        } else {
                                            List<ParameterInfo> parameterInfoList = new ArrayList<>();
                                            parameterInfoList.add(new ParameterInfo(TAG_NAME_TYPE_OF_SECURITY_IN_SALES_TOOL, SCREEN_ID, "", false, true));
                                            parameterInfoList.add(new ParameterInfo(TAG_NAME_SECURITY_OWNERSHIP_IN_SALES_TOOL, SCREEN_ID, "", false, true));
                                            EnableOrDisableByFieldNameInDB(parameterInfoList, viewParametersList);
                                        }
                                    }else if(!TextUtils.isEmpty(dynamicUITable.getFieldTag())
                                            && (dynamicUITable.getFieldTag().equalsIgnoreCase(TAG_NAME_ANY_EXISTING_LOAN_RUNNING_IN_SALES_TOOL))){
                                        if (!TextUtils.isEmpty(selectedRadioButton) && selectedRadioButton.equalsIgnoreCase(RADIO_BUTTON_ITEM_YES)) {
                                            List<ParameterInfo> parameterInfoList = new ArrayList<>();
                                            parameterInfoList.add(new ParameterInfo(TAG_NAME_TYPE_OF_LOAN_IN_SALES_TOOL, SCREEN_ID, "", true, true));
                                            EnableOrDisableByFieldNameInDB(parameterInfoList, viewParametersList);
                                        } else {
                                            List<ParameterInfo> parameterInfoList = new ArrayList<>();
                                            parameterInfoList.add(new ParameterInfo(TAG_NAME_TYPE_OF_LOAN_IN_SALES_TOOL, SCREEN_ID, "", false, true));
                                            EnableOrDisableByFieldNameInDB(parameterInfoList, viewParametersList);
                                        }

                                    }


                                } else if (LOSBaseFragment.this instanceof CollectionFragment) {
                                    if (!TextUtils.isEmpty(dynamicUITable.getFieldTag())
                                            && (dynamicUITable.getFieldTag().equalsIgnoreCase(TAG_NAME_TELECALLING_STATUS_IN_COLLECTION))) {
                                        if (!TextUtils.isEmpty(selectedRadioButton) && selectedRadioButton.equalsIgnoreCase(RADIO_BUTTON_ITEM_FIELD_NAME_COMPLETED)) {
                                            List<ParameterInfo> parameterInfoList = new ArrayList<>();
                                            parameterInfoList.add(new ParameterInfo(TAG_NAME_CALLING_REMARKS_IN_COLLECTION, SCREEN_ID, "", true, true));
                                            parameterInfoList.add(new ParameterInfo(TAG_NAME_PTP_DATE_COLLECTION, SCREEN_ID, "", true, true));
                                            parameterInfoList.add(new ParameterInfo(TAG_NAME_MODE_OF_PAYMENT_COLLECTION, SCREEN_ID, "", true, true));
                                            EnableOrDisableByFieldNameInDB(parameterInfoList, viewParametersList);
                                        } else {
                                            List<ParameterInfo> parameterInfoList = new ArrayList<>();
                                            parameterInfoList.add(new ParameterInfo(TAG_NAME_CALLING_REMARKS_IN_COLLECTION, SCREEN_ID, "", false, true));
                                            parameterInfoList.add(new ParameterInfo(TAG_NAME_PTP_DATE_COLLECTION, SCREEN_ID, "", false, true));
                                            parameterInfoList.add(new ParameterInfo(TAG_NAME_MODE_OF_PAYMENT_COLLECTION, SCREEN_ID, "", false, true));
                                            EnableOrDisableByFieldNameInDB(parameterInfoList, viewParametersList);
                                        }
                                    }
                                } else if (LOSBaseFragment.this instanceof LeadFragment) {
                                    setLeadScreenChangesByRadioButton(dynamicUITable, selectedRadioButton, viewParametersList);
                                } else if (LOSBaseFragment.this instanceof LeadSummaryFragment) {
                                    setLeadScreenChangesByRadioButton(dynamicUITable, selectedRadioButton, viewParametersList);
                                } else if (LOSBaseFragment.this instanceof GeneralIncomeFragment) {
//                                    // TODO: if verified is selected "no" then disble below fields
//                                    if (!TextUtils.isEmpty(dynamicUITable.getFieldTag())
//                                            && (dynamicUITable.getFieldTag().equalsIgnoreCase(TAG_NAME_VERIFIED_IN_GENERAL_INCOME))) {
//                                        if (!TextUtils.isEmpty(selectedRadioButton) && selectedRadioButton.equalsIgnoreCase("Yes")) {
//                                            List<ParameterInfo> parameterInfoList = new ArrayList<>();
//                                            parameterInfoList.add(new ParameterInfo(TAG_NAME_VERIFIED_INCOME_IN_GENERAL_INCOME, SCREEN_ID, "", true, true));
//                                            parameterInfoList.add(new ParameterInfo(TAG_NAME_STABILITY_OF_INCOME, SCREEN_ID, "", true, true));
//                                            parameterInfoList.add(new ParameterInfo(TAG_NAME_VERIFIED_BY_IN_GENERAL_INCOME, SCREEN_ID, "", true, true));
//                                            EnableOrDisableByFieldNameInDB(parameterInfoList, viewParametersList);
//                                        } else {
//                                            List<ParameterInfo> parameterInfoList = new ArrayList<>();
//                                            parameterInfoList.add(new ParameterInfo(TAG_NAME_VERIFIED_INCOME_IN_GENERAL_INCOME, SCREEN_ID, "", false, true));
//                                            parameterInfoList.add(new ParameterInfo(TAG_NAME_STABILITY_OF_INCOME, SCREEN_ID, "", false, true));
//                                            parameterInfoList.add(new ParameterInfo(TAG_NAME_VERIFIED_BY_IN_GENERAL_INCOME, SCREEN_ID, "", false, true));
//                                            EnableOrDisableByFieldNameInDB(parameterInfoList, viewParametersList);
//                                        }
//                                    }else {
//                                        setNamesByRadioButtonChanges(dynamicUITable, selectedRadioButton, viewParametersList);
//                                    }
                                    if (!TextUtils.isEmpty(dynamicUITable.getFieldTag())
                                            && dynamicUITable.getFieldTag().equalsIgnoreCase(TAG_NAME_CATEGORY_IN_GENERAL_INCOME) || dynamicUITable.getFieldTag().equalsIgnoreCase(TAG_NAME_VERIFIED_IN_GENERAL_INCOME)) {
                                        setNamesByRadioButtonChanges(dynamicUITable, selectedRadioButton, viewParametersList);
                                    }
                                } else if (LOSBaseFragment.this instanceof OtherIncomeSourceFragment) {
                                    setNamesByRadioButtonChanges(dynamicUITable, selectedRadioButton, viewParametersList);
                                } else if (LOSBaseFragment.this instanceof BankingHistoryFragment) {
                                    bankingHistoryCalculation(dynamicUITable, viewParametersList);
                                } else if (LOSBaseFragment.this instanceof SalesBillsFragment) {
                                    salesBillsCalculation(dynamicUITable, viewParametersList);
                                } else if (LOSBaseFragment.this instanceof DirectBusinessExpenseFragment) {
                                    directBusinessExpenseCalculation(dynamicUITable, viewParametersList);
                                } else if (LOSBaseFragment.this instanceof HouseLiabilitiesMsmeFragment) {
                                    houseLiabilitiesMsmeCalculation(dynamicUITable, viewParametersList);
                                } else if (LOSBaseFragment.this instanceof BusinessLiabilitiesMsmeFragment) {
                                    businessLiabilitiesMsmeCalculation(dynamicUITable, viewParametersList);
                                } else if (LOSBaseFragment.this instanceof PurchaseBillsFragment) {
                                    purchaseBillsCalculation(dynamicUITable, viewParametersList);
                                } else if (LOSBaseFragment.this instanceof GSTFragment) {
                                    GSTCalculation(dynamicUITable, viewParametersList);
                                } else if (LOSBaseFragment.this instanceof ITRFragment) {
                                    ITRCalculation(dynamicUITable, viewParametersList);
                                } else if (LOSBaseFragment.this instanceof ApplicantLoanProposalFragment
                                        && (dynamicUITable.getLoanType().equalsIgnoreCase(LOAN_NAME_MSME)|| dynamicUITable.getLoanType().equalsIgnoreCase(LOAN_NAME_AHL)|| dynamicUITable.getLoanType().equalsIgnoreCase(LOAN_NAME_PHL))) {
                                    applicantLoanProposalScreenChangesByDropdown(dynamicUITable, viewParametersList);
                                } else if (LOSBaseFragment.this instanceof NomineeDetailFragment
                                        && dynamicUITable.getLoanType().equalsIgnoreCase(LOAN_NAME_JLG)) {
                                    nomineeDetailsValidationInJLG(dynamicUITable, viewParametersList, null);

                                } else if (LOSBaseFragment.this instanceof PersonalDetailFragment) {
                                    if (!TextUtils.isEmpty(dynamicUITable.getLoanType()) && dynamicUITable.getLoanType().equalsIgnoreCase(LOAN_NAME_JLG)) {
                                        if (!TextUtils.isEmpty(dynamicUITable.getFieldTag()) && dynamicUITable.getFieldTag().equalsIgnoreCase(TAG_NAME_APPLICANT_MARITAL_STATUS)) {
                                            if (!TextUtils.isEmpty(selectedRadioButton) && selectedRadioButton.equalsIgnoreCase("Married")) {

                                                List<ParameterInfo> parameterInfoList = new ArrayList<>();
                                                parameterInfoList.add(new ParameterInfo(TAG_NAME_APPLICANT_SPOUSE_NAME, SCREEN_ID, "", true, true));
                                                parameterInfoList.add(new ParameterInfo(TAG_NAME_KYC_TYPE_SPOUSE_JLG, SCREEN_ID, "", true, true));
                                                parameterInfoList.add(new ParameterInfo(TAG_NAME_QR_READING_BUTTON, SCREEN_ID, "", true, true));
                                                parameterInfoList.add(new ParameterInfo(TAG_NAME_SPOUSE_DATE_OF_BIRTH, SCREEN_ID, "", true, true));
                                                parameterInfoList.add(new ParameterInfo(TAG_NAME_SPOUSE_AGE, SCREEN_ID, "", true, true));

                                                DynamicUITable dynamicUITablekycid = getObjectByFieldName(TAG_NAME_KYC_ID_SPOUSE_JLG, viewParametersList);
                                                if (dynamicUITablekycid != null && dynamicUITablekycid.getFieldTag() != null) {
                                                    parameterInfoList.add(new ParameterInfo(dynamicUITablekycid.getFieldTag(), SCREEN_ID, "", true, true));
                                                }
                                                parameterInfoList.add(new ParameterInfo(TAG_NAME_FATHER_NAME, SCREEN_ID, "", false, true));

                                                EnableOrDisableByFieldNameInDB(parameterInfoList, viewParametersList);
                                            } else if (!TextUtils.isEmpty(selectedRadioButton) && selectedRadioButton.equalsIgnoreCase("Widow")) {

                                                List<ParameterInfo> parameterInfoList = new ArrayList<>();
                                                parameterInfoList.add(new ParameterInfo(TAG_NAME_APPLICANT_SPOUSE_NAME, SCREEN_ID, "", false, true));
                                                parameterInfoList.add(new ParameterInfo(TAG_NAME_KYC_TYPE_SPOUSE_JLG, SCREEN_ID, "", false, true));
                                                parameterInfoList.add(new ParameterInfo(TAG_NAME_SPOUSE_DATE_OF_BIRTH, SCREEN_ID, "", false, true));
                                                parameterInfoList.add(new ParameterInfo(TAG_NAME_SPOUSE_AGE, SCREEN_ID, "", false, true));

                                                DynamicUITable dynamicUITablekycid = getObjectByFieldName(TAG_NAME_KYC_ID_SPOUSE_JLG, viewParametersList);
                                                if (dynamicUITablekycid != null && dynamicUITablekycid.getFieldTag() != null) {
                                                    parameterInfoList.add(new ParameterInfo(dynamicUITablekycid.getFieldTag(), SCREEN_ID, "", false, true));
                                                }
                                                parameterInfoList.add(new ParameterInfo(TAG_NAME_QR_READING_BUTTON, SCREEN_ID, "", false, true));
                                                parameterInfoList.add(new ParameterInfo(TAG_NAME_FATHER_NAME, SCREEN_ID, "", true, true));

                                                EnableOrDisableByFieldNameInDB(parameterInfoList, viewParametersList);
                                            } else if (!TextUtils.isEmpty(selectedRadioButton) && selectedRadioButton.equalsIgnoreCase("Single") && dynamicUITable.getLoanType().equalsIgnoreCase(LOAN_NAME_JLG)) {
                                                ageValidationJLG_Radio(dynamicUITable, viewParametersList);
                                            } else {

                                                List<ParameterInfo> parameterInfoList = new ArrayList<>();
                                                parameterInfoList.add(new ParameterInfo(TAG_NAME_APPLICANT_SPOUSE_NAME, SCREEN_ID, "", false, true));
                                                parameterInfoList.add(new ParameterInfo(TAG_NAME_KYC_TYPE_SPOUSE_JLG, SCREEN_ID, "", false, true));
                                                parameterInfoList.add(new ParameterInfo(TAG_NAME_SPOUSE_DATE_OF_BIRTH, SCREEN_ID, "", false, true));
                                                parameterInfoList.add(new ParameterInfo(TAG_NAME_SPOUSE_AGE, SCREEN_ID, "", false, true));

                                                DynamicUITable dynamicUITablekycid = getObjectByFieldName(TAG_NAME_KYC_ID_SPOUSE_JLG, viewParametersList);
                                                if (dynamicUITablekycid != null && dynamicUITablekycid.getFieldTag() != null) {
                                                    parameterInfoList.add(new ParameterInfo(dynamicUITablekycid.getFieldTag(), SCREEN_ID, "", false, true));
                                                }
                                                parameterInfoList.add(new ParameterInfo(TAG_NAME_QR_READING_BUTTON, SCREEN_ID, "", false, true));
                                                parameterInfoList.add(new ParameterInfo(TAG_NAME_FATHER_NAME, SCREEN_ID, "", true, true));
                                                EnableOrDisableByFieldNameInDB(parameterInfoList, viewParametersList);
                                            }
                                        }

                                    } else {
                                        if (!TextUtils.isEmpty(dynamicUITable.getFieldTag()) && dynamicUITable.getFieldTag().equalsIgnoreCase(TAG_NAME_APPLICANT_MARITAL_STATUS)) {
                                            DynamicUITable spouseNameObj = getObjectByTAG(TAG_NAME_SPOUSE_NAME, viewParametersList);
                                            // TODO: To autofill spouse name
                                            String spouseName = "";
                                            if (spouseNameObj != null) {
                                                spouseName = spouseNameObj.getValue();
                                            }

                                            if (!TextUtils.isEmpty(selectedRadioButton) && selectedRadioButton.equalsIgnoreCase("Married") ||
                                                    !TextUtils.isEmpty(selectedRadioButton) && selectedRadioButton.equalsIgnoreCase("Widow")) {
                                                List<ParameterInfo> parameterInfoList = new ArrayList<>();
                                                parameterInfoList.add(new ParameterInfo(TAG_NAME_APPLICANT_SPOUSE_NAME, SCREEN_ID, spouseName, true, true));
                                                parameterInfoList.add(new ParameterInfo(TAG_PD_DATE_OF_BIRTH, SCREEN_ID, "", true, true));
                                                parameterInfoList.add(new ParameterInfo(TAG_PD_AGE, SCREEN_ID, "", true, true));
                                                EnableOrDisableByFieldNameInDB(parameterInfoList, viewParametersList);
                                            } else {
                                                List<ParameterInfo> parameterInfoList = new ArrayList<>();
                                                parameterInfoList.add(new ParameterInfo(TAG_NAME_APPLICANT_SPOUSE_NAME, SCREEN_ID, "", false, true));
                                                parameterInfoList.add(new ParameterInfo(TAG_PD_DATE_OF_BIRTH, SCREEN_ID, "", false, true));
                                                parameterInfoList.add(new ParameterInfo(TAG_PD_AGE, SCREEN_ID, "", false, true));
                                                EnableOrDisableByFieldNameInDB(parameterInfoList, viewParametersList);
                                            }
                                        }
                                    }
                                } else if (LOSBaseFragment.this instanceof CoApplicantPersonalDetailFragment) {
                                    if (!TextUtils.isEmpty(dynamicUITable.getFieldTag()) && dynamicUITable.getFieldTag().equalsIgnoreCase(TAG_NAME_COAPPLICANT_MARITAL_STATUS)) {
                                        DynamicUITable spouseNameObj = getObjectByTAG(TAG_NAME_SPOUSE_NAME, viewParametersList);
                                        // TODO: To autofill spouse name
                                        String spouseName = "";
                                        if (spouseNameObj != null) {
                                            spouseName = spouseNameObj.getValue();
                                        }
                                        if (!TextUtils.isEmpty(selectedRadioButton) && selectedRadioButton.equalsIgnoreCase("Married") ||
                                                !TextUtils.isEmpty(selectedRadioButton) && selectedRadioButton.equalsIgnoreCase("Widow")) {

                                            List<ParameterInfo> parameterInfoList = new ArrayList<>();
                                            parameterInfoList.add(new ParameterInfo(TAG_NAME_COAPPLICANT_SPOUSE_NAME, SCREEN_ID, "", true, true));
                                            parameterInfoList.add(new ParameterInfo(TAG_PD_DATE_OF_BIRTH, SCREEN_ID, "", true, true));
                                            parameterInfoList.add(new ParameterInfo(TAG_PD_AGE, SCREEN_ID, "", true, true));
                                            EnableOrDisableByFieldNameInDB(parameterInfoList, viewParametersList);
                                        } else {
                                            List<ParameterInfo> parameterInfoList = new ArrayList<>();
                                            parameterInfoList.add(new ParameterInfo(TAG_NAME_COAPPLICANT_SPOUSE_NAME, SCREEN_ID, "", false, true));
                                            parameterInfoList.add(new ParameterInfo(TAG_PD_DATE_OF_BIRTH, SCREEN_ID, "", false, true));
                                            parameterInfoList.add(new ParameterInfo(TAG_PD_AGE, SCREEN_ID, "", false, true));
                                            EnableOrDisableByFieldNameInDB(parameterInfoList, viewParametersList);
                                        }
                                    }

                                } else if (LOSBaseFragment.this instanceof DeclaredSalesWeeklyFragment) {
                                    setDeclaredSalesWeeklyChangesByRadioButton(dynamicUITable, selectedRadioButton, viewParametersList);
                                } else if (LOSBaseFragment.this instanceof PurchaseAnalysisFragment) {
                                    if (!TextUtils.isEmpty(dynamicUITable.getFieldTag()) &&
                                            dynamicUITable.getFieldTag().equalsIgnoreCase(TAG_NAME_FREQUENCY_IN_PURCHASE_ANALYSIS)) {
                                        calculatePurchaseFrequency(dynamicUITable, viewParametersList);
                                    }
                                } else if (LOSBaseFragment.this instanceof ChildFragment) {
                                    if (SCREEN_NAME.equalsIgnoreCase(SCREEN_NAME_HOUSE_ASSETS_BUILDING_DETAIL_MSME)
                                            || SCREEN_NAME.equalsIgnoreCase(SCREEN_NAME_HOUSE_ASSETS_LAND_DETAIL_MSME)
                                            || SCREEN_NAME.equalsIgnoreCase(SCREEN_NAME_HOUSE_ASSETS_OPEN_PLOT_DETAIL_MSME)
                                            || SCREEN_NAME.equalsIgnoreCase(SCREEN_NAME_HOUSE_ASSETS_TWO_WHEELER_DETAIL_MSME)
                                            || SCREEN_NAME.equalsIgnoreCase(SCREEN_NAME_HOUSE_ASSETS_CAR_DETAIL_MSME)
                                            || SCREEN_NAME.equalsIgnoreCase(SCREEN_NAME_HOUSE_ASSETS_VEHICLES_DETAIL_MSME)
                                            || SCREEN_NAME.equalsIgnoreCase(SCREEN_NAME_HOUSE_ASSETS_PLANT_MACHINERY_DETAIL_MSME)
                                            || SCREEN_NAME.equalsIgnoreCase(SCREEN_NAME_HOUSE_ASSETS_UTILITIES_DETAIL_MSME)
                                            || SCREEN_NAME.equalsIgnoreCase(SCREEN_NAME_HOUSE_ASSETS_LIVESTOCK_DETAIL_MSME)
                                            || SCREEN_NAME.equalsIgnoreCase(SCREEN_NAME_HOUSE_ASSETS_EQUIPMENT_DETAIL_MSME)
                                            || SCREEN_NAME.equalsIgnoreCase(SCREEN_NAME_HOUSE_ASSETS_TELEVISION_DETAIL_MSME)
                                            || SCREEN_NAME.equalsIgnoreCase(SCREEN_NAME_HOUSE_ASSETS_OTHERS_DETAIL_MSME)
                                            || SCREEN_NAME.equalsIgnoreCase(SCREEN_NAME_HOUSE_ASSETS_FURNITURE_DETAIL_MSME)
                                            || SCREEN_NAME.equalsIgnoreCase(SCREEN_NAME_HOUSE_ASSETS_COMMERCIAL_VEHICLE_DETAIL_MSME)
                                    ) {
                                        FixedAssetValidation(dynamicUITable, viewParametersList);
                                    } else if (SCREEN_NAME.equalsIgnoreCase(SCREEN_NAME_BUSINESS_ASSETS_BUILDING_DETAIL_MSME)
                                            || SCREEN_NAME.equalsIgnoreCase(SCREEN_NAME_BUSINESS_ASSETS_LAND_DETAIL_MSME)
                                            || SCREEN_NAME.equalsIgnoreCase(SCREEN_NAME_BUSINESS_ASSETS_OPEN_PLOT_DETAIL_MSME)
                                            || SCREEN_NAME.equalsIgnoreCase(SCREEN_NAME_BUSINESS_ASSETS_TWO_WHEELER_DETAIL_MSME)
                                            || SCREEN_NAME.equalsIgnoreCase(SCREEN_NAME_BUSINESS_ASSETS_CAR_DETAIL_MSME)
                                            || SCREEN_NAME.equalsIgnoreCase(SCREEN_NAME_BUSINESS_ASSETS_VEHICLES_DETAIL_MSME)
                                            || SCREEN_NAME.equalsIgnoreCase(SCREEN_NAME_BUSINESS_ASSETS_PLANT_MACHINERY_DETAIL_MSME)
                                            || SCREEN_NAME.equalsIgnoreCase(SCREEN_NAME_BUSINESS_ASSETS_UTILITIES_DETAIL_MSME)
                                            || SCREEN_NAME.equalsIgnoreCase(SCREEN_NAME_BUSINESS_ASSETS_LIVESTOCK_DETAIL_MSME)
                                            || SCREEN_NAME.equalsIgnoreCase(SCREEN_NAME_BUSINESS_ASSETS_EQUIPMENT_DETAIL_MSME)
                                            || SCREEN_NAME.equalsIgnoreCase(SCREEN_NAME_BUSINESS_ASSETS_TELEVISION_DETAIL_MSME)
                                            || SCREEN_NAME.equalsIgnoreCase(SCREEN_NAME_BUSINESS_ASSETS_OTHERS_DETAIL_MSME)
                                            || SCREEN_NAME.equalsIgnoreCase(SCREEN_NAME_BUSINESS_ASSETS_FURNITURE_DETAIL_MSME)
                                            || SCREEN_NAME.equalsIgnoreCase(SCREEN_NAME_BUSINESS_ASSETS_COMMERCIAL_VEHICLE_DETAIL_MSME)
                                    ) {
                                        FixedAssetValidation(dynamicUITable, viewParametersList);
                                    } else if (SCREEN_NAME.equalsIgnoreCase(SCREEN_NAME_MORTGAGE_BUILDING_MSME)
                                            || SCREEN_NAME.equalsIgnoreCase(SCREEN_NAME_MORTGAGE_LAND_MSME)
                                            || SCREEN_NAME.equalsIgnoreCase(SCREEN_NAME_MORTGAGE_OPEN_PLOT_MSME)
                                    ) {
                                        FixedAssetValidation(dynamicUITable, viewParametersList);
                                    } else if (SCREEN_NAME.equalsIgnoreCase(SCREEN_NAME_GENERAL_INCOME)
                                    ) {
                                        // TODO: if verified is selected "no" then disable below fields
                                        if (!TextUtils.isEmpty(dynamicUITable.getFieldTag())
                                                && (dynamicUITable.getFieldTag().equalsIgnoreCase(TAG_NAME_VERIFIED_IN_GENERAL_INCOME))) {
                                            if (!TextUtils.isEmpty(selectedRadioButton) && selectedRadioButton.equalsIgnoreCase("Yes")) {
                                                List<ParameterInfo> parameterInfoList = new ArrayList<>();
                                                parameterInfoList.add(new ParameterInfo(TAG_NAME_VERIFIED_INCOME_IN_GENERAL_INCOME, SCREEN_ID, "", true, true));
                                                parameterInfoList.add(new ParameterInfo(TAG_NAME_STABILITY_OF_INCOME, SCREEN_ID, "", true, true));
                                                parameterInfoList.add(new ParameterInfo(TAG_NAME_VERIFIED_BY_IN_GENERAL_INCOME, SCREEN_ID, "", true, true));
                                                EnableOrDisableByFieldNameInDB(parameterInfoList, viewParametersList);
                                            } else {
                                                List<ParameterInfo> parameterInfoList = new ArrayList<>();
                                                parameterInfoList.add(new ParameterInfo(TAG_NAME_VERIFIED_INCOME_IN_GENERAL_INCOME, SCREEN_ID, "", false, true));
                                                parameterInfoList.add(new ParameterInfo(TAG_NAME_STABILITY_OF_INCOME, SCREEN_ID, "", false, true));
                                                parameterInfoList.add(new ParameterInfo(TAG_NAME_VERIFIED_BY_IN_GENERAL_INCOME, SCREEN_ID, "", false, true));
                                                EnableOrDisableByFieldNameInDB(parameterInfoList, viewParametersList);
                                            }
                                        }
                                    } else if (SCREEN_NAME.equalsIgnoreCase(SCREEN_NAME_OTHER_INCOME_SOURCE)
                                    ) {
                                        // TODO: if verified is selected "no" then disable below fields
                                        if (!TextUtils.isEmpty(dynamicUITable.getFieldTag())
                                                && (dynamicUITable.getFieldTag().equalsIgnoreCase(TAG_NAME_VERIFIED_IN_OTHER_INCOME))) {
                                            if (!TextUtils.isEmpty(selectedRadioButton) && selectedRadioButton.equalsIgnoreCase("Yes")) {
                                                List<ParameterInfo> parameterInfoList = new ArrayList<>();
                                                parameterInfoList.add(new ParameterInfo(TAG_NAME_VERIFIED_INCOME_IN_OTHER_INCOME, SCREEN_ID, "", true, true));
                                                EnableOrDisableByFieldNameInDB(parameterInfoList, viewParametersList);
                                            } else {
                                                List<ParameterInfo> parameterInfoList = new ArrayList<>();
                                                parameterInfoList.add(new ParameterInfo(TAG_NAME_VERIFIED_INCOME_IN_OTHER_INCOME, SCREEN_ID, "", false, true));
                                                EnableOrDisableByFieldNameInDB(parameterInfoList, viewParametersList);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    });

                }

                // TODO: DROP DOWN
                else if ((!TextUtils.isEmpty(viewParameters.getFieldType())
                        && viewParameters.getFieldType().equalsIgnoreCase(FIELD_TYPE_LIST_BOX)
                        || viewParameters.getFieldType().equalsIgnoreCase(FIELD_TYPE_LIST)
                        || viewParameters.getFieldType().equalsIgnoreCase(FIELD_TYPE_DROPDOWN)
                        || viewParameters.getFieldType().equalsIgnoreCase(FIELD_TYPE_DROPDOWN1)
                        || viewParameters.getFieldType().equalsIgnoreCase(FIELD_TYPE_DROPDOWN2))) { // TODO:  SPINNER


                    LinearLayout llParent = new LinearLayout(getActivity());
                    LinearLayout.LayoutParams llParentParrams = new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT);
                    llParent.setOrientation(LinearLayout.VERTICAL);

                    llParentParrams.setMargins(0, 15, 5, 10);
                    llParent.setLayoutParams(llParentParrams);

                    llParent.addView(addTextViews(viewParameters));

                    LinearLayout llSpinner = new LinearLayout(getActivity());
                    LinearLayout.LayoutParams llSpinnerParrams = new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT);
                    llSpinner.setOrientation(LinearLayout.VERTICAL);
                    llSpinnerParrams.weight = 1;
                    llSpinner.setBackground(this.getResources().getDrawable(R.drawable.bg_custom_border));
                    llSpinner.setLayoutParams(llSpinnerParrams);

                    final List<String> spinnerParamList = Arrays.asList(viewParameters.getParamlist());
                    final XMLCustomSpinner customSpinner = new XMLCustomSpinner(getActivity(), spinnerParamList, "0", viewParameters, ll);
                    if (viewParameters.getParamlist() != null && viewParameters.getParamlist().length > 0) {
                        for (int s = 0; s < viewParameters.getParamlist().length; s++) {
                            String spinnerItem = viewParameters.getParamlist()[s];
                            if (!TextUtils.isEmpty(spinnerItem) && !TextUtils.isEmpty(viewParameters.getValue())) {
                                if (!spinnerItem.equalsIgnoreCase("null")) {
                                    spinnerItem = spinnerItem.trim();
                                    if (spinnerItem.equalsIgnoreCase(viewParameters.getValue())) {
                                        customSpinner.setSelection(s + 1);
                                        if (!viewParameters.isEditable()) {
                                            customSpinner.setEnabled(false); // TODO: Need to check
                                        }
                                    }
                                }
                            }
                        }
                    }


                    customSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                            if (customSpinner.spinnerTouched && customSpinner.getSelectedItemPosition() > 0) {

                                customSpinner.spinnerTouched = false; // TODO: Again setting as false

                                String selectedItem = customSpinner.getSelectedItem().toString();
                                if (!TextUtils.isEmpty(selectedItem)) {
                                    viewParameters.setValue(selectedItem);
                                }

                                if (LOSBaseFragment.this instanceof ApplicantKYCFragment
                                        || LOSBaseFragment.this instanceof CoApplicantKYCFragment) {
                                    // TODO: APPLICANT Fragment spinner select
                                    //getRawDataFromTable(viewParameters, viewParametersList);
                                    setApplicantScreenChangesBySpinner(viewParametersList, viewParameters, customSpinner);
                                } else if (LOSBaseFragment.this instanceof PersonalDetailFragment) {
                                    if (viewParameters.getLoanType().equalsIgnoreCase(LOAN_NAME_JLG)) {
                                        // TODO: Personal detail Fragment spinner select
                                        setPersonalScreenChangesBySpinner(viewParametersList, viewParameters, customSpinner);
                                    }
                                } else if (LOSBaseFragment.this instanceof AddressDetailsFragment) {
                                    // TODO: Address Fragment spinner select
                                    if (viewParameters.getFieldTag().equalsIgnoreCase(TAG_NAME_PERMANENT_KYC_TYPE)) {
                                        DynamicUITable dynamicUITable = getObjectByFieldName(FIELD_NAME_PERMANENT_KYC_ID, viewParametersList);
                                        if (dynamicUITable != null) {
                                            DataTypeInfo datatypeInfo = new DataTypeInfo(selectedItem, dynamicUITable);

                                            dynamicUITable.setLength(datatypeInfo.getLength());
                                            dynamicUITable.setHint(datatypeInfo.getHint());
                                            dynamicUITable.setDataType(datatypeInfo.getInputType());
                                            dynamicUITable.setDataEntryType(datatypeInfo.getDataEntryType());
                                            dynamicUITable.setFieldTag(datatypeInfo.getHintTag());
                                            dynamicUITable.setValue("");
                                            dynamicUITable.setErrorMessage("");// TODO: clearing the value once changing the spinner item
                                            if (viewParameters.getValue().equalsIgnoreCase(SPINNER_ITEM_FIELD_NAME_UTILITY_BILLS)
                                                    || viewParameters.getValue().equalsIgnoreCase(SPINNER_ITEM_FIELD_NAME_BANK_PASSBOOK_WITH_PHOTOGRAPH)) {
                                                dynamicUITable.setVisibility(false);
                                            } else if (viewParameters.getValue().equalsIgnoreCase(SPINNER_ITEM_FIELD_NAME_DEEMED_OVD)
                                                    || viewParameters.getValue().equalsIgnoreCase(SPINNER_ITEM_FIELD_NAME_UIDAI_ELIGIBLE_DOCUMENTS)) {
                                                dynamicUITable.setVisibility(true);
                                                dynamicUITable.setIsRequired(VIEW_NOT_REQUIRED);
                                            } else {
                                                dynamicUITable.setVisibility(true);//
                                                dynamicUITable.setIsRequired(VIEW_REQUIRED);
                                            }
                                        }

                                    } else if (viewParameters.getFieldTag().equalsIgnoreCase(TAG_NAME_COMMUNICATION_KYC_TYPE)) {
                                        DynamicUITable dynamicUITable = getObjectByFieldName(FIELD_NAME_COMMUNICATION_KYC_ID, viewParametersList);
                                        if (dynamicUITable != null) {
                                            DataTypeInfo datatypeInfo = new DataTypeInfo(selectedItem, dynamicUITable);
                                            dynamicUITable.setLength(datatypeInfo.getLength());
                                            dynamicUITable.setHint(datatypeInfo.getHint());
                                            dynamicUITable.setDataType(datatypeInfo.getInputType());
                                            dynamicUITable.setDataEntryType(datatypeInfo.getDataEntryType());
                                            dynamicUITable.setFieldTag(datatypeInfo.getHintTag());
                                            dynamicUITable.setValue("");
                                            dynamicUITable.setErrorMessage("");// TODO: clearing the value once changing the spinner item
                                            if (viewParameters.getValue().equalsIgnoreCase(SPINNER_ITEM_FIELD_NAME_UTILITY_BILLS)) {
                                                dynamicUITable.setVisibility(false);
                                            } else if (viewParameters.getValue().equalsIgnoreCase(SPINNER_ITEM_FIELD_NAME_DEEMED_OVD)
                                                    || viewParameters.getValue().equalsIgnoreCase(SPINNER_ITEM_FIELD_NAME_UIDAI_ELIGIBLE_DOCUMENTS)) {
                                                dynamicUITable.setVisibility(true);
                                                dynamicUITable.setIsRequired(VIEW_NOT_REQUIRED);
                                            } else {
                                                dynamicUITable.setVisibility(true);
                                                dynamicUITable.setIsRequired(VIEW_REQUIRED);
                                            }
                                        }

                                    }
//                                    copyAddressBasedOnDropDown(viewParameters, viewParametersList, SCREEN_NAME_APPLICANT_KYC);

                                    addressDetailValidation(viewParameters, viewParametersList);
                                } else if (LOSBaseFragment.this instanceof CoApplicantAddressDetailFragment) {
                                    // TODO: Co Applicant Address Fragment spinner select

                                    if (viewParameters.getFieldTag().equalsIgnoreCase(TAG_NAME_PERMANENT_KYC_TYPE)) {
                                        DynamicUITable dynamicUITable = getObjectByFieldName(FIELD_NAME_PERMANENT_KYC_ID, viewParametersList);
                                        if (dynamicUITable != null) {
                                            DataTypeInfo datatypeInfo = new DataTypeInfo(selectedItem, dynamicUITable);

                                            dynamicUITable.setLength(datatypeInfo.getLength());
                                            dynamicUITable.setHint(datatypeInfo.getHint());
                                            dynamicUITable.setDataType(datatypeInfo.getInputType());
                                            dynamicUITable.setDataEntryType(datatypeInfo.getDataEntryType());
                                            dynamicUITable.setFieldTag(datatypeInfo.getHintTag());
                                            dynamicUITable.setValue("");
                                            dynamicUITable.setErrorMessage("");// TODO: clearing the value once changing the spinner item
                                            if (viewParameters.getValue().equalsIgnoreCase(SPINNER_ITEM_FIELD_NAME_UTILITY_BILLS)
                                                    || viewParameters.getValue().equalsIgnoreCase(SPINNER_ITEM_FIELD_NAME_BANK_PASSBOOK_WITH_PHOTOGRAPH)) {
                                                dynamicUITable.setVisibility(false);
                                            } else if (viewParameters.getValue().equalsIgnoreCase(SPINNER_ITEM_FIELD_NAME_DEEMED_OVD)
                                                    || viewParameters.getValue().equalsIgnoreCase(SPINNER_ITEM_FIELD_NAME_UIDAI_ELIGIBLE_DOCUMENTS)) {
                                                dynamicUITable.setVisibility(true);
                                                dynamicUITable.setIsRequired(VIEW_NOT_REQUIRED);
                                            } else {
                                                dynamicUITable.setVisibility(true);
                                                dynamicUITable.setIsRequired(VIEW_REQUIRED);
                                            }
                                        }

                                    } else if (viewParameters.getFieldTag().equalsIgnoreCase(TAG_NAME_COMMUNICATION_KYC_TYPE)) {
                                        DynamicUITable dynamicUITable = getObjectByFieldName(FIELD_NAME_COMMUNICATION_KYC_ID, viewParametersList);
                                        if (dynamicUITable != null) {
                                            DataTypeInfo datatypeInfo = new DataTypeInfo(selectedItem, dynamicUITable);
                                            dynamicUITable.setLength(datatypeInfo.getLength());
                                            dynamicUITable.setHint(datatypeInfo.getHint());
                                            dynamicUITable.setDataType(datatypeInfo.getInputType());
                                            dynamicUITable.setDataEntryType(datatypeInfo.getDataEntryType());
                                            dynamicUITable.setFieldTag(datatypeInfo.getHintTag());
                                            dynamicUITable.setValue("");
                                            dynamicUITable.setErrorMessage("");
                                            if (viewParameters.getValue().equalsIgnoreCase(SPINNER_ITEM_FIELD_NAME_UTILITY_BILLS)
                                                    || viewParameters.getValue().equalsIgnoreCase(SPINNER_ITEM_FIELD_NAME_BANK_PASSBOOK_WITH_PHOTOGRAPH)) {
                                                dynamicUITable.setVisibility(false);
                                            } else if (viewParameters.getValue().equalsIgnoreCase(SPINNER_ITEM_FIELD_NAME_DEEMED_OVD)
                                                    || viewParameters.getValue().equalsIgnoreCase(SPINNER_ITEM_FIELD_NAME_UIDAI_ELIGIBLE_DOCUMENTS)) {
                                                dynamicUITable.setVisibility(true);
                                                dynamicUITable.setIsRequired(VIEW_NOT_REQUIRED);
                                            } else {
                                                dynamicUITable.setVisibility(true);
                                                dynamicUITable.setIsRequired(VIEW_REQUIRED);
                                            }
                                        }
                                    }
//                                    copyAddressBasedOnDropDown(viewParameters, viewParametersList, SCREEN_NAME_CO_APPLICANT_KYC);
                                    addressDetailValidation(viewParameters, viewParametersList);
                                } else if (LOSBaseFragment.this instanceof CoApplicantPersonalDetailFragment) {
                                    // TODO: CoApplicant Personal Detail Fragment spinner select
                                    if (viewParameters.getFieldTag().equalsIgnoreCase(TAG_NAME_COAPPLICANT_RELATIONSHIP_WITH_THE_APPLICANT)) {
                                        if (viewParameters.getValue().equalsIgnoreCase(SPINNER_ITEM_FIELD_NAME_SPOUSE)) {
                                            getSpouseNameFromApplicantKYC(viewParameters, viewParametersList);
                                        } else {
                                            DynamicUITable comaritalStatusObj = getObjectByTAG(TAG_NAME_COAPPLICANT_MARITAL_STATUS, viewParametersList);
                                            if (comaritalStatusObj != null) {
                                                String selectedRadioButton = comaritalStatusObj.getValue();
                                                if (!TextUtils.isEmpty(selectedRadioButton) && selectedRadioButton.equalsIgnoreCase("Married") ||
                                                        !TextUtils.isEmpty(selectedRadioButton) && selectedRadioButton.equalsIgnoreCase("Widow")) {

                                                    List<ParameterInfo> parameterInfoList = new ArrayList<>();
                                                    parameterInfoList.add(new ParameterInfo(TAG_NAME_COAPPLICANT_SPOUSE_NAME, SCREEN_ID, "", true, true));
                                                    parameterInfoList.add(new ParameterInfo(TAG_PD_DATE_OF_BIRTH, SCREEN_ID, "", true, true));
                                                    parameterInfoList.add(new ParameterInfo(TAG_PD_AGE, SCREEN_ID, "", true, true));
                                                    EnableOrDisableByFieldNameInDB(parameterInfoList, viewParametersList);
                                                } else {
                                                    List<ParameterInfo> parameterInfoList = new ArrayList<>();
                                                    parameterInfoList.add(new ParameterInfo(TAG_NAME_COAPPLICANT_SPOUSE_NAME, SCREEN_ID, "", false, true));
                                                    parameterInfoList.add(new ParameterInfo(TAG_PD_DATE_OF_BIRTH, SCREEN_ID, "", false, true));
                                                    parameterInfoList.add(new ParameterInfo(TAG_PD_AGE, SCREEN_ID, "", false, true));
                                                    EnableOrDisableByFieldNameInDB(parameterInfoList, viewParametersList);
                                                }

                                            }
                                        }
                                    }

                                }else if(LOSBaseFragment.this instanceof SalesToolFragment) {

                                    if (!TextUtils.isEmpty(viewParameters.getFieldTag())
                                            && (viewParameters.getFieldTag().equalsIgnoreCase(TAG_NAME_INCOME_PROOF_AVAILABLE_IN_SALES_TOOL))) {
                                        if (!TextUtils.isEmpty(selectedItem) && selectedItem.equalsIgnoreCase(SPINNER_ITEM_FIELD_NAME_ITR_NO_OF_YEARS)) {
                                            List<ParameterInfo> parameterInfoList = new ArrayList<>();
                                            parameterInfoList.add(new ParameterInfo(TAG_NAME_ITR_NO_OF_YEARS_IN_SALES_TOOL, SCREEN_ID, "", true, true));
                                            EnableOrDisableByFieldNameInDB(parameterInfoList, viewParametersList);
                                        } else {
                                            List<ParameterInfo> parameterInfoList = new ArrayList<>();
                                            parameterInfoList.add(new ParameterInfo(TAG_NAME_ITR_NO_OF_YEARS_IN_SALES_TOOL, SCREEN_ID, "", false, true));
                                            EnableOrDisableByFieldNameInDB(parameterInfoList, viewParametersList);
                                        }

                                    }
                                }else if (LOSBaseFragment.this instanceof BusinessAddressProofFragment) {
                                    // TODO: Business Proof Fragment spinner select
                                    copyBusinessProofAddressBasedOnDropDown(viewParameters, viewParametersList);
                                } else if (LOSBaseFragment.this instanceof NomineeDetailFragment) {
                                    if (viewParameters.getLoanType().equalsIgnoreCase(LOAN_NAME_JLG)) {
                                        nomineeDetailsValidationInJLG(viewParameters, viewParametersList, customSpinner);
                                    } else {
                                        // TODO: Nominee Detail Fragment spinner select
                                        getCoApplicantDetails(viewParameters, viewParametersList, SCREEN_NAME_CO_APPLICANT_KYC);
                                    }
                                } else if (LOSBaseFragment.this instanceof LoanProposalSummaryFragment) {
                                    // TODO: Loan proposal summary screen
                                    calculateLoanProposal(viewParametersList.get(0), viewParametersList);
                                } else if (LOSBaseFragment.this instanceof GeneralIncomeFragment) {
                                    // TODO: General Income Screen
                                    if (!TextUtils.isEmpty(viewParameters.getFieldTag())
                                            && viewParameters.getFieldTag().equalsIgnoreCase(TAG_NAME_NAME_IN_GENERAL_INCOME)) {
                                        DynamicUITable dynamicUITable = getObjectByFieldName(TAG_NAME_CATEGORY_IN_GENERAL_INCOME, viewParametersList);
                                        String selectedRadioButton = dynamicUITable.getValue();
                                        setGeneralIncomeScreenChangesByDropDown(viewParameters, selectedItem, selectedRadioButton, viewParametersList);
                                    }
                                } else if (LOSBaseFragment.this instanceof OtherIncomeSourceFragment) {
                                    // TODO: Other Income Screen
                                    DynamicUITable dynamicUITable = getObjectByFieldName(TAG_NAME_CATEGORY_IN_OTHER_INCOME, viewParametersList);
                                    String selectedRadioButton = dynamicUITable.getValue();
                                    setOtherIncomeScreenChangesByDropDown(viewParameters, selectedItem, selectedRadioButton, viewParametersList);
                                } else if (LOSBaseFragment.this instanceof SubjectToConditionsMsmeFragment) {
                                    // TODO: Subject to Conditions Screen
                                    if (viewParameters.getFieldTag().equalsIgnoreCase(TAG_NAME_TYPE_OF_CONDITION_IN_SUBJECT_TO_CONDITIONS_MSME)) {
                                        if (viewParameters.getValue().equalsIgnoreCase(SPINNER_ITEM_FIELD_NAME_PRE_DISBURSEMENT_CONDITIONS)) {
                                            List<ParameterInfo> parameterInfoList = new ArrayList<>();
                                            parameterInfoList.add(new ParameterInfo(TAG_NAME_INTERVAL_IN_SUBJECT_TO_CONDITIONS_MSME, SCREEN_ID, "", false, true));
                                            parameterInfoList.add(new ParameterInfo(TAG_NAME_INTERVAL_TYPE_IN_SUBJECT_TO_CONDITIONS_MSME, SCREEN_ID, "", false, true));
                                            EnableOrDisableByFieldNameInDB(parameterInfoList, viewParametersList);
                                        } else {
                                            List<ParameterInfo> parameterInfoList = new ArrayList<>();
                                            parameterInfoList.add(new ParameterInfo(TAG_NAME_INTERVAL_IN_SUBJECT_TO_CONDITIONS_MSME, SCREEN_ID, "", true, true));
                                            parameterInfoList.add(new ParameterInfo(TAG_NAME_INTERVAL_TYPE_IN_SUBJECT_TO_CONDITIONS_MSME, SCREEN_ID, "", true, true));
                                            EnableOrDisableByFieldNameInDB(parameterInfoList, viewParametersList);
                                        }
                                    }

                                } else if (LOSBaseFragment.this instanceof ChildFragment) {
                                    if (SCREEN_NAME.equalsIgnoreCase(SCREEN_NAME_HOUSE_ASSETS_BUILDING_DETAIL_MSME)
                                            || SCREEN_NAME.equalsIgnoreCase(SCREEN_NAME_HOUSE_ASSETS_LAND_DETAIL_MSME)
                                            || SCREEN_NAME.equalsIgnoreCase(SCREEN_NAME_HOUSE_ASSETS_OPEN_PLOT_DETAIL_MSME)
                                            || SCREEN_NAME.equalsIgnoreCase(SCREEN_NAME_HOUSE_ASSETS_TWO_WHEELER_DETAIL_MSME)
                                            || SCREEN_NAME.equalsIgnoreCase(SCREEN_NAME_HOUSE_ASSETS_CAR_DETAIL_MSME)
                                            || SCREEN_NAME.equalsIgnoreCase(SCREEN_NAME_HOUSE_ASSETS_VEHICLES_DETAIL_MSME)
                                            || SCREEN_NAME.equalsIgnoreCase(SCREEN_NAME_HOUSE_ASSETS_PLANT_MACHINERY_DETAIL_MSME)
                                            || SCREEN_NAME.equalsIgnoreCase(SCREEN_NAME_HOUSE_ASSETS_UTILITIES_DETAIL_MSME)
                                            || SCREEN_NAME.equalsIgnoreCase(SCREEN_NAME_HOUSE_ASSETS_LIVESTOCK_DETAIL_MSME)
                                            || SCREEN_NAME.equalsIgnoreCase(SCREEN_NAME_HOUSE_ASSETS_EQUIPMENT_DETAIL_MSME)
                                            || SCREEN_NAME.equalsIgnoreCase(SCREEN_NAME_HOUSE_ASSETS_TELEVISION_DETAIL_MSME)
                                            || SCREEN_NAME.equalsIgnoreCase(SCREEN_NAME_HOUSE_ASSETS_OTHERS_DETAIL_MSME)
                                            || SCREEN_NAME.equalsIgnoreCase(SCREEN_NAME_HOUSE_ASSETS_FURNITURE_DETAIL_MSME)
                                            || SCREEN_NAME.equalsIgnoreCase(SCREEN_NAME_HOUSE_ASSETS_COMMERCIAL_VEHICLE_DETAIL_MSME)
                                    ) {
                                        FixedAssetValidation(viewParameters, viewParametersList);
                                    } else if (SCREEN_NAME.equalsIgnoreCase(SCREEN_NAME_BUSINESS_ASSETS_BUILDING_DETAIL_MSME)
                                            || SCREEN_NAME.equalsIgnoreCase(SCREEN_NAME_BUSINESS_ASSETS_LAND_DETAIL_MSME)
                                            || SCREEN_NAME.equalsIgnoreCase(SCREEN_NAME_BUSINESS_ASSETS_OPEN_PLOT_DETAIL_MSME)
                                            || SCREEN_NAME.equalsIgnoreCase(SCREEN_NAME_BUSINESS_ASSETS_TWO_WHEELER_DETAIL_MSME)
                                            || SCREEN_NAME.equalsIgnoreCase(SCREEN_NAME_BUSINESS_ASSETS_CAR_DETAIL_MSME)
                                            || SCREEN_NAME.equalsIgnoreCase(SCREEN_NAME_BUSINESS_ASSETS_VEHICLES_DETAIL_MSME)
                                            || SCREEN_NAME.equalsIgnoreCase(SCREEN_NAME_BUSINESS_ASSETS_PLANT_MACHINERY_DETAIL_MSME)
                                            || SCREEN_NAME.equalsIgnoreCase(SCREEN_NAME_BUSINESS_ASSETS_UTILITIES_DETAIL_MSME)
                                            || SCREEN_NAME.equalsIgnoreCase(SCREEN_NAME_BUSINESS_ASSETS_LIVESTOCK_DETAIL_MSME)
                                            || SCREEN_NAME.equalsIgnoreCase(SCREEN_NAME_BUSINESS_ASSETS_EQUIPMENT_DETAIL_MSME)
                                            || SCREEN_NAME.equalsIgnoreCase(SCREEN_NAME_BUSINESS_ASSETS_TELEVISION_DETAIL_MSME)
                                            || SCREEN_NAME.equalsIgnoreCase(SCREEN_NAME_BUSINESS_ASSETS_OTHERS_DETAIL_MSME)
                                            || SCREEN_NAME.equalsIgnoreCase(SCREEN_NAME_BUSINESS_ASSETS_FURNITURE_DETAIL_MSME)
                                            || SCREEN_NAME.equalsIgnoreCase(SCREEN_NAME_BUSINESS_ASSETS_COMMERCIAL_VEHICLE_DETAIL_MSME)
                                    ) {
                                        FixedAssetValidation(viewParameters, viewParametersList);
                                    } else if (SCREEN_NAME.equalsIgnoreCase(SCREEN_NAME_MORTGAGE_BUILDING_MSME)
                                            || SCREEN_NAME.equalsIgnoreCase(SCREEN_NAME_MORTGAGE_LAND_MSME)
                                            || SCREEN_NAME.equalsIgnoreCase(SCREEN_NAME_MORTGAGE_OPEN_PLOT_MSME)
                                    ) {
                                        FixedAssetValidation(viewParameters, viewParametersList);
                                    } else if (SCREEN_NAME.equalsIgnoreCase(SCREEN_NAME_SAVINGS_CHITS_BUSINESS_ASSETS_MSME)
                                            || SCREEN_NAME.equalsIgnoreCase(SCREEN_NAME_INSURANCE_BUSINESS_ASSETS_MSME)
                                            || SCREEN_NAME.equalsIgnoreCase(SCREEN_NAME_RECEIVABLES_BUSINESS_ASSETS_MSME)
                                    ) {
                                        // TODO:  Business Assessment Child Screen Changes
                                        setBusinessAssetsChildScreenChangesByDropDown(viewParameters, selectedItem, viewParametersList);
                                    } else if (SCREEN_NAME.equalsIgnoreCase(SCREEN_NAME_DIRECT_BUSINESS_EXPENSE_DETAIL_MSME)) {
                                        DynamicUITable dynamicUITable = (DynamicUITable) customSpinner.getTag();
                                        if (dynamicUITable != null && !TextUtils.isEmpty(dynamicUITable.getFieldTag())) {
                                            if (dynamicUITable.getFieldTag().equalsIgnoreCase(TAG_NAME_EXPENSE_NAME_BUSINESS_EXPENSE)) {
                                                String selectedDropdownItem = dynamicUITable.getValue();//
                                                setBusinessExpenseScreenChangesByDropDown(viewParameters, selectedItem, viewParametersList);//
                                            } else if (dynamicUITable.getFieldTag().equalsIgnoreCase(TAG_NAME_FREQUENCY_IN_DIRECT_BUSINESS_EXPENSE)) {

                                                DynamicUITable valueObj = getObjectByTAG(TAG_NAME_VALUE_IN_DIRECT_BUSINESS_EXPENSE, viewParametersList);
                                                int frequencyvalue = 0;
                                                int value = 0;
                                                if (valueObj != null) {
                                                    if (!TextUtils.isEmpty(valueObj.getValue())) {
                                                        value = Integer.parseInt(valueObj.getValue());
                                                    }
                                                    DynamicUITable totalexpenseObj = getObjectByTAG(TAG_NAME_TOTAL_EXPENSES_IN_DIRECT_BUSINESS_EXPENSE, viewParametersList);

                                                    String frequencytype = customSpinner.getSelectedItem().toString();
                                                    if (!TextUtils.isEmpty(frequencytype)) {
                                                        //Daily:25,weekly:4,monthly:1
                                                        if (frequencytype.equalsIgnoreCase(FREQUENCY_DAILY)) {
                                                            frequencyvalue = 25;
                                                        } else if (frequencytype.equalsIgnoreCase(FREQUENCY_WEEKLY)) {
                                                            frequencyvalue = 4;
                                                        } else if (frequencytype.equalsIgnoreCase(FREQUENCY_MONTHLY)) {
                                                            frequencyvalue = 1;
                                                        }
                                                    }
                                                    if (totalexpenseObj != null) {
                                                        if (!TextUtils.isEmpty(frequencytype) && valueObj != null && !TextUtils.isEmpty(valueObj.getValue())) {
                                                            int totalexpense = frequencyvalue * value;
                                                            totalexpenseObj.setValue(String.valueOf(totalexpense));
                                                            List<ParameterInfo> parameterInfoList = new ArrayList<>();
                                                            parameterInfoList.add(new ParameterInfo(TAG_NAME_TOTAL_EXPENSES_IN_DIRECT_BUSINESS_EXPENSE, SCREEN_ID, "" + totalexpense, true, true));
                                                            EnableOrDisableByFieldNameInDB(parameterInfoList, viewParametersList);
                                                        } else {
                                                            totalexpenseObj.setValue(null);
                                                            List<ParameterInfo> parameterInfoList = new ArrayList<>();
                                                            parameterInfoList.add(new ParameterInfo(TAG_NAME_TOTAL_EXPENSES_IN_DIRECT_BUSINESS_EXPENSE, SCREEN_ID, "", true, true));
                                                            EnableOrDisableByFieldNameInDB(parameterInfoList, viewParametersList);
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    } else if (SCREEN_NAME.equalsIgnoreCase(SCREEN_NAME_HOUSE_DEBTS) || SCREEN_NAME.equalsIgnoreCase(SCREEN_NAME_BUSINESS_DEBTS_DETAIL_MSME)) {
                                        if (viewParameters.getFieldTag().equalsIgnoreCase(TAG_NAME_CATEGORY_IN_HOUSELIABILITY_MSME)) {
                                            getRawDataFromOtherScreenAndUpdate(viewParametersList, SCREEN_NAME);
                                        }
                                    }

                                } else if (LOSBaseFragment.this instanceof ReferencesFragment) {
                                    // TODO:  References Fragment spinner select
                                    getReferenceDetails(viewParameters, viewParametersList, SCREEN_NAME_REFERENCE_CHECK);

                                } else if (LOSBaseFragment.this instanceof IncomeAssessmentSummaryMsmeFragment) {
                                    // TODO:  Income Assessment Summary Screen
                                    IncomeAssessmentSummaryCalculation(viewParameters, viewParametersList);

                                } else if (LOSBaseFragment.this instanceof ApplicantLoanProposalFragment) {
                                    // TODO:  Applicant Loan Proposal MSME
                                    if (viewParameters.getLoanType().equalsIgnoreCase(LOAN_NAME_MSME)
                                            && (viewParameters.getFieldTag().equalsIgnoreCase(TAG_NAME_LOAN_PRODUCT)
                                            || viewParameters.getFieldTag().equalsIgnoreCase(TAG_NAME_LOAN_SCHEME)
                                            || viewParameters.getFieldTag().equalsIgnoreCase(TAG_NAME_PREVIOUS_LOAN_TYPE))
                                    ) {
                                        applicantLoanProposalScreenChangesByDropdown(viewParameters, viewParametersList);
                                    }// TODO:  Applicant Loan Proposal AHL
                                    else if (viewParameters.getLoanType().equalsIgnoreCase(AppConstant.LOAN_NAME_AHL)
                                            && (viewParameters.getFieldTag().equalsIgnoreCase(TAG_NAME_LOAN_PRODUCT)
                                            || viewParameters.getFieldTag().equalsIgnoreCase(TAG_NAME_LOAN_SCHEME)
                                            || viewParameters.getFieldTag().equalsIgnoreCase(TAG_NAME_PURPOSE))
                                    ) {
                                        applicantLoanProposalScreenChangesByDropdown(viewParameters, viewParametersList);
                                    }
                                    // TODO:  Applicant Loan Proposal PHL
                                    else if (viewParameters.getLoanType().equalsIgnoreCase(AppConstant.LOAN_NAME_PHL)
                                            && (viewParameters.getFieldTag().equalsIgnoreCase(TAG_NAME_LOAN_PRODUCT)
                                            || viewParameters.getFieldTag().equalsIgnoreCase(TAG_NAME_LOAN_SCHEME)
                                            || viewParameters.getFieldTag().equalsIgnoreCase(TAG_NAME_PURPOSE))
                                    ) {
                                        applicantLoanProposalScreenChangesByDropdown(viewParameters, viewParametersList);
                                    }
                                    else if (viewParameters.getLoanType().equalsIgnoreCase(LOAN_NAME_JLG)
                                            && (viewParameters.getFieldTag().equalsIgnoreCase(TAG_NAME_REQUESTED_LOAN_AMOUNT))) {
                                        if (!TextUtils.isEmpty(viewParameters.getValue())) {

                                            int loanamount = Integer.parseInt(viewParameters.getValue());
                                            if (loanamount >= 30000) {
                                                List<ParameterInfo> parameterInfoList = new ArrayList<>();
                                                parameterInfoList.add(new ParameterInfo(TAG_NAME_LOAN_TENURE_IN_MONTHS, SCREEN_ID, SPINNER_ITEM_FIELD_NAME_LOAN_TENURE_24_MONTHS, true, false));
                                                EnableOrDisableByFieldNameInDB(parameterInfoList, viewParametersList);
                                            } else {
                                                List<ParameterInfo> parameterInfoList = new ArrayList<>();
                                                parameterInfoList.add(new ParameterInfo(TAG_NAME_LOAN_TENURE_IN_MONTHS, SCREEN_ID, "", true, true));
                                                EnableOrDisableByFieldNameInDB(parameterInfoList, viewParametersList);
                                            }

                                            //loan amount should be less than CB eligible amount
                                            DynamicUITable cbLoanAmountObj = getObjectByTAG(TAG_NAME_CB_ELIGIBLE_LOAN_AMOUNT, viewParametersList);
                                            if (cbLoanAmountObj != null) {
                                                int cbLoanAmount = Integer.parseInt(cbLoanAmountObj.getValue());
                                                if (loanamount > cbLoanAmount) {
                                                    viewParameters.setErrorMessage("Requested loan amount should be lessthab CB eligible amount");
                                                }

                                            }

                                        }

                                    }
                                } else if (LOSBaseFragment.this instanceof CashFlowSummaryMSMEFragment) {
                                    // TODO:  Cash Flow Summary MSME
                                    if (viewParameters.getLoanType().equalsIgnoreCase(LOAN_NAME_MSME)
                                            && (viewParameters.getFieldTag().equalsIgnoreCase(TAG_NAME_LOAN_PRODUCT_TYPE_IN_CASH_FLOW_SUMMARY_MSME)
                                            || viewParameters.getFieldTag().equalsIgnoreCase(TAG_NAME_SCHEME_IN_CASH_FLOW_SUMMARY_MSME)
                                            || viewParameters.getFieldTag().equalsIgnoreCase(TAG_NAME_TENURE_IN_CASH_FLOW_SUMMARY_MSME)
                                            || viewParameters.getFieldTag().equalsIgnoreCase(TAG_NAME_ROI_IN_CASH_FLOW_SUMMARY_MSME)
                                    )) {
                                        cashFlowScreenChangesByDropdown(viewParameters, viewParametersList);
                                    }
                                } else if (LOSBaseFragment.this instanceof LoanApprovalMSMEFragment) {
                                    // TODO:  Loan Approval MSME
                                    if (viewParameters.getLoanType().equalsIgnoreCase(LOAN_NAME_MSME)
                                            && (viewParameters.getFieldTag().equalsIgnoreCase(TAG_NAME_LOAN_PRODUCT_IN_LOAN_APPROVAL_MSME)
                                            || viewParameters.getFieldTag().equalsIgnoreCase(TAG_NAME_LOAN_SCHEME_IN_LOAN_APPROVAL_MSME)
                                            || viewParameters.getFieldTag().equalsIgnoreCase(TAG_NAME_TERM_IN_LOAN_APPROVAL_MSME)
                                            || viewParameters.getFieldTag().equalsIgnoreCase(TAG_NAME_RATE_OF_INTEREST_IN_LOAN_APPROVAL_MSME)
                                    )) {
                                        loanApprovalScreenChangesByDropdown(viewParameters, viewParametersList);
                                    }
                                } else if (LOSBaseFragment.this instanceof LeadFragment) {
                                    // TODO: LEAD Fragment spinner select
                                    setLeadScreenChangesBySpinner(viewParametersList, viewParameters, customSpinner);
                                } else if (LOSBaseFragment.this instanceof LeadSummaryFragment) {
                                    // TODO: LEAD Fragment spinner select
                                    setLeadScreenChangesBySpinner(viewParametersList, viewParameters, customSpinner);
                                } else if (LOSBaseFragment.this instanceof SocioEconomicDetailFragment) {
                                    if (customSpinner.getSelectedItemPosition() > 0) {
                                        DynamicUITable dynamicUITable = (DynamicUITable) customSpinner.getTag();
                                        if (dynamicUITable != null && !TextUtils.isEmpty(dynamicUITable.getFieldTag())) {
                                            if (dynamicUITable.getFieldTag().equalsIgnoreCase(TAG_NAME_NO_OF_ADULTS)) {
                                                String noOfAdults = customSpinner.getSelectedItem().toString();
                                                dynamicUITable.setValue(noOfAdults);
                                                setFamilyMemberValue(noOfAdults, TAG_NAME_NO_OF_ADULTS, viewParametersList);
                                            } else if (dynamicUITable.getFieldTag().equalsIgnoreCase(TAG_NAME_NO_OF_CHILDREN)) {
                                                String noOfAdults = customSpinner.getSelectedItem().toString();
                                                dynamicUITable.setValue(noOfAdults);
                                                setFamilyMemberValue(noOfAdults, TAG_NAME_NO_OF_CHILDREN, viewParametersList);
                                            } else if (dynamicUITable.getFieldTag().equalsIgnoreCase(TAG_NAME_PRIMARY_OCCUPATION_CATEGORY)) {
                                                String primaryOccupation = customSpinner.getSelectedItem().toString();
                                                dynamicUITable.setValue(primaryOccupation);
                                                if (dynamicUITable.getLoanType().equalsIgnoreCase(LOAN_NAME_INDIVIDUAL)) {
                                                    changeSpinnerList(TAG_NAME_OCCUPATION,
                                                            primaryOccupation, SCREEN_ID, viewParametersList);
                                                } else {
                                                    if (primaryOccupation.equalsIgnoreCase(SPINNER_ITEM_FIELD_NAME_HOUSEWIFE)
                                                            || primaryOccupation.equalsIgnoreCase(SPINNER_ITEM_FIELD_NAME_HOUSE_WIFE)
                                                            || primaryOccupation.equalsIgnoreCase(SPINNER_ITEM_FIELD_NAME_STUDENT)
                                                            || primaryOccupation.equalsIgnoreCase(SPINNER_ITEM_FIELD_NAME_NOT_WORKING)) {
                                                        List<ParameterInfo> parameterInfoList = new ArrayList<>();
                                                        //HIDE FIELDS
                                                        parameterInfoList.add(new ParameterInfo(TAG_NAME_INCOME_SOURCE, SCREEN_ID, "", false, true));
                                                        parameterInfoList.add(new ParameterInfo(TAG_NAME_MONTHLY_INCOME, SCREEN_ID, "", false, true));
                                                        parameterInfoList.add(new ParameterInfo(TAG_NAME_GROSS_ANNUAL_INCOME, SCREEN_ID, "", false, true));
                                                        parameterInfoList.add(new ParameterInfo(TAG_NAME_YEARS_OF_EXPERIENCE, SCREEN_ID, "", false, true));
                                                        EnableOrDisableByFieldNameInDB(parameterInfoList, viewParametersList);
                                                    } else {
                                                        List<ParameterInfo> parameterInfoList = new ArrayList<>();
                                                        parameterInfoList.add(new ParameterInfo(TAG_NAME_INCOME_SOURCE, SCREEN_ID, "", true, true));
                                                        parameterInfoList.add(new ParameterInfo(TAG_NAME_MONTHLY_INCOME, SCREEN_ID, "", true, true));
                                                        parameterInfoList.add(new ParameterInfo(TAG_NAME_GROSS_ANNUAL_INCOME, SCREEN_ID, "", true, true));
                                                        parameterInfoList.add(new ParameterInfo(TAG_NAME_YEARS_OF_EXPERIENCE, SCREEN_ID, "", true, true));
                                                        EnableOrDisableByFieldNameInDB(parameterInfoList, viewParametersList);
                                                    }
                                                }
                                            } else if (dynamicUITable.getFieldTag().equalsIgnoreCase(TAG_NAME_SB_ACCOUNT_BALANCE)
                                                    || dynamicUITable.getFieldTag().equalsIgnoreCase(TAG_NAME_TWO_WHEELER)
                                                    || dynamicUITable.getFieldTag().equalsIgnoreCase(TAG_NAME_ANIMALS)
                                                    || dynamicUITable.getFieldTag().equalsIgnoreCase(TAG_NAME_PENSION)
                                                    || dynamicUITable.getFieldTag().equalsIgnoreCase(TAG_NAME_ANY_OTHER_INVESTMENT)) {
                                                setSocioEconomicScreenChangesBySpinner(viewParametersList, viewParameters, customSpinner);
                                            }
                                        }
                                    }

                                } else if (LOSBaseFragment.this instanceof CoApplicantSocioEconomicDetail) {
                                    if (customSpinner.getSelectedItemPosition() > 0) {
                                        DynamicUITable dynamicUITable = (DynamicUITable) customSpinner.getTag();
                                        if (dynamicUITable != null && !TextUtils.isEmpty(dynamicUITable.getFieldTag())) {

                                            if (dynamicUITable.getFieldTag().equalsIgnoreCase(TAG_NAME_CO_APPLICANT_PRIMARY_OCCUPATION_CATEGORY)) {
                                                String primaryOccupation = customSpinner.getSelectedItem().toString();
                                                dynamicUITable.setValue(primaryOccupation);

                                                if (dynamicUITable.getLoanType().equalsIgnoreCase(LOAN_NAME_INDIVIDUAL)) {
                                                    changeSpinnerList(TAG_NAME_OCCUPATION,
                                                            primaryOccupation, SCREEN_ID, viewParametersList);
                                                } else {
                                                    if (primaryOccupation.equalsIgnoreCase(SPINNER_ITEM_FIELD_NAME_HOUSEWIFE)
                                                            || primaryOccupation.equalsIgnoreCase(SPINNER_ITEM_FIELD_NAME_HOUSE_WIFE)
                                                            || primaryOccupation.equalsIgnoreCase(SPINNER_ITEM_FIELD_NAME_STUDENT)
                                                            || primaryOccupation.equalsIgnoreCase(SPINNER_ITEM_FIELD_NAME_NOT_WORKING)) {
                                                        List<ParameterInfo> parameterInfoList = new ArrayList<>();
                                                        //HIDE FIELDS
                                                        parameterInfoList.add(new ParameterInfo(TAG_NAME_INCOME_SOURCE, SCREEN_ID, "", false, true));
                                                        parameterInfoList.add(new ParameterInfo(TAG_NAME_MONTHLY_INCOME, SCREEN_ID, "", false, true));
                                                        parameterInfoList.add(new ParameterInfo(TAG_NAME_GROSS_ANNUAL_INCOME, SCREEN_ID, "", false, true));
                                                        parameterInfoList.add(new ParameterInfo(TAG_NAME_YEARS_OF_EXPERIENCE, SCREEN_ID, "", false, true));
                                                        EnableOrDisableByFieldNameInDB(parameterInfoList, viewParametersList);
                                                    } else {
                                                        List<ParameterInfo> parameterInfoList = new ArrayList<>();
                                                        parameterInfoList.add(new ParameterInfo(TAG_NAME_INCOME_SOURCE, SCREEN_ID, "", true, true));
                                                        parameterInfoList.add(new ParameterInfo(TAG_NAME_MONTHLY_INCOME, SCREEN_ID, "", true, true));
                                                        parameterInfoList.add(new ParameterInfo(TAG_NAME_GROSS_ANNUAL_INCOME, SCREEN_ID, "", true, true));
                                                        parameterInfoList.add(new ParameterInfo(TAG_NAME_YEARS_OF_EXPERIENCE, SCREEN_ID, "", true, true));
                                                        EnableOrDisableByFieldNameInDB(parameterInfoList, viewParametersList);
                                                    }
                                                }
                                               /* if (primaryOccupation.equalsIgnoreCase(SPINNER_ITEM_FIELD_NAME_HOUSEWIFE)
                                                        || primaryOccupation.equalsIgnoreCase(SPINNER_ITEM_FIELD_NAME_STUDENT)
                                                        || primaryOccupation.equalsIgnoreCase(SPINNER_ITEM_FIELD_NAME_NOT_WORKING)) {
                                                    List<ParameterInfo> parameterInfoList = new ArrayList<>();
                                                    //HIDE FIELDS
                                                    parameterInfoList.add(new ParameterInfo(TAG_NAME_INCOME_SOURCE, SCREEN_ID, "", false, true));
                                                    parameterInfoList.add(new ParameterInfo(TAG_NAME_MONTHLY_INCOME, SCREEN_ID, "", false, true));
                                                    parameterInfoList.add(new ParameterInfo(TAG_NAME_GROSS_ANNUAL_INCOME, SCREEN_ID, "", false, true));
                                                    parameterInfoList.add(new ParameterInfo(TAG_NAME_YEARS_OF_EXPERIENCE, SCREEN_ID, "", false, true));
                                                    EnableOrDisableByFieldNameInDB(parameterInfoList, viewParametersList);
                                                }else {
                                                    List<ParameterInfo> parameterInfoList = new ArrayList<>();
                                                    parameterInfoList.add(new ParameterInfo(TAG_NAME_INCOME_SOURCE, SCREEN_ID, "", true, true));
                                                    parameterInfoList.add(new ParameterInfo(TAG_NAME_MONTHLY_INCOME, SCREEN_ID, "", true, true));
                                                    parameterInfoList.add(new ParameterInfo(TAG_NAME_GROSS_ANNUAL_INCOME, SCREEN_ID, "", true, true));
                                                    parameterInfoList.add(new ParameterInfo(TAG_NAME_YEARS_OF_EXPERIENCE, SCREEN_ID, "", true, true));
                                                    EnableOrDisableByFieldNameInDB(parameterInfoList, viewParametersList);
                                                }*/
//                                                changeSpinnerList(TAG_NAME_OCCUPATION,
//                                                        primaryOccupation, SCREEN_ID, viewParametersList);

                                            }


                                        }
                                    }
                                } else if (LOSBaseFragment.this instanceof CollectionFragment) {
                                    if (customSpinner.getSelectedItemPosition() > 0) {
                                        DynamicUITable dynamicUITable = (DynamicUITable) customSpinner.getTag();
                                        if (dynamicUITable != null && !TextUtils.isEmpty(dynamicUITable.getFieldTag())) {
                                            if (dynamicUITable.getFieldTag().equalsIgnoreCase(TAG_NAME_CALLING_REMARKS_IN_COLLECTION)) {
                                                String callingRemarks = customSpinner.getSelectedItem().toString();
                                                List<ParameterInfo> parameterInfoList = new ArrayList<>();

                                                if (callingRemarks.equalsIgnoreCase(SPINNER_ITEM_FIELD_NAME_PTP_LESS_THAN_OR_EQUAL_TO_7_DAYS)
                                                        || callingRemarks.equalsIgnoreCase(SPINNER_ITEM_FIELD_NAME_PTP_GREATER_THAN_7_DAYS)) {

                                                    parameterInfoList.add(new ParameterInfo(TAG_NAME_PTP_DATE_COLLECTION, SCREEN_ID, "", true, true));
                                                    parameterInfoList.add(new ParameterInfo(TAG_NAME_MODE_OF_PAYMENT_COLLECTION, SCREEN_ID, "", true, true));
                                                    EnableOrDisableByFieldNameInDB(parameterInfoList, viewParametersList);

                                                } else {
                                                    parameterInfoList.add(new ParameterInfo(TAG_NAME_PTP_DATE_COLLECTION, SCREEN_ID, "", false, true));
                                                    parameterInfoList.add(new ParameterInfo(TAG_NAME_MODE_OF_PAYMENT_COLLECTION, SCREEN_ID, "", false, true));
                                                    EnableOrDisableByFieldNameInDB(parameterInfoList, viewParametersList);

                                                }

                                            }
                                        }
                                    }

//
                                }else if (LOSBaseFragment.this instanceof OfficeAddressProofFragment) {
                                    if (customSpinner.getSelectedItemPosition() > 0) {
                                        DynamicUITable dynamicUITable = (DynamicUITable) customSpinner.getTag();
                                        if (dynamicUITable != null && !TextUtils.isEmpty(dynamicUITable.getFieldTag()) && dynamicUITable.getLoanType().equalsIgnoreCase(LOAN_NAME_AHL)) {
                                            if (dynamicUITable.getFieldTag().equalsIgnoreCase(TAG_NAME_ADDRESS_PROOF_IN_OFFICE_ADDRESS_PROOF)) {
                                                if (!TextUtils.isEmpty(dynamicUITable.getValue())) {
                                                    List<ParameterInfo> parameterInfoList = new ArrayList<>();

                                                    if (dynamicUITable.getValue().contains(SPINNER_ITEM_FIELD_NAME_OTHERS)) {

                                                        parameterInfoList.add(new ParameterInfo(TAG_NAME_OTHERS, SCREEN_ID, "", true, true));

                                                    } else {
                                                        parameterInfoList.add(new ParameterInfo(TAG_NAME_OTHERS, SCREEN_ID, "", false, true));

                                                    }
                                                    EnableOrDisableByFieldNameInDB(parameterInfoList, viewParametersList);

                                                }

                                            }
                                        }
                                    }
                                }
                                else if (LOSBaseFragment.this instanceof OfficeAddressProofFragment) {
                                    if (customSpinner.getSelectedItemPosition() > 0) {
                                        DynamicUITable dynamicUITable = (DynamicUITable) customSpinner.getTag();
                                        if (dynamicUITable != null && !TextUtils.isEmpty(dynamicUITable.getFieldTag()) && dynamicUITable.getLoanType().equalsIgnoreCase(LOAN_NAME_PHL)) {
                                            if (dynamicUITable.getFieldTag().equalsIgnoreCase(TAG_NAME_ADDRESS_PROOF_IN_OFFICE_ADDRESS_PROOF)) {
                                                if (!TextUtils.isEmpty(dynamicUITable.getValue())) {
                                                    List<ParameterInfo> parameterInfoList = new ArrayList<>();

                                                    if (dynamicUITable.getValue().contains(SPINNER_ITEM_FIELD_NAME_OTHERS)) {

                                                        parameterInfoList.add(new ParameterInfo(TAG_NAME_OTHERS, SCREEN_ID, "", true, true));

                                                    } else {
                                                        parameterInfoList.add(new ParameterInfo(TAG_NAME_OTHERS, SCREEN_ID, "", false, true));

                                                    }
                                                    EnableOrDisableByFieldNameInDB(parameterInfoList, viewParametersList);

                                                }

                                            }
                                        }
                                    }
                                }
                                else if (LOSBaseFragment.this instanceof BusinessEntityFragment) {
                                    if (customSpinner.getSelectedItemPosition() > 0) {
                                        DynamicUITable dynamicUITable = (DynamicUITable) customSpinner.getTag();
                                        if (dynamicUITable != null && !TextUtils.isEmpty(dynamicUITable.getFieldTag())) {
                                            if (dynamicUITable.getFieldTag().equalsIgnoreCase(TAG_NAME_INDUSTRY_CLASS)) {
//                                                setBusinessProfileBySpinner(viewParametersList, viewParameters, customSpinner);
                                                String businessType = customSpinner.getSelectedItem().toString();
                                                dynamicUITable.setValue(businessType);
                                                if (businessType.equalsIgnoreCase(SPINNER_ITEM_FIELD_NAME_AGRICULTURE)
                                                        || businessType.equalsIgnoreCase(SPINNER_ITEM_FIELD_NAME_LIVESTOCK)) {
                                                    List<ParameterInfo> parameterInfoList = new ArrayList<>();
                                                    parameterInfoList.add(new ParameterInfo(TAG_NAME_TYPE_OF_BUSINESS, SCREEN_ID, "", false, true));
                                                    EnableOrDisableByFieldNameInDB(parameterInfoList, viewParametersList);
                                                } else {
                                                    changeSpinnerList(TAG_NAME_TYPE_OF_BUSINESS, businessType, SCREEN_ID, viewParametersList);
                                                }
                                            }
                                        }
                                    }
                                }

                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });

                    llSpinner.addView(customSpinner);
                    llParent.addView(llSpinner);

                    if (viewParameters.isVisibility()) {
                        dynamicViews.add(customSpinner);
                        addViewToParentLayout(llParent);
                    }

                }

                // TODO: BUTTON
                else if ((!TextUtils.isEmpty(viewParameters.getFieldType()) && viewParameters.getFieldType().
                        equalsIgnoreCase(FIELD_TYPE_BUTTON)) && viewParameters.isVisibility()) { // TODO: BUTTON
                    addButton(viewParametersList, viewParameters, viewParameters.getFieldName(), FIELD_TYPE_BUTTON, new ButtonClick() {
                        @Override
                        public void onButtonClickSuccess() {
                            if (viewParameters.getFieldTag().equalsIgnoreCase(TAG_NAME_EKYC_BUTTON)) {
                                /*appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.SUCCESS,
                                        "EKYC SUCCESS ! ", new ConfirmationDialog.ActionCallback() {
                                            @Override
                                            public void onAction() {
//                                                setEKYCData(viewParametersList);
//                                                updateDynamicUITable(viewParametersList, SCREEN_ID);
                                                checkScanner();
                                            }
                                        });*/

                                appHelper.getDialogHelper().getConfirmationDialog().showTwoButtons(
                                        "Do you like to perform  EKYC ? ", " Yes,EKYC ", " No,Manual Entry ", new ConfirmationDialog.PrintActionCallback() {
                                            @Override
                                            public void onAction() {
                                                // TODO: No
                                                DynamicUITable dynamicUITable = getObjectByTAG(TAG_NAME_EKYC_BUTTON, viewParametersList);
                                                dynamicUITable.setVisibility(false);
                                                if(LOAN_TYPE.equalsIgnoreCase(LOAN_NAME_JLG)&& SCREEN_NAME.equalsIgnoreCase(SCREEN_NAME_APPLICANT_KYC)){
                                                    DynamicUITable dynamicUITableQRCode = getObjectByTAG(TAG_NAME_QR_READING_BUTTON, viewParametersList);
                                                    dynamicUITableQRCode.setVisibility(true);
                                                    updateDynamicUITable(viewParametersList, SCREEN_ID);
                                                }else {
                                                    EnableOrDisableByLooping(viewParametersList, TAG_NAME_QR_READING_BUTTON, true);
                                                    setQRData(viewParametersList, null);
                                                }

                                            }

                                            @Override
                                            public void onPrint() {
                                                // TODO: Yes

                                                // TODO: To check Mantra app installed or not
                                                boolean isAppInstalled = appHelper.appInstalledOrNot(MANTRA_RD_SERVICE_PACKAGE);

                                                if (isAppInstalled) {

                                                    updateDynamicUITable(viewParametersList, SCREEN_ID);

                                                    BaseActivity baseActivity = (BaseActivity) getActivity();

                                                    ConfigurationDto configurationDto = new ConfigurationDto();
                                                    configurationDto.setAquirerID(AquirerID);
                                                    configurationDto.setEnvType(envType + "");
                                                    RequestType reqType = RequestType.EB;
                                                    configurationDto.setEnv(env); //todo added ekyc env UAT or PROD

                                                    baseActivity.setConfigurationDto(configurationDto);

                                                    PauaAuth pauaAuth = new PauaAuth();

                                                    TransactionDto transactionDto = new TransactionDto();
                                                    transactionDto.setAuthTransType(reqType);
                                                    transactionDto.setAutoFill(false);

                                                    String aadhaarNo = "";
                                                    DynamicUITable dynamicUITable = getObjectByTAG(TAG_NAME_KYC_TYPE_AADHAAR, viewParametersList);
                                                    if (dynamicUITable != null && !TextUtils.isEmpty(dynamicUITable.getValue())) {
                                                        aadhaarNo = dynamicUITable.getValue();
                                                    }
                                                    transactionDto.setAadharNo(aadhaarNo);

                                                    baseActivity.setTransactionDto(transactionDto);
                                                    checkScanner(); // TODO: Bio metric scanner

                                                } else {
                                                    // Do whatever we want to do if application not installed
                                                    // For example, Redirect to play store
                                                    appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT,
                                                            "Please install Mantra RD Service");

                                                    // Toast.makeText(getContext(), "Please install Mantra RD Service", Toast.LENGTH_SHORT).show();
                                                }

                                            }
                                        });

                            } else if (viewParameters.getFieldTag().equalsIgnoreCase(TAG_NAME_QR_READING_BUTTON)
                                    || viewParameters.getFieldTag().equalsIgnoreCase(TAG_NAME_NOMINEE_QR_SCAN)) {
                                scanQRCode(); // TODO: QR SCANNING
                            } else if (viewParameters.getFieldTag().equalsIgnoreCase(TAG_NAME_PERFORM_CB)) {
                                performCB(viewParameters, viewParametersList); // TODO: PERFORM CB
                            }else if (viewParameters.getFieldTag().equalsIgnoreCase(TAG_NAME_CALCULATE_BUTTON)) {
                                if(viewParameters.getScreenName().equalsIgnoreCase(SCREEN_NAME_SALES_TOOL)){
                                    String submittedValues = getSubmittedValuesFromUI(viewParameters, viewParametersList);
                                    final RawDataTable rawDataTable = new RawDataTable(submittedValues, SCREEN_ID, SCREEN_NAME,
                                            "", viewParameters.getClientID(), viewParameters.getLoanType(), USER_ID, MODULE_TYPE
                                            , viewParameters.getCoRelationID());
                                    salesToolApiCall(rawDataTable, viewParametersList, submittedValues,
                                            false, viewParameters, "", ""); // TODO: To save in local DB
                                }
                            }else if (viewParameters.getFieldTag().equalsIgnoreCase(TAG_NAME_SEND_OTP_BUTTON)) {
                                appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.SUCCESS,
                                        "OTP tvMobNo delivered to the customer mobile no",
                                        new ConfirmationDialog.ActionCallback() {
                                            @Override
                                            public void onAction() {
                                                viewParameters.setEditable(false);
                                                List<ParameterInfo> parameterInfoList = new ArrayList<>();
                                                parameterInfoList.add(new ParameterInfo(TAG_NAME_ENTER_OTP, SCREEN_ID, "", true, true));
                                                parameterInfoList.add(new ParameterInfo(TAG_NAME_RESEND_OTP_BUTTON, SCREEN_ID, "", true, true));
                                                parameterInfoList.add(new ParameterInfo(TAG_NAME_SEND_OTP_BUTTON, SCREEN_ID, "", false, true));
                                                EnableOrDisableByFieldNameInDB(parameterInfoList, viewParametersList);
                                            }
                                        });
                            } else if (viewParameters.getFieldTag().equalsIgnoreCase(FIELD_TYPE_ORAL_VERIFICATION_BUTTON)) {
                                // TODO: EMPLOYEE VERIFICATION
                                List<ParameterInfo> parameterInfoList = new ArrayList<>();
                                DynamicUITable dynamicUITableev = getObjectByTAG(TAG_NAME_EMPLOYER_VERIFICATION, viewParametersList);
                                if (dynamicUITableev != null) {
                                    if (!TextUtils.isEmpty(dynamicUITableev.getValue())) {
                                        parameterInfoList.add(new ParameterInfo(TAG_NAME_EMPLOYER_VERIFICATION, SCREEN_ID, dynamicUITableev.getValue(), true, true));
                                    } else {
                                        parameterInfoList.add(new ParameterInfo(TAG_NAME_EMPLOYER_VERIFICATION, SCREEN_ID, "", true, true));
                                    }
                                }
                                DynamicUITable dynamicUITablesince = getObjectByTAG(TAG_NAME_SINCE_WHEN_IS_THE_MEMBER_WORKING_WITH_YOU, viewParametersList);
                                if (dynamicUITablesince != null) {
                                    if (!TextUtils.isEmpty(dynamicUITablesince.getValue())) {
                                        parameterInfoList.add(new ParameterInfo(TAG_NAME_SINCE_WHEN_IS_THE_MEMBER_WORKING_WITH_YOU, SCREEN_ID, dynamicUITablesince.getValue(), true, true));
                                    } else {
                                        parameterInfoList.add(new ParameterInfo(TAG_NAME_SINCE_WHEN_IS_THE_MEMBER_WORKING_WITH_YOU, SCREEN_ID, "", true, true));
                                    }
                                }

                                DynamicUITable dynamicUITableatwhat = getObjectByTAG(TAG_NAME_AT_WHAT_POSITION_IS_HE_SHE_WORKING, viewParametersList);
                                if (dynamicUITableatwhat != null) {
                                    if (!TextUtils.isEmpty(dynamicUITableatwhat.getValue())) {
                                        parameterInfoList.add(new ParameterInfo(TAG_NAME_AT_WHAT_POSITION_IS_HE_SHE_WORKING, SCREEN_ID, dynamicUITableatwhat.getValue(), true, true));
                                    } else {
                                        parameterInfoList.add(new ParameterInfo(TAG_NAME_AT_WHAT_POSITION_IS_HE_SHE_WORKING, SCREEN_ID, "", true, true));
                                    }
                                }
                                DynamicUITable dynamicUITabletoj = getObjectByTAG(TAG_NAME_TYPE_OF_JOB, viewParametersList);
                                if (dynamicUITabletoj != null) {
                                    if (!TextUtils.isEmpty(dynamicUITabletoj.getValue())) {
                                        parameterInfoList.add(new ParameterInfo(TAG_NAME_TYPE_OF_JOB, SCREEN_ID, dynamicUITabletoj.getValue(), true, true));
                                    } else {
                                        parameterInfoList.add(new ParameterInfo(TAG_NAME_TYPE_OF_JOB, SCREEN_ID, "", true, true));
                                    }
                                }
                                DynamicUITable dynamicUITablemodesalary = getObjectByTAG(TAG_NAME_MODE_OF_SALARY_PAYMENT, viewParametersList);
                                if (dynamicUITablemodesalary != null) {
                                    if (!TextUtils.isEmpty(dynamicUITablemodesalary.getValue())) {
                                        parameterInfoList.add(new ParameterInfo(TAG_NAME_MODE_OF_SALARY_PAYMENT, SCREEN_ID, dynamicUITablemodesalary.getValue(), true, true));
                                    } else {
                                        parameterInfoList.add(new ParameterInfo(TAG_NAME_MODE_OF_SALARY_PAYMENT, SCREEN_ID, "", true, true));
                                    }
                                }
                                DynamicUITable dynamicUITableeother = getObjectByTAG(TAG_NAME_OTHERS, viewParametersList);
                                if (dynamicUITableeother != null) {
                                    if (!TextUtils.isEmpty(dynamicUITableeother.getValue())) {
                                        parameterInfoList.add(new ParameterInfo(TAG_NAME_OTHERS, SCREEN_ID, dynamicUITableeother.getValue(), true, true));
                                    } else {
                                        parameterInfoList.add(new ParameterInfo(TAG_NAME_OTHERS, SCREEN_ID, "", true, true));
                                    }
                                }
                                DynamicUITable dynamicUITablemonthsalary = getObjectByTAG(TAG_NAME_MONTHLY_SALARY_PAID_BY_EMPLOYER, viewParametersList);
                                if (dynamicUITablemonthsalary != null) {
                                    if (!TextUtils.isEmpty(dynamicUITablemonthsalary.getValue())) {
                                        parameterInfoList.add(new ParameterInfo(TAG_NAME_MONTHLY_SALARY_PAID_BY_EMPLOYER, SCREEN_ID, dynamicUITablemonthsalary.getValue(), true, true));
                                    } else {
                                        parameterInfoList.add(new ParameterInfo(TAG_NAME_MONTHLY_SALARY_PAID_BY_EMPLOYER, SCREEN_ID, "", true, true));
                                    }
                                }
                                DynamicUITable dynamicUITablelike = getObjectByTAG(TAG_NAME_LIKELY_TO_CONTINUE_HIS_HER_EMPLOYMENT, viewParametersList);
                                if (dynamicUITablelike != null) {
                                    if (!TextUtils.isEmpty(dynamicUITablelike.getValue())) {
                                        parameterInfoList.add(new ParameterInfo(TAG_NAME_LIKELY_TO_CONTINUE_HIS_HER_EMPLOYMENT, SCREEN_ID, dynamicUITablelike.getValue(), true, true));
                                    } else {
                                        parameterInfoList.add(new ParameterInfo(TAG_NAME_LIKELY_TO_CONTINUE_HIS_HER_EMPLOYMENT, SCREEN_ID, "", true, true));
                                    }
                                }
                                DynamicUITable dynamicUITableanyadv = getObjectByTAG(TAG_NAME_ANY_ADVERSE_BEHAVIOR_CONDUCT, viewParametersList);
                                if (dynamicUITableanyadv != null) {
                                    if (!TextUtils.isEmpty(dynamicUITableanyadv.getValue())) {
                                        parameterInfoList.add(new ParameterInfo(TAG_NAME_ANY_ADVERSE_BEHAVIOR_CONDUCT, SCREEN_ID, dynamicUITableanyadv.getValue(), true, true));
                                    } else {
                                        parameterInfoList.add(new ParameterInfo(TAG_NAME_ANY_ADVERSE_BEHAVIOR_CONDUCT, SCREEN_ID, "", true, true));
                                    }
                                }
                                OralVerificationEnableOrDisable(parameterInfoList, viewParametersList);// TODO: Oral verification enable or disable
                            }
                            else if (viewParameters.getFieldTag().contains(TAG_NAME_SAVE_BUTTON)) { // TODO: note contains comparison

                                String submittedValues = getSubmittedValuesFromUI(viewParameters, viewParametersList);

                                if (SCREEN_NAME.equalsIgnoreCase(SCREEN_NAME_APPLICANT_KYC) || SCREEN_NAME.equalsIgnoreCase(SCREEN_NAME_CO_APPLICANT_KYC)) {

                                    String selectedItem = getValuesBasedOnTAGinLoop(TAG_NAME_KYC_TYPE, viewParametersList);
                                    final RawDataTable rawDataTable = new RawDataTable(submittedValues, SCREEN_ID, SCREEN_NAME,
                                            selectedItem, viewParameters.getClientID(), viewParameters.getLoanType(), USER_ID, MODULE_TYPE
                                            , viewParameters.getCoRelationID());
                                    saveSubmittedData(rawDataTable, viewParametersList, submittedValues,
                                            true, viewParameters, selectedItem, TAG_NAME_ADD_ANOTHER_KYC_PLUS_BUTTON); // TODO: To save in local DB
                                } else if (SCREEN_NAME.equalsIgnoreCase(SCREEN_NAME_REFERENCE_CHECK)) {

                                    String referenceName = getValuesBasedOnTAGinLoop(TAG_NAME_FULL_NAME, viewParametersList);
                                    if (!TextUtils.isEmpty(referenceName)) {
                                        final RawDataTable rawDataTable = new RawDataTable(submittedValues, SCREEN_ID,
                                                SCREEN_NAME, referenceName, CLIENT_ID, viewParameters.getLoanType(), USER_ID, MODULE_TYPE
                                                , viewParameters.getCoRelationID());
                                        saveSubmittedData(rawDataTable, viewParametersList, submittedValues, true,
                                                viewParameters, referenceName, TAG_NAME_ADD_ANOTHER_REFERENCE); // TODO: To save in local DB

                                    }
                                } else if (SCREEN_NAME.equalsIgnoreCase(SCREEN_NAME_REFERENCES)) {
                                    String referenceName = getValuesBasedOnTAGinLoop(TAG_NAME_FULL_NAME, viewParametersList);
                                    final RawDataTable rawDataTable = new RawDataTable(submittedValues, SCREEN_ID, SCREEN_NAME,
                                            referenceName, viewParameters.getClientID(), viewParameters.getLoanType(), USER_ID, MODULE_TYPE
                                            , viewParameters.getCoRelationID());
                                    saveSubmittedData(rawDataTable, viewParametersList, submittedValues,
                                            true, viewParameters, referenceName, TAG_NAME_FULL_NAME); // TODO: To save in local DB
                                } else if (! TextUtils.isEmpty(LOAN_TYPE) && LOAN_TYPE.equalsIgnoreCase(LOAN_NAME_MSME)
                                        && SCREEN_NAME.equalsIgnoreCase(SCREEN_NAME_BANK_DETAILS)) {
                                    String bankName = getValuesBasedOnTAGinLoop(TAG_NAME_BANK_NAME, viewParametersList);
                                    final RawDataTable rawDataTable = new RawDataTable(submittedValues, SCREEN_ID, SCREEN_NAME,
                                            bankName, viewParameters.getClientID(), viewParameters.getLoanType(), USER_ID, MODULE_TYPE
                                            , viewParameters.getCoRelationID());
                                    saveSubmittedData(rawDataTable, viewParametersList, submittedValues,
                                            true, viewParameters, bankName, TAG_NAME_BANK_NAME); // TODO: To save in local DB
                                } else if (SCREEN_NAME.equalsIgnoreCase(SCREEN_NAME_SALES_TOOL)){
                                    final RawDataTable rawDataTable = new RawDataTable(submittedValues, SCREEN_ID, SCREEN_NAME,
                                            "", viewParameters.getClientID(), viewParameters.getLoanType(), USER_ID, MODULE_TYPE
                                            , viewParameters.getCoRelationID());
                                    saveSubmittedData(rawDataTable, viewParametersList, submittedValues,
                                            false, viewParameters, "", ""); // TODO: To save in local DB

                                } else {
                                    final RawDataTable rawDataTable = new RawDataTable(submittedValues, SCREEN_ID, SCREEN_NAME,
                                            "", viewParameters.getClientID(), viewParameters.getLoanType(), USER_ID, MODULE_TYPE
                                            , viewParameters.getCoRelationID());
                                    saveSubmittedData(rawDataTable, viewParametersList, submittedValues,
                                            false, viewParameters, "", ""); // TODO: To save in local DB
                                }
                            }
                            else if (LOSBaseFragment.this instanceof OTPAuthenticationFragment) {

                                if (viewParameters.getFieldTag().equalsIgnoreCase(TAG_NAME_RESEND_OTP_BUTTON)) {
                                    appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.SUCCESS,
                                            "OTP tvMobNo delivered to customer mobile number", new ConfirmationDialog.ActionCallback() {
                                                @Override
                                                public void onAction() {

                                                }
                                            });
                                } else if (viewParameters.getFieldTag().equalsIgnoreCase(TAG_NAME_CBCHECK_BUTTON)) {
                                    appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.SUCCESS,
                                            "CB Eligible", new ConfirmationDialog.ActionCallback() {
                                                @Override
                                                public void onAction() {
                                                    FragmentToActivityInterface fragmentToActivityInterface = (FragmentToActivityInterface) getActivity();
                                                    fragmentToActivityInterface.valueFromFragment(SCREEN_ID, SCREEN_NAME, "", null);
                                                }
                                            });
                                }
                            } else {
                                getSubmittedValuesFromUI(viewParameters, viewParametersList);
                            }
                        }
                    });

                }

                // TODO: PLUS BUTTON
                else if ((!TextUtils.isEmpty(viewParameters.getFieldType()) && viewParameters.getFieldType().
                        equalsIgnoreCase(FIELD_TYPE_PLUS_BUTTON)) && viewParameters.isVisibility()) { // TODO: PLUS BUTTON
                    addButton(viewParametersList, viewParameters, viewParameters.getFieldName(), FIELD_TYPE_PLUS_BUTTON, new ButtonClick() {
                        @Override
                        public void onButtonClickSuccess() {
//                             String submittedValues=getSubmittedValuesFromUI();
                            if (LOSBaseFragment.this instanceof ChildFragment) {
                                String submittedValues = getSubmittedValuesFromUI(viewParameters, viewParametersList);

                                final RawDataTable rawDataTable = new RawDataTable(submittedValues, SCREEN_ID, SCREEN_NAME,
                                        "", viewParameters.getClientID(), viewParameters.getLoanType(), USER_ID, MODULE_TYPE
                                        , viewParameters.getCoRelationID());

                                saveSubmittedData(rawDataTable, viewParametersList, submittedValues, false, viewParameters, "", ""); // TODO: To save in local DB
//
                            } else if (LOSBaseFragment.this instanceof ApplicantKYCFragment
                                    || LOSBaseFragment.this instanceof CoApplicantKYCFragment) {
                                String selectedItem = getValuesBasedOnTAGinLoop(TAG_NAME_KYC_TYPE, viewParametersList);
                                if (!TextUtils.isEmpty(selectedItem)) {

                                    appHelper.getDialogHelper().getConfirmationDialog().showTwoButtons(
                                            "Do you want to add another KYC ? ", new ConfirmationDialog.ActionCallback() {
                                                @Override
                                                public void onAction() {
                                                    String submittedValues = getSubmittedValuesFromUI(viewParameters, viewParametersList);
                                                    final RawDataTable rawDataTable = new RawDataTable(submittedValues, SCREEN_ID,
                                                            SCREEN_NAME, selectedItem, CLIENT_ID, viewParameters.getLoanType(), USER_ID, MODULE_TYPE
                                                            , viewParameters.getCoRelationID());
                                                    saveSubmittedData(rawDataTable, viewParametersList, submittedValues, true,
                                                            viewParameters, selectedItem, viewParameters.getFieldTag()); // TODO: To save in local DB

                                                    /*updatePlusButtonDataInDB(selectedItem, SCREEN_ID, TAG_NAME_KYC_TYPE, viewParameters
                                                            , viewParametersList);*/
                                                }
                                            });


                                }
                            } else if (LOSBaseFragment.this instanceof BusinessProofFragment) {
                                String selectedItem = getValuesBasedOnTAGinLoop(TAG_NAME_BUSINESS_DOCUMENT_PROOF, viewParametersList);
                                if (!TextUtils.isEmpty(selectedItem)) {

                                    appHelper.getDialogHelper().getConfirmationDialog().showTwoButtons(
                                            "Do you want to add another Business proof ? ", new ConfirmationDialog.ActionCallback() {
                                                @Override
                                                public void onAction() {
                                                    String submittedValues = getSubmittedValuesFromUI(viewParameters, viewParametersList);
                                                    final RawDataTable rawDataTable = new RawDataTable(submittedValues, SCREEN_ID,
                                                            SCREEN_NAME, selectedItem, CLIENT_ID, viewParameters.getLoanType(), USER_ID, MODULE_TYPE
                                                            , viewParameters.getCoRelationID());
                                                    saveSubmittedData(rawDataTable, viewParametersList, submittedValues, true,
                                                            viewParameters, selectedItem, viewParameters.getFieldTag()); // TODO: To save in local DB

                                                    /*updatePlusButtonDataInDB(selectedItem, SCREEN_ID, TAG_NAME_KYC_TYPE, viewParameters
                                                            , viewParametersList);*/
                                                }
                                            });


                                } else {
                                    appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT,
                                            "Select Business Proof");
                                }
                            } else if (LOSBaseFragment.this instanceof BankDetailsFragment
                                    || LOSBaseFragment.this instanceof CoApplicantBankDetailsFragment) {
                                String selectedItem = getValuesBasedOnTAGinLoop(TAG_NAME_BANK_NAME, viewParametersList);
                                if (!TextUtils.isEmpty(selectedItem)) {

                                    appHelper.getDialogHelper().getConfirmationDialog().showTwoButtons(
                                            "Do you want to add this bank detail ? ", new ConfirmationDialog.ActionCallback() {
                                                @Override
                                                public void onAction() {
                                                    String submittedValues = getSubmittedValuesFromUI(viewParameters, viewParametersList);
                                                    final RawDataTable rawDataTable = new RawDataTable(submittedValues, SCREEN_ID,
                                                            SCREEN_NAME, selectedItem, CLIENT_ID, viewParameters.getLoanType(), USER_ID, MODULE_TYPE
                                                            , viewParameters.getCoRelationID());
                                                    saveSubmittedData(rawDataTable, viewParametersList, submittedValues, true,
                                                            viewParameters, selectedItem, viewParameters.getFieldTag()); // TODO: To save in local DB

                                                    /*updatePlusButtonDataInDB(selectedItem, SCREEN_ID, TAG_NAME_KYC_TYPE, viewParameters
                                                            , viewParametersList);*/
                                                }
                                            });


                                } else {
                                    appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT,
                                            "Select Bank Name");
                                }
                            } else if (LOSBaseFragment.this instanceof ReferenceCheckFragment) {

                                String referenceName = getValuesBasedOnTAGinLoop(TAG_NAME_FULL_NAME, viewParametersList);
                                if (!TextUtils.isEmpty(referenceName)) {
                                    appHelper.getDialogHelper().getConfirmationDialog().showTwoButtons(
                                            "Do you want to add another reference ? ", new ConfirmationDialog.ActionCallback() {
                                                @Override
                                                public void onAction() {
                                                    String submittedValues = getSubmittedValuesFromUI(viewParameters, viewParametersList);
                                                    final RawDataTable rawDataTable = new RawDataTable(submittedValues, SCREEN_ID,
                                                            SCREEN_NAME, referenceName, CLIENT_ID, viewParameters.getLoanType(), USER_ID, MODULE_TYPE
                                                            , viewParameters.getCoRelationID());
                                                    saveSubmittedData(rawDataTable, viewParametersList, submittedValues, true,
                                                            viewParameters, referenceName, viewParameters.getFieldTag()); // TODO: To save in local DB

                                                }
                                            });


                                }
                            } else if (LOSBaseFragment.this instanceof GeneralIncomeFragment) {

                                String generalIncomeName = getValuesBasedOnTAGinLoop(TAG_NAME_NAME_IN_GENERAL_INCOME, viewParametersList);
                                if (!TextUtils.isEmpty(generalIncomeName)) {
                                    appHelper.getDialogHelper().getConfirmationDialog().showTwoButtons(
                                            "Do you want to this general income ? ", new ConfirmationDialog.ActionCallback() {
                                                @Override
                                                public void onAction() {
                                                    String submittedValues = getSubmittedValuesFromUI(viewParameters, viewParametersList);
                                                    final RawDataTable rawDataTable = new RawDataTable(submittedValues, SCREEN_ID,
                                                            SCREEN_NAME, generalIncomeName, CLIENT_ID, viewParameters.getLoanType(), USER_ID, MODULE_TYPE
                                                            , viewParameters.getCoRelationID());
                                                    saveSubmittedData(rawDataTable, viewParametersList, submittedValues, true,
                                                            viewParameters, generalIncomeName, viewParameters.getFieldTag()); // TODO: To save in local DB

                                                }
                                            });


                                }
                            } else if (LOSBaseFragment.this instanceof OtherIncomeSourceFragment) {

                                String generalIncomeName = getValuesBasedOnTAGinLoop(TAG_NAME_NAME_IN_OTHER_INCOME, viewParametersList);
                                if (!TextUtils.isEmpty(generalIncomeName)) {

                                    appHelper.getDialogHelper().getConfirmationDialog().showTwoButtons(
                                            "Do you want to this other income source ? ", new ConfirmationDialog.ActionCallback() {
                                                @Override
                                                public void onAction() {
                                                    String submittedValues = getSubmittedValuesFromUI(viewParameters, viewParametersList);
                                                    final RawDataTable rawDataTable = new RawDataTable(submittedValues, SCREEN_ID,
                                                            SCREEN_NAME, generalIncomeName, CLIENT_ID, viewParameters.getLoanType(), USER_ID, MODULE_TYPE
                                                            , viewParameters.getCoRelationID());
                                                    saveSubmittedData(rawDataTable, viewParametersList, submittedValues, true,
                                                            viewParameters, generalIncomeName, viewParameters.getFieldTag()); // TODO: To save in local DB

                                                }
                                            });
                                }
                            } else if (LOSBaseFragment.this instanceof MortgageMSMEFragment) {

                                DynamicUITable assetTypeObj = getObjectByTAG(TAG_NAME_ASSET_TYPE_IN_MORTGAGE_MSME, viewParametersList);

                                if (assetTypeObj != null && !TextUtils.isEmpty(assetTypeObj.getValue())) {
                                    // TODO: GET SCREEN NAME AND SCREEN NUMBER FOR MORTGAGE ASSETS
                                    String[] screenNameAndNumber = getScreenNameAndNumberForMortgageFixedAssets(assetTypeObj.getValue());
                                    String screenName = screenNameAndNumber[0]; // TODO: to get screen name
                                    String screenNumber = screenNameAndNumber[1]; // TODO: to get screen number
                                    if (!TextUtils.isEmpty(screenName) && !TextUtils.isEmpty(screenNumber)) {

                                        updateDynamicUITable(viewParametersList, SCREEN_ID);
                                        removeAllChildFragments(ll.getId() + "");
                                        initChild(LOAN_TYPE, CLIENT_ID, PROJECT_ID_LOS, PRODUCT_ID_MSME, screenNumber,
                                                screenName, viewParametersList, null
                                                , viewParametersList.get(0).getCoRelationID());
                                    }
                                } else {
                                    appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, "Please Select Asset Type");
                                }

                            } else if (LOSBaseFragment.this instanceof BankingHistoryFragment) {

                                updateDynamicUITable(viewParametersList, SCREEN_ID); // TODO : don't remove this , without updating value column queries wont work
                                removeAllChildFragments(ll.getId() + "");
                                initChild(LOAN_TYPE, CLIENT_ID, PROJECT_ID_LOS, PRODUCT_ID_MSME, SCREEN_N0_MONTHLY_TRANSACTION_DETAIL_MSME,
                                        SCREEN_NAME_MONTHLY_TRANSACTION_DETAIL_MSME, viewParametersList, null
                                        , viewParametersList.get(0).getCoRelationID());

                            } else if (LOSBaseFragment.this instanceof SalesBillsFragment) {

                                updateDynamicUITable(viewParametersList, SCREEN_ID); // TODO : don't remove this , without updating value column queries wont work
                                removeAllChildFragments(ll.getId() + "");
                                initChild(LOAN_TYPE, CLIENT_ID, PROJECT_ID_LOS, PRODUCT_ID_MSME, SCREEN_N0_SALES_BILLS_DETAIL_MSME,
                                        SCREEN_NAME_SALES_BILLS_DETAIL_MSME, viewParametersList, null
                                        , viewParametersList.get(0).getCoRelationID());

                            } else if (LOSBaseFragment.this instanceof PurchaseBillsFragment) {

                                updateDynamicUITable(viewParametersList, SCREEN_ID); // TODO : don't remove this , without updating value column queries wont work
                                removeAllChildFragments(ll.getId() + "");
                                initChild(LOAN_TYPE, CLIENT_ID, PROJECT_ID_LOS, PRODUCT_ID_MSME, SCREEN_N0_PURCHASE_BILLS_DETAIL_MSME,
                                        SCREEN_NAME_PURCHASE_BILLS_DETAIL_MSME, viewParametersList, null
                                        , viewParametersList.get(0).getCoRelationID());

                            } else if (LOSBaseFragment.this instanceof ITRFragment) {

                                updateDynamicUITable(viewParametersList, SCREEN_ID); // TODO : don't remove this , without updating value column queries wont work
                                removeAllChildFragments(ll.getId() + "");
                                initChild(LOAN_TYPE, CLIENT_ID, PROJECT_ID_LOS, PRODUCT_ID_MSME, SCREEN_N0_ITR_DETAIL_MSME,
                                        SCREEN_NAME_ITR_DETAIL_MSME, viewParametersList, null
                                        , viewParametersList.get(0).getCoRelationID());

                            } else if (LOSBaseFragment.this instanceof HouseAssetsMsmeFragment) {

                                DynamicUITable assetTypeObj = getObjectByTAG(TAG_NAME_ASSET_TYPE_IN_HOUSE_ASSET_MSME, viewParametersList);

                                if (assetTypeObj != null && !TextUtils.isEmpty(assetTypeObj.getValue())) {
                                    // TODO: GET SCREEN NAME AND SCREEN NUMBER FOR FIXED ASSETS
                                    String[] screenNameAndNumber = getScreenNameAndNumberForHouseAssets(assetTypeObj.getValue());
                                    String screenName = screenNameAndNumber[0]; // TODO: to get screen name
                                    String screenNumber = screenNameAndNumber[1]; // TODO: to get screen number
                                    if (!TextUtils.isEmpty(screenName) && !TextUtils.isEmpty(screenNumber)) {

                                        updateDynamicUITable(viewParametersList, SCREEN_ID); // TODO : don't remove this , without updating value column queries wont work
                                        removeAllChildFragments(ll.getId() + "");
                                        initChild(LOAN_TYPE, CLIENT_ID, PROJECT_ID_LOS, PRODUCT_ID_MSME, screenNumber,
                                                screenName, viewParametersList, null
                                                , viewParametersList.get(0).getCoRelationID());
                                    }
                                }
                            } else if (LOSBaseFragment.this instanceof HypothecationMsmeFragment) {
                                updateDynamicUITable(viewParametersList, SCREEN_ID); // TODO : don't remove this , without updating value column queries wont work
                                removeAllChildFragments(ll.getId() + "");
                                initChild(LOAN_TYPE, CLIENT_ID, PROJECT_ID_LOS, PRODUCT_ID_MSME, SCREEN_N0_DECISIONS_HYPOTHECATION_DETAIL_MSME,
                                        SCREEN_NAME_HYPOTHECATION_DETAIL_MSME, viewParametersList, null
                                        , viewParametersList.get(0).getCoRelationID());
                            } else if (LOSBaseFragment.this instanceof ProductEstimateDetailFragment) {

                                updateDynamicUITable(viewParametersList, SCREEN_ID); // TODO : don't remove this , without updating value column queries wont work
                                removeAllChildFragments(ll.getId() + "");
                                initChild(LOAN_TYPE, CLIENT_ID, PROJECT_ID_LOS, PRODUCT_ID_MSME, SCREEN_N0_PRODUCT_RAW_MATERIAL_MSME,
                                        SCREEN_NAME_PRODUCT_RAW_MATERIAL_MSME, viewParametersList, null
                                        , viewParametersList.get(0).getCoRelationID());

                            } else if (LOSBaseFragment.this instanceof ProductEstimateMSMEFragment) {

                                FragmentToActivityInterface fragmentToActivityInterface = (FragmentToActivityInterface) getActivity();
                                fragmentToActivityInterface.oneFragmentToAnotherFragment(SCREEN_NAME, SCREEN_NAME_PRODUCT_ESTIMATE_DETAIL_MSME
                                        , null, viewParametersList);

                            } else if (LOSBaseFragment.this instanceof ServiceEstimateDetailFragment) {

                                updateDynamicUITable(viewParametersList, SCREEN_ID); // TODO : don't remove this , without updating value column queries wont work
                                removeAllChildFragments(ll.getId() + "");
                                initChild(LOAN_TYPE, CLIENT_ID, PROJECT_ID_LOS, PRODUCT_ID_MSME, SCREEN_N0_SERVICE_RAW_MATERIAL_MSME,
                                        SCREEN_NAME_SERVICE_RAW_MATERIAL_MSME, viewParametersList, null
                                        , viewParametersList.get(0).getCoRelationID());

                            } else if (LOSBaseFragment.this instanceof ServiceEstimateFragment) {

                                FragmentToActivityInterface fragmentToActivityInterface = (FragmentToActivityInterface) getActivity();
                                fragmentToActivityInterface.oneFragmentToAnotherFragment(SCREEN_NAME, SCREEN_NAME_SERVICE_ESTIMATE_DETAIL_MSME
                                        , null, viewParametersList);

                            } else if (LOSBaseFragment.this instanceof AddressDetailsFragment ||
                                    LOSBaseFragment.this instanceof CoApplicantAddressDetailFragment) {

                                //Toast.makeText(getActivity(), "test", Toast.LENGTH_SHORT).show();
                                if (viewParameters.getFieldTag().equalsIgnoreCase(TAG_NAME_PERMANENT_ADD_ANOTHER_BUTTON)) {
                                    String selectedItem = getValuesBasedOnTAGinLoop(TAG_NAME_PERMANENT_KYC_TYPE, viewParametersList);
                                    if (!TextUtils.isEmpty(selectedItem)) {
                                        appHelper.getDialogHelper().getConfirmationDialog().showTwoButtons(
                                                "Do you want to add another permanent address ? ", new ConfirmationDialog.ActionCallback() {
                                                    @Override
                                                    public void onAction() {
                                                        String submittedValues = getSubmittedValuesFromUI(viewParameters, viewParametersList);
                                                        final RawDataTable rawDataTable = new RawDataTable(submittedValues,
                                                                SCREEN_ID, SCREEN_NAME, selectedItem, CLIENT_ID, viewParameters.getLoanType(),
                                                                USER_ID, MODULE_TYPE, viewParameters.getCoRelationID());
                                                        saveSubmittedData(rawDataTable, viewParametersList, submittedValues, true,
                                                                viewParameters, selectedItem, TAG_NAME_PERMANENT_KYC_TYPE); // TODO: To save in local DB
                                                        /*updatePlusButtonDataInDB(selectedItem, SCREEN_ID, TAG_NAME_PERMANENT_KYC_TYPE, viewParameters,
                                                                viewParametersList);*/
                                                    }
                                                });
                                    }
                                } else if (viewParameters.getFieldTag().equalsIgnoreCase(TAG_NAME_COMMUNICATION_ADD_ANOTHER_BUTTON)) {
                                    String selectedItem = getValuesBasedOnTAGinLoop(TAG_NAME_COMMUNICATION_KYC_TYPE, viewParametersList);
                                    if (!TextUtils.isEmpty(selectedItem)) {
                                        appHelper.getDialogHelper().getConfirmationDialog().showTwoButtons(
                                                "Do you want to add another communication address ? ", new ConfirmationDialog.ActionCallback() {
                                                    @Override
                                                    public void onAction() {
                                                        String submittedValues = getSubmittedValuesFromUI(viewParameters, viewParametersList);
                                                        final RawDataTable rawDataTable = new RawDataTable(submittedValues, SCREEN_ID,
                                                                SCREEN_NAME, selectedItem, CLIENT_ID, viewParameters.getLoanType(),
                                                                USER_ID, MODULE_TYPE, viewParameters.getCoRelationID());
                                                        saveSubmittedData(rawDataTable, viewParametersList, submittedValues, true,
                                                                viewParameters, selectedItem, TAG_NAME_COMMUNICATION_KYC_TYPE); // TODO: To save in local DB
                                                      /*  updatePlusButtonDataInDB(selectedItem, SCREEN_ID, TAG_NAME_COMMUNICATION_KYC_TYPE, viewParameters,
                                                                viewParametersList);*/

                                                    }
                                                });
                                    }
                                } else {
                                    // TODO: Need to check this condition
                                    String submittedValues = getSubmittedValuesFromUI(viewParameters, viewParametersList);
                                    final RawDataTable rawDataTable = new RawDataTable(submittedValues, SCREEN_ID, SCREEN_NAME,
                                            "", CLIENT_ID, viewParameters.getLoanType(),
                                            USER_ID, MODULE_TYPE, viewParameters.getCoRelationID());
                                    saveSubmittedData(rawDataTable, viewParametersList, submittedValues, false, viewParameters,
                                            "", ""); // TODO: To save in local DB
//
                                }
                            }

                        }
                    });
                }

                // TODO: ADD BUTTON
                else if ((!TextUtils.isEmpty(viewParameters.getFieldType()) && viewParameters.getFieldType().
                        equalsIgnoreCase(FIELD_TYPE_ADD_BUTTON)) && viewParameters.isVisibility()) { // TODO: ADD BUTTON
                    addButton(viewParametersList, viewParameters, viewParameters.getFieldName(), FIELD_TYPE_ADD_BUTTON, new ButtonClick() {
                        @Override
                        public void onButtonClickSuccess() {
                            String submittedValues = getSubmittedValuesFromUI(viewParameters, viewParametersList);
                            // TODO: ************************** NEED TO CHANGE FIRST **************************
                            final RawDataTable rawDataTable = new RawDataTable(submittedValues, SCREEN_ID, SCREEN_NAME,
                                    "", CLIENT_ID, viewParameters.getLoanType(), USER_ID, MODULE_TYPE, viewParameters.getCoRelationID());
                            saveSubmittedData(rawDataTable, viewParametersList, submittedValues, false, viewParameters, "", ""); // TODO: To save in local DB
//
                        }
                    });
                }
            }
            if (this instanceof AuditFragment || this instanceof LeadFragment
                    || this instanceof LeadSummaryFragment
                    || this instanceof SalesToolFragment
                    || this instanceof HouseLiabilitiesFragment
                    || this instanceof HouseIncomeFragment
                    || this instanceof HouseExpensesFragment
                    || this instanceof OperatingExpenseFragment
                    || this instanceof LoanProposalSummaryFragment
                    || this instanceof BusinessLiabilitiesFragment
                    || this instanceof DeclaredSalesWeeklyFragment
                    || this instanceof DeclaredSalesYearlyFragment
                    || this instanceof PurchaseAnalysisFragment
                    || this instanceof ProductEstimateFragment
                    || this instanceof SummaryFragment
                    || this instanceof ObservationsFragment
                    || this instanceof ReferencesFragment
                    || this instanceof ApplicantKYCFragment
                    || this instanceof ReferenceCheckFragment
                    || this instanceof BusinessAssetsFragment
                    || this instanceof BusinessProofFragment
                    || this instanceof BusinessAddressProofFragment
                    || this instanceof GSTFragment
                    || this instanceof IncomeAssessmentSummaryMsmeFragment
                    || this instanceof CoApplicantKYCFragment) {
                cardView(dynamicViews);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void getSpouseNameFromApplicantKYC(DynamicUITable dynamicUITable, List<DynamicUITable> viewParametersList) {
        try {
            String SCREEN_NO_APPLICANT_KYC = SCREEN_NO_APPLICANT_KYC_MSME;
            if (!TextUtils.isEmpty(dynamicUITable.getLoanType()) && dynamicUITable.getLoanType().equalsIgnoreCase(LOAN_NAME_INDIVIDUAL)) {
                SCREEN_NO_APPLICANT_KYC = SCREEN_NO_APPLICANT_KYC_IL;
            }
            // TODO: PHL spouse validation
            else if (!TextUtils.isEmpty(dynamicUITable.getLoanType()) && dynamicUITable.getLoanType().equalsIgnoreCase(LOAN_NAME_PHL)) {
                SCREEN_NO_APPLICANT_KYC = SCREEN_NO_APPLICANT_KYC_PHL;
            }
            // TODO: AHL spouse validation
            else if (!TextUtils.isEmpty(dynamicUITable.getLoanType()) && dynamicUITable.getLoanType().equalsIgnoreCase(LOAN_NAME_AHL)) {
                SCREEN_NO_APPLICANT_KYC = SCREEN_NO_APPLICANT_KYC_AHL;
            }
            viewModel.getSpouseName(dynamicUITable, SCREEN_NO_APPLICANT_KYC, viewParametersList);
            if (viewModel.getDynamicUITableLiveData() != null) {
                Observer getSpouseNameObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        List<DynamicUITable> dynamicUITableList = (List<DynamicUITable>) o;
                        viewModel.getDynamicUITableLiveData().removeObserver(this);
                        dynamicUI(dynamicUITableList);


                    }
                };
                viewModel.getDynamicUITableLiveData().observe(this, getSpouseNameObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    private void setSocioEconomicScreenChangesBySpinner(List<DynamicUITable> viewParametersList, DynamicUITable viewParameters, XMLCustomSpinner customSpinner) {

        if (viewParameters.getFieldTag().equalsIgnoreCase(TAG_NAME_SB_ACCOUNT_BALANCE)) {
            String sbAccount = customSpinner.getSelectedItem().toString();
            viewParameters.setValue(sbAccount);
            if (sbAccount.equalsIgnoreCase("NO")) {
                List<ParameterInfo> parameterInfoList = new ArrayList<>();
                parameterInfoList.add(new ParameterInfo(TAG_NAME_SB_ACCOUNT_BALANCE_AMOUNT, SCREEN_ID, "", false, true));
                EnableOrDisableByFieldNameInDB(parameterInfoList, viewParametersList);
            } else {
                List<ParameterInfo> parameterInfoList = new ArrayList<>();
                parameterInfoList.add(new ParameterInfo(TAG_NAME_SB_ACCOUNT_BALANCE_AMOUNT, SCREEN_ID, "", true, true));
                EnableOrDisableByFieldNameInDB(parameterInfoList, viewParametersList);
            }

        } else if (viewParameters.getFieldTag().equalsIgnoreCase(TAG_NAME_TWO_WHEELER)) {
            String twoWheeler = customSpinner.getSelectedItem().toString();
            viewParameters.setValue(twoWheeler);
            if (twoWheeler.equalsIgnoreCase("NO")) {
                List<ParameterInfo> parameterInfoList = new ArrayList<>();
                parameterInfoList.add(new ParameterInfo(TAG_NAME_TWO_WHEELER_ASSET_VALUE, SCREEN_ID, "", false, true));
                parameterInfoList.add(new ParameterInfo(TAG_NAME_NO_OF_YEARS_OF_PURCHASE, SCREEN_ID, "", false, true));
                EnableOrDisableByFieldNameInDB(parameterInfoList, viewParametersList);
            } else {
                List<ParameterInfo> parameterInfoList = new ArrayList<>();
                parameterInfoList.add(new ParameterInfo(TAG_NAME_TWO_WHEELER_ASSET_VALUE, SCREEN_ID, "", true, true));
                parameterInfoList.add(new ParameterInfo(TAG_NAME_NO_OF_YEARS_OF_PURCHASE, SCREEN_ID, "", true, true));
                EnableOrDisableByFieldNameInDB(parameterInfoList, viewParametersList);
            }


        } else if (viewParameters.getFieldTag().equalsIgnoreCase(TAG_NAME_ANIMALS)) {
            String animals = customSpinner.getSelectedItem().toString();
            viewParameters.setValue(animals);
            if (animals.equalsIgnoreCase("NO")) {
                List<ParameterInfo> parameterInfoList = new ArrayList<>();
                parameterInfoList.add(new ParameterInfo(TAG_NAME_NUMBER_OF_ANIMALS, SCREEN_ID, "", false, true));
                EnableOrDisableByFieldNameInDB(parameterInfoList, viewParametersList);
            } else {
                List<ParameterInfo> parameterInfoList = new ArrayList<>();
                parameterInfoList.add(new ParameterInfo(TAG_NAME_NUMBER_OF_ANIMALS, SCREEN_ID, "", true, true));
                EnableOrDisableByFieldNameInDB(parameterInfoList, viewParametersList);
            }

        } else if (viewParameters.getFieldTag().equalsIgnoreCase(TAG_NAME_PENSION)) {
            String pension = customSpinner.getSelectedItem().toString();
            viewParameters.setValue(pension);
            if (pension.equalsIgnoreCase("NO")) {
                List<ParameterInfo> parameterInfoList = new ArrayList<>();
                parameterInfoList.add(new ParameterInfo(TAG_NAME_PENSION_AMOUNT, SCREEN_ID, "", false, true));
                EnableOrDisableByFieldNameInDB(parameterInfoList, viewParametersList);
            } else {
                List<ParameterInfo> parameterInfoList = new ArrayList<>();
                parameterInfoList.add(new ParameterInfo(TAG_NAME_PENSION_AMOUNT, SCREEN_ID, "", true, true));
                EnableOrDisableByFieldNameInDB(parameterInfoList, viewParametersList);
            }

        } else if (viewParameters.getFieldTag().equalsIgnoreCase(TAG_NAME_ANY_OTHER_INVESTMENT)) {
            String other_investment = customSpinner.getSelectedItem().toString();
            viewParameters.setValue(other_investment);
            if (other_investment.equalsIgnoreCase("NO")) {
                List<ParameterInfo> parameterInfoList = new ArrayList<>();
                parameterInfoList.add(new ParameterInfo(TAG_NAME_INVESTMENT_VALUE, SCREEN_ID, "", false, true));
                EnableOrDisableByFieldNameInDB(parameterInfoList, viewParametersList);
            } else {
                List<ParameterInfo> parameterInfoList = new ArrayList<>();
                parameterInfoList.add(new ParameterInfo(TAG_NAME_INVESTMENT_VALUE, SCREEN_ID, "", true, true));
                EnableOrDisableByFieldNameInDB(parameterInfoList, viewParametersList);
            }
        }


    }

    private void setLeadScreenChangesByRadioButton(DynamicUITable dynamicUITable, String selectedRadioButton, List<DynamicUITable> dynamicUITableList) {
        try {
            if (dynamicUITable != null) {
                if (!TextUtils.isEmpty(dynamicUITable.getFieldTag()) && dynamicUITable.getFieldTag().equalsIgnoreCase(TAG_NAME_INTERESTED_IN_LOAN)) {
                    if (!TextUtils.isEmpty(selectedRadioButton) && selectedRadioButton.equalsIgnoreCase("No")) {
                        List<ParameterInfo> parameterInfoList = new ArrayList<>();
                        parameterInfoList.add(new ParameterInfo(TAG_NAME_INTERESTED_IN_LOAN, SCREEN_ID, selectedRadioButton, true, true));
                        parameterInfoList.add(new ParameterInfo(TAG_NAME_REASON_FOR_NO_INTEREST, SCREEN_ID, "", true, true));
                        parameterInfoList.add(new ParameterInfo(TAG_NAME_TYPE_OF_LOAN, SCREEN_ID, "", false, true));
                        parameterInfoList.add(new ParameterInfo(TAG_NAME_REQUESTED_LOAN_AMOUNT, SCREEN_ID, "", false, true));
                        //if (!TextUtils.isEmpty(dynamicUITable.getLoanType()) && dynamicUITable.getLoanType().equalsIgnoreCase(LOAN_NAME_AHL)) {
                            // TODO: AHL interested in loan validation
                            parameterInfoList.add(new ParameterInfo(TAG_NAME_LOAN_SUB_TYPE, SCREEN_ID, "", false, true));
                            parameterInfoList.add(new ParameterInfo(TAG_NAME_LOAN_TENURE, SCREEN_ID, "", false, true));
                       // }
                        EnableOrDisableByFieldNameInDB(parameterInfoList, dynamicUITableList);

                    } else {
                        List<ParameterInfo> parameterInfoList = new ArrayList<>();
                        parameterInfoList.add(new ParameterInfo(TAG_NAME_INTERESTED_IN_LOAN, SCREEN_ID, selectedRadioButton, true, true));
                        parameterInfoList.add(new ParameterInfo(TAG_NAME_REASON_FOR_NO_INTEREST, SCREEN_ID, "", false, true));
                        parameterInfoList.add(new ParameterInfo(TAG_NAME_TYPE_OF_LOAN, SCREEN_ID, "", true, true));
                        parameterInfoList.add(new ParameterInfo(TAG_NAME_REQUESTED_LOAN_AMOUNT, SCREEN_ID, "", true, true));
                       // if (!TextUtils.isEmpty(dynamicUITable.getLoanType()) && dynamicUITable.getLoanType().equalsIgnoreCase(LOAN_NAME_AHL)) {
                            // TODO: AHL interested in loan validation
                            parameterInfoList.add(new ParameterInfo(TAG_NAME_LOAN_SUB_TYPE, SCREEN_ID, "", true, true));
                            parameterInfoList.add(new ParameterInfo(TAG_NAME_LOAN_TENURE, SCREEN_ID, "", true, true));
                       // }
                        EnableOrDisableByFieldNameInDB(parameterInfoList, dynamicUITableList);

                    }
                } //////////
                if (!TextUtils.isEmpty(dynamicUITable.getFieldTag()) && dynamicUITable.getFieldTag().equalsIgnoreCase(TAG_NAME_INTERESTED_IN_LOAN)) {
                    if (!TextUtils.isEmpty(selectedRadioButton) && selectedRadioButton.equalsIgnoreCase("No")) {
                        List<ParameterInfo> parameterInfoList = new ArrayList<>();
                        parameterInfoList.add(new ParameterInfo(TAG_NAME_INTERESTED_IN_LOAN, SCREEN_ID, selectedRadioButton, true, true));
                        parameterInfoList.add(new ParameterInfo(TAG_NAME_REASON_FOR_NO_INTEREST, SCREEN_ID, "", true, true));
                        parameterInfoList.add(new ParameterInfo(TAG_NAME_TYPE_OF_LOAN, SCREEN_ID, "", false, true));
                        parameterInfoList.add(new ParameterInfo(TAG_NAME_REQUESTED_LOAN_AMOUNT, SCREEN_ID, "", false, true));
                       // if (!TextUtils.isEmpty(dynamicUITable.getLoanType()) && dynamicUITable.getLoanType().equalsIgnoreCase(LOAN_NAME_PHL)) {
                            // TODO: PHL interested in loan validation
                            parameterInfoList.add(new ParameterInfo(TAG_NAME_LOAN_SUB_TYPE, SCREEN_ID, "", false, true));
                            parameterInfoList.add(new ParameterInfo(TAG_NAME_LOAN_TENURE, SCREEN_ID, "", false, true));
                        //}
                        EnableOrDisableByFieldNameInDB(parameterInfoList, dynamicUITableList);

                    } else {
                        List<ParameterInfo> parameterInfoList = new ArrayList<>();
                        parameterInfoList.add(new ParameterInfo(TAG_NAME_INTERESTED_IN_LOAN, SCREEN_ID, selectedRadioButton, true, true));
                        parameterInfoList.add(new ParameterInfo(TAG_NAME_REASON_FOR_NO_INTEREST, SCREEN_ID, "", false, true));
                        parameterInfoList.add(new ParameterInfo(TAG_NAME_TYPE_OF_LOAN, SCREEN_ID, "", true, true));
                        parameterInfoList.add(new ParameterInfo(TAG_NAME_REQUESTED_LOAN_AMOUNT, SCREEN_ID, "", true, true));
                       // if (!TextUtils.isEmpty(dynamicUITable.getLoanType()) && dynamicUITable.getLoanType().equalsIgnoreCase(LOAN_NAME_PHL)) {
                            // TODO: PHL interested in loan validation
                            parameterInfoList.add(new ParameterInfo(TAG_NAME_LOAN_SUB_TYPE, SCREEN_ID, "", true, true));
                            parameterInfoList.add(new ParameterInfo(TAG_NAME_LOAN_TENURE, SCREEN_ID, "", true, true));
                       // }
                        EnableOrDisableByFieldNameInDB(parameterInfoList, dynamicUITableList);

                    }
                }
                else if (!TextUtils.isEmpty(dynamicUITable.getFieldTag()) && dynamicUITable.getFieldTag().equalsIgnoreCase(TAG_NAME_QUALIFY_FOR_LOAN)) {
                    if (!TextUtils.isEmpty(selectedRadioButton) && selectedRadioButton.equalsIgnoreCase("No")) {
                        List<ParameterInfo> parameterInfoList = new ArrayList<>();
                        parameterInfoList.add(new ParameterInfo(TAG_NAME_QUALIFY_FOR_LOAN, SCREEN_ID, selectedRadioButton, true, true));
                        parameterInfoList.add(new ParameterInfo(TAG_NAME_REASON_FOR_NOT_ELIGIBLE, SCREEN_ID, "", true, true));
                        EnableOrDisableByFieldNameInDB(parameterInfoList, dynamicUITableList);
                    } else {
                        List<ParameterInfo> parameterInfoList = new ArrayList<>();
                        parameterInfoList.add(new ParameterInfo(TAG_NAME_QUALIFY_FOR_LOAN, SCREEN_ID, selectedRadioButton, true, true));
                        parameterInfoList.add(new ParameterInfo(TAG_NAME_REASON_FOR_NOT_ELIGIBLE, SCREEN_ID, "", false, true));
                        EnableOrDisableByFieldNameInDB(parameterInfoList, dynamicUITableList);
                    }
                } else if (!TextUtils.isEmpty(dynamicUITable.getFieldTag()) && dynamicUITable.getFieldTag().equalsIgnoreCase(TAG_NAME_BUSINESS_SECTOR)) {
                    // TODO: pass tag name of which field needs to get change
                    dynamicUITable.setValue(selectedRadioButton);
                    changeSpinnerList(TAG_NAME_BUSINESS_SUB_SECTOR,
                            selectedRadioButton, SCREEN_ID, dynamicUITableList);
                }
                // TODO: AHl customer type validation
                else if (!TextUtils.isEmpty(dynamicUITable.getFieldTag()) && dynamicUITable.getFieldTag().equalsIgnoreCase(TAG_NAME_CUSTOMER_TYPE)) {
                    if (!TextUtils.isEmpty(selectedRadioButton) && selectedRadioButton.equalsIgnoreCase(RADIO_BUTTON_ITEM_SELF_EMPLOYED)) {
                        List<ParameterInfo> parameterInfoList = new ArrayList<>();
                        parameterInfoList.add(new ParameterInfo(TAG_NAME_BUSINESS_SECTOR, SCREEN_ID, "", true, true));
                        parameterInfoList.add(new ParameterInfo(TAG_NAME_BUSINESS_SUB_SECTOR, SCREEN_ID, "", true, true));
                        parameterInfoList.add(new ParameterInfo(TAG_NAME_ENTERPRISE_NAME, SCREEN_ID, "", true, true));
                        parameterInfoList.add(new ParameterInfo(TAG_NAME_COMPANY_NAME, SCREEN_ID, "", false, true));
                        EnableOrDisableByFieldNameInDB(parameterInfoList, dynamicUITableList);

                    } else {
                        List<ParameterInfo> parameterInfoList = new ArrayList<>();
                        parameterInfoList.add(new ParameterInfo(TAG_NAME_BUSINESS_SECTOR, SCREEN_ID, "", false, true));
                        parameterInfoList.add(new ParameterInfo(TAG_NAME_BUSINESS_SUB_SECTOR, SCREEN_ID, "", false, true));
                        parameterInfoList.add(new ParameterInfo(TAG_NAME_ENTERPRISE_NAME, SCREEN_ID, "", false, true));
                        parameterInfoList.add(new ParameterInfo(TAG_NAME_COMPANY_NAME, SCREEN_ID, "", true, true));
                        EnableOrDisableByFieldNameInDB(parameterInfoList, dynamicUITableList);

                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void setDeclaredSalesWeeklyChangesByRadioButton(DynamicUITable dynamicUITable, String selectedRadioButton, List<DynamicUITable> dynamicUITableList) {
        try {
            if (dynamicUITable != null) {
                if (!TextUtils.isEmpty(dynamicUITable.getFieldTag()) && dynamicUITable.getFieldTag().equalsIgnoreCase(TAG_NAME_DECLARED_SALES)) {
                    if (!TextUtils.isEmpty(selectedRadioButton) && selectedRadioButton.equalsIgnoreCase(DECLARED_SALES_DAILY)) {
                        // TODO: disable declared sales weekly  ( vice versa for daily )
                        EnableOrDisableByFeatureId(TAG_NAME_DECLARED_SALES_DAILY, TAG_NAME_DECLARED_SALES_WEEKLY, dynamicUITableList);
                    } else if (!TextUtils.isEmpty(selectedRadioButton) && selectedRadioButton.equalsIgnoreCase(DECLARED_SALES_WEEKLY)) {
                        // TODO: disable declared sales daily
                        EnableOrDisableByFeatureId(TAG_NAME_DECLARED_SALES_WEEKLY, TAG_NAME_DECLARED_SALES_DAILY, dynamicUITableList);
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void setReferencesScreenChangesByReferenceType(DynamicUITable dynamicUITable, String referenceType, List<DynamicUITable> dynamicUITableList) {
        try {
            if (dynamicUITable != null) {
                if (!TextUtils.isEmpty(dynamicUITable.getFieldTag()) && dynamicUITable.getFieldTag().equalsIgnoreCase(TAG_NAME_REFERENCE_TYPE)) {
                    if (!TextUtils.isEmpty(referenceType) && (referenceType.equalsIgnoreCase(REFERENCE_TYPE_BUYER)
                            || referenceType.equalsIgnoreCase(REFERENCE_TYPE_SELLER)
                    )
                    ) {
                        EnableOrDisableByFeatureId(TAG_NAME_BUSINESS_RELATIONSHIP_SINCE, TAG_NAME_ARE_YOU_RELATED_TO_APPLICANT, dynamicUITableList);

                    } else {
                        EnableOrDisableByFeatureId(TAG_NAME_ARE_YOU_RELATED_TO_APPLICANT, TAG_NAME_BUSINESS_RELATIONSHIP_SINCE, dynamicUITableList);
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void setLeadScreenChangesBySpinner(List<DynamicUITable> viewParametersList, DynamicUITable viewParameters, XMLCustomSpinner customSpinner) {
        try {
            if (!TextUtils.isEmpty(viewParameters.getFieldTag()) && viewParameters.getFieldTag().equalsIgnoreCase(TAG_NAME_KYC_TYPE)) {
                String selectedItem = customSpinner.getSelectedItem().toString();
                DataTypeInfo datatypeInfo = new DataTypeInfo(selectedItem, viewParameters);
                for (int i = 0; i < viewParametersList.size(); i++) {
                    DynamicUITable dynamicUITable = viewParametersList.get(i);
                    if (dynamicUITable != null && dynamicUITable.getFieldTag().equalsIgnoreCase(TAG_NAME_KYC_TYPE)) {
                        if (dynamicUITable.getFieldTag().equalsIgnoreCase(TAG_NAME_KYC_TYPE)) {
                            dynamicUITable.setSpinnerItemPosition(customSpinner.getSelectedItemPosition());
                            dynamicUITable.setValue(selectedItem);
                        }
                    } else if (dynamicUITable.getFieldName().equalsIgnoreCase(TAG_NAME_KYC_ID)) {
                        // TODO: Only here we need to check with field name
                        dynamicUITable.setLength(datatypeInfo.getLength());
                        dynamicUITable.setHint(datatypeInfo.getHint());
                        dynamicUITable.setDataType(datatypeInfo.getInputType());
                        dynamicUITable.setDataEntryType(datatypeInfo.getDataEntryType());
                        dynamicUITable.setFieldTag(datatypeInfo.getHintTag());
                        dynamicUITable.setValue(""); // TODO: clearing the value once changing the spinner item
                        if (!TextUtils.isEmpty(selectedItem) && selectedItem.equalsIgnoreCase(SPINNER_ITEM_FIELD_NAME_UTILITY_BILLS)) {
                            dynamicUITable.setVisibility(false);
                        } else {
                            dynamicUITable.setVisibility(true);
                        }
                    }

                }
                updateDynamicUITable(viewParametersList, SCREEN_ID);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void setApplicantScreenChangesBySpinner(List<DynamicUITable> viewParametersList, DynamicUITable viewParameters, XMLCustomSpinner customSpinner) {
        try {
            if (!TextUtils.isEmpty(viewParameters.getFieldTag()) && viewParameters.getFieldTag().equalsIgnoreCase(TAG_NAME_KYC_TYPE)) {
                viewParameters.setFieldType(FIELD_TYPE_LIST);
                viewParameters.setEditable(true);
                String selectedItem = customSpinner.getSelectedItem().toString();
                DataTypeInfo datatypeInfo = new DataTypeInfo(selectedItem, viewParameters);
                for (int i = 0; i < viewParametersList.size(); i++) {
                    DynamicUITable dynamicUITable = viewParametersList.get(i);
                    if (dynamicUITable != null && dynamicUITable.getFieldTag().equalsIgnoreCase(TAG_NAME_KYC_TYPE)) {
                        if (dynamicUITable.getFieldTag().equalsIgnoreCase(TAG_NAME_KYC_TYPE)) {
                            dynamicUITable.setSpinnerItemPosition(customSpinner.getSelectedItemPosition());
                            dynamicUITable.setFieldType(FIELD_TYPE_LIST);
                            dynamicUITable.setEditable(true);
                            dynamicUITable.setValue(selectedItem);
                        }
                    } else if (!TextUtils.isEmpty(dynamicUITable.getFieldName()) &&
                            dynamicUITable.getFieldName().equalsIgnoreCase(TAG_NAME_KYC_ID)) {
                        // TODO: Only here we need to check with field name
                        dynamicUITable.setLength(datatypeInfo.getLength());
                        dynamicUITable.setEditable(true);
                        dynamicUITable.setHint(datatypeInfo.getHint());
                        dynamicUITable.setDataType(datatypeInfo.getInputType());
                        dynamicUITable.setDataEntryType(datatypeInfo.getDataEntryType());
                        dynamicUITable.setFieldTag(datatypeInfo.getHintTag());

   //                     dynamicUITable.setValue("");
                        dynamicUITable.setErrorMessage("");// TODO: clearing the value once changing the spinner item
                        if (!TextUtils.isEmpty(selectedItem) &&
                                (selectedItem.equalsIgnoreCase(SPINNER_ITEM_FIELD_NAME_UTILITY_BILLS)
                                        || selectedItem.equalsIgnoreCase(SPINNER_ITEM_FIELD_NAME_BANK_PASSBOOK_WITH_PHOTOGRAPH))
                                || selectedItem.equalsIgnoreCase(SPINNER_ITEM_FIELD_NAME_AUTHORIZED_SIGNATURE_PROOF)) {
                            dynamicUITable.setVisibility(false);

                        } else if (!TextUtils.isEmpty(selectedItem) &&
                                (selectedItem.equalsIgnoreCase(SPINNER_ITEM_FIELD_NAME_DEEMED_OVD)
                                        || selectedItem.equalsIgnoreCase(SPINNER_ITEM_FIELD_NAME_UIDAI_ELIGIBLE_DOCUMENTS))) {
                            dynamicUITable.setVisibility(true);
                            dynamicUITable.setIsRequired(VIEW_NOT_REQUIRED);

                        } else {
                            dynamicUITable.setVisibility(true);
                            dynamicUITable.setIsRequired(VIEW_REQUIRED);
                        }
                    } else if (!TextUtils.isEmpty(dynamicUITable.getFieldName()) &&
                            dynamicUITable.getFieldName().equalsIgnoreCase(TAG_NAME_RE_ENTER_KYC_ID)) {
                        // TODO: Only here we need to check with field name
                        dynamicUITable.setLength(datatypeInfo.getLength());
                        dynamicUITable.setEditable(true);
                        dynamicUITable.setHint("Re " + datatypeInfo.getHint());
                        dynamicUITable.setDataType(datatypeInfo.getInputType());
                        dynamicUITable.setDataEntryType(datatypeInfo.getDataEntryType());
                        dynamicUITable.setFieldTag("RE ENTER " + datatypeInfo.getHintTag()); // TODO: RE ENTER TAG NAME
//                        dynamicUITable.setValue(""); // TODO: clearing the value once changing the spinner item
                        if (!TextUtils.isEmpty(selectedItem) &&
                                (selectedItem.equalsIgnoreCase(SPINNER_ITEM_FIELD_NAME_UTILITY_BILLS)
                                        || selectedItem.equalsIgnoreCase(SPINNER_ITEM_FIELD_NAME_BANK_PASSBOOK_WITH_PHOTOGRAPH)
                                        || selectedItem.equalsIgnoreCase(SPINNER_ITEM_FIELD_NAME_AUTHORIZED_SIGNATURE_PROOF)
                                )) {
                            dynamicUITable.setVisibility(false);
                        } else if (!TextUtils.isEmpty(selectedItem) &&
                                (selectedItem.equalsIgnoreCase(SPINNER_ITEM_FIELD_NAME_DEEMED_OVD)
                                        || selectedItem.equalsIgnoreCase(SPINNER_ITEM_FIELD_NAME_UIDAI_ELIGIBLE_DOCUMENTS))) {
                            dynamicUITable.setVisibility(true);
                            dynamicUITable.setIsRequired(VIEW_NOT_REQUIRED);

                        } else {
                            dynamicUITable.setVisibility(true);
                            dynamicUITable.setIsRequired(VIEW_REQUIRED);
                        }
                    }

                }

                if (selectedItem.equalsIgnoreCase(SPINNER_ITEM_FIELD_NAME_PAN_CARD) || selectedItem.equalsIgnoreCase(SPINNER_ITEM_FIELD_NAME_AUTHORIZED_SIGNATURE_PROOF)) {
                    List<ParameterInfo> parameterInfoList = new ArrayList<>();
                    parameterInfoList.add(new ParameterInfo(TAG_NAME_KYC_TYPE, SCREEN_ID, selectedItem, true, true));
                     parameterInfoList.add(new ParameterInfo(TAG_NAME_CONTAINS_DOB, SCREEN_ID, "", true, true));
                    parameterInfoList.add(new ParameterInfo(TAG_NAME_CONTAINS_AGE, SCREEN_ID, "", true, true));
                    parameterInfoList.add(new ParameterInfo(TAG_NAME_GENDER, SCREEN_ID, "", true, true));

                    parameterInfoList.add(new ParameterInfo(TAG_NAME_ADDRESS_LINE_1, SCREEN_ID, "", false, true));
                    parameterInfoList.add(new ParameterInfo(TAG_NAME_ADDRESS_LINE_2, SCREEN_ID, "", false, true));
                    parameterInfoList.add(new ParameterInfo(TAG_NAME_ADDRESS_LINE_3, SCREEN_ID, "", false, true));
                    parameterInfoList.add(new ParameterInfo(TAG_NAME_PINCODE, SCREEN_ID, "", false, true));
                    parameterInfoList.add(new ParameterInfo(TAG_NAME_CITY, SCREEN_ID, "", false, true));
                    parameterInfoList.add(new ParameterInfo(TAG_NAME_DISTRICT, SCREEN_ID, "", false, true));
                    parameterInfoList.add(new ParameterInfo(TAG_NAME_STATE, SCREEN_ID, "", false, true));
                    parameterInfoList.add(new ParameterInfo(TAG_NAME_VILLAGE, SCREEN_ID, "", false, true));
                    parameterInfoList.add(new ParameterInfo(TAG_NAME_LANDMARK, SCREEN_ID, "", false, true));
                    parameterInfoList.add(new ParameterInfo(TAG_NAME_EKYC_BUTTON, SCREEN_ID, "", false, true));
                    parameterInfoList.add(new ParameterInfo(TAG_NAME_QR_READING_BUTTON, SCREEN_ID, "", false, true));
                    parameterInfoList.add(new ParameterInfo(TAG_NAME_EXPIRY_DATE, SCREEN_ID, "", false, true));

                    parameterInfoList.add(new ParameterInfo(TAG_NAME_ADD_ANOTHER_KYC_PLUS_BUTTON, SCREEN_ID, "", true, true));
                    // parameterInfoList.add(new ParameterInfo(TAG_NAME_SAVE_BUTTON, SCREEN_ID, "", true, true));

                    EnableOrDisableByFieldNameInDB(parameterInfoList, viewParametersList);

                } else if (selectedItem.equalsIgnoreCase(SPINNER_ITEM_FIELD_NAME_AADHAAR)
                        || selectedItem.equalsIgnoreCase(SPINNER_ITEM_FIELD_NAME_VID)) {

                    List<ParameterInfo> parameterInfoList = new ArrayList<>();

                    parameterInfoList.add(new ParameterInfo(TAG_NAME_KYC_TYPE, SCREEN_ID, selectedItem, true, true));

                    parameterInfoList.add(new ParameterInfo(TAG_NAME_FULL_NAME, SCREEN_ID, "", false, true));
                    parameterInfoList.add(new ParameterInfo(TAG_NAME_CONTAINS_DOB, SCREEN_ID, "", false, true));
                    parameterInfoList.add(new ParameterInfo(TAG_NAME_CONTAINS_AGE, SCREEN_ID, "", false, true));
                    parameterInfoList.add(new ParameterInfo(TAG_NAME_GENDER, SCREEN_ID, "", false, true));

                    parameterInfoList.add(new ParameterInfo(TAG_NAME_ADDRESS_LINE_1, SCREEN_ID, "", false, true));
                    parameterInfoList.add(new ParameterInfo(TAG_NAME_ADDRESS_LINE_2, SCREEN_ID, "", false, true));
                    parameterInfoList.add(new ParameterInfo(TAG_NAME_ADDRESS_LINE_3, SCREEN_ID, "", false, true));
                    parameterInfoList.add(new ParameterInfo(TAG_NAME_PINCODE, SCREEN_ID, "", false, true));
                    parameterInfoList.add(new ParameterInfo(TAG_NAME_CITY, SCREEN_ID, "", false, true));
                    parameterInfoList.add(new ParameterInfo(TAG_NAME_DISTRICT, SCREEN_ID, "", false, true));
                    parameterInfoList.add(new ParameterInfo(TAG_NAME_STATE, SCREEN_ID, "", false, true));
                    parameterInfoList.add(new ParameterInfo(TAG_NAME_VILLAGE, SCREEN_ID, "", false, true));
                    parameterInfoList.add(new ParameterInfo(TAG_NAME_LANDMARK, SCREEN_ID, "", false, true));
                    parameterInfoList.add(new ParameterInfo(TAG_NAME_EKYC_BUTTON, SCREEN_ID, "", true, true));
                    parameterInfoList.add(new ParameterInfo(TAG_NAME_QR_READING_BUTTON, SCREEN_ID, "", false, true));
                    parameterInfoList.add(new ParameterInfo(TAG_NAME_EXPIRY_DATE, SCREEN_ID, "", false, true));
                    parameterInfoList.add(new ParameterInfo(TAG_NAME_ADD_ANOTHER_KYC_PLUS_BUTTON, SCREEN_ID, "", false, true));
                    //   parameterInfoList.add(new ParameterInfo(TAG_NAME_SAVE_BUTTON, SCREEN_ID, "", false, true));
                    EnableOrDisableByFieldNameInDB(parameterInfoList, viewParametersList);

                } else {
                    List<ParameterInfo> parameterInfoList = new ArrayList<>();
                    parameterInfoList.add(new ParameterInfo(TAG_NAME_KYC_TYPE, SCREEN_ID, selectedItem, true, true));

                    parameterInfoList.add(new ParameterInfo(TAG_NAME_FULL_NAME, SCREEN_ID, "", true, true));
                    parameterInfoList.add(new ParameterInfo(TAG_NAME_CONTAINS_DOB, SCREEN_ID, "", true, true));
                    parameterInfoList.add(new ParameterInfo(TAG_NAME_CONTAINS_AGE, SCREEN_ID, "", true, true));
                    // TODO: for JLG gender is female by default
                    if (viewParameters.getLoanType().equalsIgnoreCase(LOAN_NAME_JLG) && selectedItem.equalsIgnoreCase(SPINNER_ITEM_FIELD_NAME_VOTER_ID)) {
                        parameterInfoList.add(new ParameterInfo(TAG_NAME_GENDER, SCREEN_ID, "FEMALE", true, false));

                    } else {
                        parameterInfoList.add(new ParameterInfo(TAG_NAME_GENDER, SCREEN_ID, "", true, true));
                    }
                    parameterInfoList.add(new ParameterInfo(TAG_NAME_ADDRESS_LINE_1, SCREEN_ID, "", true, true));
                    parameterInfoList.add(new ParameterInfo(TAG_NAME_ADDRESS_LINE_2, SCREEN_ID, "", true, true));
                    parameterInfoList.add(new ParameterInfo(TAG_NAME_ADDRESS_LINE_3, SCREEN_ID, "", true, true));
                    parameterInfoList.add(new ParameterInfo(TAG_NAME_PINCODE, SCREEN_ID, "", true, true));
                    parameterInfoList.add(new ParameterInfo(TAG_NAME_CITY, SCREEN_ID, "", true, true));
                    parameterInfoList.add(new ParameterInfo(TAG_NAME_DISTRICT, SCREEN_ID, "", true, true));
                    parameterInfoList.add(new ParameterInfo(TAG_NAME_STATE, SCREEN_ID, "", true, true));
                    parameterInfoList.add(new ParameterInfo(TAG_NAME_VILLAGE, SCREEN_ID, "", true, true));
                    parameterInfoList.add(new ParameterInfo(TAG_NAME_LANDMARK, SCREEN_ID, "", true, true));

                    parameterInfoList.add(new ParameterInfo(TAG_NAME_EKYC_BUTTON, SCREEN_ID, "", false, true));
                    parameterInfoList.add(new ParameterInfo(TAG_NAME_QR_READING_BUTTON, SCREEN_ID, "", false, true));

                    /*if (selectedItem.equalsIgnoreCase(SPINNER_ITEM_FIELD_NAME_VOTER_ID)) {
                        parameterInfoList.add(new ParameterInfo(TAG_NAME_CONTAINS_DOB, SCREEN_ID, "", true, false));

                    }*/

                    if (selectedItem.equalsIgnoreCase(SPINNER_ITEM_FIELD_NAME_DRIVING_LICENSE)
                            || selectedItem.equalsIgnoreCase(SPINNER_ITEM_FIELD_NAME_PASSPORT)) {
                        parameterInfoList.add(new ParameterInfo(TAG_NAME_EXPIRY_DATE, SCREEN_ID, "", true, true));
                    } else {
                        parameterInfoList.add(new ParameterInfo(TAG_NAME_EXPIRY_DATE, SCREEN_ID, "", false, true));
                    }

                    parameterInfoList.add(new ParameterInfo(TAG_NAME_ADD_ANOTHER_KYC_PLUS_BUTTON, SCREEN_ID, "", true, true));
                    //  parameterInfoList.add(new ParameterInfo(TAG_NAME_SAVE_BUTTON, SCREEN_ID, "", true, true));

                    EnableOrDisableByFieldNameInDB(parameterInfoList, viewParametersList);
                }

//                updateDynamicUITable(viewParametersList, SCREEN_ID);
            }


        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void setPersonalScreenChangesBySpinner(List<DynamicUITable> viewParametersList, DynamicUITable viewParameters, XMLCustomSpinner customSpinner) {
        try {
            if (!TextUtils.isEmpty(viewParameters.getFieldTag()) && viewParameters.getFieldTag().equalsIgnoreCase(TAG_NAME_KYC_TYPE)) {
                viewParameters.setFieldType(FIELD_TYPE_LIST);
                viewParameters.setEditable(true);
                String selectedItem = customSpinner.getSelectedItem().toString();
                DataTypeInfo datatypeInfo = new DataTypeInfo(selectedItem, viewParameters);
                for (int i = 0; i < viewParametersList.size(); i++) {
                    DynamicUITable dynamicUITable = viewParametersList.get(i);
                    if (dynamicUITable != null && dynamicUITable.getFieldTag().equalsIgnoreCase(TAG_NAME_KYC_TYPE)) {
                        if (dynamicUITable.getFieldTag().equalsIgnoreCase(TAG_NAME_KYC_TYPE)) {
                            dynamicUITable.setSpinnerItemPosition(customSpinner.getSelectedItemPosition());
                            dynamicUITable.setFieldType(FIELD_TYPE_LIST);
                            dynamicUITable.setEditable(true);
                            dynamicUITable.setValue(selectedItem);
                        }
                    } else if (!TextUtils.isEmpty(dynamicUITable.getFieldName()) &&
                            dynamicUITable.getFieldName().equalsIgnoreCase(TAG_NAME_KYC_ID)) {
                        // TODO: Only here we need to check with field name
                        dynamicUITable.setLength(datatypeInfo.getLength());
                        dynamicUITable.setEditable(true);
                        dynamicUITable.setHint(datatypeInfo.getHint());
                        dynamicUITable.setDataType(datatypeInfo.getInputType());
                        dynamicUITable.setDataEntryType(datatypeInfo.getDataEntryType());
                        dynamicUITable.setFieldTag(datatypeInfo.getHintTag());

                        dynamicUITable.setValue("");
                        dynamicUITable.setErrorMessage("");// TODO: clearing the value once changing the spinner item
                        if (!TextUtils.isEmpty(selectedItem) &&
                                (selectedItem.equalsIgnoreCase(SPINNER_ITEM_FIELD_NAME_UTILITY_BILLS)
                                        || selectedItem.equalsIgnoreCase(SPINNER_ITEM_FIELD_NAME_BANK_PASSBOOK_WITH_PHOTOGRAPH))
                                || selectedItem.equalsIgnoreCase(SPINNER_ITEM_FIELD_NAME_AUTHORIZED_SIGNATURE_PROOF)) {
                            dynamicUITable.setVisibility(false);

                        } else if (!TextUtils.isEmpty(selectedItem) &&
                                (selectedItem.equalsIgnoreCase(SPINNER_ITEM_FIELD_NAME_DEEMED_OVD)
                                        || selectedItem.equalsIgnoreCase(SPINNER_ITEM_FIELD_NAME_UIDAI_ELIGIBLE_DOCUMENTS))) {
                            dynamicUITable.setVisibility(true);
                            dynamicUITable.setIsRequired(VIEW_NOT_REQUIRED);

                        } else {
                            dynamicUITable.setVisibility(true);
                            dynamicUITable.setIsRequired(VIEW_REQUIRED);
                        }
                    }
                }

                if (selectedItem.equalsIgnoreCase(SPINNER_ITEM_FIELD_NAME_AADHAAR)
                        || selectedItem.equalsIgnoreCase(SPINNER_ITEM_FIELD_NAME_VID)) {

                    List<ParameterInfo> parameterInfoList = new ArrayList<>();

                    parameterInfoList.add(new ParameterInfo(TAG_NAME_QR_READING_BUTTON, SCREEN_ID, "", true, true));

                    EnableOrDisableByFieldNameInDB(parameterInfoList, viewParametersList);

                } else {
                    List<ParameterInfo> parameterInfoList = new ArrayList<>();

                    parameterInfoList.add(new ParameterInfo(TAG_NAME_QR_READING_BUTTON, SCREEN_ID, "", false, true));

                    EnableOrDisableByFieldNameInDB(parameterInfoList, viewParametersList);
                }

//                updateDynamicUITable(viewParametersList, SCREEN_ID);
            }


        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void setNomineeScreenChangesBySpinner(List<DynamicUITable> viewParametersList, DynamicUITable viewParameters, XMLCustomSpinner customSpinner) {
        try {
            if (!TextUtils.isEmpty(viewParameters.getFieldTag()) && viewParameters.getFieldTag().equalsIgnoreCase(TAG_NAME_NOMINEE_KYC_TYPE)) {
                viewParameters.setFieldType(FIELD_TYPE_LIST);
                viewParameters.setEditable(true);
                String selectedItem = customSpinner.getSelectedItem().toString();
                DataTypeInfo datatypeInfo = new DataTypeInfo(selectedItem, viewParameters);
                for (int i = 0; i < viewParametersList.size(); i++) {
                    DynamicUITable dynamicUITable = viewParametersList.get(i);
                    if (dynamicUITable != null && dynamicUITable.getFieldTag().equalsIgnoreCase(TAG_NAME_NOMINEE_KYC_TYPE)) {
                        if (dynamicUITable.getFieldTag().equalsIgnoreCase(TAG_NAME_NOMINEE_KYC_TYPE)) {
                            dynamicUITable.setSpinnerItemPosition(customSpinner.getSelectedItemPosition());
                            dynamicUITable.setFieldType(FIELD_TYPE_LIST);
                            dynamicUITable.setEditable(true);
                            dynamicUITable.setValue(selectedItem);
                        }
                    } else if (!TextUtils.isEmpty(dynamicUITable.getFieldName()) &&
                            dynamicUITable.getFieldName().equalsIgnoreCase(TAG_NAME_NOMINEE_KYC_ID)) {
                        // TODO: Only here we need to check with field name
                        dynamicUITable.setLength(datatypeInfo.getLength());
                        dynamicUITable.setEditable(true);
                        dynamicUITable.setHint(datatypeInfo.getHint());
                        dynamicUITable.setDataType(datatypeInfo.getInputType());
                        dynamicUITable.setDataEntryType(datatypeInfo.getDataEntryType());
                        dynamicUITable.setFieldTag(datatypeInfo.getHintTag());

                        dynamicUITable.setValue("");
                        dynamicUITable.setErrorMessage("");// TODO: clearing the value once changing the spinner item
                        if (!TextUtils.isEmpty(selectedItem) &&
                                (selectedItem.equalsIgnoreCase(SPINNER_ITEM_FIELD_NAME_UTILITY_BILLS)
                                        || selectedItem.equalsIgnoreCase(SPINNER_ITEM_FIELD_NAME_BANK_PASSBOOK_WITH_PHOTOGRAPH))
                                || selectedItem.equalsIgnoreCase(SPINNER_ITEM_FIELD_NAME_AUTHORIZED_SIGNATURE_PROOF)) {
                            dynamicUITable.setVisibility(false);

                        } else if (!TextUtils.isEmpty(selectedItem) &&
                                (selectedItem.equalsIgnoreCase(SPINNER_ITEM_FIELD_NAME_DEEMED_OVD)
                                        || selectedItem.equalsIgnoreCase(SPINNER_ITEM_FIELD_NAME_UIDAI_ELIGIBLE_DOCUMENTS))) {
                            dynamicUITable.setVisibility(true);
                            dynamicUITable.setIsRequired(VIEW_NOT_REQUIRED);

                        } else {
                            dynamicUITable.setVisibility(true);
                            dynamicUITable.setIsRequired(VIEW_REQUIRED);
                        }
                    }
                }

                if (selectedItem.equalsIgnoreCase(SPINNER_ITEM_FIELD_NAME_AADHAAR)
                        || selectedItem.equalsIgnoreCase(SPINNER_ITEM_FIELD_NAME_VID)) {

                    List<ParameterInfo> parameterInfoList = new ArrayList<>();

                    parameterInfoList.add(new ParameterInfo(TAG_NAME_NOMINEE_QR_SCAN, SCREEN_ID, "", true, true));

                    EnableOrDisableByFieldNameInDB(parameterInfoList, viewParametersList);

                } else {
                    List<ParameterInfo> parameterInfoList = new ArrayList<>();

                    parameterInfoList.add(new ParameterInfo(TAG_NAME_NOMINEE_QR_SCAN, SCREEN_ID, "", false, true));

                    EnableOrDisableByFieldNameInDB(parameterInfoList, viewParametersList);
                }


//                updateDynamicUITable(viewParametersList, SCREEN_ID);
            }


        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void setFamilyMemberValue(String value, String tagName, List<DynamicUITable> dynamicUITableList) {
        try {
            if (!TextUtils.isEmpty(value)) {
                List<ParameterInfo> parameterInfoList = new ArrayList<>();
                parameterInfoList.add(new ParameterInfo(tagName, SCREEN_ID, value, true, true));
                parameterInfoList.add(new ParameterInfo(TAG_NAME_FAMILY_MEMBERS, SCREEN_ID, value, true, false));
                EnableOrDisableByFieldNameInDB(parameterInfoList, dynamicUITableList);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    private String getValuesBasedOnTAGinLoop(String tagNameKycType, List<DynamicUITable> viewParametersList) {
        String value = "";
        try {
            for (DynamicUITable dynamicUITable : viewParametersList) {
                if (dynamicUITable != null && !TextUtils.isEmpty(dynamicUITable.getFieldTag())
                        && dynamicUITable.getFieldTag().trim().equalsIgnoreCase(tagNameKycType)) {

                    value = dynamicUITable.getValue();
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return value;
    }


    public void saveSubmittedData(RawDataTable rawDataTable, List<DynamicUITable> viewParametersList, String submittedValues,
                                  boolean isAdditional,
                                  DynamicUITable dynamicUITable, String selectedItem, String tagName) {

        appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();
        try {
            // TODO: Note ==> checking field name
            if (!TextUtils.isEmpty(dynamicUITable.getFieldName()) &&
                    (dynamicUITable.getFieldName().equalsIgnoreCase(FIELD_NAME_SAVE)
                            || dynamicUITable.getFieldName().equalsIgnoreCase(FIELD_NAME_ADD_ANOTHER_KYC)
                            || dynamicUITable.getFieldName().equalsIgnoreCase(FIELD_NAME_SUBMIT)
                            || dynamicUITable.getFieldName().equalsIgnoreCase(FIELD_NAME_SAVE_AND_ADD_ANOTHER_KYC)
                            || dynamicUITable.getFieldName().equalsIgnoreCase(TAG_NAME_ADD_ANOTHER_BUSINESS_PROOF_PLUS_BUTTON)
                            || dynamicUITable.getFieldName().equalsIgnoreCase(TAG_NAME_ADD_BANK_DETAILS_PLUS_BUTTON)
                            || dynamicUITable.getFieldName().equalsIgnoreCase(FIELD_NAME_ADD_ANOTHER_REFERENCE)
                            || dynamicUITable.getFieldName().equalsIgnoreCase(FIELD_NAME_SAVE_AND_ADD_GENERAL_INCOME)
                            || dynamicUITable.getFieldName().equalsIgnoreCase(FIELD_NAME_SAVE_AND_ADD_INCOME_DETAIL)
                            || dynamicUITable.getFieldName().equalsIgnoreCase(FIELD_NAME_ADD_BANKING_HISTORY)
                            || dynamicUITable.getFieldName().equalsIgnoreCase(FIELD_NAME_ADD_ITR)
                            || dynamicUITable.getFieldName().equalsIgnoreCase(FIELD_NAME_ADD_RAW_MATERIAL_IN_PRODUCT_ESTIMATE_DETAIL)
                            || dynamicUITable.getFieldName().equalsIgnoreCase(FIELD_NAME_ADD_RAW_MATERIAL_IN_SERVICE_ESTIMATE_DETAIL)
                            || dynamicUITable.getFieldName().equalsIgnoreCase(FIELD_NAME_ADD_SALES_BILLS)
                            || dynamicUITable.getFieldName().equalsIgnoreCase(FIELD_NAME_ADD_PURCHASE_BILLS)
                            || dynamicUITable.getFieldName().equalsIgnoreCase(FIELD_NAME_ADD_LIABILITY)
                            || dynamicUITable.getFieldName().equalsIgnoreCase(FIELD_NAME_ADD_PRODUCT_OR_INVENTORY)
                            || dynamicUITable.getFieldName().equalsIgnoreCase(FIELD_NAME_ADD_ADVANCES_OR_ARREARS))) {

                viewModel.insertRawData(rawDataTable, viewParametersList, dynamicUITable, false);
                Observer rawDataObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        appHelper.getDialogHelper().getLoadingDialog().closeDialog();
                        viewModel.getRawTableLiveData().removeObserver(this);
                        if (isAdditional) {

                            // TODO: APPLICANT KYC SCREEN
                            if (SCREEN_NAME.equalsIgnoreCase(SCREEN_NAME_APPLICANT_KYC)
                                    || SCREEN_NAME.equalsIgnoreCase(SCREEN_NAME_CO_APPLICANT_KYC)
                                    || SCREEN_NAME.equalsIgnoreCase(SCREEN_NAME_REFERENCE_CHECK)
                                    || SCREEN_NAME.equalsIgnoreCase(SCREEN_NAME_REFERENCES)
                                    || SCREEN_NAME.equalsIgnoreCase(SCREEN_NAME_GENERAL_INCOME)
                                    || SCREEN_NAME.equalsIgnoreCase(SCREEN_NAME_OTHER_INCOME_SOURCE)
                            ) {
                                appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.SUCCESS,
                                        ERROR_MESSAGE_DATA_SAVED_SUCCESSFULLY, new ConfirmationDialog.ActionCallback() {
                                            @Override
                                            public void onAction() {
                                                if (!TextUtils.isEmpty(LOAN_TYPE) && LOAN_TYPE.equalsIgnoreCase(LOAN_NAME_JLG)) {
                                                    if (SCREEN_NAME.equalsIgnoreCase(SCREEN_NAME_APPLICANT_KYC)) {
                                                        // TODO: MOVE TO NEXT SCREEN
                                                        moveToNextScreen();
                                                    } else {
                                                        getMetaDataByScreenName(SCREEN_NAME, LOAN_TYPE);
                                                    }
                                                } else {
                                                    getMetaDataByScreenName(SCREEN_NAME, LOAN_TYPE);
                                                }
                                            }
                                        });
                            } else {
                                // TODO: NEEDS TO REMOVE THIS METHOD
                                getTagNameList(rawDataTable.getScreen_name(), viewParametersList, tagName);
                            }

                        } else {
                            if (SCREEN_NAME.equalsIgnoreCase(SCREEN_NAME_LEAD) || SCREEN_NAME.equalsIgnoreCase(SCREEN_NAME_COLD_CALLING)
                                /*|| SCREEN_NAME.equalsIgnoreCase(SCREEN_NAME_SALES_TOOL)*/) {
                                // TODO: LEAD SCREEN VALIDATION ==> Base Activity ==> Lead Details Activity by onActivityResult
                                FragmentToActivityInterface fragmentToActivityInterface = (FragmentToActivityInterface) getActivity();
                                fragmentToActivityInterface.valueFromFragment(SCREEN_ID, SCREEN_NAME, "", null);
                            } else if (SCREEN_NAME.equalsIgnoreCase(SCREEN_NAME_VILLAGE_SURVEY) || SCREEN_NAME.equalsIgnoreCase(SCREEN_NAME_CENTER_CREATION)) {
                                getActivity().finish();
                            } else if (!TextUtils.isEmpty(SCREEN_ID) && SCREEN_ID.equalsIgnoreCase(SCREEN_N0_EMPLOYER_VERIFICATION)) {
                                if (LOSBaseFragment.this instanceof ChildFragment) {
                                    ChildFragment childFragment = (ChildFragment) LOSBaseFragment.this;
                                    childFragment.callCloseDialogFragment();
                                }
                            } else if (!TextUtils.isEmpty(SCREEN_ID) && SCREEN_ID.equalsIgnoreCase(SCREEN_N0_ADD_PRODUCT_ESTIMATE)) {

                                List<String> FromFieldNameList = new ArrayList<>();
                                FromFieldNameList.add(TAG_NAME_PRODUCT_NAME);
                                FromFieldNameList.add(TAG_NAME_MONTHLY_SALES);

                                List<String> ToFieldNameList = new ArrayList<>();
                                ToFieldNameList.add(TAG_NAME_PRODUCT_NAME);
                                ToFieldNameList.add(TAG_NAME_PRODUCTS);

                                calculateAndUpdate(FromFieldNameList, ToFieldNameList,
                                        SCREEN_N0_ADD_PRODUCT_ESTIMATE, SCREEN_N0_MANUFACTURING_PRODUCT_ESTIMATE,
                                        dynamicUITable, TAG_NAME_PRODUCT_NAME, TAG_NAME_PRODUCT_NAME,
                                        TAG_NAME_MONTHLY_SALES, false, rawDataTable,
                                        viewParametersList);

                            } else {
                                if (LOSBaseFragment.this instanceof ChildFragment) {

                                    DynamicUITable dynamicUITableChildObj = viewParametersList.get(0);
                                    if (dynamicUITableChildObj != null) {
                                        // TODO: GET RAW DATA FOR PARENT SCREEN
                                        if (dynamicUITableChildObj.getScreenName().equalsIgnoreCase(SCREEN_NAME_MONTHLY_TRANSACTION_DETAIL_MSME)) {
                                            getMetaDataByScreenName(SCREEN_NAME_BANKING_HISTORY_MSME, LOAN_TYPE);
                                        } else if (dynamicUITableChildObj.getScreenName().equalsIgnoreCase(SCREEN_NAME_SALES_BILLS_DETAIL_MSME)) {
                                            getMetaDataByScreenName(SCREEN_NAME_SALES_BILLS_MSME, LOAN_TYPE);
                                        } else if (dynamicUITableChildObj.getScreenName().equalsIgnoreCase(SCREEN_NAME_PURCHASE_BILLS_DETAIL_MSME)) {
                                            getMetaDataByScreenName(SCREEN_NAME_PURCHASE_BILLS_MSME, LOAN_TYPE);
                                        } else if (dynamicUITableChildObj.getScreenName().equalsIgnoreCase(SCREEN_NAME_ITR_DETAIL_MSME)) {
                                            getMetaDataByScreenName(SCREEN_NAME_ITR_MSME, LOAN_TYPE);
                                        } else if (dynamicUITableChildObj.getScreenName().equalsIgnoreCase(SCREEN_NAME_PRODUCT_RAW_MATERIAL_MSME)) {
                                            getMetaDataByScreenName(SCREEN_NAME_PRODUCT_ESTIMATE_DETAIL_MSME, LOAN_TYPE);
                                        } else if (dynamicUITableChildObj.getScreenName().equalsIgnoreCase(SCREEN_NAME_SERVICE_RAW_MATERIAL_MSME)) {
                                            getMetaDataByScreenName(SCREEN_NAME_SERVICE_ESTIMATE_DETAIL_MSME, LOAN_TYPE);
                                        } else if (dynamicUITableChildObj.getScreenName().equalsIgnoreCase(SCREEN_NAME_DIRECT_BUSINESS_EXPENSE_DETAIL_MSME)) {
                                            getMetaDataByScreenName(SCREEN_NAME_DIRECT_BUSINESS_EXPENSE_MSME, LOAN_TYPE);
                                        } else if (dynamicUITableChildObj.getScreenName().equalsIgnoreCase(SCREEN_NAME_HOUSE_DEBTS)) {
                                            getMetaDataByScreenName(SCREEN_NAME_HOUSE_LIABILITIES_MSME, LOAN_TYPE);
                                        } else if (dynamicUITableChildObj.getScreenName().equalsIgnoreCase(SCREEN_NAME_BUSINESS_ACCOUNT_PAYABLE)) {
                                            getMetaDataByScreenName(SCREEN_NAME_BUSINESS_LIABILITIES_MSME, LOAN_TYPE);
                                        } else if (dynamicUITableChildObj.getScreenName().equalsIgnoreCase(SCREEN_NAME_BUSINESS_DEBTS_DETAIL_MSME)) {
                                            getMetaDataByScreenName(SCREEN_NAME_BUSINESS_LIABILITIES_MSME, LOAN_TYPE);
                                        } else if (dynamicUITableChildObj.getScreenName().equalsIgnoreCase(SCREEN_NAME_ADVANCE_DETAIL__MSME)) {
                                            getMetaDataByScreenName(SCREEN_NAME_BUSINESS_LIABILITIES_MSME, LOAN_TYPE);
                                        } else if (dynamicUITableChildObj.getScreenName().equalsIgnoreCase(SCREEN_NAME_HYPOTHECATION_DETAIL_MSME)) {
                                            getMetaDataByScreenName(SCREEN_NAME_HYPOTHECATION_MSME, LOAN_TYPE);
                                        } else if (dynamicUITableChildObj.getScreenName().equalsIgnoreCase(SCREEN_NAME_HOUSE_ASSETS_BUILDING_DETAIL_MSME)
                                                || dynamicUITableChildObj.getScreenName().equalsIgnoreCase(SCREEN_NAME_HOUSE_ASSETS_LAND_DETAIL_MSME)
                                                || dynamicUITableChildObj.getScreenName().equalsIgnoreCase(SCREEN_NAME_HOUSE_ASSETS_OPEN_PLOT_DETAIL_MSME)
                                                || dynamicUITableChildObj.getScreenName().equalsIgnoreCase(SCREEN_NAME_HOUSE_ASSETS_TWO_WHEELER_DETAIL_MSME)
                                                || dynamicUITableChildObj.getScreenName().equalsIgnoreCase(SCREEN_NAME_HOUSE_ASSETS_CAR_DETAIL_MSME)
                                                || dynamicUITableChildObj.getScreenName().equalsIgnoreCase(SCREEN_NAME_HOUSE_ASSETS_VEHICLES_DETAIL_MSME)
                                                || dynamicUITableChildObj.getScreenName().equalsIgnoreCase(SCREEN_NAME_HOUSE_ASSETS_PLANT_MACHINERY_DETAIL_MSME)
                                                || dynamicUITableChildObj.getScreenName().equalsIgnoreCase(SCREEN_NAME_HOUSE_ASSETS_UTILITIES_DETAIL_MSME)
                                                || dynamicUITableChildObj.getScreenName().equalsIgnoreCase(SCREEN_NAME_HOUSE_ASSETS_LIVESTOCK_DETAIL_MSME)
                                                || dynamicUITableChildObj.getScreenName().equalsIgnoreCase(SCREEN_NAME_HOUSE_ASSETS_EQUIPMENT_DETAIL_MSME)
                                                || dynamicUITableChildObj.getScreenName().equalsIgnoreCase(SCREEN_NAME_HOUSE_ASSETS_TELEVISION_DETAIL_MSME)
                                                || dynamicUITableChildObj.getScreenName().equalsIgnoreCase(SCREEN_NAME_HOUSE_ASSETS_OTHERS_DETAIL_MSME)
                                                || dynamicUITableChildObj.getScreenName().equalsIgnoreCase(SCREEN_NAME_HOUSE_ASSETS_FURNITURE_DETAIL_MSME)
                                                || dynamicUITableChildObj.getScreenName().equalsIgnoreCase(SCREEN_NAME_HOUSE_ASSETS_COMMERCIAL_VEHICLE_DETAIL_MSME)
                                        ) {
                                            getMetaDataByScreenName(SCREEN_NAME_HOUSE_ASSETS_MSME, LOAN_TYPE);
                                        } else if (dynamicUITableChildObj.getScreenName().equalsIgnoreCase(SCREEN_NAME_BUSINESS_ASSETS_BUILDING_DETAIL_MSME)
                                                || dynamicUITableChildObj.getScreenName().equalsIgnoreCase(SCREEN_NAME_BUSINESS_ASSETS_LAND_DETAIL_MSME)
                                                || dynamicUITableChildObj.getScreenName().equalsIgnoreCase(SCREEN_NAME_BUSINESS_ASSETS_OPEN_PLOT_DETAIL_MSME)
                                                || dynamicUITableChildObj.getScreenName().equalsIgnoreCase(SCREEN_NAME_BUSINESS_ASSETS_TWO_WHEELER_DETAIL_MSME)
                                                || dynamicUITableChildObj.getScreenName().equalsIgnoreCase(SCREEN_NAME_BUSINESS_ASSETS_CAR_DETAIL_MSME)
                                                || dynamicUITableChildObj.getScreenName().equalsIgnoreCase(SCREEN_NAME_BUSINESS_ASSETS_VEHICLES_DETAIL_MSME)
                                                || dynamicUITableChildObj.getScreenName().equalsIgnoreCase(SCREEN_NAME_BUSINESS_ASSETS_PLANT_MACHINERY_DETAIL_MSME)
                                                || dynamicUITableChildObj.getScreenName().equalsIgnoreCase(SCREEN_NAME_BUSINESS_ASSETS_UTILITIES_DETAIL_MSME)
                                                || dynamicUITableChildObj.getScreenName().equalsIgnoreCase(SCREEN_NAME_BUSINESS_ASSETS_LIVESTOCK_DETAIL_MSME)
                                                || dynamicUITableChildObj.getScreenName().equalsIgnoreCase(SCREEN_NAME_BUSINESS_ASSETS_EQUIPMENT_DETAIL_MSME)
                                                || dynamicUITableChildObj.getScreenName().equalsIgnoreCase(SCREEN_NAME_BUSINESS_ASSETS_TELEVISION_DETAIL_MSME)
                                                || dynamicUITableChildObj.getScreenName().equalsIgnoreCase(SCREEN_NAME_BUSINESS_ASSETS_OTHERS_DETAIL_MSME)
                                                || dynamicUITableChildObj.getScreenName().equalsIgnoreCase(SCREEN_NAME_BUSINESS_ASSETS_FURNITURE_DETAIL_MSME)
                                                || dynamicUITableChildObj.getScreenName().equalsIgnoreCase(SCREEN_NAME_BUSINESS_ASSETS_COMMERCIAL_VEHICLE_DETAIL_MSME)

                                                || dynamicUITableChildObj.getScreenName().equalsIgnoreCase(SCREEN_NAME_CASH_BUSINESS_ASSETS_MSME)
                                                || dynamicUITableChildObj.getScreenName().equalsIgnoreCase(SCREEN_NAME_SAVINGS_CHITS_BUSINESS_ASSETS_MSME)
                                                || dynamicUITableChildObj.getScreenName().equalsIgnoreCase(SCREEN_NAME_DEPOSITS_BUSINESS_ASSETS_BONDS_MSME)
                                                || dynamicUITableChildObj.getScreenName().equalsIgnoreCase(SCREEN_NAME_INSURANCE_BUSINESS_ASSETS_MSME)
                                                || dynamicUITableChildObj.getScreenName().equalsIgnoreCase(SCREEN_NAME_INVENTORY_BUSINESS_ASSETS_MSME)
                                                || dynamicUITableChildObj.getScreenName().equalsIgnoreCase(SCREEN_NAME_RECEIVABLES_BUSINESS_ASSETS_MSME)
                                        ) {
                                            getMetaDataByScreenName(SCREEN_NAME_BUSINESS_ASSETS_MSME, LOAN_TYPE);
                                        } else if (dynamicUITableChildObj.getScreenName().equalsIgnoreCase(SCREEN_NAME_MORTGAGE_BUILDING_MSME)
                                                || dynamicUITableChildObj.getScreenName().equalsIgnoreCase(SCREEN_NAME_MORTGAGE_LAND_MSME)
                                                || dynamicUITableChildObj.getScreenName().equalsIgnoreCase(SCREEN_NAME_MORTGAGE_OPEN_PLOT_MSME)
                                        ) {
                                            getMetaDataByScreenName(SCREEN_NAME_MORTGAGE_MSME, LOAN_TYPE);
                                        } else if (dynamicUITableChildObj.getScreenName().equalsIgnoreCase(SCREEN_NAME_LIABILITIES)) {
                                            getMetaDataByScreenName(SCREEN_NAME_HOUSE_LIABILITIES, LOAN_TYPE);
                                        } else if (dynamicUITableChildObj.getScreenName().equalsIgnoreCase(SCREEN_NAME_PRODUCT)) {
                                            getMetaDataByScreenName(SCREEN_NAME_BUSINESS_ASSETS, LOAN_TYPE);
                                        } else if (dynamicUITableChildObj.getScreenName().equalsIgnoreCase(SCREEN_NAME_BUSINESS_DEBTS)) {
                                            getMetaDataByScreenName(SCREEN_NAME_BUSINESS_LIABILITIES, LOAN_TYPE);
                                        } else if (dynamicUITableChildObj.getScreenName().equalsIgnoreCase(SCREEN_NAME_ADVANCES)) {
                                            getMetaDataByScreenName(SCREEN_NAME_BUSINESS_LIABILITIES, LOAN_TYPE);
                                        } else if (dynamicUITableChildObj.getScreenName().equalsIgnoreCase(SCREEN_NAME_FAMILY_MEMBER_INCOME)) {
                                            getMetaDataByScreenName(SCREEN_NAME_HOUSE_INCOME, LOAN_TYPE);
                                        }
                                    }

                                } else {
                                    appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.SUCCESS,
                                            ERROR_MESSAGE_DATA_SAVED_SUCCESSFULLY, new ConfirmationDialog.ActionCallback() {
                                                @Override
                                                public void onAction() {
                                                    // TODO: LOAN APPROVAL SCREEN
                                                    if (SCREEN_NAME.equalsIgnoreCase(SCREEN_NAME_LOAN_APPROVAL_MSME)) {
                                                        DynamicUITable dynamicUITableLoanApprovalObj = viewParametersList.get(0);
                                                        if (dynamicUITableLoanApprovalObj != null) {
                                                            FragmentToActivityInterface fragmentToActivityInterface = (FragmentToActivityInterface) getActivity();
                                                            fragmentToActivityInterface.oneFragmentToAnotherFragment(SCREEN_NAME, SCREEN_NAME_CASH_FLOW_SUMMARY_MSME
                                                                    , dynamicUITableLoanApprovalObj, viewParametersList);
                                                        }
                                                    } else if (!TextUtils.isEmpty(LOAN_TYPE) && LOAN_TYPE.equalsIgnoreCase(LOAN_NAME_JLG)
                                                            && !SCREEN_NAME.equalsIgnoreCase(SCREEN_NAME_SOCIO_ECONOMIC_DETAIL)
                                                            && !SCREEN_NAME.equalsIgnoreCase(SCREEN_NAME_PERSONAL_DETAIL)
                                                            && !SCREEN_NAME.equalsIgnoreCase(SCREEN_NAME_BANK_DETAILS)
                                                    ) {
                                                        // TODO: MOVE TO NEXT SCREEN
                                                        moveToNextScreen();
                                                    } else if (!TextUtils.isEmpty(LOAN_TYPE) && LOAN_TYPE.equalsIgnoreCase(LOAN_NAME_JLG)
                                                            && SCREEN_NAME.equalsIgnoreCase(SCREEN_NAME_SOCIO_ECONOMIC_DETAIL)
                                                    ) {
                                                        // TODO: GO TO TARGET DETAILS SUMMARY
                                                        BaseActivity baseActivity = (BaseActivity) getActivity();
                                                        Intent targetDetails = new Intent(baseActivity, TargetDetailsActivity.class);
                                                        targetDetails.putExtra(PARAM_LOAN_TYPE, baseActivity.loanType);
                                                        targetDetails.putExtra(PARAM_USER_NAME, baseActivity.userName);
                                                        targetDetails.putExtra(PARAM_BRANCH_ID, baseActivity.branchId);
                                                        targetDetails.putExtra(PARAM_BRANCH_GST_CODE, baseActivity.branchGSTcode);
                                                        targetDetails.putExtra(PARAM_USER_ID, baseActivity.userId);
                                                        targetDetails.putExtra(PARAM_MODULE_TYPE, MODULE_TYPE_TARGET_DETAILS);
                                                        targetDetails.putExtra(PARAM_PRODUCT_ID, baseActivity.productId);
                                                        String centerTableJson = new Gson().toJson(baseActivity.CENTER_CREATION_TABLE, CenterCreationTable.class);
                                                        if (!TextUtils.isEmpty(centerTableJson)) {
                                                            targetDetails.putExtra(PARAM_CENTER_TABLE_JSON, centerTableJson);
                                                            targetDetails.putExtra(PARAM_CLIENT_ID, baseActivity.CENTER_CREATION_TABLE.getCenterId());
                                                        }
                                                        startActivity(targetDetails);

                                                        baseActivity.finish();

                                                    }else if (!TextUtils.isEmpty(LOAN_TYPE) && LOAN_TYPE.equalsIgnoreCase(LOAN_NAME_JLG)
                                                            && SCREEN_NAME.equalsIgnoreCase(SCREEN_NAME_BANK_DETAILS)) {

                                                        // TODO: Finish the activity for Bank details JLG
                                                        getActivity().finish();

                                                    } else {
                                                        getRawDataForParentFragment(SCREEN_NAME, viewParametersList);
                                                    }
                                                }
                                            });
                                }
                            }
                        }
                    }
                };
                viewModel.getRawTableLiveData().observe(this, rawDataObserver);
            } else if (!TextUtils.isEmpty(dynamicUITable.getFieldName()) &&
                    dynamicUITable.getFieldName().equalsIgnoreCase(FIELD_NAME_UPDATE)) {
                // TODO: Button UPDATE click
                if (!TextUtils.isEmpty(SCREEN_ID) && SCREEN_ID.equalsIgnoreCase(SCREEN_N0_ADD_PRODUCT_ESTIMATE)) {

                    List<String> FromFieldNameList = new ArrayList<>();
                    FromFieldNameList.add(TAG_NAME_PRODUCT_NAME);
                    FromFieldNameList.add(TAG_NAME_MONTHLY_SALES);

                    List<String> ToFieldNameList = new ArrayList<>();
                    ToFieldNameList.add(dynamicUITable.getValue());
                    ToFieldNameList.add(TAG_NAME_PRODUCTS);

                    calculateAndUpdate(FromFieldNameList, ToFieldNameList,
                            SCREEN_N0_ADD_PRODUCT_ESTIMATE, SCREEN_N0_MANUFACTURING_PRODUCT_ESTIMATE,
                            dynamicUITable, dynamicUITable.getValue(), TAG_NAME_PRODUCT_NAME,
                            TAG_NAME_MONTHLY_SALES, false, rawDataTable,
                            viewParametersList);

                } else {
                    updateRawDataTable(rawDataTable, viewParametersList, dynamicUITable);
                }

            } else if (!TextUtils.isEmpty(dynamicUITable.getFieldType()) &&
                    dynamicUITable.getFieldType().equalsIgnoreCase(FIELD_TYPE_ADD_BUTTON)) {
                // TODO: ADD BUTTON DATA SAVING
                viewModel.insertRawData(rawDataTable, viewParametersList, dynamicUITable, false);
                Observer rawDataUpdateObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        viewModel.getRawTableLiveData().removeObserver(this);
                    }
                };
                viewModel.getRawTableLiveData().observe(this, rawDataUpdateObserver);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            appHelper.getDialogHelper().getLoadingDialog().closeDialog();
        }
    }

    public void salesToolApiCall(RawDataTable rawDataTable, List<DynamicUITable> viewParametersList, String submittedValues,
                                 boolean isAdditional,
                                 DynamicUITable dynamicUITable, String selectedItem, String tagName) {

        appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();
        try {
            if (appHelper.isNetworkAvailable()) {
                viewModel.salesToolApiCall(rawDataTable, viewParametersList, dynamicUITable, false);
                Observer rawDataObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        List<RawDataTable> rawDataTableList = (List<RawDataTable>) o;
                        appHelper.getDialogHelper().getLoadingDialog().closeDialog();
                        viewModel.getRawTableLiveData().removeObserver(this);
                        // TODO: need to change validation
                        if(rawDataTableList!=null) {

                            appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.SUCCESS,
                                    MESSAGE_SUCCESS_RESPONSE, new ConfirmationDialog.ActionCallback() {
                                        @Override
                                        public void onAction() {
//                                            getRawDataForParentFragment(SCREEN_NAME, viewParametersList);
                                            deleteAndInsertNewRecord(viewParametersList, SCREEN_NAME);

                                        }
                                    });
                        }else{
                            appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ERROR, ERROR_MESSAGE_SALESTOOL_CALCULATION_FAILED);

                        }

                    }
                };
                viewModel.getRawTableLiveData().observe(this, rawDataObserver);
            }else{
                appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT,"No Internet Connection");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            appHelper.getDialogHelper().getLoadingDialog().closeDialog();
        }
    }

    private void moveToNextScreen() {
        try {
            BaseActivity baseActivity = (BaseActivity) getActivity();
            for (int i = 0; i < baseActivity.adapter.mFragmentTitleList.size(); i++) {
                String title = baseActivity.adapter.mFragmentTitleList.get(i);
                if (SCREEN_NAME.equalsIgnoreCase(title)) {
                    baseActivity.viewPager.setCurrentItem(i + 1);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    public void saveParentScreenRawData(RawDataTable rawDataTable, List<DynamicUITable> viewParametersList,
                                        DynamicUITable dynamicUITable, String newRowTagName) {
        try {
            viewModel.insertOrUpdateParentRawData(rawDataTable, viewParametersList, dynamicUITable);
            Observer insertOrUpdateParentRawDataObserver = new Observer() {
                @Override
                public void onChanged(@Nullable Object o) {
                    viewModel.getRawTableLiveData().removeObserver(this);
                    getRawDataForParentFragment(rawDataTable.getScreen_name(), viewParametersList);
                }
            };
            viewModel.getRawTableLiveData().observe(this, insertOrUpdateParentRawDataObserver);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void updateRawDataTable(RawDataTable rawDataTable, List<DynamicUITable> viewParametersList, DynamicUITable dynamicUITable) {
        try {
            appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();

            viewModel.updateRawDataBag(rawDataTable, viewParametersList, dynamicUITable, false);

            Observer rawDataUpdateObserver = new Observer() {
                @Override
                public void onChanged(@Nullable Object o) {
                    appHelper.getDialogHelper().getLoadingDialog().closeDialog();
                    RawDataTable updatedRawDataTable = (RawDataTable) o;
                    viewModel.getUpdatedRawdataRow().removeObserver(this);

                    if (SCREEN_NAME.equalsIgnoreCase(SCREEN_NAME_LEAD)
                            || SCREEN_NAME.equalsIgnoreCase(SCREEN_NAME_COLD_CALLING)
                        /*|| SCREEN_NAME.equalsIgnoreCase(SCREEN_NAME_SALES_TOOL)*/) {
                        // TODO: LEAD SCREEN VALIDATION ==> Base Activity ==> Lead Details Activity by onActivityResult
                        FragmentToActivityInterface fragmentToActivityInterface = (FragmentToActivityInterface) getActivity();
                        fragmentToActivityInterface.valueFromFragment(SCREEN_ID, SCREEN_NAME, "", null);
                    } else if (SCREEN_NAME.equalsIgnoreCase(SCREEN_NAME_LOAN_APPROVAL_MSME)) {
                        Toast.makeText(getContext(), "Data updated successfully", Toast.LENGTH_SHORT).show();
                        if (viewParametersList != null && viewParametersList.size() > 0) {
                            DynamicUITable dynamicUITableLoanApprovalObj = viewParametersList.get(0);
                            if (dynamicUITableLoanApprovalObj != null) {
                                FragmentToActivityInterface fragmentToActivityInterface = (FragmentToActivityInterface) getActivity();
                                fragmentToActivityInterface.oneFragmentToAnotherFragment(SCREEN_NAME, SCREEN_NAME_CASH_FLOW_SUMMARY_MSME
                                        , dynamicUITableLoanApprovalObj, viewParametersList);
                            }
                        }
                    } else {
                        if (viewParametersList != null && viewParametersList.size() > 0) {
                            if (LOSBaseFragment.this instanceof ChildFragment) {

                                // TODO: GET RAW DATA FOR PARENT SCREEN
                                if (updatedRawDataTable.getScreen_name().equalsIgnoreCase(SCREEN_NAME_MONTHLY_TRANSACTION_DETAIL_MSME)) {
                                    getMetaDataByScreenName(SCREEN_NAME_BANKING_HISTORY_MSME, LOAN_TYPE);
                                } else if (updatedRawDataTable.getScreen_name().equalsIgnoreCase(SCREEN_NAME_SALES_BILLS_DETAIL_MSME)) {
                                    getMetaDataByScreenName(SCREEN_NAME_SALES_BILLS_MSME, LOAN_TYPE);
                                } else if (updatedRawDataTable.getScreen_name().equalsIgnoreCase(SCREEN_NAME_PURCHASE_BILLS_DETAIL_MSME)) {
                                    getMetaDataByScreenName(SCREEN_NAME_PURCHASE_BILLS_MSME, LOAN_TYPE);
                                } else if (updatedRawDataTable.getScreen_name().equalsIgnoreCase(SCREEN_NAME_ITR_DETAIL_MSME)) {
                                    getMetaDataByScreenName(SCREEN_NAME_ITR_MSME, LOAN_TYPE);
                                } else if (updatedRawDataTable.getScreen_name().equalsIgnoreCase(SCREEN_NAME_PRODUCT_RAW_MATERIAL_MSME)) {
                                    getMetaDataByScreenName(SCREEN_NAME_PRODUCT_ESTIMATE_DETAIL_MSME, LOAN_TYPE);

                                } else if (updatedRawDataTable.getScreen_name().equalsIgnoreCase(SCREEN_NAME_SERVICE_RAW_MATERIAL_MSME)) {
                                    getMetaDataByScreenName(SCREEN_NAME_SERVICE_ESTIMATE_DETAIL_MSME, LOAN_TYPE);

                                } else if (updatedRawDataTable.getScreen_name().equalsIgnoreCase(SCREEN_NAME_DIRECT_BUSINESS_EXPENSE_DETAIL_MSME)) {
                                    getMetaDataByScreenName(SCREEN_NAME_DIRECT_BUSINESS_EXPENSE_MSME, LOAN_TYPE);
                                } else if (updatedRawDataTable.getScreen_name().equalsIgnoreCase(SCREEN_NAME_HOUSE_DEBTS)) {
                                    getMetaDataByScreenName(SCREEN_NAME_HOUSE_LIABILITIES_MSME, LOAN_TYPE);
                                } else if (updatedRawDataTable.getScreen_name().equalsIgnoreCase(SCREEN_NAME_BUSINESS_ACCOUNT_PAYABLE)) {
                                    getMetaDataByScreenName(SCREEN_NAME_BUSINESS_LIABILITIES_MSME, LOAN_TYPE);
                                } else if (updatedRawDataTable.getScreen_name().equalsIgnoreCase(SCREEN_NAME_BUSINESS_DEBTS_DETAIL_MSME)) {
                                    getMetaDataByScreenName(SCREEN_NAME_BUSINESS_LIABILITIES_MSME, LOAN_TYPE);
                                } else if (updatedRawDataTable.getScreen_name().equalsIgnoreCase(SCREEN_NAME_ADVANCE_DETAIL__MSME)) {
                                    getMetaDataByScreenName(SCREEN_NAME_BUSINESS_LIABILITIES_MSME, LOAN_TYPE);
                                } else if (updatedRawDataTable.getScreen_name().equalsIgnoreCase(SCREEN_NAME_HYPOTHECATION_DETAIL_MSME)) {
                                    getMetaDataByScreenName(SCREEN_NAME_HYPOTHECATION_MSME, LOAN_TYPE);

                                } else if (updatedRawDataTable.getScreen_name().equalsIgnoreCase(SCREEN_NAME_HOUSE_ASSETS_BUILDING_DETAIL_MSME)
                                        || updatedRawDataTable.getScreen_name().equalsIgnoreCase(SCREEN_NAME_HOUSE_ASSETS_LAND_DETAIL_MSME)
                                        || updatedRawDataTable.getScreen_name().equalsIgnoreCase(SCREEN_NAME_HOUSE_ASSETS_OPEN_PLOT_DETAIL_MSME)
                                        || updatedRawDataTable.getScreen_name().equalsIgnoreCase(SCREEN_NAME_HOUSE_ASSETS_TWO_WHEELER_DETAIL_MSME)
                                        || updatedRawDataTable.getScreen_name().equalsIgnoreCase(SCREEN_NAME_HOUSE_ASSETS_CAR_DETAIL_MSME)
                                        || updatedRawDataTable.getScreen_name().equalsIgnoreCase(SCREEN_NAME_HOUSE_ASSETS_VEHICLES_DETAIL_MSME)
                                        || updatedRawDataTable.getScreen_name().equalsIgnoreCase(SCREEN_NAME_HOUSE_ASSETS_PLANT_MACHINERY_DETAIL_MSME)
                                        || updatedRawDataTable.getScreen_name().equalsIgnoreCase(SCREEN_NAME_HOUSE_ASSETS_UTILITIES_DETAIL_MSME)
                                        || updatedRawDataTable.getScreen_name().equalsIgnoreCase(SCREEN_NAME_HOUSE_ASSETS_LIVESTOCK_DETAIL_MSME)
                                        || updatedRawDataTable.getScreen_name().equalsIgnoreCase(SCREEN_NAME_HOUSE_ASSETS_EQUIPMENT_DETAIL_MSME)
                                        || updatedRawDataTable.getScreen_name().equalsIgnoreCase(SCREEN_NAME_HOUSE_ASSETS_TELEVISION_DETAIL_MSME)
                                        || updatedRawDataTable.getScreen_name().equalsIgnoreCase(SCREEN_NAME_HOUSE_ASSETS_OTHERS_DETAIL_MSME)
                                        || updatedRawDataTable.getScreen_name().equalsIgnoreCase(SCREEN_NAME_HOUSE_ASSETS_FURNITURE_DETAIL_MSME)
                                        || updatedRawDataTable.getScreen_name().equalsIgnoreCase(SCREEN_NAME_HOUSE_ASSETS_COMMERCIAL_VEHICLE_DETAIL_MSME)
                                ) {
                                    getMetaDataByScreenName(SCREEN_NAME_HOUSE_ASSETS_MSME, LOAN_TYPE);
                                } else if (updatedRawDataTable.getScreen_name().equalsIgnoreCase(SCREEN_NAME_BUSINESS_ASSETS_BUILDING_DETAIL_MSME)
                                        || updatedRawDataTable.getScreen_name().equalsIgnoreCase(SCREEN_NAME_BUSINESS_ASSETS_LAND_DETAIL_MSME)
                                        || updatedRawDataTable.getScreen_name().equalsIgnoreCase(SCREEN_NAME_BUSINESS_ASSETS_OPEN_PLOT_DETAIL_MSME)
                                        || updatedRawDataTable.getScreen_name().equalsIgnoreCase(SCREEN_NAME_BUSINESS_ASSETS_TWO_WHEELER_DETAIL_MSME)
                                        || updatedRawDataTable.getScreen_name().equalsIgnoreCase(SCREEN_NAME_BUSINESS_ASSETS_CAR_DETAIL_MSME)
                                        || updatedRawDataTable.getScreen_name().equalsIgnoreCase(SCREEN_NAME_BUSINESS_ASSETS_VEHICLES_DETAIL_MSME)
                                        || updatedRawDataTable.getScreen_name().equalsIgnoreCase(SCREEN_NAME_BUSINESS_ASSETS_PLANT_MACHINERY_DETAIL_MSME)
                                        || updatedRawDataTable.getScreen_name().equalsIgnoreCase(SCREEN_NAME_BUSINESS_ASSETS_UTILITIES_DETAIL_MSME)
                                        || updatedRawDataTable.getScreen_name().equalsIgnoreCase(SCREEN_NAME_BUSINESS_ASSETS_LIVESTOCK_DETAIL_MSME)
                                        || updatedRawDataTable.getScreen_name().equalsIgnoreCase(SCREEN_NAME_BUSINESS_ASSETS_EQUIPMENT_DETAIL_MSME)
                                        || updatedRawDataTable.getScreen_name().equalsIgnoreCase(SCREEN_NAME_BUSINESS_ASSETS_TELEVISION_DETAIL_MSME)
                                        || updatedRawDataTable.getScreen_name().equalsIgnoreCase(SCREEN_NAME_BUSINESS_ASSETS_OTHERS_DETAIL_MSME)
                                        || updatedRawDataTable.getScreen_name().equalsIgnoreCase(SCREEN_NAME_BUSINESS_ASSETS_FURNITURE_DETAIL_MSME)
                                        || updatedRawDataTable.getScreen_name().equalsIgnoreCase(SCREEN_NAME_BUSINESS_ASSETS_COMMERCIAL_VEHICLE_DETAIL_MSME)

                                        || updatedRawDataTable.getScreen_name().equalsIgnoreCase(SCREEN_NAME_CASH_BUSINESS_ASSETS_MSME)
                                        || updatedRawDataTable.getScreen_name().equalsIgnoreCase(SCREEN_NAME_SAVINGS_CHITS_BUSINESS_ASSETS_MSME)
                                        || updatedRawDataTable.getScreen_name().equalsIgnoreCase(SCREEN_NAME_DEPOSITS_BUSINESS_ASSETS_BONDS_MSME)
                                        || updatedRawDataTable.getScreen_name().equalsIgnoreCase(SCREEN_NAME_INSURANCE_BUSINESS_ASSETS_MSME)
                                        || updatedRawDataTable.getScreen_name().equalsIgnoreCase(SCREEN_NAME_INVENTORY_BUSINESS_ASSETS_MSME)
                                        || updatedRawDataTable.getScreen_name().equalsIgnoreCase(SCREEN_NAME_RECEIVABLES_BUSINESS_ASSETS_MSME)
                                ) {
                                    getMetaDataByScreenName(SCREEN_NAME_BUSINESS_ASSETS_MSME, LOAN_TYPE);
                                } else if (updatedRawDataTable.getScreen_name().equalsIgnoreCase(SCREEN_NAME_MORTGAGE_BUILDING_MSME)
                                        || updatedRawDataTable.getScreen_name().equalsIgnoreCase(SCREEN_NAME_MORTGAGE_LAND_MSME)
                                        || updatedRawDataTable.getScreen_name().equalsIgnoreCase(SCREEN_NAME_MORTGAGE_OPEN_PLOT_MSME)
                                ) {
                                    getMetaDataByScreenName(SCREEN_NAME_MORTGAGE_MSME, LOAN_TYPE);
                                }
                                // TODO: PARENT AND CHILD HAS SAME SCREEN
                                else if (updatedRawDataTable.getScreen_name().equalsIgnoreCase(SCREEN_NAME_APPLICANT_KYC)
                                        || updatedRawDataTable.getScreen_name().equalsIgnoreCase(SCREEN_NAME_CO_APPLICANT_KYC)
                                        || updatedRawDataTable.getScreen_name().equalsIgnoreCase(SCREEN_NAME_REFERENCE_CHECK)
                                        || updatedRawDataTable.getScreen_name().equalsIgnoreCase(SCREEN_NAME_REFERENCES)
                                        || updatedRawDataTable.getScreen_name().equalsIgnoreCase(SCREEN_NAME_GENERAL_INCOME)
                                        || updatedRawDataTable.getScreen_name().equalsIgnoreCase(SCREEN_NAME_OTHER_INCOME_SOURCE)
                                ) {
                                    getMetaDataByScreenName(updatedRawDataTable.getScreen_name(), LOAN_TYPE);
                                } else if (updatedRawDataTable.getScreen_name().equalsIgnoreCase(SCREEN_NAME_LIABILITIES)) {
                                    getMetaDataByScreenName(SCREEN_NAME_HOUSE_LIABILITIES, LOAN_TYPE);
                                } else if (updatedRawDataTable.getScreen_name().equalsIgnoreCase(SCREEN_NAME_PRODUCT)) {
                                    getMetaDataByScreenName(SCREEN_NAME_BUSINESS_ASSETS, LOAN_TYPE);
                                } else if (updatedRawDataTable.getScreen_name().equalsIgnoreCase(SCREEN_NAME_BUSINESS_DEBTS)) {
                                    getMetaDataByScreenName(SCREEN_NAME_BUSINESS_LIABILITIES, LOAN_TYPE);
                                } else if (updatedRawDataTable.getScreen_name().equalsIgnoreCase(SCREEN_NAME_ADVANCES)) {
                                    getMetaDataByScreenName(SCREEN_NAME_BUSINESS_LIABILITIES, LOAN_TYPE);
                                } else if (updatedRawDataTable.getScreen_name().equalsIgnoreCase(SCREEN_NAME_FAMILY_MEMBER_INCOME)) {
                                    getMetaDataByScreenName(SCREEN_NAME_HOUSE_INCOME, LOAN_TYPE);
                                } else {
                                    ChildFragment childFragment = (ChildFragment) LOSBaseFragment.this;
                                    childFragment.callCloseDialogFragment();
                                    FragmentToActivityInterface fragmentToActivityInterface = (FragmentToActivityInterface) getActivity();
                                    fragmentToActivityInterface.valueFromFragment(viewParametersList.get(0).getScreenID(), SCREEN_NAME, viewParametersList.get(0).getScreenName(), viewParametersList);

                                }

                            }
                        } else {
                            FragmentToActivityInterface fragmentToActivityInterface = (FragmentToActivityInterface) getActivity();
                            fragmentToActivityInterface.valueFromFragment(SCREEN_ID, SCREEN_NAME, "", null);
                        }

                        Toast.makeText(getContext(), "Data updated successfully", Toast.LENGTH_SHORT).show();
                    }
                }
            };
            viewModel.getUpdatedRawdataRow().observe(this, rawDataUpdateObserver);
        } catch (Exception ex) {
            ex.printStackTrace();
            appHelper.getDialogHelper().getLoadingDialog().closeDialog();
        }
    }


    public void setQRData(List<DynamicUITable> viewParametersList, AadharData aadharData) {
        try {
            String aadharNo_entered = "";
            String aadharNo_scanned = null;
            if (aadharData != null)
                aadharNo_scanned = aadharData.getUID();
            else {
                aadharData = new AadharData();
            }

            DynamicUITable dynamicUITableRef = viewParametersList.get(0);
            if (dynamicUITableRef != null) {

                if (dynamicUITableRef.getLoanType().equalsIgnoreCase(LOAN_NAME_JLG)
                        && (dynamicUITableRef.getScreenName().equalsIgnoreCase(SCREEN_NAME_PERSONAL_DETAIL)
                        || dynamicUITableRef.getScreenName().equalsIgnoreCase(SCREEN_NAME_NOMINEE_DETAIL))) {


                    for (DynamicUITable dynamicUITable : viewParametersList) {

                        if (dynamicUITable.getFieldTag().contains(TAG_NAME_KYC_TYPE_AADHAAR)) {
                            aadharNo_entered = dynamicUITable.getValue().toString();
                        }
                        if (aadharNo_scanned != null && aadharNo_scanned.length() == 4) {
                            aadharNo_entered = aadharNo_entered.length() >= 4 ? aadharNo_entered.substring(aadharNo_entered.length() - 4) : "";
                        }
                        // Validate aadhar no
                        Log.e("aadhar scanned " + aadharNo_scanned, "aadhar entered " + aadharNo_entered);
                        if ((aadharNo_entered.equalsIgnoreCase(aadharNo_scanned)) || aadharNo_scanned == null) {

                            if (dynamicUITableRef.getScreenName().equalsIgnoreCase(SCREEN_NAME_PERSONAL_DETAIL)) {

                                if (!TextUtils.isEmpty(aadharData.getGender()) && aadharData.getGender().equalsIgnoreCase("f")) {
                                    List<ParameterInfo> parameterInfoList = new ArrayList<>();
                                    parameterInfoList.add(new ParameterInfo(TAG_NAME_KYC_TYPE_AADHAAR, SCREEN_ID, "", true, true));
                                    EnableOrDisableByFieldNameInDB(parameterInfoList, viewParametersList);
                                    appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, "Gender should be Male, Please scan proper Aadhar card");
                                    break;
                                }
                            }

                            if (dynamicUITable.getFieldTag().contains(TAG_NAME_SPOUSE_NAME) || dynamicUITable.getFieldTag().contains(TAG_NAME_NOMINEE_FULL_NAME)) {
                                if (!TextUtils.isEmpty(aadharData.getName())) {
                                    //  dynamicUITable.setValue("Name");
                                    dynamicUITable.setValue(aadharData.getName());
                                    dynamicUITable.setEditable(false);
                                }
                                dynamicUITable.setVisibility(true);

                            }

                            if (dynamicUITable.getFieldTag().contains(TAG_NAME_NOMINEE_DATE_OF_BIRTH) || dynamicUITable.getFieldTag().equalsIgnoreCase(TAG_NAME_SPOUSE_DATE_OF_BIRTH)) {
                                if (!TextUtils.isEmpty(aadharData.getDOB())) {
                                    dynamicUITable.setValue(aadharData.getDOB());
                                    dynamicUITable.setEditable(false);

                                    try {
                                        String dobFromEkyc = aadharData.getDOB();
                                        String convertedDate = appHelper.convertDateToGivenDateFormat(dobFromEkyc, DATE_FORMAT_DD_MM_YYYY2, DATE_FORMAT_YYYY_MM_DD);
                                        if (!TextUtils.isEmpty(convertedDate)) {
                                            dynamicUITable.setValue(convertedDate);
                                        }

                                    } catch (Exception ex) {
                                        ex.printStackTrace();
                                    }
                                }
                                dynamicUITable.setVisibility(true);
                            }
                            if (dynamicUITable.getFieldTag().equalsIgnoreCase(TAG_NAME_NOMINEE_AGE) || dynamicUITable.getFieldTag().equalsIgnoreCase(TAG_NAME_SPOUSE_AGE)) {
                                if (!TextUtils.isEmpty(aadharData.getDOB())) {

                                    String dobFromEkyc = aadharData.getDOB();
                                    try {
                                        String convertedDate = appHelper.convertDateToGivenDateFormat(dobFromEkyc, DATE_FORMAT_DD_MM_YYYY2, DATE_FORMAT_YYYY_MM_DD);
                                        if (!TextUtils.isEmpty(convertedDate)) {
                                            dobFromEkyc = convertedDate;
                                        }
                                    } catch (Exception ex) {
                                        ex.printStackTrace();
                                    }

                                    int age = appHelper.getAgeBasedOnDOB(dobFromEkyc);
                                    dynamicUITable.setValue(String.valueOf(age));
                                    dynamicUITable.setEditable(false);
                                }
                                dynamicUITable.setVisibility(true);
                            }
                        } else if (!aadharNo_entered.isEmpty()) {
                            //   Toast.makeText(getContext(), "Aadhar number mismatch", Toast.LENGTH_SHORT).show();
                            appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, "Entered Aadhar number not matched with Scanned QR code");
                        }

                    }

                } else {
                    for (DynamicUITable dynamicUITable : viewParametersList) {

                        // TODO: ALL HARDCODED VALUES ( note : contains)

                        if (dynamicUITable.getFieldTag().contains(TAG_NAME_KYC_TYPE_AADHAAR)) {
                            aadharNo_entered = dynamicUITable.getValue().toString();
                        }
                        if (aadharNo_scanned != null && aadharNo_scanned.length() == 4) {
                            aadharNo_entered = aadharNo_entered.length() >= 4 ? aadharNo_entered.substring(aadharNo_entered.length() - 4) : "";
                        }
                        // Validate aadhar no
                        Log.e("aadhar scanned " + aadharNo_scanned, "aadhar entered " + aadharNo_entered);
                        if ((aadharNo_entered.equalsIgnoreCase(aadharNo_scanned)) || aadharNo_scanned == null) {
                            if (dynamicUITable.getFieldTag().contains(TAG_NAME_CONTAINS_NAME)) {

                                if (!TextUtils.isEmpty(aadharData.getName())) {
                                    //  dynamicUITable.setValue("Name");
                                    dynamicUITable.setValue(aadharData.getName());
                                    dynamicUITable.setEditable(false);
                                }
                                dynamicUITable.setVisibility(true);

                            }
                            if (dynamicUITable.getFieldTag().contains(TAG_NAME_CONTAINS_DOB)) {
                                if (!TextUtils.isEmpty(aadharData.getDOB())) {
                                    dynamicUITable.setValue(aadharData.getDOB());
                                    dynamicUITable.setEditable(false);

                                    try {
                                        String dobFromEkyc = aadharData.getDOB();
                                        String convertedDate = appHelper.convertDateToGivenDateFormat(dobFromEkyc, DATE_FORMAT_DD_MM_YYYY2, DATE_FORMAT_YYYY_MM_DD);
                                        if (!TextUtils.isEmpty(convertedDate)) {
                                            dynamicUITable.setValue(convertedDate);
                                        }

                                    } catch (Exception ex) {
                                        ex.printStackTrace();
                                    }
                                }
                                dynamicUITable.setVisibility(true);
                            }
                            if (dynamicUITable.getFieldTag().contains(TAG_NAME_CONTAINS_AGE)) {
                                if (!TextUtils.isEmpty(aadharData.getDOB())) {

                                    String dobFromEkyc = aadharData.getDOB();
                                    try {
                                        String convertedDate = appHelper.convertDateToGivenDateFormat(dobFromEkyc, DATE_FORMAT_DD_MM_YYYY2, DATE_FORMAT_YYYY_MM_DD);
                                        if (!TextUtils.isEmpty(convertedDate)) {
                                            dobFromEkyc = convertedDate;
                                        }
                                    } catch (Exception ex) {
                                        ex.printStackTrace();
                                    }
                                    int age = appHelper.getAgeBasedOnDOB(dobFromEkyc);
                                    dynamicUITable.setValue(String.valueOf(age));
                                    dynamicUITable.setEditable(false);
                                }
                                dynamicUITable.setVisibility(true);
                            }
                            if (dynamicUITable.getFieldTag().equalsIgnoreCase(TAG_NAME_GENDER)) {

                                if (dynamicUITable.getLoanType().equalsIgnoreCase(LOAN_NAME_JLG)) {
                                    if (!TextUtils.isEmpty(aadharData.getGender())) {
                                        dynamicUITable.setValue(aadharData.getGender());
                                        String gender = aadharData.getGender();
                                        if (gender.equalsIgnoreCase("m")) {
                                            dynamicUITable.setValue("MALE");
                                        }
                                        if (gender.equalsIgnoreCase("f")) {
                                            dynamicUITable.setValue("FEMALE");
                                        }
                                        dynamicUITable.setEditable(false);
                                    } else {
                                        // TODO: Default FEMALE
                                        dynamicUITable.setValue("FEMALE");
                                        dynamicUITable.setEditable(false);
                                    }
                                } else if (!TextUtils.isEmpty(aadharData.getGender())) {
                                    dynamicUITable.setValue(aadharData.getGender());
                                    String gender = aadharData.getGender();
                                    if (gender.equalsIgnoreCase("m")) {
                                        dynamicUITable.setValue("MALE");
                                    }
                                    if (gender.equalsIgnoreCase("f")) {
                                        dynamicUITable.setValue("FEMALE");
                                    }
                                    dynamicUITable.setEditable(false);
                                }
                                dynamicUITable.setVisibility(true);

                            }
                            if (dynamicUITable.getFieldTag().equalsIgnoreCase(TAG_NAME_ADDRESS_LINE_1)) {

                                if (!TextUtils.isEmpty(aadharData.getAddress())) {
                                    // dynamicUITable.setValue("Address Line 1");
                                    dynamicUITable.setValue(aadharData.getAddress());
                                    dynamicUITable.setEditable(false);
                                }
//                                else if(dynamicUITable.getLoanType().equalsIgnoreCase(LOAN_NAME_JLG)){
//                                    dynamicUITable.setValue(".");
//                                    dynamicUITable.setEditable(false);
//                                }
                                dynamicUITable.setVisibility(true);

                            }
                            if (dynamicUITable.getFieldTag().equalsIgnoreCase(TAG_NAME_ADDRESS_LINE_2)) {
                                String address2 = aadharData.getLandmark() + ", " + aadharData.getLoc();

                                if (!TextUtils.isEmpty(aadharData.getLandmark())) {
                                    // dynamicUITable.setValue("Address Line 2");
                                    dynamicUITable.setValue(aadharData.getLandmark());
                                    dynamicUITable.setEditable(false);

                                }
                                if (dynamicUITable.getLoanType().equalsIgnoreCase(LOAN_NAME_JLG) && dynamicUITable.getScreenName().equalsIgnoreCase(SCREEN_NAME_APPLICANT_KYC)) {
                                    dynamicUITable.setIsRequired(VIEW_REQUIRED);
                                }
                                dynamicUITable.setVisibility(true);

                            }
//                            if (dynamicUITable.getFieldTag().equalsIgnoreCase(TAG_NAME_ADDRESS_LINE_3)) {
//
//                                if (!TextUtils.isEmpty(aadharData.getLoc()) && !(dynamicUITable.getLoanType().equalsIgnoreCase(LOAN_NAME_JLG))) {
//                                    // dynamicUITable.setValue("Address Line 2");
//                                    dynamicUITable.setValue(aadharData.getLoc());
//                                    dynamicUITable.setEditable(false);
//                                }
//                                dynamicUITable.setVisibility(true);
//
//                            }
                            if (dynamicUITable.getFieldTag().equalsIgnoreCase(TAG_NAME_PINCODE)) {

                                if (!TextUtils.isEmpty(aadharData.getPinCode())) {
                                    // dynamicUITable.setValue("500000");
                                    dynamicUITable.setValue(aadharData.getPinCode());
                                    dynamicUITable.setEditable(false);
                                }
                                dynamicUITable.setVisibility(true);

                            }
                            if (dynamicUITable.getFieldTag().equalsIgnoreCase(TAG_NAME_VILLAGE)) {

                                if (!TextUtils.isEmpty(aadharData.getVillage())) {
                                    // dynamicUITable.setValue("Village");
                                    dynamicUITable.setValue(aadharData.getVillage());
                                    dynamicUITable.setEditable(false);
                                }
                                dynamicUITable.setVisibility(true);

                            }
                            if (dynamicUITable.getFieldTag().equalsIgnoreCase(TAG_NAME_CITY)) {

                                if (!TextUtils.isEmpty(aadharData.getPostOffice())) {
                                    //  dynamicUITable.setValue("City");
                                    dynamicUITable.setValue(aadharData.getPostOffice());
                                    dynamicUITable.setEditable(false);
                                }
                                dynamicUITable.setVisibility(true);

                            }
                            if (dynamicUITable.getFieldTag().equalsIgnoreCase(TAG_NAME_DISTRICT)) {

                                if (!TextUtils.isEmpty(aadharData.getDistrict())) {
                                    //  dynamicUITable.setValue("District");
                                    dynamicUITable.setValue(aadharData.getDistrict());
                                    dynamicUITable.setEditable(false);
                                }
                                dynamicUITable.setVisibility(true);

                            }
                            if (dynamicUITable.getFieldTag().equalsIgnoreCase(TAG_NAME_STATE)) {

                                if (!TextUtils.isEmpty(aadharData.getState())) {
                                    //  dynamicUITable.setValue("State");
                                    dynamicUITable.setValue(aadharData.getState());
                                    dynamicUITable.setEditable(false);
                                }
                                dynamicUITable.setVisibility(true);

                            }
                            // TODO: 30-11-2019 new requirement
                            if (dynamicUITable.getFieldTag().equalsIgnoreCase(TAG_NAME_LANDMARK)) {
                                dynamicUITable.setVisibility(true);

                            }

                            if (dynamicUITable.getFieldTag().equalsIgnoreCase(TAG_NAME_KYC_ID)) {
                                //  dynamicUITable.setValue("602273125954");
                                if (!TextUtils.isEmpty(aadharData.getUID())) {
                                    dynamicUITable.setValue(aadharData.getUID());
                                }
                                dynamicUITable.setVisibility(true);
                                dynamicUITable.setEditable(false);
                            }

                            if (dynamicUITable.getFieldTag().equalsIgnoreCase(TAG_NAME_RE_ENTER_KYC_ID)) {
                                //   dynamicUITable.setValue("602273125954");
                                if (!TextUtils.isEmpty(aadharData.getUID())) {
                                    dynamicUITable.setValue(aadharData.getUID());
                                }
                                dynamicUITable.setVisibility(true);
                                dynamicUITable.setEditable(false);
                            }
                    /*if (dynamicUITable.getFieldTag().equalsIgnoreCase(TAG_NAME_GPS_LOCATION)) {
                        dynamicUITable.setValue(viewModel.getLocationTableLiveData().getValue().getLatitude());
                        dynamicUITable.setVisibility(true);
                        dynamicUITable.setEditable(false);
                    }*/
                            if (dynamicUITable.getFieldTag().equalsIgnoreCase(TAG_NAME_EKYC_BUTTON)) {
                                dynamicUITable.setVisibility(false);
                            }
                            if (dynamicUITable.getFieldTag().equalsIgnoreCase(TAG_NAME_QR_READING_BUTTON)) {
                                dynamicUITable.setVisibility(false);
                            }
                            if (dynamicUITable.getFieldTag().equalsIgnoreCase(TAG_NAME_SAVE_BUTTON)) {
                                dynamicUITable.setVisibility(false);
                            }
                            if (dynamicUITable.getFieldTag().equalsIgnoreCase(TAG_NAME_ADD_ANOTHER_KYC_PLUS_BUTTON)) {
                                dynamicUITable.setVisibility(true);
                            }
                            if (dynamicUITable.getFieldTag().equalsIgnoreCase(TAG_NAME_KYC_TYPE)) {
                                dynamicUITable.setVisibility(true);
                                dynamicUITable.setEditable(false);
                            }
                        } else if (!aadharNo_entered.isEmpty()) {
                            //   Toast.makeText(getContext(), "Aadhar number mismatch", Toast.LENGTH_SHORT).show();
                            appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, "Entered Aadhar number not matched with Scanned QR code");
                        }
                    }
                }
            }
            updateDynamicUITable(viewParametersList, SCREEN_ID);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void setEkycDataFromServer(String aadhaar, List<DynamicUITable> viewParametersList, ApiResponse ekycData) {
        try {
            String aadharNo_entered = "";
            String aadharNo_scanned = null;
            if (ekycData != null) {
                aadharNo_scanned = aadhaar;
            } else {
                ekycData = new ApiResponse();
            }

            for (DynamicUITable dynamicUITable : viewParametersList) {

                // TODO: ALL HARDCODED VALUES ( note : contains)

                if (dynamicUITable.getFieldTag().equalsIgnoreCase(TAG_NAME_IS_VERIFIED)) {

                    if (ekycData != null) {
                        // dynamicUITable.setValue("Address Line 1");
                        dynamicUITable.setValue(IS_VERIFIED_TRUE);
                        dynamicUITable.setEditable(false);
                    }
                    dynamicUITable.setVisibility(false);

                }

                if (dynamicUITable.getFieldTag().contains(TAG_NAME_KYC_TYPE_AADHAAR)) {
                    aadharNo_entered = dynamicUITable.getValue().toString();
                }
                // Validate aadhar no
                if ((aadharNo_entered.equalsIgnoreCase(aadharNo_scanned)) || aadharNo_scanned == null) {
                    if (dynamicUITable.getFieldTag().contains(TAG_NAME_CONTAINS_NAME)) {

                        if (!TextUtils.isEmpty(ekycData.getName())) {
                            //  dynamicUITable.setValue("Name");
                            dynamicUITable.setValue(ekycData.getName());
                            dynamicUITable.setEditable(false);
                        }
                        dynamicUITable.setVisibility(true);

                    }
                    if (dynamicUITable.getFieldTag().contains(TAG_NAME_CONTAINS_DOB)) {
                        if (!TextUtils.isEmpty(ekycData.getDob())) {
                            dynamicUITable.setValue(ekycData.getDob());
                            dynamicUITable.setEditable(false);

                            try {
                                String dobFromEkyc = ekycData.getDob();
                                String convertedDate = appHelper.convertDateToGivenDateFormat(dobFromEkyc, DATE_FORMAT_DD_MM_YYYY2, DATE_FORMAT_YYYY_MM_DD);
                                if (!TextUtils.isEmpty(convertedDate)) {
                                    dynamicUITable.setValue(convertedDate);
                                }

                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }
                        dynamicUITable.setVisibility(true);
                    }
                    if (dynamicUITable.getFieldTag().contains(TAG_NAME_CONTAINS_AGE)) {
                        if (!TextUtils.isEmpty(ekycData.getDob())) {

                            String dobFromEkyc = ekycData.getDob();
                            try {
                                String convertedDate = appHelper.convertDateToGivenDateFormat(dobFromEkyc, DATE_FORMAT_DD_MM_YYYY2, DATE_FORMAT_YYYY_MM_DD);
                                if (!TextUtils.isEmpty(convertedDate)) {
                                    dobFromEkyc = convertedDate;
                                }
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                            int age = appHelper.getAgeBasedOnDOB(dobFromEkyc);
                            dynamicUITable.setValue(String.valueOf(age));
                            dynamicUITable.setEditable(false);
                        }
                        dynamicUITable.setVisibility(true);
                    }
                    if (dynamicUITable.getFieldTag().equalsIgnoreCase(TAG_NAME_GENDER)) {

                        if (!TextUtils.isEmpty(ekycData.getGender())) {
                            //  dynamicUITable.setValue("MALE");
                            dynamicUITable.setValue(ekycData.getGender());
                            String gender = ekycData.getGender();
                            if (gender.equalsIgnoreCase("m")) {
                                dynamicUITable.setValue("MALE");
                            }
                            if (gender.equalsIgnoreCase("f")) {
                                dynamicUITable.setValue("FEMALE");
                            }
                            dynamicUITable.setEditable(false);
                        }
                        dynamicUITable.setVisibility(true);

                    }
                    if (dynamicUITable.getFieldTag().equalsIgnoreCase(TAG_NAME_ADDRESS_LINE_1)) {
                        String address1 = ekycData.getBuilding() + ", " + ekycData.getStreet();
                        if (!TextUtils.isEmpty(address1)) {
                            dynamicUITable.setValue(address1);
                            dynamicUITable.setEditable(false);
                        }
//                        else if(dynamicUITable.getLoanType().equalsIgnoreCase(LOAN_NAME_JLG)){
//                            dynamicUITable.setValue(".");
//                            dynamicUITable.setEditable(false);
//                        }
                        dynamicUITable.setVisibility(true);

                    }
                    if (dynamicUITable.getFieldTag().equalsIgnoreCase(TAG_NAME_ADDRESS_LINE_2)) {
                        String address2 = ekycData.getArea() + ", " + ekycData.getCity() + ", " + ekycData.getDistrict();
                        if (!TextUtils.isEmpty(address2)) {
                            dynamicUITable.setValue(address2);
                            dynamicUITable.setEditable(false);
                        }
                        dynamicUITable.setVisibility(true);

                    }
//                    if (dynamicUITable.getFieldTag().equalsIgnoreCase(TAG_NAME_ADDRESS_LINE_3)) {
//                        String address2 = ekycData.getState() + ", " + ekycData.getCountry();
//                        if (!TextUtils.isEmpty(address2) && !(dynamicUITable.getLoanType().equalsIgnoreCase(LOAN_NAME_JLG))) {
//                            // dynamicUITable.setValue("Address Line 2");
//                            dynamicUITable.setValue(address2);
//                            dynamicUITable.setEditable(false);
//                        }
//                        dynamicUITable.setVisibility(true);
//
//                    }
                    if (dynamicUITable.getFieldTag().equalsIgnoreCase(TAG_NAME_PINCODE)) {

                        if (!TextUtils.isEmpty(ekycData.getPin())) {
                            // dynamicUITable.setValue("500000");
                            dynamicUITable.setValue(ekycData.getPin());
                            dynamicUITable.setEditable(false);
                        }
                        dynamicUITable.setVisibility(true);

                    }
                    if (dynamicUITable.getFieldTag().equalsIgnoreCase(TAG_NAME_VILLAGE)) {

                        if (!TextUtils.isEmpty(ekycData.getArea())) {
                            // dynamicUITable.setValue("Village");
                            dynamicUITable.setValue(ekycData.getArea());
                            dynamicUITable.setEditable(false);
                        }
                        dynamicUITable.setVisibility(true);

                    }
                    if (dynamicUITable.getFieldTag().equalsIgnoreCase(TAG_NAME_CITY)) {

                        if (!TextUtils.isEmpty(ekycData.getCity())) {
                            //  dynamicUITable.setValue("City");
                            dynamicUITable.setValue(ekycData.getCity());
                            dynamicUITable.setEditable(false);
                        }
                        dynamicUITable.setVisibility(true);

                    }
                    if (dynamicUITable.getFieldTag().equalsIgnoreCase(TAG_NAME_DISTRICT)) {

                        if (!TextUtils.isEmpty(ekycData.getDistrict())) {
                            //  dynamicUITable.setValue("District");
                            dynamicUITable.setValue(ekycData.getDistrict());
                            dynamicUITable.setEditable(false);
                        }
                        dynamicUITable.setVisibility(true);

                    }
                    if (dynamicUITable.getFieldTag().equalsIgnoreCase(TAG_NAME_STATE)) {

                        if (!TextUtils.isEmpty(ekycData.getState())) {
                            //  dynamicUITable.setValue("State");
                            dynamicUITable.setValue(ekycData.getState());
                            dynamicUITable.setEditable(false);
                        }
                        dynamicUITable.setVisibility(true);

                    }
                    if (dynamicUITable.getFieldTag().equalsIgnoreCase(TAG_NAME_KYC_ID)) {
                        //  dynamicUITable.setValue("602273125954");
                        if (!TextUtils.isEmpty(aadhaar)) {
                            dynamicUITable.setValue(aadhaar);
                        }
                        dynamicUITable.setVisibility(true);
                        dynamicUITable.setEditable(false);
                    }
                    if (dynamicUITable.getFieldTag().equalsIgnoreCase(TAG_NAME_RE_ENTER_KYC_ID)) {
                        //   dynamicUITable.setValue("602273125954");
                        if (!TextUtils.isEmpty(aadhaar)) {
                            dynamicUITable.setValue(aadhaar);
                        }
                        dynamicUITable.setVisibility(true);
                        dynamicUITable.setEditable(false);
                    }
                    /*if (dynamicUITable.getFieldTag().equalsIgnoreCase(TAG_NAME_GPS_LOCATION)) {
                        dynamicUITable.setValue(viewModel.getLocationTableLiveData().getValue().getLatitude());
                        dynamicUITable.setVisibility(true);
                        dynamicUITable.setEditable(false);
                    }*/
                    if (dynamicUITable.getFieldTag().equalsIgnoreCase(TAG_NAME_EKYC_BUTTON)) {
                        dynamicUITable.setVisibility(false);
                    }
                    if (dynamicUITable.getFieldTag().equalsIgnoreCase(TAG_NAME_QR_READING_BUTTON)) {
                        dynamicUITable.setVisibility(false);
                    }

                    if (dynamicUITable.getFieldTag().equalsIgnoreCase(TAG_NAME_ADD_ANOTHER_KYC_PLUS_BUTTON)) {
                        dynamicUITable.setVisibility(true);
                    }
                    if (dynamicUITable.getFieldTag().equalsIgnoreCase(TAG_NAME_KYC_TYPE)) {
                        dynamicUITable.setVisibility(true);
                        dynamicUITable.setEditable(false);
                    }
                } else if (!aadharNo_entered.isEmpty()) {
                    //   Toast.makeText(getContext(), "Aadhar number mismatch", Toast.LENGTH_SHORT).show();
                    appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, "Entered Aadhar number not matched with Scanned QR code");
                }
            }
            updateDynamicUITable(viewParametersList, SCREEN_ID);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void EnableOrDisableByLooping(List<DynamicUITable> viewParametersList, String tagName, boolean visibility) {
        try {
            for (DynamicUITable dynamicUITable : viewParametersList) {
                if (dynamicUITable.getFieldTag().equalsIgnoreCase(tagName)) {
                    dynamicUITable.setVisibility(visibility);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    private void setAllEditable(List<DynamicUITable> dynamicUITableList, boolean editable) {
        try {
            for (DynamicUITable dynamicUITable : dynamicUITableList) {
                dynamicUITable.setEditable(editable);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void setValueByLoopingDynamicUI(List<View> dynamicViews, String value, String nameContains,
                                            List<DynamicUITable> dynamicUITableList) {
        try {
            for (View view : dynamicViews) {
                Log.d("id", "GET TAG ====>" + view.getTag());
                if (view instanceof XMLCustomTIL && view.getTag() != null) {
                    DynamicUITable viewParameters = (DynamicUITable) ((XMLCustomTIL) view).getTag();
                    if (viewParameters.getFieldTag().equalsIgnoreCase(nameContains)
                            && !viewParameters.getFieldTag().equalsIgnoreCase(TAG_NAME_VILLAGE)) {
                        ((XMLCustomTIL) view).getEditText().setText(value);
                        ((XMLCustomTIL) view).getEditText().setEnabled(false);
                        DynamicUITable dynamicUITable = getObjectByFieldName(viewParameters.getFieldTag(), dynamicUITableList);
                        if (dynamicUITable != null) {
                            dynamicUITable.setValue(value);
                            dynamicUITable.setEditable(false);
                        }
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void setValueByLoopingDynamicUI(List<View> dynamicViews, String value, String nameContains) {
        try {
            for (View view : dynamicViews) {
                Log.d("id", "GET TAG ====>" + view.getTag());
                if (view instanceof XMLCustomTIL && view.getTag() != null) {
                    DynamicUITable viewParameters = (DynamicUITable) ((XMLCustomTIL) view).getTag();
                    if (viewParameters.getFieldTag().equalsIgnoreCase(nameContains)
                            && !viewParameters.getFieldTag().equalsIgnoreCase(TAG_NAME_VILLAGE)) {
                        ((XMLCustomTIL) view).getEditText().setText(value);
                        ((XMLCustomTIL) view).getEditText().setEnabled(false);
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    public String getSubmittedValuesFromUI(DynamicUITable dynamicUITable, List<DynamicUITable> dynamicUITableList) {
        JSONObject jsonObject = new JSONObject();
        try {
            for (View view : dynamicViews) {
                Log.d("id", "GET TAG ====>" + view.getTag());
                DynamicUITable dynamicUITableFromView = (DynamicUITable) view.getTag();
                if (dynamicUITableFromView != null) {
                    if (dynamicUITableFromView.getFieldTag().equalsIgnoreCase(dynamicUITable.getFieldTag())) {
                        break;
                    }
                    if (dynamicUITableFromView.isVisibility()) {
                        if (!TextUtils.isEmpty(dynamicUITableFromView.getValue())) {

                            if (view instanceof XMLCustomTIL && view.getTag() != null) {
                                XMLCustomTIL xmlCustomTIL = (XMLCustomTIL) view;
                                DynamicUITable viewParameters = (DynamicUITable) xmlCustomTIL.getTag();
                                if (viewParameters.getFieldName().equalsIgnoreCase(TAG_NAME_KYC_ID)
                                        || viewParameters.getFieldName().equalsIgnoreCase(TAG_NAME_RE_ENTER_KYC_ID)
                                        || viewParameters.getFieldName().equalsIgnoreCase(FIELD_NAME_PERMANENT_KYC_ID)
                                        || viewParameters.getFieldName().equalsIgnoreCase(FIELD_NAME_COMMUNICATION_KYC_ID)
                                        || viewParameters.getFieldName().equalsIgnoreCase(TAG_NAME_NOMINEE_KYC_ID)
                                ) {
                                    if (viewParameters.getFieldTag().equalsIgnoreCase(TAG_NAME_KYC_TYPE_AADHAAR)
                                            || viewParameters.getFieldTag().equalsIgnoreCase(TAG_NAME_KYC_TYPE_RE_ENTER_AADHAAR)) {
                                        // TODO: ONLY FOR AADHAAR WE NEED TO GET VALUE FROM OPTIONAL COLUMN ( IT CONTAINS AADHAAR VAULT REF KEY )
                                        if (!viewParameters.getLoanType().equalsIgnoreCase(LOAN_NAME_JLG) &&
                                                !TextUtils.isEmpty(viewParameters.getOptional())) {
                                            // TODO: Not for JLG
                                            // TODO: this is for to get aadhaar vault id tvName optional field
                                            jsonObject.put(viewParameters.getFieldName().toUpperCase(), viewParameters.getOptional());
                                        } else {
                                            jsonObject.put(viewParameters.getFieldName().toUpperCase(), xmlCustomTIL.getEditText().getText());
                                        }
                                    } else {
                                        jsonObject.put(viewParameters.getFieldName().toUpperCase(), xmlCustomTIL.getEditText().getText());
                                    }
                                } else {
                                    jsonObject.put(viewParameters.getFieldTag(), xmlCustomTIL.getEditText().getText());
                                }
                            } else if (view instanceof XMLCustomSpinner && view.getTag() != null) {
                                XMLCustomSpinner xmlCustomSpinner = (XMLCustomSpinner) view;
                                if (xmlCustomSpinner.getSelectedItemPosition() > 0) {
                                    DynamicUITable viewParameters = (DynamicUITable) xmlCustomSpinner.getTag();
                                    jsonObject.put(viewParameters.getFieldTag(), xmlCustomSpinner.getSelectedItem().toString());
                                }/*else {
                                    DynamicUITable viewParameters = (DynamicUITable) xmlCustomSpinner.getTag();
                                    jsonObject.put(viewParameters.getFieldTag(), "");
                                }*/
                            } else if (view instanceof XmlCustomRadioGroup && view.getTag() != null) {
                                XmlCustomRadioGroup xmlCustomRadioGroup = (XmlCustomRadioGroup) view;
                                DynamicUITable viewParameters = (DynamicUITable) xmlCustomRadioGroup.getTag();
                                jsonObject.put(viewParameters.getFieldTag(), appHelper.getSelectedRadioButtonItem(xmlCustomRadioGroup, xmlCustomRadioGroup.getCheckedRadioButtonId()));
                            } else if (view instanceof XmlCustomCheckBox && view.getTag() != null) {
                                // TODO: Need to check this condition for multiple checkbox selection
                                XmlCustomCheckBox xmlCustomCheckBox = (XmlCustomCheckBox) view;
                                DynamicUITable viewParameters = (DynamicUITable) xmlCustomCheckBox.getTag();
                                jsonObject.put(viewParameters.getFieldTag(), viewParameters.getValue());
                            } else {
                                // TODO: For remaining
                                DynamicUITable viewParameters = (DynamicUITable) view.getTag();
                                if (viewParameters != null && !TextUtils.isEmpty(viewParameters.getValue())) {
                                    jsonObject.put(viewParameters.getFieldTag(), viewParameters.getValue());
                                }
                            }
                        }
                    } else if (view instanceof XMLCustomView && view.getTag() != null) {
                        // TODO: View ( only for Module type & no of co applicant)
                        XMLCustomView xmlCustomView = (XMLCustomView) view;
                        DynamicUITable viewParameters = (DynamicUITable) xmlCustomView.getTag();
                        if (viewParameters.getFieldTag().equalsIgnoreCase(TAG_NAME_MODULE_TYPE)) {
                            if (viewParameters.getScreenName().equalsIgnoreCase(SCREEN_NAME_GENERAL_INCOME)
                                    || viewParameters.getScreenName().equalsIgnoreCase(SCREEN_NAME_OTHER_INCOME_SOURCE)) {
                                jsonObject.put(viewParameters.getFieldTag(), viewParameters.getValue());
                            } else {
                                jsonObject.put(viewParameters.getFieldTag(), viewParameters.getModuleType());
                            }
                        }

                        if (viewParameters.getFieldTag().equalsIgnoreCase(TAG_NAME_PROOF_TYPE)) {
                            DynamicUITable kycTypeObj = getObjectByTAG(TAG_NAME_KYC_TYPE, dynamicUITableList);
                            if (kycTypeObj != null && !TextUtils.isEmpty(kycTypeObj.getValue())) {
                                String kycType = kycTypeObj.getValue();
                                String proofType = getProofType(kycType);
                                jsonObject.put(viewParameters.getFieldTag(), proofType);
                            }
                        }

                    }
                }
            }
            Log.d(TAG, "Final Json ==> " + jsonObject.toString());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return jsonObject.toString();
    }


    public String getSubmittedValuesFromUI(List<DynamicUITable> dynamicUITableList) {
        JSONObject jsonObject = new JSONObject();
        try {
            for (View view : dynamicViews) {
                Log.d("id", "GET TAG ====>" + view.getTag());

                if (view instanceof XMLCustomTIL && view.getTag() != null) {
                    XMLCustomTIL xmlCustomTIL = (XMLCustomTIL) view;
                    DynamicUITable viewParameters = (DynamicUITable) xmlCustomTIL.getTag();
                    if (viewParameters.getFieldName().equalsIgnoreCase(TAG_NAME_KYC_ID)
                            || viewParameters.getFieldName().equalsIgnoreCase(TAG_NAME_RE_ENTER_KYC_ID)
                            || viewParameters.getFieldName().equalsIgnoreCase(FIELD_NAME_PERMANENT_KYC_ID)
                            || viewParameters.getFieldName().equalsIgnoreCase(FIELD_NAME_COMMUNICATION_KYC_ID)
                    ) {
                        jsonObject.put(viewParameters.getFieldName(), xmlCustomTIL.getEditText().getText());
                    } else {
                        jsonObject.put(viewParameters.getFieldTag(), xmlCustomTIL.getEditText().getText());
                    }
                } else if (view instanceof XMLCustomSpinner && view.getTag() != null) {
                    XMLCustomSpinner xmlCustomSpinner = (XMLCustomSpinner) view;
                    DynamicUITable viewParameters = (DynamicUITable) xmlCustomSpinner.getTag();
                    jsonObject.put(viewParameters.getFieldTag(), xmlCustomSpinner.getSelectedItem().toString());
                } else if (view instanceof XmlCustomRadioGroup && view.getTag() != null) {
                    XmlCustomRadioGroup xmlCustomRadioGroup = (XmlCustomRadioGroup) view;
                    DynamicUITable viewParameters = (DynamicUITable) xmlCustomRadioGroup.getTag();
                    jsonObject.put(viewParameters.getFieldTag(), appHelper.getSelectedRadioButtonItem(xmlCustomRadioGroup, xmlCustomRadioGroup.getCheckedRadioButtonId()));
                } else if (view instanceof XmlCustomCheckBox && view.getTag() != null) {
                    // TODO: Need to check this condition for multiple checkbox selection
                    XmlCustomCheckBox xmlCustomCheckBox = (XmlCustomCheckBox) view;
                    DynamicUITable viewParameters = (DynamicUITable) xmlCustomCheckBox.getTag();
                    jsonObject.put(viewParameters.getFieldTag(), viewParameters.getValue());
                } else if (view instanceof XMLCustomView && view.getTag() != null) {
                    // TODO: View ( only for Module type & no of co applicant)
                    XMLCustomView xmlCustomView = (XMLCustomView) view;
                    DynamicUITable viewParameters = (DynamicUITable) xmlCustomView.getTag();
                    if (viewParameters.getFieldTag().equalsIgnoreCase(TAG_NAME_MODULE_TYPE)) {
                        jsonObject.put(viewParameters.getFieldTag(), viewParameters.getModuleType());
                    }
                    if (viewParameters.getFieldTag().equalsIgnoreCase(TAG_NAME_PROOF_TYPE)) {
                        DynamicUITable kycTypeObj = getObjectByTAG(TAG_NAME_KYC_TYPE, dynamicUITableList);
                        if (kycTypeObj != null && !TextUtils.isEmpty(kycTypeObj.getValue())) {
                            String kycType = kycTypeObj.getValue();
                            String proofType = getProofType(kycType);
                            jsonObject.put(viewParameters.getFieldTag(), proofType);
                        }

                    }
                } else {
                    // TODO: ????????????
                    if (view.getTag() != null) {
                        DynamicUITable viewParameters = (DynamicUITable) view.getTag();
                        if (viewParameters != null && !TextUtils.isEmpty(viewParameters.getFieldTag())) {
                            jsonObject.put(viewParameters.getFieldTag(), viewParameters.getValue());
                        }
                    }
                }
            }
            Log.d(TAG, "Final Json ==> " + jsonObject.toString());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return jsonObject.toString();
    }


    public void addButton(List<DynamicUITable> dynamicUITableList, DynamicUITable dynamicUITable, final String btnLabel, final String btnType,
                          final ButtonClick buttonClick) {
        try {
            if (btnType.equalsIgnoreCase(FIELD_TYPE_PLUS_BUTTON)) { // TODO: PLUS BUTTON CLICK
                View btnView = LayoutInflater.from(getActivity()).inflate(R.layout.view_add_another, null);
                ImageButton imageButton = (ImageButton) btnView.findViewById(R.id.btn_common);
                TextView tvAddButtonLabel = (TextView) btnView.findViewById(R.id.tv_add_button_label);
                tvAddButtonLabel.setText(btnLabel);
                imageButton.setTag(dynamicUITable);
                imageButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view1) {
                        if (dynamicUITable.getFieldTag().equalsIgnoreCase(TAG_NAME_ADD_DIRECT_BUSINESS_EXPENSE)) {


                            updateDynamicUITable(dynamicUITableList, SCREEN_ID); // TODO : don't remove this , without updating value column queries wont work


                            removeAllChildFragments(ll.getId() + "");
                            initChild(LOAN_TYPE, CLIENT_ID, PROJECT_ID_LOS, PRODUCT_ID_MSME, SCREEN_N0_DIRECT_BUSINESS_EXPENSE_DETAIL_MSME,
                                    SCREEN_NAME_DIRECT_BUSINESS_EXPENSE_DETAIL_MSME, dynamicUITableList, null, dynamicUITableList.get(0).getCoRelationID());


//                            FragmentToActivityInterface fragmentToActivityInterface = (FragmentToActivityInterface) getActivity();
//                            fragmentToActivityInterface.valueFromFragment(SCREEN_ID, SCREEN_NAME_PRODUCT, null);
                        } else if (dynamicUITable.getFieldTag().equalsIgnoreCase(TAG_NAME_ADD_ANOTHER_BUSINESS_DEBTS)) {
                            updateDynamicUITable(dynamicUITableList, SCREEN_ID); // TODO : don't remove this , without updating value column queries wont work

                            removeAllChildFragments(ll.getId() + "");
                            initChild(LOAN_TYPE, CLIENT_ID, PROJECT_ID_LOS, PRODUCT_ID_MSME, SCREEN_N0_DEBTS_MSME,
                                    SCREEN_NAME_HOUSE_DEBTS, dynamicUITableList, null, dynamicUITableList.get(0).getCoRelationID());

                        } else
                            validateDynamicUI(buttonClick, getTempDynamicUIViews(btnLabel), dynamicUITableList);
                    }
                });
                if (dynamicUITable.isVisibility()) {
                    dynamicViews.add(imageButton);
                    addViewToParentLayout(btnView);
                }
            } else if (btnType.equalsIgnoreCase(FIELD_TYPE_ADD_BUTTON)) { // TODO: ADD BUTTON CLICK
                View btnView = LayoutInflater.from(getActivity()).inflate(R.layout.view_add_button, null);
                ImageButton imageButton = (ImageButton) btnView.findViewById(R.id.btn_common);
                TextView tvAddButtonLabel = (TextView) btnView.findViewById(R.id.tv_add_button_label);
                tvAddButtonLabel.setText(btnLabel);
                imageButton.setTag(dynamicUITable);
                imageButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view1) {
                        if (dynamicUITable.getFieldTag().equalsIgnoreCase(TAG_NAME_ADD_LIABILITY)) {

                            updateDynamicUITable(dynamicUITableList, SCREEN_ID);

                            removeAllChildFragments(ll.getId() + "");
                            initChild(LOAN_TYPE, CLIENT_ID, PROJECT_ID_LOS, PRODUCT_ID_IL, SCREEN_N0_LIABILITIES,
                                    SCREEN_NAME_LIABILITIES, dynamicUITableList, null
                                    , dynamicUITableList.get(0).getCoRelationID()); // TODO : don't remove this , without updating value column queries wont work

                        } else if (dynamicUITable.getFieldTag().equalsIgnoreCase(TAG_NAME_ADD_PRODUCT)) {

                            updateDynamicUITable(dynamicUITableList, SCREEN_ID); // TODO : don't remove this , without updating value column queries wont work

                            removeAllChildFragments(ll.getId() + "");
                            initChild(LOAN_TYPE, CLIENT_ID, PROJECT_ID_LOS, PRODUCT_ID_IL, SCREEN_N0_PRODUCT,
                                    SCREEN_NAME_PRODUCT, dynamicUITableList, null
                                    , dynamicUITableList.get(0).getCoRelationID());

                        } else if (dynamicUITable.getFieldTag().equalsIgnoreCase(TAG_NAME_ADD_ANOTHER_BUSINESS_DEBTS)) {

                            updateDynamicUITable(dynamicUITableList, SCREEN_ID); // TODO : don't remove this , without updating value column queries wont work

                            removeAllChildFragments(ll.getId() + "");
                            initChild(LOAN_TYPE, CLIENT_ID, PROJECT_ID_LOS, PRODUCT_ID_IL, SCREEN_N0_BUSINESS_DEBTS,
                                    SCREEN_NAME_BUSINESS_DEBTS, dynamicUITableList, null
                                    , dynamicUITableList.get(0).getCoRelationID());

                        } else if (dynamicUITable.getFieldTag().equalsIgnoreCase(TAG_NAME_ADD_ADVANCES)) {

                            updateDynamicUITable(dynamicUITableList, SCREEN_ID); // TODO : don't remove this `, without updating value column queries wont work

                            removeAllChildFragments(ll.getId() + "");
                            initChild(LOAN_TYPE, CLIENT_ID, PROJECT_ID_LOS, PRODUCT_ID_IL, SCREEN_N0_ADVANCES,
                                    SCREEN_NAME_ADVANCES, dynamicUITableList, null
                                    , dynamicUITableList.get(0).getCoRelationID());
                        } else if (dynamicUITable.getFieldTag().equalsIgnoreCase(TAG_NAME_ADD_FAMILY_MEMBER)) {
                            updateDynamicUITable(dynamicUITableList, SCREEN_ID); // TODO : don't remove this , without updating value column queries wont work

                            removeAllChildFragments(ll.getId() + "");
                            initChild(LOAN_TYPE, CLIENT_ID, PROJECT_ID_LOS, PRODUCT_ID_IL, SCREEN_N0_FAMILY_MEMBER_INCOME,
                                    SCREEN_NAME_FAMILY_MEMBER_INCOME, dynamicUITableList, null
                                    , dynamicUITableList.get(0).getCoRelationID());
                        } else if (dynamicUITable.getFieldTag().equalsIgnoreCase(TAG_NAME_ADD_PRODUCT_ESTIMATE)) {

                            updateDynamicUITable(dynamicUITableList, SCREEN_ID); // TODO : don't remove this , without updating value column queries wont work
                            removeAllChildFragments(ll.getId() + "");
                            initChild(LOAN_TYPE, CLIENT_ID, PROJECT_ID_LOS, PRODUCT_ID_IL, SCREEN_N0_ADD_PRODUCT_ESTIMATE,
                                    SCREEN_NAME_ADD_PRODUCT_ESTIMATE, dynamicUITableList, null
                                    , dynamicUITableList.get(0).getCoRelationID());
                        } else if (dynamicUITable.getFieldTag().equalsIgnoreCase(TAG_NAME_ADD_ADD_ACCOUNT_PAYABLE)) {
                            updateDynamicUITable(dynamicUITableList, SCREEN_ID); // TODO : don't remove this , without updating value column queries wont work

                            removeAllChildFragments(ll.getId() + "");
                            initChild(LOAN_TYPE, CLIENT_ID, PROJECT_ID_LOS, PRODUCT_ID_MSME, SCREEN_N0_ACCOUNT_PAYABLE_MSME,
                                    SCREEN_NAME_BUSINESS_ACCOUNT_PAYABLE, dynamicUITableList, null, dynamicUITableList.get(0).getCoRelationID());

                        } else if (dynamicUITable.getFieldTag().equalsIgnoreCase(TAG_NAME_ADD_BUSINESS_DEBTS)) {
                            updateDynamicUITable(dynamicUITableList, SCREEN_ID); // TODO : don't remove this , without updating value column queries wont work

                            removeAllChildFragments(ll.getId() + "");
                            initChild(LOAN_TYPE, CLIENT_ID, PROJECT_ID_LOS, PRODUCT_ID_MSME, SCREEN_N0_BUSINESS_DEBTS_MSME,
                                    SCREEN_NAME_BUSINESS_DEBTS_DETAIL_MSME, dynamicUITableList, null, dynamicUITableList.get(0).getCoRelationID());

                        } else if (dynamicUITable.getFieldTag().equalsIgnoreCase(TAG_NAME_ADD_ADVANCES_MSME)) {
                            updateDynamicUITable(dynamicUITableList, SCREEN_ID); // TODO : don't remove this , without updating value column queries wont work

                            removeAllChildFragments(ll.getId() + "");
                            initChild(LOAN_TYPE, CLIENT_ID, PROJECT_ID_LOS, PRODUCT_ID_MSME, SCREEN_N0_ADVANCES_DEBTS_MSME,
                                    SCREEN_NAME_ADVANCE_DETAIL__MSME, dynamicUITableList, null, dynamicUITableList.get(0).getCoRelationID());

                        } else if (LOSBaseFragment.this instanceof BusinessAssertsMsmeFragment) {

                            if (dynamicUITable.getFieldTag().equalsIgnoreCase(TAG_NAME_ADD_ANOTHER_FIXED_ASSET_IN_BUSINESS_ASSETS_MSME)) {

                                DynamicUITable assetTypeObj = getObjectByTAG(TAG_NAME_ASSET_TYPE_IN_BUSINESS_ASSETS_MSME, dynamicUITableList);

                                if (assetTypeObj != null && !TextUtils.isEmpty(assetTypeObj.getValue())) {
                                    // TODO: GET SCREEN NAME AND SCREEN NUMBER FOR FIXED ASSETS ( BUSINESS ASSETS )
                                    String[] screenNameAndNumber = getScreenNameAndNumberForBusinessAssets(assetTypeObj.getValue());
                                    String screenName = screenNameAndNumber[0]; // TODO: to get screen name
                                    String screenNumber = screenNameAndNumber[1]; // TODO: to get screen number
                                    if (!TextUtils.isEmpty(screenName) && !TextUtils.isEmpty(screenNumber)) {

                                        updateDynamicUITable(dynamicUITableList, SCREEN_ID);
                                        removeAllChildFragments(ll.getId() + "");
                                        initChild(LOAN_TYPE, CLIENT_ID, PROJECT_ID_LOS, PRODUCT_ID_MSME, screenNumber,
                                                screenName, dynamicUITableList, null
                                                , dynamicUITableList.get(0).getCoRelationID());
                                    }
                                } else {
                                    appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, "Please Select Asset Type");
                                }
                            } else if (dynamicUITable.getFieldTag().equalsIgnoreCase(TAG_NAME_ADD_CASH_IN_BUSINESS_ASSETS_MSME)) {

                                updateDynamicUITable(dynamicUITableList, SCREEN_ID);
                                removeAllChildFragments(ll.getId() + "");
                                initChild(LOAN_TYPE, CLIENT_ID, PROJECT_ID_LOS, PRODUCT_ID_MSME, SCREEN_N0_CASH_BUSINESS_ASSETS_MSME,
                                        SCREEN_NAME_CASH_BUSINESS_ASSETS_MSME, dynamicUITableList, null
                                        , dynamicUITableList.get(0).getCoRelationID());
                            } else if (dynamicUITable.getFieldTag().equalsIgnoreCase(TAG_NAME_ADD_SAVINGS_OR_CHITS_IN_BUSINESS_ASSETS_MSME)) {

                                updateDynamicUITable(dynamicUITableList, SCREEN_ID);
                                removeAllChildFragments(ll.getId() + "");
                                initChild(LOAN_TYPE, CLIENT_ID, PROJECT_ID_LOS, PRODUCT_ID_MSME, SCREEN_N0_SAVING_CHITS_BUSINESS_ASSETS_MSME,
                                        SCREEN_NAME_SAVINGS_CHITS_BUSINESS_ASSETS_MSME, dynamicUITableList, null
                                        , dynamicUITableList.get(0).getCoRelationID());
                            } else if (dynamicUITable.getFieldTag().equalsIgnoreCase(TAG_NAME_ADD_DEPOSITS_AND_BONDS_IN_BUSINESS_ASSETS_MSME)) {

                                updateDynamicUITable(dynamicUITableList, SCREEN_ID);
                                removeAllChildFragments(ll.getId() + "");
                                initChild(LOAN_TYPE, CLIENT_ID, PROJECT_ID_LOS, PRODUCT_ID_MSME, SCREEN_N0_DEPOSITS_BONDS_BUSINESS_ASSETS_MSME,
                                        SCREEN_NAME_DEPOSITS_BUSINESS_ASSETS_BONDS_MSME, dynamicUITableList, null
                                        , dynamicUITableList.get(0).getCoRelationID());
                            } else if (dynamicUITable.getFieldTag().equalsIgnoreCase(TAG_NAME_ADD_INSURANCE_IN_BUSINESS_ASSETS_MSME)) {

                                updateDynamicUITable(dynamicUITableList, SCREEN_ID);
                                removeAllChildFragments(ll.getId() + "");
                                initChild(LOAN_TYPE, CLIENT_ID, PROJECT_ID_LOS, PRODUCT_ID_MSME, SCREEN_N0_INSURANCE_BUSINESS_ASSETS_MSME,
                                        SCREEN_NAME_INSURANCE_BUSINESS_ASSETS_MSME, dynamicUITableList, null
                                        , dynamicUITableList.get(0).getCoRelationID());
                            } else if (dynamicUITable.getFieldTag().equalsIgnoreCase(TAG_NAME_ADD_INVENTORY_IN_BUSINESS_ASSETS_MSME)) {

                                updateDynamicUITable(dynamicUITableList, SCREEN_ID);
                                removeAllChildFragments(ll.getId() + "");
                                initChild(LOAN_TYPE, CLIENT_ID, PROJECT_ID_LOS, PRODUCT_ID_MSME, SCREEN_N0_INVENTORY_BUSINESS_ASSETS_MSME,
                                        SCREEN_NAME_INVENTORY_BUSINESS_ASSETS_MSME, dynamicUITableList, null
                                        , dynamicUITableList.get(0).getCoRelationID());
                            } else if (dynamicUITable.getFieldTag().equalsIgnoreCase(TAG_NAME_ADD_RECEIVABLES_IN_BUSINESS_ASSETS_MSME)) {

                                updateDynamicUITable(dynamicUITableList, SCREEN_ID);
                                removeAllChildFragments(ll.getId() + "");
                                initChild(LOAN_TYPE, CLIENT_ID, PROJECT_ID_LOS, PRODUCT_ID_MSME, SCREEN_N0_RECEIVABLES_BUSINESS_ASSETS_MSME,
                                        SCREEN_NAME_RECEIVABLES_BUSINESS_ASSETS_MSME, dynamicUITableList, null
                                        , dynamicUITableList.get(0).getCoRelationID());
                            }
                        } else {
                            validateDynamicUI(buttonClick, getTempDynamicUIViews(btnLabel), dynamicUITableList);
                        }
                    }
                });
                if (dynamicUITable.isVisibility()) {
                    dynamicViews.add(imageButton);
                    addViewToParentLayout(btnView);
                }
            } else {
                View btnView = LayoutInflater.from(getActivity()).inflate(R.layout.view_btn, null);
                Button button = (Button) btnView.findViewById(R.id.btn_common);
                button.setText(btnLabel);
                button.setTag(dynamicUITable);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view1) { // TODO: Save button click
                        if (dynamicUITable.getFieldTag().equalsIgnoreCase(FIELD_TYPE_ORAL_VERIFICATION_BUTTON)) {
                            if (buttonClick != null) {
                                buttonClick.onButtonClickSuccess();
                            }
                        } else {
                            validateDynamicUI(buttonClick, getTempDynamicUIViews(btnLabel), dynamicUITableList);
                        }
                    }
                });
                if (dynamicUITable.isVisibility()) {
                    dynamicViews.add(button);
                    addViewToParentLayout(btnView);
                }
            }

        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
        }
    }

    private List<View> getTempDynamicUIViews(String fieldName) {
        List<View> tempDynamicViews = new ArrayList<>();
        try {
            for (View view : dynamicViews) {
                DynamicUITable dynamicUITable = (DynamicUITable) view.getTag();
                if (dynamicUITable != null && !TextUtils.isEmpty(dynamicUITable.getFieldName())
                        && dynamicUITable.getFieldName().equalsIgnoreCase(fieldName)) {
                    break;
                } else {
                    tempDynamicViews.add(view);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return tempDynamicViews;
    }

    public void validateDynamicUI(ButtonClick buttonClick, List<View> dynamicViews, List<DynamicUITable> dynamicUITableList) {
        try {
            boolean isValid = true;

            for (int i = 0; i < dynamicViews.size(); i++) {
                View view = dynamicViews.get(i);
                DynamicUITable dynamicUITableFromUI = (DynamicUITable) view.getTag();
                Log.d("id", "GET TAG ====> " + dynamicUITableFromUI.getFieldName());
            }
            for (int i = 0; i < dynamicViews.size(); i++) {
                View view = dynamicViews.get(i);
                DynamicUITable dynamicUITableFromUI = (DynamicUITable) view.getTag();
                Log.d("id", "GET TAG ====> " + dynamicUITableFromUI.getFieldName());
//                                DynamicUITable tag=(DynamicUITable)view.getTag();
                if (view instanceof XMLCustomTIL && view.getTag() != null) {
                    DynamicUITable params = (DynamicUITable) ((XMLCustomTIL) view).getTag();
                    if (((XMLCustomTIL) view).isMandatory() && ((XMLCustomTIL) view).getVisibility() == View.VISIBLE) {
                        String text = ((XMLCustomTIL) view).getEditText().getText().toString().trim();
//                                        String displayName = params.getFieldName();
                        String error = "Please enter valid detail";
                        if (!TextUtils.isEmpty(params.getErrorMessage())) {
                            error = params.getErrorMessage();
                        }
                        if (TextUtils.isEmpty(text)) {
                            if (params.getScreenID().equalsIgnoreCase(SCREEN_N0_MANUFACTURING_OPERATING_EXPENSE)) {
                                //djjdd
                            } else if (params.getScreenID().equalsIgnoreCase(SCREEN_N0_HOUSE_EXPENSES)) {
                                //ggg
                            } else {
                                setError((XMLCustomTIL) view, error);
                                isValid = false;
                            }
                        } else if (params.getFieldName().contains(RE_ENTER_TAG)) {
                            View view2 = dynamicViews.get(i - 1);
                            String aboveValue = ((XMLCustomTIL) view2).getEditText().getText().toString().trim();
                            String reEnteredValue = ((XMLCustomTIL) view).getEditText().getText().toString().trim();
                            if (!TextUtils.isEmpty(aboveValue) &&
                                    !aboveValue.equalsIgnoreCase(reEnteredValue)) {
                                error = "Re enter correct value";
                                setError((XMLCustomTIL) view, error);
                                isValid = false;
                            }
                        }
                       /* if (params.getFieldName().contains("mobile") || params.getFieldName().contains("Mobile")) {
                            if (!appHelper.patternMatch(AppConstants.REGEX_PATTERN_MOBILE_NUMBER, text)) {
                                error = "Enter Valid Mobile";
                                setError((XMLCustomTIL) view, error);
                                isValid = false;
                            }
                        }*/
                        // TODO: newly added condition
                        if (!params.isValid()) {
                            if (!TextUtils.isEmpty(params.getErrorMessage())) {
                                ((XMLCustomTIL) view).setErrorMsg(params.getErrorMessage());
                                ((XMLCustomTIL) view).setHasValidInput(params.isValid());
                                isValid = false;
                            }
                        }

                        // TODO: 26-09-2019 added operation expense condition
                        if (params.getScreenID().equalsIgnoreCase(SCREEN_N0_MANUFACTURING_OPERATING_EXPENSE)) {
                            if (!TextUtils.isEmpty(params.getValue())) {
                                int iLength = Integer.parseInt(params.getMinLength());
                                if (params.getValue().length() < iLength) {
                                    if (!params.getValue().startsWith("0")) {
                                        isValid = false;
                                    }
                                }
                            }
                        }

                        if (params.getScreenID().equalsIgnoreCase(SCREEN_N0_HOUSE_EXPENSES)) {
                            if (!TextUtils.isEmpty(params.getValue())) {
                                int iLength = Integer.parseInt(params.getMinLength());
                                if (params.getValue().length() < iLength) {
                                    if (!params.getValue().startsWith("0")) {
                                        isValid = false;
                                    }
                                }
                            }
                        }

                        if (params.getFieldTag().equalsIgnoreCase(TAG_NAME_MOBILE)
                                || params.getFieldTag().equalsIgnoreCase(TAG_NAME_MOBILE_NO)
                                || params.getFieldTag().equalsIgnoreCase(TAG_NAME_CONTACT_NO)
                                || params.getFieldTag().equalsIgnoreCase(TAG_NAME_CONTACT_NO_1)
                                || params.getFieldTag().equalsIgnoreCase(TAG_NAME_RE_ENTER_CONTACT_NO_1)
                                || params.getFieldTag().equalsIgnoreCase(TAG_NAME_MOBILE_NUMBER)) {
                            if (!appHelper.patternMatch(AppConstants.REGEX_PATTERN_MOBILE_NUMBER, text)) {
//                                    error = context.getString(getIdentifier(tag + "_INVALID_ERROR"));
                                error = "Enter Valid Mobile Number";
                                setError((XMLCustomTIL) view, error);
                                isValid = false;
                            } else if (params.getFieldTag().equalsIgnoreCase(TAG_NAME_RE_ENTER_CONTACT_NO_1)) {
                                DynamicUITable contactNumber = getObjectByTAG(TAG_NAME_CONTACT_NO_1, dynamicUITableList);
                                if (contactNumber != null) {
                                    if (!TextUtils.isEmpty(contactNumber.getValue())) {
                                        double conNum = Double.parseDouble(contactNumber.getValue());
                                        double reConNum = Double.parseDouble(text);
                                        if (!(reConNum == conNum)) {
                                            error = "Mobile number and re entered mobile number should be same";
                                            setError((XMLCustomTIL) view, error);
                                            isValid = false;
                                        }
                                    } else {
                                        error = "Please enter mobile number first";
                                        setError((XMLCustomTIL) view, error);
                                        isValid = false;
                                    }
                                }
                            }
                        }
                        if (params.getFieldTag().equalsIgnoreCase(TAG_NAME_CONTACT_NO_2)) {
                            DynamicUITable contactNumber1 = getObjectByTAG(TAG_NAME_CONTACT_NO_1, dynamicUITableList);

                            if (contactNumber1 != null && !TextUtils.isEmpty(contactNumber1.getValue())) {

                                if (!appHelper.patternMatch(AppConstants.REGEX_PATTERN_MOBILE_NUMBER, text.toString())) {
//                                    error = context.getString(getIdentifier(tag + "_INVALID_ERROR"));
                                    error = params.getFieldTag() + " invalid";

                                } else if (contactNumber1.getValue().toString().equalsIgnoreCase(text.toString())) {
                                    error = "Both contacts should be different";
                                }


                            } else {
                                error = "Please enter Contact number 1";
                            }

                        }
                        if (params.getFieldTag().equalsIgnoreCase(TAG_NAME_RE_ENTER_CONTACT_NO_2)) {

                            DynamicUITable contactNumber2 = getObjectByTAG(TAG_NAME_CONTACT_NO_2, dynamicUITableList);
                            DynamicUITable contactNumber1 = getObjectByTAG(TAG_NAME_CONTACT_NO_1, dynamicUITableList);
                            if (contactNumber1 != null && !TextUtils.isEmpty(contactNumber1.getValue())) {
                                if (contactNumber2 != null) {
                                    if (!TextUtils.isEmpty(contactNumber2.getValue())) {
                                        if (!(contactNumber2.getValue().equalsIgnoreCase(text))) {
                                            error = "Mobile number and re entered mobile number should be same";
                                            isValid = false;
                                        } else {
                                            //isValid = true;
                                        }
                                    } else {
                                        error = "Please enter mobile number first";
                                        isValid = false;
                                    }
                                } else {
                                    //isValid = true;
                                }
                            } else {

                                error = "Please enter Contact number 1";
                            }

                        }
//                        if (!TextUtils.isEmpty(params.getHint()) && params.getHint().contains("PAN")) {
                        if (params.getFieldTag().equalsIgnoreCase("PANCARD")
                                || params.getFieldTag().equalsIgnoreCase(TAG_NAME_KYC_TYPE_PANCARD)
                                || params.getFieldTag().equalsIgnoreCase(TAG_NAME_KYC_TYPE_RE_ENTER_PANCARD)
                        ) {
                            if (!appHelper.patternMatch(AppConstants.REGEX_PATTERN_PANCARD, text)) {
                                error = "Enter Valid PAN";
                                setError((XMLCustomTIL) view, error);
                                isValid = false;
                            }
                        }

                        if (params.getFieldTag().equalsIgnoreCase(TAG_NAME_NET_BUSINESS_INCOME)
                                || params.getFieldTag().equalsIgnoreCase(TAG_NAME_NET_BUSINESS_INCOME_NBI)) {
                            double netBusinessIncome = Double.parseDouble(text);
                            if (netBusinessIncome < 0) {
                                error = "Net Business Income should not be negative";
                                setError((XMLCustomTIL) view, error);
                                isValid = false;
                            }

                        }
                        if (params.getFieldTag().equalsIgnoreCase(TAG_NAME_GROSS_BUSINESS_INCOME)) {
                            double netBusinessIncome = Double.parseDouble(text);
                            if (netBusinessIncome < 0) {
                                error = "Gross Business Income should not be negative";
                                setError((XMLCustomTIL) view, error);
                                isValid = false;
                            }

                        }

                        if (params.getScreenName().equalsIgnoreCase(SCREEN_NAME_LEAD)
                                && params.getFieldTag().equalsIgnoreCase(TAG_NAME_LOAN_REQUESTED_AMOUNT)) {
                            double loanAmount = Double.parseDouble(text);
                            if (loanAmount < IL_MINIMUM_LOAN_AMOUNT) {
                                error = "Loan amount should be greater than " + IL_MINIMUM_LOAN_AMOUNT;
                                setError((XMLCustomTIL) view, error);
                                isValid = false;
                            }
                            if (loanAmount > IL_MAXIMUM_LOAN_AMOUNT) {
                                error = "Loan amount should be less than " + IL_MAXIMUM_LOAN_AMOUNT;
                                setError((XMLCustomTIL) view, error);
                                isValid = false;
                            }

                        }

                        if (params.getFieldTag().equalsIgnoreCase(TAG_NAME_KYC_TYPE_AADHAAR)
                                || params.getFieldTag().equalsIgnoreCase(TAG_NAME_KYC_TYPE_RE_ENTER_AADHAAR)) {
                            // TODO: ONLY FOR AADHAAR WE NEED TO GET VALUE FROM OPTIONAL COLUMN ( IT CONTAINS AADHAAR VAULT REF KEY )

                            if (params.getScreenName().equalsIgnoreCase(SCREEN_NAME_APPLICANT_KYC)
                                    || params.getScreenName().equalsIgnoreCase(SCREEN_NAME_CO_APPLICANT_KYC)) {
                                if (!TextUtils.isEmpty(params.getOptional())) {

                                    // TODO: this is for to get aadhaar vault id tvName optional field
                                    DynamicUITable kycObj = getObjectByTAG(TAG_NAME_KYC_TYPE_AADHAAR, dynamicUITableList);
                                    DynamicUITable reEnterKycObj = getObjectByTAG(TAG_NAME_KYC_TYPE_RE_ENTER_AADHAAR, dynamicUITableList);
                                    if (kycObj != null && reEnterKycObj != null && !TextUtils.isEmpty(kycObj.getOptional()) &&
                                            !TextUtils.isEmpty(kycObj.getOptional()) && kycObj.getOptional().equalsIgnoreCase(reEnterKycObj.getOptional())) {
                                        Log.d(TAG, "Aadhaar Vault Id & ReEntered Aadhaar Vault Id Is Same, NO ISSUE ");
                                    } else {
                                        error = "Aadhaar Vault Id & ReEntered Aadhaar Vault Id Should Be Same";
                                        setError((XMLCustomTIL) view, error);
                                        isValid = false;
                                    }
                                } else {
                                    error = "Aadhaar Vault Reference Key Is Empty";
                                    setError((XMLCustomTIL) view, error);
                                    isValid = false;
                                }
                            }
                        }

                        if (params.getFieldTag().equalsIgnoreCase(TAG_NAME_KYC_TYPE_PASSPORT)
                                || params.getFieldTag().equalsIgnoreCase(TAG_NAME_KYC_TYPE_RE_ENTER_PASSPORT)) {
                            if (!appHelper.patternMatch(AppConstants.REGEX_PATTERN_PASSPORT, text)) {
                                error = "Enter Valid passport";
                                setError((XMLCustomTIL) view, error);
                                isValid = false;
                            }
                        }
                        if (params.getFieldTag().equalsIgnoreCase("VOTERID")
                                || params.getFieldTag().equalsIgnoreCase(TAG_NAME_KYC_TYPE_VOTERID)
                                || params.getFieldTag().equalsIgnoreCase(TAG_NAME_KYC_TYPE_RE_ENTER_VOTERID)) {
                            if (text.length() < 10) {
                                error = "Enter Valid VoterId";
                                setError((XMLCustomTIL) view, error);
                                isValid = false;
                            }
                        }
                        if (params.getFieldTag().equalsIgnoreCase(TAG_NAME_CONTAINS_AGE) || params.getFieldTag().equalsIgnoreCase(TAG_NAME_GUARDIAN_AGE)) {
                            if (!TextUtils.isEmpty(text)) {
                                if (params.getLoanType().equalsIgnoreCase(LOAN_NAME_MSME)||params.getLoanType().equalsIgnoreCase(LOAN_NAME_AHL)||params.getLoanType().equalsIgnoreCase(LOAN_NAME_PHL)) {
                                    if (Integer.parseInt(text) < 0) {
                                        error = ERROR_MESSAGE_FOR_MIN_AGE_MSME;
                                        setError((XMLCustomTIL) view, error);
                                        isValid = false;
                                    } else if (Integer.parseInt(text) > 100) {
                                        error = ERROR_MESSAGE_FOR_MAX_AGE_MSME;
                                        setError((XMLCustomTIL) view, error);
                                        isValid = false;
                                    }
                                } else if (params.getLoanType().equalsIgnoreCase(LOAN_NAME_JLG)) {
//                                    ageValidationJLG(params,dynamicUITableList);
                                    if (Integer.parseInt(text) < 18) {
                                        error = ERROR_MESSAGE_FOR_MIN_AGE_JLG;
                                        setError((XMLCustomTIL) view, error);
                                        isValid = false;
                                    } else if (Integer.parseInt(text) > 55) {
                                        error = ERROR_MESSAGE_FOR_MAX_AGE_JLG;
                                        setError((XMLCustomTIL) view, error);
                                        isValid = false;
                                    }
                                } else {
                                    if (Integer.parseInt(text) < 21) {
                                        error = ERROR_MESSAGE_FOR_MIN_AGE_IL;
                                        setError((XMLCustomTIL) view, error);
                                        isValid = false;
                                    } else if (Integer.parseInt(text) > 58) {
                                        error = ERROR_MESSAGE_FOR_MAX_AGE_IL;
                                        setError((XMLCustomTIL) view, error);
                                        isValid = false;
                                    }
                                }
                            }
                        }
                        if (params.getFieldTag().equalsIgnoreCase(TAG_NAME_PINCODE)) {
                            if (!appHelper.patternMatch(AppConstants.REGEX_PATTERN_PINCODE, text)) {
                                error = "Enter Valid Pincode";
                                setError((XMLCustomTIL) view, error);
                                isValid = false;
                            }
                        }

                        //Added by Prasanna
                        if (params.getFieldTag().equalsIgnoreCase(TAG_NAME_ACCOUNT_NUMBER)) {
                            if (!appHelper.patternMatch(ACCOUNT_NUMBER_VALIDATION, text)) {
                                error = "Enter Valid Account Number";
                                setError((XMLCustomTIL) view, error);
                                isValid = false;
                            }
                        }
                        //Added by Prasanna
                        else if (params.getFieldTag().equalsIgnoreCase(TAG_NAME_RE_ENTER_ACCOUNT_NUMBER)) {
                            DynamicUITable accountNumber = getObjectByTAG(TAG_NAME_ACCOUNT_NUMBER, dynamicUITableList);
                            if (accountNumber != null) {
                                if (!TextUtils.isEmpty(accountNumber.getValue())) {
                                    double acNum = Double.parseDouble(accountNumber.getValue());
                                    double reAcc = Double.parseDouble(text);
                                    if (!(reAcc == acNum)) {
                                        error = "Account number and re account number should be same";
                                        setError((XMLCustomTIL) view, error);
                                        isValid = false;
                                    }
                                } else {
                                    error = "Please enter account number first";
                                    setError((XMLCustomTIL) view, error);
                                    isValid = false;
                                }
                            }
                        }


                        if (params.getFieldTag().equalsIgnoreCase(TAG_NAME_IFSC_CODE)) {
                            if (!appHelper.patternMatch(AppConstants.IFSC_CODE_VALIDATION, text)) {
                                error = "Enter Valid IFSC code";
                                setError((XMLCustomTIL) view, error);
                                isValid = false;
                            }
                        } else if (params.getFieldTag().equalsIgnoreCase(TAG_NAME_RE_ENTER_IFSC_CODE)) {
                            DynamicUITable ifsc = getObjectByTAG(TAG_NAME_IFSC_CODE, dynamicUITableList);
                            if (ifsc != null) {
                                if (!TextUtils.isEmpty(ifsc.getValue())) {
                                    if (!text.equalsIgnoreCase(ifsc.getValue())) {
                                        error = "IFSC code and re IFSC code should be same";
                                        setError((XMLCustomTIL) view, error);
                                        isValid = false;
                                    }
                                } else {
                                    error = "Please enter ifsc code first";
                                    setError((XMLCustomTIL) view, error);
                                    isValid = false;
                                }
                            }
                        }

                        if (params.getFieldTag().equalsIgnoreCase(TAG_NAME_REQUESTED_LOAN_AMOUNT)) {

                            int minimumAmount = 1000;
                            int maximumAmount = 150000000;
                            if (params.getLoanType().equalsIgnoreCase(LOAN_NAME_INDIVIDUAL)) {
                                minimumAmount = 40000;
                                maximumAmount = 200000;
                            }
                            if (Integer.parseInt(text) < minimumAmount) {
                                error = "Requested loan amount should be greater than " + minimumAmount;
                                setError((XMLCustomTIL) view, error);
                                isValid = false;
                            } else if (Integer.parseInt(text) > maximumAmount) {
                                error = "Requested loan amount should be less than " + maximumAmount;
                                setError((XMLCustomTIL) view, error);
                                isValid = false;
                            }

                        }

                        if (params.getFieldTag().equalsIgnoreCase(TAG_NAME_DECLARED_MONTHLY_REPAYMENT_CAPACITY)) {
                            DynamicUITable reqLoanAmtObj = getObjectByTAG(TAG_NAME_REQUESTED_LOAN_AMOUNT, dynamicUITableList);

                            int declared_monthly_repayment = text.length();
                            if (declared_monthly_repayment >= 4) {
                                //getFullName(SCREEN_NO_APPLICANT_KYC_IL,SCREEN_NO_ADDRESS_DETAIL_IL,CLIENT_ID,MODULE_TYPE_APPLICANT);

                                //   getApplicantAadharNum(SCREEN_NO_APPLICANT_KYC_IL, SCREEN_NO_CO_APPLICANT_KYC_IL,params.getValue(),params.getClientID(),MODULE_TYPE_APPLICANT  );

                                if (!text.startsWith("0")) {
                                    if (Integer.parseInt(text) >= Integer.parseInt(reqLoanAmtObj.getValue())) {
                                        error = "Declared monthly repayment should be less than requested loan amount";
                                        setError((XMLCustomTIL) view, error);
                                        isValid = false;
                                    }
                                } else {
                                    error = "Should Not Start With 0";
                                    setError((XMLCustomTIL) view, error);
                                    isValid = false;
                                }
                            } else {
                                error = "Declared monthly repayment capacity should be atleast 4 digit";
                                setError((XMLCustomTIL) view, error);
                                isValid = false;
                            }

                          /*  if (reqLoanAmtObj != null) {
                                if (!TextUtils.isEmpty(reqLoanAmtObj.getValue())) {
                                    double loanAmtValue = Double.parseDouble(reqLoanAmtObj.getValue());
                                    if (Double.parseDouble(s.toString()) >= loanAmtValue) {
                                        error = "Declared monthly repayment should be less than requested loan amount";
                                    } else {
                                        moveToNext = true;
                                    }

                                } else {
                                    error = "Please enter the requested loan amount first";
                                }
                            } else {
                                moveToNext = true;
                            }*/
                        }


                    } else {
                        // TODO: Not mandatory
                        String text = ((XMLCustomTIL) view).getEditText().getText().toString().trim();
                        String error = "Please enter valid detail";
                        if (!TextUtils.isEmpty(text)) {
                            if (params.getFieldTag().equalsIgnoreCase("PANCARD")
                                    || params.getFieldTag().equalsIgnoreCase(TAG_NAME_KYC_TYPE_PANCARD)
                                    || params.getFieldTag().equalsIgnoreCase(TAG_NAME_KYC_TYPE_RE_ENTER_PANCARD)
                            ) {
                                if (!appHelper.patternMatch(AppConstants.REGEX_PATTERN_PANCARD, text)) {
                                    error = "Enter Valid PAN";
                                    setError((XMLCustomTIL) view, error);
                                    isValid = false;
                                }
                            }
//                        if (!TextUtils.isEmpty(params.getHint()) && params.getHint().contains("Aadhaar")) {
                            if (params.getFieldTag().equalsIgnoreCase("AADHAAR")
                                    || params.getFieldTag().equalsIgnoreCase(TAG_NAME_KYC_TYPE_AADHAAR)
                                    || params.getFieldTag().equalsIgnoreCase(TAG_NAME_KYC_TYPE_RE_ENTER_AADHAAR)
                                    || params.getFieldTag().equalsIgnoreCase("VID")) {
                                int aadhaarSize = text.length();
                                if (aadhaarSize == 12) {
                                    if (!text.startsWith("0") && !text.startsWith("1")) {
                                        if (!Verhoeff.validateVerhoeff(text)) {
                                            error = "Enter Valid Aadhaar";
                                            setError((XMLCustomTIL) view, error);
                                            isValid = false;
                                        }
                                    } else {
                                        error = "Aadhaar No Should Not Start With 0 or 1";
                                        setError((XMLCustomTIL) view, error);
                                        isValid = false;
                                    }
                                } else {
                                    error = "Enter Valid 12 Digit Aadhaar Naumber";
                                    setError((XMLCustomTIL) view, error);
                                    isValid = false;
                                }
                            }
                            if (params.getFieldTag().equalsIgnoreCase(TAG_NAME_KYC_TYPE_PASSPORT)
                                    || params.getFieldTag().equalsIgnoreCase(TAG_NAME_KYC_TYPE_RE_ENTER_PASSPORT)) {
                                if (!appHelper.patternMatch(AppConstants.REGEX_PATTERN_PASSPORT, text)) {
                                    error = "Enter Valid passport";
                                    setError((XMLCustomTIL) view, error);
                                    isValid = false;
                                }
                            }
                            if (params.getFieldTag().equalsIgnoreCase("VOTERID")
                                    || params.getFieldTag().equalsIgnoreCase(TAG_NAME_KYC_TYPE_VOTERID)
                                    || params.getFieldTag().equalsIgnoreCase(TAG_NAME_KYC_TYPE_RE_ENTER_VOTERID)) {
                                if (text.length() < 10) {
                                    error = "Enter Valid VoterId";
                                    setError((XMLCustomTIL) view, error);
                                    isValid = false;
                                }
                            }
                            if (params.getFieldTag().equalsIgnoreCase(TAG_NAME_CONTAINS_AGE)) {
                                if (!TextUtils.isEmpty(text)) {
                                    if (params.getLoanType().equalsIgnoreCase(LOAN_NAME_MSME)||params.getLoanType().equalsIgnoreCase(LOAN_NAME_AHL)||params.getLoanType().equalsIgnoreCase(LOAN_NAME_PHL)) {
                                        if (Integer.parseInt(text) < 0) {
                                            error = ERROR_MESSAGE_FOR_MIN_AGE_MSME;
                                            setError((XMLCustomTIL) view, error);
                                            isValid = false;
                                        } else if (Integer.parseInt(text) > 100) {
                                            error = ERROR_MESSAGE_FOR_MAX_AGE_MSME;
                                            setError((XMLCustomTIL) view, error);
                                            isValid = false;
                                        }
                                    } else if (params.getLoanType().equalsIgnoreCase(LOAN_NAME_JLG)) {
//                                        ageValidationJLG(params,dynamicUITableList);
                                        if (Integer.parseInt(text) < 18) {
                                            error = ERROR_MESSAGE_FOR_MIN_AGE_JLG;
                                            setError((XMLCustomTIL) view, error);
                                            isValid = false;
                                        } else if (Integer.parseInt(text) > 55) {
                                            error = ERROR_MESSAGE_FOR_MAX_AGE_JLG;
                                            setError((XMLCustomTIL) view, error);
                                            isValid = false;
                                        }
                                    } else {
                                        if (Integer.parseInt(text) < 21) {
                                            error = ERROR_MESSAGE_FOR_MIN_AGE_IL;
                                            setError((XMLCustomTIL) view, error);
                                            isValid = false;
                                        } else if (Integer.parseInt(text) > 58) {
                                            error = ERROR_MESSAGE_FOR_MAX_AGE_IL;
                                            setError((XMLCustomTIL) view, error);
                                            isValid = false;
                                        }
                                    }
                                }
                            }
                            if (params.getFieldTag().equalsIgnoreCase(TAG_NAME_PINCODE)) {
                                if (!appHelper.patternMatch(AppConstants.REGEX_PATTERN_PINCODE, text)) {
                                    error = "Enter Valid Pincode";
                                    setError((XMLCustomTIL) view, error);
                                    isValid = false;
                                }
                            }

                            if (params.getFieldTag().equalsIgnoreCase(TAG_NAME_CONTACT_NO_2) && !TextUtils.isEmpty(params.getValue())) {

                                DynamicUITable contactNumber1 = getObjectByTAG(TAG_NAME_CONTACT_NO_1, dynamicUITableList);

                                if (contactNumber1 != null && !TextUtils.isEmpty(contactNumber1.getValue())) {

                                    if (!appHelper.patternMatch(AppConstants.REGEX_PATTERN_MOBILE_NUMBER, text.toString())) {
//                                    error = context.getString(getIdentifier(tag + "_INVALID_ERROR"));
                                        error = params.getFieldTag() + " invalid";
                                        setError((XMLCustomTIL) view, error);
                                        isValid = false;

                                    } else if (contactNumber1.getValue().toString().equalsIgnoreCase(text.toString())) {
                                        error = "Both contacts should be different";
                                        setError((XMLCustomTIL) view, error);
                                        isValid = false;
                                    }


                                } else {
                                    error = "Please enter Contact number 1";
                                    setError((XMLCustomTIL) view, error);
                                    isValid = false;
                                }

                            }
                            if (params.getFieldTag().equalsIgnoreCase(TAG_NAME_RE_ENTER_CONTACT_NO_2)  && !TextUtils.isEmpty(params.getValue())) {

                                DynamicUITable contactNumber2 = getObjectByTAG(TAG_NAME_CONTACT_NO_2, dynamicUITableList);
                                DynamicUITable contactNumber1 = getObjectByTAG(TAG_NAME_CONTACT_NO_1, dynamicUITableList);
                                if (contactNumber1 != null && !TextUtils.isEmpty(contactNumber1.getValue())) {
                                    if (contactNumber2 != null) {
                                        if (!TextUtils.isEmpty(contactNumber2.getValue())) {
                                            if (!(contactNumber2.getValue().equalsIgnoreCase(text))) {
                                                error = "Mobile number and re entered mobile number should be same";
                                                setError((XMLCustomTIL) view, error);
                                                isValid = false;
                                            } else {
                                                //isValid = true;
                                            }
                                        } else {
                                            error = "Please enter mobile number first";

                                            setError((XMLCustomTIL) view, error);
                                            isValid = false;
                                        }
                                    } else {
                                        //isValid = true;
                                    }
                                } else {

                                    error = "Please enter Contact number 1";
                                    setError((XMLCustomTIL) view, error);
                                    isValid = false;
                                }

                            }

                            if (params.getFieldTag().equalsIgnoreCase(TAG_NAME_IFSC_CODE)) {
                                if (!appHelper.patternMatch(AppConstants.IFSC_CODE_VALIDATION, text)) {
                                    error = "Enter Valid IFSC code";
                                    setError((XMLCustomTIL) view, error);
                                    isValid = false;
                                }
                            } else if (params.getFieldTag().equalsIgnoreCase(TAG_NAME_RE_ENTER_IFSC_CODE)) {
                                DynamicUITable ifsc = getObjectByTAG(TAG_NAME_IFSC_CODE, dynamicUITableList);
                                if (ifsc != null) {
                                    if (!TextUtils.isEmpty(ifsc.getValue())) {
                                        if (!text.equalsIgnoreCase(ifsc.getValue())) {
                                            error = "IFSC code and re IFSC code should be same";
                                            setError((XMLCustomTIL) view, error);
                                            isValid = false;
                                        }
                                    } else {
                                        error = "Please enter ifsc code first";
                                        setError((XMLCustomTIL) view, error);
                                        isValid = false;
                                    }
                                }
                            }
                        }

                        if (params.getFieldTag().equalsIgnoreCase(TAG_NAME_KYC_TYPE_RE_ENTER_UIDAI_ELIGIBLE_DOCUMENTS)
                                || params.getFieldTag().equalsIgnoreCase(TAG_NAME_KYC_TYPE_RE_ENTER_DEEMED_OVD)) {
                            View view2 = dynamicViews.get(i - 1);
                            String aboveValue = ((XMLCustomTIL) view2).getEditText().getText().toString().trim();
                            String reEnteredValue = ((XMLCustomTIL) view).getEditText().getText().toString().trim();
                            if (TextUtils.isEmpty(aboveValue) && TextUtils.isEmpty(reEnteredValue)) {
                                //fine
                            } else if (!TextUtils.isEmpty(aboveValue) && TextUtils.isEmpty(reEnteredValue)
                                    || TextUtils.isEmpty(aboveValue) && !TextUtils.isEmpty(reEnteredValue)) {
                                error = "enter both values";
                                setError((XMLCustomTIL) view, error);
                                isValid = false;

                            } else if (!TextUtils.isEmpty(aboveValue) &&
                                    !aboveValue.equalsIgnoreCase(reEnteredValue)) {
                                error = "Re enter correct value";
                                setError((XMLCustomTIL) view, error);
                                isValid = false;
                            }
                        }

                        if (params.getScreenID().equalsIgnoreCase(SCREEN_N0_MANUFACTURING_OPERATING_EXPENSE)) {
                            if (!TextUtils.isEmpty(params.getValue())) {
                                int iLength = Integer.parseInt(params.getMinLength());
                                if (params.getValue().length() < iLength) {
                                    if (!params.getValue().startsWith("0")) {
                                        isValid = false;
                                    }
                                }
                            }
                        }
                        if (params.getScreenID().equalsIgnoreCase(SCREEN_N0_HOUSE_EXPENSES)) {
                            if (!TextUtils.isEmpty(params.getValue())) {
                                int iLength = Integer.parseInt(params.getMinLength());
                                if (params.getValue().length() < iLength) {
                                    if (!params.getValue().startsWith("0")) {
                                        isValid = false;
                                    }
                                }
                            }
                        }
                    }
                } else if (view instanceof SearchableSpinner && view.getTag() != null) {
                    DynamicUITable params = (DynamicUITable) ((SearchableSpinner) view).getTag();
                    if (params != null && params.getIsRequired().equalsIgnoreCase(VIEW_REQUIRED) && view.getVisibility() == View.VISIBLE) {
                        if (((SearchableSpinner) view).getSelectedItemPosition() <= 0) {
                            isValid = false;
                            ((SearchableSpinner) view).setHasValidInput(false);
                        } else {
                            String selectedItem = ((SearchableSpinner) view).getSelectedItem().toString();
                            if (!TextUtils.isEmpty(selectedItem)) {
                                params.setValue(selectedItem);
                            }
                        }
                    }
                } else if (view instanceof XmlCustomCheckBox) {
                    DynamicUITable params = (DynamicUITable) ((XmlCustomCheckBox) view).getTag();
                                   /* if (params != null && view.getTag() != null) {
                                        if (!((XmlCustomCheckBox) view).isChecked()) {
                                            isValid = false;
                                            ((XmlCustomCheckBox) view).setError("Please check");
                                            Log.i(TAG, "error with CheckBox" + view.toString());
                                        } else {
                                            ((XmlCustomCheckBox) view).setError(null);
                                            Log.i(TAG, "error with CheckBox cleared" + view.toString());
                                        }
                                    }*/

                    ViewParent parent = view.getParent();
                    if (parent != null && parent instanceof ViewGroup && params.isVisibility() && params.getIsRequired().equalsIgnoreCase("Y")) {
                        ViewGroup viewGroup = (ViewGroup) parent;
                        boolean isCheckboxGroupValid = true;
                        for (int j = 0, count = viewGroup.getChildCount(); j < count; ++j) {
                            View view2 = viewGroup.getChildAt(j);
                            if (view2 instanceof CheckBox && view2.getTag() != null) {
                                DynamicUITable dynamicUITable = (DynamicUITable) view2.getTag();
                                if (!((CheckBox) view2).isChecked()) {
                                    isCheckboxGroupValid = false;
//                                                    ((CheckBox) view2).setError("Please check");
                                    Log.i(TAG, "error with CheckBox" + view2.toString());
                                } else {
                                    isCheckboxGroupValid = true;
                                    dynamicUITable.setValue(((CheckBox) view2).getText().toString());
//                                                    ((CheckBox) view2).setError(null);
                                    break;
                                }
                            }
                        }

                        if (!isCheckboxGroupValid) {
                            Toast.makeText(getActivity(), "Please select checkbox for " + params.getFieldName(), Toast.LENGTH_SHORT).show();
                            isValid = false;
                            ((XmlCustomCheckBox) view).setError("Please check");
                        } else {
                            ((XmlCustomCheckBox) view).setError(null);
                        }
                    }

                } else if (view instanceof XmlCustomRadioGroup && view.getTag() != null
                        && view.getVisibility() == View.VISIBLE) {
                    DynamicUITable params = (DynamicUITable) ((XmlCustomRadioGroup) view).getTag();
                    if(params.getIsRequired().equalsIgnoreCase("N")) {
                        int total = ((XmlCustomRadioGroup) view).getChildCount();
                        for (int j = 0; j < total; j++) {
                            View v = ((XmlCustomRadioGroup) view).getChildAt(j);
                            if (v instanceof RadioButton) {
                                ((RadioButton) v).setError(null);
                            }
                        }
                        view.getParent().requestChildFocus(view, view);
                    }else {
                        if (((XmlCustomRadioGroup) view).getCheckedRadioButtonId() == -1) {
                            isValid = false;
                            int total = ((XmlCustomRadioGroup) view).getChildCount();
                            for (int j = 0; j < total; j++) {
                                View v = ((XmlCustomRadioGroup) view).getChildAt(j);
                                if (v instanceof RadioButton) {
                                    ((RadioButton) v).setError("Please check");
                                }
                            }
                            view.getParent().requestChildFocus(view, view);
                            Log.i(TAG, "error with RadioGroup" + view.toString());
                        } else {
                            int total = ((XmlCustomRadioGroup) view).getChildCount();
                            for (int j = 0; j < total; j++) {
                                View v = ((XmlCustomRadioGroup) view).getChildAt(j);
                                if (v instanceof RadioButton) {
                                    ((RadioButton) v).setError(null);
                                }
                            }
                            view.getParent().requestChildFocus(view, view);
                            Log.i(TAG, "Error cleared tvName RadioGroup" + view.toString());
                        }
                    }
                }

            }
            if (isValid) {
                if (buttonClick != null) {


                    buttonClick.onButtonClickSuccess();
                }
            } else {
                appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, "Enter All Mandatory Fields");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void setValueForSingleFieldInUI(DynamicUITable dynamicUITable, List<DynamicUITable> dynamicUITableListFromUI) {
        try {
            for (int i = 0; i < dynamicViews.size(); i++) {
                View view = dynamicViews.get(i);
                Log.d("id", "GET TAG ====>" + view.getTag());
                DynamicUITable tag = (DynamicUITable) view.getTag();
                if (tag != null && tag.getFieldTag().equalsIgnoreCase(dynamicUITable.getFieldTag())) {
                    view.setTag(dynamicUITable);
                    // TODO: TextInputLayout View
                    if (view instanceof XMLCustomTIL && view.getTag() != null) {
                        ((XMLCustomTIL) view).setTag(dynamicUITable);
                        DynamicUITable params = (DynamicUITable) ((XMLCustomTIL) view).getTag();
//                             if (((XMLCustomTIL) view).isMandatory()) {
                        if (!params.isValid()) {
                            if (!TextUtils.isEmpty(params.getErrorMessage())) {
                                ((XMLCustomTIL) view).setErrorMsg(params.getErrorMessage());
                                ((XMLCustomTIL) view).setHasValidInput(params.isValid());
                            }
                        } else if (!TextUtils.isEmpty(params.getFieldTag()) && params.getFieldTag().equalsIgnoreCase(dynamicUITable.getFieldTag())) {
                            DynamicUITable dynamicUITableFromUI = getObjectByTAG(dynamicUITable.getFieldTag(), dynamicUITableListFromUI);
                            if (dynamicUITableFromUI != null) {
                                dynamicUITableFromUI.setValue(dynamicUITable.getValue());
                                dynamicUITableFromUI.setDefaultValue(dynamicUITable.getDefaultValue());
                            }
                            // TODO: Set value only for below screens
                            if ((dynamicUITable.getScreenName().equalsIgnoreCase(SCREEN_NAME_MONTHLY_TRANSACTION_DETAIL_MSME) &&
                                    dynamicUITable.getFieldTag().equalsIgnoreCase(TAG_NAME_TOTAL_BOUNCES_IN_BANKING_HISTORY))
                                    || (dynamicUITable.getScreenName().equalsIgnoreCase(SCREEN_NAME_PRODUCT_ESTIMATE_DETAIL_MSME) &&
                                    dynamicUITable.getFieldTag().equalsIgnoreCase(TAG_NAME_TOTAL_SALES_IN_PRODUCT_ESTIMATE_DETAIL))
                                    || (dynamicUITable.getScreenName().equalsIgnoreCase(SCREEN_NAME_PRODUCT_ESTIMATE_DETAIL_MSME) &&
                                    dynamicUITable.getFieldTag().equalsIgnoreCase(TAG_NAME_DIRECT_COSTS_IN_PRODUCT_ESTIMATE_DETAIL))
                                    || (dynamicUITable.getScreenName().equalsIgnoreCase(SCREEN_NAME_DIRECT_BUSINESS_EXPENSE_DETAIL_MSME) &&
                                    dynamicUITable.getFieldTag().equalsIgnoreCase(TAG_NAME_TOTAL_EXPENSES_IN_DIRECT_BUSINESS_EXPENSE))
                                    || (dynamicUITable.getScreenName().equalsIgnoreCase(SCREEN_NAME_FAMILY_MEMBER_INCOME) &&
                                    dynamicUITable.getFieldTag().equalsIgnoreCase(TAG_NAME_TOTAL_VERIFIED_INCOME))
                            ) {
                                ((XMLCustomTIL) view).getEditText().setText(params.getValue());
                            }
                        }
                    }

                    // TODO: radio group view
                    if (view instanceof XmlCustomRadioGroup && view.getTag() != null
                            && view.getVisibility() == View.VISIBLE) {
                        ((XmlCustomRadioGroup) view).setTag(dynamicUITable);
                        DynamicUITable params = (DynamicUITable) ((XmlCustomRadioGroup) view).getTag();
//                             if (((XMLCustomTIL) view).isMandatory()) {
                        if (!params.isValid()) {
                            if (!TextUtils.isEmpty(params.getErrorMessage())) {
                                ((XmlCustomRadioGroup) view).clearCheck();

                                if (params.getIsRequired().equalsIgnoreCase("N")) {
                                        int total = ((XmlCustomRadioGroup) view).getChildCount();
                                        for (int j = 0; j < total; j++) {
                                            View v = ((XmlCustomRadioGroup) view).getChildAt(j);
                                            if (v instanceof RadioButton) {
                                                ((RadioButton) v).setError(params.getErrorMessage());
                                            }
                                        }
                                        view.getParent().requestChildFocus(view, view);
                                        Toast.makeText(getContext(), params.getErrorMessage(), Toast.LENGTH_SHORT).show();
                                        Log.i(TAG, "Error cleared tvName RadioGroup" + view.toString());
                                } else {

                                    if (((XmlCustomRadioGroup) view).getCheckedRadioButtonId() == -1) {

                                        int total = ((XmlCustomRadioGroup) view).getChildCount();
                                        for (int j = 0; j < total; j++) {
                                            View v = ((XmlCustomRadioGroup) view).getChildAt(j);
                                            if (v instanceof RadioButton) {
                                                ((RadioButton) v).setError("Please check");
                                            }
                                        }
                                        view.getParent().requestChildFocus(view, view);
                                        Toast.makeText(getContext(), params.getErrorMessage(), Toast.LENGTH_SHORT).show();
                                        Log.i(TAG, "error with RadioGroup" + view.toString());
                                    } else {
                                        int total = ((XmlCustomRadioGroup) view).getChildCount();
                                        for (int j = 0; j < total; j++) {
                                            View v = ((XmlCustomRadioGroup) view).getChildAt(j);
                                            if (v instanceof RadioButton) {
                                                ((RadioButton) v).setError(params.getErrorMessage());
                                            }
                                        }
                                        view.getParent().requestChildFocus(view, view);
                                        Toast.makeText(getContext(), params.getErrorMessage(), Toast.LENGTH_SHORT).show();
                                        Log.i(TAG, "Error cleared tvName RadioGroup" + view.toString());
                                    }
                                }
                            }
                        }
                    }
                    // TODO: Calculator View Or New Row View
                    else if (view instanceof ViewGroup && (((ViewGroup) view).getChildCount() > 0)) {

                        if (view instanceof LinearLayout) {
                            View childView2 = ((ViewGroup) view).getChildAt(1);
                            if (childView2 instanceof ViewGroup && (((ViewGroup) view).getChildCount() > 0)) {
                                View tView = ((ViewGroup) childView2).getChildAt(0);
                                if (tView instanceof TextView && view.getTag() != null) {
                                    ((TextView) tView).setTag(dynamicUITable);
                                    DynamicUITable params = (DynamicUITable) ((TextView) tView).getTag();
                                    if (!TextUtils.isEmpty(params.getFieldTag()) && params.getFieldTag().equalsIgnoreCase(dynamicUITable.getFieldTag())) {
                                        DynamicUITable dynamicUITableFromUI = getObjectByTAG(dynamicUITable.getFieldTag(), dynamicUITableListFromUI);
                                        if (dynamicUITableFromUI != null) {
                                            dynamicUITableFromUI.setValue(dynamicUITable.getValue());
                                        }
                                        ((TextView) tView).setText(dynamicUITable.getValue());

                                    }
                                }
                            } else {
                                if (childView2 instanceof TextView && view.getTag() != null) {
                                    ((TextView) childView2).setTag(dynamicUITable);
                                    DynamicUITable params = (DynamicUITable) ((TextView) childView2).getTag();
                                    if (!TextUtils.isEmpty(params.getFieldTag()) && params.getFieldTag().equalsIgnoreCase(dynamicUITable.getFieldTag())) {
                                        DynamicUITable dynamicUITableFromUI = getObjectByTAG(dynamicUITable.getFieldTag(), dynamicUITableListFromUI);
                                        if (dynamicUITableFromUI != null) {
                                            dynamicUITableFromUI.setValue(dynamicUITable.getValue());
                                        }
                                        ((TextView) childView2).setText(dynamicUITable.getValue());

                                    }
                                }
                            }
                        }

                    } else {
                        if (view instanceof TextView && view.getTag() != null) {
                            ((TextView) view).setTag(dynamicUITable);
                            DynamicUITable params = (DynamicUITable) ((TextView) view).getTag();
                            if (!TextUtils.isEmpty(params.getFieldTag()) && params.getFieldTag().equalsIgnoreCase(dynamicUITable.getFieldTag())) {
                                DynamicUITable dynamicUITableFromUI = getObjectByTAG(dynamicUITable.getFieldTag(), dynamicUITableListFromUI);
                                if (dynamicUITableFromUI != null) {
                                    dynamicUITableFromUI.setValue(dynamicUITable.getValue());
                                }
                                ((TextView) view).setText(dynamicUITable.getValue());

                            }
                        }
                    }

                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    // -------------------------
    // UPDATE DYNAMIC UI TABLE
    // -------------------------
    public void updateDynamicUITable(List<DynamicUITable> viewParametersList, String screenNumber) {
        try {
            viewModel.updateDynamicUITable(viewParametersList, screenNumber);
            if (viewModel.getDynamicUITableLiveData() != null) {

                Observer updateDynamicUITableObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        List<DynamicUITable> list = (List<DynamicUITable>) o;
                        viewModel.getDynamicUITableLiveData().removeObserver(this);
                        dynamicUI(list);
                    }
                };
                viewModel.getDynamicUITableLiveData().observe(this, updateDynamicUITableObserver);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void deleteAndInsertNewRecord(List<DynamicUITable> viewParametersList, String screenName) {
        try {
            viewModel.deleteAndInsertNewRecord(viewParametersList, screenName);
            if (viewModel.getDynamicUITableLiveData() != null) {

                Observer deleteAndInsertNewRecordObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        List<DynamicUITable> list = (List<DynamicUITable>) o;
                        viewModel.getDynamicUITableLiveData().removeObserver(this);

                        if (SCREEN_NAME.equalsIgnoreCase(SCREEN_NAME_HOUSE_EXPENSES)
                                || SCREEN_NAME.equalsIgnoreCase(SCREEN_NAME_NOMINEE_DETAIL)
                                || SCREEN_NAME.equalsIgnoreCase(SCREEN_NAME_REFERENCES)

                        ) {
                            getRawDataFromOtherScreenAndUpdate(list, SCREEN_NAME);
                        }
                        // TODO: PHL bank details
                        else if (!TextUtils.isEmpty(LOAN_TYPE) && LOAN_TYPE.equalsIgnoreCase(LOAN_NAME_PHL)
                                && SCREEN_NAME.equalsIgnoreCase(SCREEN_NAME_BANK_DETAILS)) {
                            getRawDataFromOtherScreenAndUpdate(list, SCREEN_NAME);
                        }
                        // TODO: AHL bank details
                        else if (!TextUtils.isEmpty(LOAN_TYPE) && LOAN_TYPE.equalsIgnoreCase(LOAN_NAME_AHL)
                                && SCREEN_NAME.equalsIgnoreCase(SCREEN_NAME_BANK_DETAILS)) {
                            getRawDataFromOtherScreenAndUpdate(list, SCREEN_NAME);
                        } else {
                            dynamicUI(list);
                        }
                    }
                };
                viewModel.getDynamicUITableLiveData().observe(this, deleteAndInsertNewRecordObserver);
            }


        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    public void autoSumFields(DynamicUITable dynamicUITable, String toFieldName,
                              String fromScreen, String toScreen) {
        try {
            viewModel.autoSumFields(dynamicUITable, toFieldName, fromScreen, toScreen);
            if (viewModel.getDynamicUITableLiveData() != null) {
                Observer copyValuesBasedOnScreenObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        List<DynamicUITable> list = (List<DynamicUITable>) o;
                        viewModel.getDynamicUITableLiveData().removeObserver(this);
                        dynamicUI(list);
                    }
                };
                viewModel.getDynamicUITableLiveData().observe(this, copyValuesBasedOnScreenObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void sumOfAllFieldsByFeatureId(DynamicUITable dynamicUITable, List<DynamicUITable> dynamicUIList,
                                          String resultTag) {
        try {
            viewModel.sumOfAllFieldsByFeatureId(dynamicUITable, dynamicUIList,
                    resultTag);
            if (viewModel.getDynamicUITableLiveData() != null) {
                Observer sumOfAllFieldsByFeatureIdObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        List<DynamicUITable> dynamicUITableListFromDB = (List<DynamicUITable>) o;
                        viewModel.getDynamicUITableLiveData().removeObserver(this);
                        for (DynamicUITable dynamicUITableFromDB : dynamicUITableListFromDB) {
                            setValueForSingleFieldInUI(dynamicUITableFromDB, dynamicUIList);
                        }
                    }
                };
                viewModel.getDynamicUITableLiveData().observe(this, sumOfAllFieldsByFeatureIdObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // TODO: GST CALCULATION
    public void GSTCalculationByFeatureId(DynamicUITable dynamicUITable, List<DynamicUITable> dynamicUIList,
                                          String resultTag) {
        try {
            viewModel.GSTCalculationByFeatureId(dynamicUITable, dynamicUIList,
                    resultTag);
            if (viewModel.getDynamicUITableLiveData() != null) {
                Observer GSTCalculationByFeatureIdObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        List<DynamicUITable> dynamicUITableListFromDB = (List<DynamicUITable>) o;
                        viewModel.getDynamicUITableLiveData().removeObserver(this);
                        for (DynamicUITable dynamicUITableFromDB : dynamicUITableListFromDB) {
                            setValueForSingleFieldInUI(dynamicUITableFromDB, dynamicUIList);
                        }
                    }
                };
                viewModel.getDynamicUITableLiveData().observe(this, GSTCalculationByFeatureIdObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void calculateAndUpdate(List<String> FromfieldNameList, List<String> TofieldNameList,
                                   String fromScreen, String toScreen, DynamicUITable dynamicUITable, String newRowTAGName,
                                   String lableTagName, String valueTagName, boolean updateButtonClick,
                                   RawDataTable rawDataTable, List<DynamicUITable> viewParametersList) {
        try {
            viewModel.calculateAndUpdate(FromfieldNameList, TofieldNameList, fromScreen, toScreen,
                    newRowTAGName,
                    lableTagName, valueTagName, updateButtonClick, viewParametersList);
            if (viewModel.getDynamicUITableLiveData() != null) {
                Observer calculateLiabilityObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        List<DynamicUITable> list = (List<DynamicUITable>) o;
                        viewModel.getDynamicUITableLiveData().removeObserver(this);

                        if (updateButtonClick) {
                            updateRawDataTable(rawDataTable, list, dynamicUITable);
                        } else {
                            if (list != null && list.size() > 0) {
                                if (LOSBaseFragment.this instanceof ChildFragment) {
                                    ChildFragment childFragment = (ChildFragment) LOSBaseFragment.this;
                                    childFragment.callCloseDialogFragment();
                                }
                                FragmentToActivityInterface fragmentToActivityInterface = (FragmentToActivityInterface) getActivity();
                                fragmentToActivityInterface.valueFromFragment(list.get(0).getScreenID(), SCREEN_NAME, list.get(0).getScreenName(), list);
                            }
                        }

                    }
                };
                viewModel.getDynamicUITableLiveData().observe(this, calculateLiabilityObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    public void clearValuesBasedOnScreen(String screen) {
        try {
            viewModel.clearValuesBasedOnScreen(screen);
            if (viewModel.getDynamicUITableLiveData() != null) {
                Observer clearValuesBasedOnScreenObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        List<DynamicUITable> dynamicUITableListd = (List<DynamicUITable>) o;
                        viewModel.getDynamicUITableLiveData().removeObserver(this);
                        dynamicUI(dynamicUITableListd);
                    }
                };
                viewModel.getDynamicUITableLiveData().observe(this, clearValuesBasedOnScreenObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void clearValuesBasedOnScreenTAGInDP(String screen, String FieldTAG) {
        try {
            viewModel.clearValuesBasedOnScreenTAGInDP(screen, FieldTAG);
            if (viewModel.getDynamicUITableLiveData() != null) {
                Observer clearValuesBasedOnScreenTAGInDPObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        List<DynamicUITable> dynamicUITableListd = (List<DynamicUITable>) o;
                        viewModel.getDynamicUITableLiveData().removeObserver(this);
                        dynamicUI(dynamicUITableListd);
                    }
                };
                viewModel.getDynamicUITableLiveData().observe(this, clearValuesBasedOnScreenTAGInDPObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    public void EnableOrDisableByFieldNameInDB(List<ParameterInfo> parameterInfoList, List<DynamicUITable> dynamicUITableList) {
        try {
            viewModel.EnableOrDisableByFieldName(parameterInfoList, dynamicUITableList);
            if (viewModel.getDynamicUITableLiveData() != null) {
                Observer EnableOrDisableObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        List<DynamicUITable> dynamicUITableListd = (List<DynamicUITable>) o;
                        viewModel.getDynamicUITableLiveData().removeObserver(this);
                        dynamicUI(dynamicUITableListd);
                    }
                };
                viewModel.getDynamicUITableLiveData().observe(this, EnableOrDisableObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void getMetaDataByScreenName(String screenName, String loanType) {
        try {
            viewModel.getMetaDataByScreenName(screenName, loanType);
            if (viewModel.getDynamicUITableLiveData() != null) {
                Observer getMetaDataByScreenNameObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        List<DynamicUITable> dynamicUITableListd = (List<DynamicUITable>) o;
                        viewModel.getDynamicUITableLiveData().removeObserver(this);
                        if (screenName.equalsIgnoreCase(SCREEN_NAME_BANKING_HISTORY_MSME)) { // TODO: BANKING HISTORY SCREEN
                            bankingHistoryCalculation(dynamicUITableListd.get(dynamicUITableListd.size() - 1), dynamicUITableListd);
                        }
                        if (screenName.equalsIgnoreCase(SCREEN_NAME_SALES_BILLS_MSME)) { // TODO: SALES BILLS SCREEN
                            salesBillsCalculation(dynamicUITableListd.get(dynamicUITableListd.size() - 1), dynamicUITableListd);
                        }
                        if (screenName.equalsIgnoreCase(SCREEN_NAME_PURCHASE_BILLS_MSME)) { // TODO: PURCHASE BILLS SCREEN
                            purchaseBillsCalculation(dynamicUITableListd.get(dynamicUITableListd.size() - 1), dynamicUITableListd);
                        }
                        if (screenName.equalsIgnoreCase(SCREEN_NAME_ITR_MSME)) { // TODO: ITR SCREEN
                            ITRCalculation(dynamicUITableListd.get(dynamicUITableListd.size() - 1), dynamicUITableListd);
                        }
                        if (screenName.equalsIgnoreCase(SCREEN_NAME_PRODUCT_ESTIMATE_DETAIL_MSME)) { // TODO: PRODUCT ESTIMATE DETAIL SCREEN
                            ProductEstimateDetailCalculation(dynamicUITableListd.get(dynamicUITableListd.size() - 1), dynamicUITableListd);
                        }
                        if (screenName.equalsIgnoreCase(SCREEN_NAME_SERVICE_ESTIMATE_DETAIL_MSME)) { // TODO: SERVICE ESTIMATE DETAIL SCREEN
                            ServiceEstimateDetailCalculation(dynamicUITableListd.get(dynamicUITableListd.size() - 1), dynamicUITableListd);
                        }
                        if (screenName.equalsIgnoreCase(SCREEN_NAME_DIRECT_BUSINESS_EXPENSE_MSME)) { // TODO: DIRECT BUSINESS EXPENSE SCREEN
                            directBusinessExpenseCalculation(dynamicUITableListd.get(dynamicUITableListd.size() - 1), dynamicUITableListd);
                        }
                        if (screenName.equalsIgnoreCase(SCREEN_NAME_HOUSE_LIABILITIES_MSME)
                                && loanType.equalsIgnoreCase(LOAN_NAME_MSME)) { // TODO: HOUSE LIABILITY SCREEN
                            houseLiabilitiesMsmeCalculation(dynamicUITableListd.get(dynamicUITableListd.size() - 1), dynamicUITableListd);
                        }
                        if (screenName.equalsIgnoreCase(SCREEN_NAME_BUSINESS_LIABILITIES_MSME)
                                && loanType.equalsIgnoreCase(LOAN_NAME_MSME)) { // TODO: BUSINESS LIABILITY SCREEN
                            businessLiabilitiesMsmeCalculation(dynamicUITableListd.get(dynamicUITableListd.size() - 1), dynamicUITableListd);
                        }
                        if (screenName.equalsIgnoreCase(SCREEN_NAME_HOUSE_ASSETS_MSME)
                                && loanType.equalsIgnoreCase(LOAN_NAME_MSME)) { // TODO: HOUSE ASSET SCREEN
                            houseAssetCalculation(dynamicUITableListd.get(dynamicUITableListd.size() - 1), dynamicUITableListd);
                        }
                        if (screenName.equalsIgnoreCase(SCREEN_NAME_BUSINESS_ASSETS_MSME)
                                && loanType.equalsIgnoreCase(LOAN_NAME_MSME)) { // TODO: BUSINESS ASSET SCREEN
                            businessAssetMSMECalculation(dynamicUITableListd.get(dynamicUITableListd.size() - 1), dynamicUITableListd);
                        }
                        if (screenName.equalsIgnoreCase(SCREEN_NAME_HYPOTHECATION_MSME)
                                && loanType.equalsIgnoreCase(LOAN_NAME_MSME)) { // TODO: BUSINESS ASSET SCREEN
                            hypothecationMsmeCalculation(dynamicUITableListd.get(dynamicUITableListd.size() - 1), dynamicUITableListd);
                        }
                        if (screenName.equalsIgnoreCase(SCREEN_NAME_MORTGAGE_MSME)
                                && loanType.equalsIgnoreCase(LOAN_NAME_MSME)) { // TODO: MORTGAGE SCREEN
                            mortgageCalculation(dynamicUITableListd.get(dynamicUITableListd.size() - 1), dynamicUITableListd);
                        }
                        if (screenName.equalsIgnoreCase(SCREEN_NAME_APPLICANT_KYC)) { // TODO: APPLICANT KYC SCREEN
                            applicantKYCScreenValidation(dynamicUITableListd.get(0), dynamicUITableListd);
                        }
                        if (screenName.equalsIgnoreCase(SCREEN_NAME_CO_APPLICANT_KYC)) { // TODO: CO APPLICANT KYC SCREEN
                            applicantKYCScreenValidation(dynamicUITableListd.get(0), dynamicUITableListd);
                        }
                        if (screenName.equalsIgnoreCase(SCREEN_NAME_REFERENCE_CHECK)) { // TODO: REFERENCE CHECK SCREEN
                            referenceCheckScreenValidation(dynamicUITableListd.get(0), dynamicUITableListd);
                        }
                        if (screenName.equalsIgnoreCase(SCREEN_NAME_GENERAL_INCOME)
                                && loanType.equalsIgnoreCase(LOAN_NAME_MSME)) { // TODO: GENERAL INCOME SCREEN
                            generalIncomeScreenValidation(dynamicUITableListd.get(0), dynamicUITableListd);
                        }
                        if (screenName.equalsIgnoreCase(SCREEN_NAME_OTHER_INCOME_SOURCE)
                                && loanType.equalsIgnoreCase(LOAN_NAME_MSME)) { // TODO: GENERAL INCOME SCREEN
                            otherIncomeSourceScreenValidation(dynamicUITableListd.get(0), dynamicUITableListd);
                        }
                        if (screenName.equalsIgnoreCase(SCREEN_NAME_REFERENCES)) { // TODO: REFERENCE CERIFICATION SCREEN
                            referenceVerificationScreenValidation(dynamicUITableListd.get(0), dynamicUITableListd);
                        }
                        if (screenName.equalsIgnoreCase(SCREEN_NAME_HOUSE_LIABILITIES)
                                && loanType.equalsIgnoreCase(LOAN_NAME_INDIVIDUAL)) { // TODO: HOUSE LIABILITY SCREEN
                            houseLiabilitiesILCalculation(dynamicUITableListd.get(dynamicUITableListd.size() - 1), dynamicUITableListd);
                        }
                        if (screenName.equalsIgnoreCase(SCREEN_NAME_BUSINESS_ASSETS)
                                && loanType.equalsIgnoreCase(LOAN_NAME_INDIVIDUAL)) { // TODO: BUSINESS ASSET SCREEN
                            businessAssetILCalculation(dynamicUITableListd.get(dynamicUITableListd.size() - 1), dynamicUITableListd);
                        }
                        if (screenName.equalsIgnoreCase(SCREEN_NAME_BUSINESS_LIABILITIES)
                                && loanType.equalsIgnoreCase(LOAN_NAME_INDIVIDUAL)) { // TODO: BUSINESS LIABILITY SCREEN
                            businessLiabilitiesILCalculation(dynamicUITableListd.get(dynamicUITableListd.size() - 1), dynamicUITableListd);
                        }
                        if (screenName.equalsIgnoreCase(SCREEN_NAME_HOUSE_INCOME)
                                && loanType.equalsIgnoreCase(LOAN_NAME_INDIVIDUAL)) { // TODO: BUSINESS LIABILITY SCREEN
                            houseIncomeILCalculation(dynamicUITableListd.get(dynamicUITableListd.size() - 1), dynamicUITableListd);
                        }

                    }
                };
                viewModel.getDynamicUITableLiveData().observe(this, getMetaDataByScreenNameObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void OralVerificationEnableOrDisable(List<ParameterInfo> parameterInfoList, List<DynamicUITable> dynamicUITableList) {
        try {
            viewModel.OralVerificationEnableOrDisable(parameterInfoList, dynamicUITableList);
            if (viewModel.getDynamicUITableLiveData() != null) {
                Observer OralVerificationEnableOrDisableObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        List<DynamicUITable> dynamicUITableListd = (List<DynamicUITable>) o;
                        viewModel.getDynamicUITableLiveData().removeObserver(this);
                        dynamicUI(dynamicUITableListd);
                    }
                };
                viewModel.getDynamicUITableLiveData().observe(this, OralVerificationEnableOrDisableObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    private void copyValuesFromPersonalDetailScreen(List<DynamicUITable> viewParametersList) {
        try {
            viewModel.copyValuesFromPersonalDetailScreen(SCREEN_NAME_PERSONAL_DETAIL, SCREEN_NAME_NOMINEE_DETAIL, viewParametersList);
            if (viewModel.getDynamicUITableLiveData() != null) {
                Observer copyValuesFromPersonalDetailScreenObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        List<DynamicUITable> dynamicUITableList = (List<DynamicUITable>) o;
                        viewModel.getDynamicUITableLiveData().removeObserver(this);
                        dynamicUI(dynamicUITableList);

                    }
                };
                viewModel.getDynamicUITableLiveData().observe(this, copyValuesFromPersonalDetailScreenObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    public void copyValuesFromScreenToScreen(List<ParameterInfo> fromScreenList, List<ParameterInfo> toScreenList,
                                             List<DynamicUITable> dynamicUITableList1) {
        try {
            viewModel.copyValuesFromScreenToScreen(fromScreenList, toScreenList, dynamicUITableList1);
            if (viewModel.getDynamicUITableLiveData() != null) {
                Observer copyValuesFromScreenToScreenObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        List<DynamicUITable> dynamicUITableList = (List<DynamicUITable>) o;
                        viewModel.getDynamicUITableLiveData().removeObserver(this);
                        if (toScreenList.get(0).getscreenId().equalsIgnoreCase(SCREEN_N0_MANUFACTURING_SUMMARY)) {
                            calculateNBI(dynamicUITableList.get(0), dynamicUITableList);
                        } else if (toScreenList.get(0).getscreenId().equalsIgnoreCase(SCREEN_NO_REFERENCES_IL)) {
                            DynamicUITable dynamicUITable = getObjectByTAG(TAG_NAME_REFERENCE_TYPE, dynamicUITableList);
                            if (dynamicUITable != null && !TextUtils.isEmpty(dynamicUITable.getValue())) {
                                setReferencesScreenChangesByReferenceType(dynamicUITable, dynamicUITable.getValue(),
                                        dynamicUITableList);
                            } else {
                                dynamicUI(dynamicUITableList);
                            }
                        } else {
                            dynamicUI(dynamicUITableList);
                        }
                    }
                };
                viewModel.getDynamicUITableLiveData().observe(this, copyValuesFromScreenToScreenObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    private void calculateAvgSales(List<DynamicUITable> dynamicUITableList, DynamicUITable dynamicUITable,
                                   String tag, String tagToDisplayResult, int totalNo) {
        try {
            viewModel.calculateAvgSales(dynamicUITableList, dynamicUITable,
                    tag, tagToDisplayResult, totalNo);
            if (viewModel.getDynamicUITableLiveData() != null) {
                Observer calculateAvgSalesObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        List<DynamicUITable> dynamicUITableListd = (List<DynamicUITable>) o;
                        viewModel.getDynamicUITableLiveData().removeObserver(this);
//                        dynamicUI(dynamicUITableListd);
                        for (DynamicUITable dynamicUITable1 : dynamicUITableListd) {
                            setValueForSingleFieldInUI(dynamicUITable1, dynamicUITableList);
                        }
                    }
                };
                viewModel.getDynamicUITableLiveData().observe(this, calculateAvgSalesObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void EnableOrDisableByFeatureId(String enableFeatureTag, String disableFeatureTag, List<DynamicUITable> dynamicUITableList) {
        try {
            viewModel.EnableOrDisableByFeatureId(enableFeatureTag, disableFeatureTag, dynamicUITableList);
            if (viewModel.getDynamicUITableLiveData() != null) {
                Observer EnableOrDisableByFeatureIdObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        List<DynamicUITable> dynamicUITableListd = (List<DynamicUITable>) o;
                        viewModel.getDynamicUITableLiveData().removeObserver(this);
                        dynamicUI(dynamicUITableListd);

                    }
                };
                viewModel.getDynamicUITableLiveData().observe(this, EnableOrDisableByFeatureIdObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void GSTCalculation(DynamicUITable dynamicUITable, List<DynamicUITable> dynamicUITableList) {
        try {
            viewModel.GSTCalculation(dynamicUITable, dynamicUITableList);
            if (viewModel.getDynamicUITableLiveData() != null) {
                Observer EnableOrDisableByFeatureIdObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        List<DynamicUITable> dynamicUITableListd = (List<DynamicUITable>) o;
                        viewModel.getDynamicUITableLiveData().removeObserver(this);
                        dynamicUI(dynamicUITableListd);

                    }
                };
                viewModel.getDynamicUITableLiveData().observe(this, EnableOrDisableByFeatureIdObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    private void setAddressKYCType(DynamicUITable dynamicUITable, List<DynamicUITable> dynamicUITableList, String screenToGetData) {
        try {
            viewModel.setAddressKYCType(dynamicUITable, dynamicUITableList, screenToGetData);
            if (viewModel.getDynamicUITableLiveData() != null) {
                Observer setAddressKYCTypeObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        List<DynamicUITable> dynamicUITableList1 = (List<DynamicUITable>) o;
                        viewModel.getDynamicUITableLiveData().removeObserver(this);
                        dynamicUI(dynamicUITableList1);
                    }
                };
                viewModel.getDynamicUITableLiveData().observe(this, setAddressKYCTypeObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void setNamesByRadioButtonChanges(DynamicUITable dynamicUITable, String selectedRadioButton, List<DynamicUITable> dynamicUITableList) {
        try {
            viewModel.setNamesByRadioButtonChanges(dynamicUITable, selectedRadioButton, dynamicUITableList);
            if (viewModel.getDynamicUITableLiveData() != null) {
                Observer setNamesByRadioButtonChangesObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        List<DynamicUITable> dynamicUITableList1 = (List<DynamicUITable>) o;
                        viewModel.getDynamicUITableLiveData().removeObserver(this);
                        dynamicUI(dynamicUITableList1);
                    }
                };
                viewModel.getDynamicUITableLiveData().observe(this, setNamesByRadioButtonChangesObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void FixedAssetValidation(DynamicUITable dynamicUITable, List<DynamicUITable> dynamicUITableList) {
        try {
            viewModel.FixedAssetValidation(dynamicUITable, dynamicUITableList);
            if (viewModel.getDynamicUITableLiveData() != null) {
                Observer FixedAssetValidationObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        List<DynamicUITable> dynamicUITableListFromDB = (List<DynamicUITable>) o;
                        viewModel.getDynamicUITableLiveData().removeObserver(this);
                        dynamicUI(dynamicUITableListFromDB);
                    }
                };
                viewModel.getDynamicUITableLiveData().observe(this, FixedAssetValidationObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void setGeneralIncomeScreenChangesByDropDown(DynamicUITable dynamicUITable, String selectedItem, String selectedRadioButton,
                                                         List<DynamicUITable> dynamicUITableList) {
        try {
            viewModel.setGeneralIncomeScreenChangesByDropDown(dynamicUITable, selectedItem, selectedRadioButton, dynamicUITableList);
            if (viewModel.getDynamicUITableLiveData() != null) {
                Observer setGeneralIncomeScreenChangesByDropDown = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        List<DynamicUITable> dynamicUITableList1 = (List<DynamicUITable>) o;
                        viewModel.getDynamicUITableLiveData().removeObserver(this);
                        dynamicUI(dynamicUITableList1);
                    }
                };
                viewModel.getDynamicUITableLiveData().observe(this, setGeneralIncomeScreenChangesByDropDown);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    private void setOtherIncomeScreenChangesByDropDown(DynamicUITable dynamicUITable, String selectedItem, String selectedRadioButton,
                                                       List<DynamicUITable> dynamicUITableList) {
        try {
            viewModel.setOtherIncomeScreenChangesByDropDown(dynamicUITable, selectedItem, selectedRadioButton, dynamicUITableList);
            if (viewModel.getDynamicUITableLiveData() != null) {
                Observer setOtherIncomeScreenChangesByDropDownObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        List<DynamicUITable> dynamicUITableList1 = (List<DynamicUITable>) o;
                        viewModel.getDynamicUITableLiveData().removeObserver(this);
                        dynamicUI(dynamicUITableList1);
                    }
                };
                viewModel.getDynamicUITableLiveData().observe(this, setOtherIncomeScreenChangesByDropDownObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void copyAddressBasedOnDropDown(DynamicUITable dynamicUITable, List<DynamicUITable> dynamicUITableList, String screenToGetData) {
        try {
            viewModel.copyAddressBasedOnDropDown(dynamicUITable, dynamicUITableList, screenToGetData);
            if (viewModel.getDynamicUITableLiveData() != null) {
                Observer copyAddressBasedOnDropDownObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        List<DynamicUITable> dynamicUITableList1 = (List<DynamicUITable>) o;
                        viewModel.getDynamicUITableLiveData().removeObserver(this);
                        dynamicUI(dynamicUITableList1);
                    }
                };
                viewModel.getDynamicUITableLiveData().observe(this, copyAddressBasedOnDropDownObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void nomineeDetailsValidationInJLG(DynamicUITable viewParameters, List<DynamicUITable> viewParametersList, XMLCustomSpinner customSpinner) {

        if (viewParameters.getFieldTag().equalsIgnoreCase(TAG_NAME_NOMINEE_KYC_TYPE)) {
            // TODO: nominee detail Fragment spinner select
            setNomineeScreenChangesBySpinner(viewParametersList, viewParameters, customSpinner);
        }
        else if (!TextUtils.isEmpty(viewParameters.getFieldTag()) && viewParameters.getFieldTag().equalsIgnoreCase(TAG_NAME_HOSPI_CASH_REQUIRED)) {
            if (viewParameters.getValue().equalsIgnoreCase("yes")) {
                List<ParameterInfo> parameterInfoList = new ArrayList<>();
                parameterInfoList.add(new ParameterInfo(TAG_NAME_HOSPI_NO_OF_YEARS, SCREEN_ID, "", true, true));
                parameterInfoList.add(new ParameterInfo(TAG_NAME_HOSPI_CASH_TYPE, SCREEN_ID, "", true, true));
                EnableOrDisableByFieldNameInDB(parameterInfoList, viewParametersList);
            } else {
                List<ParameterInfo> parameterInfoList = new ArrayList<>();
                parameterInfoList.add(new ParameterInfo(TAG_NAME_HOSPI_NO_OF_YEARS, SCREEN_ID, "", false, true));
                parameterInfoList.add(new ParameterInfo(TAG_NAME_HOSPI_CASH_TYPE, SCREEN_ID, "", false, true));
                EnableOrDisableByFieldNameInDB(parameterInfoList, viewParametersList);
            }
        }
    }


    private void addressDetailValidation(DynamicUITable dynamicUITable, List<DynamicUITable> dynamicUITableList) {
        try {
            viewModel.addressDetailValidation(dynamicUITable, dynamicUITableList);
            if (viewModel.getDynamicUITableLiveData() != null) {
                Observer addressDetailValidationObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        List<DynamicUITable> dynamicUITableListFromDB = (List<DynamicUITable>) o;
                        viewModel.getDynamicUITableLiveData().removeObserver(this);
                        dynamicUI(dynamicUITableListFromDB);
                    }
                };

                viewModel.getDynamicUITableLiveData().observe(this, addressDetailValidationObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void copyBusinessProofAddressBasedOnDropDown(DynamicUITable dynamicUITable, List<DynamicUITable> dynamicUITableList) {
        try {
            viewModel.copyBusinessProofAddressBasedOnDropDown(dynamicUITable, dynamicUITableList);
            if (viewModel.getDynamicUITableLiveData() != null) {
                Observer copyBusinessProofAddressBasedOnDropDownObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        List<DynamicUITable> dynamicUITableList1 = (List<DynamicUITable>) o;
                        viewModel.getDynamicUITableLiveData().removeObserver(this);
                        dynamicUI(dynamicUITableList1);
                    }
                };
                viewModel.getDynamicUITableLiveData().observe(this, copyBusinessProofAddressBasedOnDropDownObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void getCoApplicantDetails(DynamicUITable dynamicUITable, List<DynamicUITable> dynamicUITableList, String screenToGetData) {
        try {
            viewModel.getCoApplicantDetails(dynamicUITable, dynamicUITableList, screenToGetData);
            if (viewModel.getDynamicUITableLiveData() != null) {
                Observer getCoApplicantDetailsObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        List<DynamicUITable> dynamicUITableList1 = (List<DynamicUITable>) o;
                        viewModel.getDynamicUITableLiveData().removeObserver(this);
                        dynamicUI(dynamicUITableList1);
                    }
                };
                viewModel.getDynamicUITableLiveData().observe(this, getCoApplicantDetailsObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // TODO: to fetch reference Details
    private void getReferenceDetails(DynamicUITable dynamicUITable, List<DynamicUITable> dynamicUITableList, String screenToGetData) {
        try {
            viewModel.getReferenceDetails(dynamicUITable, dynamicUITableList, screenToGetData);
            if (viewModel.getDynamicUITableLiveData() != null) {
                Observer getReferenceDetailsObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        List<DynamicUITable> dynamicUITableList1 = (List<DynamicUITable>) o;
                        viewModel.getDynamicUITableLiveData().removeObserver(this);
//                        dynamicUI(dynamicUITableList1);
                        DynamicUITable dynamicUITable = getObjectByTAG(TAG_NAME_REFERENCE_TYPE, dynamicUITableList1);
                        if (dynamicUITable != null && !TextUtils.isEmpty(dynamicUITable.getValue())) {
                            setReferencesScreenChangesByReferenceType(dynamicUITable, dynamicUITable.getValue(),
                                    dynamicUITableList1);
                        } else {
                            dynamicUI(dynamicUITableList1);
                        }

                    }
                };
                viewModel.getDynamicUITableLiveData().observe(this, getReferenceDetailsObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void calculatePurchaseFrequency(DynamicUITable dynamicUITable, List<DynamicUITable> dynamicUITableList) {
        try {
            viewModel.calculatePurchaseFrequency(dynamicUITable, dynamicUITableList);
            if (viewModel.getDynamicUITableLiveData() != null) {
                Observer EnableOrDisableObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        List<DynamicUITable> dynamicUITableList1 = (List<DynamicUITable>) o;
                        viewModel.getDynamicUITableLiveData().removeObserver(this);
//                        dynamicUI(dynamicUITableList);
                        for (DynamicUITable dynamicUITable1 : dynamicUITableList1) {
                            setValueForSingleFieldInUI(dynamicUITable1, dynamicUITableList);
                        }
                    }
                };
                viewModel.getDynamicUITableLiveData().observe(this, EnableOrDisableObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void calculateNBI(DynamicUITable dynamicUITable, List<DynamicUITable> dynamicUITableList) {
        try {
            viewModel.calculateNBI(dynamicUITable, dynamicUITableList);
            if (viewModel.getDynamicUITableLiveData() != null) {
                Observer calculateNBIObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        List<DynamicUITable> dynamicUITableList = (List<DynamicUITable>) o;
                        viewModel.getDynamicUITableLiveData().removeObserver(this);
                        dynamicUI(dynamicUITableList);
                    }
                };
                viewModel.getDynamicUITableLiveData().observe(this, calculateNBIObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void calculateHouseIncomeSummary(DynamicUITable dynamicUITable, List<DynamicUITable> dynamicUITableList) {
        try {
            viewModel.calculateHouseIncomeSummary(dynamicUITable, dynamicUITableList);
            if (viewModel.getDynamicUITableLiveData() != null) {
                Observer calculateHouseIncomeSummaryObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        List<DynamicUITable> dynamicUITableList = (List<DynamicUITable>) o;
                        viewModel.getDynamicUITableLiveData().removeObserver(this);
                        dynamicUI(dynamicUITableList);
                    }
                };
                viewModel.getDynamicUITableLiveData().observe(this, calculateHouseIncomeSummaryObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void bankingHistoryCalculation(DynamicUITable dynamicUITable, List<DynamicUITable> dynamicUITableList) {
        try {
            viewModel.bankingHistoryCalculation(dynamicUITable, dynamicUITableList);
            if (viewModel.getDynamicUITableLiveData() != null) {
                Observer bankingHistoryCalculationObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        List<DynamicUITable> dynamicUITableListParentScreen = (List<DynamicUITable>) o;
                        viewModel.getDynamicUITableLiveData().removeObserver(this);

                        if (dynamicUITableListParentScreen != null && dynamicUITableListParentScreen.size() > 0) {
                            if (LOSBaseFragment.this instanceof ChildFragment) {
                                ChildFragment childFragment = (ChildFragment) LOSBaseFragment.this;
                                childFragment.callCloseDialogFragment();
                                FragmentToActivityInterface fragmentToActivityInterface = (FragmentToActivityInterface) getActivity();
                                fragmentToActivityInterface.valueFromFragment(dynamicUITableListParentScreen.get(0).getScreenID(), SCREEN_NAME,
                                        dynamicUITableListParentScreen.get(0).getScreenName(), dynamicUITableListParentScreen);
                            } else {

                                // TODO: Update UI
                                dynamicUI(dynamicUITableListParentScreen);

                                // TODO: save parent screen data
                                String submittedValues = getSubmittedValuesFromUI(dynamicUITableListParentScreen);
                                DynamicUITable dynamicUITable = dynamicUITableListParentScreen.get(dynamicUITableListParentScreen.size() - 1);

                                final RawDataTable rawDataTable = new RawDataTable(submittedValues, dynamicUITable.getScreenID()
                                        , dynamicUITable.getScreenName(), "", dynamicUITable.getClientID(),
                                        dynamicUITable.getLoanType(), dynamicUITable.getUser_id(),
                                        dynamicUITable.getModuleType(), dynamicUITable.getCoRelationID());

                                String dynamicUIRawData = new Gson().toJson(dynamicUITableListParentScreen);
                                rawDataTable.setDynamic_ui_rawdata(dynamicUIRawData);

                                // TODO: To save parent screen raw data
                                saveParentScreenRawData(rawDataTable, dynamicUITableListParentScreen,
                                        dynamicUITable, dynamicUITable.getFieldTag());
                            }

                        }

                    }
                };
                viewModel.getDynamicUITableLiveData().observe(this, bankingHistoryCalculationObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // TODO: APPLICANT LOAN PROPOSAL MSME & IL & AHL
    public void applicantLoanProposalScreenChangesByDropdown(DynamicUITable dynamicUITable, List<DynamicUITable> dynamicUITableList) {
        try {
            viewModel.applicantLoanProposalScreenChangesByDropdown(dynamicUITable, dynamicUITableList);
            if (viewModel.getDynamicUITableLiveData() != null) {
                Observer applicantLoanProposalScreenChangesByDropdownObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        List<DynamicUITable> dynamicUITableListParentScreen = (List<DynamicUITable>) o;
                        viewModel.getDynamicUITableLiveData().removeObserver(this);
                        if (dynamicUITableListParentScreen != null && dynamicUITableListParentScreen.size() > 0) {
                            dynamicUI(dynamicUITableListParentScreen);
                        }
                    }
                };
                viewModel.getDynamicUITableLiveData().observe(this, applicantLoanProposalScreenChangesByDropdownObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // TODO: APPLICANT LOAN PROPOSAL MSME
    public void loanApprovalScreenChangesByDropdown(DynamicUITable dynamicUITable, List<DynamicUITable> dynamicUITableList) {
        try {
            viewModel.loanApprovalScreenChangesByDropdown(dynamicUITable, dynamicUITableList);
            if (viewModel.getDynamicUITableLiveData() != null) {
                Observer loanApprovalScreenChangesByDropdownObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        List<DynamicUITable> dynamicUITableListParentScreen = (List<DynamicUITable>) o;
                        viewModel.getDynamicUITableLiveData().removeObserver(this);
                        if (dynamicUITableListParentScreen != null && dynamicUITableListParentScreen.size() > 0) {
                            dynamicUI(dynamicUITableListParentScreen);
                        }
                    }
                };
                viewModel.getDynamicUITableLiveData().observe(this, loanApprovalScreenChangesByDropdownObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // TODO: CASH FLOW SCREEN CHANGES MSME
    public void cashFlowScreenChangesByDropdown(DynamicUITable dynamicUITable, List<DynamicUITable> dynamicUITableList) {
        try {
            viewModel.cashFlowScreenChangesByDropdown(dynamicUITable, dynamicUITableList);
            if (viewModel.getDynamicUITableLiveData() != null) {
                Observer cashFlowScreenChangesByDropdownByDropdownObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        List<DynamicUITable> dynamicUITableListParentScreen = (List<DynamicUITable>) o;
                        viewModel.getDynamicUITableLiveData().removeObserver(this);
                        if (dynamicUITableListParentScreen != null && dynamicUITableListParentScreen.size() > 0) {
                            dynamicUI(dynamicUITableListParentScreen);

//                              getMaximumLoanAmount(dynamicUITable,dynamicUITableListParentScreen);//old
                            getMinAndMaximumLoanAmount(dynamicUITable, dynamicUITableListParentScreen);
                        }
                    }
                };
                viewModel.getDynamicUITableLiveData().observe(this, cashFlowScreenChangesByDropdownByDropdownObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void getMaximumLoanAmount(DynamicUITable dynamicUITable, List<DynamicUITable> dynamicUITableListFromDB) {
        try {
            viewModel.getMaximumLoanAmount(dynamicUITable, dynamicUITableListFromDB);
            if (viewModel.getIntegerLiveData() != null) {
                Observer getMaximumLoanAmountObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        int MAXIMUM_LOAN_AMOUNT = (int) o;
                        viewModel.getIntegerLiveData().removeObserver(this);

                        DynamicUITable recommendedLoanAmountObj = getObjectByTAG(TAG_NAME_RECOMMENDED_LOAN_AMOUNT_IN_CASH_FLOW_SUMMARY_MSME,
                                dynamicUITableListFromDB);

                        if (recommendedLoanAmountObj != null && !TextUtils.isEmpty(recommendedLoanAmountObj.getValue())) {

                            double RECOMMENDED_LOAN_AMOUNT = 0;
                            RECOMMENDED_LOAN_AMOUNT = Double.valueOf(recommendedLoanAmountObj.getValue());

                            if (RECOMMENDED_LOAN_AMOUNT > MAXIMUM_LOAN_AMOUNT) {
                                appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT,
                                        "Recommended Loan Amount : " + RECOMMENDED_LOAN_AMOUNT +
                                                ", It Should Not Be Greater Than" +
                                                " Maximum Scheme Amount : " + MAXIMUM_LOAN_AMOUNT, new ConfirmationDialog.ActionCallback() {
                                            @Override
                                            public void onAction() {
                                                // TODO: Updating Recommended Loan Amount
                                                recommendedLoanAmountObj.setValue(String.valueOf(MAXIMUM_LOAN_AMOUNT));
                                                updateDynamicUITable(dynamicUITableListFromDB, dynamicUITable.getScreenID());

                                            }
                                        });
                            } else if (RECOMMENDED_LOAN_AMOUNT < MSME_MINIMUM_LOAN_AMOUNT) {
                                appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT,
                                        "Recommended Loan Amount : " + RECOMMENDED_LOAN_AMOUNT +
                                                ", It Should Be Minimum " + MSME_MINIMUM_LOAN_AMOUNT
                                        , new ConfirmationDialog.ActionCallback() {
                                            @Override
                                            public void onAction() {
                                                // TODO: Updating Recommended Loan Amount
                                                recommendedLoanAmountObj.setValue(String.valueOf(MSME_MINIMUM_LOAN_AMOUNT));
                                                updateDynamicUITable(dynamicUITableListFromDB, dynamicUITable.getScreenID());

                                            }
                                        });
                            }
                        }

                    }
                };
                viewModel.getIntegerLiveData().observe(this, getMaximumLoanAmountObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void getMinAndMaximumLoanAmount(DynamicUITable dynamicUITable, List<DynamicUITable> dynamicUITableListFromDB) {
        try {
            viewModel.getMinAndMaximumLoanAmount(dynamicUITable, dynamicUITableListFromDB);
            if (viewModel.getProductMasterTableLiveData() != null) {
                Observer getMaximumLoanAmountObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        ProductMasterTable productMasterTable = (ProductMasterTable) o;
                        viewModel.getProductMasterTableLiveData().removeObserver(this);

                        int MAXIMUM_LOAN_AMOUNT = (int) Double.parseDouble(productMasterTable.getMaxLoanAmount());
                        int MINIMUM_LOAN_AMOUNT = (int) Double.parseDouble(productMasterTable.getMinLoanAmount());

                        if (dynamicUITable.getLoanType().equalsIgnoreCase(LOAN_NAME_MSME)) {

                            DynamicUITable recommendedLoanAmountObj = getObjectByTAG(TAG_NAME_RECOMMENDED_LOAN_AMOUNT_IN_CASH_FLOW_SUMMARY_MSME,
                                    dynamicUITableListFromDB);

                            if (recommendedLoanAmountObj != null && !TextUtils.isEmpty(recommendedLoanAmountObj.getValue())) {

                                double RECOMMENDED_LOAN_AMOUNT = 0;
                                RECOMMENDED_LOAN_AMOUNT = Double.valueOf(recommendedLoanAmountObj.getValue());

                                if (RECOMMENDED_LOAN_AMOUNT > MAXIMUM_LOAN_AMOUNT) {
                                    appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT,
                                            "Recommended Loan Amount : " + RECOMMENDED_LOAN_AMOUNT +
                                                    ", It Should Not Be Greater Than" +
                                                    " Maximum Scheme Amount : " + MAXIMUM_LOAN_AMOUNT, new ConfirmationDialog.ActionCallback() {
                                                @Override
                                                public void onAction() {
                                                    // TODO: Updating Recommended Loan Amount
                                                    recommendedLoanAmountObj.setValue(String.valueOf(MAXIMUM_LOAN_AMOUNT));
                                                    updateDynamicUITable(dynamicUITableListFromDB, dynamicUITable.getScreenID());

                                                }
                                            });
                                } else if (RECOMMENDED_LOAN_AMOUNT < MINIMUM_LOAN_AMOUNT) {
                                    appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT,
                                            "Recommended Loan Amount : " + RECOMMENDED_LOAN_AMOUNT +
                                                    ", It Should Be Minimum " + MINIMUM_LOAN_AMOUNT
                                            , new ConfirmationDialog.ActionCallback() {
                                                @Override
                                                public void onAction() {
                                                    // TODO: Updating Recommended Loan Amount
                                                    recommendedLoanAmountObj.setValue(String.valueOf(MINIMUM_LOAN_AMOUNT));
                                                    updateDynamicUITable(dynamicUITableListFromDB, dynamicUITable.getScreenID());

                                                }
                                            });
                                }
                            }
                        }
                        else if (dynamicUITable.getLoanType().equalsIgnoreCase(LOAN_NAME_INDIVIDUAL)) {

                            DynamicUITable recommendedLoanAmountObj = getObjectByTAG(TAG_NAME_LOAN_RECOMMENDED_AMOUNT,
                                    dynamicUITableListFromDB);

                            if (recommendedLoanAmountObj != null && !TextUtils.isEmpty(recommendedLoanAmountObj.getValue())) {

                                double RECOMMENDED_LOAN_AMOUNT = 0;
                                RECOMMENDED_LOAN_AMOUNT = Double.valueOf(recommendedLoanAmountObj.getValue());

                                if (RECOMMENDED_LOAN_AMOUNT > MAXIMUM_LOAN_AMOUNT) {
                                    appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT,
                                            "Recommended Loan Amount : " + RECOMMENDED_LOAN_AMOUNT +
                                                    ", It Should Not Be Greater Than" +
                                                    " Maximum Scheme Amount : " + MAXIMUM_LOAN_AMOUNT, new ConfirmationDialog.ActionCallback() {
                                                @Override
                                                public void onAction() {
                                                    // TODO: Updating Recommended Loan Amount
                                                    recommendedLoanAmountObj.setValue(String.valueOf(MAXIMUM_LOAN_AMOUNT));
                                                    updateDynamicUITable(dynamicUITableListFromDB, dynamicUITable.getScreenID());

                                                }
                                            });
                                } else if (RECOMMENDED_LOAN_AMOUNT < MINIMUM_LOAN_AMOUNT) {
                                    appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT,
                                            "Recommended Loan Amount : " + RECOMMENDED_LOAN_AMOUNT +
                                                    ", It Should Be Minimum " + MINIMUM_LOAN_AMOUNT
                                            , new ConfirmationDialog.ActionCallback() {
                                                @Override
                                                public void onAction() {
                                                    // TODO: Updating Recommended Loan Amount
                                                    recommendedLoanAmountObj.setValue(String.valueOf(MINIMUM_LOAN_AMOUNT));
                                                    updateDynamicUITable(dynamicUITableListFromDB, dynamicUITable.getScreenID());

                                                }
                                            });
                                }
                            }
                        }

                    }
                };
                viewModel.getProductMasterTableLiveData().observe(this, getMaximumLoanAmountObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // TODO: ITR SCREEN CALCULATION
    public void ITRCalculation(DynamicUITable dynamicUITable, List<DynamicUITable> dynamicUITableList) {
        try {
            viewModel.ITRCalculation(dynamicUITable, dynamicUITableList);
            if (viewModel.getDynamicUITableLiveData() != null) {
                Observer ITRCalculationObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        List<DynamicUITable> dynamicUITableListParentScreen = (List<DynamicUITable>) o;
                        viewModel.getDynamicUITableLiveData().removeObserver(this);

                        if (dynamicUITableListParentScreen != null && dynamicUITableListParentScreen.size() > 0) {
                            if (LOSBaseFragment.this instanceof ChildFragment) {
                                ChildFragment childFragment = (ChildFragment) LOSBaseFragment.this;
                                childFragment.callCloseDialogFragment();
                                FragmentToActivityInterface fragmentToActivityInterface = (FragmentToActivityInterface) getActivity();
                                fragmentToActivityInterface.valueFromFragment(dynamicUITableListParentScreen.get(0).getScreenID(),
                                        SCREEN_NAME,
                                        dynamicUITableListParentScreen.get(0).getScreenName(), dynamicUITableListParentScreen);
                            } else {

                                // TODO: Update UI
                                dynamicUI(dynamicUITableListParentScreen);

                                // TODO: save parent screen data
                                String submittedValues = getSubmittedValuesFromUI(dynamicUITableListParentScreen);
                                DynamicUITable dynamicUITable = dynamicUITableListParentScreen.get(dynamicUITableListParentScreen.size() - 1);

                                final RawDataTable rawDataTable = new RawDataTable(submittedValues, dynamicUITable.getScreenID()
                                        , dynamicUITable.getScreenName(), "", dynamicUITable.getClientID(),
                                        dynamicUITable.getLoanType(), dynamicUITable.getUser_id(),
                                        dynamicUITable.getModuleType(), dynamicUITable.getCoRelationID());

                                String dynamicUIRawData = new Gson().toJson(dynamicUITableListParentScreen);
                                rawDataTable.setDynamic_ui_rawdata(dynamicUIRawData);

                                // TODO: To save parent screen raw data
                                saveParentScreenRawData(rawDataTable, dynamicUITableListParentScreen,
                                        dynamicUITable, dynamicUITable.getFieldTag());
                            }

                        }

                    }
                };
                viewModel.getDynamicUITableLiveData().observe(this, ITRCalculationObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // TODO: MORTGAGE SCREEN CALCULATION
    public void mortgageCalculation(DynamicUITable dynamicUITable, List<DynamicUITable> dynamicUITableList) {
        try {
            viewModel.mortgageCalculation(dynamicUITable, dynamicUITableList);
            if (viewModel.getDynamicUITableLiveData() != null) {
                Observer mortgageCalculationObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        List<DynamicUITable> dynamicUITableListParentScreen = (List<DynamicUITable>) o;
                        viewModel.getDynamicUITableLiveData().removeObserver(this);

                        if (dynamicUITableListParentScreen != null && dynamicUITableListParentScreen.size() > 0) {
                            if (LOSBaseFragment.this instanceof ChildFragment) {
                                ChildFragment childFragment = (ChildFragment) LOSBaseFragment.this;
                                childFragment.callCloseDialogFragment();
                                FragmentToActivityInterface fragmentToActivityInterface = (FragmentToActivityInterface) getActivity();
                                fragmentToActivityInterface.valueFromFragment(dynamicUITableListParentScreen.get(0).getScreenID(), SCREEN_NAME,
                                        dynamicUITableListParentScreen.get(0).getScreenName(), dynamicUITableListParentScreen);
                            } else {

                                // TODO: Update UI
                                dynamicUI(dynamicUITableListParentScreen);

                                // TODO: save parent screen data
                                String submittedValues = getSubmittedValuesFromUI(dynamicUITableListParentScreen);
                                DynamicUITable dynamicUITable = dynamicUITableListParentScreen.get(dynamicUITableListParentScreen.size() - 1);

                                final RawDataTable rawDataTable = new RawDataTable(submittedValues, dynamicUITable.getScreenID()
                                        , dynamicUITable.getScreenName(), "", dynamicUITable.getClientID(),
                                        dynamicUITable.getLoanType(), dynamicUITable.getUser_id(),
                                        dynamicUITable.getModuleType(), dynamicUITable.getCoRelationID());

                                String dynamicUIRawData = new Gson().toJson(dynamicUITableListParentScreen);
                                rawDataTable.setDynamic_ui_rawdata(dynamicUIRawData);

                                // TODO: To save parent screen raw data
                                saveParentScreenRawData(rawDataTable, dynamicUITableListParentScreen,
                                        dynamicUITable, dynamicUITable.getFieldTag());
                            }

                        }
                    }
                };
                viewModel.getDynamicUITableLiveData().observe(this, mortgageCalculationObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // TODO: LOAN APPROVAL SCREEN CALCULATION
    public void loanApprovalCalculation(DynamicUITable dynamicUITable, List<DynamicUITable> dynamicUITableList) {
        try {
            viewModel.loanApprovalCalculation(dynamicUITable, dynamicUITableList);
            if (viewModel.getDynamicUITableLiveData() != null) {
                Observer loanApprovalCalculationObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        List<DynamicUITable> dynamicUITableListParentScreen = (List<DynamicUITable>) o;
                        viewModel.getDynamicUITableLiveData().removeObserver(this);

                        dynamicUI(dynamicUITableListParentScreen);
                    }
                };
                viewModel.getDynamicUITableLiveData().observe(this, loanApprovalCalculationObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // TODO: CENTER CREATION SCREEN VALIDATION
    public void centerCreationScreenValidation(DynamicUITable dynamicUITable, List<DynamicUITable> dynamicUITableList) {
        try {
            viewModel.centerCreationScreenValidation(dynamicUITable, dynamicUITableList);
            if (viewModel.getDynamicUITableLiveData() != null) {
                Observer observer = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        List<DynamicUITable> dynamicUITableListParentScreen = (List<DynamicUITable>) o;
                        viewModel.getDynamicUITableLiveData().removeObserver(this);

                        dynamicUI(dynamicUITableListParentScreen);
                    }
                };
                viewModel.getDynamicUITableLiveData().observe(this, observer);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // TODO: CASH FLOW SUMMARY SCREEN CALCULATION
    public void cashFlowSummaryCalculation(DynamicUITable dynamicUITable, List<DynamicUITable> dynamicUITableList) {
        try {
            viewModel.cashFlowSummaryCalculation(dynamicUITable, dynamicUITableList);
            if (viewModel.getDynamicUITableLiveData() != null) {
                Observer cashFlowSummaryCalculationObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        List<DynamicUITable> dynamicUITableListParentScreen = (List<DynamicUITable>) o;
                        viewModel.getDynamicUITableLiveData().removeObserver(this);

                        dynamicUI(dynamicUITableListParentScreen);

                        getMinAndMaximumLoanAmount(dynamicUITable, dynamicUITableListParentScreen);

                    }
                };
                viewModel.getDynamicUITableLiveData().observe(this, cashFlowSummaryCalculationObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    // TODO: HOUSE ASSET SCREEN CALCULATION
    public void houseAssetCalculation(DynamicUITable dynamicUITable, List<DynamicUITable> dynamicUITableList) {
        try {
            viewModel.houseAssetCalculation(dynamicUITable, dynamicUITableList);
            if (viewModel.getDynamicUITableLiveData() != null) {
                Observer houseAssetCalculationObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        List<DynamicUITable> dynamicUITableListParentScreen = (List<DynamicUITable>) o;
                        viewModel.getDynamicUITableLiveData().removeObserver(this);

                        if (dynamicUITableListParentScreen != null && dynamicUITableListParentScreen.size() > 0) {
                            if (LOSBaseFragment.this instanceof ChildFragment) {
                                ChildFragment childFragment = (ChildFragment) LOSBaseFragment.this;
                                childFragment.callCloseDialogFragment();
                                FragmentToActivityInterface fragmentToActivityInterface = (FragmentToActivityInterface) getActivity();
                                fragmentToActivityInterface.valueFromFragment(dynamicUITableListParentScreen.get(0).getScreenID(), SCREEN_NAME,
                                        dynamicUITableListParentScreen.get(0).getScreenName(), dynamicUITableListParentScreen);
                            } else {

                                // TODO: Update UI
                                dynamicUI(dynamicUITableListParentScreen);

                                // TODO: save parent screen data
                                String submittedValues = getSubmittedValuesFromUI(dynamicUITableListParentScreen);
                                DynamicUITable dynamicUITable = dynamicUITableListParentScreen.get(dynamicUITableListParentScreen.size() - 1);

                                final RawDataTable rawDataTable = new RawDataTable(submittedValues, dynamicUITable.getScreenID()
                                        , dynamicUITable.getScreenName(), "", dynamicUITable.getClientID(),
                                        dynamicUITable.getLoanType(), dynamicUITable.getUser_id(),
                                        dynamicUITable.getModuleType(), dynamicUITable.getCoRelationID());

                                String dynamicUIRawData = new Gson().toJson(dynamicUITableListParentScreen);
                                rawDataTable.setDynamic_ui_rawdata(dynamicUIRawData);

                                // TODO: To save parent screen raw data
                                saveParentScreenRawData(rawDataTable, dynamicUITableListParentScreen,
                                        dynamicUITable, dynamicUITable.getFieldTag());
                            }

                        }
                    }
                };
                viewModel.getDynamicUITableLiveData().observe(this, houseAssetCalculationObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // TODO: BUSINESS ASSET SCREEN CALCULATION IL
    public void businessAssetILCalculation(DynamicUITable dynamicUITable, List<DynamicUITable> dynamicUITableList) {
        try {
            viewModel.businessAssetILCalculation(dynamicUITable, dynamicUITableList);
            if (viewModel.getDynamicUITableLiveData() != null) {
                Observer businessAssetCalculationObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        List<DynamicUITable> dynamicUITableListParentScreen = (List<DynamicUITable>) o;
                        viewModel.getDynamicUITableLiveData().removeObserver(this);

                        if (dynamicUITableListParentScreen != null && dynamicUITableListParentScreen.size() > 0) {
                            if (LOSBaseFragment.this instanceof ChildFragment) {
                                ChildFragment childFragment = (ChildFragment) LOSBaseFragment.this;
                                childFragment.callCloseDialogFragment();
                                FragmentToActivityInterface fragmentToActivityInterface = (FragmentToActivityInterface) getActivity();
                                fragmentToActivityInterface.valueFromFragment(dynamicUITableListParentScreen.get(0).getScreenID(), SCREEN_NAME,
                                        dynamicUITableListParentScreen.get(0).getScreenName(), dynamicUITableListParentScreen);
                            } else {

                                // TODO: Update UI
                                dynamicUI(dynamicUITableListParentScreen);

                                // TODO: save parent screen data
                                String submittedValues = getSubmittedValuesFromUI(dynamicUITableListParentScreen);
                                DynamicUITable dynamicUITable = dynamicUITableListParentScreen.get(dynamicUITableListParentScreen.size() - 1);

                                final RawDataTable rawDataTable = new RawDataTable(submittedValues, dynamicUITable.getScreenID()
                                        , dynamicUITable.getScreenName(), "", dynamicUITable.getClientID(),
                                        dynamicUITable.getLoanType(), dynamicUITable.getUser_id(),
                                        dynamicUITable.getModuleType(), dynamicUITable.getCoRelationID());

                                String dynamicUIRawData = new Gson().toJson(dynamicUITableListParentScreen);
                                rawDataTable.setDynamic_ui_rawdata(dynamicUIRawData);

                                // TODO: To save parent screen raw data
                                saveParentScreenRawData(rawDataTable, dynamicUITableListParentScreen,
                                        dynamicUITable, dynamicUITable.getFieldTag());
                            }

                        }
                    }
                };
                viewModel.getDynamicUITableLiveData().observe(this, businessAssetCalculationObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    // TODO: BUSINESS ASSET SCREEN CALCULATION
    public void businessAssetMSMECalculation(DynamicUITable dynamicUITable, List<DynamicUITable> dynamicUITableList) {
        try {
            viewModel.businessAssetMSMECalculation(dynamicUITable, dynamicUITableList);
            if (viewModel.getDynamicUITableLiveData() != null) {
                Observer businessAssetCalculationObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        List<DynamicUITable> dynamicUITableListParentScreen = (List<DynamicUITable>) o;
                        viewModel.getDynamicUITableLiveData().removeObserver(this);

                        if (dynamicUITableListParentScreen != null && dynamicUITableListParentScreen.size() > 0) {
                            if (LOSBaseFragment.this instanceof ChildFragment) {
                                ChildFragment childFragment = (ChildFragment) LOSBaseFragment.this;
                                childFragment.callCloseDialogFragment();
                                FragmentToActivityInterface fragmentToActivityInterface = (FragmentToActivityInterface) getActivity();
                                fragmentToActivityInterface.valueFromFragment(dynamicUITableListParentScreen.get(0).getScreenID(), SCREEN_NAME,
                                        dynamicUITableListParentScreen.get(0).getScreenName(), dynamicUITableListParentScreen);
                            } else {

                                // TODO: Update UI
                                dynamicUI(dynamicUITableListParentScreen);

                                // TODO: save parent screen data
                                String submittedValues = getSubmittedValuesFromUI(dynamicUITableListParentScreen);
                                DynamicUITable dynamicUITable = dynamicUITableListParentScreen.get(dynamicUITableListParentScreen.size() - 1);

                                final RawDataTable rawDataTable = new RawDataTable(submittedValues, dynamicUITable.getScreenID()
                                        , dynamicUITable.getScreenName(), "", dynamicUITable.getClientID(),
                                        dynamicUITable.getLoanType(), dynamicUITable.getUser_id(),
                                        dynamicUITable.getModuleType(), dynamicUITable.getCoRelationID());

                                String dynamicUIRawData = new Gson().toJson(dynamicUITableListParentScreen);
                                rawDataTable.setDynamic_ui_rawdata(dynamicUIRawData);

                                // TODO: To save parent screen raw data
                                saveParentScreenRawData(rawDataTable, dynamicUITableListParentScreen,
                                        dynamicUITable, dynamicUITable.getFieldTag());
                            }

                        }
                    }
                };
                viewModel.getDynamicUITableLiveData().observe(this, businessAssetCalculationObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // TODO: INCOME ASSESSMENT SUMMARY SCREEN CALCULATION
    public void IncomeAssessmentSummaryCalculation(DynamicUITable dynamicUITable, List<DynamicUITable> dynamicUITableList) {
        try {
            viewModel.IncomeAssessmentSummaryCalculation(dynamicUITable, dynamicUITableList);
            if (viewModel.getDynamicUITableLiveData() != null) {
                Observer IncomeAssessmentSummaryCalculationObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        List<DynamicUITable> dynamicUITableListParentScreen = (List<DynamicUITable>) o;
                        viewModel.getDynamicUITableLiveData().removeObserver(this);

                        // TODO: Update UI
                        dynamicUI(dynamicUITableListParentScreen);

                        // TODO: save parent screen data
                        String submittedValues = getSubmittedValuesFromUI(dynamicUITableListParentScreen);
                        DynamicUITable dynamicUITable = dynamicUITableListParentScreen.get(dynamicUITableListParentScreen.size() - 1);

                        final RawDataTable rawDataTable = new RawDataTable(submittedValues, dynamicUITable.getScreenID()
                                , dynamicUITable.getScreenName(), "", dynamicUITable.getClientID(),
                                dynamicUITable.getLoanType(), dynamicUITable.getUser_id(),
                                dynamicUITable.getModuleType(), dynamicUITable.getCoRelationID());

                        String dynamicUIRawData = new Gson().toJson(dynamicUITableListParentScreen);
                        rawDataTable.setDynamic_ui_rawdata(dynamicUIRawData);

                        // TODO: To save parent screen raw data
                        saveParentScreenRawData(rawDataTable, dynamicUITableListParentScreen,
                                dynamicUITable, dynamicUITable.getFieldTag());
                    }
                };
                viewModel.getDynamicUITableLiveData().observe(this, IncomeAssessmentSummaryCalculationObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // TODO: PRODUCT ESTIMATE DETAIL SCREEN CALCULATION
    public void ProductEstimateDetailCalculation(DynamicUITable dynamicUITable, List<DynamicUITable> dynamicUITableList) {
        try {
            viewModel.ProductEstimateDetailCalculation(dynamicUITable, dynamicUITableList);
            if (viewModel.getDynamicUITableLiveData() != null) {
                Observer ProductEstimateDetailCalculationObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        List<DynamicUITable> dynamicUITableListParentScreen = (List<DynamicUITable>) o;
                        viewModel.getDynamicUITableLiveData().removeObserver(this);

                        if (dynamicUITableListParentScreen != null && dynamicUITableListParentScreen.size() > 0) {
                            if (LOSBaseFragment.this instanceof ChildFragment) {
                                ChildFragment childFragment = (ChildFragment) LOSBaseFragment.this;
                                childFragment.callCloseDialogFragment();

                                FragmentToActivityInterface fragmentToActivityInterface = (FragmentToActivityInterface) getActivity();
                                fragmentToActivityInterface.valueFromFragment(dynamicUITableListParentScreen.get(0).getScreenID(), SCREEN_NAME,
                                        dynamicUITableListParentScreen.get(0).getScreenName(), dynamicUITableListParentScreen);
                            } else {

                                // TODO: Update UI
                                dynamicUI(dynamicUITableListParentScreen);

                                // TODO: save parent screen data
                                String submittedValues = getSubmittedValuesFromUI(dynamicUITableListParentScreen);
                                DynamicUITable dynamicUITable = dynamicUITableListParentScreen.get(dynamicUITableListParentScreen.size() - 1);

                                final RawDataTable rawDataTable = new RawDataTable(submittedValues, dynamicUITable.getScreenID()
                                        , dynamicUITable.getScreenName(), "", dynamicUITable.getClientID(),
                                        dynamicUITable.getLoanType(), dynamicUITable.getUser_id(),
                                        dynamicUITable.getModuleType(), dynamicUITable.getCoRelationID());

                                String dynamicUIRawData = new Gson().toJson(dynamicUITableListParentScreen);
                                rawDataTable.setDynamic_ui_rawdata(dynamicUIRawData);

                                // TODO: To save parent screen raw data
                                saveParentScreenRawData(rawDataTable, dynamicUITableListParentScreen,
                                        dynamicUITable, dynamicUITable.getFieldTag());
                            }

                        }

                    }
                };
                viewModel.getDynamicUITableLiveData().observe(this, ProductEstimateDetailCalculationObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // TODO: SERVICE ESTIMATE DETAIL SCREEN CALCULATION
    public void ServiceEstimateDetailCalculation(DynamicUITable dynamicUITable, List<DynamicUITable> dynamicUITableList) {
        try {
            viewModel.ServiceEstimateDetailCalculation(dynamicUITable, dynamicUITableList);
            if (viewModel.getDynamicUITableLiveData() != null) {
                Observer ServiceEstimateDetailCalculationObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        List<DynamicUITable> dynamicUITableListParentScreen = (List<DynamicUITable>) o;
                        viewModel.getDynamicUITableLiveData().removeObserver(this);

                        if (dynamicUITableListParentScreen != null && dynamicUITableListParentScreen.size() > 0) {
                            if (LOSBaseFragment.this instanceof ChildFragment) {
                                ChildFragment childFragment = (ChildFragment) LOSBaseFragment.this;
                                childFragment.callCloseDialogFragment();
                                FragmentToActivityInterface fragmentToActivityInterface = (FragmentToActivityInterface) getActivity();
                                fragmentToActivityInterface.valueFromFragment(dynamicUITableListParentScreen.get(0).getScreenID(), SCREEN_NAME,
                                        dynamicUITableListParentScreen.get(0).getScreenName(), dynamicUITableListParentScreen);
                            } else {

                                // TODO: Update UI
                                dynamicUI(dynamicUITableListParentScreen);

                                // TODO: save parent screen data
                                String submittedValues = getSubmittedValuesFromUI(dynamicUITableListParentScreen);
                                DynamicUITable dynamicUITable = dynamicUITableListParentScreen.get(dynamicUITableListParentScreen.size() - 1);

                                final RawDataTable rawDataTable = new RawDataTable(submittedValues, dynamicUITable.getScreenID()
                                        , dynamicUITable.getScreenName(), "", dynamicUITable.getClientID(),
                                        dynamicUITable.getLoanType(), dynamicUITable.getUser_id(),
                                        dynamicUITable.getModuleType(), dynamicUITable.getCoRelationID());

                                String dynamicUIRawData = new Gson().toJson(dynamicUITableListParentScreen);
                                rawDataTable.setDynamic_ui_rawdata(dynamicUIRawData);

                                // TODO: To save parent screen raw data
                                saveParentScreenRawData(rawDataTable, dynamicUITableListParentScreen,
                                        dynamicUITable, dynamicUITable.getFieldTag());
                            }

                        }

                    }
                };
                viewModel.getDynamicUITableLiveData().observe(this, ServiceEstimateDetailCalculationObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // TODO: PRODUCT ESTIMATE SCREEN CALCULATION
    public void ProductEstimateCalculation(DynamicUITable dynamicUITable, List<DynamicUITable> dynamicUITableList) {
        try {
            viewModel.ProductEstimateCalculation(dynamicUITable, dynamicUITableList);
            if (viewModel.getDynamicUITableLiveData() != null) {
                Observer ProductEstimateCalculationObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        List<DynamicUITable> dynamicUITableListParentScreen = (List<DynamicUITable>) o;
                        viewModel.getDynamicUITableLiveData().removeObserver(this);

                        if (dynamicUITableListParentScreen != null && dynamicUITableListParentScreen.size() > 0) {

                            // TODO: Update UI
                            dynamicUI(dynamicUITableListParentScreen);

                            // TODO: save parent screen data
                            String submittedValues = getSubmittedValuesFromUI(dynamicUITableListParentScreen);
                            DynamicUITable dynamicUITable = dynamicUITableListParentScreen.get(dynamicUITableListParentScreen.size() - 1);

                            final RawDataTable rawDataTable = new RawDataTable(submittedValues, dynamicUITable.getScreenID()
                                    , dynamicUITable.getScreenName(), "", dynamicUITable.getClientID(),
                                    dynamicUITable.getLoanType(), dynamicUITable.getUser_id(),
                                    dynamicUITable.getModuleType(), dynamicUITable.getCoRelationID());

                            String dynamicUIRawData = new Gson().toJson(dynamicUITableListParentScreen);
                            rawDataTable.setDynamic_ui_rawdata(dynamicUIRawData);

                            // TODO: To save parent screen raw data
                            saveParentScreenRawData(rawDataTable, dynamicUITableListParentScreen,
                                    dynamicUITable, dynamicUITable.getFieldTag());
                        }


                    }
                };
                viewModel.getDynamicUITableLiveData().observe(this, ProductEstimateCalculationObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // TODO: SERVICE ESTIMATE SCREEN CALCULATION
    public void ServiceEstimateCalculation(DynamicUITable dynamicUITable, List<DynamicUITable> dynamicUITableList) {
        try {
            viewModel.ServiceEstimateCalculation(dynamicUITable, dynamicUITableList);
            if (viewModel.getDynamicUITableLiveData() != null) {
                Observer ServiceEstimateCalculationObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        List<DynamicUITable> dynamicUITableListParentScreen = (List<DynamicUITable>) o;
                        viewModel.getDynamicUITableLiveData().removeObserver(this);

                        if (dynamicUITableListParentScreen != null && dynamicUITableListParentScreen.size() > 0) {

                            // TODO: Update UI
                            dynamicUI(dynamicUITableListParentScreen);

                            // TODO: save parent screen data
                            String submittedValues = getSubmittedValuesFromUI(dynamicUITableListParentScreen);
                            DynamicUITable dynamicUITable = dynamicUITableListParentScreen.get(dynamicUITableListParentScreen.size() - 1);

                            final RawDataTable rawDataTable = new RawDataTable(submittedValues, dynamicUITable.getScreenID()
                                    , dynamicUITable.getScreenName(), "", dynamicUITable.getClientID(),
                                    dynamicUITable.getLoanType(), dynamicUITable.getUser_id(),
                                    dynamicUITable.getModuleType(), dynamicUITable.getCoRelationID());

                            String dynamicUIRawData = new Gson().toJson(dynamicUITableListParentScreen);
                            rawDataTable.setDynamic_ui_rawdata(dynamicUIRawData);

                            // TODO: To save parent screen raw data
                            saveParentScreenRawData(rawDataTable, dynamicUITableListParentScreen,
                                    dynamicUITable, dynamicUITable.getFieldTag());
                        }
                    }
                };
                viewModel.getDynamicUITableLiveData().observe(this, ServiceEstimateCalculationObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void salesBillsCalculation(DynamicUITable dynamicUITable, List<DynamicUITable> dynamicUITableList) {
        try {
            viewModel.salesBillsCalculation(dynamicUITable, dynamicUITableList);
            if (viewModel.getDynamicUITableLiveData() != null) {
                Observer salesBillsCalculationObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        List<DynamicUITable> dynamicUITableListParentScreen = (List<DynamicUITable>) o;
                        viewModel.getDynamicUITableLiveData().removeObserver(this);

                        if (dynamicUITableListParentScreen != null && dynamicUITableListParentScreen.size() > 0) {
                            if (LOSBaseFragment.this instanceof ChildFragment) {
                                ChildFragment childFragment = (ChildFragment) LOSBaseFragment.this;
                                childFragment.callCloseDialogFragment();
                                FragmentToActivityInterface fragmentToActivityInterface = (FragmentToActivityInterface) getActivity();
                                fragmentToActivityInterface.valueFromFragment(dynamicUITableListParentScreen.get(0).getScreenID(), SCREEN_NAME,
                                        dynamicUITableListParentScreen.get(0).getScreenName(), dynamicUITableListParentScreen);
                            } else {

                                // TODO: Update UI
                                dynamicUI(dynamicUITableListParentScreen);

                                // TODO: save parent screen data
                                String submittedValues = getSubmittedValuesFromUI(dynamicUITableListParentScreen);
                                DynamicUITable dynamicUITable = dynamicUITableListParentScreen.get(dynamicUITableListParentScreen.size() - 1);

                                final RawDataTable rawDataTable = new RawDataTable(submittedValues, dynamicUITable.getScreenID()
                                        , dynamicUITable.getScreenName(), "", dynamicUITable.getClientID(),
                                        dynamicUITable.getLoanType(), dynamicUITable.getUser_id(),
                                        dynamicUITable.getModuleType(), dynamicUITable.getCoRelationID());

                                String dynamicUIRawData = new Gson().toJson(dynamicUITableListParentScreen);
                                rawDataTable.setDynamic_ui_rawdata(dynamicUIRawData);

                                // TODO: To save parent screen raw data
                                saveParentScreenRawData(rawDataTable, dynamicUITableListParentScreen,
                                        dynamicUITable, dynamicUITable.getFieldTag());
                            }

                        }

                    }
                };
                viewModel.getDynamicUITableLiveData().observe(this, salesBillsCalculationObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void purchaseBillsCalculation(DynamicUITable dynamicUITable, List<DynamicUITable> dynamicUITableList) {
        try {
            viewModel.purchaseBillsCalculation(dynamicUITable, dynamicUITableList);
            if (viewModel.getDynamicUITableLiveData() != null) {
                Observer purchaseBillsCalculationObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        List<DynamicUITable> dynamicUITableListParentScreen = (List<DynamicUITable>) o;
                        viewModel.getDynamicUITableLiveData().removeObserver(this);

                        if (dynamicUITableListParentScreen != null && dynamicUITableListParentScreen.size() > 0) {
                            if (LOSBaseFragment.this instanceof ChildFragment) {
                                ChildFragment childFragment = (ChildFragment) LOSBaseFragment.this;
                                childFragment.callCloseDialogFragment();
                                FragmentToActivityInterface fragmentToActivityInterface = (FragmentToActivityInterface) getActivity();
                                fragmentToActivityInterface.valueFromFragment(dynamicUITableListParentScreen.get(0).getScreenID(), SCREEN_NAME,
                                        dynamicUITableListParentScreen.get(0).getScreenName(), dynamicUITableListParentScreen);
                            } else {

                                // TODO: Update UI
                                dynamicUI(dynamicUITableListParentScreen);

                                // TODO: save parent screen data
                                String submittedValues = getSubmittedValuesFromUI(dynamicUITableListParentScreen);
                                DynamicUITable dynamicUITable = dynamicUITableListParentScreen.get(dynamicUITableListParentScreen.size() - 1);

                                final RawDataTable rawDataTable = new RawDataTable(submittedValues, dynamicUITable.getScreenID()
                                        , dynamicUITable.getScreenName(), "", dynamicUITable.getClientID(),
                                        dynamicUITable.getLoanType(), dynamicUITable.getUser_id(),
                                        dynamicUITable.getModuleType(), dynamicUITable.getCoRelationID());

                                String dynamicUIRawData = new Gson().toJson(dynamicUITableListParentScreen);
                                rawDataTable.setDynamic_ui_rawdata(dynamicUIRawData);

                                // TODO: To save parent screen raw data
                                saveParentScreenRawData(rawDataTable, dynamicUITableListParentScreen,
                                        dynamicUITable, dynamicUITable.getFieldTag());
                            }

                        }

                    }
                };
                viewModel.getDynamicUITableLiveData().observe(this, purchaseBillsCalculationObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void directBusinessExpenseCalculation(DynamicUITable dynamicUITable, List<DynamicUITable> dynamicUITableList) {
        try {
            viewModel.directBusinessExpenseCalculation(dynamicUITable, dynamicUITableList);
            if (viewModel.getDynamicUITableLiveData() != null) {
                Observer directBusinessExpenseCalculationObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        List<DynamicUITable> dynamicUITableListParentScreen = (List<DynamicUITable>) o;
                        viewModel.getDynamicUITableLiveData().removeObserver(this);

                        if (dynamicUITableListParentScreen != null && dynamicUITableListParentScreen.size() > 0) {
                            if (LOSBaseFragment.this instanceof ChildFragment) {
                                ChildFragment childFragment = (ChildFragment) LOSBaseFragment.this;
                                childFragment.callCloseDialogFragment();
                                FragmentToActivityInterface fragmentToActivityInterface = (FragmentToActivityInterface) getActivity();
                                fragmentToActivityInterface.valueFromFragment(dynamicUITableListParentScreen.get(0).getScreenID(), SCREEN_NAME,
                                        dynamicUITableListParentScreen.get(0).getScreenName(), dynamicUITableListParentScreen);
                            } else {

                                // TODO: Update UI
                                dynamicUI(dynamicUITableListParentScreen);

                                // TODO: save parent screen data
                                String submittedValues = getSubmittedValuesFromUI(dynamicUITableListParentScreen);
                                DynamicUITable dynamicUITable = dynamicUITableListParentScreen.get(dynamicUITableListParentScreen.size() - 1);

                                final RawDataTable rawDataTable = new RawDataTable(submittedValues, dynamicUITable.getScreenID()
                                        , dynamicUITable.getScreenName(), "", dynamicUITable.getClientID(),
                                        dynamicUITable.getLoanType(), dynamicUITable.getUser_id(),
                                        dynamicUITable.getModuleType(), dynamicUITable.getCoRelationID());

                                String dynamicUIRawData = new Gson().toJson(dynamicUITableListParentScreen);
                                rawDataTable.setDynamic_ui_rawdata(dynamicUIRawData);

                                // TODO: To save parent screen raw data
                                saveParentScreenRawData(rawDataTable, dynamicUITableListParentScreen,
                                        dynamicUITable, dynamicUITable.getFieldTag());
                            }

                        }

                    }
                };
                viewModel.getDynamicUITableLiveData().observe(this, directBusinessExpenseCalculationObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void hypothecationMsmeCalculation(DynamicUITable dynamicUITable, List<DynamicUITable> dynamicUITableList) {
        try {
            viewModel.hypothecationMsmeCalculation(dynamicUITable, dynamicUITableList);
            if (viewModel.getDynamicUITableLiveData() != null) {
                Observer houseLiabilitiesMsmeCalculationObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        List<DynamicUITable> dynamicUITableListParentScreen = (List<DynamicUITable>) o;
                        viewModel.getDynamicUITableLiveData().removeObserver(this);

                        if (dynamicUITableListParentScreen != null && dynamicUITableListParentScreen.size() > 0) {
                            if (LOSBaseFragment.this instanceof ChildFragment) {
                                ChildFragment childFragment = (ChildFragment) LOSBaseFragment.this;
                                childFragment.callCloseDialogFragment();
                                FragmentToActivityInterface fragmentToActivityInterface = (FragmentToActivityInterface) getActivity();
                                fragmentToActivityInterface.valueFromFragment(dynamicUITableListParentScreen.get(0).getScreenID(), SCREEN_NAME,
                                        dynamicUITableListParentScreen.get(0).getScreenName(), dynamicUITableListParentScreen);
                            } else {

                                // TODO: Update UI
                                dynamicUI(dynamicUITableListParentScreen);

                                // TODO: save parent screen data
                                String submittedValues = getSubmittedValuesFromUI(dynamicUITableListParentScreen);
                                DynamicUITable dynamicUITable = dynamicUITableListParentScreen.get(dynamicUITableListParentScreen.size() - 1);

                                final RawDataTable rawDataTable = new RawDataTable(submittedValues, dynamicUITable.getScreenID()
                                        , dynamicUITable.getScreenName(), "", dynamicUITable.getClientID(),
                                        dynamicUITable.getLoanType(), dynamicUITable.getUser_id(),
                                        dynamicUITable.getModuleType(), dynamicUITable.getCoRelationID());

                                String dynamicUIRawData = new Gson().toJson(dynamicUITableListParentScreen);
                                rawDataTable.setDynamic_ui_rawdata(dynamicUIRawData);

                                // TODO: To save parent screen raw data
                                saveParentScreenRawData(rawDataTable, dynamicUITableListParentScreen,
                                        dynamicUITable, dynamicUITable.getFieldTag());
                            }

                        }

                    }
                };
                viewModel.getDynamicUITableLiveData().observe(this, houseLiabilitiesMsmeCalculationObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void houseLiabilitiesILCalculation(DynamicUITable dynamicUITable, List<DynamicUITable> dynamicUITableList) {
        try {
            viewModel.houseLiabilitiesILCalculation(dynamicUITable, dynamicUITableList);
            if (viewModel.getDynamicUITableLiveData() != null) {
                Observer houseLiabilitiesILCalculationObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        List<DynamicUITable> dynamicUITableListParentScreen = (List<DynamicUITable>) o;
                        viewModel.getDynamicUITableLiveData().removeObserver(this);

                        if (dynamicUITableListParentScreen != null && dynamicUITableListParentScreen.size() > 0) {
                            if (LOSBaseFragment.this instanceof ChildFragment) {
                                ChildFragment childFragment = (ChildFragment) LOSBaseFragment.this;
                                childFragment.callCloseDialogFragment();
                                FragmentToActivityInterface fragmentToActivityInterface = (FragmentToActivityInterface) getActivity();
                                fragmentToActivityInterface.valueFromFragment(dynamicUITableListParentScreen.get(0).getScreenID(), SCREEN_NAME,
                                        dynamicUITableListParentScreen.get(0).getScreenName(), dynamicUITableListParentScreen);
                            } else {

                                // TODO: Update UI
                                dynamicUI(dynamicUITableListParentScreen);

                                // TODO: save parent screen data
                                String submittedValues = getSubmittedValuesFromUI(dynamicUITableListParentScreen);
                                DynamicUITable dynamicUITable = dynamicUITableListParentScreen.get(dynamicUITableListParentScreen.size() - 1);

                                final RawDataTable rawDataTable = new RawDataTable(submittedValues, dynamicUITable.getScreenID()
                                        , dynamicUITable.getScreenName(), "", dynamicUITable.getClientID(),
                                        dynamicUITable.getLoanType(), dynamicUITable.getUser_id(),
                                        dynamicUITable.getModuleType(), dynamicUITable.getCoRelationID());

                                String dynamicUIRawData = new Gson().toJson(dynamicUITableListParentScreen);
                                rawDataTable.setDynamic_ui_rawdata(dynamicUIRawData);

                                // TODO: To save parent screen raw data
                                saveParentScreenRawData(rawDataTable, dynamicUITableListParentScreen,
                                        dynamicUITable, dynamicUITable.getFieldTag());
                            }

                        }

                    }
                };
                viewModel.getDynamicUITableLiveData().observe(this, houseLiabilitiesILCalculationObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void houseLiabilitiesMsmeCalculation(DynamicUITable dynamicUITable, List<DynamicUITable> dynamicUITableList) {
        try {
            viewModel.houseLiabilitiesMsmeCalculation(dynamicUITable, dynamicUITableList);
            if (viewModel.getDynamicUITableLiveData() != null) {
                Observer houseLiabilitiesMsmeCalculationObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        List<DynamicUITable> dynamicUITableListParentScreen = (List<DynamicUITable>) o;
                        viewModel.getDynamicUITableLiveData().removeObserver(this);

                        if (dynamicUITableListParentScreen != null && dynamicUITableListParentScreen.size() > 0) {
                            if (LOSBaseFragment.this instanceof ChildFragment) {
                                ChildFragment childFragment = (ChildFragment) LOSBaseFragment.this;
                                childFragment.callCloseDialogFragment();
                                FragmentToActivityInterface fragmentToActivityInterface = (FragmentToActivityInterface) getActivity();
                                fragmentToActivityInterface.valueFromFragment(dynamicUITableListParentScreen.get(0).getScreenID(), SCREEN_NAME,
                                        dynamicUITableListParentScreen.get(0).getScreenName(), dynamicUITableListParentScreen);
                            } else {

                                // TODO: Update UI
                                dynamicUI(dynamicUITableListParentScreen);

                                // TODO: save parent screen data
                                String submittedValues = getSubmittedValuesFromUI(dynamicUITableListParentScreen);
                                DynamicUITable dynamicUITable = dynamicUITableListParentScreen.get(dynamicUITableListParentScreen.size() - 1);

                                final RawDataTable rawDataTable = new RawDataTable(submittedValues, dynamicUITable.getScreenID()
                                        , dynamicUITable.getScreenName(), "", dynamicUITable.getClientID(),
                                        dynamicUITable.getLoanType(), dynamicUITable.getUser_id(),
                                        dynamicUITable.getModuleType(), dynamicUITable.getCoRelationID());

                                String dynamicUIRawData = new Gson().toJson(dynamicUITableListParentScreen);
                                rawDataTable.setDynamic_ui_rawdata(dynamicUIRawData);

                                // TODO: To save parent screen raw data
                                saveParentScreenRawData(rawDataTable, dynamicUITableListParentScreen,
                                        dynamicUITable, dynamicUITable.getFieldTag());
                            }

                        }

                    }
                };
                viewModel.getDynamicUITableLiveData().observe(this, houseLiabilitiesMsmeCalculationObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void houseIncomeILCalculation(DynamicUITable dynamicUITable, List<DynamicUITable> dynamicUITableList) {
        try {
            viewModel.houseIncomeILCalculation(dynamicUITable, dynamicUITableList);
            if (viewModel.getDynamicUITableLiveData() != null) {
                Observer houseIncomeILCalculationObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        List<DynamicUITable> dynamicUITableListParentScreen = (List<DynamicUITable>) o;
                        viewModel.getDynamicUITableLiveData().removeObserver(this);

                        if (dynamicUITableListParentScreen != null && dynamicUITableListParentScreen.size() > 0) {
                            if (LOSBaseFragment.this instanceof ChildFragment) {
                                ChildFragment childFragment = (ChildFragment) LOSBaseFragment.this;
                                childFragment.callCloseDialogFragment();
                                FragmentToActivityInterface fragmentToActivityInterface = (FragmentToActivityInterface) getActivity();
                                fragmentToActivityInterface.valueFromFragment(dynamicUITableListParentScreen.get(0).getScreenID(), SCREEN_NAME,
                                        dynamicUITableListParentScreen.get(0).getScreenName(), dynamicUITableListParentScreen);
                            } else {

                                // TODO: Update UI
                                dynamicUI(dynamicUITableListParentScreen);

                                // TODO: save parent screen data
                                String submittedValues = getSubmittedValuesFromUI(dynamicUITableListParentScreen);
                                DynamicUITable dynamicUITable = dynamicUITableListParentScreen.get(dynamicUITableListParentScreen.size() - 1);

                                final RawDataTable rawDataTable = new RawDataTable(submittedValues, dynamicUITable.getScreenID()
                                        , dynamicUITable.getScreenName(), "", dynamicUITable.getClientID(),
                                        dynamicUITable.getLoanType(), dynamicUITable.getUser_id(),
                                        dynamicUITable.getModuleType(), dynamicUITable.getCoRelationID());

                                String dynamicUIRawData = new Gson().toJson(dynamicUITableListParentScreen);
                                rawDataTable.setDynamic_ui_rawdata(dynamicUIRawData);

                                // TODO: To save parent screen raw data
                                saveParentScreenRawData(rawDataTable, dynamicUITableListParentScreen,
                                        dynamicUITable, dynamicUITable.getFieldTag());
                            }

                        }

                    }
                };
                viewModel.getDynamicUITableLiveData().observe(this, houseIncomeILCalculationObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void businessLiabilitiesILCalculation(DynamicUITable dynamicUITable, List<DynamicUITable> dynamicUITableList) {
        try {
            viewModel.businessLiabilitiesILCalculation(dynamicUITable, dynamicUITableList);
            if (viewModel.getDynamicUITableLiveData() != null) {
                Observer businessLiabilitiesMsmeCalculationObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        List<DynamicUITable> dynamicUITableListParentScreen = (List<DynamicUITable>) o;
                        viewModel.getDynamicUITableLiveData().removeObserver(this);

                        if (dynamicUITableListParentScreen != null && dynamicUITableListParentScreen.size() > 0) {
                            if (LOSBaseFragment.this instanceof ChildFragment) {
                                ChildFragment childFragment = (ChildFragment) LOSBaseFragment.this;
                                childFragment.callCloseDialogFragment();
                                FragmentToActivityInterface fragmentToActivityInterface = (FragmentToActivityInterface) getActivity();
                                fragmentToActivityInterface.valueFromFragment(dynamicUITableListParentScreen.get(0).getScreenID(), SCREEN_NAME,
                                        dynamicUITableListParentScreen.get(0).getScreenName(), dynamicUITableListParentScreen);
                            } else {

                                // TODO: Update UI
                                dynamicUI(dynamicUITableListParentScreen);

                                // TODO: save parent screen data
                                String submittedValues = getSubmittedValuesFromUI(dynamicUITableListParentScreen);
                                DynamicUITable dynamicUITable = dynamicUITableListParentScreen.get(dynamicUITableListParentScreen.size() - 1);

                                final RawDataTable rawDataTable = new RawDataTable(submittedValues, dynamicUITable.getScreenID()
                                        , dynamicUITable.getScreenName(), "", dynamicUITable.getClientID(),
                                        dynamicUITable.getLoanType(), dynamicUITable.getUser_id(),
                                        dynamicUITable.getModuleType(), dynamicUITable.getCoRelationID());

                                String dynamicUIRawData = new Gson().toJson(dynamicUITableListParentScreen);
                                rawDataTable.setDynamic_ui_rawdata(dynamicUIRawData);

                                // TODO: To save parent screen raw data
                                saveParentScreenRawData(rawDataTable, dynamicUITableListParentScreen,
                                        dynamicUITable, dynamicUITable.getFieldTag());
                            }

                        }

                    }
                };
                viewModel.getDynamicUITableLiveData().observe(this, businessLiabilitiesMsmeCalculationObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void businessLiabilitiesMsmeCalculation(DynamicUITable dynamicUITable, List<DynamicUITable> dynamicUITableList) {
        try {
            viewModel.businessLiabilitiesMsmeCalculation(dynamicUITable, dynamicUITableList);
            if (viewModel.getDynamicUITableLiveData() != null) {
                Observer businessLiabilitiesMsmeCalculationObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        List<DynamicUITable> dynamicUITableListParentScreen = (List<DynamicUITable>) o;
                        viewModel.getDynamicUITableLiveData().removeObserver(this);

                        if (dynamicUITableListParentScreen != null && dynamicUITableListParentScreen.size() > 0) {
                            if (LOSBaseFragment.this instanceof ChildFragment) {
                                ChildFragment childFragment = (ChildFragment) LOSBaseFragment.this;
                                childFragment.callCloseDialogFragment();
                                FragmentToActivityInterface fragmentToActivityInterface = (FragmentToActivityInterface) getActivity();
                                fragmentToActivityInterface.valueFromFragment(dynamicUITableListParentScreen.get(0).getScreenID(), SCREEN_NAME,
                                        dynamicUITableListParentScreen.get(0).getScreenName(), dynamicUITableListParentScreen);
                            } else {

                                // TODO: Update UI
                                dynamicUI(dynamicUITableListParentScreen);

                                // TODO: save parent screen data
                                String submittedValues = getSubmittedValuesFromUI(dynamicUITableListParentScreen);
                                DynamicUITable dynamicUITable = dynamicUITableListParentScreen.get(dynamicUITableListParentScreen.size() - 1);

                                final RawDataTable rawDataTable = new RawDataTable(submittedValues, dynamicUITable.getScreenID()
                                        , dynamicUITable.getScreenName(), "", dynamicUITable.getClientID(),
                                        dynamicUITable.getLoanType(), dynamicUITable.getUser_id(),
                                        dynamicUITable.getModuleType(), dynamicUITable.getCoRelationID());

                                String dynamicUIRawData = new Gson().toJson(dynamicUITableListParentScreen);
                                rawDataTable.setDynamic_ui_rawdata(dynamicUIRawData);

                                // TODO: To save parent screen raw data
                                saveParentScreenRawData(rawDataTable, dynamicUITableListParentScreen,
                                        dynamicUITable, dynamicUITable.getFieldTag());
                            }

                        }

                    }
                };
                viewModel.getDynamicUITableLiveData().observe(this, businessLiabilitiesMsmeCalculationObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void changePinCodeFields(List<PincodeParameterInfo> parameterInfoList, List<DynamicUITable> dynamicUITableList) {
        try {
            viewModel.changePinCodeFields(parameterInfoList, dynamicUITableList);
            if (viewModel.getDynamicUITableLiveData() != null) {
                Observer changePinCodeFieldsObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        List<DynamicUITable> dynamicUITableListd = (List<DynamicUITable>) o;
                        viewModel.getDynamicUITableLiveData().removeObserver(this);
                        dynamicUI(dynamicUITableListd);
                    }
                };
                viewModel.getDynamicUITableLiveData().observe(this, changePinCodeFieldsObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    private void addOrRemoveSpinnerItem(String FieldTag, String[] paramList, String spinnerItem,
                                        boolean AddOrRemove, String screenNo, List<DynamicUITable> dynamicUITableList) {
        try {
            String[] newSpinnerItems = {};
            List<String> spinnerList = new ArrayList<>();
            spinnerList.addAll(Arrays.asList(paramList));
            try {
                if (AddOrRemove) {
                    spinnerList.add(spinnerItem);
                } else {
                    for (int i = 0; i < spinnerList.size(); i++) {
                        String spinnerName = spinnerList.get(i);
                        if (spinnerName.equalsIgnoreCase(spinnerItem)) {
                            spinnerList.remove(i);
                            break;
                        }
                    }
                }
                newSpinnerItems = spinnerList.toArray(new String[spinnerList.size()]);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            if (newSpinnerItems.length > 0) {
                viewModel.changeSpinnerList(FieldTag, newSpinnerItems, screenNo, dynamicUITableList);
                if (viewModel.getDynamicUITableLiveData() != null) {
                    Observer changeSpinnerListObserver = new Observer() {
                        @Override
                        public void onChanged(@Nullable Object o) {
                            // TODO: No need to change UI
                            List<DynamicUITable> dynamicUITableListd = (List<DynamicUITable>) o;
                            viewModel.getDynamicUITableLiveData().removeObserver(this);
                            dynamicUI(dynamicUITableListd);
                        }
                    };
                    viewModel.getDynamicUITableLiveData().observe(this, changeSpinnerListObserver);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    private void changeSpinnerList(String FieldTag, String spinnerItem, String screenNo, List<DynamicUITable> dynamicUITableList) {
        try {

            String[] newSpinnerItems = {};
            // TODO: NEW SPINNER ITEMS BASED ON TAG NAME
            if (FieldTag.equalsIgnoreCase(TAG_NAME_OCCUPATION)) {
                newSpinnerItems = getNewSpinnerList(TAG_NAME_OCCUPATION, spinnerItem);
            } else if (FieldTag.equalsIgnoreCase(TAG_NAME_KYC_TYPE)) {
                newSpinnerItems = getNewSpinnerList(TAG_NAME_KYC_TYPE, spinnerItem);
            } else if (FieldTag.equalsIgnoreCase(TAG_NAME_BUSINESS_SUB_SECTOR)) {
                newSpinnerItems = getNewSpinnerList(TAG_NAME_BUSINESS_SUB_SECTOR, spinnerItem);
            }// TODO: added by prasanna for business profile  8/3/2019
            else if (FieldTag.equalsIgnoreCase(TAG_NAME_TYPE_OF_BUSINESS)) {
                newSpinnerItems = getNewSpinnerList(TAG_NAME_TYPE_OF_BUSINESS, spinnerItem);
            }

            if (newSpinnerItems.length > 0) {
                viewModel.changeSpinnerList(FieldTag, newSpinnerItems, screenNo, dynamicUITableList);
                if (viewModel.getDynamicUITableLiveData() != null) {
                    Observer changeSpinnerListObserver = new Observer() {
                        @Override
                        public void onChanged(@Nullable Object o) {
                            // TODO: No need to change UI
                            List<DynamicUITable> dynamicUITableListd = (List<DynamicUITable>) o;
                            viewModel.getDynamicUITableLiveData().removeObserver(this);
                            if (SCREEN_NAME.equalsIgnoreCase(SCREEN_NAME_SOCIO_ECONOMIC_DETAIL)
                                    || SCREEN_NAME.equalsIgnoreCase(SCREEN_NAME_COAPPLICANT_SOCIO_ECONOMIC_DETAIL)) {

                                List<ParameterInfo> parameterInfoList = new ArrayList<>();
                                parameterInfoList.add(new ParameterInfo(TAG_NAME_OCCUPATION, SCREEN_ID, "", true, true));

                                if (spinnerItem.equalsIgnoreCase(SPINNER_ITEM_FIELD_NAME_SALARIED)
                                        || spinnerItem.equalsIgnoreCase(SPINNER_ITEM_FIELD_NAME_SELF_EMPLOYED)
                                        || spinnerItem.equalsIgnoreCase(SPINNER_ITEM_FIELD_NAME_PENSIONER)) {
                                    //   parameterInfoList.add(new ParameterInfo(TAG_NAME_OCCUPATION_DETAIL, SCREEN_ID, "", true, true));
                                    parameterInfoList.add(new ParameterInfo(TAG_NAME_INCOME_TYPE, SCREEN_ID, "", true, true));
                                    parameterInfoList.add(new ParameterInfo(TAG_NAME_MONTHLY_INCOME, SCREEN_ID, "", true, true));
                                    parameterInfoList.add(new ParameterInfo(TAG_NAME_GROSS_ANNUAL_INCOME, SCREEN_ID, "", true, true));
                                    parameterInfoList.add(new ParameterInfo(TAG_NAME_YEARS_OF_EXPERIENCE, SCREEN_ID, "", true, true));
                                } else if (spinnerItem.equalsIgnoreCase(SPINNER_ITEM_FIELD_NAME_STUDENT)
                                        || spinnerItem.equalsIgnoreCase(SPINNER_ITEM_FIELD_NAME_HOUSEWIFE)
                                        || spinnerItem.equalsIgnoreCase(SPINNER_ITEM_FIELD_NAME_HOUSE_WIFE)
                                        || spinnerItem.equalsIgnoreCase(SPINNER_ITEM_FIELD_NAME_NOT_WORKING)) {
                                    parameterInfoList.add(new ParameterInfo(TAG_NAME_INCOME_TYPE, SCREEN_ID, "", false, true));
                                    parameterInfoList.add(new ParameterInfo(TAG_NAME_MONTHLY_INCOME, SCREEN_ID, "", false, true));
                                    parameterInfoList.add(new ParameterInfo(TAG_NAME_GROSS_ANNUAL_INCOME, SCREEN_ID, "", false, true));
                                    parameterInfoList.add(new ParameterInfo(TAG_NAME_YEARS_OF_EXPERIENCE, SCREEN_ID, "", false, true));

                                }
                                EnableOrDisableByFieldNameInDB(parameterInfoList, dynamicUITableListd);

                            } else if (SCREEN_NAME.equalsIgnoreCase(SCREEN_NAME_BUSINESS_PROFILE)) {
                                DynamicUITable type_of_buzObj = getObjectByTAG(TAG_NAME_TYPE_OF_BUSINESS, dynamicUITableListd);
                                String typeOfBusiness = type_of_buzObj.getValue();
                                List<ParameterInfo> parameterInfoList = new ArrayList<>();
                                parameterInfoList.add(new ParameterInfo(TAG_NAME_TYPE_OF_BUSINESS, SCREEN_ID, typeOfBusiness, true, true));
                                EnableOrDisableByFieldNameInDB(parameterInfoList, dynamicUITableListd);

                            } else {
                                dynamicUI(dynamicUITableListd);
                            }
                        }
                    };
                    viewModel.getDynamicUITableLiveData().observe(this, changeSpinnerListObserver);
                }
            } else {
                if (SCREEN_NAME.equalsIgnoreCase(SCREEN_NAME_SOCIO_ECONOMIC_DETAIL)
                        || SCREEN_NAME.equalsIgnoreCase(SCREEN_NAME_COAPPLICANT_SOCIO_ECONOMIC_DETAIL)) {
                    List<ParameterInfo> parameterInfoList1 = new ArrayList<>();
                    parameterInfoList1.add(new ParameterInfo(TAG_NAME_OCCUPATION, SCREEN_ID, "", false, true));
                    // parameterInfoList1.add(new ParameterInfo(TAG_NAME_OCCUPATION_DETAIL, SCREEN_ID, "", false, true));
                    if (spinnerItem.equalsIgnoreCase(SPINNER_ITEM_FIELD_NAME_HOUSEWIFE)
                            || spinnerItem.equalsIgnoreCase(SPINNER_ITEM_FIELD_NAME_HOUSE_WIFE)
                            || spinnerItem.equalsIgnoreCase(SPINNER_ITEM_FIELD_NAME_STUDENT)
                            || spinnerItem.equalsIgnoreCase(SPINNER_ITEM_FIELD_NAME_NOT_WORKING)) {
                        //HIDE FIELDS
                        parameterInfoList1.add(new ParameterInfo(TAG_NAME_INCOME_TYPE, SCREEN_ID, "", false, true));
                        parameterInfoList1.add(new ParameterInfo(TAG_NAME_MONTHLY_INCOME, SCREEN_ID, "", false, true));
                        parameterInfoList1.add(new ParameterInfo(TAG_NAME_GROSS_ANNUAL_INCOME, SCREEN_ID, "", false, true));
                        parameterInfoList1.add(new ParameterInfo(TAG_NAME_YEARS_OF_EXPERIENCE, SCREEN_ID, "", false, true));

                    } else {
                        parameterInfoList1.add(new ParameterInfo(TAG_NAME_INCOME_TYPE, SCREEN_ID, "", true, true));
                        parameterInfoList1.add(new ParameterInfo(TAG_NAME_MONTHLY_INCOME, SCREEN_ID, "", true, true));
                        parameterInfoList1.add(new ParameterInfo(TAG_NAME_GROSS_ANNUAL_INCOME, SCREEN_ID, "", true, true));
                        parameterInfoList1.add(new ParameterInfo(TAG_NAME_YEARS_OF_EXPERIENCE, SCREEN_ID, "", true, true));

                    }

                    EnableOrDisableByFieldNameInDB(parameterInfoList1, dynamicUITableList);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void cardView(List<View> dynamicViews) {
        CardView cardView = null;
        try {
            int count = 2;
            ll.removeAllViews();
            for (View view : dynamicViews) {
                DynamicUITable dynamicUITable = (DynamicUITable) view.getTag();
                if (dynamicUITable != null && dynamicUITable.getFeatureID() != 1) {
                    if (dynamicUITable.getFeatureID() == count) {
                        count++;
                        cardView = createCardView(getActivity());
                        LinearLayout linearLayout = (LinearLayout) cardView.getChildAt(0);
                        linearLayout.addView(view.getRootView());
                        ll.addView(cardView);
                    } else {
                        LinearLayout linearLayout = (LinearLayout) cardView.getChildAt(0);
                        linearLayout.addView(view.getRootView());
                    }
                } else {
                    ll.addView(view.getRootView());
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    private void getPincodeDetails(String pincode, List<DynamicUITable> dynamicUITableList, String fieldTag) {
        try {
            Log.i(TAG, "Pin code -->" + pincode);
            appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();
            DynamicUIWebService.changeApiBaseUrl(AppConstant.PIN_CODE_URL);
            getServicesAPI().getPincodeDetails(pincode, appHelper.getSharedPrefObj().getString(AUTHORIZATION_TOKEN_KEY, ""))
                    .enqueue(new Callback<PincodeResponseDTO>() {
                        @Override
                        public void onResponse(@NonNull Call<PincodeResponseDTO> call, @NonNull Response<PincodeResponseDTO> response) {
                            appHelper.getDialogHelper().getLoadingDialog().closeDialog();
                            try {
                                Log.d(TAG, "Pin code EKYCLoginRequest ===> " + response);
                                if (response.isSuccessful()) {
                                    Log.d(TAG, "Pin code EKYCLoginRequest code===> " + response.code());
                                    Log.d(TAG, "Pin code EKYCLoginRequest Msg===> " + response.message());
                                    PincodeResponseDTO pincodeResponseDTO = response.body();
                                    Log.d(TAG, "Pin code EKYCLoginRequest Body===> " + pincodeResponseDTO);
                                    if (pincodeResponseDTO != null && pincodeResponseDTO.getStatus().equalsIgnoreCase("Success")) {
                                        // TODO: write success logic here
                                        List<PincodeParameterInfo> parameterInfoList = new ArrayList<>();
                                        List<String> cityList = new ArrayList<>();
                                        List<String> villageList = new ArrayList<>();
                                        for (PincodeResponseDTO.PostOffice postOffice : pincodeResponseDTO.getPostOffice()) {
                                            if (!(SCREEN_NAME.equalsIgnoreCase(SCREEN_NAME_ADDRESS_DETAIL)) && !(SCREEN_NAME.equalsIgnoreCase(SCREEN_NAME_COAPPLICANT_ADDRESS_DETAIL))) {
                                                parameterInfoList.add(new PincodeParameterInfo(TAG_NAME_PINCODE, SCREEN_ID, pincode, true, true, FIELD_TYPE_TEXT_BOX, new String[0]));

                                            } else if (fieldTag.equalsIgnoreCase(TAG_NAME_PERMANENT_PINCODE)) {
                                                parameterInfoList.add(new PincodeParameterInfo(TAG_NAME_PERMANENT_PINCODE, SCREEN_ID, pincode, true, true, FIELD_TYPE_TEXT_BOX, new String[0]));
                                            } else if (fieldTag.equalsIgnoreCase(TAG_NAME_COMMUNICATION_PINCODE)) {
                                                parameterInfoList.add(new PincodeParameterInfo(TAG_NAME_COMMUNICATION_PINCODE, SCREEN_ID, pincode, true, true, FIELD_TYPE_TEXT_BOX, new String[0]));

                                            }
                                            if (pincodeResponseDTO.getPostOffice() != null
                                                    && pincodeResponseDTO.getPostOffice().size() > 0
                                                    && !TextUtils.isEmpty(postOffice.getName())) {
                                                String village = postOffice.getName();
                                                villageList.add(village);
                                                if (!SCREEN_NAME.equalsIgnoreCase(SCREEN_NAME_ADDRESS_DETAIL) && !(SCREEN_NAME.equalsIgnoreCase(SCREEN_NAME_COAPPLICANT_ADDRESS_DETAIL))) {
                                                    parameterInfoList.add(new PincodeParameterInfo(TAG_NAME_VILLAGE, SCREEN_ID, village, true,
                                                            true, FIELD_TYPE_LIST_BOX, villageList.toArray(new String[0])));
                                                }
                                            }
                                            if (pincodeResponseDTO.getPostOffice() != null
                                                    && pincodeResponseDTO.getPostOffice().size() > 0
                                                    && !TextUtils.isEmpty(postOffice.getDistrict())) {
                                                String city = postOffice.getDistrict();
                                                cityList.add(city);
                                                if (!(SCREEN_NAME.equalsIgnoreCase(SCREEN_NAME_ADDRESS_DETAIL)) && !(SCREEN_NAME.equalsIgnoreCase(SCREEN_NAME_COAPPLICANT_ADDRESS_DETAIL))) {
                                                    parameterInfoList.add(new PincodeParameterInfo(TAG_NAME_CITY, SCREEN_ID, city, true, true, FIELD_TYPE_LIST_BOX, cityList.toArray(new String[0])));
                                                } else if (fieldTag.equalsIgnoreCase(TAG_NAME_PERMANENT_PINCODE)) {
                                                    parameterInfoList.add(new PincodeParameterInfo(TAG_NAME_PERMANENT_CITY, SCREEN_ID, city, true, true, FIELD_TYPE_LIST_BOX, cityList.toArray(new String[0])));
                                                } else if (fieldTag.equalsIgnoreCase(TAG_NAME_COMMUNICATION_PINCODE)) {
                                                    parameterInfoList.add(new PincodeParameterInfo(TAG_NAME_COMMUNICATION_CITY, SCREEN_ID, city, true, true, FIELD_TYPE_LIST_BOX, cityList.toArray(new String[0])));
                                                }
                                            }
                                            if (pincodeResponseDTO.getPostOffice() != null
                                                    && pincodeResponseDTO.getPostOffice().size() > 0
                                                    && !TextUtils.isEmpty(postOffice.getDistrict())) {
                                                String district = postOffice.getDistrict();
                                                if (!SCREEN_NAME.equalsIgnoreCase(SCREEN_NAME_ADDRESS_DETAIL) && !(SCREEN_NAME.equalsIgnoreCase(SCREEN_NAME_COAPPLICANT_ADDRESS_DETAIL))) {
                                                    parameterInfoList.add(new PincodeParameterInfo(TAG_NAME_DISTRICT, SCREEN_ID, district, true, false, FIELD_TYPE_TEXT_BOX, new String[0]));
                                                } else if (fieldTag.equalsIgnoreCase(TAG_NAME_PERMANENT_PINCODE)) {
                                                    parameterInfoList.add(new PincodeParameterInfo(TAG_NAME_PERMANENT_DISTRICT, SCREEN_ID, district, true, false, FIELD_TYPE_TEXT_BOX, new String[0]));
                                                } else if (fieldTag.equalsIgnoreCase(TAG_NAME_COMMUNICATION_PINCODE)) {
                                                    parameterInfoList.add(new PincodeParameterInfo(TAG_NAME_COMMUNICATION_DISTRICT, SCREEN_ID, district, true, false, FIELD_TYPE_TEXT_BOX, new String[0]));
                                                }
                                            }
                                            if (pincodeResponseDTO.getPostOffice() != null
                                                    && pincodeResponseDTO.getPostOffice().size() > 0
                                                    && !TextUtils.isEmpty(postOffice.getState())) {
                                                String state = postOffice.getState();
                                                if(postOffice.getState().equalsIgnoreCase("Chattisgarh")){
                                                     state = "Chhattisgarh";
                                                }else{
                                                    state = postOffice.getState();
                                                }
                                                if (!SCREEN_NAME.equalsIgnoreCase(SCREEN_NAME_ADDRESS_DETAIL) && !(SCREEN_NAME.equalsIgnoreCase(SCREEN_NAME_COAPPLICANT_ADDRESS_DETAIL))) {
                                                    parameterInfoList.add(new PincodeParameterInfo(TAG_NAME_STATE, SCREEN_ID, state, true, false, FIELD_TYPE_TEXT_BOX, new String[0]));
                                                } else if (fieldTag.equalsIgnoreCase(TAG_NAME_PERMANENT_PINCODE)) {
                                                    parameterInfoList.add(new PincodeParameterInfo(TAG_NAME_PERMANENT_STATE, SCREEN_ID, state, true, false, FIELD_TYPE_TEXT_BOX, new String[0]));
                                                } else if (fieldTag.equalsIgnoreCase(TAG_NAME_COMMUNICATION_PINCODE)) {
                                                    parameterInfoList.add(new PincodeParameterInfo(TAG_NAME_COMMUNICATION_STATE, SCREEN_ID, state, true, false, FIELD_TYPE_TEXT_BOX, new String[0]));
                                                }
                                            }
                                        }
                                        changePinCodeFields(parameterInfoList, dynamicUITableList);

                                    } else {
                                        appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ERROR,
                                                "Something went wrong. Please try again later", new ConfirmationDialog.ActionCallback() {
                                                    @Override
                                                    public void onAction() {
                                                        List<PincodeParameterInfo> parameterInfoList = new ArrayList<>();
                                                        parameterInfoList.add(new PincodeParameterInfo(TAG_NAME_PINCODE, SCREEN_ID, pincode, true, true, FIELD_TYPE_TEXT_BOX, new String[0]));
                                                        parameterInfoList.add(new PincodeParameterInfo(TAG_NAME_VILLAGE, SCREEN_ID, "", true, true, FIELD_TYPE_TEXT_BOX, new String[0]));
                                                        parameterInfoList.add(new PincodeParameterInfo(TAG_NAME_CITY, SCREEN_ID, "", true, true, FIELD_TYPE_TEXT_BOX, new String[0]));
                                                        parameterInfoList.add(new PincodeParameterInfo(TAG_NAME_DISTRICT, SCREEN_ID, "", true, true, FIELD_TYPE_TEXT_BOX, new String[0]));
                                                        parameterInfoList.add(new PincodeParameterInfo(TAG_NAME_STATE, SCREEN_ID, "", true, true, FIELD_TYPE_TEXT_BOX, new String[0]));
                                                        changePinCodeFields(parameterInfoList, dynamicUITableList);
                                                    }
                                                });


                                        /*// TODO: write failure logic here
                                        List<PincodeParameterInfo> parameterInfoList = new ArrayList<>();
                                        parameterInfoList.add(new PincodeParameterInfo(TAG_NAME_PINCODE, SCREEN_ID, pincode, true, true, FIELD_TYPE_TEXT_BOX, new String[0]));
                                        parameterInfoList.add(new PincodeParameterInfo(TAG_NAME_VILLAGE, SCREEN_ID, "", true, true, FIELD_TYPE_TEXT_BOX, new String[0]));
                                        parameterInfoList.add(new PincodeParameterInfo(TAG_NAME_CITY, SCREEN_ID, "", true, true, FIELD_TYPE_TEXT_BOX, new String[0]));
                                        parameterInfoList.add(new PincodeParameterInfo(TAG_NAME_DISTRICT, SCREEN_ID, "", true, true, FIELD_TYPE_TEXT_BOX, new String[0]));
                                        parameterInfoList.add(new PincodeParameterInfo(TAG_NAME_STATE, SCREEN_ID, "", true, true, FIELD_TYPE_TEXT_BOX, new String[0]));
                                        changePinCodeFields(parameterInfoList, dynamicUITableList);*/
                                    }
                                } else {
                                    appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ERROR,
                                            "Something went wrong. Please try again later", new ConfirmationDialog.ActionCallback() {
                                                @Override
                                                public void onAction() {
                                                    List<PincodeParameterInfo> parameterInfoList = new ArrayList<>();
                                                    parameterInfoList.add(new PincodeParameterInfo(TAG_NAME_PINCODE, SCREEN_ID, pincode, true, true, FIELD_TYPE_TEXT_BOX, new String[0]));
                                                    parameterInfoList.add(new PincodeParameterInfo(TAG_NAME_VILLAGE, SCREEN_ID, "", true, true, FIELD_TYPE_TEXT_BOX, new String[0]));
                                                    parameterInfoList.add(new PincodeParameterInfo(TAG_NAME_CITY, SCREEN_ID, "", true, true, FIELD_TYPE_TEXT_BOX, new String[0]));
                                                    parameterInfoList.add(new PincodeParameterInfo(TAG_NAME_DISTRICT, SCREEN_ID, "", true, true, FIELD_TYPE_TEXT_BOX, new String[0]));
                                                    parameterInfoList.add(new PincodeParameterInfo(TAG_NAME_STATE, SCREEN_ID, "", true, true, FIELD_TYPE_TEXT_BOX, new String[0]));
                                                    changePinCodeFields(parameterInfoList, dynamicUITableList);
                                                }
                                            });

                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                                appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ERROR,
                                        "Something went wrong. Please try again later", new ConfirmationDialog.ActionCallback() {
                                            @Override
                                            public void onAction() {
                                                List<PincodeParameterInfo> parameterInfoList = new ArrayList<>();
                                                parameterInfoList.add(new PincodeParameterInfo(TAG_NAME_PINCODE, SCREEN_ID, pincode, true, true, FIELD_TYPE_TEXT_BOX, new String[0]));
                                                parameterInfoList.add(new PincodeParameterInfo(TAG_NAME_VILLAGE, SCREEN_ID, "", true, true, FIELD_TYPE_TEXT_BOX, new String[0]));
                                                parameterInfoList.add(new PincodeParameterInfo(TAG_NAME_CITY, SCREEN_ID, "", true, true, FIELD_TYPE_TEXT_BOX, new String[0]));
                                                parameterInfoList.add(new PincodeParameterInfo(TAG_NAME_DISTRICT, SCREEN_ID, "", true, true, FIELD_TYPE_TEXT_BOX, new String[0]));
                                                parameterInfoList.add(new PincodeParameterInfo(TAG_NAME_STATE, SCREEN_ID, "", true, true, FIELD_TYPE_TEXT_BOX, new String[0]));
                                                changePinCodeFields(parameterInfoList, dynamicUITableList);
                                            }
                                        });
                            }
                        }

                        @Override
                        public void onFailure(@NonNull Call<PincodeResponseDTO> call, @NonNull Throwable t) {
                            appHelper.getDialogHelper().getLoadingDialog().closeDialog();
                            t.printStackTrace();
                            appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ERROR,
                                    "Something went wrong. Please try again later", new ConfirmationDialog.ActionCallback() {
                                        @Override
                                        public void onAction() {
                                            List<PincodeParameterInfo> parameterInfoList = new ArrayList<>();
                                            parameterInfoList.add(new PincodeParameterInfo(TAG_NAME_PINCODE, SCREEN_ID, pincode, true, true, FIELD_TYPE_TEXT_BOX, new String[0]));
                                            parameterInfoList.add(new PincodeParameterInfo(TAG_NAME_VILLAGE, SCREEN_ID, "", true, true, FIELD_TYPE_TEXT_BOX, new String[0]));
                                            parameterInfoList.add(new PincodeParameterInfo(TAG_NAME_CITY, SCREEN_ID, "", true, true, FIELD_TYPE_TEXT_BOX, new String[0]));
                                            parameterInfoList.add(new PincodeParameterInfo(TAG_NAME_DISTRICT, SCREEN_ID, "", true, true, FIELD_TYPE_TEXT_BOX, new String[0]));
                                            parameterInfoList.add(new PincodeParameterInfo(TAG_NAME_STATE, SCREEN_ID, "", true, true, FIELD_TYPE_TEXT_BOX, new String[0]));
                                            changePinCodeFields(parameterInfoList, dynamicUITableList);
                                        }
                                    });
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getRawData(String screen, List<DynamicUITable> list, List<RawDataTable> tagNameList, String newRowTagName) {
        ArrayList<HashMap<String, Object>> hashMapList = new ArrayList<>();
        try {
            viewModel.getRawData(screen, CLIENT_ID, MODULE_TYPE);
            if (viewModel.getRawTableLiveData() != null) {
                Observer getLeadRawDataObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        List<RawDataTable> rawDataTableList = (List<RawDataTable>) o;
                        viewModel.getRawTableLiveData().removeObserver(this);
                        if (rawDataTableList != null && rawDataTableList.size() > 0) {
                            for (RawDataTable rawDataTable : rawDataTableList) {
                                HashMap<String, Object> hashMap = setKeyValueForObject(rawDataTable);
                                hashMapList.add(hashMap);
                            }
                            Log.d(TAG, "HashMapList ==> " + hashMapList.toString());
                            if (hashMapList != null && hashMapList.size() > 0) {
                                // TODO: Already saved data
                                HashMap<String, Object> hashMap = hashMapList.get(0);
                                if (hashMap != null && hashMap.size() > 0) {
                                    List<DynamicUITable> newList = new ArrayList<>();
                                    for (int i = 0; i < list.size(); i++) {
                                        DynamicUITable dynamicUITable = list.get(i);
                                        dynamicUITable.setVisibility(false);
                                        if (!TextUtils.isEmpty(dynamicUITable.getFieldTag())) {
                                            if (hashMap.containsKey(dynamicUITable.getFieldTag())) {
                                                String value = hashMap.get(dynamicUITable.getFieldTag()).toString();
                                                if (!TextUtils.isEmpty(value)) {
                                                    if (SCREEN_NAME.equalsIgnoreCase(SCREEN_NAME_APPLICANT_KYC) || SCREEN_NAME.equalsIgnoreCase(SCREEN_NAME_CO_APPLICANT_KYC)) {
                                                        dynamicUITable.setValue("");
                                                        if (dynamicUITable.getFieldTag().equalsIgnoreCase(TAG_NAME_KYC_TYPE)) {
                                                            dynamicUITable.setVisibility(true);
                                                        }
                                                    } else if (SCREEN_NAME.equalsIgnoreCase(SCREEN_NAME_BUSINESS_PROOF)) {
                                                        dynamicUITable.setValue("");
                                                        if (dynamicUITable.getFieldTag().equalsIgnoreCase(TAG_NAME_BUSINESS_DOCUMENT_PROOF)) {
                                                            dynamicUITable.setVisibility(true);
                                                        }
                                                    } else {
                                                        dynamicUITable.setValue(value);
                                                    }

                                                    newList.add(dynamicUITable);

                                                    if (!TextUtils.isEmpty(newRowTagName)) {
                                                        if (dynamicUITable.getFieldTag().equalsIgnoreCase(newRowTagName)) {
                                                            for (RawDataTable rawDataTable : tagNameList) {
                                                                if (SCREEN_NAME.equalsIgnoreCase(SCREEN_NAME_APPLICANT_KYC) ||
                                                                        SCREEN_NAME.equalsIgnoreCase(SCREEN_NAME_CO_APPLICANT_KYC) || SCREEN_NAME.equalsIgnoreCase(SCREEN_NAME_BUSINESS_PROOF)) {
                                                                    DynamicUITable newDynamicUITable = createNewRow(dynamicUITable,
                                                                            rawDataTable,
                                                                            rawDataTable.getAdditional(), hashMap);
                                                                    newList.add(newDynamicUITable);
                                                                } else {
                                                                    DynamicUITable newDynamicUITable = createNewRow(dynamicUITable,
                                                                            rawDataTable,
                                                                            rawDataTable.getField_name(), hashMap);
                                                                    newList.add(newDynamicUITable);
                                                                }

                                                            }
                                                        }
                                                    }
                                                }

                                            } else if (!SCREEN_NAME.equalsIgnoreCase(SCREEN_NAME_APPLICANT_KYC)
                                                    && (!SCREEN_NAME.equalsIgnoreCase(SCREEN_NAME_CO_APPLICANT_KYC))) {

                                                if (dynamicUITable.getFieldName().equalsIgnoreCase(FIELD_NAME_SAVE)) {
                                                    dynamicUITable.setVisibility(true);
                                                    dynamicUITable.setFieldName(FIELD_NAME_UPDATE);
                                                    newList.add(dynamicUITable);
                                                } else if (dynamicUITable.getFieldType().equalsIgnoreCase(FIELD_TYPE_ADD_BUTTON)) {
                                                    dynamicUITable.setVisibility(true);
                                                    newList.add(dynamicUITable);
                                                } else if (dynamicUITable.getFieldType().equalsIgnoreCase(FIELD_TYPE_PLUS_BUTTON)) {
                                                    dynamicUITable.setVisibility(true);
                                                    newList.add(dynamicUITable);
                                                }
                                            }
                                        }
                                    }

                                    deleteAndInsertNewRecord(newList, SCREEN_NAME);
                                }
                            } else {
                                // TODO: Fresh Data
                                dynamicUI(list);
                            }
                        } else {
                            // TODO: Fresh Data
                            dynamicUI(list);
                        }
                    }
                };
                viewModel.getRawTableLiveData().observe(this, getLeadRawDataObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    // TODO: use this method for multiple add button screen
    public void getTagNameList(String screenName, List<DynamicUITable> list, String newRowTagName) {
        try {
            viewModel.getTagNameList(screenName, CLIENT_ID, MODULE_TYPE, list.get(0).getCoRelationID());
            if (viewModel.getRawTableLiveData() != null) {
                Observer getLeadRawDataObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        List<RawDataTable> rawDataTableList = (List<RawDataTable>) o;
                        viewModel.getRawTableLiveData().removeObserver(this);
                        if (rawDataTableList != null && rawDataTableList.size() > 0) {

                            try {

                                List<DynamicUITable> newList = new ArrayList<>();
                                List<DynamicUITable> oldList = new ArrayList<>();
                                List<DynamicUITable> filteredOldList = new ArrayList<>();
                                List<DynamicUITable> totalList = new ArrayList<>();

                                for (DynamicUITable dynamicUITable : list) {
                                    // TODO: Need to write condition here for all screens

                                   /* if(rawDataTableList.size()==1){
                                        HashMap<String, Object> hashMap = setKeyValueForObject(rawDataTableList.get(0));
                                        if (!TextUtils.isEmpty(dynamicUITable.getFieldTag())) {
                                            if (hashMap.containsKey(dynamicUITable.getFieldTag())) {
                                                String value = hashMap.get(dynamicUITable.getFieldTag()).toString();
                                                if (!TextUtils.isEmpty(value)) {
                                                     dynamicUITable.setValue(value);
                                                }
                                            }
                                        }
                                    }*/

                                    if (dynamicUITable.getScreenName().equalsIgnoreCase(SCREEN_NAME_APPLICANT_KYC)
                                            || dynamicUITable.getScreenName().equalsIgnoreCase(SCREEN_NAME_CO_APPLICANT_KYC)

                                            || dynamicUITable.getScreenName().equalsIgnoreCase(SCREEN_NAME_REFERENCE_CHECK)
                                            || dynamicUITable.getScreenName().equalsIgnoreCase(SCREEN_NAME_BUSINESS_PROOF)
                                            || dynamicUITable.getScreenName().equalsIgnoreCase(SCREEN_NAME_BANK_DETAILS)
                                            || dynamicUITable.getScreenName().equalsIgnoreCase(SCREEN_NAME_CO_APPLICANT_BANK_DETAILS)
                                            || dynamicUITable.getScreenName().equalsIgnoreCase(SCREEN_NAME_HOUSE_LIABILITIES) && dynamicUITable.getLoanType().equalsIgnoreCase(LOAN_NAME_INDIVIDUAL)

                                            || dynamicUITable.getScreenName().equalsIgnoreCase(SCREEN_NAME_BUSINESS_ASSETS) && dynamicUITable.getLoanType().equalsIgnoreCase(LOAN_NAME_INDIVIDUAL)
                                            || dynamicUITable.getScreenName().equalsIgnoreCase(SCREEN_NAME_REFERENCES)) {
                                        dynamicUITable.setVisibility(false);
                                        dynamicUITable.setValue("");
                                    }

                                    if (dynamicUITable.getScreenName().equalsIgnoreCase(SCREEN_NAME_BUSINESS_PROOF)) {
                                        dynamicUITable.setVisibility(true);
                                        if (dynamicUITable.getFieldTag() != null && dynamicUITable.getFieldTag().equalsIgnoreCase(TAG_NAME_BUSINESS_LOCATION)
                                                || dynamicUITable.getFieldTag() != null && dynamicUITable.getFieldTag().equalsIgnoreCase(TAG_NAME_PROOF_TYPE)) {
                                            dynamicUITable.setVisibility(false);
                                        } else if (dynamicUITable.getFieldTag() != null && dynamicUITable.getFieldName().equalsIgnoreCase(FIELD_NAME_UPDATE)) {
                                            dynamicUITable.setFieldName(FIELD_NAME_ADD_ANOTHER_BUSINESS_PROOF);
                                        }

                                    }

                                    if (dynamicUITable.getScreenName().equalsIgnoreCase(SCREEN_NAME_HOUSE_LIABILITIES) && dynamicUITable.getLoanType().equalsIgnoreCase(LOAN_NAME_INDIVIDUAL)
                                            || dynamicUITable.getScreenName().equalsIgnoreCase(SCREEN_NAME_BUSINESS_ASSETS) && dynamicUITable.getLoanType().equalsIgnoreCase(LOAN_NAME_INDIVIDUAL)
                                            || dynamicUITable.getScreenName().equalsIgnoreCase(SCREEN_NAME_BUSINESS_LIABILITIES) && dynamicUITable.getLoanType().equalsIgnoreCase(LOAN_NAME_INDIVIDUAL)) {
                                        dynamicUITable.setVisibility(true);
                                    }


                                    if (dynamicUITable.getScreenName().equalsIgnoreCase(SCREEN_NAME_REFERENCE_CHECK)) {
                                        if (!dynamicUITable.getFieldTag().equalsIgnoreCase(TAG_NAME_SAVE_BUTTON)) {
                                            dynamicUITable.setVisibility(true);
                                        }

                                        if (!dynamicUITable.getFieldTag().equalsIgnoreCase(newRowTagName)) {
                                            dynamicUITable.setValue("");
                                        }
                                    }
                                    if (dynamicUITable.getScreenName().equalsIgnoreCase(SCREEN_NAME_BANK_DETAILS)) {
                                        if (!dynamicUITable.getFieldTag().equalsIgnoreCase(TAG_NAME_SAVE_BUTTON)) {
                                            dynamicUITable.setVisibility(true);
                                        }
                                        if (dynamicUITable.getFieldTag() != null && dynamicUITable.getFieldName().equalsIgnoreCase(FIELD_NAME_UPDATE)) {
                                            dynamicUITable.setFieldName(FIELD_NAME_ADD_BANK_DETAILS);
                                        }

                                        // TODO: for co applicant - ignore module type
                                        if (!dynamicUITable.getFieldTag().equalsIgnoreCase(newRowTagName)
                                                || !dynamicUITable.getFieldTag().equalsIgnoreCase(TAG_NAME_MODULE_TYPE)) {
                                            dynamicUITable.setValue("");
                                        }
                                        // TODO: module type set visibility false
                                        if (dynamicUITable.getFieldTag().equalsIgnoreCase(TAG_NAME_MODULE_TYPE)) {
                                            dynamicUITable.setVisibility(false);
                                        }
                                    }

                                    if (dynamicUITable.getScreenName().equalsIgnoreCase(SCREEN_NAME_CO_APPLICANT_BANK_DETAILS)) {
                                        if (!dynamicUITable.getFieldTag().equalsIgnoreCase(TAG_NAME_SAVE_BUTTON)) {
                                            dynamicUITable.setVisibility(true);
                                        }
                                        if (dynamicUITable.getFieldTag() != null && dynamicUITable.getFieldName().equalsIgnoreCase(FIELD_NAME_UPDATE)) {
                                            dynamicUITable.setFieldName(FIELD_NAME_ADD_BANK_DETAILS);
                                        }

                                        // TODO: for co applicant - ignore module type
                                        if (!dynamicUITable.getFieldTag().equalsIgnoreCase(newRowTagName)
                                                || !dynamicUITable.getFieldTag().equalsIgnoreCase(TAG_NAME_MODULE_TYPE)) {
                                            dynamicUITable.setValue("");
                                        }
                                        // TODO: module type set visibility false
                                        if (dynamicUITable.getFieldTag().equalsIgnoreCase(TAG_NAME_MODULE_TYPE)) {
                                            dynamicUITable.setVisibility(false);
                                        }
                                    }
                                    if (dynamicUITable.getScreenName().equalsIgnoreCase(SCREEN_NAME_GENERAL_INCOME)
                                            || dynamicUITable.getScreenName().equalsIgnoreCase(SCREEN_NAME_OTHER_INCOME_SOURCE)) {

                                        if (!dynamicUITable.getFieldTag().equalsIgnoreCase(newRowTagName)) {
                                            dynamicUITable.setValue("");
                                        }
                                    }

//                                    if (dynamicUITable.getScreenName().equalsIgnoreCase(SCREEN_NAME_REFERENCES)) {
//
//                                       // dynamicUITable.setVisibility(true);
//                                        if (!dynamicUITable.getFieldTag().equalsIgnoreCase(newRowTagName)) {
//                                            dynamicUITable.setValue("");
//                                        }
//                                    }

                                    if (dynamicUITable.getFieldTag() != null && dynamicUITable.getFieldTag().equalsIgnoreCase(newRowTagName)) {
                                        if (dynamicUITable.getScreenName().equalsIgnoreCase(SCREEN_NAME_REFERENCES)) {
                                            dynamicUITable.setVisibility(true);
                                            dynamicUITable.setEditable(true);
                                        }
                                        for (RawDataTable rawDataTable : rawDataTableList) {
                                            HashMap<String, Object> hashMap = setKeyValueForObject(rawDataTable);
                                            // TODO:  CREATING NEW ROW
                                            DynamicUITable newDynamicUITable = createNewRow(dynamicUITable,
                                                    rawDataTable,
                                                    rawDataTable.getAdditional(), hashMap);
                                            newList.add(newDynamicUITable);
                                        }

                                    }
                                    if (dynamicUITable.getFieldTag() != null && (dynamicUITable.getFieldTag().equalsIgnoreCase(TAG_NAME_KYC_TYPE) ||
                                            dynamicUITable.getFieldTag().equalsIgnoreCase(TAG_NAME_BUSINESS_DOCUMENT_PROOF)
                                            || (dynamicUITable.getScreenName().equalsIgnoreCase(SCREEN_NAME_REFERENCES)
                                            && dynamicUITable.getFieldTag().equalsIgnoreCase(TAG_NAME_FULL_NAME))
                                            || (dynamicUITable.getScreenName().equalsIgnoreCase(SCREEN_NAME_GENERAL_INCOME)
                                            && dynamicUITable.getFieldTag().equalsIgnoreCase(TAG_NAME_NAME_IN_GENERAL_INCOME))
                                            || (dynamicUITable.getScreenName().equalsIgnoreCase(SCREEN_NAME_OTHER_INCOME_SOURCE)
                                            && dynamicUITable.getFieldTag().equalsIgnoreCase(TAG_NAME_NAME_IN_OTHER_INCOME)))
                                    ) {
                                        dynamicUITable.setVisibility(true);
                                        dynamicUITable.setEditable(true);
                                        dynamicUITable.setFieldType(FIELD_TYPE_LIST_BOX);

                                        // TODO: condition to remove added kyc type tvName spinner list
                                        String[] newSpinnerItems = {};
                                        List<String> spinnerList = new ArrayList<>();
                                        spinnerList.addAll(Arrays.asList(dynamicUITable.getParamlist()));
                                        try {
                                            for (int i = 0; i < spinnerList.size(); i++) {
                                                String spinnerName = spinnerList.get(i);
                                                for (RawDataTable rawDataTable : rawDataTableList) {
                                                    if (!TextUtils.isEmpty(rawDataTable.getAdditional())) {
                                                        if (spinnerName.equalsIgnoreCase(rawDataTable.getAdditional())) {
                                                            spinnerList.remove(i);
                                                        }
                                                    }
                                                }
                                            }

                                            newSpinnerItems = spinnerList.toArray(new String[spinnerList.size()]);

                                            dynamicUITable.setParamlist(newSpinnerItems);

                                        } catch (Exception ex) {
                                            ex.printStackTrace();
                                        }
                                    }
                                       /* if (dynamicUITable.getFieldTag().equalsIgnoreCase(TAG_NAME_ADD_ANOTHER_KYC_PLUS_BUTTON)) {
                                            dynamicUITable.setFieldName(FIELD_NAME_ADD_ANOTHER_KYC);
                                        }*/
                                    oldList.add(dynamicUITable);
                                }
//                                } // TODO: Need to check

                                // TODO: if new list data is already exist in the old list data then remove it
                                for (int i = 0; i < oldList.size(); i++) {
                                    DynamicUITable oldData = oldList.get(i);
                                    filteredOldList.add(oldData);
                                    for (DynamicUITable newData : newList) {
                                        if (oldData.getFieldTag() != null && oldData.getFieldTag().equalsIgnoreCase(newData.getFieldTag())) {
                                            filteredOldList.remove(oldData);
                                        }
                                    }
                                }

                                // TODO: combine both filtered old and new list
                                for (DynamicUITable oldDynamicUITable : filteredOldList) {
                                    totalList.add(oldDynamicUITable);
                                    if (oldDynamicUITable.getFieldTag() != null && oldDynamicUITable.getFieldTag().equalsIgnoreCase(newRowTagName)) {
                                        for (DynamicUITable newDynamicUITable : newList) {
                                            totalList.add(newDynamicUITable);
                                        }
                                    }

                                }

                                deleteAndInsertNewRecord(totalList, SCREEN_NAME);
                            } catch (Exception ex) {
                                ex.printStackTrace();
                                dynamicUI(list);
                            }
                        } else {
                            // TODO: Fresh Data
                            if (screenName.equalsIgnoreCase(SCREEN_N0_HOUSE_EXPENSES)) {

                                List<ParameterInfo> fromScreenList = new ArrayList<>();
                                fromScreenList.add(new ParameterInfo(TAG_NAME_LIABILITY, SCREEN_N0_HOUSE_LIABILITIES, "", true, true));
                                fromScreenList.add(new ParameterInfo(TAG_NAME_BUSINESS_DEBTS, SCREEN_N0_BUSINESS_LIABILITIES, "", true, true));

                                List<ParameterInfo> toScreenList = new ArrayList<>();
                                toScreenList.add(new ParameterInfo(TAG_NAME_PAYMENT_OF_HH_DEBTS, SCREEN_ID, "", true, false));
                                toScreenList.add(new ParameterInfo(TAG_NAME_PAYMENT_OF_BUS_DEBTS, SCREEN_ID, "", true, false));
                                copyValuesFromScreenToScreen(fromScreenList, toScreenList, list);

                            }else if (SCREEN_NAME.equalsIgnoreCase(SCREEN_NAME_REFERENCES)
                            ) {
                                getRawDataFromOtherScreenAndUpdate(list, SCREEN_NAME);
                            }
                            // TODO: PHL bank details
                            else if (!TextUtils.isEmpty(LOAN_TYPE) && LOAN_TYPE.equalsIgnoreCase(LOAN_NAME_PHL)
                                    && SCREEN_NAME.equalsIgnoreCase(SCREEN_NAME_BANK_DETAILS)) {
                                getRawDataFromOtherScreenAndUpdate(list, SCREEN_NAME);
                            }
                            // TODO: AHL bank details
                            else if (!TextUtils.isEmpty(LOAN_TYPE) && LOAN_TYPE.equalsIgnoreCase(LOAN_NAME_AHL)
                                    && SCREEN_NAME.equalsIgnoreCase(SCREEN_NAME_BANK_DETAILS)) {
                                getRawDataFromOtherScreenAndUpdate(list, SCREEN_NAME);
                            } else {
                                dynamicUI(list);
                            }
                        }
                    }
                };
                viewModel.getRawTableLiveData().observe(this, getLeadRawDataObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    public void getRawDataForParentFragment(String screenName, List<DynamicUITable> list) {
        try {
            viewModel.getTagNameList(screenName, CLIENT_ID, MODULE_TYPE, list.get(0).getCoRelationID());
            if (viewModel.getRawTableLiveData() != null) {
                Observer getLeadRawDataObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        List<RawDataTable> rawDataTableList = (List<RawDataTable>) o;
                        viewModel.getRawTableLiveData().removeObserver(this);
                        if (rawDataTableList != null && rawDataTableList.size() > 0) {
                            try {
                                RawDataTable rawDataTable = rawDataTableList.get(rawDataTableList.size() - 1);
                                if (rawDataTable != null && !TextUtils.isEmpty(rawDataTable.getDynamic_ui_rawdata())) {
                                    Type type = new TypeToken<List<DynamicUITable>>() {
                                    }.getType();
                                    List<DynamicUITable> dynamicUITableListFromDB = new Gson().fromJson(rawDataTable.getDynamic_ui_rawdata(), type);
                                    if (dynamicUITableListFromDB != null && dynamicUITableListFromDB.size() > 0) {
                                        // TODO: Setting dynamic ui table sync
                                        setSyncForDynamicUITable(dynamicUITableListFromDB, list.get(0).isSync());
                                        DynamicUITable dynamicUITable = getObjectByTAG(TAG_NAME_SAVE_BUTTON, dynamicUITableListFromDB);
                                        if (dynamicUITable != null) {
                                            // TODO: change button as update other than sales tool
                                            if (!SCREEN_NAME.equalsIgnoreCase(SCREEN_NAME_SALES_TOOL)) {
                                                dynamicUITable.setFieldName(FIELD_NAME_UPDATE);
                                            }
                                        }

//                                        if (LOAN_TYPE.equalsIgnoreCase(LOAN_NAME_JLG) && SCREEN_NAME.equalsIgnoreCase(SCREEN_NAME_PERSONAL_DETAIL)) {
//                                            DynamicUITable dynamicUITable_performCB = getObjectByTAG(TAG_NAME_PERFORM_CB, dynamicUITableListFromDB);
//                                            if (dynamicUITable_performCB != null) {
//                                                dynamicUITable_performCB.setVisibility(true);
//                                            }
//                                        }
                                        if(LOAN_TYPE.equalsIgnoreCase(LOAN_NAME_JLG) &&
                                                (SCREEN_NAME.equalsIgnoreCase(SCREEN_NAME_APPLICANT_KYC)|| SCREEN_NAME.equalsIgnoreCase(SCREEN_NAME_PERSONAL_DETAIL))) {
                                            checkCBStatus(dynamicUITableListFromDB);
                                        }

                                        deleteAndInsertNewRecord(dynamicUITableListFromDB, rawDataTable.getScreen_name());

                                    }
                                }

                            } catch (Exception ex) {
                                ex.printStackTrace();
                                dynamicUI(list);
                            }
                        } else {
                            // TODO: Fresh Data

                            if (SCREEN_NAME.equalsIgnoreCase(SCREEN_NAME_PERSONAL_DETAIL)
                                    || SCREEN_NAME.equalsIgnoreCase(SCREEN_NAME_COAPPLICANT_PERSONAL_DETAIL)
                                    || SCREEN_NAME.equalsIgnoreCase(SCREEN_NAME_BUSINESS_PROFILE)
                                    || SCREEN_NAME.equalsIgnoreCase(SCREEN_NAME_HOUSE_EXPENSES)
                                    || SCREEN_NAME.equalsIgnoreCase(SCREEN_NAME_NOMINEE_DETAIL)
                                    || SCREEN_NAME.equalsIgnoreCase(SCREEN_NAME_REFERENCES)
                                    || SCREEN_NAME.equalsIgnoreCase(SCREEN_NAME_COAPPLICANT_ADDRESS_DETAIL)
                                    || SCREEN_NAME.equalsIgnoreCase(SCREEN_NAME_SOCIO_ECONOMIC_DETAIL)

                            ) {
                                getRawDataFromOtherScreenAndUpdate(list, SCREEN_NAME);

                            } else if (SCREEN_NAME.equalsIgnoreCase(SCREEN_NAME_LOAN_PROPOSAL)) {
                                calculateLoanProposalFinalPD(list.get(0), list, false);
                            } else if (SCREEN_NAME.equalsIgnoreCase(SCREEN_NAME_COLLECTION)) {
                                DynamicUITable dynamicUITable = getObjectByTAG(TAG_NAME_CUSTOMER_ID_IN_COLLECTION, list);
                                if (dynamicUITable != null) {
                                    getCollectionTableData(list, dynamicUITable);
                                }
                            } else if (SCREEN_NAME.equalsIgnoreCase(SCREEN_NAME_SUMMARY)) {
                                calculateNBI(list.get(0), list);
                            } else if (SCREEN_NAME.equalsIgnoreCase(SCREEN_NAME_SUMMARY_LOAN_PROPOSAL)) {
                                getCBAmountFromServer(list.get(0), list);
                            } else if (SCREEN_NAME.equalsIgnoreCase(SCREEN_NAME_LOAN_APPROVAL_MSME)) {
                                loanApprovalCalculation(list.get(list.size() - 1), list);
                            } else if (SCREEN_NAME.equalsIgnoreCase(SCREEN_NAME_CENTER_CREATION)) {
                                centerCreationScreenValidation(list.get(list.size() - 1), list);
                            } else if (!TextUtils.isEmpty(LOAN_TYPE) && LOAN_TYPE.equalsIgnoreCase(LOAN_NAME_INDIVIDUAL)
                                    && SCREEN_NAME.equalsIgnoreCase(SCREEN_NAME_APPLICANT_LOAN_PROPOSAL)) {
                                DynamicUITable dynamicUITable = getObjectByTAG(TAG_NAME_LOAN_TYPE, list);
                                if (dynamicUITable != null) {
                                    applicantLoanProposalScreenChangesByDropdown(dynamicUITable, list);
                                }
                            } else if (!TextUtils.isEmpty(LOAN_TYPE) && LOAN_TYPE.equalsIgnoreCase(LOAN_NAME_AHL)
                                    && SCREEN_NAME.equalsIgnoreCase(SCREEN_NAME_APPLICANT_LOAN_PROPOSAL)) {
                                // TODO: AHL applicant loan proposal
                                DynamicUITable dynamicUITable = getObjectByTAG(TAG_NAME_LOAN_TYPE, list);
                                if (dynamicUITable != null) {
                                    applicantLoanProposalScreenChangesByDropdown(dynamicUITable, list);
                                }
                            }
                            else if (!TextUtils.isEmpty(LOAN_TYPE) && LOAN_TYPE.equalsIgnoreCase(LOAN_NAME_PHL)
                                    && SCREEN_NAME.equalsIgnoreCase(SCREEN_NAME_APPLICANT_LOAN_PROPOSAL)) {
                                // TODO: PHL applicant loan proposal
                                DynamicUITable dynamicUITable = getObjectByTAG(TAG_NAME_LOAN_TYPE, list);
                                if (dynamicUITable != null) {
                                    applicantLoanProposalScreenChangesByDropdown(dynamicUITable, list);
                                }
                            }
                            else if (!TextUtils.isEmpty(LOAN_TYPE) && LOAN_TYPE.equalsIgnoreCase(LOAN_NAME_JLG)
                                    && SCREEN_NAME.equalsIgnoreCase(SCREEN_NAME_APPLICANT_LOAN_PROPOSAL)) {
                                getRawDataFromOtherScreenAndUpdate(list, SCREEN_NAME);
                                DynamicUITable dynamicUITable = getObjectByTAG(TAG_NAME_LOAN_APPLICATION_DATE, list);
                                String currentDate = appHelper.getCurrentDate(DATE_FORMAT_DD_MM_YYYY2);
                                dynamicUITable.setValue(currentDate);
                            }else if (!TextUtils.isEmpty(LOAN_TYPE) && (LOAN_TYPE.equalsIgnoreCase(LOAN_NAME_MSME)||LOAN_TYPE.equalsIgnoreCase(LOAN_NAME_AHL)||LOAN_TYPE.equalsIgnoreCase(LOAN_NAME_PHL))
                                    && SCREEN_NAME.equalsIgnoreCase(SCREEN_NAME_LEAD)) {
                                getRawDataFromOtherScreenAndUpdate(list, SCREEN_NAME);
                            }else if (!TextUtils.isEmpty(LOAN_TYPE) && (LOAN_TYPE.equalsIgnoreCase(LOAN_NAME_MSME)||LOAN_TYPE.equalsIgnoreCase(LOAN_NAME_AHL)||LOAN_TYPE.equalsIgnoreCase(LOAN_NAME_PHL))
                                    && SCREEN_NAME.equalsIgnoreCase(SCREEN_NAME_APPLICANT_KYC)) {
                                Log.e(TAG, "APPLICANT EKYC: "+LOAN_TYPE);
                                getRawDataFromOtherScreenAndUpdate(list, SCREEN_NAME);
                            }else if (!TextUtils.isEmpty(LOAN_TYPE) && (LOAN_TYPE.equalsIgnoreCase(LOAN_NAME_MSME)||LOAN_TYPE.equalsIgnoreCase(LOAN_NAME_AHL)||LOAN_TYPE.equalsIgnoreCase(LOAN_NAME_PHL))
                                    && SCREEN_NAME.equalsIgnoreCase(SCREEN_NAME_SALES_TOOL)) {
                                getRawDataFromOtherScreenAndUpdate(list, SCREEN_NAME);
                            } else {
                                dynamicUI(list);
                                Log.e(TAG, "APPLICANT EKYC: 3333 "+LOAN_TYPE);
                            }
                        }
                    }
                };
                viewModel.getRawTableLiveData().observe(this, getLeadRawDataObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void checkCBStatus(List<DynamicUITable> dynamicUITableListFromDB) {
        DynamicUITable dynamicUITableRef = dynamicUITableListFromDB.get(0);
        if (dynamicUITableRef != null) {
            try {
                viewModel.getCBCheckTableData(dynamicUITableRef.getClientID());
                if (viewModel.getCBCheckTableLiveData() != null) {
                    Observer getCBCheckTableDataObserver = new Observer() {
                        @Override
                        public void onChanged(@Nullable Object o) {
                            CBCheckTable cbCheckTable = (CBCheckTable) o;
                            viewModel.getCBCheckTableLiveData().removeObserver(this);
                            if (cbCheckTable != null && (cbCheckTable.getStatus() != null) && (cbCheckTable.getStatus().equalsIgnoreCase(CB_STATUS_ACCEPT))) {
                                for (DynamicUITable dynamicUITable : dynamicUITableListFromDB) {
                                    if (dynamicUITable != null &&
                                            (!dynamicUITable.getFieldTag().equalsIgnoreCase(TAG_NAME_CONTACT_NO_1) && !dynamicUITable.getFieldTag().equalsIgnoreCase(TAG_NAME_RE_ENTER_CONTACT_NO_1))) {
                                        dynamicUITable.setEditable(false);
                                    }

                                }
                                updateDynamicUITable(dynamicUITableListFromDB, SCREEN_ID);
                            }
                        }
                    };
                    viewModel.getCBCheckTableLiveData().observe(this, getCBCheckTableDataObserver);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }

    }


    private void setSyncForDynamicUITable(List<DynamicUITable> dynamicUITableListFromDB, boolean sync) {
        try {
            if (dynamicUITableListFromDB != null && dynamicUITableListFromDB.size() > 0) {
                for (DynamicUITable dynamicUITable : dynamicUITableListFromDB) {
                    dynamicUITable.setSync(sync);
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    private void getCollectionTableData(List<DynamicUITable> dynamicUITableList, DynamicUITable dynamicUITable) {
        try {
            viewModel.getCollectionTableData(dynamicUITableList, dynamicUITable);
            if (viewModel.getDynamicUITableLiveData() != null) {
                Observer getCollectionTableDataObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        List<DynamicUITable> dynamicUITableListFromDB = (List<DynamicUITable>) o;
                        viewModel.getDynamicUITableLiveData().removeObserver(this);
                        dynamicUI(dynamicUITableListFromDB);

                    }
                };
                viewModel.getDynamicUITableLiveData().observe(this, getCollectionTableDataObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    // TODO: use this method to auto fill values from other screen ( Eg : lead to personal detail screen )
    private void getRawDataFromOtherScreenAndUpdate(List<DynamicUITable> dynamicUITableList, String screenNameToUpdate) {
        Log.e("BBBBB", "APPLICANT EKYC: 8888 ");
        try {
            viewModel.getRawDataFromOtherScreenAndUpdate(dynamicUITableList, screenNameToUpdate);
            if (viewModel.getDynamicUITableLiveData() != null) {
                Observer getRawDataFromOtherScreenAndUpdateObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        List<DynamicUITable> dynamicUITableListFromDB = (List<DynamicUITable>) o;
                        viewModel.getDynamicUITableLiveData().removeObserver(this);

                        // TODO: loan type should not be AHL
                        if (!TextUtils.isEmpty(LOAN_TYPE) && !LOAN_TYPE.equalsIgnoreCase(LOAN_NAME_AHL)
                                && SCREEN_NAME.equalsIgnoreCase(SCREEN_NAME_BUSINESS_PROFILE)) {
                            DynamicUITable industryClassObj = getObjectByTAG(TAG_NAME_INDUSTRY_CLASS, dynamicUITableListFromDB);
                            String industryClassItem = "";
                            if (industryClassObj != null && ! TextUtils.isEmpty(industryClassObj.getValue())) {
                                industryClassItem = industryClassObj.getValue();

                                for (DynamicUITable dynamicUITable : dynamicUITableListFromDB) {
                                    if (dynamicUITable.getFieldTag().equalsIgnoreCase(TAG_NAME_TYPE_OF_BUSINESS)) {
                                        changeSpinnerList(TAG_NAME_TYPE_OF_BUSINESS, industryClassItem, SCREEN_ID, dynamicUITableListFromDB);

                                        break;
                                    }
                                }
                            }

                            else{
                                dynamicUI(dynamicUITableListFromDB);
                            }



                        }
                        // TODO: loan type should not be PHL
                        if (!TextUtils.isEmpty(LOAN_TYPE) && !LOAN_TYPE.equalsIgnoreCase(LOAN_NAME_PHL)
                                && SCREEN_NAME.equalsIgnoreCase(SCREEN_NAME_BUSINESS_PROFILE)) {
                            DynamicUITable industryClassObj = getObjectByTAG(TAG_NAME_INDUSTRY_CLASS, dynamicUITableListFromDB);
                            String industryClassItem = "";
                            if (industryClassObj != null && ! TextUtils.isEmpty(industryClassObj.getValue())) {
                                industryClassItem = industryClassObj.getValue();

                                for (DynamicUITable dynamicUITable : dynamicUITableListFromDB) {
                                    if (dynamicUITable.getFieldTag().equalsIgnoreCase(TAG_NAME_TYPE_OF_BUSINESS)) {
                                        changeSpinnerList(TAG_NAME_TYPE_OF_BUSINESS, industryClassItem, SCREEN_ID, dynamicUITableListFromDB);

                                        break;
                                    }
                                }
                            }

                            else{
                                dynamicUI(dynamicUITableListFromDB);
                            }



                        }
//                        else if (!TextUtils.isEmpty(LOAN_TYPE) && LOAN_TYPE.equalsIgnoreCase(LOAN_NAME_JLG)
//                                && SCREEN_NAME.equalsIgnoreCase(SCREEN_NAME_NOMINEE_DETAIL)) {
//
//                            DynamicUITable relativesOfApplicantObj = getObjectByTAG(TAG_NAME_RELATIONSHIP_WITH_THE_APPLICANT, dynamicUITableListFromDB);
//                            DynamicUITable saveObj = getObjectByTAG(TAG_NAME_SAVE_BUTTON, dynamicUITableList);
//
//                            if (saveObj != null && saveObj.getFieldName().equalsIgnoreCase(FIELD_NAME_SAVE)) {
//                                List<ParameterInfo> parameterInfoList = new ArrayList<>();
//
//                                if (relativesOfApplicantObj != null && relativesOfApplicantObj.getDefaultValue().equalsIgnoreCase(SPINNER_ITEM_FIELD_NAME_SPOUSE)) {
//
//                                    parameterInfoList.add(new ParameterInfo(TAG_NAME_HOSPI_CASH_REQUIRED, SCREEN_ID, "Yes", true, true));
//                                    parameterInfoList.add(new ParameterInfo(TAG_NAME_HOSPI_NO_OF_YEARS, SCREEN_ID, "", true, true));
//                                    parameterInfoList.add(new ParameterInfo(TAG_NAME_HOSPI_CASH_TYPE, SCREEN_ID, "", true, true));
//                                 //   parameterInfoList.add(new ParameterInfo(TAG_NAME_HOSPI_RELATIONSHIP, SCREEN_ID, relativesOfApplicantObj.getValue(), true, false));
//                                    parameterInfoList.add(new ParameterInfo(TAG_NAME_NOMINEE_GENDER, SCREEN_ID, "MALE", true, false));
//
//                                } else {
//                                    parameterInfoList.add(new ParameterInfo(TAG_NAME_HOSPI_CASH_REQUIRED, SCREEN_ID, "", true, true));
//                                    parameterInfoList.add(new ParameterInfo(TAG_NAME_HOSPI_NO_OF_YEARS, SCREEN_ID, "", false, true));
//                                   // parameterInfoList.add(new ParameterInfo(TAG_NAME_HOSPI_CASH_TYPE, SCREEN_ID, "", false, true));
//                                    parameterInfoList.add(new ParameterInfo(TAG_NAME_NOMINEE_GENDER, SCREEN_ID, "", true, true));
//                                  //  parameterInfoList.add(new ParameterInfo(TAG_NAME_HOSPI_RELATIONSHIP, SCREEN_ID, "", false, true));
//
//                                }
//                                EnableOrDisableByFieldNameInDB(parameterInfoList, dynamicUITableListFromDB);
//                            } else {
//
//                                dynamicUI(dynamicUITableListFromDB);
//
//                            }
//
//                        }
                        else {
                            dynamicUI(dynamicUITableListFromDB);
                        }

                    }
                };
                viewModel.getDynamicUITableLiveData().observe(this, getRawDataFromOtherScreenAndUpdateObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void getRawDataForChildFragmentNew(String screenName, List<DynamicUITable> list, String fieldTag, String loanType) {
        try {
            viewModel.getRawDataForChildFragment(screenName, CLIENT_ID, fieldTag, loanType);
            if (viewModel.getUpdatedRawdataRow() != null) {
                Observer getRawDataForChildFragmentObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        RawDataTable rawDataTable = (RawDataTable) o;
                        viewModel.getUpdatedRawdataRow().removeObserver(this);
                        if (rawDataTable != null) {
                            try {
                                if (!TextUtils.isEmpty(rawDataTable.getDynamic_ui_rawdata())) {
                                    Type type = new TypeToken<List<DynamicUITable>>() {
                                    }.getType();
                                    List<DynamicUITable> dynamicUITableListFromDB = new Gson().fromJson(rawDataTable.getDynamic_ui_rawdata(), type);
                                    // TODO: Setting dynamic ui table sync
                                    setSyncForDynamicUITable(dynamicUITableListFromDB, list.get(0).isSync());

                                    if (dynamicUITableListFromDB != null && dynamicUITableListFromDB.size() > 0) {
                                       /* DynamicUITable dynamicUITable = getObjectByTAG(TAG_NAME_SAVE_BUTTON, dynamicUITableListFromDB);
                                        if (dynamicUITable != null) {
                                            dynamicUITable.setFieldName(FIELD_NAME_UPDATE);
                                        }*/
                                        if(LOAN_TYPE.equalsIgnoreCase(LOAN_NAME_JLG) &&
                                                (SCREEN_NAME.equalsIgnoreCase(SCREEN_NAME_APPLICANT_KYC)|| SCREEN_NAME.equalsIgnoreCase(SCREEN_NAME_PERSONAL_DETAIL))) {
                                            checkCBStatus(dynamicUITableListFromDB);
                                        }
                                        deleteAndInsertNewRecord(dynamicUITableListFromDB, rawDataTable.getScreen_name());
                                    }
                                }

                            } catch (Exception ex) {
                                ex.printStackTrace();
                                dynamicUI(list);
                            }
                        } else {
                            // TODO: Fresh Data
                            dynamicUI(list);
                        }
                    }
                };
                viewModel.getUpdatedRawdataRow().observe(this, getRawDataForChildFragmentObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void dedupeValidation(String screenToCheck, String value, String tagToCheck,
                                 DynamicUITable dynamicUITable, List<DynamicUITable> dynamicUITableList) {
        ArrayList<HashMap<String, Object>> hashMapList = new ArrayList<>();
        try {
            viewModel.getLeadRawData(screenToCheck, USER_ID);
            if (viewModel.getRawTableLiveData() != null) {
                Observer getLeadRawDataObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        List<RawDataTable> rawDataTableList = (List<RawDataTable>) o;
                        viewModel.getRawTableLiveData().removeObserver(this);
                        if (rawDataTableList != null && rawDataTableList.size() > 0) {
                            for (RawDataTable rawDataTable : rawDataTableList) {
                                HashMap<String, Object> hashMap = setKeyValueForObject(rawDataTable);
                                hashMapList.add(hashMap);
                            }
                            Log.d(TAG, "HashMapList ==> " + hashMapList.toString());
                            if (hashMapList != null && hashMapList.size() > 0) {
                                for (HashMap<String, Object> hashMap : hashMapList) {
                                    if (hashMap.containsKey(tagToCheck)) {
                                        String valueFromDB = hashMap.get(tagToCheck).toString();
                                        if (valueFromDB.equalsIgnoreCase(value)) {
//                                            ((XMLCustomTIL) view).setErrorMsg("Mobile No Already Exist");
//                                            ((XMLCustomTIL) view).setHasValidInput(false);
                                            dynamicUITable.setValid(false);
                                            if (dynamicUITable.getFieldTag().equalsIgnoreCase(TAG_NAME_ACCOUNT_NUMBER)) {
                                                dynamicUITable.setErrorMessage("Applicant and Co-Applicant Account No.s should not be same");
                                            } else {
                                                dynamicUITable.setErrorMessage("Mobile No Already Exist");
                                            }

                                            break;
                                        } else {
                                            dynamicUITable.setValid(true);
                                        }
                                    }
                                }
                                updateDynamicUITable(dynamicUITableList, dynamicUITable.getScreenID());
                            }
                        }
                    }
                };
                viewModel.getRawTableLiveData().observe(this, getLeadRawDataObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void loanAmountValidation(DynamicUITable dynamicUITable, List<DynamicUITable> dynamicUITableList) {
        try {
            viewModel.loanAmountValidation(dynamicUITable, dynamicUITableList);
            if (viewModel.getDynamicUITableRowLiveData() != null) {
                Observer loanAmountValidationObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        DynamicUITable dynamicUITableFromDB = (DynamicUITable) o;
                        viewModel.getDynamicUITableRowLiveData().removeObserver(this);

                        setValueForSingleFieldInUI(dynamicUITableFromDB, dynamicUITableList);
                    }
                };

                viewModel.getDynamicUITableRowLiveData().observe(this, loanAmountValidationObserver);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void ageValidationJLG(DynamicUITable dynamicUITable, List<DynamicUITable> dynamicUITableList) {
        try {
            viewModel.ageValidationJLG(dynamicUITable, dynamicUITableList);
            if (viewModel.getDynamicUITableRowLiveData() != null) {
                Observer ageValidationJLGObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        DynamicUITable dynamicUITableFromDB = (DynamicUITable) o;
                        viewModel.getDynamicUITableRowLiveData().removeObserver(this);

                        setValueForSingleFieldInUI(dynamicUITableFromDB, dynamicUITableList);
                        if(dynamicUITable.getFieldTag().equalsIgnoreCase(TAG_NAME_NOMINEE_AGE)){
                            if(dynamicUITable.isValid() && Integer.parseInt(dynamicUITable.getValue())<18){
                                // TODO: Enable Gaurdian details
                                List<ParameterInfo> parameterInfoList = new ArrayList<>();
                                parameterInfoList.add(new ParameterInfo(TAG_NAME_GUARDIAN_DETAIL, SCREEN_ID, "", true, true));
                                parameterInfoList.add(new ParameterInfo(TAG_NAME_GUARDIAN_FULL_NAME, SCREEN_ID, "", true, true));
                                parameterInfoList.add(new ParameterInfo(TAG_NAME_GUARDIAN_KYC_TYPE, SCREEN_ID, "", true, true));
                                parameterInfoList.add(new ParameterInfo(TAG_NAME_GUARDIAN_KYC_ID, SCREEN_ID, "", true, true));
                                parameterInfoList.add(new ParameterInfo(TAG_NAME_GUARDIAN_DATE_OF_BIRTH, SCREEN_ID, "", true, true));
                                parameterInfoList.add(new ParameterInfo(TAG_NAME_GUARDIAN_AGE, SCREEN_ID, "", true, true));
                                parameterInfoList.add(new ParameterInfo(TAG_NAME_GUARDIAN_GENDER, SCREEN_ID, "", true, true));
                                EnableOrDisableByFieldNameInDB(parameterInfoList, dynamicUITableList);

                            }else{
                                // TODO: Disable Gaurdian details
                                List<ParameterInfo> parameterInfoList = new ArrayList<>();
                                parameterInfoList.add(new ParameterInfo(TAG_NAME_GUARDIAN_DETAIL, SCREEN_ID, "", false, true));
                                parameterInfoList.add(new ParameterInfo(TAG_NAME_GUARDIAN_FULL_NAME, SCREEN_ID, "", false, true));
                                parameterInfoList.add(new ParameterInfo(TAG_NAME_GUARDIAN_KYC_TYPE, SCREEN_ID, "", false, true));
                                parameterInfoList.add(new ParameterInfo(TAG_NAME_GUARDIAN_KYC_ID, SCREEN_ID, "", false, true));
                                parameterInfoList.add(new ParameterInfo(TAG_NAME_GUARDIAN_DATE_OF_BIRTH, SCREEN_ID, "", false, true));
                                parameterInfoList.add(new ParameterInfo(TAG_NAME_GUARDIAN_AGE, SCREEN_ID, "", false, true));
                                parameterInfoList.add(new ParameterInfo(TAG_NAME_GUARDIAN_GENDER, SCREEN_ID, "", false, true));
                                EnableOrDisableByFieldNameInDB(parameterInfoList, dynamicUITableList);

                            }


                        }
                    }
                };

                viewModel.getDynamicUITableRowLiveData().observe(this, ageValidationJLGObserver);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void ageValidationJLG_Radio(DynamicUITable dynamicUITable, List<DynamicUITable> dynamicUITableList) {
        try {
            viewModel.ageValidationJLG_Radio(dynamicUITable, dynamicUITableList);
            if (viewModel.getDynamicUITableRowLiveData() != null) {
                Observer ageValidationJLG_RadioObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        DynamicUITable dynamicUITableFromDB = (DynamicUITable) o;
                        viewModel.getDynamicUITableRowLiveData().removeObserver(this);

                        if (dynamicUITableFromDB != null && !dynamicUITableFromDB.isValid()) {
                            setValueForSingleFieldInUI(dynamicUITableFromDB, dynamicUITableList);
                        } else {

                            List<ParameterInfo> parameterInfoList = new ArrayList<>();
                            parameterInfoList.add(new ParameterInfo(TAG_NAME_APPLICANT_SPOUSE_NAME, SCREEN_ID, "", false, true));
                            parameterInfoList.add(new ParameterInfo(TAG_NAME_KYC_TYPE_SPOUSE_JLG, SCREEN_ID, "", false, true));
                            parameterInfoList.add(new ParameterInfo(TAG_NAME_SPOUSE_DATE_OF_BIRTH, SCREEN_ID, "", false, true));
                            parameterInfoList.add(new ParameterInfo(TAG_NAME_SPOUSE_AGE, SCREEN_ID, "", false, true));

                            DynamicUITable dynamicUITablekycid = getObjectByFieldName(TAG_NAME_KYC_ID_SPOUSE_JLG, dynamicUITableList);
                            if (dynamicUITablekycid != null && dynamicUITablekycid.getFieldTag() != null) {
                                parameterInfoList.add(new ParameterInfo(dynamicUITablekycid.getFieldTag(), SCREEN_ID, "", false, true));
                            }
                            parameterInfoList.add(new ParameterInfo(TAG_NAME_QR_READING_BUTTON, SCREEN_ID, "", false, true));
                            parameterInfoList.add(new ParameterInfo(TAG_NAME_FATHER_NAME, SCREEN_ID, "", true, true));
                            EnableOrDisableByFieldNameInDB(parameterInfoList, dynamicUITableList);
                        }
                    }
                };

                viewModel.getDynamicUITableRowLiveData().observe(this, ageValidationJLG_RadioObserver);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    // TODO: unique for month in add banking historyy, sales bills
    private void deDupValidationforMonthInPD(DynamicUITable dynamicUITable, List<DynamicUITable> dynamicUITableList, String month_datepicker) {
        try {
            viewModel.deDupValidationforMonthInPD(dynamicUITable, dynamicUITableList, month_datepicker);
            if (viewModel.getDynamicUITableRowLiveData() != null) {
                Observer deDupValidationforMonthInPDObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        DynamicUITable dynamicUITableFromDB = (DynamicUITable) o;
                        viewModel.getDynamicUITableRowLiveData().removeObserver(this);

                        setValueForSingleFieldInUI(dynamicUITableFromDB, dynamicUITableList);
                    }
                };

                viewModel.getDynamicUITableRowLiveData().observe(this, deDupValidationforMonthInPDObserver);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void deDupeValidationForAllScreen(DynamicUITable dynamicUITable, List<DynamicUITable> dynamicUITableList) {
        try {
            viewModel.deDupeValidationForAllScreen(dynamicUITable, dynamicUITableList);
            if (viewModel.getDynamicUITableRowLiveData() != null) {
                Observer deDupeValidationForAllScreenObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        DynamicUITable dynamicUITableFromDB = (DynamicUITable) o;
                        viewModel.getDynamicUITableRowLiveData().removeObserver(this);

                        // TODO: If it is valid and kyc type is RE ENTER PAN then call PAN Card validation
                        if (dynamicUITableFromDB != null && dynamicUITableFromDB.isValid()
                                && dynamicUITableFromDB.getFieldTag().equalsIgnoreCase(TAG_NAME_KYC_TYPE_RE_ENTER_PANCARD)) {

                            if (appHelper.isNetworkAvailable()) {
                                // TODO: add a dialogue here

                                appHelper.getDialogHelper().getConfirmationDialog().showTwoButtons(
                                        "Would you like to do PAN card verification ? ", "Yes", " No,Manual Entry ", new ConfirmationDialog.PrintActionCallback() {
                                            @Override
                                            public void onAction() {
                                                // TODO: No
                                                List<ParameterInfo> parameterInfoList = new ArrayList<>();
                                                parameterInfoList.add(new ParameterInfo(TAG_NAME_IS_VERIFIED, SCREEN_ID, "false", false, true));
                                                EnableOrDisableByFieldNameInDB(parameterInfoList, dynamicUITableList);

                                            }

                                            @Override
                                            public void onPrint() {
                                                // TODO: Yes

                                                panValidationServiceCall(dynamicUITableFromDB, dynamicUITableList);
                                            }
                                        });
                            }

                        } else {
                            // TODO: For other screens set error tvMobNo
                            setValueForSingleFieldInUI(dynamicUITableFromDB, dynamicUITableList);
                        }
                    }
                };

                viewModel.getDynamicUITableRowLiveData().observe(this, deDupeValidationForAllScreenObserver);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // TODO: PAN VALIDATION SERVICE CALL
    private void panValidationServiceCall(DynamicUITable dynamicUITable, List<DynamicUITable> dynamicUITableList) {
        try {
            appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();

            viewModel.panValidationServiceCall(dynamicUITable, dynamicUITableList);
            if (viewModel.getDynamicUITableLiveData() != null) {
                Observer observer = new Observer() {
                    @Override
                    public void onChanged(Object o) {

                        appHelper.getDialogHelper().getLoadingDialog().closeDialog();

                        List<DynamicUITable> dynamicUITableListFromDB = (List<DynamicUITable>) o;
                        viewModel.getDynamicUITableLiveData().removeObserver(this::onChanged);

                        if (dynamicUITableListFromDB != null && dynamicUITableListFromDB.size() > 0) {

                            DynamicUITable kycIdObj = getObjectByTAG(TAG_NAME_KYC_TYPE_RE_ENTER_PANCARD, dynamicUITableListFromDB);
                            if (kycIdObj != null && kycIdObj.isValid()) {

                                appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.SUCCESS,
                                        SUCCESS_RESPONSE_FOR_PAN_VALIDATION, new ConfirmationDialog.ActionCallback() {
                                            @Override
                                            public void onAction() {
                                                dynamicUI(dynamicUITableListFromDB);
                                            }
                                        });
                            } else {
                                appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ERROR,
                                        FAILURE_RESPONSE_FOR_PAN_VALIDATION, new ConfirmationDialog.ActionCallback() {
                                            @Override
                                            public void onAction() {
                                                dynamicUI(dynamicUITableListFromDB);
                                            }
                                        });
                            }
                        }

                    }
                };

                viewModel.getDynamicUITableLiveData().observe(this, observer);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // TODO: IFSC Service Call
    private void getIFSCDataFromServer(DynamicUITable dynamicUITable, List<DynamicUITable> dynamicUITableList) {
        try {
            appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();

            viewModel.getIFSCDataFromServer(dynamicUITable, dynamicUITableList);
            if (viewModel.getDynamicUITableLiveData() != null) {
                Observer observer = new Observer() {
                    @Override
                    public void onChanged(Object o) {

                        appHelper.getDialogHelper().getLoadingDialog().closeDialog();

                        List<DynamicUITable> dynamicUITableListFromDB = (List<DynamicUITable>) o;
                        viewModel.getDynamicUITableLiveData().removeObserver(this::onChanged);
                        DynamicUITable branchNameObj = getObjectByTAG(TAG_NAME_BRANCH_NAME, dynamicUITableListFromDB);
                        DynamicUITable bankNameObj = getObjectByTAG(TAG_NAME_BANK_NAME, dynamicUITableListFromDB);
                        if(branchNameObj!=null && !TextUtils.isEmpty(branchNameObj.getValue())
                                && bankNameObj!=null && !TextUtils.isEmpty(bankNameObj.getValue())) {
                            dynamicUI(dynamicUITableListFromDB);
                        }else{
                            appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ERROR,
                                    FAILURE_RESPONSE_FOR_IFSC, new ConfirmationDialog.ActionCallback() {
                                        @Override
                                        public void onAction() {

                                            dynamicUI(dynamicUITableListFromDB);
                                        }
                                    });
                        }

                    }
                };

                viewModel.getDynamicUITableLiveData().observe(this, observer);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // TODO: AADHAAR VAULT SERVICE CALL
    private void aadhaarVaultServiceCall(DynamicUITable dynamicUITable, List<DynamicUITable> dynamicUITableList) {
        try {
            appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();

            viewModel.aadhaarVaultServiceCall(dynamicUITable, dynamicUITableList);
            if (viewModel.getDynamicUITableLiveData() != null) {
                Observer observer = new Observer() {
                    @Override
                    public void onChanged(Object o) {

                        appHelper.getDialogHelper().getLoadingDialog().closeDialog();

                        List<DynamicUITable> dynamicUITableListFromDB = (List<DynamicUITable>) o;
                        viewModel.getDynamicUITableLiveData().removeObserver(this::onChanged);

                        if (dynamicUITableListFromDB != null && dynamicUITableListFromDB.size() > 0) {

                            dynamicUI(dynamicUITableListFromDB);

                            DynamicUITable kycIdObj = getObjectByTAG(dynamicUITable.getFieldTag(), dynamicUITableListFromDB);

                            if (kycIdObj != null && !TextUtils.isEmpty(kycIdObj.getOptional())) {
                                Toast.makeText(getActivity(), SUCCESS_RESPONSE_FOR_AADHAAR_VAULT, Toast.LENGTH_SHORT).show();


                                // TODO: Checking deDupe validation for Aadhaar Reference ID & only for Re Enter KYC ID
                                if (kycIdObj.getFieldTag().equalsIgnoreCase(TAG_NAME_KYC_TYPE_RE_ENTER_AADHAAR)) {

                                    deDupeValidationForAllScreen(kycIdObj, dynamicUITableListFromDB);
                                }else if((kycIdObj.getScreenName().equalsIgnoreCase(SCREEN_NAME_PERSONAL_DETAIL) || kycIdObj.getScreenName().equalsIgnoreCase(SCREEN_NAME_NOMINEE_DETAIL))
                                        && kycIdObj.getFieldTag().equalsIgnoreCase(TAG_NAME_KYC_TYPE_AADHAAR)){
                                    deDupeValidationForAllScreen(kycIdObj, dynamicUITableListFromDB);

                                }
                            } else {
                                appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ERROR,
                                        FAILURE_RESPONSE_FOR_AADHAAR_VAULT, new ConfirmationDialog.ActionCallback() {
                                            @Override
                                            public void onAction() {
                                                dynamicUI(dynamicUITableListFromDB);
                                            }
                                        });
                            }
                        }

                    }
                };

                viewModel.getDynamicUITableLiveData().observe(this, observer);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void dedupeValidationForKYCId(String fromScreenName, String screenToCheck, String value, String kyc_type,
                                         DynamicUITable dynamicUITable, List<DynamicUITable> dynamicUITableList) {
        ArrayList<HashMap<String, Object>> hashMapList = new ArrayList<>();
        try {
            viewModel.getApplicantKycRawData(fromScreenName, screenToCheck, dynamicUITable.getFieldTag(), dynamicUITable.getClientID());
            if (viewModel.getRawTableLiveData() != null) {
                Observer getLeadRawDataObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        List<RawDataTable> rawDataTableList = (List<RawDataTable>) o;
                        viewModel.getRawTableLiveData().removeObserver(this);
                        if (rawDataTableList != null && rawDataTableList.size() > 0) {
                            for (RawDataTable rawDataTable : rawDataTableList) {
                                HashMap<String, Object> hashMap = setKeyValueForObject(rawDataTable);
                                hashMapList.add(hashMap);
                            }
                            Log.d(TAG, "HashMapList ==> " + hashMapList.toString());
                            if (hashMapList != null && hashMapList.size() > 0) {
                                for (HashMap<String, Object> hashMap : hashMapList) {

                                    if (hashMap.containsKey(TAG_NAME_KYC_ID) && hashMap.containsKey(TAG_NAME_KYC_TYPE)) {
                                        String kyctype_db = hashMap.get(TAG_NAME_KYC_TYPE).toString();

                                        String valueFromDB = hashMap.get(TAG_NAME_KYC_ID).toString();
                                        if (kyctype_db.equalsIgnoreCase(kyc_type) && valueFromDB.equalsIgnoreCase(value)) {

                                            dynamicUITable.setValid(false);
                                            dynamicUITable.setErrorMessage("Applicant and CoApplicant KYC Ids should not be same");
                                            break;
                                        } else if (fromScreenName.equalsIgnoreCase(SCREEN_NAME_APPLICANT_KYC)) {
                                            if (valueFromDB.equalsIgnoreCase(value)) {
                                                dynamicUITable.setValid(false);
                                                dynamicUITable.setErrorMessage("KYC already exists");
                                                break;
                                            } else {
                                                dynamicUITable.setValid(true);
                                            }
                                        } else {
                                            dynamicUITable.setValid(true);
                                        }
                                    }
                                }
                                updateDynamicUITable(dynamicUITableList, dynamicUITable.getScreenID());
                            }
                        }
                    }
                };
                viewModel.getRawTableLiveData().observe(this, getLeadRawDataObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    public void dedupeValidationForKYCIdforcoapplicant(String screenname1, String screenname2, String value, String kyc_type, String module_type,
                                                       DynamicUITable dynamicUITable, List<DynamicUITable> dynamicUITableList) {
        ArrayList<HashMap<String, Object>> hashMapList = new ArrayList<>();
        try {
            viewModel.getApplicantKycRawData(screenname1, dynamicUITable.getFieldTag(), dynamicUITable.getClientID());

            if (viewModel.getRawTableLiveData() != null) {
                Observer getLeadRawDataObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        List<RawDataTable> rawDataTableList = (List<RawDataTable>) o;
                        viewModel.getRawTableLiveData().removeObserver(this);
                        if (rawDataTableList != null && rawDataTableList.size() > 0) {
                            for (RawDataTable rawDataTable : rawDataTableList) {
                                HashMap<String, Object> hashMap = setKeyValueForObject(rawDataTable);
                                hashMapList.add(hashMap);
                            }
                            Log.d(TAG, "HashMapList ==> " + hashMapList.toString());
                            if (hashMapList != null && hashMapList.size() > 0) {
                                for (HashMap<String, Object> hashMap : hashMapList) {
                                    if (screenname2.equalsIgnoreCase(SCREEN_NAME_CO_APPLICANT_KYC)) {

                                        if (hashMap.containsKey(TAG_NAME_KYC_ID) && hashMap.containsKey(TAG_NAME_KYC_TYPE)) {
                                            String kyctype_db = hashMap.get(TAG_NAME_KYC_TYPE).toString();
                                            kyctype_db = kyctype_db.replaceAll(" ", "");

                                            String valueFromDB = hashMap.get(TAG_NAME_KYC_ID).toString();
                                            if (kyctype_db.equalsIgnoreCase(kyc_type) && valueFromDB.equalsIgnoreCase(value)) {

                                                dynamicUITable.setValid(false);
                                                dynamicUITable.setErrorMessage("Applicant and CoApplicant KYC Ids should not be same");
                                                break;
                                            } else if (screenname1.equalsIgnoreCase(SCREEN_NAME_APPLICANT_KYC)) {
                                                if (valueFromDB.equalsIgnoreCase(value)) {
                                                    dynamicUITable.setValid(false);
                                                    dynamicUITable.setErrorMessage("KYC already exists");
                                                    break;
                                                } else {
                                                    if (hashMapList.indexOf(hashMap) == hashMapList.size() - 1) {
                                                        dedupeValidationForKYCIdforcoapplicantsub(screenname2, value, kyc_type, module_type, dynamicUITable, dynamicUITableList);
                                                    } else {
                                                        dynamicUITable.setValid(true);
                                                    }
                                                }
                                            } else {
                                                if (hashMapList.indexOf(hashMap) == hashMapList.size() - 1) {
                                                    dedupeValidationForKYCIdforcoapplicantsub(screenname2, value, kyc_type, module_type, dynamicUITable, dynamicUITableList);
                                                } else {
                                                    dynamicUITable.setValid(true);
                                                }
                                            }
                                        } else {
                                            if (hashMapList.indexOf(hashMap) == hashMapList.size() - 1) {
                                                dedupeValidationForKYCIdforcoapplicantsub(screenname2, value, kyc_type, module_type, dynamicUITable, dynamicUITableList);
                                            } else {
                                                dynamicUITable.setValid(true);//true
                                            }
                                        }
                                    }
                                    // TODO: mobile number unique for applicant &coapplicant personal details
                                    if (screenname2.equalsIgnoreCase(SCREEN_NAME_COAPPLICANT_PERSONAL_DETAIL)) {

                                        if (hashMap.containsKey(TAG_NAME_CONTACT_NO_1)) {
                                            String mob_db = hashMap.get(TAG_NAME_CONTACT_NO_1).toString();
                                            mob_db = mob_db.replaceAll(" ", "");
                                            if (mob_db.equalsIgnoreCase(value)) {

                                                dynamicUITable.setValid(false);
                                                dynamicUITable.setErrorMessage("Applicant and CoApplicant Mobile number's should not be same");
                                                break;
                                            } else if (screenname1.equalsIgnoreCase(SCREEN_NAME_PERSONAL_DETAIL)) {
                                                if (mob_db.equalsIgnoreCase(value)) {
                                                    dynamicUITable.setValid(false);
                                                    dynamicUITable.setErrorMessage("Applicant and CoApplicant Mobile number's should not be same");
                                                    break;
                                                } else {
                                                    if (hashMapList.indexOf(hashMap) == hashMapList.size() - 1) {
                                                        dedupeValidationForKYCIdforcoapplicantsub(screenname2, value, kyc_type, module_type, dynamicUITable, dynamicUITableList);
                                                    } else {
                                                        dynamicUITable.setValid(true);
                                                    }
                                                }
                                            } else {
                                                if (hashMapList.indexOf(hashMap) == hashMapList.size() - 1) {
                                                    dedupeValidationForKYCIdforcoapplicantsub(screenname2, value, kyc_type, module_type, dynamicUITable, dynamicUITableList);
                                                } else {
                                                    dynamicUITable.setValid(true);
                                                }
                                            }
                                        } else {
                                            if (hashMapList.indexOf(hashMap) == hashMapList.size() - 1) {
                                                dedupeValidationForKYCIdforcoapplicantsub(screenname2, value, kyc_type, module_type, dynamicUITable, dynamicUITableList);
                                            } else {
                                                dynamicUITable.setValid(true);//true
                                            }
                                        }
                                    }
                                }
                                updateDynamicUITable(dynamicUITableList, dynamicUITable.getScreenID());
                            }
                        }
                    }
                };
                viewModel.getRawTableLiveData().observe(this, getLeadRawDataObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void dedupeValidationForKYCIdforcoapplicantsub(String screen2, String value, String kyc_type, String module_type,
                                                          DynamicUITable dynamicUITable, List<DynamicUITable> dynamicUITableList) {
        ArrayList<HashMap<String, Object>> hashMapList = new ArrayList<>();//forcoapplicant
        try {
            viewModel.getApplicantKycRawData(screen2, dynamicUITable.getFieldTag(), dynamicUITable.getClientID());

            if (viewModel.getRawTableLiveData() != null) {
                Observer getLeadRawDataObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        List<RawDataTable> rawDataTableList = (List<RawDataTable>) o;
                        viewModel.getRawTableLiveData().removeObserver(this);
                        if (rawDataTableList != null && rawDataTableList.size() > 0) {
                            for (RawDataTable rawDataTable : rawDataTableList) {
                                HashMap<String, Object> hashMap = setKeyValueForObject(rawDataTable);
                                hashMapList.add(hashMap);
                            }
                            Log.d(TAG, "HashMapList ==> " + hashMapList.toString());
                            if (hashMapList != null && hashMapList.size() > 0) {
                                for (HashMap<String, Object> hashMap : hashMapList) {
                                    if (screen2.equalsIgnoreCase(SCREEN_NAME_CO_APPLICANT_KYC)) {
                                        if (hashMap.containsKey(TAG_NAME_KYC_ID) && hashMap.containsKey(TAG_NAME_KYC_TYPE) && hashMap.containsKey(TAG_NAME_MODULE_TYPE)) {
                                            String kyctype_db = hashMap.get(TAG_NAME_KYC_TYPE).toString();
                                            kyctype_db = kyctype_db.replaceAll(" ", "");

                                            String valueFromDB = hashMap.get(TAG_NAME_KYC_ID).toString();

                                            String moduletype_db = hashMap.get(TAG_NAME_MODULE_TYPE).toString();

                                            if (moduletype_db != null && !moduletype_db.equalsIgnoreCase(module_type)) {

                                                if (kyctype_db.equalsIgnoreCase(kyc_type) && valueFromDB.equalsIgnoreCase(value)) {

                                                    dynamicUITable.setValid(false);
                                                    dynamicUITable.setErrorMessage("Applicant and CoApplicant KYC Ids should not be same");
                                                    break;
                                                } else if (screen2.equalsIgnoreCase(SCREEN_NAME_CO_APPLICANT_KYC)) {
                                                    if (valueFromDB.equalsIgnoreCase(value)) {
                                                        dynamicUITable.setValid(false);
                                                        dynamicUITable.setErrorMessage("KYC already exists");
                                                        break;
                                                    } else {
                                                        dynamicUITable.setValid(true);
                                                    }
                                                } else {
                                                    dynamicUITable.setValid(true);
                                                }
                                            }
                                        }
                                    }
                                    // TODO: mobile number unique for applicant &coapplicant personal details
                                    if (screen2.equalsIgnoreCase(SCREEN_NAME_COAPPLICANT_PERSONAL_DETAIL)) {
                                        if (hashMap.containsKey(TAG_NAME_CONTACT_NO_1) && hashMap.containsKey(TAG_NAME_MODULE_TYPE)) {
                                            String mob_db = hashMap.get(TAG_NAME_CONTACT_NO_1).toString();
                                            mob_db = mob_db.replaceAll(" ", "");

                                            String moduletype_db = hashMap.get(TAG_NAME_MODULE_TYPE).toString();

                                            if (moduletype_db != null && !moduletype_db.equalsIgnoreCase(module_type)) {

                                                if (mob_db.equalsIgnoreCase(value)) {

                                                    dynamicUITable.setValid(false);
                                                    dynamicUITable.setErrorMessage("Applicant and CoApplicant Mobile Number's should not be same");
                                                    break;
                                                } else if (screen2.equalsIgnoreCase(SCREEN_NAME_COAPPLICANT_PERSONAL_DETAIL)) {
                                                    if (mob_db.equalsIgnoreCase(value)) {
                                                        dynamicUITable.setValid(false);
                                                        dynamicUITable.setErrorMessage("Applicant and CoApplicant Mobile Number's should not be same");
                                                        break;
                                                    } else {
                                                        dynamicUITable.setValid(true);
                                                    }
                                                } else {
                                                    dynamicUITable.setValid(true);
                                                }
                                            }
                                        }
                                    }
                                }
                                updateDynamicUITable(dynamicUITableList, dynamicUITable.getScreenID());
                            }
                        } else {
                            dynamicUITable.setValid(true);
                        }
                    }
                };
                viewModel.getRawTableLiveData().observe(this, getLeadRawDataObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    public void EKYCRequest(DeviceInfo deviceInfo, CaptureResponse captureResponse, String aadhaar, List<DynamicUITable> dynamicUITableList) {
        try {

            appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();
            // TODO: EKYC ROOT DTO
            EKYCRootDTO ekycRootDTO = new EKYCRootDTO();
            String timeStamp = appHelper.getCurrentDateTime(DATE_FORMAT_DDMMYYYYSMS);
            ekycRootDTO.setUniqueId(timeStamp);
            ekycRootDTO.setClientID(CLIENT_ID);
            ekycRootDTO.setExternalCustomerId("");
            ekycRootDTO.setKYCId(aadhaar);
            ekycRootDTO.setCreatedDate(appHelper.getCurrentDateTime(DATE_FORMAT_YYYY_MM_DD_T_HH_MM_SS));
            ekycRootDTO.setCreatedBy(USER_ID);
            ekycRootDTO.setServiceType(SERVICE_TYPE_EKYC);

            // TODO: EKYC Request DTO
            EkycRequest ekycRequest = new EkycRequest();
            ekycRequest.setTerminalId(TERMINAL_ID);
            ekycRequest.setFreshnessFactor(DUPLICATE_FRESHNESS_FACTOR);
            ekycRequest.setTransType(TRANS_TYPE);
            ekycRequest.setCsrId(CRS_ID);
            ekycRequest.setCsrPassword("");
            ekycRequest.setRequestId(timeStamp); // TODO: time stamp
            ekycRequest.setResentCount(RESEND_COUNT);
            ekycRequest.setDeviceId(DEVICE_ID);
            ekycRequest.setChannel(CHANNEL);
            ekycRequest.setVersion(SERVICE_VERSION);
            ekycRequest.setAadhaarNo(aadhaar);
            ekycRequest.setEncryptedPid(captureResponse.getPiddata());
            ekycRequest.setEncryptedHmac(captureResponse.getHmac());
            ekycRequest.setSessionKeyValue(captureResponse.getSessionKey());
            ekycRequest.setCertificateIdentifier(captureResponse.getCi());
            ekycRequest.setUserConcent(USER_CONSENT);
            ekycRequest.setEkycType(KYC_TYPE);
            ekycRequest.setAuthVersionToUse(AUTH_VERSION);
            ekycRequest.setEkycPurposeStr("");
            ekycRequest.setOnlyEkycConfirmationRequired(ONLY_EKYC_CONFIRMATION_REQUIRED);
            ekycRequest.setRegisteredDeviceServiceId(deviceInfo.getRdsId());
            ekycRequest.setRegisteredDeviceServiceVersion(deviceInfo.getRdsVer());
            ekycRequest.setRegisteredDeviceProviderId(deviceInfo.getDpId());
            ekycRequest.setRegisteredDeviceCode(deviceInfo.getDc());
            ekycRequest.setRegisteredDeviceModelId(deviceInfo.getMi());
            ekycRequest.setRegisteredDevicePublicKey(deviceInfo.getMc());

            // TODO: Request string DTO
            RequestString requestString = new RequestString();
            requestString.setEkycRequest(ekycRequest);

            ekycRootDTO.setRequestString(requestString);

            viewModel.EKYCRequest(ekycRootDTO, dynamicUITableList.get(0));
            if (viewModel.getEKYCResponseLiveData() != null) {
                Observer EKYCRequestObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {

                        appHelper.getDialogHelper().getLoadingDialog().closeDialog();

                        EKYCResponseDTO ekycResponseDTO = (EKYCResponseDTO) o;
                        viewModel.getEKYCResponseLiveData().removeObserver(this);

                        if (ekycResponseDTO != null && ekycResponseDTO.getApiResponse() != null &&
                                !TextUtils.isEmpty(ekycResponseDTO.getApiResponse().getObj_responseCode())
                                && ekycResponseDTO.getApiResponse().getObj_responseCode().equalsIgnoreCase("00")) {
                            appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.SUCCESS,
                                    "EKYC SUCCESS", new ConfirmationDialog.ActionCallback() {
                                        @Override
                                        public void onAction() {

                                            if (dynamicUITableList != null && dynamicUITableList.size() > 0 &&
                                                    dynamicUITableList.get(0).getLoanType().equalsIgnoreCase(LOAN_NAME_JLG)) {

                                                String gender = ekycResponseDTO.getApiResponse().getGender();
                                                if (!TextUtils.isEmpty(gender)) {
                                                    if (gender.equalsIgnoreCase("f")) {
                                                        setEkycDataFromServer(aadhaar, dynamicUITableList, ekycResponseDTO.getApiResponse());
                                                    } else {
                                                        appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT,
                                                                ERROR_MESSAGE_ONLY_FEMALE_CUSTOMER_ALLOWED);
                                                    }
                                                }
                                            } else {
                                                setEkycDataFromServer(aadhaar, dynamicUITableList, ekycResponseDTO.getApiResponse());
                                            }
                                        }
                                    });

                        } else {
                            // TODO: 20-11-2019 get attempt value functionallity
                            String errorMessage = "EKYC FAILED";
                            if (ekycResponseDTO != null && ekycResponseDTO.getApiResponse() != null
                                    && !TextUtils.isEmpty(ekycResponseDTO.getApiResponse().getAuthenticationMessage())) {
                                errorMessage = ekycResponseDTO.getApiResponse().getAuthenticationMessage();
                            } else if (ekycResponseDTO != null && !TextUtils.isEmpty(ekycResponseDTO.getErrorMessage())) {
                                errorMessage = ekycResponseDTO.getErrorMessage();
                            }

                            appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ERROR, errorMessage,
                                    new ConfirmationDialog.ActionCallback() {
                                        @Override
                                        public void onAction() {
                                            if (ekycResponseDTO.getAttempt() == EKYC_FAILED_MAX_VALUE) {
                                                DynamicUITable dynamicUITable = getObjectByTAG(TAG_NAME_EKYC_BUTTON, dynamicUITableList);
                                                dynamicUITable.setVisibility(false);
                                                EnableOrDisableByLooping(dynamicUITableList, TAG_NAME_QR_READING_BUTTON, true);
                                                setQRData(dynamicUITableList, null);
                                            }
                                        }
                                    });

                        }
                    }
                };
                viewModel.getEKYCResponseLiveData().observe(this, EKYCRequestObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            appHelper.getDialogHelper().getLoadingDialog().closeDialog();
        }
    }

    public void checkScanner() {
        try {
            Intent sendIntent = new Intent();
            sendIntent.setAction("in.gov.uidai.rdservice.fp.INFO");
            getActivity().startActivityForResult(sendIntent, INFO_REQUEST);
        } catch (Exception e) {
            Log.e("TAG", "Device Not Installed");
            e.printStackTrace();
            appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT,
                    "RD Service Not Installed");
        }
    }

    public interface ButtonClick {
        void onButtonClickSuccess();
    }


    public void calculateLoanProposal(DynamicUITable dynamicUITable, List<DynamicUITable> dynamicUITableList) {
        try {
            viewModel.calculateLoanProposal(dynamicUITable, dynamicUITableList);
            if (viewModel.getDynamicUITableLiveData() != null) {
                Observer calculateLoanProposalObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        List<DynamicUITable> dynamicUITableListd = (List<DynamicUITable>) o;
                        viewModel.getDynamicUITableLiveData().removeObserver(this);
                        dynamicUI(dynamicUITableListd);

                        getMinAndMaximumLoanAmount(dynamicUITable, dynamicUITableListd);
                    }
                };
                viewModel.getDynamicUITableLiveData().observe(this, calculateLoanProposalObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void calculateLoanProposalFinalPD(DynamicUITable dynamicUITable, List<DynamicUITable> dynamicUITableList, boolean loanAmountChanges) {
        try {
            viewModel.calculateLoanProposalFinalPD(dynamicUITable, dynamicUITableList, loanAmountChanges);
            if (viewModel.getDynamicUITableLiveData() != null) {
                Observer calculateLoanProposalFinalPDObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        List<DynamicUITable> dynamicUITableListd = (List<DynamicUITable>) o;
                        viewModel.getDynamicUITableLiveData().removeObserver(this);
                        dynamicUI(dynamicUITableListd);
                    }
                };
                viewModel.getDynamicUITableLiveData().observe(this, calculateLoanProposalFinalPDObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void getCBAmountFromServer(DynamicUITable dynamicUITable, List<DynamicUITable> dynamicUITableList) {
        try {
            viewModel.getCBAmountFromServer(dynamicUITable, dynamicUITableList);
            if (viewModel.getDynamicUITableLiveData() != null) {
                Observer getCBAmountFromServerObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        List<DynamicUITable> dynamicUITableListFromServer = (List<DynamicUITable>) o;
                        viewModel.getDynamicUITableLiveData().removeObserver(this);
                        // TODO: After updating CB Amount , calculate loan proposal
                        if (dynamicUITableListFromServer != null && dynamicUITableListFromServer.size() > 0) {
                            calculateLoanProposal(dynamicUITableListFromServer.get(0), dynamicUITableListFromServer);
                        } else {
                            dynamicUI(dynamicUITableListFromServer);
                        }
                    }
                };
                viewModel.getDynamicUITableLiveData().observe(this, getCBAmountFromServerObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void getMetaDataWithCorrelationID(String CORRELATAION_ID) {
        try {
            viewModel.getMetaDataWithCorrelationID(SCREEN_ID, SCREEN_NAME, LOAN_TYPE, PROJECT_ID, PRODUCT_ID, CLIENT_ID, USER_ID, MODULE_TYPE, CORRELATAION_ID);
            Observer getMetaDataObserver = new Observer() {
                @Override
                public void onChanged(@Nullable Object o) {
                    List<DynamicUITable> list = (List<DynamicUITable>) o;
                    viewModel.getDynamicUITableLiveData().removeObserver(this);
                    if (list.size() > 0) {
                        dynamicUI(list);
                    }
                }
            };
            viewModel.getDynamicUITableLiveData().observe(getViewLifecycleOwner(), getMetaDataObserver);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    private void setBusinessExpenseScreenChangesByDropDown(DynamicUITable viewParameters, String selectedItem,
                                                           List<DynamicUITable> viewParametersList) {
        if (viewParameters.getFieldTag().equalsIgnoreCase(TAG_NAME_EXPENSE_NAME_BUSINESS_EXPENSE)) {
            viewParameters.setValue(selectedItem);
            if (selectedItem.equalsIgnoreCase(SPINNER_ITEM_FIELD_NAME_OTHERS_BUSINESS_EXPENSE)) {
                List<ParameterInfo> parameterInfoList = new ArrayList<>();
                parameterInfoList.add(new ParameterInfo(TAG_NAME_OTHERS_BUSINESS_EXPENSE, SCREEN_ID, "", true, true));
                EnableOrDisableByFieldNameInDB(parameterInfoList, viewParametersList);
            } else {
                List<ParameterInfo> parameterInfoList = new ArrayList<>();
                parameterInfoList.add(new ParameterInfo(TAG_NAME_OTHERS_BUSINESS_EXPENSE, SCREEN_ID, "", false, true));
                EnableOrDisableByFieldNameInDB(parameterInfoList, viewParametersList);
            }
        }

    }

    private void setBusinessAssetsChildScreenChangesByDropDown(DynamicUITable viewParameters, String selectedItem,
                                                               List<DynamicUITable> viewParametersList) {
        if (viewParameters.getFieldTag().equalsIgnoreCase(TAG_NAME_TYPE_IN_BUSINESS_ASSETS_MSME)) {
            viewParameters.setValue(selectedItem);
            if (selectedItem.equalsIgnoreCase(SPINNER_ITEM_FIELD_NAME_OTHERS_IN_BUSINESS_ASSETS_MSME)) {
                List<ParameterInfo> parameterInfoList = new ArrayList<>();
                parameterInfoList.add(new ParameterInfo(TAG_NAME_OTHERS_IN_BUSINESS_ASSETS_MSME, SCREEN_ID, "", true, true));
                EnableOrDisableByFieldNameInDB(parameterInfoList, viewParametersList);
            } else {
                List<ParameterInfo> parameterInfoList = new ArrayList<>();
                parameterInfoList.add(new ParameterInfo(TAG_NAME_OTHERS_IN_BUSINESS_ASSETS_MSME, SCREEN_ID, "", false, true));
                EnableOrDisableByFieldNameInDB(parameterInfoList, viewParametersList);
            }
        }

    }


    public void updateVillageAndCenter(DynamicUITable dynamicUITable, List<DynamicUITable> dynamicUITableList, CenterCreationTable centerCreationTable) {
        try {

            viewModel.updateVillageAndCenter(dynamicUITable, dynamicUITableList, centerCreationTable);
            Observer observer = new Observer() {
                @Override
                public void onChanged(@Nullable Object o) {
                    List<DynamicUITable> dynamicUITableListResult = (List<DynamicUITable>) o;
                    if (dynamicUITableListResult != null && dynamicUITableListResult.size() > 0) {
                        applicantKYCScreenValidation(dynamicUITable, dynamicUITableListResult);
                    }
                }
            };
            viewModel.getDynamicUITableLiveData().observe(getViewLifecycleOwner(), observer);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    public void applicantKYCScreenValidation(DynamicUITable dynamicUITable, List<DynamicUITable> dynamicUITableList) {
        try {

            viewModel.applicantKYCScreenValidation(dynamicUITable, dynamicUITableList);
            Observer observer = new Observer() {
                @Override
                public void onChanged(@Nullable Object o) {
                    List<DynamicUITable> dynamicUITableListResult = (List<DynamicUITable>) o;
                    viewModel.getDynamicUITableLiveData().removeObserver(this);


                    if (dynamicUITableListResult != null && dynamicUITableListResult.size() > 0) {
                        if (LOSBaseFragment.this instanceof ChildFragment) {
                            ChildFragment childFragment = (ChildFragment) LOSBaseFragment.this;
                            childFragment.callCloseDialogFragment();
                            FragmentToActivityInterface fragmentToActivityInterface = (FragmentToActivityInterface) getActivity();
                            fragmentToActivityInterface.valueFromFragment(dynamicUITableListResult.get(0).getScreenID(), SCREEN_NAME,
                                    dynamicUITableListResult.get(0).getScreenName(), dynamicUITableListResult);
                        } else {
                            // TODO: Update UI
                            dynamicUI(dynamicUITableListResult);
                        }
                    }
                }
            };
            viewModel.getDynamicUITableLiveData().observe(getViewLifecycleOwner(), observer);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void referenceCheckScreenValidation(DynamicUITable dynamicUITable, List<DynamicUITable> dynamicUITableList) {
        try {
            viewModel.referenceCheckScreenValidation(dynamicUITable, dynamicUITableList);
            Observer observer = new Observer() {
                @Override
                public void onChanged(@Nullable Object o) {
                    List<DynamicUITable> dynamicUITableListResult = (List<DynamicUITable>) o;
                    viewModel.getDynamicUITableLiveData().removeObserver(this);

                    if (dynamicUITableListResult != null && dynamicUITableListResult.size() > 0) {
                        if (LOSBaseFragment.this instanceof ChildFragment) {
                            ChildFragment childFragment = (ChildFragment) LOSBaseFragment.this;
                            childFragment.callCloseDialogFragment();
                            FragmentToActivityInterface fragmentToActivityInterface = (FragmentToActivityInterface) getActivity();
                            fragmentToActivityInterface.valueFromFragment(dynamicUITableListResult.get(0).getScreenID(), SCREEN_NAME,
                                    dynamicUITableListResult.get(0).getScreenName(), dynamicUITableListResult);
                        } else {
                            // TODO: Update UI
                            dynamicUI(dynamicUITableListResult);
                        }
                    }
                }
            };
            viewModel.getDynamicUITableLiveData().observe(getViewLifecycleOwner(), observer);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void referenceVerificationScreenValidation(DynamicUITable dynamicUITable, List<DynamicUITable> dynamicUITableList) {
        try {
            viewModel.referenceVerificationScreenValidation(dynamicUITable, dynamicUITableList);
            Observer observer = new Observer() {
                @Override
                public void onChanged(@Nullable Object o) {
                    List<DynamicUITable> dynamicUITableListResult = (List<DynamicUITable>) o;
                    viewModel.getDynamicUITableLiveData().removeObserver(this);

                    if (dynamicUITableListResult != null && dynamicUITableListResult.size() > 0) {
                        if (LOSBaseFragment.this instanceof ChildFragment) {
                            ChildFragment childFragment = (ChildFragment) LOSBaseFragment.this;
                            childFragment.callCloseDialogFragment();
                            FragmentToActivityInterface fragmentToActivityInterface = (FragmentToActivityInterface) getActivity();
                            fragmentToActivityInterface.valueFromFragment(dynamicUITableListResult.get(0).getScreenID(), SCREEN_NAME,
                                    dynamicUITableListResult.get(0).getScreenName(), dynamicUITableListResult);
                        } else {
                            // TODO: Update UI
                            dynamicUI(dynamicUITableListResult);
                        }
                    }
                }
            };
            viewModel.getDynamicUITableLiveData().observe(getViewLifecycleOwner(), observer);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void generalIncomeScreenValidation(DynamicUITable dynamicUITable, List<DynamicUITable> dynamicUITableList) {
        try {
            viewModel.generalIncomeScreenValidation(dynamicUITable, dynamicUITableList);
            Observer observer = new Observer() {
                @Override
                public void onChanged(@Nullable Object o) {
                    List<DynamicUITable> dynamicUITableListResult = (List<DynamicUITable>) o;
                    viewModel.getDynamicUITableLiveData().removeObserver(this);

                    if (dynamicUITableListResult != null && dynamicUITableListResult.size() > 0) {
                        if (LOSBaseFragment.this instanceof ChildFragment) {
                            ChildFragment childFragment = (ChildFragment) LOSBaseFragment.this;
                            childFragment.callCloseDialogFragment();
                            FragmentToActivityInterface fragmentToActivityInterface = (FragmentToActivityInterface) getActivity();
                            fragmentToActivityInterface.valueFromFragment(dynamicUITableListResult.get(0).getScreenID(), SCREEN_NAME,
                                    dynamicUITableListResult.get(0).getScreenName(), dynamicUITableListResult);
                        } else {
                            // TODO: Update UI
                            dynamicUI(dynamicUITableListResult);
                        }
                    }
                }
            };
            viewModel.getDynamicUITableLiveData().observe(getViewLifecycleOwner(), observer);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void otherIncomeSourceScreenValidation(DynamicUITable dynamicUITable, List<DynamicUITable> dynamicUITableList) {
        try {
            viewModel.otherIncomeSourceScreenValidation(dynamicUITable, dynamicUITableList);
            Observer observer = new Observer() {
                @Override
                public void onChanged(@Nullable Object o) {
                    List<DynamicUITable> dynamicUITableListResult = (List<DynamicUITable>) o;
                    viewModel.getDynamicUITableLiveData().removeObserver(this);

                    if (dynamicUITableListResult != null && dynamicUITableListResult.size() > 0) {
                        if (LOSBaseFragment.this instanceof ChildFragment) {
                            ChildFragment childFragment = (ChildFragment) LOSBaseFragment.this;
                            childFragment.callCloseDialogFragment();
                            FragmentToActivityInterface fragmentToActivityInterface = (FragmentToActivityInterface) getActivity();
                            fragmentToActivityInterface.valueFromFragment(dynamicUITableListResult.get(0).getScreenID(), SCREEN_NAME,
                                    dynamicUITableListResult.get(0).getScreenName(), dynamicUITableListResult);
                        } else {
                            // TODO: Update UI
                            dynamicUI(dynamicUITableListResult);
                        }
                    }
                }
            };
            viewModel.getDynamicUITableLiveData().observe(getViewLifecycleOwner(), observer);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void performCB(DynamicUITable dynamicUITable, List<DynamicUITable> dynamicUITableList) {

        // TODO: Perform CB MFI SERVICE CALL
        try {
            appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();

            viewModel.performCB_MFI_ServiceCall( dynamicUITable, dynamicUITableList,USER_ID, LOAN_TYPE);
            if (viewModel.getStringLiveData() != null) {
                Observer observer = new Observer() {
                    @Override
                    public void onChanged(Object o) {

                        appHelper.getDialogHelper().getLoadingDialog().closeDialog();

                        String response = (String) o;
                        viewModel.getStringLiveData().removeObserver(this::onChanged);


                        if( ! TextUtils.isEmpty(response)){
                            if(response.equalsIgnoreCase(MESSAGE_CIBIL_FAILED)){
                                appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ERROR, response,
                                        new ConfirmationDialog.ActionCallback() {
                                            @Override
                                            public void onAction() {
                                                // TODO: Get DynamiUI Table List From Local DB
                                                getDynamicUITableLocalDB(dynamicUITable.getScreenID());
                                            }
                                        });
                            }else if(response.contains(MESSAGE_CIBIL_SUCCESS)){
                                appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.SUCCESS, response,
                                        new ConfirmationDialog.ActionCallback() {
                                            @Override
                                            public void onAction() {

                                                // TODO: Move to Next screen
//                                                    moveToNextScreen();

                                                checkCBStatus(dynamicUITableList);
                                                updateDynamicUITable(dynamicUITableList, SCREEN_ID);

                                                // TODO: Get DynamicUI Table List From Local DB
                                                getDynamicUITableLocalDB(dynamicUITable.getScreenID());
                                            }
                                        });
                            }else{
                                appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ERROR, response,
                                        new ConfirmationDialog.ActionCallback() {
                                            @Override
                                            public void onAction() {
                                                // TODO: Get DynamiUI Table List From Local DB
                                                getDynamicUITableLocalDB(dynamicUITable.getScreenID());
                                            }
                                        });
                            }

                        }else{
                            appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ERROR, MESSAGE_CIBIL_FAILED,
                                    new ConfirmationDialog.ActionCallback() {
                                        @Override
                                        public void onAction() {

                                            // TODO: Get DynamiUI Table List From Local DB
                                            getDynamicUITableLocalDB(dynamicUITable.getScreenID());
                                        }
                                    });
                        }
                    }
                };

                viewModel.getStringLiveData().observe(this, observer);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    private void getDynamicUITableLocalDB(String screenId) {
        try {
            viewModel.getDynamicUITableLocalDB(screenId);
            Observer observer = new Observer() {
                @Override
                public void onChanged(@Nullable Object o) {
                    List<DynamicUITable> dynamicUITableListResult = (List<DynamicUITable>) o;
                    viewModel.getDynamicUITableLiveData().removeObserver(this);

                    if (dynamicUITableListResult != null && dynamicUITableListResult.size() > 0) {
                        // TODO: Update UI
                        dynamicUI(dynamicUITableListResult);
                    }
                }
            };
            viewModel.getDynamicUITableLiveData().observe(getViewLifecycleOwner(), observer);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }




    public void getRawDataFromTable(DynamicUITable dynamicUITable, List<DynamicUITable> dynamicUITableList) {
        try {
            viewModel.bankingHistoryCalculation(dynamicUITable, dynamicUITableList);
            if (viewModel.getDynamicUITableLiveData() != null) {
                Observer bankingHistoryCalculationObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        List<DynamicUITable> dynamicUITableListParentScreen = (List<DynamicUITable>) o;
                        viewModel.getDynamicUITableLiveData().removeObserver(this);
                        String submittedValues = getSubmittedValuesFromUI(dynamicUITableListParentScreen);
                        final RawDataTable rawDataTable = new RawDataTable(submittedValues, dynamicUITable.getScreenID()
                                , dynamicUITable.getScreenName(), "", dynamicUITable.getClientID(),
                                dynamicUITable.getLoanType(), dynamicUITable.getUser_id(),
                                dynamicUITable.getModuleType(), dynamicUITable.getCoRelationID());

                        // TODO: To save parent screen raw data
                       /* saveParentScreenRawData(rawDataTable, dynamicUITableListParentScreen,
                                dynamicUITable, dynamicUITable.getFieldTag());*/
                        rawDataFronRawDataTable=rawDataTable;

                    }
                };
                viewModel.getDynamicUITableLiveData().observe(this, bankingHistoryCalculationObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


}
