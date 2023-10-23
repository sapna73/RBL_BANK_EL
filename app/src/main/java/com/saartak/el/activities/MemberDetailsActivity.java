package com.saartak.el.activities;

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

import com.google.gson.Gson;
import com.saartak.el.R;
import com.saartak.el.adapter.MemberDetailsAdapter;
import com.saartak.el.constants.AppConstant;
import com.saartak.el.database.entity.CenterCreationTable;
import com.saartak.el.database.entity.RawDataTable;
import com.saartak.el.helper.MyDividerItemDecoration;
import com.saartak.el.models.DocumentUploadRawdataResponseDTO;
import com.saartak.el.view_models.DynamicUIViewModel;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

import static com.saartak.el.constants.AppConstant.APP_FOLDER;
import static com.saartak.el.constants.AppConstant.IMAGE_UPLOAD_FOLDER_NAME;
import static com.saartak.el.constants.AppConstant.LOAN_NAME_JLG;
import static com.saartak.el.constants.AppConstant.MODULE_TYPE_APPLICANT;
import static com.saartak.el.constants.AppConstant.MODULE_TYPE_LOAN_PROPOSAL_WITH_NOMINEE;
import static com.saartak.el.constants.AppConstant.PARAM_BRANCH_GST_CODE;
import static com.saartak.el.constants.AppConstant.PARAM_BRANCH_ID;
import static com.saartak.el.constants.AppConstant.PARAM_CENTER_TABLE_JSON;
import static com.saartak.el.constants.AppConstant.PARAM_CLIENT_ID;
import static com.saartak.el.constants.AppConstant.PARAM_LOAN_TYPE;
import static com.saartak.el.constants.AppConstant.PARAM_MODULE_TYPE;
import static com.saartak.el.constants.AppConstant.PARAM_USER_ID;
import static com.saartak.el.constants.AppConstant.PARAM_USER_NAME;
import static com.saartak.el.constants.AppConstant.setKeyValueForObject;
import static com.saartak.el.dynamicui.constants.ParametersConstant.SERVICE_CONNECTION_STRING_AUDIT;

public class MemberDetailsActivity extends LOSBaseActivity implements View.OnClickListener, MemberDetailsAdapter.ClientDetailsInterface {
    private static final String TAG = MemberDetailsActivity.class.getCanonicalName();
    LinearLayout llApplicant,llLoanProposal, llDocument;
    String loanType;
    TextView tvClientDetails;
    RecyclerView rvApplicant,rvLoanProposal, rvDocument;
    ImageView ivApplicant,ivLoanProposal, ivDocument;
    TextView tvApplicant,tvLoanProposal, tvDocuments;

    CenterCreationTable CENTER_CREATION_TABLE;

    @Inject
    public ViewModelProvider.Factory viewModelFactory;
    public DynamicUIViewModel viewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_member_details);
        llApplicant = (LinearLayout) findViewById(R.id.ll_applicant);
        llApplicant.setOnClickListener(this);
        llLoanProposal = (LinearLayout) findViewById(R.id.ll_loan_proposal_with_nominee);
        llLoanProposal.setOnClickListener(this);
        llDocument = (LinearLayout) findViewById(R.id.ll_document_upload);
        llDocument.setOnClickListener(this);

        rvApplicant = (RecyclerView) findViewById(R.id.rv_add_applicant);
        rvLoanProposal = (RecyclerView) findViewById(R.id.rv_add_loan_proposal);
        rvDocument = (RecyclerView) findViewById(R.id.rv_add_document);

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

        // TODO: Intent string values
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
        String centerJsonString = getIntent().getStringExtra(PARAM_CENTER_TABLE_JSON);
        if (!TextUtils.isEmpty(centerJsonString)) {
            CENTER_CREATION_TABLE = new Gson().fromJson(centerJsonString, CenterCreationTable.class);
            if (CENTER_CREATION_TABLE != null) {
            }
        }

//        getRawDataForDocumentSection(Arrays.asList(DOCUMENTS_MODULE_SCREEN_NUMBERS_IL),
//                Arrays.asList(DOCUMENTS_MODULE_SCREEN_NAMES_IL), CLIENT_ID, loanType, MODULE_TYPE_DOCUMENTS);

        getDocumentRawData();
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
               /* case R.id.ll_loan_proposal_with_nominee:
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
                    break;*/
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

                            MemberDetailsAdapter applicantSectionAdapter = new MemberDetailsAdapter(MemberDetailsActivity.this,
                                    applicantRawDataList, allClientHashMapList, appHelper, screenNameList, screenNoList, moduleType,
                                    MemberDetailsActivity.this);
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

                            MemberDetailsAdapter applicantSectionAdapter = new MemberDetailsAdapter(MemberDetailsActivity.this,
                                    applicantRawDataList, allClientHashMapList, appHelper, screenNameList, screenNoList, moduleType,
                                    MemberDetailsActivity.this);

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


    private void getDocumentRawData() {
        try {


            appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();

            String folderPath = Environment.getExternalStorageDirectory()
                    .getAbsolutePath() + File.separator + APP_FOLDER + File.separator + IMAGE_UPLOAD_FOLDER_NAME + File.separator
                    + CLIENT_ID + File.separator;

            if (!TextUtils.isEmpty(folderPath)) {

                File folder = new File(folderPath);
                if ( ! folder.exists()) {

                    viewModel.getDocumentRawData(CLIENT_ID,userId,loanType,SERVICE_CONNECTION_STRING_AUDIT);
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

    private void downloadDocuments(List<DocumentUploadRawdataResponseDTO> rawdataResponseDTOList,String clientId,String moduleType) {
        try {
            appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();

            viewModel.downloadDocuments(rawdataResponseDTOList,clientId,moduleType);
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
            INSERT_LOG("downloadDocuments","Exception : "+ex.getMessage());
        }
    }

}
