package com.saartak.el.activities;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.text.method.DigitsKeyListener;
import android.util.Log;
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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bfil.uilibrary.dialogs.ConfirmationDialog;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.saartak.el.App;
import com.saartak.el.R;
import com.saartak.el.adapter.LeadDetailsAdapter;
import com.saartak.el.constants.AppConstant;
import com.saartak.el.database.entity.DynamicUITable;
import com.saartak.el.database.entity.RawDataTable;
import com.saartak.el.database.entity.SubmitDataTable;
import com.saartak.el.models.SubmitDataDTO;
import com.saartak.el.view_models.DynamicUIViewModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

import static com.saartak.el.constants.AppConstant.DATE_FORMAT_DD_MM_YYYY2;
import static com.saartak.el.constants.AppConstant.LOAN_NAME_INDIVIDUAL;
import static com.saartak.el.constants.AppConstant.LOAN_NAME_MSME;
import static com.saartak.el.constants.AppConstant.PARAM_LOAN_TYPE;
import static com.saartak.el.constants.AppConstant.PARAM_BRANCH_GST_CODE;
import static com.saartak.el.constants.AppConstant.PARAM_MODULE_TYPE;
import static com.saartak.el.constants.AppConstant.MODULE_TYPE_LEAD;
import static com.saartak.el.constants.AppConstant.PARAM_BRANCH_ID;
import static com.saartak.el.constants.AppConstant.PARAM_CLIENT_ID;
import static com.saartak.el.constants.AppConstant.PARAM_PRODUCT_ID;
import static com.saartak.el.constants.AppConstant.PARAM_REQUEST_FROM_SAARTHI_APP;
import static com.saartak.el.constants.AppConstant.PARAM_ROLE_NAME;
import static com.saartak.el.constants.AppConstant.PARAM_SCREEN_NO;
import static com.saartak.el.constants.AppConstant.PARAM_USER_ID;
import static com.saartak.el.constants.AppConstant.PARAM_USER_NAME;
import static com.saartak.el.constants.AppConstant.REQUEST_CODE_NEW_LEAD;
import static com.saartak.el.constants.AppConstant.REQUEST_CODE_UPDATE_LEAD;
import static com.saartak.el.constants.AppConstant.SCREEN_N0_LEAD;
import static com.saartak.el.constants.AppConstant.SCREEN_N0_LEAD_IL;
import static com.saartak.el.constants.AppConstant.SCREEN_N0_LEAD_MSME;
import static com.saartak.el.constants.AppConstant.STAGE_ID_LEAD;
import static com.saartak.el.dynamicui.constants.ParametersConstant.TAG_NAME_INTERESTED_IN_LOAN;

public class LeadDetailsActivity extends LOSBaseActivity implements LeadDetailsAdapter.SyncCallbackInterface {
    private static final String TAG = LeadDetailsActivity.class.getCanonicalName();
    RecyclerView rvLeadDetails;
    Button btnNewLead, btnSyncAll;
    SearchView searchByPhoneNo;
    AppCompatSpinner spinnerSortByDate, spinnerSortByLoanType, spinnerSortByInterested;
    LeadDetailsAdapter leadDetailsAdapter;
    List<RawDataTable> rawDataTableList;
    private Toolbar toolbar;
    TextView tvClientName;
    String SCREEN_NO;
    boolean IS_REQUEST_FROM_SAARTHI;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView navigationView;
    CoordinatorLayout coordinatorLayout;
    ImageView ivStaffImage;
    RelativeLayout rlNoLeads;
    TextView tvStaffId, tvStaffName, tvSOBDate;

    @Inject
    public ViewModelProvider.Factory viewModelFactory;
    public DynamicUIViewModel viewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,
//                WindowManager.LayoutParams.FLAG_SECURE);

        setContentView(R.layout.activity_lead_details_card_view);
        rvLeadDetails =  findViewById(R.id.rv_lead_details);
        rlNoLeads =  findViewById(R.id.rl_no_leads);
        btnNewLead =  findViewById(R.id.btn_new_lead);
        btnSyncAll =  findViewById(R.id.btn_sync_all);
        searchByPhoneNo =  findViewById(R.id.sv_phone_no);
        spinnerSortByDate =  findViewById(R.id.sp_sort_by_date);
        spinnerSortByLoanType =  findViewById(R.id.sp_sort_by_loan_type);
        spinnerSortByInterested =  findViewById(R.id.sp_sort_by_interest_or_not);
        toolbar =  findViewById(R.id.toolbar);
        tvClientName =  findViewById(R.id.tv_client_name_custom);
        EditText searchEditText =  searchByPhoneNo.findViewById(androidx.appcompat.R.id.search_src_text);
        searchEditText.setKeyListener(DigitsKeyListener.getInstance("abcdefghijklmnopqrstuvwxyz ABCDEFGHIJKLMNOPQRSTUVWXYZ 0123456789"));
        searchEditText.setRawInputType(InputType.TYPE_TEXT_VARIATION_PERSON_NAME);
        searchEditText.setTextColor(getResources().getColor(R.color.black));
        searchEditText.setHintTextColor(getResources().getColor(R.color.black));

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
        roleName = getIntent().getStringExtra(PARAM_ROLE_NAME);
        productId = getIntent().getStringExtra(PARAM_PRODUCT_ID);
        IS_REQUEST_FROM_SAARTHI = getIntent().getBooleanExtra(PARAM_REQUEST_FROM_SAARTHI_APP, false);

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
        rvLeadDetails.setNestedScrollingEnabled(false);
       // rvLeadDetails.addItemDecoration(new MyDividerItemDecoration(this, DividerItemDecoration.VERTICAL, 36));


        btnNewLead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                CLIENT_ID = AppConstant.getRandomNumberString() + EMP_LAST_5_DIGIT;
                String timeStamp = new SimpleDateFormat("yyMMddHHmmss",
                        Locale.getDefault()).format(new Date());
                    if (!TextUtils.isEmpty(userId)) {
                        EMP_LAST_5_DIGIT = userId.substring(3);
                        CLIENT_ID = EMP_LAST_5_DIGIT + timeStamp;
                        // TODO: CLIENT ID FORMATION ( FIRST 7 DIGIT EMP ID - 100004141 , YEAR 21 ,
                        //  MONTH 11 , DATE 12, HOUR 08 , MINUTE 25 , SECOND 48 ==> TOTAL 19 DIGITS )
                        if (!TextUtils.isEmpty(CLIENT_ID) && CLIENT_ID.length() > 12) {
                            Intent intentApplication = new Intent(LeadDetailsActivity.this, BaseActivity.class);
                            intentApplication.putExtra(AppConstant.PARAM_LOAN_TYPE, loanType);
                            intentApplication.putExtra(PARAM_USER_NAME, userName);
                            intentApplication.putExtra(PARAM_USER_ID, userId);
                            intentApplication.putExtra(PARAM_BRANCH_ID, branchId);
                            intentApplication.putExtra(PARAM_BRANCH_GST_CODE, branchGSTcode);
                            intentApplication.putExtra(PARAM_CLIENT_ID, CLIENT_ID);
                            intentApplication.putExtra(PARAM_MODULE_TYPE, MODULE_TYPE_LEAD);
                            startActivityForResult(intentApplication, REQUEST_CODE_NEW_LEAD);
                        } else {

                            appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, "Invalid Client ID");
                        }
                    } else {
                        appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, "User ID Is Empty");
                    }
            }
        });
        btnSyncAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (appHelper.isNetworkAvailable()) {
                    //appHelper.getDialogHelper().getConfirmationDialog().showTwoButtons("Would You Like To Sync All Leads ?", new ConfirmationDialog.ActionCallback() {
                    appHelper.getDialogHelper().getConfirmationDialog().showTwoButtons("Would You Like To Proceed/Convert All To Application ?", new ConfirmationDialog.ActionCallback() {
                        @Override
                        public void onAction() {
                            if (rawDataTableList != null && rawDataTableList.size() > 0) {
                                boolean allSynced = false;
                                for (RawDataTable rawDataTable : rawDataTableList) {
                                    // TODO: Need to remove for lead
                                    HashMap<String, Object> hashMap = setKeyValueForObject(rawDataTable);
                                    postSubmittedData(rawDataTable);
                                    if (hashMap != null&& hashMap.containsKey(TAG_NAME_INTERESTED_IN_LOAN)) {
                                        if (hashMap.get(TAG_NAME_INTERESTED_IN_LOAN).toString().equalsIgnoreCase("Yes")) {
                                            if (!rawDataTable.isSync()) {
                                                allSynced = false;
                                                postSubmittedData(rawDataTable);
                                            } else {
                                                allSynced = true;
                                            }
                                        }
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

                }else{
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
                    if (leadDetailsAdapter != null && leadDetailsAdapter.getFilter() != null) {
                        leadDetailsAdapter.getFilter().filter(parent.getSelectedItem().toString());
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
                    if (leadDetailsAdapter != null && leadDetailsAdapter.getFilter() != null) {
                        leadDetailsAdapter.getFilter().filter(parent.getSelectedItem().toString());
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

                    if (leadDetailsAdapter != null && leadDetailsAdapter.getFilter() != null) {
                        leadDetailsAdapter.getFilter().filter(parent.getSelectedItem().toString());
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

                if (leadDetailsAdapter != null && leadDetailsAdapter.getFilter() != null) {
                    leadDetailsAdapter.getFilter().filter(query);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                // filter recycler view when text is changed
                spinnerSortByDate.setSelection(0);
                spinnerSortByLoanType.setSelection(0);
                spinnerSortByInterested.setSelection(0);

                if (leadDetailsAdapter != null && leadDetailsAdapter.getFilter() != null) {
                    leadDetailsAdapter.getFilter().filter(query);
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
        }
        else if (loanType.equalsIgnoreCase(LOAN_NAME_MSME)) {
            SCREEN_NO = SCREEN_N0_LEAD_MSME;
        } else {
            SCREEN_NO = SCREEN_N0_LEAD;
        }

        // TODO: ************ To get Data from Local DB  **************

        getLeadRawDataFromLocalDB(SCREEN_NO,userId);

        // TODO: ************ To get Data from Local DB  **************

        // TODO: ************ To get Data from server  **************

//        viewModel.init(SCREEN_NO,SCREEN_NAME_LEAD,loanType,PROJECT_ID_LOS,productId,CLIENT_ID,userId,MODULE_TYPE_LEAD);
//        Observer observer = new Observer() {
//            @Override
//            public void onChanged(@Nullable Object o) {
//                List<DynamicUITable> list = (List<DynamicUITable>) o;
//                viewModel.getDynamicUITableLiveData().removeObserver(this);
//
//                if(list !=null && list.size()>0) {
//                    DynamicUITable dynamicUITable=getObjectByTAG(TAG_NAME_SAVE_BUTTON,list);
//                    if(dynamicUITable !=null) {
//                        getLeadRawDataFromServer(SCREEN_NO, SCREEN_NAME_LEAD, loanType, userId,dynamicUITable,list);
//                    }
//                }
//
//            }
//        };
//        viewModel.getDynamicUITableLiveData().observe(this, observer);

        // TODO: ************ To get Data from server  **************
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
                    getLeadRawDataFromLocalDB(screenNO,userId);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
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
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void getLeadRawDataFromLocalDB(String screen, String userId) {
        ArrayList<HashMap<String, Object>> hashMapList = new ArrayList<>();
        try {

            appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();

            viewModel.getLeadRawData(screen,userId);
            if (viewModel.getRawTableLiveData() != null) {
                Observer getLeadRawDataObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        rawDataTableList = (List<RawDataTable>) o;
                        viewModel.getRawTableLiveData().removeObserver(this);

                        appHelper.getDialogHelper().getLoadingDialog().closeDialog();

                        if (rawDataTableList != null && rawDataTableList.size() > 0) {
                            for (RawDataTable rawDataTable : rawDataTableList) {
                                HashMap<String, Object> hashMap = setKeyValueForObject(rawDataTable);
                                hashMapList.add(hashMap);
                            }
                            Log.d(TAG, "HashMapList ==> " + hashMapList.toString());
                            if (hashMapList != null && hashMapList.size() > 0) {
                                rlNoLeads.setVisibility(View.GONE);

                                leadDetailsAdapter = new LeadDetailsAdapter(LeadDetailsActivity.this, hashMapList,
                                        rawDataTableList, LeadDetailsActivity.this, appHelper);
                                rvLeadDetails.setAdapter(leadDetailsAdapter);
                                leadDetailsAdapter.notifyDataSetChanged();
                            } else {
                                rlNoLeads.setVisibility(View.VISIBLE);
                            }
                        }
                    }
                };
                viewModel.getRawTableLiveData().observe(this, getLeadRawDataObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            appHelper.getDialogHelper().getLoadingDialog().closeDialog();

            INSERT_LOG("getLeadRawDataFromLocalDB","Exception : "+ex.getMessage());
        }
    }


    public void insertOrUpdateRawData(RawDataTable rawDataTable, List<DynamicUITable> viewParametersList,
                                        DynamicUITable dynamicUITable) {
        try {
            viewModel.insertOrUpdateParentRawData(rawDataTable, viewParametersList, dynamicUITable);
            Observer insertOrUpdateParentRawDataObserver = new Observer() {
                @Override
                public void onChanged(@Nullable Object o) {
                    viewModel.getRawTableLiveData().removeObserver(this);
//                    getRawDataForParentFragment(rawDataTable.getScreen_name(), viewParametersList);
                }
            };
            viewModel.getRawTableLiveData().observe(this, insertOrUpdateParentRawDataObserver);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void getLeadRawDataFromServer(String screenNo,String screenName,String loanType, String userId,
                                         DynamicUITable dynamicUITable,List<DynamicUITable> dynamicUITableList) {
        ArrayList<HashMap<String, Object>> hashMapList = new ArrayList<>();
        try {

            appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();

            viewModel.getLeadRawDataFromServer(screenNo,screenName,loanType,userId,dynamicUITable,dynamicUITableList);
            if (viewModel.getRawTableLiveData() != null) {
                Observer getLeadRawDataObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        rawDataTableList = (List<RawDataTable>) o;
                        viewModel.getRawTableLiveData().removeObserver(this);

                        appHelper.getDialogHelper().getLoadingDialog().closeDialog();

                        if (rawDataTableList != null && rawDataTableList.size() > 0) {
                            for (RawDataTable rawDataTable : rawDataTableList) {
                                HashMap<String, Object> hashMap = setKeyValueForObject(rawDataTable);
                                hashMapList.add(hashMap);
                            }
                            Log.d(TAG, "HashMapList ==> " + hashMapList.toString());
                            if (hashMapList != null && hashMapList.size() > 0) {
                                rlNoLeads.setVisibility(View.GONE);

                                leadDetailsAdapter = new LeadDetailsAdapter(LeadDetailsActivity.this, hashMapList,
                                        rawDataTableList, LeadDetailsActivity.this, appHelper);
                                rvLeadDetails.setAdapter(leadDetailsAdapter);
                                leadDetailsAdapter.notifyDataSetChanged();
                            } else {
                                rlNoLeads.setVisibility(View.VISIBLE);
                            }
                        }
                    }
                };
                viewModel.getRawTableLiveData().observe(this, getLeadRawDataObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            appHelper.getDialogHelper().getLoadingDialog().closeDialog();

            INSERT_LOG("getLeadRawDataFromServer","Exception : "+ex.getMessage());
        }
    }

    private HashMap<String, Object> setKeyValueForObject(RawDataTable rawDataTable) {
        HashMap<String, Object> rawDataHashMap = new HashMap<>();
        try {
            if (!TextUtils.isEmpty(rawDataTable.getRawdata())) {
                String rawData = rawDataTable.getRawdata();

                rawDataHashMap = App.createHashMapFromJsonString(rawData);
               /* if (rawDataHashMap != null && rawDataHashMap.size() > 0) {
                    if (rawDataHashMap.containsKey(TAG_NAME_TYPE_OF_LOAN)) {
                        String typeOfLoan = rawDataHashMap.get(TAG_NAME_TYPE_OF_LOAN).toString();
                        String shortNameOfLoan = getShortNameOfLoan(typeOfLoan);
                        String BranchName = "";
                        if (loginResponseDTO != null && !TextUtils.isEmpty(loginResponseDTO.getBranchName())) {
                            BranchName = loginResponseDTO.getBranchName();
                        }
                        addLeadIDtoHashMap(rawDataHashMap, shortNameOfLoan, BranchName);
                    }
                }*/
                Log.d(TAG, "Hashmap ==> " + rawDataHashMap);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            INSERT_LOG("setKeyValueForObject","Exception : "+ex.getMessage());
        }
        return rawDataHashMap;
    }


    private void postSubmittedData(RawDataTable rawDataTable) {
        try {
            if (appHelper.isNetworkAvailable()) {

                appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();

                final SubmitDataTable submitDataTable = new SubmitDataTable();
                submitDataTable.setScreenId(SCREEN_NO);
                submitDataTable.setUniqueID(rawDataTable.getClient_id());
                submitDataTable.setApplicationId(rawDataTable.getClient_id());
                submitDataTable.setIMEINumber(appHelper.getIMEI());
                if( ! TextUtils.isEmpty(branchGSTcode)) {
                    submitDataTable.setBCBRID(branchGSTcode);
                }else {
                    submitDataTable.setBCBRID(branchId);
                }
                submitDataTable.setBCID(branchId);
                submitDataTable.setCreatedBy(userId);
                submitDataTable.setStageId(STAGE_ID_LEAD); // TODO: LEAD STAGE ID
                ArrayList<String> stringArrayList = new ArrayList<>();
                stringArrayList.add(rawDataTable.getRawdata());
                submitDataTable.setRawData(rawDataTable.getRawdata());
                // TODO: 22-12-2020 submit DTO
                final SubmitDataDTO submitDataDTO = new SubmitDataDTO();
                submitDataDTO.setScreenId(SCREEN_NO);
                submitDataDTO.setUniqueID(rawDataTable.getClient_id());
                submitDataDTO.setApplicationId(rawDataTable.getClient_id());
                submitDataDTO.setIMEINumber(appHelper.getIMEI());
                if( ! TextUtils.isEmpty(branchGSTcode)) {
                    submitDataDTO.setBCBRID(branchGSTcode);
                }else {
                    submitDataDTO.setBCBRID(branchId);
                }
                submitDataDTO.setBCID(branchId);
                submitDataDTO.setCreatedBy(userId);
                submitDataDTO.setStageId(STAGE_ID_LEAD); // TODO: LEAD STAGE ID
                JsonParser jsonParser = new JsonParser();
                JsonObject jsonObject = (JsonObject)jsonParser.parse(rawDataTable.getRawdata());
                JsonArray jsonArrayRawData=new JsonArray();
                jsonArrayRawData.add(jsonObject);
                submitDataDTO.setRawData(jsonArrayRawData);
                viewModel.postSubmittedData(submitDataTable,submitDataDTO, rawDataTable.getRawdata(), SCREEN_NO, rawDataTable);
                if (viewModel.getDynamicUITableLiveData() != null) {
                    Observer postSubmittedDataObserver = new Observer() {
                        @Override
                        public void onChanged(@Nullable Object o) {
                            viewModel.getDynamicUITableLiveData().removeObserver(this);
                            appHelper.getDialogHelper().getLoadingDialog().closeDialog();

                            getLeadRawDataFromLocalDB(SCREEN_NO,userId);
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


    @Override
    public void syncCallback(int position, RawDataTable rawDataTable) {
        try {
            if (rawDataTable != null) {
                postSubmittedData(rawDataTable);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            INSERT_LOG("syncCallback","Exception : "+ex.getMessage());
        }
    }


    @Override
    public void callBackForPhoneCall(int position, String phoneNo) {
        try {
            if (!TextUtils.isEmpty(phoneNo)) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + phoneNo));
                startActivity(callIntent);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            INSERT_LOG("callBackForPhoneCall","Exception : "+ex.getMessage());
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


    private void INSERT_LOG(String methodName,String message){
        try{
            if(viewModel !=null){
                viewModel.insertLog(methodName,message,userId,"","LeadDetailsActivity"
                        ,CLIENT_ID,loanType,moduleType);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

}
