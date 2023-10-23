package com.saartak.el.activities;

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
import com.saartak.el.R;
import com.saartak.el.view_models.DynamicUIViewModel;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

import static com.saartak.el.constants.AppConstant.PARAM_BRANCH_GST_CODE;
import static com.saartak.el.constants.AppConstant.PARAM_BRANCH_ID;
import static com.saartak.el.constants.AppConstant.PARAM_BRANCH_NAME;
import static com.saartak.el.constants.AppConstant.PARAM_LOAN_TYPE;
import static com.saartak.el.constants.AppConstant.PARAM_PRODUCT_ID;
import static com.saartak.el.constants.AppConstant.PARAM_ROLE_NAME;
import static com.saartak.el.constants.AppConstant.PARAM_USER_ID;
import static com.saartak.el.constants.AppConstant.PARAM_USER_NAME;
import static com.saartak.el.dynamicui.constants.ParametersConstant.ERROR_MESSAGE_APP_NOT_AVAILABLE_PLEASE_INSTALL;
import static com.saartak.el.dynamicui.constants.ParametersConstant.PACKAGE_NAME_HELP;
import static com.saartak.el.dynamicui.constants.ParametersConstant.PACKAGE_NAME_LETS_CONNECT;
import static com.saartak.el.dynamicui.constants.ParametersConstant.PACKAGE_NAME_TEAMS;
import static com.saartak.el.dynamicui.constants.ParametersConstant.PACKAGE_NAME_ZING_HR;

public class PersonalHomeActivity extends LOSBaseActivity implements View.OnClickListener {
    private static final String TAG = PersonalHomeActivity.class.getCanonicalName();
    LinearLayout llLetsConnect, llZingHr, llTeams,llHelp;
    TextView tvLoanType;
    CardView cv_lets_connect, cv_zing_hr,cv_help, cv_teams;

    @Inject
    public ViewModelProvider.Factory viewModelFactory;
    public DynamicUIViewModel viewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_personal_home);


        llLetsConnect = (LinearLayout) findViewById(R.id.ll_lets_connect);
        llZingHr = (LinearLayout) findViewById(R.id.ll_zing_hr);
        llTeams = (LinearLayout) findViewById(R.id.ll_teams);
        llHelp = (LinearLayout) findViewById(R.id.ll_help);

        cv_lets_connect = (CardView) findViewById(R.id.cv_lets_connect);
        cv_zing_hr = (CardView) findViewById(R.id.cv_zing_hr);
        cv_teams = (CardView) findViewById(R.id.cv_teams);
        cv_help = (CardView) findViewById(R.id.cv_help);

        tvLoanType = (TextView) findViewById(R.id.tv_product_name);

        userName = getIntent().getStringExtra(PARAM_USER_NAME);
        userId = getIntent().getStringExtra(PARAM_USER_ID);
        branchId = getIntent().getStringExtra(PARAM_BRANCH_ID);
        branchName = getIntent().getStringExtra(PARAM_BRANCH_NAME);
        branchGSTcode = getIntent().getStringExtra(PARAM_BRANCH_GST_CODE);
        productId = getIntent().getStringExtra(PARAM_PRODUCT_ID);
        roleName = getIntent().getStringExtra(PARAM_ROLE_NAME);
        loanType = getIntent().getStringExtra(PARAM_LOAN_TYPE);

        llLetsConnect.setOnClickListener(this);
        llZingHr.setOnClickListener(this);
        llTeams.setOnClickListener(this);
        llHelp.setOnClickListener(this);


        if (!TextUtils.isEmpty(loanType)) {
//            tvLoanType.setText(loanType);
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
                case R.id.ll_lets_connect:
                    // TODO: Open APP
                    Intent launchIntent_connect = getPackageManager().getLaunchIntentForPackage(PACKAGE_NAME_LETS_CONNECT);
                    if (launchIntent_connect != null) {
                        startActivity(launchIntent_connect);
                    } else {
                        appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT,
                                ERROR_MESSAGE_APP_NOT_AVAILABLE_PLEASE_INSTALL);
                    }

                    break;
                case R.id.ll_zing_hr:
                    // TODO: Open APP
                    Intent launchIntent_zingHr = getPackageManager().getLaunchIntentForPackage(PACKAGE_NAME_ZING_HR);
                    if (launchIntent_zingHr != null) {
                        startActivity(launchIntent_zingHr);
                    } else {
                        appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT,
                                ERROR_MESSAGE_APP_NOT_AVAILABLE_PLEASE_INSTALL);
                    }

                    break;

                case R.id.ll_teams:
                    // TODO: Open APP
                    Intent launchIntentTeams = getPackageManager().getLaunchIntentForPackage(PACKAGE_NAME_TEAMS);
                    if (launchIntentTeams != null) {
                        startActivity(launchIntentTeams);
                    } else {
                        appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT,
                                ERROR_MESSAGE_APP_NOT_AVAILABLE_PLEASE_INSTALL);
                    }

                    break;

                case R.id.ll_help:
                    // TODO: Open APP
                    Intent launchIntentHelp = getPackageManager().getLaunchIntentForPackage(PACKAGE_NAME_HELP);
                    if (launchIntentHelp != null) {
                        startActivity(launchIntentHelp);
                    } else {
                        appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT,
                                ERROR_MESSAGE_APP_NOT_AVAILABLE_PLEASE_INSTALL);
                    }

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
                viewModel.insertLog(methodName, message, userId, "", PersonalHomeActivity.class.getCanonicalName()
                        , CLIENT_ID, loanType, moduleType);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}