package com.saartak.el.magisk;

import android.content.pm.ApplicationInfo;

import androidx.annotation.NonNull;

public interface ZygotePreload {
    void doPreload(@NonNull ApplicationInfo var1);
}
