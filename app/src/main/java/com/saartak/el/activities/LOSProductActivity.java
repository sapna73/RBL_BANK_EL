package com.saartak.el.activities;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bfil.uilibrary.dialogs.ConfirmationDialog;
import com.google.android.material.navigation.NavigationView;
import com.saartak.el.App;
import com.saartak.el.BuildConfig;
import com.saartak.el.R;
import com.saartak.el.adapter.LoanTypeAdapter;
import com.saartak.el.helper.ItemOffsetDecoration;
import com.saartak.el.models.LoanTypeDto;
import com.saartak.el.models.UserLoginMenu.UserLoginMenuTable;
import com.saartak.el.view_models.DynamicUIViewModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

import static com.bfil.uilibrary.helpers.AppConstants.ALL_PERMISSION_CODE;
import static com.bfil.uilibrary.helpers.AppConstants.PERMISSION_DENIED_EXPLANATION;
import static com.bfil.uilibrary.helpers.AppConstants.permissions;
import static com.saartak.el.constants.AppConstant.AUTHORIZATION_TOKEN_KEY;
import static com.saartak.el.constants.AppConstant.DATE_FORMAT_DD_MM_YYYY2;
import static com.saartak.el.constants.AppConstant.MODULE_TYPE_LEAD;
import static com.saartak.el.constants.AppConstant.PARAM_BRANCH_GST_CODE;
import static com.saartak.el.constants.AppConstant.PARAM_BRANCH_ID;
import static com.saartak.el.constants.AppConstant.PARAM_BRANCH_NAME;
import static com.saartak.el.constants.AppConstant.PARAM_LOAN_TYPE;
import static com.saartak.el.constants.AppConstant.PARAM_MODULE_TYPE;
import static com.saartak.el.constants.AppConstant.PARAM_PRODUCT_ID;
import static com.saartak.el.constants.AppConstant.PARAM_REQUEST_FROM_SAARTHI_APP;
import static com.saartak.el.constants.AppConstant.PARAM_ROLE_NAME;
import static com.saartak.el.constants.AppConstant.PARAM_USER_ID;
import static com.saartak.el.constants.AppConstant.PARAM_USER_NAME;
import static com.saartak.el.constants.AppConstant.RESET_PASSWORD_TOKEN;
import static com.saartak.el.dynamicui.constants.ParametersConstant.ERROR_MESSAGE_APP_NOT_AVAILABLE_PLEASE_INSTALL;
import static com.saartak.el.dynamicui.constants.ParametersConstant.PACKAGE_NAME_HELP;
import static com.saartak.el.dynamicui.constants.ParametersConstant.PACKAGE_NAME_LETS_CONNECT;
import static com.saartak.el.dynamicui.constants.ParametersConstant.PACKAGE_NAME_TEAMS;
import static com.saartak.el.dynamicui.constants.ParametersConstant.PACKAGE_NAME_ZING_HR;
import static com.saartak.el.dynamicui.constants.ParametersConstant.REQUEST_CODE_LOCATION;

public class LOSProductActivity extends LOSBaseActivity implements HasSupportFragmentInjector, View.OnClickListener, LoanTypeAdapter.ProductCallbakInterface {
    private static final String TAG = LOSProductActivity.class.getCanonicalName();
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView navigationView;
    ImageView ivStaffImageND;
    TextView tvStaffId, tvStaffName, tvSOBDate;
    RecyclerView rvLoanType;
    RelativeLayout rl_no_product;
    ImageView ivStaffImage;
    TextView tvUserName, tvUserId, tvAppVersion, tvCurrentDate, tvSodStatus;
    LinearLayout llLetsConnect, llZingHr, llTeams, llHelp;
    CardView cv_lets_connect, cv_zing_hr, cv_help, cv_teams;
    private Toolbar toolbar;
    ArrayList<UserLoginMenuTable>  userLoginMemuList;

    boolean IS_REQUEST_FROM_SAARTHI;

    public String userName, userId, branchId, branchName, branchGSTcode, roleName;

    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;

    @Inject
    public ViewModelProvider.Factory viewModelFactory;
    public DynamicUIViewModel viewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_los_product);

        llLetsConnect = (LinearLayout) findViewById(R.id.ll_lets_connect);
        llZingHr = (LinearLayout) findViewById(R.id.ll_zing_hr);
        llTeams = (LinearLayout) findViewById(R.id.ll_teams);
        llHelp = (LinearLayout) findViewById(R.id.ll_help);

        cv_lets_connect = (CardView) findViewById(R.id.cv_lets_connect);
        cv_zing_hr = (CardView) findViewById(R.id.cv_zing_hr);
        cv_teams = (CardView) findViewById(R.id.cv_teams);
        cv_help = (CardView) findViewById(R.id.cv_help);

        tvSodStatus = (TextView) findViewById(R.id.tv_sod_status);

        ivStaffImage = (ImageView) findViewById(R.id.iv_staff_image);
        tvUserName = (TextView) findViewById(R.id.tv_dashboard_username);
        tvUserId = (TextView) findViewById(R.id.tv_dashboard_user_id);
        tvAppVersion = (TextView) findViewById(R.id.tv_app_version);
        tvCurrentDate = (TextView) findViewById(R.id.tv_currentdate);
        rvLoanType = (RecyclerView) findViewById(R.id.rv_product);
        rl_no_product = (RelativeLayout) findViewById(R.id.rl_no_product);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(LOSProductActivity.this, 2);
        ItemOffsetDecoration itemDecoration = new ItemOffsetDecoration(LOSProductActivity.this, R.dimen._2sdp);
        rvLoanType.addItemDecoration(itemDecoration);
        ViewGroup.MarginLayoutParams marginLayoutParams =
                (ViewGroup.MarginLayoutParams) rvLoanType.getLayoutParams();
        //  marginLayoutParams.setMargins(50, 0, 0, 0);
        marginLayoutParams.setMargins(0, 0, 0, 0);
        rvLoanType.setLayoutParams(marginLayoutParams);
        rvLoanType.setLayoutManager(gridLayoutManager);


        // TODO: List of icons
        List<LoanTypeDto> loanTypeDtoList = new ArrayList<>();

//        loanTypeDtoList.add(new LoanTypeDto("JLG", R.drawable.jlg_));
        loanTypeDtoList.add(new LoanTypeDto("INDIVIDUAL", R.drawable.individual_));
        loanTypeDtoList.add(new LoanTypeDto("MSME", R.drawable.msme_));
        loanTypeDtoList.add(new LoanTypeDto("TW", R.drawable.tw_));
        loanTypeDtoList.add(new LoanTypeDto("EL", R.drawable.el_img));
        loanTypeDtoList.add(new LoanTypeDto("AHL", R.drawable.house_));


        userName = getIntent().getStringExtra(PARAM_USER_NAME);
        userId = getIntent().getStringExtra(PARAM_USER_ID);
        branchId = getIntent().getStringExtra(PARAM_BRANCH_ID);
        branchName = getIntent().getStringExtra(PARAM_BRANCH_NAME);
        branchGSTcode = getIntent().getStringExtra(PARAM_BRANCH_GST_CODE);
        if (TextUtils.isEmpty(branchGSTcode)) {
            branchGSTcode = branchId;
        }
        roleName = getIntent().getStringExtra(PARAM_ROLE_NAME);

        if (!TextUtils.isEmpty(userName)) {
            tvUserName.setText(userName);
        }
        if (!TextUtils.isEmpty(userId)) {
            tvUserId.setText(userId);
        }

        IS_REQUEST_FROM_SAARTHI = getIntent().getBooleanExtra(PARAM_REQUEST_FROM_SAARTHI_APP, false);

        if (IS_REQUEST_FROM_SAARTHI) {
            // TODO: Clear Bearer Token & Insert Static Token for Saarthi App

            // TODO: Clearing shared preference
            appHelper.getSharedPrefObj().edit().remove(AUTHORIZATION_TOKEN_KEY).apply();

            // TODO: Inserting into key store
            appHelper.getSharedPrefObj().edit().putString(AUTHORIZATION_TOKEN_KEY, RESET_PASSWORD_TOKEN).apply();

            // TODO: Check App Permission
            checkAllPermissions();
        }

        llLetsConnect.setOnClickListener(this);
        llZingHr.setOnClickListener(this);
        llTeams.setOnClickListener(this);
        llHelp.setOnClickListener(this);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);

        actionBarDrawerToggle.getDrawerArrowDrawable().setColor(Color.BLACK);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        actionBarDrawerToggle.syncState();
        setUpDrawerContent(navigationView);
        View header = navigationView.getHeaderView(0);
        ivStaffImageND = header.findViewById(R.id.iv_staff_image);
        tvStaffId = header.findViewById(R.id.tv_staff_id);
        tvStaffName = header.findViewById(R.id.tv_staff_name);
        tvSOBDate = header.findViewById(R.id.tv_sod_date);

        String appVersion = BuildConfig.VERSION_NAME;
        if (!TextUtils.isEmpty(appVersion)) {
            tvAppVersion.setText(getResources().getString(R.string.version) + " " + appVersion);
        }
        if (!TextUtils.isEmpty(userName)) {
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


        LoanTypeAdapter loanTypeAdapter = new LoanTypeAdapter(LOSProductActivity.this, loanTypeDtoList,userLoginMemuList, this);
        rvLoanType.setAdapter(loanTypeAdapter);

        configureDagger();
        configureViewModel();

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
    protected void onResume() {
        super.onResume();
        try {
            userName = getIntent().getStringExtra(PARAM_USER_NAME);
            userId = getIntent().getStringExtra(PARAM_USER_ID);
            branchId = getIntent().getStringExtra(PARAM_BRANCH_ID);
            branchName = getIntent().getStringExtra(PARAM_BRANCH_NAME);
            branchGSTcode = getIntent().getStringExtra(PARAM_BRANCH_GST_CODE);
            IS_REQUEST_FROM_SAARTHI = getIntent().getBooleanExtra(PARAM_REQUEST_FROM_SAARTHI_APP, false);
            if (TextUtils.isEmpty(branchGSTcode)) {
                branchGSTcode = branchId;
            }
            roleName = getIntent().getStringExtra(PARAM_ROLE_NAME);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void selectItemDrawer(MenuItem menuItem) {
        try {
            switch (menuItem.getItemId()) {

//                case R.id.menu_item_knowledge_bank:
//                    Intent intentKnowledge = new Intent(this, KnowledgeBankHeaderActivity.class);
//                    intentKnowledge.putExtra(PARAM_USER_NAME, userName);
//                    intentKnowledge.putExtra(PARAM_USER_ID, userId);
//                    intentKnowledge.putExtra(PARAM_BRANCH_ID, branchId);
//                    intentKnowledge.putExtra(PARAM_BRANCH_GST_CODE, branchGSTcode);
//                    startActivity(intentKnowledge);
//                    break;
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        appHelper.getDialogHelper().getConfirmationDialog().showTwoButtons("Do you want to leave this screen ? ", new ConfirmationDialog.ActionCallback() {
            @Override
            public void onAction() {
                finish();
            }
        });

    }

    private void configureDagger() {
        AndroidInjection.inject(this);
    }

    public void configureViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(DynamicUIViewModel.class);

    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return dispatchingAndroidInjector;
    }

    @Override
    public void onClick(View v) {
        try {
            switch (v.getId()) {
                case R.id.ll_lets_connect:
                    // TODO: Open APP - LETS CONNECT
                    Intent launchIntent_connect = getPackageManager().getLaunchIntentForPackage(PACKAGE_NAME_LETS_CONNECT);
                    if (launchIntent_connect != null) {
                        startActivity(launchIntent_connect);
                    } else {
                        appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT,
                                ERROR_MESSAGE_APP_NOT_AVAILABLE_PLEASE_INSTALL);
                    }

                    break;
                case R.id.ll_zing_hr:
                    // TODO: Open APP - ZING HR
                    Intent launchIntent_zingHr = getPackageManager().getLaunchIntentForPackage(PACKAGE_NAME_ZING_HR);
                    if (launchIntent_zingHr != null) {
                        startActivity(launchIntent_zingHr);
                    } else {
                        appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT,
                                ERROR_MESSAGE_APP_NOT_AVAILABLE_PLEASE_INSTALL);
                    }

                    break;

                case R.id.ll_teams:
                    // TODO: Open APP - TEAMS
                    Intent launchIntentTeams = getPackageManager().getLaunchIntentForPackage(PACKAGE_NAME_TEAMS);
                    if (launchIntentTeams != null) {
                        startActivity(launchIntentTeams);
                    } else {
                        appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT,
                                ERROR_MESSAGE_APP_NOT_AVAILABLE_PLEASE_INSTALL);
                    }

                    break;

                case R.id.ll_help:
                    // TODO: Open APP - HELP
                    Intent launchIntentHelp = getPackageManager().getLaunchIntentForPackage(PACKAGE_NAME_HELP);
                    if (launchIntentHelp != null) {
                        startActivity(launchIntentHelp);
                    } else {
                        appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT,
                                ERROR_MESSAGE_APP_NOT_AVAILABLE_PLEASE_INSTALL);
                    }

                    break;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //  super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == 0) {
            switch (requestCode) {
                case REQUEST_CODE_LOCATION:
                    break;
            }
        }
    }

    @Override
    public void openProductScreenCallback(String loanType, String productId,String workflowId) {
        try {
            Intent intentApplication = new Intent(this, LeadDetailsActivity.class);
            intentApplication.putExtra(PARAM_LOAN_TYPE, loanType);
            intentApplication.putExtra(PARAM_USER_NAME, userName);
            intentApplication.putExtra(PARAM_USER_ID, userId);
            intentApplication.putExtra(PARAM_BRANCH_ID, branchId);
            intentApplication.putExtra(PARAM_BRANCH_GST_CODE, branchGSTcode);
            intentApplication.putExtra(PARAM_MODULE_TYPE, MODULE_TYPE_LEAD);
            intentApplication.putExtra(PARAM_PRODUCT_ID, productId);
            intentApplication.putExtra(PARAM_ROLE_NAME, roleName);
            intentApplication.putExtra(PARAM_REQUEST_FROM_SAARTHI_APP, true);

            startActivity(intentApplication);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    private boolean checkAllPermissions() {
        try {
            // TODO: Check which permissions are granted
            List<String> listPermissionsNeeded = new ArrayList<>();

            for (String permission : permissions) {
                if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                    listPermissionsNeeded.add(permission);
                }
            }

            // TODO: Ask for non granted permissions
            if (!listPermissionsNeeded.isEmpty()) {
                ActivityCompat.requestPermissions(this, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]),
                        ALL_PERMISSION_CODE);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        // TODO: App has all permissions
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        try {
            if (requestCode == ALL_PERMISSION_CODE) {
                HashMap<String, Integer> permissionResults = new HashMap<>();
                int deniedCount = 0;

                for (int i = 0; i < grantResults.length; i++) {
                    // TODO: Add only permissions which are denied
                    if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                        permissionResults.put(permissions[i], grantResults[i]);
                        deniedCount++;
                    }
                }

                // TODO: Check if all permissions are granted
                if (deniedCount == 0) {
                    // TODO: All permissions granted , proceed with application

                    try {
                        Toast.makeText(this, "Permissions granted",
                                Toast.LENGTH_SHORT).show();

                        // TODO: CREATING APP DB
                        App.createDBPath();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                } else {
                    // TODO: At least one permission or all permissions are denied
                    for (Map.Entry<String, Integer> entry : permissionResults.entrySet()) {
                        String permissionName = entry.getKey();
                        int permissonResult = entry.getValue();

                        // TODO: Permission is denied ( this is the first time , when never ask again is not checked )
                        // TODO: so ask again and explain the usage of permission
                        // TODO: shouldShowRequestPermissionRationale will return true
                        if (ActivityCompat.shouldShowRequestPermissionRationale(this, permissionName)) {
                            appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT,
                                    PERMISSION_DENIED_EXPLANATION, new ConfirmationDialog.ActionCallback() {
                                        @Override
                                        public void onAction() {
                                            checkAllPermissions();
                                        }
                                    });
                        }
                        // TODO: permission is denied ( and never ask again is checked )
                        // TODO: shouldShowRequestPermissionRationale will return false
                        else {
                            // TODO: Ask user to go to settings and manually allow permissions
                            appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT,
                                    PERMISSION_DENIED_EXPLANATION, new ConfirmationDialog.ActionCallback() {
                                        @Override
                                        public void onAction() {
                                            checkAllPermissions();
                                        }
                                    });

                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
