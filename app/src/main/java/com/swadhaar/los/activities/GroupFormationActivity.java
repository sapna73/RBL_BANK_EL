package com.swadhaar.los.activities;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.Selection;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.DigitsKeyListener;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatSpinner;
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
import com.bfil.uilibrary.widgets.customSpinner.SearchableSpinner;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;
import com.swadhaar.los.R;
import com.swadhaar.los.adapter.GroupFormationAdapter;
import com.swadhaar.los.constants.AppConstant;
import com.swadhaar.los.database.entity.CGTTable;
import com.swadhaar.los.database.entity.GroupTable;
import com.swadhaar.los.database.entity.RawDataTable;
import com.swadhaar.los.helper.MyDividerItemDecoration;
import com.swadhaar.los.models.GroupNameRequestDTO;
import com.swadhaar.los.view_models.DynamicUIViewModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

import static com.swadhaar.los.constants.AppConstant.DATE_FORMAT_DD_MM_YYYY2;
import static com.swadhaar.los.constants.AppConstant.MODULE_TYPE_CGT;
import static com.swadhaar.los.constants.AppConstant.PARAM_ACTION_TO_DO;
import static com.swadhaar.los.constants.AppConstant.PARAM_BRANCH_GST_CODE;
import static com.swadhaar.los.constants.AppConstant.PARAM_BRANCH_ID;
import static com.swadhaar.los.constants.AppConstant.PARAM_CGT_TABLE_JSON;
import static com.swadhaar.los.constants.AppConstant.PARAM_CLIENT_ID;
import static com.swadhaar.los.constants.AppConstant.PARAM_GROUP_NAME;
import static com.swadhaar.los.constants.AppConstant.PARAM_LOAN_TYPE;
import static com.swadhaar.los.constants.AppConstant.PARAM_MODULE_TYPE;
import static com.swadhaar.los.constants.AppConstant.PARAM_PRODUCT_ID;
import static com.swadhaar.los.constants.AppConstant.PARAM_USER_ID;
import static com.swadhaar.los.constants.AppConstant.PARAM_USER_NAME;
import static com.swadhaar.los.dynamicui.constants.ParametersConstant.ACTION_VIEW_GROUP_MEMBERS;
import static com.swadhaar.los.dynamicui.constants.ParametersConstant.ACTION_VIEW_REMAINING_MEMBERS;
import static com.swadhaar.los.dynamicui.constants.ParametersConstant.ERROR_MESSAGE_GROUP_CREATED_SUCCESSFULLY;
import static com.swadhaar.los.dynamicui.constants.ParametersConstant.SP_NAME_TO_GET_GROUP_NAME;

public class GroupFormationActivity extends LOSBaseActivity implements GroupFormationAdapter.SyncCallbackInterface {


    private static final String TAG = GroupFormationActivity.class.getCanonicalName();
    RecyclerView rvLeadDetails;
    SearchView searchByPhoneNo;
    AppCompatSpinner spinnerSortByDate, spinnerSortByLoanType, spinnerSortByInterested;
    GroupFormationAdapter groupFormationAdapter;
    List<List<GroupTable>> groupTableList;
    private AlertDialog notificationDialog;

    private Toolbar toolbar;
    TextView tvClientName;
    String SCREEN_NO;

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView navigationView;
    CoordinatorLayout coordinatorLayout;
    ImageView ivStaffImage;
    RelativeLayout rlNoLeads;
    TextView tvStaffId, tvStaffName, tvSOBDate;
    FloatingActionButton btnNewTarget;

    CGTTable CGT_TABLE;

//    public String userName,userId,branchId;

    List<RawDataTable> allClientRawDataTableList;

    @Inject
    public ViewModelProvider.Factory viewModelFactory;
    public DynamicUIViewModel viewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_group_formation);
        rvLeadDetails = (RecyclerView) findViewById(R.id.rv_lead_details);
        rlNoLeads = (RelativeLayout) findViewById(R.id.rl_no_leads);
        searchByPhoneNo = (SearchView) findViewById(R.id.sv_phone_no);
        btnNewTarget = findViewById(R.id.fb_add_target_details);
        spinnerSortByDate = (AppCompatSpinner) findViewById(R.id.sp_sort_by_date);
        spinnerSortByLoanType = (AppCompatSpinner) findViewById(R.id.sp_sort_by_loan_type);
        spinnerSortByInterested = (AppCompatSpinner) findViewById(R.id.sp_sort_by_interest_or_not);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
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

        userName = getIntent().getStringExtra(PARAM_USER_NAME);
        userId = getIntent().getStringExtra(PARAM_USER_ID);
        branchId = getIntent().getStringExtra(PARAM_BRANCH_ID);
        branchGSTcode = getIntent().getStringExtra(PARAM_BRANCH_GST_CODE);
        loanType = getIntent().getStringExtra(PARAM_LOAN_TYPE);
        productId = getIntent().getStringExtra(PARAM_PRODUCT_ID);
        CLIENT_ID = getIntent().getStringExtra(PARAM_CLIENT_ID);
        String cgtJsonString = getIntent().getStringExtra(PARAM_CGT_TABLE_JSON);
        if (!TextUtils.isEmpty(cgtJsonString)) {
            CGT_TABLE = new Gson().fromJson(cgtJsonString, CGTTable.class);
            if (CGT_TABLE != null) {
            }
        }

        if (!TextUtils.isEmpty(userName)) {
            tvClientName.setText(userName.toUpperCase());
            tvStaffName.setText(userName.toUpperCase());
        }
        if (!TextUtils.isEmpty(userId)) {
            tvStaffId.setText(userId);
        }
        String currentDate = appHelper.getCurrentDate(DATE_FORMAT_DD_MM_YYYY2);
        if (!TextUtils.isEmpty(currentDate)) {
            tvSOBDate.setText("SOD DATE : " + currentDate);
        }
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        rvLeadDetails.setLayoutManager(mLayoutManager);
        rvLeadDetails.setItemAnimator(new DefaultItemAnimator());
        rvLeadDetails.addItemDecoration(new MyDividerItemDecoration(this, DividerItemDecoration.VERTICAL, 36));
        rvLeadDetails.setNestedScrollingEnabled(false);

        spinnerSortByDate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0) {

                    spinnerSortByLoanType.setSelection(0);
                    spinnerSortByInterested.setSelection(0);
                    searchByPhoneNo.setQuery("", false);
                    searchByPhoneNo.setIconified(true);
                    if (groupFormationAdapter != null && groupFormationAdapter.getFilter() != null) {
                        groupFormationAdapter.getFilter().filter(parent.getSelectedItem().toString());
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerSortByLoanType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0) {

                    spinnerSortByDate.setSelection(0);
                    spinnerSortByInterested.setSelection(0);
                    searchByPhoneNo.setQuery("", false);
                    searchByPhoneNo.setIconified(true);
                    if (groupFormationAdapter != null && groupFormationAdapter.getFilter() != null) {
                        groupFormationAdapter.getFilter().filter(parent.getSelectedItem().toString());
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerSortByInterested.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0) {

                    spinnerSortByDate.setSelection(0);
                    spinnerSortByLoanType.setSelection(0);
                    searchByPhoneNo.setQuery("", false);
                    searchByPhoneNo.setIconified(true);

                    if (groupFormationAdapter != null && groupFormationAdapter.getFilter() != null) {
                        groupFormationAdapter.getFilter().filter(parent.getSelectedItem().toString());
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

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
                spinnerSortByDate.setSelection(0);
                spinnerSortByLoanType.setSelection(0);
                spinnerSortByInterested.setSelection(0);

                if (groupFormationAdapter != null && groupFormationAdapter.getFilter() != null) {
                    groupFormationAdapter.getFilter().filter(query);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                // filter recycler view when text is changed
                spinnerSortByDate.setSelection(0);
                spinnerSortByLoanType.setSelection(0);
                spinnerSortByInterested.setSelection(0);

                if (groupFormationAdapter != null && groupFormationAdapter.getFilter() != null) {
                    groupFormationAdapter.getFilter().filter(query);
                }
                return false;
            }
        });

        btnNewTarget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    popUpGroupNameCreationPopUp(new GroupCreationCallback() {
                        @Override
                        public void onCreateGroupName(String zoneName, String areaName) {
                            callGroupNameCreationService(zoneName, areaName);
                        }
                    });
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        configureDagger();
        configureViewModel();


    }

    private void callGroupNameCreationService(String zoneName, String areaName) {
        try {

            appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();

            final GroupNameRequestDTO groupNameRequestDTO = new GroupNameRequestDTO();

            groupNameRequestDTO.setIMEINumber(appHelper.getIMEI());
            GroupNameRequestDTO.SpNameWithParameterClass spNameWithParameter = new GroupNameRequestDTO.SpNameWithParameterClass();
            spNameWithParameter.setSpName(SP_NAME_TO_GET_GROUP_NAME);
            GroupNameRequestDTO.SpParametersClass spParametersClass = new GroupNameRequestDTO.SpParametersClass();
            spParametersClass.setStaffId(userId); // TODO: user id
            spParametersClass.setBranchId(branchId); // TODO: branch id
            spParametersClass.setVillageName(areaName); // TODO: area name
            if(!TextUtils.isEmpty(zoneName)) {
                String sequenceNumber = String.valueOf(zoneName.charAt(zoneName.length() - 1));
                spParametersClass.setSequenceNumber(sequenceNumber); // TODO: Sequence number
            }
            spNameWithParameter.setSpParameters(spParametersClass);
            ArrayList<GroupNameRequestDTO.SpNameWithParameterClass> SpNameWithParameterList = new ArrayList<GroupNameRequestDTO.SpNameWithParameterClass>();
            SpNameWithParameterList.add(spNameWithParameter);
            groupNameRequestDTO.setSpNameWithParameter(SpNameWithParameterList);

            viewModel.getGroupNameFromServer(groupNameRequestDTO);
            if (viewModel.getStringLiveData() != null) {
                Observer getLeadRawDataObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        String groupName = (String) o;
                        viewModel.getStringLiveData().removeObserver(this);

                        appHelper.getDialogHelper().getLoadingDialog().closeDialog();

                        if ( ! TextUtils.isEmpty(groupName)) {
                            appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.SUCCESS,
                                    ERROR_MESSAGE_GROUP_CREATED_SUCCESSFULLY, new ConfirmationDialog.ActionCallback() {
                                        @Override
                                        public void onAction() {
                                            // TODO: Calling Group Name Activity
                                           callGroupMemberActivity(groupName);
                                        }
                                    });

                        }else{
                            appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, "Group creation failed, please try again!");

                        }
                    }
                };
                viewModel.getStringLiveData().observe(this, getLeadRawDataObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            appHelper.getDialogHelper().getLoadingDialog().closeDialog();

        }
    }

    private void callGroupMemberActivity(String groupName) {
        try {
            String timeStamp = new SimpleDateFormat("yyMMddHHmmss",
                    Locale.getDefault()).format(new Date());
            if (!TextUtils.isEmpty(EMP_LAST_5_DIGIT)) {
                if (!TextUtils.isEmpty(userId)) {
                    EMP_LAST_5_DIGIT = userId.substring(3);
                    CLIENT_ID = EMP_LAST_5_DIGIT + timeStamp;
                    // TODO: CLIENT ID FORMATION ( FIRST 7 DIGIT EMP ID - 100004141 , YEAR 19 ,
                    //  MONTH 11 , DATE 12, HOUR 08 , MINUTE 25 , SECOND 48 ==> TOTAL 19 DIGITS )
                    if (!TextUtils.isEmpty(CLIENT_ID) && CLIENT_ID.length() > 16) {
                        Intent intentApplication = new Intent(GroupFormationActivity.this, GroupMembersActivity.class);
                        intentApplication.putExtra(AppConstant.PARAM_LOAN_TYPE, loanType);
                        intentApplication.putExtra(PARAM_USER_NAME, userName);
                        intentApplication.putExtra(PARAM_USER_ID, userId);
                        intentApplication.putExtra(PARAM_BRANCH_ID, branchId);
                        intentApplication.putExtra(PARAM_BRANCH_GST_CODE, branchGSTcode);
                        intentApplication.putExtra(PARAM_CLIENT_ID, CLIENT_ID);
                        intentApplication.putExtra(PARAM_MODULE_TYPE, MODULE_TYPE_CGT);
                        intentApplication.putExtra(PARAM_ACTION_TO_DO, ACTION_VIEW_REMAINING_MEMBERS);

                        String grtTableJsonAttendance = new Gson().toJson(CGT_TABLE, CGTTable.class);
                        if (!TextUtils.isEmpty(grtTableJsonAttendance)) {
                            intentApplication.putExtra(PARAM_CGT_TABLE_JSON, grtTableJsonAttendance);
                        }
                        intentApplication.putExtra(PARAM_GROUP_NAME, groupName); // TODO: Group Name

                        startActivity(intentApplication);

                    } else {

                        appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, "Invalid Client ID");
                    }
                } else {
                    appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, "User ID Is Empty");
                }
            } else {
                appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, "Employee ID Is Empty");
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
            spinnerSortByDate.setSelection(0);
            spinnerSortByLoanType.setSelection(0);
            spinnerSortByInterested.setSelection(0);
            searchByPhoneNo.setQuery("", false);
            searchByPhoneNo.setIconified(true);

            userName = getIntent().getStringExtra(PARAM_USER_NAME);
            userId = getIntent().getStringExtra(PARAM_USER_ID);
            branchId = getIntent().getStringExtra(PARAM_BRANCH_ID);
            branchGSTcode = getIntent().getStringExtra(PARAM_BRANCH_GST_CODE);
            loanType = getIntent().getStringExtra(PARAM_LOAN_TYPE);
            productId = getIntent().getStringExtra(PARAM_PRODUCT_ID);
            CLIENT_ID = getIntent().getStringExtra(PARAM_CLIENT_ID);
            String cgtJsonString = getIntent().getStringExtra(PARAM_CGT_TABLE_JSON);
            if (!TextUtils.isEmpty(cgtJsonString)) {
                CGT_TABLE = new Gson().fromJson(cgtJsonString, CGTTable.class);
                if (CGT_TABLE != null) {

                    // TODO: Get GRT Table
                    getGroupTable();
                }
            }


        } catch (Exception ex) {
            ex.printStackTrace();
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
        }
    }


    @Override
    public void onBackPressed() {
        finish();

    }

    public void getGroupTable() {
        appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();

        try {
            viewModel.getGroupTable(CGT_TABLE);
            if (viewModel.getListOfGroupTableLiveDataList() != null) {
                Observer observer = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        appHelper.getDialogHelper().getLoadingDialog().closeDialog();
                        groupTableList = (List<List<GroupTable>>) o;
                        viewModel.getListOfGroupTableLiveDataList().removeObserver(this);

                        if (groupTableList != null && groupTableList.size() > 0) {
                            rlNoLeads.setVisibility(View.GONE);
                            rvLeadDetails.setVisibility(View.VISIBLE);

                            if (groupFormationAdapter != null) {
                                groupFormationAdapter.setItem(groupTableList);
                            } else {
                                groupFormationAdapter = new GroupFormationAdapter(GroupFormationActivity.this, groupTableList,
                                        GroupFormationActivity.this, appHelper);
                                rvLeadDetails.setAdapter(groupFormationAdapter);
                                groupFormationAdapter.notifyDataSetChanged();
                            }

                        } else {
                            rvLeadDetails.setVisibility(View.GONE);
                            rlNoLeads.setVisibility(View.VISIBLE);
                        }
                    }
                };
                viewModel.getListOfGroupTableLiveDataList().observe(this, observer);
            }
        } catch (Exception ex) {
            appHelper.getDialogHelper().getLoadingDialog().closeDialog();
            ex.printStackTrace();
        }
    }


    @Override
    public void viewMemberCallBack(int position, List<GroupTable> groupTableList) {
        try {
            Intent intentApplication = new Intent(GroupFormationActivity.this, GroupMembersActivity.class);
            intentApplication.putExtra(AppConstant.PARAM_LOAN_TYPE, loanType);
            intentApplication.putExtra(PARAM_USER_NAME, userName);
            intentApplication.putExtra(PARAM_USER_ID, userId);
            intentApplication.putExtra(PARAM_BRANCH_ID, branchId);
            intentApplication.putExtra(PARAM_BRANCH_GST_CODE, branchGSTcode);
            intentApplication.putExtra(PARAM_CLIENT_ID, groupTableList.get(0).getGroupId());
            intentApplication.putExtra(PARAM_MODULE_TYPE, MODULE_TYPE_CGT);

            String grtTableJsonAttendance = new Gson().toJson(CGT_TABLE, CGTTable.class);
            if (!TextUtils.isEmpty(grtTableJsonAttendance)) {
                intentApplication.putExtra(PARAM_CGT_TABLE_JSON, grtTableJsonAttendance);
            }
            intentApplication.putExtra(PARAM_GROUP_NAME, groupTableList.get(0).getGroupName());
            intentApplication.putExtra(PARAM_ACTION_TO_DO, ACTION_VIEW_GROUP_MEMBERS);
            startActivity(intentApplication);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void addMemberCallBack(int position, List<GroupTable> groupTableList) {
        try {
            Intent intentApplication = new Intent(GroupFormationActivity.this, GroupMembersActivity.class);
            intentApplication.putExtra(AppConstant.PARAM_LOAN_TYPE, loanType);
            intentApplication.putExtra(PARAM_USER_NAME, userName);
            intentApplication.putExtra(PARAM_USER_ID, userId);
            intentApplication.putExtra(PARAM_BRANCH_ID, branchId);
            intentApplication.putExtra(PARAM_BRANCH_GST_CODE, branchGSTcode);
            intentApplication.putExtra(PARAM_CLIENT_ID, groupTableList.get(0).getGroupId());
            intentApplication.putExtra(PARAM_MODULE_TYPE, MODULE_TYPE_CGT);

            String grtTableJsonAttendance = new Gson().toJson(CGT_TABLE, CGTTable.class);
            if (!TextUtils.isEmpty(grtTableJsonAttendance)) {
                intentApplication.putExtra(PARAM_CGT_TABLE_JSON, grtTableJsonAttendance);
            }
            intentApplication.putExtra(PARAM_GROUP_NAME, groupTableList.get(0).getGroupName());
            intentApplication.putExtra(PARAM_ACTION_TO_DO, ACTION_VIEW_REMAINING_MEMBERS);
            startActivity(intentApplication);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void chooseLeaderCallBack(int position, List<GroupTable> groupTableListFromAdapter) {
        try {
            showChooseLeaderPopUp(new ActionCallback() {
                @Override
                public void onAction(List<GroupTable> groupTableList) {
                    insertAndDeleteGroupTableListByMemberId(groupTableList);
                }
            }, groupTableListFromAdapter);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void insertAndDeleteGroupTableListByMemberId(List<GroupTable> groupTableList) {
        try {
            viewModel.insertAndDeleteGroupTableListByMemberId(groupTableList);
            if (viewModel.getGroupTableLiveDataList() != null) {
                Observer observer = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        // TODO: Get GRT Table
                        getGroupTable();
                    }
                };
                viewModel.getGroupTableLiveDataList().observe(this, observer);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void INSERT_LOG(String methodName, String message) {
        try {
            if (viewModel != null) {
                viewModel.insertLog(methodName, message, userId, "", "CenterCreationActivity"
                        , CLIENT_ID, loanType, moduleType);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    public void showChooseLeaderPopUp(final ActionCallback callback, List<GroupTable> groupTableList) {

        notificationDialog = new AlertDialog.Builder(this).create();

        View notificationDialogView = LayoutInflater.from(this).inflate(R.layout.
                popup_choose_leader, null);
        notificationDialog.setView(notificationDialogView);
        notificationDialog.setCancelable(false);

        Button btnSave = (Button) notificationDialogView.findViewById(R.id.btn_popup_yes);
        Button btnCancel = (Button) notificationDialogView.findViewById(R.id.btn_popup_no);
        SearchableSpinner spLeader1 = (SearchableSpinner) notificationDialogView.findViewById(R.id.sp_leader1);
        SearchableSpinner spLeader2 = (SearchableSpinner) notificationDialogView.findViewById(R.id.sp_leader2);

        if (groupTableList != null && groupTableList.size() > 0) {
            List<String> memberList = new ArrayList<>();
            memberList.add("Choose Leader");
            for (GroupTable groupTable : groupTableList) {
                if (groupTable != null && !TextUtils.isEmpty(groupTable.getClientName())) {
                    memberList.add(groupTable.getClientName());
                }
            }

            if (memberList.size() > 0) {
                ArrayAdapter arrayAdapter = new ArrayAdapter(GroupFormationActivity.this, R.layout.view_spinner_textheight, memberList);
                spLeader1.setAdapter(arrayAdapter);
                spLeader2.setAdapter(arrayAdapter);
            }
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
                    if (callback != null) {

                        if (spLeader1.getSelectedItemPosition() > 0) {
                            setLeader(groupTableList, true, spLeader1.getSelectedItemPosition() - 1);
                        } else {
                            Toast.makeText(GroupFormationActivity.this, "Please Choose Leader 1", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if (spLeader2.getSelectedItemPosition() > 0) {
                            setLeader(groupTableList, false, spLeader2.getSelectedItemPosition() - 1);
                        } else {
                            Toast.makeText(GroupFormationActivity.this, "Select Choose Leader 2", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        closeDialog();
                        callback.onAction(groupTableList);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        notificationDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        notificationDialog.show();
    }

    private List<GroupTable> setLeader(List<GroupTable> groupTableList, boolean isLeader1, int position) {
        try {
            if (groupTableList != null && groupTableList.size() > 0) {
                for (int i = 0; i < groupTableList.size(); i++) {
                    GroupTable groupTable = groupTableList.get(i);
                    if (isLeader1) {
                        groupTable.setTeamLeaderOne(false);
                        if (i == position) {
                            groupTable.setTeamLeaderOne(true);
                        }
                    } else {
                        groupTable.setTeamLeaderTwo(false);
                        if (i == position) {
                            groupTable.setTeamLeaderTwo(true);
                        }
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return groupTableList;
    }

    public void closeDialog() {
        try {
            if (notificationDialog != null && notificationDialog.isShowing()) {
                notificationDialog.dismiss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public interface ActionCallback {
        void onAction(List<GroupTable> groupTableList);
    }

    public interface GroupCreationCallback {
        void onCreateGroupName(String zoneName, String areaName);
    }


    public void popUpGroupNameCreationPopUp(final GroupCreationCallback callback) {

        notificationDialog = new AlertDialog.Builder(this).create();

        View notificationDialogView = LayoutInflater.from(this).inflate(R.layout.
                popup_create_group_name, null);
        notificationDialog.setView(notificationDialogView);
        notificationDialog.setCancelable(false);

        Button btnSave = (Button) notificationDialogView.findViewById(R.id.btn_popup_yes);
        Button btnCancel = (Button) notificationDialogView.findViewById(R.id.btn_popup_no);
        TextInputLayout tilZoneName = (TextInputLayout) notificationDialogView.findViewById(R.id.til_zone_name);
        TextInputLayout tilAreaName = (TextInputLayout) notificationDialogView.findViewById(R.id.til_area_name);

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
                if (!s.toString().startsWith("Z")) {
                    edtZoneName.setText("Z");
                    Selection.setSelection(edtZoneName.getText(), edtZoneName.getText().length());

                }

            }
        });

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
                    if (callback != null) {

                        String zoneName = tilZoneName.getEditText().getText().toString();
                        String areaName = tilAreaName.getEditText().getText().toString();

                        if (zoneName.length() == 2) {
                            if (!TextUtils.isEmpty(areaName)) {
                                closeDialog();
                                callback.onCreateGroupName(zoneName, areaName);
                            } else {
                                Toast.makeText(GroupFormationActivity.this, "Please Enter Area Name", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(GroupFormationActivity.this, "Please Enter Zone ID", Toast.LENGTH_SHORT).show();
                        }

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        notificationDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        notificationDialog.show();
    }

}
