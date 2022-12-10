package com.swadhaar.los.activities;

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
import android.widget.Button;
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
import com.swadhaar.los.BuildConfig;
import com.swadhaar.los.R;
import com.swadhaar.los.adapter.PlannerSummaryAdapter;
import com.swadhaar.los.constants.AppConstant;
import com.swadhaar.los.database.entity.PlannerTable;
import com.swadhaar.los.database.entity.RawDataTable;
import com.swadhaar.los.helper.MyDividerItemDecoration;
import com.swadhaar.los.view_models.DynamicUIViewModel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

import static com.swadhaar.los.constants.AppConstant.DATE_FORMAT_DD_MM_YYYY;
import static com.swadhaar.los.constants.AppConstant.DATE_FORMAT_DD_MM_YYYY2;
import static com.swadhaar.los.constants.AppConstant.LOAN_NAME_INDIVIDUAL;
import static com.swadhaar.los.constants.AppConstant.MODULE_TYPE_PLANNER;
import static com.swadhaar.los.constants.AppConstant.PARAM_BRANCH_GST_CODE;
import static com.swadhaar.los.constants.AppConstant.PARAM_BRANCH_ID;
import static com.swadhaar.los.constants.AppConstant.PARAM_BRANCH_NAME;
import static com.swadhaar.los.constants.AppConstant.PARAM_CLIENT_ID;
import static com.swadhaar.los.constants.AppConstant.PARAM_LOAN_TYPE;
import static com.swadhaar.los.constants.AppConstant.PARAM_MODULE_TYPE;
import static com.swadhaar.los.constants.AppConstant.PARAM_PRODUCT_ID;
import static com.swadhaar.los.constants.AppConstant.PARAM_USER_ID;
import static com.swadhaar.los.constants.AppConstant.PARAM_USER_NAME;

public class PlannerSummaryActivity extends LOSBaseActivity implements PlannerSummaryAdapter.SyncCallbackInterface {
    private static final String TAG = PlannerSummaryActivity.class.getCanonicalName();
    RecyclerView rvPlannerDetails;
    Button btnNewTrip, btnSyncAll;
    SearchView searchByPhoneNo;
    AppCompatSpinner spinnerSortByDate, spinnerSortByAlphabeticalOrder;
    PlannerSummaryAdapter plannerSummaryAdapter;

    private Toolbar toolbar;
    TextView tvClientName;
    TextView tvAppVersion,tvCurrentDate;
    String SCREEN_NO;

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView navigationView;
    CoordinatorLayout coordinatorLayout;
    ImageView ivStaffImage;
    RelativeLayout rlNoPlanners;
    TextView tvStaffId, tvStaffName, tvSOBDate;
    List<PlannerTable> plannerTableDataTableList;

    List<RawDataTable> allClientRawDataTableList;

    @Inject
    public ViewModelProvider.Factory viewModelFactory;
    public DynamicUIViewModel viewModel;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_planner_summary);
        rvPlannerDetails = (RecyclerView) findViewById(R.id.rv_planner_details);
        rlNoPlanners = (RelativeLayout) findViewById(R.id.rl_no_planners);
        btnNewTrip = (Button) findViewById(R.id.btn_new_trip);
        btnSyncAll = (Button) findViewById(R.id.btn_sync_all);
        searchByPhoneNo = (SearchView) findViewById(R.id.sv_phone_no);
        spinnerSortByDate = (AppCompatSpinner) findViewById(R.id.sp_sort_by_date);
        spinnerSortByDate = (AppCompatSpinner) findViewById(R.id.sp_sort_by_date);
        spinnerSortByAlphabeticalOrder = (AppCompatSpinner) findViewById(R.id.sp_sort_by_alphabetical_order);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tvCurrentDate = (TextView)findViewById(R.id.tv_currentdate);
        tvAppVersion = (TextView)findViewById(R.id.tv_app_version);
        tvClientName = (TextView) findViewById(R.id.tv_client_name_custom);
        EditText searchEditText = (EditText) searchByPhoneNo.findViewById(androidx.appcompat.R.id.search_src_text);
        searchEditText.setKeyListener(DigitsKeyListener.getInstance("abcdefghijklmnopqrstuvwxyz ABCDEFGHIJKLMNOPQRSTUVWXYZ 0123456789"));
        searchEditText.setRawInputType(InputType.TYPE_TEXT_VARIATION_PERSON_NAME);
        searchEditText.setTextColor(getResources().getColor(R.color.black));
        searchEditText.setHintTextColor(getResources().getColor(R.color.black));

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);

        ImageView icon = searchByPhoneNo.findViewById(androidx.appcompat.R.id.search_button);
        icon.setImageResource(R.drawable.ic_search_black_24dp);
        icon.setColorFilter(Color.RED);

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
        branchName = getIntent().getStringExtra(PARAM_BRANCH_NAME);
        branchGSTcode = getIntent().getStringExtra(PARAM_BRANCH_GST_CODE);
        loanType = getIntent().getStringExtra(PARAM_LOAN_TYPE);
        productId = getIntent().getStringExtra(PARAM_PRODUCT_ID);

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
            tvAppVersion.setText(getResources().getString(R.string.version)+" "+appVersion);//add
        }
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        rvPlannerDetails.setLayoutManager(mLayoutManager);
        rvPlannerDetails.setItemAnimator(new DefaultItemAnimator());
        rvPlannerDetails.addItemDecoration(new MyDividerItemDecoration(this, DividerItemDecoration.VERTICAL, 36));
        rvPlannerDetails.setNestedScrollingEnabled(false);

        spinnerSortByDate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0) {

                    spinnerSortByAlphabeticalOrder.setSelection(0);
                    searchByPhoneNo.setQuery("", false);
                    searchByPhoneNo.setIconified(true);
                    if (plannerSummaryAdapter != null && plannerSummaryAdapter.getFilter() != null) {
                        plannerSummaryAdapter.getFilter().filter(parent.getSelectedItem().toString());
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerSortByAlphabeticalOrder.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0) {

                    spinnerSortByDate.setSelection(0);
                    searchByPhoneNo.setQuery("", false);
                    searchByPhoneNo.setIconified(true);
                    if (plannerSummaryAdapter != null && plannerSummaryAdapter.getFilter() != null) {
                        plannerSummaryAdapter.getFilter().filter(parent.getSelectedItem().toString());
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
                spinnerSortByAlphabeticalOrder.setSelection(0);

                if (plannerSummaryAdapter != null && plannerSummaryAdapter.getFilter() != null) {
                    plannerSummaryAdapter.getFilter().filter(query);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                // filter recycler view when text is changed
                spinnerSortByDate.setSelection(0);
                spinnerSortByAlphabeticalOrder.setSelection(0);

                if (plannerSummaryAdapter != null && plannerSummaryAdapter.getFilter() != null) {
                    plannerSummaryAdapter.getFilter().filter(query);
                }
                return false;
            }
        });

        btnNewTrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String timeStamp = new SimpleDateFormat("yyMMddHHmmss",
                        Locale.getDefault()).format(new Date());
                if( ! TextUtils.isEmpty(EMP_LAST_5_DIGIT)) {
                    if (!TextUtils.isEmpty(userId)) {
                        EMP_LAST_5_DIGIT = userId.substring(3);
                        CLIENT_ID = EMP_LAST_5_DIGIT + timeStamp;
                        // TODO: CLIENT ID FORMATION ( FIRST 7 DIGIT EMP ID - 100004141 , YEAR 19 ,
                        //  MONTH 11 , DATE 12, HOUR 08 , MINUTE 25 , SECOND 48 ==> TOTAL 19 DIGITS )
                        if (!TextUtils.isEmpty(CLIENT_ID) && CLIENT_ID.length() > 16) {
                            Intent intentApplication = new Intent(PlannerSummaryActivity.this, PlannerActivity.class);
                            intentApplication.putExtra(AppConstant.PARAM_LOAN_TYPE, loanType);
                            intentApplication.putExtra(PARAM_USER_NAME, userName);
                            intentApplication.putExtra(PARAM_USER_ID, userId);
                            intentApplication.putExtra(PARAM_BRANCH_ID, branchId);
                            intentApplication.putExtra(PARAM_BRANCH_NAME, branchName);
                            intentApplication.putExtra(PARAM_BRANCH_GST_CODE, branchGSTcode);
                            intentApplication.putExtra(PARAM_CLIENT_ID, CLIENT_ID);
                            intentApplication.putExtra(PARAM_MODULE_TYPE, MODULE_TYPE_PLANNER);
                            intentApplication.putExtra(PARAM_PRODUCT_ID, productId);
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
            }
        });

        btnSyncAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (appHelper.isNetworkAvailable()) {
                    //appHelper.getDialogHelper().getConfirmationDialog().showTwoButtons("Would You Like To Sync All Leads ?", new ConfirmationDialog.ActionCallback() {
                    appHelper.getDialogHelper().getConfirmationDialog().showTwoButtons("Would You Like To sync all planners ?", new ConfirmationDialog.ActionCallback() {
                        @Override
                        public void onAction() {
                            if (plannerTableDataTableList != null && plannerTableDataTableList.size() > 0) {
                                boolean allSynced = false;
                                for (PlannerTable plannerTable : plannerTableDataTableList) {
                                    if (plannerTable.isTripEnd()){
                                        if (!plannerTable.isSync()) {
                                            allSynced = false;
                                            postSubmittedData(plannerTable);
                                        } else {
                                            allSynced = true;
                                        }
                                    }
                                }
                                if (allSynced) {
                                    appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, "All Planners are synced");
                                }
                            } else {
                                appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, "No Planners To Snyc");
                            }
                        }
                    });

                }else{
                    appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, "Internet Connection Required To Sync Data");
                }
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

      //  getCollectionSummaryData();

    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            spinnerSortByDate.setSelection(0);
            spinnerSortByAlphabeticalOrder.setSelection(0);
            searchByPhoneNo.setQuery("", false);
            searchByPhoneNo.setIconified(true);

            userName = getIntent().getStringExtra(PARAM_USER_NAME);
            userId = getIntent().getStringExtra(PARAM_USER_ID);
            branchId = getIntent().getStringExtra(PARAM_BRANCH_ID);
            branchName = getIntent().getStringExtra(PARAM_BRANCH_NAME);
            branchGSTcode = getIntent().getStringExtra(PARAM_BRANCH_GST_CODE);
            loanType = getIntent().getStringExtra(PARAM_LOAN_TYPE);
            productId = getIntent().getStringExtra(PARAM_PRODUCT_ID);

            getPlannerSummaryDataFromDB(SCREEN_NO, userId);


        } catch (Exception ex) {
            ex.printStackTrace();
            INSERT_LOG("onResume","Exception : "+ex.getMessage());
        }
    }


    private void getPlannerSummaryDataFromDB(String screen, String userId) {
        try {

            appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();

            viewModel.getPlannerData(screen,userId,loanType);
            if (viewModel.getPlannerTableLiveDataList() != null) {
                Observer getLeadRawDataObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        plannerTableDataTableList = (List<PlannerTable>) o;
                        viewModel.getPlannerTableLiveDataList().removeObserver(this);

                        appHelper.getDialogHelper().getLoadingDialog().closeDialog();

                        if (plannerTableDataTableList != null && plannerTableDataTableList.size() > 0) {
                            PlannerTable topPlannerTable = plannerTableDataTableList.get(0);
                            if(topPlannerTable.isTripEnd()){
                                btnNewTrip.setVisibility(View.VISIBLE);
                            }else {
                                btnNewTrip.setVisibility(View.GONE);

                            }
                            rlNoPlanners.setVisibility(View.GONE);
                            plannerSummaryAdapter = new PlannerSummaryAdapter(PlannerSummaryActivity.this,
                                    plannerTableDataTableList, PlannerSummaryActivity.this, appHelper);
                            rvPlannerDetails.setAdapter(plannerSummaryAdapter);
                            plannerSummaryAdapter.notifyDataSetChanged();
                        } else {
                            rlNoPlanners.setVisibility(View.VISIBLE);
                        }
                    }
                };
                viewModel.getPlannerTableLiveDataList().observe(this, getLeadRawDataObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            appHelper.getDialogHelper().getLoadingDialog().closeDialog();

            INSERT_LOG("getPlannerSummaryData","Exception : "+ex.getMessage());
        }


    }

    private void INSERT_LOG(String methodName,String message){
        try{
            if(viewModel !=null){
                viewModel.insertLog(methodName,message,userId,"","PlannerSummaryActivity"
                        ,CLIENT_ID,loanType,moduleType);
            }
        }catch (Exception ex){
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
            INSERT_LOG("setUpDrawerContent","Exception : "+ex.getMessage());
        }
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
            INSERT_LOG("selectItemDrawer","Exception : "+ex.getMessage());
        }
    }




    @Override
    public void syncCallback(int position, PlannerTable plannerTable) {
        try {
            if (plannerTable != null) {
                postSubmittedData(plannerTable);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            INSERT_LOG("syncCallback","Exception : "+ex.getMessage());
        }
    }

    private void postSubmittedData(PlannerTable plannerTable) {
        try {

            if (appHelper.isNetworkAvailable()) {

                appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();

                viewModel.postPlannerDataToServer(plannerTable, SCREEN_NO);
                if (viewModel.getDynamicUITableLiveData() != null) {
                    Observer postSubmittedDataObserver = new Observer() {
                        @Override
                        public void onChanged(@Nullable Object o) {
                            viewModel.getDynamicUITableLiveData().removeObserver(this);
                            appHelper.getDialogHelper().getLoadingDialog().closeDialog();

                            getPlannerSummaryDataFromDB(SCREEN_NO,userId);
                        }
                    };
                    viewModel.getDynamicUITableLiveData().observe(this, postSubmittedDataObserver);
                }
            } else {
                appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT,
                        "Please check your internet connection and try again");
            }
        } catch (Exception ex) {
            appHelper.getDialogHelper().getLoadingDialog().closeDialog();
            ex.printStackTrace();
            INSERT_LOG("postSubmittedData","Exception : "+ex.getMessage());
        }
    }

}


