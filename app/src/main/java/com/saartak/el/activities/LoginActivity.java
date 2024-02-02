package com.saartak.el.activities;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Debug;
import android.os.IBinder;
import android.system.Os;
import android.text.Editable;
import android.text.Html;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.bfil.uilibrary.dialogs.ConfirmationDialog;
import com.bfil.uilibrary.widgets.customEditText.CustomEditText;
import com.bfil.uilibrary.widgets.textInputLayout.CustomTextInputLayout;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.gson.Gson;
import com.saartak.el.magisk.IIsolatedService;
import com.saartak.el.magisk.IsolatedService;
import com.saartak.el.magisk.RootDetector;
import com.saartak.el.magisk.RootUtil;
import com.saartak.el.safeNet.RootShell.RootShell;
import com.saartak.el.safeNet.RootShell.execution.Command;
import com.saartak.el.safeNet.RootShell.execution.Shell;
import com.scottyab.rootbeer.RootBeer;
import com.saartak.el.App;
import com.saartak.el.R;
import com.saartak.el.database.entity.LogInTable;
import com.saartak.el.database.entity.RoleNameTable;
import com.saartak.el.models.LDAP_Login.LoginnewResponseDTO;
import com.saartak.el.models.LoginResponseDTO;
import com.saartak.el.models.RoleNamesRequestDTO;
import com.saartak.el.models.UserLoginMenu.UserLoginMenuTable;
import com.saartak.el.view_models.DynamicUIViewModel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.zip.Inflater;
import java.util.zip.InflaterOutputStream;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

import static com.bfil.uilibrary.helpers.AppConstants.ALL_PERMISSION_CODE;
import static com.bfil.uilibrary.helpers.AppConstants.PERMISSION_DENIED_EXPLANATION;
import static com.bfil.uilibrary.helpers.AppConstants.permissions;
import static com.saartak.el.constants.AppConstant.AUTHORIZATION_TOKEN_KEY;
import static com.saartak.el.constants.AppConstant.LOAN_NAME_MSME;
import static com.saartak.el.constants.AppConstant.MESSAGE_ROOTED_DEVICE_NOT_SUPPORTED;
import static com.saartak.el.constants.AppConstant.PARAM_BRANCH_GST_CODE;
import static com.saartak.el.constants.AppConstant.PARAM_BRANCH_NAME;
import static com.saartak.el.constants.AppConstant.PARAM_LOAN_TYPE;
import static com.saartak.el.constants.AppConstant.PARAM_MODULE_TYPE;
import static com.saartak.el.constants.AppConstant.MODULE_TYPE_LEAD;
import static com.saartak.el.constants.AppConstant.PARAM_BRANCH_ID;
import static com.saartak.el.constants.AppConstant.PARAM_EMPLOYEE_ID;
import static com.saartak.el.constants.AppConstant.PARAM_ROLE_NAME;
import static com.saartak.el.constants.AppConstant.PARAM_USER_ID;
import static com.saartak.el.constants.AppConstant.PARAM_USER_NAME;
import static com.saartak.el.constants.AppConstant.USER_API_KEY;
import static com.saartak.el.dynamicui.constants.ParametersConstant.ERROR_MESSAGE_LOGIN_FAILED;
import static com.saartak.el.dynamicui.constants.ParametersConstant.ROLE_NAME_BCM;
import static com.saartak.el.dynamicui.constants.ParametersConstant.ROLE_NAME_LO;
import static com.saartak.el.dynamicui.constants.ParametersConstant.ROLE_NAME_RO;
import static com.saartak.el.dynamicui.constants.ParametersConstant.ROLE_NAME_SM;
import static com.saartak.el.dynamicui.constants.ParametersConstant.SPINNER_ITEM_SELECT_ROLE;

public class LoginActivity extends LOSBaseActivity implements View.OnClickListener {

    private static final String TAG = LoginActivity.class.getCanonicalName();
    private TextView btLogin;

    private GoogleApiClient mGoogleApiClient;
    private CustomTextInputLayout tilUserId, tilPassword;
    public static LogInTable loginResponseDTO;
    private TextView tvForgot, tvAppVersion;
    CustomEditText edt_login_id;
    List<RoleNameTable> roleNamesTableList;
    Spinner spinner_roleNames;
    List<String> roleNameList;
    ArrayAdapter<String> spinnerRoleNameAdapter;
    RelativeLayout rl_roleNames;
    String roleNameFromServer;
    SwitchCompat ldap;
    boolean ldapvalue;
    ArrayList<UserLoginMenuTable>  userLoginMemuList;
    private final SecureRandom mRandom = new SecureRandom();
    String selectedItemText = "";

    @Inject
    public ViewModelProvider.Factory viewModelFactory;
    public DynamicUIViewModel viewModel;

    private IIsolatedService serviceBinder;
    private boolean bServiceBound;
    private static final String TAGM = "DetectMagisk";


    private HashMap<View, View> tabs = new HashMap<View, View>();
    private TextView scriptInput;
    private TextView scriptOutput;
    private AutoCompleteTextView pkgName;
    private Shell rootShell;

    private byte rt;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login_new);

        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        btLogin =  findViewById(R.id.btn_login);
        tilUserId = (CustomTextInputLayout) findViewById(R.id.til_userid);
        edt_login_id = (CustomEditText) findViewById(R.id.edt_login_id);
        tvForgot = (TextView) findViewById(R.id.tvForgot);
        spinner_roleNames = (Spinner)findViewById(R.id.sp_roles);
        rl_roleNames =(RelativeLayout)findViewById(R.id.rl_roleNames);
        tvForgot.setOnClickListener(this);
        tvAppVersion =  findViewById(R.id.tv_app_version);
        tilPassword = (CustomTextInputLayout) findViewById(R.id.til_pwd);
        btLogin.setOnClickListener(this);
        tilUserId.getEditText().setText("");
        // tilUserId.getEditText().setInputType(InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS); // TODO: setting all capital letters
        tilPassword.getEditText().setText("");
        tvForgot.setText(Html.fromHtml(String.format(getString(R.string.forgotpassword))));
        ldap= (SwitchCompat) findViewById(R.id.ldap);

        roleNameList = new LinkedList<String>(Arrays.asList(getResources().getStringArray(R.array.user_roles)));

        spinnerRoleNameAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, roleNameList);
        spinnerRoleNameAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        if(spinner_roleNames!=null) {
            spinner_roleNames.setAdapter(spinnerRoleNameAdapter);
        }

        ldap.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    ldapvalue=true;
                }else if(!isChecked){
                    ldapvalue=false;
                }
            }
        });

        edt_login_id.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                if (charSequence.length() >= 4) {
                    userId = appHelper.getTILText(tilUserId);
                    getRoleNamesFromServer(userId);
                    /*getUserLoginMenuFromServer();
                    getLeadDropDownProductNameServer();
                    getLeadDropDownProductTypeServer();
                    getLeadDropDownBankDetailsServer();
                    getLeadDropDownBranchNameServer();
                    getLeadDropDownSqIdAndNameServer();*/
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        spinner_roleNames.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                 selectedItemText = (String) adapterView.getItemAtPosition(position);
                updateSelectedRoleNameInDB(selectedItemText);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        try {
            PackageInfo packageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            String appVersion = packageInfo.versionName;
            if( ! TextUtils.isEmpty(appVersion)){
                tvAppVersion.setText("App Version : "+appVersion);
            }
        }catch (Exception ex){
            ex.printStackTrace();
            INSERT_LOG("packageInfo","Exception : "+ex.getMessage());
        }

        // TODO: Clearing shared preference
        appHelper.getSharedPrefObj().edit().remove(AUTHORIZATION_TOKEN_KEY).apply();
        appHelper.getSharedPrefObj().edit().remove(USER_API_KEY).apply();

        // TODO: Configure Dagger
        configureDagger();
        // TODO: Configure View Model
        configureViewModel();
        //initClient();
        // TODO: Check Rooted device or not
        RootBeer rootBeer = new RootBeer(this);
       /* if (rootBeer.isRooted()) {
            //we found indication of root
            appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT,
                    MESSAGE_ROOTED_DEVICE_NOT_SUPPORTED, new ConfirmationDialog.ActionCallback() {
                        @Override
                        public void onAction() {
                            finish();
                        }
                    });
        } else {
            //we didn't find indication of root
            // TODO: Check App Permission
            checkAllPermissions();
        }*/
        RootBeer rb = new RootBeer(getApplicationContext());
        if(!Debug.isDebuggerConnected()||!Debug.waitingForDebugger()) {
            if (RootDetector.isDeviceRootedOrNot(getApplicationContext()) || checkRooted() || isDeviceRooted() || checkRootMethod() ||rb.isRootedWithoutBusyBoxCheck()
                    || rootCheckPath()||rb.isRooted() || (rb.canLoadNativeLibrary() && rb.checkForRootNative())) {
                appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT,
                        MESSAGE_ROOTED_DEVICE_NOT_SUPPORTED, new ConfirmationDialog.ActionCallback() {
                            @Override
                            public void onAction() {
                                finish();
                            }
                        });
            }else {
                checkAllPermissions();
            }
        }
        if (RootDetector.isDeviceRootedOrNot(getApplicationContext()) || checkRooted() || isDeviceRooted() || checkRootMethod() ||rb.isRootedWithoutBusyBoxCheck()
                || rootCheckPath()||rb.isRooted() || (rb.canLoadNativeLibrary() && rb.checkForRootNative())) {
            appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT,
                    MESSAGE_ROOTED_DEVICE_NOT_SUPPORTED, new ConfirmationDialog.ActionCallback() {
                        @Override
                        public void onAction() {
                            finish();
                        }
                    });
        }

        detectMagisk();

    }

    private void detectMagisk() {

        if(bServiceBound){
            boolean bIsMagisk = false;
            try {
                Log.d(TAG, "UID:"+ Os.getuid());
                bIsMagisk = serviceBinder.isMagiskPresent();
                if(bIsMagisk) {
                    Toast.makeText(getApplicationContext(), "Magisk Found", Toast.LENGTH_LONG).show();
                    finish();
                }

                //getApplicationContext().unbindService(mIsolatedServiceConnection);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }


    private void updateSelectedRoleNameInDB(String selectedItemText) {
        if(viewModel != null){
            viewModel.updateSelectedRoleName(selectedItemText);
        }
    }

    @Override
    public void onClick(View v) {
        if (v == btLogin) {
            //validationToCheckCertificate();
            validateLogin();
        }else if (v == tvForgot) {
            validateForgot();
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        ldapvalue = false;
        ldap.setChecked(false);
        RootBeer rb = new RootBeer(getApplicationContext());
        if(!Debug.isDebuggerConnected()||!Debug.waitingForDebugger()) {
            if (RootDetector.isDeviceRootedOrNot(getApplicationContext()) || checkRooted() || isDeviceRooted() || checkRootMethod() || rootCheckPath() || rb.isRooted() || (rb.canLoadNativeLibrary() && rb.checkForRootNative())) {
                Toast.makeText(getApplicationContext(), "11111", Toast.LENGTH_SHORT).show();
                appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT,
                        MESSAGE_ROOTED_DEVICE_NOT_SUPPORTED, new ConfirmationDialog.ActionCallback() {
                            @Override
                            public void onAction() {
                                finish();
                            }
                        });
            }
        }
        if (RootDetector.isDeviceRootedOrNot(getApplicationContext()) || checkRooted() || isDeviceRooted() || checkRootMethod() || rootCheckPath() || rb.isRooted() || (rb.canLoadNativeLibrary() && rb.checkForRootNative())) {
            appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT,
                    MESSAGE_ROOTED_DEVICE_NOT_SUPPORTED, new ConfirmationDialog.ActionCallback() {
                        @Override
                        public void onAction() {
                            finish();
                        }
                    });
        }
        //tilUserId.getEditText().setText("C27063"); // TODO:  LO/SM
        //tilPassword.getEditText().setText("fgqf2730FG");
    }

    private void configureDagger(){
        AndroidInjection.inject(this);
    }
    public void configureViewModel()                                                                                                 {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(DynamicUIViewModel.class);
    }

    private void INSERT_LOG(String methodName,String message){
        try{
            if(viewModel !=null){
                viewModel.insertLog(methodName, message, userId,"", "LoginActivity",
                        CLIENT_ID, loanType, moduleType);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private void validateLogin() {
        try {
            userId = appHelper.getTILText(tilUserId);
            String password = appHelper.getTILText(tilPassword);

            SharedPreferences sharedPreferences=getSharedPreferences("shared preferences", MODE_PRIVATE);
            SharedPreferences.Editor editor=sharedPreferences.edit();
            Gson gson= new Gson();
            String json =gson.toJson(userLoginMemuList);
            editor.putString("user menu list",json);
            editor.apply();
            if (!TextUtils.isEmpty(userId)) {
                if (!TextUtils.isEmpty(password)) {
                    if(appHelper.isNetworkAvailable()) {
                        // TODO: Role name validation
                        // TODO: dropdown validation
                        if (roleNameList != null && roleNameList.size() > 1) {
                            if(spinner_roleNames.getSelectedItemPosition() > 0) {
                                String selectedRoleName = spinner_roleNames.getSelectedItem().toString();

                                byte[] byteArrayPassword = password.getBytes();
                                String  loginPassword = Base64.encodeToString(byteArrayPassword, Base64.DEFAULT);
                                callLDAPLoginService(userId,loginPassword.trim());
                                /*if (selectedRoleName.equalsIgnoreCase(ROLE_NAME_LO) || selectedRoleName.equalsIgnoreCase(ROLE_NAME_RO)
                                        || selectedRoleName.equalsIgnoreCase(ROLE_NAME_SM)
                                        || selectedRoleName.equalsIgnoreCase(ROLE_NAME_BM)
                                        || selectedRoleName.equalsIgnoreCase(ROLE_NAME_BCM)) {

                                        byte[] byteArrayPassword = password.getBytes();
                                        String  loginPassword = Base64.encodeToString(byteArrayPassword, Base64.DEFAULT);
                                        callLDAPLoginService(userId,loginPassword);
                                        //TODO This is Comitted For Disabled LOS Login
                                    *//*if(ldapvalue == true){
                                    } else if(ldapvalue == false) {
                                        callLoginService(userId, password);
                                    }*//*
                                } else {
                                    appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, selectedRoleName + " not allowed to login from LOS APP");


                                }*/
                            }else{
                                appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, "Please Select Role");

                            }
                        } else {
                            // TODO: if we get one rolename from server
                            if (roleNamesTableList != null && roleNamesTableList.size() == 1) {
                                roleNameFromServer = roleNamesTableList.get(0).getRolename();

                                if ((!TextUtils.isEmpty(roleNameFromServer)) &&
                                        (roleNameFromServer.equalsIgnoreCase(ROLE_NAME_LO) || roleNameFromServer.equalsIgnoreCase(ROLE_NAME_RO))
                                        || roleNameFromServer.equalsIgnoreCase(ROLE_NAME_SM)
                                        || roleNameFromServer.equalsIgnoreCase(ROLE_NAME_BCM)) {
                                    callLoginService(userId, password);
                                } else {
                                    appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, roleNameFromServer + " not allowed to login from LOS APP");
                                }
                            } else {
                                appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, "Invalid login ID");

                            }

                        }

                    }else{
                        appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT,"No Internet Connection");
                    }

                } else {
                    appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, "Enter Password");
                }
            } else {
                appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, "Enter User ID");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            INSERT_LOG("validateLogin","Exception case : "+ex.getMessage());
        }
    }

    private void validateForgot() {
        try {
            userId = appHelper.getTILText(tilUserId);

            if (!TextUtils.isEmpty(userId)) {
                if(appHelper.isNetworkAvailable()) {
                    Intent intentApplication = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
                    intentApplication.putExtra(PARAM_USER_ID, userId);
                    startActivity(intentApplication);
                }else{
                    appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT,"No Internet Connection");
                }
            } else {
                appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, "Enter User ID");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            INSERT_LOG("validateLogin","Exception : "+ex.getMessage());
        }
    }

    private void callLoginService(String userId, String password) {
        try{
            appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();
            viewModel.logInService(userId,password);
            if( viewModel.getLoginResponseDTOLiveData() !=null){
                Observer observer=new Observer() {
                    @Override
                    public void onChanged(Object o) {

                        appHelper.getDialogHelper().getLoadingDialog().closeDialog();

                        LoginResponseDTO loginResponseDTO = (LoginResponseDTO) o;
                        viewModel.getLoginResponseDTOLiveData().removeObserver(this);
                        if (loginResponseDTO != null && loginResponseDTO.getLogInTable() != null
                                && loginResponseDTO.getLogInTable().getErrorCode() == 0) {
                            // TODO: LOGIN SUCCESS

                            LogInTable logInTable=loginResponseDTO.getLogInTable();

                            Log.d(TAG, "Error Code ==> " + logInTable.getErrorCode());
                            Log.d(TAG, "User Name ==> " + logInTable.getUsername());
                            Log.d(TAG, "Branch ID ==> " + logInTable.getBranchID());
                            Log.d(TAG, "BC ID ==> " + logInTable.getBCID());
                            Log.d(TAG, "BranchGSTCode ==> " + logInTable.getBranchGSTCode());
                            Log.d(TAG, "Branch Name ==> " + logInTable.getBranchName());
                            Log.d(TAG, "Role Name ==> " + logInTable.getRoleName());
                            Log.d(TAG, "Api Key ==> " + logInTable.getApiKey());

                            if( ! TextUtils.isEmpty(logInTable.getApiKey())){
                                // TODO: Inserting into key store
                                appHelper.getSharedPrefObj().edit().putString(USER_API_KEY,logInTable.getApiKey()).apply();
                            }


//                           Intent intentApplication = new Intent(LoginActivity.this, LeadDetailsActivity.class);
                            Intent intentApplication = new Intent(LoginActivity.this, ProductActivity.class);
                            intentApplication.putExtra(PARAM_LOAN_TYPE, LOAN_NAME_MSME); // TODO: MSME LOAN
                            intentApplication.putExtra(PARAM_USER_NAME, logInTable.getUsername());
                            intentApplication.putExtra(PARAM_USER_ID, userId);
                            intentApplication.putExtra(PARAM_BRANCH_ID, String.valueOf(logInTable.getBCID()));
                            intentApplication.putExtra(PARAM_BRANCH_NAME, logInTable.getBranchName());
                            intentApplication.putExtra(PARAM_BRANCH_GST_CODE, logInTable.getBranchGSTCode());
                            intentApplication.putExtra(PARAM_ROLE_NAME, logInTable.getRoleName());

                            intentApplication.putExtra(PARAM_MODULE_TYPE, MODULE_TYPE_LEAD);

                            if (userId.length() > 3) {
                                EMP_LAST_5_DIGIT = userId.substring(3);
                                intentApplication.putExtra(PARAM_EMPLOYEE_ID, EMP_LAST_5_DIGIT);
                            }
                            startActivity(intentApplication);

                        } else {
                            String errorMessage = ERROR_MESSAGE_LOGIN_FAILED;
                            if (loginResponseDTO != null && loginResponseDTO.getLogInTable() != null
                                    && !TextUtils.isEmpty(loginResponseDTO.getLogInTable().getErrorMessage())) {
                                errorMessage = loginResponseDTO.getLogInTable().getErrorMessage();
                            }
                            appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT,
                                    errorMessage);
                        }

                    }
                };
                viewModel.getLoginResponseDTOLiveData().observe(this,observer);
            }
        }catch (Exception ex){
            ex.printStackTrace();
            appHelper.getDialogHelper().getLoadingDialog().closeDialog();
            INSERT_LOG("callLoginService","Exception : "+ex.getMessage());
        }
    }

    private void callLDAPLoginService(String userId, String password) {
        try{
            appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();
            String uniqueId= String.valueOf(System.currentTimeMillis());
                viewModel.logInLDAPService(userId,password,uniqueId);
            if( viewModel.getLoginNewResponseDTOLiveData() !=null){
                Observer observer=new Observer() {
                    @Override
                    public void onChanged(Object o) {
                        appHelper.getDialogHelper().getLoadingDialog().closeDialog();
                        LoginnewResponseDTO loginnewResponseDTO = (LoginnewResponseDTO) o;
                        viewModel.getLoginNewResponseDTOLiveData().removeObserver(this);

                            if(loginnewResponseDTO.getErrorCode()!=null&&loginnewResponseDTO.getErrorCode().equalsIgnoreCase("")){
                            if (loginnewResponseDTO != null&&loginnewResponseDTO.getApiResponse().getLoginResponse().getStatus().equalsIgnoreCase("1")) {
                                // TODO: LOGIN SUCCESS
                                if (loginnewResponseDTO != null && loginnewResponseDTO.getApiResponse() != null
                                        && loginnewResponseDTO.getApiResponse().getLoginResponse() != null) {

                                Log.d(TAG, "Error Code ==> " + loginnewResponseDTO.getErrorCode());
                                Log.d(TAG, "User Name ==> " + loginnewResponseDTO.getApiResponse().getLoginResponse().getUserName());
                                Log.d(TAG, "Branch ID ==> " + loginnewResponseDTO.getApiResponse().getLoginResponse().getBranchId());
                                Log.d(TAG, "BC ID ==> " + loginnewResponseDTO.getApiResponse().getLoginResponse().getBranchId());
                                Log.d(TAG, "BranchGSTCode ==> " + loginnewResponseDTO.getApiResponse().getLoginResponse().getBranchGSTCode());
                                Log.d(TAG, "Branch Name ==> " + loginnewResponseDTO.getApiResponse().getLoginResponse().getBranchName());
                                Log.d(TAG, "Api Key ==> " + loginnewResponseDTO.getApiResponse().getLoginResponse().getToken());

                                if (!TextUtils.isEmpty(loginnewResponseDTO.getApiResponse().getLoginResponse().getToken())) {
                                    // TODO: Inserting into key store
                                    appHelper.getSharedPrefObj().edit().putString(USER_API_KEY, loginnewResponseDTO.getApiResponse().getLoginResponse().getToken()).apply();
                                }


    //                           Intent intentApplication = new Intent(LoginActivity.this, LeadDetailsActivity.class);
                                Intent intentApplication = new Intent(LoginActivity.this, ProductActivity.class);
                                intentApplication.putExtra(PARAM_LOAN_TYPE, LOAN_NAME_MSME); // TODO: MSME LOAN
                                intentApplication.putExtra(PARAM_USER_NAME, loginnewResponseDTO.getApiResponse().getLoginResponse().getUserName());
                                intentApplication.putExtra(PARAM_USER_ID, userId);
                                intentApplication.putExtra(PARAM_BRANCH_ID, String.valueOf(loginnewResponseDTO.getApiResponse().getLoginResponse().getBranchId()));
                                intentApplication.putExtra(PARAM_BRANCH_NAME, loginnewResponseDTO.getApiResponse().getLoginResponse().getBranchName());
                                intentApplication.putExtra(PARAM_BRANCH_GST_CODE, loginnewResponseDTO.getApiResponse().getLoginResponse().getBranchGSTCode());
                                intentApplication.putExtra(PARAM_ROLE_NAME, selectedItemText);
                                intentApplication.putExtra(PARAM_MODULE_TYPE, MODULE_TYPE_LEAD);

                                    SharedPreferences mPrefs = getSharedPreferences("ROLENAMEVALUE", 0);
                                    SharedPreferences.Editor editor = mPrefs.edit();
                                    editor.putString("rolename", selectedItemText);
                                    editor.commit();

                                if (userId.length() > 3) {
                                    EMP_LAST_5_DIGIT = userId.substring(3);
                                    intentApplication.putExtra(PARAM_EMPLOYEE_ID, EMP_LAST_5_DIGIT);
                                }
                                startActivity(intentApplication);
                            }

                            } else {
                                String errorMessage = ERROR_MESSAGE_LOGIN_FAILED;
                                if (loginnewResponseDTO != null &&loginnewResponseDTO.getApiResponse().getLoginResponse().getStatus() != null
                                        && !TextUtils.isEmpty(loginnewResponseDTO.getApiResponse().getLoginResponse().getMessage())) {
                                    errorMessage = loginnewResponseDTO.getApiResponse().getLoginResponse().getMessage();
                                }
                                appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT,
                                        errorMessage);
                            }
                            }else {
                                String errorMessage = ERROR_MESSAGE_LOGIN_FAILED;
                                appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT,
                                        errorMessage);
                            }

                    }
                };
                viewModel.getLoginNewResponseDTOLiveData().observe(this,observer);
            }
        }catch (Exception ex){
            ex.printStackTrace();
            appHelper.getDialogHelper().getLoadingDialog().closeDialog();
            INSERT_LOG("callLoginService","Exception : "+ex.getMessage());
        }
    }

    private void getRoleNamesFromServer(String userID) {
        try{
            appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();

            RoleNamesRequestDTO roleNamesRequestDTO=new RoleNamesRequestDTO();
            roleNamesRequestDTO.setUserID(userID);
            viewModel.getRoleNamesServiceCall(roleNamesRequestDTO, userID);

            if (viewModel.getRoleNamesLiveData() != null) {
                Observer getRoleNameDataObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        roleNamesTableList = (List<RoleNameTable>) o;
                        viewModel.getRoleNamesLiveData().removeObserver(this);

                        appHelper.getDialogHelper().getLoadingDialog().closeDialog();
                        roleNameList.clear();

                        if (roleNamesTableList != null && roleNamesTableList.size() >=1) {
                            rl_roleNames.setVisibility(View.VISIBLE);
                            roleNameList.add(SPINNER_ITEM_SELECT_ROLE);
                            for (RoleNameTable roleNameTable : roleNamesTableList) {
                                if (roleNameTable != null) {
                                    String roleName = roleNameTable.getRolename();
                                    if (!TextUtils.isEmpty(roleName))
                                        roleNameList.add(roleName);
                                }
                            }
                            spinner_roleNames.setSelection(0);
                        }else{
                            if(roleNamesTableList!=null && roleNamesTableList.size()==1){
                                updateSelectedRoleNameInDB(roleNamesTableList.get(0).getRolename());
                            }
                            rl_roleNames.setVisibility(View.GONE);
                        }


                    }
                };
                viewModel.getRoleNamesLiveData().observe(this, getRoleNameDataObserver);
            }
        }catch (Exception ex){
            ex.printStackTrace();
            appHelper.getDialogHelper().getLoadingDialog().closeDialog();

            INSERT_LOG("getRoleNamesServiceCall","Exception : "+ex.getMessage());

        }
    }



    private boolean checkAllPermissions(){
        try{
            // TODO: Check which permissions are granted
            List<String> listPermissionsNeeded=new ArrayList<>();

            for( String permission : permissions){
                if(ContextCompat.checkSelfPermission(this,permission) != PackageManager.PERMISSION_GRANTED){
                    listPermissionsNeeded.add(permission);
                }
            }

            // TODO: Ask for non granted permissions
            if( ! listPermissionsNeeded.isEmpty()){
                ActivityCompat.requestPermissions(this,listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]),
                        ALL_PERMISSION_CODE);
            }

        }catch (Exception ex){
            ex.printStackTrace();
            INSERT_LOG("checkAllPermissions","Exception : "+ex.getMessage());
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
            if(requestCode==ALL_PERMISSION_CODE){
                HashMap<String,Integer> permissionResults=new HashMap<>();
                int deniedCount=0;

                for( int i=0;i<grantResults.length;i++){
                    // TODO: Add only permissions which are denied
                    if(grantResults[i] == PackageManager.PERMISSION_DENIED){
                        permissionResults.put(permissions[i],grantResults[i]);
                        deniedCount++;
                    }
                }

                // TODO: Check if all permissions are granted
                if(deniedCount==0){
                    // TODO: All permissions granted , proceed with application

                    try {
                        Toast.makeText(LoginActivity.this, "Permissions granted",
                                Toast.LENGTH_SHORT).show();

                        // TODO: CREATING APP DB
                        App.createDBPath();

                    } catch (Exception e) {
                        e.printStackTrace();
                        INSERT_LOG("onRequestPermissionsResult","Exception : "+e.getMessage());
                    }

                }else{
                    // TODO: At least one permission or all permissions are denied
                    for(Map.Entry<String,Integer> entry : permissionResults.entrySet()){
                        String permissionName=entry.getKey();
                        int permissonResult=entry.getValue();

                        // TODO: Permission is denied ( this is the first time , when never ask again is not checked )
                        // TODO: so ask again and explain the usage of permission
                        // TODO: shouldShowRequestPermissionRationale will return true
                        if(ActivityCompat.shouldShowRequestPermissionRationale(this,permissionName)){
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
                        else{
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
            INSERT_LOG("onRequestPermissionsResult","Exception : "+e.getMessage());
        }
    }


    public void tabClicked(View v) {
        for (Map.Entry<View, View> tab : tabs.entrySet()) {
            View value = tab.getValue(),
                    key = tab.getKey();
            if (key == v) {
                value.setVisibility(View.VISIBLE);
                key.setBackground(getResources().getDrawable(R.drawable.bordered_view));
            } else {
                value.setVisibility(View.GONE);
                key.setBackground(null);
            }
        }
    }

    public void launchClicked(View v) {
        try {
            scriptOutput.setText(null);
            if (rootShell == null || !rootShell.isShellOpen())
                rootShell = RootShell.getShell(true);
            String dataFolder = "/data/data/" + getPackageName();
            writeToFile(dataFolder + "/script.js", scriptInput.getText().toString());
            showToast("Injecting to the target application...");
            rootShell.runRootCommand(new Command(0, dataFolder + "/frida64 -s " + dataFolder +
                    "/script.js -f " + pkgName.getText()) {
                @Override
                public void commandOutput(int id, String line) {
                    scriptOutput.append(line + "\n");
                    showToast(line);
                }
            });

        } catch (Exception e) {
            showToast(e.getMessage());
        }
    }

    public  void ShowDialog(String title, String msg, final boolean exit) {
        new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(msg)
                .setCancelable(false)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (exit)
                            finishAffinity();
                    }
                })
                .show();
    }

    public void showToast(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
    }

    public ArrayAdapter<String> getInstalledAppNames() {
        Context context = getApplicationContext();
        PackageManager packageManager = context.getPackageManager();
        List<ApplicationInfo> applicationInfos = packageManager.getInstalledApplications(PackageManager.GET_META_DATA);
        List<String> appNames = new ArrayList<>();
        for (ApplicationInfo applicationInfo : applicationInfos) {
            if ((applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0) {
                appNames.add(applicationInfo.packageName);
            }
        }
        return new ArrayAdapter<>(
                context,
                R.layout.item,
                appNames
        );
    }

    public void writeToFile(String path, String text) throws IOException {
        File file = new File(path);
        FileWriter writer = new FileWriter(file);
        writer.write(text);
        writer.close();
    }


    public void extractAsset(String assetName, String outputFilePath) throws IOException {
        InputStream inputStream = null;
        FileOutputStream fileOutputStream = null;
        InflaterOutputStream infOutput = null;
        try {
            Inflater inflater = new Inflater(true);
            inputStream = getAssets().open(assetName);
            fileOutputStream = new FileOutputStream(outputFilePath);
            infOutput = new InflaterOutputStream(fileOutputStream, inflater);
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) > 0) {
                infOutput.write(buffer, 0, bytesRead);
            }
        } catch (IOException e) {
            showToast("Error extracting asset: " + e.getMessage());
        } finally {
            if (fileOutputStream != null)
                fileOutputStream.close();
            if (inputStream != null)
                inputStream.close();
            if (infOutput != null) {
                infOutput.close();
            }
        }
    }

    private boolean isDeviceRooted() {

        try {
            File superuserApk = new File("/system/app/Superuser.apk");
            File KingoUserAPK = new File("/system/app/KingoUser.apk");
            File SuperSuAPK = new File("/system/app/SuperSu.apk");
            File suBinary = new File("/system/bin/su");
            File busybox = new File("/system/bin/busybox");
            File supersu = new File("/system/bin/supersu");
            File magisk = new File("/system/bin/magisk");
            return superuserApk.exists() ||KingoUserAPK.exists() ||SuperSuAPK.exists() ||busybox.exists() ||
                    supersu.exists() ||magisk.exists() || suBinary.exists();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private boolean rootCheckPath() {
        String[] paths = {
                "/system/app/Superuser.apk",
                "/system/app/KingoUser.apk",
                "/system/app/SuperSu.apk",

                "/sbin/su",
                "/system/bin/su",
                "/data/local/bin/su",
                "/system/xbin/su",
                "/data/local/xbin/su",
                "/system/sd/xbin/su",
                "/system/bin/failsafe/su",
                "/data/local/su",

                "/sbin/busybox",
                "/system/bin/busybox",
                "/data/local/bin/busybox",
                "/system/xbin/busybox",
                "/data/local/xbin/busybox",
                "/system/sd/xbin/busybox",
                "/system/bin/failsafe/busybox",
                "/data/local/busybox",

                "/sbin/magisk",
                "/system/bin/magisk",
                "/data/local/bin/magisk",
                "/system/xbin/magisk",
                "/data/local/xbin/magisk",
                "/system/sd/xbin/magisk",
                "/system/bin/failsafe/magisk",
                "/data/local/magisk" ,

                "/sbin/supersu",
                "/system/bin/supersu",
                "/data/local/bin/supersu",
                "/system/xbin/supersu",
                "/data/local/xbin/supersu",
                "/system/sd/xbin/supersu",
                "/system/bin/failsafe/supersu",
                "/data/local/supersu"
        };

        for (String path : paths) {
            if (new File(path).exists()) {
                return true;
            }
        }
        return false;
    }

    public boolean checkRooted() {
        if(RootUtil.checkRootMethod3()|| RootUtil.checkRootMethod1()|| RootUtil.checkRootMethod2())
            return true;
        return false;
    }


    private ServiceConnection mIsolatedServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            serviceBinder = IIsolatedService.Stub.asInterface(iBinder);
            bServiceBound = true;
            Log.d(TAGM, "Service bound");
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            bServiceBound = false;
            Log.d(TAGM, "Service Unbound");
        }
    };

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(getApplicationContext(), IsolatedService.class);
        /*Binding to an isolated service */
        getApplicationContext().bindService(intent, mIsolatedServiceConnection, BIND_AUTO_CREATE);

    }

    public static boolean isAPKRooted() {

        // get from build info
        String buildTags = android.os.Build.TAGS;
        if (buildTags != null && buildTags.contains("test-keys")) {
            return true;
        }

        // check if /system/app/Superuser.apk is present
        try {
            File file = new File("/system/app/Superuser.apk");
            if (file.exists()) {
                return true;
            }
        } catch (Exception e1) {
            // ignore
        }

        // try executing commands
        return canExecuteCommand("/system/xbin/which su") || canExecuteCommand("/system/bin/which su") || canExecuteCommand("which su");
    }

    // executes a command on the system
    private static boolean canExecuteCommand(String command) {
        boolean executedSuccesfully;
        try {
            Runtime.getRuntime().exec(command);
            executedSuccesfully = true;
        } catch (Exception e) {
            executedSuccesfully = false;
        }

        return executedSuccesfully;
    }
}

