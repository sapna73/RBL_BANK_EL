package com.saartak.el.activities;

import static com.saartak.el.constants.AppConstant.DATE_FORMAT_DD_MM_YYYY2;
import static com.saartak.el.constants.AppConstant.LOAN_NAME_AHL;
import static com.saartak.el.constants.AppConstant.LOAN_NAME_PHL;
import static com.saartak.el.constants.AppConstant.LOAN_NAME_TWL;
import static com.saartak.el.constants.AppConstant.LOAN_NAME_EL;
import static com.saartak.el.constants.AppConstant.MODULE_TYPE_LEAD;
import static com.saartak.el.constants.AppConstant.PARAM_BRANCH_GST_CODE;
import static com.saartak.el.constants.AppConstant.PARAM_BRANCH_ID;
import static com.saartak.el.constants.AppConstant.PARAM_LOAN_TYPE;
import static com.saartak.el.constants.AppConstant.PARAM_PRODUCT_ID;
import static com.saartak.el.constants.AppConstant.PARAM_ROLE_NAME;
import static com.saartak.el.constants.AppConstant.PARAM_USER_ID;
import static com.saartak.el.constants.AppConstant.PARAM_USER_NAME;
import static com.saartak.el.constants.AppConstant.PARAM_WORKFLOW_ID;
import static com.saartak.el.constants.AppConstant.PROJECT_ID_EL;
import static com.saartak.el.constants.AppConstant.SCREEN_N0_LEAD_EL;
import static com.saartak.el.constants.AppConstant.SCREEN_NAME_LEAD;
import static com.saartak.el.constants.AppConstant.WORKFLOW_ID_AHL;
import static com.saartak.el.constants.AppConstant.WORKFLOW_ID_PHL;
import static com.saartak.el.constants.AppConstant.WORKFLOW_ID_TWL;
import static com.saartak.el.constants.AppConstant.WORKFLOW_ID_EL;
import static com.saartak.el.constants.AppConstant.getObjectByTAG;
import static com.saartak.el.constants.AppConstant.setKeyValueForObject;
import static com.saartak.el.dynamicui.constants.ParametersConstant.TAG_NAME_SAVE_BUTTON;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.text.method.DigitsKeyListener;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.saartak.el.BuildConfig;
import com.saartak.el.R;
import com.saartak.el.adapter.LeadToPricingAdapter;
import com.saartak.el.database.entity.DynamicUITable;
import com.saartak.el.database.entity.LeadTable;
import com.saartak.el.database.entity.MasterTable;
import com.saartak.el.database.entity.RawDataTable;
import com.saartak.el.helper.MyDividerItemDecoration;
import com.saartak.el.models.GetPricingInbox.GetPricingInboxResponseTable;
import com.saartak.el.view_models.DynamicUIViewModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class LeadToPricingActivity extends LOSBaseActivity implements LeadToPricingAdapter.SyncCallbackInterface {

    private static final String TAG = LeadToPricingActivity.class.getCanonicalName();
    RecyclerView rvLeadDetails;
    Button btnNewLead;
    SearchView searchByPhoneNo;
    LeadToPricingAdapter leadToPricingAdapter;
    private Toolbar toolbar;
    TextView tvClientName;
    TextView tvAppVersion, tvCurrentDate;
    String SCREEN_NO;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView navigationView;
    ImageView ivStaffImage;
    RelativeLayout rlNoLeads;
    TextView tvStaffId, tvStaffName, tvSOBDate;
    List<RawDataTable> allClientRawDataTableList;
    ArrayList<GetPricingInboxResponseTable> getPricingInboxResponseTableList;

    @Inject
    public ViewModelProvider.Factory viewModelFactory;
    public DynamicUIViewModel viewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lead_to_pricing);
        rvLeadDetails = (RecyclerView) findViewById(R.id.rv_lead_details);
        rlNoLeads = (RelativeLayout) findViewById(R.id.rl_no_leads);
        btnNewLead = (Button) findViewById(R.id.btn_new_lead);
        searchByPhoneNo = (SearchView) findViewById(R.id.sv_phone_no);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tvCurrentDate = (TextView) findViewById(R.id.tv_currentdate);
        tvAppVersion = (TextView) findViewById(R.id.tv_app_version);
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
        workFlowId = getIntent().getStringExtra(PARAM_WORKFLOW_ID);
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
            tvAppVersion.setText(getResources().getString(R.string.version) + " " + appVersion);
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

                if (leadToPricingAdapter != null && leadToPricingAdapter.getFilter() != null) {
                    leadToPricingAdapter.getFilter().filter(query);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                if (leadToPricingAdapter != null && leadToPricingAdapter.getFilter() != null) {
                    leadToPricingAdapter.getFilter().filter(query);
                }
                return false;
            }
        });

        configureDagger();
        configureViewModel();

        // TODO: Getting Lead data tvName server
//        getLeadDataFromServer();
        // TODO: Getting Lead data tvName server
        getLeadDataFromLocalDB(SCREEN_NO, userId, loanType);


    }

    private void configureDagger() {
        AndroidInjection.inject(this);
    }

    public void configureViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(DynamicUIViewModel.class);
        if (loanType.equalsIgnoreCase(LOAN_NAME_PHL)) {
            SCREEN_NO = SCREEN_N0_LEAD_EL;
        } else if (loanType.equalsIgnoreCase(LOAN_NAME_EL)) {
            SCREEN_NO = SCREEN_N0_LEAD_EL;
        } else if (loanType.equalsIgnoreCase(LOAN_NAME_TWL)) {
            SCREEN_NO = SCREEN_N0_LEAD_EL;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            searchByPhoneNo.setQuery("", false);
            searchByPhoneNo.setIconified(true);

            userName = getIntent().getStringExtra(PARAM_USER_NAME);
            userId = getIntent().getStringExtra(PARAM_USER_ID);
            branchId = getIntent().getStringExtra(PARAM_BRANCH_ID);
            branchGSTcode = getIntent().getStringExtra(PARAM_BRANCH_GST_CODE);
            loanType = getIntent().getStringExtra(PARAM_LOAN_TYPE);
            productId = getIntent().getStringExtra(PARAM_PRODUCT_ID);
            roleName = getIntent().getStringExtra(PARAM_ROLE_NAME);

        } catch (Exception ex) {
            ex.printStackTrace();
            INSERT_LOG("onResume", "Exception : " + ex.getMessage());
        }
    }


    public void getLeadDataFromLocalDB(String screen, String userId, String loanType) {
        try {

            appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();

            viewModel.getLeadData(screen, userId, loanType);
            if (viewModel.getLeadTableLiveData() != null) {
                Observer getLeadDataObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        List<LeadTable> leadDataTableList = (List<LeadTable>) o;
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

            INSERT_LOG("getLeadDataFromLocalDB", "Exception : " + ex.getMessage());

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
                    }
                };
                viewModel.getRawTableLiveData().observe(this, getRawDataForAllClientObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();

            INSERT_LOG("getRawDataForAllClient", "Exception : " + ex.getMessage());
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
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            INSERT_LOG("selectItemDrawer", "Exception : " + ex.getMessage());
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
            INSERT_LOG("setUpDrawerContent", "Exception : " + ex.getMessage());
        }
    }


    @Override
    public void onBackPressed() {
        finish();

    }

    public void getLeadDataFromServer() {
        try {
            // TODO: ************ To get Data tvName server  **************
            appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();
            viewModel.init(SCREEN_NO, SCREEN_NAME_LEAD, loanType, PROJECT_ID_EL, productId, CLIENT_ID, userId, MODULE_TYPE_LEAD);
            Observer observer = new Observer() {
                @Override
                public void onChanged(@Nullable Object o) {
                    appHelper.getDialogHelper().getLoadingDialog().closeDialog();
                    List<DynamicUITable> list = (List<DynamicUITable>) o;
                    viewModel.getDynamicUITableLiveData().removeObserver(this);

                    if (list != null && list.size() > 0) {
                        DynamicUITable dynamicUITable = getObjectByTAG(TAG_NAME_SAVE_BUTTON, list);
                        if (dynamicUITable != null) {
                            getLeadTableDataFromServer(SCREEN_NO, SCREEN_NAME_LEAD, loanType, userId, dynamicUITable, list);
                        } else {
                            // TODO: Calling getMyStage
                            getMyStages();
                        }
                    } else {
                        // TODO: Calling getMyStage
                        getMyStages();
                    }

                }
            };
            viewModel.getDynamicUITableLiveData().observe(this, observer);

            // TODO: ************ To get Data tvName server  **************
        } catch (Exception ex) {
            ex.printStackTrace();
            appHelper.getDialogHelper().getLoadingDialog().closeDialog();
            INSERT_LOG("getLeadDataFromServer", "Exception : " + ex.getMessage());

            // TODO: Calling getMyStage
            getMyStages();
        }
    }


    public void getLeadTableDataFromServer(String screenNo, String screenName, String loanType, String userId,
                                           DynamicUITable dynamicUITable, List<DynamicUITable> dynamicUITableList) {
        try {

            appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();

            viewModel.getLeadTableDataFromServer(screenNo, screenName, loanType, userId, dynamicUITable, dynamicUITableList);
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

            INSERT_LOG("getLeadTableDataFromServer", "Exception : " + ex.getMessage());
        }
    }

    public void getMyStages() {
        try {
            appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();

            String workflowId = productId;
            if (loanType.equalsIgnoreCase(LOAN_NAME_PHL)) {
                workflowId = WORKFLOW_ID_PHL; // TODO: PHL workflow id
            } else if (loanType.equalsIgnoreCase(LOAN_NAME_EL)) {
                workflowId = WORKFLOW_ID_EL; // TODO: PHL workflow id
            } else if (loanType.equalsIgnoreCase(LOAN_NAME_TWL)) {
                workflowId = WORKFLOW_ID_TWL; // TODO: PHL workflow id
            } else if (loanType.equalsIgnoreCase(LOAN_NAME_AHL)) {
                workflowId = WORKFLOW_ID_AHL; // TODO: AHL workflow id
            } else {
                workflowId = WORKFLOW_ID_PHL;
            }
            viewModel.getMyStages(userId, workFlowId, loanType, branchId, branchGSTcode);
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
        } catch (Exception ex) {
            ex.printStackTrace();
            appHelper.getDialogHelper().getLoadingDialog().closeDialog();

            INSERT_LOG("getMyStages", "Exception : " + ex.getMessage());
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

                            if (leadToPricingAdapter != null) {
                                leadToPricingAdapter.setItem(masterTableList);
                            } else {
                                leadToPricingAdapter = new LeadToPricingAdapter(LeadToPricingActivity.this, masterTableList,
                                        LeadToPricingActivity.this, appHelper);
                                rvLeadDetails.setAdapter(leadToPricingAdapter);
                                leadToPricingAdapter.notifyDataSetChanged();
                            }

                        } else {
                            rvLeadDetails.setVisibility(View.GONE);
                            rlNoLeads.setVisibility(View.VISIBLE);
                        }
                    }
                };
                viewModel.getmasterTableLiveDataList().observe(this, getMasterTableDetailsObserver);
            }
        } catch (Exception ex) {
            appHelper.getDialogHelper().getLoadingDialog().closeDialog();
            ex.printStackTrace();

            INSERT_LOG("getMasterTableByCGTTable", "Exception : " + ex.getMessage());
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

                            if (leadToPricingAdapter != null) {
                                leadToPricingAdapter.setItem(masterTableList);
                            } else {
                                leadToPricingAdapter = new LeadToPricingAdapter(LeadToPricingActivity.this, masterTableList,
                                        LeadToPricingActivity.this, appHelper);
                                rvLeadDetails.setAdapter(leadToPricingAdapter);
                                leadToPricingAdapter.notifyDataSetChanged();
                            }

                        } else {
                            rvLeadDetails.setVisibility(View.GONE);
                            rlNoLeads.setVisibility(View.VISIBLE);
                        }
                    }
                };
                viewModel.getmasterTableLiveDataList().observe(this, getMasterTableDetailsObserver);
            }
        } catch (Exception ex) {
            appHelper.getDialogHelper().getLoadingDialog().closeDialog();
            ex.printStackTrace();

            INSERT_LOG("getCurrentStageDetailsByClientId", "Exception : " + ex.getMessage());
        }
    }


    private void INSERT_LOG(String methodName, String message) {
        try {
            if (viewModel != null) {
                viewModel.insertLog(methodName, message, userId, "", "LeadToPricingActivity"
                        , CLIENT_ID, loanType, moduleType);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}