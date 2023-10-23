package com.saartak.el.activities;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.text.method.DigitsKeyListener;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.SearchView;
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
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;
import com.saartak.el.BuildConfig;
import com.saartak.el.R;
import com.saartak.el.adapter.LoanApplicationSummaryAdapter;
import com.saartak.el.database.entity.CGTTable;
import com.saartak.el.database.entity.CenterCreationTable;
import com.saartak.el.database.entity.LoanTable;
import com.saartak.el.database.entity.MasterTable;
import com.saartak.el.database.entity.RawDataTable;
import com.saartak.el.database.entity.SubmitDataTable;
import com.saartak.el.helper.MyDividerItemDecoration;
import com.saartak.el.models.RawDataResponseDTO;
import com.saartak.el.view_models.DynamicUIViewModel;

import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

import static com.saartak.el.constants.AppConstant.DATE_FORMAT_DD_MM_YYYY2;
import static com.saartak.el.constants.AppConstant.MODULE_TYPE_LOAN_APPLICATION;
import static com.saartak.el.constants.AppConstant.MODULE_TYPE_LOAN_PROPOSAL_WITH_NOMINEE;
import static com.saartak.el.constants.AppConstant.PARAM_BRANCH_GST_CODE;
import static com.saartak.el.constants.AppConstant.PARAM_BRANCH_ID;
import static com.saartak.el.constants.AppConstant.PARAM_CALLING_FROM_CONNECT_APP;
import static com.saartak.el.constants.AppConstant.PARAM_CENTER_TABLE_JSON;
import static com.saartak.el.constants.AppConstant.PARAM_CGT_TABLE_JSON;
import static com.saartak.el.constants.AppConstant.PARAM_CLIENT_ID;
import static com.saartak.el.constants.AppConstant.PARAM_LOAN_TYPE;
import static com.saartak.el.constants.AppConstant.PARAM_MODULE_TYPE;
import static com.saartak.el.constants.AppConstant.PARAM_PRODUCT_ID;
import static com.saartak.el.constants.AppConstant.PARAM_USER_ID;
import static com.saartak.el.constants.AppConstant.PARAM_USER_NAME;
import static com.saartak.el.constants.AppConstant.PRODUCT_ID_JLG;
import static com.saartak.el.dynamicui.constants.ParametersConstant.CURRENT_STAGE_APPLICATION;
import static com.saartak.el.dynamicui.constants.ParametersConstant.ERROR_MESSAGE_SYNC_FAILED;
import static com.saartak.el.dynamicui.constants.ParametersConstant.ERROR_MESSAGE_SYNC_SUCCESS;
import static com.saartak.el.dynamicui.constants.ParametersConstant.FINAL_STATUS_SUBMITTED;
import static com.saartak.el.dynamicui.constants.ParametersConstant.LOAN_PRODUCT_CODE_EMERGENCY_LOAN;

public class LoanApplicationSummaryActivity extends LOSBaseActivity implements LoanApplicationSummaryAdapter.SyncCallbackInterface {


    private static final String TAG = LoanApplicationSummaryActivity.class.getCanonicalName();
    RecyclerView rvLeadDetails;
    SearchView searchByPhoneNo;
    LoanApplicationSummaryAdapter loanApplicationSummaryAdapter;

    private Toolbar toolbar;
    TextView tvClientName;
    TextView tvAppVersion,tvCurrentDate;
    String SCREEN_NO;
    boolean isFromConnectApp;
    CGTTable CGT_TABLE;

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView navigationView;
    CoordinatorLayout coordinatorLayout;
    ImageView ivStaffImage;
    RelativeLayout rlNoLeads;
    TextView tvStaffId, tvStaffName, tvSOBDate;
    TextView tv_center_name,tv_center_id;
    FloatingActionButton btnNewTarget;

    CenterCreationTable CENTER_CREATION_TABLE;

//    public String userName,userId,branchId;

    List<RawDataTable> allClientRawDataTableList;

    @Inject
    public ViewModelProvider.Factory viewModelFactory;
    public DynamicUIViewModel viewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_loan_application_summary);
        rvLeadDetails = (RecyclerView) findViewById(R.id.rv_lead_details);
        rlNoLeads = (RelativeLayout) findViewById(R.id.rl_no_leads);
        searchByPhoneNo = (SearchView) findViewById(R.id.sv_phone_no);
        btnNewTarget =  findViewById(R.id.fb_add_target_details);
//        spinnerSortByDate = (AppCompatSpinner) findViewById(R.id.sp_sort_by_date);
//        spinnerSortByLoanType = (AppCompatSpinner) findViewById(R.id.sp_sort_by_loan_type);
//        spinnerSortByInterested = (AppCompatSpinner) findViewById(R.id.sp_sort_by_interest_or_not);
//        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tvCurrentDate = (TextView)findViewById(R.id.tv_currentdate);
        tvAppVersion = (TextView)findViewById(R.id.tv_app_version);
        tvClientName = (TextView) findViewById(R.id.tv_client_name_custom);
        tv_center_id = (TextView) findViewById(R.id.tv_center_id);
        tv_center_name = (TextView) findViewById(R.id.tv_center_name);
        EditText searchEditText = (EditText) searchByPhoneNo.findViewById(androidx.appcompat.R.id.search_src_text);
        searchEditText.setKeyListener(DigitsKeyListener.getInstance("abcdefghijklmnopqrstuvwxyz ABCDEFGHIJKLMNOPQRSTUVWXYZ 0123456789"));
        searchEditText.setRawInputType(InputType.TYPE_TEXT_VARIATION_PERSON_NAME);
        searchEditText.setTextColor(getResources().getColor(R.color.black));
        searchEditText.setHintTextColor(getResources().getColor(R.color.black));

        // TODO: Search view
        ImageView icon = searchByPhoneNo.findViewById(androidx.appcompat.R.id.search_button);
        icon.setImageResource(R.drawable.ic_search_black_24dp);
        icon.setColorFilter(Color.RED);

        //fyfftyd
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);

        actionBarDrawerToggle.getDrawerArrowDrawable().setColor(Color.BLACK);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        actionBarDrawerToggle.syncState();
        setUpDrawerContent(navigationView);
        View header = navigationView.getHeaderView(0);
        ivStaffImage = header.findViewById(R.id.iv_staff_image);
        tvStaffId = header.findViewById(R.id.tv_staff_id);
        tvStaffName = header.findViewById(R.id.tv_staff_name);
        tvSOBDate = header.findViewById(R.id.tv_sod_date);

        userName = getIntent().getStringExtra(PARAM_USER_NAME);
        userId = getIntent().getStringExtra(PARAM_USER_ID);
        branchId = getIntent().getStringExtra(PARAM_BRANCH_ID);
        branchGSTcode = getIntent().getStringExtra(PARAM_BRANCH_GST_CODE);
        loanType = getIntent().getStringExtra(PARAM_LOAN_TYPE);
        productId = getIntent().getStringExtra(PARAM_PRODUCT_ID);
        moduleType = getIntent().getStringExtra(PARAM_MODULE_TYPE);
        CLIENT_ID = getIntent().getStringExtra(PARAM_CLIENT_ID);
        isFromConnectApp = getIntent().getBooleanExtra(PARAM_CALLING_FROM_CONNECT_APP,false);
        String centerJsonString = getIntent().getStringExtra(PARAM_CENTER_TABLE_JSON);
        if (!TextUtils.isEmpty(centerJsonString)) {
            CENTER_CREATION_TABLE = new Gson().fromJson(centerJsonString, CenterCreationTable.class);
            if (CENTER_CREATION_TABLE != null) {
                if( ! TextUtils.isEmpty(CENTER_CREATION_TABLE.getCenterName())) {
                    tv_center_name.setText(CENTER_CREATION_TABLE.getCenterName());
                }
                if( ! TextUtils.isEmpty(CENTER_CREATION_TABLE.getCenterId())) {
                    tv_center_id.setText(CENTER_CREATION_TABLE.getCenterId());
                }
            }
        }
        String cgtJsonString = getIntent().getStringExtra(PARAM_CGT_TABLE_JSON);
        if (!TextUtils.isEmpty(cgtJsonString)) {
            CGT_TABLE = new Gson().fromJson(cgtJsonString, CGTTable.class);
        }

        if (!TextUtils.isEmpty(userName)) {
            tvClientName.setText(userName.toUpperCase());
            tvStaffName.setText(userName.toUpperCase());
        }
        if (!TextUtils.isEmpty(userId)) {
            tvStaffId.setText(userId);
        }
        String currentDate = appHelper.getCurrentDate(DATE_FORMAT_DD_MM_YYYY2);
        if (!TextUtils.isEmpty(currentDate)) {
            tvSOBDate.setText("SOD DATE : " + currentDate);
            tvCurrentDate.setText(currentDate);
        }
        String appVersion = BuildConfig.VERSION_NAME;
        if (!TextUtils.isEmpty(appVersion)) {
            tvAppVersion.setText(getResources().getString(R.string.version)+" "+appVersion);
        }

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        rvLeadDetails.setLayoutManager(mLayoutManager);
        rvLeadDetails.setItemAnimator(new DefaultItemAnimator());
        rvLeadDetails.addItemDecoration(new MyDividerItemDecoration(this, DividerItemDecoration.VERTICAL, 36));
        rvLeadDetails.setNestedScrollingEnabled(false);


        // Associate searchable configuration with the SearchView
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchByPhoneNo.setSearchableInfo(searchManager
                .getSearchableInfo(getComponentName()));
        searchByPhoneNo.setMaxWidth(Integer.MAX_VALUE);
        // listening to search query text change
        searchByPhoneNo.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // filter recycler view when query submitted
              /*  spinnerSortByDate.setSelection(0);
                spinnerSortByLoanType.setSelection(0);
                spinnerSortByInterested.setSelection(0);*/

                if (loanApplicationSummaryAdapter != null && loanApplicationSummaryAdapter.getFilter() != null) {
                    loanApplicationSummaryAdapter.getFilter().filter(query);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                // filter recycler view when text is changed
             /*   spinnerSortByDate.setSelection(0);
                spinnerSortByLoanType.setSelection(0);
                spinnerSortByInterested.setSelection(0);*/

                if (loanApplicationSummaryAdapter != null && loanApplicationSummaryAdapter.getFilter() != null) {
                    loanApplicationSummaryAdapter.getFilter().filter(query);
                }
                return false;
            }
        });

        configureDagger();
        configureViewModel();


        if(isFromConnectApp){
            getRawDataFromServerForSingleCustomer();
        }else {
            // TODO: Get Loan Table Details
            getLoanTableByCenterId();
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
            moduleType = getIntent().getStringExtra(PARAM_MODULE_TYPE);
            CLIENT_ID = getIntent().getStringExtra(PARAM_CLIENT_ID);
            isFromConnectApp = getIntent().getBooleanExtra(PARAM_CALLING_FROM_CONNECT_APP,false);
            String centerJsonString = getIntent().getStringExtra(PARAM_CENTER_TABLE_JSON);
            if (!TextUtils.isEmpty(centerJsonString)) {
                CENTER_CREATION_TABLE = new Gson().fromJson(centerJsonString, CenterCreationTable.class);
            }
            String cgtJsonString = getIntent().getStringExtra(PARAM_CGT_TABLE_JSON);
            if (!TextUtils.isEmpty(cgtJsonString)) {
                CGT_TABLE = new Gson().fromJson(cgtJsonString, CGTTable.class);
            }

           /* spinnerSortByDate.setSelection(0);
            spinnerSortByLoanType.setSelection(0);
            spinnerSortByInterested.setSelection(0);*/
            searchByPhoneNo.setQuery("", false);
            searchByPhoneNo.setIconified(true);


        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }



    private void syncMemberLoanApplicationDetail(LoanTable loanTable) {
        try {
            if (appHelper.isNetworkAvailable()) {

                appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();

                viewModel.syncMemberLoanApplicationDetail(loanTable);
                if (viewModel.getMemberLoanDetailTableLiveData() != null) {
                    Observer syncSingleClientDataObserver = new Observer() {
                        @Override
                        public void onChanged(@Nullable Object o) {
                            appHelper.getDialogHelper().getLoadingDialog().closeDialog();
                            LoanTable loanTableResponse = (LoanTable) o;
                            viewModel.getMemberLoanDetailTableLiveData().removeObserver(this);

                            // TODO: Need to remove this
                            if (loanTableResponse != null && loanTableResponse.isSync()) {
                                if(loanTableResponse.getLoanProductCode().equalsIgnoreCase(LOAN_PRODUCT_CODE_EMERGENCY_LOAN)){
                                    // TODO: Sync Data to BCM
                                    syncLoanDetailsToBCM(loanTable);
                                }else{
                                    appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.SUCCESS,
                                            ERROR_MESSAGE_SYNC_SUCCESS, new ConfirmationDialog.ActionCallback() {
                                                @Override
                                                public void onAction() {
                                                    getLoanTableByCenterId();
                                                }
                                            });
                                }



                            } else {
                                String errorMsg = ERROR_MESSAGE_SYNC_FAILED;
                                if (loanTableResponse != null && !TextUtils.isEmpty(loanTableResponse.getResponse())) {
                                    errorMsg = loanTableResponse.getResponse();
                                }
                                appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ERROR,
                                        errorMsg, new ConfirmationDialog.ActionCallback() {
                                            @Override
                                            public void onAction() {
                                                getLoanTableByCenterId();
                                            }
                                        });
                            }
                        }
                    };
                    viewModel.getMemberLoanDetailTableLiveData().observe(this, syncSingleClientDataObserver);
                }
            } else {
                appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT,
                        "Please check your internet connection and try again");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            appHelper.getDialogHelper().getLoadingDialog().closeDialog();

            INSERT_LOG("syncAllScreenByClient","Exception : "+ex.getMessage());
        }
    }

    private void syncLoanDetailsToBCM(LoanTable loanTable) {
        try {
            if (appHelper.isNetworkAvailable()) {
                appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();

                viewModel.syncLoanDetailsToBCM(loanTable);
                if (viewModel.getSubmitDataTableLiveData() != null) {
                    Observer syncDataToServerObserver = new Observer() {
                        @Override
                        public void onChanged(@Nullable Object o) {
                            appHelper.getDialogHelper().getLoadingDialog().closeDialog();
                            SubmitDataTable submitDataTable = (SubmitDataTable) o;
                            viewModel.getSubmitDataTableLiveData().removeObserver(this);

                            appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.SUCCESS,
                                    ERROR_MESSAGE_SYNC_SUCCESS, new ConfirmationDialog.ActionCallback() {
                                        @Override
                                        public void onAction() {
                                            getLoanTableByCenterId();
                                        }
                                    });
                        }
                    };
                    viewModel.getSubmitDataTableLiveData().observe(this, syncDataToServerObserver);
                }
            } else {
                appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT,
                        "Please check your internet connection and try again");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            appHelper.getDialogHelper().getLoadingDialog().closeDialog();

            INSERT_LOG("syncDataToServer","Exception : "+ex.getMessage());
        }
    }


    private void updateMemberLoanDetailTableStatus(LoanTable loanTable, String finalStatus) {
        try {
            viewModel.updateMemberLoanDetailTableStatus(loanTable, finalStatus);
            if (viewModel.getMemberLoanDetailTableLiveData() != null) {
                Observer updateMasterTableStatusObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        LoanTable loanTableResult = (LoanTable) o;
                        viewModel.getMemberLoanDetailTableLiveData().removeObserver(this);
                        if (loanTableResult != null && loanTableResult.isAllDataCaptured()) {
                            getLoanTableByCenterId();
                        } else {
                            String errorMsg = "Unable to update data";
                            if (loanTableResult != null && !TextUtils.isEmpty(loanTableResult.getResponse())) {
                                errorMsg = loanTableResult.getResponse();
                            }
                            appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ERROR, errorMsg);
                        }
                    }
                };
                viewModel.getMemberLoanDetailTableLiveData().observe(this, updateMasterTableStatusObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();

        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void selectItemDrawer(MenuItem menuItem) {
        try {
            switch (menuItem.getItemId()) {
                case R.id.menu_item_new_lead:
                    drawerLayout.closeDrawer(Gravity.LEFT);
                   /* appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT,
                            "This Feature Is Not Yet Developed");*/
                    break;
                case R.id.menu_item_tasks:
                    appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT,
                            "This Feature Is Not Yet Developed");
                    break;
                case R.id.menu_item_search:
                    appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT,
                            "This Feature Is Not Yet Developed");
                    break;
                case R.id.menu_item_settings:
                    appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT,
                            "This Feature Is Not Yet Developed");
                    break;
                case R.id.menu_item_logout:
                    appHelper.getDialogHelper().getConfirmationDialog().showTwoButtons("Do you want to logout ? ", new ConfirmationDialog.ActionCallback() {
                        @Override
                        public void onAction() {
                            finish();
                        }
                    });
                    break;
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void setUpDrawerContent(NavigationView navigationView) {
        try {
            navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    selectItemDrawer(menuItem);
                    return true;
                }
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    @Override
    public void onBackPressed() {
        finish();

    }


    public void getLoanTableByClientId() {
        appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();

        try {
            viewModel.getLoanTableByClientId(CLIENT_ID);
            if (viewModel.getMemberLoanDetailTableLiveDataList() != null) {
                Observer observer = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        appHelper.getDialogHelper().getLoadingDialog().closeDialog();
                        List<LoanTable> loanTableList = (List<LoanTable>) o;
                        viewModel.getMemberLoanDetailTableLiveDataList().removeObserver(this);

                        if (loanTableList != null && loanTableList.size() > 0) {
                            rlNoLeads.setVisibility(View.GONE);
                            rvLeadDetails.setVisibility(View.VISIBLE);

                            if (loanApplicationSummaryAdapter != null) {
                                loanApplicationSummaryAdapter.setItem(loanTableList);
                            } else {
                                loanApplicationSummaryAdapter = new LoanApplicationSummaryAdapter(LoanApplicationSummaryActivity.this, loanTableList,
                                        LoanApplicationSummaryActivity.this, appHelper);
                                rvLeadDetails.setAdapter(loanApplicationSummaryAdapter);
                                loanApplicationSummaryAdapter.notifyDataSetChanged();
                            }

                        } else {
                            rvLeadDetails.setVisibility(View.GONE);
                            rlNoLeads.setVisibility(View.VISIBLE);
                        }
                    }
                };
                viewModel.getMemberLoanDetailTableLiveDataList().observe(this, observer);
            }
        }catch (Exception ex){
            appHelper.getDialogHelper().getLoadingDialog().closeDialog();
            ex.printStackTrace();

            INSERT_LOG("getMasterTableByCGTTable","Exception : "+ex.getMessage());
        }
    }

    public void getLoanTableByCenterId() {
        appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();

        try {
            viewModel.getLoanTableByCenterId(CLIENT_ID,CGT_TABLE); // TODO: Nothing but center id
            if (viewModel.getMemberLoanDetailTableLiveDataList() != null) {
                Observer observer = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        appHelper.getDialogHelper().getLoadingDialog().closeDialog();
                        List<LoanTable> loanTableList = (List<LoanTable>) o;
                        viewModel.getMemberLoanDetailTableLiveDataList().removeObserver(this);

                        if (loanTableList != null && loanTableList.size() > 0) {
                            rlNoLeads.setVisibility(View.GONE);
                            rvLeadDetails.setVisibility(View.VISIBLE);

                            if (loanApplicationSummaryAdapter != null) {
                                loanApplicationSummaryAdapter.setItem(loanTableList);
                            } else {
                                loanApplicationSummaryAdapter = new LoanApplicationSummaryAdapter(LoanApplicationSummaryActivity.this, loanTableList,
                                        LoanApplicationSummaryActivity.this, appHelper);
                                rvLeadDetails.setAdapter(loanApplicationSummaryAdapter);
                                loanApplicationSummaryAdapter.notifyDataSetChanged();
                            }

                        } else {
                            rvLeadDetails.setVisibility(View.GONE);
                            rlNoLeads.setVisibility(View.VISIBLE);
                        }
                    }
                };
                viewModel.getMemberLoanDetailTableLiveDataList().observe(this, observer);
            }
        }catch (Exception ex){
            appHelper.getDialogHelper().getLoadingDialog().closeDialog();
            ex.printStackTrace();

            INSERT_LOG("getMasterTableByCGTTable","Exception : "+ex.getMessage());
        }
    }



    @Override
    public void syncCallback(int position, LoanTable loanTable) {
        try {
            syncMemberLoanApplicationDetail(loanTable);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void submitCallback(int position, LoanTable loanTable) {
        try {
            updateMemberLoanDetailTableStatus(loanTable, FINAL_STATUS_SUBMITTED);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void rejectCallback(int position, LoanTable loanTable) {
        try {
            // TODO: Reject functionality
//            updateMasterTableStatusForRejectAndSendback(loanTable,FINAL_STATUS_REJECTED);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void openScreenCallback(int position, LoanTable loanTable) {
        try{
            getCenterCreationTableByMemberLoanTable(loanTable);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private void getCenterCreationTableByMemberLoanTable(LoanTable loanTable) {
        try {
            appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();
            viewModel.getCenterCreationTableByMemberLoanTable(loanTable);
            if (viewModel.getCenterCreationTableLiveData() != null) {
                Observer getCenterCreationTableObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        appHelper.getDialogHelper().getLoadingDialog().closeDialog();
                        viewModel.getCenterCreationTableLiveData().removeObserver(this);
                        CenterCreationTable centerCreationTable = (CenterCreationTable) o;

                        if (centerCreationTable != null ) {
                           Intent application = new Intent(LoanApplicationSummaryActivity.this, LoanApplicationActivity.class);
 //                           Intent application = new Intent(LoanApplicationSummaryActivity.this, BaseActivity.class);
                            application.putExtra(PARAM_LOAN_TYPE, loanType);
                            if(loanTable !=null && loanTable.isExistingCustomer()) {
                                application.putExtra(PARAM_MODULE_TYPE, MODULE_TYPE_LOAN_APPLICATION);
                            }else {
                                application.putExtra(PARAM_MODULE_TYPE, MODULE_TYPE_LOAN_PROPOSAL_WITH_NOMINEE);
                            }
                            application.putExtra(PARAM_USER_NAME, userName);
                            application.putExtra(PARAM_BRANCH_ID, branchId);
                            application.putExtra(PARAM_BRANCH_GST_CODE, branchGSTcode);
                            application.putExtra(PARAM_USER_ID, loanTable.getCreatedBy());
                            application.putExtra(PARAM_CLIENT_ID, loanTable.getClientId());
                            application.putExtra(PARAM_PRODUCT_ID, productId);

                            String centerTableJson=new Gson().toJson(centerCreationTable,CenterCreationTable.class);
                            if(!TextUtils.isEmpty(centerTableJson)){
                                application.putExtra(PARAM_CENTER_TABLE_JSON, centerTableJson);
                            }
                            startActivity(application);

                        }else{
                            appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT,
                                    "No Applicant Details");
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

    private void INSERT_LOG(String methodName, String message){
        try{
            if(viewModel !=null){
                viewModel.insertLog(methodName,message,userId,"","TargetDetailsActivity"
                        ,CLIENT_ID,loanType,moduleType);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private void getRawDataFromServerForSingleCustomer() {
        try {
            if (appHelper.isNetworkAvailable()) {

                appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();

                viewModel.getRawDataFromServerForSingleCustomer(CLIENT_ID,userId,loanType,PRODUCT_ID_JLG);
                if (viewModel.getRawDataFromServerList() != null) {
                    Observer syncDataToServerObserver = new Observer() {
                        @Override
                        public void onChanged(@Nullable Object o) {
                            appHelper.getDialogHelper().getLoadingDialog().closeDialog();

                            List<RawDataResponseDTO.Table> rawDataFromServerList = (List<RawDataResponseDTO.Table>) o;
                            viewModel.getRawDataFromServerList().removeObserver(this);
                            if(rawDataFromServerList !=null && rawDataFromServerList.size()>0){

                                getMetaDataForMultipleScreen(rawDataFromServerList,loanType,userId);

                            }else{
                                // TODO: Get Member Table Details
                                getLoanTableByClientId();
                            }
                        }
                    };
                    viewModel.getRawDataFromServerList().observe(this, syncDataToServerObserver);
                }
            } else {
                appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT,
                        "Please check your internet connection and try again");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            appHelper.getDialogHelper().getLoadingDialog().closeDialog();
            INSERT_LOG("getRawDataFromServer","Exception : "+ex.getMessage());
        }
    }

    private void getMetaDataForMultipleScreen( List<RawDataResponseDTO.Table> rawDataFromServerList,
                                               String loanType,String userId){
        try{

            appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();
            viewModel.getMetaDataForMultipleScreen(rawDataFromServerList,loanType,userId,productId,CURRENT_STAGE_APPLICATION);
            if (viewModel.getmasterTableLiveDataList() != null) {
                Observer getMasterTableDetailsObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        appHelper.getDialogHelper().getLoadingDialog().closeDialog();
                        List<MasterTable> masterTableList = (List<MasterTable>) o;
                        viewModel.getmasterTableLiveDataList().removeObserver(this);

                        // TODO: Get Member Table Details
                        getLoanTableByClientId();
                    }
                };
                viewModel.getmasterTableLiveDataList().observe(this, getMasterTableDetailsObserver);
            }
        }catch (Exception ex){
            ex.printStackTrace();
            appHelper.getDialogHelper().getLoadingDialog().closeDialog();
        }
    }

}
