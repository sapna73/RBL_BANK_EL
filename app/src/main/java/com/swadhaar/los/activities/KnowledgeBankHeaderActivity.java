package com.swadhaar.los.activities;

import androidx.annotation.NonNull;
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

import com.bfil.uilibrary.dialogs.ConfirmationDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;
import com.swadhaar.los.BuildConfig;
import com.swadhaar.los.R;
import com.swadhaar.los.adapter.KnowledgeBankHeaderAdapter;
import com.swadhaar.los.database.entity.KnowledgeBankTable;
import com.swadhaar.los.database.entity.RawDataTable;
import com.swadhaar.los.helper.MyDividerItemDecoration;
import com.swadhaar.los.view_models.DynamicUIViewModel;

import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

import static com.swadhaar.los.constants.AppConstant.DATE_FORMAT_DD_MM_YYYY2;
import static com.swadhaar.los.constants.AppConstant.PARAM_BRANCH_GST_CODE;
import static com.swadhaar.los.constants.AppConstant.PARAM_BRANCH_ID;
import static com.swadhaar.los.constants.AppConstant.PARAM_KNOWLEDGE_BANK_TABLE;
import static com.swadhaar.los.constants.AppConstant.PARAM_LOAN_TYPE;
import static com.swadhaar.los.constants.AppConstant.PARAM_MODULE_TYPE;
import static com.swadhaar.los.constants.AppConstant.PARAM_PRODUCT_ID;
import static com.swadhaar.los.constants.AppConstant.PARAM_USER_ID;
import static com.swadhaar.los.constants.AppConstant.PARAM_USER_NAME;
import static com.swadhaar.los.constants.AppConstant.PROJECT_ID_LOS;

public class KnowledgeBankHeaderActivity extends LOSBaseActivity implements KnowledgeBankHeaderAdapter.SyncCallbackInterface {


    private static final String TAG = KnowledgeBankHeaderActivity.class.getCanonicalName();
    RecyclerView rvLeadDetails;
    SearchView searchByPhoneNo;
    AppCompatSpinner spinnerSortByDate, spinnerSortByAlphabet;
    KnowledgeBankHeaderAdapter knowledgeBankHeaderAdapter;

    private Toolbar toolbar;
    TextView tvClientName, tvCentersSummaryHeader;
    TextView tvAppVersion, tvCurrentDate;
    String SCREEN_NO;

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView navigationView;
    CoordinatorLayout coordinatorLayout;
    ImageView ivStaffImage;
    RelativeLayout rlNoLeads;
    TextView tvStaffId, tvStaffName, tvSOBDate;
    FloatingActionButton btnNewTarget;
    RelativeLayout rl_parent_cold_calling;

//    public String userName,userId,branchId;

    List<RawDataTable> allClientRawDataTableList;

    @Inject
    public ViewModelProvider.Factory viewModelFactory;
    public DynamicUIViewModel viewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_knowledge_bank_header);
        rvLeadDetails = (RecyclerView) findViewById(R.id.rv_lead_details);
        rlNoLeads = (RelativeLayout) findViewById(R.id.rl_no_leads);
        searchByPhoneNo = (SearchView) findViewById(R.id.sv_phone_no);
        btnNewTarget = findViewById(R.id.fb_add_target_details);
        spinnerSortByDate = (AppCompatSpinner) findViewById(R.id.sp_sort_by_date);
        spinnerSortByAlphabet = (AppCompatSpinner) findViewById(R.id.sp_sort_by_alphabetical_order);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tvCurrentDate = (TextView) findViewById(R.id.tv_currentdate);
        tvAppVersion = (TextView) findViewById(R.id.tv_app_version);
        tvClientName = (TextView) findViewById(R.id.tv_client_name_custom);
        tvCentersSummaryHeader = (TextView) findViewById(R.id.tv_centers_summary_header);
        rl_parent_cold_calling = findViewById(R.id.rl_parent_cold_calling);

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
            tvCurrentDate.setText(currentDate);
        }

        String appVersion = BuildConfig.VERSION_NAME;
        if (!TextUtils.isEmpty(appVersion)) {
            tvAppVersion.setText(getResources().getString(R.string.version) + " " + appVersion);
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
                    if (knowledgeBankHeaderAdapter != null && knowledgeBankHeaderAdapter.getFilter() != null) {
                        knowledgeBankHeaderAdapter.getFilter().filter(parent.getSelectedItem().toString());
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

                    if (knowledgeBankHeaderAdapter != null && knowledgeBankHeaderAdapter.getFilter() != null) {
                        knowledgeBankHeaderAdapter.getFilter().filter(parent.getSelectedItem().toString());
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

                if (knowledgeBankHeaderAdapter != null && knowledgeBankHeaderAdapter.getFilter() != null) {
                    knowledgeBankHeaderAdapter.getFilter().filter(query);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                // filter recycler view when text is changed
                spinnerSortByDate.setSelection(0);
                spinnerSortByAlphabet.setSelection(0);

                if (knowledgeBankHeaderAdapter != null && knowledgeBankHeaderAdapter.getFilter() != null) {
                    knowledgeBankHeaderAdapter.getFilter().filter(query);
                }
                return false;
            }
        });

        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);

        configureDagger();
        configureViewModel();


    }

    private void configureDagger() {
        AndroidInjection.inject(this);
    }

    public void configureViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(DynamicUIViewModel.class);

        // TODO: Getting Knowledge Bank Header From Server
        getKnowledgeBankFromServer();
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

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void getKnowledgeBankFromServer() {
        try {
            appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();
            viewModel.getKnowledgeBankFromServer(productId, PROJECT_ID_LOS);
            if (viewModel.getKnowledgeBankTableLiveDataList() != null) {
                Observer observer = new Observer() {
                    @Override
                    public void onChanged(Object o) {
                        appHelper.getDialogHelper().getLoadingDialog().closeDialog();
                        List<KnowledgeBankTable> knowledgeBankTableList = (List<KnowledgeBankTable>) o;
                        viewModel.getKnowledgeBankTableLiveDataList().removeObserver(this);

                        if (knowledgeBankTableList != null && knowledgeBankTableList.size() > 0) {
                            rlNoLeads.setVisibility(View.GONE);
                            rvLeadDetails.setVisibility(View.VISIBLE);

                            if (knowledgeBankHeaderAdapter != null) {
                                knowledgeBankHeaderAdapter.setItem(knowledgeBankTableList);
                            } else {
                                knowledgeBankHeaderAdapter = new KnowledgeBankHeaderAdapter(KnowledgeBankHeaderActivity.this, knowledgeBankTableList,
                                        KnowledgeBankHeaderActivity.this, appHelper);
                                rvLeadDetails.setAdapter(knowledgeBankHeaderAdapter);
                                knowledgeBankHeaderAdapter.notifyDataSetChanged();
                            }
                        } else {
                            rvLeadDetails.setVisibility(View.GONE);
                            rlNoLeads.setVisibility(View.VISIBLE);
                        }

                    }
                };
                viewModel.getKnowledgeBankTableLiveDataList().observe(this, observer);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            appHelper.getDialogHelper().getLoadingDialog().closeDialog();
            INSERT_LOG("getKnowledgeBankFromServer", "Exception : " + ex.getMessage());
        }
    }

//    public void getKnowledgeBankFromServer() {
//        try {
//            appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();
//
//            List<KnowledgeBankTable> knowledgeBankTableList=new ArrayList<>();
//
//            KnowledgeBankTable knowledgeBankTable1=new KnowledgeBankTable();
//            knowledgeBankTable1.setDocument_name("Login Checklist");
//            knowledgeBankTable1.setFile_name("1011589200625135806_busaddprf_aadhaar_front_1.jpeg");
//            knowledgeBankTable1.setFile_type(EXTENSION_JPG);
//
//            KnowledgeBankTable knowledgeBankTable2=new KnowledgeBankTable();
//            knowledgeBankTable2.setDocument_name("RO One");
//            knowledgeBankTable2.setFile_name("1004141200311172358_appldk_form60_front_1.pdf");
//            knowledgeBankTable2.setFile_type(EXTENSION_PDF);
//
//            KnowledgeBankTable knowledgeBankTable3=new KnowledgeBankTable();
//            knowledgeBankTable3.setDocument_name("Sales Kit");
//            knowledgeBankTable3.setFile_name("1004141191022105352_apphoupht_housephoto_front_1.jpg");
//            knowledgeBankTable3.setFile_type(EXTENSION_JPG);
//
//            knowledgeBankTableList.add(knowledgeBankTable3);
//            knowledgeBankTableList.add(knowledgeBankTable2);
//            knowledgeBankTableList.add(knowledgeBankTable1);
//
//            viewModel.getKnowledgeBankFromServer(knowledgeBankTableList);
//            if (viewModel.getKnowledgeBankTableLiveData() != null) {
//                Observer getCustomerViewDataObserver = new Observer() {
//                    @Override
//                    public void onChanged(@Nullable Object o) {
//                        appHelper.getDialogHelper().getLoadingDialog().closeDialog();
//                        List<KnowledgeBankTable> applicationStatusTableList = (List<KnowledgeBankTable>) o;
//                        viewModel.getKnowledgeBankTableLiveData().removeObserver(this);
//
//                        if (applicationStatusTableList != null && applicationStatusTableList.size() > 0) {
//                            rlNoLeads.setVisibility(View.GONE);
//                            rvLeadDetails.setVisibility(View.VISIBLE);
//
//                            if (knowledgeBankHeaderAdapter != null) {
//                                knowledgeBankHeaderAdapter.setItem(applicationStatusTableList);
//                            } else {
//                                knowledgeBankHeaderAdapter = new KnowledgeBankHeaderAdapter(KnowledgeBankHeaderActivity.this, applicationStatusTableList,
//                                        KnowledgeBankHeaderActivity.this, appHelper);
//                                rvLeadDetails.setAdapter(knowledgeBankHeaderAdapter);
//                                knowledgeBankHeaderAdapter.notifyDataSetChanged();
//                            }
//                        } else {
//                            rvLeadDetails.setVisibility(View.GONE);
//                            rlNoLeads.setVisibility(View.VISIBLE);
//                        }
//                    }
//                };
//                viewModel.getKnowledgeBankTableLiveData().observe(this, getCustomerViewDataObserver);
//            }
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            appHelper.getDialogHelper().getLoadingDialog().closeDialog();
//            INSERT_LOG("getKnowledgeBankFromServer", "Exception : " + ex.getMessage());
//
//        }
//    }


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

    @Override
    public void openScreenCallback(int position, KnowledgeBankTable knowledgeBankTable) {
        try {
            if (knowledgeBankTable!=null){
                if (!TextUtils.isEmpty(knowledgeBankTable.getFileType()) && knowledgeBankTable.getFileType().equalsIgnoreCase("PDF")){
                    openPDFViewerActivity(knowledgeBankTable);
                }
                else if (!TextUtils.isEmpty(knowledgeBankTable.getFileType()) && knowledgeBankTable.getFileType().equalsIgnoreCase("JPG")){
                    openFullscreenImageActivity(knowledgeBankTable);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void openFullscreenImageActivity(KnowledgeBankTable knowledgeBankTable) {
        try {
            Intent targetDetails = new Intent(this, FullImageScreenActivity.class);
            String knowledgebankJson=new Gson().toJson(knowledgeBankTable,KnowledgeBankTable.class);
            targetDetails.putExtra(PARAM_KNOWLEDGE_BANK_TABLE, knowledgebankJson);
            startActivity(targetDetails);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    private void openPDFViewerActivity(KnowledgeBankTable knowledgeBankTable) {
        try {
            Intent targetDetails = new Intent(this, PDFViewerActivity.class);
            String knowledgebankJson=new Gson().toJson(knowledgeBankTable,KnowledgeBankTable.class);
            targetDetails.putExtra(PARAM_KNOWLEDGE_BANK_TABLE, knowledgebankJson);
            startActivity(targetDetails);
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
}