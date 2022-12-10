package com.swadhaar.los.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bfil.uilibrary.dialogs.ConfirmationDialog;
import com.google.gson.Gson;
import com.swadhaar.los.BuildConfig;
import com.swadhaar.los.R;
import com.swadhaar.los.adapter.MemberDetailsAdapter;
import com.swadhaar.los.constants.AppConstant;
import com.swadhaar.los.database.entity.CenterCreationTable;
import com.swadhaar.los.database.entity.MasterTable;
import com.swadhaar.los.database.entity.RawDataTable;
import com.swadhaar.los.helper.MyDividerItemDecoration;
import com.swadhaar.los.models.DocumentUploadRawdataResponseDTO;
import com.swadhaar.los.models.RawDataResponseDTO;
import com.swadhaar.los.view_models.DynamicUIViewModel;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

import static com.swadhaar.los.constants.AppConstant.APPLICANT_TAB_SCREEN_NAMES_JLG;
import static com.swadhaar.los.constants.AppConstant.APPLICANT_TAB_SCREEN_NUMBERS_JLG;
import static com.swadhaar.los.constants.AppConstant.APP_FOLDER;
import static com.swadhaar.los.constants.AppConstant.DATE_FORMAT_DD_MM_YYYY2;
import static com.swadhaar.los.constants.AppConstant.IMAGE_UPLOAD_FOLDER_NAME;
import static com.swadhaar.los.constants.AppConstant.LOAN_NAME_JLG;
import static com.swadhaar.los.constants.AppConstant.LOAN_PROPOSAL_WITH_NOMINEE_MODULE_SCREEN_NAMES_JLG;
import static com.swadhaar.los.constants.AppConstant.LOAN_PROPOSAL_WITH_NOMINEE_MODULE_SCREEN_NUMBERS_JLG;
import static com.swadhaar.los.constants.AppConstant.MODULE_OTP_VERIFICATION;
import static com.swadhaar.los.constants.AppConstant.MODULE_TYPE_APPLICANT;
import static com.swadhaar.los.constants.AppConstant.MODULE_TYPE_DOCUMENTS;
import static com.swadhaar.los.constants.AppConstant.MODULE_TYPE_LOAN_PROPOSAL_WITH_NOMINEE;
import static com.swadhaar.los.constants.AppConstant.PARAM_BRANCH_GST_CODE;
import static com.swadhaar.los.constants.AppConstant.PARAM_BRANCH_ID;
import static com.swadhaar.los.constants.AppConstant.PARAM_CENTER_TABLE_JSON;
import static com.swadhaar.los.constants.AppConstant.PARAM_CLIENT_ID;
import static com.swadhaar.los.constants.AppConstant.PARAM_LOAN_TYPE;
import static com.swadhaar.los.constants.AppConstant.PARAM_MODULE_TYPE;
import static com.swadhaar.los.constants.AppConstant.PARAM_PRODUCT_ID;
import static com.swadhaar.los.constants.AppConstant.PARAM_USER_ID;
import static com.swadhaar.los.constants.AppConstant.PARAM_USER_NAME;
import static com.swadhaar.los.constants.AppConstant.setKeyValueForObject;
import static com.swadhaar.los.dynamicui.constants.ParametersConstant.CURRENT_STAGE_APPLICATION;
import static com.swadhaar.los.dynamicui.constants.ParametersConstant.SERVICE_CONNECTION_STRING_JLG;

public class LoanApplicationActivity extends LOSBaseActivity implements View.OnClickListener, MemberDetailsAdapter.ClientDetailsInterface {
    private static final String TAG = LoanApplicationActivity.class.getCanonicalName();
    LinearLayout llApplicant,llLoanProposal, llDocument, llUserConsent;
    String loanType;
    TextView tvClientDetails;
    RecyclerView rvApplicant,rvLoanProposal, rvDocument, rvUserConsent;
    ImageView ivApplicant,ivLoanProposal, ivDocument, ivUserConsent;
    TextView tvApplicant,tvLoanProposal, tvDocuments, tvUserConsent;
    TextView tvAppVersion,tvCurrentDate,tvUserName;

    CenterCreationTable CENTER_CREATION_TABLE;

    @Inject
    public ViewModelProvider.Factory viewModelFactory;
    public DynamicUIViewModel viewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_loan_application);

        tvUserName = (TextView)findViewById(R.id.tv_user_name);
        tvCurrentDate = (TextView)findViewById(R.id.tv_currentdate);
        tvAppVersion = (TextView)findViewById(R.id.tv_app_version);

        llApplicant = (LinearLayout) findViewById(R.id.ll_applicant);
        llApplicant.setOnClickListener(this);
        llLoanProposal = (LinearLayout) findViewById(R.id.ll_loan_proposal_with_nominee);
        llLoanProposal.setOnClickListener(this);
        llDocument = (LinearLayout) findViewById(R.id.ll_document_upload);
        llDocument.setOnClickListener(this);
        llUserConsent = (LinearLayout) findViewById(R.id.ll_user_consent);
        llUserConsent.setOnClickListener(this);


        rvApplicant = (RecyclerView) findViewById(R.id.rv_add_applicant);
        rvLoanProposal = (RecyclerView) findViewById(R.id.rv_add_loan_proposal);
        rvDocument = (RecyclerView) findViewById(R.id.rv_add_document);
        rvUserConsent = (RecyclerView) findViewById(R.id.rv_add_user_consent);

        RecyclerView.LayoutManager appLayoutManager = new LinearLayoutManager(getApplicationContext());
        rvApplicant.setLayoutManager(appLayoutManager);
        rvApplicant.setItemAnimator(new DefaultItemAnimator());
        rvApplicant.addItemDecoration(new MyDividerItemDecoration(this, DividerItemDecoration.VERTICAL, 24));

        RecyclerView.LayoutManager loanPropLayoutManager = new LinearLayoutManager(getApplicationContext());
        rvLoanProposal.setLayoutManager(loanPropLayoutManager);
        rvLoanProposal.setItemAnimator(new DefaultItemAnimator());
        rvLoanProposal.addItemDecoration(new MyDividerItemDecoration(this, DividerItemDecoration.VERTICAL, 24));

        RecyclerView.LayoutManager documentLayoutManager = new LinearLayoutManager(getApplicationContext());
        rvDocument.setLayoutManager(documentLayoutManager);
        rvDocument.setItemAnimator(new DefaultItemAnimator());
        rvDocument.addItemDecoration(new MyDividerItemDecoration(this, DividerItemDecoration.VERTICAL, 24));

        RecyclerView.LayoutManager userConsentLayoutManager = new LinearLayoutManager(getApplicationContext());
        rvUserConsent.setLayoutManager(userConsentLayoutManager);
        rvUserConsent.setItemAnimator(new DefaultItemAnimator());
        rvUserConsent.addItemDecoration(new MyDividerItemDecoration(this, DividerItemDecoration.VERTICAL, 24));


        tvClientDetails = (TextView) findViewById(R.id.tv_client_detail);

        tvApplicant = (TextView) findViewById(R.id.tv_add_applicant);
        tvLoanProposal = (TextView) findViewById(R.id.tv_add_loan_proposal);
        tvDocuments = (TextView) findViewById(R.id.tv_add_document);

        ivApplicant = (ImageView) findViewById(R.id.iv_add_applicant);
        ivApplicant.setOnClickListener(this);
        ivLoanProposal = (ImageView) findViewById(R.id.iv_add_loan_proposal);
        ivLoanProposal.setOnClickListener(this);
        ivDocument = (ImageView) findViewById(R.id.iv_add_document);
        ivDocument.setOnClickListener(this);
        ivUserConsent = (ImageView) findViewById(R.id.iv_add_user_consent);
        ivUserConsent.setOnClickListener(this);

        // TODO: Intent string values
        loanType = getIntent().getStringExtra(PARAM_LOAN_TYPE);
        moduleType = getIntent().getStringExtra(PARAM_MODULE_TYPE);
        userName = getIntent().getStringExtra(PARAM_USER_NAME);
        userId = getIntent().getStringExtra(PARAM_USER_ID);
        branchId = getIntent().getStringExtra(PARAM_BRANCH_ID);
        branchGSTcode = getIntent().getStringExtra(PARAM_BRANCH_GST_CODE);
        CLIENT_ID = getIntent().getStringExtra(PARAM_CLIENT_ID);
        productId = getIntent().getStringExtra(PARAM_PRODUCT_ID);
        String centerJsonString = getIntent().getStringExtra(PARAM_CENTER_TABLE_JSON);
        if (!TextUtils.isEmpty(centerJsonString)) {
            CENTER_CREATION_TABLE = new Gson().fromJson(centerJsonString, CenterCreationTable.class);
            if (CENTER_CREATION_TABLE != null) {
            }
        }

        if(!TextUtils.isEmpty(userName)){
            tvUserName.setText(userName);
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

    }

    private void INSERT_LOG(String methodName,String message){
        try{
            if(viewModel !=null){
                viewModel.insertLog(methodName,message,userId,"","ClientDetailsActivity"
                        ,CLIENT_ID,loanType,moduleType);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }


    @Override
    protected void onResume() {
        super.onResume();

        loanType = getIntent().getStringExtra(PARAM_LOAN_TYPE);
        moduleType = getIntent().getStringExtra(PARAM_MODULE_TYPE);
        userName = getIntent().getStringExtra(PARAM_USER_NAME);
        userId = getIntent().getStringExtra(PARAM_USER_ID);
        branchId = getIntent().getStringExtra(PARAM_BRANCH_ID);
        branchGSTcode = getIntent().getStringExtra(PARAM_BRANCH_GST_CODE);
        CLIENT_ID = getIntent().getStringExtra(PARAM_CLIENT_ID);
        productId = getIntent().getStringExtra(PARAM_PRODUCT_ID);
        String centerJsonString = getIntent().getStringExtra(PARAM_CENTER_TABLE_JSON);
        if (!TextUtils.isEmpty(centerJsonString)) {
            CENTER_CREATION_TABLE = new Gson().fromJson(centerJsonString, CenterCreationTable.class);
            if (CENTER_CREATION_TABLE != null) {
            }
        }

        // TODO: Getting Master Table By Client
        getMasterTableByClientId();

    }

    @Override
    public void onClick(View v) {
        try {
            switch (v.getId()) {
                case R.id.ll_applicant:
                    Intent applicant = new Intent(this, BaseActivity.class);
                    applicant.putExtra(PARAM_LOAN_TYPE, loanType);
                    applicant.putExtra(PARAM_USER_NAME, userName);
                    applicant.putExtra(PARAM_USER_ID, userId);
                    applicant.putExtra(PARAM_CLIENT_ID, CLIENT_ID);
                    applicant.putExtra(PARAM_BRANCH_ID, branchId);
                    applicant.putExtra(PARAM_BRANCH_GST_CODE, branchGSTcode);
                    applicant.putExtra(PARAM_MODULE_TYPE, MODULE_TYPE_APPLICANT);
                    if( ! TextUtils.isEmpty(loanType) && loanType.equalsIgnoreCase(LOAN_NAME_JLG) && CENTER_CREATION_TABLE !=null){
                        String centerTableJson=new Gson().toJson(CENTER_CREATION_TABLE, CenterCreationTable.class);
                        if(!TextUtils.isEmpty(centerTableJson)){
                            applicant.putExtra(PARAM_CENTER_TABLE_JSON, centerTableJson);
                        }
                    }
                    startActivity(applicant);
                    break;
                case R.id.ll_loan_proposal_with_nominee:
                    Intent loanProposal = new Intent(this, BaseActivity.class);
                    loanProposal.putExtra(PARAM_LOAN_TYPE, loanType);
                    loanProposal.putExtra(PARAM_USER_NAME, userName);
                    loanProposal.putExtra(PARAM_USER_ID, userId);
                    loanProposal.putExtra(PARAM_CLIENT_ID, CLIENT_ID);
                    loanProposal.putExtra(PARAM_BRANCH_ID, branchId);
                    loanProposal.putExtra(PARAM_BRANCH_GST_CODE, branchGSTcode);
                    loanProposal.putExtra(PARAM_MODULE_TYPE, MODULE_TYPE_LOAN_PROPOSAL_WITH_NOMINEE);
                    startActivity(loanProposal);
                    break;

                case R.id.ll_document_upload:
                    Intent intentDocument = new Intent(this, BaseActivity.class);
                    intentDocument.putExtra(PARAM_LOAN_TYPE, loanType);
                    intentDocument.putExtra(PARAM_USER_NAME, userName);
                    intentDocument.putExtra(PARAM_USER_ID, userId);
                    intentDocument.putExtra(PARAM_CLIENT_ID, CLIENT_ID);
                    intentDocument.putExtra(PARAM_BRANCH_ID, branchId);
                    intentDocument.putExtra(PARAM_BRANCH_GST_CODE, branchGSTcode);
                    intentDocument.putExtra(PARAM_MODULE_TYPE, MODULE_TYPE_DOCUMENTS);
                    startActivity(intentDocument);
                    break;

                case R.id.ll_user_consent:
                    Intent intentUserConsent = new Intent(this, BaseActivity.class);
                    intentUserConsent.putExtra(PARAM_LOAN_TYPE, loanType);
                    intentUserConsent.putExtra(PARAM_USER_NAME, userName);
                    intentUserConsent.putExtra(PARAM_BRANCH_ID, branchId);
                    intentUserConsent.putExtra(PARAM_USER_ID, userId);
                    intentUserConsent.putExtra(PARAM_BRANCH_GST_CODE, branchGSTcode);
                    intentUserConsent.putExtra(PARAM_MODULE_TYPE, MODULE_OTP_VERIFICATION);
                    intentUserConsent.putExtra(PARAM_CLIENT_ID, CLIENT_ID);
                    startActivity(intentUserConsent);
                    break;
                    
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void getRawDataForApplicantSection(List<String> screenNoList, List<String> screenNameList, String client, String loanType,
                                              String moduleType) {
        ArrayList<HashMap<String, Object>> allClientHashMapList = new ArrayList<>();
        try {
            viewModel.getRawDataForSingleClient(screenNameList, client, loanType, moduleType);
            if (viewModel.getRawTableLiveData() != null) {
                Observer getRawDataForAllClientObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        List<RawDataTable> applicantRawDataList = (List<RawDataTable>) o;
                        viewModel.getRawTableLiveData().removeObserver(this);
                        if (applicantRawDataList != null && applicantRawDataList.size() > 0) {
                            for (RawDataTable rawDataTable : applicantRawDataList) {
                                HashMap<String, Object> hashMap = setKeyValueForObject(rawDataTable);
                                allClientHashMapList.add(hashMap);
                            }
                            Log.d(TAG, "HashMapList ==> " + allClientHashMapList.toString());
                        }
                        if (allClientHashMapList.size() > 0) {

                            ivApplicant.setVisibility(View.GONE);
                            tvApplicant.setVisibility(View.GONE);

                            MemberDetailsAdapter applicantSectionAdapter = new MemberDetailsAdapter(LoanApplicationActivity.this,
                                    applicantRawDataList, allClientHashMapList, appHelper, screenNameList, screenNoList, moduleType,
                                    LoanApplicationActivity.this);
                            rvApplicant.setAdapter(applicantSectionAdapter);
                            applicantSectionAdapter.notifyDataSetChanged();

                        } else {
                            ivApplicant.setVisibility(View.VISIBLE);
                            tvApplicant.setVisibility(View.VISIBLE);
                        }

                    }
                };
                viewModel.getRawTableLiveData().observe(this, getRawDataForAllClientObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            INSERT_LOG("getRawDataForApplicantSection","Exception : "+ex.getMessage());
        }
    }


    public void getRawDataForLoanProposalSection(List<String> screenNoList, List<String> screenNameList, String client, String loanType,
                                                 String moduleType) {
        ArrayList<HashMap<String, Object>> allClientHashMapList = new ArrayList<>();
        try {
            viewModel.getRawDataForSingleClient(screenNameList, client, loanType, moduleType);
            if (viewModel.getRawTableLiveData() != null) {
                Observer getRawDataForAllClientObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        List<RawDataTable> applicantRawDataList = (List<RawDataTable>) o;
                        viewModel.getRawTableLiveData().removeObserver(this);
                        if (applicantRawDataList != null && applicantRawDataList.size() > 0) {
                            for (RawDataTable rawDataTable : applicantRawDataList) {
                                HashMap<String, Object> hashMap = setKeyValueForObject(rawDataTable);
                                allClientHashMapList.add(hashMap);
                            }
                            Log.d(TAG, "HashMapList ==> " + allClientHashMapList.toString());
                        }
                        if (allClientHashMapList.size() > 0) {

                            ivLoanProposal.setVisibility(View.GONE);
                            tvLoanProposal.setVisibility(View.GONE);

                            MemberDetailsAdapter applicantSectionAdapter = new MemberDetailsAdapter(LoanApplicationActivity.this,
                                    applicantRawDataList, allClientHashMapList, appHelper, screenNameList, screenNoList, moduleType,
                                    LoanApplicationActivity.this);

                            rvLoanProposal.setAdapter(applicantSectionAdapter);
                            applicantSectionAdapter.notifyDataSetChanged();

                        } else {
                            ivLoanProposal.setVisibility(View.VISIBLE);
                            tvLoanProposal.setVisibility(View.VISIBLE);
                        }

                    }
                };
                viewModel.getRawTableLiveData().observe(this, getRawDataForAllClientObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            INSERT_LOG("getRawDataForLoanProposalSection","Exception : "+ex.getMessage());
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    public void callBackForScreen(String moduleType, String screenNameToCall) {
        try {

            loanType = getIntent().getStringExtra(PARAM_LOAN_TYPE);
            moduleType = getIntent().getStringExtra(PARAM_MODULE_TYPE);
            userName = getIntent().getStringExtra(PARAM_USER_NAME);
            userId = getIntent().getStringExtra(PARAM_USER_ID);
            branchId = getIntent().getStringExtra(PARAM_BRANCH_ID);
            branchGSTcode = getIntent().getStringExtra(PARAM_BRANCH_GST_CODE);
            CLIENT_ID = getIntent().getStringExtra(PARAM_CLIENT_ID);
            String centerJsonString = getIntent().getStringExtra(PARAM_CENTER_TABLE_JSON);
            if (!TextUtils.isEmpty(centerJsonString)) {
                CENTER_CREATION_TABLE = new Gson().fromJson(centerJsonString, CenterCreationTable.class);
                if (CENTER_CREATION_TABLE != null) {
                }
            }
            Intent intentDocument = new Intent(this, BaseActivity.class);
            intentDocument.putExtra(PARAM_LOAN_TYPE, loanType);
            intentDocument.putExtra(PARAM_USER_NAME, userName);
            intentDocument.putExtra(PARAM_BRANCH_ID, branchId);
            intentDocument.putExtra(PARAM_BRANCH_GST_CODE, branchGSTcode);
            intentDocument.putExtra(PARAM_USER_ID, userId);
            intentDocument.putExtra(PARAM_MODULE_TYPE, moduleType);
            intentDocument.putExtra(PARAM_CLIENT_ID, CLIENT_ID);
            startActivity(intentDocument);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }



    private void getMasterTableByClientId() {
        try {

            if (appHelper.isNetworkAvailable()) {

                appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();

                viewModel.getMasterTableByClientId(CLIENT_ID);
                if (viewModel.getmasterTableLiveData() != null) {
                    Observer getMasterTableByClientIdObserver = new Observer() {
                        @Override
                        public void onChanged(@Nullable Object o) {
                            appHelper.getDialogHelper().getLoadingDialog().closeDialog();
                            MasterTable masterTableResponse = (MasterTable) o;
                            viewModel.getmasterTableLiveData().removeObserver(this);

                            // TODO: Checking.. is data needs to capture
                            if (masterTableResponse != null && masterTableResponse.isDataNeedsToCaptureFromServer()) {
                                // TODO: Calling Raw Data From Server
                                getRawDataFromServerForSingleCustomerApplication();
                            } else {
                                // TODO: getRawDataForAllsections
                                getRawDataForAllSections();
                            }
                        }
                    };
                    viewModel.getmasterTableLiveData().observe(this, getMasterTableByClientIdObserver);
                }
            } else {
                appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT,
                        "Please check your internet connection and try again");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            appHelper.getDialogHelper().getLoadingDialog().closeDialog();

            INSERT_LOG("getMasterTableByClientId", "Exception : " + ex.getMessage());
        }
    }

    private void getRawDataFromServerForSingleCustomerApplication() {
        try {
            if (appHelper.isNetworkAvailable()) {

                appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();

                viewModel.getRawDataFromServerForSingleCustomerApplication(CLIENT_ID, userId, loanType, productId,SERVICE_CONNECTION_STRING_JLG);
                if (viewModel.getRawDataFromServerList() != null) {
                    Observer syncDataToServerObserver = new Observer() {
                        @Override
                        public void onChanged(@Nullable Object o) {
                            appHelper.getDialogHelper().getLoadingDialog().closeDialog();

                            List<RawDataResponseDTO.Table> rawDataFromServerList = (List<RawDataResponseDTO.Table>) o;
                            viewModel.getRawDataFromServerList().removeObserver(this);
                            if (rawDataFromServerList != null && rawDataFromServerList.size() > 0) {

                                // TODO: Getting raw data for multiple screen
                                getMetaDataForMultipleScreen(rawDataFromServerList, loanType, userId);

                            } else {
                                // TODO:  getRawData For All sections
                                getRawDataForAllSections();

                            }
                        }
                    };
                    viewModel.getRawDataFromServerList().observe(this, syncDataToServerObserver);
                }
            } else {
                appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT,
                        "Please check your internet connection and try again");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            appHelper.getDialogHelper().getLoadingDialog().closeDialog();
            INSERT_LOG("getRawDataFromServer", "Exception : " + ex.getMessage());
        }
    }

    private void getMetaDataForMultipleScreen(List<RawDataResponseDTO.Table> rawDataFromServerList,
                                              String loanType, String userId) {
        try {

            appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();
            viewModel.getMetaDataForMultipleScreen(rawDataFromServerList, loanType, userId, productId, CURRENT_STAGE_APPLICATION);
            if (viewModel.getmasterTableLiveDataList() != null) {
                Observer getMasterTableDetailsObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        appHelper.getDialogHelper().getLoadingDialog().closeDialog();
                        List<MasterTable> masterTableList = (List<MasterTable>) o;
                        viewModel.getmasterTableLiveDataList().removeObserver(this);

                        // TODO:  getRawDataForAllsections
                        getRawDataForAllSections();
                        // TODO: getdocumentrawdata
                        getDocumentRawData();
                    }
                };
                viewModel.getmasterTableLiveDataList().observe(this, getMasterTableDetailsObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            appHelper.getDialogHelper().getLoadingDialog().closeDialog();
            INSERT_LOG("getMetaDataForMultipleScreen", "Exception : " + ex.getMessage());
        }
    }

    private void getDocumentRawData() {
        try {

            appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();

            String folderPath = Environment.getExternalStorageDirectory()
                    .getAbsolutePath() + File.separator + APP_FOLDER + File.separator + IMAGE_UPLOAD_FOLDER_NAME + File.separator
                    + CLIENT_ID + File.separator;

            if (!TextUtils.isEmpty(folderPath)) {

                File folder = new File(folderPath);
                if ( ! folder.exists()) {

                    viewModel.getDocumentRawData(CLIENT_ID,userId,loanType,SERVICE_CONNECTION_STRING_JLG);
                    if (viewModel.getDocumentUploadRawDataResponseList() != null) {
                        Observer observer = new Observer() {
                            @Override
                            public void onChanged(Object o) {
                                appHelper.getDialogHelper().getLoadingDialog().closeDialog();
                                List<DocumentUploadRawdataResponseDTO> rawdataResponseDTOList = (List<DocumentUploadRawdataResponseDTO>) o;
                                viewModel.getDocumentUploadRawDataResponseList().removeObserver(this);

                                if( rawdataResponseDTOList !=null && rawdataResponseDTOList.size()>0){
                                    // TODO: DOWNLOAD DOCUMENTS
                                    downloadDocuments(rawdataResponseDTOList,CLIENT_ID,moduleType);
                                }
                            }
                        };
                        viewModel.getDocumentUploadRawDataResponseList().observe(this, observer);
                    }

                }else{
                    appHelper.getDialogHelper().getLoadingDialog().closeDialog();
                }
            }else{
                appHelper.getDialogHelper().getLoadingDialog().closeDialog();
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            appHelper.getDialogHelper().getLoadingDialog().closeDialog();
            INSERT_LOG("getDocumentRawData","Exception : "+ex.getMessage());
        }
    }

    private void downloadDocuments(List<DocumentUploadRawdataResponseDTO> rawdataResponseDTOList, String clientId, String moduleType) {
        try {
            appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();

            viewModel.downloadDocuments(rawdataResponseDTOList, clientId, moduleType);
            if (viewModel.getStringLiveData() != null) {
                Observer observer = new Observer() {
                    @Override
                    public void onChanged(Object o) {
                        appHelper.getDialogHelper().getLoadingDialog().closeDialog();
                        String response = (String) o;
                        Log.d(TAG, "downloadDocuments ==> " + response);
                        viewModel.getStringLiveData().removeObserver(this);
                    }
                };
                viewModel.getStringLiveData().observe(this, observer);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            appHelper.getDialogHelper().getLoadingDialog().closeDialog();
            INSERT_LOG("downloadDocuments", "Exception : " + ex.getMessage());
        }
    }

    public void getRawDataForAllSections() {
        getRawDataForApplicantSection(Arrays.asList(APPLICANT_TAB_SCREEN_NUMBERS_JLG),
                Arrays.asList(APPLICANT_TAB_SCREEN_NAMES_JLG), CLIENT_ID, loanType, AppConstant.MODULE_TYPE_APPLICANT);

        getRawDataForLoanProposalSection(Arrays.asList(LOAN_PROPOSAL_WITH_NOMINEE_MODULE_SCREEN_NUMBERS_JLG),
                Arrays.asList(LOAN_PROPOSAL_WITH_NOMINEE_MODULE_SCREEN_NAMES_JLG), CLIENT_ID, loanType, MODULE_TYPE_LOAN_PROPOSAL_WITH_NOMINEE);

    }

}
