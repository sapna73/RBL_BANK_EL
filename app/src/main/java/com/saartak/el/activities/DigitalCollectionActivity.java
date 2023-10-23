package com.saartak.el.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.view.ActionMode;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bfil.uilibrary.dialogs.ConfirmationDialog;
import com.google.android.material.navigation.NavigationView;
import com.saartak.el.BuildConfig;
import com.saartak.el.R;
import com.saartak.el.adapter.DigitalCollectionAdapter;
import com.saartak.el.database.entity.CenterCreationTable;
import com.saartak.el.database.entity.RawDataTable;
import com.saartak.el.models.CMCollectionLocalResponseDTO;
import com.saartak.el.view_models.DynamicUIViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

import static com.saartak.el.constants.AppConstant.DATE_FORMAT_DD_MM_YYYY2;
import static com.saartak.el.constants.AppConstant.PARAM_BRANCH_GST_CODE;
import static com.saartak.el.constants.AppConstant.PARAM_BRANCH_ID;
import static com.saartak.el.constants.AppConstant.PARAM_CENTER_NAME;
import static com.saartak.el.constants.AppConstant.PARAM_LOAN_TYPE;
import static com.saartak.el.constants.AppConstant.PARAM_PRODUCT_ID;
import static com.saartak.el.constants.AppConstant.PARAM_USER_ID;
import static com.saartak.el.constants.AppConstant.PARAM_USER_NAME;
import static com.saartak.el.dynamicui.constants.ParametersConstant.ERROR_MESSAGE_COLLECTION_FAILED;
import static com.saartak.el.dynamicui.constants.ParametersConstant.ERROR_MESSAGE_DO_YOU_WANT_TO_PROCEED;

public class DigitalCollectionActivity extends LOSBaseActivity {

    private static final String TAG = DigitalCollectionActivity.class.getCanonicalName();
    RecyclerView rvLeadDetails;
    DigitalCollectionAdapter digitalCollectionAdapter;

    public String CENTER_NAME;

    private Toolbar toolbar;

    private ActionMode actionMode;

    String SCREEN_NO;

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView navigationView;
    CoordinatorLayout coordinatorLayout;
    ImageView ivStaffImage;
    RelativeLayout rlNoLeads;
    TextView tvCenterName;
    TextView tvAppVersion,tvCurrentDate,tvUserName;
    Button btnProceed;
    CenterCreationTable CENTER_CREATION_TABLE;
    List<RawDataTable> allClientRawDataTableList;

    @Inject
    public ViewModelProvider.Factory viewModelFactory;
    public DynamicUIViewModel viewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_digital_collection);

        tvUserName = (TextView)findViewById(R.id.tv_user_name);
        tvCurrentDate = (TextView)findViewById(R.id.tv_currentdate);
        tvAppVersion = (TextView)findViewById(R.id.tv_app_version);

        rvLeadDetails = (RecyclerView) findViewById(R.id.rv_lead_details);
        rlNoLeads = (RelativeLayout) findViewById(R.id.rl_no_leads);
        toolbar = (Toolbar)findViewById(R.id.toolbar_eligibility);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // TODO: Insert Into Staff Activity Table
                insertStaffActivity(viewModel,CENTER_NAME,userId,"COLLECTION",1);

                // back button pressed
                finish();
            }
        });

        tvCenterName=(TextView)findViewById(R.id.tv_center_name_value);
        btnProceed=(Button)findViewById(R.id.btn_proceed);

        userName = getIntent().getStringExtra(PARAM_USER_NAME);
        userId = getIntent().getStringExtra(PARAM_USER_ID);
        branchId = getIntent().getStringExtra(PARAM_BRANCH_ID);
        branchGSTcode = getIntent().getStringExtra(PARAM_BRANCH_GST_CODE);
        loanType = getIntent().getStringExtra(PARAM_LOAN_TYPE);
        productId = getIntent().getStringExtra(PARAM_PRODUCT_ID);
        CENTER_NAME = getIntent().getStringExtra(PARAM_CENTER_NAME);

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

        digitalCollectionAdapter = new DigitalCollectionAdapter(DigitalCollectionActivity.this,
                new ArrayList<>(),appHelper,
                viewModel,CENTER_NAME,true);
        rvLeadDetails.setAdapter(digitalCollectionAdapter);

        btnProceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(digitalCollectionAdapter !=null && digitalCollectionAdapter.getGroupList() !=null
                        && digitalCollectionAdapter.getGroupList().size()>0){

                    appHelper.getDialogHelper().getConfirmationDialog().showTwoButtons(ERROR_MESSAGE_DO_YOU_WANT_TO_PROCEED, new ConfirmationDialog.ActionCallback() {
                        @Override
                        public void onAction() {
                            // TODO: Saving Center Meeting Details
                            saveCenterMeetingDigitalCollection();
                        }
                    });


                }
            }
        });

    }

    private void saveCenterMeetingDigitalCollection() {
        try{
            viewModel.saveCenterMeetingDigitalCollection(CENTER_NAME);
            if (viewModel.getCMCollectionLocalResponseDTOLiveData() != null) {
                Observer observer = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        viewModel.getCMCollectionLocalResponseDTOLiveData().removeObserver(this);
                        CMCollectionLocalResponseDTO cmCollectionLocalResponseDTO=(CMCollectionLocalResponseDTO)o;
                        if(cmCollectionLocalResponseDTO !=null && cmCollectionLocalResponseDTO.isValid()
                                && ! TextUtils.isEmpty(cmCollectionLocalResponseDTO.getResponse())) {
                            appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.SUCCESS,
                                    cmCollectionLocalResponseDTO.getResponse(), new ConfirmationDialog.ActionCallback() {
                                        @Override
                                        public void onAction() {
                                            //  TODO: Insert Into Staff Activity Table
                                            insertStaffActivity(viewModel,CENTER_NAME,userId,"COLLECTION",1);

                                            finish();
                                        }
                                    });
                        }else{
                            String response=ERROR_MESSAGE_COLLECTION_FAILED;
                            if(cmCollectionLocalResponseDTO !=null && ! TextUtils.isEmpty(cmCollectionLocalResponseDTO.getResponse())){
                                response=cmCollectionLocalResponseDTO.getResponse();
                            }
                            // TODO: Failure case
                            appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT,
                                    response, new ConfirmationDialog.ActionCallback() {
                                        @Override
                                        public void onAction() {

                                        }
                                    });
                        }
                    }
                };
                viewModel.getCMCollectionLocalResponseDTOLiveData().observe(this, observer);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }


        // TODO: Insert Into Staff Activity Table
        insertStaffActivity(viewModel,CENTER_NAME,userId,"COLLECTION",0);
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
            CENTER_NAME = getIntent().getStringExtra(PARAM_CENTER_NAME);

            // TODO: Getting group list tvName center meeting table
//            if(! TextUtils.isEmpty(CENTER_NAME)) {
//                getGroupListForCollection(userId,CENTER_NAME);
//            }

            // TODO: Getting cash collection summary list
//            Date dateTime = TimestampConverter.toDate(appHelper.getCurrentDateTime(DATE_FORMAT_YYYY_MM_DD));
//            if(dateTime !=null) {
//                getCashCollectionSummaryList(userId,dateTime);
//            }

////            String cmDateFromCalendar = DUMMY_CENTER_MEETING_DATE; // TODO: Hardcoded date
////            Date cmDate = TimestampConverter.toDate(cmDateFromCalendar);
//            Date cmDate = TimestampConverter.toDate(appHelper.getCurrentDateTime(DATE_FORMAT_YYYY_MM_DD));
//            if(cmDate !=null) {
//                getCashCollectionSummaryList(userId,cmDate);
//            }

            // TODO: Getting group list from center meeting table
            getGroupListForCollection(userId,CENTER_NAME);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    @Override
    public void onBackPressed() {

        // TODO: Insert Into Staff Activity Table
        insertStaffActivity(viewModel,CENTER_NAME,userId,"COLLECTION",1);

        finish();

    }


    // TODO: GETTING GROUP LIST FOR COLLECTION
    public void getGroupListForCollection(String userId,String centerName) {
        appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();

        try {
            viewModel.getGroupListForCollectionByIsDigitalPayment(userId,centerName, true);
            if (viewModel.getStringListLiveData() != null) {
                Observer observer = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        appHelper.getDialogHelper().getLoadingDialog().closeDialog();
                        List<String> groupList = (List<String>) o;
                        viewModel.getStringListLiveData().removeObserver(this);

                        if(groupList !=null && groupList.size()>0){
                            rlNoLeads.setVisibility(View.GONE);
                            rvLeadDetails.setVisibility(View.VISIBLE);

                            if (digitalCollectionAdapter != null) {
                                digitalCollectionAdapter.setItem(groupList);
                            }

                        }else{
                            rvLeadDetails.setVisibility(View.GONE);
                            rlNoLeads.setVisibility(View.VISIBLE);
                        }


                    }
                };
                viewModel.getStringListLiveData().observe(this, observer);
            }
        } catch (Exception ex) {
            appHelper.getDialogHelper().getLoadingDialog().closeDialog();
            ex.printStackTrace();
        }
    }


}
