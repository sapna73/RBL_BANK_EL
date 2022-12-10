package com.swadhaar.los.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bfil.uilibrary.dialogs.ConfirmationDialog;
import com.bfil.uilibrary.widgets.customSpinner.SearchableSpinner;
import com.swadhaar.los.BuildConfig;
import com.swadhaar.los.R;
import com.swadhaar.los.constants.AppConstant;
import com.swadhaar.los.database.entity.CMFetchTable;
import com.swadhaar.los.database.entity.DocumentMasterTable;
import com.swadhaar.los.database.entity.GroupTable;
import com.swadhaar.los.database.entity.SODTable;
import com.swadhaar.los.database.entity.WorkFlowTable;
import com.swadhaar.los.view_models.DynamicUIViewModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

import static com.swadhaar.los.constants.AppConstant.DATE_FORMAT_DD_MM_YYYY2;
import static com.swadhaar.los.constants.AppConstant.DATE_FORMAT_YYYY_MM_DD;
import static com.swadhaar.los.constants.AppConstant.LOAN_NAME_JLG;
import static com.swadhaar.los.constants.AppConstant.LOAN_NAME_MSME;
import static com.swadhaar.los.constants.AppConstant.MODULE_TYPE_ADD_OR_REMOVE_MEMBER;
import static com.swadhaar.los.constants.AppConstant.MODULE_TYPE_CENTER_CREATION;
import static com.swadhaar.los.constants.AppConstant.MODULE_TYPE_CGT;
import static com.swadhaar.los.constants.AppConstant.MODULE_TYPE_COLD_CALLING;
import static com.swadhaar.los.constants.AppConstant.MODULE_TYPE_COLLECTION;
import static com.swadhaar.los.constants.AppConstant.MODULE_TYPE_DROP_OUT_CUSTOMER;
import static com.swadhaar.los.constants.AppConstant.MODULE_TYPE_ELIGIBILITY;
import static com.swadhaar.los.constants.AppConstant.MODULE_TYPE_GRT;
import static com.swadhaar.los.constants.AppConstant.MODULE_TYPE_LEAD;
import static com.swadhaar.los.constants.AppConstant.MODULE_TYPE_LOAN_APPLICATION;
import static com.swadhaar.los.constants.AppConstant.MODULE_TYPE_TARGET_DETAILS;
import static com.swadhaar.los.constants.AppConstant.MODULE_TYPE_VILLAGE_SURVEY;
import static com.swadhaar.los.constants.AppConstant.PARAM_BRANCH_GST_CODE;
import static com.swadhaar.los.constants.AppConstant.PARAM_BRANCH_ID;
import static com.swadhaar.los.constants.AppConstant.PARAM_BRANCH_NAME;
import static com.swadhaar.los.constants.AppConstant.PARAM_CENTER_NAME;
import static com.swadhaar.los.constants.AppConstant.PARAM_CLIENT_ID;
import static com.swadhaar.los.constants.AppConstant.PARAM_LOAN_TYPE;
import static com.swadhaar.los.constants.AppConstant.PARAM_MODULE_TYPE;
import static com.swadhaar.los.constants.AppConstant.PARAM_PRODUCT_ID;
import static com.swadhaar.los.constants.AppConstant.PARAM_ROLE_NAME;
import static com.swadhaar.los.constants.AppConstant.PARAM_USER_ID;
import static com.swadhaar.los.constants.AppConstant.PARAM_USER_NAME;
import static com.swadhaar.los.constants.AppConstant.PROJECT_ID_LOS;
import static com.swadhaar.los.constants.AppConstant.ROLE_ID_BM;
import static com.swadhaar.los.constants.AppConstant.getCMDate;
import static com.swadhaar.los.dynamicui.constants.ParametersConstant.ALERT_FOR_INTERNET_CONNECTION_REQUIRED_TO_FETCH_DATA;
import static com.swadhaar.los.dynamicui.constants.ParametersConstant.ALERT_FOR_SYNC;
import static com.swadhaar.los.dynamicui.constants.ParametersConstant.EOD_PERFORMED_FOR_DAY;
import static com.swadhaar.los.dynamicui.constants.ParametersConstant.ERROR_MESSAGE_EOD_NOT_DONE_YESTERDAY;
import static com.swadhaar.los.dynamicui.constants.ParametersConstant.ERROR_MESSAGE_NO_CENTERS_AVAILABLE;
import static com.swadhaar.los.dynamicui.constants.ParametersConstant.ERROR_MESSAGE_SYNC_FAILED;
import static com.swadhaar.los.dynamicui.constants.ParametersConstant.ERROR_MESSAGE_UNABLE_TO_GET_DOCUMENT_MASTER;
import static com.swadhaar.los.dynamicui.constants.ParametersConstant.ERROR_MESSAGE_UNABLE_TO_GET_TOKEN;
import static com.swadhaar.los.dynamicui.constants.ParametersConstant.ERROR_MESSAGE_UNABLE_TO_GET_WORKFLOW;
import static com.swadhaar.los.dynamicui.constants.ParametersConstant.FAILURE_RESPONSE_FOR_BRANCH_PRODUCT_FEATURE_MASTER_FETCH_FROM_SERVER;
import static com.swadhaar.los.dynamicui.constants.ParametersConstant.FAILURE_RESPONSE_FOR_CENTER_MEETING_FETCH_FROM_SERVER;
import static com.swadhaar.los.dynamicui.constants.ParametersConstant.FAILURE_RESPONSE_FOR_ELIGIBILITY_FETCH_FROM_SERVER;
import static com.swadhaar.los.dynamicui.constants.ParametersConstant.ROLE_NAME_BM;
import static com.swadhaar.los.dynamicui.constants.ParametersConstant.SOD_PERFORMED_FOR_DAY;
import static com.swadhaar.los.dynamicui.constants.ParametersConstant.SUCCESS_RESPONSE_FOR_BRANCH_PRODUCT_FEATURE_MASTER_FETCH_FROM_SERVER;
import static com.swadhaar.los.dynamicui.constants.ParametersConstant.SUCCESS_RESPONSE_FOR_CENTER_MEETING_FETCH_FROM_SERVER;
import static com.swadhaar.los.dynamicui.constants.ParametersConstant.SUCCESS_RESPONSE_FOR_ELIGIBILITY_FETCH_FROM_SERVER;
import static com.swadhaar.los.dynamicui.constants.ParametersConstant.SUCCESS_RESPONSE_MESSAGE;
import static com.swadhaar.los.dynamicui.constants.ParametersConstant.SWITCH_ITEM_EOD;
import static com.swadhaar.los.dynamicui.constants.ParametersConstant.SWITCH_ITEM_SOD;

public class JLGHomeActivity extends LOSBaseActivity implements View.OnClickListener {
    private static final String TAG = JLGHomeActivity.class.getCanonicalName();
    LinearLayout llVillageSurvey, llCenterCreation,llCollection,llLuc,llQcRecapture,llFetchToDay,llGRT;
    TextView tvLoanType;
    CardView cv_village_survey, cv_center_creation,cv_collection,cv_luc,cv_qc_recapture,cv_grt;
    TextView tvAppVersion,tvCurrentDate,tvUserName;

    private AlertDialog notificationDialog;

    @Inject
    public ViewModelProvider.Factory viewModelFactory;
    public DynamicUIViewModel viewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_jlghome);

        tvUserName = (TextView)findViewById(R.id.tv_user_name);
        tvCurrentDate = (TextView)findViewById(R.id.tv_currentdate);
        tvAppVersion = (TextView)findViewById(R.id.tv_app_version);

        llVillageSurvey = (LinearLayout) findViewById(R.id.ll_village_survey);
        llCenterCreation = (LinearLayout) findViewById(R.id.ll_center_creation);
        llCollection = (LinearLayout) findViewById(R.id.ll_collection);
        llLuc = (LinearLayout) findViewById(R.id.ll_luc);
        llQcRecapture = (LinearLayout) findViewById(R.id.ll_qc_recapture);
        llFetchToDay = (LinearLayout) findViewById(R.id.ll_fetch_today);
        llGRT = (LinearLayout) findViewById(R.id.ll_grt);

        cv_village_survey = (CardView) findViewById(R.id.cv_village_survey);
        cv_center_creation = (CardView) findViewById(R.id.cv_center_creation);
        cv_collection = (CardView) findViewById(R.id.cv_collection);
        cv_luc = (CardView) findViewById(R.id.cv_luc);
        cv_qc_recapture = (CardView) findViewById(R.id.cv_qc_recapture);
        cv_grt = (CardView) findViewById(R.id.cv_grt);


        llVillageSurvey.setOnClickListener(this);
        llCenterCreation.setOnClickListener(this);
        llCollection.setOnClickListener(this);
        llQcRecapture.setOnClickListener(this);
        llFetchToDay.setOnClickListener(this);
        llGRT.setOnClickListener(this);

        tvLoanType = (TextView) findViewById(R.id.tv_product_name);

        userName = getIntent().getStringExtra(PARAM_USER_NAME);
        userId = getIntent().getStringExtra(PARAM_USER_ID);
        branchId = getIntent().getStringExtra(PARAM_BRANCH_ID);
        branchName = getIntent().getStringExtra(PARAM_BRANCH_NAME);
        branchGSTcode = getIntent().getStringExtra(PARAM_BRANCH_GST_CODE);
        productId = getIntent().getStringExtra(PARAM_PRODUCT_ID);
        roleName = getIntent().getStringExtra(PARAM_ROLE_NAME);
        loanType = getIntent().getStringExtra(PARAM_LOAN_TYPE);


        if (!TextUtils.isEmpty(loanType)) {
            tvLoanType.setText(loanType);
        }

        if(!TextUtils.isEmpty(userName)){
            tvUserName.setText(userName);
        }

        if(!TextUtils.isEmpty(roleName) && roleName.equalsIgnoreCase(ROLE_NAME_BM)){
            cv_grt.setVisibility(View.VISIBLE);
            cv_village_survey.setVisibility(View.GONE);
            cv_center_creation.setVisibility(View.GONE);
            cv_collection.setVisibility(View.GONE);
            cv_luc.setVisibility(View.GONE);
            cv_qc_recapture.setVisibility(View.GONE);
        }
        else{
            // TODO: Display remaining all icons
            cv_grt.setVisibility(View.GONE);
            cv_village_survey.setVisibility(View.VISIBLE);
            cv_center_creation.setVisibility(View.VISIBLE);
            cv_collection.setVisibility(View.VISIBLE);
            cv_luc.setVisibility(View.VISIBLE);
            cv_qc_recapture.setVisibility(View.VISIBLE);
        }

        String appVersion = BuildConfig.VERSION_NAME;
        if (!TextUtils.isEmpty(appVersion)) {
            tvAppVersion.setText(getResources().getString(R.string.version)+" "+appVersion);
        }

        String currentDate = appHelper.getCurrentDate(DATE_FORMAT_DD_MM_YYYY2);
        if (!TextUtils.isEmpty(currentDate)) {
            tvCurrentDate.setText(currentDate);
        }

        configureDagger();
        configureViewModel();

    }

    private void configureDagger() {
        AndroidInjection.inject(this);
    }

    public void configureViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(DynamicUIViewModel.class);
        // TODO: Getting Document Master From Server
            getDocumentMasterFromServer();
    }

    @Override
    public void onClick(View v) {
        try {
            switch (v.getId()) {
                case R.id.ll_fetch_today:
                    if (appHelper.isNetworkAvailable()) {
                        appHelper.getDialogHelper().getConfirmationDialog().showTwoButtons(ALERT_FOR_SYNC, new ConfirmationDialog.ActionCallback() {
                            @Override
                            public void onAction() {
                                getCenterMeetingDetailsFromServer();
                            }
                        });

                    } else {
                        appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, ALERT_FOR_INTERNET_CONNECTION_REQUIRED_TO_FETCH_DATA);
                    }
                    break;
                case R.id.ll_loan_application:
                    Intent loanApplication = new Intent(this, CentersSummaryActivity.class);
                    loanApplication.putExtra(PARAM_LOAN_TYPE, loanType);
                    loanApplication.putExtra(PARAM_USER_NAME, userName);
                    loanApplication.putExtra(PARAM_BRANCH_ID, branchId);
                    loanApplication.putExtra(PARAM_BRANCH_GST_CODE, branchGSTcode);
                    loanApplication.putExtra(PARAM_USER_ID, userId);
                    loanApplication.putExtra(PARAM_MODULE_TYPE, MODULE_TYPE_LOAN_APPLICATION);
                    loanApplication.putExtra(PARAM_PRODUCT_ID, productId);
                    startActivity(loanApplication);
                    break;
                case R.id.ll_eligibility:
                    Intent eligibility = new Intent(this, EligibilityActivity.class);
                    eligibility.putExtra(PARAM_LOAN_TYPE, loanType);
                    eligibility.putExtra(PARAM_USER_NAME, userName);
                    eligibility.putExtra(PARAM_BRANCH_ID, branchId);
                    eligibility.putExtra(PARAM_BRANCH_GST_CODE, branchGSTcode);
                    eligibility.putExtra(PARAM_USER_ID, userId);
                    eligibility.putExtra(PARAM_MODULE_TYPE, MODULE_TYPE_ELIGIBILITY);
                    eligibility.putExtra(PARAM_PRODUCT_ID, productId);
                    startActivity(eligibility);
                    break;
                case R.id.ll_village_survey:
                    Intent villageSurvey = new Intent(this, VillageSurveyActivity.class);
                    villageSurvey.putExtra(PARAM_LOAN_TYPE, loanType);
                    villageSurvey.putExtra(PARAM_USER_NAME, userName);
                    villageSurvey.putExtra(PARAM_BRANCH_ID, branchId);
                    villageSurvey.putExtra(PARAM_BRANCH_GST_CODE, branchGSTcode);
                    villageSurvey.putExtra(PARAM_USER_ID, userId);
                    villageSurvey.putExtra(PARAM_MODULE_TYPE, MODULE_TYPE_VILLAGE_SURVEY);
                    villageSurvey.putExtra(PARAM_PRODUCT_ID, productId);
                    startActivity(villageSurvey);
                    break;
                case R.id.ll_center_creation:
                    Intent centerCreation = new Intent(this, CenterCreationHomeActivity.class);
                    centerCreation.putExtra(PARAM_LOAN_TYPE, loanType);
                    centerCreation.putExtra(PARAM_USER_NAME, userName);
                    centerCreation.putExtra(PARAM_BRANCH_ID, branchId);
                    centerCreation.putExtra(PARAM_BRANCH_GST_CODE, branchGSTcode);
                    centerCreation.putExtra(PARAM_USER_ID, userId);
                    centerCreation.putExtra(PARAM_MODULE_TYPE, MODULE_TYPE_CENTER_CREATION);
                    centerCreation.putExtra(PARAM_PRODUCT_ID, productId);
                    startActivity(centerCreation);
                    break;
                case R.id.ll_collection:
                    Intent collection = new Intent(this, CollectionDashBoardActivity.class);
                    collection.putExtra(PARAM_LOAN_TYPE, loanType);
                    collection.putExtra(PARAM_USER_NAME, userName);
                    collection.putExtra(PARAM_BRANCH_ID, branchId);
                    collection.putExtra(PARAM_BRANCH_GST_CODE, branchGSTcode);
                    collection.putExtra(PARAM_USER_ID, userId);
                    collection.putExtra(PARAM_MODULE_TYPE, MODULE_TYPE_COLLECTION);
                    collection.putExtra(PARAM_PRODUCT_ID, productId);
                    startActivity(collection);
                    break;
                case R.id.ll_qc_recapture:
                    Intent qcReCapture = new Intent(this, QCReCaptureActivity.class);
                    qcReCapture.putExtra(PARAM_LOAN_TYPE, loanType);
                    qcReCapture.putExtra(PARAM_USER_NAME, userName);
                    qcReCapture.putExtra(PARAM_BRANCH_ID, branchId);
                    qcReCapture.putExtra(PARAM_BRANCH_GST_CODE, branchGSTcode);
                    qcReCapture.putExtra(PARAM_USER_ID, userId);
                    qcReCapture.putExtra(PARAM_MODULE_TYPE, MODULE_TYPE_CENTER_CREATION);
                    qcReCapture.putExtra(PARAM_PRODUCT_ID, productId);
                    startActivity(qcReCapture);
                    break;
                case R.id.ll_cgt:
                    Intent cgt = new Intent(this, CGTSummaryActivity.class);
                    cgt.putExtra(PARAM_LOAN_TYPE, loanType);
                    cgt.putExtra(PARAM_USER_NAME, userName);
                    cgt.putExtra(PARAM_BRANCH_ID, branchId);
                    cgt.putExtra(PARAM_BRANCH_GST_CODE, branchGSTcode);
                    cgt.putExtra(PARAM_USER_ID, userId);
                    cgt.putExtra(PARAM_MODULE_TYPE, MODULE_TYPE_CGT);
                    cgt.putExtra(PARAM_PRODUCT_ID, productId);
                    startActivity(cgt);
                    break;
                case R.id.ll_drop_out_customer:
                    Intent dropOut = new Intent(this, CentersSummaryActivity.class);
                    dropOut.putExtra(PARAM_LOAN_TYPE, loanType);
                    dropOut.putExtra(PARAM_USER_NAME, userName);
                    dropOut.putExtra(PARAM_BRANCH_ID, branchId);
                    dropOut.putExtra(PARAM_BRANCH_GST_CODE, branchGSTcode);
                    dropOut.putExtra(PARAM_USER_ID, userId);
                    dropOut.putExtra(PARAM_MODULE_TYPE, MODULE_TYPE_DROP_OUT_CUSTOMER);
                    dropOut.putExtra(PARAM_PRODUCT_ID, productId);
                    startActivity(dropOut);
                    break;


                case R.id.ll_grt:
                    Intent grt = new Intent(this, GRTSummaryActivity.class);
                    grt.putExtra(PARAM_LOAN_TYPE, loanType);
                    grt.putExtra(PARAM_USER_NAME, userName);
                    grt.putExtra(PARAM_BRANCH_ID, branchId);
                    grt.putExtra(PARAM_BRANCH_GST_CODE, branchGSTcode);
                    grt.putExtra(PARAM_USER_ID, userId);
                    grt.putExtra(PARAM_MODULE_TYPE, MODULE_TYPE_GRT);
                    grt.putExtra(PARAM_PRODUCT_ID, productId);
                    startActivity(grt);
					break;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void getCenterMeetingDetailsFromServer() {
        try {
            appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();

//            String cmDateFromCalendar = appHelper.getCurrentDate(DATE_FORMAT_YYYYMMDD);
//            String userId="SIF1000059";
//            String cmDateFromCalendar = appHelper.getCurrentDate(DATE_FORMAT_YYYYMMDD);
            String cmDateFromCalendar = getCMDate(appHelper);
            viewModel.getCenterMeetingDetailsFromServer(cmDateFromCalendar, userId);
            if (viewModel.getStringLiveData() != null) {
                Observer observer = new Observer() {
                    @Override
                    public void onChanged(Object o) {
                        appHelper.getDialogHelper().getLoadingDialog().closeDialog();
                        String response = (String) o;
                        Log.d(TAG, "getCenterMeetingDetailsFromServer ==> " + response);
                        viewModel.getStringLiveData().removeObserver(this);
                        if (!TextUtils.isEmpty(response) && response.equalsIgnoreCase(SUCCESS_RESPONSE_MESSAGE)) {
                            appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.SUCCESS,
                                    SUCCESS_RESPONSE_FOR_CENTER_MEETING_FETCH_FROM_SERVER, new ConfirmationDialog.ActionCallback() {
                                        @Override
                                        public void onAction() {
                                            // TODO: Calling Eligibility data from server
                                            getEligibilityDataFromServer(cmDateFromCalendar, userId);
                                        }
                                    });
                        } else {
                            appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ERROR,
                                    FAILURE_RESPONSE_FOR_CENTER_MEETING_FETCH_FROM_SERVER);
                        }
                    }
                };
                viewModel.getStringLiveData().observe(this, observer);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            appHelper.getDialogHelper().getLoadingDialog().closeDialog();
            INSERT_LOG("getCenterMeetingDetailsFromServer", "Exception : " + ex.getMessage());
        }
    }

    public void getEligibilityDataFromServer(String centerMeetingDate, String userId) {
        try {
            appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();

            viewModel.getEligibilityDataFromServer(centerMeetingDate, userId);
            if (viewModel.getStringLiveData() != null) {
                Observer observer = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        appHelper.getDialogHelper().getLoadingDialog().closeDialog();
                        String response = (String) o;
                        Log.d(TAG, "getEligibilityDataFromServer ==>  " + response);
                        viewModel.getStringLiveData().removeObserver(this);
                        if (!TextUtils.isEmpty(response) && response.equalsIgnoreCase(SUCCESS_RESPONSE_MESSAGE)) {
                            appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.SUCCESS,
                                    SUCCESS_RESPONSE_FOR_ELIGIBILITY_FETCH_FROM_SERVER, new ConfirmationDialog.ActionCallback() {
                                        @Override
                                        public void onAction() {
                                            llFetchToDay.setVisibility(View.GONE);
                                            // TODO: Calling Branch Product Feature Master Table
                                            getBranchProductFeatureMasterDataFromServer(userId);
                                        }
                                    });
                        } else {
                            appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ERROR,
                                    FAILURE_RESPONSE_FOR_ELIGIBILITY_FETCH_FROM_SERVER);
                        }
                    }
                };
                viewModel.getStringLiveData().observe(this, observer);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            appHelper.getDialogHelper().getLoadingDialog().closeDialog();
            INSERT_LOG("getEligibilityDataFromServer", "Exception : " + ex.getMessage());

        }
    }

    public void getBranchProductFeatureMasterDataFromServer(String userId) {
        try {
            appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();

            viewModel.getBranchProductFeatureMasterDataFromServer(userId);
            if (viewModel.getStringLiveData() != null) {
                Observer observer = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        appHelper.getDialogHelper().getLoadingDialog().closeDialog();
                        String response = (String) o;
                        Log.d(TAG, "getBranchProductFeatureMasterDataFromServer ==>  " + response);
                        viewModel.getStringLiveData().removeObserver(this);
                        if (!TextUtils.isEmpty(response) && response.equalsIgnoreCase(SUCCESS_RESPONSE_MESSAGE)) {
                            appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.SUCCESS,
                                    SUCCESS_RESPONSE_FOR_BRANCH_PRODUCT_FEATURE_MASTER_FETCH_FROM_SERVER, new ConfirmationDialog.ActionCallback() {
                                        @Override
                                        public void onAction() {
                                            llFetchToDay.setVisibility(View.GONE);
                                        }
                                    });
                        } else {
                            appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ERROR,
                                    FAILURE_RESPONSE_FOR_BRANCH_PRODUCT_FEATURE_MASTER_FETCH_FROM_SERVER);
                        }
                    }
                };
                viewModel.getStringLiveData().observe(this, observer);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            appHelper.getDialogHelper().getLoadingDialog().closeDialog();
            INSERT_LOG("getBranchProductFeatureMasterDataFromServer", "Exception : " + ex.getMessage());

        }
    }


    private void getDocumentMasterFromServer() {
        try {
            viewModel.getDocumentMasterFromServer(productId, PROJECT_ID_LOS);
            if (viewModel.getDocumentMasterListLiveData() != null) {
                Observer getDocumentUploadMasterObserver = new Observer() {
                    @Override
                    public void onChanged(Object o) {
                        List<DocumentMasterTable> documentMasterTableList = (List<DocumentMasterTable>) o;
                        viewModel.getDocumentMasterListLiveData().removeObserver(this);

                        if (documentMasterTableList != null && documentMasterTableList.size() > 0) {
                            // TODO: get cm fetch table
                            String date = appHelper.getCurrentDateTime(AppConstant.DATE_FORMAT_YYYY_MM_DD);
                            getCMFetchDataFromLocalDB(userId,date);
                        } else {
                            appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ERROR,
                                    ERROR_MESSAGE_UNABLE_TO_GET_DOCUMENT_MASTER, new ConfirmationDialog.ActionCallback() {
                                        @Override
                                        public void onAction() {
                                            finish();
                                        }
                                    });
                        }
                    }
                };
                viewModel.getDocumentMasterListLiveData().observe(this, getDocumentUploadMasterObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            INSERT_LOG("getDocumentMasterFromServer", "Exception : " + ex.getMessage());
        }
    }


    public void getCMFetchDataFromLocalDB(String userId,String date) {
        try {
            appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();

            viewModel.getCMFetchDataFromLocalDB(userId,date);
            if (viewModel.getCMFetchTableLiveData() != null) {
                Observer getSODDataObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        CMFetchTable cmFetchTable = (CMFetchTable) o;
                        viewModel.getCMFetchTableLiveData().removeObserver(this);

                        appHelper.getDialogHelper().getLoadingDialog().closeDialog();

                        if (cmFetchTable != null && cmFetchTable.isSync()) {
                            llFetchToDay.setVisibility(View.GONE);
                        } else {
                            llFetchToDay.setVisibility(View.VISIBLE);
                        }
                    }
                };
                viewModel.getCMFetchTableLiveData().observe(this, getSODDataObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            appHelper.getDialogHelper().getLoadingDialog().closeDialog();

            INSERT_LOG("getCMFetchDataFromLocalDB", "Exception : " + ex.getMessage());
        }
    }


    @Override
    public void onBackPressed() {
        finish();
    }

    private void INSERT_LOG(String methodName, String message) {
        try {
            if (viewModel != null) {
                viewModel.insertLog(methodName, message, userId, "", JLGHomeActivity.class.getCanonicalName()
                        , CLIENT_ID, loanType, moduleType);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }



    public void displayCenterMeetingPopUp( final ActionCallback callback,List<String> centerNameList) {

        notificationDialog = new AlertDialog.Builder(this).create();

        View notificationDialogView = LayoutInflater.from(this).inflate(R.layout.
                popup_choose_center_meeting, null);
        notificationDialog.setView(notificationDialogView);
        notificationDialog.setCancelable(false);

        Button btnSave = (Button) notificationDialogView.findViewById(R.id.btn_popup_yes);
        Button btnCancel = (Button) notificationDialogView.findViewById(R.id.btn_popup_no);
        SearchableSpinner spCenterList=(SearchableSpinner)notificationDialogView.findViewById(R.id.sp_center_list);
        TextView tvCenterMeetingDate=(TextView) notificationDialogView.findViewById(R.id.tv_center_meeting_date);

        if(centerNameList !=null && centerNameList.size()>0){
                ArrayAdapter arrayAdapter = new ArrayAdapter(JLGHomeActivity.this, R.layout.view_spinner_textheight, centerNameList);
                spCenterList.setAdapter(arrayAdapter);
        }

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (callback != null) {
                        closeDialog();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (callback !=  null){

                        if( spCenterList.getSelectedItemPosition()<=0){
                            Toast.makeText(JLGHomeActivity.this,"Please Choose Center",Toast.LENGTH_SHORT).show();
                            return;
                        }
                        closeDialog();
                        callback.onAction(spCenterList.getSelectedItem().toString());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        notificationDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        notificationDialog.show();
    }

    private void openCenterMeetingHomeActivity(String centerName){
        try{
            Intent targetDetails = new Intent(this, CenterMeetingHomeActivity.class);
            targetDetails.putExtra(PARAM_LOAN_TYPE, loanType);
            targetDetails.putExtra(PARAM_USER_NAME, userName);
            targetDetails.putExtra(PARAM_BRANCH_ID, branchId);
            targetDetails.putExtra(PARAM_BRANCH_GST_CODE, branchGSTcode);
            targetDetails.putExtra(PARAM_USER_ID, userId);
            targetDetails.putExtra(PARAM_MODULE_TYPE, MODULE_TYPE_TARGET_DETAILS);
            targetDetails.putExtra(PARAM_PRODUCT_ID, productId);
            targetDetails.putExtra(PARAM_CENTER_NAME, centerName); // TODO: CenterName
            startActivity(targetDetails);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    public void closeDialog(){
        try {
            if (notificationDialog != null && notificationDialog.isShowing()){
                notificationDialog.dismiss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public interface ActionCallback {
        void onAction(String centerName);
    }

}
