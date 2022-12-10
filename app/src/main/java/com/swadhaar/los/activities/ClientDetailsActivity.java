package com.swadhaar.los.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
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
import com.swadhaar.los.BuildConfig;
import com.swadhaar.los.R;
import com.swadhaar.los.adapter.ClientDetailsAdapter;
import com.swadhaar.los.adapter.MultipleCoApplicantAdapter;
import com.swadhaar.los.constants.AppConstant;
import com.swadhaar.los.database.entity.MasterTable;
import com.swadhaar.los.database.entity.RawDataTable;
import com.swadhaar.los.helper.MyDividerItemDecoration;
import com.swadhaar.los.models.DocumentUploadRawdataResponseDTO;
import com.swadhaar.los.models.NumberOfCoApplicant;
import com.swadhaar.los.models.RawDataResponseDTO;
import com.swadhaar.los.view_models.DynamicUIViewModel;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

import static com.swadhaar.los.constants.AppConstant.APPLICANT_TAB_SCREEN_NAMES_IL;
import static com.swadhaar.los.constants.AppConstant.APPLICANT_TAB_SCREEN_NUMBERS_IL;
import static com.swadhaar.los.constants.AppConstant.APP_FOLDER;
import static com.swadhaar.los.constants.AppConstant.BUSINESS_TAB_SCREEN_NAMES_IL;
import static com.swadhaar.los.constants.AppConstant.BUSINESS_TAB_SCREEN_NUMBERS_IL;
import static com.swadhaar.los.constants.AppConstant.CO_APPLICANT_TAB_SCREEN_NAMES_IL;
import static com.swadhaar.los.constants.AppConstant.CO_APPLICANT_TAB_SCREEN_NUMBERS_IL;
import static com.swadhaar.los.constants.AppConstant.DATE_FORMAT_DD_MM_YYYY2;
import static com.swadhaar.los.constants.AppConstant.IMAGE_UPLOAD_FOLDER_NAME;
import static com.swadhaar.los.constants.AppConstant.LOAN_NAME_AHL;
import static com.swadhaar.los.constants.AppConstant.LOAN_NAME_MSME;
import static com.swadhaar.los.constants.AppConstant.LOAN_NAME_PHL;
import static com.swadhaar.los.constants.AppConstant.LOAN_PROPOSAL_WITH_NOMINEE_MODULE_SCREEN_NAMES_IL;
import static com.swadhaar.los.constants.AppConstant.LOAN_PROPOSAL_WITH_NOMINEE_MODULE_SCREEN_NUMBERS_IL;
import static com.swadhaar.los.constants.AppConstant.MODULE_TYPE_GENERATE_CIBIL;
import static com.swadhaar.los.constants.AppConstant.MODULE_OTP_VERIFICATION;
import static com.swadhaar.los.constants.AppConstant.MODULE_TYPE_SALARY;
import static com.swadhaar.los.constants.AppConstant.PARAM_LOAN_TYPE;
import static com.swadhaar.los.constants.AppConstant.MODULE_TYPE_APPLICANT;
import static com.swadhaar.los.constants.AppConstant.MODULE_TYPE_BUSINESS;
import static com.swadhaar.los.constants.AppConstant.MODULE_TYPE_CO_APPLICANT;
import static com.swadhaar.los.constants.AppConstant.PARAM_MODULE_TYPE;
import static com.swadhaar.los.constants.AppConstant.MODULE_TYPE_DOCUMENTS;
import static com.swadhaar.los.constants.AppConstant.MODULE_TYPE_LOAN_PROPOSAL_WITH_NOMINEE;
import static com.swadhaar.los.constants.AppConstant.PARAM_BRANCH_ID;
import static com.swadhaar.los.constants.AppConstant.PARAM_CLIENT_ID;
import static com.swadhaar.los.constants.AppConstant.PARAM_PRODUCT_ID;
import static com.swadhaar.los.constants.AppConstant.PARAM_USER_ID;
import static com.swadhaar.los.constants.AppConstant.PARAM_USER_NAME;
import static com.swadhaar.los.constants.AppConstant.SALARY_TAB_SCREEN_NAMES_AHL;
import static com.swadhaar.los.constants.AppConstant.SALARY_TAB_SCREEN_NAMES_PHL;
import static com.swadhaar.los.constants.AppConstant.SALARY_TAB_SCREEN_NUMBERS_AHL;
import static com.swadhaar.los.constants.AppConstant.SALARY_TAB_SCREEN_NUMBERS_PHL;
import static com.swadhaar.los.constants.AppConstant.SCREEN_NAME_CO_APPLICANT_KYC;
import static com.swadhaar.los.constants.AppConstant.setKeyValueForObject;
import static com.swadhaar.los.dynamicui.constants.ParametersConstant.CURRENT_STAGE_APPLICATION;
import static com.swadhaar.los.dynamicui.constants.ParametersConstant.SERVICE_CONNECTION_STRING_AUDIT;
import static com.swadhaar.los.dynamicui.constants.ParametersConstant.TAG_NAME_NO_OF_COAPPLICANT;

public class ClientDetailsActivity extends LOSBaseActivity implements View.OnClickListener, ClientDetailsAdapter.ClientDetailsInterface {
    private static final String TAG = ClientDetailsActivity.class.getCanonicalName();
    LinearLayout llApplicant, llCoApplicant, llBusiness, llSalary, llLoanProposal, llDocument, llUserConsent, llGenerateCibil;
    ImageButton ibAddCoApplicant;
    String loanType;
    TextView tvClientDetails;
    RecyclerView rvApplicant, rvCoApplicant, rvBusiness, rvSalary, rvLoanProposal, rvDocument, rvUserConsent, rvGenerateCibil;
    ImageView ivApplicant, ivCoApplicant, ivBusiness, ivSalary, ivLoanProposal, ivDocument, ivUserConsent, ivGenerateCibil;
    TextView tvApplicant, tvCoApplicant, tvBusiness, tvSalary, tvLoanProposal, tvDocuments, tvUserConsent;
    TextView tvAppVersion, tvCurrentDate, tvUserName;

    @Inject
    public ViewModelProvider.Factory viewModelFactory;
    public DynamicUIViewModel viewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_client_details);

        tvUserName = (TextView) findViewById(R.id.tv_user_name);
        tvCurrentDate = (TextView) findViewById(R.id.tv_currentdate);
        tvAppVersion = (TextView) findViewById(R.id.tv_app_version);

        llApplicant = (LinearLayout) findViewById(R.id.ll_applicant);
        llApplicant.setOnClickListener(this);
        llCoApplicant = (LinearLayout) findViewById(R.id.ll_co_applicant);
        llCoApplicant.setOnClickListener(this);
        llBusiness = (LinearLayout) findViewById(R.id.ll_business);
        llBusiness.setOnClickListener(this);
        llSalary = (LinearLayout) findViewById(R.id.ll_salary);
        llSalary.setOnClickListener(this);
        llLoanProposal = (LinearLayout) findViewById(R.id.ll_loan_proposal_with_nominee);
        llLoanProposal.setOnClickListener(this);
        llDocument = (LinearLayout) findViewById(R.id.ll_document_upload);
        llDocument.setOnClickListener(this);
        llUserConsent = (LinearLayout) findViewById(R.id.ll_user_consent);
        llUserConsent.setOnClickListener(this);
        llGenerateCibil = (LinearLayout) findViewById(R.id.ll_generate_cibil);
        llGenerateCibil.setOnClickListener(this);

        ibAddCoApplicant = (ImageButton) findViewById(R.id.ib_add_co_applicant);
        ibAddCoApplicant.setOnClickListener(this);

        rvApplicant = (RecyclerView) findViewById(R.id.rv_add_applicant);
        rvCoApplicant = (RecyclerView) findViewById(R.id.rv_add_co_applicant);
        rvBusiness = (RecyclerView) findViewById(R.id.rv_add_business);
        rvSalary = (RecyclerView) findViewById(R.id.rv_add_salary);
        rvLoanProposal = (RecyclerView) findViewById(R.id.rv_add_loan_proposal);
        rvDocument = (RecyclerView) findViewById(R.id.rv_add_document);
        rvUserConsent = (RecyclerView) findViewById(R.id.rv_add_user_consent);
        rvGenerateCibil = (RecyclerView) findViewById(R.id.rv_generate_cibil);

        RecyclerView.LayoutManager appLayoutManager = new LinearLayoutManager(getApplicationContext());
        rvApplicant.setLayoutManager(appLayoutManager);
        rvApplicant.setItemAnimator(new DefaultItemAnimator());
        rvApplicant.addItemDecoration(new MyDividerItemDecoration(this, DividerItemDecoration.VERTICAL, 24));

        RecyclerView.LayoutManager coAppLayoutManager = new LinearLayoutManager(getApplicationContext());
        ((LinearLayoutManager) coAppLayoutManager).setOrientation(RecyclerView.HORIZONTAL);
        rvCoApplicant.setLayoutManager(coAppLayoutManager);
        rvCoApplicant.setItemAnimator(new DefaultItemAnimator());
//        rvCoApplicant.addItemDecoration(new MyDividerItemDecoration(this, DividerItemDecoration.HORIZONTAL, 24));

        RecyclerView.LayoutManager businessLayoutManager = new LinearLayoutManager(getApplicationContext());
        rvBusiness.setLayoutManager(businessLayoutManager);
        rvBusiness.setItemAnimator(new DefaultItemAnimator());
        rvBusiness.addItemDecoration(new MyDividerItemDecoration(this, DividerItemDecoration.VERTICAL, 24));

        RecyclerView.LayoutManager salaryLayoutManager = new LinearLayoutManager(getApplicationContext());
        rvSalary.setLayoutManager(salaryLayoutManager);
        rvSalary.setItemAnimator(new DefaultItemAnimator());
        rvSalary.addItemDecoration(new MyDividerItemDecoration(this, DividerItemDecoration.VERTICAL, 24));

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

        RecyclerView.LayoutManager generateCibilLayoutManager = new LinearLayoutManager(getApplicationContext());
        rvGenerateCibil.setLayoutManager(generateCibilLayoutManager);
        rvGenerateCibil.setItemAnimator(new DefaultItemAnimator());
        rvGenerateCibil.addItemDecoration(new MyDividerItemDecoration(this, DividerItemDecoration.VERTICAL, 24));

        tvClientDetails = (TextView) findViewById(R.id.tv_client_detail);

        tvApplicant = (TextView) findViewById(R.id.tv_add_applicant);
        tvCoApplicant = (TextView) findViewById(R.id.tv_add_co_applicant);
        tvBusiness = (TextView) findViewById(R.id.tv_add_business);
        tvSalary = (TextView) findViewById(R.id.tv_add_salary);
        tvLoanProposal = (TextView) findViewById(R.id.tv_add_loan_proposal);
        tvDocuments = (TextView) findViewById(R.id.tv_add_document);

        ivApplicant = (ImageView) findViewById(R.id.iv_add_applicant);
        ivApplicant.setOnClickListener(this);
        ivCoApplicant = (ImageView) findViewById(R.id.iv_add_co_applicant);
        ivCoApplicant.setOnClickListener(this);
        ivBusiness = (ImageView) findViewById(R.id.iv_add_business);
        ivBusiness.setOnClickListener(this);
        ivSalary = (ImageView) findViewById(R.id.iv_add_salary);
        ivSalary.setOnClickListener(this);
        ivLoanProposal = (ImageView) findViewById(R.id.iv_add_loan_proposal);
        ivLoanProposal.setOnClickListener(this);
        ivDocument = (ImageView) findViewById(R.id.iv_add_document);
        ivDocument.setOnClickListener(this);
        ivUserConsent = (ImageView) findViewById(R.id.iv_add_user_consent);
        ivUserConsent.setOnClickListener(this);
        ivGenerateCibil = (ImageView) findViewById(R.id.iv_generate_cibil);
        ivGenerateCibil.setOnClickListener(this);

        // TODO: Intent string values
        loanType = getIntent().getStringExtra(PARAM_LOAN_TYPE);
        moduleType = getIntent().getStringExtra(PARAM_MODULE_TYPE);
        userName = getIntent().getStringExtra(PARAM_USER_NAME);
        userId = getIntent().getStringExtra(PARAM_USER_ID);
        branchId = getIntent().getStringExtra(PARAM_BRANCH_ID);
        CLIENT_ID = getIntent().getStringExtra(PARAM_CLIENT_ID);
        productId = getIntent().getStringExtra(PARAM_PRODUCT_ID);

        if (!TextUtils.isEmpty(userName)) {
            tvUserName.setText(userName);
        }

        String appVersion = BuildConfig.VERSION_NAME;
        if (!TextUtils.isEmpty(appVersion)) {
            tvAppVersion.setText(getResources().getString(R.string.version) + " " + appVersion);
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

    private void INSERT_LOG(String methodName, String message) {
        try {
            if (viewModel != null) {
                viewModel.insertLog(methodName, message, userId, "", "ClientDetailsActivity"
                        , CLIENT_ID, loanType, moduleType);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    @Override
    protected void onResume() {
        super.onResume();

        // TODO: Intent string values
        loanType = getIntent().getStringExtra(PARAM_LOAN_TYPE);
        moduleType = getIntent().getStringExtra(PARAM_MODULE_TYPE);
        userName = getIntent().getStringExtra(PARAM_USER_NAME);
        userId = getIntent().getStringExtra(PARAM_USER_ID);
        branchId = getIntent().getStringExtra(PARAM_BRANCH_ID);
        CLIENT_ID = getIntent().getStringExtra(PARAM_CLIENT_ID);
        productId = getIntent().getStringExtra(PARAM_PRODUCT_ID);

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
                    applicant.putExtra(PARAM_BRANCH_ID, branchId);
                    applicant.putExtra(PARAM_USER_ID, userId);
                    applicant.putExtra(PARAM_CLIENT_ID, CLIENT_ID);
                    applicant.putExtra(PARAM_MODULE_TYPE, MODULE_TYPE_APPLICANT);
                    startActivity(applicant);
                    break;

                case R.id.ll_co_applicant:
                    Intent coapplicant = new Intent(this, BaseActivity.class);
                    coapplicant.putExtra(PARAM_LOAN_TYPE, loanType);
                    coapplicant.putExtra(PARAM_USER_NAME, userName);
                    coapplicant.putExtra(PARAM_BRANCH_ID, branchId);
                    coapplicant.putExtra(PARAM_USER_ID, userId);
                    coapplicant.putExtra(PARAM_CLIENT_ID, CLIENT_ID);
                    coapplicant.putExtra(PARAM_MODULE_TYPE, MODULE_TYPE_CO_APPLICANT + 1); // TODO: hardcoded as co applicant 1
                    startActivity(coapplicant);
                    break;
                case R.id.ib_add_co_applicant:
                    getNoOfCoApplicants(SCREEN_NAME_CO_APPLICANT_KYC, CLIENT_ID, userId, loanType, TAG_NAME_NO_OF_COAPPLICANT);
                    break;
                case R.id.ll_business:
                    Intent business = new Intent(this, BaseActivity.class);
                    business.putExtra(PARAM_LOAN_TYPE, loanType);
                    business.putExtra(PARAM_USER_NAME, userName);
                    business.putExtra(PARAM_BRANCH_ID, branchId);
                    business.putExtra(PARAM_USER_ID, userId);
                    business.putExtra(PARAM_CLIENT_ID, CLIENT_ID);
                    business.putExtra(PARAM_MODULE_TYPE, MODULE_TYPE_BUSINESS);
                    startActivity(business);
                    break;
                case R.id.ll_salary:
                    Intent salary = new Intent(this, BaseActivity.class);
                    salary.putExtra(PARAM_LOAN_TYPE, loanType);
                    salary.putExtra(PARAM_USER_NAME, userName);
                    salary.putExtra(PARAM_BRANCH_ID, branchId);
                    salary.putExtra(PARAM_USER_ID, userId);
                    salary.putExtra(PARAM_CLIENT_ID, CLIENT_ID);
                    salary.putExtra(PARAM_MODULE_TYPE, MODULE_TYPE_SALARY);
                    startActivity(salary);
                    break;
                case R.id.ll_loan_proposal_with_nominee:
                    Intent loanProposal = new Intent(this, BaseActivity.class);
                    loanProposal.putExtra(PARAM_LOAN_TYPE, loanType);
                    loanProposal.putExtra(PARAM_USER_NAME, userName);
                    loanProposal.putExtra(PARAM_BRANCH_ID, branchId);
                    loanProposal.putExtra(PARAM_USER_ID, userId);
                    loanProposal.putExtra(PARAM_CLIENT_ID, CLIENT_ID);
                    loanProposal.putExtra(PARAM_MODULE_TYPE, MODULE_TYPE_LOAN_PROPOSAL_WITH_NOMINEE);
                    startActivity(loanProposal);
                    break;
                case R.id.ll_document_upload:
                    Intent intentDocument = new Intent(this, BaseActivity.class);
                    intentDocument.putExtra(PARAM_LOAN_TYPE, loanType);
                    intentDocument.putExtra(PARAM_USER_NAME, userName);
                    intentDocument.putExtra(PARAM_BRANCH_ID, branchId);
                    intentDocument.putExtra(PARAM_USER_ID, userId);
                    intentDocument.putExtra(PARAM_MODULE_TYPE, MODULE_TYPE_DOCUMENTS);
                    intentDocument.putExtra(PARAM_CLIENT_ID, CLIENT_ID);
                    startActivity(intentDocument);
                    break;
                case R.id.ll_user_consent:
                    Intent intentUserConsent = new Intent(this, BaseActivity.class);
                    intentUserConsent.putExtra(PARAM_LOAN_TYPE, loanType);
                    intentUserConsent.putExtra(PARAM_USER_NAME, userName);
                    intentUserConsent.putExtra(PARAM_BRANCH_ID, branchId);
                    intentUserConsent.putExtra(PARAM_USER_ID, userId);
                    intentUserConsent.putExtra(PARAM_MODULE_TYPE, MODULE_OTP_VERIFICATION);
                    intentUserConsent.putExtra(PARAM_CLIENT_ID, CLIENT_ID);
                    startActivity(intentUserConsent);
                    break;
                case R.id.ll_generate_cibil:
                    Intent intentGenerateCibil = new Intent(this, BaseActivity.class);
                    intentGenerateCibil.putExtra(PARAM_LOAN_TYPE, loanType);
                    intentGenerateCibil.putExtra(PARAM_USER_NAME, userName);
                    intentGenerateCibil.putExtra(PARAM_BRANCH_ID, branchId);
                    intentGenerateCibil.putExtra(PARAM_USER_ID, userId);
                    intentGenerateCibil.putExtra(PARAM_MODULE_TYPE, MODULE_TYPE_GENERATE_CIBIL);
                    intentGenerateCibil.putExtra(PARAM_CLIENT_ID, CLIENT_ID);
                    startActivity(intentGenerateCibil);
                    break;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            INSERT_LOG("onClick", "Exception : " + ex.getMessage());
        }
    }

    public void getRawDataForAllSections() {
        // TODO:  getRawDataForAllsections

        if (!TextUtils.isEmpty(loanType) && loanType.equalsIgnoreCase(LOAN_NAME_AHL)) {
            // TODO: AHL SALARY MODULETYPE VALIDATION
            getAHLModuleType(loanType, CLIENT_ID);
        } else {
            getRawDataForApplicantSection(Arrays.asList(APPLICANT_TAB_SCREEN_NUMBERS_IL),
                    Arrays.asList(APPLICANT_TAB_SCREEN_NAMES_IL), CLIENT_ID, loanType, AppConstant.MODULE_TYPE_APPLICANT);

            getRawDataForCoApplicantSection(Arrays.asList(CO_APPLICANT_TAB_SCREEN_NUMBERS_IL),
                    Arrays.asList(CO_APPLICANT_TAB_SCREEN_NAMES_IL), CLIENT_ID, loanType, AppConstant.MODULE_TYPE_CO_APPLICANT);

            getRawDataForBusinessSection(Arrays.asList(BUSINESS_TAB_SCREEN_NUMBERS_IL),
                    Arrays.asList(BUSINESS_TAB_SCREEN_NAMES_IL), CLIENT_ID, loanType, MODULE_TYPE_BUSINESS);

            getRawDataForLoanProposalSection(Arrays.asList(LOAN_PROPOSAL_WITH_NOMINEE_MODULE_SCREEN_NUMBERS_IL),
                    Arrays.asList(LOAN_PROPOSAL_WITH_NOMINEE_MODULE_SCREEN_NAMES_IL), CLIENT_ID, loanType, MODULE_TYPE_LOAN_PROPOSAL_WITH_NOMINEE);
        }
        if (!TextUtils.isEmpty(loanType) && loanType.equalsIgnoreCase(LOAN_NAME_PHL)) {
            // TODO: PHL SALARY MODULETYPE VALIDATION
            getAHLModuleType(loanType, CLIENT_ID);
        } else {
            getRawDataForApplicantSection(Arrays.asList(APPLICANT_TAB_SCREEN_NUMBERS_IL),
                    Arrays.asList(APPLICANT_TAB_SCREEN_NAMES_IL), CLIENT_ID, loanType, AppConstant.MODULE_TYPE_APPLICANT);

            getRawDataForCoApplicantSection(Arrays.asList(CO_APPLICANT_TAB_SCREEN_NUMBERS_IL),
                    Arrays.asList(CO_APPLICANT_TAB_SCREEN_NAMES_IL), CLIENT_ID, loanType, AppConstant.MODULE_TYPE_CO_APPLICANT);

            getRawDataForBusinessSection(Arrays.asList(BUSINESS_TAB_SCREEN_NUMBERS_IL),
                    Arrays.asList(BUSINESS_TAB_SCREEN_NAMES_IL), CLIENT_ID, loanType, MODULE_TYPE_BUSINESS);

            getRawDataForLoanProposalSection(Arrays.asList(LOAN_PROPOSAL_WITH_NOMINEE_MODULE_SCREEN_NUMBERS_IL),
                    Arrays.asList(LOAN_PROPOSAL_WITH_NOMINEE_MODULE_SCREEN_NAMES_IL), CLIENT_ID, loanType, MODULE_TYPE_LOAN_PROPOSAL_WITH_NOMINEE);
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

                            ClientDetailsAdapter applicantSectionAdapter = new ClientDetailsAdapter(ClientDetailsActivity.this,
                                    applicantRawDataList, allClientHashMapList, appHelper, screenNameList, screenNoList, moduleType,
                                    ClientDetailsActivity.this);
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
            INSERT_LOG("getRawDataForApplicantSection", "Exception : " + ex.getMessage());
        }
    }

    public void getRawDataForCoApplicantSection(List<String> screenNoList, List<String> screenNameList, String client, String loanType,
                                                String moduleType) {
        try {
            viewModel.getRawDataForCoApplicantSection(screenNameList, screenNoList, client, loanType, moduleType,
                    ClientDetailsActivity.this, ClientDetailsActivity.this);
            if (viewModel.getClientDetailsAdapterList() != null) {
                Observer getRawDataForCoApplicantSectionObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        List<ClientDetailsAdapter> clientDetailsAdapterList = (List<ClientDetailsAdapter>) o;
                        viewModel.getClientDetailsAdapterList().removeObserver(this);


                        if (clientDetailsAdapterList != null && clientDetailsAdapterList.size() > 0) {

                            ivCoApplicant.setVisibility(View.GONE);
                            tvCoApplicant.setVisibility(View.GONE);
                            ibAddCoApplicant.setVisibility(View.VISIBLE);

                            MultipleCoApplicantAdapter multipleCoApplicantAdapter = new MultipleCoApplicantAdapter(ClientDetailsActivity.this,
                                    clientDetailsAdapterList, appHelper);

                            rvCoApplicant.setAdapter(multipleCoApplicantAdapter);
                            multipleCoApplicantAdapter.notifyDataSetChanged();

                        } else {
                            ivCoApplicant.setVisibility(View.VISIBLE);
                            tvCoApplicant.setVisibility(View.VISIBLE);
                            ibAddCoApplicant.setVisibility(View.GONE);
                        }

                    }
                };
                viewModel.getClientDetailsAdapterList().observe(this, getRawDataForCoApplicantSectionObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            INSERT_LOG("getRawDataForCoApplicantSection", "Exception : " + ex.getMessage());
        }
    }

    public void getRawDataForBusinessSection(List<String> screenNoList, List<String> screenNameList, String client, String loanType,
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

                            ivBusiness.setVisibility(View.GONE);
                            tvBusiness.setVisibility(View.GONE);

                            ClientDetailsAdapter applicantSectionAdapter = new ClientDetailsAdapter(ClientDetailsActivity.this,
                                    applicantRawDataList, allClientHashMapList, appHelper, screenNameList, screenNoList, moduleType,
                                    ClientDetailsActivity.this);

                            rvBusiness.setAdapter(applicantSectionAdapter);
                            applicantSectionAdapter.notifyDataSetChanged();

                        } else {
                            ivBusiness.setVisibility(View.VISIBLE);
                            tvBusiness.setVisibility(View.VISIBLE);
                        }

                    }
                };
                viewModel.getRawTableLiveData().observe(this, getRawDataForAllClientObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            INSERT_LOG("getRawDataForBusinessSection", "Exception : " + ex.getMessage());
        }
    }

    public void getRawDataForSalarySection(List<String> screenNoList, List<String> screenNameList, String client, String loanType,
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

                            ivSalary.setVisibility(View.GONE);
                            tvSalary.setVisibility(View.GONE);

                            ClientDetailsAdapter applicantSectionAdapter = new ClientDetailsAdapter(ClientDetailsActivity.this,
                                    applicantRawDataList, allClientHashMapList, appHelper, screenNameList, screenNoList, moduleType,
                                    ClientDetailsActivity.this);

                            rvSalary.setAdapter(applicantSectionAdapter);
                            applicantSectionAdapter.notifyDataSetChanged();

                        } else {
                            ivSalary.setVisibility(View.VISIBLE);
                            tvSalary.setVisibility(View.VISIBLE);
                        }

                    }
                };
                viewModel.getRawTableLiveData().observe(this, getRawDataForAllClientObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            INSERT_LOG("getRawDataForBusinessSection", "Exception : " + ex.getMessage());
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

                            ClientDetailsAdapter applicantSectionAdapter = new ClientDetailsAdapter(ClientDetailsActivity.this,
                                    applicantRawDataList, allClientHashMapList, appHelper, screenNameList, screenNoList, moduleType,
                                    ClientDetailsActivity.this);

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
            INSERT_LOG("getRawDataForLoanProposalSection", "Exception : " + ex.getMessage());
        }
    }

    public void getRawDataForDocumentSection(List<String> screenNoList, List<String> screenNameList, String client, String loanType,
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

                            ivDocument.setVisibility(View.GONE);
                            tvDocuments.setVisibility(View.GONE);
                            ClientDetailsAdapter applicantSectionAdapter = new ClientDetailsAdapter(ClientDetailsActivity.this,
                                    applicantRawDataList, allClientHashMapList, appHelper, screenNameList, screenNoList, moduleType,
                                    ClientDetailsActivity.this);
                            rvDocument.setAdapter(applicantSectionAdapter);
                            applicantSectionAdapter.notifyDataSetChanged();

                        } else {
                            ivDocument.setVisibility(View.VISIBLE);
                            tvDocuments.setVisibility(View.VISIBLE);
                        }

                    }
                };
                viewModel.getRawTableLiveData().observe(this, getRawDataForAllClientObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            INSERT_LOG("getRawDataForDocumentSection", "Exception : " + ex.getMessage());
        }
    }


    public void getNoOfCoApplicants(String screenName, String clientId, String userId,
                                    String loanType, String fieldTag) {
        try {
            viewModel.getNoOfCoApplicants(screenName, clientId, userId, loanType, fieldTag);
            if (viewModel.getNumberOfCoApplicantLiveData() != null) {
                Observer getNoOfCoApplicantsObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        NumberOfCoApplicant numberOfCoApplicantObj = (NumberOfCoApplicant) o;
                        viewModel.getNumberOfCoApplicantLiveData().removeObserver(this);


                        if (numberOfCoApplicantObj != null && numberOfCoApplicantObj.getRemainingCoApplicant() > 0
                                && numberOfCoApplicantObj.getNextCoApplicant() > 0) {

                            appHelper.getDialogHelper().getConfirmationDialog().showTwoButtons(
                                    "Do you want to add another co applicant ? ",
                                    new ConfirmationDialog.ActionCallback() {
                                        @Override
                                        public void onAction() {
                                            Intent ibCoApplicant = new Intent(ClientDetailsActivity.this, BaseActivity.class);
                                            ibCoApplicant.putExtra(PARAM_LOAN_TYPE, loanType);
                                            ibCoApplicant.putExtra(PARAM_USER_NAME, userName);
                                            ibCoApplicant.putExtra(PARAM_BRANCH_ID, branchId);
                                            ibCoApplicant.putExtra(PARAM_USER_ID, userId);
                                            ibCoApplicant.putExtra(PARAM_CLIENT_ID, CLIENT_ID);
                                            ibCoApplicant.putExtra(PARAM_MODULE_TYPE, MODULE_TYPE_CO_APPLICANT + numberOfCoApplicantObj.getNextCoApplicant());
                                            startActivity(ibCoApplicant);
                                        }
                                    });
                        } else {

                            if (numberOfCoApplicantObj != null) {
                                INSERT_LOG("getNoOfCoApplicants", "getRemainingCoApplicant ==> " + numberOfCoApplicantObj.getRemainingCoApplicant());
                                INSERT_LOG("getNoOfCoApplicants", "getNextCoApplicant ==> " + numberOfCoApplicantObj.getNextCoApplicant());
                            }
                            appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT,
                                    "Maximum number of Co Applicants are already added ", new ConfirmationDialog.ActionCallback() {
                                        @Override
                                        public void onAction() {
                                            ibAddCoApplicant.setVisibility(View.GONE);
                                        }
                                    });
                        }


                    }
                };
                viewModel.getNumberOfCoApplicantLiveData().observe(this, getNoOfCoApplicantsObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            INSERT_LOG("getNoOfCoApplicants", "Exception : " + ex.getMessage());
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    public void callBackForScreen(String moduleType, String screenNameToCall) {
        try {
            Intent intentDocument = new Intent(this, BaseActivity.class);
            intentDocument.putExtra(PARAM_LOAN_TYPE, loanType);
            intentDocument.putExtra(PARAM_USER_NAME, userName);
            intentDocument.putExtra(PARAM_BRANCH_ID, branchId);
            intentDocument.putExtra(PARAM_USER_ID, userId);
            intentDocument.putExtra(PARAM_MODULE_TYPE, moduleType);
            intentDocument.putExtra(PARAM_CLIENT_ID, CLIENT_ID);
            startActivity(intentDocument);
        } catch (Exception ex) {
            ex.printStackTrace();
            INSERT_LOG("callBackForScreen", "Exception : " + ex.getMessage());
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
                if (!folder.exists()) {

                    viewModel.getDocumentRawData(CLIENT_ID, userId,loanType,SERVICE_CONNECTION_STRING_AUDIT);
                    if (viewModel.getDocumentUploadRawDataResponseList() != null) {
                        Observer observer = new Observer() {
                            @Override
                            public void onChanged(Object o) {
                                appHelper.getDialogHelper().getLoadingDialog().closeDialog();
                                List<DocumentUploadRawdataResponseDTO> rawdataResponseDTOList = (List<DocumentUploadRawdataResponseDTO>) o;
                                viewModel.getDocumentUploadRawDataResponseList().removeObserver(this);

                                if (rawdataResponseDTOList != null && rawdataResponseDTOList.size() > 0) {
                                    // TODO: DOWNLOAD DOCUMENTS
                                    downloadDocuments(rawdataResponseDTOList, CLIENT_ID, moduleType);
                                }
                            }
                        };
                        viewModel.getDocumentUploadRawDataResponseList().observe(this, observer);
                    }

                } else {
                    appHelper.getDialogHelper().getLoadingDialog().closeDialog();
                }
            } else {
                appHelper.getDialogHelper().getLoadingDialog().closeDialog();
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            appHelper.getDialogHelper().getLoadingDialog().closeDialog();
            INSERT_LOG("getDocumentRawData", "Exception : " + ex.getMessage());
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

                viewModel.getRawDataFromServerForSingleCustomerApplication(CLIENT_ID, userId, loanType, productId,SERVICE_CONNECTION_STRING_AUDIT);
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
                                // TODO:  getRawDataForAllsections
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

    private void getAHLModuleType(String loanType, String clientId) {
        try {
            // TODO: AHL SALARY MODULETYPE VALIDATION
            viewModel.getAHLModuleType(loanType, clientId);
            if (viewModel.getStringLiveData() != null) {
                Observer observer = new Observer() {
                    @Override
                    public void onChanged(Object o) {
                        String moduleType = (String) o;
                        viewModel.getStringLiveData().removeObserver(this);

                        getRawDataForApplicantSection(Arrays.asList(APPLICANT_TAB_SCREEN_NUMBERS_IL),
                                Arrays.asList(APPLICANT_TAB_SCREEN_NAMES_IL), CLIENT_ID, loanType, AppConstant.MODULE_TYPE_APPLICANT);

                        getRawDataForCoApplicantSection(Arrays.asList(CO_APPLICANT_TAB_SCREEN_NUMBERS_IL),
                                Arrays.asList(CO_APPLICANT_TAB_SCREEN_NAMES_IL), CLIENT_ID, loanType, AppConstant.MODULE_TYPE_CO_APPLICANT);

//                        getRawDataForBusinessSection(Arrays.asList(BUSINESS_TAB_SCREEN_NUMBERS_IL),
//                                Arrays.asList(BUSINESS_TAB_SCREEN_NAMES_IL), CLIENT_ID, loanType, MODULE_TYPE_BUSINESS);

                        getRawDataForLoanProposalSection(Arrays.asList(LOAN_PROPOSAL_WITH_NOMINEE_MODULE_SCREEN_NUMBERS_IL),
                                Arrays.asList(LOAN_PROPOSAL_WITH_NOMINEE_MODULE_SCREEN_NAMES_IL), CLIENT_ID, loanType, MODULE_TYPE_LOAN_PROPOSAL_WITH_NOMINEE);

                        if (!TextUtils.isEmpty(moduleType) && moduleType.equalsIgnoreCase(MODULE_TYPE_BUSINESS)) {
                            llBusiness.setVisibility(View.VISIBLE);
                            llSalary.setVisibility(View.GONE);

                            getRawDataForBusinessSection(Arrays.asList(BUSINESS_TAB_SCREEN_NUMBERS_IL),
                                    Arrays.asList(BUSINESS_TAB_SCREEN_NAMES_IL), CLIENT_ID, loanType, MODULE_TYPE_BUSINESS);

                        } else if (!TextUtils.isEmpty(moduleType) && moduleType.equalsIgnoreCase(MODULE_TYPE_SALARY)) {
                            llBusiness.setVisibility(View.GONE);
                            llSalary.setVisibility(View.VISIBLE);

                            getRawDataForSalarySection(Arrays.asList(SALARY_TAB_SCREEN_NUMBERS_AHL),
                                    Arrays.asList(SALARY_TAB_SCREEN_NAMES_AHL), CLIENT_ID, loanType, MODULE_TYPE_SALARY);

                        }
                        else if (!TextUtils.isEmpty(moduleType) && moduleType.equalsIgnoreCase(MODULE_TYPE_SALARY)) {
                            llBusiness.setVisibility(View.GONE);
                            llSalary.setVisibility(View.VISIBLE);

                            getRawDataForSalarySection(Arrays.asList(SALARY_TAB_SCREEN_NUMBERS_PHL),
                                    Arrays.asList(SALARY_TAB_SCREEN_NAMES_PHL), CLIENT_ID, loanType, MODULE_TYPE_SALARY);

                        }
                    }
                };
                viewModel.getStringLiveData().observe(this, observer);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            INSERT_LOG("getModuleType", "Exception : " + ex.getMessage());
        }
    }
}
