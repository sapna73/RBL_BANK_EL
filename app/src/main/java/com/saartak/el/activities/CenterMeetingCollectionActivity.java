package com.saartak.el.activities;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

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

import com.bfil.uilibrary.dialogs.ConfirmationDialog;
import com.bfil.uilibrary.widgets.customSpinner.SearchableSpinner;
import com.google.android.material.navigation.NavigationView;
import com.saartak.el.BuildConfig;
import com.saartak.el.R;
import com.saartak.el.adapter.CenterMeetingCollectionAdapter;
import com.saartak.el.database.entity.CenterCreationTable;
import com.saartak.el.database.entity.CenterMeetingCollectionTable;
import com.saartak.el.database.entity.RawDataTable;
import com.saartak.el.models.CMCollectionLocalResponseDTO;
import com.saartak.el.models.OTPTriggerDTO;
import com.saartak.el.models.OTPTriggerRequestDTO;
import com.saartak.el.models.OTPTriggerRequestStringDTO;
import com.saartak.el.models.OTPTriggerResponseDTO;
import com.saartak.el.view_models.DynamicUIViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

import static com.saartak.el.constants.AppConstant.DAILY_COLLECTION_SMS_FORMAT;
import static com.saartak.el.constants.AppConstant.DATE_FORMAT_DDMMYYYYHHMM;
import static com.saartak.el.constants.AppConstant.DATE_FORMAT_DD_MM_YYYY2;
import static com.saartak.el.constants.AppConstant.MODULE_TYPE_COLLECTION;
import static com.saartak.el.constants.AppConstant.MODULE_TYPE_TARGET_DETAILS;
import static com.saartak.el.constants.AppConstant.PARAM_BRANCH_GST_CODE;
import static com.saartak.el.constants.AppConstant.PARAM_BRANCH_ID;
import static com.saartak.el.constants.AppConstant.PARAM_CENTER_NAME;
import static com.saartak.el.constants.AppConstant.PARAM_LOAN_TYPE;
import static com.saartak.el.constants.AppConstant.PARAM_MODULE_TYPE;
import static com.saartak.el.constants.AppConstant.PARAM_PRODUCT_ID;
import static com.saartak.el.constants.AppConstant.PARAM_USER_ID;
import static com.saartak.el.constants.AppConstant.PARAM_USER_NAME;
import static com.saartak.el.dynamicui.constants.ParametersConstant.ERROR_MESSAGE_COLLECTION_FAILED;
import static com.saartak.el.dynamicui.constants.ParametersConstant.ERROR_MESSAGE_DO_YOU_WANT_TO_PROCEED;
import static com.saartak.el.dynamicui.constants.ParametersConstant.COLLECTION_TYPE_REGULAR;
import static com.saartak.el.dynamicui.constants.ParametersConstant.ERROR_MESSAGE_NO_CLIENTS_AVAILABLE;

public class CenterMeetingCollectionActivity extends LOSBaseActivity {

    private static final String TAG = CenterMeetingCollectionActivity.class.getCanonicalName();
    RecyclerView rvLeadDetails;
    CenterMeetingCollectionAdapter centerMeetingCollectionAdapter;

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

    private AlertDialog notificationDialog;

    @Inject
    public ViewModelProvider.Factory viewModelFactory;
    public DynamicUIViewModel viewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_center_meeting_collection);

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

        centerMeetingCollectionAdapter = new CenterMeetingCollectionAdapter(CenterMeetingCollectionActivity.this,
                new ArrayList<>(),appHelper,
                viewModel,CENTER_NAME,true);
        rvLeadDetails.setAdapter(centerMeetingCollectionAdapter);

        btnProceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(centerMeetingCollectionAdapter !=null && centerMeetingCollectionAdapter.getGroupList() !=null
                        && centerMeetingCollectionAdapter.getGroupList().size()>0){

                    appHelper.getDialogHelper().getConfirmationDialog().showTwoButtons(ERROR_MESSAGE_DO_YOU_WANT_TO_PROCEED, new ConfirmationDialog.ActionCallback() {
                        @Override
                        public void onAction() {
                            // TODO: Saving Center Meeting Details
                            saveCenterMeetingCollection();
                        }
                    });


                }
            }
        });

    }

    private void saveCenterMeetingCollection() {
        try{
            viewModel.saveCenterMeetingCollection(CENTER_NAME, COLLECTION_TYPE_REGULAR);
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
                                           // TODO: Insert Into Staff Activity Table
                                           insertStaffActivity(viewModel,CENTER_NAME,userId,"COLLECTION",1);

//                                           finish();
                                           showDigitalReceipt(cmCollectionLocalResponseDTO.getResponse(), String.valueOf(cmCollectionLocalResponseDTO.getOverallTotalCollection()));
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

    private void showDigitalReceipt(String smsMsg,String totalCollectionAmt) {
        try {
            viewModel.getMembersFromCenterMeetingTableForCollectionCenterWise(CENTER_NAME, COLLECTION_TYPE_REGULAR);
            if (viewModel.getCenterMeetingCollectionTableLiveDataList() != null) {
                Observer observer = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        List<CenterMeetingCollectionTable> centerMeetingCollectionTableList = (List<CenterMeetingCollectionTable>) o;
                        viewModel.getCenterMeetingCollectionTableLiveDataList().removeObserver(this::onChanged);

                        if(centerMeetingCollectionTableList !=null && centerMeetingCollectionTableList.size()>0){
                            List<String> stringList = new ArrayList<>();
                            String uniqueId="";
                            boolean isDigitalChecked=false;
                            for (CenterMeetingCollectionTable centerMeetingCollectionTable: centerMeetingCollectionTableList){
                                if (centerMeetingCollectionTable!=null && !TextUtils.isEmpty(centerMeetingCollectionTable.getCustomerName()) && !TextUtils.isEmpty(centerMeetingCollectionTable.getCustomerId())){
                                    stringList.add(centerMeetingCollectionTable.getCustomerName());
                                    if (TextUtils.isEmpty(uniqueId)) {
                                        uniqueId=centerMeetingCollectionTable.getCustomerId();
                                    }

                                    if (centerMeetingCollectionTable.isDigitalPayment()){
                                        isDigitalChecked=true;
                                    }
                                }
                            }

                            if(stringList !=null && stringList.size()>0){
                                stringList.add(0,"SELECT CLIENT NAME");
                                displayCenterMeetingPopUp(new ActionCallback() {
                                    @Override
                                    public void onAction(String clientName,String mobileNumber,String uniqueId,boolean isDigitalChecked) {
                                        triggerOTP(clientName,uniqueId,mobileNumber,smsMsg,totalCollectionAmt,isDigitalChecked);
//                                        finish();
                                    }
                                },stringList,smsMsg,uniqueId,isDigitalChecked);
                            }else{
                                appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT,ERROR_MESSAGE_NO_CLIENTS_AVAILABLE);
                            }
                        }
                    }
                };
                viewModel.getCenterMeetingCollectionTableLiveDataList().observe(this, observer);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
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
            viewModel.getGroupListForCollection(userId,centerName);
//            viewModel.getGroupListForCollectionByCollectionType(userId,centerName, COLLECTION_TYPE_REGULAR);
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

                            if (centerMeetingCollectionAdapter != null) {
                                centerMeetingCollectionAdapter.setItem(groupList);
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

    public void displayCenterMeetingPopUp(final ActionCallback callback, List<String> clientNameList,String smsMsg,String uniqueId,boolean isDigitalChecked) {

        notificationDialog = new AlertDialog.Builder(this).create();

        View notificationDialogView = LayoutInflater.from(this).inflate(R.layout.
                popup_digital_reciept_sms, null);
        notificationDialog.setView(notificationDialogView);
        notificationDialog.setCancelable(false);

        Button btnSave = (Button) notificationDialogView.findViewById(R.id.btn_popup_yes);
        Button btnCancel = (Button) notificationDialogView.findViewById(R.id.btn_popup_no);
        SearchableSpinner spCenterList=(SearchableSpinner)notificationDialogView.findViewById(R.id.sp_center_list);
        TextView tvCenterMeetingDate=(TextView) notificationDialogView.findViewById(R.id.tv_center_meeting_date);
        EditText mobile = (EditText) notificationDialogView.findViewById(R.id.et_mobile);

        if(clientNameList !=null && clientNameList.size()>0){
            ArrayAdapter arrayAdapter = new ArrayAdapter(CenterMeetingCollectionActivity.this, R.layout.view_spinner_textheight, clientNameList);
            spCenterList.setAdapter(arrayAdapter);
        }

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (callback != null) {
                        closeDialog();
                        if (isDigitalChecked) {
                            Intent collection_summary = new Intent(CenterMeetingCollectionActivity.this, DigitalCollectionActivity.class);
                            collection_summary.putExtra(PARAM_LOAN_TYPE, loanType);
                            collection_summary.putExtra(PARAM_USER_NAME, userName);
                            collection_summary.putExtra(PARAM_BRANCH_ID, branchId);
                            collection_summary.putExtra(PARAM_BRANCH_GST_CODE, branchGSTcode);
                            collection_summary.putExtra(PARAM_USER_ID, userId);
                            collection_summary.putExtra(PARAM_MODULE_TYPE, MODULE_TYPE_COLLECTION);
                            collection_summary.putExtra(PARAM_PRODUCT_ID, productId);
                            collection_summary.putExtra(PARAM_CENTER_NAME, CENTER_NAME); // TODO: CenterName
                            startActivity(collection_summary);
                        }
                        finish();
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
                            Toast.makeText(CenterMeetingCollectionActivity.this,"Please Choose Client",Toast.LENGTH_SHORT).show();
                            return;
                        }else if(TextUtils.isEmpty(mobile.getText().toString()) || (!TextUtils.isEmpty(mobile.getText().toString()) && mobile.getText().length()<10)){
                            Toast.makeText(CenterMeetingCollectionActivity.this,"Please Enter 10 Digits Mobile Number",Toast.LENGTH_SHORT).show();
                            return;
                        }
                        closeDialog();
                        callback.onAction(spCenterList.getSelectedItem().toString(),mobile.getText().toString(),uniqueId,isDigitalChecked);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        notificationDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        notificationDialog.show();
    }

    private void triggerOTP(String clientName,String uniqueId,String mobileNumber,String smsMsg,String totalCollectionAmt,boolean isDigitalChecked){
        try{
            appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();
            OTPTriggerDTO otpTriggerDTO=new OTPTriggerDTO();
            otpTriggerDTO.setUniqueId(uniqueId);
            otpTriggerDTO.setClientID(uniqueId);
            otpTriggerDTO.setCreatedDate(appHelper.getCurrentDate(DATE_FORMAT_DDMMYYYYHHMM));
            otpTriggerDTO.setCreatedBy("LOSMobile"); // TODO: Hard coded
            otpTriggerDTO.setServiceType("OTPTrigger"); // TODO: Hard coded

            OTPTriggerRequestStringDTO otpTriggerRequestStringDTO=new OTPTriggerRequestStringDTO();

            OTPTriggerRequestDTO otpTriggerRequestDTO=new OTPTriggerRequestDTO();
            otpTriggerRequestDTO.setMobileNumber(mobileNumber);
            otpTriggerRequestDTO.setIMEINumber(appHelper.getIMEI());
//            String msgText=COLLECTION_SMS_FORMAT;
//            msgText = msgText.replace("XXXXXX", CENTER_NAME);
//            msgText = msgText.replace("YYYYYY", totalCollectionAmt);
//            msgText = msgText.replace("ZZZZZZ", cmDate);
//            otpTriggerRequestDTO.setMessageText(msgText);

            String msgText=DAILY_COLLECTION_SMS_FORMAT;
            msgText = msgText.replace("XXXXXX", clientName);
            msgText = msgText.replace("YYYYYY", totalCollectionAmt);
            msgText = msgText.replace("ZZZZZZ", CENTER_NAME);
            otpTriggerRequestDTO.setMessageText(msgText);

            otpTriggerRequestDTO.setPurpose("To Digital Receipt"); // TODO: Hard coded

            otpTriggerRequestStringDTO.setOTPTriggerRequest(otpTriggerRequestDTO);
            otpTriggerDTO.setRequestString(otpTriggerRequestStringDTO);

            viewModel.generateCollectionSMS(otpTriggerDTO);
            if (viewModel.getOtpTriggerResponseDTOLiveData() != null) {
                Observer generateOTPObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        appHelper.getDialogHelper().getLoadingDialog().closeDialog();
                        OTPTriggerResponseDTO otpTriggerResponseDTO = (OTPTriggerResponseDTO) o;
                        viewModel.getOtpTriggerResponseDTOLiveData().removeObserver(this);

                        String errorMsg="Digital Receipt Send Failed";

                        if(otpTriggerResponseDTO !=null && otpTriggerResponseDTO.getApiResponse() !=null){
                            if( ! TextUtils.isEmpty(otpTriggerResponseDTO.getApiResponse().getStatus())
                                    && otpTriggerResponseDTO.getApiResponse().getStatus().equalsIgnoreCase("1")) {
                                String responseMsg="Digital Receipt Send Success";
//                                if(! TextUtils.isEmpty(otpTriggerResponseDTO.getApiResponse().getResponseMessage())) {
//                                    responseMsg = otpTriggerResponseDTO.getApiResponse().getResponseMessage();
//                                }
                                appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.SUCCESS,
                                        responseMsg, new ConfirmationDialog.ActionCallback() {
                                            @Override
                                            public void onAction() {
                                                if (isDigitalChecked) {
                                                    Intent collection_summary = new Intent(CenterMeetingCollectionActivity.this, DigitalCollectionActivity.class);
                                                    collection_summary.putExtra(PARAM_LOAN_TYPE, loanType);
                                                    collection_summary.putExtra(PARAM_USER_NAME, userName);
                                                    collection_summary.putExtra(PARAM_BRANCH_ID, branchId);
                                                    collection_summary.putExtra(PARAM_BRANCH_GST_CODE, branchGSTcode);
                                                    collection_summary.putExtra(PARAM_USER_ID, userId);
                                                    collection_summary.putExtra(PARAM_MODULE_TYPE, MODULE_TYPE_COLLECTION);
                                                    collection_summary.putExtra(PARAM_PRODUCT_ID, productId);
                                                    collection_summary.putExtra(PARAM_CENTER_NAME, CENTER_NAME); // TODO: CenterName
                                                    startActivity(collection_summary);
                                                }
                                                finish();
                                            }
                                        });
                            }else{

                                if(! TextUtils.isEmpty(otpTriggerResponseDTO.getApiResponse().getResponseMessage())){
//                                    errorMsg=otpTriggerResponseDTO.getApiResponse().getResponseMessage();
                                    appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ERROR,
                                            errorMsg, new ConfirmationDialog.ActionCallback() {
                                                @Override
                                                public void onAction() {
                                                }
                                            });
                                }
                            }
                        }else{
                            appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ERROR,
                                    errorMsg, new ConfirmationDialog.ActionCallback() {
                                        @Override
                                        public void onAction() {

                                        }
                                    });
                        }

                    }
                };
                viewModel.getOtpTriggerResponseDTOLiveData().observe(this, generateOTPObserver);
            }
        }catch (Exception ex){
            ex.printStackTrace();
            appHelper.getDialogHelper().getLoadingDialog().closeDialog();
        }

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
        void onAction(String centerName,String mobileNumber,String uniqueId,boolean isDigitalChecked);
    }


}
