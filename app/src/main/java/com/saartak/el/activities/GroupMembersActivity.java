package com.saartak.el.activities;

import android.app.SearchManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.text.method.DigitsKeyListener;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.view.ActionMode;
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
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;
import com.saartak.el.R;
import com.saartak.el.adapter.GroupMemberAdapter;
import com.saartak.el.database.entity.CGTTable;
import com.saartak.el.database.entity.MasterTable;
import com.saartak.el.database.entity.RawDataTable;
import com.saartak.el.helper.MyDividerItemDecoration;
import com.saartak.el.helper.Tools;
import com.saartak.el.view_models.DynamicUIViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

import static com.saartak.el.constants.AppConstant.DATE_FORMAT_DD_MM_YYYY2;
import static com.saartak.el.constants.AppConstant.PARAM_ACTION_TO_DO;
import static com.saartak.el.constants.AppConstant.PARAM_BRANCH_GST_CODE;
import static com.saartak.el.constants.AppConstant.PARAM_BRANCH_ID;
import static com.saartak.el.constants.AppConstant.PARAM_CLIENT_ID;
import static com.saartak.el.constants.AppConstant.PARAM_GROUP_NAME;
import static com.saartak.el.constants.AppConstant.PARAM_CGT_TABLE_JSON;
import static com.saartak.el.constants.AppConstant.PARAM_LOAN_TYPE;
import static com.saartak.el.constants.AppConstant.PARAM_PRODUCT_ID;
import static com.saartak.el.constants.AppConstant.PARAM_USER_ID;
import static com.saartak.el.constants.AppConstant.PARAM_USER_NAME;
import static com.saartak.el.dynamicui.constants.ParametersConstant.ACTION_VIEW_GROUP_MEMBERS;

public class GroupMembersActivity extends LOSBaseActivity  {

    private static final String TAG = GroupMembersActivity.class.getCanonicalName();
    RecyclerView rvLeadDetails;
    SearchView searchByPhoneNo;
    AppCompatSpinner spinnerSortByDate, spinnerSortByLoanType, spinnerSortByInterested;
    GroupMemberAdapter groupMemberAdapter;


    private Toolbar toolbar;

    private ActionModeCallback actionModeCallback;
    private ActionMode actionMode;

    TextView tvClientName;
    TextView tvGroupName;
    String SCREEN_NO;

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView navigationView;
    CoordinatorLayout coordinatorLayout;
    ImageView ivStaffImage;
    RelativeLayout rlNoLeads;
    TextView tvStaffId, tvStaffName, tvSOBDate;
    CGTTable CGT_TABLE;
    String GROUP_NAME;
    String ACTION;

//    public String userName,userId,branchId;

    List<RawDataTable> allClientRawDataTableList;

    @Inject
    public ViewModelProvider.Factory viewModelFactory;
    public DynamicUIViewModel viewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_group_members);

        Toast.makeText(this, "Long press for multi selection", Toast.LENGTH_SHORT).show();

        rvLeadDetails = (RecyclerView) findViewById(R.id.rv_lead_details);
        rlNoLeads = (RelativeLayout) findViewById(R.id.rl_no_leads);
        searchByPhoneNo = (SearchView) findViewById(R.id.sv_phone_no);
        spinnerSortByDate = (AppCompatSpinner) findViewById(R.id.sp_sort_by_date);
        spinnerSortByLoanType = (AppCompatSpinner) findViewById(R.id.sp_sort_by_loan_type);
        spinnerSortByInterested = (AppCompatSpinner) findViewById(R.id.sp_sort_by_interest_or_not);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tvClientName = (TextView) findViewById(R.id.tv_client_name_custom);
        tvGroupName = (TextView) findViewById(R.id.tv_group_name_value);
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
        GROUP_NAME = getIntent().getStringExtra(PARAM_GROUP_NAME);
        ACTION = getIntent().getStringExtra(PARAM_ACTION_TO_DO);
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
        if (!TextUtils.isEmpty(GROUP_NAME)) {
            tvGroupName.setText(GROUP_NAME);
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

        actionModeCallback = new ActionModeCallback();

        groupMemberAdapter = new GroupMemberAdapter(GroupMembersActivity.this, new ArrayList<>(),
                appHelper);
        rvLeadDetails.setAdapter(groupMemberAdapter);

        groupMemberAdapter.setOnClickListener(new GroupMemberAdapter.OnClickListener() {
            @Override
            public void onItemClick(View view, MasterTable masterTable, int pos) {
                if (groupMemberAdapter.getSelectedItemCount() > 0) {
                    enableActionMode(pos);
                } else {
                    // read the inbox which removes bold tvName the row
//                    MasterTable masterTableFromAdapter = groupMemberAdapter.getItem(pos);
                    Toast.makeText(getApplicationContext(), "Long Press For Multi Selection", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onItemLongClick(View view, MasterTable masterTable, int pos) {
                enableActionMode(pos);
            }
        });

        spinnerSortByDate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0) {

                    spinnerSortByLoanType.setSelection(0);
                    spinnerSortByInterested.setSelection(0);
                    searchByPhoneNo.setQuery("", false);
                    searchByPhoneNo.setIconified(true);
                    if (groupMemberAdapter != null && groupMemberAdapter.getFilter() != null) {
                        groupMemberAdapter.getFilter().filter(parent.getSelectedItem().toString());
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
                    if (groupMemberAdapter != null && groupMemberAdapter.getFilter() != null) {
                        groupMemberAdapter.getFilter().filter(parent.getSelectedItem().toString());
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

                    if (groupMemberAdapter != null && groupMemberAdapter.getFilter() != null) {
                        groupMemberAdapter.getFilter().filter(parent.getSelectedItem().toString());
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

                if (groupMemberAdapter != null && groupMemberAdapter.getFilter() != null) {
                    groupMemberAdapter.getFilter().filter(query);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                // filter recycler view when text is changed
                spinnerSortByDate.setSelection(0);
                spinnerSortByLoanType.setSelection(0);
                spinnerSortByInterested.setSelection(0);

                if (groupMemberAdapter != null && groupMemberAdapter.getFilter() != null) {
                    groupMemberAdapter.getFilter().filter(query);
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
            GROUP_NAME = getIntent().getStringExtra(PARAM_GROUP_NAME);
            ACTION = getIntent().getStringExtra(PARAM_ACTION_TO_DO);
            String cgtJsonString = getIntent().getStringExtra(PARAM_CGT_TABLE_JSON);
            if (!TextUtils.isEmpty(cgtJsonString)) {
                CGT_TABLE = new Gson().fromJson(cgtJsonString, CGTTable.class);
                if (CGT_TABLE != null) {
                }
            }
            getMasterTableByCGTTable(CGT_TABLE,ACTION,GROUP_NAME);

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

    public void getMasterTableByCGTTable(CGTTable cgtTable, String action, String groupName) {
        appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();

        try {
            viewModel.getMasterTableByCGTTable( cgtTable,action, groupName);
            if (viewModel.getmasterTableLiveDataList() != null) {
                Observer getMasterTableDetailsObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        appHelper.getDialogHelper().getLoadingDialog().closeDialog();
                        List<MasterTable> masterTableList = (List<MasterTable>) o;
                        viewModel.getmasterTableLiveDataList().removeObserver(this);

                        if (masterTableList != null && masterTableList.size() > 0) {
                            rlNoLeads.setVisibility(View.GONE);
                            rvLeadDetails.setVisibility(View.VISIBLE);

                            if (groupMemberAdapter != null) {
                                groupMemberAdapter.setItem(masterTableList);
                            }

                        } else {
                            rvLeadDetails.setVisibility(View.GONE);
                            rlNoLeads.setVisibility(View.VISIBLE);
                        }
                    }
                };
                viewModel.getmasterTableLiveDataList().observe(this, getMasterTableDetailsObserver);
            }
        }catch (Exception ex){
            appHelper.getDialogHelper().getLoadingDialog().closeDialog();
            ex.printStackTrace();

            INSERT_LOG("getMasterTableByCGTTable","Exception : "+ex.getMessage());
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

    private void enableActionMode(int position) {
        if (actionMode == null) {
            actionMode = startSupportActionMode(actionModeCallback);
        }
        toggleSelection(position);
    }

    private void toggleSelection(int position) {
        groupMemberAdapter.toggleSelection(position);
        int count = groupMemberAdapter.getSelectedItemCount();

        if (count == 0) {
            actionMode.finish();
        } else {
            actionMode.setTitle(String.valueOf(count));
            actionMode.invalidate();
        }
    }


    private class ActionModeCallback implements ActionMode.Callback {
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            Tools.setSystemBarColor(GroupMembersActivity.this, R.color.colorDarkBlue2);
            if(!TextUtils.isEmpty(ACTION) && ACTION.equalsIgnoreCase(ACTION_VIEW_GROUP_MEMBERS)) {
                mode.getMenuInflater().inflate(R.menu.menu_delete, menu);
            }else{
                mode.getMenuInflater().inflate(R.menu.menu_add, menu);
            }
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            int id = item.getItemId();
            if (id == R.id.action_add) {
//                deleteInboxes();
                mode.finish();
                return true;
            }else if (id == R.id.action_delete) {
//                deleteInboxes();
                deleteMembersInGroupTable();
//                AddMembers();
                mode.finish();
                return true;
            }
            return false;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            groupMemberAdapter.clearSelections();
            actionMode = null;
            Tools.setSystemBarColor(GroupMembersActivity.this, R.color.colorPrimary);
        }
    }

    private void deleteInboxes() {
        List<Integer> selectedItemPositions = groupMemberAdapter.getSelectedItems();
        for (int i = selectedItemPositions.size() - 1; i >= 0; i--) {
            groupMemberAdapter.removeData(selectedItemPositions.get(i));
        }
        groupMemberAdapter.notifyDataSetChanged();
    }
    private void deleteMembersInGroupTable() {
        try {
            List<MasterTable> masterTableList = groupMemberAdapter.getSelectedMembers();
            if(masterTableList !=null && masterTableList.size()>0){
                viewModel.deleteMembersInGroupTable(masterTableList,GROUP_NAME,CLIENT_ID); // TODO: Group name & group id
                if (viewModel.getGroupTableLiveDataList() != null) {
                    Observer observer = new Observer() {
                        @Override
                        public void onChanged(@Nullable Object o) {
                            viewModel.getGroupTableLiveDataList().removeObserver(this);
                            finish();
                        }
                    };
                    viewModel.getGroupTableLiveDataList().observe(this, observer);
                }

            }
        }catch (Exception ex){
            ex.printStackTrace();
        }

    }


}
