package com.swadhaar.los.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;

import com.bfil.ekyclibrary.activities.EKYCActivity;
import com.bfil.uilibrary.dialogs.ConfirmationDialog;
import com.bfil.uilibrary.helpers.AppHelper;
import com.swadhaar.los.App;
import com.swadhaar.los.api.DynamicUIWebservice;
import com.swadhaar.los.database.entity.CenterMeetingAttendanceTable;
import com.swadhaar.los.dynamicui.services.DynamicUIWebService;
import com.swadhaar.los.view_models.DynamicUIViewModel;

import java.util.HashMap;
import java.util.List;

public class LOSBaseActivity extends EKYCActivity {
    protected App mMyApp;
        AppHelper appHelper;

    public static String CLIENT_ID="";
    public static String loanType,moduleType,branchId,branchName,branchGSTcode,userName, userId,productId, roleName;
    public static boolean AUTOFILL=false,GET_APPLICATION_ID=false;
    public HashMap<String,Object> leadDetailsHashMap;
    public static String EMP_LAST_5_DIGIT="";

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,
//                WindowManager.LayoutParams.FLAG_SECURE);

        mMyApp = (App)this.getApplicationContext();
                appHelper=new AppHelper(this);
    }
    public DynamicUIWebservice getServicesAPI() {
        return DynamicUIWebService.createService(DynamicUIWebservice.class);
    }

    protected void onResume() {
        super.onResume();
        mMyApp.setCurrentActivity(this);
    }
    protected void onPause() {
        clearReferences();
        super.onPause();
    }
    protected void onDestroy() {
        clearReferences();
        super.onDestroy();
    }

    private void clearReferences(){
        Activity currActivity = mMyApp.getCurrentActivity();
        if (this.equals(currActivity))
            mMyApp.setCurrentActivity(null);
    }

    public void insertStaffActivity(DynamicUIViewModel viewModel, String centerName, String staffId, String activity, int status){
        try{
            viewModel.insertStaffActivity(centerName,staffId,activity,status);
            if (viewModel.getStringLiveData() != null) {
                Observer observer = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        viewModel.getStringLiveData().removeObserver(this);

                    }
                };
                viewModel.getStringLiveData().observe(this, observer);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

}
