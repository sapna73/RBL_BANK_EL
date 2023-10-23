package com.saartak.el.activities;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

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

import com.bfil.uilibrary.dialogs.ConfirmationDialog;
import com.google.android.material.navigation.NavigationView;
import com.saartak.el.BuildConfig;
import com.saartak.el.R;
import com.saartak.el.adapter.CenterMeetingCashCollectionSummaryAdapter;
import com.saartak.el.database.converter.TimestampConverter;
import com.saartak.el.database.entity.CashDenominationTable;
import com.saartak.el.database.entity.CenterCreationTable;
import com.saartak.el.database.entity.RawDataTable;
import com.saartak.el.fragments.DenominationFragment;
import com.saartak.el.models.CashCollectionSummaryDTO;
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
import static com.saartak.el.constants.AppConstant.MODULE_TYPE_COLLECTION;
import static com.saartak.el.constants.AppConstant.PARAM_BRANCH_GST_CODE;
import static com.saartak.el.constants.AppConstant.PARAM_BRANCH_ID;
import static com.saartak.el.constants.AppConstant.PARAM_LOAN_TYPE;
import static com.saartak.el.constants.AppConstant.PARAM_MODULE_TYPE;
import static com.saartak.el.constants.AppConstant.PARAM_PRODUCT_ID;
import static com.saartak.el.constants.AppConstant.PARAM_USER_ID;
import static com.saartak.el.constants.AppConstant.PARAM_USER_NAME;
import static com.saartak.el.dynamicui.constants.ParametersConstant.FAILURE_RESPONSE_MESSAGE;
import static com.saartak.el.dynamicui.constants.ParametersConstant.SUCCESS_RESPONSE_MESSAGE;

public class CenterMeetingCashCollectionSummaryActivity extends LOSBaseActivity implements HasSupportFragmentInjector , DenominationFragment.DenominationInterface {

    private static final String TAG = CenterMeetingCashCollectionSummaryActivity.class.getCanonicalName();
    RecyclerView rvLeadDetails;
    CenterMeetingCashCollectionSummaryAdapter centerMeetingCashCollectionSummaryAdapter;

    public String CENTER_NAME="";

    private Toolbar toolbar;

    private ActionMode actionMode;

    String SCREEN_NO;

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView navigationView;
    CoordinatorLayout coordinatorLayout;
    ImageView ivStaffImage;
    RelativeLayout rlNoLeads;
    TextView tvAppVersion,tvCurrentDate,tvUserName;
    TextView tvCollectionDate,tvTotalCenterDue, tvTotalCashCollection,tvTotalSavingsCollection,tvOverAllTotalCollection;
    Button btnEnterDenomination,btnUpload;
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

        setContentView(R.layout.activity_center_meeting_cash_collection_summary);

        tvUserName = (TextView)findViewById(R.id.tv_user_name);
        tvCurrentDate = (TextView)findViewById(R.id.tv_currentdate);
        tvAppVersion = (TextView)findViewById(R.id.tv_app_version);

        tvCollectionDate = (TextView)findViewById(R.id.tv_collection_date);
        tvTotalCenterDue = (TextView)findViewById(R.id.tv_total_center_due);
        tvTotalCashCollection = (TextView)findViewById(R.id.tv_total_cash_collection);
        tvTotalSavingsCollection = (TextView)findViewById(R.id.tv_total_savings_collection);
        tvOverAllTotalCollection = (TextView)findViewById(R.id.tv_overall_total_collection);

        btnEnterDenomination=(Button)findViewById(R.id.btn_enter_denomination);
        btnUpload=(Button)findViewById(R.id.btn_confirm_upload);

        rvLeadDetails = (RecyclerView) findViewById(R.id.rv_lead_details);
        rlNoLeads = (RelativeLayout) findViewById(R.id.rl_no_leads);
        toolbar = (Toolbar)findViewById(R.id.toolbar_eligibility);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // TODO: Insert Into Staff Activity Table
                insertStaffActivity(viewModel,CENTER_NAME,userId,"COLLECTION SUMMARY",1);

                // back button pressed
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
            // TODO: set collection date
            tvCollectionDate.setText(currentDate);
        }


        // TODO: Configure Dagger
        configureDagger();
        // TODO: Configure View Model
        configureViewModel();

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        rvLeadDetails.setLayoutManager(mLayoutManager);
        rvLeadDetails.setItemAnimator(new DefaultItemAnimator());
        rvLeadDetails.setNestedScrollingEnabled(false);

        centerMeetingCashCollectionSummaryAdapter = new CenterMeetingCashCollectionSummaryAdapter(
                CenterMeetingCashCollectionSummaryActivity.this, new ArrayList<>(),appHelper,viewModel,true);
        rvLeadDetails.setAdapter(centerMeetingCashCollectionSummaryAdapter);

        btnEnterDenomination.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(centerMeetingCashCollectionSummaryAdapter !=null && centerMeetingCashCollectionSummaryAdapter.getIndividualCenterCollectionList() !=null
                        && centerMeetingCashCollectionSummaryAdapter.getIndividualCenterCollectionList().size()>0){

                    boolean isAllConfirmed=false;
                    for(CashCollectionSummaryDTO.IndividualCenterCollection individualCenterCollection : centerMeetingCashCollectionSummaryAdapter.getIndividualCenterCollectionList()){
                        if(individualCenterCollection.isCenterConfirm()){
                            isAllConfirmed=true;
                        }else{
                            isAllConfirmed=false;
                            break;
                        }
                    }

                    if(isAllConfirmed) {
                        // TODO: Open Cash Denomination screen

                        Bundle bundle = new Bundle();
                        bundle.putString(PARAM_LOAN_TYPE, loanType);
                        bundle.putString(PARAM_USER_NAME, userName);
                        bundle.putString(PARAM_BRANCH_ID, branchId);
                        bundle.putString(PARAM_BRANCH_GST_CODE, branchGSTcode);
                        bundle.putString(PARAM_USER_ID, userId);
                        bundle.putString(PARAM_MODULE_TYPE, MODULE_TYPE_COLLECTION);
                        bundle.putString(PARAM_PRODUCT_ID, productId);
//                        bundle.putString(PARAM_CENTER_NAME, CENTER_NAME); // TODO: CenterName

                        DenominationFragment denominationFragment = new DenominationFragment();
                        denominationFragment.show(getSupportFragmentManager(), "Cash Denomination");
                        denominationFragment.setArguments(bundle);
                    }else{
                        appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT,"Please Confirm All");
                    }

                }
            }
        });

        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(centerMeetingCashCollectionSummaryAdapter !=null && centerMeetingCashCollectionSummaryAdapter.getIndividualCenterCollectionList() !=null
                        && centerMeetingCashCollectionSummaryAdapter.getIndividualCenterCollectionList().size()>0){

                    boolean isAllConfirmed=false;
                    for(CashCollectionSummaryDTO.IndividualCenterCollection individualCenterCollection : centerMeetingCashCollectionSummaryAdapter.getIndividualCenterCollectionList()){
                        if(individualCenterCollection.isCenterConfirm()){
                            isAllConfirmed=true;
                        }else{
                            isAllConfirmed=false;
                            break;
                        }
                    }

                    if(isAllConfirmed) {
                        // TODO: Sync Cash Collection Summary
//                        syncCashCollectionSummary(centerMeetingCashCollectionSummaryAdapter.getIndividualCenterCollectionList());
                        sendCollectionToServer(centerMeetingCashCollectionSummaryAdapter.getIndividualCenterCollectionList());


                    }else{
                        appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT,"Please Confirm All");
                    }

                }
            }
        });

    }

    private void syncCashCollectionSummary(List<CashCollectionSummaryDTO.IndividualCenterCollection> cashCollectionSummaryList) {
        try{
            viewModel.syncCashCollectionSummary(cashCollectionSummaryList);
            if (viewModel.getStringLiveData() != null) {
                Observer observer = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        viewModel.getStringLiveData().removeObserver(this);
                        String response=(String)o;

                        appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.SUCCESS,
                                response, new ConfirmationDialog.ActionCallback() {
                                    @Override
                                    public void onAction() {

                                        // TODO: Insert Into Staff Activity Table
                                        insertStaffActivity(viewModel,CENTER_NAME,userId,"CASH COLLECTION SUMMARY",1);

                                        finish();
                                    }
                                });
                    }
                };
                viewModel.getStringLiveData().observe(this, observer);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private void sendCollectionToServer(List<CashCollectionSummaryDTO.IndividualCenterCollection> cashCollectionSummaryList) {
        try {
            appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();

//            viewModel.sendCollectionToServer(userId,cashCollectionSummaryList);
            viewModel.sendCollectionToServerNew(userId,cashCollectionSummaryList);
            if (viewModel.getStringLiveData() != null) {
                Observer observer = new Observer() {
                    @Override
                    public void onChanged(Object o) {
                        appHelper.getDialogHelper().getLoadingDialog().closeDialog();
                        String response = (String) o;
                        Log.d(TAG, "sendCollectionToServer ==> " + response);
                        viewModel.getStringLiveData().removeObserver(this);
                        if (!TextUtils.isEmpty(response) && response.equalsIgnoreCase(SUCCESS_RESPONSE_MESSAGE)) {
                            appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.SUCCESS,
                                    SUCCESS_RESPONSE_MESSAGE, new ConfirmationDialog.ActionCallback() {
                                        @Override
                                        public void onAction() {
                                            // TODO: Insert Into Staff Activity Table
                                            insertStaffActivity(viewModel,CENTER_NAME,userId,"CASH COLLECTION SUMMARY",1);

                                            finish();
                                        }
                                    });
                        }else {

                            String errorMsg = FAILURE_RESPONSE_MESSAGE;
                            if (!TextUtils.isEmpty(response)) {
                                errorMsg = response;
                            }
                            appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ERROR,
                                    errorMsg, new ConfirmationDialog.ActionCallback() {
                                        @Override
                                        public void onAction() {

                                        }
                                    });
                        }
                    }
                };
                viewModel.getStringLiveData().observe(this, observer);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            appHelper.getDialogHelper().getLoadingDialog().closeDialog();
            INSERT_LOG("sendCollectionToServer", "Exception : " + ex.getMessage());
        }
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
        // TODO: Getting cash collection summary list
        Date cmDate = TimestampConverter.toDate(appHelper.getCurrentDateTime(DATE_FORMAT_YYYY_MM_DD));
        if(cmDate !=null) {
            getCashCollectionSummaryList(userId,cmDate);
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
//            CENTER_NAME = getIntent().getStringExtra(PARAM_CENTER_NAME);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    @Override
    public void onBackPressed() {

        // TODO: Insert Into Staff Activity Table
        insertStaffActivity(viewModel,CENTER_NAME,userId,"COLLECTION SUMMARY",1);

        finish();

    }


    // TODO: GETTING CASH COLLECTION SUMMARY LIST
    public void getCashCollectionSummaryList(String userId,Date cmDate ) {
        appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();

        try {
            viewModel.getCashCollectionSummaryList(userId,cmDate);
            if (viewModel.getCashCollectionSummaryDTOLiveData() != null) {
                Observer observer = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        appHelper.getDialogHelper().getLoadingDialog().closeDialog();
                        CashCollectionSummaryDTO cashCollectionSummaryDTO = (CashCollectionSummaryDTO) o;
                        viewModel.getCashCollectionSummaryDTOLiveData().removeObserver(this);

                        if(cashCollectionSummaryDTO !=null && cashCollectionSummaryDTO.getIndividualCenterCollectionList() !=null
                                && cashCollectionSummaryDTO.getIndividualCenterCollectionList().size()>0){

                            rlNoLeads.setVisibility(View.GONE);
                            rvLeadDetails.setVisibility(View.VISIBLE);

                            if (centerMeetingCashCollectionSummaryAdapter != null) {
                                boolean isEditable=true;
                                if(cashCollectionSummaryDTO.isUpload()){
                                 isEditable=false;
                                }

                                tvTotalCenterDue.setText(String.valueOf(cashCollectionSummaryDTO.getOverAllTotalDueCollection()));
                                tvTotalCashCollection.setText(String.valueOf(cashCollectionSummaryDTO.getOverAllTotalCashCollection()));
                                tvTotalSavingsCollection.setText(String.valueOf(cashCollectionSummaryDTO.getOverAllTotalSavingsCollection()));
                                tvOverAllTotalCollection.setText(String.valueOf(cashCollectionSummaryDTO.getOverAllTotalCollection()));

                                centerMeetingCashCollectionSummaryAdapter.setItem(cashCollectionSummaryDTO.getIndividualCenterCollectionList(),
                                        isEditable);

                                if( ! isEditable){
                                    btnEnterDenomination.setVisibility(View.GONE);
                                    btnUpload.setVisibility(View.GONE);
                                }
                                else {
                                    // TODO: Getting Cash Denomination Table
                                    getCashDenominationTable(userId, cmDate, cashCollectionSummaryDTO);
                                }
                            }

                        }else{
                            rvLeadDetails.setVisibility(View.GONE);
                            rlNoLeads.setVisibility(View.VISIBLE);
                        }


                    }
                };
                viewModel.getCashCollectionSummaryDTOLiveData().observe(this, observer);
            }
        } catch (Exception ex) {
            appHelper.getDialogHelper().getLoadingDialog().closeDialog();
            ex.printStackTrace();
        }
    }



    // TODO: GETTING CASH DENOMINATION TABLE
    private void getCashDenominationTable(String userId,Date cmDate,CashCollectionSummaryDTO cashCollectionSummaryDTO ) {
        appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();

        try {
            viewModel.getCashDenominationTable(userId,cmDate);
            if (viewModel.getCashDenominationTableLiveData() != null) {
                Observer observer = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        appHelper.getDialogHelper().getLoadingDialog().closeDialog();
                        CashDenominationTable cashDenominationTable = (CashDenominationTable) o;
                        viewModel.getCashDenominationTableLiveData().removeObserver(this);

                        if(cashDenominationTable !=null
                                && cashDenominationTable.getTotal_amount()==cashCollectionSummaryDTO.getOverAllTotalCollection()){

                            btnEnterDenomination.setVisibility(View.GONE);
                            btnUpload.setVisibility(View.VISIBLE);
                        }else{
                            btnEnterDenomination.setVisibility(View.VISIBLE);
                            btnUpload.setVisibility(View.GONE);
                        }


                    }
                };
                viewModel.getCashDenominationTableLiveData().observe(this, observer);
            }
        } catch (Exception ex) {
            appHelper.getDialogHelper().getLoadingDialog().closeDialog();
            ex.printStackTrace();
        }
    }

    @Override
    public void successCallBack() {
        try{
            // TODO: Getting cash collection summary list
//            Date dateTime = TimestampConverter.toDate(appHelper.getCurrentDateTime(DATE_FORMAT_YYYY_MM_DD));
//            Date dateTime = DateTimeConverter.toDate(appHelper.getCurrentDateTime(DATE_FORMAT_YYYY_MM_DD_HH_MM_SS_SSS2));
//            String cmDateFromCalendar = DUMMY_CENTER_MEETING_DATE; // TODO: Hardcoded date
//            Date cmDate = TimestampConverter.toDate(cmDateFromCalendar);
            Date cmDate = TimestampConverter.toDate(appHelper.getCurrentDateTime(DATE_FORMAT_YYYY_MM_DD));
            if(cmDate !=null) {
                getCashCollectionSummaryList(userId,cmDate);
            }

        }catch (Exception ex){
            ex.printStackTrace();
        }
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
