package com.saartak.el.activities;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.location.Location;
import android.os.Bundle;
import android.provider.Settings;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.bfil.ekyclibrary.models.AadharData;
import com.bfil.uilibrary.dialogs.ConfirmationDialog;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.precision.rdservice.CaptureResponse;
import com.precision.rdservice.DeviceInfo;
import com.saartak.el.BuildConfig;
import com.saartak.el.R;
import com.saartak.el.adapter.SmartFragmentStatePagerAdapter;
import com.saartak.el.constants.AppConstant;
import com.saartak.el.database.entity.CenterCreationTable;
import com.saartak.el.database.entity.DynamicUITable;
import com.saartak.el.database.entity.LocationTable;
import com.saartak.el.database.entity.RawDataTable;
import com.saartak.el.fragments.LOSBaseFragment;
import com.saartak.el.fragments.decisions.msme.CashFlowSummaryMSMEFragment;
import com.saartak.el.fragments.incomeassessment.msme.ProductEstimateDetailFragment;
import com.saartak.el.fragments.incomeassessment.msme.ServiceEstimateDetailFragment;
import com.saartak.el.helper.CustomViewPager;
import com.saartak.el.interfce.FragmentInterface;
import com.saartak.el.interfce.FragmentToActivityInterface;
import com.saartak.el.livedata.BoundLocationManager;
import com.saartak.el.livedata.ConnectionLiveData;
import com.saartak.el.models.ConnectionModel;
import com.saartak.el.view_models.DynamicUIViewModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

import static com.saartak.el.constants.AppConstant.*;
import static com.saartak.el.dynamicui.constants.ParametersConstant.ERROR_MESSAGE_ENABLE_GPS;
import static com.saartak.el.dynamicui.constants.ParametersConstant.RADIO_BUTTON_ITEM_BANK_SALARIED;
import static com.saartak.el.dynamicui.constants.ParametersConstant.RADIO_BUTTON_ITEM_CASH_SALARIED;
import static com.saartak.el.dynamicui.constants.ParametersConstant.RADIO_BUTTON_ITEM_SALARIED;
import static com.saartak.el.dynamicui.constants.ParametersConstant.RADIO_BUTTON_ITEM_SELF_EMPLOYED;
import static com.saartak.el.dynamicui.constants.ParametersConstant.RADIO_BUTTON_ITEM_SENP;
import static com.saartak.el.dynamicui.constants.ParametersConstant.RADIO_BUTTON_ITEM_SEP;
import static com.saartak.el.dynamicui.constants.ParametersConstant.REQUEST_CODE_LOCATION;
import static com.saartak.el.dynamicui.constants.ParametersConstant.TAG_NAME_ADD_ANOTHER_BUSINESS_PROOF_PLUS_BUTTON;
import static com.saartak.el.dynamicui.constants.ParametersConstant.TAG_NAME_ADD_BANK_DETAILS_PLUS_BUTTON;
import static com.saartak.el.dynamicui.constants.ParametersConstant.TAG_NAME_CUSTOMER_TYPE;
import static com.saartak.el.dynamicui.constants.ParametersConstant.TAG_NAME_EKYC_BUTTON;
import static com.saartak.el.dynamicui.constants.ParametersConstant.TAG_NAME_FULL_NAME;
import static com.saartak.el.dynamicui.constants.ParametersConstant.TAG_NAME_QR_READING_BUTTON;

public class BaseActivity extends LOSBaseActivity implements HasSupportFragmentInjector, FragmentToActivityInterface {

    private static final String TAG = BaseActivity.class.getCanonicalName();
    private RelativeLayout rlayout;
    private Animation animation;

    private Toolbar toolbar;
    private TabLayout tabLayout;
    public CustomViewPager viewPager;

    public HomePagerAdapter adapter;
    public Fragment mFragment;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView navigationView;
    CoordinatorLayout coordinatorLayout;
    TextView tvClientID, tvClientName,clientId;
    TextView tvAppVersion,tvCurrentDate;
    ImageView ivStaffImage;

    String fieldTag;
    TextView tvStaffId, tvStaffName, tvSOBDate;
    public CenterCreationTable CENTER_CREATION_TABLE;

    private static final String MENU_ITEM = "menu_item";
    private int menuItemId;

    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;

    @Inject
    public ViewModelProvider.Factory viewModelFactory;
    public DynamicUIViewModel viewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,
//                WindowManager.LayoutParams.FLAG_SECURE);

        setContentView(R.layout.activity_base);

//        rlayout = findViewById(R.id.rlayout);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        this.setSupportActionBar(toolbar);
//        tvClientID = (TextView) findViewById(R.id.tv_client_id_custom);
        tvCurrentDate = (TextView)findViewById(R.id.tv_currentdate);
        tvAppVersion = (TextView)findViewById(R.id.tv_app_version);
        tvClientName = (TextView) findViewById(R.id.tv_client_name_custom);
        clientId = (TextView) findViewById(R.id.clientId);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.cl_base);

        appHelper.getSharedPrefObj().edit().remove(MENU_ITEM).apply();


        /*if (getSupportActionBar() != null)
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }*/
       /*this.getSupportActionBar().setTitle("Ali Hussain");
       this.getSupportActionBar().setSubtitle(CLIENT_ID);*/

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        actionBarDrawerToggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.graryDark));
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        actionBarDrawerToggle.syncState();
        setUpDrawerContent(navigationView);
        View header = navigationView.getHeaderView(0);
        ivStaffImage = header.findViewById(R.id.iv_staff_image);

        tvStaffId = header.findViewById(R.id.tv_staff_id);
        tvStaffName = header.findViewById(R.id.tv_staff_name);
        tvSOBDate = header.findViewById(R.id.tv_sod_date);

        viewPager = (CustomViewPager) findViewById(R.id.viewpager);
        viewPager.setPagingEnabled(false);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setTabGravity(TabLayout.GRAVITY_CENTER);
        tabLayout.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        // TODO: Intent string values
        loanType = getIntent().getStringExtra(PARAM_LOAN_TYPE);
        moduleType = getIntent().getStringExtra(PARAM_MODULE_TYPE);
        userName = getIntent().getStringExtra(PARAM_USER_NAME);
        userId = getIntent().getStringExtra(PARAM_USER_ID);
        branchId = getIntent().getStringExtra(PARAM_BRANCH_ID);
        branchGSTcode = getIntent().getStringExtra(PARAM_BRANCH_GST_CODE);
        productId = getIntent().getStringExtra(PARAM_PRODUCT_ID);
        workFlowId = getIntent().getStringExtra(PARAM_WORKFLOW_ID);
        CLIENT_ID = getIntent().getStringExtra(PARAM_CLIENT_ID);
        currentStage = getIntent().getStringExtra(PARAM_CURRENT_STAGE);
        currentStageID = getIntent().getStringExtra(PARAM_CURRENT_STAGE_ID);
        AUTOFILL = getIntent().getBooleanExtra(PARAM_AUTO_FILL, false);
        if (AUTOFILL) {
            leadDetailsHashMap = (HashMap<String, Object>) getIntent().getSerializableExtra(PARAM_HASH_MAP);
        }

        String centerJsonString = getIntent().getStringExtra(PARAM_CENTER_TABLE_JSON);
        if (!TextUtils.isEmpty(centerJsonString)) {
            CENTER_CREATION_TABLE = new Gson().fromJson(centerJsonString, CenterCreationTable.class);
        }

        if(!moduleType.equalsIgnoreCase(MODULE_TYPE_LEAD)){
            clientId.setText(CLIENT_ID);
        }


//        if (!TextUtils.isEmpty(CLIENT_ID)) {
//            tvClientID.setText(CLIENT_ID);
//        }

        if (!TextUtils.isEmpty(userName)) {
           // tvClientName.setText(userName.toUpperCase());
           // tvStaffName.setText(userName.toUpperCase());
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
            tvAppVersion.setText(getResources().getString(R.string.version) + " "+ appVersion);
        }

        this.configureDagger();
        this.configureViewModel();

        // TODO: SETTING SCREEN NAME , SCREEN NO & SCREEN ICON BASED ON LOAN AND MODULE TPE
        if (!TextUtils.isEmpty(loanType)) {
             if (loanType.equalsIgnoreCase(LOAN_NAME_EL)) { // TODO: EL LOAN
                 if (!TextUtils.isEmpty(moduleType) && moduleType.equalsIgnoreCase(MODULE_TYPE_LEAD)) {
                     setScreens(LEAD_TAB_SCREEN_NAMES_AHL, LEAD_TAB_SCREEN_NUMBERS_PHL, LEAD_TAB_ICONS_AHL, PROJECT_ID_EL, PRODUCT_ID_EL, loanType, moduleType);
                 }
                 if (!TextUtils.isEmpty(moduleType) && moduleType.equalsIgnoreCase(MODULE_TYPE_LEAD)) {
                     setScreens(LEAD_TAB_SCREEN_NAMES_EL, LEAD_TAB_SCREEN_NUMBERS_PHL, LEAD_TAB_ICONS_AHL, PROJECT_ID_EL, PRODUCT_ID_EL, loanType, moduleType);
                 }
                 if (!TextUtils.isEmpty(moduleType) && moduleType.equalsIgnoreCase(MODULE_TYPE_COLLECTION)) {
                     // TODO: COLLECTION PHL
                     setScreens(COLLECTION_TAB_SCREEN_NAMES_AHL, COLLECTION_TAB_SCREEN_NUMBERS_PHL, COLLECTION_TAB_ICONS_AHL, PROJECT_ID_EL, PRODUCT_ID_EL, loanType, moduleType);
                 } else if (!TextUtils.isEmpty(moduleType) && moduleType.equalsIgnoreCase(AppConstant.MODULE_TYPE_APPLICANT)) {
                     setRawDataBusinessOrSalary();
                 } else if (!TextUtils.isEmpty(moduleType) && moduleType.contains(AppConstant.MODULE_TYPE_CO_APPLICANT)) {
                     // TODO: CO APPLICANT PHL ( contains )
                     setScreens(CO_APPLICANT_TAB_SCREEN_NAMES_EL, CO_APPLICANT_TAB_SCREEN_NUMBERS_EL, CO_APPLICANT_TAB_ICONS_NAMES_EL, PROJECT_ID_EL, PRODUCT_ID_EL, loanType, moduleType);
                 } else if (!TextUtils.isEmpty(moduleType) && moduleType.equalsIgnoreCase(AppConstant.MODULE_TYPE_CO_APPLICANT)) {
                     // TODO: CO APPLICANT PHL
                     setScreens(CO_APPLICANT_TAB_SCREEN_NAMES_EL, CO_APPLICANT_TAB_SCREEN_NUMBERS_EL, CO_APPLICANT_TAB_ICONS_NAMES_EL, PROJECT_ID_EL, PRODUCT_ID_EL, loanType, moduleType);
                 }else if (!TextUtils.isEmpty(moduleType) && moduleType.equalsIgnoreCase(MODULE_TYPE_GUARANTOR)) {
                     // TODO: CO APPLICANT PHL
                     setScreens(GUARANTOR_TAB_SCREEN_NAMES_EL, GUARANTOR_TAB_SCREEN_NUMBER_EL, GUARANTOR_TAB_ICONS_NAMES_EL, PROJECT_ID_EL, PRODUCT_ID_EL, loanType, moduleType);
                 }
                 /* else if (!TextUtils.isEmpty(moduleType) && moduleType.equalsIgnoreCase(AppConstant.MODULE_TYPE_BUSINESS)) {
                    // TODO: BUSINESS PHL
                    setScreens(BUSINESS_TAB_SCREEN_NAMES_AHL, BUSINESS_TAB_SCREEN_NUMBERS_PHL, BUSINESS_TAB_ICONS_AHL, PROJECT_ID_LOS, PRODUCT_ID_EL, loanType, moduleType);
                }  else if (!TextUtils.isEmpty(moduleType) && moduleType.equalsIgnoreCase(MODULE_TYPE_SALARY)) {
                    // TODO: SALARY PHL
                    setScreens(SALARY_TAB_SCREEN_NAMES_AHL, SALARY_TAB_SCREEN_NUMBERS_PHL, SALARY_TAB_ICONS_AHL, PROJECT_ID_LOS, PRODUCT_ID_EL, loanType, moduleType);
                } else if (!TextUtils.isEmpty(moduleType) && moduleType.equalsIgnoreCase(AppConstant.MODULE_TYPE_HOUSE_INCOME)) {
                    // TODO: HOUSE INCOME PHL
                    setScreens(HOUSE_INCOME_MODULE_SCREEN_NAMES_AHL, HOUSE_INCOME_MODULE_SCREEN_NUMBERS_PHL, HOUSE_INCOME_MODULE_ICONS_AHL, PROJECT_ID_LOS, PRODUCT_ID_EL, loanType, moduleType);
                } else if (!TextUtils.isEmpty(moduleType) && moduleType.equalsIgnoreCase(AppConstant.MODULE_TYPE_HOUSE_EXPENSE)) {
                    // TODO: HOUSE EXPENSE PHL
                    setScreens(HOUSE_EXPENSE_MODULE_SCREEN_NAMES_AHL, HOUSE_EXPENSE_MODULE_SCREEN_NUMBERS_PHL, HOUSE_EXPENSE_MODULE_ICONS_AHL, PROJECT_ID_LOS, PRODUCT_ID_EL, loanType, moduleType);
                } else if (!TextUtils.isEmpty(moduleType) && moduleType.equalsIgnoreCase(AppConstant.MODULE_TYPE_REFERENCES)) {
                    // TODO: REFERENCES MODULE PHL
                    setScreens(REFERENCES_MODULE_SCREEN_NAMES_AHL, REFERENCES_MODULE_SCREEN_NUMBERS_PHL, REFERENCES_MODULE_ICONS_AHL, PROJECT_ID_LOS, PRODUCT_ID_EL, loanType, moduleType);
                } */else if (!TextUtils.isEmpty(moduleType) && moduleType.equalsIgnoreCase(AppConstant.MODULE_TYPE_LOAN_PROPOSAL_WITH_NOMINEE)) {
                    // TODO: LOAN PROPOSAL WITH NOMINEE MODULE PHL
                    setScreens(LOAN_PROPOSAL_WITH_NOMINEE_MODULE_SCREEN_NAMES_EL, LOAN_PROPOSAL_WITH_NOMINEE_MODULE_SCREEN_NUMBERS_EL,
                            LOAN_PROPOSAL_WITH_NOMINEE_MODULE_ICONS_EL, PROJECT_ID_EL, PRODUCT_ID_EL, loanType, moduleType);
                } else if (!TextUtils.isEmpty(moduleType) && moduleType.equalsIgnoreCase(AppConstant.MODULE_TYPE_LOAN_PROPOSAL)) {
                    // TODO: LOAN PROPOSAL MODULE PHL
                    setScreens(LOAN_PROPOSAL_MODULE_SCREEN_NAMES_AHL, LOAN_PROPOSAL_MODULE_SCREEN_NUMBERS_PHL, LOAN_PROPOSAL_MODULE_ICONS_AHL, PROJECT_ID_EL, PRODUCT_ID_EL, loanType, moduleType);
                } else if (!TextUtils.isEmpty(moduleType) && moduleType.equalsIgnoreCase(AppConstant.MODULE_TYPE_DOCUMENTS)) {
                    // TODO: DOCUMENT MODULE PHL
                    setScreens(DOCUMENTS_MODULE_SCREEN_NAMES_EL, DOCUMENTS_MODULE_SCREEN_NUMBERS_EL, DOCUMENTS_MODULE_ICONS_EL, PROJECT_ID_EL, PRODUCT_ID_EL, loanType, moduleType);
                } else if (!TextUtils.isEmpty(moduleType) && moduleType.equalsIgnoreCase(AppConstant.MODULE_TYPE_LOAN_PROPOSAL)) {
                     // TODO: LOAN PROPOSAL MODULE PHL
                     setScreens(LOAN_PROPOSAL_MODULE_SCREEN_NAMES_AHL, LOAN_PROPOSAL_MODULE_SCREEN_NUMBERS_PHL, LOAN_PROPOSAL_MODULE_ICONS_AHL, PROJECT_ID_EL, PRODUCT_ID_EL, loanType, moduleType);
                 } else if (!TextUtils.isEmpty(moduleType) && moduleType.equalsIgnoreCase(AppConstant.MODULE_OTP_VERIFICATION)) {
                    // TODO: OTP VERIFICATION MODULE
                    setScreens(OTP_VERIFICATION_MODULE_SCREEN_NAMES, OTP_VERIFICATION_MODULE_SCREEN_NUMBERS, OTP_VERIFICATION_MODULE_ICONS, PROJECT_ID_EL, PRODUCT_ID_EL, loanType, moduleType);
                }else if (!TextUtils.isEmpty(moduleType) && moduleType.equalsIgnoreCase(MODULE_TYPE_GENERATE_CIBIL)) {
                    // TODO: GENERATE CIBIL MODULE
                    setScreens(GENERATE_CIBIL_MODULE_SCREEN_NAMES_EL, GENERATE_CIBIL_MODULE_SCREEN_NUMBERS_EL, GENERATE_CIBIL_MODULE_ICONS_EL, PROJECT_ID_EL, PRODUCT_ID_EL, loanType, moduleType);
                }

             }
        }

        // TODO: TO DISABLE TAB CLICKS
      /*  LinearLayout tabStrip = ((LinearLayout)tabLayout.getChildAt(0));
        for(int i = 0; i < tabStrip.getChildCount(); i++) {
            tabStrip.getChildAt(i).setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    return true;
                }
            });
        }*/

      /*  tabLayout.setTabTextColors(getResources().getColor(R.color.colorAccent),
                getResources().getColor(R.color.white));*/

        // TODO: 12-04-2019 Animation no need
  /*      animation = AnimationUtils.loadAnimation(this,R.anim.uptodowndiagonal);
        viewPager.setAnimation(animation);*/

        viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                try {
                    FragmentInterface fragmentInterface = (FragmentInterface) adapter.instantiateItem(viewPager, position);
                    Fragment fragment = (Fragment) adapter.getRegisteredFragment(viewPager.getCurrentItem());
                    if (fragment != null) {
                        mFragment = fragment;
                        fragmentInterface.fragmentBecameVisible();
                    }
//                    toolbarTitle.setText(adapter.getPageTitle(position));
                } catch (Exception e) {
                    e.printStackTrace();
                    // TODO: No need to insert log here
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        // TODO: ( No need to show )
//        Glide.with(this).load(getImage("passport_photo")).apply(RequestOptions.circleCropTransform()).into(ivStaffImage);


        checkInternetConnection();
        getGPSLocation(); // TODO: GPS LOCATION
    }

    private void setScreens(String[] tabNames, String[] screenNos, int[] tabIcon, String projectId, String moduleId, String loanType, String moduleType) {
        try {
            setupViewPager(viewPager, tabNames, screenNos, loanType, CLIENT_ID, projectId, moduleId, moduleType);
            tabLayout.setupWithViewPager(viewPager);
            setupTabIcons(tabIcon, tabNames);
            List<String> screenNosList = Arrays.asList(screenNos);
            List<String> tabNamesList = Arrays.asList(tabNames);

            getRawDataForApplicantSection(screenNosList, tabNamesList, CLIENT_ID, loanType, moduleType, navigationView, tabNames, tabIcon);
            //setupNavigationMenuItem(navigationView, tabNames, tabIcon);
        } catch (Exception ex) {
            ex.printStackTrace();
            INSERT_LOG("setScreens","Exception : "+ex.getMessage());
        }
    }

    private void checkInternetConnection() {
        try {
            ConnectionLiveData connectionLiveData = new ConnectionLiveData(getApplicationContext());
            connectionLiveData.observe(this, new Observer<ConnectionModel>() {
                @Override
                public void onChanged(@Nullable ConnectionModel connection) {
                    if (connection.isConnected()) {
                        // TODO: No need to show if internet is connected
                       /* switch (connection.getType()) {
                            case WIFI_CONNECTION:
                                Toast.makeText(BaseActivity.this, String.format("Wifi turned ON"), Toast.LENGTH_SHORT).show();
                                break;
                            case MOBILE_CONNECTION:
                                Toast.makeText(BaseActivity.this, String.format("Mobile data turned ON"), Toast.LENGTH_SHORT).show();
                                break;
                        }*/
                    } else {
                        Snackbar snackbar = Snackbar.make(coordinatorLayout, "No Internet Connection !", Snackbar.LENGTH_LONG).
                                setAction("OK", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                    }
                                });

                        // TODO: Changing action button text color
                        snackbar.setActionTextColor(Color.RED);

                        // TODO: Changing tvMobNo text color
                        View sbView = snackbar.getView();
                        sbView.setBackgroundColor(ContextCompat.getColor(BaseActivity.this, R.color.colorGreen));
                        sbView.getLayoutParams().width = ViewGroup.LayoutParams.MATCH_PARENT;
                        TextView textView = (TextView) sbView.findViewById(com.google.android.material.R.id.snackbar_text);
                        textView.setTextColor(Color.YELLOW);

                        snackbar.show();
                    }
                }
            });
        } catch (Exception ex) {
            ex.printStackTrace();
            INSERT_LOG("checkInternetConnection","Exception : "+ex.getMessage());
        }
    }


    private void setupViewPager(ViewPager viewPager, String[] tabNames, String[] screenNos, String loanType, String clientId,
                                String projectId, String moduleId, String moduleType) {
        try {
            adapter = new HomePagerAdapter(getSupportFragmentManager());
            String screenName;
            String screenNo;
            Fragment fragment = null;
            for (int i = 0; tabNames.length > i; i++) {
                screenName = tabNames[i];
                screenNo = screenNos[i];
                fragment = AppConstant.getFragment(screenName, screenNo, loanType, clientId, projectId, moduleId, moduleType, userId);
                adapter.addFrag(fragment, screenName);
            }
            viewPager.setAdapter(adapter);
        } catch (Exception e) {
            e.printStackTrace();
            INSERT_LOG("setupViewPager","Exception : "+e.getMessage());
        }
    }

    public void getRawDataForApplicantSection(List<String> screenNoList, List<String> screenNameList, String client, String loanType,
                                              String moduleType,NavigationView navigationView, String[] tabNames, int[] tabIcon) {
        ArrayList<HashMap<String, Object>> allClientHashMapList = new ArrayList<>();
        try {
            viewModel.getRawDataForSingleClient(screenNameList, client, loanType, moduleType);
            if (viewModel.getRawTableLiveData() != null) {
                Observer getRawDataForAllClientObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        List<RawDataTable> applicantRawDataList = (List<RawDataTable>) o;
                        viewModel.getRawTableLiveData().removeObserver(this);
                        if (applicantRawDataList != null && applicantRawDataList.size() > 0) {
                            RawDataTable rawDataTable_one = applicantRawDataList.get(0);
                            if(rawDataTable_one != null) {
                                HashMap<String, Object> hashMap = setKeyValueForObject(rawDataTable_one);
                                if (hashMap!=null && hashMap.containsKey(TAG_NAME_FULL_NAME)) {
                                    String fullname = hashMap.get(TAG_NAME_FULL_NAME).toString();
                                    if( ! TextUtils.isEmpty(fullname)) {
                                        tvClientName.setText(fullname);
                                    }
                                }
                            }


//                          }  for (RawDataTable rawDataTable : applicantRawDataList) {
//                                HashMap<String, Object> hashMap = setKeyValueForObject(rawDataTable);
//                                allClientHashMapList.add(hashMap);
//                            }
                            Log.d(TAG, "HashMapList ==> " + allClientHashMapList.toString());

                            List<String> completedScreens=new ArrayList<>();

                            for (RawDataTable rawDataTable : applicantRawDataList) {
                                completedScreens.add(rawDataTable.getScreen_name());
                            }

                            setupNavigationMenuItem(navigationView, tabNames, tabIcon, completedScreens);   //latest
                        } else {
                            setupNavigationMenuItem(navigationView, tabNames, tabIcon);
                        }

                    }
                };
                viewModel.getRawTableLiveData().observe(this, getRawDataForAllClientObserver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            INSERT_LOG("getRawDataForApplicantSection","Exception : "+ex.getMessage());
        }
    }

    private void setupNavigationMenuItem(NavigationView navigationView, String[] tabNames, int[] tabIcons,List<String> completedScreens) {
        try {
            Menu menu = navigationView.getMenu();
            String screenName;
            int tabIcon;
            for (int i = 0; tabNames.length > i; i++) {
                screenName = tabNames[i];
                tabIcon = tabIcons[i];

                if (completedScreens.size()>0){
                    if (completedScreens.contains(screenName)){
                        menu.add(
                                Menu.NONE, // groupId
                                i, // itemId
                                Menu.NONE, // order
                                screenName // title
                        ).setIcon(tabIcon).setActionView(R.layout.widget_custom_tick_mark);
                    }else {
                        menu.add(Menu.NONE, // groupId
                                i, // itemId
                                Menu.NONE, // order
                                screenName // title
                        ).setIcon(tabIcon);
                    }
                }else {
                    menu.add(
                            Menu.NONE, // groupId
                            i, // itemId
                            Menu.NONE, // order
                            screenName // title
                    ).setIcon(tabIcon);
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
            INSERT_LOG("setupNavigationMenuItem","Exception : "+e.getMessage());
        }
    }
    private void setupNavigationMenuItem(NavigationView navigationView, String[] tabNames, int[] tabIcons) {
        try {
            Menu menu = navigationView.getMenu();
            String screenName;
            int tabIcon;
            for (int i = 0; tabNames.length > i; i++) {
                screenName = tabNames[i];
                tabIcon = tabIcons[i];
                menu.add(
                        Menu.NONE, // groupId
                        i, // itemId
                        Menu.NONE, // order
                        screenName // title
                ).setIcon(tabIcon);

            }

        } catch (Exception e) {
            e.printStackTrace();
            INSERT_LOG("setupNavigationMenuItem","Exception : "+e.getMessage());
        }
    }

    private void setupTabIcons(int[] tabIcons, String[] tabNames) {
        String screenName;
        int tabIcon;
        try {
            for (int i = 0; tabIcons.length > i; i++) {
                screenName = tabNames[i];
                tabIcon = tabIcons[i];
                TextView tab = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
                tab.setText(screenName);
                tab.setGravity(Gravity.CENTER);
                tab.setTypeface(Typeface.DEFAULT_BOLD);
                tab.setCompoundDrawablesWithIntrinsicBounds(0, tabIcon, 0, 0);
//                tabLayout.getTabAt(i).setCustomView(tab);
                tabLayout.getTabAt(i).setText(screenName);
                tabLayout.getTabAt(i).setIcon(tabIcon);
//                tabLayout.setTabTextColors(R.color.dark_gray,R.color.white);
                tabLayout.setTabTextColors(Color.GRAY, Color.WHITE);
//                tabLayout.setTabRippleColor(ContextCompat.getColorStateList(this, R.color.white));
//                tabLayout.setSelectedTabIndicatorColor(ContextCompat.getColor(this, R.color.white));
//                tabLayout.setTabIconTint(ContextCompat.getColorStateList(this, R.color.dark_gray));
                tabLayout.setTabIconTint(ContextCompat.getColorStateList(this, R.color.white));

                tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                    @Override
                    public void onTabSelected(TabLayout.Tab tab) {
                        if (tab != null && tab.getIcon() != null) {
                            tab.getIcon().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);
                        }
                    }

                    @Override
                    public void onTabUnselected(TabLayout.Tab tab) {
                        if (tab != null && tab.getIcon() != null) {
                            tab.getIcon().setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_IN);
                        }
                    }

                    @Override
                    public void onTabReselected(TabLayout.Tab tab) {

                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
            INSERT_LOG("setupTabIcons","Exception : "+e.getMessage());
        }
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return dispatchingAndroidInjector;
    }

    private void configureDagger() {
        AndroidInjection.inject(this);
    }

    public void configureViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(DynamicUIViewModel.class);
    }

    @Override
    public void onBackPressed() {
        appHelper.getDialogHelper().getConfirmationDialog().showTwoButtons("Do you want to leave  this screen ? ", new ConfirmationDialog.ActionCallback() {
            @Override
            public void onAction() {
                finish();
            }
        });
    }

    @Override
    public void valueFromFragment(String currentScreenID, String currentScreenName, String fragmentToCall, List<DynamicUITable> dynamicUITableList) {
        if (currentScreenName.equalsIgnoreCase(SCREEN_NAME_LEAD)
                ||currentScreenName.equalsIgnoreCase(SCREEN_NAME_COLD_CALLING)
            /*|| currentScreenName.equalsIgnoreCase(SCREEN_NAME_SALES_TOOL)*/ ) {
            Intent intent = getIntent();
            intent.putExtra(PARAM_SCREEN_NO, currentScreenID);
            setResult(RESULT_OK, intent);
            finish();
        } else {
//            menuItemId = appHelper.getSharedPrefObj().getInt(MENU_ITEM, 0);
//            MenuItem menuItem = navigationView.getMenu().getItem(menuItemId);
            setMenuItemTickMark(currentScreenName, fragmentToCall, dynamicUITableList);
        }
    }

    @Override
    public void oneFragmentToAnotherFragment(String currentScreenName, String fragmentToCall,
                                             DynamicUITable dynamicUITableFromCurrentScreen, List<DynamicUITable> dynamicUITableListFromCurrentScreen) {
        try{
            if (!TextUtils.isEmpty(fragmentToCall)) {
                for (int i = 0; i < adapter.mFragmentTitleList.size(); i++) {
                    String fragmentName = adapter.mFragmentTitleList.get(i);
                    if (fragmentToCall.equalsIgnoreCase(fragmentName)) {
                        viewPager.setCurrentItem(i);
                        if(dynamicUITableFromCurrentScreen !=null) {
                            if (fragmentToCall.equalsIgnoreCase(SCREEN_NAME_PRODUCT_ESTIMATE_DETAIL_MSME)) {
                                ProductEstimateDetailFragment productEstimateDetailFragment = (ProductEstimateDetailFragment) adapter.getRegisteredFragment(i);
                                productEstimateDetailFragment.getRawDataForChildFragment(dynamicUITableFromCurrentScreen);
                            }else if ( fragmentToCall.equalsIgnoreCase(SCREEN_NAME_SERVICE_ESTIMATE_DETAIL_MSME)) {
                                ServiceEstimateDetailFragment serviceEstimateDetailFragment = (ServiceEstimateDetailFragment) adapter.getRegisteredFragment(i);
                                serviceEstimateDetailFragment.getRawDataForChildFragment(dynamicUITableFromCurrentScreen);

                            }else if ( fragmentToCall.equalsIgnoreCase(SCREEN_NAME_CASH_FLOW_SUMMARY_MSME)) {

                                CashFlowSummaryMSMEFragment cashFlowSummaryMSMEFragment = (CashFlowSummaryMSMEFragment) adapter.getRegisteredFragment(i);
                                cashFlowSummaryMSMEFragment.fragmentBecameVisible();
                            }
                        }else{
                            LOSBaseFragment losBaseFragment = (LOSBaseFragment) adapter.getRegisteredFragment(i);
                            losBaseFragment.getMetaDataWithCorrelationID(dynamicUITableListFromCurrentScreen.get(0).getCoRelationID());
                        }
                        break;
                    }
                }
            }
        }catch (Exception ex){
            ex.printStackTrace();
            INSERT_LOG("oneFragmentToAnotherFragment","Exception : "+ex.getMessage());
        }
    }

    public class HomePagerAdapter extends SmartFragmentStatePagerAdapter<LOSBaseFragment> {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        public final List<String> mFragmentTitleList = new ArrayList<>();

        HomePagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        void clearFragment() {
            mFragmentList.clear();
            mFragmentTitleList.clear();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
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

            menuItemId = menuItem.getItemId();
            appHelper.getSharedPrefObj().edit().putInt(MENU_ITEM, menuItemId).apply();
            menuItemId = appHelper.getSharedPrefObj().getInt(MENU_ITEM, 0);
            for (int i = 0; i < adapter.mFragmentTitleList.size(); i++) {
                String title = adapter.mFragmentTitleList.get(i);
                if (menuItem.getTitle().toString().equalsIgnoreCase(title)) {
                    viewPager.setCurrentItem(i);
                }
            }
            if (menuItem.isChecked())
                menuItem.setChecked(true);
            else
                menuItem.setChecked(false);
            drawerLayout.closeDrawers();
        } catch (Exception ex) {
            ex.printStackTrace();
            INSERT_LOG("selectItemDrawer","Exception : "+ex.getMessage());
        }
    }

    /*@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        outPersistentState.putInt(MENU_ITEM,menuItemId);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        this.menuItemId=savedInstanceState.getInt(MENU_ITEM);
    }*/

    private void setUpDrawerContent(NavigationView navigationView) {
        try {
//            navigationView.setBackgroundColor(getResources().getColor(R.color.dark_gray));

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

    public void setMenuItemTickMark(String currentScreenName, String fragmentToCall, List<DynamicUITable> dynamicUITableList) {
        try {
            // TODO: CHANGE MENU ITEM TEXT COLOR AND TICK MARK

            if (!TextUtils.isEmpty(fragmentToCall)) {
                for (int i = 0; i < adapter.mFragmentTitleList.size(); i++) {
                    String fragmentName = adapter.mFragmentTitleList.get(i);
                    if (fragmentToCall.equalsIgnoreCase(fragmentName)) {
                        if (dynamicUITableList != null && dynamicUITableList.size() > 0) {

                            if (dynamicUITableList.get(0).getScreenName().equalsIgnoreCase(SCREEN_NAME_APPLICANT_KYC)
                                    || dynamicUITableList.get(0).getScreenName().equalsIgnoreCase(SCREEN_NAME_CO_APPLICANT_KYC)
                            ) {
                                adapter.getRegisteredFragment(i).dynamicUI(dynamicUITableList);
//                                adapter.getRegisteredFragment(i).getTagNameList(dynamicUITableList.get(0).getScreenName(), dynamicUITableList, TAG_NAME_ADD_ANOTHER_KYC_PLUS_BUTTON);
                            }
                            else if (dynamicUITableList.get(0).getScreenName().equalsIgnoreCase(SCREEN_NAME_GUARANTOR_DETAILS)
                            ) {
                                adapter.getRegisteredFragment(i).dynamicUI(dynamicUITableList);
//                                adapter.getRegisteredFragment(i).getTagNameList(dynamicUITableList.get(0).getScreenName(), dynamicUITableList, TAG_NAME_ADD_ANOTHER_REFERENCE);
                            }
                            else if (dynamicUITableList.get(0).getScreenName().equalsIgnoreCase(SCREEN_NAME_REFERENCE_CHECK)
                            ) {
                                adapter.getRegisteredFragment(i).dynamicUI(dynamicUITableList);
//                                adapter.getRegisteredFragment(i).getTagNameList(dynamicUITableList.get(0).getScreenName(), dynamicUITableList, TAG_NAME_ADD_ANOTHER_REFERENCE);
                            } else if (dynamicUITableList.get(0).getScreenName().equalsIgnoreCase(SCREEN_NAME_REFERENCES)
                            ) {
                                adapter.getRegisteredFragment(i).dynamicUI(dynamicUITableList);
//                                adapter.getRegisteredFragment(i).getTagNameList(dynamicUITableList.get(0).getScreenName(), dynamicUITableList, TAG_NAME_FULL_NAME);
                            } else if (dynamicUITableList.get(0).getScreenName().equalsIgnoreCase(SCREEN_NAME_BUSINESS_PROOF)
                            ) {
                                adapter.getRegisteredFragment(i).getTagNameList(dynamicUITableList.get(0).getScreenName(), dynamicUITableList, TAG_NAME_ADD_ANOTHER_BUSINESS_PROOF_PLUS_BUTTON);
                            } else if (dynamicUITableList.get(0).getScreenName().equalsIgnoreCase(SCREEN_NAME_BANK_DETAILS)
                            ) {
                                adapter.getRegisteredFragment(i).getTagNameList(dynamicUITableList.get(0).getScreenName(),
                                        dynamicUITableList, TAG_NAME_ADD_BANK_DETAILS_PLUS_BUTTON);
                            } else if (dynamicUITableList.get(0).getScreenName().equalsIgnoreCase(SCREEN_NAME_CO_APPLICANT_BANK_DETAILS)
                            ) {
                                adapter.getRegisteredFragment(i).getTagNameList(dynamicUITableList.get(0).getScreenName(),
                                        dynamicUITableList, TAG_NAME_ADD_BANK_DETAILS_PLUS_BUTTON);
                            } else if (dynamicUITableList.get(0).getScreenName().equalsIgnoreCase(SCREEN_NAME_GENERAL_INCOME)
                            ) {
                                adapter.getRegisteredFragment(i).dynamicUI(dynamicUITableList);
//                                adapter.getRegisteredFragment(i).getTagNameList(dynamicUITableList.get(0).getScreenName(), dynamicUITableList, TAG_NAME_SAVE_AND_ADD_GENERAL_INCOME);
                            } else if (dynamicUITableList.get(0).getScreenName().equalsIgnoreCase(SCREEN_NAME_OTHER_INCOME_SOURCE)
                            ) {
                                adapter.getRegisteredFragment(i).dynamicUI(dynamicUITableList);
//                                adapter.getRegisteredFragment(i).getTagNameList(dynamicUITableList.get(0).getScreenName(), dynamicUITableList, TAG_NAME_SAVE_AND_ADD_INCOME_DETAIL);
                            } else {

                                adapter.getRegisteredFragment(i).dynamicUI(dynamicUITableList);

                                String submittedValues = adapter.getRegisteredFragment(i).getSubmittedValuesFromUI(dynamicUITableList);
                                // TODO: Hardcoded screen id and screen name
                                final RawDataTable rawDataTable = new RawDataTable(submittedValues, dynamicUITableList.get(0).getScreenID()
                                        , dynamicUITableList.get(0).getScreenName(), "", dynamicUITableList.get(0).getClientID(),
                                        dynamicUITableList.get(0).getLoanType(), dynamicUITableList.get(0).getUser_id(),
                                        dynamicUITableList.get(0).getModuleType(),dynamicUITableList.get(0).getCoRelationID());

                                /*adapter.getRegisteredFragment(i).saveSubmittedData(rawDataTable, dynamicUITableList, submittedValues,
                                        false, dynamicUITableList.get(dynamicUITableList.size() - 1), "", ""); // TODO: To save in local DB
*/
                                String dynamicUIRawData = new Gson().toJson(dynamicUITableList);
                                rawDataTable.setDynamic_ui_rawdata(dynamicUIRawData);
                                DynamicUITable dynamicUITable = dynamicUITableList.get(dynamicUITableList.size() - 1);
                                adapter.getRegisteredFragment(i).saveParentScreenRawData(rawDataTable, dynamicUITableList,
                                        dynamicUITable, dynamicUITable.getFieldTag()); // TODO: To save parent screen raw data
                                Log.e(TAG, "getRawDataForChildFragment: 22221");

                            }
                        }
                        MenuItem menuItem = navigationView.getMenu().getItem(i);
                        SpannableString spanString = new SpannableString(menuItem.getTitle().toString());
                        spanString.setSpan(new ForegroundColorSpan(Color.BLACK), 0, spanString.length(), 0); //fix the color to white
                        menuItem.setTitle(spanString);
                        menuItem.setActionView(R.layout.widget_custom_tick_mark);
//            menuItem.setEnabled(false); // TODO: Allow to edit

                        viewPager.setCurrentItem(i);
                    }
                }
            } else if (!TextUtils.isEmpty(currentScreenName)) {
                for (int i = 0; i < adapter.mFragmentTitleList.size(); i++) {
                    String fragmentName = adapter.mFragmentTitleList.get(i);
                    if (currentScreenName.equalsIgnoreCase(fragmentName)) {

                        MenuItem menuItem = navigationView.getMenu().getItem(i);
                        SpannableString spanString = new SpannableString(menuItem.getTitle().toString());
                        spanString.setSpan(new ForegroundColorSpan(Color.BLACK), 0, spanString.length(), 0); //fix the color to white
                        menuItem.setTitle(spanString);
                        menuItem.setActionView(R.layout.widget_custom_tick_mark);
//            menuItem.setEnabled(false); // TODO: Allow to edit
                        int positionToCall = i + 1;
                        viewPager.setCurrentItem(positionToCall);
                    }
                }
            } else {
                menuItemId = appHelper.getSharedPrefObj().getInt(MENU_ITEM, 0);
                menuItemId++;
                appHelper.getSharedPrefObj().edit().putInt(MENU_ITEM, menuItemId).apply();
                MenuItem nextMenuItem = navigationView.getMenu().getItem(menuItemId);
                if (nextMenuItem != null && nextMenuItem.isEnabled()) {
                    selectItemDrawer(nextMenuItem);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            INSERT_LOG("setMenuItemTickMark","Exception : " + ex.getMessage());
            if (ex instanceof IndexOutOfBoundsException) {
                appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.SUCCESS,
                        "Data saved successfully", new ConfirmationDialog.ActionCallback() {
                            @Override
                            public void onAction() {
                                finish();
                            }
                        });
            }
        }
    }

    @Override
    public void qrScanCallback(AadharData aadharData) {
        try {
            super.qrScanCallback(aadharData);
            final LOSBaseFragment losBaseFragment = (LOSBaseFragment) adapter.getRegisteredFragment(viewPager.getCurrentItem());
            List<DynamicUITable> dynamicUITableList = losBaseFragment.viewModel.getDynamicUITableLiveData().getValue();

            losBaseFragment.setQRData(dynamicUITableList, aadharData);
            losBaseFragment.updateDynamicUITable(dynamicUITableList, losBaseFragment.SCREEN_ID);
        }catch (Exception ex){
            ex.printStackTrace();
            INSERT_LOG("qrScanCallback","Exception : "+ex.getMessage());
        }
    }

    @Override
    public void qrScanTimeOut() {
        try {
            super.qrScanTimeOut();
            appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT,
                    "Unable to scan QR, Please enter manually", new ConfirmationDialog.ActionCallback() {
                        @Override
                        public void onAction() {
                            final LOSBaseFragment losBaseFragment = (LOSBaseFragment) adapter.getRegisteredFragment(viewPager.getCurrentItem());
                            List<DynamicUITable> dynamicUITableList = losBaseFragment.viewModel.getDynamicUITableLiveData().getValue();
                            DynamicUITable dynamicUITable = getObjectByTAG(TAG_NAME_QR_READING_BUTTON, dynamicUITableList);
                            dynamicUITable.setVisibility(false);
                            losBaseFragment.EnableOrDisableByLooping(dynamicUITableList, TAG_NAME_EKYC_BUTTON, false);
                            losBaseFragment.updateDynamicUITable(dynamicUITableList, losBaseFragment.SCREEN_ID);
                            losBaseFragment.setQRData(dynamicUITableList, null);
                        }
                    });
        }catch (Exception ex){
            ex.printStackTrace();
            INSERT_LOG("qrScanTimeOut","Exception : " + ex.getMessage());
        }
    }

    @Override
    public void biometricCallBack(DeviceInfo deviceInfo, CaptureResponse captureResponse, String aadhaar) {
        try {
            if (deviceInfo != null && captureResponse != null) {

                final LOSBaseFragment losBaseFragment = (LOSBaseFragment) adapter.getRegisteredFragment(viewPager.getCurrentItem());
                List<DynamicUITable> dynamicUITableList = losBaseFragment.viewModel.getDynamicUITableLiveData().getValue();
//                DynamicUITable dynamicUITable=losBaseFragment.getObjectByTAG(TAG_NAME_KYC_ID,dynamicUITableList);

                losBaseFragment.EKYCRequest(deviceInfo, captureResponse, aadhaar, dynamicUITableList);
            } else {
                appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ERROR, "Capture Failed");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            INSERT_LOG("biometricCallBack","Exception : "+ex.getMessage());
        }
    }

  /*  @SuppressLint("NewApi")
    public void getGPSLocation() {
        try {
            if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSIONS_REQUEST_LOCATION);
            else {
                getLocationUpdates();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            INSERT_LOG("getGPSLocation","Exception : "+ex.getMessage());
        }
    }*/

    @SuppressLint("NewApi")
    public void getGPSLocation(){

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
//                    getWeather(location.getLatitude(), location.getLongitude());
//                    getAddress(location.getLatitude(), location.getLongitude());
                        LocationTable locationTable = new LocationTable(1, String.valueOf(location.getLatitude()),
                                String.valueOf(location.getLongitude()));
                        viewModel.insertGPSLocation(locationTable);
                        Observer observer = new Observer() {
                            @Override
                            public void onChanged(@Nullable Object o) {
//                            List<DynamicUITable> list = (List<DynamicUITable>) o;
                                viewModel.getLocationTableLiveData().removeObserver(this);
                            }
//            }
                        };
                        viewModel.getLocationTableLiveData().observe(BaseActivity.this, observer);

//                    BoundLocationManager.getInstance(getApplicationContext()).removeObserver(this);
                    } else
                        Toast.makeText(BaseActivity.this, "Location is null", Toast.LENGTH_SHORT).show();
                }
            });
        }catch (Exception ex){
            ex.printStackTrace();
            INSERT_LOG("getLocationUpdates","Exception : " + ex.getMessage());
        }
    }

    private void INSERT_LOG(String methodName,String message){
        try{
            if(viewModel !=null){
                viewModel.insertLog(methodName, message, userId, "", BaseActivity.class.getCanonicalName(),
                        CLIENT_ID, loanType, moduleType);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void setRawDataBusinessOrSalary(){
        try {
            viewModel.getRawDataByClientIDAndModuleType(SCREEN_NAME_LEAD, CLIENT_ID, MODULE_TYPE_LEAD);
            if (viewModel.getRawTableLiveData() != null) {
                Observer observer = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        List<RawDataTable> leadRawData = (List<RawDataTable>) o;
                        viewModel.getRawTableLiveData().removeObserver(this);
                        for (RawDataTable rawDataTable : leadRawData) {
                            if (rawDataTable != null) {
                                HashMap<String, Object> hashMap = setKeyValueForObject(rawDataTable);
                                if (hashMap != null && hashMap.size() > 0) {
                                    if (hashMap.containsKey(TAG_NAME_CUSTOMER_TYPE)) {
                                        String customerType = hashMap.get(TAG_NAME_CUSTOMER_TYPE).toString();
                                        if (!TextUtils.isEmpty(customerType)) {
                                            if (customerType.equalsIgnoreCase(RADIO_BUTTON_ITEM_SELF_EMPLOYED)
                                                    || customerType.equalsIgnoreCase(RADIO_BUTTON_ITEM_SEP)
                                                    || customerType.equalsIgnoreCase(RADIO_BUTTON_ITEM_SENP)) {
                                                if (fieldTag != "") {
                                                    setScreens(APPLICANT_TAB_SCREEN_NAMES_BUSINESS_EL, APPLICANT_TAB_SCREEN_NUMBERS_BUSINESS_EL,
                                                            APPLICANT_TAB_ICONS_NAMES_BUSINESS_EL, PROJECT_ID_EL, PRODUCT_ID_EL, loanType, moduleType);
                                                }
                                                else{
                                                    setScreens(CO_APPLICANT_TAB_SCREEN_NUMBERS_EL, CO_APPLICANT_TAB_SCREEN_NUMBERS_BUSINESS_EL,
                                                            CO_APPLICANT_TAB_ICONS_NAMES_BUSINESS_EL, PROJECT_ID_EL, PRODUCT_ID_EL, loanType, moduleType);
                                                }
//                                               setScreens(APPLICANT_TAB_SCREEN_NAMES_BUSINESS_EL, APPLICANT_TAB_SCREEN_NUMBERS_BUSINESS_EL,
//                                                        APPLICANT_TAB_ICONS_NAMES_BUSINESS_EL, PROJECT_ID_EL, PRODUCT_ID_EL, loanType, moduleType);
                                            } else if (customerType.equalsIgnoreCase(RADIO_BUTTON_ITEM_CASH_SALARIED)
                                                    ||customerType.equalsIgnoreCase(RADIO_BUTTON_ITEM_BANK_SALARIED)
                                                    || customerType.equalsIgnoreCase(RADIO_BUTTON_ITEM_SALARIED)) {
                                                setScreens(APPLICANT_TAB_SCREEN_NAMES_EL, APPLICANT_TAB_SCREEN_NUMBERS_EL, APPLICANT_TAB_ICONS_NAMES_EL, PROJECT_ID_EL, PRODUCT_ID_EL, loanType, moduleType);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                };
                viewModel.getRawTableLiveData().
                        observe(this, observer);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            INSERT_LOG("getRawDataForApplicantSection", "Exception : " + ex.getMessage());
        }
    }
}

