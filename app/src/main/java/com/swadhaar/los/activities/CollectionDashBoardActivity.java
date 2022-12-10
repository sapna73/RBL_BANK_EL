package com.swadhaar.los.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bfil.uilibrary.dialogs.ConfirmationDialog;
import com.bfil.uilibrary.widgets.customSpinner.SearchableSpinner;
import com.swadhaar.los.BuildConfig;
import com.swadhaar.los.R;
import com.swadhaar.los.database.entity.CenterMeetingAttendanceTable;
import com.swadhaar.los.models.CMCollectionLocalResponseDTO;
import com.swadhaar.los.view_models.DynamicUIViewModel;

import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

import static com.swadhaar.los.constants.AppConstant.DATE_FORMAT_DD_MM_YYYY2;
import static com.swadhaar.los.constants.AppConstant.MODULE_TYPE_COLLECTION;
import static com.swadhaar.los.constants.AppConstant.MODULE_TYPE_DROP_OUT_CUSTOMER;
import static com.swadhaar.los.constants.AppConstant.MODULE_TYPE_TARGET_DETAILS;
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
import static com.swadhaar.los.dynamicui.constants.ParametersConstant.ERROR_MESSAGE_NO_CENTERS_AVAILABLE;
import static com.swadhaar.los.dynamicui.constants.ParametersConstant.ERROR_MESSAGE_SAVE_COLLECTION_OD_COLLECTION;

public class CollectionDashBoardActivity extends LOSBaseActivity implements View.OnClickListener {
    private static final String TAG = CollectionDashBoardActivity.class.getCanonicalName();
    LinearLayout llCenterMeeting,llFetchOtherDay,llCollectionSummary,llTodayCollectionScheduled,llPendingCollection;
    TextView tvLoanType;
    TextView tvAppVersion, tvCurrentDate, tvUserName;
    private AlertDialog notificationDialog;

    @Inject
    public ViewModelProvider.Factory viewModelFactory;
    public DynamicUIViewModel viewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_collection_dash_board);

        tvUserName = (TextView) findViewById(R.id.tv_user_name);
        tvCurrentDate = (TextView) findViewById(R.id.tv_currentdate);
        tvAppVersion = (TextView) findViewById(R.id.tv_app_version);

        tvLoanType = (TextView) findViewById(R.id.tv_product_name);

        llCenterMeeting = (LinearLayout) findViewById(R.id.ll_center_meeting);
        llCollectionSummary = (LinearLayout) findViewById(R.id.ll_collection_summary);
        llFetchOtherDay = (LinearLayout) findViewById(R.id.ll_fetch_other_day);
        llTodayCollectionScheduled = (LinearLayout) findViewById(R.id.ll_today_collection_scheduled);
        llPendingCollection = (LinearLayout) findViewById(R.id.ll_pending_collection);

        llCenterMeeting.setOnClickListener(this);
        llCollectionSummary.setOnClickListener(this);
        llFetchOtherDay.setOnClickListener(this);
        llTodayCollectionScheduled.setOnClickListener(this);
        llPendingCollection.setOnClickListener(this);

        userName = getIntent().getStringExtra(PARAM_USER_NAME);
        userId = getIntent().getStringExtra(PARAM_USER_ID);
        branchId = getIntent().getStringExtra(PARAM_BRANCH_ID);
        branchName = getIntent().getStringExtra(PARAM_BRANCH_NAME);
        branchGSTcode = getIntent().getStringExtra(PARAM_BRANCH_GST_CODE);
        productId = getIntent().getStringExtra(PARAM_PRODUCT_ID);
        roleName = getIntent().getStringExtra(PARAM_ROLE_NAME);
        loanType = getIntent().getStringExtra(PARAM_LOAN_TYPE);

        if (!TextUtils.isEmpty(loanType)) {
//            tvLoanType.setText(loanType);
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
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        try {
            switch (v.getId()) {
                case R.id.ll_center_meeting:
                    getAllCenterNamesFromCenterMeetingTable();
                    break;
                case R.id.ll_fetch_other_day:
                    Intent fetchOtherDay = new Intent(this, FetchOtherDayCollectionActivity.class);
                    fetchOtherDay.putExtra(PARAM_LOAN_TYPE, loanType);
                    fetchOtherDay.putExtra(PARAM_USER_NAME, userName);
                    fetchOtherDay.putExtra(PARAM_BRANCH_ID, branchId);
                    fetchOtherDay.putExtra(PARAM_BRANCH_GST_CODE, branchGSTcode);
                    fetchOtherDay.putExtra(PARAM_USER_ID, userId);
                    fetchOtherDay.putExtra(PARAM_MODULE_TYPE, MODULE_TYPE_DROP_OUT_CUSTOMER);
                    fetchOtherDay.putExtra(PARAM_PRODUCT_ID, productId);
                    startActivity(fetchOtherDay);
                    break;
                case R.id.ll_pending_collection:
                    Intent pendingCollection = new Intent(this, PendingCollectionActivity.class);
                    pendingCollection.putExtra(PARAM_LOAN_TYPE, loanType);
                    pendingCollection.putExtra(PARAM_USER_NAME, userName);
                    pendingCollection.putExtra(PARAM_BRANCH_ID, branchId);
                    pendingCollection.putExtra(PARAM_BRANCH_GST_CODE, branchGSTcode);
                    pendingCollection.putExtra(PARAM_USER_ID, userId);
                    pendingCollection.putExtra(PARAM_MODULE_TYPE, MODULE_TYPE_DROP_OUT_CUSTOMER);
                    pendingCollection.putExtra(PARAM_PRODUCT_ID, productId);
                    startActivity(pendingCollection);
                    break;
                case R.id.ll_collection_summary:
                    Intent collection_summary = new Intent(this, CenterMeetingCollectionSummaryHomeActivity.class);
                    collection_summary.putExtra(PARAM_LOAN_TYPE, loanType);
                    collection_summary.putExtra(PARAM_USER_NAME, userName);
                    collection_summary.putExtra(PARAM_BRANCH_ID, branchId);
                    collection_summary.putExtra(PARAM_BRANCH_GST_CODE, branchGSTcode);
                    collection_summary.putExtra(PARAM_USER_ID, userId);
                    collection_summary.putExtra(PARAM_MODULE_TYPE, MODULE_TYPE_COLLECTION);
                    collection_summary.putExtra(PARAM_PRODUCT_ID, productId);
                    startActivity(collection_summary);
                    break;
                case R.id.ll_today_collection_scheduled:
                    Intent today_scheduled = new Intent(this, TodayCollectionScheduledActivity.class);
                    today_scheduled.putExtra(PARAM_LOAN_TYPE, loanType);
                    today_scheduled.putExtra(PARAM_USER_NAME, userName);
                    today_scheduled.putExtra(PARAM_BRANCH_ID, branchId);
                    today_scheduled.putExtra(PARAM_BRANCH_GST_CODE, branchGSTcode);
                    today_scheduled.putExtra(PARAM_USER_ID, userId);
                    today_scheduled.putExtra(PARAM_MODULE_TYPE, MODULE_TYPE_COLLECTION);
                    today_scheduled.putExtra(PARAM_PRODUCT_ID, productId);
                    startActivity(today_scheduled);
                    break;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    private void getAllCenterNamesFromCenterMeetingTable() {

        appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();

        try {
            viewModel.getAllCenterNamesFromCenterMeetingTable(userId);
            if (viewModel.getStringListLiveData() != null) {
                Observer observer = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        appHelper.getDialogHelper().getLoadingDialog().closeDialog();
                        List<String> stringList = (List<String>) o;
                        viewModel.getStringListLiveData().removeObserver(this);

                        if(stringList !=null && stringList.size()>0){
                            stringList.add(0,"SELECT CENTER");
                            displayCenterMeetingPopUp(new ActionCallback() {
                                @Override
                                public void onAction(String centerName) {
                                    openCenterMeetingHomeActivity(centerName);
                                }
                            },stringList);
                        }else{
                            appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT,ERROR_MESSAGE_NO_CENTERS_AVAILABLE);
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

    public void displayCenterMeetingPopUp(final ActionCallback callback, List<String> centerNameList) {

        notificationDialog = new AlertDialog.Builder(this).create();

        View notificationDialogView = LayoutInflater.from(this).inflate(R.layout.
                popup_choose_center_meeting, null);
        notificationDialog.setView(notificationDialogView);
        notificationDialog.setCancelable(false);

        Button btnSave = (Button) notificationDialogView.findViewById(R.id.btn_popup_yes);
        Button btnCancel = (Button) notificationDialogView.findViewById(R.id.btn_popup_no);
        SearchableSpinner spCenterList=(SearchableSpinner)notificationDialogView.findViewById(R.id.sp_center_list);
        TextView tvCenterMeetingDate=(TextView) notificationDialogView.findViewById(R.id.tv_center_meeting_date);

        if(centerNameList !=null && centerNameList.size()>0){
            ArrayAdapter arrayAdapter = new ArrayAdapter(CollectionDashBoardActivity.this, R.layout.view_spinner_textheight, centerNameList);
            spCenterList.setAdapter(arrayAdapter);
        }

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (callback != null) {
                        closeDialog();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (callback !=  null){

                        if( spCenterList.getSelectedItemPosition()<=0){
                            Toast.makeText(CollectionDashBoardActivity.this,"Please Choose Center",Toast.LENGTH_SHORT).show();
                            return;
                        }
                        closeDialog();
                        callback.onAction(spCenterList.getSelectedItem().toString());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        notificationDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        notificationDialog.show();
    }

    private void openCenterMeetingHomeActivity(String centerName){
        try{
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
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void closeDialog(){
        try {
            if (notificationDialog != null && notificationDialog.isShowing()){
                notificationDialog.dismiss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public interface ActionCallback {
        void onAction(String centerName);
    }

}