package com.swadhaar.los.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.swadhaar.los.BuildConfig;
import com.swadhaar.los.R;
import com.swadhaar.los.view_models.DynamicUIViewModel;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

import static com.swadhaar.los.constants.AppConstant.DATE_FORMAT_DD_MM_YYYY2;
import static com.swadhaar.los.constants.AppConstant.MODULE_TYPE_ELIGIBILITY;
import static com.swadhaar.los.constants.AppConstant.PARAM_BRANCH_GST_CODE;
import static com.swadhaar.los.constants.AppConstant.PARAM_BRANCH_ID;
import static com.swadhaar.los.constants.AppConstant.PARAM_BRANCH_NAME;
import static com.swadhaar.los.constants.AppConstant.PARAM_CENTER_NAME;
import static com.swadhaar.los.constants.AppConstant.PARAM_LOAN_TYPE;
import static com.swadhaar.los.constants.AppConstant.PARAM_MODULE_TYPE;
import static com.swadhaar.los.constants.AppConstant.PARAM_PRODUCT_ID;
import static com.swadhaar.los.constants.AppConstant.PARAM_ROLE_NAME;
import static com.swadhaar.los.constants.AppConstant.PARAM_USER_ID;
import static com.swadhaar.los.constants.AppConstant.PARAM_USER_NAME;

public class CenterMeetingCollectionSummaryHomeActivity extends LOSBaseActivity implements View.OnClickListener {
    private static final String TAG = CenterMeetingCollectionSummaryHomeActivity.class.getCanonicalName();
    LinearLayout llCashCollectionsSummary,llDigitalCollectionsReport;
    TextView tvLoanType;
    TextView tvAppVersion, tvCurrentDate, tvUserName;
    private String CENTER_NAME="";

    @Inject
    public ViewModelProvider.Factory viewModelFactory;
    public DynamicUIViewModel viewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_center_meeting_collection_summary_home);

        tvUserName = (TextView) findViewById(R.id.tv_user_name);
        tvCurrentDate = (TextView) findViewById(R.id.tv_currentdate);
        tvAppVersion = (TextView) findViewById(R.id.tv_app_version);

        llCashCollectionsSummary = (LinearLayout) findViewById(R.id.ll_cash_collection_summary);
        llDigitalCollectionsReport = (LinearLayout) findViewById(R.id.ll_digital_collections_report);

        llCashCollectionsSummary.setOnClickListener(this);
        llDigitalCollectionsReport.setOnClickListener(this);

        tvLoanType = (TextView) findViewById(R.id.tv_product_name);

        userName = getIntent().getStringExtra(PARAM_USER_NAME);
        userId = getIntent().getStringExtra(PARAM_USER_ID);
        branchId = getIntent().getStringExtra(PARAM_BRANCH_ID);
        branchName = getIntent().getStringExtra(PARAM_BRANCH_NAME);
        branchGSTcode = getIntent().getStringExtra(PARAM_BRANCH_GST_CODE);
        productId = getIntent().getStringExtra(PARAM_PRODUCT_ID);
        roleName = getIntent().getStringExtra(PARAM_ROLE_NAME);
        loanType = getIntent().getStringExtra(PARAM_LOAN_TYPE);
//        CENTER_NAME = getIntent().getStringExtra(PARAM_CENTER_NAME);

        if (!TextUtils.isEmpty(loanType)) {
//            tvLoanType.setText(loanType);
        }

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

        // TODO: Insert Into Staff Activity Table
        insertStaffActivity(viewModel, CENTER_NAME, userId, "CENTER MEETING COLLECTION HOME", 0);
    }

    private void configureDagger() {
        AndroidInjection.inject(this);
    }

    public void configureViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(DynamicUIViewModel.class);
    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            userName = getIntent().getStringExtra(PARAM_USER_NAME);
            userId = getIntent().getStringExtra(PARAM_USER_ID);
            branchId = getIntent().getStringExtra(PARAM_BRANCH_ID);
            branchName = getIntent().getStringExtra(PARAM_BRANCH_NAME);
            branchGSTcode = getIntent().getStringExtra(PARAM_BRANCH_GST_CODE);
            productId = getIntent().getStringExtra(PARAM_PRODUCT_ID);
            roleName = getIntent().getStringExtra(PARAM_ROLE_NAME);
            loanType = getIntent().getStringExtra(PARAM_LOAN_TYPE);
//            CENTER_NAME = getIntent().getStringExtra(PARAM_CENTER_NAME);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        try {
            switch (v.getId()) {
                case R.id.ll_cash_collection_summary:
                    Intent eligibility = new Intent(this, CenterMeetingCashCollectionSummaryActivity.class);
                    eligibility.putExtra(PARAM_LOAN_TYPE, loanType);
                    eligibility.putExtra(PARAM_USER_NAME, userName);
                    eligibility.putExtra(PARAM_BRANCH_ID, branchId);
                    eligibility.putExtra(PARAM_BRANCH_GST_CODE, branchGSTcode);
                    eligibility.putExtra(PARAM_USER_ID, userId);
                    eligibility.putExtra(PARAM_MODULE_TYPE, MODULE_TYPE_ELIGIBILITY);
                    eligibility.putExtra(PARAM_PRODUCT_ID, productId);
//                    eligibility.putExtra(PARAM_CENTER_NAME, CENTER_NAME); // TODO: CenterName
                    startActivity(eligibility);
                    break;

                case R.id.ll_digital_collections_report:
                    Intent digital = new Intent(this, DigitalCollectionReportActivity.class);
                    digital.putExtra(PARAM_LOAN_TYPE, loanType);
                    digital.putExtra(PARAM_USER_NAME, userName);
                    digital.putExtra(PARAM_BRANCH_ID, branchId);
                    digital.putExtra(PARAM_BRANCH_GST_CODE, branchGSTcode);
                    digital.putExtra(PARAM_USER_ID, userId);
                    digital.putExtra(PARAM_MODULE_TYPE, MODULE_TYPE_ELIGIBILITY);
                    digital.putExtra(PARAM_PRODUCT_ID, productId);
//                    eligibility.putExtra(PARAM_CENTER_NAME, CENTER_NAME); // TODO: CenterName
                    startActivity(digital);

                    break;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();

        // TODO: Insert Into Staff Activity Table
        insertStaffActivity(viewModel, CENTER_NAME, userId, "CENTER MEETING COLLECTION HOME", 1);

        finish();

    }

    private void INSERT_LOG(String methodName, String message) {
        try {
            if (viewModel != null) {
                viewModel.insertLog(methodName, message, userId, "", CenterMeetingCollectionSummaryHomeActivity.class.getCanonicalName()
                        , CLIENT_ID, loanType, moduleType);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
