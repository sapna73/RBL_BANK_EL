package com.saartak.el.activities;

import static com.saartak.el.constants.AppConstant.MESSAGE_ROOTED_DEVICE_NOT_SUPPORTED;

import android.app.Activity;
import android.os.Bundle;
import android.os.Debug;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;

import com.bfil.ekyclibrary.activities.EKYCActivity;
import com.bfil.uilibrary.dialogs.ConfirmationDialog;
import com.bfil.uilibrary.helpers.AppHelper;
import com.saartak.el.App;
import com.saartak.el.magisk.RootDetector;
import com.saartak.el.magisk.RootUtil;
import com.saartak.el.api.DynamicUIWebservice;
import com.saartak.el.dynamicui.services.DynamicUIWebService;
import com.saartak.el.view_models.DynamicUIViewModel;
import com.scottyab.rootbeer.RootBeer;

import java.io.File;
import java.util.HashMap;

public class LOSBaseActivity extends EKYCActivity {
    protected App mMyApp;
        AppHelper appHelper;

    public static String CLIENT_ID = "";
    public static String loanType, moduleType, branchId, branchName,branchGSTcode,userName, userId,productId,workFlowId, roleName="",currentStage="",currentStageID="";
    public static boolean AUTOFILL = false, GET_APPLICATION_ID=false;
    public HashMap<String,Object> leadDetailsHashMap;
    public static String EMP_LAST_5_DIGIT="";

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mMyApp = (App)this.getApplicationContext();
        appHelper=new AppHelper(this);
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

    public boolean checkRootMethod(){
        String buildTags = android.os.Build.TAGS;

        if (buildTags != null && buildTags.contains("test-keys")) {
            return true;
        }
        return false;
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
                "/system/app/magisk.apk",

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
        String[] RootPackages = {"com.noshufou.android.su", "com.noshufou.android.su.elite", "eu.chainfire.supersu",
                "com.koushikdutta.superuser", "com.thirdparty.superuser", "com.yellowes.su", "com.koushikdutta.rommanager",
                "com.koushikdutta.rommanager.license", "com.dimonvideo.luckypatcher", "com.chelpus.lackypatch",
                "com.ramdroid.appquarantine", "com.ramdroid.appquarantinepro", "com.devadvance.rootcloak", "com.devadvance.rootcloakplus",
                "de.robv.android.xposed.installer", "com.saurik.substrate", "com.zachspong.temprootremovejb", "com.amphoras.hidemyroot",
                "com.amphoras.hidemyrootadfree", "com.formyhm.hiderootPremium", "com.formyhm.hideroot", "me.phh.superuser",
                "eu.chainfire.supersu.pro", "com.kingouser.com", "com.topjohnwu.magisk"
        };
        String[] RootBinaries = {"su", "busybox", "supersu", "Superuser.apk", "KingoUser.apk", "SuperSu.apk", "magisk"};
        String[] RootPropertiesKeys = {"ro.build.selinux", "ro.debuggable", "service.adb.root", "ro.secure"};

        for (String path : paths) {
            if (new File(path).exists()) {
                return true;
            }
        }
        return false;
    }

    public boolean checkRooted() {
        if(RootUtil.checkRootMethod3()|| RootUtil.checkRootMethod1()|| RootUtil.checkRootMethod2()||RootUtil.isDeviceRooted())
            return true;
        return false;
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
        return canExecuteCommand("/system/xbin/which su")
                || canExecuteCommand("/system/bin/which su") || canExecuteCommand("which su");
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
