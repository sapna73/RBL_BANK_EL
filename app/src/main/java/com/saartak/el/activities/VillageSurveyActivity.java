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
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.saartak.el.R;
import com.saartak.el.adapter.VillageSurveyAdapter;
import com.saartak.el.constants.AppConstant;
import com.saartak.el.database.entity.RawDataTable;
import com.saartak.el.database.entity.SubmitDataTable;
import com.saartak.el.database.entity.VillageSurveyTable;
import com.saartak.el.helper.MyDividerItemDecoration;
import com.saartak.el.models.SubmitDataDTO;
import com.saartak.el.view_models.DynamicUIViewModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

import static com.saartak.el.constants.AppConstant.DATE_FORMAT_DD_MM_YYYY2;
import static com.saartak.el.constants.AppConstant.MODULE_TYPE_VILLAGE_SURVEY;
import static com.saartak.el.constants.AppConstant.PARAM_BRANCH_GST_CODE;
import static com.saartak.el.constants.AppConstant.PARAM_BRANCH_ID;
import static com.saartak.el.constants.AppConstant.PARAM_CLIENT_ID;
import static com.saartak.el.constants.AppConstant.PARAM_LOAN_TYPE;
import static com.saartak.el.constants.AppConstant.PARAM_MODULE_TYPE;
import static com.saartak.el.constants.AppConstant.PARAM_PRODUCT_ID;
import static com.saartak.el.constants.AppConstant.PARAM_USER_ID;
import static com.saartak.el.constants.AppConstant.PARAM_USER_NAME;
import static com.saartak.el.constants.AppConstant.SCREEN_NO_VILLAGE_SURVEY_JLG;
import static com.saartak.el.constants.AppConstant.STAGE_ID_LEAD;

public class VillageSurveyActivity extends LOSBaseActivity implements VillageSurveyAdapter.SyncCallbackInterface {


    private static final String TAG = VillageSurveyActivity.class.getCanonicalName();
    RecyclerView rvLeadDetails;
    SearchView searchByPhoneNo;
    AppCompatSpinner spinnerSortByDate,  spinnerSortByAlphabeticalOrder;
    VillageSurveyAdapter villageSurveyAdapter;

    private Toolbar toolbar;
    TextView tvClientName;
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

        setContentView(R.layout.activity_village_survey);
        rvLeadDetails = (RecyclerView) findViewById(R.id.rv_lead_details);
        rlNoLeads = (RelativeLayout) findViewById(R.id.rl_no_leads);
        searchByPhoneNo = (SearchView) findViewById(R.id.sv_phone_no);
        btnNewTarget =  findViewById(R.id.fb_add_target_details);
        spinnerSortByDate = (AppCompatSpinner) findViewById(R.id.sp_sort_by_date);
        spinnerSortByAlphabeticalOrder = (AppCompatSpinner) findViewById(R.id.sp_sort_by_alphabetical_order);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tvClientName = (TextView) findViewById(R.id.tv_client_name_custom);
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

        spinnerSortByDate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0) {

                    spinnerSortByAlphabeticalOrder.setSelection(0);
                    searchByPhoneNo.setQuery("", false);
                    searchByPhoneNo.setIconified(true);
                    if (villageSurveyAdapter != null && villageSurveyAdapter.getFilter() != null) {
                        villageSurveyAdapter.getFilter().filter(parent.getSelectedItem().toString());
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
                    if (villageSurveyAdapter != null && villageSurveyAdapter.getFilter() != null) {
                        villageSurveyAdapter.getFilter().filter(parent.getSelectedItem().toString());
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


                if (villageSurveyAdapter != null && villageSurveyAdapter.getFilter() != null) {
                    villageSurveyAdapter.getFilter().filter(query);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                // filter recycler view when text is changed
                spinnerSortByDate.setSelection(0);
                spinnerSortByAlphabeticalOrder.setSelection(0);


                if (villageSurveyAdapter != null && villageSurveyAdapter.getFilter() != null) {
                    villageSurveyAdapter.getFilter().filter(query);
                }
                return false;
            }
        });

        btnNewTarget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String timeStamp = new SimpleDateFormat("yyMMddHHmmss",
                        Locale.getDefault()).format(new Date());
                if( ! TextUtils.isEmpty(EMP_LAST_5_DIGIT)) {
                    if (!TextUtils.isEmpty(userId)) {
                        EMP_LAST_5_DIGIT = userId.substring(3);
                        CLIENT_ID = EMP_LAST_5_DIGIT + timeStamp;
                        // TODO: CLIENT ID FORMATION ( FIRST 7 DIGIT EMP ID - 100004141 , YEAR 19 ,
                        //  MONTH 11 , DATE 12, HOUR 08 , MINUTE 25 , SECOND 48 ==> TOTAL 19 DIGITS )
                        if (!TextUtils.isEmpty(CLIENT_ID) && CLIENT_ID.length() > 12) {
                            Intent intentApplication = new Intent(VillageSurveyActivity.this, BaseActivity.class);
                            intentApplication.putExtra(AppConstant.PARAM_LOAN_TYPE, loanType);
                            intentApplication.putExtra(PARAM_USER_NAME, userName);
                            intentApplication.putExtra(PARAM_USER_ID, userId);
                            intentApplication.putExtra(PARAM_BRANCH_ID, branchId);
                            intentApplication.putExtra(PARAM_BRANCH_GST_CODE, branchGSTcode);
                            intentApplication.putExtra(PARAM_CLIENT_ID, CLIENT_ID);
                            intentApplication.putExtra(PARAM_MODULE_TYPE, MODULE_TYPE_VILLAGE_SURVEY);
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

        configureDagger();
        configureViewModel();

    }

    private void configureDagger() {
        AndroidInjection.inject(this);
    }

    public void configureViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(DynamicUIViewModel.class);
        SCREEN_NO = SCREEN_NO_VILLAGE_SURVEY_JLG;
    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            spinnerSortByDate.setSelection(0);
            spinnerSortByAlphabeticalOrder.setSelection(0);
            searchByPhoneNo.setQuery("", false);
            searchByPhoneNo.setIconified(true);

            getVillageSurveyTable();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }



    private void syncSingleVillageSurveyData(VillageSurveyTable villageSurveyTable) {
        try {
            if (appHelper.isNetworkAvailable()) {
                appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();

                viewModel.syncSingleVillageSurveyData(villageSurveyTable);
                if (viewModel.getSubmitDataTableLiveData() != null) {
                    Observer syncDataToServerObserver = new Observer() {
                        @Override
                        public void onChanged(@Nullable Object o) {
                            appHelper.getDialogHelper().getLoadingDialog().closeDialog();
                            SubmitDataTable submitDataTable = (SubmitDataTable) o;
                            viewModel.getSubmitDataTableLiveData().removeObserver(this);

                            // TODO: Need to remove this condition
                            getVillageSurveyTable();

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

    public void getVillageSurveyTable() {
        appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();

        try {
            viewModel.getVillageSurveyTable(userId, loanType);
            if (viewModel.getVillageSurveyTableLiveDataList() != null) {
                Observer getVillageTableObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        appHelper.getDialogHelper().getLoadingDialog().closeDialog();
                        List<VillageSurveyTable> villageSurveyTableList = (List<VillageSurveyTable>) o;
                        viewModel.getVillageSurveyTableLiveDataList().removeObserver(this);

                        if (villageSurveyTableList != null && villageSurveyTableList.size() > 0) {
                            rlNoLeads.setVisibility(View.GONE);
                            rvLeadDetails.setVisibility(View.VISIBLE);

                            if (villageSurveyAdapter != null) {
                                villageSurveyAdapter.setItem(villageSurveyTableList);
                            } else {
                                villageSurveyAdapter = new VillageSurveyAdapter(VillageSurveyActivity.this, villageSurveyTableList,
                                        VillageSurveyActivity.this, appHelper);
                                rvLeadDetails.setAdapter(villageSurveyAdapter);
                                villageSurveyAdapter.notifyDataSetChanged();
                            }

                        } else {
                            rvLeadDetails.setVisibility(View.GONE);
                            rlNoLeads.setVisibility(View.VISIBLE);
                        }
                    }
                };
                viewModel.getVillageSurveyTableLiveDataList().observe(this, getVillageTableObserver);
            }
        }catch (Exception ex){
            appHelper.getDialogHelper().getLoadingDialog().closeDialog();
            ex.printStackTrace();
        }
    }


    @Override
    public void syncCallback(int position, VillageSurveyTable villageSurveyTable) {
        syncSingleVillageSurveyData(villageSurveyTable);
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

                            getVillageSurveyTable();
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
    public void openScreenCallback(int position, VillageSurveyTable villageSurveyTable) {
        try{
            Intent intentApplication = new Intent(VillageSurveyActivity.this, BaseActivity.class);
            intentApplication.putExtra(AppConstant.PARAM_LOAN_TYPE, loanType);
            intentApplication.putExtra(PARAM_USER_NAME, userName);
            intentApplication.putExtra(PARAM_USER_ID, userId);
            intentApplication.putExtra(PARAM_BRANCH_ID, branchId);
            intentApplication.putExtra(PARAM_BRANCH_GST_CODE, branchGSTcode);
            intentApplication.putExtra(PARAM_CLIENT_ID, villageSurveyTable.getVillageId());
            intentApplication.putExtra(PARAM_MODULE_TYPE, MODULE_TYPE_VILLAGE_SURVEY);
            startActivity(intentApplication);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private void INSERT_LOG(String methodName, String message){
        try{
            if(viewModel !=null){
                viewModel.insertLog(methodName,message,userId,"","VillageSurveyActivity"
                        ,CLIENT_ID,loanType,moduleType);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
