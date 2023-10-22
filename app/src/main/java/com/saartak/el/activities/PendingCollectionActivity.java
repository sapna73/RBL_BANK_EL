package com.saartak.el.activities;

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
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.saartak.el.BuildConfig;
import com.saartak.el.R;
import com.saartak.el.adapter.FTOverDueCMAdapter;
import com.saartak.el.adapter.OverDueCMAdapter;
import com.saartak.el.database.entity.CenterCreationTable;
import com.saartak.el.database.entity.RawDataTable;
import com.saartak.el.models.FTOverDueCMDTO;
import com.saartak.el.models.OverDueCMDTO;
import com.saartak.el.view_models.DynamicUIViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

import static com.saartak.el.constants.AppConstant.DATE_FORMAT_DD_MM_YYYY2;
import static com.saartak.el.constants.AppConstant.PARAM_BRANCH_GST_CODE;
import static com.saartak.el.constants.AppConstant.PARAM_BRANCH_ID;
import static com.saartak.el.constants.AppConstant.PARAM_LOAN_TYPE;
import static com.saartak.el.constants.AppConstant.PARAM_PRODUCT_ID;
import static com.saartak.el.constants.AppConstant.PARAM_USER_ID;
import static com.saartak.el.constants.AppConstant.PARAM_USER_NAME;

public class PendingCollectionActivity extends LOSBaseActivity {

    private static final String TAG = PendingCollectionActivity.class.getCanonicalName();
    RecyclerView rvLeadDetails;
    OverDueCMAdapter overDueCMAdapter;

    RecyclerView rvFtOdDetails;
    RelativeLayout rlNoFtOd;
    FTOverDueCMAdapter ftOverDueCMAdapter;

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
    CenterCreationTable CENTER_CREATION_TABLE;
    List<RawDataTable> allClientRawDataTableList;

    @Inject
    public ViewModelProvider.Factory viewModelFactory;
    public DynamicUIViewModel viewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_pending_collection);

        tvUserName = (TextView)findViewById(R.id.tv_user_name);
        tvCurrentDate = (TextView)findViewById(R.id.tv_currentdate);
        tvAppVersion = (TextView)findViewById(R.id.tv_app_version);

        rvLeadDetails = (RecyclerView) findViewById(R.id.rv_lead_details);
        rlNoLeads = (RelativeLayout) findViewById(R.id.rl_no_leads);

        rvFtOdDetails = (RecyclerView) findViewById(R.id.rv_ftod_details);
        rlNoFtOd = (RelativeLayout) findViewById(R.id.rl_no_ftod);
        toolbar = (Toolbar)findViewById(R.id.toolbar_eligibility);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // TODO: Insert Into Staff Activity Table
//                insertStaffActivity(viewModel,CENTER_NAME,userId,"COLLECTION",1);

                // back button pressed
                finish();
            }
        });

        tvCenterName=(TextView)findViewById(R.id.tv_center_name_value);

        userName = getIntent().getStringExtra(PARAM_USER_NAME);
        userId = getIntent().getStringExtra(PARAM_USER_ID);
        branchId = getIntent().getStringExtra(PARAM_BRANCH_ID);
        branchGSTcode = getIntent().getStringExtra(PARAM_BRANCH_GST_CODE);
        loanType = getIntent().getStringExtra(PARAM_LOAN_TYPE);
        productId = getIntent().getStringExtra(PARAM_PRODUCT_ID);

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

        overDueCMAdapter = new OverDueCMAdapter(PendingCollectionActivity.this,
                new ArrayList<>(),appHelper);
        rvLeadDetails.setAdapter(overDueCMAdapter);

        RecyclerView.LayoutManager mLayoutManagerFtOd = new LinearLayoutManager(getApplicationContext());
        rvFtOdDetails.setLayoutManager(mLayoutManagerFtOd);
        rvFtOdDetails.setItemAnimator(new DefaultItemAnimator());
        rvFtOdDetails.setNestedScrollingEnabled(false);

        ftOverDueCMAdapter = new FTOverDueCMAdapter(PendingCollectionActivity.this,
                new ArrayList<>(),appHelper);
        rvFtOdDetails.setAdapter(ftOverDueCMAdapter);

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

            getCMDetailsFromServerForPendingOD();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    @Override
    public void onBackPressed() {

        // TODO: Insert Into Staff Activity Table
//        insertStaffActivity(viewModel,CENTER_NAME,userId,"COLLECTION",1);
        finish();

    }

    private void getCMDetailsFromServerForPendingOD() {
        try {
            appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();
            viewModel.getCMDetailsFromServerForPendingOD(userId);
            if (viewModel.getOverDueCMDTOListLiveData() != null) {
                Observer observer = new Observer() {
                    @Override
                    public void onChanged(Object o) {
                        appHelper.getDialogHelper().getLoadingDialog().closeDialog();
                        List<OverDueCMDTO> overDueCMDTOList = (List<OverDueCMDTO>) o;
                        viewModel.getOverDueCMDTOListLiveData().removeObserver(this);

                        if(overDueCMDTOList !=null && overDueCMDTOList.size()>0){
                            rlNoLeads.setVisibility(View.GONE);
                            rvLeadDetails.setVisibility(View.VISIBLE);

                            if (overDueCMAdapter != null) {
                                overDueCMAdapter.setItem(overDueCMDTOList);
                            }

                            getCMDetailsFromServerForPendingFTOD();

                        }else{
                            rvLeadDetails.setVisibility(View.GONE);
                            rlNoLeads.setVisibility(View.VISIBLE);

                            getCMDetailsFromServerForPendingFTOD();
                        }

                    }
                };
                viewModel.getOverDueCMDTOListLiveData().observe(this, observer);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            appHelper.getDialogHelper().getLoadingDialog().closeDialog();
        }
    }

    private void getCMDetailsFromServerForPendingFTOD() {
        try {
            appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();
            viewModel.getCMDetailsFromServerForPendingFTOD(userId);
            if (viewModel.getFTOverDueCMDTOListLiveData() != null) {
                Observer observer = new Observer() {
                    @Override
                    public void onChanged(Object o) {
                        appHelper.getDialogHelper().getLoadingDialog().closeDialog();
                        List<FTOverDueCMDTO> overDueCMDTOList = (List<FTOverDueCMDTO>) o;
                        viewModel.getFTOverDueCMDTOListLiveData().removeObserver(this);

                        if(overDueCMDTOList !=null && overDueCMDTOList.size()>0){
                            rlNoFtOd.setVisibility(View.GONE);
                            rvFtOdDetails.setVisibility(View.VISIBLE);

                            if (ftOverDueCMAdapter != null) {
                                ftOverDueCMAdapter.setItem(overDueCMDTOList);
                            }

                        }else{
                            rvFtOdDetails.setVisibility(View.GONE);
                            rlNoFtOd.setVisibility(View.VISIBLE);
                        }

                    }
                };
                viewModel.getFTOverDueCMDTOListLiveData().observe(this, observer);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            appHelper.getDialogHelper().getLoadingDialog().closeDialog();
        }
    }
}