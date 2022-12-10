package com.swadhaar.los.activities;

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

import android.app.DatePickerDialog;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bfil.uilibrary.dialogs.ConfirmationDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.swadhaar.los.BuildConfig;
import com.swadhaar.los.R;
import com.swadhaar.los.adapter.FetchOtherDayCollectionAdapter;
import com.swadhaar.los.constants.AppConstant;
import com.swadhaar.los.database.entity.CenterMeetingAttendanceTable;
import com.swadhaar.los.database.entity.CenterMeetingTable;
import com.swadhaar.los.database.entity.FetchOtherDayCollectionTable;
import com.swadhaar.los.database.entity.RawDataTable;
import com.swadhaar.los.helper.MyDividerItemDecoration;
import com.swadhaar.los.models.CenterMeetingAttendanceDTO;
import com.swadhaar.los.models.FetchOtherDayCMDTO;
import com.swadhaar.los.view_models.DynamicUIViewModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

import static com.swadhaar.los.constants.AppConstant.DATE_FORMAT_DD_MM_YYYY2;
import static com.swadhaar.los.constants.AppConstant.DATE_FORMAT_YYYYMMDD;
import static com.swadhaar.los.constants.AppConstant.MODULE_TYPE_TARGET_DETAILS;
import static com.swadhaar.los.constants.AppConstant.PARAM_BRANCH_GST_CODE;
import static com.swadhaar.los.constants.AppConstant.PARAM_BRANCH_ID;
import static com.swadhaar.los.constants.AppConstant.PARAM_CENTER_NAME;
import static com.swadhaar.los.constants.AppConstant.PARAM_LOAN_TYPE;
import static com.swadhaar.los.constants.AppConstant.PARAM_MODULE_TYPE;
import static com.swadhaar.los.constants.AppConstant.PARAM_PRODUCT_ID;
import static com.swadhaar.los.constants.AppConstant.PARAM_USER_ID;
import static com.swadhaar.los.constants.AppConstant.PARAM_USER_NAME;
import static com.swadhaar.los.dynamicui.constants.ParametersConstant.ALERT_FOR_INTERNET_CONNECTION_REQUIRED_TO_FETCH_DATA;
import static com.swadhaar.los.dynamicui.constants.ParametersConstant.ERROR_MESSAGE_ATTENDANCE_SUCCESS;
import static com.swadhaar.los.dynamicui.constants.ParametersConstant.ERROR_MESSAGE_DO_YOU_WANT_TO_PROCEED;
import static com.swadhaar.los.dynamicui.constants.ParametersConstant.ERROR_MESSAGE_NO_CENTERS_AVAILABLE;
import static com.swadhaar.los.dynamicui.constants.ParametersConstant.ERROR_MESSAGE_SELECT_ABSENT_REASON;
import static com.swadhaar.los.dynamicui.constants.ParametersConstant.ERROR_MESSAGE_SELECT_ONE_CENTER_AT_LEAST;
import static com.swadhaar.los.dynamicui.constants.ParametersConstant.ERROR_MESSAGE_SELECT_REMINDER;
import static com.swadhaar.los.dynamicui.constants.ParametersConstant.ERROR_MESSAGE_SYNC_FAILED;
import static com.swadhaar.los.dynamicui.constants.ParametersConstant.FAILURE_RESPONSE_FOR_CENTER_MEETING_FETCH_FROM_SERVER;
import static com.swadhaar.los.dynamicui.constants.ParametersConstant.RADIO_BUTTON_ITEM_COLD_CALLING;
import static com.swadhaar.los.dynamicui.constants.ParametersConstant.RADIO_BUTTON_ITEM_COLLECTION;
import static com.swadhaar.los.dynamicui.constants.ParametersConstant.RADIO_BUTTON_ITEM_LEAD;
import static com.swadhaar.los.dynamicui.constants.ParametersConstant.SUCCESS_RESPONSE_FOR_CENTER_MEETING_FETCH_FROM_SERVER;
import static com.swadhaar.los.dynamicui.constants.ParametersConstant.SUCCESS_RESPONSE_MESSAGE;

public class FetchOtherDayCollectionActivity extends LOSBaseActivity implements FetchOtherDayCollectionAdapter.SyncCallbackInterface {


    private static final String TAG = FetchOtherDayCollectionActivity.class.getCanonicalName();
    RecyclerView rvLeadDetails;
    SearchView searchByPhoneNo;
    AppCompatSpinner spinnerSortByDate, spinnerSortByAlphabet;
    FetchOtherDayCollectionAdapter fetchOtherDayCollectionAdapter;

    private Toolbar toolbar;
    TextView tvClientName, tvCentersSummaryHeader;
    TextView tvAppVersion, tvCurrentDate;
    String SCREEN_NO;

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView navigationView;
    CoordinatorLayout coordinatorLayout;
    ImageView ivStaffImage;
    RelativeLayout rlNoLeads;
    TextView tvStaffId, tvStaffName, tvSOBDate;
    FloatingActionButton btnNewTarget;
    RelativeLayout rl_parent_cold_calling;

    Button btnProceed;
    String selectDate;

//    public String userName,userId,branchId;

    List<RawDataTable> allClientRawDataTableList;

    @Inject
    public ViewModelProvider.Factory viewModelFactory;
    public DynamicUIViewModel viewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fetch_other_day_colection);
        rvLeadDetails = (RecyclerView) findViewById(R.id.rv_lead_details);
        rlNoLeads = (RelativeLayout) findViewById(R.id.rl_no_leads);
        searchByPhoneNo = (SearchView) findViewById(R.id.sv_phone_no);
        btnNewTarget = findViewById(R.id.fb_add_target_details);
        spinnerSortByDate = (AppCompatSpinner) findViewById(R.id.sp_sort_by_date);
        spinnerSortByAlphabet = (AppCompatSpinner) findViewById(R.id.sp_sort_by_alphabetical_order);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tvCurrentDate = (TextView) findViewById(R.id.tv_currentdate);
        tvAppVersion = (TextView) findViewById(R.id.tv_app_version);
        tvClientName = (TextView) findViewById(R.id.tv_client_name_custom);
        tvCentersSummaryHeader = (TextView) findViewById(R.id.tv_centers_summary_header);
        rl_parent_cold_calling = findViewById(R.id.rl_parent_cold_calling);

        btnProceed=(Button)findViewById(R.id.btn_proceed);

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

        spinnerSortByDate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0) {

                    spinnerSortByAlphabet.setSelection(0);
                    searchByPhoneNo.setQuery("", false);
                    searchByPhoneNo.setIconified(true);
                    if (fetchOtherDayCollectionAdapter != null && fetchOtherDayCollectionAdapter.getFilter() != null) {
                        fetchOtherDayCollectionAdapter.getFilter().filter(parent.getSelectedItem().toString());
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

                    if (fetchOtherDayCollectionAdapter != null && fetchOtherDayCollectionAdapter.getFilter() != null) {
                        fetchOtherDayCollectionAdapter.getFilter().filter(parent.getSelectedItem().toString());
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

                if (fetchOtherDayCollectionAdapter != null && fetchOtherDayCollectionAdapter.getFilter() != null) {
                    fetchOtherDayCollectionAdapter.getFilter().filter(query);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                // filter recycler view when text is changed
                spinnerSortByDate.setSelection(0);
                spinnerSortByAlphabet.setSelection(0);

                if (fetchOtherDayCollectionAdapter != null && fetchOtherDayCollectionAdapter.getFilter() != null) {
                    fetchOtherDayCollectionAdapter.getFilter().filter(query);
                }
                return false;
            }
        });

        btnProceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(fetchOtherDayCollectionAdapter !=null && fetchOtherDayCollectionAdapter.getFetchOtherDayCollectionTableList() !=null
                        && fetchOtherDayCollectionAdapter.getFetchOtherDayCollectionTableList().size()>0){
                    boolean isValid=false;
                    for(FetchOtherDayCMDTO fetchOtherDayCMDTO : fetchOtherDayCollectionAdapter.getFetchOtherDayCollectionTableList()){
                        if (fetchOtherDayCMDTO!=null){
                            if (fetchOtherDayCMDTO.isSelected()){
                                isValid=true;
                                break;
                            }
                        }
                    }

                    if(isValid) {

                        appHelper.getDialogHelper().getConfirmationDialog().showTwoButtons(ERROR_MESSAGE_DO_YOU_WANT_TO_PROCEED, new ConfirmationDialog.ActionCallback() {
                            @Override
                            public void onAction() {
//                                saveCenterMeetingDetails(fetchOtherDayCollectionAdapter.getFetchOtherDayCollectionTableList());
                                getCenterMeetingDetailsFromServer(fetchOtherDayCollectionAdapter.getFetchOtherDayCollectionTableList());
                            }
                        });
                    }else{
                        appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT,ERROR_MESSAGE_SELECT_ONE_CENTER_AT_LEAST);
                    }

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

//            String selectDate = et_fromdate.getText().toString();

            getCenterNamesFromServerForFetchOtherDay();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void saveCenterMeetingDetails(List<FetchOtherDayCMDTO> fetchOtherDayCMDTOList) {
        try{
            viewModel.saveCenterMeetingDetails(fetchOtherDayCMDTOList);
            if (viewModel.getFetchOtherDayCMDTOLiveDataList() != null) {
                Observer observer = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        viewModel.getFetchOtherDayCMDTOLiveDataList().removeObserver(this);

                        appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.SUCCESS,
                                SUCCESS_RESPONSE_FOR_CENTER_MEETING_FETCH_FROM_SERVER, new ConfirmationDialog.ActionCallback() {
                                    @Override
                                    public void onAction() {
                                        finish();
                                    }
                                });
                    }
                };
                viewModel.getFetchOtherDayCMDTOLiveDataList().observe(this, observer);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private void getCenterMeetingDetailsFromServer(List<FetchOtherDayCMDTO> fetchOtherDayCMDTOList) {
        try {
            appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();
            viewModel.getCMDetailsFromServerForFetchOtherDay(fetchOtherDayCMDTOList,userId);
            if (viewModel.getStringLiveData() != null) {
                Observer observer = new Observer() {
                    @Override
                    public void onChanged(Object o) {
                        appHelper.getDialogHelper().getLoadingDialog().closeDialog();
                        String response = (String) o;
                        viewModel.getStringLiveData().removeObserver(this);

                        if (!TextUtils.isEmpty(response) && response.equalsIgnoreCase(SUCCESS_RESPONSE_MESSAGE)) {
                            appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.SUCCESS,
                                    SUCCESS_RESPONSE_FOR_CENTER_MEETING_FETCH_FROM_SERVER, new ConfirmationDialog.ActionCallback() {
                                        @Override
                                        public void onAction() {
                                            finish();
                                        }
                                    });
                        } else {
                            appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ERROR,
                                    FAILURE_RESPONSE_FOR_CENTER_MEETING_FETCH_FROM_SERVER);
                        }
                    }
                };
                viewModel.getStringLiveData().observe(this, observer);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            appHelper.getDialogHelper().getLoadingDialog().closeDialog();
            INSERT_LOG("getCenterMeetingDetailsFromServer", "Exception : " + ex.getMessage());
        }
    }

    private void getCenterNamesFromServerForFetchOtherDay() {
        try {
            appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();

//            String cmDateFromCalendar = appHelper.getCurrentDate(DATE_FORMAT_YYYYMMDD);
//            String cmDateFromCalendar = "20200519"; // TODO: Hardcoded date
//            String hardcodedUserid="SIF1000059";
//            String cmDateFromCalendar = appHelper.getCurrentDate(DATE_FORMAT_YYYYMMDD);
//            String cmDateFromCalendar = "20200519"; // TODO: Hardcoded date
            viewModel.getCenterNamesFromServerForFetchOtherDay(userId);
            if (viewModel.getStringListLiveData() != null) {
                Observer observer = new Observer() {
                    @Override
                    public void onChanged(Object o) {
                        appHelper.getDialogHelper().getLoadingDialog().closeDialog();
                        List<String> stringList = (List<String>) o;
                        viewModel.getStringListLiveData().removeObserver(this);
                        btnProceed.setVisibility(View.VISIBLE);

                        if (stringList != null && stringList.size() > 0) {
                            List<FetchOtherDayCMDTO> fetchOtherDayCMDTOList=new ArrayList<>();
                            for (String centerName:stringList){
                                if (!TextUtils.isEmpty(centerName)) {
                                    FetchOtherDayCMDTO fetchOtherDayCMDTO=new FetchOtherDayCMDTO();
                                    fetchOtherDayCMDTO.setCenterName(centerName);
                                    fetchOtherDayCMDTOList.add(fetchOtherDayCMDTO);
                                }
                            }
                            if (fetchOtherDayCMDTOList != null && fetchOtherDayCMDTOList.size() > 0) {
                                rlNoLeads.setVisibility(View.GONE);
                                rvLeadDetails.setVisibility(View.VISIBLE);

                                if (fetchOtherDayCollectionAdapter != null) {
                                    fetchOtherDayCollectionAdapter.setItem(fetchOtherDayCMDTOList);
                                } else {
                                    fetchOtherDayCollectionAdapter = new FetchOtherDayCollectionAdapter(FetchOtherDayCollectionActivity.this, fetchOtherDayCMDTOList,
                                            FetchOtherDayCollectionActivity.this, appHelper);
                                    rvLeadDetails.setAdapter(fetchOtherDayCollectionAdapter);
                                    fetchOtherDayCollectionAdapter.notifyDataSetChanged();
                                }
                            }else {
                                rvLeadDetails.setVisibility(View.GONE);
                                rlNoLeads.setVisibility(View.VISIBLE);
                            }
                        } else {
                            rvLeadDetails.setVisibility(View.GONE);
                            rlNoLeads.setVisibility(View.VISIBLE);
                        }
                    }
                };
                viewModel.getStringListLiveData().observe(this, observer);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            appHelper.getDialogHelper().getLoadingDialog().closeDialog();
            INSERT_LOG("getCenterMeetingDetailsFromServer", "Exception : " + ex.getMessage());
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

    @Override
    public void openScreenCallback(int position, String centerName) {
        try {
            openCenterMeetingHomeActivity(centerName);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void openCenterMeetingHomeActivity(String centerName) {
        try {
            Intent targetDetails = new Intent(this, CenterMeetingHomeActivity.class);
            targetDetails.putExtra(PARAM_LOAN_TYPE, loanType);
            targetDetails.putExtra(PARAM_USER_NAME, userName);
            targetDetails.putExtra(PARAM_BRANCH_ID, branchId);
            targetDetails.putExtra(PARAM_BRANCH_GST_CODE, branchGSTcode);
            targetDetails.putExtra(PARAM_USER_ID, userId);
            targetDetails.putExtra(PARAM_MODULE_TYPE, MODULE_TYPE_TARGET_DETAILS);
            targetDetails.putExtra(PARAM_PRODUCT_ID, productId);
            targetDetails.putExtra(PARAM_CENTER_NAME, centerName); // TODO: CenterName
            startActivity(targetDetails);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void INSERT_LOG(String methodName, String message) {
        try {
            if (viewModel != null) {
                viewModel.insertLog(methodName, message, userId, "", "FetchOtherDayCollectionActivity"
                        , CLIENT_ID, loanType, moduleType);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}