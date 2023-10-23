package com.saartak.el.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.Selection;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.view.ActionMode;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.bfil.uilibrary.dialogs.ConfirmationDialog;
import com.bfil.uilibrary.widgets.customSpinner.SearchableSpinner;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;
import com.saartak.el.BuildConfig;
import com.saartak.el.R;
import com.saartak.el.database.entity.CenterCreationTable;
import com.saartak.el.view_models.DynamicUIViewModel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

import static com.saartak.el.constants.AppConstant.DATE_FORMAT_DD_MM_YYYY2;
import static com.saartak.el.constants.AppConstant.MODULE_TYPE_APPLICANT;
import static com.saartak.el.constants.AppConstant.PARAM_BRANCH_GST_CODE;
import static com.saartak.el.constants.AppConstant.PARAM_BRANCH_ID;
import static com.saartak.el.constants.AppConstant.PARAM_CENTER_TABLE_JSON;
import static com.saartak.el.constants.AppConstant.PARAM_CLIENT_ID;
import static com.saartak.el.constants.AppConstant.PARAM_LOAN_TYPE;
import static com.saartak.el.constants.AppConstant.PARAM_MODULE_TYPE;
import static com.saartak.el.constants.AppConstant.PARAM_PRODUCT_ID;
import static com.saartak.el.constants.AppConstant.PARAM_USER_ID;
import static com.saartak.el.constants.AppConstant.PARAM_USER_NAME;
import static com.saartak.el.dynamicui.constants.ParametersConstant.ERROR_MESSAGE_CENTER_CREATED_SUCCESSFULLY;
import static com.saartak.el.dynamicui.constants.ParametersConstant.FINAL_STATUS_PENDING;

public class CreateNewCenterActivity extends LOSBaseActivity {

    private static final String TAG = CreateNewCenterActivity.class.getCanonicalName();

    private Toolbar toolbar;

    private ActionMode actionMode;

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView navigationView;
    CoordinatorLayout coordinatorLayout;
    TextView tvVillage,tvCenterId,tvCenterName, tvZoneName, tvAreaName;
    SearchableSpinner spVillage;
    Button btnCreateNewCenter,btnAddTargetDetails;
    TextInputLayout tilCenterId,tilCenterName, tilZoneName, tilAreaName;
    TextView tvAppVersion,tvCurrentDate,tvUserName;

    CenterCreationTable CENTER_CREATION_TABLE;

    @Inject
    public ViewModelProvider.Factory viewModelFactory;
    public DynamicUIViewModel viewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_create_new_center);

        toolbar = (Toolbar)findViewById(R.id.toolbar_eligibility);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // back button pressed
                finish();
            }
        });

        tvUserName = (TextView)findViewById(R.id.tv_user_name);
        tvCurrentDate = (TextView)findViewById(R.id.tv_currentdate);
        tvAppVersion = (TextView)findViewById(R.id.tv_app_version);
        tvVillage=(TextView)findViewById(R.id.tv_label_VillageName);
        tvCenterId=(TextView)findViewById(R.id.tv_label_center_id);
        tvCenterName=(TextView)findViewById(R.id.tv_label_center_name);
        tvZoneName=(TextView)findViewById(R.id.tv_label_zone_name);
        tvAreaName=(TextView)findViewById(R.id.tv_label_area_name);
        btnCreateNewCenter=(Button)findViewById(R.id.btn_create_new_center);
        btnAddTargetDetails=(Button)findViewById(R.id.btn_add_target_details);
        tilCenterId=(TextInputLayout)findViewById(R.id.til_center_id);
        tilCenterName=(TextInputLayout)findViewById(R.id.til_center_name);
        tilZoneName=(TextInputLayout)findViewById(R.id.til_zone_name);
        tilAreaName=(TextInputLayout)findViewById(R.id.til_area_name);

        spVillage=(SearchableSpinner) findViewById(R.id.spn_village);


        final EditText edtZoneName = tilZoneName.getEditText();

        edtZoneName.setText("Z");
        Selection.setSelection(edtZoneName.getText(), edtZoneName.getText().length());


        edtZoneName.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO Auto-generated method stub

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!s.toString().startsWith("Z")){
                    edtZoneName.setText("Z");
                    Selection.setSelection(edtZoneName.getText(), edtZoneName.getText().length());

                }

            }
        });

        SpannableStringBuilder builder_village = new SpannableStringBuilder("Village*");
        builder_village.setSpan(new ForegroundColorSpan(Color.RED), builder_village.length()-1, builder_village.length(),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvVillage.setText(builder_village);

          SpannableStringBuilder builder_centerId = new SpannableStringBuilder("Center Id*");
        builder_centerId.setSpan(new ForegroundColorSpan(Color.RED), builder_centerId.length()-1, builder_centerId.length(),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvCenterId.setText(builder_centerId);

        SpannableStringBuilder builder_zoneName = new SpannableStringBuilder("Zone name*");
        builder_zoneName.setSpan(new ForegroundColorSpan(Color.RED), builder_zoneName.length()-1, builder_zoneName.length(),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvZoneName.setText(builder_zoneName);

        SpannableStringBuilder builder_areaName = new SpannableStringBuilder("Area Name*");
        builder_areaName.setSpan(new ForegroundColorSpan(Color.RED), builder_areaName.length()-1, builder_areaName.length(),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvAreaName.setText(builder_areaName);

        /* SpannableStringBuilder builder_centerName = new SpannableStringBuilder("Center Name*");
        builder_centerName.setSpan(new ForegroundColorSpan(Color.RED), builder_centerName.length()-1, builder_centerName.length(),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvCenterName.setText(builder_centerName);*/


        userName = getIntent().getStringExtra(PARAM_USER_NAME);
        userId = getIntent().getStringExtra(PARAM_USER_ID);
        branchId = getIntent().getStringExtra(PARAM_BRANCH_ID);
        branchGSTcode = getIntent().getStringExtra(PARAM_BRANCH_GST_CODE);
        loanType = getIntent().getStringExtra(PARAM_LOAN_TYPE);
        productId = getIntent().getStringExtra(PARAM_PRODUCT_ID);
        CLIENT_ID = getIntent().getStringExtra(PARAM_CLIENT_ID);

        if(!TextUtils.isEmpty(CLIENT_ID)){
            tilCenterId.getEditText().setText(CLIENT_ID);
        }

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


        btnAddTargetDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent targetDetails = new Intent(CreateNewCenterActivity.this, TargetDetailsActivity.class);
//                targetDetails.putExtra(PARAM_LOAN_TYPE, loanType);
//                targetDetails.putExtra(PARAM_USER_NAME, userName);
//                targetDetails.putExtra(PARAM_BRANCH_ID, branchId);
//                targetDetails.putExtra(PARAM_BRANCH_GST_CODE, branchGSTcode);
//                targetDetails.putExtra(PARAM_USER_ID, userId);
//                targetDetails.putExtra(PARAM_MODULE_TYPE, MODULE_TYPE_TARGET_DETAILS);
//                targetDetails.putExtra(PARAM_PRODUCT_ID, productId);
//                String centerTableJson = new Gson().toJson(CENTER_CREATION_TABLE, CenterCreationTable.class);
//                if (!TextUtils.isEmpty(centerTableJson)) {
//                    targetDetails.putExtra(PARAM_CENTER_TABLE_JSON, centerTableJson);
//                    targetDetails.putExtra(PARAM_CLIENT_ID, CENTER_CREATION_TABLE.getCenterId());
//                }
//                startActivity(targetDetails);


                String timeStamp = new SimpleDateFormat("yyMMddHHmmss",
                        Locale.getDefault()).format(new Date());
                if( ! TextUtils.isEmpty(EMP_LAST_5_DIGIT)) {
                    if (!TextUtils.isEmpty(userId)) {
                        EMP_LAST_5_DIGIT = userId.substring(3);
                        CLIENT_ID = EMP_LAST_5_DIGIT + timeStamp;
                        // TODO: CLIENT ID FORMATION ( FIRST 7 DIGIT EMP ID - 100004141 , YEAR 19 ,
                        //  MONTH 11 , DATE 12, HOUR 08 , MINUTE 25 , SECOND 48 ==> TOTAL 19 DIGITS )
                        if (!TextUtils.isEmpty(CLIENT_ID) && CLIENT_ID.length() > 12) {
//                            Intent intentApplication = new Intent(TargetDetailsActivity.this, MemberDetailsActivity.class);
                            Intent applicant = new Intent(CreateNewCenterActivity.this, BaseActivity.class);
                            applicant.putExtra(PARAM_LOAN_TYPE, loanType);
                            applicant.putExtra(PARAM_USER_NAME, userName);
                            applicant.putExtra(PARAM_USER_ID, userId);
                            applicant.putExtra(PARAM_CLIENT_ID, CLIENT_ID);
                            applicant.putExtra(PARAM_BRANCH_ID, branchId);
                            applicant.putExtra(PARAM_BRANCH_GST_CODE, branchGSTcode);
                            applicant.putExtra(PARAM_MODULE_TYPE, MODULE_TYPE_APPLICANT);
                            if(  CENTER_CREATION_TABLE !=null){
                                String centerTableJson=new Gson().toJson(CENTER_CREATION_TABLE, CenterCreationTable.class);
                                if(!TextUtils.isEmpty(centerTableJson)){
                                    applicant.putExtra(PARAM_CENTER_TABLE_JSON, centerTableJson);
                                }
                            }
                            startActivity(applicant);
                        } else {

                            appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, "Invalid Client ID");
                        }
                    } else {
                        appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, "User ID Is Empty");
                    }
                }else {
                    appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, "Employee ID Is Empty");
                }
            }
        });
        btnCreateNewCenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!TextUtils.isEmpty(CLIENT_ID)) {
                    String centerName=tilCenterName.getEditText().getText().toString();
                    String zoneName = tilZoneName.getEditText().getText().toString();
                    String areaName = tilAreaName.getEditText().getText().toString().trim();

                        if (spVillage.getSelectedItemPosition() > 0) {
                            if (zoneName.length() == 2) {
                                if(!TextUtils.isEmpty(areaName)) {
                                    String selectedVillage = spVillage.getSelectedItem().toString();

                                    // TODO: INSERT INTO CENTER CREATION TABLE
                                    CenterCreationTable centerCreationTable = new CenterCreationTable();
                                    centerCreationTable.setCenterId(CLIENT_ID);
                                    centerCreationTable.setCenterName(centerName);
                                    centerCreationTable.setVillageName(selectedVillage);
                                    centerCreationTable.setZoneName(zoneName);
                                    centerCreationTable.setAreaName(areaName);
                                    centerCreationTable.setStatus(FINAL_STATUS_PENDING);
                                    centerCreationTable.setSync(false);
                                    centerCreationTable.setLoan_type(loanType); // TODO: LOAN TYPE

                                    if (!TextUtils.isEmpty(LOSBaseActivity.branchId)) {
                                        centerCreationTable.setBranchId(LOSBaseActivity.branchId);
                                    }
                                    if (!TextUtils.isEmpty(branchGSTcode)) {
                                        centerCreationTable.setBranchGSTcode(branchGSTcode);
                                    }
                                    centerCreationTable.setCreatedBy(userId); // TODO: STAFF ID
                                    String dateTime = appHelper.getCurrentDateTime(DATE_FORMAT_DD_MM_YYYY2);
                                    centerCreationTable.setCreated_date(dateTime);

                                    insertOrUpdateCenterCreationData(centerCreationTable);
                                }else{
                                    appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, "Enter Area Name");

                                }

                            }else{
                                appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, "Enter Zone Name");

                            }

                        } else {
                            appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, "Choose Village");
                        }


                } else {
                    appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, "Invalid Client ID");
                }
            }
        });

        configureDagger();
        configureViewModel();

        getVillagesFromEligibility(userId, loanType);
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
            CLIENT_ID = getIntent().getStringExtra(PARAM_CLIENT_ID);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    @Override
    public void onBackPressed() {
        finish();

    }

    public void getVillagesFromEligibility(String userId, String loanType) {
        appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();

        try {
            viewModel.getVillageList(userId,loanType);
            if (viewModel.getStringListLiveData() != null) {
                Observer observer = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        appHelper.getDialogHelper().getLoadingDialog().closeDialog();
                        List<String> stringList = (List<String>) o;
                        viewModel.getStringListLiveData().removeObserver(this);

                        if(stringList !=null && stringList.size()>0){
                            stringList.add(0,"SELECT VILLAGE");
                                ArrayAdapter arrayAdapter = new ArrayAdapter(CreateNewCenterActivity.this, R.layout.view_spinner_textheight, stringList);
                                spVillage.setAdapter(arrayAdapter);

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

    public void insertOrUpdateCenterCreationData(CenterCreationTable centerCreationTable) {
        try {

            appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();

            viewModel.insertOrUpdateCenterCreationData(centerCreationTable);
            if (viewModel.getCenterCreationTableLiveData() != null) {
                Observer getLeadRawDataObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                         CENTER_CREATION_TABLE = (CenterCreationTable) o;
                        viewModel.getCenterCreationTableLiveData().removeObserver(this);

                        appHelper.getDialogHelper().getLoadingDialog().closeDialog();

                        if (CENTER_CREATION_TABLE != null) {
                            appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.SUCCESS,
                                    ERROR_MESSAGE_CENTER_CREATED_SUCCESSFULLY, new ConfirmationDialog.ActionCallback() {
                                        @Override
                                        public void onAction() {
//                                            btnCreateNewCenter.setText("Center Created");
                                            btnCreateNewCenter.setVisibility(View.GONE);
                                            tilCenterName.getEditText().setText(CENTER_CREATION_TABLE.getCenterName());

                                            tilCenterName.getEditText().setEnabled(false);
                                            tilZoneName.getEditText().setEnabled(false);
                                            tilAreaName.getEditText().setEnabled(false);
                                            spVillage.setEnabled(false);
                                            btnAddTargetDetails.setVisibility(View.VISIBLE);
                                        }
                                    });

                        }else{
                            appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, "Center creation failed, please try again!");

                        }
                    }
                };
                viewModel.getCenterCreationTableLiveData().observe(this, getLeadRawDataObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            appHelper.getDialogHelper().getLoadingDialog().closeDialog();

        }
    }


}
