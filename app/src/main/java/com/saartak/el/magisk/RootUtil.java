package com.saartak.el.magisk;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

public class RootUtil {

    public static boolean isDeviceRooted() {
        return checkRootMethod1() || checkRootMethod2() || checkRootMethod3();
    }

    public static boolean checkRootMethod1() {
        String buildTags = android.os.Build.TAGS;
        return buildTags != null && buildTags.contains("test-keys");
    }

    public static boolean checkRootMethod2() {
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
            if (new File(path).exists()) return true;
        }
        return false;
    }

    public static boolean checkRootMethod3() {
        Process process = null;
        try {
            process = Runtime.getRuntime().exec(new String[] { "/system/xbin/which", "su" });
            BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
            if (in.readLine() != null) return true;
            return false;
        } catch (Throwable t) {
            return false;
        } finally {
            if (process != null) process.destroy();
        }
    }

}
