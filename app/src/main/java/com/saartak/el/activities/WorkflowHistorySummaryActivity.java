package com.saartak.el.activities;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.text.method.DigitsKeyListener;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
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
import com.bfil.uilibrary.helpers.AppConstants;
import com.google.android.material.navigation.NavigationView;
import com.saartak.el.R;

import com.saartak.el.adapter.WorkflowHistorySummaryAdapter;
import com.saartak.el.database.entity.ApplicationStatusTable;
import com.saartak.el.database.entity.RawDataTable;
import com.saartak.el.helper.MyDividerItemDecoration;
import com.saartak.el.view_models.DynamicUIViewModel;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

import static com.saartak.el.constants.AppConstant.DATE_FORMAT_DD_MM_YYYY2;
import static com.saartak.el.constants.AppConstant.LOAN_NAME_AHL;
import static com.saartak.el.constants.AppConstant.LOAN_NAME_INDIVIDUAL;
import static com.saartak.el.constants.AppConstant.LOAN_NAME_MSME;
import static com.saartak.el.constants.AppConstant.LOAN_NAME_PHL;
import static com.saartak.el.constants.AppConstant.LOAN_NAME_TWL;
import static com.saartak.el.constants.AppConstant.LOAN_NAME_EL;
import static com.saartak.el.constants.AppConstant.PARAM_BRANCH_GST_CODE;
import static com.saartak.el.constants.AppConstant.PARAM_BRANCH_ID;
import static com.saartak.el.constants.AppConstant.PARAM_GET_APPLICATION_ID;
import static com.saartak.el.constants.AppConstant.PARAM_LOAN_TYPE;
import static com.saartak.el.constants.AppConstant.PARAM_PRODUCT_ID;
import static com.saartak.el.constants.AppConstant.PARAM_SCREEN_NO;
import static com.saartak.el.constants.AppConstant.PARAM_USER_ID;
import static com.saartak.el.constants.AppConstant.PARAM_USER_NAME;
import static com.saartak.el.constants.AppConstant.PARAM_WORKFLOW_ID;
import static com.saartak.el.constants.AppConstant.REQUEST_CODE_NEW_LEAD;
import static com.saartak.el.constants.AppConstant.REQUEST_CODE_UPDATE_LEAD;
import static com.saartak.el.constants.AppConstant.SCREEN_N0_LEAD;
import static com.saartak.el.constants.AppConstant.SCREEN_N0_LEAD_IL;
import static com.saartak.el.constants.AppConstant.SCREEN_N0_LEAD_MSME;
import static com.saartak.el.constants.AppConstant.WORKFLOW_ID_AHL;
import static com.saartak.el.constants.AppConstant.WORKFLOW_ID_IL;
import static com.saartak.el.constants.AppConstant.WORKFLOW_ID_MSME;
import static com.saartak.el.constants.AppConstant.WORKFLOW_ID_PHL;
import static com.saartak.el.constants.AppConstant.WORKFLOW_ID_EL;

public class WorkflowHistorySummaryActivity extends LOSBaseActivity implements WorkflowHistorySummaryAdapter.SyncCallbackInterface{
    private static final String TAG = WorkflowHistorySummaryActivity.class.getCanonicalName();
    RecyclerView rvLeadDetails;

    SearchView searchByPhoneNo;
    AppCompatSpinner spinnerSortByAlphabet,  spinnersortByApplicationStage;
    WorkflowHistorySummaryAdapter workflowHistorySummaryAdapter;

    private Toolbar toolbar;
    TextView tvClientName;
    TextView et_fromDate, et_toDate;
    String SCREEN_NO;

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView navigationView;
    CoordinatorLayout coordinatorLayout;
    ImageView ivStaffImage;
    RelativeLayout rlNoLeads;
    TextView tvStaffId, tvStaffName, tvSOBDate;
    LinearLayout ll_fromDate, ll_toDate;
    List<RawDataTable> allClientRawDataTableList;
    List<String> applicationStageList;
    ArrayAdapter<String> spinnerApplicationStageAdapter;

    @Inject
    public ViewModelProvider.Factory viewModelFactory;
    public DynamicUIViewModel viewModel;


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_workflow_history_card_view);

        et_fromDate = (TextView)findViewById(R.id.tv_fromdate);
        et_toDate = (TextView)findViewById(R.id.tv_toDate);
        rvLeadDetails = (RecyclerView) findViewById(R.id.rv_lead_details);
        rlNoLeads = (RelativeLayout) findViewById(R.id.rl_no_leads);
        searchByPhoneNo = (SearchView) findViewById(R.id.sv_phone_no);
        spinnersortByApplicationStage = (AppCompatSpinner) findViewById(R.id.sp_sort_by_Application_stage);
        spinnerSortByAlphabet = (AppCompatSpinner) findViewById(R.id.sp_sort_by_alphabetical_order);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tvClientName = (TextView) findViewById(R.id.tv_client_name_custom);
        ll_fromDate = (LinearLayout)findViewById(R.id.ll_fromDate);
        ll_toDate = (LinearLayout)findViewById(R.id.ll_toDate);
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
        branchGSTcode = getIntent().getStringExtra(PARAM_BRANCH_GST_CODE);
        loanType = getIntent().getStringExtra(PARAM_LOAN_TYPE);
        productId = getIntent().getStringExtra(PARAM_PRODUCT_ID);
        GET_APPLICATION_ID = getIntent().getBooleanExtra(PARAM_GET_APPLICATION_ID, false);

        applicationStageList = new LinkedList<String>(Arrays.asList(getResources().getStringArray(R.array.lead_sort_by_application_stage)));


        spinnerApplicationStageAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, applicationStageList);
        spinnerApplicationStageAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnersortByApplicationStage.setAdapter(spinnerApplicationStageAdapter);

        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int  mDay = c.get(Calendar.DAY_OF_MONTH);

        ll_fromDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get Current Date
                DatePickerDialog datePickerDialog = new DatePickerDialog(WorkflowHistorySummaryActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                et_fromDate.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                                String fromdate = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
                                Date fromDate = appHelper.convertStringToDate(fromdate, AppConstants.DATE_FORMAT_YYYY_MM_DD);

                            }
                        }, mYear, mMonth, mDay);
                DatePicker datePicker = datePickerDialog.getDatePicker();
                datePicker.setMaxDate(System.currentTimeMillis());

                datePickerDialog.show();

            }
        });


        ll_toDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fromDate = et_fromDate.getText().toString();
                if (TextUtils.isEmpty(fromDate) || (fromDate.equalsIgnoreCase("tvName date"))) {
                    appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, "Please select From Date first");

                } else {
                    DatePickerDialog datePickerDialog = new DatePickerDialog(WorkflowHistorySummaryActivity.this,
                            new DatePickerDialog.OnDateSetListener() {

                                @Override
                                public void onDateSet(DatePicker view, int year,
                                                      int monthOfYear, int dayOfMonth) {

                                    et_toDate.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                                    String todate = et_toDate.getText().toString();
                                    getApplicationStatusFromServer(fromDate, todate);

                                }
                            }, mYear, mMonth, mDay);

                    DatePicker datePicker = datePickerDialog.getDatePicker();
                    datePicker.setMaxDate(System.currentTimeMillis());

                    datePickerDialog.show();
                }
            }
        });

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
        }
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        rvLeadDetails.setLayoutManager(mLayoutManager);
        rvLeadDetails.setItemAnimator(new DefaultItemAnimator());
        rvLeadDetails.addItemDecoration(new MyDividerItemDecoration(this, DividerItemDecoration.VERTICAL, 36));
        rvLeadDetails.setNestedScrollingEnabled(false);

        spinnerSortByAlphabet.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0) {

                    spinnersortByApplicationStage.setSelection(0);
                    searchByPhoneNo.setQuery("", false);
                    searchByPhoneNo.setIconified(true);
                    if (workflowHistorySummaryAdapter != null && workflowHistorySummaryAdapter.getFilter() != null) {
                        workflowHistorySummaryAdapter.getFilter().filter(parent.getSelectedItem().toString());
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnersortByApplicationStage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0) {

                    spinnerSortByAlphabet.setSelection(0);
                    searchByPhoneNo.setQuery("", false);
                    searchByPhoneNo.setIconified(true);
                    if (workflowHistorySummaryAdapter != null && workflowHistorySummaryAdapter.getFilter() != null) {
                        workflowHistorySummaryAdapter.getFilter().filter(parent.getSelectedItem().toString());
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
                spinnerSortByAlphabet.setSelection(0);
                spinnersortByApplicationStage.setSelection(0);

                if (workflowHistorySummaryAdapter != null && workflowHistorySummaryAdapter.getFilter() != null) {
                    workflowHistorySummaryAdapter.getFilter().filter(query);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                // filter recycler view when text is changed
                spinnerSortByAlphabet.setSelection(0);
                spinnersortByApplicationStage.setSelection(0);

                if (workflowHistorySummaryAdapter != null && workflowHistorySummaryAdapter.getFilter() != null) {
                    workflowHistorySummaryAdapter.getFilter().filter(query);
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
        if (loanType.equalsIgnoreCase(LOAN_NAME_INDIVIDUAL)) {
            SCREEN_NO = SCREEN_N0_LEAD_IL;
        } else if (loanType.equalsIgnoreCase(LOAN_NAME_MSME)) {
            SCREEN_NO = SCREEN_N0_LEAD_MSME;
        } else {
            SCREEN_NO = SCREEN_N0_LEAD;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i(TAG, "requestcode---> " + requestCode + ", resultcode----> " + resultCode);
        try {
            if ((requestCode == REQUEST_CODE_NEW_LEAD || requestCode == REQUEST_CODE_UPDATE_LEAD) && resultCode == RESULT_OK) {
                if (data != null) {
                    String screenNO = data.getStringExtra(PARAM_SCREEN_NO);
                    Log.i(TAG, "SCREEN NO ---> " + screenNO);
//                    getLeadRawDataFromLocalDB(screenNO);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            INSERT_LOG("onActivityResult","Exception : "+e.getMessage());
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            spinnerSortByAlphabet.setSelection(0);
            spinnersortByApplicationStage.setSelection(0);
            searchByPhoneNo.setQuery("", false);
            searchByPhoneNo.setIconified(true);

            userName = getIntent().getStringExtra(PARAM_USER_NAME);
            userId = getIntent().getStringExtra(PARAM_USER_ID);
            branchId = getIntent().getStringExtra(PARAM_BRANCH_ID);
            branchGSTcode = getIntent().getStringExtra(PARAM_BRANCH_GST_CODE);
            loanType = getIntent().getStringExtra(PARAM_LOAN_TYPE);
            productId = getIntent().getStringExtra(PARAM_PRODUCT_ID);
            workFlowId = getIntent().getStringExtra(PARAM_WORKFLOW_ID);
            GET_APPLICATION_ID = getIntent().getBooleanExtra(PARAM_GET_APPLICATION_ID, false);

        } catch (Exception ex) {
            ex.printStackTrace();

            INSERT_LOG("onResume","Exception : "+ex.getMessage());
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
            INSERT_LOG("selectItemDrawer","Exception : "+ex.getMessage());
        }
    }

    public void getApplicationStatusFromServer(String fromDate, String todate) {
        try {
            appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();

            String workflowId = productId;
            if (loanType.equalsIgnoreCase(LOAN_NAME_INDIVIDUAL)) {
                workflowId = WORKFLOW_ID_IL;
            }else if (loanType.equalsIgnoreCase(LOAN_NAME_MSME)) {
                workflowId = WORKFLOW_ID_MSME;
            }
            else if (loanType.equalsIgnoreCase(LOAN_NAME_PHL)) {
                workflowId = WORKFLOW_ID_PHL; // TODO: PHL workflow id
            }  else if (loanType.equalsIgnoreCase(LOAN_NAME_EL)) {
                workflowId = WORKFLOW_ID_EL; // TODO: EL workflow id
            }else if (loanType.equalsIgnoreCase(LOAN_NAME_TWL)) {
                workflowId = WORKFLOW_ID_EL; // TODO: EL workflow id
            }
            else if (loanType.equalsIgnoreCase(LOAN_NAME_AHL)) {
                workflowId = WORKFLOW_ID_AHL; // TODO: AHL workflow id
            } else {
                workflowId = WORKFLOW_ID_PHL;
            }
            viewModel.getApplicationStatusFromServer(userId, workFlowId, loanType, fromDate, todate);
            if (viewModel.getCustometviewLiveData() != null) {
                Observer getCustomerViewDataObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        appHelper.getDialogHelper().getLoadingDialog().closeDialog();
                        List<ApplicationStatusTable> applicationStatusTableList = (List<ApplicationStatusTable>) o;
                        viewModel.getCustometviewLiveData().removeObserver(this);

                        if (applicationStatusTableList != null && applicationStatusTableList.size() > 0) {
                            rlNoLeads.setVisibility(View.GONE);
                            rvLeadDetails.setVisibility(View.VISIBLE);

                            workflowHistorySummaryAdapter = new WorkflowHistorySummaryAdapter(WorkflowHistorySummaryActivity.this, applicationStatusTableList,
                                    WorkflowHistorySummaryActivity.this, appHelper);
                            rvLeadDetails.setAdapter(workflowHistorySummaryAdapter);
                            workflowHistorySummaryAdapter.notifyDataSetChanged();
                        } else {
                            rlNoLeads.setVisibility(View.VISIBLE);
                            rvLeadDetails.setVisibility(View.GONE);
                        }

                        loadApplicationStageSpinnerData(applicationStatusTableList);

                    }
                };
                viewModel.getCustometviewLiveData().observe(this, getCustomerViewDataObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            appHelper.getDialogHelper().getLoadingDialog().closeDialog();

            INSERT_LOG("getApplicationStatusFromServer","Exception : "+ex.getMessage());

        }
    }

    private void loadApplicationStageSpinnerData(List<ApplicationStatusTable> masterTableList) {
        if (masterTableList != null && masterTableList.size() > 0) {
            for (ApplicationStatusTable masterTable : masterTableList) {
                if (masterTable != null) {
                    String currentStage = masterTable.getCurrentStage();
                    if (!TextUtils.isEmpty(currentStage))
                        applicationStageList.add(currentStage);
                }
            }
            // TODO: Hashmap is used to remove duplicate items
            HashSet<String> hashSet = new HashSet<String>();
            hashSet.addAll(applicationStageList);
            applicationStageList.clear();
            applicationStageList.addAll(hashSet);

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

    @Override
    public void openScreenCallback(int position, ApplicationStatusTable masterTable) {
        if (GET_APPLICATION_ID) {
            Intent returnIntent = new Intent();
            returnIntent.putExtra("result",masterTable.getClientId());
            setResult(Activity.RESULT_OK,returnIntent);
            finish();
        }
    }

    @Override
    public void syncCallback(int position, ApplicationStatusTable masterTable) {

    }

    @Override
    public void submitCallback(int position, ApplicationStatusTable masterTable) {

    }

    @Override
    public void rejectCallback(int position, ApplicationStatusTable masterTable) {

    }

    private void INSERT_LOG(String methodName, String message){
        try{
            if(viewModel !=null){
                viewModel.insertLog(methodName,message,userId,"",WorkflowHistorySummaryActivity.class.getCanonicalName()
                        ,CLIENT_ID,loanType,moduleType);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}