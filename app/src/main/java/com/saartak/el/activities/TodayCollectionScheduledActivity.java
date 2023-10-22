package com.saartak.el.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.view.ActionMode;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.saartak.el.BuildConfig;
import com.saartak.el.R;
import com.saartak.el.adapter.OtherDayCollectionScheduledAdapter;
import com.saartak.el.adapter.TodayCollectionScheduledAdapter;
import com.saartak.el.database.converter.TimestampConverter;
import com.saartak.el.database.entity.CenterCreationTable;
import com.saartak.el.database.entity.RawDataTable;
import com.saartak.el.database.entity.TodayCollectionScheduledTable;
import com.saartak.el.view_models.DynamicUIViewModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

import static com.saartak.el.constants.AppConstant.DATE_FORMAT_DD_MM_YYYY2;
import static com.saartak.el.constants.AppConstant.DATE_FORMAT_YYYY_MM_DD;
import static com.saartak.el.constants.AppConstant.PARAM_BRANCH_GST_CODE;
import static com.saartak.el.constants.AppConstant.PARAM_BRANCH_ID;
import static com.saartak.el.constants.AppConstant.PARAM_LOAN_TYPE;
import static com.saartak.el.constants.AppConstant.PARAM_PRODUCT_ID;
import static com.saartak.el.constants.AppConstant.PARAM_USER_ID;
import static com.saartak.el.constants.AppConstant.PARAM_USER_NAME;
import static com.saartak.el.constants.AppConstant.getCMDateForTodayCollectionScheduled;

public class TodayCollectionScheduledActivity extends LOSBaseActivity implements HasSupportFragmentInjector{

    private static final String TAG = TodayCollectionScheduledActivity.class.getCanonicalName();
    RecyclerView rvLeadDetails,rvOtherDay;
    TodayCollectionScheduledAdapter todayCollectionScheduledAdapter;
    OtherDayCollectionScheduledAdapter otherDayCollectionScheduledAdapter;

    public String CENTER_NAME="";

    private Toolbar toolbar;

    private ActionMode actionMode;

    String SCREEN_NO;

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView navigationView;
    CoordinatorLayout coordinatorLayout;
    ImageView ivStaffImage;
    RelativeLayout rlNoLeads,rlNoOtherDay;
    TextView tvAppVersion,tvCurrentDate,tvUserName;
    CenterCreationTable CENTER_CREATION_TABLE;
    List<RawDataTable> allClientRawDataTableList;

    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;

    @Inject
    public ViewModelProvider.Factory viewModelFactory;
    public DynamicUIViewModel viewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_today_collection_scheduled);

        tvUserName = (TextView)findViewById(R.id.tv_user_name);
        tvCurrentDate = (TextView)findViewById(R.id.tv_currentdate);
        tvAppVersion = (TextView)findViewById(R.id.tv_app_version);


        rvLeadDetails = (RecyclerView) findViewById(R.id.rv_lead_details);
        rvOtherDay = (RecyclerView) findViewById(R.id.rv_other_day);
        rlNoLeads = (RelativeLayout) findViewById(R.id.rl_no_leads);
        rlNoOtherDay = (RelativeLayout) findViewById(R.id.rl_no_other_day);
        toolbar = (Toolbar)findViewById(R.id.toolbar_eligibility);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // TODO: Insert Into Staff Activity Table
                insertStaffActivity(viewModel,CENTER_NAME,userId,"TODAY COLLECTION SCHEDULED",1);

                // TODO: back button pressed
                finish();
            }
        });


        userName = getIntent().getStringExtra(PARAM_USER_NAME);
        userId = getIntent().getStringExtra(PARAM_USER_ID);
        branchId = getIntent().getStringExtra(PARAM_BRANCH_ID);
        branchGSTcode = getIntent().getStringExtra(PARAM_BRANCH_GST_CODE);
        loanType = getIntent().getStringExtra(PARAM_LOAN_TYPE);
        productId = getIntent().getStringExtra(PARAM_PRODUCT_ID);
//        CENTER_NAME = getIntent().getStringExtra(PARAM_CENTER_NAME);

        if(!TextUtils.isEmpty(userName)){
            tvUserName.setText(userName);
        }

        String appVersion = BuildConfig.VERSION_NAME;
        if (!TextUtils.isEmpty(appVersion)) {
            tvAppVersion.setText(getResources().getString(R.string.version)+" "+appVersion);
        }

        String currentDate = appHelper.getCurrentDate(DATE_FORMAT_DD_MM_YYYY2);
        if (!TextUtils.isEmpty(currentDate)) {
            tvCurrentDate.setText(currentDate);
        }


        // TODO: Configure Dagger
        configureDagger();
        // TODO: Configure View Model
        configureViewModel();

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        rvLeadDetails.setLayoutManager(mLayoutManager);
        rvLeadDetails.setItemAnimator(new DefaultItemAnimator());
        rvLeadDetails.setNestedScrollingEnabled(false);

        todayCollectionScheduledAdapter = new TodayCollectionScheduledAdapter(
                TodayCollectionScheduledActivity.this, new ArrayList<>(),appHelper,viewModel,true);
        rvLeadDetails.setAdapter(todayCollectionScheduledAdapter);

        RecyclerView.LayoutManager mLayoutManagerOther = new LinearLayoutManager(getApplicationContext());
        rvOtherDay.setLayoutManager(mLayoutManagerOther);
        rvOtherDay.setItemAnimator(new DefaultItemAnimator());
        rvOtherDay.setNestedScrollingEnabled(false);

        otherDayCollectionScheduledAdapter = new OtherDayCollectionScheduledAdapter(
                TodayCollectionScheduledActivity.this, new ArrayList<>(),appHelper,viewModel,true);
        rvOtherDay.setAdapter(otherDayCollectionScheduledAdapter);
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return dispatchingAndroidInjector;
    }


    private void configureDagger() {
        AndroidInjection.inject(this);
    }

    public void configureViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(DynamicUIViewModel.class);
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
//            CENTER_NAME = getIntent().getStringExtra(PARAM_CENTER_NAME);

            Date cmDate = TimestampConverter.toDate(appHelper.getCurrentDateTime(DATE_FORMAT_YYYY_MM_DD));
            if(cmDate !=null) {
                getTodayCollectionScheduledList(userId,cmDate);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // TODO: GETTING CASH COLLECTION SUMMARY LIST
    public void getTodayCollectionScheduledList(String userId,Date cmDate ) {
        appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();

        try {
            viewModel.getTodayCollectionScheduledList(userId,cmDate);
            if (viewModel.getTodayCollectionScheduledTableLiveDataList() != null) {
                Observer observer = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        appHelper.getDialogHelper().getLoadingDialog().closeDialog();
                        List<TodayCollectionScheduledTable> todayCollectionScheduledTableList = (List<TodayCollectionScheduledTable>) o;
                        List<TodayCollectionScheduledTable> todayList =new ArrayList<>();
                        List<TodayCollectionScheduledTable> otherDayList =new ArrayList<>();
                        viewModel.getTodayCollectionScheduledTableLiveDataList().removeObserver(this);

                        if(todayCollectionScheduledTableList !=null && todayCollectionScheduledTableList.size()>0){
                            String currentDate = getCMDateForTodayCollectionScheduled(appHelper);

                            for (TodayCollectionScheduledTable todayCollectionScheduledTable:todayCollectionScheduledTableList){
                                if (todayCollectionScheduledTable!=null && !TextUtils.isEmpty(todayCollectionScheduledTable.getCenterMeetingDate())){
                                    if (todayCollectionScheduledTable.getCenterMeetingDate().equalsIgnoreCase(currentDate)){
                                        todayList.add(todayCollectionScheduledTable);
                                    }else {
                                        otherDayList.add(todayCollectionScheduledTable);
                                    }
                                }
                            }

                            if(todayList !=null && todayList.size()>0){
                                rlNoLeads.setVisibility(View.GONE);
                                rvLeadDetails.setVisibility(View.VISIBLE);

                                if (todayCollectionScheduledAdapter != null) {

                                    todayCollectionScheduledAdapter.setItem(todayList,
                                            false);
                                }
                            }else {
                                rvLeadDetails.setVisibility(View.GONE);
                                rlNoLeads.setVisibility(View.VISIBLE);
                            }

                            if(otherDayList !=null && otherDayList.size()>0){
                                rlNoOtherDay.setVisibility(View.GONE);
                                rvOtherDay.setVisibility(View.VISIBLE);

                                if (otherDayCollectionScheduledAdapter != null) {

                                    otherDayCollectionScheduledAdapter.setItem(otherDayList,
                                            false);
                                }
                            }else {
                                rvOtherDay.setVisibility(View.GONE);
                                rlNoOtherDay.setVisibility(View.VISIBLE);
                            }

                        }else{
                            rvLeadDetails.setVisibility(View.GONE);
                            rlNoLeads.setVisibility(View.VISIBLE);

                            rvOtherDay.setVisibility(View.GONE);
                            rlNoOtherDay.setVisibility(View.VISIBLE);
                        }


                    }
                };
                viewModel.getTodayCollectionScheduledTableLiveDataList().observe(this, observer);
            }
        } catch (Exception ex) {
            appHelper.getDialogHelper().getLoadingDialog().closeDialog();
            ex.printStackTrace();
        }
    }


    @Override
    public void onBackPressed() {

        // TODO: Insert Into Staff Activity Table
        insertStaffActivity(viewModel,CENTER_NAME,userId,"TODAY COLLECTION SCHEDULED",1);

        finish();

    }

    private void INSERT_LOG(String methodName, String message) {
        try {
            if (viewModel != null) {
                viewModel.insertLog(methodName, message, userId, "", "ProductActivity"
                        , CLIENT_ID, loanType, moduleType);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}