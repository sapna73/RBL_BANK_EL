package com.swadhaar.los.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.swadhaar.los.R;
import com.swadhaar.los.view_models.DynamicUIViewModel;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

import static com.swadhaar.los.constants.AppConstant.LOAN_NAME_MSME;
import static com.swadhaar.los.constants.AppConstant.MODULE_TYPE_HOUSE_ASSETS;
import static com.swadhaar.los.constants.AppConstant.PARAM_LOAN_TYPE;
import static com.swadhaar.los.constants.AppConstant.PARAM_BRANCH_GST_CODE;
import static com.swadhaar.los.constants.AppConstant.PARAM_BRANCH_ID;
import static com.swadhaar.los.constants.AppConstant.PARAM_CLIENT_ID;
import static com.swadhaar.los.constants.AppConstant.PARAM_MODULE_TYPE;
import static com.swadhaar.los.constants.AppConstant.MODULE_TYPE_BUSINESS_ASSETS;
import static com.swadhaar.los.constants.AppConstant.MODULE_TYPE_BUSINESS_LIABILITY;
import static com.swadhaar.los.constants.AppConstant.MODULE_TYPE_HOUSE_LIABILITY;
import static com.swadhaar.los.constants.AppConstant.PARAM_USER_ID;
import static com.swadhaar.los.constants.AppConstant.PARAM_USER_NAME;

public class BalancesheetStaticActivity extends LOSBaseActivity implements View.OnClickListener, HasSupportFragmentInjector {
    LinearLayout llHLiability,llHAssets,llBAssets,llBLiability;
    CardView cvHouseAssets;
    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;

    @Inject
    public ViewModelProvider.Factory viewModelFactory;
    public DynamicUIViewModel viewModel;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_balance_sheet_static);
        cvHouseAssets =(CardView) findViewById(R.id.cv_h_assets);
        llHLiability=(LinearLayout)findViewById(R.id.ll_h_liability);
        llHLiability.setOnClickListener(this);
        llHAssets=(LinearLayout)findViewById(R.id.ll_h_assets);
        llHAssets.setOnClickListener(this);
        llBAssets=(LinearLayout)findViewById(R.id.ll_b_assets);
        llBAssets.setOnClickListener(this);
        llBLiability=(LinearLayout)findViewById(R.id.ll_b_liability);
        llBLiability.setOnClickListener(this);

        userName = getIntent().getStringExtra(PARAM_USER_NAME);
        userId = getIntent().getStringExtra(PARAM_USER_ID);
        branchId = getIntent().getStringExtra(PARAM_BRANCH_ID);
        branchGSTcode = getIntent().getStringExtra(PARAM_BRANCH_GST_CODE);
        loanType = getIntent().getStringExtra(PARAM_LOAN_TYPE);
        CLIENT_ID = getIntent().getStringExtra(PARAM_CLIENT_ID);

        if( !TextUtils.isEmpty(loanType) && loanType.equalsIgnoreCase(LOAN_NAME_MSME)){
            cvHouseAssets.setVisibility(View.VISIBLE);
        }else {
            cvHouseAssets.setVisibility(View.GONE);
        }

        configureDagger();
        configureViewModel();

    }

        private void configureDagger(){
            AndroidInjection.inject(this);
        }
        public void configureViewModel() {
            viewModel = ViewModelProviders.of(this, viewModelFactory).get(DynamicUIViewModel.class);
        }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return dispatchingAndroidInjector;
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onClick(View v) {
        try{
            switch (v.getId()){
                case R.id.ll_h_liability:
                    Intent intentApplication = new Intent(this, BaseActivity.class);
                    intentApplication.putExtra(PARAM_LOAN_TYPE, loanType);
                    intentApplication.putExtra(PARAM_MODULE_TYPE, MODULE_TYPE_HOUSE_LIABILITY);
                    intentApplication.putExtra(PARAM_USER_NAME, userName);
                    intentApplication.putExtra(PARAM_BRANCH_ID, branchId);
                    intentApplication.putExtra(PARAM_USER_ID, userId);
                    intentApplication.putExtra(PARAM_CLIENT_ID, CLIENT_ID);
                    startActivity(intentApplication);
                    break;
                case R.id.ll_h_assets:
                    Intent intentHouseAssets = new Intent(this, BaseActivity.class);
                    intentHouseAssets.putExtra(PARAM_LOAN_TYPE, loanType);
                    intentHouseAssets.putExtra(PARAM_MODULE_TYPE, MODULE_TYPE_HOUSE_ASSETS);
                    intentHouseAssets.putExtra(PARAM_USER_NAME, userName);
                    intentHouseAssets.putExtra(PARAM_BRANCH_ID, branchId);
                    intentHouseAssets.putExtra(PARAM_USER_ID, userId);
                    intentHouseAssets.putExtra(PARAM_CLIENT_ID, CLIENT_ID);
                    startActivity(intentHouseAssets);
                    break;
                case R.id.ll_b_assets:
                    Intent intentBalanceSheet = new Intent(this, BaseActivity.class);
                    intentBalanceSheet.putExtra(PARAM_LOAN_TYPE, loanType);
                    intentBalanceSheet.putExtra(PARAM_MODULE_TYPE, MODULE_TYPE_BUSINESS_ASSETS);
                    intentBalanceSheet.putExtra(PARAM_USER_NAME, userName);
                    intentBalanceSheet.putExtra(PARAM_BRANCH_ID, branchId);
                    intentBalanceSheet.putExtra(PARAM_USER_ID, userId);
                    intentBalanceSheet.putExtra(PARAM_CLIENT_ID, CLIENT_ID);
                    startActivity(intentBalanceSheet);
                    break;
                case R.id.ll_b_liability:
                    Intent intentIncomeAssessment = new Intent(this, BaseActivity.class);
                    intentIncomeAssessment.putExtra(PARAM_LOAN_TYPE, loanType);
                    intentIncomeAssessment.putExtra(PARAM_MODULE_TYPE, MODULE_TYPE_BUSINESS_LIABILITY);
                    intentIncomeAssessment.putExtra(PARAM_USER_NAME, userName);
                    intentIncomeAssessment.putExtra(PARAM_BRANCH_ID, branchId);
                    intentIncomeAssessment.putExtra(PARAM_USER_ID, userId);
                    intentIncomeAssessment.putExtra(PARAM_CLIENT_ID, CLIENT_ID);
                    startActivity(intentIncomeAssessment);
                    break;

            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }


}
