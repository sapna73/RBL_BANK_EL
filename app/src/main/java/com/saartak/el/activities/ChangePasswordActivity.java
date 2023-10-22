package com.saartak.el.activities;

import android.os.Bundle;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import android.text.TextUtils;


import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;


import com.bfil.uilibrary.widgets.textInputLayout.CustomTextInputLayout;

import com.bfil.uilibrary.dialogs.ConfirmationDialog;

import com.saartak.el.R;
import com.saartak.el.models.ChangePasswordRequestDTO;
import com.saartak.el.models.ChangePasswordResponseDTO;
import com.saartak.el.view_models.DynamicUIViewModel;

import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

import static com.bfil.uilibrary.helpers.AppConstants.REGEX_PATTERN_PASSWORD;
import static com.saartak.el.constants.AppConstant.PARAM_OLD_PASSWORD;
import static com.saartak.el.constants.AppConstant.PARAM_USER_ID;
import static com.saartak.el.dynamicui.constants.ParametersConstant.ERROR_MESSAGE_PASSWORD_CHANGED_SUCCESSFULLY;
import static com.saartak.el.dynamicui.constants.ParametersConstant.ERROR_MESSAGE_UNABLE_TO_CHANGE_PASSWORD;

public class ChangePasswordActivity extends LOSBaseActivity implements HasSupportFragmentInjector {

    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;

    @Inject
    public ViewModelProvider.Factory viewModelFactory;
    public DynamicUIViewModel viewModel;
    EditText et_new_password, et_confirm_password;
    private CustomTextInputLayout til_new_password, til_confirmPassword;
    private TextView btn_change_password;
    private Toolbar toolbar;

    String oldPassword;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

       toolbar = (Toolbar)findViewById(R.id.toolbar_changePassword);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // back button pressed
                finish();
            }
        });

        userId = getIntent().getStringExtra(PARAM_USER_ID);
        oldPassword = getIntent().getStringExtra(PARAM_OLD_PASSWORD);


        til_new_password = (CustomTextInputLayout) findViewById(R.id.til_new_passowrd);
        til_confirmPassword = (CustomTextInputLayout) findViewById(R.id.til_confirm_password);
        btn_change_password = findViewById(R.id.btn_change_password);
        et_new_password = (EditText) findViewById(R.id.edt_new_password);
        et_confirm_password = (EditText) findViewById(R.id.edt_confirm_password);

        til_new_password.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (appHelper.patternMatch(REGEX_PATTERN_PASSWORD, s.toString())) {
                    if (!TextUtils.isEmpty(til_confirmPassword.getEditText().getText().toString())) {
                        if(til_confirmPassword.getEditText().getText().toString().equalsIgnoreCase(s.toString())){
                            til_new_password.setHasValidInput(true);
                            til_confirmPassword.setHasValidInput(true);
                        }else {
                            til_new_password.setErrorMsg("Password does not match");
                            til_new_password.setHasValidInput(false);
                        }
                    } else {
                        til_new_password.setHasValidInput(true);
                    }
                } else {
                    til_new_password.setErrorMsg("Enter Valid Password");
                    til_new_password.setHasValidInput(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        til_confirmPassword.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (til_new_password.hasValidInput()) {
                    if (appHelper.patternMatch(REGEX_PATTERN_PASSWORD, s.toString())) {
                        if (!TextUtils.isEmpty(til_new_password.getEditText().getText().toString())
                                && til_new_password.getEditText().getText().toString().equalsIgnoreCase(s.toString())) {
                            til_confirmPassword.setHasValidInput(true);
                            til_new_password.setHasValidInput(true);
                        } else {
                            til_confirmPassword.setErrorMsg("Password does not match");
                            til_confirmPassword.setHasValidInput(false);
                        }
                    } else {
                        til_confirmPassword.setErrorMsg("Enter Valid Password");
                        til_confirmPassword.setHasValidInput(false);
                    }
                } else {
                    til_confirmPassword.setErrorMsg("Enter Valid Password");
                    til_confirmPassword.setHasValidInput(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        configureDagger();
        configureViewModel();

        btn_change_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(userId)) {
                    if (!TextUtils.isEmpty(oldPassword)) {
                        if (!TextUtils.isEmpty(til_new_password.getEditText().getText().toString())) {
                            if (!TextUtils.isEmpty(til_confirmPassword.getEditText().getText().toString())) {

                                if(til_new_password.hasValidInput()) {
                                    if(til_confirmPassword.hasValidInput()) {
                                        // TODO: CHANGE PASSWORD SERVICE CALL
                                        changePasswordServiceCall(
                                                til_new_password.getEditText().getText().toString(),
                                                til_confirmPassword.getEditText().getText().toString());
                                    }else{
                                        appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT,
                                                til_confirmPassword.getErrorMsg());
                                    }
                                }else{
                                    appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT,
                                            til_new_password.getErrorMsg());
                                }
                            } else {
                                appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT,
                                        "Enter Confirm Password");
                            }
                        } else {
                            appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT,
                                    "Enter New Password");
                        }
                    } else {
                        appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT,
                                "Old Password Is Empty");
                    }
                } else {
                    appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT,
                            "User ID Is Empty");
                }
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
    protected void onResume() {
        super.onResume();
    }

    private void changePasswordServiceCall(String newPassword, String confirmPassword) {
        try {
            appHelper.getDialogHelper().getLoadingDialog().showGIFLoading();

            ChangePasswordRequestDTO changePasswordRequestDTO = new ChangePasswordRequestDTO();

            changePasswordRequestDTO.setUserID(userId);
            changePasswordRequestDTO.setUsername(userId);
            changePasswordRequestDTO.setOldPassword(oldPassword);
            changePasswordRequestDTO.setNewPassword(newPassword);
            changePasswordRequestDTO.setConfirmPassword(confirmPassword);

            viewModel.changePasswordServiceCall(changePasswordRequestDTO);

            if (viewModel.getChangePasswordResponseDTOLiveDataList() != null) {
                Observer observer = new Observer() {
                    @Override
                    public void onChanged(Object o) {

                        appHelper.getDialogHelper().getLoadingDialog().closeDialog();

                        List<ChangePasswordResponseDTO> changePasswordResponseDTOList = (List<ChangePasswordResponseDTO>) o;

                        viewModel.getChangePasswordResponseDTOLiveDataList().removeObserver(this);

                        if (changePasswordResponseDTOList != null && changePasswordResponseDTOList.size() > 0 &&
                                TextUtils.isEmpty(changePasswordResponseDTOList.get(0).getErrorMsg()) &&
                                changePasswordResponseDTOList.get(0).getErrorCode() == 0) {
                            appHelper.getDialogHelper().getConfirmationDialog().show(ConfirmationDialog.ALERT,
                                    ERROR_MESSAGE_PASSWORD_CHANGED_SUCCESSFULLY, new ConfirmationDialog.ActionCallback() {
                                        @Override
                                        public void onAction() {
                                            finish();
                                        }
                                    });
                        } else {
                            String errorMessage = ERROR_MESSAGE_UNABLE_TO_CHANGE_PASSWORD;
                            if (changePasswordResponseDTOList != null && changePasswordResponseDTOList.size() > 0 &&
                                    !TextUtils.isEmpty(changePasswordResponseDTOList.get(0).getErrorMsg())) {
                                errorMessage = changePasswordResponseDTOList.get(0).getErrorMsg();
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
                viewModel.getChangePasswordResponseDTOLiveDataList().observe(this, observer);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            appHelper.getDialogHelper().getLoadingDialog().closeDialog();

            INSERT_LOG("changePasswordServiceCall", "Exception : " + ex.getMessage());

        }
    }

 private void INSERT_LOG(String methodName, String message){
        try{
            if(viewModel !=null){
                viewModel.insertLog(methodName,message,userId,"","ChangePasswordActivity"
                        ,CLIENT_ID,loanType,moduleType);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
