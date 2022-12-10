package com.swadhaar.los.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
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
import com.swadhaar.los.BuildConfig;
import com.swadhaar.los.R;
import com.swadhaar.los.database.entity.CenterMeetingAttendanceTable;
import com.swadhaar.los.models.CMCollectionLocalResponseDTO;
import com.swadhaar.los.view_models.DynamicUIViewModel;

import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

import static com.swadhaar.los.constants.AppConstant.DATE_FORMAT_DD_MM_YYYY2;
import static com.swadhaar.los.constants.AppConstant.MODULE_TYPE_ATTENDANCE;
import static com.swadhaar.los.constants.AppConstant.MODULE_TYPE_COLLECTION;
import static com.swadhaar.los.constants.AppConstant.MODULE_TYPE_ELIGIBILITY;
import static com.swadhaar.los.constants.AppConstant.PARAM_BRANCH_GST_CODE;
import static com.swadhaar.los.constants.AppConstant.PARAM_BRANCH_ID;
import static com.swadhaar.los.constants.AppConstant.PARAM_BRANCH_NAME;
import static com.swadhaar.los.constants.AppConstant.PARAM_CENTER_NAME;
import static com.swadhaar.los.constants.AppConstant.PARAM_LOAN_TYPE;
import static com.swadhaar.los.constants.AppConstant.PARAM_MODULE_TYPE;
import static com.swadhaar.los.constants.AppConstant.PARAM_PRODUCT_ID;
import static com.swadhaar.los.constants.AppConstant.PARAM_ROLE_NAME;
import static com.swadhaar.los.constants.AppConstant.PARAM_USER_ID;
import static com.swadhaar.los.constants.AppConstant.PARAM_USER_NAME;
import static com.swadhaar.los.dynamicui.constants.ParametersConstant.ERROR_MESSAGE_MARK_ATTENDANCE;
import static com.swadhaar.los.dynamicui.constants.ParametersConstant.ERROR_MESSAGE_SAVE_COLLECTION_OD_COLLECTION;

public class CollectionHomeActivity extends LOSBaseActivity implements View.OnClickListener {
    private static final String TAG = CollectionHomeActivity.class.getCanonicalName();
    LinearLayout llCollection, llODCollection, llDigitalCollection;
    TextView tvLoanType;
    TextView tvCenterName;
    CardView cv_collection, cv_od_collection, cv_digital_collection;
    TextView tvAppVersion, tvCurrentDate, tvUserName;
    private String CENTER_NAME;

    @Inject
    public ViewModelProvider.Factory viewModelFactory;
    public DynamicUIViewModel viewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_collection_home);

        tvUserName = (TextView) findViewById(R.id.tv_user_name);
        tvCurrentDate = (TextView) findViewById(R.id.tv_currentdate);
        tvAppVersion = (TextView) findViewById(R.id.tv_app_version);

        tvCenterName = (TextView) findViewById(R.id.tv_center_name_value);

        llCollection = (LinearLayout) findViewById(R.id.ll_collection);
        llODCollection = (LinearLayout) findViewById(R.id.ll_od_collection);
        llDigitalCollection = (LinearLayout) findViewById(R.id.ll_digital_collection);

        llCollection.setOnClickListener(this);
        llODCollection.setOnClickListener(this);
        llDigitalCollection.setOnClickListener(this);

        cv_collection = (CardView) findViewById(R.id.cv_collection);
        cv_od_collection = (CardView) findViewById(R.id.cv_od_collection);
        cv_digital_collection = (CardView) findViewById(R.id.cv_digital_collection);

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
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        try {
            switch (v.getId()) {
                case R.id.ll_collection:
                    Intent collection = new Intent(CollectionHomeActivity.this, CenterMeetingCollectionActivity.class);
                    collection.putExtra(PARAM_LOAN_TYPE, loanType);
                    collection.putExtra(PARAM_USER_NAME, userName);
                    collection.putExtra(PARAM_BRANCH_ID, branchId);
                    collection.putExtra(PARAM_BRANCH_GST_CODE, branchGSTcode);
                    collection.putExtra(PARAM_USER_ID, userId);
                    collection.putExtra(PARAM_MODULE_TYPE, MODULE_TYPE_COLLECTION);
                    collection.putExtra(PARAM_PRODUCT_ID, productId);
                    collection.putExtra(PARAM_CENTER_NAME, CENTER_NAME); // TODO: CenterName
                    startActivity(collection);
//                    // TODO: Checking Attendance
//                    checkAttendance();
                    break;
                case R.id.ll_od_collection:
                    // TODO: Checking Attendance
                    checkAttendanceForODCollection();
                    break;
                case R.id.ll_digital_collection:
                    // TODO: Checking center meeting collection
                    checkCenterMeetingCollection();
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

                            Intent collection = new Intent(CollectionHomeActivity.this, CenterMeetingCollectionActivity.class);
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

    private void checkAttendanceForODCollection() {
        try {
            viewModel.checkAttendance(CENTER_NAME);
            if (viewModel.getCenterMeetingAttendanceTableLiveDataList() != null) {
                Observer observer = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        viewModel.getCenterMeetingAttendanceTableLiveDataList().removeObserver(this);
                        List<CenterMeetingAttendanceTable> centerMeetingAttendanceTableList = (List<CenterMeetingAttendanceTable>) o;
                        if (centerMeetingAttendanceTableList != null && centerMeetingAttendanceTableList.size() > 0) {

                            Intent collection = new Intent(CollectionHomeActivity.this, CenterMeetingODCollectionActivity.class);
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

    private void checkCenterMeetingCollection() {
        try {
            viewModel.checkCenterMeetingCollection(CENTER_NAME);
            if (viewModel.getCMCollectionLocalResponseDTOLiveData() != null) {
                Observer observer = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        viewModel.getCMCollectionLocalResponseDTOLiveData().removeObserver(this);
                        CMCollectionLocalResponseDTO cmCollectionLocalResponseDTO = (CMCollectionLocalResponseDTO) o;
                        if (cmCollectionLocalResponseDTO != null && cmCollectionLocalResponseDTO.isValid()
                                && !TextUtils.isEmpty(cmCollectionLocalResponseDTO.getResponse())) {
                            Intent collection_summary = new Intent(CollectionHomeActivity.this, DigitalCollectionActivity.class);
                            collection_summary.putExtra(PARAM_LOAN_TYPE, loanType);
                            collection_summary.putExtra(PARAM_USER_NAME, userName);
                            collection_summary.putExtra(PARAM_BRANCH_ID, branchId);
                            collection_summary.putExtra(PARAM_BRANCH_GST_CODE, branchGSTcode);
                            collection_summary.putExtra(PARAM_USER_ID, userId);
                            collection_summary.putExtra(PARAM_MODULE_TYPE, MODULE_TYPE_COLLECTION);
                            collection_summary.putExtra(PARAM_PRODUCT_ID, productId);
                            collection_summary.putExtra(PARAM_CENTER_NAME, CENTER_NAME); // TODO: CenterName
                            startActivity(collection_summary);

                        } else {
                            appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT,
                                    ERROR_MESSAGE_SAVE_COLLECTION_OD_COLLECTION);
                        }

                    }
                };
                viewModel.getCMCollectionLocalResponseDTOLiveData().observe(this, observer);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    private void INSERT_LOG(String methodName, String message) {
        try {
            if (viewModel != null) {
                viewModel.insertLog(methodName, message, userId, "", CollectionHomeActivity.class.getCanonicalName()
                        , CLIENT_ID, loanType, moduleType);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}