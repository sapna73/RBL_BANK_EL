package com.saartak.el.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bfil.uilibrary.dialogs.ConfirmationDialog;
import com.google.android.material.navigation.NavigationView;
import com.saartak.el.R;
import com.saartak.el.adapter.ReminderColdCallingAdapter;
import com.saartak.el.adapter.ReminderCollectionAdapter;
import com.saartak.el.adapter.ReminderLeadAdapter;
import com.saartak.el.constants.AppConstant;
import com.saartak.el.database.entity.ColdCallTable;
import com.saartak.el.database.entity.CollectionTable;
import com.saartak.el.database.entity.LeadTable;
import com.saartak.el.view_models.DynamicUIViewModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

import static com.saartak.el.constants.AppConstant.DATE_FORMAT_DD_MM_YYYY2;
import static com.saartak.el.constants.AppConstant.DATE_FORMAT_YYYY_MM_DD;
import static com.saartak.el.constants.AppConstant.LOAN_NAME_MSME;
import static com.saartak.el.constants.AppConstant.PARAM_BRANCH_GST_CODE;
import static com.saartak.el.constants.AppConstant.PARAM_BRANCH_ID;
import static com.saartak.el.constants.AppConstant.PARAM_LOAN_TYPE;
import static com.saartak.el.constants.AppConstant.PARAM_PRODUCT_ID;
import static com.saartak.el.constants.AppConstant.PARAM_ROLE_NAME;
import static com.saartak.el.constants.AppConstant.PARAM_SCREEN_NO;
import static com.saartak.el.constants.AppConstant.PARAM_USER_ID;
import static com.saartak.el.constants.AppConstant.PARAM_USER_NAME;
import static com.saartak.el.constants.AppConstant.REQUEST_CODE_NEW_LEAD;
import static com.saartak.el.constants.AppConstant.REQUEST_CODE_UPDATE_LEAD;
import static com.saartak.el.constants.AppConstant.SCREEN_NO_COLD_CALLING_MSME;
import static com.saartak.el.dynamicui.constants.ParametersConstant.ERROR_MESSAGE_SELECT_REMINDER;
import static com.saartak.el.dynamicui.constants.ParametersConstant.RADIO_BUTTON_ITEM_COLD_CALLING;
import static com.saartak.el.dynamicui.constants.ParametersConstant.RADIO_BUTTON_ITEM_COLLECTION;
import static com.saartak.el.dynamicui.constants.ParametersConstant.RADIO_BUTTON_ITEM_LEAD;
import static com.saartak.el.dynamicui.constants.ParametersConstant.RADIO_BUTTON_WHEN_ITEM_NOW;

public class ReminderActivity extends LOSBaseActivity implements ReminderColdCallingAdapter.SyncCallbackInterface {
    private static final String TAG = ColdCallingDetailsActivity.class.getCanonicalName();
    RecyclerView rvColdCallingDetails,rv_leads_details,rv_collection_details;
    RelativeLayout rlNoColdCallings,rl_no_lead,rl_no_collection;
    LinearLayout ll_fromDate,ll_view;
    TextView et_fromdate;
    ReminderColdCallingAdapter ColdCallingDetailsAdapter;
    ReminderLeadAdapter leadDetailsAdapter;
    ReminderCollectionAdapter collectionDetailsAdapter;
    List<ColdCallTable> coldCallingDataTableList;
    List<CollectionTable> collectionDataTableList;
    List<LeadTable> leadDataTableList;
    private Toolbar toolbar;
    TextView tvClientName;
    String SCREEN_NO;

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView navigationView;
    CoordinatorLayout coordinatorLayout;
    ImageView ivStaffImage;
    TextView tvStaffId, tvStaffName, tvSOBDate;

    RadioGroup rg_reminder;
    RadioButton rb_cold_calling,rb_lead,rb_collection;

    RelativeLayout rl_parent_cold_calling,rl_parent_leads,rl_parent_collection;

    @Inject
    public ViewModelProvider.Factory viewModelFactory;
    public DynamicUIViewModel viewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,
//                WindowManager.LayoutParams.FLAG_SECURE);

        setContentView(R.layout.activity_reminder);
        ll_view = (LinearLayout)findViewById(R.id.ll_view);
        ll_fromDate = (LinearLayout)findViewById(R.id.ll_fromDate);
        et_fromdate = (TextView)findViewById(R.id.tv_fromdate);
        rl_parent_cold_calling = findViewById(R.id.rl_parent_cold_calling);
        rl_parent_leads = findViewById(R.id.rl_parent_leads);
        rl_parent_collection = findViewById(R.id.rl_parent_collection);
        rvColdCallingDetails =  findViewById(R.id.rv_cold_calling_details);
        rv_leads_details =  findViewById(R.id.rv_leads_details);
        rv_collection_details =  findViewById(R.id.rv_collection_details);
        rlNoColdCallings =  findViewById(R.id.rl_no_cold_calling);
        rl_no_lead =  findViewById(R.id.rl_no_lead);
        rl_no_collection =  findViewById(R.id.rl_no_collection);
        toolbar =  findViewById(R.id.toolbar);
        tvClientName =  findViewById(R.id.tv_client_name_custom);

        rg_reminder=(RadioGroup) findViewById(R.id.rg_reminder);
        rb_cold_calling=(RadioButton) findViewById(R.id.rb_cold_calling);
        rb_lead=(RadioButton) findViewById(R.id.rb_lead);
        rb_collection=(RadioButton) findViewById(R.id.rb_collection);

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
        /*if (toolbar != null) {
            // you can safely invoke methods on the Toolbar
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }*/

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
        RecyclerView.LayoutManager mLayoutManagerLead = new LinearLayoutManager(getApplicationContext());
        RecyclerView.LayoutManager mLayoutManagerCollection = new LinearLayoutManager(getApplicationContext());
        rvColdCallingDetails.setLayoutManager(mLayoutManager);
        rvColdCallingDetails.setItemAnimator(new DefaultItemAnimator());
        rvColdCallingDetails.setNestedScrollingEnabled(false);

        rv_leads_details.setLayoutManager(mLayoutManagerLead);
        rv_leads_details.setItemAnimator(new DefaultItemAnimator());
        rv_leads_details.setNestedScrollingEnabled(false);

        rv_collection_details.setLayoutManager(mLayoutManagerCollection);
        rv_collection_details.setItemAnimator(new DefaultItemAnimator());
        rv_collection_details.setNestedScrollingEnabled(false);
        // rvColdCallingDetails.addItemDecoration(new MyDividerItemDecoration(this, DividerItemDecoration.VERTICAL, 36));

        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int  mDay = c.get(Calendar.DAY_OF_MONTH);
        ll_fromDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get Current Date
                DatePickerDialog datePickerDialog = new DatePickerDialog(ReminderActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,int month, int day) {
                                Calendar calendar = Calendar.getInstance();
                                calendar.set(Calendar.YEAR, year);
                                calendar.set(Calendar.MONTH, month);
                                calendar.set(Calendar.DAY_OF_MONTH, day);
                                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(AppConstant.DATE_FORMAT_YYYY_MM_DD, Locale.US);
                                String selectDate = simpleDateFormat.format(calendar.getTime());

//                                getColdCallingDataFromLocalDB(SCREEN_NO,userId,selectDate);
                                et_fromdate.setText(selectDate);

                            }
                        }, mYear, mMonth, mDay);
                DatePicker datePicker = datePickerDialog.getDatePicker();
                datePicker.setMinDate(System.currentTimeMillis());

                datePickerDialog.show();

            }
        });

        ll_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(rg_reminder !=null && rg_reminder.getCheckedRadioButtonId()!=-1) {
                    String selectedRadioButton = appHelper.getSelectedRadioButtonItem(rg_reminder, rg_reminder.getCheckedRadioButtonId());
                    if(!TextUtils.isEmpty(selectedRadioButton)){
                        String selectDate = et_fromdate.getText().toString();
                        if(TextUtils.isEmpty(selectDate)) {
                            appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, "Please select Date From Calendar");
                        }else {
                            if(selectedRadioButton.equalsIgnoreCase(RADIO_BUTTON_ITEM_COLD_CALLING)){
                                visibilityForRecyclerviews(RADIO_BUTTON_ITEM_COLD_CALLING);
                                getColdCallingDataFromLocalDB(SCREEN_NO,userId,selectDate);
                            }
                            else if(selectedRadioButton.equalsIgnoreCase(RADIO_BUTTON_ITEM_LEAD)){
                                visibilityForRecyclerviews(RADIO_BUTTON_ITEM_LEAD);
                                getLeadDataFromLocalDB(SCREEN_NO,userId,loanType,selectDate);
                            }
                            else if(selectedRadioButton.equalsIgnoreCase(RADIO_BUTTON_ITEM_COLLECTION)){
                                visibilityForRecyclerviews(RADIO_BUTTON_ITEM_COLLECTION);
                                getCollectionDataFromLocalDB(SCREEN_NO,userId,selectDate);
                            }
//                            getColdCallingDataFromLocalDB(SCREEN_NO,userId,selectDate);
                        }
                    }
                }else{
                    appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT,ERROR_MESSAGE_SELECT_REMINDER);
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
        try {
            viewModel = ViewModelProviders.of(this, viewModelFactory).get(DynamicUIViewModel.class);

            if (loanType.equalsIgnoreCase(LOAN_NAME_MSME)) {
                SCREEN_NO = SCREEN_NO_COLD_CALLING_MSME;
            } else {
                SCREEN_NO = SCREEN_NO_COLD_CALLING_MSME;
            }

            String currentDate = appHelper.getCurrentDate(DATE_FORMAT_YYYY_MM_DD);
            et_fromdate.setText(currentDate);
        }catch (Exception ex){
            ex.printStackTrace();
            INSERT_LOG("configureViewModel","Exception : "+ex.getMessage());
        }

    }

    public void visibilityForRecyclerviews(String selectedRadioButton){
        if(selectedRadioButton.equalsIgnoreCase(RADIO_BUTTON_ITEM_COLD_CALLING)){
            rl_parent_cold_calling.setVisibility(View.VISIBLE);
            rl_parent_leads.setVisibility(View.GONE);
            rl_parent_collection.setVisibility(View.GONE);

        }
        else if(selectedRadioButton.equalsIgnoreCase(RADIO_BUTTON_ITEM_LEAD)){
            rl_parent_cold_calling.setVisibility(View.GONE);
            rl_parent_leads.setVisibility(View.VISIBLE);
            rl_parent_collection.setVisibility(View.GONE);
        }
        else if(selectedRadioButton.equalsIgnoreCase(RADIO_BUTTON_ITEM_COLLECTION)){
            rl_parent_cold_calling.setVisibility(View.GONE);
            rl_parent_leads.setVisibility(View.GONE);
            rl_parent_collection.setVisibility(View.VISIBLE);
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
//                    getColdCallingDataFromLocalDB(screenNO,userId);
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
            userName = getIntent().getStringExtra(PARAM_USER_NAME);
            userId = getIntent().getStringExtra(PARAM_USER_ID);
            branchId = getIntent().getStringExtra(PARAM_BRANCH_ID);
            branchGSTcode = getIntent().getStringExtra(PARAM_BRANCH_GST_CODE);
            loanType = getIntent().getStringExtra(PARAM_LOAN_TYPE);
            roleName = getIntent().getStringExtra(PARAM_ROLE_NAME);
            productId = getIntent().getStringExtra(PARAM_PRODUCT_ID);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void getColdCallingDataFromLocalDB(String screen, String userId,String selectedDate) {
        try {

            appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();

            viewModel.getColdCallingData(screen,userId,loanType);
            if (viewModel.getColdCallTableLiveData() != null) {
                Observer getLeadRawDataObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        List<ColdCallTable> coldCallingDataTableListAll = (List<ColdCallTable>) o;
                        viewModel.getColdCallTableLiveData().removeObserver(this);

                        appHelper.getDialogHelper().getLoadingDialog().closeDialog();

                        if (coldCallingDataTableListAll != null && coldCallingDataTableListAll.size() > 0) {
                            coldCallingDataTableList=new ArrayList<>();
                            for (ColdCallTable callTable:coldCallingDataTableListAll){
                                if (!TextUtils.isEmpty(callTable.getWhen())&& !callTable.getWhen().equalsIgnoreCase(RADIO_BUTTON_WHEN_ITEM_NOW)) {
                                    if (!TextUtils.isEmpty(callTable.getNextFollowUpDate()) && callTable.getNextFollowUpDate().equalsIgnoreCase(selectedDate)) {
                                        coldCallingDataTableList.add(callTable);
                                    }
                                }
                            }

                            if (coldCallingDataTableList != null && coldCallingDataTableList.size() > 0) {
                                rlNoColdCallings.setVisibility(View.GONE);
                                rvColdCallingDetails.setVisibility(View.VISIBLE);

                                ColdCallingDetailsAdapter = new ReminderColdCallingAdapter(ReminderActivity.this,
                                        coldCallingDataTableList, ReminderActivity.this, appHelper);
                                rvColdCallingDetails.setAdapter(ColdCallingDetailsAdapter);
                                ColdCallingDetailsAdapter.notifyDataSetChanged();
                            } else {
                                rlNoColdCallings.setVisibility(View.VISIBLE);
                                rvColdCallingDetails.setVisibility(View.GONE);
                            }
                        }
                    }
                };
                viewModel.getColdCallTableLiveData().observe(this, getLeadRawDataObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            appHelper.getDialogHelper().getLoadingDialog().closeDialog();
            INSERT_LOG("getLeadRawDataFromLocalDB","Exception : "+ex.getMessage());
        }
    }

    public void getLeadDataFromLocalDB(String screen, String userId, String loanType,String selectedDate) {
        try {

            appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();

            viewModel.getLeadData(screen,userId,loanType);
            if (viewModel.getLeadTableLiveData() != null) {
                Observer getLeadDataObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        List<LeadTable> leadDetailsAdapterAll = (List<LeadTable>) o;
                        viewModel.getLeadTableLiveData().removeObserver(this);

                        appHelper.getDialogHelper().getLoadingDialog().closeDialog();

                        if (leadDetailsAdapterAll != null && leadDetailsAdapterAll.size() > 0) {
                            leadDataTableList = new ArrayList<>();
                            for (LeadTable leadTable : leadDetailsAdapterAll) {
                                if (!leadTable.isSync() && leadTable.getInterestedInLoan() == 1) {
                                        if (!TextUtils.isEmpty(leadTable.getCreated_date()) && leadTable.getCreated_date().equalsIgnoreCase(selectedDate)) {
                                            leadDataTableList.add(leadTable);
                                        }
                                }
                            }

                            if (leadDataTableList != null && leadDataTableList.size() > 0) {
                                rl_no_lead.setVisibility(View.GONE);
                                rv_leads_details.setVisibility(View.VISIBLE);

                                leadDetailsAdapter = new ReminderLeadAdapter(ReminderActivity.this,
                                        leadDataTableList,appHelper);
                                rv_leads_details.setAdapter(leadDetailsAdapter);
                                leadDetailsAdapter.notifyDataSetChanged();
                            } else {
                                rl_no_lead.setVisibility(View.VISIBLE);
                                rv_leads_details.setVisibility(View.GONE);
                            }
                        }
                    }
                };
                viewModel.getLeadTableLiveData().observe(this, getLeadDataObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            appHelper.getDialogHelper().getLoadingDialog().closeDialog();

            INSERT_LOG("getLeadDataFromLocalDB","Exception : "+ex.getMessage());
        }
    }

    public void getCollectionDataFromLocalDB(String screen, String userId,String selectedDate) {
        try {
            appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();

            viewModel.getCollectiondata(screen,userId);
            if (viewModel.getCollectionTableLiveData() != null) {
                Observer getLeadRawDataObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        List<CollectionTable> collectionDataTableListAll = (List<CollectionTable>) o;
                        viewModel.getCollectionTableLiveData().removeObserver(this);

                        appHelper.getDialogHelper().getLoadingDialog().closeDialog();

                        if (collectionDataTableListAll != null && collectionDataTableListAll.size() > 0) {
                            collectionDataTableList=new ArrayList<>();
                            for (CollectionTable collectionTable:collectionDataTableListAll){
                                if (!collectionTable.isSync()) {
                                    if (!TextUtils.isEmpty(collectionTable.getCreated_date()) && collectionTable.getCreated_date().equalsIgnoreCase(selectedDate)) {
                                        collectionDataTableList.add(collectionTable);
                                    }
                                }
                            }

                            if (collectionDataTableList != null && collectionDataTableList.size() > 0) {
                                rl_no_collection.setVisibility(View.GONE);
                                rv_collection_details.setVisibility(View.VISIBLE);

                                collectionDetailsAdapter = new ReminderCollectionAdapter(ReminderActivity.this,
                                        collectionDataTableList,  appHelper);
                                rv_collection_details.setAdapter(collectionDetailsAdapter);
                                collectionDetailsAdapter.notifyDataSetChanged();
                            } else {
                                rl_no_collection.setVisibility(View.VISIBLE);
                                rv_collection_details.setVisibility(View.GONE);
                            }
                        }
                    }
                };
                viewModel.getCollectionTableLiveData().observe(this, getLeadRawDataObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            appHelper.getDialogHelper().getLoadingDialog().closeDialog();

            INSERT_LOG("getLeadRawDataFromLocalDB","Exception : "+ex.getMessage());
        }
    }


    private void postSubmittedData(ColdCallTable rawDataTable) {
        try {

            if (appHelper.isNetworkAvailable()) {

                appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();

                viewModel.postColdCallDataToServer(rawDataTable, SCREEN_NO);
                if (viewModel.getDynamicUITableLiveData() != null) {
                    Observer postSubmittedDataObserver = new Observer() {
                        @Override
                        public void onChanged(@Nullable Object o) {
                            viewModel.getDynamicUITableLiveData().removeObserver(this);
                            appHelper.getDialogHelper().getLoadingDialog().closeDialog();

//                            getColdCallingDataFromLocalDB(SCREEN_NO,userId);
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
    public void syncCallback(int position, ColdCallTable rawDataTable) {
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


    private void INSERT_LOG(String methodName,String message){
        try{
            if(viewModel !=null){
                viewModel.insertLog(methodName,message,userId,"","ReminderActivity"
                        ,CLIENT_ID,loanType,moduleType);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}

