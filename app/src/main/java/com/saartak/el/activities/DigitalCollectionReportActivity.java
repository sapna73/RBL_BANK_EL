package com.saartak.el.activities;

import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bfil.uilibrary.dialogs.ConfirmationDialog;
import com.saartak.el.BuildConfig;
import com.saartak.el.R;
import com.saartak.el.adapter.DigitalCollectionReportAdapter;
import com.saartak.el.constants.AppConstant;
import com.saartak.el.database.entity.InitiatePaymentStatusTable;
import com.saartak.el.view_models.DynamicUIViewModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

import static com.saartak.el.constants.AppConstant.DATE_FORMAT_DD_MM_YYYY2;
import static com.saartak.el.constants.AppConstant.PARAM_BRANCH_GST_CODE;
import static com.saartak.el.constants.AppConstant.PARAM_BRANCH_ID;
import static com.saartak.el.constants.AppConstant.PARAM_CENTER_NAME;
import static com.saartak.el.constants.AppConstant.PARAM_CLIENT_ID;
import static com.saartak.el.constants.AppConstant.PARAM_LOAN_TYPE;
import static com.saartak.el.constants.AppConstant.PARAM_PRODUCT_ID;
import static com.saartak.el.constants.AppConstant.PARAM_USER_ID;
import static com.saartak.el.constants.AppConstant.PARAM_USER_NAME;
import static com.saartak.el.dynamicui.constants.ParametersConstant.ALERT_FOR_INTERNET_CONNECTION_REQUIRED_TO_FETCH_DATA;

public class DigitalCollectionReportActivity extends LOSBaseActivity {

    private static final String TAG = DigitalCollectionReportActivity.class.getCanonicalName();
    RecyclerView rvLeadDetails;
    DigitalCollectionReportAdapter digitalCollectionReportAdapter;
    private String CENTER_NAME;
    private Toolbar toolbar;
    RelativeLayout rlNoLeads;
    TextView tvAppVersion,tvCurrentDate,tvUserName;
    LinearLayout ll_fromDate, ll_view;
    TextView et_fromdate;

    @Inject
    public ViewModelProvider.Factory viewModelFactory;
    public DynamicUIViewModel viewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_digital_collection_report);

        tvUserName = (TextView)findViewById(R.id.tv_user_name);
        tvCurrentDate = (TextView)findViewById(R.id.tv_currentdate);
        tvAppVersion = (TextView)findViewById(R.id.tv_app_version);

        rvLeadDetails = (RecyclerView) findViewById(R.id.rv_lead_details);
        rlNoLeads = (RelativeLayout) findViewById(R.id.rl_no_leads);

        ll_view = (LinearLayout) findViewById(R.id.ll_view);
        ll_fromDate = (LinearLayout) findViewById(R.id.ll_fromDate);
        et_fromdate = (TextView) findViewById(R.id.tv_fromdate);

        toolbar = (Toolbar)findViewById(R.id.toolbar_eligibility);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: Insert Into Staff Activity Table
                insertStaffActivity(viewModel,CENTER_NAME,userId,"Digital Collection Report",1);
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
        CENTER_NAME = getIntent().getStringExtra(PARAM_CENTER_NAME);
        CLIENT_ID = getIntent().getStringExtra(PARAM_CLIENT_ID);

//        if(!TextUtils.isEmpty(CENTER_NAME)){
//            tvCenterName.setText(CENTER_NAME);
//        }
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

        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);
        ll_fromDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get Current Date
                DatePickerDialog datePickerDialog = new DatePickerDialog(DigitalCollectionReportActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int day) {
                                Calendar calendar = Calendar.getInstance();
                                calendar.set(Calendar.YEAR, year);
                                calendar.set(Calendar.MONTH, month);
                                calendar.set(Calendar.DAY_OF_MONTH, day);
//                                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(AppConstant.DATE_FORMAT_YYYYMMDD, Locale.US);
                                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(AppConstant.DATE_FORMAT_YYYY_MM_DD, Locale.US);
                                String selectDate = simpleDateFormat.format(calendar.getTime());

                                et_fromdate.setText(selectDate);

                            }
                        }, mYear, mMonth, mDay);
                DatePicker datePicker = datePickerDialog.getDatePicker();
                Calendar myCalendar = Calendar.getInstance();
//                datePicker.setMinDate(myCalendar.getTimeInMillis() + (24 * 60 * 60 * 1000));
//                datePicker.setMinDate(System.currentTimeMillis());
                datePickerDialog.show();

            }
        });

        ll_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String selectDate = et_fromdate.getText().toString();
                if (!TextUtils.isEmpty(selectDate)) {
                    if (appHelper.isNetworkAvailable()) {
                        getInitiatePaymentStatusFromServer(selectDate);
                    } else {
                        appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, ALERT_FOR_INTERNET_CONNECTION_REQUIRED_TO_FETCH_DATA);
                    }
                } else {
                    appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, "Please select Date From Calendar");
                }
            }
        });


        // TODO: Configure Dagger
        configureDagger();
        // TODO: Configure View Model
        configureViewModel();

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        rvLeadDetails.setLayoutManager(mLayoutManager);
        rvLeadDetails.setItemAnimator(new DefaultItemAnimator());
        rvLeadDetails.setNestedScrollingEnabled(false);

        digitalCollectionReportAdapter = new DigitalCollectionReportAdapter(DigitalCollectionReportActivity.this, new ArrayList<>(),appHelper);
        rvLeadDetails.setAdapter(digitalCollectionReportAdapter);

        // TODO: Insert Into Staff Activity Table
        insertStaffActivity(viewModel,CENTER_NAME,userId,"Digital Collection Report",0);
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
            CLIENT_ID = getIntent().getStringExtra(PARAM_CLIENT_ID);

            String selectDate = et_fromdate.getText().toString();
            if (!TextUtils.isEmpty(selectDate)) {
                getInitiatePaymentStatusFromServer(selectDate);
            }


        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void getInitiatePaymentStatusFromServer(String cmDateFromCalendar) {
        try {
            appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();

//            String cmDateFromCalendar = appHelper.getCurrentDate(DATE_FORMAT_YYYYMMDD);
//            String cmDateFromCalendar = "2020-09-03"; // TODO: Hardcoded date
            cmDateFromCalendar = "2020-09-03"; // TODO: Hardcoded date
            String hardcodedUserid="SIF1009362";
            viewModel.getInitiatePaymentStatusFromServer(cmDateFromCalendar, hardcodedUserid);
            if (viewModel.getInitiatePaymentStatusTableLiveDataList() != null) {
                Observer observer = new Observer() {
                    @Override
                    public void onChanged(Object o) {
                        appHelper.getDialogHelper().getLoadingDialog().closeDialog();
                        List<InitiatePaymentStatusTable> initiatePaymentStatusTableList = (List<InitiatePaymentStatusTable>) o;
                        viewModel.getInitiatePaymentStatusTableLiveDataList().removeObserver(this);

                        if (initiatePaymentStatusTableList!=null && initiatePaymentStatusTableList.size()>0) {
                            rlNoLeads.setVisibility(View.GONE);
                            rvLeadDetails.setVisibility(View.VISIBLE);

                            if (digitalCollectionReportAdapter != null) {
                                digitalCollectionReportAdapter.setItem(initiatePaymentStatusTableList);
                            } else {
                                digitalCollectionReportAdapter = new DigitalCollectionReportAdapter(DigitalCollectionReportActivity.this, initiatePaymentStatusTableList,appHelper);
                                rvLeadDetails.setAdapter(digitalCollectionReportAdapter);
                                digitalCollectionReportAdapter.notifyDataSetChanged();
                            }
                        } else {
                            rvLeadDetails.setVisibility(View.GONE);
                            rlNoLeads.setVisibility(View.VISIBLE);
                        }
                    }
                };
                viewModel.getInitiatePaymentStatusTableLiveDataList().observe(this, observer);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            appHelper.getDialogHelper().getLoadingDialog().closeDialog();
        }
    }


    @Override
    public void onBackPressed() {

        // TODO: Insert Into Staff Activity Table
        insertStaffActivity(viewModel,CENTER_NAME,userId,"Digital Collection Report",1);

        finish();

    }
}