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
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.AppCompatSpinner;
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
import com.saartak.el.adapter.CentersSummaryAdapter;
import com.saartak.el.constants.AppConstant;
import com.saartak.el.database.entity.CenterCreationTable;
import com.saartak.el.database.entity.RawDataTable;
import com.saartak.el.database.entity.SubmitDataTable;
import com.saartak.el.helper.MyDividerItemDecoration;
import com.saartak.el.view_models.DynamicUIViewModel;

import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

import static com.saartak.el.constants.AppConstant.DATE_FORMAT_DD_MM_YYYY2;
import static com.saartak.el.constants.AppConstant.MODULE_TYPE_ADD_OR_REMOVE_MEMBER;
import static com.saartak.el.constants.AppConstant.MODULE_TYPE_DROP_OUT_CUSTOMER;
import static com.saartak.el.constants.AppConstant.MODULE_TYPE_HOUSE_VERIFICATION_CGT;
import static com.saartak.el.constants.AppConstant.MODULE_TYPE_LOAN_APPLICATION;
import static com.saartak.el.constants.AppConstant.MODULE_TYPE_TARGET_DETAILS;
import static com.saartak.el.constants.AppConstant.PARAM_BRANCH_GST_CODE;
import static com.saartak.el.constants.AppConstant.PARAM_BRANCH_ID;
import static com.saartak.el.constants.AppConstant.PARAM_CENTER_TABLE_JSON;
import static com.saartak.el.constants.AppConstant.PARAM_CLIENT_ID;
import static com.saartak.el.constants.AppConstant.PARAM_LOAN_TYPE;
import static com.saartak.el.constants.AppConstant.PARAM_MODULE_TYPE;
import static com.saartak.el.constants.AppConstant.PARAM_PRODUCT_ID;
import static com.saartak.el.constants.AppConstant.PARAM_USER_ID;
import static com.saartak.el.constants.AppConstant.PARAM_USER_NAME;
import static com.saartak.el.dynamicui.constants.ParametersConstant.CENTERS_SUMMARY_HEADER_ADD_OR_REMOVE_CUSTOMER;
import static com.saartak.el.dynamicui.constants.ParametersConstant.CENTERS_SUMMARY_HEADER_FOR_HOUSE_VERIFICATION;
import static com.saartak.el.dynamicui.constants.ParametersConstant.CENTERS_SUMMARY_HEADER_LOAN_APPLICATION;
import static com.saartak.el.dynamicui.constants.ParametersConstant.CENTERS_SUMMARY_HEADER_TARGET_DETAILS;
import static com.saartak.el.dynamicui.constants.ParametersConstant.CENTERS_SUMMARY_HEADER_TO_DROP_OUT_CUSTOMER;

public class CentersSummaryActivity extends LOSBaseActivity implements CentersSummaryAdapter.SyncCallbackInterface {


    private static final String TAG = CentersSummaryActivity.class.getCanonicalName();
    RecyclerView rvLeadDetails;
    SearchView searchByPhoneNo;
    AppCompatSpinner spinnerSortByDate,  spinnerSortByAlphabet;
    CentersSummaryAdapter centersSummaryAdapter;

    private Toolbar toolbar;
    TextView tvClientName,tvCentersSummaryHeader;
    TextView tvAppVersion,tvCurrentDate;
    String SCREEN_NO;

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView navigationView;
    CoordinatorLayout coordinatorLayout;
    ImageView ivStaffImage;
    RelativeLayout rlNoLeads;
    TextView tvStaffId, tvStaffName, tvSOBDate;
    FloatingActionButton btnNewTarget;

//    public String userName,userId,branchId;

    List<RawDataTable> allClientRawDataTableList;

    @Inject
    public ViewModelProvider.Factory viewModelFactory;
    public DynamicUIViewModel viewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_centers_summary);
        rvLeadDetails = (RecyclerView) findViewById(R.id.rv_lead_details);
        rlNoLeads = (RelativeLayout) findViewById(R.id.rl_no_leads);
        searchByPhoneNo = (SearchView) findViewById(R.id.sv_phone_no);
        btnNewTarget =  findViewById(R.id.fb_add_target_details);
        spinnerSortByDate = (AppCompatSpinner) findViewById(R.id.sp_sort_by_date);
        spinnerSortByAlphabet = (AppCompatSpinner) findViewById(R.id.sp_sort_by_alphabetical_order);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tvCurrentDate = (TextView)findViewById(R.id.tv_currentdate);
        tvAppVersion = (TextView)findViewById(R.id.tv_app_version);
        tvClientName = (TextView) findViewById(R.id.tv_client_name_custom);
        tvCentersSummaryHeader = (TextView) findViewById(R.id.tv_centers_summary_header);
        EditText searchEditText = (EditText) searchByPhoneNo.findViewById(androidx.appcompat.R.id.search_src_text);
        searchEditText.setKeyListener(DigitsKeyListener.getInstance("abcdefghijklmnopqrstuvwxyz ABCDEFGHIJKLMNOPQRSTUVWXYZ 0123456789"));
        searchEditText.setRawInputType(InputType.TYPE_TEXT_VARIATION_PERSON_NAME);
        searchEditText.setTextColor(getResources().getColor(R.color.black));
        searchEditText.setHintTextColor(getResources().getColor(R.color.black));

        // TODO: Search view
        ImageView icon = searchByPhoneNo.findViewById(androidx.appcompat.R.id.search_button);
        icon.setImageResource(R.drawable.ic_search_black_24dp);
        icon.setColorFilter(Color.RED);

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

        if(moduleType !=null && moduleType.equalsIgnoreCase(MODULE_TYPE_TARGET_DETAILS)) {
          tvCentersSummaryHeader.setText(CENTERS_SUMMARY_HEADER_TARGET_DETAILS);
        }
        else if(moduleType !=null && moduleType.equalsIgnoreCase(MODULE_TYPE_LOAN_APPLICATION)) {
            tvCentersSummaryHeader.setText(CENTERS_SUMMARY_HEADER_LOAN_APPLICATION);
        }
        else if(moduleType !=null && moduleType.equalsIgnoreCase(MODULE_TYPE_ADD_OR_REMOVE_MEMBER)) {
            tvCentersSummaryHeader.setText(CENTERS_SUMMARY_HEADER_ADD_OR_REMOVE_CUSTOMER);
        }
        else if(moduleType !=null && moduleType.equalsIgnoreCase(MODULE_TYPE_DROP_OUT_CUSTOMER)) {
            tvCentersSummaryHeader.setText(CENTERS_SUMMARY_HEADER_TO_DROP_OUT_CUSTOMER);
        }
        else if(moduleType !=null && moduleType.equalsIgnoreCase(MODULE_TYPE_HOUSE_VERIFICATION_CGT)) {
            tvCentersSummaryHeader.setText(CENTERS_SUMMARY_HEADER_FOR_HOUSE_VERIFICATION);
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

        spinnerSortByDate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0) {

                    spinnerSortByAlphabet.setSelection(0);
                     searchByPhoneNo.setQuery("", false);
                    searchByPhoneNo.setIconified(true);
                    if (centersSummaryAdapter != null && centersSummaryAdapter.getFilter() != null) {
                        centersSummaryAdapter.getFilter().filter(parent.getSelectedItem().toString());
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerSortByAlphabet.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0) {

                    spinnerSortByDate.setSelection(0);
                    searchByPhoneNo.setQuery("", false);
                    searchByPhoneNo.setIconified(true);

                    if (centersSummaryAdapter != null && centersSummaryAdapter.getFilter() != null) {
                        centersSummaryAdapter.getFilter().filter(parent.getSelectedItem().toString());
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

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
                spinnerSortByDate.setSelection(0);
                spinnerSortByAlphabet.setSelection(0);

                if (centersSummaryAdapter != null && centersSummaryAdapter.getFilter() != null) {
                    centersSummaryAdapter.getFilter().filter(query);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                // filter recycler view when text is changed
                spinnerSortByDate.setSelection(0);
                spinnerSortByAlphabet.setSelection(0);

                if (centersSummaryAdapter != null && centersSummaryAdapter.getFilter() != null) {
                    centersSummaryAdapter.getFilter().filter(query);
                }
                return false;
            }
        });

        configureDagger();
        configureViewModel();


    }

    private void configureDagger() {
        AndroidInjection.inject(this);
    }

    public void configureViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(DynamicUIViewModel.class);

       /* if (loanType.equalsIgnoreCase(LOAN_NAME_INDIVIDUAL)) {
            SCREEN_NO = SCREEN_N0_LEAD_IL;
        } else if (loanType.equalsIgnoreCase(LOAN_NAME_MSME)) {
            SCREEN_NO = SCREEN_N0_LEAD_MSME;
        } else {
            SCREEN_NO = SCREEN_N0_LEAD;
        }*/
    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            spinnerSortByDate.setSelection(0);
            spinnerSortByAlphabet.setSelection(0);
           searchByPhoneNo.setQuery("", false);
            searchByPhoneNo.setIconified(true);


            userName = getIntent().getStringExtra(PARAM_USER_NAME);
            userId = getIntent().getStringExtra(PARAM_USER_ID);
            branchId = getIntent().getStringExtra(PARAM_BRANCH_ID);
            branchGSTcode = getIntent().getStringExtra(PARAM_BRANCH_GST_CODE);
            loanType = getIntent().getStringExtra(PARAM_LOAN_TYPE);
            productId = getIntent().getStringExtra(PARAM_PRODUCT_ID);
            moduleType = getIntent().getStringExtra(PARAM_MODULE_TYPE);

            getCenterCreationTable();

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

    public void getCenterCreationTable() {
        appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();

        try {
            viewModel.getCenterCreationTable(userId, loanType);
            if (viewModel.getCenterCreationTableLiveDataList() != null) {
                Observer getCenterCreationTableObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        appHelper.getDialogHelper().getLoadingDialog().closeDialog();
                        List<CenterCreationTable> centerCreationTableList = (List<CenterCreationTable>) o;
                        viewModel.getCenterCreationTableLiveDataList().removeObserver(this);

                        if (centerCreationTableList != null && centerCreationTableList.size() > 0) {
                            rlNoLeads.setVisibility(View.GONE);
                            rvLeadDetails.setVisibility(View.VISIBLE);

                            if (centersSummaryAdapter != null) {
                                centersSummaryAdapter.setItem(centerCreationTableList);
                            } else {
                                centersSummaryAdapter = new CentersSummaryAdapter(CentersSummaryActivity.this, centerCreationTableList,
                                        CentersSummaryActivity.this, appHelper);
                                rvLeadDetails.setAdapter(centersSummaryAdapter);
                                centersSummaryAdapter.notifyDataSetChanged();
                            }

                        } else {
                            rvLeadDetails.setVisibility(View.GONE);
                            rlNoLeads.setVisibility(View.VISIBLE);
                        }
                    }
                };
                viewModel.getCenterCreationTableLiveDataList().observe(this, getCenterCreationTableObserver);
            }
        }catch (Exception ex){
            appHelper.getDialogHelper().getLoadingDialog().closeDialog();
            ex.printStackTrace();
        }
    }

    private void syncSingleCenterCreationData(CenterCreationTable centerCreationTable) {
        try {
            if (appHelper.isNetworkAvailable()) {
                appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();

                viewModel.syncSingleCenterCreationData(centerCreationTable);
                if (viewModel.getSubmitDataTableLiveData() != null) {
                    Observer syncDataToServerObserver = new Observer() {
                        @Override
                        public void onChanged(@Nullable Object o) {
                            appHelper.getDialogHelper().getLoadingDialog().closeDialog();
                            SubmitDataTable submitDataTable = (SubmitDataTable) o;
                            viewModel.getSubmitDataTableLiveData().removeObserver(this);

                            // TODO: Need to remove this condition
                            getCenterCreationTable();

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

        }
    }


    @Override
    public void syncCallback(int position, CenterCreationTable centerCreationTable) {
        syncSingleCenterCreationData(centerCreationTable);
    }

    @Override
    public void openScreenCallback(int position, CenterCreationTable centerCreationTable) {
        try{
            if(moduleType !=null && moduleType.equalsIgnoreCase(MODULE_TYPE_TARGET_DETAILS)) {
                Intent intentApplication = new Intent(CentersSummaryActivity.this, TargetDetailsActivity.class);
                intentApplication.putExtra(AppConstant.PARAM_LOAN_TYPE, loanType);
                intentApplication.putExtra(PARAM_USER_NAME, userName);
                intentApplication.putExtra(PARAM_USER_ID, userId);
                intentApplication.putExtra(PARAM_BRANCH_ID, branchId);
                intentApplication.putExtra(PARAM_BRANCH_GST_CODE, branchGSTcode);
                intentApplication.putExtra(PARAM_CLIENT_ID, centerCreationTable.getCenterId());
                intentApplication.putExtra(PARAM_MODULE_TYPE, moduleType);
                String centerTableJson = new Gson().toJson(centerCreationTable, CenterCreationTable.class);
                if (!TextUtils.isEmpty(centerTableJson)) {
                    intentApplication.putExtra(PARAM_CENTER_TABLE_JSON, centerTableJson);
                }
                startActivity(intentApplication);
            }
            else if(moduleType !=null && moduleType.equalsIgnoreCase(MODULE_TYPE_LOAN_APPLICATION)) {
//                Intent intentApplication = new Intent(CentersSummaryActivity.this, LoanApplicationMemberDetailsActivity.class);
                Intent intentApplication = new Intent(CentersSummaryActivity.this, LoanApplicationSummaryActivity.class);
                intentApplication.putExtra(AppConstant.PARAM_LOAN_TYPE, loanType);
                intentApplication.putExtra(PARAM_USER_NAME, userName);
                intentApplication.putExtra(PARAM_USER_ID, userId);
                intentApplication.putExtra(PARAM_BRANCH_ID, branchId);
                intentApplication.putExtra(PARAM_BRANCH_GST_CODE, branchGSTcode);
                intentApplication.putExtra(PARAM_CLIENT_ID, centerCreationTable.getCenterId());
                intentApplication.putExtra(PARAM_MODULE_TYPE, moduleType);
                String centerTableJson = new Gson().toJson(centerCreationTable, CenterCreationTable.class);
                if (!TextUtils.isEmpty(centerTableJson)) {
                    intentApplication.putExtra(PARAM_CENTER_TABLE_JSON, centerTableJson);
                }
                startActivity(intentApplication);
            }
            else if(moduleType !=null && moduleType.equalsIgnoreCase(MODULE_TYPE_ADD_OR_REMOVE_MEMBER)) {
                Intent intentApplication = new Intent(CentersSummaryActivity.this, AddOrRemoveCGTActivity.class);
                intentApplication.putExtra(AppConstant.PARAM_LOAN_TYPE, loanType);
                intentApplication.putExtra(PARAM_USER_NAME, userName);
                intentApplication.putExtra(PARAM_USER_ID, userId);
                intentApplication.putExtra(PARAM_BRANCH_ID, branchId);
                intentApplication.putExtra(PARAM_BRANCH_GST_CODE, branchGSTcode);
                intentApplication.putExtra(PARAM_CLIENT_ID, centerCreationTable.getCenterId());
                intentApplication.putExtra(PARAM_MODULE_TYPE, moduleType);
                String centerTableJson = new Gson().toJson(centerCreationTable, CenterCreationTable.class);
                if (!TextUtils.isEmpty(centerTableJson)) {
                    intentApplication.putExtra(PARAM_CENTER_TABLE_JSON, centerTableJson);
                }
                startActivity(intentApplication);
            }
            else if(moduleType !=null && moduleType.equalsIgnoreCase(MODULE_TYPE_DROP_OUT_CUSTOMER)) {
                Intent intentApplication = new Intent(CentersSummaryActivity.this, DropOutCustomerActivity.class);
                intentApplication.putExtra(AppConstant.PARAM_LOAN_TYPE, loanType);
                intentApplication.putExtra(PARAM_USER_NAME, userName);
                intentApplication.putExtra(PARAM_USER_ID, userId);
                intentApplication.putExtra(PARAM_BRANCH_ID, branchId);
                intentApplication.putExtra(PARAM_BRANCH_GST_CODE, branchGSTcode);
                intentApplication.putExtra(PARAM_CLIENT_ID, centerCreationTable.getCenterId());
                intentApplication.putExtra(PARAM_MODULE_TYPE, moduleType);
                String centerTableJson = new Gson().toJson(centerCreationTable, CenterCreationTable.class);
                if (!TextUtils.isEmpty(centerTableJson)) {
                    intentApplication.putExtra(PARAM_CENTER_TABLE_JSON, centerTableJson);
                }
                startActivity(intentApplication);
            }
            else if(moduleType !=null && moduleType.equalsIgnoreCase(MODULE_TYPE_HOUSE_VERIFICATION_CGT)) {
                Intent intentApplication = new Intent(CentersSummaryActivity.this, HouseVerificationMemberDetailsSummaryActivity.class);
                intentApplication.putExtra(AppConstant.PARAM_LOAN_TYPE, loanType);
                intentApplication.putExtra(PARAM_USER_NAME, userName);
                intentApplication.putExtra(PARAM_USER_ID, userId);
                intentApplication.putExtra(PARAM_BRANCH_ID, branchId);
                intentApplication.putExtra(PARAM_BRANCH_GST_CODE, branchGSTcode);
                intentApplication.putExtra(PARAM_CLIENT_ID, centerCreationTable.getCenterId());
                intentApplication.putExtra(PARAM_MODULE_TYPE, moduleType);
                String centerTableJson = new Gson().toJson(centerCreationTable, CenterCreationTable.class);
                if (!TextUtils.isEmpty(centerTableJson)) {
                    intentApplication.putExtra(PARAM_CENTER_TABLE_JSON, centerTableJson);
                }
                startActivity(intentApplication);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private void INSERT_LOG(String methodName, String message){
        try{
            if(viewModel !=null){
                viewModel.insertLog(methodName,message,userId,"","CenterCreationActivity"
                        ,CLIENT_ID,loanType,moduleType);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
