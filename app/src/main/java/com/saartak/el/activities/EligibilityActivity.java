package com.saartak.el.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bfil.uilibrary.dialogs.ConfirmationDialog;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;
import com.saartak.el.BuildConfig;
import com.saartak.el.R;
import com.saartak.el.adapter.EligibilityAdapter;
import com.saartak.el.adapter.EligibilityHeaderAdapter;
import com.saartak.el.database.entity.CenterCreationTable;
import com.saartak.el.database.entity.EligibilityTable;
import com.saartak.el.database.entity.RawDataTable;
import com.saartak.el.models.EligibilityByGroupDTO;
import com.saartak.el.view_models.DynamicUIViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

import static com.saartak.el.constants.AppConstant.DATE_FORMAT_DD_MM_YYYY2;
import static com.saartak.el.constants.AppConstant.MODULE_TYPE_LOAN_APPLICATION;
import static com.saartak.el.constants.AppConstant.PARAM_BRANCH_GST_CODE;
import static com.saartak.el.constants.AppConstant.PARAM_BRANCH_ID;
import static com.saartak.el.constants.AppConstant.PARAM_CENTER_NAME;
import static com.saartak.el.constants.AppConstant.PARAM_CENTER_TABLE_JSON;
import static com.saartak.el.constants.AppConstant.PARAM_CLIENT_ID;
import static com.saartak.el.constants.AppConstant.PARAM_LOAN_TYPE;
import static com.saartak.el.constants.AppConstant.PARAM_MODULE_TYPE;
import static com.saartak.el.constants.AppConstant.PARAM_PRODUCT_ID;
import static com.saartak.el.constants.AppConstant.PARAM_USER_ID;
import static com.saartak.el.constants.AppConstant.PARAM_USER_NAME;
import static com.saartak.el.dynamicui.constants.ParametersConstant.ERROR_MESSAGE_DO_YOU_WANT_TO_PROCEED;

public class EligibilityActivity extends LOSBaseActivity {

    private static final String TAG = EligibilityActivity.class.getCanonicalName();
    RecyclerView rvLeadDetails;
    RecyclerView rvProductHeader;
    EligibilityAdapter eligibilityAdapter;
    EligibilityHeaderAdapter eligibilityHeaderAdapter;

    private String CENTER_NAME;

    private Toolbar toolbar;

    private ActionMode actionMode;

    String SCREEN_NO;

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView navigationView;
    CoordinatorLayout coordinatorLayout;
    ImageView ivStaffImage;
    RelativeLayout rlNoLeads;
    TextView tvCenterName;
    TextView tvAppVersion,tvCurrentDate,tvUserName;
    RelativeLayout rlEligibilityHeader;
    Button btnProceed;
    CenterCreationTable CENTER_CREATION_TABLE;
    List<RawDataTable> allClientRawDataTableList;

    @Inject
    public ViewModelProvider.Factory viewModelFactory;
    public DynamicUIViewModel viewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_eligibility);

        tvUserName = (TextView)findViewById(R.id.tv_user_name);
        tvCurrentDate = (TextView)findViewById(R.id.tv_currentdate);
        tvAppVersion = (TextView)findViewById(R.id.tv_app_version);

        rvLeadDetails = (RecyclerView) findViewById(R.id.rv_lead_details);
        rvProductHeader = (RecyclerView) findViewById(R.id.rv_eligibility_header);
        rlNoLeads = (RelativeLayout) findViewById(R.id.rl_no_leads);
        rlEligibilityHeader = (RelativeLayout) findViewById(R.id.rl_eligibility_header);
        toolbar = (Toolbar)findViewById(R.id.toolbar_eligibility);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // back button pressed
                finish();
            }
        });

        tvCenterName=(TextView)findViewById(R.id.tv_center_name_value);
        btnProceed=(Button)findViewById(R.id.btn_proceed);

        userName = getIntent().getStringExtra(PARAM_USER_NAME);
        userId = getIntent().getStringExtra(PARAM_USER_ID);
        branchId = getIntent().getStringExtra(PARAM_BRANCH_ID);
        branchGSTcode = getIntent().getStringExtra(PARAM_BRANCH_GST_CODE);
        loanType = getIntent().getStringExtra(PARAM_LOAN_TYPE);
        productId = getIntent().getStringExtra(PARAM_PRODUCT_ID);
        CENTER_NAME = getIntent().getStringExtra(PARAM_CENTER_NAME);

        if(!TextUtils.isEmpty(CENTER_NAME)){
            tvCenterName.setText(CENTER_NAME);
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


        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        rvLeadDetails.setLayoutManager(mLayoutManager);
        rvLeadDetails.setItemAnimator(new DefaultItemAnimator());
        rvLeadDetails.setNestedScrollingEnabled(false);

        eligibilityAdapter = new EligibilityAdapter(EligibilityActivity.this, new ArrayList<>(),new ArrayList<>(),
                appHelper);
        rvLeadDetails.setAdapter(eligibilityAdapter);

        rvProductHeader.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true));
        rvProductHeader.setNestedScrollingEnabled(false);

        eligibilityHeaderAdapter = new EligibilityHeaderAdapter(EligibilityActivity.this, new ArrayList<>(),
                appHelper);
        rvProductHeader.setAdapter(eligibilityHeaderAdapter);



        btnProceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(eligibilityAdapter !=null && eligibilityAdapter.getListOfEligibilityTableList() !=null
                        && eligibilityAdapter.getListOfEligibilityTableList().size()>0){

                    appHelper.getDialogHelper().getConfirmationDialog().showTwoButtons(ERROR_MESSAGE_DO_YOU_WANT_TO_PROCEED, "Yes", "No", new ConfirmationDialog.PrintActionCallback() {
                        @Override
                        public void onAction() {

                        }

                        @Override
                        public void onPrint() {
                            insertEligibleLoans(eligibilityAdapter.getListOfEligibilityTableList());
                        }
                    });


                }
            }
        });

//        spVillage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                if(spVillage.getSelectedItemPosition()>0){
//                    getCentersFromCenterMeetingTable(userId,spVillage.getSelectedItem().toString());
//                }
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
//
//        spCenter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                if(spCenter.getSelectedItemPosition()>0){
//                    getGroupsFromCenterMeetingTable(userId,spCenter.getSelectedItem().toString());
//                }
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
//
//        spGroup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                if(spGroup.getSelectedItemPosition()>0){
//                    getHeadersFromEligibilityTable(userId,spGroup.getSelectedItem().toString());
//                }
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });

        configureDagger();
        configureViewModel();

    }

    private void insertEligibleLoans(List<EligibilityByGroupDTO> listOfEligibilityList) {
        try{

            List<List<EligibilityTable>> listOfEligibilityListNew=new ArrayList<>();
            if(listOfEligibilityList!=null && listOfEligibilityList.size()>0){
                for(EligibilityByGroupDTO eligibilityByGroupDTO : listOfEligibilityList){
                    if(eligibilityByGroupDTO !=null && eligibilityByGroupDTO.getListOfEligibilityProductList() !=null &&
                      eligibilityByGroupDTO.getListOfEligibilityProductList().size()>0){
                        for(List<EligibilityTable> eligibilityTableList : eligibilityByGroupDTO.getListOfEligibilityProductList()){
                            listOfEligibilityListNew.add(eligibilityTableList);
                        }
                    }
                }
            }

            viewModel.insertEligibleLoans(listOfEligibilityListNew);
            if (viewModel.getStringLiveData() != null) {
                Observer observer = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                       viewModel.getStringLiveData().removeObserver(this);
                       String centerId=(String)o;

                       if(!TextUtils.isEmpty(centerId)) {
                           // TODO: Open Loan Application Module
                           getCenterCreationTableByCenterId(userId, loanType, centerId);
                       }
                    }
                };
                viewModel.getStringLiveData().observe(this, observer);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private void getCenterCreationTableByCenterId(String userId,String loanType,String centerId) {
        try {
            appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();
            viewModel.getCenterCreationTableByCenterId(userId,loanType,centerId);
            if (viewModel.getCenterCreationTableLiveData() != null) {
                Observer getCenterCreationTableObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        appHelper.getDialogHelper().getLoadingDialog().closeDialog();
                        viewModel.getCenterCreationTableLiveData().removeObserver(this);
                        CenterCreationTable centerCreationTable = (CenterCreationTable) o;

                        if (centerCreationTable != null ) {
                            Intent application = new Intent(EligibilityActivity.this, EligibleNewLoanHomeActivity.class);
                            application.putExtra(PARAM_LOAN_TYPE, loanType);
                            application.putExtra(PARAM_MODULE_TYPE, MODULE_TYPE_LOAN_APPLICATION);
                            application.putExtra(PARAM_USER_NAME, userName);
                            application.putExtra(PARAM_BRANCH_ID, branchId);
                            application.putExtra(PARAM_BRANCH_GST_CODE, branchGSTcode);
                            application.putExtra(PARAM_USER_ID,userId);
                            application.putExtra(PARAM_CLIENT_ID, centerId);

                            String centerTableJson=new Gson().toJson(centerCreationTable,CenterCreationTable.class);
                            if(!TextUtils.isEmpty(centerTableJson)){
                                application.putExtra(PARAM_CENTER_TABLE_JSON, centerTableJson);
                            }
                            startActivity(application);

                        }
                    }
                };
                viewModel.getCenterCreationTableLiveData().observe(this, getCenterCreationTableObserver);
            }
        }catch (Exception ex){
            ex.printStackTrace();
            appHelper.getDialogHelper().getLoadingDialog().closeDialog();
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
            CENTER_NAME = getIntent().getStringExtra(PARAM_CENTER_NAME);

            // TODO: Getting headers tvName Eligibility Table
            if(! TextUtils.isEmpty(CENTER_NAME)) {
                getHeadersFromEligibilityTable(userId, CENTER_NAME);
            }


        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    @Override
    public void onBackPressed() {
        finish();

    }

//    public void getVillagesFromCenterMeetingTable(String userId) {
//        appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();
//
//        try {
//            viewModel.getVillagesFromCenterMeetingTable(userId);
//            if (viewModel.getStringListLiveData() != null) {
//                Observer observer = new Observer() {
//                    @Override
//                    public void onChanged(@Nullable Object o) {
//                        appHelper.getDialogHelper().getLoadingDialog().closeDialog();
//                        List<String> stringList = (List<String>) o;
//                        viewModel.getStringListLiveData().removeObserver(this);
//
//                        rvLeadDetails.setVisibility(View.GONE);
//                        rlEligibilityHeader.setVisibility(View.GONE);
//                        rlNoLeads.setVisibility(View.VISIBLE);
//
//                        if(stringList !=null && stringList.size()>0){
//                            stringList.add(0,"SELECT VILLAGE");
//                                ArrayAdapter arrayAdapter = new ArrayAdapter(EligibilityActivity.this, R.layout.view_spinner_textheight, stringList);
//                                spVillage.setAdapter(arrayAdapter);
//
//                                spCenter.setAdapter(null);
//                                spGroup.setAdapter(null);
//                        }
//
//
//                    }
//                };
//                viewModel.getStringListLiveData().observe(this, observer);
//            }
//        } catch (Exception ex) {
//            appHelper.getDialogHelper().getLoadingDialog().closeDialog();
//            ex.printStackTrace();
//        }
//    }
//
//    public void getCentersFromCenterMeetingTable(String userId,String villageName) {
//        appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();
//
//        try {
//            viewModel.getCentersFromCenterMeetingTable(userId,villageName);
//            if (viewModel.getStringListLiveData() != null) {
//                Observer observer = new Observer() {
//                    @Override
//                    public void onChanged(@Nullable Object o) {
//                        appHelper.getDialogHelper().getLoadingDialog().closeDialog();
//                        List<String> stringList = (List<String>) o;
//                        viewModel.getStringListLiveData().removeObserver(this);
//
//                        rvLeadDetails.setVisibility(View.GONE);
//                        rlEligibilityHeader.setVisibility(View.GONE);
//                        rlNoLeads.setVisibility(View.VISIBLE);
//
//                        if(stringList !=null && stringList.size()>0){
//                            stringList.add(0,"SELECT CENTER");
//                            ArrayAdapter arrayAdapter = new ArrayAdapter(EligibilityActivity.this, R.layout.view_spinner_textheight, stringList);
//                            spCenter.setAdapter(arrayAdapter);
//
//                            spGroup.setAdapter(null);
//                        }
//
//
//                    }
//                };
//                viewModel.getStringListLiveData().observe(this, observer);
//            }
//        } catch (Exception ex) {
//            appHelper.getDialogHelper().getLoadingDialog().closeDialog();
//            ex.printStackTrace();
//        }
//    }
//
//    public void getGroupsFromCenterMeetingTable(String userId,String centerName) {
//        appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();
//
//        try {
//            viewModel.getGroupsFromCenterMeetingTable(userId,centerName);
//            if (viewModel.getStringListLiveData() != null) {
//                Observer observer = new Observer() {
//                    @Override
//                    public void onChanged(@Nullable Object o) {
//                        appHelper.getDialogHelper().getLoadingDialog().closeDialog();
//                        List<String> stringList = (List<String>) o;
//                        viewModel.getStringListLiveData().removeObserver(this);
//
//                        rvLeadDetails.setVisibility(View.GONE);
//                        rlEligibilityHeader.setVisibility(View.GONE);
//                        rlNoLeads.setVisibility(View.VISIBLE);
//
//                        if(stringList !=null && stringList.size()>0){
//                            stringList.add(0,"SELECT GROUP");
//                            ArrayAdapter arrayAdapter = new ArrayAdapter(EligibilityActivity.this, R.layout.view_spinner_textheight, stringList);
//                            spGroup.setAdapter(arrayAdapter);
//                        }
//
//
//                    }
//                };
//                viewModel.getStringListLiveData().observe(this, observer);
//            }
//        } catch (Exception ex) {
//            appHelper.getDialogHelper().getLoadingDialog().closeDialog();
//            ex.printStackTrace();
//        }
//    }

    public void getMembersFromCenterMeetingTable(String userId,String centerName,List<String> productCodeHeadList) {
        appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();

        try {
            viewModel.getMembersFromCenterMeetingTable(userId,centerName);
            if (viewModel.getEligibilityByGroupLiveDataList() != null) {
                Observer observer = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        appHelper.getDialogHelper().getLoadingDialog().closeDialog();
                        List<EligibilityByGroupDTO> eligibilityTableList = (List<EligibilityByGroupDTO>) o;
                        viewModel.getEligibilityByGroupLiveDataList().removeObserver(this);

                        if(eligibilityTableList !=null && eligibilityTableList.size()>0){
                            rlNoLeads.setVisibility(View.GONE);
                            rvLeadDetails.setVisibility(View.VISIBLE);
                            rlEligibilityHeader.setVisibility(View.VISIBLE);

                            if (eligibilityAdapter != null) {
                                eligibilityAdapter.setItem(eligibilityTableList,productCodeHeadList);
                            }

                        }else{
                            rvLeadDetails.setVisibility(View.GONE);
                            rlEligibilityHeader.setVisibility(View.GONE);
                            rlNoLeads.setVisibility(View.VISIBLE);
                        }


                    }
                };
                viewModel.getEligibilityByGroupLiveDataList().observe(this, observer);
            }
        } catch (Exception ex) {
            appHelper.getDialogHelper().getLoadingDialog().closeDialog();
            ex.printStackTrace();
        }
    }
//    public void getHeadersFromEligibilityTable( String userId,String groupName) {
//        appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();
//
//        try {
//            viewModel.getHeadersFromEligibilityTable();
//            if (viewModel.getStringListLiveData() != null) {
//                Observer observer = new Observer() {
//                    @Override
//                    public void onChanged(@Nullable Object o) {
//                        appHelper.getDialogHelper().getLoadingDialog().closeDialog();
//                        List<String> productCodeHeaderList = (List<String>) o;
//                        viewModel.getStringListLiveData().removeObserver(this);
//
//                        if(productCodeHeaderList !=null && productCodeHeaderList.size()>0){
//                            rlNoLeads.setVisibility(View.GONE);
//                            rvLeadDetails.setVisibility(View.VISIBLE);
//                            rlEligibilityHeader.setVisibility(View.VISIBLE);
//
//                            if(eligibilityHeaderAdapter !=null){
//                                eligibilityHeaderAdapter.setItem(productCodeHeaderList);
//                            }
//
//                            getMembersFromCenterMeetingTable(userId,groupName,productCodeHeaderList);
//
//                        }else{
//                            rvLeadDetails.setVisibility(View.GONE);
//                            rlEligibilityHeader.setVisibility(View.GONE);
//                            rlNoLeads.setVisibility(View.VISIBLE);
//                        }
//
//                    }
//                };
//                viewModel.getStringListLiveData().observe(this, observer);
//            }
//        } catch (Exception ex) {
//            appHelper.getDialogHelper().getLoadingDialog().closeDialog();
//            ex.printStackTrace();
//        }
//    }

    public void getHeadersFromEligibilityTable( String userId,String centerName) {
        appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();

        try {
            viewModel.getHeadersFromEligibilityTable();
            if (viewModel.getStringListLiveData() != null) {
                Observer observer = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        appHelper.getDialogHelper().getLoadingDialog().closeDialog();
                        List<String> productCodeHeaderList = (List<String>) o;
                        viewModel.getStringListLiveData().removeObserver(this);

                        if(productCodeHeaderList !=null && productCodeHeaderList.size()>0){
                            rlNoLeads.setVisibility(View.GONE);
                            rvLeadDetails.setVisibility(View.VISIBLE);
                            rlEligibilityHeader.setVisibility(View.VISIBLE);

                            if(eligibilityHeaderAdapter !=null){
                                eligibilityHeaderAdapter.setItem(productCodeHeaderList);
                            }

                            getMembersFromCenterMeetingTable(userId,centerName,productCodeHeaderList);

                        }else{
                            rvLeadDetails.setVisibility(View.GONE);
                            rlEligibilityHeader.setVisibility(View.GONE);
                            rlNoLeads.setVisibility(View.VISIBLE);
                        }

                    }
                };
                viewModel.getStringListLiveData().observe(this, observer);
            }
        } catch (Exception ex) {
            appHelper.getDialogHelper().getLoadingDialog().closeDialog();
            ex.printStackTrace();
        }
    }



}
