package com.swadhaar.los.activities;

import androidx.cardview.widget.CardView;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bfil.uilibrary.dialogs.ConfirmationDialog;
import com.swadhaar.los.BuildConfig;
import com.swadhaar.los.R;
import com.swadhaar.los.constants.AppConstant;
import com.swadhaar.los.view_models.DynamicUIViewModel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

import static com.swadhaar.los.constants.AppConstant.DATE_FORMAT_DD_MM_YYYY2;
import static com.swadhaar.los.constants.AppConstant.MODULE_TYPE_CENTER_CREATION;
import static com.swadhaar.los.constants.AppConstant.MODULE_TYPE_CGT;
import static com.swadhaar.los.constants.AppConstant.MODULE_TYPE_DROP_OUT_CUSTOMER;
import static com.swadhaar.los.constants.AppConstant.MODULE_TYPE_HOUSE_VERIFICATION_CGT;
import static com.swadhaar.los.constants.AppConstant.MODULE_TYPE_LOAN_APPLICATION;
import static com.swadhaar.los.constants.AppConstant.MODULE_TYPE_TARGET_DETAILS;
import static com.swadhaar.los.constants.AppConstant.PARAM_BRANCH_GST_CODE;
import static com.swadhaar.los.constants.AppConstant.PARAM_BRANCH_ID;
import static com.swadhaar.los.constants.AppConstant.PARAM_BRANCH_NAME;
import static com.swadhaar.los.constants.AppConstant.PARAM_CLIENT_ID;
import static com.swadhaar.los.constants.AppConstant.PARAM_LOAN_TYPE;
import static com.swadhaar.los.constants.AppConstant.PARAM_MODULE_TYPE;
import static com.swadhaar.los.constants.AppConstant.PARAM_PRODUCT_ID;
import static com.swadhaar.los.constants.AppConstant.PARAM_ROLE_NAME;
import static com.swadhaar.los.constants.AppConstant.PARAM_USER_ID;
import static com.swadhaar.los.constants.AppConstant.PARAM_USER_NAME;

public class CenterCreationHomeActivity extends LOSBaseActivity implements View.OnClickListener {
    private static final String TAG = CenterCreationHomeActivity.class.getCanonicalName();
    LinearLayout llCreateNewCenter, llAddMembers, llCGT,llLoanApplication,llHouseVerification,
            llDropOutCustomer;
    TextView tvLoanType;
    CardView cv_create_new_center, cv_add_members, cv_cgt, cv_loan_application,cv_house_verification,
            cv_drop_out_customer;
    TextView tvAppVersion,tvCurrentDate,tvUserName;

    @Inject
    public ViewModelProvider.Factory viewModelFactory;
    public DynamicUIViewModel viewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_center_creation_home);

        tvUserName = (TextView)findViewById(R.id.tv_user_name);
        tvCurrentDate = (TextView)findViewById(R.id.tv_currentdate);
        tvAppVersion = (TextView)findViewById(R.id.tv_app_version);

        llCreateNewCenter = (LinearLayout) findViewById(R.id.ll_create_new_center);
        llAddMembers = (LinearLayout) findViewById(R.id.ll_add_members);
        llLoanApplication = (LinearLayout) findViewById(R.id.ll_loan_application);
        llCGT = (LinearLayout) findViewById(R.id.ll_cgt);
        llDropOutCustomer = (LinearLayout) findViewById(R.id.ll_drop_out_customer);
        llHouseVerification = (LinearLayout) findViewById(R.id.ll_house_verification);

        llCreateNewCenter.setOnClickListener(this);
        llAddMembers.setOnClickListener(this);
        llLoanApplication.setOnClickListener(this);
        llCGT.setOnClickListener(this);
        llDropOutCustomer.setOnClickListener(this);
        llHouseVerification.setOnClickListener(this);

        cv_create_new_center = (CardView) findViewById(R.id.cv_create_new_center);
        cv_add_members = (CardView) findViewById(R.id.cv_add_members);
        cv_loan_application = (CardView) findViewById(R.id.cv_loan_application);
        cv_cgt = (CardView) findViewById(R.id.cv_cgt);
        cv_drop_out_customer = (CardView) findViewById(R.id.cv_drop_out_customer);
        cv_house_verification = (CardView) findViewById(R.id.cv_house_verification);

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
//            tvLoanType.setText(loanType);
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

    @Override
    public void onClick(View v) {
        try {
            switch (v.getId()) {
                case R.id.ll_create_new_center:
                    String timeStamp = new SimpleDateFormat("yyMMddHHmmss",
                            Locale.getDefault()).format(new Date());
                    if( ! TextUtils.isEmpty(EMP_LAST_5_DIGIT)) {
                        if (!TextUtils.isEmpty(userId)) {
                            EMP_LAST_5_DIGIT = userId.substring(3);
                            CLIENT_ID = EMP_LAST_5_DIGIT + timeStamp;
                            // TODO: CLIENT ID FORMATION ( FIRST 7 DIGIT EMP ID - 100004141 , YEAR 19 ,
                            //  MONTH 11 , DATE 12, HOUR 08 , MINUTE 25 , SECOND 48 ==> TOTAL 19 DIGITS )
                            if (!TextUtils.isEmpty(CLIENT_ID) && CLIENT_ID.length() > 16) {
                                Intent intentApplication = new Intent(this, CreateNewCenterActivity.class);
                                intentApplication.putExtra(AppConstant.PARAM_LOAN_TYPE, loanType);
                                intentApplication.putExtra(PARAM_USER_NAME, userName);
                                intentApplication.putExtra(PARAM_USER_ID, userId);
                                intentApplication.putExtra(PARAM_BRANCH_ID, branchId);
                                intentApplication.putExtra(PARAM_BRANCH_GST_CODE, branchGSTcode);
                                intentApplication.putExtra(PARAM_CLIENT_ID, CLIENT_ID);
                                intentApplication.putExtra(PARAM_MODULE_TYPE, MODULE_TYPE_CENTER_CREATION);
                                startActivity(intentApplication);
                            } else {
                                appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, "Invalid Client ID");
                            }
                        } else {
                            appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, "User ID Is Empty");
                        }
                    }else {
                        appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, "Employee ID Is Empty");
                    }
                    break;
                case R.id.ll_add_members:
                    Intent targetDetails = new Intent(this, CentersSummaryActivity.class);
                    targetDetails.putExtra(PARAM_LOAN_TYPE, loanType);
                    targetDetails.putExtra(PARAM_USER_NAME, userName);
                    targetDetails.putExtra(PARAM_BRANCH_ID, branchId);
                    targetDetails.putExtra(PARAM_BRANCH_GST_CODE, branchGSTcode);
                    targetDetails.putExtra(PARAM_USER_ID, userId);
                    targetDetails.putExtra(PARAM_MODULE_TYPE, MODULE_TYPE_TARGET_DETAILS);
                    targetDetails.putExtra(PARAM_PRODUCT_ID, productId);
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
                case R.id.ll_house_verification:
                    Intent houseVerification = new Intent(this, CentersSummaryActivity.class);
                    houseVerification.putExtra(PARAM_LOAN_TYPE, loanType);
                    houseVerification.putExtra(PARAM_USER_NAME, userName);
                    houseVerification.putExtra(PARAM_BRANCH_ID, branchId);
                    houseVerification.putExtra(PARAM_BRANCH_GST_CODE, branchGSTcode);
                    houseVerification.putExtra(PARAM_USER_ID, userId);
                    houseVerification.putExtra(PARAM_MODULE_TYPE, MODULE_TYPE_HOUSE_VERIFICATION_CGT);
                    houseVerification.putExtra(PARAM_PRODUCT_ID, productId);
                    startActivity(houseVerification);
                    break;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    private void INSERT_LOG(String methodName, String message) {
        try {
            if (viewModel != null) {
                viewModel.insertLog(methodName, message, userId, "", CenterCreationHomeActivity.class.getCanonicalName()
                        , CLIENT_ID, loanType, moduleType);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}