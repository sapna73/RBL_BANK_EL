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
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.swadhaar.los.BuildConfig;
import com.swadhaar.los.R;
import com.swadhaar.los.adapter.CollectionSummaryAdapter;
import com.swadhaar.los.constants.AppConstant;
import com.swadhaar.los.database.entity.CollectionTable;
import com.swadhaar.los.database.entity.MasterTable;
import com.swadhaar.los.database.entity.RawDataTable;
import com.swadhaar.los.database.entity.SubmitDataTable;
import com.swadhaar.los.dynamicui.constants.ParametersConstant;
import com.swadhaar.los.helper.MyDividerItemDecoration;
import com.swadhaar.los.models.SubmitDataDTO;
import com.swadhaar.los.view_models.DynamicUIViewModel;

import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

import static com.swadhaar.los.constants.AppConstant.DATE_FORMAT_DD_MM_YYYY;
import static com.swadhaar.los.constants.AppConstant.DATE_FORMAT_DD_MM_YYYY2;
import static com.swadhaar.los.constants.AppConstant.LOAN_NAME_AHL;
import static com.swadhaar.los.constants.AppConstant.LOAN_NAME_INDIVIDUAL;
import static com.swadhaar.los.constants.AppConstant.LOAN_NAME_MSME;
import static com.swadhaar.los.constants.AppConstant.LOAN_NAME_PHL;
import static com.swadhaar.los.constants.AppConstant.MODULE_TYPE_COLLECTION;
import static com.swadhaar.los.constants.AppConstant.PARAM_BRANCH_GST_CODE;
import static com.swadhaar.los.constants.AppConstant.PARAM_BRANCH_ID;
import static com.swadhaar.los.constants.AppConstant.PARAM_CLIENT_ID;
import static com.swadhaar.los.constants.AppConstant.PARAM_LOAN_TYPE;
import static com.swadhaar.los.constants.AppConstant.PARAM_MODULE_TYPE;
import static com.swadhaar.los.constants.AppConstant.PARAM_PRODUCT_ID;
import static com.swadhaar.los.constants.AppConstant.PARAM_SCREEN_NO;
import static com.swadhaar.los.constants.AppConstant.PARAM_USER_ID;
import static com.swadhaar.los.constants.AppConstant.PARAM_USER_NAME;
import static com.swadhaar.los.constants.AppConstant.REQUEST_CODE_NEW_LEAD;
import static com.swadhaar.los.constants.AppConstant.REQUEST_CODE_UPDATE_LEAD;
import static com.swadhaar.los.constants.AppConstant.SCREEN_NO_COLLECTION_MSME;
import static com.swadhaar.los.constants.AppConstant.STAGE_ID_LEAD;
import static com.swadhaar.los.constants.AppConstant.WORKFLOW_ID_AHL;
import static com.swadhaar.los.constants.AppConstant.WORKFLOW_ID_IL;
import static com.swadhaar.los.constants.AppConstant.WORKFLOW_ID_MSME;
import static com.swadhaar.los.constants.AppConstant.WORKFLOW_ID_PHL;


public class CollectionSummaryActivity extends  LOSBaseActivity implements CollectionSummaryAdapter.SyncCallbackInterface {
    private static final String TAG = CollectionSummaryActivity.class.getCanonicalName();
    RecyclerView rvLeadDetails;
    Button btnNewLead, btnSyncAll;
    SearchView searchByPhoneNo;
    CollectionSummaryAdapter collectionSummaryAdapter;

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
    AppCompatSpinner spinnerSortByDate, spinnerSortByAlphabeticalOrder;

    List<CollectionTable> collectionTableList;

    @Inject
    public ViewModelProvider.Factory viewModelFactory;
    public DynamicUIViewModel viewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_collection_summary);
        rvLeadDetails = (RecyclerView) findViewById(R.id.rv_lead_details);
        rlNoLeads = (RelativeLayout) findViewById(R.id.rl_no_leads);
        spinnerSortByDate = (AppCompatSpinner) findViewById(R.id.sp_sort_by_date);
        spinnerSortByAlphabeticalOrder = (AppCompatSpinner) findViewById(R.id.sp_sort_by_alphabetical_order);

        btnNewLead = (Button) findViewById(R.id.btn_new_lead);
        btnSyncAll = (Button) findViewById(R.id.btn_sync_all);
        searchByPhoneNo = (SearchView) findViewById(R.id.sv_phone_no);
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
                            if (collectionTableList != null && collectionTableList.size() > 0) {
                                boolean allSynced = false;
                                for (CollectionTable collectionTable : collectionTableList) {
                                    // TODO: Need to remove for lead
                                    if (!collectionTable.isSync()) {
                                        allSynced = false;
                                        postSubmittedData(collectionTable);
                                    } else {
                                        allSynced = true;
                                    }
                                }
                                if (allSynced) {
                                    appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, "All collections are synced");
                                }
                            } else {
                                appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, "No Collection Details To Snyc");
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

                    spinnerSortByAlphabeticalOrder.setSelection(0);
                    searchByPhoneNo.setQuery("", false);
                    searchByPhoneNo.setIconified(true);
                    if (collectionSummaryAdapter != null && collectionSummaryAdapter.getFilter() != null) {
                        collectionSummaryAdapter.getFilter().filter(parent.getSelectedItem().toString());
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

                    if (collectionSummaryAdapter != null && collectionSummaryAdapter.getFilter() != null) {
                        collectionSummaryAdapter.getFilter().filter(parent.getSelectedItem().toString());
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
                spinnerSortByDate.setSelection(0);
                spinnerSortByAlphabeticalOrder.setSelection(0);

                if (collectionSummaryAdapter != null && collectionSummaryAdapter.getFilter() != null) {
                    collectionSummaryAdapter.getFilter().filter(query);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                spinnerSortByDate.setSelection(0);
                spinnerSortByAlphabeticalOrder.setSelection(0);


                if (collectionSummaryAdapter != null && collectionSummaryAdapter.getFilter() != null) {
                    collectionSummaryAdapter.getFilter().filter(query);
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

        if(loanType.equalsIgnoreCase(LOAN_NAME_MSME)) {
            SCREEN_NO = SCREEN_NO_COLLECTION_MSME;
        }
        getCollectionSummaryData();

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
            spinnerSortByAlphabeticalOrder.setSelection(0);
            searchByPhoneNo.setQuery("", false);
            searchByPhoneNo.setIconified(true);
            getCollectionSummaryDataFromLocalDB(SCREEN_NO, userId);
            // getCollectionSummaryData();

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



    @Override
    public void syncCallback(int position, CollectionTable collectionTable) {
        try {
            postSubmittedData(collectionTable);
        } catch (Exception ex) {
            ex.printStackTrace();
            INSERT_LOG("syncCallback","Exception : "+ex.getMessage());
        }
    }

    private void getCollectionSummaryData() {
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
            viewModel.getCollectionSummarydata(userId, loanType);

            if (viewModel.getCollectionTableLiveData() != null) {
                Observer getCollectionSummaydataObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        appHelper.getDialogHelper().getLoadingDialog().closeDialog();
                        collectionTableList = (List<CollectionTable>) o;
                        viewModel.getCollectionTableLiveData().removeObserver(this);

                        if (collectionTableList != null && collectionTableList.size() > 0) {
                            rlNoLeads.setVisibility(View.GONE);
                            rvLeadDetails.setVisibility(View.VISIBLE);

                            collectionSummaryAdapter = new CollectionSummaryAdapter(CollectionSummaryActivity.this, collectionTableList,
                                    CollectionSummaryActivity.this, appHelper);
                            rvLeadDetails.setAdapter(collectionSummaryAdapter);
                            collectionSummaryAdapter.notifyDataSetChanged();
                        } else {
                            rlNoLeads.setVisibility(View.VISIBLE);
                            rvLeadDetails.setVisibility(View.GONE);

                        }

                    }
                };
                viewModel.getCollectionTableLiveData().observe(this, getCollectionSummaydataObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            appHelper.getDialogHelper().getLoadingDialog().closeDialog();
            INSERT_LOG("getCollectionSummaryData","Exception : "+ex.getMessage());

        }


    }


    private void postSubmittedData(CollectionTable collectionTable) {
        try {

            if (appHelper.isNetworkAvailable()) {

                appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();

                final SubmitDataTable submitDataTable = new SubmitDataTable();
                submitDataTable.setScreenId(SCREEN_NO);
                submitDataTable.setUniqueID(collectionTable.getClientId());
                submitDataTable.setApplicationId(collectionTable.getClientId());
                submitDataTable.setIMEINumber(appHelper.getIMEI());
                if( ! TextUtils.isEmpty(branchGSTcode)) {
                    submitDataTable.setBCBRID(branchGSTcode);
                }else {
                    submitDataTable.setBCBRID(branchId);
                }
                submitDataTable.setBCID(branchId);
                submitDataTable.setCreatedBy(userId);
                submitDataTable.setStageId(STAGE_ID_LEAD); // TODO: LEAD STAGE ID
                // TODO: 22-12-2020 submitdataDTO
                final SubmitDataDTO submitDataDTO = new SubmitDataDTO();
                submitDataDTO.setScreenId(SCREEN_NO);
                submitDataDTO.setUniqueID(collectionTable.getClientId());
                submitDataDTO.setApplicationId(collectionTable.getClientId());
                submitDataDTO.setIMEINumber(appHelper.getIMEI());
                if( ! TextUtils.isEmpty(branchGSTcode)) {
                    submitDataDTO.setBCBRID(branchGSTcode);
                }else {
                    submitDataDTO.setBCBRID(branchId);
                }
                submitDataDTO.setBCID(branchId);
                submitDataDTO.setCreatedBy(userId);
                submitDataDTO.setStageId(STAGE_ID_LEAD); // TODO: LEAD STAGE ID
                viewModel.postSubmittedDataForCollection(submitDataTable,submitDataDTO, SCREEN_NO,  collectionTable);
                if (viewModel.getDynamicUITableLiveData() != null) {
                    Observer postSubmittedDataObserver = new Observer() {
                        @Override
                        public void onChanged(@Nullable Object o) {
                            viewModel.getDynamicUITableLiveData().removeObserver(this);
                            appHelper.getDialogHelper().getLoadingDialog().closeDialog();

                            getCollectionSummaryDataFromLocalDB(SCREEN_NO,userId);
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

    public void getCollectionSummaryDataFromLocalDB(String screenNO, String userId) {
        try {

            appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();

            viewModel.getCollectiondata(screenNO,userId);
            if (viewModel.getCollectionTableLiveData() != null) {
                Observer getLeadDataObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        collectionTableList  = (List<CollectionTable>) o;
                        viewModel.getCollectionTableLiveData().removeObserver(this);

                        appHelper.getDialogHelper().getLoadingDialog().closeDialog();

                        if(collectionTableList !=null && collectionTableList.size()>0) {
                            rlNoLeads.setVisibility(View.GONE);
                            rvLeadDetails.setVisibility(View.VISIBLE);

                            collectionSummaryAdapter = new CollectionSummaryAdapter(CollectionSummaryActivity.this, collectionTableList,
                                    CollectionSummaryActivity.this, appHelper);
                            rvLeadDetails.setAdapter(collectionSummaryAdapter);
                            collectionSummaryAdapter.notifyDataSetChanged();
                        }else{
                            rlNoLeads.setVisibility(View.VISIBLE);
                            rvLeadDetails.setVisibility(View.GONE);

                        }

                    }
                };
                viewModel.getCollectionTableLiveData().observe(this, getLeadDataObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            appHelper.getDialogHelper().getLoadingDialog().closeDialog();

            INSERT_LOG("getLeadDataFromLocalDB","Exception : "+ex.getMessage());
        }
    }

    private void INSERT_LOG(String methodName,String message){
        try{
            if(viewModel !=null){
                viewModel.insertLog(methodName,message,userId,"","CollectionSummaryActivity"
                        ,CLIENT_ID,loanType,moduleType);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }


}
