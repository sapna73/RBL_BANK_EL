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
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.swadhaar.los.BuildConfig;
import com.swadhaar.los.R;
import com.swadhaar.los.adapter.TargetDetailsAdapter;
import com.swadhaar.los.constants.AppConstant;
import com.swadhaar.los.database.entity.CenterCreationTable;
import com.swadhaar.los.database.entity.EligibilityTable;
import com.swadhaar.los.database.entity.LoanProductCodeTable;
import com.swadhaar.los.database.entity.MasterTable;
import com.swadhaar.los.database.entity.RawDataTable;
import com.swadhaar.los.database.entity.SubmitDataTable;
import com.swadhaar.los.helper.MyDividerItemDecoration;
import com.swadhaar.los.models.EligibilityByGroupDTO;
import com.swadhaar.los.models.SubmitDataDTO;
import com.swadhaar.los.view_models.DynamicUIViewModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

import static com.swadhaar.los.constants.AppConstant.DATE_FORMAT_DD_MM_YYYY2;
import static com.swadhaar.los.constants.AppConstant.LOAN_NAME_INDIVIDUAL;
import static com.swadhaar.los.constants.AppConstant.LOAN_NAME_MSME;
import static com.swadhaar.los.constants.AppConstant.MODULE_TYPE_APPLICANT;
import static com.swadhaar.los.constants.AppConstant.MODULE_TYPE_LOAN_PROPOSAL_WITH_NOMINEE;
import static com.swadhaar.los.constants.AppConstant.PARAM_BRANCH_GST_CODE;
import static com.swadhaar.los.constants.AppConstant.PARAM_BRANCH_ID;
import static com.swadhaar.los.constants.AppConstant.PARAM_CENTER_TABLE_JSON;
import static com.swadhaar.los.constants.AppConstant.PARAM_CLIENT_ID;
import static com.swadhaar.los.constants.AppConstant.PARAM_LOAN_TYPE;
import static com.swadhaar.los.constants.AppConstant.PARAM_MODULE_TYPE;
import static com.swadhaar.los.constants.AppConstant.PARAM_PRODUCT_ID;
import static com.swadhaar.los.constants.AppConstant.PARAM_SCREEN_NO;
import static com.swadhaar.los.constants.AppConstant.PARAM_USER_ID;
import static com.swadhaar.los.constants.AppConstant.PARAM_USER_NAME;
import static com.swadhaar.los.constants.AppConstant.REQUEST_CODE_NEW_LEAD;
import static com.swadhaar.los.constants.AppConstant.REQUEST_CODE_UPDATE_LEAD;
import static com.swadhaar.los.constants.AppConstant.SCREEN_N0_LEAD;
import static com.swadhaar.los.constants.AppConstant.SCREEN_N0_LEAD_IL;
import static com.swadhaar.los.constants.AppConstant.SCREEN_N0_LEAD_MSME;
import static com.swadhaar.los.constants.AppConstant.STAGE_ID_ZERO;
import static com.swadhaar.los.dynamicui.constants.ParametersConstant.ERROR_MESSAGE_SYNC_FAILED;
import static com.swadhaar.los.dynamicui.constants.ParametersConstant.ERROR_MESSAGE_SYNC_SUCCESS;
import static com.swadhaar.los.dynamicui.constants.ParametersConstant.ERROR_MESSAGE_UNABLE_TO_INSERT_LOAN_PRODUCT;
import static com.swadhaar.los.dynamicui.constants.ParametersConstant.ERROR_MESSAGE_UNABLE_TO_UPDATE_SESSION;
import static com.swadhaar.los.dynamicui.constants.ParametersConstant.FINAL_STATUS_REJECTED;
import static com.swadhaar.los.dynamicui.constants.ParametersConstant.LOAN_PRODUCT_CODE_CYCLE_1_JLG_LOAN;
import static com.swadhaar.los.dynamicui.constants.ParametersConstant.LOAN_PRODUCT_CYCLE_1_JLG_LOAN;

public class TargetDetailsActivity extends LOSBaseActivity implements TargetDetailsAdapter.SyncCallbackInterface {


    private static final String TAG = TargetDetailsActivity.class.getCanonicalName();
    RecyclerView rvLeadDetails;
    SearchView searchByPhoneNo;
    TargetDetailsAdapter targetDetailsAdapter;
    AppCompatSpinner spinnerSortByDate,  spinnerSortByAlphabeticalOrder;
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

        setContentView(R.layout.activity_target_details);
        rvLeadDetails = (RecyclerView) findViewById(R.id.rv_lead_details);
        rlNoLeads = (RelativeLayout) findViewById(R.id.rl_no_leads);
        searchByPhoneNo = (SearchView) findViewById(R.id.sv_phone_no);
        btnNewTarget =  findViewById(R.id.fb_add_target_details);
        spinnerSortByDate = (AppCompatSpinner) findViewById(R.id.sp_sort_by_date);
        spinnerSortByAlphabeticalOrder = (AppCompatSpinner) findViewById(R.id.sp_sort_by_alphabetical_order);
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

        spinnerSortByDate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0) {

                    spinnerSortByAlphabeticalOrder.setSelection(0);
                    searchByPhoneNo.setQuery("", false);
                    searchByPhoneNo.setIconified(true);
                    if (targetDetailsAdapter != null && targetDetailsAdapter.getFilter() != null) {
                        targetDetailsAdapter.getFilter().filter(parent.getSelectedItem().toString());
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

                    if (targetDetailsAdapter != null && targetDetailsAdapter.getFilter() != null) {
                        targetDetailsAdapter.getFilter().filter(parent.getSelectedItem().toString());
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


                if (targetDetailsAdapter != null && targetDetailsAdapter.getFilter() != null) {
                    targetDetailsAdapter.getFilter().filter(query);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                // filter recycler view when text is changed
               spinnerSortByDate.setSelection(0);
                spinnerSortByAlphabeticalOrder.setSelection(0);

                if (targetDetailsAdapter != null && targetDetailsAdapter.getFilter() != null) {
                    targetDetailsAdapter.getFilter().filter(query);
                }
                return false;
            }
        });

        btnNewTarget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // TODO: Inserting into Loan Product Code Table
                insertLoanProductCodeTable(LOAN_PRODUCT_CODE_CYCLE_1_JLG_LOAN,LOAN_PRODUCT_CYCLE_1_JLG_LOAN);

            }
        });

        configureDagger();
        configureViewModel();

    }

    private void insertLoanProductCodeTable(String loanProductCode , String loanProductName) {
        try{
            viewModel.insertLoanProductCodeTable(loanProductCode,loanProductName);
            if (viewModel.getLoanProductCodeTableLiveData() != null) {
                Observer observer = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        viewModel.getLoanProductCodeTableLiveData().removeObserver(this);
                        LoanProductCodeTable loanProductCodeTable=(LoanProductCodeTable)o;

                        if(loanProductCodeTable !=null) {
                            // TODO: Calling Base Activity
                            callingBaseActivity();
                        }else{
                            appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT,ERROR_MESSAGE_UNABLE_TO_INSERT_LOAN_PRODUCT);
                        }
                    }
                };
                viewModel.getLoanProductCodeTableLiveData().observe(this, observer);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }

    }

    private void callingBaseActivity() {
        try{
            String timeStamp = new SimpleDateFormat("yyMMddHHmmss",
                    Locale.getDefault()).format(new Date());
            if( ! TextUtils.isEmpty(EMP_LAST_5_DIGIT)) {
                if (!TextUtils.isEmpty(userId)) {
                    EMP_LAST_5_DIGIT = userId.substring(3);
                    CLIENT_ID = EMP_LAST_5_DIGIT + timeStamp;
                    // TODO: CLIENT ID FORMATION ( FIRST 7 DIGIT EMP ID - 100004141 , YEAR 19 ,
                    //  MONTH 11 , DATE 12, HOUR 08 , MINUTE 25 , SECOND 48 ==> TOTAL 19 DIGITS )
                    if (!TextUtils.isEmpty(CLIENT_ID) && CLIENT_ID.length() > 16) {
//                            Intent intentApplication = new Intent(TargetDetailsActivity.this, MemberDetailsActivity.class);
                        Intent applicant = new Intent(TargetDetailsActivity.this, BaseActivity.class);
                        applicant.putExtra(PARAM_LOAN_TYPE, loanType);
                        applicant.putExtra(PARAM_USER_NAME, userName);
                        applicant.putExtra(PARAM_USER_ID, userId);
                        applicant.putExtra(PARAM_CLIENT_ID, CLIENT_ID);
                        applicant.putExtra(PARAM_BRANCH_ID, branchId);
                        applicant.putExtra(PARAM_BRANCH_GST_CODE, branchGSTcode);
                        applicant.putExtra(PARAM_MODULE_TYPE, MODULE_TYPE_APPLICANT);
                        if(  CENTER_CREATION_TABLE !=null){
                            String centerTableJson=new Gson().toJson(CENTER_CREATION_TABLE, CenterCreationTable.class);
                            if(!TextUtils.isEmpty(centerTableJson)){
                                applicant.putExtra(PARAM_CENTER_TABLE_JSON, centerTableJson);
                            }
                        }
                        startActivity(applicant);
                    } else {

                        appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, "Invalid Client ID");
                    }
                } else {
                    appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, "User ID Is Empty");
                }
            }else {
                appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, "Employee ID Is Empty");
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
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
        }
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
            String centerJsonString = getIntent().getStringExtra(PARAM_CENTER_TABLE_JSON);
            if (!TextUtils.isEmpty(centerJsonString)) {
                 CENTER_CREATION_TABLE = new Gson().fromJson(centerJsonString, CenterCreationTable.class);
                if (CENTER_CREATION_TABLE != null) {
                    getMasterTableDetailsByCenter();
                }
            }

            spinnerSortByDate.setSelection(0);
            spinnerSortByAlphabeticalOrder.setSelection(0);
            searchByPhoneNo.setQuery("", false);
            searchByPhoneNo.setIconified(true);



        } catch (Exception ex) {
            ex.printStackTrace();
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
                            getMasterTableDetailsByCenter();

                           /* if(submitDataTable !=null) {
                                if( ! TextUtils.isEmpty(submitDataTable.getResponse())
                                        && submitDataTable.getResponse().equalsIgnoreCase(SUCCESS_RESPONSE_MESSAGE)) {
//                                    getLeadRawDataFromLocalDB(SCREEN_NO);
                                }else{
                                    Toast.makeText(TargetDetailsActivity.this,"Unable to sync data",Toast.LENGTH_SHORT).show();
                                }
                            }else{
                                Toast.makeText(TargetDetailsActivity.this,"Unable to sync data",Toast.LENGTH_SHORT).show();
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


    private void syncSingleClientData(MasterTable masterTable) {
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
                                                getMasterTableDetailsByCenter();
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
                                                getMasterTableDetailsByCenter();
                                            }
                                        });
                            }
                           /* if(submitDataTable !=null) {
                                if( ! TextUtils.isEmpty(submitDataTable.getResponse())
                                        && submitDataTable.getResponse().equalsIgnoreCase(SUCCESS_RESPONSE_MESSAGE)) {
//                                    getLeadRawDataFromLocalDB(SCREEN_NO);
                                }else{
                                    Toast.makeText(TargetDetailsActivity.this,"Unable to sync data",Toast.LENGTH_SHORT).show();
                                }
                            }else{
                                Toast.makeText(TargetDetailsActivity.this,"Unable to sync data",Toast.LENGTH_SHORT).show();
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
                            getMasterTableDetailsByCenter();
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
                        if (masterTableResult != null && masterTableResult.isAllDataCaptured()) {
                            getMasterTableDetailsByCenter();
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
                case R.id.menu_item_new_lead:
                    drawerLayout.closeDrawer(Gravity.LEFT);

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


    public void getMasterTableDetailsByCenter() {
        appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();

        try {
            viewModel.getMasterTableDetailsByCenter(CENTER_CREATION_TABLE);
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

                            if (targetDetailsAdapter != null) {
                                targetDetailsAdapter.setItem(masterTableList);
                            } else {
                                targetDetailsAdapter = new TargetDetailsAdapter(TargetDetailsActivity.this, masterTableList,
                                        TargetDetailsActivity.this, appHelper);
                                rvLeadDetails.setAdapter(targetDetailsAdapter);
                                targetDetailsAdapter.notifyDataSetChanged();
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



    @Override
    public void syncCallback(int position, MasterTable masterTable) {
        try {
            syncSingleClientData(masterTable);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void openLoanApplicationCallback(int position, MasterTable masterTable) {
        try {

//            Intent intentApplication = new Intent(TargetDetailsActivity.this, LoanApplicationSummaryActivity.class);
            Intent intentApplication = new Intent(TargetDetailsActivity.this, LoanApplicationActivity.class);
//            Intent intentApplication = new Intent(TargetDetailsActivity.this, BaseActivity.class);
            intentApplication.putExtra(AppConstant.PARAM_LOAN_TYPE, loanType);
            intentApplication.putExtra(PARAM_USER_NAME, userName);
            intentApplication.putExtra(PARAM_USER_ID, userId);
            intentApplication.putExtra(PARAM_BRANCH_ID, branchId);
            intentApplication.putExtra(PARAM_BRANCH_GST_CODE, branchGSTcode);
//            intentApplication.putExtra(PARAM_MODULE_TYPE, MODULE_TYPE_LOAN_APPLICATION);
            intentApplication.putExtra(PARAM_MODULE_TYPE, MODULE_TYPE_LOAN_PROPOSAL_WITH_NOMINEE);
            intentApplication.putExtra(PARAM_CLIENT_ID, masterTable.getClientId());
            if(CENTER_CREATION_TABLE!=null) {
                String centerTableJson = new Gson().toJson(CENTER_CREATION_TABLE, CenterCreationTable.class);
                if (!TextUtils.isEmpty(centerTableJson)) {
                    intentApplication.putExtra(PARAM_CENTER_TABLE_JSON, centerTableJson);
                }
            }
            startActivity(intentApplication);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void rejectCallback(int position, MasterTable masterTable) {
        try {
            // TODO: Reject functionality
            updateMasterTableStatusForRejectAndSendback(masterTable,FINAL_STATUS_REJECTED);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void openScreenCallback(int position, MasterTable masterTable) {

//        Intent intentApplication = new Intent(TargetDetailsActivity.this, MemberDetailsActivity.class);
        Intent applicant = new Intent(TargetDetailsActivity.this, BaseActivity.class);
        applicant.putExtra(PARAM_LOAN_TYPE, loanType);
        applicant.putExtra(PARAM_USER_NAME, userName);
        applicant.putExtra(PARAM_USER_ID, userId);
        applicant.putExtra(PARAM_CLIENT_ID, masterTable.getClientId());
        applicant.putExtra(PARAM_BRANCH_ID, branchId);
        applicant.putExtra(PARAM_BRANCH_GST_CODE, branchGSTcode);
        applicant.putExtra(PARAM_MODULE_TYPE, MODULE_TYPE_APPLICANT);
        if(  CENTER_CREATION_TABLE !=null){
            String centerTableJson=new Gson().toJson(CENTER_CREATION_TABLE, CenterCreationTable.class);
            if(!TextUtils.isEmpty(centerTableJson)){
                applicant.putExtra(PARAM_CENTER_TABLE_JSON, centerTableJson);
            }
        }
        startActivity(applicant);
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

}
