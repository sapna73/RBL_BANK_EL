package com.saartak.el.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.bfil.uilibrary.dialogs.ConfirmationDialog;
import com.google.android.material.navigation.NavigationView;
import com.saartak.el.BuildConfig;
import com.saartak.el.R;
import com.saartak.el.database.entity.PlannerTable;
import com.saartak.el.view_models.DynamicUIViewModel;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.DispatchingAndroidInjector;

import static com.saartak.el.constants.AppConstant.DATE_FORMAT_DD_MM_YYYY2;
import static com.saartak.el.constants.AppConstant.DATE_FORMAT_HH_MM_SS;
import static com.saartak.el.constants.AppConstant.MODULE_TYPE_LEAD;
import static com.saartak.el.constants.AppConstant.PARAM_BRANCH_GST_CODE;
import static com.saartak.el.constants.AppConstant.PARAM_BRANCH_ID;
import static com.saartak.el.constants.AppConstant.PARAM_BRANCH_NAME;
import static com.saartak.el.constants.AppConstant.PARAM_CLIENT_ID;
import static com.saartak.el.constants.AppConstant.PARAM_GET_APPLICATION_ID;
import static com.saartak.el.constants.AppConstant.PARAM_LOAN_TYPE;
import static com.saartak.el.constants.AppConstant.PARAM_MODULE_TYPE;
import static com.saartak.el.constants.AppConstant.PARAM_PRODUCT_ID;
import static com.saartak.el.constants.AppConstant.PARAM_USER_ID;
import static com.saartak.el.constants.AppConstant.PARAM_USER_NAME;
import static com.saartak.el.constants.AppConstant.REQUEST_CODE_NEW_LEAD;
import static com.saartak.el.constants.AppConstant.REQUEST_CODE_UPDATE_LEAD;
import static com.saartak.el.dynamicui.constants.ParametersConstant.ALONG_WITH_STAFF_RADIO_BUTTON_NO;
import static com.saartak.el.dynamicui.constants.ParametersConstant.ALONG_WITH_STAFF_RADIO_BUTTON_YES;
import static com.saartak.el.dynamicui.constants.ParametersConstant.ERROR_MESSAGE_DATA_SAVED_SUCCESSFULLY;
import static com.saartak.el.dynamicui.constants.ParametersConstant.ERROR_MESSAGE_ENTER_EMPLOYEE_DETAILS;
import static com.saartak.el.dynamicui.constants.ParametersConstant.ERROR_MESSAGE_ENTER_SHOP_NAME;
import static com.saartak.el.dynamicui.constants.ParametersConstant.ERROR_MESSAGE_PURPOSE_OF_VISIT;
import static com.saartak.el.dynamicui.constants.ParametersConstant.ERROR_MESSAGE_SELECT_ALONG_WITH_STAFF;
import static com.saartak.el.dynamicui.constants.ParametersConstant.ERROR_MESSAGE_SELECT_APPLICATION_ID;
import static com.saartak.el.dynamicui.constants.ParametersConstant.ERROR_MESSAGE_SELECT_TRAVEL_WITH_OWN_VEHICLE;
import static com.saartak.el.dynamicui.constants.ParametersConstant.ERROR_MESSAGE_SELECT_VEHICLE_TYPE;
import static com.saartak.el.dynamicui.constants.ParametersConstant.OWN_VEHICLE_RADIO_BUTTON_NO;
import static com.saartak.el.dynamicui.constants.ParametersConstant.OWN_VEHICLE_RADIO_BUTTON_YES;
import static com.saartak.el.dynamicui.constants.ParametersConstant.PURPOSE_OF_VISIT_SPINNER_ITEM_APPLICATION;
import static com.saartak.el.dynamicui.constants.ParametersConstant.VEHICLE_TYPE_RADIO_BUTTON_FW;
import static com.saartak.el.dynamicui.constants.ParametersConstant.VEHICLE_TYPE_RADIO_BUTTON_TW;

public class PlannerActivity extends LOSBaseActivity {
    private static String TAG = PlannerActivity.class.getCanonicalName();

    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;
    TextView tvLabelPurposeOfVisit,tvLabelShop,tvLabelAlongStaff,tvLabelEmpId,tvLabelEmpName,tvLabelDesignation,tvLabelTravel,tvLabelVehicleType;
    TextView tvPlannerDate,tvUserId,tvBranchName, tv_currentTime;
    EditText etShopName, etEmpId, etEmpName, etDesignation,etPurposeOfVisit,etApplicationId;
    LinearLayout llOtherEmp,llEmpId,llApplicationId;
    Button btn_startVisit, btn_endVisit;
    RadioGroup rg_alongWithStaff, rg_vehicleType, rg_travelWithOwnVehicle;
    RadioButton rb_along_with_staff_yes, rb_along_with_staff_no, rb_own_vehicle_yes, rb_own_vehicle_no,
            rb_vehicle_type_TW, rb_vehicle_type_FW;
    AppCompatSpinner sp_purpose_of_visit;
    String purpose_of_visit,  shopName, selectedTravelOwnVehicleRadioButton, selectedVehicleTypeRadioButton;
    private PlannerTable plannerTable;

    private Toolbar toolbar;
    TextView tvClientName;
    TextView tvAppVersion,tvCurrentDate;
    String SCREEN_NO;

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView navigationView;
    CoordinatorLayout coordinatorLayout;
    ImageView ivStaffImage;
    TextView tvStaffId, tvStaffName, tvSOBDate;
    LinearLayout ll_vehicle_type;



    @Inject
    public ViewModelProvider.Factory viewModelFactory;
    public DynamicUIViewModel viewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planner);

        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        rg_alongWithStaff=(RadioGroup) findViewById(R.id.rg_along_with_staff);
        rg_travelWithOwnVehicle=(RadioGroup) findViewById(R.id.rg_travel_with_own_vehicle);
        rg_vehicleType=(RadioGroup) findViewById(R.id.rg_vehicle_type);
        rb_along_with_staff_yes=(RadioButton) findViewById(R.id.rb_along_with_staff_yes);
        rb_along_with_staff_no=(RadioButton) findViewById(R.id.rb_along_with_staff_no);
        rb_own_vehicle_yes=(RadioButton) findViewById(R.id.rb_own_vehicle_yes);
        rb_own_vehicle_no=(RadioButton) findViewById(R.id.rb_own_vehicle_no);
        rb_vehicle_type_TW=(RadioButton) findViewById(R.id.rb_vehicle_type_TW);
        rb_vehicle_type_FW=(RadioButton) findViewById(R.id.rb_vehicle_type_FW);
        ll_vehicle_type=(LinearLayout) findViewById(R.id.ll_vehicle_type);

        tvUserId = (TextView) findViewById(R.id.tv_userid);
        tvBranchName = (TextView) findViewById(R.id.tv_branch_name);
        tvPlannerDate = (TextView) findViewById(R.id.tv_planner_date);
        tv_currentTime = (TextView) findViewById(R.id.tv_current_time);
        etShopName = (EditText)findViewById(R.id.et_shopName);
        etEmpId = (EditText)findViewById(R.id.et_empid);
        etEmpName = (EditText)findViewById(R.id.et_empName);
        etDesignation = (EditText)findViewById(R.id.et_designation);
        etPurposeOfVisit = (EditText)findViewById(R.id.et_purpose_of_visit);
        etApplicationId = (EditText)findViewById(R.id.et_applicationid);
        btn_startVisit = (Button)findViewById(R.id.btn_start_visit);
        btn_endVisit = (Button)findViewById(R.id.btn_end_visit);
        sp_purpose_of_visit = (AppCompatSpinner) findViewById(R.id.sp_purpose_of_visit);
        llOtherEmp = (LinearLayout) findViewById(R.id.ll_other_emp);
        llEmpId = (LinearLayout) findViewById(R.id.ll_empid);
        llApplicationId = (LinearLayout) findViewById(R.id.ll_applicationid);

        toolbar =  findViewById(R.id.toolbar);
        tvCurrentDate = (TextView)findViewById(R.id.tv_currentdate);
        tvAppVersion = (TextView)findViewById(R.id.tv_app_version);
        tvClientName =  findViewById(R.id.tv_client_name_custom);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        actionBarDrawerToggle.getDrawerArrowDrawable().setColor(Color.BLACK);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        actionBarDrawerToggle.syncState();
        setUpDrawerContent(navigationView);
        View header = navigationView.getHeaderView(0);
        ivStaffImage = header.findViewById(R.id.iv_staff_image);
        tvStaffId = header.findViewById(R.id.tv_staff_id);
        tvStaffName = header.findViewById(R.id.tv_staff_name);
        tvSOBDate = header.findViewById(R.id.tv_sod_date);

        tvLabelPurposeOfVisit = (TextView) findViewById(R.id.tv_label_purposeofvisit);
        SpannableStringBuilder builder_purpose = new SpannableStringBuilder("Purpose of Visit:*");
        builder_purpose.setSpan(new ForegroundColorSpan(Color.RED), builder_purpose.length() - 1, builder_purpose.length(),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvLabelPurposeOfVisit.setText(builder_purpose);

        tvLabelShop = (TextView) findViewById(R.id.tv_label_shop);
       /* SpannableStringBuilder builder_shop = new SpannableStringBuilder("Shop Name:*");
        builder_shop.setSpan(new ForegroundColorSpan(Color.RED), builder_shop.length() - 1, builder_shop.length(),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvLabelShop.setText(builder_shop);*/

        tvLabelAlongStaff = (TextView) findViewById(R.id.tv_label_alongstaff);
        SpannableStringBuilder builder_alongStaff = new SpannableStringBuilder("Along with staff:*");
        builder_alongStaff.setSpan(new ForegroundColorSpan(Color.RED), builder_alongStaff.length() - 1, builder_alongStaff.length(),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvLabelAlongStaff.setText(builder_alongStaff);

        tvLabelEmpId = (TextView) findViewById(R.id.tv_label_empid);
        SpannableStringBuilder builder_empId = new SpannableStringBuilder("Employee ID:*");
        builder_empId.setSpan(new ForegroundColorSpan(Color.RED), builder_empId.length() - 1, builder_empId.length(),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvLabelEmpId.setText(builder_empId);

        tvLabelEmpName = (TextView) findViewById(R.id.tv_label_empname);
        SpannableStringBuilder builder_empName= new SpannableStringBuilder("Employee Name:*");
        builder_empName.setSpan(new ForegroundColorSpan(Color.RED), builder_empName.length() - 1, builder_empName.length(),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvLabelEmpName.setText(builder_empName);

        tvLabelDesignation = (TextView) findViewById(R.id.tv_label_designation);
        SpannableStringBuilder builder_designation= new SpannableStringBuilder("Designation:*");
        builder_designation.setSpan(new ForegroundColorSpan(Color.RED), builder_designation.length() - 1, builder_designation.length(),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvLabelDesignation.setText(builder_designation);

        tvLabelTravel = (TextView) findViewById(R.id.tv_label_travel);
        SpannableStringBuilder builder_travel= new SpannableStringBuilder("Travel with own Vehicle:*");
        builder_travel.setSpan(new ForegroundColorSpan(Color.RED), builder_travel.length() - 1, builder_travel.length(),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvLabelTravel.setText(builder_travel);

        tvLabelVehicleType = (TextView) findViewById(R.id.tv_label_vehicletype);
        SpannableStringBuilder builder_vehicleType= new SpannableStringBuilder("Vehicle Type:*");
        builder_vehicleType.setSpan(new ForegroundColorSpan(Color.RED), builder_vehicleType.length() - 1, builder_vehicleType.length(),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvLabelVehicleType.setText(builder_vehicleType);

        userName = getIntent().getStringExtra(PARAM_USER_NAME);
        userId = getIntent().getStringExtra(PARAM_USER_ID);
        branchId = getIntent().getStringExtra(PARAM_BRANCH_ID);
        branchName = getIntent().getStringExtra(PARAM_BRANCH_NAME);
        branchGSTcode = getIntent().getStringExtra(PARAM_BRANCH_GST_CODE);
        loanType = getIntent().getStringExtra(PARAM_LOAN_TYPE);
        productId = getIntent().getStringExtra(PARAM_PRODUCT_ID);
        CLIENT_ID = getIntent().getStringExtra(PARAM_CLIENT_ID);

        if (!TextUtils.isEmpty(userName)) {
            tvClientName.setText(userName.toUpperCase());
            tvStaffName.setText(userName.toUpperCase());
        }
        if (!TextUtils.isEmpty(userId)) {
            tvStaffId.setText(userId);
            tvUserId.setText(userId);
        }
        if (!TextUtils.isEmpty(branchName)) {
            tvBranchName.setText(branchName);
        }
        String currentDate = appHelper.getCurrentDate(DATE_FORMAT_DD_MM_YYYY2);
        if (!TextUtils.isEmpty(currentDate)) {
            tvSOBDate.setText("SOD DATE : " + currentDate);
            tvCurrentDate.setText(currentDate);
            tvPlannerDate.setText(currentDate);
        }
        String appVersion = BuildConfig.VERSION_NAME;
        if (!TextUtils.isEmpty(appVersion)) {
            tvAppVersion.setText(getResources().getString(R.string.version)+" "+appVersion);
        }

        String currentTime = appHelper.getCurrentDate(DATE_FORMAT_HH_MM_SS);
        if (!TextUtils.isEmpty(currentTime)) {
            tv_currentTime.setText(currentTime);
        }

        btn_startVisit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startEndVisit(true);
            }
        });

        btn_endVisit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startEndVisit(false);
            }
        });

        rb_along_with_staff_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llOtherEmp.setVisibility(View.VISIBLE);
                llEmpId.setVisibility(View.VISIBLE);
            }
        });

        rb_along_with_staff_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llOtherEmp.setVisibility(View.GONE);
                llEmpId.setVisibility(View.INVISIBLE);
            }
        });

        rb_own_vehicle_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ll_vehicle_type.setVisibility(View.VISIBLE);
            }
        });

        rb_own_vehicle_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ll_vehicle_type.setVisibility(View.GONE);

            }
        });

        sp_purpose_of_visit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
                String selectedItem=adapterView.getItemAtPosition(pos).toString();
                if (!TextUtils.isEmpty(selectedItem) && selectedItem.equalsIgnoreCase(PURPOSE_OF_VISIT_SPINNER_ITEM_APPLICATION)){
                    llApplicationId.setVisibility(View.VISIBLE);
                }else {
                    llApplicationId.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        etApplicationId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent workflowHistory = new Intent(PlannerActivity.this, WorkflowHistorySummaryActivity.class);
                workflowHistory.putExtra(PARAM_LOAN_TYPE, loanType);
                workflowHistory.putExtra(PARAM_USER_NAME, userName);
                workflowHistory.putExtra(PARAM_BRANCH_ID, branchId);
                workflowHistory.putExtra(PARAM_BRANCH_GST_CODE, branchGSTcode);
                workflowHistory.putExtra(PARAM_USER_ID, userId);
                workflowHistory.putExtra(PARAM_MODULE_TYPE, MODULE_TYPE_LEAD);
                workflowHistory.putExtra(PARAM_PRODUCT_ID, productId);
                workflowHistory.putExtra(PARAM_GET_APPLICATION_ID, true);
                startActivityForResult(workflowHistory, REQUEST_CODE_NEW_LEAD);
            }
        });

        configureDagger();

        configureViewModel();

    }

    public void startEndVisit(boolean visit){
        try {
            if (sp_purpose_of_visit.getSelectedItemPosition() > 0) {
                purpose_of_visit = sp_purpose_of_visit.getSelectedItem().toString();
                if (!TextUtils.isEmpty(purpose_of_visit)) {
                    if (!TextUtils.isEmpty(purpose_of_visit) && !purpose_of_visit.equalsIgnoreCase(PURPOSE_OF_VISIT_SPINNER_ITEM_APPLICATION)
                            || !TextUtils.isEmpty(purpose_of_visit) && purpose_of_visit.equalsIgnoreCase(PURPOSE_OF_VISIT_SPINNER_ITEM_APPLICATION) && !TextUtils.isEmpty(etApplicationId.getText().toString())) {
                        shopName = etShopName.getText().toString();

                        if (rg_travelWithOwnVehicle != null && rg_travelWithOwnVehicle.getCheckedRadioButtonId() != -1) {
                            selectedTravelOwnVehicleRadioButton = appHelper.getSelectedRadioButtonItem(rg_travelWithOwnVehicle, rg_travelWithOwnVehicle.getCheckedRadioButtonId());
                            if (!TextUtils.isEmpty(selectedTravelOwnVehicleRadioButton) && selectedTravelOwnVehicleRadioButton.equalsIgnoreCase(OWN_VEHICLE_RADIO_BUTTON_YES)) {
                                if (rg_vehicleType != null && rg_vehicleType.getCheckedRadioButtonId() != -1) {
                                    selectedVehicleTypeRadioButton = appHelper.getSelectedRadioButtonItem(rg_vehicleType, rg_vehicleType.getCheckedRadioButtonId());
                                    if (!TextUtils.isEmpty(selectedVehicleTypeRadioButton)) {
                                        if (rg_alongWithStaff != null && rg_alongWithStaff.getCheckedRadioButtonId() != -1) {
                                            insertIntoPlannerTable(visit);
                                        } else {
                                            appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, ERROR_MESSAGE_SELECT_ALONG_WITH_STAFF);
                                        }
                                    }
                                } else {
                                    appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, ERROR_MESSAGE_SELECT_VEHICLE_TYPE);
                                }
                            } else if (!TextUtils.isEmpty(selectedTravelOwnVehicleRadioButton) && selectedTravelOwnVehicleRadioButton.equalsIgnoreCase(OWN_VEHICLE_RADIO_BUTTON_NO)) {
                                if (rg_alongWithStaff != null && rg_alongWithStaff.getCheckedRadioButtonId() != -1) {
                                    insertIntoPlannerTable(visit);
                                } else {
                                    appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, ERROR_MESSAGE_SELECT_ALONG_WITH_STAFF);
                                }
                            }
                        } else {
                            appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, ERROR_MESSAGE_SELECT_TRAVEL_WITH_OWN_VEHICLE);
                        }

                    } else {
                        appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, ERROR_MESSAGE_SELECT_APPLICATION_ID);
                    }
                } else {
                    appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, ERROR_MESSAGE_PURPOSE_OF_VISIT);
                }
            } else {
                appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, ERROR_MESSAGE_PURPOSE_OF_VISIT);
            }
        }catch (Exception ex){
            ex.printStackTrace();
            INSERT_LOG("startEndVisit","Exception : "+ex.getMessage());
        }
    }

    private void insertIntoPlannerTable(boolean visit) {
        try{
            PlannerTable plannerTable = new PlannerTable();
            plannerTable.setStaffName(userName);
            plannerTable.setStaffId(userId);
            plannerTable.setBranchId(branchId);
            plannerTable.setBranchGSTcode(branchGSTcode);
            plannerTable.setLoan_type(loanType);
            plannerTable.setClientId(CLIENT_ID);

            plannerTable.setPurposeOfVisit(purpose_of_visit);
            plannerTable.setShopName(shopName);
            if (selectedTravelOwnVehicleRadioButton.equalsIgnoreCase(OWN_VEHICLE_RADIO_BUTTON_YES)) {
                plannerTable.setTravelWithOwnVehicle(true);
            } else if (selectedTravelOwnVehicleRadioButton.equalsIgnoreCase(OWN_VEHICLE_RADIO_BUTTON_NO)) {
                plannerTable.setTravelWithOwnVehicle(false);
            }

            if (selectedVehicleTypeRadioButton !=null && selectedVehicleTypeRadioButton.equalsIgnoreCase(VEHICLE_TYPE_RADIO_BUTTON_TW)) {
                plannerTable.setVehicleType(true);
            } else if (selectedVehicleTypeRadioButton !=null && selectedVehicleTypeRadioButton.equalsIgnoreCase(VEHICLE_TYPE_RADIO_BUTTON_FW)) {
                plannerTable.setVehicleType(false);
            }

            if (purpose_of_visit.equalsIgnoreCase(PURPOSE_OF_VISIT_SPINNER_ITEM_APPLICATION)){
                plannerTable.setApplicationId(etApplicationId.getText().toString());
            }else {
                plannerTable.setApplicationId("");
            }

            String selectedAlongWithStaffRadioButton = appHelper.getSelectedRadioButtonItem(rg_alongWithStaff, rg_alongWithStaff.getCheckedRadioButtonId());
            if (!TextUtils.isEmpty(selectedAlongWithStaffRadioButton) && selectedAlongWithStaffRadioButton.equalsIgnoreCase(ALONG_WITH_STAFF_RADIO_BUTTON_YES)) {
                plannerTable.setAlongWithStaff(true);

                String empID = etEmpId.getText().toString();
                String empName = etEmpName.getText().toString();
                String designation = etDesignation.getText().toString();
                if (!TextUtils.isEmpty(empID) && !TextUtils.isEmpty(empName) && !TextUtils.isEmpty(designation)) {
                    plannerTable.setEmployeeId(empID);
                    plannerTable.setEmployeeName(empName);
                    plannerTable.setDesignation(designation);
                    if(visit) {
                        insertDataInPlannerTable(plannerTable, visit);
                    }else if(!TextUtils.isEmpty(shopName)){
                        insertDataInPlannerTable(plannerTable, visit);
                    }else{
                        appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, ERROR_MESSAGE_ENTER_SHOP_NAME);
                    }
                } else {
                    appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, ERROR_MESSAGE_ENTER_EMPLOYEE_DETAILS);
                }
            } else if (!TextUtils.isEmpty(selectedAlongWithStaffRadioButton) && selectedAlongWithStaffRadioButton.equalsIgnoreCase(ALONG_WITH_STAFF_RADIO_BUTTON_NO)) {
                plannerTable.setAlongWithStaff(false);
                if(visit) {
                    insertDataInPlannerTable(plannerTable, visit);
                }else if(!TextUtils.isEmpty(shopName)){
                    insertDataInPlannerTable(plannerTable, visit);
                }else{
                    appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, ERROR_MESSAGE_ENTER_SHOP_NAME);
                }


            }
        }
        catch (Exception ex){
            ex.printStackTrace();
            INSERT_LOG("insertIntoPlannerTable","Exception : "+ex.getMessage());
        }
    }


    private void configureDagger() {
        AndroidInjection.inject(this);
    }

    public void configureViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(DynamicUIViewModel.class);

        getPlannerDataFromLocalDB(userId, CLIENT_ID); // TODO: get planner data
    }

    public void getPlannerDataFromLocalDB(String userId, String clientId) {

        try {

            appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();

            viewModel.getPlannerDataFromLocalDB(userId, clientId);
            if (viewModel.getPlannerTableLiveData() != null) {
                Observer getPlannerDataObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        PlannerTable plannerTable = (PlannerTable) o;
                        viewModel.getPlannerTableLiveData().removeObserver(this);

                        appHelper.getDialogHelper().getLoadingDialog().closeDialog();

                        if (plannerTable != null) {
                            uIChanges(plannerTable);
                        }
                    }
                };
                viewModel.getPlannerTableLiveData().observe(this, getPlannerDataObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            appHelper.getDialogHelper().getLoadingDialog().closeDialog();

            INSERT_LOG("getPlannerDataFromLocalDB","Exception : "+ex.getMessage());
        }
    }

    public void insertDataInPlannerTable(PlannerTable plannerTable,boolean isChecked) {
        try {

            appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();

            viewModel.insertorUpdatePlannerData(plannerTable,isChecked);
            if (viewModel.getPlannerTableLiveData() != null) {
                Observer getPlannerDataObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        PlannerTable plannerTable = (PlannerTable) o;
                        viewModel.getPlannerTableLiveData().removeObserver(this);

                        appHelper.getDialogHelper().getLoadingDialog().closeDialog();

                        if (plannerTable != null) {
                            appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.SUCCESS,
                                    ERROR_MESSAGE_DATA_SAVED_SUCCESSFULLY, new ConfirmationDialog.ActionCallback() {
                                        @Override
                                        public void onAction() {
                                            uIChanges(plannerTable);
                                        }
                                    });
                        }
                    }
                };
                viewModel.getPlannerTableLiveData().observe(this, getPlannerDataObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            appHelper.getDialogHelper().getLoadingDialog().closeDialog();

            INSERT_LOG("insertDataInPlannerTable","Exception : "+ex.getMessage());
        }
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
            loanType = getIntent().getStringExtra(PARAM_LOAN_TYPE);
            productId = getIntent().getStringExtra(PARAM_PRODUCT_ID);
            CLIENT_ID = getIntent().getStringExtra(PARAM_CLIENT_ID);

        } catch (Exception ex) {
            ex.printStackTrace();
            INSERT_LOG("onResume","Exception : " + ex.getMessage());
        }
    }

    public void uIChanges(PlannerTable plannerTable){
        try {
            if (plannerTable != null) {
                if (!plannerTable.isTripStart()) {
                    btn_startVisit.setEnabled(true);
                    btn_endVisit.setEnabled(false);
                    //set visibility
                    btn_startVisit.setVisibility(View.VISIBLE);
                    btn_endVisit.setVisibility(View.GONE);

                }
                else if (plannerTable.isTripStart()) {

                    if (plannerTable.isTripEnd()) {
                        btn_startVisit.setEnabled(false);
                        btn_endVisit.setEnabled(false);
                        //set visibility
                        btn_startVisit.setVisibility(View.GONE);
                        btn_endVisit.setVisibility(View.VISIBLE);

                        btn_endVisit.setText("Trip Completed");
                        etShopName.setEnabled(false);
//                    btn_startVisit.setVisibility(View.GONE);
                    } else {
                        if (!TextUtils.isEmpty(plannerTable.getShopName())) {
                            etShopName.setEnabled(false);
                        } else {
                            etShopName.setEnabled(true);
                        }
                        btn_startVisit.setEnabled(false);
                        btn_startVisit.setText("Trip Started");
                        btn_endVisit.setEnabled(true);

                        //set visibility
                        btn_startVisit.setVisibility(View.GONE);
                        btn_endVisit.setVisibility(View.VISIBLE);
                    }
                    sp_purpose_of_visit.setEnabled(false);


                    rb_along_with_staff_yes.setEnabled(false);
                    rb_along_with_staff_no.setEnabled(false);
                    etEmpId.setEnabled(false);
                    etEmpName.setEnabled(false);
                    etDesignation.setEnabled(false);
                    rb_own_vehicle_yes.setEnabled(false);
                    rb_own_vehicle_no.setEnabled(false);
                    rb_vehicle_type_TW.setEnabled(false);
                    rb_vehicle_type_FW.setEnabled(false);
                    etApplicationId.setEnabled(false);

                    if (!TextUtils.isEmpty(plannerTable.getPurposeOfVisit())) {
                        for (int i = 0; i < sp_purpose_of_visit.getCount(); i++) {
                            if (sp_purpose_of_visit.getItemAtPosition(i).equals(plannerTable.getPurposeOfVisit())) {
                                sp_purpose_of_visit.setSelection(i);
                                break;
                            }
                        }
                        if (plannerTable.getPurposeOfVisit().equalsIgnoreCase("Application")) {
                            if (!TextUtils.isEmpty(plannerTable.getApplicationId())) {
                                etApplicationId.setText(plannerTable.getApplicationId());
                            }
                        }
                    }

                    if (!TextUtils.isEmpty(plannerTable.getShopName())) {
                        etShopName.setText(plannerTable.getShopName());
                    }

                    if (plannerTable.isAlongWithStaff()) {
                        rb_along_with_staff_yes.setChecked(true);
                        llOtherEmp.setVisibility(View.VISIBLE);
                        llEmpId.setVisibility(View.VISIBLE);

                        if (!TextUtils.isEmpty(plannerTable.getEmployeeId())) {
                            etEmpId.setText(plannerTable.getEmployeeId());
                        }

                        if (!TextUtils.isEmpty(plannerTable.getEmployeeName())) {
                            etEmpName.setText(plannerTable.getEmployeeName());
                        }

                        if (!TextUtils.isEmpty(plannerTable.getDesignation())) {
                            etDesignation.setText(plannerTable.getDesignation());
                        }

                    } else {
                        rb_along_with_staff_no.setChecked(true);
                        llOtherEmp.setVisibility(View.GONE);
                        llEmpId.setVisibility(View.INVISIBLE);
                    }

                    if (plannerTable.isTravelWithOwnVehicle()) {
                        rb_own_vehicle_yes.setChecked(true);
                        ll_vehicle_type.setVisibility(View.VISIBLE);
                    } else {
                        rb_own_vehicle_no.setChecked(true);
                        ll_vehicle_type.setVisibility(View.GONE);
                    }

                    if (plannerTable.isVehicleType()) {
                        rb_vehicle_type_TW.setChecked(true);
                    } else {
                        rb_vehicle_type_FW.setChecked(true);
                    }
                }
            }
        }catch (Exception ex){
            ex.printStackTrace();
            INSERT_LOG("uIChanges","Exception : "+ex.getMessage());
        }
    }

    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void selectItemDrawer(MenuItem menuItem) {
        try {
            switch (menuItem.getItemId()) {
                case R.id.menu_item_new_lead:
                    drawerLayout.closeDrawer(Gravity.LEFT);
                   /* appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT,
                            "This Feature Is Not Yet Developed");*/
                    break;
                case R.id.menu_item_tasks:
                    appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT,
                            "This Feature Is Not Yet Developed");
                    break;
                case R.id.menu_item_search:
                    appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT,
                            "This Feature Is Not Yet Developed");
                    break;
                case R.id.menu_item_settings:
                    appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT,
                            "This Feature Is Not Yet Developed");
                    break;
                case R.id.menu_item_logout:
                    appHelper.getDialogHelper().getConfirmationDialog().showTwoButtons("Do you want to logout ? ", new ConfirmationDialog.ActionCallback() {
                        @Override
                        public void onAction() {
                            finish();
                        }
                    });
                    break;
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            INSERT_LOG("selectItemDrawer","Exception : "+ex.getMessage());
        }
    }

    private void setUpDrawerContent(NavigationView navigationView) {
        try {
            navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    selectItemDrawer(menuItem);
                    return true;
                }
            });
        } catch (Exception ex) {
            ex.printStackTrace();
            INSERT_LOG("setUpDrawerContent","Exception : "+ex.getMessage());
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i(TAG, "requestcode---> " + requestCode + ", resultcode----> " + resultCode);
        try {
            if ((requestCode == REQUEST_CODE_NEW_LEAD || requestCode == REQUEST_CODE_UPDATE_LEAD) && resultCode == RESULT_OK) {
                if (data != null) {
                    String applicationid = data.getStringExtra("result");
                   if (!TextUtils.isEmpty(applicationid)){
                       etApplicationId.setText(applicationid);
                   }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            INSERT_LOG("onActivityResult","Exception : "+e.getMessage());
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }


    private void INSERT_LOG(String methodName,String message){
        try{
            if(viewModel !=null){
                viewModel.insertLog(methodName,message,userId,"","PlannerActivity"
                        ,CLIENT_ID,loanType,moduleType);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
