package com.saartak.el.magisk;

public class Native {

static {
        System.loadLibrary("native-lib");
        }

static native boolean isMagiskPresentNative();

        }
