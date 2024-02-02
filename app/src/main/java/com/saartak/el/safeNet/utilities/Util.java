package com.saartak.el.safeNet.utilities;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;

import androidx.appcompat.app.AlertDialog;

public class Util   {

    public static void showAlert(Context context, String title, String text, String positive,
                                 DialogInterface.OnClickListener positiveClick,
                                 Dialog.OnKeyListener onBackPressed) {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
        alertBuilder.setTitle(title);
        alertBuilder.setMessage(text);
        alertBuilder.setCancelable(false);
        alertBuilder.setOnKeyListener(onBackPressed);
        alertBuilder.setPositiveButton(positive, positiveClick);
        alertBuilder.create().show();
    }


}
