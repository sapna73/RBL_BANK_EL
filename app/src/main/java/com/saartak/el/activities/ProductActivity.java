package com.saartak.el.activities;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.telephony.PhoneStateListener;
import android.telephony.SignalStrength;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.SwitchCompat;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bfil.uilibrary.dialogs.ConfirmationDialog;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.saartak.el.BuildConfig;
import com.saartak.el.database.entity.CenterMeetingCollectionTable;
import com.saartak.el.database.entity.DynamicUITable;
import com.saartak.el.helper.ItemOffsetDecoration;
import com.saartak.el.R;
import com.saartak.el.adapter.LoanTypeAdapter;
import com.saartak.el.database.entity.LocationTable;
import com.saartak.el.database.entity.NetworkStrengthTable;
import com.saartak.el.database.entity.SODTable;
import com.saartak.el.livedata.BoundLocationManager;
import com.saartak.el.models.BearerTokenRequestDTO;
import com.saartak.el.models.BearerTokenResponseDTO;
import com.saartak.el.models.LoanTypeDto;
import com.saartak.el.models.LogOutResponseDTO;
import com.saartak.el.models.Logoff.LogOffResponseTable;
import com.saartak.el.models.UserLoginMenu.UserLoginMenuTable;
import com.saartak.el.view_models.DynamicUIViewModel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

import static com.saartak.el.constants.AppConstant.APP_ENVIRONMENT;
import static com.saartak.el.constants.AppConstant.APP_FOLDER;
import static com.saartak.el.constants.AppConstant.AUTHORIZATION_TOKEN_KEY;
import static com.saartak.el.constants.AppConstant.DATE_FORMAT_DDMMYYYYSMS;
import static com.saartak.el.constants.AppConstant.DATE_FORMAT_DD_MM_YYYY2;
import static com.saartak.el.constants.AppConstant.DATE_FORMAT_YYYY_MM_DD;
import static com.saartak.el.constants.AppConstant.DATE_FORMAT_YYYY_MM_DD_T_HH_MM_SS;
import static com.saartak.el.constants.AppConstant.DB_FOLDER;
import static com.saartak.el.constants.AppConstant.LOAN_NAME_AHL;
import static com.saartak.el.constants.AppConstant.LOAN_NAME_INDIVIDUAL;
import static com.saartak.el.constants.AppConstant.LOAN_NAME_JLG;
import static com.saartak.el.constants.AppConstant.LOAN_NAME_MSME;
import static com.saartak.el.constants.AppConstant.LOAN_NAME_PHL;
import static com.saartak.el.constants.AppConstant.LOAN_NAME_TWL;
import static com.saartak.el.constants.AppConstant.LOAN_NAME_EL;
import static com.saartak.el.constants.AppConstant.MY_PERMISSIONS_REQUEST_LOCATION;
import static com.saartak.el.constants.AppConstant.PARAM_BRANCH_GST_CODE;
import static com.saartak.el.constants.AppConstant.PARAM_BRANCH_ID;
import static com.saartak.el.constants.AppConstant.PARAM_BRANCH_NAME;
import static com.saartak.el.constants.AppConstant.PARAM_EMPLOYEE_ID;
import static com.saartak.el.constants.AppConstant.PARAM_LOAN_TYPE;
import static com.saartak.el.constants.AppConstant.PARAM_PRODUCT_ID;
import static com.saartak.el.constants.AppConstant.PARAM_ROLE_NAME;
import static com.saartak.el.constants.AppConstant.PARAM_USER_ID;
import static com.saartak.el.constants.AppConstant.PARAM_USER_NAME;
import static com.saartak.el.constants.AppConstant.PARAM_WORKFLOW_ID;
import static com.saartak.el.constants.AppConstant.PROJECT_NAME_LOS;
import static com.saartak.el.dynamicui.constants.ParametersConstant.ALERT_FOR_SYNC;
import static com.saartak.el.dynamicui.constants.ParametersConstant.ALERT_FOR_INTERNET_CONNECTION_REQUIRED_TO_FETCH_DATA;
import static com.saartak.el.dynamicui.constants.ParametersConstant.DO_YOU_WANT_PERFORM_EOD;
import static com.saartak.el.dynamicui.constants.ParametersConstant.DO_YOU_WANT_PERFORM_SOD;
import static com.saartak.el.dynamicui.constants.ParametersConstant.EOD_PERFORMED_FOR_DAY;
import static com.saartak.el.dynamicui.constants.ParametersConstant.ERROR_MESSAGE_APP_NOT_AVAILABLE_PLEASE_INSTALL;
import static com.saartak.el.dynamicui.constants.ParametersConstant.ERROR_MESSAGE_ENABLE_GPS;
import static com.saartak.el.dynamicui.constants.ParametersConstant.ERROR_MESSAGE_EOD_NOT_DONE_YESTERDAY;
import static com.saartak.el.dynamicui.constants.ParametersConstant.ERROR_MESSAGE_EXPORT_DB_FAILED;
import static com.saartak.el.dynamicui.constants.ParametersConstant.ERROR_MESSAGE_LOGOUT_FAILED;

import static com.saartak.el.dynamicui.constants.ParametersConstant.ERROR_MESSAGE_PLEASE_CONFIRM_UPLOAD_SAVED_COLLECTIONS;
import static com.saartak.el.dynamicui.constants.ParametersConstant.ERROR_MESSAGE_SYNC_FAILED;
import static com.saartak.el.dynamicui.constants.ParametersConstant.ERROR_MESSAGE_UNABLE_TO_GET_TOKEN;
import static com.saartak.el.dynamicui.constants.ParametersConstant.ERROR_MESSAGE_UNABLE_TO_PERFORM_EOD;
import static com.saartak.el.dynamicui.constants.ParametersConstant.ERROR_MESSAGE_USER_ID_IS_EMPTY;
import static com.saartak.el.dynamicui.constants.ParametersConstant.FAILURE_RESPONSE_FOR_BRANCH_PRODUCT_FEATURE_MASTER_FETCH_FROM_SERVER;
import static com.saartak.el.dynamicui.constants.ParametersConstant.FAILURE_RESPONSE_FOR_ELIGIBILITY_FETCH_FROM_SERVER;

import static com.saartak.el.dynamicui.constants.ParametersConstant.PACKAGE_NAME_HELP;
import static com.saartak.el.dynamicui.constants.ParametersConstant.PACKAGE_NAME_LETS_CONNECT;
import static com.saartak.el.dynamicui.constants.ParametersConstant.PACKAGE_NAME_TEAMS;
import static com.saartak.el.dynamicui.constants.ParametersConstant.PACKAGE_NAME_ZING_HR;
import static com.saartak.el.dynamicui.constants.ParametersConstant.REQUEST_CODE_LOCATION;
import static com.saartak.el.dynamicui.constants.ParametersConstant.SERVICE_TYPE_IDENTITY_SERVICE;
import static com.saartak.el.dynamicui.constants.ParametersConstant.SOD_PERFORMED_FOR_DAY;
import static com.saartak.el.dynamicui.constants.ParametersConstant.SUCCESS_RESPONSE_FOR_BRANCH_PRODUCT_FEATURE_MASTER_FETCH_FROM_SERVER;
import static com.saartak.el.dynamicui.constants.ParametersConstant.SUCCESS_RESPONSE_FOR_CENTER_MEETING_FETCH_FROM_SERVER;
import static com.saartak.el.dynamicui.constants.ParametersConstant.SUCCESS_RESPONSE_FOR_ELIGIBILITY_FETCH_FROM_SERVER;
import static com.saartak.el.dynamicui.constants.ParametersConstant.SUCCESS_RESPONSE_MESSAGE;
import static com.saartak.el.dynamicui.constants.ParametersConstant.SWITCH_ITEM_EOD;
import static com.saartak.el.dynamicui.constants.ParametersConstant.SWITCH_ITEM_SOD;

public class ProductActivity extends LOSBaseActivity implements HasSupportFragmentInjector, View.OnClickListener, LoanTypeAdapter.ProductCallbakInterface {
    private static final String TAG = ProductActivity.class.getCanonicalName();
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView navigationView;
    ImageView ivStaffImageND;
    TextView tvStaffId, tvStaffName, tvSOBDate;
    RecyclerView rvLoanType;
    RelativeLayout rl_no_product;
    SwitchCompat sw_sod;
    ImageView ivStaffImage;
    TextView tvUserName, tvUserId, tvAppVersion, tvCurrentDate, tvSodStatus;
    LinearLayout llLetsConnect, llZingHr, llTeams, llHelp;
    CardView cv_lets_connect, cv_zing_hr, cv_help, cv_teams;
    private Toolbar toolbar;
    TelephonyManager telephonyManager;
    myPhoneStateListener psListener;
    ArrayList<UserLoginMenuTable>  userLoginMemuList;
    public String userName, userId, branchId, branchName, branchGSTcode, roleName;
    List<LoanTypeDto> loanTypeDtoList = new ArrayList<>();

    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;

    @Inject
    public ViewModelProvider.Factory viewModelFactory;
    public DynamicUIViewModel viewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_product);

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
        sw_sod = (SwitchCompat) findViewById(R.id.sw_sod);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(ProductActivity.this, 2);
        ItemOffsetDecoration itemDecoration = new ItemOffsetDecoration(ProductActivity.this, R.dimen._2sdp);
        rvLoanType.addItemDecoration(itemDecoration);
        ViewGroup.MarginLayoutParams marginLayoutParams =
                (ViewGroup.MarginLayoutParams) rvLoanType.getLayoutParams();
        //  marginLayoutParams.setMargins(50, 0, 0, 0);
        marginLayoutParams.setMargins(0, 0, 0, 0);
        rvLoanType.setLayoutParams(marginLayoutParams);
        rvLoanType.setLayoutManager(gridLayoutManager);

        userName = getIntent().getStringExtra(PARAM_USER_NAME);
        userId = getIntent().getStringExtra(PARAM_USER_ID);
        branchId = getIntent().getStringExtra(PARAM_BRANCH_ID);
        branchName = getIntent().getStringExtra(PARAM_BRANCH_NAME);
        branchGSTcode = getIntent().getStringExtra(PARAM_BRANCH_GST_CODE);
        if (TextUtils.isEmpty(branchGSTcode)) {
            branchGSTcode = branchId;
        }
        roleName = getIntent().getStringExtra(PARAM_ROLE_NAME);
//        loanType = getIntent().getStringExtra(PARAM_LOAN_TYPE);

        if (!TextUtils.isEmpty(userName)) {
            tvUserName.setText(userName);
        }
        if (!TextUtils.isEmpty(userId)) {
            tvUserId.setText(userId);
        }


        // TODO: List of icons
//        loanTypeDtoList.add(new LoanTypeDto("JLG", R.drawable.pay_date));
//        loanTypeDtoList.add(new LoanTypeDto("INDIVIDUAL", R.drawable.individual));
//        loanTypeDtoList.add(new LoanTypeDto("MSME", R.drawable.cash_in_hand));
//        loanTypeDtoList.add(new LoanTypeDto("TW", R.drawable.bank_building));
//        loanTypeDtoList.add(new LoanTypeDto("TW", R.drawable.tw_));
        loanTypeDtoList.add(new LoanTypeDto("EL", R.drawable.el_img));
       // loanTypeDtoList.add(new LoanTypeDto("TWL", R.drawable.two_wheeler_loan));



//        if(roleName.equalsIgnoreCase(ROLE_NAME_LO) || roleName.equalsIgnoreCase(ROLE_NAME_RO)){
//            loanTypeDtoList.add(new LoanTypeDto("JLG", R.drawable.jlg_));
//            loanTypeDtoList.add(new LoanTypeDto("INDIVIDUAL", R.drawable.individual_));
//            loanTypeDtoList.add(new LoanTypeDto("MSME", R.drawable.msme_));
//
//        }else if(roleName.equalsIgnoreCase(ROLE_NAME_SM)){
//            loanTypeDtoList.add(new LoanTypeDto("AHL", R.drawable.house_));
//        }


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
        SharedPreferences sharedPreferences=getSharedPreferences("shared preferences",MODE_PRIVATE);
        Gson gson= new Gson();
        String json= sharedPreferences.getString("user menu list",null);
        Type type= new TypeToken<ArrayList<UserLoginMenuTable>>(){}.getType();
        userLoginMemuList=gson.fromJson(json,type);
        if(userLoginMemuList== null){
            userLoginMemuList=new ArrayList<>();
        }
        LoanTypeAdapter loanTypeAdapter = new LoanTypeAdapter(ProductActivity.this, loanTypeDtoList, userLoginMemuList,this);
        rvLoanType.setAdapter(loanTypeAdapter);

        sw_sod.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                // TODO: insert sod data
                if (compoundButton.isPressed()) {
                    String strNotificationMsg = DO_YOU_WANT_PERFORM_SOD;
                    if (isChecked) {
                        strNotificationMsg = DO_YOU_WANT_PERFORM_SOD;
                    } else {
                        strNotificationMsg = DO_YOU_WANT_PERFORM_EOD;
                    }
                    appHelper.getDialogHelper().getConfirmationDialog().showTwoButtons(strNotificationMsg,
                            " Yes", " No", new ConfirmationDialog.PrintActionCallback() {
                                @Override
                                public void onAction() {
                                    if (isChecked) {
                                        sw_sod.setChecked(false);
                                    } else {
                                        sw_sod.setChecked(true);
                                    }
                                }

                                @Override
                                public void onPrint() {
                                    getPendingCentersInJLGcollection(userName, userId, branchId, branchGSTcode, isChecked, null);
                                    //    insertDataInSODTable(userName, userId, branchId, branchGSTcode, isChecked);
                                }
                            });
//                   insertDataInSODTable(userName, userId, branchId, branchGSTcode, isChecked);
                }
            }
        });

//        sw_sod.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                insertDataInSODTable(userName, userId, branchId, branchGSTcode, sw_sod.isChecked());
//
//            }
//        });

        String empIDlastFive = "";
        if (getIntent() != null) {
            empIDlastFive = getIntent().getStringExtra(PARAM_EMPLOYEE_ID);
        }

        // TODO: CREATING RANDOM 6 DIGIT CLIENT ID
//        CLIENT_ID= AppConstant.getRandomNumberString()+empIDlastFive;


        configureDagger();
        configureViewModel();
//        getGPSLocation(); // TODO: GPS LOCATION

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

    public void selectItemDrawer(MenuItem menuItem) {
        try {
            switch (menuItem.getItemId()) {
                case R.id.menu_item_export_db:
//                    drawerLayout.closeDrawer(Gravity.LEFT);
                    uploadZipFolder();
                    break;
                case R.id.menu_item_export_log:
                    //  uploadZipFolder();
                    sendLOGToServer();
                    break;
                case R.id.menu_item_sync:

                    if (appHelper.isNetworkAvailable()) {
                        appHelper.getDialogHelper().getConfirmationDialog().showTwoButtons(ALERT_FOR_SYNC, new ConfirmationDialog.ActionCallback() {
                            @Override
                            public void onAction() {
                                getCenterMeetingDetailsFromServer();
                            }
                        });

                    } else {
                        appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, ALERT_FOR_INTERNET_CONNECTION_REQUIRED_TO_FETCH_DATA);
                    }
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

//                            finish();
                            callLogOutService(userId);
                        }
                    });
                    break;
                case R.id.menu_item_knowledge_bank:
                    Intent intentKnowledge = new Intent(this, KnowledgeBankHeaderActivity.class);
                    intentKnowledge.putExtra(PARAM_USER_NAME, userName);
                    intentKnowledge.putExtra(PARAM_USER_ID, userId);
                    intentKnowledge.putExtra(PARAM_BRANCH_ID, branchId);
                    intentKnowledge.putExtra(PARAM_BRANCH_GST_CODE, branchGSTcode);
                    startActivity(intentKnowledge);
                    break;
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        appHelper.getDialogHelper().getConfirmationDialog().showTwoButtons("Do you want to logout ? ", new ConfirmationDialog.ActionCallback() {
            @Override
            public void onAction() {
//                finish();

                // TODO: Call log out service
                callLogOutService(userId);
            }
        });

    }

    private void configureDagger() {
        AndroidInjection.inject(this);
    }

    public void configureViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(DynamicUIViewModel.class);

        if (TextUtils.isEmpty(appHelper.getSharedPrefObj().getString(AUTHORIZATION_TOKEN_KEY, ""))) {
            // TODO: GET BEARER TOKEN
            getBearerToken();
            //getUserLoginMenuFromServer();
        } else {

            //getSODDataFromLocalDB(userId); // TODO: get sod dataa
        }
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return dispatchingAndroidInjector;
    }

   /* public void getGPSLocation(){
        try {
            if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSIONS_REQUEST_LOCATION);
                }
            else {
                getLocationUpdates();
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }*/

    @SuppressLint("NewApi")
    public void getGPSLocation() {

        try {
            if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSIONS_REQUEST_LOCATION);
            else {
                // TODO: Uncomment only for MSME
                if (appHelper.isGPSEnabled()) {
                    getLocationUpdates();
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setMessage(ERROR_MESSAGE_ENABLE_GPS)
                            .setCancelable(false)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    Intent intent1 = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                                    startActivityForResult(intent1, REQUEST_CODE_LOCATION);
                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.show();
                }
            }


        } catch (Exception ex) {
            ex.printStackTrace();
            INSERT_LOG("getGPSLocation", "Exception : " + ex.getMessage());
        }
    }

    private void getLocationUpdates() {
        try {
            BoundLocationManager.getInstance(getApplicationContext()).observe(this, new Observer<Location>() {
                @Override
                public void onChanged(@Nullable Location location) {
                    if (location != null) {
                        LocationTable locationTable = new LocationTable(1, String.valueOf(location.getLatitude()),
                                String.valueOf(location.getLongitude()));
                        viewModel.insertGPSLocation(locationTable);
                        Observer observer = new Observer() {
                            @Override
                            public void onChanged(@Nullable Object o) {
                                viewModel.getLocationTableLiveData().removeObserver(this);
                            }
                        };
                        viewModel.getLocationTableLiveData().observe(ProductActivity.this, observer);

                    }
                }
            });
        } catch (Exception ex) {
            ex.printStackTrace();
            INSERT_LOG("getLocationUpdates", "Exception : " + ex.getMessage());
        }
    }

    private void getNetworkSignalStrength() {
        try {
            psListener = new myPhoneStateListener();
            telephonyManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
            telephonyManager.listen(psListener,PhoneStateListener.LISTEN_SIGNAL_STRENGTHS);
        } catch (Exception ex) {
            ex.printStackTrace();
            INSERT_LOG("getLocationUpdates", "Exception : " + ex.getMessage());
        }
    }

    public class myPhoneStateListener extends PhoneStateListener {
        private int signalStrengthValue;

        public void onSignalStrengthsChanged(SignalStrength signalStrength) {
            super.onSignalStrengthsChanged(signalStrength);
            try {
                if (signalStrength.isGsm()) {
                    if (signalStrength.getGsmSignalStrength() != 99)
                        signalStrengthValue = signalStrength.getGsmSignalStrength() * 2 - 113;
                    else
                        signalStrengthValue = signalStrength.getGsmSignalStrength();
                } else {
                    signalStrengthValue = signalStrength.getCdmaDbm();
                }
                Log.d(TAG,"Signal Strength : " + signalStrengthValue);

                NetworkStrengthTable networkStrengthTable = new NetworkStrengthTable(1, signalStrengthValue);
                viewModel.insertNetworkStrength(networkStrengthTable);
                if(viewModel.getNetworkStrengthTableLiveData()!=null) {
                    Observer observer = new Observer() {
                        @Override
                        public void onChanged(@Nullable Object o) {
                            viewModel.getNetworkStrengthTableLiveData().removeObserver(this);
                        }
                    };
                    viewModel.getNetworkStrengthTableLiveData().observe(ProductActivity.this, observer);
                }

            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }

   /* private void callLogOutService(String userId) {
        try {
            appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();
            viewModel.logOutService(userId);
            if (viewModel.getLogOutResponseDTOLiveData() != null) {
                Observer observer = new Observer() {
                    @Override
                    public void onChanged(Object o) {

                        appHelper.getDialogHelper().getLoadingDialog().closeDialog();

                        LogOutResponseDTO logOutResponseDTO = (LogOutResponseDTO) o;
                        viewModel.getLogOutResponseDTOLiveData().removeObserver(this);

                        if (logOutResponseDTO != null && !TextUtils.isEmpty(logOutResponseDTO.getError())
                                && logOutResponseDTO.getError().equalsIgnoreCase("0")
                        ) {
                            // TODO: LOG OUT SUCCESS
                            // TODO: Finish the activity
                            finish();

                        } else {
                            String errorMessage = ERROR_MESSAGE_LOGOUT_FAILED;
                            if (logOutResponseDTO != null && !TextUtils.isEmpty(logOutResponseDTO.getMessage())) {
                                errorMessage = logOutResponseDTO.getMessage();
                            }
                            appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT,
                                    errorMessage);
                        }
                    }
                };
                viewModel.getLogOutResponseDTOLiveData().observe(this, observer);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            appHelper.getDialogHelper().getLoadingDialog().closeDialog();
            INSERT_LOG("callLogOutService", "Exception : " + ex.getMessage());
        }
    }*/

    public void callLogOutService(String userId) {
        try {
            appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();
            viewModel.getLogOff(userId);
            if (viewModel.getLogOffResponseTableLiveData() != null) {
                Observer observer = new Observer() {
                    @Override
                    public void onChanged(Object o) {
                        appHelper.getDialogHelper().getLoadingDialog().closeDialog();
                        ArrayList<LogOffResponseTable> logOffResponseTable = (ArrayList<LogOffResponseTable>) o;
                        viewModel.getLogOffResponseTableLiveData().removeObserver(this);

                        if (logOffResponseTable != null && logOffResponseTable.size() > 0) {
                            if (logOffResponseTable != null && !TextUtils.isEmpty(logOffResponseTable.get(0).getError())
                                    && logOffResponseTable.get(0).getError().equalsIgnoreCase("0")) {
                                // TODO: LOG OUT SUCCESS
                                // TODO: Finish the activity
                                finish();

                            } else {
                                String errorMessage = ERROR_MESSAGE_LOGOUT_FAILED;
                                if (logOffResponseTable != null && !TextUtils.isEmpty(logOffResponseTable.get(0).getMessage())) {
                                    errorMessage = logOffResponseTable.get(0).getMessage();
                                }
                                appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT,
                                        errorMessage);
                            }
                        }
                    }
                };
                viewModel.getLogOffResponseTableLiveData().observe(this, observer);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            appHelper.getDialogHelper().getLoadingDialog().closeDialog();
        }
    }

    private void uploadZipFolder() {
        if (!TextUtils.isEmpty(userId)) {
            try {
                String zipPath = zip(userId);

                if (!TextUtils.isEmpty(zipPath)) {
                    appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();
                    viewModel.uploadZipFolder(userId, zipPath, userId + "_" + DB_FOLDER + ".zip", LOAN_NAME_EL);
                    if (viewModel.getStringLiveData() != null) {
                        Observer observer = new Observer() {
                            @Override
                            public void onChanged(Object o) {

                                appHelper.getDialogHelper().getLoadingDialog().closeDialog();

                                String responseFromServer = (String) o;
                                viewModel.getStringLiveData().removeObserver(this);
                                if (!TextUtils.isEmpty(responseFromServer)
                                        && responseFromServer.equalsIgnoreCase("Success")
                                ) {
                                    // TODO: EXPORT DB SUCCESS
                                    appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.SUCCESS,
                                            responseFromServer);

                                } else {
                                    String errorMessage = ERROR_MESSAGE_EXPORT_DB_FAILED;
                                    if (!TextUtils.isEmpty(responseFromServer)) {
                                        errorMessage = responseFromServer;
                                    }
                                    appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT,
                                            errorMessage);
                                }
                            }
                        };
                        viewModel.getStringLiveData().observe(this, observer);
                    }
                } else {
                    appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ERROR,
                            ERROR_MESSAGE_USER_ID_IS_EMPTY);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                appHelper.getDialogHelper().getLoadingDialog().closeDialog();
                INSERT_LOG("uploadZipFolder", "Exception : " + ex.getMessage());
            }
        } else {
            appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ERROR,
                    "User ID Is Empty");

            INSERT_LOG("uploadZipFolder", "User Id Is Empty");
        }
    }

    private void getBearerToken() {

       //appHelper.getSharedPrefObj().edit().putString(AUTHORIZATION_TOKEN_KEY, RESET_GET_IDENTITY_TOKEN).apply();

       try {
            appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();

            BearerTokenRequestDTO bearerTokenRequestDTO = new BearerTokenRequestDTO();
            BearerTokenRequestDTO.RequestStringClass requestStringClass = new BearerTokenRequestDTO.RequestStringClass();
            BearerTokenRequestDTO.IdentityRequestClass identityRequestClass = new BearerTokenRequestDTO.IdentityRequestClass();

            if (APP_ENVIRONMENT.equalsIgnoreCase("DEV") || APP_ENVIRONMENT.equalsIgnoreCase("UAT")) {
                identityRequestClass.setEnvironmentType("1");
            } else {
                identityRequestClass.setEnvironmentType("2");
            }

            identityRequestClass.setIMEINumber(appHelper.getIMEI());
            identityRequestClass.setProjectName(PROJECT_NAME_LOS);
            identityRequestClass.setUserId(userId);

            // TODO: Setting App Version
            try {
                PackageInfo packageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
                String appVersion = packageInfo.versionName;
                if (!TextUtils.isEmpty(appVersion)) {
                    identityRequestClass.setP1(appVersion);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            requestStringClass.setIdentityRequest(identityRequestClass);

            String timeStamp = appHelper.getCurrentDateTime(DATE_FORMAT_DDMMYYYYSMS);
            bearerTokenRequestDTO.setUniqueId(timeStamp);
            // bearerTokenRequestDTO.setClientID(CLIENT_ID);
            bearerTokenRequestDTO.setCreatedDate(appHelper.getCurrentDateTime(DATE_FORMAT_YYYY_MM_DD_T_HH_MM_SS));
            bearerTokenRequestDTO.setCreatedBy(userId);
            bearerTokenRequestDTO.setServiceType(SERVICE_TYPE_IDENTITY_SERVICE);
            bearerTokenRequestDTO.setRequestString(requestStringClass);

            viewModel.getBearerToken(bearerTokenRequestDTO);

            if (viewModel.getBearerTokenResponseDTOLiveData() != null) {
                Observer observer = new Observer() {
                    @Override
                    public void onChanged(Object o) {

                        appHelper.getDialogHelper().getLoadingDialog().closeDialog();

                        BearerTokenResponseDTO bearerTokenResponseDTO = (BearerTokenResponseDTO) o;

                        viewModel.getBearerTokenResponseDTOLiveData().removeObserver(this);

                        if (bearerTokenResponseDTO != null && bearerTokenResponseDTO.getApiResponse() != null
                                && bearerTokenResponseDTO.getApiResponse().getIdentityResponse() != null
                                && bearerTokenResponseDTO.getApiResponse().getIdentityResponse().getStatus().equalsIgnoreCase("1")
                                && !TextUtils.isEmpty(bearerTokenResponseDTO.getApiResponse().getIdentityResponse().getIdentityId())) {
                            // TODO:  SUCCESS
                            String token = "Bearer " + bearerTokenResponseDTO.getApiResponse().getIdentityResponse().getIdentityId();

                            // TODO: Inserting into key store
                            //appHelper.getSharedPrefObj().edit().putString(AUTHORIZATION_TOKEN_KEY, RESET_PASSWORD_TOKEN).apply();
                            appHelper.getSharedPrefObj().edit().putString(AUTHORIZATION_TOKEN_KEY, token).apply();

                            //getSODDataFromLocalDB(userId); // TODO: get sod data

                            // TODO: Send Log to server
//                            sendLOGToServer();

                        } else {
                            String errorMessage = ERROR_MESSAGE_UNABLE_TO_GET_TOKEN;
                            if (bearerTokenResponseDTO != null
                                    && !TextUtils.isEmpty(bearerTokenResponseDTO.getErrorMessage())) {
                                errorMessage = bearerTokenResponseDTO.getErrorMessage();
                            }
                            if (bearerTokenResponseDTO != null && bearerTokenResponseDTO.getApiResponse() != null
                                    && !TextUtils.isEmpty(bearerTokenResponseDTO.getApiResponse().getIdentityResponse().getErrorMessage())) {
                                errorMessage = bearerTokenResponseDTO.getApiResponse().getIdentityResponse().getErrorMessage();
                            }
                            appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT,
                                    errorMessage, new ConfirmationDialog.ActionCallback() {
                                        @Override
                                        public void onAction() {

                                            // TODO: Clearing shared preference
                                            appHelper.getSharedPrefObj().edit().remove(AUTHORIZATION_TOKEN_KEY).apply();

                                            // TODO: Finish the activity
//                                            finish();
                                            callLogOutService(userId);
                                        }
                                    });
                        }
                    }
                };
                viewModel.getBearerTokenResponseDTOLiveData().observe(this, observer);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            appHelper.getDialogHelper().getLoadingDialog().closeDialog();

            // TODO: Clearing shared preference
            appHelper.getSharedPrefObj().edit().remove(AUTHORIZATION_TOKEN_KEY).apply();

            INSERT_LOG("getBearerToken", "Exception : " + ex.getMessage());

        }
    }


    public void insertDataInSODTable(String userName, String userId, String branchId, String branchGSTcode, boolean isChecked) {
        try {

            appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();

            viewModel.insertOrUpdateSODData(userName, userId, branchId, branchGSTcode, isChecked);
            if (viewModel.getSODTableLiveData() != null) {
                Observer getSODDataObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        SODTable sodTable = (SODTable) o;
                        viewModel.getSODTableLiveData().removeObserver(this);

                        appHelper.getDialogHelper().getLoadingDialog().closeDialog();

                        if (sodTable != null) {
                            if (!sodTable.isSOD()) {
//                                sw_sod.setChecked(false);
                               // rvLoanType.setVisibility(View.GONE);
                                sw_sod.setText(SWITCH_ITEM_SOD);
                                tvSodStatus.setText("");
                                rl_no_product.setVisibility(View.VISIBLE);
                            } else if (sodTable.isSOD() && !sodTable.isEOD()) {
//                                sw_sod.setChecked(true);
                                sw_sod.setText(SWITCH_ITEM_EOD);
                                tvSodStatus.setText(SOD_PERFORMED_FOR_DAY + tvCurrentDate.getText().toString());
                               // rvLoanType.setVisibility(View.VISIBLE);
                                rl_no_product.setVisibility(View.GONE);
                            } else if (sodTable.isSOD() && sodTable.isEOD()) {
//                                sw_sod.setChecked(false);

                                // TODO: SOD EOD both performed , do service call here
                                SODEODServiceCall(sodTable);


                            }
                        }
                    }
                };
                viewModel.getSODTableLiveData().observe(this, getSODDataObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            appHelper.getDialogHelper().getLoadingDialog().closeDialog();

            INSERT_LOG("insertDataInSODTable", "Exception : " + ex.getMessage());
        }
    }

    private void getPendingCentersInJLGcollection(String userName, String userId, String branchId, String branchGSTcode, boolean isChecked, SODTable sodTable) {
        try {
            appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();
            viewModel.getPendingCentersInJLGcollection(userId);
            if (viewModel.getCenterMeetingCollectionTableLiveDataList() != null) {
                Observer observer = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        appHelper.getDialogHelper().getLoadingDialog().closeDialog();
                        List<CenterMeetingCollectionTable> centerMeetingCollectionTableList = (List<CenterMeetingCollectionTable>) o;
                        viewModel.getCenterMeetingCollectionTableLiveDataList().removeObserver(this::onChanged);

                        if(centerMeetingCollectionTableList!=null && centerMeetingCollectionTableList.size()>0){
                            sw_sod.setChecked(true);
                            sw_sod.setText(SWITCH_ITEM_EOD);
                            if(sodTable!=null) {
                                tvSodStatus.setText(SOD_PERFORMED_FOR_DAY + sodTable.getCreated_date());
                            }
                            appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT,
                                    ERROR_MESSAGE_PLEASE_CONFIRM_UPLOAD_SAVED_COLLECTIONS);

                        }else{

                            insertDataInSODTable(userName, userId, branchId, branchGSTcode, isChecked);
                        }

                    }
                };
                viewModel.getCenterMeetingCollectionTableLiveDataList().observe(this, observer);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            appHelper.getDialogHelper().getLoadingDialog().closeDialog();
            INSERT_LOG("getPendingCentersInJLGcollection", "Exception : " + ex.getMessage());
        }

    }

    private void SODEODServiceCall(SODTable sodTable) {
        try {
            appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();

            viewModel.SODEODServiceCall(sodTable);
            if (viewModel.getSODTableLiveData() != null) {
                Observer observer = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        appHelper.getDialogHelper().getLoadingDialog().closeDialog();
                        SODTable sodTableFromDb = (SODTable) o;
                        viewModel.getSODTableLiveData().removeObserver(this);

                        if (sodTableFromDb != null && !TextUtils.isEmpty(sodTableFromDb.getResponse())
                                && sodTableFromDb.getResponse().equalsIgnoreCase(SUCCESS_RESPONSE_MESSAGE)) {

                            // TODO: Success Case
                            String currentDate = appHelper.getCurrentDate(DATE_FORMAT_YYYY_MM_DD);
                            if (!currentDate.equalsIgnoreCase(sodTable.getCreated_date())) {
                                sw_sod.setEnabled(true);
                            } else {
                                sw_sod.setEnabled(false);
                            }
                            sw_sod.setText(SWITCH_ITEM_SOD);
                            tvSodStatus.setText(EOD_PERFORMED_FOR_DAY + tvCurrentDate.getText().toString());
                            rvLoanType.setVisibility(View.GONE);
                            rl_no_product.setVisibility(View.VISIBLE);

                        } else {
                            String errorMessage = ERROR_MESSAGE_UNABLE_TO_PERFORM_EOD;
                            if (sodTableFromDb != null && !TextUtils.isEmpty(sodTableFromDb.getResponse())) {
                                errorMessage = sodTableFromDb.getResponse();
                            }
                            appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ERROR, errorMessage,
                                    new ConfirmationDialog.ActionCallback() {
                                        @Override
                                        public void onAction() {
                                            // TODO: For Failure case
//                                          sw_sod.setText(SWITCH_ITEM_EOD);
//                                          tvSodStatus.setText(SOD_PERFORMED_FOR_DAY+tvCurrentDate.getText().toString());
//                                          rvLoanType.setVisibility(View.VISIBLE);
//                                          rl_no_product.setVisibility(View.GONE);

                                            sw_sod.setChecked(true);
                                            sw_sod.setText(SWITCH_ITEM_EOD);
                                            tvSodStatus.setText(SOD_PERFORMED_FOR_DAY + tvCurrentDate.getText().toString());
                                            rvLoanType.setVisibility(View.VISIBLE);
                                            rl_no_product.setVisibility(View.GONE);
                                        }
                                    });
                        }
                    }
                };
                viewModel.getSODTableLiveData().observe(this, observer);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            appHelper.getDialogHelper().getLoadingDialog().closeDialog();
            INSERT_LOG("SODEODServiceCall", "Exception : " + ex.getMessage());
        }
    }

    public void getSODDataFromLocalDB(String userId) {
        try {
            appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();

            viewModel.getSODDataFromLocalDB(userId);
            if (viewModel.getSODTableLiveData() != null) {
                Observer getSODDataObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        SODTable sodTable = (SODTable) o;
                        viewModel.getSODTableLiveData().removeObserver(this);

                        appHelper.getDialogHelper().getLoadingDialog().closeDialog();

                        if (sodTable != null && sodTable.getStaffId() != null) {

                            if (!sodTable.isSOD()) {
                                sw_sod.setChecked(false);
                                sw_sod.setText(SWITCH_ITEM_SOD);
                                tvSodStatus.setText("");
                                rvLoanType.setVisibility(View.GONE);
                                rl_no_product.setVisibility(View.VISIBLE);
                            } else if (sodTable.isSOD() && !sodTable.isEOD()) {
                                String currentDate = appHelper.getCurrentDate(DATE_FORMAT_YYYY_MM_DD);
                                if (!currentDate.equalsIgnoreCase(sodTable.getCreated_date())) {
                                    appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, ERROR_MESSAGE_EOD_NOT_DONE_YESTERDAY, new ConfirmationDialog.ActionCallback() {
                                        @Override
                                        public void onAction() {

                                            getPendingCentersInJLGcollection(userName, userId, branchId, branchGSTcode, false, sodTable);

                                        }
                                    });
                                } else {
                                    sw_sod.setChecked(true);
                                    sw_sod.setText(SWITCH_ITEM_EOD);
                                    tvSodStatus.setText(SOD_PERFORMED_FOR_DAY + tvCurrentDate.getText().toString());
                                    rvLoanType.setVisibility(View.VISIBLE);
                                    rl_no_product.setVisibility(View.GONE);
                                }
                            } else if (sodTable.isSOD() && sodTable.isEOD()) {
                                String currentDate = appHelper.getCurrentDate(DATE_FORMAT_YYYY_MM_DD);
                                if (!currentDate.equalsIgnoreCase(sodTable.getCreated_date())) {
                                    sw_sod.setEnabled(true);
                                } else {
                                    sw_sod.setEnabled(false);
                                }

                                sw_sod.setChecked(false);
                                sw_sod.setText(SWITCH_ITEM_SOD);
                                tvSodStatus.setText(EOD_PERFORMED_FOR_DAY + tvCurrentDate.getText().toString());
                                rvLoanType.setVisibility(View.GONE);
                                rl_no_product.setVisibility(View.VISIBLE);
                            }
                        } else {
//                            sw_sod.setChecked(false);
                            sw_sod.setEnabled(true);
                            sw_sod.setEnabled(true);
                            sw_sod.setText(SWITCH_ITEM_SOD);
                            tvSodStatus.setText("");
                            rvLoanType.setVisibility(View.GONE);
                            rl_no_product.setVisibility(View.VISIBLE);
                        }
                    }
                };
                viewModel.getSODTableLiveData().observe(this, getSODDataObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            appHelper.getDialogHelper().getLoadingDialog().closeDialog();

            INSERT_LOG("getSODDataFromLocalDB", "Exception : " + ex.getMessage());
        }
    }

    public String zip(String staffId) {
        try {
            String appDBPath = Environment.getExternalStorageDirectory()
                    .getAbsolutePath() + File.separator + APP_FOLDER + File.separator + DB_FOLDER;
            String zipPath = Environment.getExternalStorageDirectory()
                    .getAbsolutePath() + File.separator + APP_FOLDER + File.separator + staffId + "_" + DB_FOLDER + ".zip";
            FileOutputStream fos = new FileOutputStream(zipPath);
            ZipOutputStream zipOut = new ZipOutputStream(fos);
            File fileToZip = new File(appDBPath);

            zipFile(fileToZip, fileToZip.getName(), zipOut);
            zipOut.close();
            fos.close();

            return zipPath;
        } catch (Exception ex) {
            ex.printStackTrace();
            INSERT_LOG("zip", "Exception : " + ex.getMessage());
        }

        return "";
    }

    private void zipFile(File fileToZip, String fileName, ZipOutputStream zipOut) {
        try {
            if (fileToZip.isHidden()) {
                return;
            }
            if (fileToZip.isDirectory()) {
                if (fileName.endsWith("/")) {
                    zipOut.putNextEntry(new ZipEntry(fileName));
                    zipOut.closeEntry();
                } else {
                    zipOut.putNextEntry(new ZipEntry(fileName + "/"));
                    zipOut.closeEntry();
                }
                File[] children = fileToZip.listFiles();
                for (File childFile : children) {
                    zipFile(childFile, fileName + "/" + childFile.getName(), zipOut);
                }
                return;
            }
            FileInputStream fis = new FileInputStream(fileToZip);
            ZipEntry zipEntry = new ZipEntry(fileName);
            zipOut.putNextEntry(zipEntry);
            byte[] bytes = new byte[1024];
            int length;
            while ((length = fis.read(bytes)) >= 0) {
                zipOut.write(bytes, 0, length);
            }
            fis.close();

        } catch (Exception ex) {
            ex.printStackTrace();
            INSERT_LOG("zipFile", "Exception : " + ex.getMessage());
        }
    }


    private void sendLOGToServer() {
        try {
            appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();

            viewModel.sendLOGToServer(userId);
            if (viewModel.getStringLiveData() != null) {
                Observer observer = new Observer() {
                    @Override
                    public void onChanged(Object o) {
                        appHelper.getDialogHelper().getLoadingDialog().closeDialog();
                        String response = (String) o;
                        Log.d(TAG, "sendLOGToServer ==> " + response);
                        viewModel.getStringLiveData().removeObserver(this);
                    }
                };
                viewModel.getStringLiveData().observe(this, observer);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            appHelper.getDialogHelper().getLoadingDialog().closeDialog();
            INSERT_LOG("sendLOGToServer", "Exception : " + ex.getMessage());
        }
    }

    private void getCenterMeetingDetailsFromServer() {
        try {
            appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();

//            String cmDateFromCalendar = appHelper.getCurrentDate(DATE_FORMAT_YYYYMMDD);
            String userId="SIF1000059";
//            String cmDateFromCalendar = appHelper.getCurrentDate(DATE_FORMAT_YYYYMMDD);
            String cmDateFromCalendar = "20200519"; // TODO: Hardcoded date
            viewModel.getCenterMeetingDetailsFromServer(cmDateFromCalendar, userId);
            if (viewModel.getStringLiveData() != null) {
                Observer observer = new Observer() {
                    @Override
                    public void onChanged(Object o) {
                        appHelper.getDialogHelper().getLoadingDialog().closeDialog();
                        String response = (String) o;
                        Log.d(TAG, "getCenterMeetingDetailsFromServer ==> " + response);
                        viewModel.getStringLiveData().removeObserver(this);
                        if (!TextUtils.isEmpty(response) && response.equalsIgnoreCase(SUCCESS_RESPONSE_MESSAGE)) {
                            appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.SUCCESS,
                                    SUCCESS_RESPONSE_FOR_CENTER_MEETING_FETCH_FROM_SERVER, new ConfirmationDialog.ActionCallback() {
                                        @Override
                                        public void onAction() {
                                            // TODO: Calling Eligibility data tvName server
                                            getEligibilityDataFromServer(cmDateFromCalendar, userId);
                                        }
                                    });
                        } else {
                            appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ERROR,
                                    ERROR_MESSAGE_SYNC_FAILED);
                        }
                    }
                };
                viewModel.getStringLiveData().observe(this, observer);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            appHelper.getDialogHelper().getLoadingDialog().closeDialog();
            INSERT_LOG("getCenterMeetingDetailsFromServer", "Exception : " + ex.getMessage());
        }
    }

    public void getEligibilityDataFromServer(String centerMeetingDate, String userId) {
        try {
            appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();

            viewModel.getEligibilityDataFromServer(centerMeetingDate, userId);
            if (viewModel.getStringLiveData() != null) {
                Observer observer = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        appHelper.getDialogHelper().getLoadingDialog().closeDialog();
                        String response = (String) o;
                        Log.d(TAG, "getEligibilityDataFromServer ==>  " + response);
                        viewModel.getStringLiveData().removeObserver(this);
                        if (!TextUtils.isEmpty(response) && response.equalsIgnoreCase(SUCCESS_RESPONSE_MESSAGE)) {
                            appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.SUCCESS,
                                    SUCCESS_RESPONSE_FOR_ELIGIBILITY_FETCH_FROM_SERVER, new ConfirmationDialog.ActionCallback() {
                                        @Override
                                        public void onAction() {

                                            // TODO: Calling Branch Product Feature Master Table
//                                            getBranchProductFeatureMasterDataFromServer(userId);
                                        }
                                    });
                        } else {
                            appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ERROR,
                                    FAILURE_RESPONSE_FOR_ELIGIBILITY_FETCH_FROM_SERVER);
                        }
                    }
                };
                viewModel.getStringLiveData().observe(this, observer);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            appHelper.getDialogHelper().getLoadingDialog().closeDialog();
            INSERT_LOG("getEligibilityDataFromServer", "Exception : " + ex.getMessage());

        }
    }

    public void getBranchProductFeatureMasterDataFromServer(String userId) {
        try {
            appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();

            viewModel.getBranchProductFeatureMasterDataFromServer(userId);
            if (viewModel.getStringLiveData() != null) {
                Observer observer = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        appHelper.getDialogHelper().getLoadingDialog().closeDialog();
                        String response = (String) o;
                        Log.d(TAG, "getBranchProductFeatureMasterDataFromServer ==>  " + response);
                        viewModel.getStringLiveData().removeObserver(this);
                        if (!TextUtils.isEmpty(response) && response.equalsIgnoreCase(SUCCESS_RESPONSE_MESSAGE)) {
                            appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.SUCCESS,
                                    SUCCESS_RESPONSE_FOR_BRANCH_PRODUCT_FEATURE_MASTER_FETCH_FROM_SERVER, new ConfirmationDialog.ActionCallback() {
                                        @Override
                                        public void onAction() {
                                            drawerLayout.closeDrawer(Gravity.LEFT);
                                        }
                                    });
                        } else {
                            appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ERROR,
                                    FAILURE_RESPONSE_FOR_BRANCH_PRODUCT_FEATURE_MASTER_FETCH_FROM_SERVER);
                        }
                    }
                };
                viewModel.getStringLiveData().observe(this, observer);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            appHelper.getDialogHelper().getLoadingDialog().closeDialog();
            INSERT_LOG("getBranchProductFeatureMasterDataFromServer", "Exception : " + ex.getMessage());

        }
    }

    private void INSERT_LOG(String methodName, String message) {
        try {
            if (viewModel != null) {
                viewModel.insertLog(methodName, message, userId, "", "ProductActivity"
                        , CLIENT_ID, loanType, moduleType);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
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
            INSERT_LOG("onClick", "Exception : " + ex.getMessage());

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //  super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == 0) {
            switch (requestCode) {
                case REQUEST_CODE_LOCATION:
                    getLocationUpdates();
                    break;
            }
        }
    }

    @Override
    public void openProductScreenCallback(String loanType, String productId,String workflowId) {
        try {
            if (!TextUtils.isEmpty(loanType) && loanType.equalsIgnoreCase(LOAN_NAME_JLG)) {
//                if (roleName.equalsIgnoreCase(ROLE_NAME_LO) || roleName.equalsIgnoreCase(ROLE_NAME_BM)) {
//                    Intent intent = new Intent(ProductActivity.this, JLGHomeActivity.class);
//                    intent.putExtra(PARAM_LOAN_TYPE, loanType);
//                    intent.putExtra(PARAM_USER_NAME, userName);
//                    intent.putExtra(PARAM_BRANCH_ID, branchId);
//                    intent.putExtra(PARAM_BRANCH_NAME, branchName);
//                    intent.putExtra(PARAM_BRANCH_GST_CODE, branchGSTcode);
//                    intent.putExtra(PARAM_USER_ID, userId);
//                    intent.putExtra(PARAM_PRODUCT_ID, productId);
//                    intent.putExtra(PARAM_ROLE_NAME, roleName);
//                    startActivity(intent);
//                } else {
//                    appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, roleName + " can not access " + loanType);
//                }
            } else if (!TextUtils.isEmpty(loanType) && loanType.equalsIgnoreCase(LOAN_NAME_MSME)) {

//                if (roleName.equalsIgnoreCase(ROLE_NAME_RO)) {
                getIntentToHomeActivity(loanType,productId,workflowId);
//                } else {
//                    appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, roleName + " can not access " + loanType);
//
//                }
            }
            else if (!TextUtils.isEmpty(loanType) && loanType.equalsIgnoreCase(LOAN_NAME_PHL)) {
//                if (roleName.equalsIgnoreCase(ROLE_NAME_SM)) {
                getIntentToHomeActivity(loanType,productId,workflowId);
//                } else {
//                    appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, roleName + " can not access " + loanType);
//
//                }
            }else if (!TextUtils.isEmpty(loanType) && loanType.equalsIgnoreCase(LOAN_NAME_EL)) {
//                if (roleName.equalsIgnoreCase(ROLE_NAME_SM)) {
                getIntentToHomeActivity(loanType,productId,workflowId);
//                } else {
//                    appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, roleName + " can not access " + loanType);
//
//                }
            }else if (!TextUtils.isEmpty(loanType) && loanType.equalsIgnoreCase(LOAN_NAME_TWL)) {
//                if (roleName.equalsIgnoreCase(ROLE_NAME_SM)) {
                getIntentToHomeActivity(loanType,productId,workflowId);
//                } else {
//                    appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, roleName + " can not access " + loanType);
//
//                }
            }
            else if (!TextUtils.isEmpty(loanType) && loanType.equalsIgnoreCase(LOAN_NAME_AHL)) {
//                if (roleName.equalsIgnoreCase(ROLE_NAME_SM)) {
                getIntentToHomeActivity(loanType,productId,workflowId);
//                } else {
//                    appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, roleName + " can not access " + loanType);
//
//                }
            } else if (!TextUtils.isEmpty(loanType) && loanType.equalsIgnoreCase(LOAN_NAME_INDIVIDUAL)) {
//                if (roleName.equalsIgnoreCase(ROLE_NAME_LO)) {
//                    getIntentToHomeActivity(loanType,productId);
//                } else {
//                    appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, roleName + " can not access " + loanType);
//
//                }
            } else {

//                if(!loanType.equalsIgnoreCase(LOAN_NAME_INDIVIDUAL_LOAN))

                getIntentToHomeActivity(loanType,productId,workflowId);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            INSERT_LOG("openProductScreenCallback", "Exception : " + ex.getMessage());
        }
    }

    public void getIntentToHomeActivity(String loanType,String productId,String workflowId){
        Intent intent = new Intent(ProductActivity.this, HomeActivity.class);
        intent.putExtra(PARAM_LOAN_TYPE, loanType);
        intent.putExtra(PARAM_USER_NAME, userName);
        intent.putExtra(PARAM_BRANCH_ID, branchId);
        intent.putExtra(PARAM_BRANCH_NAME, branchName);
        intent.putExtra(PARAM_BRANCH_GST_CODE, branchGSTcode);
        intent.putExtra(PARAM_USER_ID, userId);
        intent.putExtra(PARAM_PRODUCT_ID, productId);
        intent.putExtra(PARAM_WORKFLOW_ID, workflowId);
        intent.putExtra(PARAM_ROLE_NAME, roleName);
        startActivity(intent);
    }



    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
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
            if (TextUtils.isEmpty(branchGSTcode)) {
                branchGSTcode = branchId;
            }
            roleName = getIntent().getStringExtra(PARAM_ROLE_NAME);

            // TODO: checking location
            getGPSLocation();
            // TODO: Checking network strength
            getNetworkSignalStrength();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    private void getUserLoginMenuFromServer() {
        try {
            appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();
            viewModel.getUserLoginMenuFromServer(userId, "4001");
            if (viewModel.getUserLoginMenuTableLiveDataList() != null) {
                Observer observer = new Observer() {
                    @Override
                    public void onChanged(Object o) {
                        appHelper.getDialogHelper().getLoadingDialog().closeDialog();
                        ArrayList<UserLoginMenuTable> userLoginMenuTableList = (ArrayList<UserLoginMenuTable>) o;
                        viewModel.getUserLoginMenuTableLiveDataList().removeObserver(this);
                          userLoginMenuTableList=userLoginMemuList;

                        LoanTypeAdapter loanTypeAdapter = new LoanTypeAdapter(ProductActivity.this, loanTypeDtoList, userLoginMenuTableList,ProductActivity.this);
                        rvLoanType.setAdapter(loanTypeAdapter);

                        /*if (userLoginMenuTableList != null && userLoginMenuTableList.size() > 0) {
                            userLoginMemuList=userLoginMenuTableList;


                        } else {

                        }*/

                    }
                };
                viewModel.getUserLoginMenuTableLiveDataList().observe(this, observer);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            appHelper.getDialogHelper().getLoadingDialog().closeDialog();
            INSERT_LOG("getKnowledgeBankFromServer", "Exception : " + ex.getMessage());
        }
    }

}
