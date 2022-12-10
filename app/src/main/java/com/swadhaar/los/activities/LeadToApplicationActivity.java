package com.swadhaar.los.activities;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.text.method.DigitsKeyListener;
import android.util.Log;
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
import com.google.android.material.navigation.NavigationView;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.swadhaar.los.BuildConfig;
import com.swadhaar.los.R;
import com.swadhaar.los.adapter.LeadDetailsSummaryAdapter;
import com.swadhaar.los.adapter.LeadToApplicationAdapter;
import com.swadhaar.los.adapter.WorkflowHistorySummaryAdapter;
import com.swadhaar.los.database.entity.ApplicationStatusTable;
import com.swadhaar.los.database.entity.DynamicUITable;
import com.swadhaar.los.database.entity.LeadTable;
import com.swadhaar.los.database.entity.MasterTable;
import com.swadhaar.los.database.entity.RawDataTable;
import com.swadhaar.los.database.entity.SubmitDataTable;
import com.swadhaar.los.helper.MyDividerItemDecoration;
import com.swadhaar.los.models.SubmitDataDTO;
import com.swadhaar.los.view_models.DynamicUIViewModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

import static com.swadhaar.los.constants.AppConstant.DATE_FORMAT_DD_MMM_YY;
import static com.swadhaar.los.constants.AppConstant.DATE_FORMAT_DD_MM_YYYY;
import static com.swadhaar.los.constants.AppConstant.DATE_FORMAT_DD_MM_YYYY2;
import static com.swadhaar.los.constants.AppConstant.DATE_FORMAT_YYYY_MM_DD;
import static com.swadhaar.los.constants.AppConstant.INDIVIDUAL_TAB_SCREEN_NUMBERS;
import static com.swadhaar.los.constants.AppConstant.LOAN_NAME_AHL;
import static com.swadhaar.los.constants.AppConstant.LOAN_NAME_INDIVIDUAL;
import static com.swadhaar.los.constants.AppConstant.LOAN_NAME_MSME;
import static com.swadhaar.los.constants.AppConstant.LOAN_NAME_PHL;
import static com.swadhaar.los.constants.AppConstant.MODULE_TYPE_LEAD;
import static com.swadhaar.los.constants.AppConstant.PARAM_BRANCH_GST_CODE;
import static com.swadhaar.los.constants.AppConstant.PARAM_BRANCH_ID;
import static com.swadhaar.los.constants.AppConstant.PARAM_LOAN_TYPE;
import static com.swadhaar.los.constants.AppConstant.PARAM_PRODUCT_ID;
import static com.swadhaar.los.constants.AppConstant.PARAM_SCREEN_NO;
import static com.swadhaar.los.constants.AppConstant.PARAM_USER_ID;
import static com.swadhaar.los.constants.AppConstant.PARAM_USER_NAME;
import static com.swadhaar.los.constants.AppConstant.PROJECT_ID_LOS;
import static com.swadhaar.los.constants.AppConstant.REQUEST_CODE_NEW_LEAD;
import static com.swadhaar.los.constants.AppConstant.REQUEST_CODE_UPDATE_LEAD;
import static com.swadhaar.los.constants.AppConstant.SCREEN_N0_LEAD;
import static com.swadhaar.los.constants.AppConstant.SCREEN_N0_LEAD_AHL;
import static com.swadhaar.los.constants.AppConstant.SCREEN_N0_LEAD_IL;
import static com.swadhaar.los.constants.AppConstant.SCREEN_N0_LEAD_MSME;
import static com.swadhaar.los.constants.AppConstant.SCREEN_N0_LEAD_PHL;
import static com.swadhaar.los.constants.AppConstant.SCREEN_NAME_LEAD;
import static com.swadhaar.los.constants.AppConstant.STAGE_ID_ZERO;
import static com.swadhaar.los.constants.AppConstant.WORKFLOW_ID_AHL;
import static com.swadhaar.los.constants.AppConstant.WORKFLOW_ID_IL;
import static com.swadhaar.los.constants.AppConstant.WORKFLOW_ID_MSME;
import static com.swadhaar.los.constants.AppConstant.WORKFLOW_ID_PHL;
import static com.swadhaar.los.constants.AppConstant.getObjectByTAG;
import static com.swadhaar.los.constants.AppConstant.setKeyValueForObject;
import static com.swadhaar.los.dynamicui.constants.ParametersConstant.ERROR_MESSAGE_SYNC_FAILED;
import static com.swadhaar.los.dynamicui.constants.ParametersConstant.ERROR_MESSAGE_SYNC_SUCCESS;
import static com.swadhaar.los.dynamicui.constants.ParametersConstant.FINAL_STATUS_REJECTED;
import static com.swadhaar.los.dynamicui.constants.ParametersConstant.FINAL_STATUS_SUBMITTED;
import static com.swadhaar.los.dynamicui.constants.ParametersConstant.TAG_NAME_SAVE_BUTTON;

public class LeadToApplicationActivity extends LOSBaseActivity implements LeadToApplicationAdapter.SyncCallbackInterface {

    private static final String TAG = LeadToApplicationActivity.class.getCanonicalName();
    RecyclerView rvLeadDetails;
    Button btnNewLead, btnSyncAll;
    SearchView searchByPhoneNo;
    AppCompatSpinner spinnerSortByDate, spinnerSortByLoanType, spinnerSortByInterested;
    LeadToApplicationAdapter leadToApplicationAdapter;

    private Toolbar toolbar;
    TextView tvClientName;
    TextView tvAppVersion,tvCurrentDate;
    String SCREEN_NO;

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView navigationView;
    CoordinatorLayout coordinatorLayout;
    ImageView ivStaffImage;
    RelativeLayout rlNoLeads;
    TextView tvStaffId, tvStaffName, tvSOBDate;

//    public String userName,userId,branchId;

    List<RawDataTable> allClientRawDataTableList;

    @Inject
    public ViewModelProvider.Factory viewModelFactory;
    public DynamicUIViewModel viewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_lead_to_application_card_view);
        rvLeadDetails = (RecyclerView) findViewById(R.id.rv_lead_details);
        rlNoLeads = (RelativeLayout) findViewById(R.id.rl_no_leads);
        btnNewLead = (Button) findViewById(R.id.btn_new_lead);
        btnSyncAll = (Button) findViewById(R.id.btn_sync_all);
        searchByPhoneNo = (SearchView) findViewById(R.id.sv_phone_no);
        spinnerSortByDate = (AppCompatSpinner) findViewById(R.id.sp_sort_by_date);
        spinnerSortByLoanType = (AppCompatSpinner) findViewById(R.id.sp_sort_by_loan_type);
        spinnerSortByInterested = (AppCompatSpinner) findViewById(R.id.sp_sort_by_interest_or_not);
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
        rvLeadDetails.setLayoutManager(mLayoutManager);
        rvLeadDetails.setItemAnimator(new DefaultItemAnimator());
        rvLeadDetails.addItemDecoration(new MyDividerItemDecoration(this, DividerItemDecoration.VERTICAL, 36));
        rvLeadDetails.setNestedScrollingEnabled(false);

        btnSyncAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (appHelper.isNetworkAvailable()) {
                    appHelper.getDialogHelper().getConfirmationDialog().showTwoButtons("Would You Like To Sync All Applications ?", new ConfirmationDialog.ActionCallback() {
                        @Override
                        public void onAction() {
                            if (allClientRawDataTableList != null && allClientRawDataTableList.size() > 0) {
                                boolean allSynced = false;
                                for (RawDataTable rawDataTable : allClientRawDataTableList) {
                                    if (!rawDataTable.isSync()) {
                                        allSynced = false;
                                        syncDataToServer(rawDataTable);
                                    } else {
                                        allSynced = true;
                                    }
                                }
                                if (allSynced) {
                                    appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, "All leads are synced");
                                }
                            } else {
                                appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, "No Lead Details To Snyc");
                            }


                        }
                    });

                } else {
                    appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, "Internet Connection Required To Sync Data");
                }
            }
        });


        spinnerSortByDate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0) {

                    spinnerSortByLoanType.setSelection(0);
                    spinnerSortByInterested.setSelection(0);
                    searchByPhoneNo.setQuery("", false);
                    searchByPhoneNo.setIconified(true);
                    if (leadToApplicationAdapter != null && leadToApplicationAdapter.getFilter() != null) {
                        leadToApplicationAdapter.getFilter().filter(parent.getSelectedItem().toString());
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerSortByLoanType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0) {

                    spinnerSortByDate.setSelection(0);
                    spinnerSortByInterested.setSelection(0);
                    searchByPhoneNo.setQuery("", false);
                    searchByPhoneNo.setIconified(true);
                    if (leadToApplicationAdapter != null && leadToApplicationAdapter.getFilter() != null) {
                        leadToApplicationAdapter.getFilter().filter(parent.getSelectedItem().toString());
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerSortByInterested.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0) {

                    spinnerSortByDate.setSelection(0);
                    spinnerSortByLoanType.setSelection(0);
                    searchByPhoneNo.setQuery("", false);
                    searchByPhoneNo.setIconified(true);

                    if (leadToApplicationAdapter != null && leadToApplicationAdapter.getFilter() != null) {
                        leadToApplicationAdapter.getFilter().filter(parent.getSelectedItem().toString());
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
                spinnerSortByLoanType.setSelection(0);
                spinnerSortByInterested.setSelection(0);

                if (leadToApplicationAdapter != null && leadToApplicationAdapter.getFilter() != null) {
                    leadToApplicationAdapter.getFilter().filter(query);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                // filter recycler view when text is changed
                spinnerSortByDate.setSelection(0);
                spinnerSortByLoanType.setSelection(0);
                spinnerSortByInterested.setSelection(0);

                if (leadToApplicationAdapter != null && leadToApplicationAdapter.getFilter() != null) {
                    leadToApplicationAdapter.getFilter().filter(query);
                }
                return false;
            }
        });

        configureDagger();
        configureViewModel();

        // TODO: Getting Lead data tvName server
//        getLeadDataFromServer();
        // TODO: Getting Lead data tvName server
        getLeadDataFromLocalDB(SCREEN_NO,userId,loanType);



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
        }
        else if (loanType.equalsIgnoreCase(LOAN_NAME_PHL)) {
            SCREEN_NO = SCREEN_N0_LEAD_PHL;
        }
        else if (loanType.equalsIgnoreCase(LOAN_NAME_AHL)) {
            SCREEN_NO = SCREEN_N0_LEAD_AHL;
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
            spinnerSortByDate.setSelection(0);
            spinnerSortByLoanType.setSelection(0);
            spinnerSortByInterested.setSelection(0);
            searchByPhoneNo.setQuery("", false);
            searchByPhoneNo.setIconified(true);

            userName = getIntent().getStringExtra(PARAM_USER_NAME);
            userId = getIntent().getStringExtra(PARAM_USER_ID);
            branchId = getIntent().getStringExtra(PARAM_BRANCH_ID);
            branchGSTcode = getIntent().getStringExtra(PARAM_BRANCH_GST_CODE);
            loanType = getIntent().getStringExtra(PARAM_LOAN_TYPE);
            productId = getIntent().getStringExtra(PARAM_PRODUCT_ID);

        } catch (Exception ex) {
            ex.printStackTrace();
            INSERT_LOG("onResume","Exception : "+ex.getMessage());
        }
    }


    public void getLeadDataFromLocalDB(String screen, String userId, String loanType) {
        try {

            appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();

            viewModel.getLeadData(screen,userId,loanType);
            if (viewModel.getLeadTableLiveData() != null) {
                Observer getLeadDataObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        List<LeadTable>  leadDataTableList = (List<LeadTable>) o;
                        viewModel.getLeadTableLiveData().removeObserver(this);

                        appHelper.getDialogHelper().getLoadingDialog().closeDialog();

                        if (leadDataTableList != null && leadDataTableList.size() > 0) {

                            // TODO: Getting My stage
                            getMyStages();
                        } else {
                            // TODO: Getting Lead data tvName server
                            getLeadDataFromServer();
                        }
                    }
                };
                viewModel.getLeadTableLiveData().observe(this, getLeadDataObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            appHelper.getDialogHelper().getLoadingDialog().closeDialog();

            INSERT_LOG("getLeadDataFromLocalDB","Exception : "+ex.getMessage());

            // TODO: Getting My stage
            getMyStages();
        }
    }

    public void getRawDataForAllClient(List<String> screenNoList, List<String> clientIdList, String userId,
                                       ArrayList<HashMap<String, Object>> leadDetailsDTOList, List<RawDataTable> leadRawDataTableList) {

        ArrayList<HashMap<String, Object>> allClientHashMapList = new ArrayList<>();
        try {
            viewModel.getRawDataForAllClient(screenNoList, clientIdList, userId);
            if (viewModel.getRawTableLiveData() != null) {
                Observer getRawDataForAllClientObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        allClientRawDataTableList = (List<RawDataTable>) o;
                        viewModel.getRawTableLiveData().removeObserver(this);

                        if (allClientRawDataTableList != null && allClientRawDataTableList.size() > 0) {
                            for (RawDataTable rawDataTable : allClientRawDataTableList) {
                                HashMap<String, Object> hashMap = setKeyValueForObject(rawDataTable);
                                allClientHashMapList.add(hashMap);
                            }
                            Log.d(TAG, "HashMapList ==> " + allClientHashMapList.toString());
                        }
                           /* if (allClientHashMapList != null && allClientHashMapList.size() > 0) {
                                rlNoLeads.setVisibility(View.GONE);

                                leadToApplicationAdapter = new LeadToApplicationAdapter(LeadToApplicationActivity.this,leadDetailsDTOList,leadRawDataTableList,
                                        allClientHashMapList, allClientRawDataTableList, LeadToApplicationActivity.this, appHelper);
                                rvLeadDetails.setAdapter(leadToApplicationAdapter);
                                leadToApplicationAdapter.notifyDataSetChanged();

                            } else {
                                rlNoLeads.setVisibility(View.VISIBLE);
                            }*/

                    }
                };
                viewModel.getRawTableLiveData().observe(this, getRawDataForAllClientObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();

            INSERT_LOG("getRawDataForAllClient","Exception : "+ex.getMessage());
        }
    }


    private void syncDataToServer(RawDataTable rawDataTable) {
        try {
            if (appHelper.isNetworkAvailable()) {
                final SubmitDataTable submitDataTable = new SubmitDataTable();
                submitDataTable.setScreenId(rawDataTable.getScreen_no());
                submitDataTable.setUniqueID(rawDataTable.getClient_id());
                submitDataTable.setApplicationId(rawDataTable.getClient_id());
                submitDataTable.setIMEINumber(appHelper.getIMEI());
                if (!TextUtils.isEmpty(branchGSTcode)) {
                    submitDataTable.setBCBRID(branchGSTcode);
                } else {
                    submitDataTable.setBCBRID(branchId);
                }
                submitDataTable.setBCID(branchId);
                submitDataTable.setCreatedBy(rawDataTable.getUser_id());
                submitDataTable.setStageId(STAGE_ID_ZERO); // TODO: STAGE ID ZERO
                ArrayList<String> stringArrayList = new ArrayList<>();
                stringArrayList.add(rawDataTable.getRawdata());
                submitDataTable.setRawData(rawDataTable.getRawdata());
                // TODO: 22-12-2020 submit DTO
                final SubmitDataDTO submitDataDTO = new SubmitDataDTO();
                submitDataDTO.setScreenId(rawDataTable.getScreen_no());
                submitDataDTO.setUniqueID(rawDataTable.getClient_id());
                submitDataDTO.setApplicationId(rawDataTable.getClient_id());
                submitDataDTO.setIMEINumber(appHelper.getIMEI());
                if (!TextUtils.isEmpty(branchGSTcode)) {
                    submitDataDTO.setBCBRID(branchGSTcode);
                } else {
                    submitDataDTO.setBCBRID(branchId);
                }
                submitDataDTO.setBCID(branchId);
                submitDataDTO.setCreatedBy(rawDataTable.getUser_id());
                submitDataDTO.setStageId(STAGE_ID_ZERO); // TODO: STAGE ID ZERO
                JsonParser jsonParser = new JsonParser();
                JsonObject jsonObject = (JsonObject)jsonParser.parse(rawDataTable.getRawdata());
                JsonArray jsonArrayRawData=new JsonArray();
                jsonArrayRawData.add(jsonObject);
                submitDataDTO.setRawData(jsonArrayRawData);
                viewModel.syncDataToServer(submitDataTable,submitDataDTO, rawDataTable.getRawdata(), rawDataTable.getScreen_no(), rawDataTable);
                if (viewModel.getSubmitDataTableLiveData() != null) {
                    Observer syncDataToServerObserver = new Observer() {
                        @Override
                        public void onChanged(@Nullable Object o) {
                            SubmitDataTable submitDataTable = (SubmitDataTable) o;
                            viewModel.getSubmitDataTableLiveData().removeObserver(this);

                            // TODO: Need to remove this condition
                            getMasterTableDetails();

                           /* if(submitDataTable !=null) {
                                if( ! TextUtils.isEmpty(submitDataTable.getResponse())
                                        && submitDataTable.getResponse().equalsIgnoreCase(SUCCESS_RESPONSE_MESSAGE)) {
//                                    getLeadRawDataFromLocalDB(SCREEN_NO);
                                }else{
                                    Toast.makeText(LeadToApplicationActivity.this,"Unable to sync data",Toast.LENGTH_SHORT).show();
                                }
                            }else{
                                Toast.makeText(LeadToApplicationActivity.this,"Unable to sync data",Toast.LENGTH_SHORT).show();
                            }*/
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

            INSERT_LOG("syncDataToServer","Exception : "+ex.getMessage());
        }
    }


    private void syncAllScreenByClient(MasterTable masterTable) {
        try {

            if (appHelper.isNetworkAvailable()) {

                appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();

                viewModel.syncAllScreenByClient(masterTable);
                if (viewModel.getmasterTableLiveData() != null) {
                    Observer syncSingleClientDataObserver = new Observer() {
                        @Override
                        public void onChanged(@Nullable Object o) {
                            appHelper.getDialogHelper().getLoadingDialog().closeDialog();
                            MasterTable masterTableResponse = (MasterTable) o;
                            viewModel.getmasterTableLiveData().removeObserver(this);

                            // TODO: Need to remove this
                            if (masterTableResponse != null && masterTableResponse.isSync()) {
                                appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.SUCCESS,
                                        ERROR_MESSAGE_SYNC_SUCCESS, new ConfirmationDialog.ActionCallback() {
                                            @Override
                                            public void onAction() {
                                                getMasterTableDetails();
                                            }
                                        });
                            } else {
                                String errorMsg = ERROR_MESSAGE_SYNC_FAILED;
                                if (masterTableResponse != null && !TextUtils.isEmpty(masterTableResponse.getResponse())) {
                                    errorMsg = masterTableResponse.getResponse();
                                }
                                appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ERROR,
                                        errorMsg, new ConfirmationDialog.ActionCallback() {
                                            @Override
                                            public void onAction() {
                                                getMasterTableDetails();
                                            }
                                        });
                            }
                           /* if(submitDataTable !=null) {
                                if( ! TextUtils.isEmpty(submitDataTable.getResponse())
                                        && submitDataTable.getResponse().equalsIgnoreCase(SUCCESS_RESPONSE_MESSAGE)) {
//                                    getLeadRawDataFromLocalDB(SCREEN_NO);
                                }else{
                                    Toast.makeText(LeadToApplicationActivity.this,"Unable to sync data",Toast.LENGTH_SHORT).show();
                                }
                            }else{
                                Toast.makeText(LeadToApplicationActivity.this,"Unable to sync data",Toast.LENGTH_SHORT).show();
                            }*/
                        }
                    };
                    viewModel.getmasterTableLiveData().observe(this, syncSingleClientDataObserver);
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


    private void updateMasterTableStatusForRejectAndSendback(MasterTable masterTable, String finalStatusRejected) {
        try {
            viewModel.updateMasterTableStatusForRejectAndSendback(masterTable, finalStatusRejected);
            if (viewModel.getmasterTableLiveData() != null) {
                Observer updateMasterTableStatusObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        MasterTable masterTableResult = (MasterTable) o;
                        viewModel.getmasterTableLiveData().removeObserver(this);
                        if (masterTableResult != null ) {
                            getMasterTableDetails();
                        } else {
                            String errorMsg = "Unable to update data";
                            if (masterTableResult != null && !TextUtils.isEmpty(masterTableResult.getResponse())) {
                                errorMsg = masterTableResult.getResponse();
                            }
                            appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ERROR, errorMsg);
                        }
                    }
                };
                viewModel.getmasterTableLiveData().observe(this, updateMasterTableStatusObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();

            INSERT_LOG("updateMasterTableStatus","Exception : "+ex.getMessage());
        }

    }

    private void updateMasterTableStatus(MasterTable masterTable, String finalStatus) {
        try {
            viewModel.updateMasterTableStatus(masterTable, finalStatus);
            if (viewModel.getmasterTableLiveData() != null) {
                Observer updateMasterTableStatusObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        MasterTable masterTableResult = (MasterTable) o;
                        viewModel.getmasterTableLiveData().removeObserver(this);
                        if (masterTableResult != null && masterTableResult.isAllDataCaptured() &&
                                masterTableResult.getFinalStatus().equalsIgnoreCase(finalStatus)) {
//                            getMasterTableDetails();
                            // TODO: CALLING SYNC SERVICE
                            syncAllScreenByClient(masterTable);
                        } else {
                            String errorMsg = "Unable to update data";
                            if (masterTableResult != null && !TextUtils.isEmpty(masterTableResult.getResponse())) {
                                errorMsg = masterTableResult.getResponse();
                            }
                            appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ERROR, errorMsg);
                        }
                    }
                };
                viewModel.getmasterTableLiveData().observe(this, updateMasterTableStatusObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();

            INSERT_LOG("updateMasterTableStatus","Exception : "+ex.getMessage());
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
//                case R.id.menu_item_new_lead:
//                    drawerLayout.closeDrawer(Gravity.LEFT);
//                   /* appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT,
//                            "This Feature Is Not Yet Developed");*/
//                    break;
//                case R.id.menu_item_tasks:
//                    appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT,
//                            "This Feature Is Not Yet Developed");
//                    break;
//                case R.id.menu_item_search:
//                    appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT,
//                            "This Feature Is Not Yet Developed");
//                    break;
//                case R.id.menu_item_settings:
//                    appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT,
//                            "This Feature Is Not Yet Developed");
//                    break;
//                case R.id.menu_item_logout:
//                    appHelper.getDialogHelper().getConfirmationDialog().showTwoButtons("Do you want to logout ? ", new ConfirmationDialog.ActionCallback() {
//                        @Override
//                        public void onAction() {
//                            finish();
//                        }
//                    });
//                    break;
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            INSERT_LOG("selectItemDrawer","Exception : "+ex.getMessage());
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


    @Override
    public void onBackPressed() {
        finish();

    }

    public void getLeadDataFromServer(){
        try{
            // TODO: ************ To get Data tvName server  **************
            appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();
            viewModel.init(SCREEN_NO,SCREEN_NAME_LEAD,loanType,PROJECT_ID_LOS,productId,CLIENT_ID,userId,MODULE_TYPE_LEAD);
            Observer observer = new Observer() {
                @Override
                public void onChanged(@Nullable Object o) {
                    appHelper.getDialogHelper().getLoadingDialog().closeDialog();
                    List<DynamicUITable> list = (List<DynamicUITable>) o;
                    viewModel.getDynamicUITableLiveData().removeObserver(this);

                    if(list !=null && list.size()>0) {
                        DynamicUITable dynamicUITable=getObjectByTAG(TAG_NAME_SAVE_BUTTON,list);
                        if(dynamicUITable !=null) {
                            getLeadTableDataFromServer(SCREEN_NO, SCREEN_NAME_LEAD, loanType, userId,dynamicUITable,list);
                        }else{
                            // TODO: Calling getMyStage
                            getMyStages();
                        }
                    }else{
                        // TODO: Calling getMyStage
                        getMyStages();
                    }

                }
            };
            viewModel.getDynamicUITableLiveData().observe(this, observer);

            // TODO: ************ To get Data tvName server  **************
        }catch (Exception ex){
            ex.printStackTrace();
            appHelper.getDialogHelper().getLoadingDialog().closeDialog();
            INSERT_LOG("getLeadDataFromServer","Exception : "+ex.getMessage());

            // TODO: Calling getMyStage
            getMyStages();
        }
    }


    public void getLeadTableDataFromServer(String screenNo,String screenName,String loanType, String userId,
                                           DynamicUITable dynamicUITable,List<DynamicUITable> dynamicUITableList) {
        try {

            appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();

            viewModel.getLeadTableDataFromServer(screenNo,screenName,loanType,userId,dynamicUITable,dynamicUITableList);
            if (viewModel.getLeadTableLiveData() != null) {
                Observer getLeadRawDataObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        appHelper.getDialogHelper().getLoadingDialog().closeDialog();
                        viewModel.getLeadTableLiveData().removeObserver(this);

                        // TODO: Calling getMyStage
                        getMyStages();
                    }
                };
                viewModel.getLeadTableLiveData().observe(this, getLeadRawDataObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            appHelper.getDialogHelper().getLoadingDialog().closeDialog();

            INSERT_LOG("getLeadTableDataFromServer","Exception : "+ex.getMessage());
        }
    }

    public void getMyStages() {
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
            }
            else if (loanType.equalsIgnoreCase(LOAN_NAME_AHL)) {
                workflowId = WORKFLOW_ID_AHL; // TODO: AHL workflow id
            } else {
                workflowId = WORKFLOW_ID_MSME;
            }
            viewModel.getMyStages(userId, workflowId,loanType,branchId,branchGSTcode);
            if (viewModel.getMyStageLiveData() != null) {
                Observer getMyStagesObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        appHelper.getDialogHelper().getLoadingDialog().closeDialog();
                        viewModel.getMyStageLiveData().removeObserver(this);

                        // TODO: GET MASTER TABLE DETAIL
                        getMasterTableDetails();
                    }
                };
                viewModel.getMyStageLiveData().observe(this, getMyStagesObserver);
            }
        }catch (Exception ex){
            ex.printStackTrace();
            appHelper.getDialogHelper().getLoadingDialog().closeDialog();

            INSERT_LOG("getMyStages","Exception : "+ex.getMessage());
        }
    }

    public void getMasterTableDetails() {
        appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();

        try {
            viewModel.getMasterTableByUserIdAndLoanType(userId, loanType);
            if (viewModel.getmasterTableLiveDataList() != null) {
                Observer getMasterTableDetailsObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        appHelper.getDialogHelper().getLoadingDialog().closeDialog();
                        List<MasterTable> masterTableList = (List<MasterTable>) o;
                        viewModel.getmasterTableLiveDataList().removeObserver(this);

                        if (masterTableList != null && masterTableList.size() > 0) {
                            rlNoLeads.setVisibility(View.GONE);
                            rvLeadDetails.setVisibility(View.VISIBLE);

                            if (leadToApplicationAdapter != null) {
                                leadToApplicationAdapter.setItem(masterTableList);
                            } else {
                                leadToApplicationAdapter = new LeadToApplicationAdapter(LeadToApplicationActivity.this, masterTableList,
                                        LeadToApplicationActivity.this, appHelper);
                                rvLeadDetails.setAdapter(leadToApplicationAdapter);
                                leadToApplicationAdapter.notifyDataSetChanged();
                            }

                        } else {
                            rvLeadDetails.setVisibility(View.GONE);
                            rlNoLeads.setVisibility(View.VISIBLE);
                        }
                    }
                };
                viewModel.getmasterTableLiveDataList().observe(this, getMasterTableDetailsObserver);
            }
        }catch (Exception ex){
            appHelper.getDialogHelper().getLoadingDialog().closeDialog();
            ex.printStackTrace();

            INSERT_LOG("getMasterTableByCGTTable","Exception : "+ex.getMessage());
        }
    }



    public void getWorkflowHistory() {
        appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();

        try {
            viewModel.getWorkflowHistory(userId, loanType);
            if (viewModel.getmasterTableLiveDataList() != null) {
                Observer getMasterTableDetailsObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        appHelper.getDialogHelper().getLoadingDialog().closeDialog();
                        List<MasterTable> masterTableList = (List<MasterTable>) o;
                        viewModel.getmasterTableLiveDataList().removeObserver(this);

                        if (masterTableList != null && masterTableList.size() > 0) {
                            rlNoLeads.setVisibility(View.GONE);
                            rvLeadDetails.setVisibility(View.VISIBLE);

                            if (leadToApplicationAdapter != null) {
                                leadToApplicationAdapter.setItem(masterTableList);
                            } else {
                                leadToApplicationAdapter = new LeadToApplicationAdapter(LeadToApplicationActivity.this, masterTableList,
                                        LeadToApplicationActivity.this, appHelper);
                                rvLeadDetails.setAdapter(leadToApplicationAdapter);
                                leadToApplicationAdapter.notifyDataSetChanged();
                            }

                        } else {
                            rvLeadDetails.setVisibility(View.GONE);
                            rlNoLeads.setVisibility(View.VISIBLE);
                        }
                    }
                };
                viewModel.getmasterTableLiveDataList().observe(this, getMasterTableDetailsObserver);
            }
        }catch (Exception ex){
            appHelper.getDialogHelper().getLoadingDialog().closeDialog();
            ex.printStackTrace();

            INSERT_LOG("getCurrentStageDetailsByClientId","Exception : "+ex.getMessage());
        }
    }



    @Override
    public void syncCallback(int position, MasterTable masterTable) {
        try {
            syncAllScreenByClient(masterTable);
        } catch (Exception ex) {
            ex.printStackTrace();
            INSERT_LOG("syncCallback","Exception : "+ex.getMessage());
        }
    }

    @Override
    public void submitCallback(int position, MasterTable masterTable) {
        try {
            updateMasterTableStatus(masterTable, FINAL_STATUS_SUBMITTED);
        } catch (Exception ex) {
            ex.printStackTrace();
            INSERT_LOG("submitCallback","Exception : "+ex.getMessage());
        }
    }

    @Override
    public void rejectCallback(int position, MasterTable masterTable) {
        try {
            // TODO: Reject functionality
            updateMasterTableStatusForRejectAndSendback(masterTable,FINAL_STATUS_REJECTED);
        } catch (Exception ex) {
            ex.printStackTrace();
            INSERT_LOG("rejectCallback","Exception : "+ex.getMessage());
        }
    }


    private void INSERT_LOG(String methodName,String message){
        try{
            if(viewModel !=null){
                viewModel.insertLog(methodName,message,userId,"","LeadToApplicationActivity"
                        ,CLIENT_ID,loanType,moduleType);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
