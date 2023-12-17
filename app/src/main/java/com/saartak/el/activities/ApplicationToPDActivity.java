package com.saartak.el.activities;

import android.app.SearchManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.text.method.DigitsKeyListener;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

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
import com.saartak.el.BuildConfig;
import com.saartak.el.R;
import com.saartak.el.adapter.ApplicationToPDAdapter;
import com.saartak.el.constants.AppConstant;
import com.saartak.el.database.entity.DynamicUITable;
import com.saartak.el.database.entity.MasterTable;
import com.saartak.el.database.entity.RawDataTable;
import com.saartak.el.database.entity.StageDetailsTable;
import com.saartak.el.helper.MyDividerItemDecoration;
import com.saartak.el.models.RawDataRequestDTO;
import com.saartak.el.models.RawDataResponseDTO;
import com.saartak.el.models.ScreenDetailsDTO;
import com.saartak.el.view_models.DynamicUIViewModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

import static com.saartak.el.constants.AppConstant.DATE_FORMAT_DD_MM_YYYY2;
import static com.saartak.el.constants.AppConstant.LOAN_NAME_AHL;
import static com.saartak.el.constants.AppConstant.LOAN_NAME_INDIVIDUAL;
import static com.saartak.el.constants.AppConstant.LOAN_NAME_MSME;
import static com.saartak.el.constants.AppConstant.LOAN_NAME_PHL;
import static com.saartak.el.constants.AppConstant.LOAN_NAME_EL;
import static com.saartak.el.constants.AppConstant.PARAM_BRANCH_GST_CODE;
import static com.saartak.el.constants.AppConstant.PARAM_BRANCH_ID;
import static com.saartak.el.constants.AppConstant.PARAM_LOAN_TYPE;
import static com.saartak.el.constants.AppConstant.PARAM_PRODUCT_ID;
import static com.saartak.el.constants.AppConstant.PARAM_USER_ID;
import static com.saartak.el.constants.AppConstant.PARAM_USER_NAME;
import static com.saartak.el.constants.AppConstant.PARAM_WORKFLOW_ID;
import static com.saartak.el.constants.AppConstant.PROJECT_ID_EL;
import static com.saartak.el.constants.AppConstant.SCREEN_N0_LEAD;
import static com.saartak.el.constants.AppConstant.SCREEN_N0_LEAD_IL;
import static com.saartak.el.constants.AppConstant.SCREEN_N0_LEAD_MSME;
import static com.saartak.el.constants.AppConstant.WORKFLOW_ID_AHL;
import static com.saartak.el.constants.AppConstant.WORKFLOW_ID_PHL;
import static com.saartak.el.constants.AppConstant.WORKFLOW_ID_IL;
import static com.saartak.el.constants.AppConstant.WORKFLOW_ID_MSME;
import static com.saartak.el.constants.AppConstant.WORKFLOW_ID_EL;
import static com.saartak.el.constants.AppConstant.getObjectByTAG;
import static com.saartak.el.dynamicui.constants.ParametersConstant.CURRENT_STAGE_PD;
import static com.saartak.el.dynamicui.constants.ParametersConstant.ERROR_MESSAGE_SYNC_FAILED;
import static com.saartak.el.dynamicui.constants.ParametersConstant.ERROR_MESSAGE_SYNC_SUCCESS;
import static com.saartak.el.dynamicui.constants.ParametersConstant.FIELD_NAME_SAVE_AND_ADD_ANOTHER_KYC;
import static com.saartak.el.dynamicui.constants.ParametersConstant.FINAL_STATUS_APPROVED;
import static com.saartak.el.dynamicui.constants.ParametersConstant.FINAL_STATUS_REJECTED;
import static com.saartak.el.dynamicui.constants.ParametersConstant.FINAL_STATUS_SEND_BACK;
import static com.saartak.el.dynamicui.constants.ParametersConstant.SP_NAME_TO_GET_RAW_DATA;
import static com.saartak.el.dynamicui.constants.ParametersConstant.TAG_NAME_KYC_TYPE;
import static com.saartak.el.dynamicui.constants.ParametersConstant.TAG_NAME_SAVE_BUTTON;

public class ApplicationToPDActivity extends LOSBaseActivity implements ApplicationToPDAdapter.SyncCallbackInterface {
    private static final String TAG = ApplicationToPDActivity.class.getCanonicalName();
    RecyclerView rvLeadDetails;
    SearchView searchByPhoneNo;
    AppCompatSpinner spinnerSortByDate, spinnerSortByLoanType, spinnerSortByInterested;
    ApplicationToPDAdapter applicationToPDAdapter;
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

    public String userName,userId,branchId;

    List<RawDataTable> allClientRawDataTableList;

    @Inject
    public ViewModelProvider.Factory viewModelFactory;
    public DynamicUIViewModel viewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_application_to_pd_card_view);
        rvLeadDetails = (RecyclerView) findViewById(R.id.rv_lead_details);
        rlNoLeads = (RelativeLayout) findViewById(R.id.rl_no_leads);
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
        workFlowId = getIntent().getStringExtra(PARAM_WORKFLOW_ID);

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

                    spinnerSortByLoanType.setSelection(0);
                    spinnerSortByInterested.setSelection(0);
                    searchByPhoneNo.setQuery("", false);
                    searchByPhoneNo.setIconified(true);
                    if (applicationToPDAdapter != null && applicationToPDAdapter.getFilter() != null) {
                        applicationToPDAdapter.getFilter().filter(parent.getSelectedItem().toString());
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
                    if (applicationToPDAdapter != null && applicationToPDAdapter.getFilter() != null) {
                        applicationToPDAdapter.getFilter().filter(parent.getSelectedItem().toString());
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

                    if (applicationToPDAdapter != null && applicationToPDAdapter.getFilter() != null) {
                        applicationToPDAdapter.getFilter().filter(parent.getSelectedItem().toString());
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

                if (applicationToPDAdapter != null && applicationToPDAdapter.getFilter() != null) {
                    applicationToPDAdapter.getFilter().filter(query);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                // filter recycler view when text is changed
                spinnerSortByDate.setSelection(0);
                spinnerSortByLoanType.setSelection(0);
                spinnerSortByInterested.setSelection(0);

                if (applicationToPDAdapter != null && applicationToPDAdapter.getFilter() != null) {
                    applicationToPDAdapter.getFilter().filter(query);
                }
                return false;
            }
        });

        configureDagger();
        configureViewModel();
        getMyStages();
    }

    private void INSERT_LOG(String methodName,String message){
        try{
            if(viewModel != null){
                viewModel.insertLog(methodName,message,userId,"",ApplicationToPDActivity.class.getCanonicalName(),
                        CLIENT_ID, loanType, moduleType);
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
        if ( ! TextUtils.isEmpty(loanType) && loanType.equalsIgnoreCase(LOAN_NAME_INDIVIDUAL)) {
            SCREEN_NO = SCREEN_N0_LEAD_IL;
        }
        else if (! TextUtils.isEmpty(loanType) && loanType.equalsIgnoreCase(LOAN_NAME_MSME)) {
            SCREEN_NO = SCREEN_N0_LEAD_MSME;
        } else {
            SCREEN_NO = SCREEN_N0_LEAD;
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
                            viewModel.getmasterTableLiveData().removeObserver(this);
                            MasterTable masterTableResponse=(MasterTable) o;
                            // TODO: Need to remove this
                            if(masterTableResponse != null && masterTableResponse.isSync()){
                                appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.SUCCESS,
                                        ERROR_MESSAGE_SYNC_SUCCESS, new ConfirmationDialog.ActionCallback() {
                                            @Override
                                            public void onAction() {
                                                getMasterTableDetails();
                                            }
                                        });
                            }else{
                                appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ERROR,
                                        ERROR_MESSAGE_SYNC_FAILED, new ConfirmationDialog.ActionCallback() {
                                            @Override
                                            public void onAction()
                                            {
                                                getMasterTableDetails();
                                            }
                                        });
                            }
                        }
                    };
                    viewModel.getmasterTableLiveData().observe(this, syncSingleClientDataObserver);
                }
            } else {

                INSERT_LOG("syncAllScreenByClient","Please check your internet connection and try again");

                appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT,
                        "Please check your internet connection and try again");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            appHelper.getDialogHelper().getLoadingDialog().closeDialog();
            INSERT_LOG("syncAllScreenByClient","Exception : "+ex.getMessage());
        }
    }

    private void updateMasterTableFromPD(MasterTable masterTable, String finalStatus,String actionName) {
        try {
            viewModel.updateMasterTableFromPD(masterTable,finalStatus,actionName);
            if (viewModel.getmasterTableLiveDataList() != null) {
                Observer updateMasterTableFromPDObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        viewModel.getmasterTableLiveDataList().removeObserver(this);
                        List<MasterTable> masterTableResult=(List<MasterTable>) o;
                        if(masterTableResult !=null && masterTableResult.size()>0) {
                            applicationToPDAdapter.setItem(masterTableResult);
                        }else{
                            INSERT_LOG("updateMasterTableFromPD","Unable to update data");
                            Toast.makeText(ApplicationToPDActivity.this,"Unable to update data",Toast.LENGTH_SHORT).show();
                        }
                    }
                };
                viewModel.getmasterTableLiveDataList().observe(this, updateMasterTableFromPDObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            INSERT_LOG("updateMasterTableFromPD","Exception : " + ex.getMessage());
        }
    }

    private void getRawDataFromServerForMultipleCustomer() {
        try {
            if (appHelper.isNetworkAvailable()) {

                appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();

                // TODO: **************** ALL HARDCODED VALUES *********************
                String RoleId = "8005";
                String ProjectId="1";
                int AllSrc=1;

                final RawDataRequestDTO rawDataRequestDTO = new RawDataRequestDTO();
                rawDataRequestDTO.setIMEINumber(appHelper.getIMEI());
                RawDataRequestDTO.SpNameWithParameterClass spNameWithParameter=new RawDataRequestDTO.SpNameWithParameterClass();
                spNameWithParameter.setSpName(SP_NAME_TO_GET_RAW_DATA);
                RawDataRequestDTO.SpParametersClass spParametersClass=new RawDataRequestDTO.SpParametersClass();
                spParametersClass.setRoleId(RoleId); // TODO: role id
                spParametersClass.setProjectId(ProjectId); // TODO: project id
                spParametersClass.setProductId(productId); // TODO: product id
                spParametersClass.setAllSrc(AllSrc); // TODO: All src
                spNameWithParameter.setSpParameters(spParametersClass);
                ArrayList<RawDataRequestDTO.SpNameWithParameterClass> SpNameWithParameterList = new ArrayList<RawDataRequestDTO.SpNameWithParameterClass>();
                SpNameWithParameterList.add(spNameWithParameter);
                rawDataRequestDTO.setSpNameWithParameter(SpNameWithParameterList);

                viewModel.getRawDataFromServerForMultipleCustomer(rawDataRequestDTO,userId,loanType);
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
                                getMasterTableDetails();
                            }
                        }
                    };
                    viewModel.getRawDataFromServerList().observe(this, syncDataToServerObserver);
                }
            } else {
                INSERT_LOG("getRawDataFromServer","Please check your internet connection and try again");
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
            viewModel.getMetaDataForMultipleScreen(rawDataFromServerList,loanType,userId,productId,CURRENT_STAGE_PD);
            if (viewModel.getmasterTableLiveDataList() != null) {
                Observer getMasterTableDetailsObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        appHelper.getDialogHelper().getLoadingDialog().closeDialog();
                        List<MasterTable> masterTableList = (List<MasterTable>) o;
                        viewModel.getmasterTableLiveDataList().removeObserver(this);
                        if (masterTableList != null && masterTableList.size() > 0) {
                            rlNoLeads.setVisibility(View.GONE);

                            applicationToPDAdapter = new ApplicationToPDAdapter(ApplicationToPDActivity.this, masterTableList,
                                    ApplicationToPDActivity.this, appHelper);
                            rvLeadDetails.setAdapter(applicationToPDAdapter);
                            applicationToPDAdapter.notifyDataSetChanged();

//                            imageDownloadFromServer(rawDataFromServerList,masterTableList);
                        } else {

                            rlNoLeads.setVisibility(View.VISIBLE);
                        }
                    }
                };
                viewModel.getmasterTableLiveDataList().observe(this, getMasterTableDetailsObserver);
            }
        }catch (Exception ex){
            ex.printStackTrace();
            appHelper.getDialogHelper().getLoadingDialog().closeDialog();
            INSERT_LOG("getMetaDataForMultipleScreen","Exception : "+ex.getMessage());
        }
    }

    private void imageDownloadFromServer(List<RawDataResponseDTO.Table> rawDataFromServerList,List<MasterTable> masterTableList ) {
        try {
//            appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();

            viewModel.imageDownloadFromServer(rawDataFromServerList);
            if (viewModel.getStringLiveData() != null) {
                Observer observer = new Observer() {
                    @Override
                    public void onChanged(Object o) {
//                        appHelper.getDialogHelper().getLoadingDialog().closeDialog();
                        String response = (String) o;
                        Log.d(TAG, "imageDownloadFromServer ==> " + response);
                        viewModel.getStringLiveData().removeObserver(this);

                        if (masterTableList != null && masterTableList.size() > 0) {
                            rlNoLeads.setVisibility(View.GONE);

                            applicationToPDAdapter = new ApplicationToPDAdapter(ApplicationToPDActivity.this, masterTableList,
                                    ApplicationToPDActivity.this, appHelper);
                            rvLeadDetails.setAdapter(applicationToPDAdapter);
                            applicationToPDAdapter.notifyDataSetChanged();
                        } else {
                            rlNoLeads.setVisibility(View.VISIBLE);
                        }
                    }
                };
                viewModel.getStringLiveData().observe(this, observer);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
//            appHelper.getDialogHelper().getLoadingDialog().closeDialog();
            INSERT_LOG("imageDownloadFromServer","Exception : "+ex.getMessage());
        }
    }


    private void getMetaDataFromService(RawDataResponseDTO.Table table){
        try{
            if (!TextUtils.isEmpty(table.getRwas())) {
                // TODO: GETTING SCREEN DETAILS BY SCREEN ID & MODULE TYPE
                ScreenDetailsDTO screenDetailsDTO= AppConstant.getScreenDetailsByScreenId(String.valueOf(table.getScreenId()),"");
                if(screenDetailsDTO != null && ! TextUtils.isEmpty(screenDetailsDTO.getScreenName())) {

                    // TODO: Meta data service
                    viewModel.init(screenDetailsDTO.getScreenId(), screenDetailsDTO.getScreenName(),
                            loanType, PROJECT_ID_EL, productId, table.getCustomerId(), userId, screenDetailsDTO.getModuleType());
                    Observer observer = new Observer() {
                        @Override
                        public void onChanged(@Nullable Object o) {
                            List<DynamicUITable> dynamicUITableList = (List<DynamicUITable>) o;
                            viewModel.getDynamicUITableLiveData().removeObserver(this);

                            if (dynamicUITableList != null && dynamicUITableList.size() > 0) {
                                try {
                                    JSONArray jsonArray = new JSONArray(table.getRwas());
                                    if (jsonArray.length() > 0) {
                                        for (int i = 0; i < jsonArray.length(); i++) {
                                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                                            if (jsonObject != null) {
                                                for(DynamicUITable dynamicUITable:dynamicUITableList){
                                                    if(jsonObject.has(dynamicUITable.getFieldTag())){
                                                        dynamicUITable.setValue(jsonObject.getString(dynamicUITable.getFieldTag()));
                                                    }
                                                }
                                                if(jsonArray.length()>1){
                                                    // TODO: Multiple Json Object
                                                    getRawDataByAdditionalColumn(dynamicUITableList,jsonObject.toString());
                                                }else{
                                                    // TODO: Single Json Object
                                                    getRawDataByScreenNameAndLoanType(dynamicUITableList,jsonObject.toString());
                                                }

                                            }
                                        }
                                    }
                                }catch (Exception ex){
                                    ex.printStackTrace();
                                }
                            }

                        }
                    };
                    viewModel.getDynamicUITableLiveData().observe(this, observer);
                }
            }
        }catch (Exception ex){
            ex.printStackTrace();
            INSERT_LOG("getMetaDataFromService","Exception : "+ex.getMessage());
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

    public void getMyStages() {

        try {
            appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();
            String workflowId = productId;
            if (!TextUtils.isEmpty(loanType) && loanType.equalsIgnoreCase(LOAN_NAME_INDIVIDUAL)) {
                workflowId = WORKFLOW_ID_IL;
            }else if (loanType.equalsIgnoreCase(LOAN_NAME_MSME)) {
                workflowId = WORKFLOW_ID_MSME;
            }
            else if (loanType.equalsIgnoreCase(LOAN_NAME_PHL)) {
                workflowId =WORKFLOW_ID_PHL ; // TODO: PHL workflow id
            }else if (loanType.equalsIgnoreCase(LOAN_NAME_EL)) {
                workflowId = WORKFLOW_ID_EL; // TODO: PHL workflow id
            }
            else if (loanType.equalsIgnoreCase(LOAN_NAME_AHL)) {
                workflowId = WORKFLOW_ID_AHL; // TODO: AHL workflow id
            } else {
                workflowId = workFlowId;
            }

            viewModel.getMyStages(userId, workflowId,loanType,branchId,branchGSTcode);
            if (viewModel.getMyStageLiveData() != null) {
                Observer getMyStagesObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        appHelper.getDialogHelper().getLoadingDialog().closeDialog();
                        List<StageDetailsTable> stageDetailsTableList = (List<StageDetailsTable>) o;
                        viewModel.getMyStageLiveData().removeObserver(this);
                        if (stageDetailsTableList != null && stageDetailsTableList.size() > 0) {
//                            getRawDataFromServer();
                            getRawDataFromServerForMultipleCustomer();
                        }
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

        try {
            appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();
            viewModel.getMasterTableDetailForPD(userId, loanType);
            if (viewModel.getmasterTableLiveDataList() != null) {
                Observer getMasterTableDetailsObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        appHelper.getDialogHelper().getLoadingDialog().closeDialog();
                        List<MasterTable> masterTableList = (List<MasterTable>) o;
                        viewModel.getmasterTableLiveDataList().removeObserver(this);
                        if (masterTableList != null && masterTableList.size() > 0) {
                            rlNoLeads.setVisibility(View.GONE);

                            applicationToPDAdapter = new ApplicationToPDAdapter(ApplicationToPDActivity.this, masterTableList,
                                    ApplicationToPDActivity.this, appHelper);
                            rvLeadDetails.setAdapter(applicationToPDAdapter);
                            applicationToPDAdapter.notifyDataSetChanged();
                        } else {
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


    private void updateMasterTableStatus(MasterTable masterTable, String finalStatus,String actionName) {
        try {
            viewModel.updateMasterTableStatus(masterTable,finalStatus);
            if (viewModel.getmasterTableLiveData() != null) {
                Observer updateMasterTableStatusObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        viewModel.getmasterTableLiveData().removeObserver(this);
                        MasterTable masterTableResult=(MasterTable) o;
                        if(masterTableResult !=null && masterTableResult.isAllDataCaptured()) {
//                            getMasterTableByCGTTable();
                            updateMasterTableFromPD(masterTable,finalStatus,actionName);
                        }else{
                            String errorMsg="Unable to update data";
                            if(masterTableResult !=null && ! TextUtils.isEmpty(masterTableResult.getResponse())){
                                errorMsg=masterTableResult.getResponse();
                            }
                            appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ERROR,errorMsg);
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

    private void updateMasterTableStatusForRejectAndSendback(MasterTable masterTable, String finalStatus,String actionName) {
        try {
            viewModel.updateMasterTableStatusForRejectAndSendback(masterTable,finalStatus);
            if (viewModel.getmasterTableLiveData() != null) {
                Observer updateMasterTableStatusObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        viewModel.getmasterTableLiveData().removeObserver(this);
                        MasterTable masterTableResult=(MasterTable) o;
                        if(masterTableResult !=null) {
//                            getMasterTableByCGTTable();
                            updateMasterTableFromPD(masterTable,finalStatus,actionName);
                        }else{
                            String errorMsg="Unable to update data";
                            if(masterTableResult !=null && ! TextUtils.isEmpty(masterTableResult.getResponse())){
                                errorMsg=masterTableResult.getResponse();
                            }
                            appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ERROR,errorMsg);
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
    public void syncCallback(int position, MasterTable masterTable) {
        try{
            syncSingleClientData(masterTable);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    public void approveCallback(int position, MasterTable masterTable,String actionName) {
        try{

            updateMasterTableStatus(masterTable,FINAL_STATUS_APPROVED,actionName);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    public void sendBackCallback(int position, MasterTable masterTable,String actionName) {
        try{
            updateMasterTableStatus(masterTable,FINAL_STATUS_SEND_BACK,actionName);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    public void rejectCallback(int position, MasterTable masterTable,String actionName) {
        try{
            updateMasterTableStatusForRejectAndSendback(masterTable,FINAL_STATUS_REJECTED,actionName);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }


    private void getRawDataByAdditionalColumn(List<DynamicUITable> dynamicUITableList, String rawData){
        try{
            DynamicUITable dynamicUITable = getObjectByTAG(FIELD_NAME_SAVE_AND_ADD_ANOTHER_KYC,dynamicUITableList);
            if(dynamicUITable != null) {
                viewModel.getRawDataByAdditionalColumn(dynamicUITable);
                if(viewModel.getRawDataTableLiveData() !=null){
                    Observer observer=new Observer() {
                        @Override
                        public void onChanged(Object o) {
                            RawDataTable rawDataTable=(RawDataTable) o;
                            viewModel.getRawDataTableLiveData().removeObserver(this::onChanged);
                            if(rawDataTable !=null){
                                // TODO: Update Raw Data Bag
                                updateRawDataBag(rawDataTable,dynamicUITableList,dynamicUITable);
                            }else{
                                // TODO: Insert Raw Data Bag

                                DynamicUITable dynamicUITableObjForSelectedItem = getObjectByTAG(TAG_NAME_KYC_TYPE, dynamicUITableList);

                                if(dynamicUITableObjForSelectedItem !=null) {
                                    final RawDataTable rawDataTableToInsert = new RawDataTable(rawData, dynamicUITable.getScreenID(),
                                            dynamicUITable.getScreenName(),
                                            dynamicUITableObjForSelectedItem.getValue(),
                                            dynamicUITable.getClientID(), dynamicUITable.getLoanType(), dynamicUITable.getUser_id(),
                                            dynamicUITable.getModuleType(), dynamicUITable.getCoRelationID());

                                    insertRawDataBag(rawDataTableToInsert, dynamicUITableList, dynamicUITable);
                                }

                            }
                        }
                    };
                    viewModel.getRawDataTableLiveData().observe(this,observer);
                }

            }

        }catch (Exception ex){
            ex.printStackTrace();
            INSERT_LOG("getRawDataByAdditionalColumn","Exception : "+ex.getMessage());
        }
    }

    private void getRawDataByScreenNameAndLoanType(List<DynamicUITable> dynamicUITableList,String rawData){
        try{
            DynamicUITable dynamicUITable=getObjectByTAG(TAG_NAME_SAVE_BUTTON,dynamicUITableList);
            if(dynamicUITable !=null) {
                viewModel.getRawDataByScreenNameAndModuleType(dynamicUITable);
                if(viewModel.getRawDataTableLiveData() !=null){
                    Observer observer=new Observer() {
                        @Override
                        public void onChanged(Object o) {
                            RawDataTable rawDataTable=(RawDataTable) o;
                            viewModel.getRawDataTableLiveData().removeObserver(this::onChanged);
                            if(rawDataTable !=null){
                                // TODO: Update Raw Data Bag

                                updateRawDataBag(rawDataTable,dynamicUITableList,dynamicUITable);
                            }else{
                                // TODO: Insert Raw Data Bag

                                final RawDataTable rawDataTableToInsert = new RawDataTable(rawData, dynamicUITable.getScreenID(),
                                        dynamicUITable.getScreenName(),
                                        "", dynamicUITable.getClientID(), dynamicUITable.getLoanType(), dynamicUITable.getUser_id(), dynamicUITable.getModuleType(),dynamicUITable.getCoRelationID());
                                insertRawDataBag(rawDataTableToInsert,dynamicUITableList,dynamicUITable);

                            }
                        }
                    };
                    viewModel.getRawDataTableLiveData().observe(this,observer);
                }

            }

        }catch (Exception ex){
            ex.printStackTrace();
            INSERT_LOG("getRawDataByScreenNameAndLoanType","Exception : "+ex.getMessage());
        }
    }

    private void insertRawDataBag(RawDataTable rawDataTable,List<DynamicUITable> dynamicUITableList,DynamicUITable dynamicUITable){
        try{
            viewModel.insertRawData(rawDataTable, dynamicUITableList, dynamicUITable,false);
            Observer rawDataObserver = new Observer() {
                @Override
                public void onChanged(@Nullable Object o) {
                    viewModel.getRawTableLiveData().removeObserver(this);
                }
            };
            viewModel.getRawDataTableLiveData().observe(this,rawDataObserver);
        }catch (Exception ex){
            ex.printStackTrace();
            INSERT_LOG("insertRawDataBag","Exception : "+ex.getMessage());
        }
    }
    private void updateRawDataBag(RawDataTable rawDataTable, List<DynamicUITable> dynamicUITableList, DynamicUITable dynamicUITable){
        try{
            viewModel.updateRawDataBag(rawDataTable, dynamicUITableList, dynamicUITable,false);

            if(viewModel.getUpdatedRawdataRow() != null) {
                Observer rawDataUpdateObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        RawDataTable updatedRawDataTable = (RawDataTable) o;
                        viewModel.getUpdatedRawdataRow().removeObserver(this);
                    }
                };
                viewModel.getUpdatedRawdataRow().observe(this, rawDataUpdateObserver);
            }

        }catch (Exception ex){
            ex.printStackTrace();
            INSERT_LOG("updateRawDataBag","Exception : "+ex.getMessage());
        }
    }
}
