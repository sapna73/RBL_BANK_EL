package com.saartak.el.activities;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.view.ActionMode;
import androidx.appcompat.widget.AppCompatCheckBox;
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
import com.google.android.material.navigation.NavigationView;
import com.saartak.el.BuildConfig;
import com.saartak.el.R;
import com.saartak.el.adapter.CenterMeetingEMIDetailsAdapter;
import com.saartak.el.constants.AppConstant;
import com.saartak.el.database.converter.TimestampConverter;
import com.saartak.el.database.entity.CenterCreationTable;
import com.saartak.el.database.entity.RawDataTable;
import com.saartak.el.models.EMIDetailsDTO;
import com.saartak.el.view_models.DynamicUIViewModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

import static com.saartak.el.constants.AppConstant.DATE_FORMAT_DD_MM_YYYY2;
import static com.saartak.el.constants.AppConstant.DATE_FORMAT_YYYY_MM_DD;
import static com.saartak.el.constants.AppConstant.PARAM_BRANCH_GST_CODE;
import static com.saartak.el.constants.AppConstant.PARAM_BRANCH_ID;
import static com.saartak.el.constants.AppConstant.PARAM_CENTER_NAME;
import static com.saartak.el.constants.AppConstant.PARAM_CLIENT_ID;
import static com.saartak.el.constants.AppConstant.PARAM_LOAN_TYPE;
import static com.saartak.el.constants.AppConstant.PARAM_PRODUCT_ID;
import static com.saartak.el.constants.AppConstant.PARAM_USER_ID;
import static com.saartak.el.constants.AppConstant.PARAM_USER_NAME;
import static com.saartak.el.dynamicui.constants.ParametersConstant.ERROR_MESSAGE_DO_YOU_WANT_TO_PROCEED;
import static com.saartak.el.dynamicui.constants.ParametersConstant.ERROR_MESSAGE_EMI_DETAILS_SAVED_SUCCESSFULLY;
import static com.saartak.el.dynamicui.constants.ParametersConstant.ERROR_MESSAGE_SELECT_ARREAR_REASON;

public class CenterMeetingEMIDetailsActivity extends LOSBaseActivity implements CenterMeetingEMIDetailsAdapter.EMIDetailsCallBackInterface {

    private static final String TAG = CenterMeetingEMIDetailsActivity.class.getCanonicalName();
    RecyclerView rvLeadDetails;
    CenterMeetingEMIDetailsAdapter centerMeetingEMIDetailsAdapter;

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
    TextView tvClientName,tvClientId,tvRelationShipName,tvTotalEMI,tvTotalDue,tvTotalCollection,tvPDPDate;
    ImageButton ibPDPDate;
    TextView tvAppVersion,tvCurrentDate,tvUserName;
    AppCompatCheckBox cbPaidByOther;
    Button btnProceed;
    CenterCreationTable CENTER_CREATION_TABLE;
    List<RawDataTable> allClientRawDataTableList;

    @Inject
    public ViewModelProvider.Factory viewModelFactory;
    public DynamicUIViewModel viewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_center_meeting_emi_details);

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
        tvRelationShipName=(TextView)findViewById(R.id.tv_member_relation_value);
        tvTotalEMI=(TextView)findViewById(R.id.tv_total_emi_value);
        tvTotalDue=(TextView)findViewById(R.id.tv_total_due_value);
        tvTotalCollection=(TextView)findViewById(R.id.tv_total_collection_value);
        tvPDPDate=(TextView)findViewById(R.id.tv_pdp_date_value);
        ibPDPDate=(ImageButton) findViewById(R.id.ib_ptp_date);

        cbPaidByOther=(AppCompatCheckBox)findViewById(R.id.cb_paid_by_other);

        btnProceed=(Button)findViewById(R.id.btn_proceed);

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

        centerMeetingEMIDetailsAdapter = new CenterMeetingEMIDetailsAdapter(CenterMeetingEMIDetailsActivity.this, new ArrayList<>(),appHelper,
                this::totalCollectionCallBack);
        rvLeadDetails.setAdapter(centerMeetingEMIDetailsAdapter);

        btnProceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(centerMeetingEMIDetailsAdapter !=null && centerMeetingEMIDetailsAdapter.getEmiDetailsDTOList() !=null
                        && centerMeetingEMIDetailsAdapter.getEmiDetailsDTOList().size()>0){

                    boolean isValid=false;
                    for(EMIDetailsDTO emiDetailsDTO : centerMeetingEMIDetailsAdapter.getEmiDetailsDTOList()){
                        int totalDue=emiDetailsDTO.getTotalDue();
                        int collection=emiDetailsDTO.getCollection();
                        if(collection>=totalDue){
                            isValid=true;
                        }
                        else if(!TextUtils.isEmpty(emiDetailsDTO.getArrearReason())){
                            isValid=true;
                        }
                        else{
                            isValid=false;
                            break;
                        }
                    }

                    if(isValid) {

                        appHelper.getDialogHelper().getConfirmationDialog().showTwoButtons(ERROR_MESSAGE_DO_YOU_WANT_TO_PROCEED, new ConfirmationDialog.ActionCallback() {
                            @Override
                            public void onAction() {
                                saveEMIDetailsCenterMeetingCollection(centerMeetingEMIDetailsAdapter.getEmiDetailsDTOList());
                            }
                        });
                    }else{
                        appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT,ERROR_MESSAGE_SELECT_ARREAR_REASON);
                    }

                }
            }
        });

        cbPaidByOther.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(centerMeetingEMIDetailsAdapter.getEmiDetailsDTOList() !=null
                        && centerMeetingEMIDetailsAdapter.getEmiDetailsDTOList().size()>0){

                    // TODO: Set EMI Paid by other member
                    for(EMIDetailsDTO emiDetailsDTO : centerMeetingEMIDetailsAdapter.getEmiDetailsDTOList()){
                        emiDetailsDTO.setPaidByOtherMember(isChecked);
                    }
                }
            }
        });

        final Calendar calendar = Calendar.getInstance();
        int mYear = calendar.get(Calendar.YEAR);
        int mMonth = calendar.get(Calendar.MONTH);
        int  mDay = calendar.get(Calendar.DAY_OF_MONTH);

        ibPDPDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog=new DatePickerDialog(CenterMeetingEMIDetailsActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        calendar.set(Calendar.YEAR, year);
                        calendar.set(Calendar.MONTH, month);
                        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(AppConstant.DATE_FORMAT_YYYY_MM_DD, Locale.US);
                        String selectedDate = simpleDateFormat.format(calendar.getTime());
                        tvPDPDate.setText(selectedDate);

                        if(centerMeetingEMIDetailsAdapter.getEmiDetailsDTOList() !=null
                                && centerMeetingEMIDetailsAdapter.getEmiDetailsDTOList().size()>0){

                            // TODO: Set PTP DATE
                            for(EMIDetailsDTO emiDetailsDTO : centerMeetingEMIDetailsAdapter.getEmiDetailsDTOList()){
                                emiDetailsDTO.setPTPDate(TimestampConverter.toDate(selectedDate));
                            }
                        }
                    }
                },mYear,mMonth,mDay);

                DatePicker datePicker = datePickerDialog.getDatePicker();
                datePicker.setMinDate(System.currentTimeMillis());

                datePickerDialog.show();
            }
        });


        // TODO: Insert Into Staff Activity Table
        insertStaffActivity(viewModel,CENTER_NAME,userId,"EMI DETAILS",0);
    }

    private void saveEMIDetailsCenterMeetingCollection(List<EMIDetailsDTO> emiDetailsDTOList) {
        try{
            viewModel.saveEMIDetailsCenterMeetingCollection(emiDetailsDTOList);
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
            viewModel.getEMIDetailsForCollection(customerId);
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
                            tvRelationShipName.setText(emiDetailsDTOList.get(0).getMemberRelationName());
                            Date ptpDate = emiDetailsDTOList.get(0).getPTPDate();
                            String date = appHelper.convertDateToStringFormat(ptpDate, DATE_FORMAT_YYYY_MM_DD);
                            tvPDPDate.setText(date);

                            if(emiDetailsDTOList.get(0).isPaidByOtherMember()){
                                cbPaidByOther.setChecked(true);
                            }else{
                                cbPaidByOther.setChecked(false);
                            }
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


                            if (centerMeetingEMIDetailsAdapter != null) {
                                centerMeetingEMIDetailsAdapter.setItem(emiDetailsDTOList);
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
}
