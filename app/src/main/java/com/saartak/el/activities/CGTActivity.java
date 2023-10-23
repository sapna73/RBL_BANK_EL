package com.saartak.el.activities;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.SwitchCompat;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.google.gson.Gson;
import com.saartak.el.BuildConfig;
import com.saartak.el.R;
import com.saartak.el.database.entity.CGTTable;
import com.saartak.el.view_models.DynamicUIViewModel;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

import static com.saartak.el.constants.AppConstant.DATE_FORMAT_DD_MM_YYYY2;
import static com.saartak.el.constants.AppConstant.MODULE_TYPE_ATTENDANCE;
import static com.saartak.el.constants.AppConstant.MODULE_TYPE_GROUP_FORMATION;
import static com.saartak.el.constants.AppConstant.MODULE_TYPE_HOUSE_VERIFICATION_CGT;
import static com.saartak.el.constants.AppConstant.MODULE_TYPE_LOAN_APPLICATION;
import static com.saartak.el.constants.AppConstant.PARAM_BRANCH_GST_CODE;
import static com.saartak.el.constants.AppConstant.PARAM_BRANCH_ID;
import static com.saartak.el.constants.AppConstant.PARAM_CGT_TABLE_JSON;
import static com.saartak.el.constants.AppConstant.PARAM_CLIENT_ID;
import static com.saartak.el.constants.AppConstant.PARAM_LOAN_TYPE;
import static com.saartak.el.constants.AppConstant.PARAM_MODULE_TYPE;
import static com.saartak.el.constants.AppConstant.PARAM_PRODUCT_ID;
import static com.saartak.el.constants.AppConstant.PARAM_USER_ID;
import static com.saartak.el.constants.AppConstant.PARAM_USER_NAME;

public class CGTActivity extends LOSBaseActivity implements View.OnClickListener, HasSupportFragmentInjector {
    LinearLayout llAttendance, llActivities, llHouseVerification, llLoanApplication , llGroupFormation;
    TextView tvCenterName, tvCGTCycle;
    TextView tvAppVersion,tvCurrentDate,tvUserName;
    CardView cvGroupFormation;
    CGTTable CGT_TABLE;

    private AlertDialog notificationDialog;

    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;

    @Inject
    public ViewModelProvider.Factory viewModelFactory;
    public DynamicUIViewModel viewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_cgt);

        tvUserName = (TextView)findViewById(R.id.tv_user_name);
        tvCurrentDate = (TextView)findViewById(R.id.tv_currentdate);
        tvAppVersion = (TextView)findViewById(R.id.tv_app_version);

        llAttendance = (LinearLayout) findViewById(R.id.ll_attendance);
        llAttendance.setOnClickListener(this);
        llActivities = (LinearLayout) findViewById(R.id.ll_activities);
        llActivities.setOnClickListener(this);
        llHouseVerification = (LinearLayout) findViewById(R.id.ll_house_verification);
        llHouseVerification.setOnClickListener(this);
        llLoanApplication = (LinearLayout) findViewById(R.id.ll_loan_application);
        llLoanApplication.setOnClickListener(this);
        llGroupFormation = (LinearLayout) findViewById(R.id.ll_group_formation);
        llGroupFormation.setOnClickListener(this);
        tvCenterName = (TextView) findViewById(R.id.tv_center_name_value);
        tvCGTCycle = (TextView) findViewById(R.id.tv_cgt_cycle_value);
        cvGroupFormation = (CardView) findViewById(R.id.cv_group_formation);

        userName = getIntent().getStringExtra(PARAM_USER_NAME);
        userId = getIntent().getStringExtra(PARAM_USER_ID);
        branchId = getIntent().getStringExtra(PARAM_BRANCH_ID);
        branchGSTcode = getIntent().getStringExtra(PARAM_BRANCH_GST_CODE);
        loanType = getIntent().getStringExtra(PARAM_LOAN_TYPE);
        productId = getIntent().getStringExtra(PARAM_PRODUCT_ID);
        CLIENT_ID = getIntent().getStringExtra(PARAM_CLIENT_ID);
        String cgtJsonString = getIntent().getStringExtra(PARAM_CGT_TABLE_JSON);
        if (!TextUtils.isEmpty(cgtJsonString)) {
            CGT_TABLE = new Gson().fromJson(cgtJsonString, CGTTable.class);
            if (CGT_TABLE != null) {
                tvCenterName.setText(CGT_TABLE.getCenterName());
                if(CGT_TABLE.isCycleOneCompleted()){
                    // TODO: Group Formation Visible
                    cvGroupFormation.setVisibility(View.VISIBLE);
                }
                else{
                    // TODO: Group Formation Non Visible
                    cvGroupFormation.setVisibility(View.GONE);
                }
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
        try {
            switch (v.getId()) {
                case R.id.ll_group_formation:
                    Intent intentGroupFormation = new Intent(this, GroupFormationActivity.class);
                    intentGroupFormation.putExtra(PARAM_LOAN_TYPE, loanType);
                    intentGroupFormation.putExtra(PARAM_MODULE_TYPE, MODULE_TYPE_GROUP_FORMATION);
                    intentGroupFormation.putExtra(PARAM_USER_NAME, userName);
                    intentGroupFormation.putExtra(PARAM_BRANCH_ID, branchId);
                    intentGroupFormation.putExtra(PARAM_USER_ID, userId);
                    intentGroupFormation.putExtra(PARAM_CLIENT_ID, CLIENT_ID);
                    intentGroupFormation.putExtra(PARAM_PRODUCT_ID, productId);
                    String cgtTableJson=new Gson().toJson(CGT_TABLE,CGTTable.class);
                    if(!TextUtils.isEmpty(cgtTableJson)){
                        intentGroupFormation.putExtra(PARAM_CGT_TABLE_JSON, cgtTableJson);
                    }
                    startActivity(intentGroupFormation);
                    break;
                case R.id.ll_house_verification:
                    Intent houseVerification = new Intent(this, HouseVerificationMemberDetailsSummaryActivity.class);
                    houseVerification.putExtra(PARAM_LOAN_TYPE, loanType);
                    houseVerification.putExtra(PARAM_MODULE_TYPE, MODULE_TYPE_HOUSE_VERIFICATION_CGT);
                    houseVerification.putExtra(PARAM_USER_NAME, userName);
                    houseVerification.putExtra(PARAM_BRANCH_ID, branchId);
                    houseVerification.putExtra(PARAM_USER_ID, userId);
                    houseVerification.putExtra(PARAM_CLIENT_ID, CLIENT_ID); // TODO: Nothing but center id
                    houseVerification.putExtra(PARAM_PRODUCT_ID, productId);
                    String cgtTableJsonForHouseVerification = new Gson().toJson(CGT_TABLE, CGTTable.class);
                    if (!TextUtils.isEmpty(cgtTableJsonForHouseVerification)) {
                        houseVerification.putExtra(PARAM_CGT_TABLE_JSON, cgtTableJsonForHouseVerification);
                    }
                    startActivity(houseVerification);
                    break;
                case R.id.ll_attendance:
                    Intent intentAttendance = new Intent(this, CGTAttendanceActivity.class);
                    intentAttendance.putExtra(PARAM_LOAN_TYPE, loanType);
                    intentAttendance.putExtra(PARAM_MODULE_TYPE, MODULE_TYPE_ATTENDANCE);
                    intentAttendance.putExtra(PARAM_USER_NAME, userName);
                    intentAttendance.putExtra(PARAM_BRANCH_ID, branchId);
                    intentAttendance.putExtra(PARAM_USER_ID, userId);
                    intentAttendance.putExtra(PARAM_CLIENT_ID, CLIENT_ID);
                    intentAttendance.putExtra(PARAM_PRODUCT_ID, productId);
                    String cgtTableJsonForAttendance = new Gson().toJson(CGT_TABLE, CGTTable.class);
                    if (!TextUtils.isEmpty(cgtTableJsonForAttendance)) {
                        intentAttendance.putExtra(PARAM_CGT_TABLE_JSON, cgtTableJsonForAttendance);
                    }
                    startActivity(intentAttendance);
                    break;
                case R.id.ll_activities:

                    if (CGT_TABLE != null) {
                        openPopUpForActivities(CGT_TABLE);
                    }
                    break;

                case R.id.ll_loan_application:
//                    Intent loanApplication = new Intent(this, LoanApplicationMemberDetailsActivity.class);
                    Intent loanApplication = new Intent(this, LoanApplicationSummaryActivity.class);
                    loanApplication.putExtra(PARAM_LOAN_TYPE, loanType);
                    loanApplication.putExtra(PARAM_USER_NAME, userName);
                    loanApplication.putExtra(PARAM_BRANCH_ID, branchId);
                    loanApplication.putExtra(PARAM_BRANCH_GST_CODE, branchGSTcode);
                    loanApplication.putExtra(PARAM_USER_ID, userId);
                    loanApplication.putExtra(PARAM_MODULE_TYPE, MODULE_TYPE_LOAN_APPLICATION);
                    if(CGT_TABLE !=null) {
                        loanApplication.putExtra(PARAM_CLIENT_ID, CGT_TABLE.getCenterId());
                        String cgtTableJsonForLoanApplication = new Gson().toJson(CGT_TABLE, CGTTable.class);
                        if (!TextUtils.isEmpty(cgtTableJsonForLoanApplication)) {
                            loanApplication.putExtra(PARAM_CGT_TABLE_JSON, cgtTableJsonForLoanApplication);
                        }
                    }
                    loanApplication.putExtra(PARAM_PRODUCT_ID, productId);
                    startActivity(loanApplication);
                    break;

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    private void openPopUpForActivities(CGTTable cgtTable) {
        try {
            showActivitiesPopUp(new ActionCallback() {
                @Override
                public void onAction(CGTTable cgtTable) {
                    //  updateCGTTable(cgtTable);
                    saveActivitiesData(cgtTable);
                }
            }, cgtTable);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void saveActivitiesData(CGTTable cgtTable) {
        try {
            appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();
            try {
                viewModel.saveActivitiesData(cgtTable);
                if (viewModel.getCgtTableLiveData() != null) {
                    Observer getCenterCreationTableObserver = new Observer() {
                        @Override
                        public void onChanged(@Nullable Object o) {
                            viewModel.getCgtTableLiveData().removeObserver(this);
                            appHelper.getDialogHelper().getLoadingDialog().closeDialog();
                        }
                    };
                    viewModel.getCgtTableLiveData().observe(this, getCenterCreationTableObserver);
                }
            } catch (Exception ex) {
                appHelper.getDialogHelper().getLoadingDialog().closeDialog();
                ex.printStackTrace();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    private void updateCGTTable(CGTTable cgtTable) {
        try {
            appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();

            try {
                viewModel.updateCGTTable(cgtTable);
                if (viewModel.getCgtTableLiveData() != null) {
                    Observer getCenterCreationTableObserver = new Observer() {
                        @Override
                        public void onChanged(@Nullable Object o) {
                            appHelper.getDialogHelper().getLoadingDialog().closeDialog();
                            viewModel.getCgtTableLiveData().removeObserver(this);
                        }
                    };
                    viewModel.getCgtTableLiveData().observe(this, getCenterCreationTableObserver);
                }
            } catch (Exception ex) {
                appHelper.getDialogHelper().getLoadingDialog().closeDialog();
                ex.printStackTrace();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void showActivitiesPopUp(final ActionCallback callback, CGTTable cgtTable) {

        notificationDialog = new AlertDialog.Builder(this).create();

        View notificationDialogView = LayoutInflater.from(this).inflate(R.layout.
                popup_task, null);
        notificationDialog.setView(notificationDialogView);
        notificationDialog.setCancelable(false);

        Button btnSave = (Button) notificationDialogView.findViewById(R.id.btn_popup_yes);
        Button btnCancel = (Button) notificationDialogView.findViewById(R.id.btn_popup_no);
        SwitchCompat swActivity1 = (SwitchCompat) notificationDialogView.findViewById(R.id.sw_activity1);
        SwitchCompat swActivity2 = (SwitchCompat) notificationDialogView.findViewById(R.id.sw_activity2);
        SwitchCompat swActivity3 = (SwitchCompat) notificationDialogView.findViewById(R.id.sw_activity3);
        RelativeLayout rlChecked1 = (RelativeLayout) notificationDialogView.findViewById(R.id.rl_checked1);
        RelativeLayout rlChecked2 = (RelativeLayout) notificationDialogView.findViewById(R.id.rl_checked2);
        RelativeLayout rlChecked3 = (RelativeLayout) notificationDialogView.findViewById(R.id.rl_checked3);

        if (cgtTable.isActivity1()) {
            swActivity1.setChecked(true);
            swActivity1.setEnabled(false);
            rlChecked1.setVisibility(View.VISIBLE);
        } else {
            swActivity1.setChecked(false);
            swActivity1.setEnabled(true);
            rlChecked1.setVisibility(View.GONE);
        }
        if (cgtTable.isActivity2()) {
            swActivity2.setChecked(true);
            swActivity2.setEnabled(false);
            rlChecked2.setVisibility(View.VISIBLE);
        } else {
            swActivity2.setChecked(false);
            swActivity2.setEnabled(true);
            rlChecked2.setVisibility(View.GONE);
        }
        if (cgtTable.isActivity3()) {
            swActivity3.setChecked(true);
            swActivity3.setEnabled(false);
            rlChecked3.setVisibility(View.VISIBLE);
        } else {
            swActivity3.setChecked(false);
            swActivity3.setEnabled(true);
            rlChecked3.setVisibility(View.GONE);
        }

        swActivity1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    rlChecked1.setVisibility(View.VISIBLE);
                } else {
                    rlChecked1.setVisibility(View.GONE);
                }
            }
        });
        swActivity2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    rlChecked2.setVisibility(View.VISIBLE);
                } else {
                    rlChecked2.setVisibility(View.GONE);
                }
            }
        });
        swActivity3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    rlChecked3.setVisibility(View.VISIBLE);
                } else {
                    rlChecked3.setVisibility(View.GONE);
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (callback != null) {
                        closeDialog();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (callback != null) {

                        if (!cgtTable.isCycleOneCompleted() && swActivity1.isChecked() && swActivity2.isChecked() && swActivity3.isChecked()) {
                            Toast.makeText(CGTActivity.this, "All activities are not allowed in CGT Session 1", Toast.LENGTH_SHORT).show();
                            return;
                        } else {
                            if (swActivity1.isChecked()) {
                                cgtTable.setActivity1(true);
                            } else {
                                cgtTable.setActivity1(false);
                            }

                            if (swActivity2.isChecked()) {
                                cgtTable.setActivity2(true);
                            } else {
                                cgtTable.setActivity2(false);
                            }

                            if (swActivity3.isChecked()) {
                                cgtTable.setActivity3(true);
                            } else {
                                cgtTable.setActivity3(false);
                            }
                        }

                        closeDialog();
                        callback.onAction(cgtTable);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        notificationDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        notificationDialog.show();
    }

    public void closeDialog() {
        try {
            if (notificationDialog != null && notificationDialog.isShowing()) {
                notificationDialog.dismiss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public interface ActionCallback {
        void onAction(CGTTable cgtTable);
    }
}
