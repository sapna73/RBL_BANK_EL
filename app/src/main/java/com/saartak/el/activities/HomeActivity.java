package com.saartak.el.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.bfil.uilibrary.dialogs.ConfirmationDialog;
import com.saartak.el.BuildConfig;
import com.saartak.el.R;
import com.saartak.el.database.entity.ApplicationStatusTable;
import com.saartak.el.database.entity.DocumentMasterTable;
import com.saartak.el.database.entity.PlannerTable;
import com.saartak.el.view_models.DynamicUIViewModel;

import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

import static com.saartak.el.constants.AppConstant.DATE_FORMAT_DD_MM_YYYY2;
import static com.saartak.el.constants.AppConstant.DATE_FORMAT_YYYY_MM_DD;
import static com.saartak.el.constants.AppConstant.LOAN_NAME_AHL;
import static com.saartak.el.constants.AppConstant.LOAN_NAME_INDIVIDUAL;
import static com.saartak.el.constants.AppConstant.LOAN_NAME_JLG;
import static com.saartak.el.constants.AppConstant.LOAN_NAME_MSME;
import static com.saartak.el.constants.AppConstant.LOAN_NAME_PHL;
import static com.saartak.el.constants.AppConstant.LOAN_NAME_EL;
import static com.saartak.el.constants.AppConstant.MODULE_TYPE_ADD_OR_REMOVE_MEMBER;
import static com.saartak.el.constants.AppConstant.MODULE_TYPE_CENTER_CREATION;
import static com.saartak.el.constants.AppConstant.MODULE_TYPE_CGT;
import static com.saartak.el.constants.AppConstant.MODULE_TYPE_DROP_OUT_CUSTOMER;
import static com.saartak.el.constants.AppConstant.MODULE_TYPE_ELIGIBILITY;
import static com.saartak.el.constants.AppConstant.MODULE_TYPE_COLD_CALLING;
import static com.saartak.el.constants.AppConstant.MODULE_TYPE_GRT;
import static com.saartak.el.constants.AppConstant.MODULE_TYPE_LOAN_APPLICATION;
import static com.saartak.el.constants.AppConstant.MODULE_TYPE_TARGET_DETAILS;
import static com.saartak.el.constants.AppConstant.MODULE_TYPE_VILLAGE_SURVEY;
import static com.saartak.el.constants.AppConstant.PARAM_BRANCH_NAME;
import static com.saartak.el.constants.AppConstant.PARAM_LOAN_TYPE;
import static com.saartak.el.constants.AppConstant.PARAM_BRANCH_GST_CODE;
import static com.saartak.el.constants.AppConstant.PARAM_MODULE_TYPE;
import static com.saartak.el.constants.AppConstant.MODULE_TYPE_LEAD;
import static com.saartak.el.constants.AppConstant.PARAM_BRANCH_ID;
import static com.saartak.el.constants.AppConstant.PARAM_PRODUCT_ID;
import static com.saartak.el.constants.AppConstant.PARAM_ROLE_NAME;
import static com.saartak.el.constants.AppConstant.PARAM_USER_ID;
import static com.saartak.el.constants.AppConstant.PARAM_USER_NAME;
import static com.saartak.el.constants.AppConstant.PARAM_WORKFLOW_ID;
import static com.saartak.el.constants.AppConstant.PROJECT_ID_EL;
import static com.saartak.el.constants.AppConstant.WORKFLOW_ID_AHL;
import static com.saartak.el.constants.AppConstant.WORKFLOW_ID_IL;
import static com.saartak.el.constants.AppConstant.WORKFLOW_ID_MSME;
import static com.saartak.el.constants.AppConstant.WORKFLOW_ID_PHL;
import static com.saartak.el.constants.AppConstant.WORKFLOW_ID_EL;
import static com.saartak.el.dynamicui.constants.ParametersConstant.PLANNER_PURPOSE_APPLICATION;
import static com.saartak.el.dynamicui.constants.ParametersConstant.PLANNER_PURPOSE_COLD_CALLING;
import static com.saartak.el.dynamicui.constants.ParametersConstant.PLANNER_PURPOSE_LEAD_GENERATION;
import static com.saartak.el.dynamicui.constants.ParametersConstant.ROLE_NAME_BCM;

public class HomeActivity extends LOSBaseActivity implements View.OnClickListener {
    private static final String TAG = HomeActivity.class.getCanonicalName();
    LinearLayout llColdCalling,llLead, llSalesTool, llApplication, ll_pricing, llPD, llVillageSurvey, llCenterCreation, llTargetDetails,llLoanApplication,
            llEligibility, llCGT, llAddorRemove, llDropOutCustomer, llGRT,llReminder;
    TextView tvLoanType;
    CardView cv_cold_calling, cv_lead, cv_pricing, cv_sales_tool, cv_application, cv_pd, cv_village_survey, cv_center_creation, cv_targe_details, cv_loan_application,
            cv_eligibility,cv_add_or_remove,cv_drop_out_customer, cv_cgt, cv_grt,cv_reminder,cv_Pricing;
    LinearLayout ll_workflow_history, ll_collection, ll_planner;
    CardView cv_workflow_history, cv_collection, cv_planner;
    TextView tvAppVersion, tvCurrentDate, tvUserName;
    String rolename = "LOAN OFFICER";

    @Inject
    public ViewModelProvider.Factory viewModelFactory;
    public DynamicUIViewModel viewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home_static);

        SharedPreferences mPrefs = getSharedPreferences("ROLENAMEVALUE",0);
        rolename = mPrefs.getString("rolename", "");

        tvUserName = (TextView)findViewById(R.id.tv_user_name);
        tvCurrentDate = (TextView)findViewById(R.id.tv_currentdate);
        tvAppVersion = (TextView)findViewById(R.id.tv_app_version);

        llColdCalling = (LinearLayout) findViewById(R.id.ll_cold_calling);
        llLead = (LinearLayout) findViewById(R.id.ll_lead);
        llLead.setOnClickListener(this);
        llSalesTool = (LinearLayout) findViewById(R.id.ll_sales_tool);
        llSalesTool.setOnClickListener(this);

        llReminder = (LinearLayout) findViewById(R.id.ll_reminder);
        llApplication = (LinearLayout) findViewById(R.id.ll_application);
        ll_pricing = (LinearLayout) findViewById(R.id.ll_pricing);
        cv_Pricing = (CardView) findViewById(R.id.cv_Pricing);
        ll_collection = (LinearLayout) findViewById(R.id.ll_collection);
        ll_planner = (LinearLayout) findViewById(R.id.ll_planner);

        ll_workflow_history = (LinearLayout) findViewById(R.id.ll_workflow_history);
        llPD = (LinearLayout) findViewById(R.id.ll_pd);
        llVillageSurvey = (LinearLayout) findViewById(R.id.ll_village_survey);
        llCenterCreation = (LinearLayout) findViewById(R.id.ll_center_creation);
        llTargetDetails = (LinearLayout) findViewById(R.id.ll_target_details);
        llLoanApplication = (LinearLayout) findViewById(R.id.ll_loan_application);
        llEligibility = (LinearLayout) findViewById(R.id.ll_eligibility);
        llCGT = (LinearLayout) findViewById(R.id.ll_cgt);
        llAddorRemove = (LinearLayout) findViewById(R.id.ll_add_or_remove);
        llDropOutCustomer = (LinearLayout) findViewById(R.id.ll_drop_out_customer);
        llGRT = (LinearLayout) findViewById(R.id.ll_grt);
        cv_cold_calling = (CardView) findViewById(R.id.cv_cold_calling);
        cv_lead = (CardView) findViewById(R.id.cv_lead);
        cv_pricing = (CardView) findViewById(R.id.cv_Pricing);
        cv_sales_tool = (CardView) findViewById(R.id.cv_sales_tool);
        cv_reminder = (CardView) findViewById(R.id.cv_reminder);
        cv_application = (CardView) findViewById(R.id.cv_application);
        cv_collection = (CardView) findViewById(R.id.cv_collection);
        cv_planner= (CardView) findViewById(R.id.cv_planner);

        cv_workflow_history = (CardView) findViewById(R.id.cv_workflow_history);
        cv_pd = (CardView) findViewById(R.id.cv_pd);
        cv_village_survey = (CardView) findViewById(R.id.cv_village_survey);
        cv_center_creation = (CardView) findViewById(R.id.cv_center_creation);
        cv_targe_details = (CardView) findViewById(R.id.cv_target_details);
        cv_loan_application = (CardView) findViewById(R.id.cv_loan_application);
        cv_eligibility = (CardView) findViewById(R.id.cv_eligibility);
        cv_cgt = (CardView) findViewById(R.id.cv_cgt);
        cv_add_or_remove = (CardView) findViewById(R.id.cv_add_or_remove);
        cv_drop_out_customer = (CardView) findViewById(R.id.cv_drop_out_customer);
        cv_grt = (CardView) findViewById(R.id.cv_grt);
        ll_planner.setOnClickListener(this);
        llColdCalling.setOnClickListener(this);
        llReminder.setOnClickListener(this);
        llApplication.setOnClickListener(this);
        ll_collection.setOnClickListener(this);
        ll_workflow_history.setOnClickListener(this);
        llPD.setOnClickListener(this);
        llVillageSurvey.setOnClickListener(this);
        llCenterCreation.setOnClickListener(this);
        llTargetDetails.setOnClickListener(this);
        llLoanApplication.setOnClickListener(this);
        llEligibility.setOnClickListener(this);
        llCGT.setOnClickListener(this);
        llAddorRemove.setOnClickListener(this);
        llDropOutCustomer.setOnClickListener(this);
        llGRT.setOnClickListener(this);

        tvLoanType = (TextView) findViewById(R.id.tv_product_name);

        userName = getIntent().getStringExtra(PARAM_USER_NAME);
        userId = getIntent().getStringExtra(PARAM_USER_ID);
        branchId = getIntent().getStringExtra(PARAM_BRANCH_ID);
        branchName = getIntent().getStringExtra(PARAM_BRANCH_NAME);
        branchGSTcode = getIntent().getStringExtra(PARAM_BRANCH_GST_CODE);
        productId = getIntent().getStringExtra(PARAM_PRODUCT_ID);
        workFlowId = getIntent().getStringExtra(PARAM_WORKFLOW_ID);
        roleName = rolename;
        loanType = getIntent().getStringExtra(PARAM_LOAN_TYPE);

        ll_pricing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rolename.equalsIgnoreCase("LOAN OFFICER")) {
                    Intent pricing = new Intent(HomeActivity.this, LeadToPricingActivity.class);
                    pricing.putExtra(PARAM_LOAN_TYPE, loanType);
                    pricing.putExtra(PARAM_USER_NAME, userName);
                    pricing.putExtra(PARAM_BRANCH_ID, branchId);
                    pricing.putExtra(PARAM_ROLE_NAME, roleName);
                    pricing.putExtra(PARAM_BRANCH_GST_CODE, branchGSTcode);
                    pricing.putExtra(PARAM_USER_ID, userId);
                    pricing.putExtra(PARAM_MODULE_TYPE, MODULE_TYPE_LEAD);
                    pricing.putExtra(PARAM_PRODUCT_ID, productId);
                    pricing.putExtra(PARAM_WORKFLOW_ID, workFlowId);
                    startActivity(pricing);
                }else {
                    Intent pricing = new Intent(HomeActivity.this, LeadToOtherThenLOPricingActivity.class);
                    pricing.putExtra(PARAM_LOAN_TYPE, loanType);
                    pricing.putExtra(PARAM_USER_NAME, userName);
                    pricing.putExtra(PARAM_BRANCH_ID, branchId);
                    pricing.putExtra(PARAM_ROLE_NAME, roleName);
                    pricing.putExtra(PARAM_BRANCH_GST_CODE, branchGSTcode);
                    pricing.putExtra(PARAM_USER_ID, userId);
                    pricing.putExtra(PARAM_MODULE_TYPE, MODULE_TYPE_LEAD);
                    pricing.putExtra(PARAM_PRODUCT_ID, productId);
                    pricing.putExtra(PARAM_WORKFLOW_ID, workFlowId);
                    startActivity(pricing);
                }
            }
        });

        cv_Pricing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (roleName.equalsIgnoreCase("LOAN OFFICER")) {
                    Intent pricing = new Intent(HomeActivity.this, LeadToPricingActivity.class);
                    pricing.putExtra(PARAM_LOAN_TYPE, loanType);
                    pricing.putExtra(PARAM_USER_NAME, userName);
                    pricing.putExtra(PARAM_BRANCH_ID, branchId);
                    pricing.putExtra(PARAM_ROLE_NAME, roleName);
                    pricing.putExtra(PARAM_BRANCH_GST_CODE, branchGSTcode);
                    pricing.putExtra(PARAM_USER_ID, userId);
                    pricing.putExtra(PARAM_MODULE_TYPE, MODULE_TYPE_LEAD);
                    pricing.putExtra(PARAM_PRODUCT_ID, productId);
                    pricing.putExtra(PARAM_WORKFLOW_ID, workFlowId);
                    startActivity(pricing);
                }else {
                    Intent pricing = new Intent(HomeActivity.this, LeadToOtherThenLOPricingActivity.class);
                    pricing.putExtra(PARAM_LOAN_TYPE, loanType);
                    pricing.putExtra(PARAM_USER_NAME, userName);
                    pricing.putExtra(PARAM_BRANCH_ID, branchId);
                    pricing.putExtra(PARAM_ROLE_NAME, roleName);
                    pricing.putExtra(PARAM_BRANCH_GST_CODE, branchGSTcode);
                    pricing.putExtra(PARAM_USER_ID, userId);
                    pricing.putExtra(PARAM_MODULE_TYPE, MODULE_TYPE_LEAD);
                    pricing.putExtra(PARAM_PRODUCT_ID, productId);
                    pricing.putExtra(PARAM_WORKFLOW_ID, workFlowId);
                    startActivity(pricing);
                }
            }
        });

        if ( ! TextUtils.isEmpty(roleName) && roleName.equalsIgnoreCase(ROLE_NAME_BCM)) {
            if (!TextUtils.isEmpty(loanType) && loanType.equalsIgnoreCase(LOAN_NAME_JLG)) {
                cv_grt.setVisibility(View.VISIBLE);
                cv_pd.setVisibility(View.GONE);
            }
            else {
                cv_pd.setVisibility(View.VISIBLE);
                cv_grt.setVisibility(View.GONE);
            }
            cv_collection.setVisibility(View.GONE);
            cv_planner.setVisibility(View.GONE);
            cv_cold_calling.setVisibility(View.GONE);
            cv_reminder.setVisibility(View.GONE);
            cv_lead.setVisibility(View.GONE);
            cv_sales_tool.setVisibility(View.GONE);
            cv_application.setVisibility(View.GONE);
            cv_workflow_history.setVisibility(View.GONE);
            cv_village_survey.setVisibility(View.GONE);
            cv_center_creation.setVisibility(View.GONE);
            cv_targe_details.setVisibility(View.GONE);
            cv_loan_application.setVisibility(View.GONE);
            cv_eligibility.setVisibility(View.GONE);
            cv_cgt.setVisibility(View.GONE);
            cv_add_or_remove.setVisibility(View.GONE);
            cv_drop_out_customer.setVisibility(View.GONE);
        } else {
            if (!TextUtils.isEmpty(loanType) && loanType.equalsIgnoreCase(LOAN_NAME_JLG)) {
                cv_pd.setVisibility(View.GONE);
                cv_planner.setVisibility(View.GONE);
                cv_cold_calling.setVisibility(View.GONE);
                cv_collection.setVisibility(View.GONE);
                cv_reminder.setVisibility(View.GONE);
                cv_lead.setVisibility(View.GONE);
                cv_sales_tool.setVisibility(View.GONE);
                cv_application.setVisibility(View.GONE);
                cv_workflow_history.setVisibility(View.GONE);
                cv_village_survey.setVisibility(View.VISIBLE);
                cv_center_creation.setVisibility(View.VISIBLE);
                cv_targe_details.setVisibility(View.VISIBLE);
                cv_loan_application.setVisibility(View.VISIBLE);
                cv_eligibility.setVisibility(View.VISIBLE);
                cv_cgt.setVisibility(View.VISIBLE);
                cv_add_or_remove.setVisibility(View.GONE); // TODO: Need to change based on requirement
                cv_drop_out_customer.setVisibility(View.VISIBLE);
                cv_grt.setVisibility(View.GONE);
            } else {
                if (!TextUtils.isEmpty(loanType) && loanType.equalsIgnoreCase(LOAN_NAME_MSME)) {
                    cv_planner.setVisibility(View.VISIBLE);
                    cv_cold_calling.setVisibility(View.VISIBLE);
                    cv_sales_tool.setVisibility(View.VISIBLE);

                    // TODO: Visibility GONE for production release v3.0.1
                    cv_collection.setVisibility(View.GONE);

                }
                // TODO: ahl loan type validation
                else if (!TextUtils.isEmpty(loanType) && loanType.equalsIgnoreCase(LOAN_NAME_AHL)) {
                    cv_planner.setVisibility(View.VISIBLE);
                    cv_cold_calling.setVisibility(View.VISIBLE);
                    cv_sales_tool.setVisibility(View.GONE);
                    // TODO: Visibility GONE for production release v3.0.1
                    cv_collection.setVisibility(View.GONE);

                }
                // TODO: phl loan type validation
                else if (!TextUtils.isEmpty(loanType) && loanType.equalsIgnoreCase(LOAN_NAME_PHL)) {
                   /* cv_planner.setVisibility(View.VISIBLE);
                    cv_cold_calling.setVisibility(View.VISIBLE);
                    cv_sales_tool.setVisibility(View.GONE);
                    // TODO: Visibility GONE for production release v3.0.1
                    cv_collection.setVisibility(View.GONE);*/
                    cv_planner.setVisibility(View.GONE);
                    cv_cold_calling.setVisibility(View.GONE);
                    cv_sales_tool.setVisibility(View.GONE);
                    // TODO: Visibility GONE for production release v3.0.1
                    cv_collection.setVisibility(View.GONE);

                }
                else {
                    cv_planner.setVisibility(View.GONE);
                    cv_cold_calling.setVisibility(View.GONE);
                    cv_sales_tool.setVisibility(View.GONE);
                    cv_reminder.setVisibility(View.GONE);
                    cv_workflow_history.setVisibility(View.GONE);
                    // TODO: Visibility GONE for production release v3.0.1
                    cv_collection.setVisibility(View.GONE);
                }
                cv_pd.setVisibility(View.GONE);
                cv_reminder.setVisibility(View.GONE);
                cv_lead.setVisibility(View.VISIBLE);
                cv_pricing.setVisibility(View.GONE);
                cv_application.setVisibility(View.VISIBLE);
                cv_workflow_history.setVisibility(View.GONE);
                cv_village_survey.setVisibility(View.GONE);
                cv_center_creation.setVisibility(View.GONE);
                cv_targe_details.setVisibility(View.GONE);
                cv_loan_application.setVisibility(View.GONE);
                cv_eligibility.setVisibility(View.GONE);
                cv_cgt.setVisibility(View.GONE);
                cv_add_or_remove.setVisibility(View.GONE);
                cv_drop_out_customer.setVisibility(View.GONE);
                cv_grt.setVisibility(View.GONE);
            }
        }


        if (!TextUtils.isEmpty(loanType)) {
            tvLoanType.setText(loanType);
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
        // TODO: Getting Document Master From Server
        getDocumentMasterFromServer();
    }

    @Override
    public void onClick(View v) {
        try {
            switch (v.getId()) {
                case R.id.ll_planner:
                    Intent intentPlanner = new Intent(this, PlannerSummaryActivity.class);
                    intentPlanner.putExtra(PARAM_LOAN_TYPE, loanType);
                    intentPlanner.putExtra(PARAM_USER_NAME, userName);
                    intentPlanner.putExtra(PARAM_BRANCH_ID, branchId);
                    intentPlanner.putExtra(PARAM_BRANCH_NAME, branchName);
                    intentPlanner.putExtra(PARAM_USER_ID, userId);
                    intentPlanner.putExtra(PARAM_BRANCH_GST_CODE, branchGSTcode);
                    intentPlanner.putExtra(PARAM_MODULE_TYPE, MODULE_TYPE_COLD_CALLING);
                    intentPlanner.putExtra(PARAM_PRODUCT_ID, productId);
                    intentPlanner.putExtra(PARAM_WORKFLOW_ID, workFlowId);
                    startActivity(intentPlanner);
                    break;
                case R.id.ll_cold_calling:
//                    checkPlannerStatus(userId, PLANNER_PURPOSE_COLD_CALLING);

                    Intent intentColdCalling = new Intent(HomeActivity.this, ColdCallingDetailsActivity.class);
                    intentColdCalling.putExtra(PARAM_LOAN_TYPE, loanType);
                    intentColdCalling.putExtra(PARAM_USER_NAME, userName);
                    intentColdCalling.putExtra(PARAM_BRANCH_ID, branchId);
                    intentColdCalling.putExtra(PARAM_USER_ID, userId);
                    intentColdCalling.putExtra(PARAM_BRANCH_GST_CODE, branchGSTcode);
                    intentColdCalling.putExtra(PARAM_MODULE_TYPE, MODULE_TYPE_COLD_CALLING);
                    intentColdCalling.putExtra(PARAM_PRODUCT_ID, productId);
                    intentColdCalling.putExtra(PARAM_WORKFLOW_ID, workFlowId);
                    startActivity(intentColdCalling);

                    break;

                case R.id.ll_sales_tool:
                    Intent intentSalesTool = new Intent(this, SalesToolSummaryActivity.class);
                    intentSalesTool.putExtra(PARAM_LOAN_TYPE, loanType);
                    intentSalesTool.putExtra(PARAM_USER_NAME, userName);
                    intentSalesTool.putExtra(PARAM_BRANCH_ID, branchId);
                    intentSalesTool.putExtra(PARAM_USER_ID, userId);
                    intentSalesTool.putExtra(PARAM_BRANCH_GST_CODE, branchGSTcode);
                    intentSalesTool.putExtra(PARAM_MODULE_TYPE, MODULE_TYPE_COLD_CALLING);
                    intentSalesTool.putExtra(PARAM_PRODUCT_ID, productId);
                    intentSalesTool.putExtra(PARAM_WORKFLOW_ID, workFlowId);
                    startActivity(intentSalesTool);
                    break;
                case R.id.ll_reminder:
                    Intent intentReminder = new Intent(this, ReminderActivity.class);
                    intentReminder.putExtra(PARAM_LOAN_TYPE, loanType);
                    intentReminder.putExtra(PARAM_USER_NAME, userName);
                    intentReminder.putExtra(PARAM_BRANCH_ID, branchId);
                    intentReminder.putExtra(PARAM_USER_ID, userId);
                    intentReminder.putExtra(PARAM_BRANCH_GST_CODE, branchGSTcode);
                    intentReminder.putExtra(PARAM_MODULE_TYPE, MODULE_TYPE_COLD_CALLING);
                    intentReminder.putExtra(PARAM_PRODUCT_ID, productId);
                    intentReminder.putExtra(PARAM_WORKFLOW_ID, workFlowId);
                    startActivity(intentReminder);
                    break;
                case R.id.ll_lead:
//                    checkPlannerStatus(userId, PLANNER_PURPOSE_LEAD_GENERATION);

                    // TODO: MSME & AHL LeadDetailsSummaryActivity
                    if (!TextUtils.isEmpty(loanType)) {
                        Intent intentApplication = new Intent(HomeActivity.this, LeadDetailsSummaryActivity.class);
                        intentApplication.putExtra(PARAM_LOAN_TYPE, loanType);
                        intentApplication.putExtra(PARAM_USER_NAME, userName);
                        intentApplication.putExtra(PARAM_BRANCH_ID, branchId);
                        intentApplication.putExtra(PARAM_USER_ID, userId);
                        intentApplication.putExtra(PARAM_BRANCH_GST_CODE, branchGSTcode);
                        intentApplication.putExtra(PARAM_MODULE_TYPE, MODULE_TYPE_LEAD);
                        intentApplication.putExtra(PARAM_PRODUCT_ID, productId);
                        intentApplication.putExtra(PARAM_WORKFLOW_ID, workFlowId);
                        intentApplication.putExtra(PARAM_ROLE_NAME, roleName);
                        startActivity(intentApplication);
                    } else {
                        Intent intentApplication = new Intent(HomeActivity.this, LeadDetailsActivity.class);
                        intentApplication.putExtra(PARAM_LOAN_TYPE, loanType);
                        intentApplication.putExtra(PARAM_USER_NAME, userName);
                        intentApplication.putExtra(PARAM_BRANCH_ID, branchId);
                        intentApplication.putExtra(PARAM_USER_ID, userId);
                        intentApplication.putExtra(PARAM_BRANCH_GST_CODE, branchGSTcode);
                        intentApplication.putExtra(PARAM_MODULE_TYPE, MODULE_TYPE_LEAD);
                        intentApplication.putExtra(PARAM_PRODUCT_ID, productId);
                        intentApplication.putExtra(PARAM_WORKFLOW_ID, workFlowId);
                        intentApplication.putExtra(PARAM_ROLE_NAME, roleName);
                        startActivity(intentApplication);
                    }

                    break;
                case R.id.ll_application:
//                    checkPlannerStatus(userId, PLANNER_PURPOSE_APPLICATION);

                    Intent application = new Intent(HomeActivity.this, LeadToApplicationActivity.class);
                    application.putExtra(PARAM_LOAN_TYPE, loanType);
                    application.putExtra(PARAM_USER_NAME, userName);
                    application.putExtra(PARAM_BRANCH_ID, branchId);
                    application.putExtra(PARAM_BRANCH_GST_CODE, branchGSTcode);
                    application.putExtra(PARAM_USER_ID, userId);
                    application.putExtra(PARAM_MODULE_TYPE, MODULE_TYPE_LEAD);
                    application.putExtra(PARAM_PRODUCT_ID, productId);
                    application.putExtra(PARAM_ROLE_NAME, roleName);
                    application.putExtra(PARAM_WORKFLOW_ID, workFlowId);
                    startActivity(application);

                    break;
                case R.id.ll_collection:
                    Intent collection = new Intent(this, CollectionSummaryActivity.class);
                    collection.putExtra(PARAM_LOAN_TYPE, loanType);
                    collection.putExtra(PARAM_USER_NAME, userName);
                    collection.putExtra(PARAM_BRANCH_ID, branchId);
                    collection.putExtra(PARAM_BRANCH_GST_CODE, branchGSTcode);
                    collection.putExtra(PARAM_USER_ID, userId);
                    collection.putExtra(PARAM_MODULE_TYPE, MODULE_TYPE_LEAD);
                    collection.putExtra(PARAM_PRODUCT_ID, productId);
                    collection.putExtra(PARAM_WORKFLOW_ID, workFlowId);
                    startActivity(collection);

                    break;
                case R.id.ll_workflow_history:

                    Intent workflowistory = new Intent(this, WorkflowHistorySummaryActivity.class);
                    workflowistory.putExtra(PARAM_LOAN_TYPE, loanType);
                    workflowistory.putExtra(PARAM_USER_NAME, userName);
                    workflowistory.putExtra(PARAM_BRANCH_ID, branchId);
                    workflowistory.putExtra(PARAM_BRANCH_GST_CODE, branchGSTcode);
                    workflowistory.putExtra(PARAM_USER_ID, userId);
                    workflowistory.putExtra(PARAM_MODULE_TYPE, MODULE_TYPE_LEAD);
                    workflowistory.putExtra(PARAM_PRODUCT_ID, productId);
                    workflowistory.putExtra(PARAM_WORKFLOW_ID, workFlowId);
                    startActivity(workflowistory);

                    break;
                case R.id.ll_pd:
                    Intent personalDiscussion = new Intent(this, ApplicationToPDActivity.class);
                    personalDiscussion.putExtra(PARAM_LOAN_TYPE, loanType);
                    personalDiscussion.putExtra(PARAM_USER_NAME, userName);
                    personalDiscussion.putExtra(PARAM_BRANCH_ID, branchId);
                    personalDiscussion.putExtra(PARAM_BRANCH_GST_CODE, branchGSTcode);
                    personalDiscussion.putExtra(PARAM_USER_ID, userId);
                    personalDiscussion.putExtra(PARAM_MODULE_TYPE, MODULE_TYPE_LEAD);
                    personalDiscussion.putExtra(PARAM_PRODUCT_ID, productId);
                    personalDiscussion.putExtra(PARAM_WORKFLOW_ID, workFlowId);
                    startActivity(personalDiscussion);
                    break;
                case R.id.ll_target_details:
                    Intent targetDetails = new Intent(this, CentersSummaryActivity.class);
                    targetDetails.putExtra(PARAM_LOAN_TYPE, loanType);
                    targetDetails.putExtra(PARAM_USER_NAME, userName);
                    targetDetails.putExtra(PARAM_BRANCH_ID, branchId);
                    targetDetails.putExtra(PARAM_BRANCH_GST_CODE, branchGSTcode);
                    targetDetails.putExtra(PARAM_USER_ID, userId);
                    targetDetails.putExtra(PARAM_MODULE_TYPE, MODULE_TYPE_TARGET_DETAILS);
                    targetDetails.putExtra(PARAM_PRODUCT_ID, productId);
                    targetDetails.putExtra(PARAM_WORKFLOW_ID, workFlowId);
                    startActivity(targetDetails);
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
                    loanApplication.putExtra(PARAM_WORKFLOW_ID, workFlowId);
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
                    eligibility.putExtra(PARAM_WORKFLOW_ID, workFlowId);
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
                    villageSurvey.putExtra(PARAM_WORKFLOW_ID, workFlowId);
                    startActivity(villageSurvey);
                    break;
                case R.id.ll_center_creation:
                    Intent centerCreation = new Intent(this, CenterCreationActivity.class);
                    centerCreation.putExtra(PARAM_LOAN_TYPE, loanType);
                    centerCreation.putExtra(PARAM_USER_NAME, userName);
                    centerCreation.putExtra(PARAM_BRANCH_ID, branchId);
                    centerCreation.putExtra(PARAM_BRANCH_GST_CODE, branchGSTcode);
                    centerCreation.putExtra(PARAM_USER_ID, userId);
                    centerCreation.putExtra(PARAM_MODULE_TYPE, MODULE_TYPE_CENTER_CREATION);
                    centerCreation.putExtra(PARAM_PRODUCT_ID, productId);
                    centerCreation.putExtra(PARAM_WORKFLOW_ID, workFlowId);
                    startActivity(centerCreation);
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
                    cgt.putExtra(PARAM_WORKFLOW_ID, workFlowId);
                    startActivity(cgt);
                    break;
                case R.id.ll_add_or_remove:
                    Intent addOrRemove = new Intent(this, CentersSummaryActivity.class);
                    addOrRemove.putExtra(PARAM_LOAN_TYPE, loanType);
                    addOrRemove.putExtra(PARAM_USER_NAME, userName);
                    addOrRemove.putExtra(PARAM_BRANCH_ID, branchId);
                    addOrRemove.putExtra(PARAM_BRANCH_GST_CODE, branchGSTcode);
                    addOrRemove.putExtra(PARAM_USER_ID, userId);
                    addOrRemove.putExtra(PARAM_MODULE_TYPE, MODULE_TYPE_ADD_OR_REMOVE_MEMBER);
                    addOrRemove.putExtra(PARAM_PRODUCT_ID, productId);
                    addOrRemove.putExtra(PARAM_WORKFLOW_ID, workFlowId);
                    startActivity(addOrRemove);
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
                    dropOut.putExtra(PARAM_WORKFLOW_ID, workFlowId);
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
                    grt.putExtra(PARAM_WORKFLOW_ID, workFlowId);
                    startActivity(grt);
                    break;

            }
        } catch (Exception ex) {
            ex.printStackTrace();
            INSERT_LOG("onClick", "Exception : " + ex.getMessage());
        }
    }

    private void checkPlannerStatus(String userId, String purpose) {

        try {
            viewModel.getPlannerDataByPurposeAndCurrentDate(userId, purpose);
            if (viewModel.getPlannerTableLiveData() != null) {

                Observer getPlannerDataObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {

                        PlannerTable plannerTable = (PlannerTable) o;
                        viewModel.getPlannerTableLiveData().removeObserver(this);

                        if (plannerTable != null) {
                            if (plannerTable.isTripStart() && !plannerTable.isTripEnd()) {
                                if (purpose.equalsIgnoreCase(PLANNER_PURPOSE_COLD_CALLING)) {
                                    Intent intentColdCalling = new Intent(HomeActivity.this, ColdCallingDetailsActivity.class);
                                    intentColdCalling.putExtra(PARAM_LOAN_TYPE, loanType);
                                    intentColdCalling.putExtra(PARAM_USER_NAME, userName);
                                    intentColdCalling.putExtra(PARAM_BRANCH_ID, branchId);
                                    intentColdCalling.putExtra(PARAM_USER_ID, userId);
                                    intentColdCalling.putExtra(PARAM_BRANCH_GST_CODE, branchGSTcode);
                                    intentColdCalling.putExtra(PARAM_MODULE_TYPE, MODULE_TYPE_COLD_CALLING);
                                    intentColdCalling.putExtra(PARAM_PRODUCT_ID, productId);
                                    intentColdCalling.putExtra(PARAM_WORKFLOW_ID, workFlowId);
                                    startActivity(intentColdCalling);
                                }
                                else if (purpose.equalsIgnoreCase(PLANNER_PURPOSE_LEAD_GENERATION)) {
                                    // TODO: MSME & AHL LeadDetailsSummaryActivity
                                    if (!TextUtils.isEmpty(loanType)) {
                                        Intent intentApplication = new Intent(HomeActivity.this, LeadDetailsSummaryActivity.class);
                                        intentApplication.putExtra(PARAM_LOAN_TYPE, loanType);
                                        intentApplication.putExtra(PARAM_USER_NAME, userName);
                                        intentApplication.putExtra(PARAM_BRANCH_ID, branchId);
                                        intentApplication.putExtra(PARAM_USER_ID, userId);
                                        intentApplication.putExtra(PARAM_BRANCH_GST_CODE, branchGSTcode);
                                        intentApplication.putExtra(PARAM_MODULE_TYPE, MODULE_TYPE_LEAD);
                                        intentApplication.putExtra(PARAM_PRODUCT_ID, productId);
                                        intentApplication.putExtra(PARAM_PRODUCT_ID, productId);
                                        startActivity(intentApplication);
                                    } else {
                                        Intent intentApplication = new Intent(HomeActivity.this, LeadDetailsActivity.class);
                                        intentApplication.putExtra(PARAM_LOAN_TYPE, loanType);
                                        intentApplication.putExtra(PARAM_USER_NAME, userName);
                                        intentApplication.putExtra(PARAM_BRANCH_ID, branchId);
                                        intentApplication.putExtra(PARAM_USER_ID, userId);
                                        intentApplication.putExtra(PARAM_BRANCH_GST_CODE, branchGSTcode);
                                        intentApplication.putExtra(PARAM_MODULE_TYPE, MODULE_TYPE_LEAD);
                                        intentApplication.putExtra(PARAM_WORKFLOW_ID, workFlowId);
                                        startActivity(intentApplication);
                                    }
                                }
//                           else if (purpose.equalsIgnoreCase(PLANNER_PURPOSE_LEAD_GENERATION)) {
//                                    // TODO: MSME & AHL LeadDetailsSummaryActivity
//                                    if (!TextUtils.isEmpty(loanType) && (loanType.equalsIgnoreCase(LOAN_NAME_MSME) || loanType.equalsIgnoreCase(LOAN_NAME_PHL))) {
//                                        Intent intentApplication = new Intent(HomeActivity.this, LeadDetailsSummaryActivity.class);
//                                        intentApplication.putExtra(PARAM_LOAN_TYPE, loanType);
//                                        intentApplication.putExtra(PARAM_USER_NAME, userName);
//                                        intentApplication.putExtra(PARAM_BRANCH_ID, branchId);
//                                        intentApplication.putExtra(PARAM_USER_ID, userId);
//                                        intentApplication.putExtra(PARAM_BRANCH_GST_CODE, branchGSTcode);
//                                        intentApplication.putExtra(PARAM_MODULE_TYPE, MODULE_TYPE_LEAD);
//                                        intentApplication.putExtra(PARAM_PRODUCT_ID, productId);
//                                        intentApplication.putExtra(PARAM_WORKFLOW_ID, workFlowId);
//                                        startActivity(intentApplication);
//                                    } else {
//                                        Intent intentApplication = new Intent(HomeActivity.this, LeadDetailsActivity.class);
//                                        intentApplication.putExtra(PARAM_LOAN_TYPE, loanType);
//                                        intentApplication.putExtra(PARAM_USER_NAME, userName);
//                                        intentApplication.putExtra(PARAM_BRANCH_ID, branchId);
//                                        intentApplication.putExtra(PARAM_USER_ID, userId);
//                                        intentApplication.putExtra(PARAM_BRANCH_GST_CODE, branchGSTcode);
//                                        intentApplication.putExtra(PARAM_MODULE_TYPE, MODULE_TYPE_LEAD);
//                                        intentApplication.putExtra(PARAM_PRODUCT_ID, productId);
//                                        intentApplication.putExtra(PARAM_WORKFLOW_ID, workFlowId);
//                                        startActivity(intentApplication);
//                                    }
//                                }
                                else if (purpose.equalsIgnoreCase(PLANNER_PURPOSE_APPLICATION)) {

                                    Intent application = new Intent(HomeActivity.this, LeadToApplicationActivity.class);
                                    application.putExtra(PARAM_LOAN_TYPE, loanType);
                                    application.putExtra(PARAM_USER_NAME, userName);
                                    application.putExtra(PARAM_BRANCH_ID, branchId);
                                    application.putExtra(PARAM_BRANCH_GST_CODE, branchGSTcode);
                                    application.putExtra(PARAM_USER_ID, userId);
                                    application.putExtra(PARAM_MODULE_TYPE, MODULE_TYPE_LEAD);
                                    application.putExtra(PARAM_PRODUCT_ID, productId);
                                    application.putExtra(PARAM_WORKFLOW_ID, workFlowId);
                                    startActivity(application);

                                }

                            } else {
                                appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, "Please start Trip for " + purpose);
                            }
                        } else {
                            appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, "Please start Trip for " + purpose);

                        }

                    }
                };
                viewModel.getPlannerTableLiveData().observe(this, getPlannerDataObserver);
            } else {
                appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, "Please start Trip for " + purpose);

            }

        } catch (Exception ex) {
            ex.printStackTrace();
            appHelper.getDialogHelper().getLoadingDialog().closeDialog();

            INSERT_LOG("getPlannerDataByPurposeAndCurrentDate", "Exception : " + ex.getMessage());
        }
    }

    private void getDocumentMasterFromServer() {
        try {
            appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();
            viewModel.getDocumentMasterFromServer(productId, PROJECT_ID_EL);
            if (viewModel.getDocumentMasterListLiveData() != null) {
                Observer getDocumentUploadMasterObserver = new Observer() {
                    @Override
                    public void onChanged(Object o) {
                        appHelper.getDialogHelper().getLoadingDialog().closeDialog();
                        List<DocumentMasterTable> documentMasterTableList = (List<DocumentMasterTable>) o;
                        viewModel.getDocumentMasterListLiveData().removeObserver(this);

                        if (documentMasterTableList != null && documentMasterTableList.size() > 0) {
                            // TODO: GET APPLICATION STATUS
                            getApplicationStatusFromServer("", appHelper.getCurrentDate(DATE_FORMAT_YYYY_MM_DD));
                        } else {
                            /*appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ERROR,
                                    ERROR_MESSAGE_UNABLE_TO_GET_DOCUMENT_MASTER, new ConfirmationDialog.ActionCallback() {
                                        @Override
                                        public void onAction() {
                                            finish();
                                        }
                                    });*/
                        }
                    }
                };
                viewModel.getDocumentMasterListLiveData().observe(this, getDocumentUploadMasterObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            appHelper.getDialogHelper().getLoadingDialog().closeDialog();
            INSERT_LOG("getDocumentMasterFromServer", "Exception : " + ex.getMessage());
        }
    }

    public void getApplicationStatusFromServer(String fromDate, String todate) {
        try {
            appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();

            String workflowId = productId;
            if (loanType.equalsIgnoreCase(LOAN_NAME_INDIVIDUAL)) {
                workflowId = WORKFLOW_ID_IL;
            }else if (loanType.equalsIgnoreCase(LOAN_NAME_MSME)) {
                workflowId = WORKFLOW_ID_MSME;
            }
            else if (loanType.equalsIgnoreCase(LOAN_NAME_PHL)) {
                workflowId = WORKFLOW_ID_PHL; // TODO: PHL workflow id
            }else if (loanType.equalsIgnoreCase(LOAN_NAME_EL)) {
                workflowId = WORKFLOW_ID_EL; // TODO: PHL workflow id
            }
            else if (loanType.equalsIgnoreCase(LOAN_NAME_AHL)) {
                workflowId = WORKFLOW_ID_AHL; // TODO: AHL workflow id
            } else {
                workflowId = workFlowId;
            }
            viewModel.getApplicationStatusFromServer(userId, workFlowId, loanType, fromDate, todate);
            if (viewModel.getCustometviewLiveData() != null) {
                Observer getCustomerViewDataObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        appHelper.getDialogHelper().getLoadingDialog().closeDialog();
                        List<ApplicationStatusTable> applicationStatusTableList = (List<ApplicationStatusTable>) o;
                        viewModel.getCustometviewLiveData().removeObserver(this);

                        // TODO: Getting Knowledge bank data from server
//                        getKnowledgeBankFromServer();

                    }
                };
                viewModel.getCustometviewLiveData().observe(this, getCustomerViewDataObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            appHelper.getDialogHelper().getLoadingDialog().closeDialog();
            INSERT_LOG("getApplicationStatusFromServer", "Exception : " + ex.getMessage());

        }
    }

    private void INSERT_LOG(String methodName, String message) {
        try {
            if (viewModel != null) {
                viewModel.insertLog(methodName, message, userId, "", HomeActivity.class.getCanonicalName(), CLIENT_ID, loanType, moduleType);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
