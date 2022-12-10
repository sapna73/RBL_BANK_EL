package com.swadhaar.los.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.view.ActionMode;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bfil.uilibrary.dialogs.ConfirmationDialog;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;
import com.swadhaar.los.BuildConfig;
import com.swadhaar.los.R;
import com.swadhaar.los.adapter.CGTCycleAdapter;
import com.swadhaar.los.constants.AppConstant;
import com.swadhaar.los.database.entity.CGTTable;
import com.swadhaar.los.database.entity.RawDataTable;
import com.swadhaar.los.helper.MyDividerItemDecoration;
import com.swadhaar.los.view_models.DynamicUIViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

import static com.swadhaar.los.constants.AppConstant.DATE_FORMAT_DD_MM_YYYY2;
import static com.swadhaar.los.constants.AppConstant.MODULE_TYPE_CGT;
import static com.swadhaar.los.constants.AppConstant.PARAM_BRANCH_GST_CODE;
import static com.swadhaar.los.constants.AppConstant.PARAM_BRANCH_ID;
import static com.swadhaar.los.constants.AppConstant.PARAM_CGT_TABLE_JSON;
import static com.swadhaar.los.constants.AppConstant.PARAM_CLIENT_ID;
import static com.swadhaar.los.constants.AppConstant.PARAM_LOAN_TYPE;
import static com.swadhaar.los.constants.AppConstant.PARAM_MODULE_TYPE;
import static com.swadhaar.los.constants.AppConstant.PARAM_PRODUCT_ID;
import static com.swadhaar.los.constants.AppConstant.PARAM_USER_ID;
import static com.swadhaar.los.constants.AppConstant.PARAM_USER_NAME;
import static com.swadhaar.los.dynamicui.constants.ParametersConstant.CGT_1;
import static com.swadhaar.los.dynamicui.constants.ParametersConstant.CGT_2;
import static com.swadhaar.los.dynamicui.constants.ParametersConstant.ERROR_MESSAGE_SELECT_CGT_CYCLE;
import static com.swadhaar.los.dynamicui.constants.ParametersConstant.ERROR_MESSAGE_START_SESSION;
import static com.swadhaar.los.dynamicui.constants.ParametersConstant.ERROR_MESSAGE_UNABLE_TO_UPDATE_SESSION;
import static com.swadhaar.los.dynamicui.constants.ParametersConstant.SUCCESS_RESPONSE_MESSAGE;

public class CGTCycleActivity extends LOSBaseActivity implements CGTCycleAdapter.SyncCallbackInterface {

    private static final String TAG = CGTCycleActivity.class.getCanonicalName();
    RecyclerView rvLeadDetails;
     CGTCycleAdapter cgtCycleAdapter;

    private Toolbar toolbar;

    private ActionMode actionMode;

    TextView tvClientName;
    TextView tvAppVersion,tvCurrentDate;
    String SCREEN_NO;

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView navigationView;
    CoordinatorLayout coordinatorLayout;
    ImageView ivStaffImage;
    RelativeLayout rlNoLeads;
//    TextView tvStaffId, tvStaffName, tvSOBDate;
    Button btnStartSession,btnEndSession,btnFinalStatus;
    RadioGroup rgCGTCycle;
    RadioButton rbCGTCycle1,rbCGTCycle2;
    CGTTable CGT_TABLE;

    List<RawDataTable> allClientRawDataTableList;

    @Inject
    public ViewModelProvider.Factory viewModelFactory;
    public DynamicUIViewModel viewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_cgt_cycle);

        rvLeadDetails = (RecyclerView) findViewById(R.id.rv_lead_details);
        rlNoLeads = (RelativeLayout) findViewById(R.id.rl_no_leads);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tvCurrentDate = (TextView)findViewById(R.id.tv_currentdate);
        tvAppVersion = (TextView)findViewById(R.id.tv_app_version);
        tvClientName = (TextView) findViewById(R.id.tv_client_name_custom);

        rgCGTCycle=(RadioGroup) findViewById(R.id.rg_cgt_cycle);
        rbCGTCycle1=(RadioButton) findViewById(R.id.rb_cgt_cycle1);
        rbCGTCycle2=(RadioButton) findViewById(R.id.rb_cgt_cycle2);

        btnStartSession=(Button)findViewById(R.id.btn_start_session);
        btnEndSession=(Button)findViewById(R.id.btn_end_session);
        btnFinalStatus=(Button)findViewById(R.id.btn_final_status);


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

            }
        }

        if (!TextUtils.isEmpty(userName)) {
            tvClientName.setText(userName.toUpperCase());
        }

        String currentDate = appHelper.getCurrentDate(DATE_FORMAT_DD_MM_YYYY2);
        if (!TextUtils.isEmpty(currentDate)) {
            tvCurrentDate.setText(currentDate);
        }
        String appVersion = BuildConfig.VERSION_NAME;
        if (!TextUtils.isEmpty(appVersion)) {
            tvAppVersion.setText(getResources().getString(R.string.version)+" "+appVersion);//add
        }

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        rvLeadDetails.setLayoutManager(mLayoutManager);
        rvLeadDetails.setItemAnimator(new DefaultItemAnimator());
        rvLeadDetails.addItemDecoration(new MyDividerItemDecoration(this, DividerItemDecoration.VERTICAL, 36));
        rvLeadDetails.setNestedScrollingEnabled(false);

        cgtCycleAdapter = new CGTCycleAdapter(CGTCycleActivity.this, new ArrayList<>(),
               CGTCycleActivity.this, appHelper);
        rvLeadDetails.setAdapter(cgtCycleAdapter);



        btnStartSession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rgCGTCycle !=null && rgCGTCycle.getCheckedRadioButtonId()!=-1) {
                    if (cgtCycleAdapter != null && cgtCycleAdapter.getCgtTableList() != null
                            && cgtCycleAdapter.getCgtTableList().size() > 0) {
                        String selectedRadioButton = appHelper.getSelectedRadioButtonItem(rgCGTCycle, rgCGTCycle.getCheckedRadioButtonId());
                        if(!TextUtils.isEmpty(selectedRadioButton)){
                            if(selectedRadioButton.equalsIgnoreCase(CGT_1)){
                                updateCGTStartSession(CGT_1,cgtCycleAdapter.getCgtTableList() );
                            }
                            else if(selectedRadioButton.equalsIgnoreCase(CGT_2)){
                                updateCGTStartSession(CGT_2,cgtCycleAdapter.getCgtTableList() );
                            }
                        }
                    }
                }else{
                    appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT,ERROR_MESSAGE_SELECT_CGT_CYCLE);
                }
            }
        });

        btnEndSession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rgCGTCycle !=null && rgCGTCycle.getCheckedRadioButtonId()!=-1) {
                        if (cgtCycleAdapter != null && cgtCycleAdapter.getCgtTableList() != null
                                && cgtCycleAdapter.getCgtTableList().size() > 0) {
                            String selectedRadioButton = appHelper.getSelectedRadioButtonItem(rgCGTCycle, rgCGTCycle.getCheckedRadioButtonId());
                            if (!TextUtils.isEmpty(selectedRadioButton)) {
                                if (selectedRadioButton.equalsIgnoreCase(CGT_1)) {
                                    updateCGTEndSession(CGT_1, cgtCycleAdapter.getCgtTableList());
                                } else if (selectedRadioButton.equalsIgnoreCase(CGT_2)) {
                                    updateCGTEndSession(CGT_2, cgtCycleAdapter.getCgtTableList());
                                }
                            }
                        }
                }else{
                    appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT,ERROR_MESSAGE_SELECT_CGT_CYCLE);
                }
            }
        });

        rgCGTCycle.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId !=-1){
                    String selectedRadioButton = appHelper.getSelectedRadioButtonItem(group, checkedId);
                    if(selectedRadioButton.equalsIgnoreCase(CGT_1)){
                        getCGTTableByCGTCycle(CGT_1);
                    }
                    else if(selectedRadioButton.equalsIgnoreCase(CGT_2)){
                        getCGTTableByCGTCycle(CGT_2);
                    }
                }
            }
        });



        configureDagger();
        configureViewModel();

        if(CGT_TABLE !=null){
            getCGTTableByCGTCycle(CGT_1);
        }
    }

    private void updateCGTStartSession(String cgtCycle, List<CGTTable> cgtTableList) {
        appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();

        try {
            viewModel.updateCGTStartSession(cgtCycle, CLIENT_ID,cgtTableList); // TODO: Here client id is nothing but center id
            if (viewModel.getCgtTableLiveDataList() != null) {
                Observer observer = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        appHelper.getDialogHelper().getLoadingDialog().closeDialog();
                        List<CGTTable> cgtTableList = (List<CGTTable>) o;
                        viewModel.getCgtTableLiveDataList().removeObserver(this);

                        if (cgtTableList != null && cgtTableList.size() > 0) {
//                            btnStartSession.setText(FINAL_STATUS_SESSION_STARTED);
//                            btnStartSession.setEnabled(false);

                            btnStartSession.setVisibility(View.GONE);
                            btnEndSession.setVisibility(View.VISIBLE);
                        } else {
//                            btnStartSession.setText(FINAL_STATUS_START_SESSION);
//                            btnStartSession.setEnabled(true);

                            btnStartSession.setVisibility(View.VISIBLE);
                            btnEndSession.setVisibility(View.GONE);
                        }
                    }
                };
                viewModel.getCgtTableLiveDataList().observe(this, observer);
            }
        }catch (Exception ex){
            appHelper.getDialogHelper().getLoadingDialog().closeDialog();
            ex.printStackTrace();
        }
    }

    private void updateCGTEndSession(String cgtCycle, List<CGTTable> cgtTableList) {
        appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();

        try {
            viewModel.updateCGTEndSession(cgtCycle, CLIENT_ID,cgtTableList); // TODO: Here client id is nothing but center id
            if (viewModel.getStringLiveData() != null) {
                Observer observer = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        appHelper.getDialogHelper().getLoadingDialog().closeDialog();
                        String response = (String) o;
                        viewModel.getStringLiveData().removeObserver(this);

                       if(!TextUtils.isEmpty(response)){
                           if(response.equalsIgnoreCase(SUCCESS_RESPONSE_MESSAGE)){

                               getCGTTableByCGTCycle(cgtCycle);
                           }else{
                               appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT,
                                       response);
                           }
                       }else{
                           appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT,
                                   ERROR_MESSAGE_UNABLE_TO_UPDATE_SESSION);
                       }
                    }
                };
                viewModel.getStringLiveData().observe(this, observer);
            }
        }catch (Exception ex){
            appHelper.getDialogHelper().getLoadingDialog().closeDialog();
            ex.printStackTrace();
        }
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
            branchGSTcode = getIntent().getStringExtra(PARAM_BRANCH_GST_CODE);
            loanType = getIntent().getStringExtra(PARAM_LOAN_TYPE);
            productId = getIntent().getStringExtra(PARAM_PRODUCT_ID);
            CLIENT_ID = getIntent().getStringExtra(PARAM_CLIENT_ID);
            String cgtJsonString = getIntent().getStringExtra(PARAM_CGT_TABLE_JSON);
            if (!TextUtils.isEmpty(cgtJsonString)) {
                CGT_TABLE = new Gson().fromJson(cgtJsonString, CGTTable.class);
                if (CGT_TABLE != null) {

                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }



    @Override
    public void onBackPressed() {
        finish();

    }

    public void getCGTTableByCGTCycle(String cgtCycle) {
        appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();

        try {
            viewModel.getCGTTableByCGTCycle(cgtCycle, CLIENT_ID); // TODO: Here client id is nothing but center id
            if (viewModel.getCgtTableLiveDataList() != null) {
                Observer observer = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        appHelper.getDialogHelper().getLoadingDialog().closeDialog();
                        List<CGTTable> cgtTableList = (List<CGTTable>) o;
                        viewModel.getCgtTableLiveDataList().removeObserver(this);

                        if (cgtTableList != null && cgtTableList.size() > 0) {
                            rlNoLeads.setVisibility(View.GONE);
                            rvLeadDetails.setVisibility(View.VISIBLE);

                                if (cgtCycle.equalsIgnoreCase(CGT_1)) {
                                    btnStartSession.setVisibility(View.VISIBLE);
//                                    btnStartSession.setText(FINAL_STATUS_START_SESSION);
//                                    btnStartSession.setEnabled(true);
                                    btnEndSession.setVisibility(View.GONE);
//                                    btnEndSession.setText(FINAL_STATUS_END_SESSION);
//                                    btnEndSession.setEnabled(true);

                                    appHelper.checkRadioButton(rgCGTCycle, cgtCycle,true);

                                } else if (cgtCycle.equalsIgnoreCase(CGT_2)) {

                                    List<CGTTable> cgtTwoCompletedList = new ArrayList<>();
                                    for (CGTTable cgtTableFromDB : cgtTableList) {
                                        if (cgtTableFromDB.isCycleTwoCompleted()) {
                                            cgtTwoCompletedList.add(cgtTableFromDB);
                                        }
                                    }

                                    if(cgtTwoCompletedList.size()==cgtTableList.size()){
                                        btnStartSession.setVisibility(View.GONE);
                                        btnEndSession.setVisibility(View.GONE);
                                        btnFinalStatus.setVisibility(View.VISIBLE);
                                    }else{

                                        btnStartSession.setVisibility(View.VISIBLE);
//                                        btnStartSession.setText(FINAL_STATUS_START_SESSION);
//                                        btnStartSession.setEnabled(true);
                                        btnEndSession.setVisibility(View.GONE);
//                                        btnEndSession.setText(FINAL_STATUS_END_SESSION);
//                                        btnEndSession.setEnabled(true);
                                    }
                                }


                            if (cgtCycleAdapter != null) {
                                cgtCycleAdapter.setItem(cgtTableList);
                            } else {
                                cgtCycleAdapter = new CGTCycleAdapter(CGTCycleActivity.this, cgtTableList,
                                         CGTCycleActivity.this,appHelper);
                                rvLeadDetails.setAdapter(cgtCycleAdapter);
                                cgtCycleAdapter.notifyDataSetChanged();
                            }

                        } else {
                            rvLeadDetails.setVisibility(View.GONE);
                            rlNoLeads.setVisibility(View.VISIBLE);

                            btnStartSession.setVisibility(View.GONE);
                            btnEndSession.setVisibility(View.GONE);
                            btnFinalStatus.setVisibility(View.GONE);
                        }
                    }
                };
                viewModel.getCgtTableLiveDataList().observe(this, observer);
            }
        }catch (Exception ex){
            appHelper.getDialogHelper().getLoadingDialog().closeDialog();
            ex.printStackTrace();
        }
    }


    @Override
    public void openScreenCallback(int position, CGTTable cgtTable) {
        try{
            if( btnStartSession !=null && btnStartSession.getVisibility()!=View.VISIBLE) {
                Intent intentApplication = new Intent(CGTCycleActivity.this, CGTActivity.class);
                intentApplication.putExtra(AppConstant.PARAM_LOAN_TYPE, loanType);
                intentApplication.putExtra(PARAM_USER_NAME, userName);
                intentApplication.putExtra(PARAM_USER_ID, userId);
                intentApplication.putExtra(PARAM_BRANCH_ID, branchId);
                intentApplication.putExtra(PARAM_BRANCH_GST_CODE, branchGSTcode);
                intentApplication.putExtra(PARAM_CLIENT_ID, cgtTable.getCenterId());
                intentApplication.putExtra(PARAM_MODULE_TYPE, MODULE_TYPE_CGT);
                String cgtTableJson = new Gson().toJson(cgtTable, CGTTable.class);
                if (!TextUtils.isEmpty(cgtTableJson)) {
                    intentApplication.putExtra(PARAM_CGT_TABLE_JSON, cgtTableJson);
                }
                startActivity(intentApplication);
            }else{
                appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT,ERROR_MESSAGE_START_SESSION);

            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
