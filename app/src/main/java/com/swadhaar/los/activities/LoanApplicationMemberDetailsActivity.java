package com.swadhaar.los.activities;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.text.method.DigitsKeyListener;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
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
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;
import com.swadhaar.los.R;
import com.swadhaar.los.adapter.LoanApplicationMemberDetailAdapter;
import com.swadhaar.los.adapter.LeadToApplicationAdapter;
import com.swadhaar.los.adapter.LoanApplicationMemberDetailAdapter;
import com.swadhaar.los.adapter.TargetDetailsAdapter;
import com.swadhaar.los.constants.AppConstant;
import com.swadhaar.los.database.entity.CenterCreationTable;
import com.swadhaar.los.database.entity.LoanTable;
import com.swadhaar.los.database.entity.MasterTable;
import com.swadhaar.los.database.entity.RawDataTable;
import com.swadhaar.los.database.entity.SubmitDataTable;
import com.swadhaar.los.helper.MyDividerItemDecoration;
import com.swadhaar.los.view_models.DynamicUIViewModel;

import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

import static com.swadhaar.los.constants.AppConstant.DATE_FORMAT_DD_MM_YYYY;
import static com.swadhaar.los.constants.AppConstant.DATE_FORMAT_DD_MM_YYYY2;
import static com.swadhaar.los.constants.AppConstant.MODULE_TYPE_TARGET_DETAILS;
import static com.swadhaar.los.constants.AppConstant.PARAM_BRANCH_GST_CODE;
import static com.swadhaar.los.constants.AppConstant.PARAM_BRANCH_ID;
import static com.swadhaar.los.constants.AppConstant.PARAM_CENTER_TABLE_JSON;
import static com.swadhaar.los.constants.AppConstant.PARAM_CLIENT_ID;
import static com.swadhaar.los.constants.AppConstant.PARAM_LOAN_TYPE;
import static com.swadhaar.los.constants.AppConstant.PARAM_MODULE_TYPE;
import static com.swadhaar.los.constants.AppConstant.PARAM_PRODUCT_ID;
import static com.swadhaar.los.constants.AppConstant.PARAM_USER_ID;
import static com.swadhaar.los.constants.AppConstant.PARAM_USER_NAME;

public class LoanApplicationMemberDetailsActivity extends LOSBaseActivity implements LoanApplicationMemberDetailAdapter.SyncCallbackInterface {


private static final String TAG = LoanApplicationMemberDetailsActivity.class.getCanonicalName();
RecyclerView rvLeadDetails;
SearchView searchByPhoneNo;
AppCompatSpinner spinnerSortByDate, spinnerSortByAlphabet;
LoanApplicationMemberDetailAdapter loanApplicationMemberDetailAdapter;

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

//    public String userName,userId,branchId;

//List<RawDataTable> allClientRawDataTableList;

CenterCreationTable CENTER_CREATION_TABLE;

@Inject
public ViewModelProvider.Factory viewModelFactory;
public DynamicUIViewModel viewModel;

@Override
public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.activity_loan_application_member_detail);
    rvLeadDetails = (RecyclerView) findViewById(R.id.rv_lead_details);
    rlNoLeads = (RelativeLayout) findViewById(R.id.rl_no_leads);
    searchByPhoneNo = (SearchView) findViewById(R.id.sv_phone_no);
    btnNewTarget =  findViewById(R.id.fb_add_target_details);
    spinnerSortByDate = (AppCompatSpinner) findViewById(R.id.sp_sort_by_date);
    spinnerSortByAlphabet = (AppCompatSpinner) findViewById(R.id.sp_sort_by_alphabetical_order);
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
    moduleType = getIntent().getStringExtra(PARAM_MODULE_TYPE);
    String centerJsonString = getIntent().getStringExtra(PARAM_CENTER_TABLE_JSON);
    if (!TextUtils.isEmpty(centerJsonString)) {
        CENTER_CREATION_TABLE = new Gson().fromJson(centerJsonString, CenterCreationTable.class);
        if (CENTER_CREATION_TABLE != null) {
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

                spinnerSortByAlphabet.setSelection(0);
                searchByPhoneNo.setQuery("", false);
                searchByPhoneNo.setIconified(true);
                if (loanApplicationMemberDetailAdapter != null && loanApplicationMemberDetailAdapter.getFilter() != null) {
                    loanApplicationMemberDetailAdapter.getFilter().filter(parent.getSelectedItem().toString());
                }
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    });

    spinnerSortByAlphabet.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            if (position > 0) {

                spinnerSortByDate.setSelection(0);
                searchByPhoneNo.setQuery("", false);
                searchByPhoneNo.setIconified(true);

                if (loanApplicationMemberDetailAdapter != null && loanApplicationMemberDetailAdapter.getFilter() != null) {
                    loanApplicationMemberDetailAdapter.getFilter().filter(parent.getSelectedItem().toString());
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
            spinnerSortByAlphabet.setSelection(0);

            if (loanApplicationMemberDetailAdapter != null && loanApplicationMemberDetailAdapter.getFilter() != null) {
                loanApplicationMemberDetailAdapter.getFilter().filter(query);
            }
            return false;
        }

        @Override
        public boolean onQueryTextChange(String query) {
            // filter recycler view when text is changed
            spinnerSortByDate.setSelection(0);
            spinnerSortByAlphabet.setSelection(0);

            if (loanApplicationMemberDetailAdapter != null && loanApplicationMemberDetailAdapter.getFilter() != null) {
                loanApplicationMemberDetailAdapter.getFilter().filter(query);
            }
            return false;
        }
    });

    configureDagger();
    configureViewModel();


}

private void configureDagger() {
    AndroidInjection.inject(this);
}

public void configureViewModel() {
    viewModel = ViewModelProviders.of(this, viewModelFactory).get(DynamicUIViewModel.class);

   /* if (loanType.equalsIgnoreCase(LOAN_NAME_INDIVIDUAL)) {
        SCREEN_NO = SCREEN_N0_LEAD_IL;
    } else if (loanType.equalsIgnoreCase(LOAN_NAME_MSME)) {
        SCREEN_NO = SCREEN_N0_LEAD_MSME;
    } else {
        SCREEN_NO = SCREEN_N0_LEAD;
    }*/
}

@Override
protected void onResume() {
    super.onResume();
    try {
        spinnerSortByDate.setSelection(0);
        spinnerSortByAlphabet.setSelection(0);
        searchByPhoneNo.setQuery("", false);
        searchByPhoneNo.setIconified(true);

        userName = getIntent().getStringExtra(PARAM_USER_NAME);
        userId = getIntent().getStringExtra(PARAM_USER_ID);
        branchId = getIntent().getStringExtra(PARAM_BRANCH_ID);
        branchGSTcode = getIntent().getStringExtra(PARAM_BRANCH_GST_CODE);
        loanType = getIntent().getStringExtra(PARAM_LOAN_TYPE);
        productId = getIntent().getStringExtra(PARAM_PRODUCT_ID);
        moduleType = getIntent().getStringExtra(PARAM_MODULE_TYPE);
        String centerJsonString = getIntent().getStringExtra(PARAM_CENTER_TABLE_JSON);
        if (!TextUtils.isEmpty(centerJsonString)) {
            CENTER_CREATION_TABLE = new Gson().fromJson(centerJsonString, CenterCreationTable.class);
            if (CENTER_CREATION_TABLE != null) {
                getLoanTableDetailsByCenter();
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

//    public void getMasterTableTable() {
//        appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();
//
//        try {
//            viewModel.getMasterTableByUserIdAndLoanTypeByTop300(userId, loanType);
//            if (viewModel.getmasterTableLiveDataList() != null) {
//                Observer getMasterTableDetailsObserver = new Observer() {
//                    @Override
//                    public void onChanged(@Nullable Object o) {
//                        appHelper.getDialogHelper().getLoadingDialog().closeDialog();
//                        List<MasterTable> masterTableList = (List<MasterTable>) o;
//                        viewModel.getmasterTableLiveDataList().removeObserver(this);
//
//                        if (masterTableList != null && masterTableList.size() > 0) {
//                            rlNoLeads.setVisibility(View.GONE);
//                            rvLeadDetails.setVisibility(View.VISIBLE);
//
//                            if (loanApplicationMemberDetailAdapter != null) {
//                                loanApplicationMemberDetailAdapter.setItem(masterTableList);
//                            } else {
//                                loanApplicationMemberDetailAdapter = new LoanApplicationMemberDetailAdapter(LoanApplicationMemberDetailsActivity.this,
//                                        masterTableList,
//                                        LoanApplicationMemberDetailsActivity.this, appHelper);
//                                rvLeadDetails.setAdapter(loanApplicationMemberDetailAdapter);
//                                loanApplicationMemberDetailAdapter.notifyDataSetChanged();
//                            }
//
//                        } else {
//                            rvLeadDetails.setVisibility(View.GONE);
//                            rlNoLeads.setVisibility(View.VISIBLE);
//                        }
//                    }
//                };
//                viewModel.getmasterTableLiveDataList().observe(this, getMasterTableDetailsObserver);
//            }
//        }catch (Exception ex){
//            appHelper.getDialogHelper().getLoadingDialog().closeDialog();
//            ex.printStackTrace();
//        }
//    }

    public void getLoanTableDetailsByCenter() {
        appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();

        try {
            viewModel.getLoanTableDetailsByCenter(CENTER_CREATION_TABLE);
            if (viewModel.getMemberLoanDetailTableLiveDataList() != null) {
                Observer getMasterTableDetailsObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        appHelper.getDialogHelper().getLoadingDialog().closeDialog();
                        List<LoanTable> loanTableList = (List<LoanTable>) o;
                        viewModel.getMemberLoanDetailTableLiveDataList().removeObserver(this);

                        if (loanTableList != null && loanTableList.size() > 0) {
                            rlNoLeads.setVisibility(View.GONE);
                            rvLeadDetails.setVisibility(View.VISIBLE);

                            if (loanApplicationMemberDetailAdapter != null) {
                                loanApplicationMemberDetailAdapter.setItem(loanTableList);
                            } else {
                                loanApplicationMemberDetailAdapter = new LoanApplicationMemberDetailAdapter(LoanApplicationMemberDetailsActivity.this, loanTableList,
                                        LoanApplicationMemberDetailsActivity.this, appHelper);
                                rvLeadDetails.setAdapter(loanApplicationMemberDetailAdapter);
                                loanApplicationMemberDetailAdapter.notifyDataSetChanged();
                            }

                        } else {
                            rvLeadDetails.setVisibility(View.GONE);
                            rlNoLeads.setVisibility(View.VISIBLE);
                        }
                    }
                };
                viewModel.getMemberLoanDetailTableLiveDataList().observe(this, getMasterTableDetailsObserver);
            }
        }catch (Exception ex){
            appHelper.getDialogHelper().getLoadingDialog().closeDialog();
            ex.printStackTrace();

            INSERT_LOG("getMasterTableByCGTTable","Exception : "+ex.getMessage());
        }
    }



@Override
public void openScreenCallback(int position, LoanTable loanTable) {
    try{
        Intent intentApplication = new Intent(LoanApplicationMemberDetailsActivity.this, LoanApplicationSummaryActivity.class);
        intentApplication.putExtra(AppConstant.PARAM_LOAN_TYPE, loanType);
        intentApplication.putExtra(PARAM_USER_NAME, userName);
        intentApplication.putExtra(PARAM_USER_ID, userId);
        intentApplication.putExtra(PARAM_BRANCH_ID, branchId);
        intentApplication.putExtra(PARAM_BRANCH_GST_CODE, branchGSTcode);
        intentApplication.putExtra(PARAM_CLIENT_ID, loanTable.getClientId());
        intentApplication.putExtra(PARAM_MODULE_TYPE, MODULE_TYPE_TARGET_DETAILS);
        startActivity(intentApplication);
    }catch (Exception ex){
        ex.printStackTrace();
    }
}

private void INSERT_LOG(String methodName, String message){
    try{
        if(viewModel !=null){
            viewModel.insertLog(methodName,message,userId,"","CenterCreationActivity"
                    ,CLIENT_ID,loanType,moduleType);
        }
    }catch (Exception ex){
        ex.printStackTrace();
    }
}
}
