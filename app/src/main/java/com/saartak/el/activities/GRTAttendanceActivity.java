package com.saartak.el.activities;

import android.app.SearchManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.method.DigitsKeyListener;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.view.ActionMode;
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
import com.google.gson.Gson;
import com.saartak.el.BuildConfig;
import com.saartak.el.R;
import com.saartak.el.adapter.GRTAttendanceAdapter;
import com.saartak.el.database.entity.GRTAttendanceTable;
import com.saartak.el.database.entity.GRTTable;
import com.saartak.el.database.entity.RawDataTable;
import com.saartak.el.helper.MyDividerItemDecoration;
import com.saartak.el.view_models.DynamicUIViewModel;

import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

import static com.saartak.el.constants.AppConstant.DATE_FORMAT_DD_MM_YYYY2;
import static com.saartak.el.constants.AppConstant.PARAM_BRANCH_GST_CODE;
import static com.saartak.el.constants.AppConstant.PARAM_BRANCH_ID;
import static com.saartak.el.constants.AppConstant.PARAM_GRT_TABLE_JSON;
import static com.saartak.el.constants.AppConstant.PARAM_CLIENT_ID;
import static com.saartak.el.constants.AppConstant.PARAM_LOAN_TYPE;
import static com.saartak.el.constants.AppConstant.PARAM_PRODUCT_ID;
import static com.saartak.el.constants.AppConstant.PARAM_USER_ID;
import static com.saartak.el.constants.AppConstant.PARAM_USER_NAME;
import static com.saartak.el.dynamicui.constants.ParametersConstant.ERROR_MESSAGE_ATTENDANCE_SUCCESS;
import static com.saartak.el.dynamicui.constants.ParametersConstant.ERROR_MESSAGE_CAPTURE_ATTENDANCE_REASON;
import static com.saartak.el.dynamicui.constants.ParametersConstant.ERROR_MESSAGE_GRT_DETAILS_EMPTY;

public class GRTAttendanceActivity extends LOSBaseActivity {

    private static final String TAG = GRTAttendanceActivity.class.getCanonicalName();
    RecyclerView rvLeadDetails;
    SearchView searchByPhoneNo;
    GRTAttendanceAdapter grtAttendanceAdapter;

    private Toolbar toolbar;

    private ActionMode actionMode;

    TextView tvClientName;
    TextView tvAppVersion,tvCurrentDate;
    String SCREEN_NO;

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView navigationView;
    CoordinatorLayout coordinatorLayout;
    ImageView ivStaffImage;
    RelativeLayout rlNoLeads;
//    TextView tvStaffId, tvStaffName, tvSOBDate;
    TextView tvCenterName,tvCenterId,tvVillageName,tvCgtCycle;
    TextView tv_label_centerName, tv_label_CenterId, tv_label_VillageName, tv_label_CgtCycle;
    Button btnSaveAttendance;
    GRTTable GRT_TABLE;

    List<RawDataTable> allClientRawDataTableList;

    @Inject
    public ViewModelProvider.Factory viewModelFactory;
    public DynamicUIViewModel viewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_attendance);

        rvLeadDetails = (RecyclerView) findViewById(R.id.rv_lead_details);
        rlNoLeads = (RelativeLayout) findViewById(R.id.rl_no_leads);
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

        // TODO: Search view
        ImageView icon = searchByPhoneNo.findViewById(androidx.appcompat.R.id.search_button);
        icon.setImageResource(R.drawable.ic_search_black_24dp);
        icon.setColorFilter(Color.RED);

        tvCenterName=(TextView)findViewById(R.id.tv_center_name_value);
        tvCenterId=(TextView)findViewById(R.id.tv_center_id_value);
        tvVillageName=(TextView)findViewById(R.id.tv_village_name_value);
        tvCgtCycle=(TextView)findViewById(R.id.tv_cgt_cycle_value);
        btnSaveAttendance=(Button)findViewById(R.id.btn_save_attendance);

        tv_label_centerName =(TextView)findViewById(R.id.tv_label_centerName);
        SpannableStringBuilder builder_center_name = new SpannableStringBuilder("Center Name:*");
        builder_center_name.setSpan(new ForegroundColorSpan(Color.RED), builder_center_name.length()-1, builder_center_name.length(),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv_label_centerName.setText(builder_center_name);

        tv_label_CenterId=(TextView)findViewById(R.id.tv_label_CenterId);
        SpannableStringBuilder builder_center_ID = new SpannableStringBuilder("Center Id:*");
        builder_center_ID.setSpan(new ForegroundColorSpan(Color.RED), builder_center_ID.length()-1, builder_center_ID.length(),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv_label_CenterId.setText(builder_center_ID);

        tv_label_VillageName=(TextView)findViewById(R.id.tv_label_VillageName);
        SpannableStringBuilder builder_villageName = new SpannableStringBuilder("Village Name:*");
        builder_villageName.setSpan(new ForegroundColorSpan(Color.RED), builder_villageName.length()-1, builder_villageName.length(),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv_label_VillageName.setText(builder_villageName);

        tv_label_CgtCycle=(TextView)findViewById(R.id.tv_label_CgtCycle);
        SpannableStringBuilder builder_CGT_Cycle = new SpannableStringBuilder("CGT Cycle:*");
        builder_CGT_Cycle.setSpan(new ForegroundColorSpan(Color.RED), builder_CGT_Cycle.length()-1, builder_CGT_Cycle.length(),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv_label_CgtCycle.setText(builder_CGT_Cycle);

        userName = getIntent().getStringExtra(PARAM_USER_NAME);
        userId = getIntent().getStringExtra(PARAM_USER_ID);
        branchId = getIntent().getStringExtra(PARAM_BRANCH_ID);
        branchGSTcode = getIntent().getStringExtra(PARAM_BRANCH_GST_CODE);
        loanType = getIntent().getStringExtra(PARAM_LOAN_TYPE);
        productId = getIntent().getStringExtra(PARAM_PRODUCT_ID);
        CLIENT_ID = getIntent().getStringExtra(PARAM_CLIENT_ID);
        String grtJsonString = getIntent().getStringExtra(PARAM_GRT_TABLE_JSON);
        if (!TextUtils.isEmpty(grtJsonString)) {
            GRT_TABLE = new Gson().fromJson(grtJsonString, GRTTable.class);
            if (GRT_TABLE != null) {
                if( ! TextUtils.isEmpty(GRT_TABLE.getCenterName())) {
                    tvCenterName.setText(GRT_TABLE.getCenterName());
                }
                if( ! TextUtils.isEmpty(GRT_TABLE.getCenterId())) {
                    tvCenterId.setText(GRT_TABLE.getCenterId());
                }
                if( ! TextUtils.isEmpty(GRT_TABLE.getVillageName())) {
                    tvVillageName.setText(GRT_TABLE.getVillageName());
                }
            }
        }

        if (!TextUtils.isEmpty(userName)) {
            tvClientName.setText(userName.toUpperCase());
        }

        String currentDate = appHelper.getCurrentDate(DATE_FORMAT_DD_MM_YYYY2);
        if (!TextUtils.isEmpty(currentDate)) {
            tvCurrentDate.setText(currentDate);
        }

        String appVersion = BuildConfig.VERSION_NAME;
        if (!TextUtils.isEmpty(appVersion)) {
            tvAppVersion.setText(getResources().getString(R.string.version)+" "+appVersion);
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

                if (grtAttendanceAdapter != null && grtAttendanceAdapter.getFilter() != null) {
                    grtAttendanceAdapter.getFilter().filter(query);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                // filter recycler view when text is changed

                if (grtAttendanceAdapter != null && grtAttendanceAdapter.getFilter() != null) {
                    grtAttendanceAdapter.getFilter().filter(query);
                }
                return false;
            }
        });

        btnSaveAttendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(grtAttendanceAdapter !=null && grtAttendanceAdapter.getGRTAttendanceTableList() !=null
                        && grtAttendanceAdapter.getGRTAttendanceTableList().size()>0){
                    if(GRT_TABLE !=null) {
                        boolean isAttendanceReasonCaptured=false;
                        for(GRTAttendanceTable grtAttendanceTable : grtAttendanceAdapter.getGRTAttendanceTableList()){
                                // TODO: Check  Attendance & Reason
                                if(grtAttendanceTable.isAttendance()){
                                    isAttendanceReasonCaptured=true;
                                }
                                else if( ! grtAttendanceTable.isAttendance() && ! TextUtils.isEmpty(grtAttendanceTable.getAbsentReason())){
                                    isAttendanceReasonCaptured=true;
                                }else{
                                    isAttendanceReasonCaptured=false;
                                    break;
                                }
                        }
                        if(isAttendanceReasonCaptured) {
                            saveGRTAttendanceDetails(grtAttendanceAdapter.getGRTAttendanceTableList());
                        }else{
                            appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT,
                                    ERROR_MESSAGE_CAPTURE_ATTENDANCE_REASON);
                        }
                    }else{
                        appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT,
                                ERROR_MESSAGE_GRT_DETAILS_EMPTY);
                    }
                }
            }
        });

        configureDagger();
        configureViewModel();
    }

    private void saveGRTAttendanceDetails(List<GRTAttendanceTable> grtAttendanceTableList) {
        try{
            viewModel.updateGRTTableAttendance(grtAttendanceTableList,GRT_TABLE);
            if (viewModel.getGrtAttendanceTableLiveDataList() != null) {
                Observer observer = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        viewModel.getGrtAttendanceTableLiveDataList().removeObserver(this);
                        appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.SUCCESS,
                                ERROR_MESSAGE_ATTENDANCE_SUCCESS, new ConfirmationDialog.ActionCallback() {
                                    @Override
                                    public void onAction() {
                                        finish();
                                    }
                                });
                    }
                };
                viewModel.getGrtAttendanceTableLiveDataList().observe(this, observer);
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
        if (GRT_TABLE != null) {
            getGRTAttendanceTable();
        }
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
            CLIENT_ID = getIntent().getStringExtra(PARAM_CLIENT_ID);
            String grtJsonString = getIntent().getStringExtra(PARAM_GRT_TABLE_JSON);
            if (!TextUtils.isEmpty(grtJsonString)) {
                GRT_TABLE = new Gson().fromJson(grtJsonString, GRTTable.class);
                if (GRT_TABLE != null) {

                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }



    @Override
    public void onBackPressed() {
        finish();

    }

    public void getGRTAttendanceTable() {
        appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();

        try {
            viewModel.getGRTAttendanceTable(CLIENT_ID); // TODO: Here client id is nothing but center id
            if (viewModel.getGrtAttendanceTableLiveDataList() != null) {
                Observer observer = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        appHelper.getDialogHelper().getLoadingDialog().closeDialog();
                        List<GRTAttendanceTable> grtAttendanceTableList = (List<GRTAttendanceTable>) o;
                        viewModel.getGrtAttendanceTableLiveDataList().removeObserver(this);
                        if (grtAttendanceTableList != null && grtAttendanceTableList.size() > 0) {
                            rlNoLeads.setVisibility(View.GONE);
                            rvLeadDetails.setVisibility(View.VISIBLE);

                            if (grtAttendanceAdapter != null) {
                                grtAttendanceAdapter.setItem(grtAttendanceTableList);
                            } else {
                                grtAttendanceAdapter = new GRTAttendanceAdapter(GRTAttendanceActivity.this,
                                        grtAttendanceTableList, appHelper);

                                rvLeadDetails.setAdapter(grtAttendanceAdapter);
                                grtAttendanceAdapter.notifyDataSetChanged();
                            }

                        } else {
                            rvLeadDetails.setVisibility(View.GONE);
                            rlNoLeads.setVisibility(View.VISIBLE);
                        }
                    }
                };
                viewModel.getGrtAttendanceTableLiveDataList().observe(this, observer);
            }
        }catch (Exception ex){
            appHelper.getDialogHelper().getLoadingDialog().closeDialog();
            ex.printStackTrace();
        }
    }

}
