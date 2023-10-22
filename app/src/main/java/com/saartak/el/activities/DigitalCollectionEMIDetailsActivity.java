package com.saartak.el.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
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

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bfil.uilibrary.dialogs.ConfirmationDialog;
import com.google.android.material.navigation.NavigationView;
import com.saartak.el.BuildConfig;
import com.saartak.el.R;
import com.saartak.el.adapter.DigitalCollectionEMIDetailsAdapter;
import com.saartak.el.database.entity.CenterCreationTable;
import com.saartak.el.database.entity.InitiatePaymentStatusTable;
import com.saartak.el.database.entity.RawDataTable;
import com.saartak.el.models.EMIDetailsDTO;
import com.saartak.el.models.InitiateResponseDTO;
import com.saartak.el.view_models.DynamicUIViewModel;

import java.util.ArrayList;
import java.util.List;

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
import static com.saartak.el.dynamicui.constants.ParametersConstant.ERROR_MESSAGE_DO_YOU_WANT_TO_PROCEED;
import static com.saartak.el.dynamicui.constants.ParametersConstant.ERROR_MESSAGE_EMI_DETAILS_SAVED_SUCCESSFULLY;
import static com.saartak.el.dynamicui.constants.ParametersConstant.ERROR_MESSAGE_ENTER_MINIMUM_AMOUNT;
import static com.saartak.el.dynamicui.constants.ParametersConstant.FAILURE_RESPONSE_FOR_PAYMENT_STATUS_FETCH_FROM_SERVER;
import static com.saartak.el.dynamicui.constants.ParametersConstant.FAILURE_RESPONSE_MESSAGE;
import static com.saartak.el.dynamicui.constants.ParametersConstant.PAYMENT_SUCCESS_IN_DIGITAL_COLLECTION;
import static com.saartak.el.dynamicui.constants.ParametersConstant.SUCCESS_RESPONSE_FOR_PAYMENT_STATUS_FETCH_FROM_SERVER;
import static com.saartak.el.dynamicui.constants.ParametersConstant.SUCCESS_RESPONSE_MESSAGE;

public class DigitalCollectionEMIDetailsActivity extends LOSBaseActivity implements DigitalCollectionEMIDetailsAdapter.EMIDetailsCallBackInterface {

    private static final String TAG = DigitalCollectionEMIDetailsActivity.class.getCanonicalName();
    RecyclerView rvLeadDetails;
    DigitalCollectionEMIDetailsAdapter digitalCollectionEMIDetailsAdapter;

    private String CENTER_NAME;

    private Toolbar toolbar;

    private ActionMode actionMode;

    String SCREEN_NO;

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView navigationView;
    CoordinatorLayout coordinatorLayout;
    ImageView ivStaffImage;
    RelativeLayout rlNoLeads;
    TextView tvClientName,tvClientId,tvTotalEMI,tvTotalDue,tvTotalCollection;
    TextView tvAppVersion,tvCurrentDate,tvUserName;
    Button btnProceed,btnFetch;
    CenterCreationTable CENTER_CREATION_TABLE;
    List<RawDataTable> allClientRawDataTableList;
    private AlertDialog notificationDialog;

    @Inject
    public ViewModelProvider.Factory viewModelFactory;
    public DynamicUIViewModel viewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_digital_collection_emi_details);

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
                insertStaffActivity(viewModel,CENTER_NAME,userId,"EMI DETAILS",1);

                // back button pressed
                finish();
            }
        });

        tvClientName=(TextView)findViewById(R.id.tv_client_name_value);
        tvClientId=(TextView)findViewById(R.id.tv_client_id_value);
        tvTotalEMI=(TextView)findViewById(R.id.tv_total_emi_value);
        tvTotalDue=(TextView)findViewById(R.id.tv_total_due_value);
        tvTotalCollection=(TextView)findViewById(R.id.tv_total_collection_value);

        btnProceed=(Button)findViewById(R.id.btn_proceed);
        btnFetch=(Button)findViewById(R.id.btn_fetch);

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


        // TODO: Configure Dagger
        configureDagger();
        // TODO: Configure View Model
        configureViewModel();

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        rvLeadDetails.setLayoutManager(mLayoutManager);
        rvLeadDetails.setItemAnimator(new DefaultItemAnimator());
        rvLeadDetails.setNestedScrollingEnabled(false);

        digitalCollectionEMIDetailsAdapter = new DigitalCollectionEMIDetailsAdapter(DigitalCollectionEMIDetailsActivity.this, new ArrayList<>(),appHelper,
                this);
        rvLeadDetails.setAdapter(digitalCollectionEMIDetailsAdapter);

        btnProceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(digitalCollectionEMIDetailsAdapter !=null && digitalCollectionEMIDetailsAdapter.getEmiDetailsDTOList() !=null
                        && digitalCollectionEMIDetailsAdapter.getEmiDetailsDTOList().size()>0){

                    boolean isValid=false;
                    for(EMIDetailsDTO emiDetailsDTO : digitalCollectionEMIDetailsAdapter.getEmiDetailsDTOList()){
                        int totalDue=emiDetailsDTO.getTotalDue();
                        int emiDue=emiDetailsDTO.getEMI();
                        int collection=emiDetailsDTO.getCollection();
                        if(collection>=500){
                            isValid=true;
                        }else if(emiDue==0 && totalDue<500){
                            isValid=true;
                        }else{
                            isValid=false;
                            break;
                        }
                    }

                    if(isValid) {

                        appHelper.getDialogHelper().getConfirmationDialog().showTwoButtons(ERROR_MESSAGE_DO_YOU_WANT_TO_PROCEED, new ConfirmationDialog.ActionCallback() {
                            @Override
                            public void onAction() {
                                saveEMIDetailsCenterMeetingDigitalCollection(digitalCollectionEMIDetailsAdapter.getEmiDetailsDTOList());
                            }
                        });
                    }else{
                        appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT,ERROR_MESSAGE_ENTER_MINIMUM_AMOUNT);
                    }

                }
            }
        });

        btnFetch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (appHelper.isNetworkAvailable()) {
                    getInitiatePaymentStatusFromServer();
                } else {
                    appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, ALERT_FOR_INTERNET_CONNECTION_REQUIRED_TO_FETCH_DATA);
                }
            }
        });

        // TODO: Insert Into Staff Activity Table
        insertStaffActivity(viewModel,CENTER_NAME,userId,"EMI DETAILS",0);
    }

    private void saveEMIDetailsCenterMeetingDigitalCollection(List<EMIDetailsDTO> emiDetailsDTOList) {
        try{
            viewModel.saveEMIDetailsCenterMeetingDigitalCollection(emiDetailsDTOList);
            if (viewModel.getEMIDetailsDTOLiveDataList() != null) {
                Observer observer = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        viewModel.getEMIDetailsDTOLiveDataList().removeObserver(this);

                        appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.SUCCESS,
                                ERROR_MESSAGE_EMI_DETAILS_SAVED_SUCCESSFULLY, new ConfirmationDialog.ActionCallback() {
                                    @Override
                                    public void onAction() {

                                        // TODO: Insert Into Staff Activity Table
                                        insertStaffActivity(viewModel,CENTER_NAME,userId,"EMI DETAILS",1);

                                        finish();
                                    }
                                });
                    }
                };
                viewModel.getEMIDetailsDTOLiveDataList().observe(this, observer);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private void getInitiatePaymentStatusFromServer() {
        try {
            appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();

//            String cmDateFromCalendar = appHelper.getCurrentDate(DATE_FORMAT_YYYYMMDD);
            String cmDateFromCalendar = "2020-09-03"; // TODO: Hardcoded date
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
                            if(digitalCollectionEMIDetailsAdapter !=null && digitalCollectionEMIDetailsAdapter.getEmiDetailsDTOList() !=null
                                    && digitalCollectionEMIDetailsAdapter.getEmiDetailsDTOList().size()>0){
                                for (EMIDetailsDTO emiDetailsDTO:digitalCollectionEMIDetailsAdapter.getEmiDetailsDTOList()) {
                                    if (emiDetailsDTO != null) {
                                        String loanAccountNumberDB=emiDetailsDTO.getLAN();
                                        for (InitiatePaymentStatusTable initiatePaymentStatusTable:initiatePaymentStatusTableList) {
                                            if (initiatePaymentStatusTable != null) {
                                                String loanAccountNumberServer=initiatePaymentStatusTable.getLoanAccountNumber();
                                                if (!TextUtils.isEmpty(loanAccountNumberDB) && !TextUtils.isEmpty(loanAccountNumberServer) && loanAccountNumberDB.equalsIgnoreCase(loanAccountNumberServer)){
                                                    if (!TextUtils.isEmpty(initiatePaymentStatusTable.getPaymentStatus()) && initiatePaymentStatusTable.getPaymentStatus().equalsIgnoreCase(PAYMENT_SUCCESS_IN_DIGITAL_COLLECTION)) {
                                                        emiDetailsDTO.setPaymentStatus(true);
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.SUCCESS,
                                    SUCCESS_RESPONSE_FOR_PAYMENT_STATUS_FETCH_FROM_SERVER, new ConfirmationDialog.ActionCallback() {
                                        @Override
                                        public void onAction() {

                                        }
                                    });
                        } else {
                            appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ERROR,
                                    FAILURE_RESPONSE_FOR_PAYMENT_STATUS_FETCH_FROM_SERVER);
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

            // TODO: Getting EMI Details
            if(! TextUtils.isEmpty(CLIENT_ID)) {
                getEMIDetailsForCollection(CLIENT_ID);
            }


        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    @Override
    public void onBackPressed() {

        // TODO: Insert Into Staff Activity Table
        insertStaffActivity(viewModel,CENTER_NAME,userId,"EMI DETAILS",1);

        finish();

    }


    public void getEMIDetailsForCollection(String customerId) {
        appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();

        try {
//            viewModel.getEMIDetailsForCollection(customerId);
            viewModel.getEMIDetailsForDigitalCollection(customerId);
            if (viewModel.getEMIDetailsDTOLiveDataList() != null) {
                Observer observer = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        appHelper.getDialogHelper().getLoadingDialog().closeDialog();
                        List<EMIDetailsDTO> emiDetailsDTOList = (List<EMIDetailsDTO>) o;
                        viewModel.getEMIDetailsDTOLiveDataList().removeObserver(this);

                        if(emiDetailsDTOList !=null && emiDetailsDTOList.size()>0){
                            rlNoLeads.setVisibility(View.GONE);
                            rvLeadDetails.setVisibility(View.VISIBLE);

                            tvClientName.setText(emiDetailsDTOList.get(0).getCustomerName());
                            tvClientId.setText(emiDetailsDTOList.get(0).getCustomerId());

                            int totalEMI=0;
                            int totalDue=0;
                            int totalColection=0;

                            for(EMIDetailsDTO emiDetailsDTO : emiDetailsDTOList){
                                totalEMI=totalEMI+emiDetailsDTO.getEMI();
                                totalDue=totalDue+emiDetailsDTO.getTotalDue();
                                totalColection=totalColection+emiDetailsDTO.getCollection();
                            }

                            tvTotalEMI.setText(String.valueOf(totalEMI));
                            tvTotalDue.setText(String.valueOf(totalDue));
                            tvTotalCollection.setText(String.valueOf(totalColection));

                            if (digitalCollectionEMIDetailsAdapter != null) {
                                digitalCollectionEMIDetailsAdapter.setItem(emiDetailsDTOList);
                            }

                        }else{
                            rvLeadDetails.setVisibility(View.GONE);
                            rlNoLeads.setVisibility(View.VISIBLE);
                        }


                    }
                };
                viewModel.getEMIDetailsDTOLiveDataList().observe(this, observer);
            }
        } catch (Exception ex) {
            appHelper.getDialogHelper().getLoadingDialog().closeDialog();
            ex.printStackTrace();
        }
    }


    @Override
    public void totalCollectionCallBack(int position, List<EMIDetailsDTO> emiDetailsDTOList) {
        try{
            int totalColection=0;

            for(EMIDetailsDTO emiDetailsDTO : emiDetailsDTOList){
                totalColection=totalColection+emiDetailsDTO.getCollection();
            }
            tvTotalCollection.setText(String.valueOf(totalColection));

        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    public void smsTriggerCallBack(int position,EMIDetailsDTO emiDetailsDTO, List<EMIDetailsDTO> emiDetailsDTOList) {
        try{
            displaySMSTriggerPopUp(new ActionCallback() {
                @Override
                public void onAction(String mobileNumber) {
                    initiateServiceCall(position,emiDetailsDTO,mobileNumber);
//                    if(digitalCollectionEMIDetailsAdapter !=null && digitalCollectionEMIDetailsAdapter.getEmiDetailsDTOList() !=null
//                            && digitalCollectionEMIDetailsAdapter.getEmiDetailsDTOList().size()>0){
//                        EMIDetailsDTO emiDetailsDTO = digitalCollectionEMIDetailsAdapter.getEmiDetailsDTOList().get(position);
//                        if (emiDetailsDTO != null) {
//                            emiDetailsDTO.setAccountCreated(true);
//                            emiDetailsDTO.setSmsTriggered(true);
//                            emiDetailsDTO.setPaymentStatus(false);
//                            emiDetailsDTO.setRequestId("12308"); // TODO: hard coded
//                        }
//                    }
                }
            });
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void initiateServiceCall(int position,EMIDetailsDTO emiDetailsDTO,String mobileNumber) {
        try {
            appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();

            viewModel.initiateServiceCall(emiDetailsDTO,mobileNumber,userId);
            if (viewModel.getInitiateResponseDTOLiveData() != null) {
                Observer observer = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        appHelper.getDialogHelper().getLoadingDialog().closeDialog();
                        InitiateResponseDTO initiateResponseDTO = (InitiateResponseDTO) o;
                        Log.d(TAG, "getEligibilityDataFromServer ==>  " + initiateResponseDTO);
                        viewModel.getInitiateResponseDTOLiveData().removeObserver(this);
                        if (initiateResponseDTO!=null && initiateResponseDTO.getApiResponse()!=null && initiateResponseDTO.getApiResponse().getAccountSmsResponse()!=null && initiateResponseDTO.getApiResponse().getAccountSmsResponse().getStatus().equalsIgnoreCase("1")) {
                            String successMsg=SUCCESS_RESPONSE_MESSAGE;
                            if (!TextUtils.isEmpty(initiateResponseDTO.getApiResponse().getAccountSmsResponse().getMessage())) {
                                successMsg=initiateResponseDTO.getApiResponse().getAccountSmsResponse().getMessage();
                            }
                            appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.SUCCESS,
                                    successMsg, new ConfirmationDialog.ActionCallback() {
                                        @Override
                                        public void onAction() {
                                            if(digitalCollectionEMIDetailsAdapter !=null && digitalCollectionEMIDetailsAdapter.getEmiDetailsDTOList() !=null
                                                    && digitalCollectionEMIDetailsAdapter.getEmiDetailsDTOList().size()>0){
                                                EMIDetailsDTO emiDetailsDTO = digitalCollectionEMIDetailsAdapter.getEmiDetailsDTOList().get(position);
                                                if (emiDetailsDTO != null) {
                                                    emiDetailsDTO.setAccountCreated(true);
                                                    emiDetailsDTO.setSmsTriggered(true);
                                                    emiDetailsDTO.setPaymentStatus(false);
                                                    emiDetailsDTO.setRequestId("12308"); // TODO: hard coded
                                                }
                                            }
                                        }
                                    });
                        } else {
                            String errMsg=FAILURE_RESPONSE_MESSAGE;
                            if (initiateResponseDTO!=null && initiateResponseDTO.getApiResponse()!=null && initiateResponseDTO.getApiResponse().getAccountSmsResponse()!=null && !TextUtils.isEmpty(initiateResponseDTO.getApiResponse().getAccountSmsResponse().getMessage())) {
                                errMsg=initiateResponseDTO.getApiResponse().getAccountSmsResponse().getMessage();
                            }

                            appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ERROR,
                                    errMsg);
                        }
                    }
                };
                viewModel.getInitiateResponseDTOLiveData().observe(this, observer);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            appHelper.getDialogHelper().getLoadingDialog().closeDialog();
        }
    }

    public void displaySMSTriggerPopUp(final ActionCallback callback) {

        notificationDialog = new AlertDialog.Builder(this).create();

        View notificationDialogView = LayoutInflater.from(this).inflate(R.layout.
                popup_enter_mobile_number, null);
        notificationDialog.setView(notificationDialogView);
        notificationDialog.setCancelable(false);

        Button btnSave = (Button) notificationDialogView.findViewById(R.id.btn_popup_yes);
        Button btnCancel = (Button) notificationDialogView.findViewById(R.id.btn_popup_no);

        EditText mobile = (EditText) notificationDialogView.findViewById(R.id.et_mobile);

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

                        if(TextUtils.isEmpty(mobile.getText().toString()) || (!TextUtils.isEmpty(mobile.getText().toString()) && mobile.getText().length()<10)){
                            Toast.makeText(DigitalCollectionEMIDetailsActivity.this,"Please Enter 10 Digits Mobile Number",Toast.LENGTH_SHORT).show();
                            return;
                        }
                        closeDialog();
                        callback.onAction(mobile.getText().toString());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        notificationDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        notificationDialog.show();
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