package com.saartak.el.magisk;

import android.content.pm.ApplicationInfo;

import androidx.annotation.NonNull;

public class AppZygotePreload implements ZygotePreload {
    @Override
    public void doPreload(@NonNull ApplicationInfo appInfo) {
        System.loadLibrary("native-lib");
    }
}