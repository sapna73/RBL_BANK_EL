package com.swadhaar.los.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.text.Editable;
import android.text.Html;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.bfil.uilibrary.dialogs.ConfirmationDialog;
import com.bfil.uilibrary.widgets.customEditText.CustomEditText;
import com.bfil.uilibrary.widgets.textInputLayout.CustomTextInputLayout;
import com.google.gson.Gson;
import com.scottyab.rootbeer.RootBeer;
import com.swadhaar.los.App;
import com.swadhaar.los.R;
import com.swadhaar.los.constants.AppConstant;
import com.swadhaar.los.database.entity.LogInTable;
import com.swadhaar.los.database.entity.RoleNameTable;
import com.swadhaar.los.dynamicui.services.DynamicUIWebService;
import com.swadhaar.los.models.LoginRequestDTO;
import com.swadhaar.los.models.LoginResponseDTO;
import com.swadhaar.los.models.RoleNamesRequestDTO;
import com.swadhaar.los.view_models.DynamicUIViewModel;

import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.bfil.uilibrary.helpers.AppConstants.ALL_PERMISSION_CODE;
import static com.bfil.uilibrary.helpers.AppConstants.CAMERA_PERMISSION_CODE;
import static com.bfil.uilibrary.helpers.AppConstants.LOCATION_PERMISSION_CODE;
import static com.bfil.uilibrary.helpers.AppConstants.PERMISSION_DENIED_EXPLANATION;
import static com.bfil.uilibrary.helpers.AppConstants.PHONE_STATE_PERMISSION_CODE;
import static com.bfil.uilibrary.helpers.AppConstants.READ_PERMISSION_CODE;
import static com.bfil.uilibrary.helpers.AppConstants.WRITE_PERMISSION_CODE;
import static com.bfil.uilibrary.helpers.AppConstants.permissions;
import static com.swadhaar.los.constants.AppConstant.APP_FOLDER;
import static com.swadhaar.los.constants.AppConstant.AUTHORIZATION_TOKEN_KEY;
import static com.swadhaar.los.constants.AppConstant.DB_FOLDER;
import static com.swadhaar.los.constants.AppConstant.LOAN_NAME_MSME;
import static com.swadhaar.los.constants.AppConstant.MESSAGE_ROOTED_DEVICE_NOT_SUPPORTED;
import static com.swadhaar.los.constants.AppConstant.PARAM_BRANCH_GST_CODE;
import static com.swadhaar.los.constants.AppConstant.PARAM_BRANCH_NAME;
import static com.swadhaar.los.constants.AppConstant.PARAM_LOAN_TYPE;
import static com.swadhaar.los.constants.AppConstant.PARAM_MODULE_TYPE;
import static com.swadhaar.los.constants.AppConstant.MODULE_TYPE_LEAD;
import static com.swadhaar.los.constants.AppConstant.PARAM_BRANCH_ID;
import static com.swadhaar.los.constants.AppConstant.PARAM_EMPLOYEE_ID;
import static com.swadhaar.los.constants.AppConstant.PARAM_ROLE_NAME;
import static com.swadhaar.los.constants.AppConstant.PARAM_USER_ID;
import static com.swadhaar.los.constants.AppConstant.PARAM_USER_NAME;
import static com.swadhaar.los.constants.AppConstant.USER_API_KEY;
import static com.swadhaar.los.dynamicui.constants.ParametersConstant.ERROR_MESSAGE_LOGIN_FAILED;
import static com.swadhaar.los.dynamicui.constants.ParametersConstant.ROLE_NAME_BCM;
import static com.swadhaar.los.dynamicui.constants.ParametersConstant.ROLE_NAME_BM;
import static com.swadhaar.los.dynamicui.constants.ParametersConstant.ROLE_NAME_LO;
import static com.swadhaar.los.dynamicui.constants.ParametersConstant.ROLE_NAME_RO;
import static com.swadhaar.los.dynamicui.constants.ParametersConstant.ROLE_NAME_SM;
import static com.swadhaar.los.dynamicui.constants.ParametersConstant.SPINNER_ITEM_SELECT_ROLE;

public class LoginActivity extends LOSBaseActivity implements View.OnClickListener {

    private static final String TAG = LoginActivity.class.getCanonicalName();
    private TextView btLogin;
    private CustomTextInputLayout tilUserId, tilPassword;
    public static LogInTable loginResponseDTO;
    private TextView tvForgot,tvAppVersion;
    CustomEditText edt_login_id;
    List<RoleNameTable> roleNamesTableList;
    Spinner spinner_roleNames;
    List<String> roleNameList;
    ArrayAdapter<String> spinnerRoleNameAdapter;
    RelativeLayout rl_roleNames;
    String roleNameFromServer;
    SwitchCompat ldap;
    boolean ldapvalue=false;

    @Inject
    public ViewModelProvider.Factory viewModelFactory;
    public DynamicUIViewModel viewModel;

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
                if (charSequence.length() >= 10) {
                    userId = appHelper.getTILText(tilUserId);
                    getRoleNamesFromServer(userId);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        spinner_roleNames.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                String selectedItemText = (String) adapterView.getItemAtPosition(position);
                updateSelectedRoleNameInDB(selectedItemText);


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        try {
            PackageInfo packageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            String appVersion=packageInfo.versionName;
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

        // TODO: Check Rooted device or not
//        RootBeer rootBeer = new RootBeer(this);
//        if (rootBeer.isRooted()) {
//            //we found indication of root
//            appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT,
//                    MESSAGE_ROOTED_DEVICE_NOT_SUPPORTED, new ConfirmationDialog.ActionCallback() {
//                        @Override
//                        public void onAction() {
//                            finish();
//                        }
//                    });
//        } else {
//            //we didn't find indication of root
//            // TODO: Check App Permission
//            checkAllPermissions();
//        }
        checkAllPermissions();
    }

    private void updateSelectedRoleNameInDB(String selectedItemText) {
        if(viewModel !=null){
            viewModel.updateSelectedRoleName(selectedItemText);
        }

    }

    @Override
    public void onClick(View v) {
        if (v == btLogin) {
            validateLogin();
        }else if (v == tvForgot) {
            validateForgot();
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
//        tilUserId.getEditText().setText("SIF1004535");
//        tilUserId.getEditText().setText("SIF7001006"); // TODO: LO
//        tilUserId.getEditText().setText("SIF1000156"); // TODO:  LO for LEAD
//        tilUserId.getEditText().setText("SIF1005591"); // TODO:  Nagsen user id
//        tilUserId.getEditText().setText("SIF1004141"); // TODO:  LO -Sandy
 //       tilUserId.getEditText().setText("SIF1008172"); // TODO:  LO/SM -Ali
//        tilUserId.getEditText().setText("SIF1000059"); // TODO:  BOE - Eligibility testing
//        tilUserId.getEditText().setText("SIF1000677"); // TODO:  BOE - OD Testing
//        tilUserId.getEditText().setText("SIF1000059"); // TODO:  BOE - Eligibility testing
//        tilUserId.getEditText().setText("SIF1000386"); // TODO:  RO/BM -Ali Testing
//        tilUserId.getEditText().setText("SIF7001011"); // TODO:  RBL BANK CB -LOS UAT Testing
//        tilUserId.getEditText().setText("SIF1004028"); // TODO:  BCM
//        tilUserId.getEditText().setText("SIF1003834"); // TODO:  LO -PRODUCTION
//        tilUserId.getEditText().setText("SIF7001011"); // TODO:  LO -Ram babu Testing
//        tilUserId.getEditText().setText("SIF1009962"); // TODO:  LO - Prashant Mehta
//        tilUserId.getEditText().setText("SIF1009874"); // TODO:  LO - Srinivas Vadla
//        tilUserId.getEditText().setText("SIF1003318"); // TODO:  LO - production testing
//        tilUserId.getEditText().setText("SIF1007762"); // TODO:  User - Center Meeting testing
//        tilPassword.getEditText().setText("Pass@123");

  //     tilPassword.getEditText().setText("Pass@123");
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
                viewModel.insertLog(methodName,message,userId,"","LoginActivity"
                        ,CLIENT_ID,loanType,moduleType);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private void validateLogin() {
        try {
            userId = appHelper.getTILText(tilUserId);
            String password = appHelper.getTILText(tilPassword);
            if (!TextUtils.isEmpty(userId)) {
                if (!TextUtils.isEmpty(password)) {
                    if(appHelper.isNetworkAvailable()) {
                        // TODO: Role name validation
                        // TODO: dropdown validation
                        if (roleNameList != null && roleNameList.size() > 1) {
                            if(spinner_roleNames.getSelectedItemPosition()>0) {
                                String selectedRoleName = spinner_roleNames.getSelectedItem().toString();
                                if (selectedRoleName.equalsIgnoreCase(ROLE_NAME_LO) || selectedRoleName.equalsIgnoreCase(ROLE_NAME_RO)
                                        || selectedRoleName.equalsIgnoreCase(ROLE_NAME_SM)
                                        || selectedRoleName.equalsIgnoreCase(ROLE_NAME_BM)
                                        || selectedRoleName.equalsIgnoreCase(ROLE_NAME_BCM)) {
                                    callLoginService(userId, password);
                                } else {
                                    appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT, selectedRoleName + " not allowed to login from LOS APP");


                                }
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
            viewModel.logInService(userId,password,ldapvalue);
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

                        if (roleNamesTableList != null && roleNamesTableList.size() > 1) {
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

}
