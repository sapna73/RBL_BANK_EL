package com.saartak.el.activities;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bfil.uilibrary.dialogs.ConfirmationDialog;
import com.saartak.el.BuildConfig;
import com.saartak.el.R;
import com.saartak.el.database.entity.CenterMeetingAttendanceTable;
import com.saartak.el.view_models.DynamicUIViewModel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

import static com.saartak.el.constants.AppConstant.DATE_FORMAT_DD_MM_YYYY2;
import static com.saartak.el.constants.AppConstant.DATE_FORMAT_YYYY_MM_DD;
import static com.saartak.el.constants.AppConstant.MODULE_TYPE_ATTENDANCE;
import static com.saartak.el.constants.AppConstant.MODULE_TYPE_COLLECTION;
import static com.saartak.el.constants.AppConstant.MODULE_TYPE_ELIGIBILITY;
import static com.saartak.el.constants.AppConstant.PARAM_BRANCH_GST_CODE;
import static com.saartak.el.constants.AppConstant.PARAM_BRANCH_ID;
import static com.saartak.el.constants.AppConstant.PARAM_BRANCH_NAME;
import static com.saartak.el.constants.AppConstant.PARAM_CENTER_NAME;
import static com.saartak.el.constants.AppConstant.PARAM_LOAN_TYPE;
import static com.saartak.el.constants.AppConstant.PARAM_MODULE_TYPE;
import static com.saartak.el.constants.AppConstant.PARAM_PRODUCT_ID;
import static com.saartak.el.constants.AppConstant.PARAM_ROLE_NAME;
import static com.saartak.el.constants.AppConstant.PARAM_USER_ID;
import static com.saartak.el.constants.AppConstant.PARAM_USER_NAME;
import static com.saartak.el.constants.AppConstant.getCMDateForTodayCollectionScheduled;
import static com.saartak.el.dynamicui.constants.ParametersConstant.ERROR_MESSAGE_MARK_ATTENDANCE;

public class CenterMeetingHomeActivity extends LOSBaseActivity implements View.OnClickListener {
    private static final String TAG = CenterMeetingHomeActivity.class.getCanonicalName();
    LinearLayout llAttendance, llCollection, llEligibility;
    TextView tvLoanType;
    TextView tvCenterName;
    CardView cv_attendance, cv_collection, cv_eligibility;
    TextView tvAppVersion, tvCurrentDate, tvUserName;
    private String CENTER_NAME;

    @Inject
    public ViewModelProvider.Factory viewModelFactory;
    public DynamicUIViewModel viewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_center_meeting_home);

        tvUserName = (TextView) findViewById(R.id.tv_user_name);
        tvCurrentDate = (TextView) findViewById(R.id.tv_currentdate);
        tvAppVersion = (TextView) findViewById(R.id.tv_app_version);

        tvCenterName = (TextView) findViewById(R.id.tv_center_name_value);

        llAttendance = (LinearLayout) findViewById(R.id.ll_attendance);
        llCollection = (LinearLayout) findViewById(R.id.ll_collection);
        llEligibility = (LinearLayout) findViewById(R.id.ll_eligibility);

        llEligibility.setOnClickListener(this);
        llAttendance.setOnClickListener(this);
        llCollection.setOnClickListener(this);

        cv_attendance = (CardView) findViewById(R.id.cv_attendance);
        cv_collection = (CardView) findViewById(R.id.cv_collection);
        cv_eligibility = (CardView) findViewById(R.id.cv_eligibility);

        tvLoanType = (TextView) findViewById(R.id.tv_product_name);

        userName = getIntent().getStringExtra(PARAM_USER_NAME);
        userId = getIntent().getStringExtra(PARAM_USER_ID);
        branchId = getIntent().getStringExtra(PARAM_BRANCH_ID);
        branchName = getIntent().getStringExtra(PARAM_BRANCH_NAME);
        branchGSTcode = getIntent().getStringExtra(PARAM_BRANCH_GST_CODE);
        productId = getIntent().getStringExtra(PARAM_PRODUCT_ID);
        roleName = getIntent().getStringExtra(PARAM_ROLE_NAME);
        loanType = getIntent().getStringExtra(PARAM_LOAN_TYPE);
        CENTER_NAME = getIntent().getStringExtra(PARAM_CENTER_NAME);

        if (!TextUtils.isEmpty(loanType)) {
//            tvLoanType.setText(loanType);
        }

        if (!TextUtils.isEmpty(CENTER_NAME)) {
            tvCenterName.setText(CENTER_NAME);
        }

        if (!TextUtils.isEmpty(userName)) {
            tvUserName.setText(userName);
        }

        String appVersion = BuildConfig.VERSION_NAME;
        if (!TextUtils.isEmpty(appVersion)) {
            tvAppVersion.setText(getResources().getString(R.string.version) + " " + appVersion);
        }

        String currentDate = appHelper.getCurrentDate(DATE_FORMAT_DD_MM_YYYY2);
        if (!TextUtils.isEmpty(currentDate)) {
            tvCurrentDate.setText(currentDate);
        }

        configureDagger();
        configureViewModel();

        // TODO: Insert Into Staff Activity Table
        insertStaffActivity(viewModel, CENTER_NAME, userId, "CENTER MEETING HOME", 0);
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
            branchName = getIntent().getStringExtra(PARAM_BRANCH_NAME);
            branchGSTcode = getIntent().getStringExtra(PARAM_BRANCH_GST_CODE);
            productId = getIntent().getStringExtra(PARAM_PRODUCT_ID);
            roleName = getIntent().getStringExtra(PARAM_ROLE_NAME);
            loanType = getIntent().getStringExtra(PARAM_LOAN_TYPE);
            CENTER_NAME = getIntent().getStringExtra(PARAM_CENTER_NAME);
            // TODO: Getting cm date
            if(! TextUtils.isEmpty(CENTER_NAME)) {
                getCMDateFromCMTableByCenterName(CENTER_NAME);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void getCMDateFromCMTableByCenterName(String centerName) {
        try {
            appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();
            viewModel.getCMDateFromCMTableByCenterName(centerName);
            if (viewModel.getStringLiveData() != null) {
                Observer observer = new Observer() {
                    @Override
                    public void onChanged(Object o) {
                        appHelper.getDialogHelper().getLoadingDialog().closeDialog();
                        String cmDate = (String) o;
                        viewModel.getStringLiveData().removeObserver(this);

                        String currentDate = getCMDateForTodayCollectionScheduled(appHelper);
                        if (!TextUtils.isEmpty(cmDate) && !TextUtils.isEmpty(currentDate)) {
                            //compare the dates
                            try {
                                SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_YYYY_MM_DD, Locale.US);
                                Date date1  = sdf.parse(cmDate);
                                Date date2 = sdf.parse(currentDate);
                                if(date1!=null && date2!=null && date1.before(date2)) {
                                    cv_attendance.setVisibility(View.GONE);
                                }else {
                                    cv_attendance.setVisibility(View.VISIBLE);
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
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

    @Override
    public void onClick(View v) {
        try {
            switch (v.getId()) {
                case R.id.ll_eligibility:
                    Intent eligibility = new Intent(this, EligibilityActivity.class);
                    eligibility.putExtra(PARAM_LOAN_TYPE, loanType);
                    eligibility.putExtra(PARAM_USER_NAME, userName);
                    eligibility.putExtra(PARAM_BRANCH_ID, branchId);
                    eligibility.putExtra(PARAM_BRANCH_GST_CODE, branchGSTcode);
                    eligibility.putExtra(PARAM_USER_ID, userId);
                    eligibility.putExtra(PARAM_MODULE_TYPE, MODULE_TYPE_ELIGIBILITY);
                    eligibility.putExtra(PARAM_PRODUCT_ID, productId);
                    eligibility.putExtra(PARAM_CENTER_NAME, CENTER_NAME); // TODO: CenterName
                    startActivity(eligibility);
                    break;
                case R.id.ll_attendance:
                    Intent attendance = new Intent(CenterMeetingHomeActivity.this, CenterMeetingAttendanceActivity.class);
                    attendance.putExtra(PARAM_LOAN_TYPE, loanType);
                    attendance.putExtra(PARAM_USER_NAME, userName);
                    attendance.putExtra(PARAM_BRANCH_ID, branchId);
                    attendance.putExtra(PARAM_BRANCH_GST_CODE, branchGSTcode);
                    attendance.putExtra(PARAM_USER_ID, userId);
                    attendance.putExtra(PARAM_MODULE_TYPE, MODULE_TYPE_ATTENDANCE);
                    attendance.putExtra(PARAM_PRODUCT_ID, productId);
                    attendance.putExtra(PARAM_CENTER_NAME, CENTER_NAME); // TODO: CenterName
                    startActivity(attendance);
                    break;
                case R.id.ll_collection:
                    if (cv_attendance.getVisibility() == View.VISIBLE) {
                        // TODO: Checking Attendance
                        checkAttendance();
                    }else {
                        Intent collection = new Intent(CenterMeetingHomeActivity.this, CollectionHomeActivity.class);
                        collection.putExtra(PARAM_LOAN_TYPE, loanType);
                        collection.putExtra(PARAM_USER_NAME, userName);
                        collection.putExtra(PARAM_BRANCH_ID, branchId);
                        collection.putExtra(PARAM_BRANCH_GST_CODE, branchGSTcode);
                        collection.putExtra(PARAM_USER_ID, userId);
                        collection.putExtra(PARAM_MODULE_TYPE, MODULE_TYPE_COLLECTION);
                        collection.putExtra(PARAM_PRODUCT_ID, productId);
                        collection.putExtra(PARAM_CENTER_NAME, CENTER_NAME); // TODO: CenterName
                        startActivity(collection);
                    }
                    break;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {

        // TODO: Insert Into Staff Activity Table
        insertStaffActivity(viewModel, CENTER_NAME, userId, "CENTER MEETING HOME", 1);

        finish();

    }

    private void checkAttendance() {
        try {
            viewModel.checkAttendance(CENTER_NAME);
            if (viewModel.getCenterMeetingAttendanceTableLiveDataList() != null) {
                Observer observer = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        viewModel.getCenterMeetingAttendanceTableLiveDataList().removeObserver(this);
                        List<CenterMeetingAttendanceTable> centerMeetingAttendanceTableList = (List<CenterMeetingAttendanceTable>) o;
                        if (centerMeetingAttendanceTableList != null && centerMeetingAttendanceTableList.size() > 0) {

                            Intent collection = new Intent(CenterMeetingHomeActivity.this, CollectionHomeActivity.class);
                            collection.putExtra(PARAM_LOAN_TYPE, loanType);
                            collection.putExtra(PARAM_USER_NAME, userName);
                            collection.putExtra(PARAM_BRANCH_ID, branchId);
                            collection.putExtra(PARAM_BRANCH_GST_CODE, branchGSTcode);
                            collection.putExtra(PARAM_USER_ID, userId);
                            collection.putExtra(PARAM_MODULE_TYPE, MODULE_TYPE_COLLECTION);
                            collection.putExtra(PARAM_PRODUCT_ID, productId);
                            collection.putExtra(PARAM_CENTER_NAME, CENTER_NAME); // TODO: CenterName
                            startActivity(collection);
                        } else {
                            appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT,
                                    ERROR_MESSAGE_MARK_ATTENDANCE);
                        }

                    }
                };
                viewModel.getCenterMeetingAttendanceTableLiveDataList().observe(this, observer);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    private void INSERT_LOG(String methodName, String message) {
        try {
            if (viewModel != null) {
                viewModel.insertLog(methodName, message, userId, "", CenterMeetingHomeActivity.class.getCanonicalName()
                        , CLIENT_ID, loanType, moduleType);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
