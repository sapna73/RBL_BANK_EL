package com.saartak.el.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.google.gson.Gson;
import com.saartak.el.BuildConfig;
import com.saartak.el.R;
import com.saartak.el.database.entity.CenterCreationTable;
import com.saartak.el.view_models.DynamicUIViewModel;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

import static com.saartak.el.constants.AppConstant.DATE_FORMAT_DD_MM_YYYY2;
import static com.saartak.el.constants.AppConstant.MODULE_TYPE_CGT;
import static com.saartak.el.constants.AppConstant.MODULE_TYPE_DROP_OUT_CUSTOMER;
import static com.saartak.el.constants.AppConstant.MODULE_TYPE_LOAN_APPLICATION;
import static com.saartak.el.constants.AppConstant.PARAM_BRANCH_GST_CODE;
import static com.saartak.el.constants.AppConstant.PARAM_BRANCH_ID;
import static com.saartak.el.constants.AppConstant.PARAM_BRANCH_NAME;
import static com.saartak.el.constants.AppConstant.PARAM_CENTER_TABLE_JSON;
import static com.saartak.el.constants.AppConstant.PARAM_CLIENT_ID;
import static com.saartak.el.constants.AppConstant.PARAM_LOAN_TYPE;
import static com.saartak.el.constants.AppConstant.PARAM_MODULE_TYPE;
import static com.saartak.el.constants.AppConstant.PARAM_PRODUCT_ID;
import static com.saartak.el.constants.AppConstant.PARAM_ROLE_NAME;
import static com.saartak.el.constants.AppConstant.PARAM_USER_ID;
import static com.saartak.el.constants.AppConstant.PARAM_USER_NAME;

public class EligibleNewLoanHomeActivity extends LOSBaseActivity implements View.OnClickListener {
    private static final String TAG = EligibleNewLoanHomeActivity.class.getCanonicalName();
    LinearLayout llCGT, llLoanApplication,
            llDropOutCustomer;
    TextView tvLoanType;
    CardView cv_cgt, cv_loan_application,
            cv_drop_out_customer;
    TextView tvAppVersion, tvCurrentDate, tvUserName;

    CenterCreationTable CENTER_CREATION_TABLE;

    @Inject
    public ViewModelProvider.Factory viewModelFactory;
    public DynamicUIViewModel viewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_eligible_new_loan_home);

        tvUserName = (TextView) findViewById(R.id.tv_user_name);
        tvCurrentDate = (TextView) findViewById(R.id.tv_currentdate);
        tvAppVersion = (TextView) findViewById(R.id.tv_app_version);

        llLoanApplication = (LinearLayout) findViewById(R.id.ll_loan_application);
        llCGT = (LinearLayout) findViewById(R.id.ll_cgt);
        llDropOutCustomer = (LinearLayout) findViewById(R.id.ll_drop_out_customer);

        llLoanApplication.setOnClickListener(this);
        llCGT.setOnClickListener(this);
        llDropOutCustomer.setOnClickListener(this);

        cv_loan_application = (CardView) findViewById(R.id.cv_loan_application);
        cv_cgt = (CardView) findViewById(R.id.cv_cgt);
        cv_drop_out_customer = (CardView) findViewById(R.id.cv_drop_out_customer);

        tvLoanType = (TextView) findViewById(R.id.tv_product_name);

        userName = getIntent().getStringExtra(PARAM_USER_NAME);
        userId = getIntent().getStringExtra(PARAM_USER_ID);
        branchId = getIntent().getStringExtra(PARAM_BRANCH_ID);
        branchName = getIntent().getStringExtra(PARAM_BRANCH_NAME);
        branchGSTcode = getIntent().getStringExtra(PARAM_BRANCH_GST_CODE);
        productId = getIntent().getStringExtra(PARAM_PRODUCT_ID);
        roleName = getIntent().getStringExtra(PARAM_ROLE_NAME);
        loanType = getIntent().getStringExtra(PARAM_LOAN_TYPE);
        String centerJsonString = getIntent().getStringExtra(PARAM_CENTER_TABLE_JSON);
        if (!TextUtils.isEmpty(centerJsonString)) {
            CENTER_CREATION_TABLE = new Gson().fromJson(centerJsonString, CenterCreationTable.class);
            if (CENTER_CREATION_TABLE != null) {

            }
        }


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

    }

    private void configureDagger() {
        AndroidInjection.inject(this);
    }

    public void configureViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(DynamicUIViewModel.class);
    }

    @Override
    public void onClick(View v) {
        try {
            switch (v.getId()) {

                case R.id.ll_loan_application:
                    Intent loanApplication = new Intent(this, LoanApplicationSummaryActivity.class);
                    loanApplication.putExtra(PARAM_LOAN_TYPE, loanType);
                    loanApplication.putExtra(PARAM_USER_NAME, userName);
                    loanApplication.putExtra(PARAM_BRANCH_ID, branchId);
                    loanApplication.putExtra(PARAM_BRANCH_GST_CODE, branchGSTcode);
                    loanApplication.putExtra(PARAM_USER_ID, userId);
                    loanApplication.putExtra(PARAM_MODULE_TYPE, MODULE_TYPE_LOAN_APPLICATION);
                    loanApplication.putExtra(PARAM_PRODUCT_ID, productId);
                    if (CENTER_CREATION_TABLE != null) {
                        loanApplication.putExtra(PARAM_CLIENT_ID, CENTER_CREATION_TABLE.getCenterId());
                        String centerTableJson=new Gson().toJson(CENTER_CREATION_TABLE, CenterCreationTable.class);
                        if(!TextUtils.isEmpty(centerTableJson)){
                            loanApplication.putExtra(PARAM_CENTER_TABLE_JSON, centerTableJson);
                        }
                    }

                    startActivity(loanApplication);
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
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }


    private void INSERT_LOG(String methodName, String message) {
        try {
            if (viewModel != null) {
                viewModel.insertLog(methodName, message, userId, "", EligibleNewLoanHomeActivity.class.getCanonicalName()
                        , CLIENT_ID, loanType, moduleType);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}