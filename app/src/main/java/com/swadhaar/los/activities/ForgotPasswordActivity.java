package com.swadhaar.los.activities;

import android.Manifest;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.bfil.uilibrary.dialogs.ConfirmationDialog;
import com.swadhaar.los.App;
import com.bfil.uilibrary.widgets.textInputLayout.CustomTextInputLayout;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.credentials.Credential;
import com.google.android.gms.auth.api.credentials.HintRequest;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.swadhaar.los.R;
import com.swadhaar.los.models.BearerTokenRequestDTO;
import com.swadhaar.los.models.BearerTokenResponseDTO;
import com.swadhaar.los.models.ResetPasswordRequestDTO;
import com.swadhaar.los.models.ResetPasswordResponseDTO;
import com.swadhaar.los.view_models.DynamicUIViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

import static com.swadhaar.los.constants.AppConstant.APP_ENVIRONMENT;
import static com.swadhaar.los.constants.AppConstant.AUTHORIZATION_TOKEN_KEY;
import static com.swadhaar.los.constants.AppConstant.DATE_FORMAT_DDMMYYYYSMS;
import static com.swadhaar.los.constants.AppConstant.DATE_FORMAT_YYYY_MM_DD_T_HH_MM_SS;
import static com.swadhaar.los.constants.AppConstant.LOAN_NAME_MSME;
import static com.swadhaar.los.constants.AppConstant.MODULE_TYPE_HOUSE_EXPENSE;
import static com.swadhaar.los.constants.AppConstant.MODULE_TYPE_HOUSE_INCOME;
import static com.swadhaar.los.constants.AppConstant.MODULE_TYPE_MANUFACTURE_BUSINESS_INCOME;
import static com.swadhaar.los.constants.AppConstant.PARAM_BRANCH_GST_CODE;
import static com.swadhaar.los.constants.AppConstant.PARAM_BRANCH_ID;
import static com.swadhaar.los.constants.AppConstant.PARAM_CLIENT_ID;
import static com.swadhaar.los.constants.AppConstant.PARAM_LOAN_TYPE;
import static com.swadhaar.los.constants.AppConstant.PARAM_MODULE_TYPE;
import static com.swadhaar.los.constants.AppConstant.PARAM_OLD_PASSWORD;
import static com.swadhaar.los.constants.AppConstant.PARAM_USER_ID;
import static com.swadhaar.los.constants.AppConstant.PARAM_USER_NAME;
import static com.swadhaar.los.constants.AppConstant.PROJECT_NAME_LOS;
import static com.swadhaar.los.dynamicui.constants.ParametersConstant.CHOOSE_YOUR_MOBILE_NUMBER;
import static com.swadhaar.los.dynamicui.constants.ParametersConstant.ERROR_MESSAGE_PASSWORD_RESET_SUCCESSFULLY;
import static com.swadhaar.los.dynamicui.constants.ParametersConstant.ERROR_MESSAGE_UNABLE_TO_GET_TOKEN;
import static com.swadhaar.los.dynamicui.constants.ParametersConstant.ERROR_MESSAGE_UNABLE_TO_RESET_PASSWORD;
import static com.swadhaar.los.dynamicui.constants.ParametersConstant.SERVICE_TYPE_IDENTITY_SERVICE;
import static com.swadhaar.los.dynamicui.constants.ParametersConstant.SERVICE_TYPE_RESET_PASSWORD;

public class ForgotPasswordActivity extends LOSBaseActivity implements HasSupportFragmentInjector , GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener{

    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;

    @Inject
    public ViewModelProvider.Factory viewModelFactory;
    public DynamicUIViewModel viewModel;

    private GoogleApiClient mCredentialsApiClient;
    private static final int RC_HINT = 1000;
    private static final int PERMISSION_REQUEST_CODE = 1;
    String wantPermission = Manifest.permission.READ_PHONE_STATE;
    ArrayList<String> numbersList = new ArrayList<String>();
    private CustomTextInputLayout til_mobile_number, til_staff_id, til_imei;
    private TextView tv_submit;
    private Toolbar toolbar;


    private static final String TAG = ForgotPasswordActivity.class.getCanonicalName();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_forgot_password);
        userId = getIntent().getStringExtra(PARAM_USER_ID);

        toolbar = (androidx.appcompat.widget.Toolbar)findViewById(R.id.toolbar_forgot_password);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // back button pressed
                finish();
            }
        });
//        et_phone = findViewById(R.id.phoneNumber);
        til_mobile_number = (CustomTextInputLayout) findViewById(R.id.til_mobile_number);
        til_staff_id = (CustomTextInputLayout) findViewById(R.id.til_staff_id);
        til_imei = (CustomTextInputLayout) findViewById(R.id.til_imei);
        tv_submit =  findViewById(R.id.tv_submit);

        if(!TextUtils.isEmpty(userId))
        {
            til_staff_id.getEditText().setText(userId);
            til_staff_id.setEnabled(false);
        }
        String imei=appHelper.getIMEI();
        if( ! TextUtils.isEmpty(imei))
        {
            til_imei.getEditText().setText(imei);
            til_imei.setEnabled(false);
        }

        configureDagger();
        configureViewModel();

        tv_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( ! TextUtils.isEmpty(til_mobile_number.getEditText().getText().toString())){
                    if( ! TextUtils.isEmpty(til_staff_id.getEditText().getText().toString())){
                        if( ! TextUtils.isEmpty(til_imei.getEditText().getText().toString())){
                            resetPasswordServiceCall(til_mobile_number.getEditText().getText().toString(),
                                    til_staff_id.getEditText().getText().toString(),
                                    til_imei.getEditText().getText().toString());
                        }else{
                            appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT,
                                    "Unable To Get IMEI");
                        }
                    }else{
                        appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT,
                                "Enter Staff ID");
                    }
                }else{
                    appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT,
                            "Enter Mobile Number");
                }
            }
        });
    }

    private void configureDagger(){
        AndroidInjection.inject(this);
    }
    public void configureViewModel() {
        try {
            viewModel = ViewModelProviders.of(this, viewModelFactory).get(DynamicUIViewModel.class);
            // TODO: initialize google api client
            mCredentialsApiClient = new GoogleApiClient.Builder(ForgotPasswordActivity.this)
                    .addConnectionCallbacks(ForgotPasswordActivity.this)
                    .enableAutoManage(ForgotPasswordActivity.this, ForgotPasswordActivity.this)
                    .addApi(Auth.CREDENTIALS_API)
                    .build();
            requestPhoneNumber();
        }catch (Exception ex){
            ex.printStackTrace();
            INSERT_LOG("configureViewModel","Exception : "+ex.getMessage());
        }
    }
    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return dispatchingAndroidInjector;
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void getMobileList(){
        try {
            if (numbersList != null && numbersList.size() > 0) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                ViewGroup viewGroup = findViewById(android.R.id.content);
                View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_moblist_view, viewGroup, false);
                builder.setView(dialogView);
                final AlertDialog alertDialog = builder.create();
                alertDialog.setCanceledOnTouchOutside(false);

                final TextView tv_title = dialogView.findViewById(R.id.tv_title);
                final TextView tv_mob1 = dialogView.findViewById(R.id.tv_mob1);
                final TextView tv_mob2 = dialogView.findViewById(R.id.tv_mob2);

                tv_title.setText(CHOOSE_YOUR_MOBILE_NUMBER);

                if (numbersList != null && numbersList.size() > 0) {
                    tv_mob1.setText(numbersList.get(0));

                    if (numbersList.size() > 1) {
                        tv_mob2.setVisibility(View.VISIBLE);
                        tv_mob2.setText(numbersList.get(1));
                    } else {
                        tv_mob2.setVisibility(View.GONE);
                    }
                }
                tv_mob1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        til_mobile_number.getEditText().setText(tv_mob1.getText().toString());
                        til_mobile_number.setEnabled(false);
                        alertDialog.dismiss();
                    }
                });

                tv_mob2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        til_mobile_number.getEditText().setText(tv_mob2.getText().toString());
                        til_mobile_number.setEnabled(false);
                        alertDialog.dismiss();
                    }
                });
                alertDialog.show();
            }
        }catch (Exception ex){
            ex.printStackTrace();
            INSERT_LOG("getMobileList","Exception : "+ex.getMessage());
        }
    }

    public void requestPhoneNumber() {
        try {
            if (mCredentialsApiClient != null) {
                HintRequest hintRequest = new HintRequest.Builder()
                        .setPhoneNumberIdentifierSupported(true)
                        .build();

                PendingIntent intent = Auth.CredentialsApi.getHintPickerIntent(mCredentialsApiClient, hintRequest);
                try {
                    startIntentSenderForResult(intent.getIntentSender(), RC_HINT, null, 0, 0, 0);
                } catch (IntentSender.SendIntentException e) {
                    Log.e(TAG, "Could not start hint picker Intent", e);
                    INSERT_LOG("requestPhoneNumber","Exception : "+e.getMessage());
                }
            }
        }catch (Exception ex){
            ex.printStackTrace();
            INSERT_LOG("requestPhoneNumber","Exception : "+ex.getMessage());
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        try {
            super.onActivityResult(requestCode, resultCode, data);
            if (requestCode == RC_HINT) {
                if (resultCode == RESULT_OK) {
                    Credential cred = data.getParcelableExtra(Credential.EXTRA_KEY);

                    if (cred != null) {
                        String mobileNo = cred.getId();
                        if (!TextUtils.isEmpty(mobileNo) && mobileNo.startsWith("+")) {
                            mobileNo = mobileNo.substring(3);
                            til_mobile_number.getEditText().setText(mobileNo);
                            til_mobile_number.setEnabled(false);
                        } else if (!TextUtils.isEmpty(mobileNo) && mobileNo.length() == 12) {
                            mobileNo = mobileNo.substring(2);
                            til_mobile_number.getEditText().setText(mobileNo);
                            til_mobile_number.setEnabled(false);
                        } else {
                            til_mobile_number.getEditText().setText(mobileNo);
                            til_mobile_number.setEnabled(false);
                        }
                    }
                } else if (resultCode == 1001) {
                    til_mobile_number.getEditText().setText("");
                    til_mobile_number.setEnabled(true);

                } else if (resultCode == 1002) {
                    numbersList = new ArrayList<>();
                    numbersList = getPhoneFromTelephone();
                    if (numbersList.size() > 0) {
                        getMobileList();
                    }
                }
            }
        }catch (Exception ex){
            ex.printStackTrace();
            INSERT_LOG("onActivityResult","Exception : "+ex.getMessage());
        }
    }

    private void resetPasswordServiceCall(String mobileNumber,String staffId,String IMEI) {
        try{
            appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();

            ResetPasswordRequestDTO resetPasswordRequestDTO=new ResetPasswordRequestDTO();
            ResetPasswordRequestDTO.RequestStringClass requestStringClass=new ResetPasswordRequestDTO.RequestStringClass();
            ResetPasswordRequestDTO.ResetPasswordRequestClass resetPasswordRequestClass=new ResetPasswordRequestDTO.ResetPasswordRequestClass();

            resetPasswordRequestClass.setMobileNumber(mobileNumber);
            resetPasswordRequestClass.setUserId(staffId);
            resetPasswordRequestClass.setIMEINumber(IMEI);

            requestStringClass.setResetPasswordRequest(resetPasswordRequestClass);

            String timeStamp=appHelper.getCurrentDateTime(DATE_FORMAT_DDMMYYYYSMS);
            resetPasswordRequestDTO.setUniqueId(timeStamp);
            resetPasswordRequestDTO.setCreatedDate(appHelper.getCurrentDateTime(DATE_FORMAT_YYYY_MM_DD_T_HH_MM_SS));
            resetPasswordRequestDTO.setCreatedBy(userId);
            resetPasswordRequestDTO.setServiceType(SERVICE_TYPE_RESET_PASSWORD);
            resetPasswordRequestDTO.setRequestString(requestStringClass);

            viewModel.resetPasswordServiceCall(resetPasswordRequestDTO);

            if( viewModel.getResetPasswordResponseDTOLiveData() !=null){
                Observer observer=new Observer() {
                    @Override
                    public void onChanged(Object o) {

                        appHelper.getDialogHelper().getLoadingDialog().closeDialog();

                        ResetPasswordResponseDTO resetPasswordResponseDTO = (ResetPasswordResponseDTO) o;

                        viewModel.getResetPasswordResponseDTOLiveData().removeObserver(this);

                        if (resetPasswordResponseDTO != null && resetPasswordResponseDTO.getApiResponse() != null
                                && resetPasswordResponseDTO.getApiResponse().getResetPasswordResponse() != null
                                && resetPasswordResponseDTO.getApiResponse().getResetPasswordResponse().getStatus().equalsIgnoreCase("1")
                                &&  ! TextUtils.isEmpty(resetPasswordResponseDTO.getApiResponse().getResetPasswordResponse().getPassword())) {

                            appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT,
                                    ERROR_MESSAGE_PASSWORD_RESET_SUCCESSFULLY, new ConfirmationDialog.ActionCallback() {
                                        @Override
                                        public void onAction() {
                                            // TODO: CALL CHANGE PASSWORD ACTIVITY
                                            Intent intent = new Intent(ForgotPasswordActivity.this, ChangePasswordActivity.class);
                                            intent.putExtra(PARAM_USER_ID, userId);
                                            intent.putExtra(PARAM_OLD_PASSWORD, resetPasswordResponseDTO.getApiResponse().getResetPasswordResponse().getPassword());
                                            startActivity(intent);

                                            finish();
                                        }
                                    });
                        } else {
                            String errorMessage = ERROR_MESSAGE_UNABLE_TO_RESET_PASSWORD;
                            if (resetPasswordResponseDTO != null
                                    && !TextUtils.isEmpty(resetPasswordResponseDTO.getErrorMessage())) {
                                errorMessage = resetPasswordResponseDTO.getErrorMessage();
                            }
                            if (resetPasswordResponseDTO != null && resetPasswordResponseDTO.getApiResponse() != null
                                    && !TextUtils.isEmpty(resetPasswordResponseDTO.getApiResponse().getResetPasswordResponse().getMessage())) {
                                errorMessage = resetPasswordResponseDTO.getApiResponse().getResetPasswordResponse().getMessage();
                            }
                            appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT,
                                    errorMessage, new ConfirmationDialog.ActionCallback() {
                                        @Override
                                        public void onAction() {

                                        }
                                    });
                        }
                    }
                };
                viewModel.getResetPasswordResponseDTOLiveData().observe(this,observer);
            }
        }catch (Exception ex){
            ex.printStackTrace();
            appHelper.getDialogHelper().getLoadingDialog().closeDialog();

            INSERT_LOG("resetPasswordServiceCall","Exception : "+ex.getMessage());

        }
    }

    private void INSERT_LOG(String methodName,String message){
        try{
            if(viewModel !=null){
                viewModel.insertLog(methodName,message,userId,"","ForgotPasswordActivity"
                        ,CLIENT_ID,loanType,moduleType);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        try {
            switch (requestCode) {
                case PERMISSION_REQUEST_CODE:
                    if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        // TODO: initialize google api client
                        mCredentialsApiClient = new GoogleApiClient.Builder(ForgotPasswordActivity.this)
                                .addConnectionCallbacks(ForgotPasswordActivity.this)
                                .enableAutoManage(ForgotPasswordActivity.this, ForgotPasswordActivity.this)
                                .addApi(Auth.CREDENTIALS_API)
                                .build();
                        requestPhoneNumber();
                    } else {
                        Toast.makeText(this, "Permission Denied. We can't get phone number.", Toast.LENGTH_LONG).show();
                        if (!checkPermission(wantPermission)) {
                            requestPermission(wantPermission);
                        }
                    }
                    break;
            }
        }catch (Exception ex){
            ex.printStackTrace();
            INSERT_LOG("onRequestPermissionsResult","Exception : "+ex.getMessage());
        }
    }

    private boolean checkPermission(String permission) {
        if (Build.VERSION.SDK_INT >= 23) {
            int result = ContextCompat.checkSelfPermission(this, permission);
            if (result == PackageManager.PERMISSION_GRANTED) {
                return true;
            } else {
                return false;
            }
        } else {
            return true;
        }
    }

    private ArrayList<String> getPhoneFromTelephone() {
        ArrayList<String> numbersList = new ArrayList<String>();
        try {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (checkSelfPermission(Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    Activity#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for Activity#requestPermissions for more details.
                    // return "";
                }
                List<SubscriptionInfo> subscription = SubscriptionManager.from(getApplicationContext()).getActiveSubscriptionInfoList();
                for (int i = 0; i < subscription.size(); i++) {
                    SubscriptionInfo info = subscription.get(i);
                    Log.d(TAG, "number " + info.getNumber());
                    Log.d(TAG, "network name : " + info.getCarrierName());
                    Log.d(TAG, "country iso " + info.getCountryIso());

                    if (info != null && !TextUtils.isEmpty(info.getNumber())) {
                        String mobileNo = info.getNumber();

                        if (!TextUtils.isEmpty(mobileNo) && mobileNo.startsWith("+")) {
                            mobileNo = mobileNo.substring(3);
                            numbersList.add(mobileNo);
                        } else if (!TextUtils.isEmpty(mobileNo) && mobileNo.length() == 12) {
                            mobileNo = mobileNo.substring(2);
                            numbersList.add(mobileNo);
                        } else {
                            numbersList.add(mobileNo);
                        }
//                    numbersList.add(info.getNumber());
                    }
                }
            } else {
                TelephonyManager phoneMgr = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
                if (ActivityCompat.checkSelfPermission(this, wantPermission) != PackageManager.PERMISSION_GRANTED) {
                    return numbersList;
                }
                if (phoneMgr != null && !TextUtils.isEmpty(phoneMgr.getLine1Number())) {
                    String mobileNo = phoneMgr.getLine1Number();

                    if (!TextUtils.isEmpty(mobileNo) && mobileNo.startsWith("+")) {
                        mobileNo = mobileNo.substring(3);
                        numbersList.add(mobileNo);
                    } else if (!TextUtils.isEmpty(mobileNo) && mobileNo.length() == 12) {
                        mobileNo = mobileNo.substring(2);
                        numbersList.add(mobileNo);
                    } else {
                        numbersList.add(mobileNo);
                    }
//                numbersList.add(phoneMgr.getLine1Number());
                }
            }
            return numbersList;
        }catch (Exception ex){
            ex.printStackTrace();
            INSERT_LOG("getPhoneFromTelephone","Exception : "+ex.getMessage());
            return numbersList;
        }
    }

    private void requestPermission(String permission){
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, permission)){
            Toast.makeText(this, "Phone state permission allows us to get phone number. Please allow it for additional functionality.", Toast.LENGTH_LONG).show();
        }
        ActivityCompat.requestPermissions(this, new String[]{permission},PERMISSION_REQUEST_CODE);
    }


    @Override
    public void onConnected(@Nullable Bundle bundle) {
//        Toast.makeText(this, "google api client connected", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onConnectionSuspended(int i) {
//        Toast.makeText(this, "google api client suspended", Toast.LENGTH_LONG).show();  n
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
//        Toast.makeText(this, "google api client failed", Toast.LENGTH_LONG).show();
    }

}
