package com.saartak.el.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.bfil.uilibrary.dialogs.ConfirmationDialog;
import com.saartak.el.R;
import com.saartak.el.view_models.DynamicUIViewModel;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

import static com.saartak.el.constants.AppConstant.LOAN_NAME_MSME;
import static com.saartak.el.constants.AppConstant.PARAM_LOAN_TYPE;
import static com.saartak.el.constants.AppConstant.PARAM_BRANCH_GST_CODE;
import static com.saartak.el.constants.AppConstant.PARAM_BRANCH_ID;
import static com.saartak.el.constants.AppConstant.PARAM_CLIENT_ID;
import static com.saartak.el.constants.AppConstant.PARAM_MODULE_TYPE;
import static com.saartak.el.constants.AppConstant.MODULE_TYPE_HOUSE_EXPENSE;
import static com.saartak.el.constants.AppConstant.MODULE_TYPE_HOUSE_INCOME;
import static com.saartak.el.constants.AppConstant.MODULE_TYPE_MANUFACTURE_BUSINESS_INCOME;
import static com.saartak.el.constants.AppConstant.PARAM_USER_ID;
import static com.saartak.el.constants.AppConstant.PARAM_USER_NAME;

public class IncomeAssessmentStaticActivity extends LOSBaseActivity implements View.OnClickListener, HasSupportFragmentInjector {
    LinearLayout llHIncome,llHExpense,llBIncome;

    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;

    @Inject
    public ViewModelProvider.Factory viewModelFactory;
    public DynamicUIViewModel viewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_income_assesment_static);
        llHIncome=(LinearLayout)findViewById(R.id.ll_h_income);
        llHIncome.setOnClickListener(this);
        llHExpense=(LinearLayout)findViewById(R.id.ll_h_expense);
        llHExpense.setOnClickListener(this);
        llBIncome=(LinearLayout)findViewById(R.id.ll_b_income);
        llBIncome.setOnClickListener(this);

        userName = getIntent().getStringExtra(PARAM_USER_NAME);
        userId = getIntent().getStringExtra(PARAM_USER_ID);
        branchId = getIntent().getStringExtra(PARAM_BRANCH_ID);
        branchGSTcode = getIntent().getStringExtra(PARAM_BRANCH_GST_CODE);
        loanType = getIntent().getStringExtra(PARAM_LOAN_TYPE);
        CLIENT_ID = getIntent().getStringExtra(PARAM_CLIENT_ID);

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
                case R.id.ll_h_income:
                    Intent intentHouseIncome = new Intent(this, BaseActivity.class);
                    intentHouseIncome.putExtra(PARAM_LOAN_TYPE, loanType);
                    intentHouseIncome.putExtra(PARAM_MODULE_TYPE, MODULE_TYPE_HOUSE_INCOME);
                    intentHouseIncome.putExtra(PARAM_USER_NAME, userName);
                    intentHouseIncome.putExtra(PARAM_BRANCH_ID, branchId);
                    intentHouseIncome.putExtra(PARAM_USER_ID, userId);
                    intentHouseIncome.putExtra(PARAM_CLIENT_ID, CLIENT_ID);
                    startActivity(intentHouseIncome);
                    break;
                case R.id.ll_h_expense:
                    Intent intentHouseExpense = new Intent(this, BaseActivity.class);
                    intentHouseExpense.putExtra(PARAM_LOAN_TYPE, loanType);
                    intentHouseExpense.putExtra(PARAM_MODULE_TYPE, MODULE_TYPE_HOUSE_EXPENSE);
                    intentHouseExpense.putExtra(PARAM_USER_NAME, userName);
                    intentHouseExpense.putExtra(PARAM_BRANCH_ID, branchId);
                    intentHouseExpense.putExtra(PARAM_USER_ID, userId);
                    intentHouseExpense.putExtra(PARAM_CLIENT_ID, CLIENT_ID);
                    startActivity(intentHouseExpense);
                    break;
                case R.id.ll_b_income:

                            if( ! TextUtils.isEmpty(loanType) && loanType.equalsIgnoreCase(LOAN_NAME_MSME)){
                                getModuleType(loanType,CLIENT_ID);
                            }else{
                                Intent intentBusinessIncome = new Intent(IncomeAssessmentStaticActivity.this, BaseActivity.class);
                                intentBusinessIncome.putExtra(PARAM_LOAN_TYPE, loanType);
                                intentBusinessIncome.putExtra(PARAM_MODULE_TYPE, MODULE_TYPE_MANUFACTURE_BUSINESS_INCOME);
                                intentBusinessIncome.putExtra(PARAM_USER_NAME, userName);
                                intentBusinessIncome.putExtra(PARAM_BRANCH_ID, branchId);
                                intentBusinessIncome.putExtra(PARAM_USER_ID, userId);
                                intentBusinessIncome.putExtra(PARAM_CLIENT_ID, CLIENT_ID);
                                startActivity(intentBusinessIncome);
                            }
                    break;
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private void getModuleType(String loanType,String clientId){
        try{
            viewModel.getModuleType(loanType,clientId);
            if(viewModel.getStringLiveData() !=null){
                Observer observer=new Observer() {
                    @Override
                    public void onChanged(Object o) {
                     String moduleType=(String)o;
                     viewModel.getStringLiveData().removeObserver(this);

                     if( !TextUtils.isEmpty(moduleType)){
                         Intent intentBusinessIncome = new Intent(IncomeAssessmentStaticActivity.this, BaseActivity.class);
                         intentBusinessIncome.putExtra(PARAM_LOAN_TYPE, loanType);
                         intentBusinessIncome.putExtra(PARAM_MODULE_TYPE, moduleType);
                         intentBusinessIncome.putExtra(PARAM_USER_NAME, userName);
                         intentBusinessIncome.putExtra(PARAM_BRANCH_ID, branchId);
                         intentBusinessIncome.putExtra(PARAM_USER_ID, userId);
                         intentBusinessIncome.putExtra(PARAM_CLIENT_ID, CLIENT_ID);
                         startActivity(intentBusinessIncome);
                     }else{
                         appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT,
                                 "Please save the business profile details in application stage first");
                     }
                    }
                };
                viewModel.getStringLiveData().observe(this,observer);
            }
        }catch (Exception ex){
            ex.printStackTrace();
            INSERT_LOG("getModuleType","Exception : "+ex.getMessage());
        }
    }



    private void INSERT_LOG(String methodName,String message){
        try{
            if(viewModel !=null){
                viewModel.insertLog(methodName,message,userId,"","IncomeAssessmentStaticActivity"
                        ,CLIENT_ID,loanType,moduleType);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
